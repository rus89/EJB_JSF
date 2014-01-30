/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "uradjeneintervencije")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UradjeneIntervencije.findAll", query = "SELECT u FROM UradjeneIntervencije u"),
    @NamedQuery(name = "UradjeneIntervencije.findBySifraIntervencije", query = "SELECT u FROM UradjeneIntervencije u WHERE u.uradjeneIntervencijePK.sifraIntervencije = :sifraIntervencije"),
    @NamedQuery(name = "UradjeneIntervencije.findByRb", query = "SELECT u FROM UradjeneIntervencije u WHERE u.uradjeneIntervencijePK.rb = :rb"),
    @NamedQuery(name = "UradjeneIntervencije.findBySifraOpisa", query = "SELECT u FROM UradjeneIntervencije u WHERE u.sifraOpisa = :sifraOpisa")})
public class UradjeneIntervencije implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UradjeneIntervencijePK uradjeneIntervencijePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SifraOpisa")
    private int sifraOpisa;
    @JoinColumn(name = "SifraIntervencije", referencedColumnName = "SifraIntervencije", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Intervencija intervencija;

    public UradjeneIntervencije() {
    }

    public UradjeneIntervencije(UradjeneIntervencijePK uradjeneIntervencijePK) {
        this.uradjeneIntervencijePK = uradjeneIntervencijePK;
    }

    public UradjeneIntervencije(UradjeneIntervencijePK uradjeneIntervencijePK, int sifraOpisa) {
        this.uradjeneIntervencijePK = uradjeneIntervencijePK;
        this.sifraOpisa = sifraOpisa;
    }

    public UradjeneIntervencije(int sifraIntervencije, int rb) {
        this.uradjeneIntervencijePK = new UradjeneIntervencijePK(sifraIntervencije, rb);
    }

    public UradjeneIntervencijePK getUradjeneIntervencijePK() {
        return uradjeneIntervencijePK;
    }

    public void setUradjeneIntervencijePK(UradjeneIntervencijePK uradjeneIntervencijePK) {
        this.uradjeneIntervencijePK = uradjeneIntervencijePK;
    }

    public int getSifraOpisa() {
        return sifraOpisa;
    }

    public void setSifraOpisa(int sifraOpisa) {
        this.sifraOpisa = sifraOpisa;
    }

    public Intervencija getIntervencija() {
        return intervencija;
    }

    public void setIntervencija(Intervencija intervencija) {
        this.intervencija = intervencija;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uradjeneIntervencijePK != null ? uradjeneIntervencijePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UradjeneIntervencije)) {
            return false;
        }
        UradjeneIntervencije other = (UradjeneIntervencije) object;
        if ((this.uradjeneIntervencijePK == null && other.uradjeneIntervencijePK != null) || (this.uradjeneIntervencijePK != null && !this.uradjeneIntervencijePK.equals(other.uradjeneIntervencijePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.UradjeneIntervencije[ uradjeneIntervencijePK=" + uradjeneIntervencijePK + " ]";
    }
    
}
