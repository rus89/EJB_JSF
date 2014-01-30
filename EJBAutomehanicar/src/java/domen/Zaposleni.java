/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rus
 */
@Entity
@Table(name = "zaposleni")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zaposleni.findAll", query = "SELECT z FROM Zaposleni z"),
    @NamedQuery(name = "Zaposleni.findBySifraZaposlenog", query = "SELECT z FROM Zaposleni z WHERE z.sifraZaposlenog = :sifraZaposlenog"),
    @NamedQuery(name = "Zaposleni.findByJmbg", query = "SELECT z FROM Zaposleni z WHERE z.jmbg = :jmbg"),
    @NamedQuery(name = "Zaposleni.findByIme", query = "SELECT z FROM Zaposleni z WHERE z.ime = :ime"),
    @NamedQuery(name = "Zaposleni.findByPrezime", query = "SELECT z FROM Zaposleni z WHERE z.prezime = :prezime"),
    @NamedQuery(name = "Zaposleni.findByUsername", query = "SELECT z FROM Zaposleni z WHERE z.username = :username"),
    @NamedQuery(name = "Zaposleni.findByPassword", query = "SELECT z FROM Zaposleni z WHERE z.password = :password"),
    @NamedQuery(name = "Zaposleni.findByDatumRodjenja", query = "SELECT z FROM Zaposleni z WHERE z.datumRodjenja = :datumRodjenja"),
    @NamedQuery(name = "Zaposleni.findByAdresa", query = "SELECT z FROM Zaposleni z WHERE z.adresa = :adresa"),
    @NamedQuery(name = "Zaposleni.findByTelefon", query = "SELECT z FROM Zaposleni z WHERE z.telefon = :telefon"),
    @NamedQuery(name = "Zaposleni.findByEmail", query = "SELECT z FROM Zaposleni z WHERE z.email = :email"),
    @NamedQuery(name = "Zaposleni.findByPol", query = "SELECT z FROM Zaposleni z WHERE z.pol = :pol")})
public class Zaposleni implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SifraZaposlenog")
    private Integer sifraZaposlenog;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "JMBG")
    private String jmbg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "Ime")
    private String ime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "Prezime")
    private String prezime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DatumRodjenja")
    @Temporal(TemporalType.DATE)
    private Date datumRodjenja;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Adresa")
    private String adresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Telefon")
    private String telefon;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "Pol")
    private String pol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sifraZaposlenog")
    private List<Intervencija> intervencijaList;

    public Zaposleni() {
    }

    public Zaposleni(Integer sifraZaposlenog) {
        this.sifraZaposlenog = sifraZaposlenog;
    }

    public Zaposleni(Integer sifraZaposlenog, String jmbg, String ime, String prezime, String username, String password, Date datumRodjenja, String adresa, String telefon, String email, String pol) {
        this.sifraZaposlenog = sifraZaposlenog;
        this.jmbg = jmbg;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.datumRodjenja = datumRodjenja;
        this.adresa = adresa;
        this.telefon = telefon;
        this.email = email;
        this.pol = pol;
    }

    public Integer getSifraZaposlenog() {
        return sifraZaposlenog;
    }

    public void setSifraZaposlenog(Integer sifraZaposlenog) {
        this.sifraZaposlenog = sifraZaposlenog;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    @XmlTransient
    public List<Intervencija> getIntervencijaList() {
        return intervencijaList;
    }

    public void setIntervencijaList(List<Intervencija> intervencijaList) {
        this.intervencijaList = intervencijaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sifraZaposlenog != null ? sifraZaposlenog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zaposleni)) {
            return false;
        }
        Zaposleni other = (Zaposleni) object;
        if ((this.sifraZaposlenog == null && other.sifraZaposlenog != null) || (this.sifraZaposlenog != null && !this.sifraZaposlenog.equals(other.sifraZaposlenog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
}
