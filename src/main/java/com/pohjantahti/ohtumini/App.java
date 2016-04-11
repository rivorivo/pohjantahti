/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pohjantahti.ohtumini;

import java.util.Locale;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String komento;
        while (true) {
            komento = reader.nextLine();
            komento = komento.toUpperCase(Locale.ROOT);
            if (komento.startsWith("FIRST COMMAND")) {
                //Juttuja

            } else if (komento.startsWith("SECOND_COMMAND")) {
                //Muita juttuja
            } else {
                System.out.println("? ");
            }
        }
    }
}
