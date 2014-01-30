/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mb;

import domen.Zaposleni;
import ejb.CallEJB;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
public class RadSaZaposlenima {

    public static final int UNOS_ZAPOSLENOG = 1;
    public static final int IZMENA_ZAPOSLENOG = 2;
    private int operacija;
    private Zaposleni trenutniZaposleni;
    private List<Zaposleni> listaZaposlenih;
    private Zaposleni selektovaniZaposleni;
    private List<Zaposleni> filtriraniZaposleni;

    public List<Zaposleni> getFiltriraniZaposleni() {
        return filtriraniZaposleni;
    }

    public void setFiltriraniZaposleni(List<Zaposleni> filtriraniZaposleni) {
        this.filtriraniZaposleni = filtriraniZaposleni;
    }

    public Zaposleni getSelektovaniZaposleni() {
        return selektovaniZaposleni;
    }

    public void setSelektovaniZaposleni(Zaposleni selektovaniZaposleni) {
        this.selektovaniZaposleni = selektovaniZaposleni;
    }

    public List<Zaposleni> getListaZaposlenih() {
        return listaZaposlenih;
    }

    public void setListaZaposlenih(List<Zaposleni> listaZaposlenih) {
        this.listaZaposlenih = listaZaposlenih;
    }

    public Zaposleni getTrenutniZaposleni() {
        return trenutniZaposleni;
    }

    public void setTrenutniZaposleni(Zaposleni trenutniZaposleni) {
        this.trenutniZaposleni = trenutniZaposleni;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    /**
     * Creates a new instance of RadSaZaposlenima
     */
    public RadSaZaposlenima() {
        inicijalizujListu();
    }

    private void inicijalizujListu() {
        listaZaposlenih = CallEJB.vratiInstancu().getZaposleni().vratiSveZaposlene();
    }

    public String dodavanjeZaposlenog() {
        trenutniZaposleni = new Zaposleni();
        operacija = UNOS_ZAPOSLENOG;
        return "/radSaZaposlenima/dodavanjeZaposlenih.xhtml";
    }

    public String sacuvajZaposlenog() {
        try {
            if (operacija == UNOS_ZAPOSLENOG) {
                CallEJB.vratiInstancu().getZaposleni().sacuvajZaposlenog(trenutniZaposleni);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zaposleni je uspesno sacuvan!", null));
                inicijalizujListu();
                trenutniZaposleni = new Zaposleni();
                return "/radSaZaposlenima/dodavanjeZaposlenih.xhtml";
            } else {
                CallEJB.vratiInstancu().getZaposleni().izmeniZaposlenog(trenutniZaposleni);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zaposleni je uspesno izmenjen!", null));
                inicijalizujListu();
                return "/radSaZaposlenima/prikazSvihZaposlenih.xhtml";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zaposleni nije sacuvan!", null));
            return null;
        }
    }

    public String izmeniZaposlenog() {
        try {
            if (trenutniZaposleni == null) {
                throw new Exception("poruka");
            }
            UIComponent komponenta = FacesContext.getCurrentInstance().getViewRoot().findComponent("frmPromenaPodataka:tblPromenaPodatakaZaposlenih");
            DataTable dt = (DataTable) komponenta;
            trenutniZaposleni = (Zaposleni) dt.getRowData(trenutniZaposleni.getSifraZaposlenog().toString());
            operacija = IZMENA_ZAPOSLENOG;
            return "/radSaZaposlenima/dodavanjeZaposlenih.xhtml";
        } catch (Exception e) {
            if (e.getMessage().equalsIgnoreCase("poruka")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate odabrati odredjenog zaposlenog!", null));
            }
            return null;
        }
    }

    public void obrisiZaposlenog() {
        try {
            if (trenutniZaposleni == null) {
                throw new Exception("poruka");
            }

            UIComponent komponenta = FacesContext.getCurrentInstance().getViewRoot().findComponent("frmBrisanjeZaposlenih:tblBrisanje");
            DataTable dt = (DataTable) komponenta;
            trenutniZaposleni = (Zaposleni) dt.getRowData(trenutniZaposleni.getSifraZaposlenog().toString());

            CallEJB.vratiInstancu().getZaposleni().obrisiZaposlenog(trenutniZaposleni);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zaposleni je uspesno obrisan!", null));
            inicijalizujListu();
        } catch (Exception e) {
            if (e.getMessage().equalsIgnoreCase("poruka")) {
                System.out.println(e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate odabrati odredjenog zaposlenog!", null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zaposleni nije obrisan!", null));
            }
        }
    }

    public void validateKorisnickoIme(FacesContext context, UIComponent validate, Object value) {
        if (value == null) {
            return;
        }

        if (value.toString().isEmpty()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate uneti korisnicko ime.", null);
            throw new ValidatorException(msg);
        }

//        List<Zaposleni> lista = CallEJB.vratiInstancu().getZaposleni().vratiSveZaposlene();
//        if (operacija == UNOS_ZAPOSLENOG) {
//            for (Zaposleni z : lista) {
//                if (z.getUsername().equals(value.toString())) {
//                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Korisnicko ime je zauzeto.", null);
//                    throw new ValidatorException(msg);
//                }
//            }
//        } else {
//            for (int i = 0; i < lista.size(); i++) {
//                if (lista.get(i).getUsername().equals(trenutniZaposleni.getUsername())) {
//                    lista.remove(i);
//                }
//            }
//            for (Zaposleni z : lista) {
//                if (z.getUsername().equals(value.toString())) {
//                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Korisnicko ime je zauzeto.", null);
//                    throw new ValidatorException(msg);
//                }
//            }
//        }
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

        List<Zaposleni> lista = CallEJB.vratiInstancu().getZaposleni().vratiSveZaposlene();
        if (operacija == UNOS_ZAPOSLENOG) {
            for (Zaposleni z : lista) {
                if (z.getJmbg().equals(value.toString())) {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Postoji zaposleni sa unetim JMBG-om.", null);
                    throw new ValidatorException(msg);
                }
            }
        } else {
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getJmbg().equals(trenutniZaposleni.getJmbg())) {
                    lista.remove(i);
                }
            }
            for (Zaposleni z : lista) {
                if (z.getJmbg().equals(value.toString())) {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Postoji zaposleni sa unetim JMBG-om.", null);
                    throw new ValidatorException(msg);
                }
            }
        }
    }

    public void validateEmail(FacesContext context, UIComponent validate, Object value) {
        if (value == null) {
            return;
        }

        if (value.toString().isEmpty()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate uneti email.", null);
            throw new ValidatorException(msg);
        }

        String email = value.toString();

        if (!daLiJeValidanEmail(email)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email nije validan.", null);
            throw new ValidatorException(msg);
        }

        List<Zaposleni> lista = CallEJB.vratiInstancu().getZaposleni().vratiSveZaposlene();
        if (operacija == UNOS_ZAPOSLENOG) {
            for (Zaposleni z : lista) {
                if (z.getEmail().equals(value.toString())) {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email je zauzet.", null);
                    throw new ValidatorException(msg);
                }
            }
        } else {
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getEmail().equals(trenutniZaposleni.getEmail())) {
                    lista.remove(i);
                }
            }
            for (Zaposleni z : lista) {
                if (z.getEmail().equals(value.toString())) {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email je zauzet.", null);
                    throw new ValidatorException(msg);
                }
            }
        }
    }

    private boolean daLiJeValidanEmail(String email) {
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email);
        boolean b = m.matches();
        if (!b) {
            return false;
        }
        return true;
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

    public void validateSifra(FacesContext context, UIComponent validate, Object value) {
        if (value == null) {
            return;
        }

        if (value.toString().isEmpty()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Morate uneti sifru.", null);
            throw new ValidatorException(msg);
        }

        List<Zaposleni> lista = CallEJB.vratiInstancu().getZaposleni().vratiSveZaposlene();
        if (operacija == UNOS_ZAPOSLENOG) {
            for (Zaposleni z : lista) {
                if (z.getPassword().equals(value.toString())) {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sifra je zauzeta.", null);
                    throw new ValidatorException(msg);
                }
            }
        } else {
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getPassword().equals(trenutniZaposleni.getPassword())) {
                    lista.remove(i);
                }
            }
            for (Zaposleni z : lista) {
                if (z.getPassword().equals(value.toString()) && operacija == UNOS_ZAPOSLENOG) {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sifra je zauzeta.", null);
                    throw new ValidatorException(msg);
                }
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
}
