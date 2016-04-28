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

    public void aloitaKysely() {
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

    //luo-viite 
    //"1"
    public void luoViite() {
        if (komento.split(" ")[0].compareToIgnoreCase("luo-viite") == 0 || komento.split(" ")[0].compareTo("1") == 0) {
           new LuoViite(IO, viitteet).suorita();
        }
    }

    //tulosta-viite <viitteen numero> 
    //"2"
    public void tulostaViite() {
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

    // aseta-kentta <viitteen numero> <kentan nimi> <arvo> 
    //"3"
    public void asetaKentta() {
        if (komento.split(" ")[0].compareToIgnoreCase("aseta-kentta") == 0 || komento.startsWith("3")) {
            String kentta;
            String avain;
            String tunniste = tunnisteenTarkastusJaKysely();
            if (!onkoTunniste(tunniste)) return;
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
    
    //tarkastaa onko tunniste asetettu aseta-kentta syötteen yhteydessä. Jos ei niin, pyytää asettamaan tunnisteen.
    public String tunnisteenTarkastusJaKysely() {
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
    
    //tarkastaa onko kentta asetettu aseta-kentta syötteen yhteydessä. Jos ei niin, pyytää asettamaan kentän.
    public String kentanTarkastusJaKysely() {
        String kentta;
        if (komento.split(" ").length > 2) {
            kentta = komento.split(" ")[2];
        } else {
            IO.print("Anna kentta: ");
            kentta = IO.readLine("> ");
        }
        return kentta;
    }

    //tarkastaa onko avain asetettu aseta-kentta syötteen yhteydessä. Jos ei niin, pyytää asettamaan avaimen.
    public String avaimenTarkastusJaKysely(String komentoNoCapitalizationChanges) {
        String avain;
        if (komento.split(" ").length > 3) {
            avain = komentoNoCapitalizationChanges.split(" ", 4)[3];
        } else {
            IO.print("Anna avain: ");
            avain = IO.readLine("> ");
        }
        return avain;
    }

    // tulosta-bibtex <viitteen numero> 
    //"4"
    public void tulostaBibTeX() {
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

    // luo bibtext tiedoston viitteistä 
    //"5"
    public void luoBibTex() {
        if (komento.split(" ")[0].compareToIgnoreCase("luo-bibtex-tiedosto") == 0 || komento.startsWith("5")) {
            BibtexTiedostoKysely t = new BibtexTiedostoKysely(IO, viitteet, bibTexViitteet);
            bibTexViitteet = t.suorita();
        }
    }
    
    //listaa viitteet
    //"6"
    public void listaaViitteet() {
        if (komento.split(" ")[0].compareToIgnoreCase("listaa-viitteet") == 0 || komento.startsWith("6")) {
            String pitkaVaiLyhyt;
            IO.print("Tulostetaanko vain lyhyt versio kustakin viitteestä? Y/n");
            pitkaVaiLyhyt = IO.readLine("> ");
            IO.print(viitteet.size() + " viitettä");
            if (pitkaVaiLyhyt.compareToIgnoreCase("n") == 0) {
                for (Viite v : viitteet) {
                    IO.print(v.getTunniste() + " (" + v.annaViitteenTyypinNimi() + ")");
                    IO.print(v.toString());
                }
            }
            else {
                for (Viite v : viitteet) {
                    IO.print(v.getTunniste() + " (" + v.annaViitteenTyypinNimi() + ")");
                }
            }
        }
    }
    
    public void poistaViite() {
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

    //tulostaa komennot 
    //"7"
    public void komennot() {
        if (komento.split(" ")[0].compareToIgnoreCase("komennot") == 0 || komento.startsWith("8") || komento.length() == 0) {
            tuloste.tulostaKomennot();
        }
    }

    public void lataaTiedosto() {
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

    public void tallennaTiedostoon() {
        if (komento.split(" ")[0].compareToIgnoreCase("tallenna") == 0 || komento.startsWith("10")) {
            new TallennusKysely(IO, viitteet).suorita();
        }
    }

    // luo bibtext tiedoston viitteistä 
    //"10"
    public void lopeta() {
        if (komento.split(" ")[0].compareToIgnoreCase("lopeta") == 0 || komento.startsWith("11")) {
            if (varmistus("Tallennetaanko muutokset?")) {
                new TallennusKysely(IO, viitteet).suorita();
            }
            running = false;
        }
    }
    
    // palauttaa tiedon siitä onko ohjelma ajossa
    public boolean getRunning(){
        return running;
    }

    //EasyB:tä varten ainakin Joda Koska startsWith
    public void lopetaTallentamatta() {
        if (komento.split(" ")[0].compareToIgnoreCase("tallentamatta-lopeta") == 0) {
            running = false;
        }
    }

    //hyrr hirviö, koska aika
    private boolean varmistus(String kysymys) {
        String yn = "";
        while (!(yn.startsWith("n") || yn.startsWith("e") || yn.startsWith("y") || yn.startsWith("k"))) {
            IO.print(kysymys);
            yn = IO.readLine("y/n > ").toLowerCase();
        }
        return (yn.startsWith("y") || yn.startsWith("k"));
    }
    
    
}
