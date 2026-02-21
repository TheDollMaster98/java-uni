public class Giocattolo extends Sorpresa {

    final String descrizione;

    public Giocattolo(String nome, String descrizione) {

        super(nome, getCostoGioco(descrizione));
        this.descrizione = descrizione;
    }

    public static double getCostoGioco(String descrizione) {
        return descrizione.split(" ").length;
    }

    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public String toString() {
        return String.format("Giocattolo %s con descrizione %s e costo %.2f", getNome(), getDescrizione(), getCosto());
    }
}