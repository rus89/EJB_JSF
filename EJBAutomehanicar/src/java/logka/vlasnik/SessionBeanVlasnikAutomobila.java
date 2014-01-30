/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logka.vlasnik;

import domen.VlasnikAutomobila;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rus
 */
@Stateless
public class SessionBeanVlasnikAutomobila implements SessionBeanVlasnikAutomobilaLocal {

    @PersistenceContext(unitName = "EJBAutomehanicarPU")
    private EntityManager em;

    @Override
    public List<VlasnikAutomobila> vratiSveVlasnike() {
        return em.createQuery("SELECT v FROM VlasnikAutomobila v").getResultList();
    }

    @Override
    public VlasnikAutomobila vratiVlasnika(VlasnikAutomobila va) {
        return em.find(VlasnikAutomobila.class, va.getSifraVlasnika());
    }

    @Override
    public void sacuvajVlasnika(VlasnikAutomobila va) {
        va.setSifraVlasnika(vratiPK());
        em.persist(va);
    }

    @Override
    public void obrisiVlasnika(VlasnikAutomobila va) {
        VlasnikAutomobila vlasnik = em.find(VlasnikAutomobila.class, va.getSifraVlasnika());
        em.remove(vlasnik);
    }

    @Override
    public void izmeniVlasnika(VlasnikAutomobila va) throws Exception {
        VlasnikAutomobila v = em.find(VlasnikAutomobila.class, va.getSifraVlasnika());        
        if (v == null) {
            throw new Exception("Trazeni vlasnik ne postoji u bazi!");
        }
        em.merge(va);
    }

    @Override
    public int vratiPK() {
        List<VlasnikAutomobila> lista = vratiSveVlasnike();
        if (lista.isEmpty()) {
            System.out.println(lista);
            return 1;
        }
        int pk = lista.get(lista.size() - 1).getSifraVlasnika();
        return pk + 1;
    }
}
