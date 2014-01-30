/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logika.dugovanje;

import domen.Dugovanje;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Rus
 */
@Local
public interface SessionBeanDugovanjeLocal {
    
    public List<Dugovanje> vratiSvaDugovanja();
    public Dugovanje vratiDugovanje(Dugovanje d);
    public void unesiDugovanje(Dugovanje d);
    public void izmeniDugovanje(Dugovanje d);
    public void obrisiDugovanje(Dugovanje d);
    public int vratiPK();
    
}
