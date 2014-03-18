
package hmod.hyperheuristic.model.selection.components;

import hmod.core.AlgorithmException;
import hmod.core.BooleanOperator;
import hmod.hyperheuristic.model.selection.HHSolution;

/**
 *
 * @author Enrique Urra C.
 */
public class CheckSolutionImproved extends BooleanOperator
{
    private SHSolutionData solutionHandler;
    private LLStepData stepHandler;

    public void setSolutionData(SHSolutionData solutionHandler)
    {
        this.solutionHandler = solutionHandler;
    }

    public void setStepData(LLStepData stepHandler)
    {
        this.stepHandler = stepHandler;
    }
    
    @Override
    public Boolean evaluate() throws AlgorithmException
    {        
        if(stepHandler.getCurrentLLStep() == null)
            return false;
        
        HHSolution acceptedSolution = solutionHandler.getAcceptedSolution();
        HHSolution inputSolution = solutionHandler.getInputSolution();
        
        if(acceptedSolution != null && inputSolution != null && inputSolution.compareTo(acceptedSolution) <= 0)
            return false;
        
        return true;
    }
}