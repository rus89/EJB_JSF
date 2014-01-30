/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import domen.Automobil;
import domen.MarkaAutomobila;
import domen.ModelAutomobila;
import domen.VlasnikAutomobila;
import domen.Zaposleni;
import ejb.CallEJB;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author Rus
 */
@ManagedBean
@SessionScoped
public class RadSaAutomobilima {

    public static final int UNOS_AUTOMOBILA = 1;
    public static final int IZMENA_AUTOMOBILA = 2;
    private int operacija;
    private Automobil automobil;
    private Automobil selektovaniAutomobil;
    private List<Automobil> listaAutomobila;
    private List<Automobil> filtriraniAutomobili;
    private List<MarkaAutomobila> listaMarkeAutomobila;
    private ModelAutomobila modelAutomobila;
    private List<ModelAutomobila> listaModelaAutomobila;
    private VlasnikAutomobila vlasnikAutomobila;

    public VlasnikAutomobila getVlasnikAutomobila() {
        return vlasnikAutomobila;
    }

    public void setVlasnikAutomobila(VlasnikAutomobila vlasnikAutomobila) {
        this.vlasnikAutomobila = vlasnikAutomobila;
    }

    public Automobil getSelektovaniAutomobil() {
        return selektovaniAutomobil;
    }

    public void setSelektovaniAutomobil(Automobil selektovaniAutomobil) {
        this.selektovaniAutomobil = selektovaniAutomobil;
    }

    public List<ModelAutomobila> getListaModelaAutomobila() {
        return listaModelaAutomobila;
    }

    public void setListaModelaAutomobila(List<ModelAutomobila> listaModelaAutomobila) {
        this.listaModelaAutomobila = listaModelaAutomobila;
    }

    public ModelAutomobila getModelAutomobila() {
        return modelAutomobila;
    }

    public void setModelAutomobila(ModelAutomobila modelAutomobila) {
        this.modelAutomobila = modelAutomobila;
    }

    public List<MarkaAutomobila> getListaMarkeAutomobila() {
        return listaMarkeAutomobila;
    }

    public void setListaMarkeAutomobila(List<MarkaAutomobila> listaMarkeAutomobila) {
        this.listaMarkeAutomobila = listaMarkeAutomobila;
    }

    public Automobil getAutomobil() {
        return automobil;
    }

    public void setAutomobil(Automobil automobil) {
        this.automobil = automobil;
    }

    public List<Automobil> getFiltriraniAutomobili() {
        return filtriraniAutomobili;
    }

    public void setFiltriraniAutomobili(List<Automobil> filtriraniAutomobili) {
        this.filtriraniAutomobili = filtriraniAutomobili;
    }

    public List<Automobil> getListaAutomobila() {
        return listaAutomobila;
    }

    public void setListaAutomobila(List<Automobil> listaAutomobila) {
        this.listaAutomobila = listaAutomobila;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    /**
     * Creates a new instance of RadSaAutomobilima
     */
    public RadSaAutomobilima() {
        inicijalizujListu();
    }

    private void inicijalizujListu() {
        listaAutomobila = CallEJB.vratiInstancu().getAutomobil().vratiSveAutomobile();
        listaMarkeAutomobila = CallEJB.vratiInstancu().getMarkaAutomobila().vratiSveMarke();
        listaModelaAutomobila = CallEJB.vratiInstancu().getModelAutomobila().vratiSveModele();
    }

    public String dodavanjeAutomobila() {
        automobil = new Automobil();
        operacija = UNOS_AUTOMOBILA;
        return "/radSaAutomobilima/dodavanjeAutomobila.xhtml";
    }

    public String sacuvajAutomobil() {
        try {
            if (operacija == UNOS_AUTOMOBILA) {
                CallEJB.vratiInstancu().getAutomobil().sacuvajAuotmobil(automobil);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Automobil je sacuvan!", null));
                inicijalizujListu();
                automobil = new Automobil();
                return "/radSaAutomobilima/dodavanjeAutomobila.xhtml";
            } else {
                CallEJB.vratiInstancu().getAutomobil().izmeniAutomobil(automobil);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Automobil je uspesno izmenjen!", null));
                inicijalizujListu();
                return "/radSaAutomobilima/prikazSvihAutomobila.xhtml";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Automobil nije sacuvan!", null));
            return null;
        }
    }

    public String izmeniAutomobil() {
        try {
            if (automobil == null) {
                throw new Exception("poruka");
            }

            UIComponent komponenta = FacesContext.getCurrentInstance().getViewRoot().findComponent("frmPromenaPodataka:tblPromenaPodatakaAutomobila");
            DataTable dt = (DataTable) komponenta;
            automobil = (Automobil) dt.getRowData(automobil.getSifraAutomobila().toString());
            operacija = IZMENA_AUTOMOBILA;
            return "/radSaAutomobilima/dodavanjeAutomobila.xhtml";
        } catch (Exception e) {
            if (e.getMessage().equalsIgnoreCase("poruka")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate odabrati odredjeni automobil!", null));
            }
            return null;
        }
    }

    public void obrisiAutomobil() {
        try {
            if (automobil == null) {
                throw new Exception("poruka");
            }
            UIComponent komponenta = FacesContext.getCurrentInstance().getViewRoot().findComponent("frmBrisanjeAutomobila:tblBrisanje");
            DataTable dt = (DataTable) komponenta;
            automobil = (Automobil) dt.getRowData(automobil.getSifraAutomobila().toString());
            CallEJB.vratiInstancu().getAutomobil().obrisiAutomobil(automobil);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Automobil je obrisan!", null));
            inicijalizujListu();
        } catch (Exception e) {
            if (e.getMessage().equalsIgnoreCase("poruka")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate odabrati odredjeni automobil!", null));
            }
        }
    }

    public List<SelectItem> vratiListuMarkeAutomobilaZaCombo() {
        List<SelectItem> lista = new LinkedList<SelectItem>();
        for (int i = 0; i < listaMarkeAutomobila.size(); i++) {
            SelectItem si = new SelectItem(listaMarkeAutomobila.get(i), listaMarkeAutomobila.get(i).getNazivMarke());
            lista.add(si);
        }
        return lista;
    }

    public List<SelectItem> vratiListuModelaAutomobilaZaOdredjenuMarkuZaCombo() {
        List<SelectItem> listaM = new LinkedList<SelectItem>();
        for (int i = 0; i < listaModelaAutomobila.size(); i++) {
            SelectItem selectItem = new SelectItem(listaModelaAutomobila.get(i), listaModelaAutomobila.get(i).getNazivModela());
            listaM.add(selectItem);
        }
        return listaM;
    }

//    public void postaviModel(AjaxBehaviorEvent e){
//        modelAutomobila = automobil.getModel();
//        if (modelAutomobila.equals(e)) {
//            
//        }
//    }
    public void validateBrojRegistracije(FacesContext context, UIComponent validate, Object value) {
        if (value == null) {
            return;
        }

        if (value.toString().isEmpty()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate uneti broj registracije.", null);
            throw new ValidatorException(msg);
        }

        if (operacija == UNOS_AUTOMOBILA) {
            for (Automobil a : listaAutomobila) {
                if (a.getBrojRegistracije().equalsIgnoreCase(value.toString())) {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Postoji automobil sa unetom registracijom.", null);
                    throw new ValidatorException(msg);
                }
            }
        }
    }
}
