
package hmod.hyperheuristic.model.selection.components;

import hmod.core.Operator;
import hmod.core.Step;
import hmod.core.AlgorithmException;
import optefx.util.random.RandomTool;

/**
 *
 * @author Enrique Urra C.
 */
public class RandomSelect implements Operator
{
    private LLStepSetData stepSetHandler;
    private LLStepData stepHandler;

    public void setStepSetHandler(LLStepSetData stepSetHandler)
    {
        this.stepSetHandler = stepSetHandler;
    }

    public void setStepData(LLStepData stepHandler)
    {
        this.stepHandler = stepHandler;
    }
    
    @Override
    public void execute() throws AlgorithmException
    {
        Step[] heuristics = stepSetHandler.getHeuristics();
        Step sel = heuristics[RandomTool.getInt(heuristics.length)];

        stepHandler.setCurrentLLStep(sel);
    }
}