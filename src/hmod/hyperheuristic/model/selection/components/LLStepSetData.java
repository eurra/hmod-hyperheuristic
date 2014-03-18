
package hmod.hyperheuristic.model.selection.components;

import hmod.core.DataInterface;
import hmod.core.Step;

/**
 *
 * @author Enrique Urra C.
 */
public interface LLStepSetData extends DataInterface
{
    void setHeuristics(Step[] steps);
    Step[] getHeuristics();
    
    void setInitHeuristic(Step steps);
    Step getInitHeuristic();
    
    void setEndHeuristic(Step steps);
    Step getEndHeuristic();
}
