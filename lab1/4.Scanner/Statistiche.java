import java.util.Scanner;

/**
 * Statistiche testo
 * Scrivere un programma che:
 * 
 * legga da standard input un testo su più righe;
 * termini la lettura quando, premendo la combinazione di tasti Ctrl+D, viene
 * inserito da standard input l'indicatore End-Of-File (EOF);
 * stampi a video le seguenti statistiche relative al testo letto:
 * il numero di linee
 * il numero di parole presenti nel testo;
 * la lunghezza media delle parole presenti nel testo.
 * Nota: una parola è una sequenza di caratteri consecutivi. I caratteri di
 * spaziatura intervallano parole diverse.
 * 
 * Esempio d'esecuzione:
 * $ java Statistiche
 * Inserisci un testo su più righe (termina con Ctrl+D):
 * testo di prova
 * su cui provare le statistiche
 * Statistiche:
 * Numero linee: 2
 * Numero parole: 8
 * Lunghezza media: 4.625
 */
public class Statistiche {

    public static void main(String[] args) {

        System.out.println("Inserisci un testo su più righe (termina con Ctrl+Z + invio su windows):");
        // scanner per leggere da riga di comando:
        Scanner s = new Scanner(System.in);

        // System.out.println(s);

        int linee = 0;
        int parole = 0;
        double media = 0;
        // String strArray[] = [];

        // finché c'è una nuova linea continua a scrivere nella stringa "a"
        while (s.hasNextLine()) {
            // stampa la nuova linea:
            String a = s.nextLine();
            // System.out.println(a);

            linee++;
            String strArray[] = a.split(" ");
            parole += strArray.length;
            // int tempMedia = 0;

            for (int i = 0; i < strArray.length; i++) {
                media += strArray[i].length();
                // System.out.println("media = " + strArray[i].length());
            }

        }

        media /= parole;
        System.out.println("Statistiche:\r\n" + //
                "- Numero linee: " + linee + "\r\n" + //
                "- Numero parole: " + parole + "\r\n" + //
                "- Lunghezza media: " + media);
    }

}
