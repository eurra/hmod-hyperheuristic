
package hmod.hyperheuristic.model.selection.components;

import hmod.core.AlgorithmException;
import hmod.core.Operator;
import hmod.core.Step;

/**
 *
 * @author Enrique Urra C.
 */
public class SelectInitHeuristic implements Operator
{
    private LLStepData llStepData;
    private LLStepSetData llStepSetData;

    public void setLLStepData(LLStepData llStepData)
    {
        this.llStepData = llStepData;
    }

    public void setLLStepSetData(LLStepSetData llStepSetData)
    {
        this.llStepSetData = llStepSetData;
    }

    @Override
    public void execute() throws AlgorithmException
    {
        Step initHeuristic = llStepSetData.getInitHeuristic();
        llStepData.setCurrentLLStep(initHeuristic);
    }
}
