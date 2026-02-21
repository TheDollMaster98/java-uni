/**
 * Per gli eventi di tipo Gara, definiti anche dal numero di atleti che vi
 * partecipano, la durata è di 5 min per ciascun atleta.
 */

public class Gara extends Evento {
    private final int numAtleti;
    private static final int DURATA = 5;

    public Gara(String nome, int numAtleti) {
        super(nome, numAtleti * DURATA);
        this.numAtleti = numAtleti;
    }

    public int getNumAtleti() {
        return numAtleti;
    }

    // Gara nome: Slittino1 durata: 75 numAtleti: 15
    @Override
    public String toString() {
        return "Gara nome: " + getNome() + " durata: " + getDurata() + " con " + numAtleti + " atleti";
    }

}
