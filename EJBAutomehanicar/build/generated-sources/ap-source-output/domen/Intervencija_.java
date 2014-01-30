package domen;

import domen.Automobil;
import domen.UradjeneIntervencije;
import domen.Zaposleni;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-09-09T13:08:30")
@StaticMetamodel(Intervencija.class)
public class Intervencija_ { 

    public static volatile SingularAttribute<Intervencija, Zaposleni> sifraZaposlenog;
    public static volatile SingularAttribute<Intervencija, Integer> sifraIntervencije;
    public static volatile SingularAttribute<Intervencija, Date> datumIntervencije;
    public static volatile SingularAttribute<Intervencija, Automobil> sifraAutomobila;
    public static volatile ListAttribute<Intervencija, UradjeneIntervencije> uradjeneIntervencijeList;

}