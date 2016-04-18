package ohtumini.UI;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Locale;
import ohtumini.bibtex.BibTexTiedosto;
import ohtumini.io.IO;
import viitteet.Article;
import viitteet.Book;
import viitteet.Viite;

/**
 *
 * @author samukaup
 */
public class Kysely {

    private IO io;
    private LinkedList<Viite> viitteet;
    private Tulosteet tuloste;
    private boolean running;
    private String komento;

    public Kysely(IO io) {
        this.io = io;
        this.viitteet = new LinkedList<>();
        this.tuloste = new Tulosteet(io);
        this.running = true;

    }

    public void run() {
        String komentoNoCapitalizationChanges;

        while (running) {
            tuloste.tulostaKomennot();
            komentoNoCapitalizationChanges = io.readLine(">");
            this.komento = komentoNoCapitalizationChanges.toLowerCase(Locale.ROOT);
            aloitaKysely(komentoNoCapitalizationChanges);
        }
    }

    public void aloitaKysely(String komentoNoCapitalizationChanges) {
        luoViite();
        tulostaViite();
        asetaKentta(komentoNoCapitalizationChanges);
        tulostaBibTeX();
        luoBibTex();
        komennot();
        lopeta();
    }

    //luo-viite 
    //"1"
    public void luoViite() {
        if (komento.startsWith("luo-viite") || komento.startsWith("1")) {
            tuloste.tulostaLuoUusiViiteKomennot();
            io.print("Anna viitteen tyyppi");
            String kasky = io.readLine("> ");
            aloitaAlikysely(kasky);
        }
    }

    public void aloitaAlikysely(String kasky) {
        luoArticle(kasky);
        luoBook(kasky);
        palaa(kasky);
    }
    
    //luo Article viitteen 
    //"1" + "1"
    public void luoArticle(String kasky) {
        if (kasky.startsWith("article") || kasky.startsWith("1")) {
            io.print("Uusi article viite luotu");
            viitteet.add(new Article());
            viiteListanKoko();
        }
    }
    
    //luo Book viitteen 
    //"1" + "2"
    public void luoBook(String kasky) {
        if (kasky.startsWith("book") || kasky.startsWith("2")) {
            io.print("Uusi book viite luotu");
            viitteet.add(new Book());
            viiteListanKoko();
        }
    }
    
    //palauttaa takaisin kyselyyn luoViite kyselystä 
    //"1" + "3"
    public void palaa(String kasky) {
        if (kasky.startsWith("palaa") || kasky.startsWith("3")) {
            io.print("\n Viitettä ei luotu. ");
        }
    }
    
    public void viiteListanKoko() {
        io.print("Viitteitä yhteensä: " + viitteet.size());
    }

    //tulosta-viite <viitteen numero> 
    //"2"
    public void tulostaViite() {
        if (komento.startsWith("tulosta-viite") || komento.startsWith("2")) {
            int viitteenNumero = Integer.parseInt(komento.substring(14)) - 1;
            io.print("= ");
            io.print("" + viitteet.get(viitteenNumero));
        }
    }

    // aseta-kentta <viitteen numero> <kentan nimi> <arvo> 
    //"3"
    public void asetaKentta(String komentoNoCapitalizationChanges) {
        if (komento.startsWith("aseta-kentta") || komento.startsWith("3")) {
            //io.print("Anna viitteen numero, jolle haluat asettaa uuden kentän: ");
            //io.readLine(komento);
            int viitteenNumero = Integer.parseInt(komento.split(" ")[1]) - 1;
            viitteet.get(viitteenNumero).lisaaTieto(komento.split(" ")[2], komentoNoCapitalizationChanges.split(" ", 4)[3]);
            io.print("= ");
        }
    }

    // tulosta-bibtex <viitteen numero> 
    //"4"
    public void tulostaBibTeX() {
        if (komento.startsWith("tulosta-bibtex") || komento.startsWith("4")) {
            int viitteenNumero = Integer.parseInt(komento.split(" ")[1]) - 1;
            io.print("= ");
            io.print(viitteet.get(viitteenNumero).luoBibTeX());
        }
    }

    // luo bibtext tiedoston viitteistä 
    //"5"
    public void luoBibTex() {
        if (komento.startsWith("luo-bibtex-tiedosto") || komento.startsWith("5")) {
            io.print("Anna tiedostonimi mihin BibTex tulostetaan:");
            try {
                String nimi = io.readLine("> ");
                BibTexTiedosto t = new BibTexTiedosto(nimi);
                for (Viite v : viitteet) {
                    t.lisaaViiteTiedostoon(v.luoBibTeX());
                }
                io.print("Viitteet löytyvät tiedostosta " + nimi + ".bib");
            } catch (IOException ex) {
                io.print("Tiedoston luominen ei onnistu.");
                io.print("Tarkista kansion kirjoitus oikeudet.");
            }
        }
    }

    //tulostaa komennot 
    //"6"
    public void komennot() {
        if (komento.startsWith("komennot") || komento.startsWith("6")) {
            tuloste.tulostaKomennot();
        }
    }

    // luo bibtext tiedoston viitteistä 
    //"7"
    public void lopeta() {
        if (komento.startsWith("lopeta") || komento.startsWith("7")) {
            running = false;
        }
    }
}
