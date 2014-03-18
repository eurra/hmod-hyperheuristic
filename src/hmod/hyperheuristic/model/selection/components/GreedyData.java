
package hmod.hyperheuristic.model.selection.components;

import hmod.hyperheuristic.model.selection.HHSolution;
import hmod.core.components.IterationData;

/**
 *
 * @author Enrique Urra C.
 */
public interface GreedyData extends IterationData
{
    void setBestIndex(int index);
    int getBestIndex();
    void setBestSolution(HHSolution solution);
    HHSolution getBestSolution();
}
