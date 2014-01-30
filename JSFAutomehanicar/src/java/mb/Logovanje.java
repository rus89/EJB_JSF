/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import domen.Zaposleni;
import ejb.CallEJB;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Rus
 */
@ManagedBean
@SessionScoped
public class Logovanje {

    private Zaposleni zaposleni;
    private String poruka;

    public Logovanje() {
        zaposleni = new Zaposleni();
    }

    /**
     * @return the zaposleni
     */
    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    /**
     * @param zaposleni the zaposleni to set
     */
    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    /**
     * @return the poruka
     */
    public String getPoruka() {
        return poruka;
    }

    /**
     * @param poruka the poruka to set
     */
    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public String ulogujKorisnika() {
        FacesContext fc = FacesContext.getCurrentInstance();
        List<Zaposleni> lz = CallEJB.vratiInstancu().getZaposleni().vratiSveZaposlene();
        for (Zaposleni z : lz) {
            if (z.getUsername().equals(zaposleni.getUsername()) && z.getPassword().equals(zaposleni.getPassword())) {
                if (zaposleni.getUsername().equalsIgnoreCase("rus") && zaposleni.getPassword().equalsIgnoreCase("rus")) {
                    zaposleni = z;
                    fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logovanje uspesno! Dobrodosli, " + zaposleni.getIme(), ""));
                    return "pocetnaAdmin.xhtml";
                } else {
                    zaposleni = z;
                    fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logovanje uspesno! Dobrodosli, " + zaposleni.getIme(), ""));
                    return "pocetna.xhtml";
                }

            }
        }
        fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pogresno korisnicko ime ili sifra!", ""));
        return "index.xhtml";
    }

    public String izlogujSe() {
        zaposleni = new Zaposleni();
        return "/index.xhtml";
    }
}
