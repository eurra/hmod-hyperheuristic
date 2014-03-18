
package hmod.hyperheuristic.model.selection.components;

import hmod.core.Operator;
import hmod.core.Step;
import hmod.core.AlgorithmException;

/**
 *
 * @author Enrique Urra C.
 */
public class RandomPermNext implements Operator
{
    private RandomPermData permData;
    private LLStepData stepHandler;

    public void setRandomPermHandler(RandomPermData permData)
    {
        this.permData = permData;
    }

    public void setStepData(LLStepData stepHandler)
    {
        this.stepHandler = stepHandler;
    }
    
    @Override
    public void execute() throws AlgorithmException
    {        
        Step[] sequence = permData.getCurrentSequence();        
        int currIndex = permData.getCurrentIteration();
        stepHandler.setCurrentLLStep(sequence[currIndex]);
    }
}
