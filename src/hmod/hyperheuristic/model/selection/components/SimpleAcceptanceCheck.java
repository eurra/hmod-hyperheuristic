
package hmod.hyperheuristic.model.selection.components;

import hmod.core.AlgorithmException;
import hmod.core.BooleanOperator;
import hmod.hyperheuristic.model.selection.HHSolution;

/**
 *
 * @author Enrique Urra C.
 */
public abstract class SimpleAcceptanceCheck<T extends HHSolution> extends BooleanOperator
{
    private SHSolutionData<T> solutionHandler;

    public void setSolutionData(SHSolutionData solutionHandler)
    {
        this.solutionHandler = solutionHandler;
    }
    
    @Override
    public Boolean evaluate() throws AlgorithmException
    {        
        T currAcceptedSolution = solutionHandler.getAcceptedSolution();        
        T currSelectedSolution = solutionHandler.getSelectedSolution();
        
        if(currAcceptedSolution == null)
            return true;
        else if(currSelectedSolution == null)
            return false;
        
        return checkAcceptance(currAcceptedSolution, currSelectedSolution);
    }
    
    public abstract boolean checkAcceptance(T prevSolution, T newSolution);
}
