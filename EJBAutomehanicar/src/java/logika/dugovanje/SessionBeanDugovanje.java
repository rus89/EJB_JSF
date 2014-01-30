/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.dugovanje;

import domen.Dugovanje;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rus
 */
@Stateless
public class SessionBeanDugovanje implements SessionBeanDugovanjeLocal {

    @PersistenceContext(unitName = "EJBAutomehanicarPU")
    private EntityManager em;

    @Override
    public List<Dugovanje> vratiSvaDugovanja() {
        return em.createQuery("SELECT d FROM Dugovanje d").getResultList();
    }

    @Override
    public int vratiPK() {
        List<Dugovanje> ld = vratiSvaDugovanja();
        if (ld.isEmpty()) {
            return 1;
        }
        int pk = ld.get(ld.size() - 1).getSifraDugovanja();
        return pk + 1;
    }

    @Override
    public Dugovanje vratiDugovanje(Dugovanje d) {
        return em.find(Dugovanje.class, d.getSifraDugovanja());
    }

    @Override
    public void unesiDugovanje(Dugovanje d) {
        d.setSifraDugovanja(vratiPK());
        em.persist(d);
    }

    @Override
    public void izmeniDugovanje(Dugovanje d) {
        Dugovanje dug = em.find(Dugovanje.class, d.getSifraDugovanja());
        if (dug == null) {
            System.out.println("trazeni dug ne postoji!");
        }
        em.merge(d);
    }

    @Override
    public void obrisiDugovanje(Dugovanje d) {
        Dugovanje dug = em.find(Dugovanje.class, d.getSifraDugovanja());
        if (dug == null) {
            System.out.println("trazeni dug ne postoji!");
        }
        em.remove(dug);
    }
}
