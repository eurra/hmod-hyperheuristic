
package hmod.hyperheuristic.model.selection.scripts;

import flexbuilders.basic.ArrayBuilder;
import static flexbuilders.basic.BasicBuilders.*;
import flexbuilders.core.BuildException;
import flexbuilders.scripting.PropertiesConfigScript;
import flexbuilders.tree.TreeHandler;
import hmod.core.Step;

/**
 *
 * @author Enrique Urra C.
 */
public class HeuristicsConfigScript extends PropertiesConfigScript
{
    public static final String ENTRY_INIT_HEURISTIC = "hmod.hyperheuristic.config.heuristics.initLowLevelHeuristicId";
    public static final String ENTRY_END_HEURISTIC = "hmod.hyperheuristic.config.heuristics.endLowLevelHeuristicId";
    public static final String ENTRY_HEURISTICS = "hmod.hyperheuristic.config.heuristics.lowLevelHeuristicsIds";

    public HeuristicsConfigScript(TreeHandler input, String propertiesFile) throws BuildException
    {
        super(input, propertiesFile);
    }

    @Override
    public void process() throws BuildException
    {
        branch(SelectionRefsIds.SELHYP_CONFIG_INIT_HEURISTIC_START).setBuildable(ref(getEntry(ENTRY_INIT_HEURISTIC)));
        branch(SelectionRefsIds.SELHYP_CONFIG_END_HEURISTIC_START).setBuildable(ref(getEntry(ENTRY_END_HEURISTIC)));
        
        String[] heuristics = getEntry(ENTRY_HEURISTICS).split(";");        
        ArrayBuilder<Step> array = array(Step.class);
        
        for(int i = 0; i < heuristics.length; i++)
            array.elem(ref(heuristics[i]));
        
        branch(SelectionRefsIds.SELHYP_CONFIG_HEURISTICS_STARTS).setBuildable(array);
    }
}
