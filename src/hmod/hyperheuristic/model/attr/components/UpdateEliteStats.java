
package hmod.hyperheuristic.model.attr.components;

import hmod.hyperheuristic.model.attr.HHAttrSolution;
import hmod.shh.attr.AttrMemory;
import hmod.hyperheuristic.model.attr.AttrMemoryFactory;
import hmod.core.Operator;
import hmod.core.AlgorithmException;
import hmod.core.components.IterationData;
import hmod.shh.SHSolutionData;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Operator related to the strategic oscillation acceptance.
 * Updates the elite stats of the search.
 * @author Enrique Urra C.
 */
public class UpdateEliteStats implements Operator
{
    private SHSolutionData<HHAttrSolution> solutionHandler;
    private SOAcceptanceData soAcceptanceHandler;
    private AttrMemoryData eliteAttrMemoryHandler;
    private IterationData iterationHandler;

    public void setSolutionData(SHSolutionData solutionHandler)
    {
        this.solutionHandler = solutionHandler;
    }

    public void setSOAcceptanceHandler(SOAcceptanceData handler)
    {
        this.soAcceptanceHandler = handler;
    }

    public void setEliteAttrMemoryHandler(AttrMemoryData handler)
    {
        this.eliteAttrMemoryHandler = handler;
    }

    public void setIterationData(IterationData iterationHandler)
    {
        this.iterationHandler = iterationHandler;
    }

    @Override
    public boolean doOperation() throws AlgorithmException
    {
        // We check if the data is not initialized
        AttrMemory eliteMemory = eliteAttrMemoryHandler.getMemory();
        LinkedList<HHAttrSolution> eliteList;
        
        if(eliteMemory == null)
        {
            eliteMemory = AttrMemoryFactory.getInstance().newMemory();
            eliteList = new LinkedList<>();
            eliteAttrMemoryHandler.setMemory(eliteMemory);
            soAcceptanceHandler.setEliteSolutions(eliteList);
        }
        else
        {
            eliteList = soAcceptanceHandler.getEliteSolutions();
        }
                
        boolean added = false;
        int currElites = eliteList.size();
        HHAttrSolution currentSolution = solutionHandler.getInputSolution();
        
        // If the current elite list is empty, the solution is always added
        if(currElites == 0)
        {
            eliteList.add(currentSolution);
            added = true;
        }
        else
        {
            // Otherwise, if the current count of elite solution is below the 
            // maximum size, or the generated solution outperforms the worser  
            // elite one, the list MAY be updated        
            int maxElites = soAcceptanceHandler.getEliteSolutionsMax();        
            HHAttrSolution bestWorstSolution = eliteList.size() == 0 ? null : eliteList.getLast();

            if(currElites < maxElites || bestWorstSolution.compareTo(currentSolution) < 0)
            {
                // We check the list to find a proper position in which the solution
                // will be added            
                ListIterator<HHAttrSolution> iterator = eliteList.listIterator(currElites);

                while(iterator.hasPrevious())
                {
                    HHAttrSolution indexSolution = iterator.previous();
                    int compResult = indexSolution.compareTo(currentSolution);

                    // If while searching the list, we found an identical 
                    // solution to the one to be added, the process is cancelled
                    if(compResult == 0)
                    {
                        return false;
                    }
                    else if(compResult > 0)
                    {
                        iterator.next();
                        break;
                    }
                }

                // At this point, we have found the position in which the 
                // new elite solution must be added
                iterator.add(currentSolution);
                currElites++;
                added = true;
                
                // We check if is necessary to remove the worst solution from   
                // the elite group, beside its statistics
                if(currElites > maxElites)
                {
                    HHAttrSolution last = eliteList.removeLast();
                    eliteMemory.removeReading(last.getAttributes());
                }
            }
        }
        
        // If the solution was added, then we add its data to the stats
        if(added)
        {
            eliteMemory.addReading(currentSolution.getAttributes(), iterationHandler.getCurrentIteration());
            return true;
        }
        
        return false;
    }
}