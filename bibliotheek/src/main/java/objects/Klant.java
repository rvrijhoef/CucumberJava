package objects;

import exceptions.KlantException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Klant {
    private int id;
    private String naam;
    private Date geboorteDatum;
    private String adres;
    private String opmerkingen;
    private boolean actief;
    private BigDecimal boete;

    private static int maxId = -1;

    public Klant(String naam, Date geboorteDatum, String adres) throws KlantException {
        id = ++maxId;
        actief = true;
        if(naam == null || naam.isEmpty()){

        }

        this.naam = naam;
        this.geboorteDatum = geboorteDatum;
        this.adres = adres;
        if(getLeeftijd() < 12){
            throw new KlantException("Klant is te jong");
        }
        boete = new BigDecimal(0);
    }

    public int getLeeftijd(){
        LocalDate today = LocalDate.now();
        LocalDate birthday = this.geboorteDatum.toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate();

        Period p = Period.between(birthday, today);

        return p.getYears();
    }

    //getter setters
    public boolean isActief() {
        return actief;
    }

    public void setActief(boolean actief) {
        this.actief = actief;
    }

    public String getOpmerkingen() {
        return opmerkingen;
    }

    public void setOpmerkingen(String opmerkingen) {
        this.opmerkingen = opmerkingen;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Date getGeboorteDatum() {
        return geboorteDatum;
    }

    public void setGeboorteDatum(Date geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getBoete() {
        return boete.setScale(2, RoundingMode.HALF_EVEN);
    }

    public void setBoete(BigDecimal boete) {
        this.boete = boete;
    }
}
