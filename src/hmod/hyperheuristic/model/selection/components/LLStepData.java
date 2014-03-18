
package hmod.hyperheuristic.model.selection.components;

import hmod.core.DataInterface;
import hmod.core.Step;

/**
 *
 * @author Enrique Urra C.
 */
public interface LLStepData extends DataInterface
{    
    void setCurrentLLStep(Step step);    
    Step getCurrentLLStep();
    
    void setSelectedLLStep(Step step);    
    Step getSelectedLLStep();
    
    void setAcceptedLLStep(Step step);    
    Step getAcceptedLLStep();
}
