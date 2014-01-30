/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.HashMap;
import java.util.Properties;
import javax.naming.InitialContext;
import logika.automobil.SessionBeanAutomobilLocal;
import logika.dugovanje.SessionBeanDugovanje;
import logika.dugovanje.SessionBeanDugovanjeLocal;
import logika.marka.SessionBeanMarkaAutomobilaLocal;
import logika.model.SessionBeanModelAutomobilaLocal;
import logika.opis.intervencije.SessionBeanOpisIntervencijeLocal;
import logika.uradjene.intervencije.SessionBeanUradjeneIntervencijeLocal;
import logika.zaposleni.SessionBeanZaposleniLocal;
import logka.vlasnik.SessionBeanVlasnikAutomobilaLocal;

/**
 *
 * @author Rus
 */
public class CallEJB {

    private static CallEJB instanca;
    private HashMap<String, Object> mapa = new HashMap<String, Object>();

    private CallEJB() {
        Properties p = new Properties();
        p.setProperty("java.naming.factory.initial",
                "com.sun.enterprise.naming.SerialInitContextFactory");
        p.setProperty("java.naming.factory.url.pkgs",
                "com.sun.enterprise.naming");
        p.setProperty("java.naming.factory.state",
                "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
        try {
            InitialContext ctx = new InitialContext(p);

            SessionBeanZaposleniLocal SBZaposleni = (SessionBeanZaposleniLocal) ctx.lookup("java:global/Automehanicar/EJBAutomehanicar/SessionBeanZaposleni");
            mapa.put("SBZaposleni", SBZaposleni);

            SessionBeanAutomobilLocal SBAutomobil = (SessionBeanAutomobilLocal) ctx.lookup("java:global/Automehanicar/EJBAutomehanicar/SessionBeanAutomobil");
            mapa.put("SBAutomobil", SBAutomobil);

            SessionBeanVlasnikAutomobilaLocal SBVlasnik = (SessionBeanVlasnikAutomobilaLocal) ctx.lookup("java:global/Automehanicar/EJBAutomehanicar/SessionBeanVlasnikAutomobila");
            mapa.put("SBVlasnik", SBVlasnik);

            SessionBeanMarkaAutomobilaLocal SBMarka = (SessionBeanMarkaAutomobilaLocal) ctx.lookup("java:global/Automehanicar/EJBAutomehanicar/SessionBeanMarkaAutomobila");
            mapa.put("SBMarka", SBMarka);

            SessionBeanModelAutomobilaLocal SBModel = (SessionBeanModelAutomobilaLocal) ctx.lookup("java:global/Automehanicar/EJBAutomehanicar/SessionBeanModelAutomobila");
            mapa.put("SBModel", SBModel);

            SessionBeanDugovanjeLocal SBDugovanje = (SessionBeanDugovanjeLocal) ctx.lookup("java:global/Automehanicar/EJBAutomehanicar/SessionBeanDugovanje");
            mapa.put("SBDugovanje", SBDugovanje);

            SessionBeanOpisIntervencijeLocal SBOpis = (SessionBeanOpisIntervencijeLocal) ctx.lookup("java:global/Automehanicar/EJBAutomehanicar/SessionBeanOpisIntervencije");
            mapa.put("SBOpis", SBOpis);

            SessionBeanUradjeneIntervencijeLocal SBUradjene = (SessionBeanUradjeneIntervencijeLocal) ctx.lookup("java:global/Automehanicar/EJBAutomehanicar/SessionBeanUradjeneIntervencije");
            mapa.put("SBUradjene", SBUradjene);

        } catch (Exception e) {
            System.out.println("GRESKA: " + e.getMessage());
        }
    }

    public static CallEJB vratiInstancu() {
        if (instanca == null) {
            instanca = new CallEJB();
        }
        return instanca;
    }

    public SessionBeanAutomobilLocal getAutomobil() {
        return (SessionBeanAutomobilLocal) mapa.get("SBAutomobil");
    }

    public SessionBeanZaposleniLocal getZaposleni() {
        return (SessionBeanZaposleniLocal) mapa.get("SBZaposleni");
    }

    public SessionBeanVlasnikAutomobilaLocal getVlasnikAutomobila() {
        return (SessionBeanVlasnikAutomobilaLocal) mapa.get("SBVlasnik");
    }

    public SessionBeanMarkaAutomobilaLocal getMarkaAutomobila() {
        return (SessionBeanMarkaAutomobilaLocal) mapa.get("SBMarka");
    }

    public SessionBeanModelAutomobilaLocal getModelAutomobila() {
        return (SessionBeanModelAutomobilaLocal) mapa.get("SBModel");
    }

    public SessionBeanDugovanjeLocal getDugovanje() {
        return (SessionBeanDugovanjeLocal) mapa.get("SBDugovanje");
    }

    public SessionBeanOpisIntervencijeLocal getOpisIntervecnije() {
        return (SessionBeanOpisIntervencijeLocal) mapa.get("SBOpis");
    }

    public SessionBeanUradjeneIntervencijeLocal getUradjeneIntervencije() {
        return (SessionBeanUradjeneIntervencijeLocal) mapa.get("SBUradjene");
    }
}
