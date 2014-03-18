
package hmod.hyperheuristic.model.selection.scripts;

import flexbuilders.core.BuildException;
import flexbuilders.core.Buildable;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.BranchBuilder;
import flexbuilders.tree.TreeHandler;
import hmod.hyperheuristic.model.selection.components.CheckHeuristicSet;
import hmod.hyperheuristic.model.selection.components.RandomSelect;
import static hmod.parser.builders.AlgorithmBuilders.*;

/**
 *
 * @author Enrique Urra C.
 */
public class CallRandomHeuristicScript extends BuildScript
{
    private BranchBuilder start, selectRandom, adapterCall;
    private Buildable dataMain, adapterStart;

    public CallRandomHeuristicScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        start = branch(SelectionRefsIds.SELHYP_OPERATOR_CALL_RANDOM_HEURISTIC);
        selectRandom = branch();
        adapterCall = branch();
        
        dataMain = ref(SelectionRefsIds.SELHYP_DATA_MAIN);
        adapterStart = ref(SelectionRefsIds.SELHYP_ADAPTER_START);
    }
    
    @Override
    public void process() throws BuildException
    {
        start.setBuildable(
            booleanStep().setTrueStep(adapterCall).setFalseStep(selectRandom).
            setDecider(
                operator(CheckHeuristicSet.class).
                injectData(dataMain)
            )
        );
        
        selectRandom.setBuildable(
            sequentialStep().setNextStep(adapterCall).
            addOperator(
                operator(RandomSelect.class).
                injectData(dataMain)
            )
        );
        
        adapterCall.setBuildable(
            subProcessStep().
            setSubStep(adapterStart)
        );
    }
}
