
package hmod.hyperheuristic.model.attr;

/**
 * Defines the singleton factory for attributive memory entities.
 * @author Enrique Urra C.
 */
public abstract class AttrMemoryFactory
{
    /**
     * The internal instance.
     */
    private static AttrMemoryFactory instance;
    
    /**
     * Gets the factory instance. By default, provides the default 
     * implementation of the factory.
     * @return The factory object.
     */
    public static AttrMemoryFactory getInstance()
    {
        if(instance == null)
            instance = new DefaultAttributiveMemoryFactory();
        
        return instance;
    }
    
    /**
     * Creates a new instance of an attributive memory structure.
     * @return The memory object.
     */
    public abstract AttrMemory newMemory();
    
    /**
     * Creates a new instance of an attribute readings collection.
     * @return The collection object.
     */
    public abstract AttrReadingCollection newReadingsCollection();
}
