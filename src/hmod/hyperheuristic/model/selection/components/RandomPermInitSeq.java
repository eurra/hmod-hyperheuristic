
package hmod.hyperheuristic.model.selection.components;

import hmod.core.Operator;
import hmod.core.AlgorithmException;

/**
 *
 * @author Enrique Urra C.
 */
public class RandomPermInitSeq implements Operator
{
    private RandomPermData permData;

    public void setRandomPermHandler(RandomPermData permData)
    {
        this.permData = permData;
    }

    @Override
    public void execute() throws AlgorithmException
    {
        permData.setCurrentIteration(0);
    }
}
