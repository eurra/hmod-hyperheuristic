
package hmod.hyperheuristic.model.selection.scripts;

import static flexbuilders.basic.MetadataBuilders.metadata;
import static hmod.parser.builders.AlgorithmBuilders.*;
import flexbuilders.core.BuildException;
import flexbuilders.core.Buildable;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.TreeHandler;
import hmod.parser.builders.OperatorInfo;
import hmod.hyperheuristic.model.selection.components.AcceptState;
import hmod.hyperheuristic.model.selection.components.ImprovingOrEqualAcceptance;

/**
 *
 * @author Enrique Urra C.
 */
public class MAImprovingOrEqualScript extends BuildScript
{
    public MAImprovingOrEqualScript(TreeHandler input)
    {
        super(input);
    }
        
    @Override
    public void process() throws BuildException
    {
        Buildable dataMain = ref(SelectionRefsIds.SELHYP_DATA_MAIN);
        
        branch(SelectionRefsIds.SELHYP_MA_IMPROVING_OR_EQUAL).setBuildable(
            metadata().
            attachData(
                new OperatorInfo.Builder().
                category(HyperheuristicOperator.MOVE_ACCEPTANCE).
                name("Only Improving or Equals").
                description(
                    "Only solution changes that improve or maintain the current " + 
                    "solution quality are accepted."
                )
            ).
            setTarget(
                booleanStep().
                setDecider(
                    operator(ImprovingOrEqualAcceptance.class).
                    injectData(dataMain)
                ).
                setTrueStep(
                    sequentialStep().
                    addOperator(
                        operator(AcceptState.class).
                        injectData(dataMain)
                    )
                )
            )
        );
    }
}
