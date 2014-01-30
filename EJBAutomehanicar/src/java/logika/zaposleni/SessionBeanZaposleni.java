/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.zaposleni;

import domen.Zaposleni;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rus
 */
@Stateless
public class SessionBeanZaposleni implements SessionBeanZaposleniLocal {

    @PersistenceContext(unitName = "EJBAutomehanicarPU")
    private EntityManager em;
    
    @Override
    public List<Zaposleni> vratiSveZaposlene() {
        return em.createQuery("SELECT z FROM Zaposleni z").getResultList();
    }

    @Override
    public Zaposleni vratiZaposlenog(Zaposleni z) {
        return em.find(Zaposleni.class, z.getSifraZaposlenog());
    }

    @Override
    public void izmeniZaposlenog(Zaposleni z) throws Exception {
        Zaposleni zap = em.find(Zaposleni.class, z.getSifraZaposlenog());
        if (zap == null) {
            throw new Exception("Taženi zaposleni ne postoji u bazi!");
        }
        em.merge(z);
    }

    @Override
    public void sacuvajZaposlenog(Zaposleni z) {
        z.setSifraZaposlenog(vratiPK());
        em.persist(z);
    }

    @Override
    public void obrisiZaposlenog(Zaposleni z) throws Exception {
        Zaposleni zap = em.find(Zaposleni.class, z.getSifraZaposlenog());
        if (zap == null) {
            throw new Exception("Taženi zaposleni ne postoji u bazi!");
        }
        em.remove(zap);
    }

    @Override
    public int vratiPK() {
        List<Zaposleni> lista = vratiSveZaposlene();
        if (lista.isEmpty()) {
            return 1;
        }
        int pk = lista.get(lista.size() - 1).getSifraZaposlenog();
        return pk + 1;
    }
}
