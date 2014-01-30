package domen;

import domen.Intervencija;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-09-09T13:08:30")
@StaticMetamodel(Zaposleni.class)
public class Zaposleni_ { 

    public static volatile SingularAttribute<Zaposleni, String> adresa;
    public static volatile SingularAttribute<Zaposleni, String> username;
    public static volatile SingularAttribute<Zaposleni, Integer> sifraZaposlenog;
    public static volatile SingularAttribute<Zaposleni, String> prezime;
    public static volatile SingularAttribute<Zaposleni, String> jmbg;
    public static volatile SingularAttribute<Zaposleni, String> email;
    public static volatile SingularAttribute<Zaposleni, String> pol;
    public static volatile SingularAttribute<Zaposleni, Date> datumRodjenja;
    public static volatile ListAttribute<Zaposleni, Intervencija> intervencijaList;
    public static volatile SingularAttribute<Zaposleni, String> ime;
    public static volatile SingularAttribute<Zaposleni, String> telefon;
    public static volatile SingularAttribute<Zaposleni, String> password;

}