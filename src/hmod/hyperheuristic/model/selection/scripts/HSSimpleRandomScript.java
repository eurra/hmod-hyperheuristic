
package hmod.hyperheuristic.model.selection.scripts;

import static flexbuilders.basic.MetadataBuilders.metadata;
import static hmod.parser.builders.AlgorithmBuilders.*;
import flexbuilders.core.BuildException;
import flexbuilders.core.Buildable;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.BranchBuilder;
import flexbuilders.tree.TreeHandler;
import hmod.hyperheuristic.model.selection.components.DeleteCurrentHeuristic;
import hmod.parser.builders.OperatorInfo;
import hmod.hyperheuristic.model.selection.components.SelectState;

/**
 *
 * @author Enrique Urra C.
 */
public class HSSimpleRandomScript extends BuildScript
{
    private BranchBuilder start, callRandom, select;
    private Buildable dataMain, callRandomStart;

    public HSSimpleRandomScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        start = branch(SelectionRefsIds.SELHYP_HS_SIMPLE_RANDOM);
        callRandom = branch();
        select = branch();
        
        dataMain = ref(SelectionRefsIds.SELHYP_DATA_MAIN);
        callRandomStart = ref(SelectionRefsIds.SELHYP_OPERATOR_CALL_RANDOM_HEURISTIC);
    }
    
    @Override
    public void process() throws BuildException
    {
        start.setBuildable(
            metadata().
            attachData(
                new OperatorInfo.Builder().
                category(HyperheuristicOperator.HEURISTIC_SELECTION).
                name("Simple Random").
                description(
                    "Select a random low-level heuristic and executes it."
                )
            ).
            setTarget(
                sequentialStep().setNextStep(callRandom).
                addOperator(
                    operator(DeleteCurrentHeuristic.class).
                    injectData(dataMain)
                )
            )
        );
        
        callRandom.setBuildable(
            subProcessStep().setNextStep(select).
            setSubStep(callRandomStart)
        );
        
        select.setBuildable(
            sequentialStep().
            addOperator(
                operator(SelectState.class).
                injectData(dataMain)
            )
        );
    }
}
