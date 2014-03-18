
package hmod.hyperheuristic.model.attr.components;

import hmod.core.DataInterface;

/**
 * Data interface for the strategic oscillation model data.
 * @author Enrique Urra C.
 */
public interface SOBasicData extends DataInterface
{    
    void setPiMultiplier(double multiplier);
    double getPiMultiplier();
    
    void setOscillationRate(double rate);
    double getOscillationRate();
    
    void setOscillationModifier(double mod);
    double getOscillationModifier();
    
    void setNoLocalImproveIterations(int iterations);
    int getNoLocalImproveIterations();
    
    void setNoGlobalImproveIterations(int iterations);
    int getNoGlobalImproveIterations();
    
    void setStrategyChangeSpeed(double rate);
    double getStrategyChangeSpeed();
}
