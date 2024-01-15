import exceptions.KlantException;
import library.Library;
import objects.Klant;
import org.junit.Assert;
import org.junit.Test;
import util.ISBNChecker;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

public class test {

    @Test
    public void testmethod() throws Exception {
        Library lib = new Library();
        Klant cust = new Klant("Jan Klaasen", Date.from(Instant.parse("1984-08-12T00:00:00.000Z")), "bladibla");
        lib.addCustomer(cust);
        lib.leenBoek(0, cust.getId());
    }

    @Test
    public void klanttejong() {
        Exception ex = Assert.assertThrows(Exception.class, () -> {
            new Klant("Jan Klaasen", Date.from(Instant.parse("2018-08-12T00:00:00.000Z")), "bladibla");
        });
        String expectedMessage = "Klant is te jong";
        Assert.assertEquals(expectedMessage,ex.getMessage());

    }

    @Test
    public void isbnCheckTrue(){
        boolean test = ISBNChecker.isGeldigISBN("9781111337568");
        Assert.assertTrue(test);
    }

    @Test
    public void isbnCheckFalse(){
        boolean test = ISBNChecker.isGeldigISBN("9781234567898");
        Assert.assertFalse(test);
    }

    @Test
    public void testAflossen0() throws KlantException {
        Library lib = new Library();
        Klant cust = new Klant("Jan Klaasen", Date.from(Instant.parse("1984-08-12T00:00:00.000Z")), "bladibla");
        lib.addCustomer(cust);
        cust.setBoete(BigDecimal.valueOf(10));
        lib.betaalBoete(cust.getId(),BigDecimal.valueOf(10.0));
    }

    @Test
    public void testAflossenteweinig() throws KlantException {
        Library lib = new Library();
        Klant cust = new Klant("Jan Klaasen", Date.from(Instant.parse("1984-08-12T00:00:00.000Z")), "bladibla");
        lib.addCustomer(cust);
        cust.setBoete(BigDecimal.valueOf(10));
        lib.betaalBoete(cust.getId(),BigDecimal.valueOf(5.0));
        Assert.assertEquals(BigDecimal.valueOf(5.00).setScale(2),cust.getBoete());
    }

    @Test(expected=KlantException.class)
    public void testAflossenteveel() throws KlantException {
        Library lib = new Library();
        Klant cust = new Klant("Jan Klaasen", Date.from(Instant.parse("1984-08-12T00:00:00.000Z")), "bladibla");
        lib.addCustomer(cust);
        cust.setBoete(BigDecimal.valueOf(10));
        lib.betaalBoete(cust.getId(),BigDecimal.valueOf(11.0));
    }
}
