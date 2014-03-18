
package hmod.hyperheuristic.model.selection.components;

import hmod.core.Operator;
import hmod.core.Step;
import hmod.core.AlgorithmException;

/**
 *
 * @author Enrique Urra C.
 */
public class GreedyInit implements Operator
{
    private GreedyData greedyData;
    private LLStepSetData stepSetHandler;

    public void setGreedyHandler(GreedyData greedyData)
    {
        this.greedyData = greedyData;
    }

    public void setStepSetHandler(LLStepSetData heuristicSetHander)
    {
        this.stepSetHandler = heuristicSetHander;
    }
    
    @Override
    public void execute() throws AlgorithmException
    {
        Step[] heuristics = stepSetHandler.getHeuristics();

        greedyData.setMaxIteration(heuristics.length);
        greedyData.setCurrentIteration(0);        
        greedyData.setBestIndex(-1);
        greedyData.setBestSolution(null);
    }
}
