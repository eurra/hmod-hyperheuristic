
package hmod.hyperheuristic.model.selection.components;

import hmod.core.DataInterface;
import hmod.core.Step;

/**
 *
 * @author Enrique Urra C.
 */
public interface ConfigData extends DataInterface
{
    void setMaxIteration(int iterations);
    int getMaxIteration();
    
    void setMaxSeconds(int seconds);
    int getMaxSeconds();
    
    void setHeuristicSelectionStep(Step hsStep);
    Step getHeuristicSelectionStep();
    
    void setMoveAcceptanceStep(Step hsStep);
    Step getMoveAcceptanceStep();
}
