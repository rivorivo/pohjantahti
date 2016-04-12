/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtumini;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;
import viitteet.Article;
import viitteet.Book;
import viitteet.Viite;

public class App {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String komento;
        String komentoNoCapitalizationChanges;
        LinkedList<Viite> viitteet = new LinkedList<>();        
        while (true) {
            komentoNoCapitalizationChanges = reader.nextLine();
            komento = komentoNoCapitalizationChanges.toLowerCase(Locale.ROOT);
            if (komento.startsWith("luo-viite")) {                  //luo-viite <viitteen tyyppi>
                if (komento.startsWith("luo-viite article")) {
                    System.out.println("= ");
                    viitteet.add(new Article());
                    System.out.println(viitteet.size());
                } else if (komento.startsWith("luo-viite book")) {
                    System.out.println("= ");
                    viitteet.add(new Book());
                    System.out.println(viitteet.size());
                } else {
                    System.out.println("? ");
                }
            } else if (komento.startsWith("tulosta-viite")) {       //tulosta-viite <viitteen numero>
                int viitteenNumero = Integer.parseInt(komento.substring(14)) - 1;
                System.out.println("= ");
                System.out.println(viitteet.get(viitteenNumero));
            } else if (komento.startsWith("aseta-kentta")) {        // aseta-kentta <viitteen numero> <kentan nimi> <arvo>
                int viitteenNumero = Integer.parseInt(komento.split(" ")[1]) - 1;
                viitteet.get(viitteenNumero).lisaaTieto(komento.split(" ")[2], komentoNoCapitalizationChanges.split(" ", 4)[3]);
                System.out.println("= ");
            } else if (komento.startsWith("tulosta-bibtex")) {        // tulosta-bibtex <viitteen numero>
                int viitteenNumero = Integer.parseInt(komento.split(" ")[1]) - 1;
                System.out.println("= ");
                System.out.println(viitteet.get(viitteenNumero).luoBibTeX());
            } else {
                System.out.println("? ");
            }
        }
    }
}
