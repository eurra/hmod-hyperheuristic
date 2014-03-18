
package hmod.hyperheuristic.model.basic.components;

import hmod.core.DataInterface;

/**
 *
 * @author Enrique Urra C.
 */
public interface LowLevelSolutionData<T> extends DataInterface
{
    void setInputSolution(T solution);
    T getInputSolution();
}
