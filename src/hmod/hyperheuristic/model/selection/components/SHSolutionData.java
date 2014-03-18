
package hmod.hyperheuristic.model.selection.components;

import hmod.core.DataInterface;
import hmod.hyperheuristic.model.selection.HHSolution;

/**
 *
 * @author Enrique Urra C.
 */
public interface SHSolutionData<T extends HHSolution> extends DataInterface
{    
    void setInputSolution(T solution);
    T getInputSolution();
    
    void setAcceptedSolution(T solution);
    T getAcceptedSolution();
    
    void setSelectedSolution(T solution);
    T getSelectedSolution();
    
    void setBestSolution(T solution);
    T getBestSolution();
}