/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.model;

import domen.MarkaAutomobila;
import domen.ModelAutomobila;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Rus
 */
@Local
public interface SessionBeanModelAutomobilaLocal {
 
    public List<ModelAutomobila> vratiSveModele();
    public ModelAutomobila vratiModel(ModelAutomobila ma);
    public List<MarkaAutomobila> vratiSveMarke();
    public MarkaAutomobila vratiMarku(MarkaAutomobila marka);
    public List<ModelAutomobila> vratiSveModeleZaOdredjenuMarku(MarkaAutomobila marka);
    
}
