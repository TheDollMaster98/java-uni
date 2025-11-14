import java.util.Scanner;

/**
 * Scrivere un programma che:
 * 
 * legga da standard input un testo su più righe;
 * termini la lettura quando viene inserita da standard input la combinazione
 * dei tasti ctrl+D;
 * ristampi il testo letto (riga vuota esclusa) sostituendo tutte le vocali con
 * delle u.
 * Esempio d'esecuzione:
 * $ java Garibaldi
 * Inserisci un testo su più righe (termina con riga vuota):
 * Garibaldi fu ferito
 * fu ferito in una gamba,
 * Garibaldi che comanda
 * che comanda i bersaglier
 * 
 * Risultato trasformazione:
 * Gurubuldu fu furutu
 * fu furutu un unu gumbu,
 * Gurubuldu chu cumundu
 * chu cumundu u bursugluur
 */
public class Garibaldi {
    public static void main(String[] args) {

        System.out.println("Inserisci un testo su più righe (termina con Ctrl+Z + invio su windows):");

        Scanner s = new Scanner(System.in);
        /**
         * StringBuilder è una classe mutable per costruire/modificare stringhe in modo
         * efficiente. A differenza di String, che è immutabile, StringBuilder permette
         * di cambiare il contenuto senza creare nuove istanze ad ogni modifica.
         */
        StringBuilder ris = new StringBuilder(); // nuovo testo modificato
        final String vocali = "aeioAEIO";

        while (s.hasNextLine()) {
            // stampa la nuova linea:
            String linea = s.nextLine();
            StringBuilder rigaCorrente = new StringBuilder(linea); // StringBuilder con la riga

            for (int i = 0; i < linea.length(); i++) {
                /*
                 * possiamo usare: "if (vocali.contains(String.valueOf(a.charAt(i))))" ma è
                 * meno efficiente e più lungo.
                 * "vocali.indexOf(c)"" cerca il carattere c nella stringa vocali e restituisce
                 * l'indice (0-based) della prima occorrenza se lo trova, oppure -1 se non lo
                 * trova.
                 * Quindi vocali.indexOf(c) >= 0 significa "il carattere c è presente in vocali"
                 * (vero) oppure no (falso).
                 */
                if (vocali.indexOf(rigaCorrente.charAt(i)) >= 0) {
                    rigaCorrente.setCharAt(i, 'u');
                }
            }

            ris.append(rigaCorrente).append("\n"); // a capo a fine riga

        }

        System.out.println("Risultato trasformazione:");
        System.out.println(ris);

    }
}