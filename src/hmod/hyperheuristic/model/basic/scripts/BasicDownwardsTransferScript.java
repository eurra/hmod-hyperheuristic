
package hmod.hyperheuristic.model.basic.scripts;

import static flexbuilders.basic.SetterBuilders.*;
import static hmod.parser.builders.AlgorithmBuilders.*;
import flexbuilders.core.BuildException;
import flexbuilders.core.Buildable;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.BranchBuilder;
import flexbuilders.tree.TreeHandler;
import hmod.hyperheuristic.model.selection.scripts.SelectionRefsIds;

/**
 * Defines the domain barrier operator for calling low-level heuristics.
 * @author Enrique Urra C.
 */
public class BasicDownwardsTransferScript extends BuildScript
{
    private BranchBuilder start, decode, download;
    private Buildable dataModel, dataMain, decoderOp, downloadOp;
    
    public BasicDownwardsTransferScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        start = branch(BasicRefsIds.SELHYP_BASIC_DOWNWARDS_TRANSFER_START);
        decode = branch(BasicRefsIds.SELHYP_BASIC_DOWNWARDS_TRANSFER_DECODE);
        download = branch(BasicRefsIds.SELHYP_BASIC_DOWNWARDS_TRANSFER_DOWNLOAD);
        
        dataModel = ref(BasicRefsIds.SELHYP_BASIC_DATA);
        dataMain = ref(SelectionRefsIds.SELHYP_DATA_MAIN);
        decoderOp = ref(BasicRefsIds.SELHYP_BASIC_CONFIG_DECODER_OPERATOR_ID);
        downloadOp = ref(BasicRefsIds.SELHYP_BASIC_CONFIG_DOWNLOAD_OPERATOR_ID);
    }
    
    @Override
    public void process() throws BuildException
    {
        start.setBuildable(
            decode
        );
        
        decode.setBuildable(
            extensionStep().setNextStep(download).
            addFirst(
                sequentialStep().
                addOperator(
                    setterInvoker(decoderOp).
                    set(beanSetter().setMethodName("setLowLevelSolutionData"), dataModel).
                    set(beanSetter().setMethodName("setSHSolutionData"), dataMain)
                )
            )
        );
        
        download.setBuildable(
            extensionStep().
            addFirst(
                sequentialStep().
                addOperator(
                    setterInvoker(downloadOp).
                    set(beanSetter().setMethodName("setLowLevelSolutionData"), dataModel)
                )
            )
        );
    }
}
