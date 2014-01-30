/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.automobil;

import domen.Automobil;
import domen.ModelAutomobila;
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
public class SessionBeanAutomobil implements SessionBeanAutomobilLocal {

    @PersistenceContext(unitName = "EJBAutomehanicarPU")
    private EntityManager em;

    @Override
    public List<Automobil> vratiSveAutomobile() {
        return em.createQuery("SELECT a FROM Automobil a").getResultList();
    }

    @Override
    public List<ModelAutomobila> vratiSveModele() {
        return em.createQuery("SELECT m FROM ModelAutomobila m").getResultList();
    }

    @Override
    public List<VlasnikAutomobila> vratiSveVlasnike() {
        return em.createQuery("SELECT v FROM VlasnikAutomobila v").getResultList();
    }

    @Override
    public Automobil vratiAutomobil(Automobil a) {
        return em.find(Automobil.class, a.getSifraAutomobila());
    }

    @Override
    public ModelAutomobila vratiModel(ModelAutomobila ma) {
        return em.find(ModelAutomobila.class, ma.getSifraModela());
    }

    @Override
    public VlasnikAutomobila vratiVlasnika(VlasnikAutomobila va) {
        return em.find(VlasnikAutomobila.class, va.getJmbg());
    }

    @Override
    public void sacuvajAuotmobil(Automobil a) {
        a.setSifraAutomobila(vratiPK());
        em.persist(a);
    }

    @Override
    public void obrisiAutomobil(Automobil a) throws Exception {
        Automobil auto = em.find(Automobil.class, a.getSifraAutomobila());
        em.remove(auto);
    }

    @Override
    public int vratiPK() {
        List<Automobil> la = vratiSveAutomobile();
        if (la.isEmpty()) {
            return 1;
        }
        int pk = la.get(la.size() - 1).getSifraAutomobila();
        return pk + 1;
    }

    @Override
    public void izmeniAutomobil(Automobil a) throws Exception {
        Automobil auto = em.find(Automobil.class, a.getSifraAutomobila());
        if (auto == null) {
            throw new Exception("Trazeni automobil ne postoji u bazi.");
        }
        em.merge(a);
    }
}
