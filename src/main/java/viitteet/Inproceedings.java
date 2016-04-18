/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viitteet;

/**
 *
 * @author rivorivo
 */
public class Inproceedings extends Viite {
    
    public static final String[] kentat = {"author", "title", "booktitle", "year", "pages", "publisher","address"};
    public static final boolean[] pakollisuus={true,true,true,true,false,false,false};    

    public Inproceedings() {
        avaimet = new String[kentat.length];
        super.pakollisuus=pakollisuus;
        super.kentat=kentat;
    }

    
    
    @Override
    public String annaViitteenTyypinNimi() {
        return "Inproceedings";
    }
    
}
