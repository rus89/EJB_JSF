/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import domen.VlasnikAutomobila;
import ejb.CallEJB;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author Rus
 */
@ManagedBean
@SessionScoped
public class RadSaVlasnikomAutomobila {

    private VlasnikAutomobila vlasnikAutomobila;
    private VlasnikAutomobila selektovaniVlasnikAutomobila;
    private List<VlasnikAutomobila> listaVlasnika;
    private List<VlasnikAutomobila> filtriraniVlasnici;
    private int operacija;
    public static final int UNOS_VLASNIKA = 1;
    public static final int IZMENA_VLASNIKA = 2;

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public VlasnikAutomobila getSelektovaniVlasnikAutomobila() {
        return selektovaniVlasnikAutomobila;
    }

    public void setSelektovaniVlasnikAutomobila(VlasnikAutomobila selektovaniVlasnikAutomobila) {
        this.selektovaniVlasnikAutomobila = selektovaniVlasnikAutomobila;
    }

    public List<VlasnikAutomobila> getFiltriraniVlasnici() {
        return filtriraniVlasnici;
    }

    public void setFiltriraniVlasnici(List<VlasnikAutomobila> filtriraniVlasnici) {
        this.filtriraniVlasnici = filtriraniVlasnici;
    }

    public List<VlasnikAutomobila> getListaVlasnika() {
        return listaVlasnika;
    }

    public void setListaVlasnika(List<VlasnikAutomobila> listaVlasnika) {
        this.listaVlasnika = listaVlasnika;
    }

    public VlasnikAutomobila getVlasnikAutomobila() {
        return vlasnikAutomobila;
    }

    public void setVlasnikAutomobila(VlasnikAutomobila vlasnikAutomobila) {
        this.vlasnikAutomobila = vlasnikAutomobila;
    }

    /**
     * Creates a new instance of RadSaVlasnikomAutomobila
     */
    public RadSaVlasnikomAutomobila() {
        inicijalizujListu();
    }

    private void inicijalizujListu() {
        listaVlasnika = CallEJB.vratiInstancu().getVlasnikAutomobila().vratiSveVlasnike();
    }

    public String dodavanjeVlasnika() {
        vlasnikAutomobila = new VlasnikAutomobila();
        operacija = UNOS_VLASNIKA;
        return "/radSaVlasnikom/unosVlasnika.xhtml";
    }

    public String sacuvajVlasnika() {
        try {
            if (operacija == UNOS_VLASNIKA) {
                CallEJB.vratiInstancu().getVlasnikAutomobila().sacuvajVlasnika(vlasnikAutomobila);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Vlasnik je uspesno sacuvan!", null));
                inicijalizujListu();
                vlasnikAutomobila = new VlasnikAutomobila();
                return "/radSaVlasnikom/unosVlasnika.xhtml";
            } else {
                CallEJB.vratiInstancu().getVlasnikAutomobila().izmeniVlasnika(vlasnikAutomobila);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Vlasnik je uspesno izmenjen!", null));
                inicijalizujListu();
                return "/radSaVlasnikom/prikazSvihVlasnika.xhtml";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Vlasnik nije sacuvan!", null));
            return null;
        }
    }

    public String izmeniVlasnika() {
        try {
            if (vlasnikAutomobila == null) {
                throw new Exception("poruka");
            }
            UIComponent komponenta = FacesContext.getCurrentInstance().getViewRoot().findComponent("frmPromenaPodataka:tblPromenaPodatakaVlasnika");
            DataTable dt = (DataTable) komponenta;
            vlasnikAutomobila = (VlasnikAutomobila) dt.getRowData(vlasnikAutomobila.getJmbg().toString());
            operacija = IZMENA_VLASNIKA;
            return "/radSaVlasnikom/unosVlasnika.xhtml";
        } catch (Exception e) {
            if (e.getMessage().equalsIgnoreCase("poruka")) {
                System.out.println(e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate odabrati odredjenog vlasnika!", null));
            }
            return null;
        }


    }

    public void obrisiVlasnika() {

        try {
            if (vlasnikAutomobila == null) {
                throw new Exception("poruka");
            }

            UIComponent komponenta = FacesContext.getCurrentInstance().getViewRoot().findComponent("frmBrisanjeVlasnika:tblBrisanjeVlasnika");
            DataTable dt = (DataTable) komponenta;
            vlasnikAutomobila = (VlasnikAutomobila) dt.getRowData(vlasnikAutomobila.getJmbg());

            CallEJB.vratiInstancu().getVlasnikAutomobila().obrisiVlasnika(vlasnikAutomobila);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Vlasnik je uspesno obrisan!", null));
            inicijalizujListu();

        } catch (Exception e) {
            if (e.getMessage().equalsIgnoreCase("poruka")) {
                System.out.println(e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate odabrati odredjenog vlasnika!", null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Vlasnik nije obrisan!", null));
            }
        }
    }

    public void validateJmbg(FacesContext context, UIComponent validate, Object value) {
        if (value == null) {
            return;
        }

        if (value.toString().isEmpty()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate uneti JMBG.", null);
            throw new ValidatorException(msg);
        }

        if (value.toString().length() != 13) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "JMBG mora sadrzati 13 cifara.", null);
            throw new ValidatorException(msg);
        }

        List<VlasnikAutomobila> lista = CallEJB.vratiInstancu().getVlasnikAutomobila().vratiSveVlasnike();

        for (VlasnikAutomobila va : lista) {
            if (va.getJmbg().equals(value.toString()) && operacija == UNOS_VLASNIKA) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Postoji vlasnik sa unetim JMBG-om.", null);
                throw new ValidatorException(msg);
            }
        }

//        for (VlasnikAutomobila va : lista) {
//            if (va.getJmbg().equals(value.toString())) {
//                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Postoji vlasnik sa unetim JMBG-om.", null);
//                throw new ValidatorException(msg);
//            }
//        }
    }

    public void validateIme(FacesContext context, UIComponent validate, Object value) {
        if (value == null) {
            return;
        }

        if (value.toString().isEmpty()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate uneti ime.", null);
            throw new ValidatorException(msg);
        }

        for (int i = 0; i < value.toString().length(); i++) {
            char znak = value.toString().charAt(i);
            if (Character.isDigit(znak)) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ime ne moze sadrzati brojeve.", null);
                throw new ValidatorException(msg);
            }
        }
    }

    public void validatePrezime(FacesContext context, UIComponent validate, Object value) {
        if (value == null) {
            return;
        }

        if (value.toString().isEmpty()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate uneti prezime.", null);
            throw new ValidatorException(msg);
        }

        for (int i = 0; i < value.toString().length(); i++) {
            char znak = value.toString().charAt(i);
            if (Character.isDigit(znak)) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Prezime ne moze sadrzati brojeve.", null);
                throw new ValidatorException(msg);
            }
        }
    }

    public List<SelectItem> vratiListuVlasnikaAutomobilaZaCombo() {
        List<SelectItem> listaV = new LinkedList<SelectItem>();
        for (int i = 0; i < listaVlasnika.size(); i++) {
            SelectItem selectItem = new SelectItem(listaVlasnika.get(i), listaVlasnika.get(i).getIme() + " " + listaVlasnika.get(i).getPrezime());
            listaV.add(selectItem);
        }
        return listaV;
    }
}
