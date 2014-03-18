
package hmod.hyperheuristic.model.selection.components;

import hmod.core.Operator;
import hmod.core.Step;
import hmod.core.AlgorithmException;

/**
 *
 * @author Enrique Urra C.
 */
public class GreedyNextHeuristic implements Operator
{
    private GreedyData greedyData;
    private LLStepData stepHandler;
    private LLStepSetData stepSetHandler;

    public void setGreedyHandler(GreedyData greedyData)
    {
        this.greedyData = greedyData;
    }

    public void setStepData(LLStepData stepHandler)
    {
        this.stepHandler = stepHandler;
    }
    
    public void setStepSetHandler(LLStepSetData stepSetHandler)
    {
        this.stepSetHandler = stepSetHandler;
    }

    @Override
    public void execute() throws AlgorithmException
    {
        Step[] heuristics = stepSetHandler.getHeuristics();        
        stepHandler.setCurrentLLStep(heuristics[greedyData.getCurrentIteration()]);
    }
}
