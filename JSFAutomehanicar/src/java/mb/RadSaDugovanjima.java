/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import domen.Dugovanje;
import domen.VlasnikAutomobila;
import ejb.CallEJB;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author Rus
 */
@ManagedBean
@SessionScoped
public class RadSaDugovanjima {

    public static final int UNOS_DUGOVANJA = 1;
    public static final int IZMENA_DUGOVANJA = 2;
    private int operacija;
    private Dugovanje trenutnoDugovanje;
    private Dugovanje selektovanoDugovanje;
    private List<Dugovanje> listaDugovanja;
    private List<Dugovanje> filtriranaDugovanja;

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public Dugovanje getTrenutnoDugovanje() {
        return trenutnoDugovanje;
    }

    public void setTrenutnoDugovanje(Dugovanje trenutnoDugovanje) {
        this.trenutnoDugovanje = trenutnoDugovanje;
    }

    public Dugovanje getSelektovanoDugovanje() {
        return selektovanoDugovanje;
    }

    public void setSelektovanoDugovanje(Dugovanje selektovanoDugovanje) {
        this.selektovanoDugovanje = selektovanoDugovanje;
    }

    public List<Dugovanje> getListaDugovanja() {
        return listaDugovanja;
    }

    public void setListaDugovanja(List<Dugovanje> listaDugovanja) {
        this.listaDugovanja = listaDugovanja;
    }

    public List<Dugovanje> getFiltriranaDugovanja() {
        return filtriranaDugovanja;
    }

    public void setFiltriranaDugovanja(List<Dugovanje> filtriranaDugovanja) {
        this.filtriranaDugovanja = filtriranaDugovanja;
    }

    public RadSaDugovanjima() {
        inicijalizujListu();
    }

    private void inicijalizujListu() {
        listaDugovanja = CallEJB.vratiInstancu().getDugovanje().vratiSvaDugovanja();
    }

    public String unosDugovanja() {
        trenutnoDugovanje = new Dugovanje();
        operacija = UNOS_DUGOVANJA;
        return "/radSaDugovanjima/unosDugovanja.xhtml";
    }

    public String sacuvajDugovanja() {
        try {
            if (operacija == UNOS_DUGOVANJA) {
                for (Dugovanje d : listaDugovanja) {
                    if (trenutnoDugovanje.getSifraVlasnika().getSifraVlasnika() == d.getSifraVlasnika().getSifraVlasnika()) {
                        throw new Exception("poruka");
                    }
                }
                CallEJB.vratiInstancu().getDugovanje().unesiDugovanje(trenutnoDugovanje);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dugovanje je sacuvano!", null));
                inicijalizujListu();
                trenutnoDugovanje = new Dugovanje();
                return "/radSaDugovanjima/unosDugovanja.xhtml";
            } else {
                CallEJB.vratiInstancu().getDugovanje().izmeniDugovanje(trenutnoDugovanje);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dugovanje je uspesno izmenjeno!", null));
                inicijalizujListu();
                return "/radSaDugovanjima/prikazSvihDugovanja.xhtml";
            }
        } catch (Exception e) {
            if (e.getMessage().equalsIgnoreCase("poruka")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Izabrani vlasnik vrec ima dugovanje!", null));                
                return "/radSaDugovanjima/izmenaDugovanja.xhtml";
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dugovanje nije sacuvano!", null));
            return null;
        }
    }

    public String izmeniDugovanja() {
        try {
            if (trenutnoDugovanje == null) {
                throw new Exception("poruka");
            }
            UIComponent komponenta = FacesContext.getCurrentInstance().getViewRoot().findComponent("frmPromenaPodataka:tblPromenaDugovanja");
            DataTable dt = (DataTable) komponenta;
            trenutnoDugovanje = (Dugovanje) dt.getRowData(trenutnoDugovanje.getSifraDugovanja().toString());
            operacija = IZMENA_DUGOVANJA;
            return "/radSaDugovanjima/unosDugovanja.xhtml";
        } catch (Exception e) {
            if (e.getMessage().equalsIgnoreCase("poruka")) {
                System.out.println(e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate odabrati odredjeno dugovanje!", null));
            }
            return null;
        }
    }

    public void obrisiDugovanje() {
        try {
            if (trenutnoDugovanje == null) {
                throw new Exception("poruka");
            }

            UIComponent komponenta = FacesContext.getCurrentInstance().getViewRoot().findComponent("frmBrisanjeDugovanja:tblBrisanje");
            DataTable dt = (DataTable) komponenta;
            trenutnoDugovanje = (Dugovanje) dt.getRowData(trenutnoDugovanje.getSifraDugovanja().toString());

            CallEJB.vratiInstancu().getDugovanje().obrisiDugovanje(trenutnoDugovanje);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dugovanje je uspesno obrisano!", null));
            inicijalizujListu();
        } catch (Exception e) {
            if (e.getMessage().equalsIgnoreCase("poruka")) {
                System.out.println(e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate odabrati odredjeno dugovanje!", null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dugovanje nije obrisano!", null));
            }
        }
    }

    public void validateVrednostDugovanja(FacesContext context, UIComponent validate, Object value) {
        if (value == null) {
            return;
        }

        if (value.toString().isEmpty()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate uneti vrednost.", null);
            throw new ValidatorException(msg);
        }

        for (int i = 0; i < value.toString().length(); i++) {
            char znak = value.toString().charAt(i);
            if (Character.isLetter(znak)) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Vrednost ne moze sadrzati slova.", null);
                throw new ValidatorException(msg);
            }
        }
    }
}
