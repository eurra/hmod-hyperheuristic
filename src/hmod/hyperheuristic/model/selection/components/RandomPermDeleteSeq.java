
package hmod.hyperheuristic.model.selection.components;

import hmod.core.AlgorithmException;
import hmod.core.Operator;

/**
 *
 * @author Enrique Urra C.
 */
public class RandomPermDeleteSeq implements Operator
{
    private RandomPermData randomPermData;

    public void setRandomPermData(RandomPermData randomPermData)
    {
        this.randomPermData = randomPermData;
    }

    @Override
    public void execute() throws AlgorithmException
    {
        randomPermData.setCurrentSequence(null);
    }
}
