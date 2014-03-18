
package hmod.hyperheuristic.model.selection.scripts;

import static flexbuilders.basic.BasicBuilders.value;
import flexbuilders.core.BuildException;
import flexbuilders.core.Buildable;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.BranchBuilder;
import flexbuilders.tree.TreeHandler;
import hmod.core.DataObjectProxy;
import hmod.core.components.CheckMaxIteration;
import hmod.hyperheuristic.model.selection.components.RandomPermCheckSeq;
import hmod.hyperheuristic.model.selection.components.RandomPermCreateSeq;
import hmod.hyperheuristic.model.selection.components.RandomPermData;
import hmod.hyperheuristic.model.selection.components.RandomPermInitSeq;
import hmod.hyperheuristic.model.selection.components.RandomPermNext;
import static hmod.parser.builders.AlgorithmBuilders.*;

/**
 *
 * @author Enrique Urra C.
 */
public class CallHeuristicPermutationScript extends BuildScript
{
    private BranchBuilder dataPerm, start, createSeq, initSeq, permNext, adapterCall, checkIteration;
    private Buildable dataMain, adapterStart;

    public CallHeuristicPermutationScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        dataPerm = branch(SelectionRefsIds.SELHYP_DATA_CALL_PERMUTATION);
        start = branch(SelectionRefsIds.SELHYP_OPERATOR_CALL_PERMUTATION);
        createSeq = branch();
        initSeq = branch();
        permNext = branch();
        adapterCall = branch();
        checkIteration = branch();
        
        dataMain = ref(SelectionRefsIds.SELHYP_DATA_MAIN);
        adapterStart = ref(SelectionRefsIds.SELHYP_ADAPTER_START);
    }
    

    @Override
    public void process() throws BuildException
    {
        dataPerm.setBuildable(value(DataObjectProxy.createFor(RandomPermData.class)));
        
        start.setBuildable(
            booleanStep().setTrueStep(initSeq).setFalseStep(createSeq).
            setDecider(
                operator(RandomPermCheckSeq.class).
                injectData(dataPerm)
            )
        );
        
        createSeq.setBuildable(
            sequentialStep().setNextStep(initSeq).
            addOperator(
                operator(RandomPermCreateSeq.class).
                injectData(dataPerm).
                injectData(dataMain)
            )
        );
        
        initSeq.setBuildable(
            sequentialStep().setNextStep(permNext).
            addOperator(
                operator(RandomPermInitSeq.class).
                injectData(dataPerm)
            )
        );
        
        permNext.setBuildable(
            sequentialStep().setNextStep(adapterCall).
            addOperator(
                operator(RandomPermNext.class).
                injectData(dataPerm).
                injectData(dataMain)
            )
        );
        
        adapterCall.setBuildable(
            subProcessStep().setNextStep(checkIteration).
            setSubStep(adapterStart)
        );
        
        checkIteration.setBuildable(
            booleanStep().setTrueStep(permNext).
            setDecider(
                operator(CheckMaxIteration.class).
                injectData(dataPerm)
            )
        );
    }
}
