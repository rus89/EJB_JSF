/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.uradjene.intervencije;

import domen.UradjeneIntervencije;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rus
 */
@Stateless
public class SessionBeanUradjeneIntervencije implements SessionBeanUradjeneIntervencijeLocal {

    @PersistenceContext(unitName = "EJBAutomehanicarPU")
    private EntityManager em;

    @Override
    public List<UradjeneIntervencije> vratiSveUradjeneIntervencije() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UradjeneIntervencije vratiUradjenuIntervenciju(UradjeneIntervencije ui) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void unosUradjenihIntervencija(UradjeneIntervencije ui) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void izmenaUradjenihIntervencija(UradjeneIntervencije ui) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void brisanjeUradjenihIntervencija(UradjeneIntervencije ui) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
