
package hmod.hyperheuristic.model.selection.scripts;

import static hmod.parser.builders.AlgorithmBuilders.*;
import flexbuilders.core.BuildException;
import flexbuilders.core.Buildable;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.BranchBuilder;
import flexbuilders.tree.TreeHandler;
import hmod.hyperheuristic.model.selection.components.LLStepCall;
import hmod.hyperheuristic.model.selection.components.PrepareSolution;

/**
 *
 * @author Enrique Urra C.
 */
public class AdapterScript extends BuildScript
{
    private BranchBuilder start, configData, downwardsTransfer, callHeuristic, upwardsTransfer;
    private Buildable dataMain, downwardsTransferStart, upwardsTransferStart;

    public AdapterScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        start = branch(SelectionRefsIds.SELHYP_ADAPTER_START);
        configData = branch(SelectionRefsIds.SELHYP_ADAPTER_CONFIGURE_DATA);
        downwardsTransfer = branch(SelectionRefsIds.SELHYP_ADAPTER_DOWNWARDS_TRANSFER);
        callHeuristic = branch(SelectionRefsIds.SELHYP_ADAPTER_CALL_HEURISTIC);
        upwardsTransfer = branch(SelectionRefsIds.SELHYP_ADAPTER_UPWARDS_TRANSFER);
        
        dataMain = ref(SelectionRefsIds.SELHYP_DATA_MAIN);
        downwardsTransferStart = ref(SelectionRefsIds.SELHYP_CONFIG_DOWNWARDS_TRANSFER_START);
        upwardsTransferStart = ref(SelectionRefsIds.SELHYP_CONFIG_UPWARDS_TRANSFER_START);
    }
    
    @Override
    public void process() throws BuildException
    {
        start.setBuildable(configData);
        
        configData.setBuildable(
            extensionStep().setNextStep(downwardsTransfer).
            addLast(
                sequentialStep().
                addOperator(
                    operator(PrepareSolution.class).
                    injectData(dataMain)
                )
            )
        );
        
        downwardsTransfer.setBuildable(
            extensionStep().setNextStep(callHeuristic).
            addLast(downwardsTransferStart)
        );
        
        callHeuristic.setBuildable(
            extensionStep().setNextStep(upwardsTransfer).
            addLast(
                sequentialStep().
                addOperator(
                    operator(LLStepCall.class).
                    injectData(dataMain)
                )
            )
        );
        
        upwardsTransfer.setBuildable(
            extensionStep().
            addLast(upwardsTransferStart)
        );
    }
}
