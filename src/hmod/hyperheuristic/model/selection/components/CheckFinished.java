
package hmod.hyperheuristic.model.selection.components;

import hmod.core.components.IterationData;
import hmod.core.components.TimeElapsedData;
import hmod.core.AlgorithmException;
import hmod.core.BooleanOperator;

/**
 *
 * @author Enrique Urra C.
 */
public class CheckFinished extends BooleanOperator
{
    private IterationData iterationHandler;
    private TimeElapsedData timeElapsedHandler;

    public void setIterationData(IterationData handler)
    {
        this.iterationHandler = handler;
    }
    
    public void setTimeElapsedData(TimeElapsedData handler)
    {
        this.timeElapsedHandler = handler;
    }
    
    @Override
    public Boolean evaluate() throws AlgorithmException
    {
        return iterationHandler.getIterationFinished() || timeElapsedHandler.getTimeFinished();
    }
}
