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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rus
 */
@Entity
@Table(name = "dugovanje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dugovanje.findAll", query = "SELECT d FROM Dugovanje d"),
    @NamedQuery(name = "Dugovanje.findBySifraDugovanja", query = "SELECT d FROM Dugovanje d WHERE d.sifraDugovanja = :sifraDugovanja"),
    @NamedQuery(name = "Dugovanje.findByVrednostDugovanja", query = "SELECT d FROM Dugovanje d WHERE d.vrednostDugovanja = :vrednostDugovanja")})
public class Dugovanje implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SifraDugovanja")
    private Integer sifraDugovanja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VrednostDugovanja")
    private double vrednostDugovanja;
    @JoinColumn(name = "SifraVlasnika", referencedColumnName = "SifraVlasnika")
    @ManyToOne(optional = false)
    private VlasnikAutomobila sifraVlasnika;

    public Dugovanje() {
    }

    public Dugovanje(Integer sifraDugovanja) {
        this.sifraDugovanja = sifraDugovanja;
    }

    public Dugovanje(Integer sifraDugovanja, double vrednostDugovanja) {
        this.sifraDugovanja = sifraDugovanja;
        this.vrednostDugovanja = vrednostDugovanja;
    }

    public Integer getSifraDugovanja() {
        return sifraDugovanja;
    }

    public void setSifraDugovanja(Integer sifraDugovanja) {
        this.sifraDugovanja = sifraDugovanja;
    }

    public double getVrednostDugovanja() {
        return vrednostDugovanja;
    }

    public void setVrednostDugovanja(double vrednostDugovanja) {
        this.vrednostDugovanja = vrednostDugovanja;
    }

    public VlasnikAutomobila getSifraVlasnika() {
        return sifraVlasnika;
    }

    public void setSifraVlasnika(VlasnikAutomobila sifraVlasnika) {
        this.sifraVlasnika = sifraVlasnika;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sifraDugovanja != null ? sifraDugovanja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dugovanje)) {
            return false;
        }
        Dugovanje other = (Dugovanje) object;
        if ((this.sifraDugovanja == null && other.sifraDugovanja != null) || (this.sifraDugovanja != null && !this.sifraDugovanja.equals(other.sifraDugovanja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Dugovanje[ sifraDugovanja=" + sifraDugovanja + " ]";
    }
    
}
