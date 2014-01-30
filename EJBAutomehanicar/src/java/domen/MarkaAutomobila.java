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
@Table(name = "markaautomobila")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MarkaAutomobila.findAll", query = "SELECT m FROM MarkaAutomobila m"),
    @NamedQuery(name = "MarkaAutomobila.findBySifraMarke", query = "SELECT m FROM MarkaAutomobila m WHERE m.sifraMarke = :sifraMarke"),
    @NamedQuery(name = "MarkaAutomobila.findByNazivMarke", query = "SELECT m FROM MarkaAutomobila m WHERE m.nazivMarke = :nazivMarke")})
public class MarkaAutomobila implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SifraMarke")
    private Integer sifraMarke;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NazivMarke")
    private String nazivMarke;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sifraMarke")
    private List<ModelAutomobila> modelAutomobilaList;

    public MarkaAutomobila() {
    }

    public MarkaAutomobila(Integer sifraMarke) {
        this.sifraMarke = sifraMarke;
    }

    public MarkaAutomobila(Integer sifraMarke, String nazivMarke) {
        this.sifraMarke = sifraMarke;
        this.nazivMarke = nazivMarke;
    }

    public Integer getSifraMarke() {
        return sifraMarke;
    }

    public void setSifraMarke(Integer sifraMarke) {
        this.sifraMarke = sifraMarke;
    }

    public String getNazivMarke() {
        return nazivMarke;
    }

    public void setNazivMarke(String nazivMarke) {
        this.nazivMarke = nazivMarke;
    }

    @XmlTransient
    public List<ModelAutomobila> getModelAutomobilaList() {
        return modelAutomobilaList;
    }

    public void setModelAutomobilaList(List<ModelAutomobila> modelAutomobilaList) {
        this.modelAutomobilaList = modelAutomobilaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sifraMarke != null ? sifraMarke.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MarkaAutomobila)) {
            return false;
        }
        MarkaAutomobila other = (MarkaAutomobila) object;
        if ((this.sifraMarke == null && other.sifraMarke != null) || (this.sifraMarke != null && !this.sifraMarke.equals(other.sifraMarke))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazivMarke;
    }
}
