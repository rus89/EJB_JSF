/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.opis.intervencije;

import domen.Intervencija;
import domen.OpisIntervencije;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rus
 */
@Stateless
public class SessionBeanOpisIntervencije implements SessionBeanOpisIntervencijeLocal {

    @PersistenceContext(unitName = "EJBAutomehanicarPU")
    private EntityManager em;

    @Override
    public List<OpisIntervencije> vratiSveOpiseIntervencija() {
        return em.createQuery("SELECT o FROM OpisIntervencije o").getResultList();
    }

    @Override
    public OpisIntervencije vratiOpisIntervencije(OpisIntervencije oi) {
        return em.find(OpisIntervencije.class, oi.getSifraOpisa());
    }

    @Override
    public void unosOpisaIntervencije(OpisIntervencije oi) {
        oi.setSifraOpisa(vratiPK());
    }

    @Override
    public void izmenaOpisaIntervencije(OpisIntervencije oi) {
        OpisIntervencije opis = em.find(OpisIntervencije.class, oi.getSifraOpisa());
        if (opis == null) {
            System.out.println("trazeni opis ne postoji");
        }
        em.merge(oi);
    }

    @Override
    public void brisanjeOpisaIntervencije(OpisIntervencije oi) {
        OpisIntervencije opis = em.find(OpisIntervencije.class, oi.getSifraOpisa());
        if (opis == null) {
            System.out.println("trazeni opis ne postoji");
        }
        em.remove(oi);
    }

    @Override
    public int vratiPK() {
        List<OpisIntervencije> l = vratiSveOpiseIntervencija();
        if (l.isEmpty()) {
            return 1;
        }
        int pk = l.get(l.size() - 1).getSifraOpisa();
        return pk + 1;
    }
}
