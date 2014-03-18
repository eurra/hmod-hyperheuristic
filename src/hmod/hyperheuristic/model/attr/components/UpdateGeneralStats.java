
package hmod.hyperheuristic.model.attr.components;

import hmod.hyperheuristic.model.attr.HHAttrSolution;
import hmod.shh.attr.AttrMemory;
import hmod.hyperheuristic.model.attr.AttrMemoryFactory;
import hmod.core.Operator;
import hmod.core.AlgorithmException;
import hmod.core.components.IterationData;
import hmod.shh.SHSolutionData;

/**
 * Operator related to the strategic oscillation acceptance.
 * Updates the general stats of the search.
 * @author Enrique Urra C.
 */
public class UpdateGeneralStats implements Operator
{
    private SHSolutionData<HHAttrSolution> solutionHandler;
    private AttrMemoryData generalAttrMemoryHandler;
    private IterationData iterationHandler;

    public void setSolutionData(SHSolutionData solutionHandler)
    {
        this.solutionHandler = solutionHandler;
    }

    public void setGeneralAttrMemoryHandler(AttrMemoryData handler)
    {
        this.generalAttrMemoryHandler = handler;
    }
    
    public void setIterationData(IterationData iterationHandler)
    {
        this.iterationHandler = iterationHandler;
    }

    @Override
    public boolean doOperation() throws AlgorithmException
    {
        HHAttrSolution solution = solutionHandler.getInputSolution();
        AttrMemory generalMemory = generalAttrMemoryHandler.getMemory();
        
        // if the memory is not initialized, we create one
        if(generalMemory == null)
        {
            generalMemory = AttrMemoryFactory.getInstance().newMemory();
            generalAttrMemoryHandler.setMemory(generalMemory);
        }
        
        generalMemory.addReading(solution.getAttributes(), iterationHandler.getCurrentIteration());
        return true;
    }
}
