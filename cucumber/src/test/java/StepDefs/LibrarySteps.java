package StepDefs;

import Utils.TestContext;
import io.cucumber.java.nl.En;
import io.cucumber.java.nl.Gegeven;
import library.Library;

public class LibrarySteps extends TestContext {
    TestContext testContext;
    public LibrarySteps(TestContext context) {
        this.testContext = context;
    }

    @Gegeven("een bibliotheek zonder klanten")
    public void eenBibliotheekZonderKlanten() {
        library = new Library();
        verwijderVorigeException();
    }

    @En("er {int} dagen zijn verstreken")
    public void erDagenZijnVerstreken(final int aantalDagen){
        for(int i = 0; i < aantalDagen;i++){
            library.nachtelijkProces();
        }
    }
}
