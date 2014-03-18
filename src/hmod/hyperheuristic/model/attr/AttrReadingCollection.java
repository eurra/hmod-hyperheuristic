
package hmod.hyperheuristic.model.attr;

import java.util.Iterator;

/**
 * Defines a collection of attributes readings which could be collected and can
 * be provided to a memory structure to be registered.
 * @author Enrique Urra C.
 */
public interface AttrReadingCollection
{    
    /**
     * Adds a new attribute to the collection.
     * @param attrId The id of the attribute.
     */
    void addAttribute(String attrId);
    
    /**
     * Checks if a particular attribute exists in the collection.
     * @param attrId The id of the attribute.
     * @return true if the attribute exists, false otherwise.
     */
    boolean hasAttribute(String attrId);
    
    /**
     * Gets an iterator to transverse the attribute ids registered within this
     * collection.
     * @return The attribute ids iterator.
     */
    Iterator<String> getIterator();
    
    /**
     * Gets the attributes count in the collection.
     * @return The attributes count.
     */
    int getCount();
}