
package hmod.hyperheuristic.model.attr;

import hmod.hyperheuristic.model.selection.HHSolution;


/**
 * Defines an extension of the common high-level solution which incorporates
 * attributive data.
 * @author Enrique Urra C.
 */
public abstract class HHAttrSolution<T extends HHSolution<T>, K> implements HHSolution<T>
{
    private K lowLevelSolution;
    private AttrReadingCollection attributesCollection;

    public HHAttrSolution(K lowLevelSolution, AttrReadingCollection attributesCollection)
    {
        if(lowLevelSolution == null)
            throw new NullPointerException("Null low level solution");
        
        if(attributesCollection == null)
            throw new NullPointerException("Null attributes collection");
        
        this.lowLevelSolution = lowLevelSolution;
        this.attributesCollection = attributesCollection;
    }
    
    /**
     * Gets the attributes considered within the solution.
     * @return The attributes collection.
     */
    public final AttrReadingCollection getAttributes()
    {
        return attributesCollection;
    }
    
    public final K getLowLevelSolution()
    {
        return lowLevelSolution;
    }
}