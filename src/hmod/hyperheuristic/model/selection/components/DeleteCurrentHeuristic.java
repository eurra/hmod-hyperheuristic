
package hmod.hyperheuristic.model.selection.components;

import hmod.core.AlgorithmException;
import hmod.core.Operator;

/**
 *
 * @author Enrique Urra C.
 */
public class DeleteCurrentHeuristic implements Operator
{
    private LLStepData stepData;

    public void setStepData(LLStepData stepData)
    {
        this.stepData = stepData;
    }

    @Override
    public void execute() throws AlgorithmException
    {
        stepData.setCurrentLLStep(null);
    }
}
