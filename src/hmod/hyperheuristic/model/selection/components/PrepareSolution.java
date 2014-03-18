
package hmod.hyperheuristic.model.selection.components;

import hmod.core.Operator;
import hmod.core.AlgorithmException;

/**
 *
 * @author Enrique Urra C.
 */
public class PrepareSolution implements Operator
{
    private SHSolutionData solutionHandler;

    public void setSolutionData(SHSolutionData solutionHandler)
    {
        this.solutionHandler = solutionHandler;
    }
    
    @Override
    public void execute() throws AlgorithmException
    {
        solutionHandler.setInputSolution(solutionHandler.getAcceptedSolution());
    }
    
}
