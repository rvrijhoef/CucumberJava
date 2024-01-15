package StepDefs;

import Utils.TestContext;
import exceptions.BoekException;
import exceptions.KlantException;
import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.En;

import static org.junit.Assert.assertEquals;

public class UitleenSteps extends TestContext {
    @Als("ik het boek met titel {string} uitleen")
    public void ikHetBoekMetTitelUitleen(final String titel) throws KlantException, KlantException, KlantException, KlantException, BoekException {
        int boekId = library.getboekId(titel);
        try {
            geleendBoek = library.leenBoek(boekId, customerId);
        }catch(Exception e){
            setVorigeException(e);
        }
    }

    @Dan("zijn er nog maar {int} exemplaren in de bibliotheek")
    public void zijnErNogMaarExemplarenInDeBibliotheek(int verwachteAantalExemplaren) {
        assertEquals(verwachteAantalExemplaren, geleendBoek.getAantalBeschikbaar());
    }

    @En("heeft de klant er {int} in bezit")
    public void heeftDeKlantErInBezit(int verwachteAantalInBezit){
        assertEquals("Het verwachte aantal boeken komt niet overeen met het daadwerkelijke aantal",
                verwachteAantalInBezit,library.getUitgeleendeBoeken(customerId).size());
    }

    @En("de klant het boek met titel {string} inleverd")
    public void klantLeverdBoekIn(final String titel){
        library.returnBook(customerId,library.getboekId(titel));
    }

    @En("de klant het boek met titel {string} verlengd")
    public void enDeKlantBoekVerlengd(final String titel){
        try {
            library.verlengBoek(customerId, library.getboekId(titel));
        } catch (Exception e) {
            setVorigeException(e);
        }
    }

}
