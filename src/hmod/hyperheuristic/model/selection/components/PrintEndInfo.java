
package hmod.hyperheuristic.model.selection.components;

import hmod.core.components.TimeElapsedData;
import hmod.core.Operator;
import hmod.core.AlgorithmException;
import hmod.hyperheuristic.model.selection.HHSolution;
import optefx.util.output.OutputManager;

/**
 *
 * @author Enrique Urra C.
 */
public class PrintEndInfo implements Operator
{
    private SHSolutionData solutionHandler;
    private TimeElapsedData timeElapsedHandler;

    public void setSolutionData(SHSolutionData handler)
    {
        this.solutionHandler = handler;
    }
    
    public void setTimeElapsedData(TimeElapsedData handler)
    {
        this.timeElapsedHandler = handler;
    }
    
    @Override
    public void execute() throws AlgorithmException
    {
        HHSolution best = solutionHandler.getBestSolution();
        double totalSecs = timeElapsedHandler.getElapsedTime() / 1000.0;
        
        OutputManager.println(OutputIds.EXEC_INFO, "- Execution time (sec.): " + totalSecs);
        OutputManager.println(OutputIds.EXEC_INFO, "- Best result (fitness): " + best.getEvaluation());
        OutputManager.println(OutputIds.RESULT_SHEET, best.getEvaluation() + "\t" + totalSecs);
    }
}
