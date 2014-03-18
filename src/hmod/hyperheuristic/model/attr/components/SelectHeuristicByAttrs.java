
package hmod.hyperheuristic.model.attr.components;

import hmod.hyperheuristic.model.attr.HeuristicStats;
import hmod.core.Operator;
import hmod.core.Step;
import hmod.core.AlgorithmException;
import hmod.hyperheuristic.model.selection.components.LLStepData;
import hmod.hyperheuristic.model.selection.components.LLStepSetData;
import java.util.Map;
import optefx.util.random.RandomTool;

/**
 * Operator related to the Attributive memory model.
 * Performs the actual selection based on the data retrieved from the generated
 * solution attributes.
 * @author Enrique Urra C.
 */
public class SelectHeuristicByAttrs implements Operator
{
    private LLStepData stepHandler;
    private LLStepSetData stepSetHandler;
    private SOBasicData soDataHandler;
    private SOSelectionData soSelectionHandler;
    
    public void setStepData(LLStepData handler)
    {
        this.stepHandler = handler;
    }

    public void setStepSetHandler(LLStepSetData handler)
    {
        this.stepSetHandler = handler;
    }

    public void setSODataHandler(SOBasicData handler)
    {
        this.soDataHandler = handler;
    }

    public void setSOSelectionHandler(SOSelectionData handler)
    {
        this.soSelectionHandler = handler;
    }

    @Override
    public boolean doOperation() throws AlgorithmException
    {        
        // The selection procedure is based on the following related values:
        // - The current impact rate mean for each heuristic (IR).
        // - The current oscillation rate (a).
        // - The strategy change speed (u).
        // - The current number of iterations passed since the last local 
        //   solution improvement (t').
        // - The number of heuristics (h).
        //
        // For each heuristic, a selection score (HS) is calculated through the
        // following formula:
        //                 HS = (1 + IR * [g(t')*(1 - a)- 1]) ^ (h * (1 + a))
        // in where
        //        g(t') = 1 - [ (exp(pi/2) - 1) / (u * t' + exp(pi/2) - 1) ]
        // is a function that depends on the current (t') value and that moves
        // between the 0 and 1 values. When (t') is near zero (recent local 
        // improvement), g(t') also tends to zero. When (t') tends to an higher
        // value (distant local improvement), g(t') tends to 1. It is important
        // to remark that the g(t') function tries to emulate the logarithmic
        // advancement of the (a) value from intensification to diversification,
        // which depends on the number of iterations passed since a new 
        // candidate global optima was found. The (t') value is never restarted,  
        // then the g(t') only aproximates to the 1 value and never crosses such
        // maximum, in contrast to the logarithmic function mentioned above, 
        // which at some time can cross the 3pi/2 maximum and the strategy is
        // restarted as well.
        //
        // The formula allows to achieve the following effects in the current 
        // selection strategy, related to the variables used:
        // - When (a) is near 1 (intensification), the score mainly depends on 
        //   the (IR) value, giving a higher score if such value is low.
        //   Therefore, when the search is intensifying, heuristics with a low 
        //   impact tend to be selected.
        // - When (a) is near 0 (diversification), the score depends on the (t')
        //   value:
        //     * If (t') is near 0 then g(t') is near 0, producing the same 
        //       effect as (a) is near 1. This allows to prefer heuristics with 
        //       a low impact when diversifying and a local optima was recently 
        //       found, allowing to focus the search on possible new places of  
        //       the search space.
        //     * If (t') is higher then g(t') is near 1, and as (a) is near 0, 
        //       the (IR) value influence in the formula is neutralized, giving 
        //       to all heuristics a score close to 1. This allows to select the 
        //       heuristics with an approximate uniform probability when 
        //       diversifying and the local optima cannot be improved, therefore
        //       highly disruptive heuristics have good chances to be selected 
        //       in this phase of the strategy.
        Map<Step, HeuristicStats> stats = soSelectionHandler.getHeuristicStats();
        double oscillationRate = soDataHandler.getOscillationRate();
        double changeSpeed = soDataHandler.getStrategyChangeSpeed();
        int nonImprovingIterations = soDataHandler.getNoLocalImproveIterations();
        Step[] heuristics = stepSetHandler.getHeuristics();
        double[] heuristicsFinalScores = new double[heuristics.length];
        double finalScoresSum = 0.0;
        double currSubScore = 1 - ((Math.exp(Math.PI / 2.0) - 1) / (changeSpeed * nonImprovingIterations + Math.exp(Math.PI / 2.0) - 1));
        
        for(int i = 0; i < heuristics.length; i++)
        {
            HeuristicStats currStats = stats.get(heuristics[i]);
            
            double currMean = currStats.getChangeImpactMean();            
            double currFinalScore = Math.pow(1 + currMean * (currSubScore * (1 - oscillationRate) - 1), (double)heuristics.length);
            
            finalScoresSum += currFinalScore;
            heuristicsFinalScores[i] = currFinalScore;
        }
        
        // By using a roulette wheel selection-scheme, an heuristic is finally
        // selected according to its final score
        double prob = RandomTool.getDouble();
        double acumProb = 0.0;
        int selectedIndex = -1;
        
        for(int i = 0; i < heuristics.length && selectedIndex == -1; i++)
        {
            double rangeDiff = heuristicsFinalScores[i] / finalScoresSum;
            double upperRange = acumProb + rangeDiff;
            
            if(prob >= acumProb && prob <= upperRange)
                selectedIndex = i;
            else
                acumProb += rangeDiff;
        }
            
        Step selected = heuristics[selectedIndex];
        stepHandler.setCurrentLLStep(selected);
        
        return true;
    }
}