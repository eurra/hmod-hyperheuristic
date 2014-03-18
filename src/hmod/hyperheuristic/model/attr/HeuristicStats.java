
package hmod.hyperheuristic.model.attr;

/**
 *
 * @author Enrique Urra C.
 */
public class HeuristicStats
{
    private int readingCount;
    private double changeImpactTotal;
    
    public void addChangeImpactReading(double reading)
    {
        changeImpactTotal += reading;
        readingCount++;
    }
    
    public double getChangeImpactMean()
    {
        if(readingCount == 0.0)
            return 1.0;
        
        return changeImpactTotal / (double)readingCount;
    }
}
