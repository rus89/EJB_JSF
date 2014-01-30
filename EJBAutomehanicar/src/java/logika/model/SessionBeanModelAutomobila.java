/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.model;

import domen.MarkaAutomobila;
import domen.ModelAutomobila;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rus
 */
@Stateless
public class SessionBeanModelAutomobila implements SessionBeanModelAutomobilaLocal {

    @PersistenceContext(unitName = "EJBAutomehanicarPU")
    private EntityManager em;

    @Override
    public List<ModelAutomobila> vratiSveModele() {
        return em.createQuery("SELECT m FROM ModelAutomobila m").getResultList();
    }

    @Override
    public ModelAutomobila vratiModel(ModelAutomobila ma) {
        return em.find(ModelAutomobila.class, ma.getSifraModela());
    }

    @Override
    public List<MarkaAutomobila> vratiSveMarke() {
        return em.createQuery("SELECT m FROM MarkaAutomobila m").getResultList();
    }

    @Override
    public MarkaAutomobila vratiMarku(MarkaAutomobila marka) {
        return em.find(MarkaAutomobila.class, marka.getSifraMarke());
    }

    @Override
    public List<ModelAutomobila> vratiSveModeleZaOdredjenuMarku(MarkaAutomobila marka) {
        return em.createQuery("SELECT m FROM ModelAutomobila m WHERE m.modelAutomobilaPK.sifraMarke = " + marka.getSifraMarke().toString()).getResultList();
    }
}
