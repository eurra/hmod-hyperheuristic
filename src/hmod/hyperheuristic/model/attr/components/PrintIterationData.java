
package hmod.hyperheuristic.model.attr.components;

import hmod.core.Operator;
import hmod.core.AlgorithmException;
import hmod.core.components.IterationData;
import optefx.util.output.AlgorithmOutputManager;

/**
 * Operator related to the strategic oscillation model.
 * Prints information regarding the oscillation data at the current iteration.
 * @author Enrique Urra C.
 */
public class PrintIterationData implements Operator
{
    private SOBasicData soDataHandler;
    private IterationData iterationHandler;

    public void setSODataHandler(SOBasicData handler)
    {
        this.soDataHandler = handler;
    }

    public void setIterationData(IterationData handler)
    {
        this.iterationHandler = handler;
    }

    @Override
    public boolean doOperation() throws AlgorithmException
    {
        AlgorithmOutputManager.println(OutputIds.SO_DATA_SHEET,
            iterationHandler.getCurrentIteration() + "\t" +
            soDataHandler.getPiMultiplier() + "\t" +
            soDataHandler.getOscillationRate() + "\t" +
            soDataHandler.getNoGlobalImproveIterations() + "\t" +
            soDataHandler.getNoLocalImproveIterations() 
       );
        
       return true;
    }
}
