
package hmod.hyperheuristic.model.basic.components;

import hmod.core.DataInterface;
import hmod.hyperheuristic.model.selection.HHSolution;

/**
 *
 * @author Enrique Urra C.
 */
public interface HighLevelSolutionData<T extends HHSolution> extends DataInterface
{
    void setInputSolution(T solution);
    T getInputSolution();
}
