
package hmod.hyperheuristic.model.attr;

/**
 * Defines an attribute processor, which can read a particular low-level 
 * solution representation and extract particular attributes from it. The 
 * attributes can be registered into an attribute collection.
 * @author Enrique Urra C.
 */
public interface AttrProcessor<T>
{
    /**
     * Performs the processing of attributes.
     * @param solution The input solution, whose type is parametrized.
     * @param collection The target attribute collection.
     */
    void processAttributes(T solution, AttrReadingCollection collection);
}
