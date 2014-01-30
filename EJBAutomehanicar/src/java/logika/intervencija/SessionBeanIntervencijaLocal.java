/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.intervencija;

import domen.Intervencija;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Rus
 */
@Local
public interface SessionBeanIntervencijaLocal {
    
    public List<Intervencija> vratiSveIntervencije();
    public Intervencija vratiIntervenciju(Intervencija i);
    public void sacuvajIntervenciju(Intervencija i);
    public void izmeniIntervenciju(Intervencija i);
    public void obrisiIntervenciju(Intervencija i);
    public int vratiPK();
    
}
