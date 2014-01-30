/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rus
 */
@Entity
@Table(name = "opisintervencije")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpisIntervencije.findAll", query = "SELECT o FROM OpisIntervencije o"),
    @NamedQuery(name = "OpisIntervencije.findBySifraOpisa", query = "SELECT o FROM OpisIntervencije o WHERE o.sifraOpisa = :sifraOpisa"),
    @NamedQuery(name = "OpisIntervencije.findByOpis", query = "SELECT o FROM OpisIntervencije o WHERE o.opis = :opis")})
public class OpisIntervencije implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SifraOpisa")
    private Integer sifraOpisa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Opis")
    private String opis;

    public OpisIntervencije() {
    }

    public OpisIntervencije(Integer sifraOpisa) {
        this.sifraOpisa = sifraOpisa;
    }

    public OpisIntervencije(Integer sifraOpisa, String opis) {
        this.sifraOpisa = sifraOpisa;
        this.opis = opis;
    }

    public Integer getSifraOpisa() {
        return sifraOpisa;
    }

    public void setSifraOpisa(Integer sifraOpisa) {
        this.sifraOpisa = sifraOpisa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sifraOpisa != null ? sifraOpisa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpisIntervencije)) {
            return false;
        }
        OpisIntervencije other = (OpisIntervencije) object;
        if ((this.sifraOpisa == null && other.sifraOpisa != null) || (this.sifraOpisa != null && !this.sifraOpisa.equals(other.sifraOpisa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.OpisIntervencije[ sifraOpisa=" + sifraOpisa + " ]";
    }
    
}
