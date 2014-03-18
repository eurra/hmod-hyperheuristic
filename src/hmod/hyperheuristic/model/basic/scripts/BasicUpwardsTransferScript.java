
package hmod.hyperheuristic.model.basic.scripts;

import static flexbuilders.basic.SetterBuilders.beanSetter;
import static flexbuilders.basic.SetterBuilders.setterInvoker;
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
public class BasicUpwardsTransferScript extends BuildScript
{
    private BranchBuilder start, upload, encode;
    private Buildable dataModel, dataMain, encoderOp, uploadOp;

    public BasicUpwardsTransferScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        start = branch(BasicRefsIds.SELHYP_BASIC_UPWARDS_TRANSFER_START);
        upload = branch(BasicRefsIds.SELHYP_BASIC_UPWARDS_TRANSFER_UPLOAD);
        encode = branch(BasicRefsIds.SELHYP_BASIC_UPWARDS_TRANSFER_ENCODE);
        
        dataModel = ref(BasicRefsIds.SELHYP_BASIC_DATA);
        dataMain = ref(SelectionRefsIds.SELHYP_DATA_MAIN);
        encoderOp = ref(BasicRefsIds.SELHYP_BASIC_CONFIG_ENCODER_OPERATOR_ID);
        uploadOp = ref(BasicRefsIds.SELHYP_BASIC_CONFIG_UPLOAD_OPERATOR_ID);
    }
    
    @Override
    public void process() throws BuildException
    {
        start.setBuildable(
            upload
        );
        
        upload.setBuildable(
            extensionStep().setNextStep(encode).
            addFirst(
                sequentialStep().
                addOperator(
                    setterInvoker(uploadOp).
                    set(beanSetter().setMethodName("setLowLevelSolutionData"), dataModel)
                )
            )
        );
        
        encode.setBuildable(
            extensionStep().
            addFirst(
                sequentialStep().
                addOperator(
                    setterInvoker(encoderOp).
                    set(beanSetter().setMethodName("setLowLevelSolutionData"), dataModel).
                    set(beanSetter().setMethodName("setSHSolutionData"), dataMain)
                )
            )
        );
    }
}
