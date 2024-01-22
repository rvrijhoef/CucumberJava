package StepDefs;

import Utils.TestContext;
import exceptions.BoekException;
import exceptions.KlantException;
import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.En;
import objects.Klant;
import util.Utils;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class KlantSteps extends TestContext {

    @Als("ik een standaard klant toevoeg")
    public void ikEenStandaardKlantToevoeg() throws Exception {
        Klant cust = new Klant("Jan Klaassen",
                Utils.dateVanString("1980-05-14"),
                "Test 33a, 1234BB Ergens");
        library.addCustomer(cust);
        customerId = cust.getId();
    }

    @Als("ik een klant toevoeg met naam {string} en met geboorteDatum {string} en adres {string}")
    public void ikEenSpecifiekeKlantToevoeg(final String naam, final String geboorteDatum, final String adres) throws Exception {
        try {
            Klant cust = new Klant(naam, Utils.dateVanString(geboorteDatum), adres);
            library.addCustomer(cust);
            customerId = cust.getId();
        } catch (Exception e) {
            setVorigeException(e);
        }
    }

    @Dan("is het totaal aantal klanten {int}")
    public void isHetTotaalAantalKlanten(int verwachtAantal) {
        assertEquals(verwachtAantal, library.getAantalKlanten());
    }

    @En("als ik eenzelfde klant toevoeg")
    public void alsIkEenzelfdeKlantToevoeg() {
        try {
            Klant cust = library.getKlanten().stream().findFirst().get();
            library.addCustomer(cust);
        } catch (Exception e) {
            setVorigeException(e);
            return;
        }
        fail("Er is geen exception opgetreden");
    }

    @Dan("is de volgende exception gegeven: {word} met de inhoud {string}")
    public void isErEenExcptionGegeven(final String verwachteException, final String bericht) {
        if (!verwachteException.equalsIgnoreCase("geen")) {
            if (getVorigeException() == null) {
                fail("Er is geen exceptie opgetreden");
            }
            switch (verwachteException) {
                case "KlantException":
                    assertEquals(KlantException.class, getVorigeException().getClass());
                    break;
                case "BoekException":
                    assertEquals(BoekException.class, getVorigeException().getClass());
                    break;
                default:
                    fail("Geen geldige exceptiesoort opgegeven.");
                    break;
            }
            assertEquals(bericht, getVorigeException().getMessage());
        } else{
            assertNull(getVorigeException());
        }
    }

    @En("heeft de klant {bigdecimal} boete openstaan")
    public void heeftDeKlantBoeteOpenstaan(final BigDecimal boete) {
        BigDecimal actueleBoete = library.getKlant(customerId).getBoete();
        try{
            assertEquals(actueleBoete.compareTo(boete),0);
        } catch (AssertionError e){
            System.out.println("Verwachte waarde is: " + boete + ", werkelijke waarde is: " + actueleBoete );
            throw e;
        }
    }

    @En("zet de boete voor de klant op {bigdecimal}")
    public void zetDeBoeteOp(final BigDecimal boete) {
        library.getKlant(customerId).setBoete(boete);
    }

    @En("de klant {bigdecimal} van zijn boete aflost")
    public void losBoeteAf(final BigDecimal aflossing) throws KlantException {
        if(aflossing.compareTo(new BigDecimal(0))!=0){
            library.betaalBoete(customerId,aflossing);
        }
    }

    @En("is het klant account gezet op status: {string}")
    public void isHetKlantAccountGezetOpStatus(String status) {
        switch (status) {
            case "actief":
                library.getKlant(customerId).setActief(true);
                break;
            case "niet actief":
                library.getKlant(customerId).setActief(false);
                break;
            default:
                fail("Geen geldige account status opgegeven");
                break;
        }
    }

    @En("heeft het klant account de status: {string}")
    public void heeftHetKlantAccountStatus(String status) {
        switch (status) {
            case "actief":
                assertTrue(library.getKlant(customerId).isActief());
                break;
            case "niet actief":
                assertFalse(library.getKlant(customerId).isActief());
                break;
            default:
                fail("Geen geldige account status opgegeven");
                break;
        }
    }
}
