#language:nl
Functionaliteit: Het uitlenen van boeken testen

  Achtergrond:
    Gegeven een bibliotheek zonder klanten
    En ik een standaard klant toevoeg

  Scenario: Een boek uitlenen
    Als ik het boek met titel "Lover Birds" uitleen
    Dan zijn er nog maar 4 exemplaren in de bibliotheek
    En heeft de klant er 1 in bezit