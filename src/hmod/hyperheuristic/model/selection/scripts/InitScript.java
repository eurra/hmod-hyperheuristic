
package hmod.hyperheuristic.model.selection.scripts;

import static hmod.parser.builders.AlgorithmBuilders.*;
import flexbuilders.core.BuildException;
import flexbuilders.core.Buildable;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.BranchBuilder;
import flexbuilders.tree.TreeHandler;
import hmod.core.components.StartTime;
import static hmod.parser.builders.AlgorithmBuilders.operator;
import static hmod.parser.builders.AlgorithmBuilders.sequentialStep;
import hmod.hyperheuristic.model.selection.components.AcceptState;
import hmod.hyperheuristic.model.selection.components.PrintInitInfo;
import hmod.hyperheuristic.model.selection.components.SelectInitHeuristic;
import hmod.hyperheuristic.model.selection.components.SelectState;

/**
 *
 * @author Enrique Urra C.
 */
public class InitScript extends BuildScript
{
    private BranchBuilder start, configureData, callHeuristic;
    private Buildable dataMain, dataConfig, adapterStart;

    public InitScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        start = branch(SelectionRefsIds.SELHYP_INIT_START);
        configureData = branch(SelectionRefsIds.SELHYP_INIT_CONFIGURE_DATA);
        callHeuristic = branch(SelectionRefsIds.SELHYP_INIT_CALL_HEURISTIC);
        
        dataMain = ref(SelectionRefsIds.SELHYP_DATA_MAIN);
        dataConfig = ref(SelectionRefsIds.SELHYP_DATA_CONFIG);
        adapterStart = ref(SelectionRefsIds.SELHYP_ADAPTER_START);
    }
    
    @Override
    public void process() throws BuildException
    {        
        start.setBuildable(configureData);
        
        configureData.setBuildable(
            extensionStep().setNextStep(callHeuristic).
            addFirst(
                sequentialStep().
                addOperator(
                    operator(PrintInitInfo.class).
                    injectData(dataConfig)
                ).
                addOperator(
                    operator(StartTime.class).
                    injectData(dataMain)
                )
            )
        );
        
        callHeuristic.setBuildable(
            extensionStep().
            addLast(
                sequentialStep().
                addOperator(
                    operator(SelectInitHeuristic.class).
                    injectData(dataMain)
                )
            ).
            addLast(
                adapterStart
            ).
            addLast(
                sequentialStep().
                addOperator(
                    operator(SelectState.class).
                    injectData(dataMain)
                ).
                addOperator(
                    operator(AcceptState.class).
                    injectData(dataMain)
                )
            )
        );
    }
}
