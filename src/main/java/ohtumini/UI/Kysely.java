package ohtumini.UI;

import ohtumini.io.IO;
import viitteet.Viite;
import viitteet.Viitelista;

public class Kysely {

    private final IO IO;
    private Viitelista viitteet;
    private Viitelista bibTexViitteet;
    private Tulosteet tuloste;
    private boolean running;
    private String komento;

    public Kysely(IO io) {
        this.IO = io;
        this.viitteet = new Viitelista("default");
        this.tuloste = new Tulosteet(io);
        this.bibTexViitteet = new Viitelista("tulostettavat");
        this.running = true;
    }

    public void run() {
        tuloste.tulostaKomennot();
        while (running) {
            this.komento = IO.readLine(">");
            aloitaKysely();
        }
    }

    private void aloitaKysely() {
        luoViite();
        tulostaViite();
        asetaKentta();
        tulostaBibTeX();
        luoBibTex();
        komennot();
        tallennaTiedostoon();
        listaaViitteet();
        poistaViite();
        lataaTiedosto();
        lopeta();
        lopetaTallentamatta();
    }

    // Aloittaa kyselyn uuden viitteen lisäämiseksi
    private void luoViite() {
        if (komento.split(" ")[0].compareToIgnoreCase("luo-viite") == 0 || komento.split(" ")[0].compareTo("1") == 0) {
            new LuoViite(IO, viitteet).suorita();
        }
    }

    // Tulosta-viite <viitteen numero> 
    private void tulostaViite() {
        String tunniste;
        if (komento.split(" ")[0].compareToIgnoreCase("tulosta-viite") == 0 || komento.startsWith("2")) {
            if (komento.split(" ").length < 2) {
                tunniste = IO.readLine("Anna tunniste\n> ");
            } else {
                tunniste = komento.split(" ")[1];
            }

            if (viitteet.get(tunniste) == null) {
                IO.print("Tunnistetta ei löytynyt");
            } else {
                IO.print(viitteet.get(tunniste).toString());
            }
        }
    }

    // Aseta-kentta <viitteen numero> <kentan nimi> <arvo> 
    private void asetaKentta() {
        if (komento.split(" ")[0].compareToIgnoreCase("aseta-kentta") == 0 || komento.startsWith("3")) {
            String kentta;
            String avain;
            String tunniste = tunnisteenTarkastusJaKysely();
            if (!onkoTunniste(tunniste)) {
                return;
            }
            kentta = kentanTarkastusJaKysely();
            if (!viitteet.get(tunniste).onkoKenttaOlemassa(kentta)) {
                IO.print("Asettaminen ei onnistunut, kenttää ei löytynyt");
                return;
            }
            IO.print("Tällä hetkellä kentän " + kentta + " arvo on \"" + viitteet.get(tunniste).lueTieto(kentta) + "\"");
            avain = avaimenTarkastusJaKysely(komento);
            viitteet.get(tunniste).lisaaTieto(kentta, avain);
            IO.print("Asettaminen onnistui");
            IO.print(tunniste + ":" + kentta + " = \"" + viitteet.get(tunniste).lueTieto(kentta) + "\"");
        }
    }

    // Tarkastaa onko tunniste asetettu aseta-kentta syötteen yhteydessä. Jos ei niin, pyytää asettamaan tunnisteen.
    private String tunnisteenTarkastusJaKysely() {
        String tunniste;
        if (komento.split(" ").length > 1) {
            tunniste = komento.split(" ")[1];
        } else {
            tunniste = IO.readLine("Anna tunniste\n> ");
        }
        return tunniste;
    }

    //Tarkastaa onko annettu tunniste validi.
    private boolean onkoTunniste(String tunniste) {
        if (viitteet.get(tunniste) == null) {
            IO.print("Tunnistetta ei löytynyt");
            return false;
        }
        return true;
    }

    // Tarkastaa onko kentta asetettu aseta-kentta syötteen yhteydessä. Jos ei niin, pyytää asettamaan kentän.
    private String kentanTarkastusJaKysely() {
        String kentta;
        if (komento.split(" ").length > 2) {
            kentta = komento.split(" ")[2];
        } else {
            IO.print("Anna kentta: ");
            kentta = IO.readLine("> ");
        }
        return kentta;
    }

    // Tarkastaa onko avain asetettu aseta-kentta syötteen yhteydessä. Jos ei niin, pyytää asettamaan avaimen.
    private String avaimenTarkastusJaKysely(String komentoNoCapitalizationChanges) {
        String avain;
        if (komento.split(" ").length > 3) {
            avain = komentoNoCapitalizationChanges.split(" ", 4)[3];
        } else {
            IO.print("Anna avain: ");
            avain = IO.readLine("> ");
        }
        return avain;
    }

    // Tulostaa BibTex-muotoisen tulosteen viitteestä
    private void tulostaBibTeX() {
        if (komento.split(" ")[0].compareToIgnoreCase("tulosta-bibtex") == 0 || komento.startsWith("4")) {
            String tunniste;
            if (komento.split(" ").length > 1) {
                tunniste = komento.split(" ")[1];
            } else {
                tunniste = IO.readLine("Anna tunniste\n> ");
            }
            if (!onkoTunniste(tunniste)) {
                return;
            }
            IO.print(viitteet.get(tunniste).luoBibTeX());
        }
    }

    // Aloittaa kyselyn BibTex tiedoston luonnista
    private void luoBibTex() {
        if (komento.split(" ")[0].compareToIgnoreCase("luo-bibtex-tiedosto") == 0 || komento.startsWith("5")) {
            BibtexTiedostoKysely t = new BibtexTiedostoKysely(IO, viitteet, bibTexViitteet);
            bibTexViitteet = t.suorita();
        }
    }

    // Listaa ohjelmaan lisätyt viitteet
    private void listaaViitteet() {
        if (komento.split(" ")[0].compareToIgnoreCase("listaa-viitteet") == 0 || komento.startsWith("6")) {
            if (!varmistus("Tulostetaanko vain lyhyt versio kustakin viitteestä? Y/n")) {
                IO.print(viitteet.size() + " viitettä");
                for (Viite v : viitteet) {
                    IO.print(v.getTunniste() + " (" + v.annaViitteenTyypinNimi() + ")");
                    IO.print(v.toString());
                }
            } else {
                IO.print(viitteet.size() + " viitettä");
                for (Viite v : viitteet) {
                    IO.print(v.getTunniste() + " (" + v.annaViitteenTyypinNimi() + ")");
                }
            }
        }
    }

    //Poistaa viitteen tunnisteen perusteella
    private void poistaViite() {
        String tunniste;
        if (komento.split(" ")[0].compareToIgnoreCase("poista-viite") == 0 || komento.startsWith("7")) {
            if (komento.split(" ").length < 2) {
                tunniste = IO.readLine("Anna tunniste\n> ");
            } else {
                tunniste = komento.split(" ")[1];
            }

            if (viitteet.get(tunniste) == null) {
                IO.print("Tunnistetta ei löytynyt");
            } else {
                IO.print("Poistetaan viite " + viitteet.get(tunniste).getTunniste());
                IO.print(viitteet.get(tunniste).toString());
                if (IO.readLine("Haluatko varmasti poistaa tämän tunnisteen? y/N\n> ").compareToIgnoreCase("y") == 0) {
                    viitteet.remove(tunniste);
                }
            }
        }
    }

    // Tulostaa ohjelman komennot
    private void komennot() {
        if (komento.split(" ")[0].compareToIgnoreCase("komennot") == 0 || komento.startsWith("8") || komento.length() == 0) {
            tuloste.tulostaKomennot();
        }
    }

    // Aloittaa kyselyn ohjelman tilan lataamisesta tiedostoon
    private void lataaTiedosto() {
        if (komento.split(" ")[0].compareToIgnoreCase("lataa") == 0 || komento.startsWith("9")) {
            if (viitteet.size() > 0) {
                if (!varmistus("Sinulla on viitteitä, muutokset katoavat jos lataat päälle uudet! Oletko varma?")) {
                    return;
                }
            }
            TallennuksenLatausKysely k = new TallennuksenLatausKysely(IO);
            k.suorita();
            if (k.getLadattuViitelista() != null) {
                viitteet = k.getLadattuViitelista();
            } else {
                IO.print("Viitteitten lataaminen ei onnistunut");
            }
        }
    }

    // Aloittaa kyselyn ohjelman tilanteentallentamisesta tiedostoon
    private void tallennaTiedostoon() {
        if (komento.split(" ")[0].compareToIgnoreCase("tallenna") == 0 || komento.startsWith("10")) {
            new TallennusKysely(IO, viitteet).suorita();
        }
    }

    // Lopettaa ohjelman ja kysyy tallenetaanko
    private void lopeta() {
        if (komento.split(" ")[0].compareToIgnoreCase("lopeta") == 0 || komento.startsWith("11")) {
            if (varmistus("Tallennetaanko muutokset?")) {
                new TallennusKysely(IO, viitteet).suorita();
            }
            running = false;
        }
    }

    // palauttaa tiedon siitä onko ohjelma ajossa
    private boolean getRunning() {
        return running;
    }

    /*
     * EasyBtä varten komento jolla saadaan ohjelma lopetettua ilman varmistusta
     */
    private void lopetaTallentamatta() {
        if (komento.split(" ")[0].compareToIgnoreCase("tallentamatta-lopeta") == 0) {
            running = false;
        }
    }

    /*
     * Kysellään varmistusta toimintoon kunnes annetaan validi vastaus
     */
    private boolean varmistus(String kysymys) {
        String yn = "";
        while (!(yn.startsWith("n") || yn.startsWith("e") || yn.startsWith("y") || yn.startsWith("k"))) {
            IO.print(kysymys);
            yn = IO.readLine("y/n > ").toLowerCase();
        }
        return (yn.startsWith("y") || yn.startsWith("k"));
    }

}
