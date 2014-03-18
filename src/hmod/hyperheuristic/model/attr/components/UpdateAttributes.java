
package hmod.hyperheuristic.model.attr.components;

import hmod.hyperheuristic.model.attr.AttrProcessor;
import hmod.hyperheuristic.model.attr.HHAttrSolution;
import hmod.shh.attr.AttrReadingCollection;
import hmod.core.Operator;
import hmod.core.AlgorithmException;
import hmod.shh.SHSolutionData;

/**
 * Operator related to the strategic oscillation model.
 * Updates the attributes of the generated solution, based on the configured
 * attribute processors.
 * @author Enrique Urra C.
 */
public class UpdateAttributes implements Operator
{
    private SHSolutionData<HHAttrSolution> solutionHandler;
    private AttrProcessorData attrProcessorHandler;
    
    public void setSolutionData(SHSolutionData solutionHandler)
    {
        this.solutionHandler = solutionHandler;
    }

    public void setAttrProcessorHandler(AttrProcessorData attrProcessorHandler)
    {
        this.attrProcessorHandler = attrProcessorHandler;
    }
    
    @Override
    public boolean doOperation() throws AlgorithmException
    {
        AttrProcessor[] processors = attrProcessorHandler.getAttrProcessors();
        
        if(processors.length < 1)
            return true;
        
        HHAttrSolution hlSolution = solutionHandler.getInputSolution();
        AttrReadingCollection attrs = hlSolution.getAttributes();
        Object llSolution = lowLevelSolutionHandler.getReadSolution();
        
        for(AttrProcessor processor : processors)
            processor.processAttributes(llSolution, attrs);
        
        return true;
    }
}