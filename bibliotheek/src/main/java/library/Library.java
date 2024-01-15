package library;

import exceptions.BoekException;
import exceptions.KlantException;
import exceptions.UitleenException;
import objects.Boek;
import objects.Klant;
import objects.UitleenObject;
import util.Utils;
import util.XmlReader;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Library {
    private XmlReader xmlReader = new XmlReader();
    private List<Boek> boeken = new ArrayList();
    private List<Klant> klanten = new ArrayList<>();
    private HashMap<Klant, List<UitleenObject>> uitgeleendeBoeken = new HashMap<>();
    private Instant datum;

    public Library() {
        boeken = xmlReader.loadBooks();
        klanten = xmlReader.loadCustomers();
        datum = Instant.now();
    }

    public void addBook(Boek boek) throws BoekException {
        if (boeken.stream().noneMatch(p -> p.getIsbn().equals(boek.getIsbn()))) {
            boeken.add(boek);
        } else {
            throw new BoekException("Het boek zit al in de bibliotheek collectie");
        }
    }

    public void addCustomer(Klant cust) throws KlantException {
        if (klanten.stream().noneMatch(p -> p.getNaam().equals(cust.getNaam()) &&
                p.getAdres().equals(cust.getAdres()) &&
                p.getGeboorteDatum().equals(cust.getGeboorteDatum()))) {
            klanten.add(cust);
        } else {
            throw new KlantException("De klant komt al in het systeem voor");
        }
    }

    public Boek leenBoek(int boekId, int klantId) throws BoekException, KlantException {
        Klant cust = getKlant(klantId);
        Boek boek = getBoek(boekId);

        if (boek.getAantalBeschikbaar() > 0)
            boek.setAantalBeschikbaar(boek.getAantalBeschikbaar() - 1);
        else {
            throw new BoekException("Het boek " + boek.getTitel() + " is niet meer op voorraad.");
        }
        if (!cust.isActief()) {
            throw new KlantException("De klant is niet actief.");
        }
        if (uitgeleendeBoeken.containsKey(getKlant(klantId))) {
            if (uitgeleendeBoeken.get(getKlant(klantId)).size() >= 3) {
                throw new KlantException("De klant heeft teveel boeken geleend");
            }
        }

        if (cust.getBoete().compareTo(new BigDecimal(0)) != 0) {
            throw new KlantException("De klant heeft nog een boete openstaan van: " + cust.getBoete());
        }
        if (uitgeleendeBoeken.containsKey(cust)) {
            if (uitgeleendeBoeken.get(cust).stream().filter(p -> p.inleverDatum.isBefore(Instant.now())).count() > 0) {
                throw new KlantException("De klant heeft nog boeken die te laat zijn");
            }
        } else {
            uitgeleendeBoeken.put(getKlant(klantId), new ArrayList<>());
        }
        uitgeleendeBoeken.get(getKlant(klantId)).add(new UitleenObject(boek, Instant.now().plus(10,ChronoUnit.DAYS)));

        return boek;
    }

    public int getboekId(final String titel) {
        return boeken.stream().filter(p -> p.getTitel().equals(titel)).findFirst().get().getBoekId();
    }

    public void returnBook(int klantId, int boekId) {
        Klant cust = klanten.stream().filter(p -> p.getId() == klantId).findFirst().get();
        UitleenObject uitleenObject = uitgeleendeBoeken.get(cust).stream().filter(p -> p.boek.getBoekId() == boekId).findFirst().get();
        uitgeleendeBoeken.get(cust).remove(uitleenObject);
    }


    public void betaalBoete(int klantId, BigDecimal amount) throws KlantException {
        Klant cust = klanten.stream().filter(p -> p.getId() == klantId).findFirst().get();
        if (cust.getBoete().compareTo(BigDecimal.valueOf(0)) == 0 || cust.getBoete().compareTo(amount) == -1) {
            throw new KlantException("De Klant heeft geen of minder boete dan je wilt aflossen");
        } else {
            cust.setBoete(cust.getBoete().subtract(amount));
            if (cust.getBoete().compareTo(BigDecimal.valueOf(25)) <= 0) {
                cust.setActief(true);
            }
        }
    }

    public int getAantalKlanten() {
        return klanten.size();
    }

    public int getAantalBoeken() {
        return boeken.size();
    }

    public Klant getKlant(final int klantId) {
        return klanten.stream().filter(p -> p.getId() == klantId).findFirst().get();
    }

    public List<Klant> getKlanten() {
        return klanten;
    }

    public List<UitleenObject> getUitgeleendeBoeken(int klantId) {
        return uitgeleendeBoeken.containsKey(getKlant(klantId)) ? uitgeleendeBoeken.get(getKlant(klantId)) : null;
    }

    public void nachtelijkProces() {
        datum = datum.plus(1, ChronoUnit.DAYS);

        for (Klant cust : klanten) {
            List<UitleenObject> uos = uitgeleendeBoeken.get(cust);
            for (UitleenObject uo: uos) {
                if(datum.isAfter(uo.inleverDatum)){
                    cust.setBoete(cust.getBoete().add(BigDecimal.valueOf(0.25)));
                }
            }

            if (cust.getBoete().compareTo(BigDecimal.valueOf(25)) > 0) {
                cust.setActief(false);
            }
        }
    }

    public int getaantalBeschikbareBoeken(String titel) {
        return boeken.stream().filter(p -> p.getTitel().equals(titel)).findFirst().get().getAantalBeschikbaar();
    }

    public void verlengBoek(final int klantId, final int boekId) throws UitleenException {
        Boek boek = getBoek(boekId);

        List<UitleenObject> uitgeleendeBoeken = getUitgeleendeBoeken(klantId);
        uitgeleendeBoeken.stream().filter(p -> p.boek.equals(boek)).findFirst().get().verlengBoek();

    }

    public Boek getBoek(final int boekId) {
        return boeken.stream().filter(p -> p.getBoekId() == boekId).findFirst().get();
    }
}

