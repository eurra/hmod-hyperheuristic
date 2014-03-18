
package hmod.hyperheuristic.model.selection.scripts;

import static flexbuilders.basic.BasicBuilders.*;
import flexbuilders.core.BuildException;
import flexbuilders.scripting.PropertiesConfigScript;
import flexbuilders.tree.TreeHandler;

/**
 *
 * @author Enrique Urra C.
 */
public class RunConfigScript extends PropertiesConfigScript
{
    public static final String ENTRY_MAX_ITERATIONS = "hmod.hyperheuristic.config.run.maxIterations";
    public static final String ENTRY_MAX_SECONDS = "hmod.hyperheuristic.config.run.maxSeconds";
    
    private String file;

    public RunConfigScript(TreeHandler input, String propertiesFile) throws BuildException
    {
        super(input, propertiesFile);
        this.file = propertiesFile;
    }

    @Override
    public void process() throws BuildException
    {
        int maxIteration, maxSeconds;
        
        try
        {
            maxIteration = Integer.parseInt(getEntry(ENTRY_MAX_ITERATIONS));
            maxSeconds = Integer.parseInt(getEntry(ENTRY_MAX_SECONDS));
        }
        catch(NumberFormatException ex)
        {
            throw new BuildException("Error while parsing the '" + file + "' config file: " + ex.getMessage());
        }
        
        branch(SelectionRefsIds.SELHYP_CONFIG_MAX_ITERATION).setBuildable(value(maxIteration));
        branch(SelectionRefsIds.SELHYP_CONFIG_MAX_SECONDS).setBuildable(value(maxSeconds));
    }
}
