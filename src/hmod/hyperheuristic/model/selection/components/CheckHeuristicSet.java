
package hmod.hyperheuristic.model.selection.components;

import hmod.core.AlgorithmException;
import hmod.core.BooleanOperator;
import hmod.core.Step;

/**
 *
 * @author Enrique Urra C.
 */
public class CheckHeuristicSet extends BooleanOperator
{
    private LLStepData stepData;

    public void setStepData(LLStepData stepData)
    {
        this.stepData = stepData;
    }

    @Override
    public Boolean evaluate() throws AlgorithmException
    {
        Step currStep = stepData.getCurrentLLStep();
        return currStep != null;
    }
}
