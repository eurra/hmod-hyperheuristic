
package hmod.hyperheuristic.model.attr;

import java.util.Iterator;
import java.util.Map;

/**
 * Defines the behaviour of an attribute-based memory for selection 
 * hyperheuristics.
 * @author Enrique Urra C.
 */
public interface AttrMemory
{
    /**
     * Returns the size of the sample from which the frecuency calculations have
     * been done. For example, the count of checked solutions is a common sample
     * size.
     * @return The size as a number.
     */
    int getSampleSize();
    
    /**
     * Indicates if the memory contains an attribute with the specified id.
     * @param attrId The id of the attribute.
     * @return true if the memory contains the attribute, false otherwise.
     */
    boolean hasAttribute(String attrId);
    
    /**
     * Registers a new reading for the specified attribute. By calling this 
     * method, the ocurrence of the attribute within the search is notified.
     * @param attrId The id of the attribute.
     * @param iteration The number of the iteration on which the reading has 
     *  been collected.
     */
    void addReading(String attrId, int iteration);
    
    /**
     * Registers a collection of readings at a specific iteration.
     * @param collection The attribute readings collection.
     * @param iteration The number of the iteration on which the readings have 
     *  been collected.
     */
    void addReading(AttrReadingCollection collection, int iteration);
    
    /**
     * Unregisters a reading ocurrence for the specified attribute.
     * @param attrId The id of the attribute.
     */
    void removeReading(String attrId);
    
    /**
     * Unregisters a collection of reading occurences.
     * @param collection The attribute readings collection.
     */
    void removeReading(AttrReadingCollection collection);
    
    /**
     * Gets a porcentual frequence measure of the specified attribute.
     * @param attrId The id of the attribute.
     * @return The measure as percentage.
     */
    double getReadingFrequency(String attrId);
    
    /**
     * Gets the total readings count of the specified attribute.
     * @param attrId The id of the attribute.
     * @return The readings quantity.
     */
    int getAttrReadingsCount(String attrId);
    
    /**
     * Get the last iteration on which the specified attribute has been 
     * observed.
     * @param attrId The id of the attribute.
     * @return The iteration number.
     */
    int getAttrLastIteration(String attrId);
    
    /**
     * Gets the current status of the readed frecuencies in a Map form.
     * @return a Map with the attributes (keys) and their related frecuencies
     *  (values).
     */
    Map<String, Double> getReadingFrequenciesSnapshot();
    
    /**
     * Gets an iterator for the attributes within this memory.
     * @return The iterator object.
     */
    Iterator<String> getAttributesIds();
    
    /**
     * Gets the attributes count in memory.
     * @return The attributes count.
     */
    int getAttributesCount();
}
