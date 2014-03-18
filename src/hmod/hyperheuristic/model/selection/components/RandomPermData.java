
package hmod.hyperheuristic.model.selection.components;

import hmod.core.Step;
import hmod.core.components.IterationData;

/**
 *
 * @author Enrique Urra C.
 */
public interface RandomPermData extends IterationData
{
    void setCurrentSequence(Step[] sequence);
    Step[] getCurrentSequence();
}
