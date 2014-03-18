
package hmod.hyperheuristic.model.attr.components;

import hmod.core.DataInterface;
import hmod.shh.attr.AttrMemory;

/**
 * Data interface for the usage of an attributive memory component.
 * @author Enrique Urra C.
 */
public interface AttrMemoryData extends DataInterface
{    
    void setMemory(AttrMemory memory);
    AttrMemory getMemory();
}
