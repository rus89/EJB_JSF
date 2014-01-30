/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.zaposleni;

import domen.Zaposleni;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Rus
 */
@Local
public interface SessionBeanZaposleniLocal {
    
    public List<Zaposleni> vratiSveZaposlene();
    public Zaposleni vratiZaposlenog(Zaposleni z);
    public void izmeniZaposlenog(Zaposleni z) throws Exception;
    public void sacuvajZaposlenog(Zaposleni z);
    public void obrisiZaposlenog(Zaposleni z) throws Exception;
    public int vratiPK();
    
}
