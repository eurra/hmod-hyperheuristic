
package hmod.hyperheuristic.model.basic.components;

import hmod.core.Operator;
import hmod.core.AlgorithmException;
import hmod.hyperheuristic.model.selection.HHSolution;
import hmod.hyperheuristic.model.selection.components.SHSolutionData;

/**
 * Solution decoding operator.
 * Picks the current solution from the high-level handler and decodes it.
 * @author Enrique Urra C.
 * @param <T> The high-level solution type.
 * @param <K> The low-level solution type.
 */
public abstract class DecodeSolutionOperator<T extends HHSolution, K> implements Operator
{     
    protected SHSolutionData<T> shSolutionData;
    protected LowLevelSolutionData<K> lowLevelSolutionData;

    public final void setSHSolutionData(SHSolutionData<T> shSolutionData)
    {
        this.shSolutionData = shSolutionData;
    }

    public final void setLowLevelSolutionData(LowLevelSolutionData<K> lowLevelSolutionData)
    {
        this.lowLevelSolutionData = lowLevelSolutionData;
    }
    
    @Override
    public void execute() throws AlgorithmException
    {        
        T hhSolution = shSolutionData.getInputSolution();
        
        if(hhSolution != null)
        {
            K llSolution = decode(hhSolution);
            lowLevelSolutionData.setInputSolution(llSolution);
        }
    }
    
    public abstract K decode(T solution) throws AlgorithmException;    
}