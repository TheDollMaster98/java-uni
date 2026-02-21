/**
 * Per gli eventi di tipo Cerimonia, che possono essere di apertura o di
 * chiusura, la durata è di 60 minuti fissi.
 */

public class Cerimonia extends Evento {
    private final String tipo;
    private static final int DURATA = 60;
    private static final String APERTURA = "apertura";
    private static final String CHIUSURA = "chiusura";

    public Cerimonia(String nome, String tipo) {
        super(nome, DURATA);
        if (!tipo.equals(APERTURA) && !tipo.equals(CHIUSURA)) {
            throw new IllegalArgumentException("Tipo di cerimonia non valido: " + tipo);
        }
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    // Cerimonia apertura nome: Apertura2026 durata: 60
    @Override
    public String toString() {
        return "Cerimonia " + tipo + " nome: " + getNome() + " durata: " + getDurata();
    }

}
