
package hmod.hyperheuristic.model.attr.components;

import hmod.core.Operator;
import hmod.core.AlgorithmException;
import optefx.util.output.AlgorithmOutputManager;

/**
 * Operator related to the strategic oscillation model.
 * Checks if the current pi multiplier remains within its valid bounds.
 * @author Enrique Urra C.
 */
public class CheckPiMultiplier implements Operator
{
    private SOBasicData soDataHandler;

    public void setSODataHandler(SOBasicData handler)
    {
        this.soDataHandler = handler;
    }

    @Override
    public boolean doOperation() throws AlgorithmException
    {
        if(soDataHandler.getPiMultiplier() <= (1.5 * Math.PI))
        {
            return true;
        }
        else
        {
            AlgorithmOutputManager.println(OutputIds.SO_EVENTS, "Reached max. div. Switching to int...");
            return false;
        }
    }
}
