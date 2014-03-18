
package hmod.hyperheuristic.model.selection.components;

/**
 *
 * @author Enrique Urra C.
 */
public final class OutputIds
{
    private OutputIds(){}    
    
    /**
     * Output that prints general information regarding the hyperheuristic
     * information.
     */
    public static final String EXEC_INFO = "selHyp-exec-info";
    
    /**
     * Output that informs when a new best solution was found.
     */
    public static final String NEW_BEST_FITNESS = "selHyp-best-fitness";
    
    /**
     * Output that provides a tabular description of the hyperheuristic 
     * execution results, such as the best evaluation and the total cpu time.
     * Can be used through appending to construct tabular reports regarding
     * multiple executions.
     */
    public static final String RESULT_SHEET = "selHyp-result-sheet";
}
