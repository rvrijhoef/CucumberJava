package objects;

import exceptions.BoekException;
import util.ISBNChecker;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

public class Boek {
    private int boekId;
    private String auteur;
    private String titel;
    private String genre;
    private BigDecimal prijs;
    private Date publicatieDatum;
    private String omschrijving;
    private Instant returnBy;
    private String isbn;
    private static int aantalBeschikbaar;

    private static int maxId = -1;

    public Boek(String titel, String auteur, String genre, BigDecimal prijs, Date publishDate, String omschrijving, String isbn, int aantalBeschikbaar) throws Exception {
        boekId = ++maxId;
        this.titel = titel;
        this.auteur = auteur;
        this.genre = genre;
        this.prijs = prijs;
        this.omschrijving = omschrijving;
        this.publicatieDatum = publishDate;
        returnBy = null;
        if(ISBNChecker.isGeldigISBN(isbn)){
            this.isbn = isbn;
        }else{
            throw new BoekException("Geen geldig isbn opgegeven.");
        }
        this.aantalBeschikbaar = aantalBeschikbaar;
    }

    // getter & setters
    public int getBoekId() {
        return boekId;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }

    public Date getPublicatieDatum() {
        return publicatieDatum;
    }

    public void setPublicatieDatum(Date publicatieDatum) {
        this.publicatieDatum = publicatieDatum;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAantalBeschikbaar() {
        return aantalBeschikbaar;
    }
    public void setAantalBeschikbaar(int amountAvailable){
        this.aantalBeschikbaar = amountAvailable;
    }

    public Instant getReturnBy() {
        return returnBy;
    }

    public void setReturnBy(Instant returnBy) {
        this.returnBy = returnBy;
    }
}
