
package hmod.hyperheuristic.model.selection.components;

import hmod.core.AlgorithmException;
import hmod.core.BooleanOperator;
import hmod.hyperheuristic.model.selection.HHSolution;

/**
 *
 * @author Enrique Urra C.
 */
public class CheckBestImproved extends BooleanOperator
{
    private SHSolutionData solutionHandler;

    public void setSolutionData(SHSolutionData solutionHandler)
    {
        this.solutionHandler = solutionHandler;
    }

    @Override
    public Boolean evaluate() throws AlgorithmException
    {
        HHSolution inputSolution = solutionHandler.getInputSolution();
        HHSolution bestSolution = solutionHandler.getBestSolution();
        
        if(inputSolution != null && bestSolution != null && inputSolution.compareTo(bestSolution) <= 0)
            return false;
        
        return true;
    }
}