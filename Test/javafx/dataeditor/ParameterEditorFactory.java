/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.dataeditor;

import db.fx.propertyeditor.editor.NamePropertyEditor;
import db.fx.propertyeditor.generic.AbstractDataEditorFactory;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.editor.PropertyEditor;

/**
 *
 * @author user
 */
public class ParameterEditorFactory extends AbstractDataEditorFactory<Parameter>
{

    @Override
    public PropertyEditor callCustom(PropertySheet.Item item) {
        if(item.getName().equals("reactorType"))
            return new NamePropertyEditor(item, "-", "PWR", "Integral PWR", "BWR", "PHWR", "HTGR", 
                    "Floating NPP", "MSR", "Immersed NPP", "LMFR", "GMFR", "Modular Helium Reactor",
                    "Modular Helium HTR", "Modular HTGR", "Pool-type", "Loop type PWR");
        else if(item.getName().equals("designStatus"))
            return new NamePropertyEditor(item, "-", "Basic design","Commercial", "Licensing", "Conceptual design", 
                    "Preliminary design completed", "Advanced concept phase", "Conceptual design development", 
                    "Detailed design", "Conceptual design complete");
        else if(item.getName().equals("coolant"))
            return new NamePropertyEditor(item, "-", "Lightwater", "Helium", "Lead (208)", "Sodium",
                                               "Lead-Bismuth", "Lead-Bismuth eutectic alloy", "Flouride fuel salt", 
                                                "NaF, BeF2 salt", "Flibe", "Molten flouride", "None");
        else if(item.getName().equals("moderator"))
            return new NamePropertyEditor(item, "-", "Light water", "Heavy water", "Helium", "Graphite", "Sodium",
                                               "Lead-Bismuth", "Lead-Bismuth eutectic alloy", "Carbon", "None");
        else if(item.getName().equals("primaryCirculation"))
            return new NamePropertyEditor(item, "-", "Natural", "Forced");
        else
            return null;
    }
    
}
