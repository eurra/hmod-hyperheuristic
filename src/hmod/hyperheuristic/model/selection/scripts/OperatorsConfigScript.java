
package hmod.hyperheuristic.model.selection.scripts;

import flexbuilders.core.BuildException;
import flexbuilders.scripting.PropertiesConfigScript;
import flexbuilders.tree.TreeHandler;

/**
 *
 * @author Enrique Urra C.
 */
public class OperatorsConfigScript extends PropertiesConfigScript
{
    public static final String ENTRY_HS_ID = "hmod.hyperheuristic.config.operators.heuristicSelectionOperatorId";
    public static final String ENTRY_MA_ID = "hmod.hyperheuristic.config.operators.moveAceptanceOperatorId";

    public OperatorsConfigScript(TreeHandler input, String propertiesFile) throws BuildException
    {
        super(input, propertiesFile);
    }

    @Override
    public void process() throws BuildException
    {
        branch(SelectionRefsIds.SELHYP_CONFIG_HS_START).setBuildable(ref(getEntry(ENTRY_HS_ID)));
        branch(SelectionRefsIds.SELHYP_CONFIG_MA_START).setBuildable(ref(getEntry(ENTRY_MA_ID)));
    }
}
