package ohtumini.UI;

import java.util.HashMap;
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

    HashMap<String, String> viiteTyypit;
    
    public LuoViite(IO io, Viitelista v) {
        this.VIITTEET = v;
        this.IO = io;
        
        viiteTyypit = new HashMap<>();
        viiteTyypit.put("1", "article");
        viiteTyypit.put("2", "book");
        viiteTyypit.put("3", "inproceedings");
    
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
    
    private Viite tulkkaaKaskyJaLuoViiteOlio(String kasky) {
        String kaskynEkaOsa = kasky.split(" ")[0].toLowerCase();
        String kaskynEkaMerkki = "" + kasky.charAt(0);
        String luokanNimi = null;
        
        if (viiteTyypit.containsValue(kaskynEkaOsa)) {
            luokanNimi = kaskynEkaOsa; 
        } else if (viiteTyypit.containsKey(kaskynEkaMerkki)) {
            luokanNimi = viiteTyypit.get(kaskynEkaMerkki);    
        }

        try {
            Class<?> viiteLuokka = Class.forName("viitteet." + luokanNimi.substring(0,1).toUpperCase() + luokanNimi.substring(1, luokanNimi.length()));
            IO.print("Luodaan uusi " + luokanNimi + "-viite");
            return (Viite) viiteLuokka.newInstance();
        } catch (Exception ex) {
            IO.print("\n");
            IO.print("Viitettä ei luotu.");
        }

        return null;
    }


    private void aloitaAlikysely(String kasky) {

        Viite uusiViite = tulkkaaKaskyJaLuoViiteOlio(kasky);
        
        if (uusiViite == null) {
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
        IO.print("Anna viitteelle tunniste:");
        do {
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
