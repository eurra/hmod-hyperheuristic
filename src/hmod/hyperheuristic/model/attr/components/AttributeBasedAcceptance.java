
package hmod.hyperheuristic.model.attr.components;

import hmod.hyperheuristic.model.attr.HHAttrSolution;
import hmod.shh.attr.AttrReadingCollection;
import hmod.shh.attr.AttrMemory;
import hmod.core.Operator;
import hmod.hyperheuristic.model.selection.components.SimpleAcceptanceCheck;
import java.util.Iterator;

/**
 * Operator related to the strategic oscillation acceptance.
 * Performs the acceptance based on solution attributes and the strategy state.
 * @author Enrique Urra C.
 */
public class AttributeBasedAcceptance extends SimpleAcceptanceCheck<HHAttrSolution> implements Operator
{
    private SOBasicData soDataHandler;
    private SOAcceptanceData soAcceptanceHandler;
    private AttrMemoryData generalMemoryHandler;
    private AttrMemoryData eliteMemoryHandler;

    public void setSODataHandler(SOBasicData handler)
    {
        this.soDataHandler = handler;
    }
    
    public void setSOAcceptanceHandler(SOAcceptanceData handler)
    {
        this.soAcceptanceHandler = handler;
    }

    public void setGeneralAttrMemoryHandler(AttrMemoryData handler)
    {
        this.generalMemoryHandler = handler;
    }

    public void setEliteAttrMemoryHandler(AttrMemoryData handler)
    {
        this.eliteMemoryHandler = handler;
    }

    private double calculateStrategyScore(AttrReadingCollection attrs, double oscRate, AttrMemory eliteMemory, AttrMemory generalMemory)
    {
        // The strategy is based on two sub-scores, one for intensification and 
        // other for diversification.
        // Consider the following elements:
        // - <Ns> represents the count of attributes present in the evaluated
        //   solution.
        // - <Si> represents the frequency percentage sum of the elite 
        //   solutions stats obtained from whose attributes that are actually 
        //   present in both the evaluated solution and the stats.
        // - <Sd> represents the frequency percentage sum of the general 
        //   solutions stats obtained from whose attributes that are actually 
        //   present in both the evaluated solution and the stats.

        // The intensification score <Pi> is calculated by the following
        // expression:
        //      Pi = Si / Ns
        // which describes the proportion of present attributes on both the
        // evaluated solution and the elite stats, ponderated by the frequency 
        // of each attribute. While more greater a stats' attribute frequency 
        // is, more score is awarded by the solution if it hold such attribute, 
        // which allows to reward solutions whose attributes are similar to the 
        // best solutions found during the search.

        // The diversification score <Pd> is calculated by the following
        // expression:
        //      Pd = 1 - (Sd / Ns)
        // which describes the inverse proportion of present attributes on 
        // both the evaluated solution and the general stats, ponderated by the 
        // frequency of each attribute. While more lower a stats' attribute 
        // frequency is, more lower the <(Sd / Ns)> value is, then more greater 
        // the score will be, which allows to reward solutions whose attributes 
        // differ to the general tendency found during the search. Note that 
        // when a new attribute is found in the evaluated solution, its related
        // frequency in the stats is considered as zero, then such finding is 
        // rewarded in the <Sd> value.        
        Iterator<String> iterator = attrs.getIterator();
        double intensificationPercSum = 0.0, diversificationPercSum = 0.0;
        
        while(iterator.hasNext())
        {
            String attr = iterator.next();
            
            if(eliteMemory.hasAttribute(attr))
                intensificationPercSum += eliteMemory.getReadingFrequency(attr);

            if(generalMemory.hasAttribute(attr))
                diversificationPercSum += generalMemory.getReadingFrequency(attr);
        }
        
        int prevAttrsCount = attrs.getCount();
        double intensificationScore = intensificationPercSum / (double)prevAttrsCount;
        double diversificationScore = 1.0 - (diversificationPercSum / (double)prevAttrsCount);
        
        // A final weighted score is calculated based on the current value of 
        // the oscillation rate. Let be (a) the rate, then the final score 
        // (P) is calculated by the following formula:
        //                     P = a * Pi + (1 - a) * Pd
        // If the (a) value is near 1, i.e. the search is focused on 
        // intensification, the related score is prioritized over the 
        // diversification one, and for the inverse case, when the (a) value
        // is near 0, the diversification score is prioritized over the 
        // intensification one.
        return oscRate * intensificationScore + (1 - oscRate) * diversificationScore;
    }

    @Override
    public boolean checkAcceptance(HHAttrSolution prevSolution, HHAttrSolution newSolution)
    {
        // The new solution may be accepted only if it is better than the worser
        // solution within the elite set
        HHAttrSolution lastElite = soAcceptanceHandler.getEliteSolutions().getLast();
        
        if(newSolution.compareTo(lastElite) < 0)
        {
            return false;
        }
        else
        {
            // To evaluate the acceptance, we calculate the strategy score for 
            // either the new and the previous solution. The former is accepted
            // only if its score is bettern than the latter one.
            double oscRate = soDataHandler.getOscillationRate();
            AttrMemory generalMemory = generalMemoryHandler.getMemory();
            AttrMemory eliteMemory = eliteMemoryHandler.getMemory();
            
            double newScore = calculateStrategyScore(newSolution.getAttributes(), oscRate, eliteMemory, generalMemory);
            double prevScore = calculateStrategyScore(prevSolution.getAttributes(), oscRate, eliteMemory, generalMemory);

            if(newScore > prevScore)
                return true;
            else
                return false;
        }
    }
}