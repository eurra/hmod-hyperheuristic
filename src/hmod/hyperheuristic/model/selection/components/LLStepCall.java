
package hmod.hyperheuristic.model.selection.components;

import static flexbuilders.basic.BasicBuilders.*;
import flexbuilders.core.BuildException;
import static hmod.parser.builders.AlgorithmBuilders.*;
import hmod.core.Operator;
import hmod.core.AlgorithmException;

/**
 *
 * @author Enrique Urra C.
 */
public class LLStepCall implements Operator
{
    private LLStepData stepHandler;

    public void setStepData(LLStepData stepHandler)
    {
        this.stepHandler = stepHandler;
    }
    
    @Override
    public void execute() throws AlgorithmException
    {        
        try
        {
            algorithm().startWith(value(stepHandler.getCurrentLLStep())).build().start();
        }
        catch(BuildException ex)
        {
            throw new AlgorithmException("Cannot create an algorithm object: " + ex.getMessage());
        }
    }
}
