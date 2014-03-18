
package hmod.hyperheuristic.model.basic.scripts;

import static flexbuilders.basic.BasicBuilders.*;
import flexbuilders.core.BuildException;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.TreeHandler;
import hmod.core.DataObjectProxy;
import hmod.hyperheuristic.model.basic.components.HighLevelSolutionData;
import hmod.hyperheuristic.model.basic.components.LowLevelSolutionData;

/**
 *
 * @author Enrique Urra C.
 */
public class BasicDataScript extends BuildScript
{
    public BasicDataScript(TreeHandler input)
    {
        super(input);
    }
    
    @Override
    public void process() throws BuildException
    {
        branch(BasicRefsIds.SELHYP_BASIC_DATA).setBuildable(
            value(
                DataObjectProxy.createFor(
                    HighLevelSolutionData.class,
                    LowLevelSolutionData.class
                )
            )
        );
    }
}
