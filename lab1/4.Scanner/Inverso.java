import java.util.Scanner;

/**
 * Testo invertito
 * Scrivere un programma che legga da standard input un testo formato da un
 * numero variabile di righe, terminando la lettura quando viene premuta la
 * combinazione dei tasti ctrl+D, e lo ristampi dall’ultimo carattere al primo.
 * 
 * Oltre al metodo main(), devono essere definiti ed utilizzati almeno i
 * seguenti metodi:
 * 
 * public static String inverso(String s) che restituisce l'inverso della
 * stringa s;
 * Esempio d'esecuzione:
 * $ java Inverso
 * Inserisci un testo su più righe (termina con riga vuota):
 * Testo di prova
 * disposto su due righe
 * 
 * Testo invertito:
 * ehgir eud us otsopsid
 * avorp id otseT
 */
public class Inverso {

    public static String inverso(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Inserisci un testo su più righe (termina con Ctrl+Z + invio su windows):");

        Scanner scanner = new Scanner(System.in);
        StringBuilder ris = new StringBuilder(); // nuovo testo modificato

        // scanner che prende ogni riga:
        while (scanner.hasNextLine()) {
            ris.append(scanner.nextLine() + "\n"); // appendo ogni riga
        }
        scanner.close();

        System.out.println("Testo invertito:" + inverso(ris.toString()));
    }
}
