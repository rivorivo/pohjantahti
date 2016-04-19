package ohtumini.UI;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import ohtumini.bibtex.BibTexTiedosto;
import ohtumini.io.IO;
import viitteet.Article;
import viitteet.Book;
import viitteet.Inproceedings;
import viitteet.Viite;
import viitteet.Viitelista;

interface LuoViite {
    Viite luo();
}
public class Kysely {

    private IO io;
    private Viitelista viitteet;
    private Tulosteet tuloste;
    private boolean running;
    private String komento;

    public Kysely(IO io) {
        this.io = io;
        this.viitteet = new Viitelista("default");
        this.tuloste = new Tulosteet(io);
        this.running = true;

    }

    public void run() {
        String komentoNoCapitalizationChanges;        
        tuloste.tulostaKomennot();
        while (running) {
            io.print("Anna komento");
            komentoNoCapitalizationChanges = io.readLine(">");
            this.komento = komentoNoCapitalizationChanges.toLowerCase(Locale.ROOT);
            aloitaKysely(komentoNoCapitalizationChanges);
        }
    }

    public void aloitaKysely(String komentoNoCapitalizationChanges) {
        HashMap<String, Runnable> komennot = new HashMap<>();
        komennot.put("luo-viite", () -> luoViite());
        komennot.put("1", () -> luoViite());
        komennot.put("tulosta-viite", () -> tulostaViite());
        komennot.put("2", () -> tulostaViite());
        komennot.put("aseta-kentta", () -> asetaKentta(komentoNoCapitalizationChanges));
        komennot.put("3", () -> asetaKentta(komentoNoCapitalizationChanges));
        komennot.put("tulosta-bibtex", () -> tulostaBibTeX());
        komennot.put("4", () -> tulostaBibTeX());
        komennot.put("luo-bibtex-tiedosto", () -> luoBibTex());
        komennot.put("5", () -> luoBibTex());
        komennot.put("komennot", () -> komennot());
        komennot.put("6", () -> komennot());
        komennot.put("lopeta", () -> lopeta());
        komennot.put("7", () -> lopeta());
        
        komennot.getOrDefault(komento.split(" ")[0], () -> komennot()).run();
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
        Viite uusiViite;
        
        HashMap<String, LuoViite> viiteTyypit = new HashMap<>();
        viiteTyypit.put("article", () -> new Article());
        viiteTyypit.put("book", () -> new Book());
        viiteTyypit.put("inproceedings", () -> new Inproceedings());
        viiteTyypit.put("1", () -> new Article());
        viiteTyypit.put("2", () -> new Book());
        viiteTyypit.put("3", () -> new Inproceedings());
        
        if (viiteTyypit.get(kasky) == null) return;
        uusiViite = viiteTyypit.get(kasky).luo();
                
        String syote;
        for (String kentta : uusiViite.kentat()) {
            io.print("Anna kenttä " + kentta + (uusiViite.onkoPakollinen(kentta) ? "*" : "") +":");
            do {
                syote = io.readLine("> ");
            } while (uusiViite.onkoPakollinen(kentta) && syote.length() == 0);
            if (syote.length() > 0) {
                uusiViite.lisaaTieto(kentta, syote);
            }
        }
        io.print("Anna viitteelle tunniste:");
        do {
            syote = io.readLine("> ");
        } while (viitteet.get(syote) != null || syote.length() == 0); //Ei anneta luoda
        uusiViite.setTunniste(syote);                                 //useaa viitettä joilla
        viitteet.add(uusiViite);                                      //sama tunniste
        io.print("Viite luotu");
        viiteListanKoko();
    }

    public void viiteListanKoko() {
        io.print("Viitteitä yhteensä: " + viitteet.size());
    }

    //tulosta-viite <viitteen numero> 
    //"2"
    public void tulostaViite() {
        String tunniste;
        if (komento.startsWith("tulosta-viite") || komento.startsWith("2")) {
            if (komento.split(" ").length < 2) {
                tunniste = io.readLine("Anna tunniste\n> ");
            } else {
                tunniste = komento.split(" ")[1];
            }

            if (viitteet.get(tunniste) == null) {
                io.print("Tunnistetta ei löytynyt");
            } else {
                io.print(viitteet.get(tunniste).toString());
            }
        }
    }

    // aseta-kentta <viitteen numero> <kentan nimi> <arvo> 
    //"3"
    public void asetaKentta(String komentoNoCapitalizationChanges) {
        if (komento.startsWith("aseta-kentta") || komento.startsWith("3")) {
            //io.print("Anna viitteen numero, jolle haluat asettaa uuden kentän: ");
            //io.readLine(komento);
            String tunniste;
            String kentta;
            String avain;
            if (komento.split(" ").length > 1) {
                tunniste = komento.split(" ")[1];
            } else {
                tunniste = io.readLine("Anna tunniste\n> ");
            }
            if (viitteet.get(tunniste) == null) {
                io.print("Tunnistetta ei löytynyt");
                return;
            }
            if (komento.split(" ").length > 2) {
                kentta = komento.split(" ")[2];
            } else {
                io.print("Anna kentta: ");
                kentta = io.readLine("> ");
            }
            if (komento.split(" ").length > 3) {
                avain = komentoNoCapitalizationChanges.split(" ", 4)[3];
            } else {
                io.print("Anna avain: ");
                avain = io.readLine("> ");
            }
            viitteet.get(tunniste).lisaaTieto(kentta, avain);
            io.print("Asettaminen onnistui");
        }
    }

    // tulosta-bibtex <viitteen numero> 
    //"4"
    public void tulostaBibTeX() {
        if (komento.startsWith("tulosta-bibtex") || komento.startsWith("4")) {
            String tunniste;
            if (komento.split(" ").length > 1) {
                tunniste = komento.split(" ")[1];
            } else {
                tunniste = io.readLine("Anna tunniste\n> ");
            }
            if (viitteet.get(tunniste) == null) {
                io.print("Tunnistetta ei löytynyt");
                return;
            }
            io.print(viitteet.get(tunniste).luoBibTeX());
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
                io.print("Tarkista kansion kirjoitusoikeudet.");
            }
        }
    }

    //tulostaa komennot 
    //"6"
    public void komennot() {
        if (komento.startsWith("komennot") || komento.startsWith("6") || komento.length() == 0) {
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
