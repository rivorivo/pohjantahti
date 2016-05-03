package ohtumini.UI;

import ohtumini.io.IO;
import viitteet.Article;
import viitteet.Book;
import viitteet.Inproceedings;
import viitteet.Booklet;
import viitteet.Inbook;
import viitteet.Viite;
import viitteet.Viitelista;

/**
 *
 * @author
 */
public class LuoViite {

    private final Viitelista VIITTEET;
    private final IO IO;

    public LuoViite(IO io, Viitelista v) {
        this.VIITTEET = v;
        this.IO = io;
    }

    public void suorita() {
        tulostaLuoUusiViiteKomennot();
        IO.print("Anna viitteen tyyppi");
        String kasky = IO.readLine("> ");
        aloitaAlikysely(kasky);
    }

    private void tulostaLuoUusiViiteKomennot() {
        IO.print("\n" + "Käytettävissä olevat komennot:");
        IO.print("- 1 / article:                Luo uuden article viitteen");
        IO.print("- 2 / book:                   Luo uuden book viitteen");
        IO.print("- 3 / inproceedings:          Luo uuden inproceedings viitteen");
        IO.print("- 4 / booklet:                Luo uuden booklet viitteen");
        IO.print("- 5 / inbook:                 Luo uuden Inbook viitteen");
        IO.print("- 6 / palaa:                  Siirtää takaisin päävalikkoon \n");
    }

    private void aloitaAlikysely(String kasky) {
        Viite uusiViite;
        if (kasky.split(" ")[0].compareToIgnoreCase("article") == 0 || kasky.startsWith("1")) {
            IO.print("Luodaan uusi article-viite");
            uusiViite = new Article();
        } else if (kasky.split(" ")[0].compareToIgnoreCase("book") == 0 || kasky.startsWith("2")) {
            IO.print("Luodaan uusi book-viite");
            uusiViite = new Book();
        } else if (kasky.split(" ")[0].compareToIgnoreCase("inproceedings") == 0 || kasky.startsWith("3")) {
            IO.print("Luodaan uusi inproceedings-viite");
            uusiViite = new Inproceedings();
        } else if (kasky.split(" ")[0].compareToIgnoreCase("booklet") == 0 || kasky.startsWith("4")) {
            IO.print("Luodaan uusi booklet-viite");
            uusiViite = new Booklet();
        } else if (kasky.split(" ")[0].compareToIgnoreCase("inbook") == 0 || kasky.startsWith("5")) {
            IO.print("Luodaan uusi inbook-viite");
            uusiViite = new Inbook();
        } else {
            IO.print("\n");
            IO.print("Viitettä ei luotu.");
            return;
        }
        String syote;
        for (String kentta : uusiViite.kentat()) {
            IO.print("Anna kentta " + kentta + (uusiViite.onkoPakollinen(kentta) ? "*" : "") + ":");
            int kentanIndeksi = -1;
            int vaihtoehtoja = kentta.split("/").length;
            do {
                kentanIndeksi++;
                if (kentanIndeksi >= vaihtoehtoja) {
                    kentanIndeksi = 0;
                }
                if (vaihtoehtoja > 1) {
                    IO.print("Anna " + kentta.split("/")[kentanIndeksi] + ", tai anna tyhjä rivi ohittaaksesi");
                }
                syote = IO.readLine("> ");
                if (uusiViite.onkoPakollinen(kentta) && syote.trim().length() <= 0 && kentanIndeksi + 1 == vaihtoehtoja) {
                    IO.print("\n" + kentta + " on pakollinen!");
                    IO.print("Anna " + kentta + "!");
                }
            } while ((kentanIndeksi + 1 < vaihtoehtoja || uusiViite.onkoPakollinen(kentta)) && syote.length() == 0);
            if (syote.length() > 0) {
                uusiViite.lisaaTieto(kentta.split("/")[kentanIndeksi], syote);
            }
        }
        boolean virhe = false;
        IO.print("Anna viitteelle tunniste:");
        do {
            if (virhe) {
                IO.print("Tunniste on pakollinen.\n Anna viitteelle tunniste:");
            }
            virhe = true;
            syote = IO.readLine("> ");
        } while (VIITTEET.get(syote) != null || syote.length() == 0); //Ei anneta luoda
        uusiViite.setTunniste(syote);                                 //useaa viitettä joilla
        VIITTEET.add(uusiViite);                                      //sama tunniste
        IO.print("Viite luotu");
        viiteListanKoko();
    }

    private void viiteListanKoko() {
        IO.print("Viitteitä yhteensä: " + VIITTEET.size());
    }

}
