package domen;

import domen.ModelAutomobila;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-09-09T13:08:30")
@StaticMetamodel(MarkaAutomobila.class)
public class MarkaAutomobila_ { 

    public static volatile SingularAttribute<MarkaAutomobila, Integer> sifraMarke;
    public static volatile SingularAttribute<MarkaAutomobila, String> nazivMarke;
    public static volatile ListAttribute<MarkaAutomobila, ModelAutomobila> modelAutomobilaList;

}