
package hmod.hyperheuristic.model.attr;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Implements a default attribute reading collection.
 * @author Enrique Urra C.
 */
class DefaultAttributeReadingCollection implements AttrReadingCollection
{
    private HashSet<String> attrs;

    public DefaultAttributeReadingCollection()
    {
        attrs = new HashSet<String>();
    }

    @Override
    public void addAttribute(String attrId)
    {
        attrs.add(attrId);
    }

    @Override
    public boolean hasAttribute(String attrId)
    {
        return attrs.contains(attrId);
    }

    @Override
    public Iterator<String> getIterator()
    {
        return attrs.iterator();
    }

    @Override
    public int getCount()
    {
        return attrs.size();
    }
}