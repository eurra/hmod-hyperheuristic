
package hmod.hyperheuristic.model.basic.scripts;

import flexbuilders.core.BuildException;
import flexbuilders.scripting.PropertiesConfigScript;
import flexbuilders.tree.TreeHandler;

/**
 * Defines the domain barrier operator for calling low-level heuristics.
 * @author Enrique Urra C.
 */
public class BasicModelConfigScript extends PropertiesConfigScript
{
    private static final String ENTRY_DECODER_OPERATOR_ID = "hmod.hyperheuristic.config.model.basic.decoderOperatorId";
    private static final String ENTRY_ENCODER_OPERATOR_ID = "hmod.hyperheuristic.config.model.basic.encoderOperatorId";
    private static final String ENTRY_DOWNLOAD_OPERATOR_ID = "hmod.hyperheuristic.config.model.basic.downloadOperatorId";
    private static final String ENTRY_UPLOAD_OPERATOR_ID = "hmod.hyperheuristic.config.model.basic.uploadOperatorId";

    public BasicModelConfigScript(TreeHandler input, String propertiesFile) throws BuildException
    {
        super(input, propertiesFile);
    }
    
    @Override
    public void process() throws BuildException
    {
        branch(BasicRefsIds.SELHYP_BASIC_CONFIG_DECODER_OPERATOR_ID).setBuildable(ref(getEntry(ENTRY_DECODER_OPERATOR_ID)));
        branch(BasicRefsIds.SELHYP_BASIC_CONFIG_ENCODER_OPERATOR_ID).setBuildable(ref(getEntry(ENTRY_ENCODER_OPERATOR_ID)));
        branch(BasicRefsIds.SELHYP_BASIC_CONFIG_DOWNLOAD_OPERATOR_ID).setBuildable(ref(getEntry(ENTRY_DOWNLOAD_OPERATOR_ID)));
        branch(BasicRefsIds.SELHYP_BASIC_CONFIG_UPLOAD_OPERATOR_ID).setBuildable(ref(getEntry(ENTRY_UPLOAD_OPERATOR_ID)));
    }
}
