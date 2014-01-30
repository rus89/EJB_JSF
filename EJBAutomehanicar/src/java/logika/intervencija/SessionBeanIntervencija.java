/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.intervencija;

import domen.Intervencija;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rus
 */
@Stateless
public class SessionBeanIntervencija implements SessionBeanIntervencijaLocal {

    @PersistenceContext(unitName = "EJBAutomehanicarPU")
    private EntityManager em;

    @Override
    public List<Intervencija> vratiSveIntervencije() {
        return em.createQuery("SELECT i FROM Intervencija i").getResultList();
    }

    @Override
    public Intervencija vratiIntervenciju(Intervencija i) {
        return em.find(Intervencija.class, i.getSifraIntervencije());
    }

    @Override
    public void sacuvajIntervenciju(Intervencija i) {
        i.setSifraIntervencije(vratiPK());
        em.persist(i);
    }

    @Override
    public void izmeniIntervenciju(Intervencija i) {
        Intervencija in = em.find(Intervencija.class, i.getSifraIntervencije());
        if (in == null) {
            System.out.println("ne postoji intervencija");
        }
        em.merge(i);
    }

    @Override
    public void obrisiIntervenciju(Intervencija i) {
        Intervencija in = em.find(Intervencija.class, i.getSifraIntervencije());
        if (in == null) {
            System.out.println("ne postoji intervencija");
        }
        em.remove(in);
    }

    @Override
    public int vratiPK() {
        List<Intervencija> li = vratiSveIntervencije();
        if (li.isEmpty()) {
            return 1;
        }
        int pk = li.get(li.size() - 1).getSifraIntervencije();
        return pk + 1;
    }
}
