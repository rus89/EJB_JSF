/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.marka;

import domen.MarkaAutomobila;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Rus
 */
@Local
public interface SessionBeanMarkaAutomobilaLocal {
 
    public List<MarkaAutomobila> vratiSveMarke();
    public MarkaAutomobila vratiMarku(MarkaAutomobila marka);
    
}
