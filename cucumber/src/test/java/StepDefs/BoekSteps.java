package StepDefs;

import Utils.TestContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import objects.Boek;
import util.Utils;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BoekSteps extends TestContext {
    TestContext testContext;
    public BoekSteps(TestContext context) {
       this.testContext = context;
    }

    @Als("het boek met titel {string} en auteur {string} en genre {string} en prijs {bigdecimal} en datum {string} en omschrijving {string} en isbn {string} en aantal {int}")
    public void hetBoekMetTitelEnAuteurEnGenreEnPrijsEnDatumEnOmschrijvingEnIsbnEnAantal(
            String titel, String auteur, String genre, BigDecimal prijs, String datum, String omschrijving, String isbn, int aantal) throws Exception {
        try {
            Boek boek = new Boek(titel, auteur, genre, prijs, Utils.dateVanString(datum), omschrijving, isbn, aantal);
            library.addBook(boek);
        } catch (Exception e){
            setVorigeException(e);
        }
    }


    @Als("de volgende boeken worden toegevoegd aan de bibliotheek")
    public void deVolgendeBoekenWordenToegevoegd(final DataTable boekenData) throws Exception {
        List<List<String>> boeken = boekenData.asLists(String.class);
        for (List<String> boek : boeken) {
            if (!boek.get(0).equals("titel")) {
                try {
                Boek book = new Boek(boek.get(0), boek.get(1), boek.get(2), new BigDecimal(boek.get(3)),
                        Utils.dateVanString(boek.get(4)), boek.get(5), boek.get(6), Integer.parseInt(boek.get(7)));
                    library.addBook(book);
                } catch (Exception e){
                    setVorigeException(e);
                }
            }
        }
    }

    @Dan("zijn/is er van het boek met titel {string} nog {int} beschikbaar")
    public void zijnErVanHetBoekMetTitelNogBeschikbaar(final String titel, final int aantalBeschikbaar) {
        assertEquals("Het verwachte aantal beschikbare boeken komt niet overeen met het daadwerkelijke aantal",
                aantalBeschikbaar,library.getaantalBeschikbareBoeken(titel));
    }
}