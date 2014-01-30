package domen;

import domen.Automobil;
import domen.Dugovanje;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-09-09T13:08:30")
@StaticMetamodel(VlasnikAutomobila.class)
public class VlasnikAutomobila_ { 

    public static volatile ListAttribute<VlasnikAutomobila, Automobil> automobilList;
    public static volatile SingularAttribute<VlasnikAutomobila, String> jmbg;
    public static volatile SingularAttribute<VlasnikAutomobila, String> prezime;
    public static volatile SingularAttribute<VlasnikAutomobila, String> ime;
    public static volatile ListAttribute<VlasnikAutomobila, Dugovanje> dugovanjeList;

}