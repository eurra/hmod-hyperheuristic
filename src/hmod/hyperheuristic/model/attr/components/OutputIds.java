
package hmod.hyperheuristic.model.attr.components;

/**
 *
 * @author Enrique Urra C.
 */
public final class OutputIds
{
    private OutputIds(){}    
    
    /**
     * Output general information regarding the strategic oscillation 
     * parameters and inputs.
     */
    static final String SO_INFO = "selHyp-so-info";
    
    /**
     * Output that print information regarding oscillation events during the
     * search, e.g., when the strategy changes.
     */
    static final String SO_EVENTS = "selHyp-so-events";
    
    /**
     * Output that print tabular information regarding the main oscillation
     * data at each iteration.
     */
    static final String SO_DATA_SHEET = "selHyp-so-data-sheet";
}
