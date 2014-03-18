
package hmod.hyperheuristic.model.attr.components;

import hmod.core.Operator;
import hmod.core.AlgorithmException;

/**
 * Operator related to the strategic oscillation model.
 * Update the oscillation rate according to the state of the search.
 * @author Enrique Urra C.
 */
public class UpdateOscillationRate implements Operator
{
    private SOBasicData soDataHandler;
    
    public void setSODataHandler(SOBasicData handler)
    {
        this.soDataHandler = handler;
    }

    @Override
    public boolean doOperation() throws AlgorithmException
    {
        // The oscillation rate is given by the following elliptic cosine 
        // function:
        //  om = oscillation modifier
        //  p = pi multiplier
        //  a(x) = 1 + (om * tan(p + 0.5 * pi)) ^ 2
        //  cose(x) = (1 + ((+/-)1 / sqrt(a(x)))) / 2
        double piMultiplier = soDataHandler.getPiMultiplier();
        double modifier = soDataHandler.getOscillationModifier();
        double sign = piMultiplier < Math.PI ? 1.0 : -1.0;
        double innerValue = 1 + Math.pow(modifier * Math.tan(piMultiplier + 0.5 * Math.PI), 2.0);        
        double finalRate = (1 + (sign / Math.sqrt(innerValue))) / 2;
        
        // Then, the rate is updated
        soDataHandler.setOscillationRate(finalRate);
        return true;
    }
}