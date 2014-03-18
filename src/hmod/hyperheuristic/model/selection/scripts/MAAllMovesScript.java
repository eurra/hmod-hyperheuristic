
package hmod.hyperheuristic.model.selection.scripts;

import static flexbuilders.basic.MetadataBuilders.metadata;
import static hmod.parser.builders.AlgorithmBuilders.*;
import flexbuilders.core.BuildException;
import flexbuilders.scripting.BuildScript;
import flexbuilders.tree.TreeHandler;
import hmod.parser.builders.OperatorInfo;
import hmod.hyperheuristic.model.selection.components.AcceptState;

/**
 *
 * @author Enrique Urra C.
 */
public class MAAllMovesScript extends BuildScript
{
    public MAAllMovesScript(TreeHandler input)
    {
        super(input);
    }
    
    @Override
    public void process() throws BuildException
    {
        branch(SelectionRefsIds.SELHYP_MA_ALL_MOVES).setBuildable(
            metadata().
            attachData(
                new OperatorInfo.Builder().
                category(HyperheuristicOperator.MOVE_ACCEPTANCE).
                name("Accept all moves").
                description(
                    "All solution changes are accepted."
                )
            ).
            setTarget(
                sequentialStep().
                addOperator(
                    operator(AcceptState.class).
                    injectData(ref(SelectionRefsIds.SELHYP_DATA_MAIN))
                )
            )
            
        );
    }
}
