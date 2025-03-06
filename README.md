# Selainpohjainen puhelinluettelo-sovellus. 
Toiminnallisuudet: 
- Kontaktien listaaminen
- Kontaktien lisääminen lomakkeella
- Kontaktien poistaminen

## Sovelluksen ajaminen lokaalisti
Sovellus on tarkoitettu ajettavaksi Docker-kontissa. Sovelluksen ajaminen lokaalisti tapahtuu helpoiten ajalla komento:
````docker-compose up --build -d```` projektin juuressa. **Huom. Vaatii, että Docker Desktop on asennettuna.**
Komento rakentaa ja pystyttää palvelin- ja tietokanta-kontit. Tietokannan testidatan alustus vaatii /local-dev/ kansiossa
olevien SQL-skriptien ajamisen luotuun tietokantaan. Tämän jälkeen voit avata sovelluksen käyttöliittymän selaimella
osoitteesta http://localhost:8080/