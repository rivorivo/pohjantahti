package viitteet;

import java.util.ArrayList;
import java.util.Iterator;
import viitteet.Viite;

public class Viitelista implements java.io.Serializable, Iterable<Viite> {
    
    private String nimi;
    private ArrayList<Viite> viitteet;

    public Viitelista(String nimi) {
        this.nimi = nimi;
        viitteet = new ArrayList<>();
    }
    
    /**
     * Lisää viitteen viitelistaan. Viitteen voi tämän jälkeen noutaa tunnisteen perusteella.
     * @param v Lisättävä viite.
     */
    public void add(Viite v) {
        //Laittomia tilanteita.
        if (v.getTunniste() == null) {
            throw new IllegalStateException("Viitteellä ei tunnistetta");
        }
        for (Viite viite : viitteet) {
            if (viite.getTunniste().equalsIgnoreCase(v.getTunniste())) {
                throw new IllegalStateException("Yritetty lisätä viite, vaikka sellainen oli jo");
            }
        }
        viitteet.add(v);
    }
    
    /**
     * Poistaa viitteen viitelistasta. Anna poistettavan viitteen tunniste.
     * @param tunniste Poistettavan viitteen tunniste.
     */
    public void remove(String tunniste) {
        for (Iterator<Viite> it = viitteet.iterator(); it.hasNext(); ) {
            Viite viite = it.next();
            if (viite.getTunniste().equalsIgnoreCase(tunniste)) {
                it.remove();
            }
        }
    }
    
    /**
     * Palauttaa viitteen listasta jonka tunniste vastaa parametria tunniste.
     * @param tunniste Haettavan viitteen tunniste.
     * @return Viite, jonka tunniste on tunniste, tai jos sellaista ei löydy listasta, null.
     */
    public Viite get(String tunniste) {
        for (Viite viite : viitteet) {
            if (viite.getTunniste().equalsIgnoreCase(tunniste)) {
                return viite;
            }
        }
        return null;
    }
   
    
    public String getNimi() {
        return nimi;
    }

    public ArrayList<Viite> getViitteet() {
        return viitteet;
    }
    
    

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int size() {
        return viitteet.size();
    }

    @Override
    public Iterator iterator() {
        return new ViiteIteraattori(this);
    }
    private class ViiteIteraattori implements Iterator<Viite> {
        private Viitelista lista;
        private int index;
        
        public ViiteIteraattori(Viitelista lista) {
            this.lista = lista;
            index = 0;
        }
        @Override
        public boolean hasNext() {
            if (lista.getViitteet() == null) return false;
            return lista.getViitteet().size() > index;
        }

        @Override
        public Viite next() {
            index++;
            return lista.getViitteet().get(index-1);
        }
        
    }
    
    
    
}
