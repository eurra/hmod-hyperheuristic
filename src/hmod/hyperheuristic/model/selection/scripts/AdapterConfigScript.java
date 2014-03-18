
package hmod.hyperheuristic.model.selection.scripts;

import flexbuilders.core.BuildException;
import flexbuilders.scripting.PropertiesConfigScript;
import flexbuilders.tree.TreeHandler;

/**
 *
 * @author Enrique Urra C.
 */
public class AdapterConfigScript extends PropertiesConfigScript
{
    public static final String ENTRY_DOWNWARDS_TRANSFER_START_ID = "hmod.hyperheuristic.config.adapter.downwardsTransferStartId";
    public static final String ENTRY_UPWARDS_TRANSFER_START_ID = "hmod.hyperheuristic.config.adapter.upwardsTransferStartId";

    public AdapterConfigScript(TreeHandler input, String propertiesFile) throws BuildException
    {
        super(input, propertiesFile);
    }

    @Override
    public void process() throws BuildException
    {
        branch(SelectionRefsIds.SELHYP_CONFIG_DOWNWARDS_TRANSFER_START).setBuildable(ref(getEntry(ENTRY_DOWNWARDS_TRANSFER_START_ID)));
        branch(SelectionRefsIds.SELHYP_CONFIG_UPWARDS_TRANSFER_START).setBuildable(ref(getEntry(ENTRY_UPWARDS_TRANSFER_START_ID)));
    }
}
