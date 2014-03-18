
package hmod.hyperheuristic.model.attr.components;

import hmod.hyperheuristic.model.attr.HHAttrSolution;
import hmod.core.Operator;
import hmod.core.AlgorithmException;
import hmod.shh.HHSolution;
import hmod.shh.SHSolutionData;

/**
 * Operator related to the strategic oscillation model.
 * Updates the information related to the improvement of the current local and
 * global optimum.
 * @author Enrique Urra C.
 */
public class UpdateImprovementData implements Operator
{
    private SHSolutionData<HHAttrSolution> solutionHandler;
    private SOBasicData soDataHandler;
    
    public void setSolutionData(SHSolutionData handler)
    {
        this.solutionHandler = handler;
    }
    
    public void setSODataHandler(SOBasicData handler)
    {
        this.soDataHandler = handler;
    }

    @Override
    public boolean doOperation() throws AlgorithmException
    {
        //HHSolution outputSolution = solutionHandler.getOutputSolution();
        HHSolution outputSolution = solutionHandler.getAcceptedSolution();
        
        // Output solution can be null if the initializer heuristic was executed.
        if(outputSolution == null)
            return true;
        
        HHSolution inputSolution = solutionHandler.getInputSolution();
        HHSolution bestSolution = solutionHandler.getBestSolution();
        int currNonLocalImprove = soDataHandler.getNoLocalImproveIterations();
        int currNonGlobalImprove = soDataHandler.getNoGlobalImproveIterations();
        
        if(inputSolution.compareTo(outputSolution) <= 0)
            soDataHandler.setNoLocalImproveIterations(++currNonLocalImprove);
        else
            soDataHandler.setNoLocalImproveIterations(0);
        
        if(inputSolution.compareTo(bestSolution) <= 0)
            soDataHandler.setNoGlobalImproveIterations(++currNonGlobalImprove);
        
        return true;
    }
}