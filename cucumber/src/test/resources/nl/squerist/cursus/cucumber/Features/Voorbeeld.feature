#language:nl
Functionaliteit: Test aanmaken klant van de bibliotheek

  Achtergrond:
    Gegeven een bibliotheek zonder klanten

  Scenario: voorbeeld scenario
    Als ik een standaard klant toevoeg
    En ik het boek met titel "Oberon's Legacy" uitleen
    Dan is het totaal aantal klanten 1
    En heeft de klant 1 boek in bezit