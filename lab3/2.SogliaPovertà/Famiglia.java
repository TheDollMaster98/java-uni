import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Famiglia {
    // OVERVIEW: modella una Famiglia con numero di persone e reddito complessivo
    private double reddito;
    private int dimensione;

    // inizializza un nuovo oggetto di tipo Famiglia e lancia una
    // InputMismatchException se dimensione è non-positiva;
    public Famiglia(double reddito, int dimensione) throws InputMismatchException {
        if (dimensione <= 0) {
            throw new InputMismatchException("Dimensione non valida: deve essere positiva");
        }

        this.reddito = reddito;
        this.dimensione = dimensione;
    }

    // restituisce true se la somma tra costoCasa e costoCibo (moltiplicato per la
    // dimensione della famiglia) è maggiore della metà del reddito della famiglia
    // (costoCibo è il costo medio mensile del cibo per ogni individuo, mentre
    // costoCasa è unico mensile per la famiglia); Il metodo lancia una
    // InputMismatchException se costoCasa o costoCibo non sono positivi
    public boolean sottoSogliaPoverta(double costoCasa, double costoCibo) throws InputMismatchException {

        if (costoCasa <= 0 || costoCibo <= 0) {
            throw new InputMismatchException("I costi devono essere positivi");
        }

        double speseTotali = costoCasa + (costoCibo * this.dimensione);
        double metaReddito = this.reddito / 2;

        return speseTotali > metaReddito;
    }

    // ridefinisce il metodo toString del supertipo Object e restituisce una stringa
    // contenente le informazioni della famiglia;
    @Override
    public String toString() {
        /*
         * famiglie sotto la soglia di povertà:
         * Famiglia 1: 4 persone con reddito complessivo di 2000.0
         * Famiglia 2: 5 persone con reddito complessivo di 2500.0
         */
        return this.dimensione + " persone con reddito complessivo di: " + this.reddito;
    }

    // deve leggere da riga di comando due numeri che definiscono rispettivamente il
    // costo medio di vitto e di alloggio. Poi, da standard input dovrà leggere una
    // serie di righe di testo costituite da reddito e dimensione di diverse
    // famiglie, fermando la lettura quando l'utente preme la combinazione dei tasti
    // CTRL+D. Dopo la lettura, visualizzare le famiglie che sono sotto la soglia di
    // povertà.
    public static void main(String[] args) {
        double costoCasa = Double.parseDouble(args[0]);
        double costoCibo = Double.parseDouble(args[1]);

        ArrayList<Famiglia> famigliePovere = new ArrayList<Famiglia>();

        Scanner scanner = new Scanner(System.in);
        System.out
                .println("Inserisci il reddito e la dimensione di una famiglia (Ctrl+D per terminare la lettura)");
        while (scanner.hasNext()) {
            double reddito = scanner.nextDouble();
            int persone = scanner.nextInt();

            Famiglia famiglia = new Famiglia(reddito, persone);

            if (famiglia.sottoSogliaPoverta(costoCasa, costoCibo)) {
                famigliePovere.add(famiglia);
            }

        }

        scanner.close();

        System.out.println();
        System.out.println(famigliePovere.size() + " famiglie sotto la soglia di povertà:");

        for (int i = 0; i < famigliePovere.size(); i++)
            System.out.println("Famiglia " + (i + 1) + ": " + famigliePovere.get(i));

    }

}
