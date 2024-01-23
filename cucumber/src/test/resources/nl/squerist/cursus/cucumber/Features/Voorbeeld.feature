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

  Abstract Scenario: ISBN controle
    Als de volgende boeken worden toegevoegd aan de bibliotheek
      | titel       | auteur     | genre | prijs | datum      | omschrijving              | isbn   | aantal |
      | test boek 1 | Tinus Test | onzin | 45.35 | 2020-02-01 | een boek over testen enzo | <isbn> | 50     |
    Dan is de volgende exception gegeven: <exception> met de inhoud "<inhoud>"

    Voorbeelden:
      | isbn          | exception     | inhoud                      |
      | 9784333443437 | geen          | geen                        |
      | 9784333443435 | BoekException | Geen geldig isbn opgegeven. |

  Scenario: Een klant scenario flow
    Als ik een standaard klant toevoeg
    En ik het boek met titel "Oberon's Legacy" uitleen
    En ik het boek met titel "Midnight Rain" uitleen
    En ik het boek met titel "Maeve Ascendant" uitleen
    Dan heeft de klant 3 boeken in bezit

    Als de klant het boek met titel "Oberon's Legacy" inleverd
    En de klant het boek met titel "Maeve Ascendant" inleverd
    Dan heeft de klant 1 boek in bezit

    Als er 60 dagen zijn verstreken
    En de klant het boek met titel "Midnight Rain" inleverd
    Dan heeft de klant 0 boeken in bezit
    Maar heeft de klant 12,50 boete openstaan
    En is het klant account gezet op status: "niet actief"

    Als de klant 10,50 van zijn boete aflost
    Dan heeft de klant 2,00 boete openstaan
    En is het klant account gezet op status: "actief"

    Als de klant 2,00 van zijn boete aflost
    Dan heeft de klant 0,00 boete openstaan
    En ik het boek met titel "Splish Splash" uitleen
    Dan heeft de klant 1 boek in bezit
    En is er van het boek met titel "Splish Splash" nog 1 beschikbaar