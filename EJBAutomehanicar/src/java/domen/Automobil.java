/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rus
 */
@Entity
@Table(name = "automobil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Automobil.findAll", query = "SELECT a FROM Automobil a"),
    @NamedQuery(name = "Automobil.findBySifraAutomobila", query = "SELECT a FROM Automobil a WHERE a.sifraAutomobila = :sifraAutomobila"),
    @NamedQuery(name = "Automobil.findByBrojRegistracije", query = "SELECT a FROM Automobil a WHERE a.brojRegistracije = :brojRegistracije")})
public class Automobil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SifraAutomobila")
    private Integer sifraAutomobila;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "BrojRegistracije")
    private String brojRegistracije;
    @JoinColumn(name = "SifraVlasnika", referencedColumnName = "SifraVlasnika")
    @ManyToOne(optional = false)
    private VlasnikAutomobila sifraVlasnika;
    @JoinColumn(name = "Model", referencedColumnName = "SifraModela")
    @ManyToOne(optional = false)
    private ModelAutomobila model;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sifraAutomobila")
    private List<Intervencija> intervencijaList;

    public Automobil() {
    }

    public Automobil(Integer sifraAutomobila) {
        this.sifraAutomobila = sifraAutomobila;
    }

    public Automobil(Integer sifraAutomobila, String brojRegistracije) {
        this.sifraAutomobila = sifraAutomobila;
        this.brojRegistracije = brojRegistracije;
    }

    public Integer getSifraAutomobila() {
        return sifraAutomobila;
    }

    public void setSifraAutomobila(Integer sifraAutomobila) {
        this.sifraAutomobila = sifraAutomobila;
    }

    public String getBrojRegistracije() {
        return brojRegistracije;
    }

    public void setBrojRegistracije(String brojRegistracije) {
        this.brojRegistracije = brojRegistracije;
    }

    public VlasnikAutomobila getSifraVlasnika() {
        return sifraVlasnika;
    }

    public void setSifraVlasnika(VlasnikAutomobila sifraVlasnika) {
        this.sifraVlasnika = sifraVlasnika;
    }

    public ModelAutomobila getModel() {
        return model;
    }

    public void setModel(ModelAutomobila model) {
        this.model = model;
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
        hash += (sifraAutomobila != null ? sifraAutomobila.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Automobil)) {
            return false;
        }
        Automobil other = (Automobil) object;
        if ((this.sifraAutomobila == null && other.sifraAutomobila != null) || (this.sifraAutomobila != null && !this.sifraAutomobila.equals(other.sifraAutomobila))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return brojRegistracije + " " + model.getSifraMarke().getNazivMarke() + " " + model.getNazivModela();
    }
}
