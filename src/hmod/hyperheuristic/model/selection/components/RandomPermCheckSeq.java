
package hmod.hyperheuristic.model.selection.components;

import hmod.core.BooleanOperator;
import hmod.core.Step;

/**
 *
 * @author Enrique Urra C.
 */
public class RandomPermCheckSeq extends BooleanOperator
{
    protected RandomPermData permData;

    public void setRandomPermHandler(RandomPermData permData)
    {
        this.permData = permData;
    }

    @Override
    public Boolean evaluate()
    {        
        Step[] sequence = permData.getCurrentSequence();
        
        if(sequence == null)
            return false;
        
        return true;
    }
}
