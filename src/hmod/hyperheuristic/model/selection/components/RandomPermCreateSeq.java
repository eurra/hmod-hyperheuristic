
package hmod.hyperheuristic.model.selection.components;

import hmod.core.Operator;
import hmod.core.Step;
import hmod.core.AlgorithmException;
import optefx.util.random.RandomTool;

/**
 *
 * @author Enrique Urra C.
 */
public class RandomPermCreateSeq implements Operator
{
    private RandomPermData permData;
    private LLStepSetData stepSetHandler;

    public void setRandomPermHandler(RandomPermData permData)
    {
        this.permData = permData;
    }

    public void setStepSetHandler(LLStepSetData stepSetHandler)
    {
        this.stepSetHandler = stepSetHandler;
    }

    @Override
    public void execute() throws AlgorithmException
    {        
        Step[] heuristics = stepSetHandler.getHeuristics();        
        Step[] sequence = RandomTool.fastArrayShuffle(heuristics);
        
        permData.setCurrentSequence(sequence);
        permData.setCurrentIteration(0);
        permData.setMaxIteration(sequence.length);
    }
}
