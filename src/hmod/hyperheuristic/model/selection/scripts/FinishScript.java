
package hmod.hyperheuristic.model.selection.scripts;

import static hmod.parser.builders.AlgorithmBuilders.*;
import flexbuilders.core.BuildException;
import flexbuilders.core.Buildable;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.BranchBuilder;
import flexbuilders.tree.TreeHandler;
import static hmod.parser.builders.AlgorithmBuilders.operator;
import static hmod.parser.builders.AlgorithmBuilders.sequentialStep;
import hmod.hyperheuristic.model.selection.components.PrintEndInfo;
import hmod.hyperheuristic.model.selection.components.SelectEndHeuristic;

/**
 *
 * @author Enrique Urra C.
 */
public class FinishScript extends BuildScript
{
    private BranchBuilder start, callHeuristic, configureData;
    private Buildable dataMain, adapterStart;

    public FinishScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        start = branch(SelectionRefsIds.SELHYP_FINISH_START);
        callHeuristic = branch(SelectionRefsIds.SELHYP_FINISH_CALL_HEURISTIC);
        configureData = branch(SelectionRefsIds.SELHYP_FINISH_CONFIGURE_DATA);
        
        dataMain = ref(SelectionRefsIds.SELHYP_DATA_MAIN);
        adapterStart = ref(SelectionRefsIds.SELHYP_ADAPTER_START);
    }
    
    @Override
    public void process() throws BuildException
    {        
        start.setBuildable(callHeuristic);
        
        callHeuristic.setBuildable(
            extensionStep().setNextStep(configureData).
            addLast(
                sequentialStep().
                addOperator(
                    operator(SelectEndHeuristic.class).
                    injectData(dataMain)
                ) 
            ).
            addLast(
                adapterStart
            )
        );
        
        configureData.setBuildable(
            extensionStep().
            addLast(
                sequentialStep().
                addOperator(
                    operator(PrintEndInfo.class).
                    injectData(dataMain)
                )
            )
        );
    }
}
