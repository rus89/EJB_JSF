package domen;

import domen.Intervencija;
import domen.ModelAutomobila;
import domen.VlasnikAutomobila;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.2.0.v20110202-r8913", date="2012-09-09T13:08:30")
@StaticMetamodel(Automobil.class)
public class Automobil_ { 

    public static volatile SingularAttribute<Automobil, String> brojRegistracije;
    public static volatile SingularAttribute<Automobil, ModelAutomobila> model;
    public static volatile ListAttribute<Automobil, Intervencija> intervencijaList;
    public static volatile SingularAttribute<Automobil, Integer> sifraAutomobila;
    public static volatile SingularAttribute<Automobil, VlasnikAutomobila> jMBGVlasnika;

}