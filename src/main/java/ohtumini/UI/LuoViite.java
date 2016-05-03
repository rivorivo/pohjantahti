package ohtumini.UI;

import ohtumini.io.IO;
import viitteet.Article;
import viitteet.Book;
import viitteet.Inproceedings;
import viitteet.Viite;
import viitteet.Viitelista;

/**
 *
 * @author
 */
public class LuoViite {

    private final Viitelista VIITTEET;
    private final IO IO;
    private Viite uusiViite;

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
        IO.print("- 4 / palaa:                  Siirtää takaisin päävalikkoon \n");
    }

    private void aloitaAlikysely(String komento) {
                    
        if (!luotuViite(komento)){
            IO.print("\n");
            IO.print("Viitettä ei luotu.");
            return;
        }
        
        kyseleKentat();
        kyseleTunniste();
        
    }

    private void viiteListanKoko() {
        IO.print("Viitteitä yhteensä: " + VIITTEET.size());
    }
    
    private boolean articleViite(String komento){
        if (komento.split(" ")[0].compareToIgnoreCase("article") == 0 || komento.startsWith("1")) {
            IO.print("Luodaan uusi article-viite");
            uusiViite = new Article();
            return true;
        }
        return false;
    }
    
    private boolean bookViite(String komento){
        if (komento.split(" ")[0].compareToIgnoreCase("book") == 0 || komento.startsWith("2")) {
            IO.print("Luodaan uusi book-viite");
            uusiViite = new Book();
            return true;
        }
        return false;
    }
    
    private boolean inproceedingsViite(String komento){
        if (komento.split(" ")[0].compareToIgnoreCase("inproceedings") == 0 || komento.startsWith("3")) {
            IO.print("Luodaan uusi inproceedings-viite");
            uusiViite = new Inproceedings();
            return true;
        }
        return false;
    }
    
    private boolean luotuViite(String komento){
        return articleViite(komento) || bookViite(komento) || inproceedingsViite(komento); 
    }
    
    private void kyseleKentat(){
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
    }
    
    private void kyseleTunniste(){
        String syote;
        IO.print("Anna viitteelle tunniste:");
        do {
            syote = IO.readLine("> ");
        } while (VIITTEET.get(syote) != null || syote.length() == 0); //Ei anneta luoda
        uusiViite.setTunniste(syote);                                 //useaa viitettä joilla
        VIITTEET.add(uusiViite);                                      //sama tunniste
        IO.print("Viite luotu");
        viiteListanKoko();
    }
}