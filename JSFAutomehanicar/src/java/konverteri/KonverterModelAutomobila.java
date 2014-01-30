/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package konverteri;

import domen.ModelAutomobila;
import ejb.CallEJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Rus
 */
@FacesConverter(forClass = ModelAutomobila.class, value = "modelKonverter")
public class KonverterModelAutomobila implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        int sifraModela = Integer.parseInt(value);                
        return CallEJB.vratiInstancu().getModelAutomobila().vratiModel(new ModelAutomobila(sifraModela));

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        ModelAutomobila model = (ModelAutomobila) value;
        return String.valueOf(model.getSifraModela());
        
    }
}
