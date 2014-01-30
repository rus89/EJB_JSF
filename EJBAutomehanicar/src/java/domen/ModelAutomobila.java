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
@Table(name = "modelautomobila")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModelAutomobila.findAll", query = "SELECT m FROM ModelAutomobila m"),
    @NamedQuery(name = "ModelAutomobila.findBySifraModela", query = "SELECT m FROM ModelAutomobila m WHERE m.sifraModela = :sifraModela"),
    @NamedQuery(name = "ModelAutomobila.findByNazivModela", query = "SELECT m FROM ModelAutomobila m WHERE m.nazivModela = :nazivModela")})
public class ModelAutomobila implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SifraModela")
    private Integer sifraModela;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NazivModela")
    private String nazivModela;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "model")
    private List<Automobil> automobilList;
    @JoinColumn(name = "SifraMarke", referencedColumnName = "SifraMarke")
    @ManyToOne(optional = false)
    private MarkaAutomobila sifraMarke;

    public ModelAutomobila() {
    }

    public ModelAutomobila(Integer sifraModela) {
        this.sifraModela = sifraModela;
    }

    public ModelAutomobila(Integer sifraModela, String nazivModela) {
        this.sifraModela = sifraModela;
        this.nazivModela = nazivModela;
    }

    public Integer getSifraModela() {
        return sifraModela;
    }

    public void setSifraModela(Integer sifraModela) {
        this.sifraModela = sifraModela;
    }

    public String getNazivModela() {
        return nazivModela;
    }

    public void setNazivModela(String nazivModela) {
        this.nazivModela = nazivModela;
    }

    @XmlTransient
    public List<Automobil> getAutomobilList() {
        return automobilList;
    }

    public void setAutomobilList(List<Automobil> automobilList) {
        this.automobilList = automobilList;
    }

    public MarkaAutomobila getSifraMarke() {
        return sifraMarke;
    }

    public void setSifraMarke(MarkaAutomobila sifraMarke) {
        this.sifraMarke = sifraMarke;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sifraModela != null ? sifraModela.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModelAutomobila)) {
            return false;
        }
        ModelAutomobila other = (ModelAutomobila) object;
        if ((this.sifraModela == null && other.sifraModela != null) || (this.sifraModela != null && !this.sifraModela.equals(other.sifraModela))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazivModela;
    }
}
