
package hmod.hyperheuristic.model.attr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Implements the default attributive memory.
 * @author Enrique Urra C.
 */
class DefaultAttributiveMemory implements AttrMemory
{
    private HashMap<String, Integer> frequencyMemory;
    private HashMap<String, Integer> recencyMemory;
    private int readingsCount;

    public DefaultAttributiveMemory()
    {
        this.frequencyMemory = new HashMap<String, Integer>();
        this.recencyMemory = new HashMap<String, Integer>();
        this.readingsCount = 0;
    }
    
    @Override
    public int getSampleSize()
    {
        return readingsCount;
    }

    @Override
    public boolean hasAttribute(String attrId)
    {
        return frequencyMemory.containsKey(attrId);
    }

    @Override
    public void addReading(String attrId, int iteration)
    {
        incrementAttr(attrId, iteration);
        readingsCount++;
    }
    
    @Override
    public void addReading(AttrReadingCollection collection, int iteration)
    {
        if(collection == null)
            throw new NullPointerException("The provided collection is null");
        
        Iterator<String> iterator = collection.getIterator();
        
        while(iterator.hasNext())
            incrementAttr(iterator.next(), iteration);
        
        readingsCount++;
    }
    
    @Override
    public void removeReading(String attrId)
    {
        decrementAttr(attrId);
        readingsCount--;
    }

    @Override
    public void removeReading(AttrReadingCollection collection)
    {
        if(collection == null)
            throw new NullPointerException("The provided collection is null");
        
        Iterator<String> iterator = collection.getIterator();
        
        while(iterator.hasNext())
            decrementAttr(iterator.next());
        
        readingsCount--;
    }
    
    private void incrementAttr(String attrId, int iteration)
    {
        Integer currAttrCount = frequencyMemory.get(attrId);
        
        if(currAttrCount == null)
            currAttrCount = 0;
        
        currAttrCount++;
        
        if(currAttrCount > 25){
            int a = 0;}
        
        frequencyMemory.put(attrId, currAttrCount);
        recencyMemory.put(attrId, iteration);
    }
    
    private void decrementAttr(String attrId)
    {
        Integer currAttrCount = frequencyMemory.get(attrId);
        
        if(currAttrCount == null || currAttrCount == 0)
            return;
        
        currAttrCount--;
        
        if(currAttrCount == 0)
            frequencyMemory.remove(attrId);
        else
            frequencyMemory.put(attrId, currAttrCount);
    }

    @Override
    public double getReadingFrequency(String attrId)
    {
        Integer currAttrCount = frequencyMemory.get(attrId);
        
        if(currAttrCount == null)
            return 0.0;
        
        return (double)currAttrCount / (double)readingsCount;
    }

    @Override
    public int getAttrReadingsCount(String attrId)
    {
        Integer currAttrCount = frequencyMemory.get(attrId);
        
        if(currAttrCount == null)
            return 0;
        
        return currAttrCount;
    }

    @Override
    public int getAttrLastIteration(String attrId)
    {
        Integer lastAttrIteration = recencyMemory.get(attrId);
        
        if(lastAttrIteration == null)
            return 0;
        
        return lastAttrIteration;
    }

    @Override
    public Map<String, Double> getReadingFrequenciesSnapshot()
    {
        Map<String, Double> res = new HashMap<String, Double>(frequencyMemory.size());
        
        for(String attrId : frequencyMemory.keySet())
            res.put(attrId, getReadingFrequency(attrId));
        
        return res;
    }

    @Override
    public Iterator<String> getAttributesIds()
    {
        return frequencyMemory.keySet().iterator();
    }

    @Override
    public int getAttributesCount()
    {
        return frequencyMemory.size();
    }
}
