/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logka.vlasnik;

import domen.VlasnikAutomobila;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Rus
 */
@Local
public interface SessionBeanVlasnikAutomobilaLocal {
    
    public List<VlasnikAutomobila> vratiSveVlasnike();
    public VlasnikAutomobila vratiVlasnika(VlasnikAutomobila va);
    public void sacuvajVlasnika(VlasnikAutomobila va);
    public void obrisiVlasnika(VlasnikAutomobila va);
    public void izmeniVlasnika(VlasnikAutomobila va) throws Exception;
    public int vratiPK();
    
}
