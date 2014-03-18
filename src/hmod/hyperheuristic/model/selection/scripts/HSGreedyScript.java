
package hmod.hyperheuristic.model.selection.scripts;

import static flexbuilders.basic.MetadataBuilders.*;
import flexbuilders.core.BuildException;
import flexbuilders.core.Buildable;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.BranchBuilder;
import hmod.core.DataObjectProxy;
import hmod.core.components.CheckMaxIteration;
import hmod.hyperheuristic.model.selection.components.GreedyCheckBest;
import hmod.hyperheuristic.model.selection.components.GreedyData;
import hmod.hyperheuristic.model.selection.components.GreedyInit;
import hmod.hyperheuristic.model.selection.components.GreedyNextHeuristic;
import hmod.hyperheuristic.model.selection.components.GreedySelectBest;
import hmod.hyperheuristic.model.selection.components.SelectState;
import hmod.parser.builders.OperatorInfo;
import static flexbuilders.basic.BasicBuilders.*;
import flexbuilders.tree.TreeHandler;
import static hmod.parser.builders.AlgorithmBuilders.*;

/**
 *
 * @author Enrique Urra C.
 */
public class HSGreedyScript extends BuildScript
{
    private BranchBuilder start, nextHeuristic, adapterCall, checkBest, checkIteration, selectBest, saveSelect;
    private Buildable dataGreedy, dataMain, adapterStart;
    
    public HSGreedyScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        start = branch(SelectionRefsIds.SELHYP_HS_GREEDY);
        nextHeuristic = branch();
        adapterCall = branch();
        checkBest = branch(); 
        checkIteration = branch();
        selectBest = branch(); 
        saveSelect = branch();
        
        dataGreedy = value(DataObjectProxy.createFor(GreedyData.class));
        dataMain = ref(SelectionRefsIds.SELHYP_DATA_MAIN);
        adapterStart = ref(SelectionRefsIds.SELHYP_ADAPTER_START);
    }
    
    @Override
    public void process() throws BuildException
    {       
        start.setBuildable(
            metadata().
            attachData(
                new OperatorInfo.Builder().
                category(HyperheuristicOperator.HEURISTIC_SELECTION).
                name("Greedy").
                description(
                    "Evaluates the result of all low-level heuristics and " +
                    "select the one whose result is the best."
                )
            ).
            setTarget(
                sequentialStep().setNextStep(nextHeuristic).
                addOperator(
                    operator(GreedyInit.class).
                    injectData(dataGreedy).
                    injectData(dataMain)
                )
            )
        );
        
        nextHeuristic.setBuildable(
            sequentialStep().setNextStep(adapterCall).
            addOperator(
                operator(GreedyNextHeuristic.class).
                injectData(dataGreedy).
                injectData(dataMain)
            )
        );
        
        adapterCall.setBuildable(
            subProcessStep().setNextStep(checkBest).
            setSubStep(adapterStart)
        );
        
        checkBest.setBuildable(
            sequentialStep().setNextStep(checkIteration).
            addOperator(
                operator(GreedyCheckBest.class).
                injectData(dataGreedy).
                injectData(dataMain)
            )
        );
        
        checkIteration.setBuildable(
            booleanStep().setTrueStep(selectBest).setFalseStep(nextHeuristic).
            setDecider(
                operator(CheckMaxIteration.class).
                injectData(dataGreedy)
            )
        );
        
        selectBest.setBuildable(
            sequentialStep().setNextStep(saveSelect).
            addOperator(
                operator(GreedySelectBest.class).
                injectData(dataGreedy).
                injectData(dataMain)
            )
        );
        
        saveSelect.setBuildable(
            sequentialStep().
            addOperator(
                operator(SelectState.class).
                injectData(dataMain)
            )
        );
    }
}
