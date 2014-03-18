
package hmod.hyperheuristic.model.basic.components;

import hmod.core.Operator;
import hmod.core.AlgorithmException;
import hmod.hyperheuristic.model.selection.HHSolution;
import hmod.hyperheuristic.model.selection.components.SHSolutionData;

/**
 * Solution encoding operator.
 * Picks the current solution from the low-level handler and encodes it.
 * @author Enrique Urra C.
 * @param <T> The high-level solution type.
 * @param <K> The low-level solution type.
 */
public abstract class EncodeSolutionOperator<T extends HHSolution, K> implements Operator
{
    protected SHSolutionData<T> highLevelSolutionHandler;
    protected LowLevelSolutionData<K> lowLevelSolutionData;

    public final void setSHSolutionData(SHSolutionData highLevelSolutionHandler)
    {
        this.highLevelSolutionHandler = highLevelSolutionHandler;
    }

    public final void setLowLevelSolutionData(LowLevelSolutionData<K> lowLevelSolutionData)
    {
        this.lowLevelSolutionData = lowLevelSolutionData;
    }
    
    @Override
    public void execute() throws AlgorithmException
    {
        K llSolution = lowLevelSolutionData.getInputSolution();
        T hhSolution = encode(llSolution);
        highLevelSolutionHandler.setInputSolution(hhSolution);
    }
    
    public abstract T encode(K llSolution) throws AlgorithmException;
}