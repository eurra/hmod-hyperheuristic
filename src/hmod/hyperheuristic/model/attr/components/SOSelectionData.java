
package hmod.hyperheuristic.model.attr.components;

import hmod.hyperheuristic.model.attr.HeuristicStats;
import hmod.core.Step;
import java.util.Map;

/**
 * Data interface for the strategic oscillation model.
 * @author Enrique Urra C.
 */
public interface SOSelectionData
{
    void setHeuristicStats(Map<Step, HeuristicStats> stats);
    Map<Step, HeuristicStats> getHeuristicStats();
}
