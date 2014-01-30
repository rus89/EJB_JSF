/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Rus
 */
@Embeddable
public class UradjeneIntervencijePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "SifraIntervencije")
    private int sifraIntervencije;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RB")
    private int rb;

    public UradjeneIntervencijePK() {
    }

    public UradjeneIntervencijePK(int sifraIntervencije, int rb) {
        this.sifraIntervencije = sifraIntervencije;
        this.rb = rb;
    }

    public int getSifraIntervencije() {
        return sifraIntervencije;
    }

    public void setSifraIntervencije(int sifraIntervencije) {
        this.sifraIntervencije = sifraIntervencije;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) sifraIntervencije;
        hash += (int) rb;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UradjeneIntervencijePK)) {
            return false;
        }
        UradjeneIntervencijePK other = (UradjeneIntervencijePK) object;
        if (this.sifraIntervencije != other.sifraIntervencije) {
            return false;
        }
        if (this.rb != other.rb) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.UradjeneIntervencijePK[ sifraIntervencije=" + sifraIntervencije + ", rb=" + rb + " ]";
    }
    
}
