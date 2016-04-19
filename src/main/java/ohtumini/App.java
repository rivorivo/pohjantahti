package ohtumini;

import ohtumini.io.*;
import ohtumini.UI.Kysely;

public class App {

    public static void main(String[] args) {
        IO io = new ConsoleIO();
        Kysely kysely = new Kysely(io);
        kysely.run();
    }
    
}
