
package hmod.hyperheuristic.model.attr.components;

import hmod.core.DataInterface;
import hmod.hyperheuristic.model.attr.AttrProcessor;

/**
 *
 * @author Enrique Urra C.
 */
public interface AttrProcessorData extends DataInterface
{
    void setAttrProcessors(AttrProcessor[] processors);
    AttrProcessor[] getAttrProcessors();
}
