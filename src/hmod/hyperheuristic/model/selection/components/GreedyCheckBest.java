
package hmod.hyperheuristic.model.selection.components;

import hmod.core.AlgorithmException;
import hmod.core.BooleanOperator;
import hmod.hyperheuristic.model.selection.HHSolution;

/**
 *
 * @author Enrique Urra C.
 */
public class GreedyCheckBest extends BooleanOperator
{
    private GreedyData greedyData;
    private SHSolutionData solutionHandler;

    public void setGreedyHandler(GreedyData greedyData)
    {
        this.greedyData = greedyData;
    }

    public void setSolutionData(SHSolutionData solutionHandler)
    {
        this.solutionHandler = solutionHandler;
    }
    
    @Override
    public Boolean evaluate() throws AlgorithmException
    {        
        HHSolution currInputSolution = solutionHandler.getInputSolution();        
        HHSolution currBestSolution = greedyData.getBestSolution();
        
        if(currBestSolution == null || currBestSolution.compareTo(currInputSolution) <= 0)
        {            
            greedyData.setBestIndex(greedyData.getCurrentIteration());
            greedyData.setBestSolution(currInputSolution);
            return true;
        }
        
        return false;
    }
}
