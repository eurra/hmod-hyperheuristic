
package hmod.hyperheuristic.model.selection.components;

import hmod.core.Operator;
import hmod.core.Step;
import hmod.core.AlgorithmException;
import hmod.hyperheuristic.model.selection.HHSolution;
import optefx.util.output.OutputManager;

/**
 *
 * @author Enrique Urra C.
 */
public class SelectState implements Operator
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
        HHSolution currInputSolution = solutionHandler.getInputSolution();        
        Step currHeuristic = stepHandler.getCurrentLLStep();
        
        solutionHandler.setSelectedSolution(currInputSolution);
        stepHandler.setSelectedLLStep(currHeuristic);
        
        HHSolution currBestSolution = solutionHandler.getBestSolution();
        
        if(currBestSolution == null || currBestSolution.compareTo(currInputSolution) < 0)
        {
            solutionHandler.setBestSolution(currInputSolution);
            OutputManager.println(OutputIds.NEW_BEST_FITNESS, "New best solution: " + currInputSolution.getEvaluation());
        }
    }
}
