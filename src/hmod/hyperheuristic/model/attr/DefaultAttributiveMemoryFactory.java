
package hmod.hyperheuristic.model.attr;

/**
 * Implements the default attributive memory factory.
 * @author Enrique Urra C.
 */
class DefaultAttributiveMemoryFactory extends AttrMemoryFactory
{
    @Override
    public AttrMemory newMemory()
    {
        return new DefaultAttributiveMemory();
    }

    @Override
    public AttrReadingCollection newReadingsCollection()
    {
        return new DefaultAttributeReadingCollection();
    }
}
