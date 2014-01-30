/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.automobil;

import domen.Automobil;
import domen.ModelAutomobila;
import domen.VlasnikAutomobila;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Rus
 */
@Local
public interface SessionBeanAutomobilLocal {
    
    public List<Automobil> vratiSveAutomobile();
    public List<ModelAutomobila> vratiSveModele();
    public List<VlasnikAutomobila> vratiSveVlasnike();
    public Automobil vratiAutomobil(Automobil a);
    public ModelAutomobila vratiModel(ModelAutomobila ma);
    public VlasnikAutomobila vratiVlasnika(VlasnikAutomobila va);
    public void sacuvajAuotmobil(Automobil a);
    public void obrisiAutomobil(Automobil a) throws Exception;
    public void izmeniAutomobil(Automobil a) throws Exception;
    public int vratiPK();
    
}
