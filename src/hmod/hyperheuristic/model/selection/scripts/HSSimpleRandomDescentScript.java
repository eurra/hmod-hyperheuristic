
package hmod.hyperheuristic.model.selection.scripts;

import static flexbuilders.basic.MetadataBuilders.metadata;
import static hmod.parser.builders.AlgorithmBuilders.*;
import flexbuilders.core.BuildException;
import flexbuilders.core.Buildable;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.BranchBuilder;
import flexbuilders.tree.TreeHandler;
import hmod.parser.builders.OperatorInfo;
import hmod.hyperheuristic.model.selection.components.CheckSolutionImproved;
import hmod.hyperheuristic.model.selection.components.DeleteCurrentHeuristic;
import hmod.hyperheuristic.model.selection.components.SelectState;

/**
 *
 * @author Enrique Urra C.
 */
public class HSSimpleRandomDescentScript extends BuildScript
{
    private BranchBuilder start, deleteCurrent, callRandom, select;
    private Buildable dataMain, callRandomStart;

    public HSSimpleRandomDescentScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        start = branch(SelectionRefsIds.SELHYP_HS_SIMPLE_RANDOM_DESCENT);
        deleteCurrent = branch();
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
                    "Selects a random low-level heuristic and executes it while " +
                    "no solution improvement is reached"
                )
            ).
            setTarget(
                booleanStep().setTrueStep(callRandom).setFalseStep(deleteCurrent).
                setDecider(
                    operator(CheckSolutionImproved.class).
                    injectData(dataMain)
                )
            )
        );
        
        deleteCurrent.setBuildable(
            sequentialStep().setNextStep(callRandom).
            addOperator(
                operator(DeleteCurrentHeuristic.class).
                injectData(dataMain)
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
