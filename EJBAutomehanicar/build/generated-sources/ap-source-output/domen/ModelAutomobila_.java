package domen;

import domen.Automobil;
import domen.MarkaAutomobila;
import domen.ModelAutomobilaPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-09-09T13:08:30")
@StaticMetamodel(ModelAutomobila.class)
public class ModelAutomobila_ { 

    public static volatile ListAttribute<ModelAutomobila, Automobil> automobilList;
    public static volatile SingularAttribute<ModelAutomobila, ModelAutomobilaPK> modelAutomobilaPK;
    public static volatile SingularAttribute<ModelAutomobila, String> nazivModela;
    public static volatile SingularAttribute<ModelAutomobila, MarkaAutomobila> markaAutomobila;

}