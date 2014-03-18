
package hmod.hyperheuristic.model.attr.components;

import hmod.core.Operator;
import hmod.core.AlgorithmException;

/**
 * Operator related to the strategic oscillation model.
 * Update the pi multiplier according to the state of the search.
 * @author Enrique Urra C.
 */
public class UpdatePiMultiplier implements Operator
{
    private SOBasicData soDataHandler;
    
    public void setSODataHandler(SOBasicData handler)
    {
        this.soDataHandler = handler;
    }    

    @Override
    public boolean doOperation() throws AlgorithmException
    {        
        // To update the pi multiplier (p), the following logarithmic function 
        // is applied, based on the strategy change speed (u) and the current 
        // non-improving global count (t):
        //  p = (pi/2) + ln(1 + u*t)
        double changeSpeed = soDataHandler.getStrategyChangeSpeed();
        int nonImproveCount = soDataHandler.getNoGlobalImproveIterations();
        double piMultiplier = (Math.PI / 2) + Math.log10(1 + changeSpeed * nonImproveCount);
        
        soDataHandler.setPiMultiplier(piMultiplier);
        return true;
    }
}
