/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.marka;

import domen.MarkaAutomobila;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rus
 */
@Stateless
public class SessionBeanMarkaAutomobila implements SessionBeanMarkaAutomobilaLocal {

    @PersistenceContext(unitName = "EJBAutomehanicarPU")
    private EntityManager em;

    @Override
    public List<MarkaAutomobila> vratiSveMarke() {
        return em.createQuery("SELECT m FROM MarkaAutomobila m").getResultList();
    }

    @Override
    public MarkaAutomobila vratiMarku(MarkaAutomobila marka) {
        return em.find(MarkaAutomobila.class, marka.getSifraMarke());
    }
}
