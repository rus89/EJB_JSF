/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package konverteri;

import domen.VlasnikAutomobila;
import ejb.CallEJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Rus
 */
@FacesConverter(forClass = VlasnikAutomobila.class, value = "konverterVlasnik")
public class KonverterVlasnikAutomobila implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        int sifraVlasnika = Integer.parseInt(value);
        return CallEJB.vratiInstancu().getVlasnikAutomobila().vratiVlasnika(new VlasnikAutomobila(sifraVlasnika));

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        VlasnikAutomobila v = (VlasnikAutomobila) value;
        return String.valueOf(v.getSifraVlasnika());

    }
}
