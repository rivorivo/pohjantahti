package ohtumini;

import java.io.IOException;
import ohtumini.io.*;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;
import ohtumini.UI.Kysely;
import ohtumini.UI.Tulosteet;
import ohtumini.bibtex.BibTexTiedosto;
import viitteet.*;

public class App {

    public static void main(String[] args) {
        IO io = new ConsoleIO();
        Kysely kysely = new Kysely(io);
        kysely.run();
    }
    
}
