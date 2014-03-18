
package hmod.hyperheuristic.model.attr.components;

import hmod.core.Operator;
import hmod.core.AlgorithmException;

/**
 * Operator related to the strategic oscillation model.
 * Force a restart on the current strategy.
 * @author Enrique Urra C.
 */
public class StrategyRestart implements Operator
{
    private SOBasicData soDataHandler;

    public void setSODataHandler(SOBasicData handler)
    {
        this.soDataHandler = handler;
    }

    @Override
    public boolean doOperation() throws AlgorithmException
    {
        soDataHandler.setNoGlobalImproveIterations(0);
        soDataHandler.setNoLocalImproveIterations(0);
        soDataHandler.setPiMultiplier(0.5 * Math.PI);
        
        return true;
    }
}
