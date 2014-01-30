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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rus
 */
@Entity
@Table(name = "intervencija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Intervencija.findAll", query = "SELECT i FROM Intervencija i"),
    @NamedQuery(name = "Intervencija.findBySifraIntervencije", query = "SELECT i FROM Intervencija i WHERE i.sifraIntervencije = :sifraIntervencije"),
    @NamedQuery(name = "Intervencija.findByDatumIntervencije", query = "SELECT i FROM Intervencija i WHERE i.datumIntervencije = :datumIntervencije")})
public class Intervencija implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SifraIntervencije")
    private Integer sifraIntervencije;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DatumIntervencije")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumIntervencije;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "intervencija")
    private List<UradjeneIntervencije> uradjeneIntervencijeList;
    @JoinColumn(name = "SifraAutomobila", referencedColumnName = "SifraAutomobila")
    @ManyToOne(optional = false)
    private Automobil sifraAutomobila;
    @JoinColumn(name = "SifraZaposlenog", referencedColumnName = "SifraZaposlenog")
    @ManyToOne(optional = false)
    private Zaposleni sifraZaposlenog;

    public Intervencija() {
    }

    public Intervencija(Integer sifraIntervencije) {
        this.sifraIntervencije = sifraIntervencije;
    }

    public Intervencija(Integer sifraIntervencije, Date datumIntervencije) {
        this.sifraIntervencije = sifraIntervencije;
        this.datumIntervencije = datumIntervencije;
    }

    public Integer getSifraIntervencije() {
        return sifraIntervencije;
    }

    public void setSifraIntervencije(Integer sifraIntervencije) {
        this.sifraIntervencije = sifraIntervencije;
    }

    public Date getDatumIntervencije() {
        return datumIntervencije;
    }

    public void setDatumIntervencije(Date datumIntervencije) {
        this.datumIntervencije = datumIntervencije;
    }

    @XmlTransient
    public List<UradjeneIntervencije> getUradjeneIntervencijeList() {
        return uradjeneIntervencijeList;
    }

    public void setUradjeneIntervencijeList(List<UradjeneIntervencije> uradjeneIntervencijeList) {
        this.uradjeneIntervencijeList = uradjeneIntervencijeList;
    }

    public Automobil getSifraAutomobila() {
        return sifraAutomobila;
    }

    public void setSifraAutomobila(Automobil sifraAutomobila) {
        this.sifraAutomobila = sifraAutomobila;
    }

    public Zaposleni getSifraZaposlenog() {
        return sifraZaposlenog;
    }

    public void setSifraZaposlenog(Zaposleni sifraZaposlenog) {
        this.sifraZaposlenog = sifraZaposlenog;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sifraIntervencije != null ? sifraIntervencije.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Intervencija)) {
            return false;
        }
        Intervencija other = (Intervencija) object;
        if ((this.sifraIntervencije == null && other.sifraIntervencije != null) || (this.sifraIntervencije != null && !this.sifraIntervencije.equals(other.sifraIntervencije))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Intervencija[ sifraIntervencije=" + sifraIntervencije + " ]";
    }
    
}
