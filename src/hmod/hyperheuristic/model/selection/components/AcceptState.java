
package hmod.hyperheuristic.model.selection.components;

import hmod.core.Operator;
import hmod.core.Step;
import hmod.core.AlgorithmException;
import hmod.hyperheuristic.model.selection.HHSolution;

/**
 *
 * @author Enrique Urra C.
 */
public class AcceptState implements Operator
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
    public void execute() throws AlgorithmException
    {        
        HHSolution currSelectedSolution = solutionHandler.getSelectedSolution();
        Step selectedHeuristic = stepHandler.getSelectedLLStep(); 
        
        solutionHandler.setAcceptedSolution(currSelectedSolution);
        stepHandler.setAcceptedLLStep(selectedHeuristic);
    }
}
