#language:nl
Functionaliteit: Test aanmaken klant van de bibliotheek

  Achtergrond:
    Gegeven een bibliotheek zonder klanten

  Scenario: voorbeeld scenario
    Als ik een standaard klant toevoeg
    En ik het boek met titel "Oberon's Legacy" uitleen
    Dan is het totaal aantal klanten 1
    En heeft de klant 1 boek in bezit

  Abstract Scenario: Leeftijd checks
    Als ik een klant toevoeg met naam "<Naam>" en met geboorteDatum "<Geboortedatum>" en adres "<Adres>"
    Dan  is het totaal aantal klanten <TotaalKlanten>
    En is de volgende exception gegeven: <Exceptie> met de inhoud "<Bericht>"

    Voorbeelden:
      | Naam       | Geboortedatum | Adres              | TotaalKlanten | Exceptie       | Bericht          |
      | Pietje Puk | 1980-10-04    | Testweg 10, ergens | 1             | geen           | geen             |
      | Pietje Puk | gisteren      | Testweg 2, ergens  | 0             | KlantException | Klant is te jong |