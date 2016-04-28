package ohtumini.UI;

import java.io.IOException;
import ohtumini.bibtex.BibTexTiedosto;
import ohtumini.io.IO;
import viitteet.Viite;
import viitteet.Viitelista;

/**
 *
 * @author Simo
 */
public class BibtexTiedostoKysely {

    private final IO IO;
    private final Viitelista KAIKKIVIITTEET;
    private Viitelista lisaamattomatViitteet;
    private Viitelista tulostettavatViitteet;
    private BibTexTiedosto tiedosto;
    private String nimi;

    /**
     * Luo uuden Bibtex tiedoston luonti kyselyn
     *
     * @param io
     * @param kaikkiViitteet Viitelista, joka sisältää kaikki ohjelmassa olevat
     * viitteet
     * @param tulostettavatViitteet Viitelista, joka sisältää Tiedoston
     * kyselyssä ennen lisätyt viitteet
     */
    public BibtexTiedostoKysely(IO io, Viitelista kaikkiViitteet, Viitelista tulostettavatViitteet) {
        this.IO = io;
        this.KAIKKIVIITTEET = kaikkiViitteet;
        this.lisaamattomatViitteet = viitelistojenLeikkaus(kaikkiViitteet, tulostettavatViitteet);
        this.nimi = "";
        this.tulostettavatViitteet = tulostettavatViitteet;
    }

    /**
     * Käynnistää Bibtex tiedoston luomis kyselyn
     *
     * @return palauttaa Viitelistan, joka sisältää viitteet jotka on lisätty
     * ennestään tulostettaviksi
     */
    public Viitelista suorita() {
        if (!luoTiedosto()) {
            return tulostettavatViitteet;
        }
        viitteetTiedostoon();
        return tulostettavatViitteet;
    }

    private void viitteetTiedostoon(Viitelista v) {
        for (Viite viite : v) {
            tiedosto.lisaaViiteTiedostoon(viite.luoBibTeX());
        }
        IO.print("Viitteet löytyvät tiedostosta " + this.nimi + ".bib");

    }

    private boolean luoTiedosto() {
        IO.print("Anna tiedostonimi mihin BibTex tulostetaan:");
        nimi = IO.readLine("> ");
        tiedosto = new BibTexTiedosto(nimi);
        try {
            if (!tiedosto.luoTiedosto()) {
                if (varmistus("Tiedosto on jo olemassa. Ylikirjoitetaanko? Y/n")) {
                    tiedosto.poistaTiedosto();
                    tiedosto.luoTiedosto();
                } else {
                    IO.print("Anna tiedostonimi mihin BibTex tulostetaan:");
                    String uusiNimi = IO.readLine("> ");
                    if (!nimi.equals(uusiNimi)) {
                        nimi = uusiNimi;
                        tiedosto = new BibTexTiedosto(nimi);
                        tiedosto.luoTiedosto();
                    } else {
                        IO.print("Tiedoston luominen ei onnistu tällä nimellä!");
                        return false;
                    }
                }
            }
            return true;
        } catch (IOException ex) {
            IO.print("Tiedoston luominen ei onnistu.");
            IO.print("Tarkista kansion kirjoitusoikeudet.");
            return false;
        }
    }

    private void viitteetTiedostoon() {
        osaViitteistaOhje();
        while (true) {
            String komento = IO.readLine(">");
            if (komento.startsWith("1") || komento.split(" ")[0].compareToIgnoreCase("aloita-lisääminen") == 0) {
                IO.print("Syötä tiedostoon lisättävien viitteiden tunnisteita. Tyhjä syöte lopettaa lisäämisen.");
                lisaaViitteitaTulostettavaksi();
                osaViitteistaOhje();
            } else if (komento.startsWith("2") || komento.split(" ")[0].compareToIgnoreCase("lisäämättömät-tunnisteet") == 0) {
                tulostaViitteidenTunnisteet(lisaamattomatViitteet);
            } else if (komento.startsWith("3") || komento.split(" ")[0].compareToIgnoreCase("lisätyt-tunnisteet") == 0) {
                tulostaViitteidenTunnisteet(tulostettavatViitteet);
            } else if (komento.startsWith("4") || komento.split(" ")[0].compareToIgnoreCase("tyhjennä-lisätyt") == 0) {
                if (varmistus("Haluatko varmasti tyhjentää tulsotettavaksi valittujen listan? Y/n")) {
                    tyhjennaLisatyt();
                }
            } else if (komento.startsWith("5") || komento.split(" ")[0].compareToIgnoreCase("luo-kaikista") == 0) {
                viitteetTiedostoon(KAIKKIVIITTEET);
                break;
            } else if (komento.startsWith("6") || komento.split(" ")[0].compareToIgnoreCase("luo-tiedosto") == 0) {
                viitteetTiedostoon(tulostettavatViitteet);
                break;
            } else if (komento.split(" ")[0].compareToIgnoreCase("palaa") == 0 || komento.startsWith("7")) {
                break;
            } else {
                osaViitteistaOhje();
            }
        }
    }

    private void osaViitteistaOhje() {
        IO.print("- 1 / aloita-lisääminen               lisää viitteitä, kunnes syötät tyhjän");
        IO.print("- 2 / lisäämättömät-tunnisteet        tulostaa lisäämättämien viitteiden tunnisteet");
        IO.print("- 3 / lisätyt-tunnisteet              tulostaa tulostettaviksi lisätyt viitteiden tunnisteet");
        IO.print("- 4 / tyhjennä-lisätyt                tyhjentää tulostukseen lisättyjen listan");
        IO.print("- 5 / luo-kaikista                    luo BibTex-tiedoston kaikista viitteistä");
        IO.print("- 6 / luo-tiedosto                    luo BibTex-tiedoston lisätyistä viitteistä");
        IO.print("- 7 / palaa                           lopettaa viitteiden lisäämisen BibTexTiedostoon");
        IO.print("- 8 / komennot                        tulostaa tämän ohjeen");
    }

    private void tulostaViitteidenTunnisteet(Viitelista lista) {
        for (Viite v : lista) {
            IO.print(v.getTunniste() + " (" + v.annaViitteenTyypinNimi() + ")");
        }
    }

    private Viitelista viitelistojenLeikkaus(Viitelista v1, Viitelista v2) {
        Viitelista uusi = new Viitelista("");
        for (Viite v : v1) {
            if (v2.get(v.getTunniste()) == null) {
                uusi.add(v);
            }
        }
        return uusi;
    }

    private void lisaaViitteitaTulostettavaksi() {
        String tunniste = IO.readLine("Tunnisteet: ") + " ";
        while (!tunniste.equals(" ")) {
            for (String s : tunniste.split(" ")) {
                Viite v = lisaamattomatViitteet.get(s);
                if (v != null) {
                    tulostettavatViitteet.add(v);
                    lisaamattomatViitteet.remove(s);
                }
            }
            tunniste = IO.readLine("Tunnisteet: ") + " ";
        }
    }

    private void tyhjennaLisatyt() {
        tulostettavatViitteet = new Viitelista("tulostettavat");
        lisaamattomatViitteet = viitelistojenLeikkaus(KAIKKIVIITTEET, tulostettavatViitteet);
    }

    private boolean varmistus(String kysymys) {
        String yn = "";
        while (!(yn.startsWith("n") || yn.startsWith("e") || yn.startsWith("y") || yn.startsWith("k"))) {
            IO.print(kysymys);
            yn = IO.readLine("y/n > ").toLowerCase();
        }
        return (yn.startsWith("y") || yn.startsWith("k"));
    }
}
