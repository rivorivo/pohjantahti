/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtumini;

import java.util.Locale;
import java.util.Scanner;
import viitteet.Article;
import viitteet.Viite;

public class App {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String komento;
        Viite viite = new Article();
        while (true) {
            komento = reader.nextLine();
            komento = komento.toLowerCase(Locale.ROOT);
            if (komento.startsWith("luo-viite")) {
                System.out.println("= ");
                viite = new Article();                

            } else if (komento.startsWith("tulosta-viite")) {
                System.out.println("= ");
                System.out.println(viite);
            } else if (komento.startsWith("tyhja-komento")) {
                System.out.println("= ");
            } else {
                System.out.println("? ");
            }
        }
    }
}
