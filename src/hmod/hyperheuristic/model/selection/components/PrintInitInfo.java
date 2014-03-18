
package hmod.hyperheuristic.model.selection.components;

import hmod.core.Operator;
import hmod.core.AlgorithmException;
import hmod.core.Step;
import hmod.parser.builders.OperatorInfo;
import optefx.util.metadata.MetadataManager;
import optefx.util.output.OutputManager;
import optefx.util.random.RandomTool;

/**
 *
 * @author Enrique Urra C.
 */
public class PrintInitInfo implements Operator
{
    private ConfigData configData;

    public void setConfigData(ConfigData configData)
    {
        this.configData = configData;
    }

    @Override
    public void execute() throws AlgorithmException
    {        
        int maxIterations = configData.getMaxIteration();
        double maxSeconds = configData.getMaxSeconds();
        
        Step hsStep = configData.getHeuristicSelectionStep();
        Step maStep = configData.getMoveAcceptanceStep();
        
        OperatorInfo hsInfo = MetadataManager.getInstance().getDataFor(hsStep, OperatorInfo.class);
        OperatorInfo maInfo = MetadataManager.getInstance().getDataFor(maStep, OperatorInfo.class);
        
        OutputManager.println(OutputIds.EXEC_INFO,
            "****** Hyperheuristic execution info ******\n\n" + 
            "- Max. iterations: " + (maxIterations == -1 ? "non-set" : maxIterations) + "\n" +
            "- Max. execution seconds: " + (maxSeconds == -1.0 ? "non-set" : maxSeconds) + "\n" +
            "- Random seed: " + RandomTool.getInstance().getSeed() + "\n" +
            "- Heuristic selection operator: " + (hsInfo != null ? hsInfo.getName() + " (" + hsInfo.getDescription() + ")" : "(no-info)") + "\n" +
            "- Move acceptance operator: " + (maInfo != null ? maInfo.getName() + " (" + maInfo.getDescription() + ")" : "(no-info)") + "\n"
        );
    }
}
