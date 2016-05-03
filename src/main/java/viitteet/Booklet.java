package viitteet;

/**
 *
 * @author jphanski
 */
public class Booklet extends Viite {
    /**
     * Kentät jotka tämä viitetyyppi muistaa, nimettyinä.
     */
    public static final String[] kentat = {"Author/Editor", "Title", "Howpublished", "Year", "Address", "Month", "Note", "Key"};
    /**
     * Merkitsee onko vastaava kentta pakollinen. True, jos on pakollinen, false jos ei pakollinen.
     * kentat[0] on pakollinen mikäli pakollisuus[0] on true.
     */
    private static final boolean[] pakollisuus = {false, true, false, false, false, false, false, false};
    
    /**
     * Luo uuden Kirjaviitteen.
     */
    public Booklet() {
        avaimet = new String[kentat.length];
        super.pakollisuus = pakollisuus;
        super.kentat = kentat;
        super.kenttaIndeksit = new int[kentat.length];

    }

    @Override
    public String annaViitteenTyypinNimi() {
        return "Booklet";
    }
}
