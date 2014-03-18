
package hmod.hyperheuristic.model.selection.components;

import hmod.hyperheuristic.model.selection.HHSolution;

/**
 *
 * @author Enrique Urra C.
 */
public class OnlyImprovingAcceptance extends SimpleAcceptanceCheck
{
    @Override
    public boolean checkAcceptance(HHSolution prevSolution, HHSolution newSolution)
    {
        return newSolution.compareTo(prevSolution) > 0;
    }
}
