/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.uradjene.intervencije;

import domen.UradjeneIntervencije;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Rus
 */
@Local
public interface SessionBeanUradjeneIntervencijeLocal {
    
    public List<UradjeneIntervencije> vratiSveUradjeneIntervencije();
    public UradjeneIntervencije vratiUradjenuIntervenciju(UradjeneIntervencije ui);
    public void unosUradjenihIntervencija(UradjeneIntervencije ui);
    public void izmenaUradjenihIntervencija(UradjeneIntervencije ui);
    public void brisanjeUradjenihIntervencija(UradjeneIntervencije ui);
    
}
