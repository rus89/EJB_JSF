/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.opis.intervencije;

import domen.Intervencija;
import domen.OpisIntervencije;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Rus
 */
@Local
public interface SessionBeanOpisIntervencijeLocal {
    
    public List<OpisIntervencije> vratiSveOpiseIntervencija();
    public OpisIntervencije vratiOpisIntervencije(OpisIntervencije oi);
    public int vratiPK();
    public void unosOpisaIntervencije(OpisIntervencije oi);
    public void izmenaOpisaIntervencije(OpisIntervencije oi);
    public void brisanjeOpisaIntervencije(OpisIntervencije oi);
    
}
