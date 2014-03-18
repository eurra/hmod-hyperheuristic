
package hmod.hyperheuristic.model.selection.scripts;

import static hmod.parser.builders.AlgorithmBuilders.*;
import flexbuilders.core.BuildException;
import flexbuilders.core.Buildable;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.BranchBuilder;
import flexbuilders.tree.TreeHandler;
import hmod.hyperheuristic.model.selection.components.CheckFinished;
import hmod.core.components.CheckMaxIteration;
import hmod.core.components.CheckMaxTime;

/**
 * Defines the main selection hyperheuristic skeleton, which provides the 
 * starting point of the execution.
 * 
 * @author Enrique Urra C.
 */
public class MainScript extends BuildScript
{   
    private BranchBuilder start, init, mainIteration, checkFinished, finish, updateState, mainHS, mainMA;
    private Buildable initStart, finishStart, hsStart, maStart, dataMain;

    public MainScript(TreeHandler input) throws BuildException
    {
        super(input);
        
        start = branch(SelectionRefsIds.SELHYP_MAIN_START);
        init = branch(SelectionRefsIds.SELHYP_MAIN_INIT);
        mainIteration = branch(SelectionRefsIds.SELHYP_MAIN_ITERATION);
        checkFinished = branch();
        finish = branch(SelectionRefsIds.SELHYP_MAIN_FINISH);
        updateState = branch();
        mainHS = branch(SelectionRefsIds.SELHYP_MAIN_HS);
        mainMA = branch(SelectionRefsIds.SELHYP_MAIN_MA);
        
        initStart = ref(SelectionRefsIds.SELHYP_INIT_START);
        finishStart = ref(SelectionRefsIds.SELHYP_FINISH_START);
        hsStart = ref(SelectionRefsIds.SELHYP_CONFIG_HS_START);
        maStart = ref(SelectionRefsIds.SELHYP_CONFIG_MA_START);
        dataMain = ref(SelectionRefsIds.SELHYP_DATA_MAIN);
    }
    
    private void configureMainIteration() throws BuildException
    {
        mainHS.setBuildable(
            extensionStep().setNextStep(mainMA).
            addFirst(hsStart)
        );
        
        mainMA.setBuildable(
            extensionStep().setNextStep(updateState).
            addFirst(maStart)
        );
        
        updateState.setBuildable(
            sequentialStep().
            addOperator(
                operator(CheckMaxIteration.class).
                injectData(dataMain)
            ).
            addOperator(
                operator(CheckMaxTime.class).
                injectData(dataMain)
            )
        );
    }
    
    @Override
    public void process() throws BuildException
    {
        configureMainIteration();
        
        start.setBuildable(init);
        
        init.setBuildable(
            extensionStep().setNextStep(mainIteration).
            addFirst(initStart)
        );
        
        mainIteration.setBuildable(
            extensionStep().setNextStep(checkFinished).
            addFirst(mainHS)
        );
        
        checkFinished.setBuildable(
            booleanStep().setTrueStep(finish).setFalseStep(mainIteration).
            setDecider(
                operator(CheckFinished.class).
                injectData(dataMain)
            )
        );
        
        finish.setBuildable(
            extensionStep().
            addFirst(finishStart)
        );
    }
}
