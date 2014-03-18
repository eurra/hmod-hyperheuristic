
package hmod.hyperheuristic.model.selection.scripts;

import static flexbuilders.basic.MetadataBuilders.metadata;
import static hmod.parser.builders.AlgorithmBuilders.*;
import flexbuilders.core.BuildException;
import flexbuilders.core.Buildable;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.BranchBuilder;
import flexbuilders.tree.TreeHandler;
import hmod.hyperheuristic.model.selection.components.CheckSolutionImproved;
import hmod.hyperheuristic.model.selection.components.SelectState;
import hmod.hyperheuristic.model.selection.components.RandomPermDeleteSeq;
import hmod.parser.builders.OperatorInfo;

/**
 *
 * @author Enrique Urra C.
 */
public class HSRandomPermutationDescentScript extends BuildScript
{
    private BranchBuilder start, deleteSeq, callPerm, select;
    private Buildable dataPerm, dataMain, callPermStart;
    
    public HSRandomPermutationDescentScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        start = branch(SelectionRefsIds.SELHYP_HS_RANDOM_PERMUTATION_DESCENT);
        deleteSeq = branch();
        callPerm = branch();
        select = branch();
        
        dataMain = ref(SelectionRefsIds.SELHYP_DATA_MAIN);
        dataPerm = ref(SelectionRefsIds.SELHYP_DATA_CALL_PERMUTATION);
        callPermStart = ref(SelectionRefsIds.SELHYP_OPERATOR_CALL_PERMUTATION);
    }
    
    
    @Override
    public void process() throws BuildException
    {
        start.setBuildable(
            metadata().
            attachData(
                new OperatorInfo.Builder().
                category(HyperheuristicOperator.HEURISTIC_SELECTION).
                name("Random permutation Descent").
                description(
                    "Creates a random low-level heuristic permutation and uses " +
                    "it until a solution improvement."
                )
            ).
            setTarget(
                booleanStep().setTrueStep(callPerm).setFalseStep(deleteSeq).
                setDecider(
                    operator(CheckSolutionImproved.class).
                    injectData(dataMain)
                )
            )
        );
        
        deleteSeq.setBuildable(
            sequentialStep().setNextStep(callPerm).
            addOperator(
                operator(RandomPermDeleteSeq.class).
                injectData(dataPerm)
            )
        );
        
        callPerm.setBuildable(
            subProcessStep().setNextStep(select).
            setSubStep(callPermStart)
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
