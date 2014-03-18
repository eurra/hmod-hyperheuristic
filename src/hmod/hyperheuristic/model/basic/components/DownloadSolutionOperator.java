
package hmod.hyperheuristic.model.basic.components;

import hmod.core.AlgorithmException;
import hmod.core.Operator;

/**
 *
 * @author Enrique Urra C.
 */
public abstract class DownloadSolutionOperator<T> implements Operator
{
    protected LowLevelSolutionData<T> lowLevelSolutionData;

    public final void setLowLevelSolutionData(LowLevelSolutionData<T> lowLevelSolutionData)
    {
        this.lowLevelSolutionData = lowLevelSolutionData;
    }

    @Override
    public void execute() throws AlgorithmException
    {
        T llSolution = lowLevelSolutionData.getInputSolution();
        
        if(llSolution != null)
            download(llSolution);
    }
    
    public abstract void download(T llSolution) throws AlgorithmException;
}
