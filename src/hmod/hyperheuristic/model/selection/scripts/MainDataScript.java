
package hmod.hyperheuristic.model.selection.scripts;

import static flexbuilders.basic.BasicBuilders.*;
import static flexbuilders.basic.SetterBuilders.*;
import flexbuilders.core.BuildException;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.TreeHandler;
import hmod.core.DataInterface;
import hmod.core.components.IterationData;
import hmod.core.components.TimeElapsedData;
import hmod.hyperheuristic.model.selection.components.SHSolutionData;
import hmod.hyperheuristic.model.selection.components.LLStepData;
import hmod.hyperheuristic.model.selection.components.LLStepSetData;
import hmod.core.DataObjectProxy;
import hmod.hyperheuristic.model.selection.components.ConfigData;


/**
 * Defines the selection hyperheuristic main data objects.
 * 
 * @author Enrique Urra C.
 */
public class MainDataScript extends BuildScript
{
    public MainDataScript(TreeHandler input)
    {
        super(input);
    }
    
    @Override
    public void process() throws BuildException
    {
        DataInterface mainObj = DataObjectProxy.createFor(
            SHSolutionData.class, 
            LLStepData.class, 
            LLStepSetData.class, 
            IterationData.class, 
            TimeElapsedData.class
        );
        
        DataInterface configObj = DataObjectProxy.createFor(
            ConfigData.class
        );
        
        branch(SelectionRefsIds.SELHYP_DATA_MAIN).setBuildable(
            setterInvoker(value(mainObj)).
            set(beanSetter().setMethodName("setMaxIteration"), ref(SelectionRefsIds.SELHYP_CONFIG_MAX_ITERATION)).
            set(beanSetter().setMethodName("setMaxSeconds"), ref(SelectionRefsIds.SELHYP_CONFIG_MAX_SECONDS)).
            set(beanSetter().setMethodName("setHeuristics"), ref(SelectionRefsIds.SELHYP_CONFIG_HEURISTICS_STARTS)).
            set(beanSetter().setMethodName("setInitHeuristic"), ref(SelectionRefsIds.SELHYP_CONFIG_INIT_HEURISTIC_START)).
            set(beanSetter().setMethodName("setEndHeuristic"), ref(SelectionRefsIds.SELHYP_CONFIG_END_HEURISTIC_START))
        );
        
        branch(SelectionRefsIds.SELHYP_DATA_CONFIG).setBuildable(
            setterInvoker(value(configObj)).
            set(beanSetter().setMethodName("setMaxIteration"), ref(SelectionRefsIds.SELHYP_CONFIG_MAX_ITERATION)).
            set(beanSetter().setMethodName("setMaxSeconds"), ref(SelectionRefsIds.SELHYP_CONFIG_MAX_SECONDS)).
            set(beanSetter().setMethodName("setHeuristicSelectionStep"), ref(SelectionRefsIds.SELHYP_CONFIG_HS_START)).
            set(beanSetter().setMethodName("setMoveAcceptanceStep"), ref(SelectionRefsIds.SELHYP_CONFIG_MA_START))
        );
    }
}
