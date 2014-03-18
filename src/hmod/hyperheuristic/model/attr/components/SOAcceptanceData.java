
package hmod.hyperheuristic.model.attr.components;

import hmod.core.DataInterface;
import hmod.hyperheuristic.model.attr.HHAttrSolution;
import java.util.LinkedList;

/**
 * Data interface for the strategic oscillation model.
 * @author Enrique Urra C.
 */
public interface SOAcceptanceData extends DataInterface
{    
    void setEliteSolutions(LinkedList<HHAttrSolution> solutions);
    LinkedList<HHAttrSolution> getEliteSolutions();
    
    void setEliteSolutionsMax(int count);
    int getEliteSolutionsMax();
}
