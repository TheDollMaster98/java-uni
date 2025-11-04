import java.util.Scanner;

/**
 * Linguaggio farfallino
 * Nel linguaggio farfallino ciascuna vocale non accentata (vocale) viene
 * sostituita da una sequenza di tre caratteri vocale-f-vocale. Per esempio, la
 * vocale a viene sostituita dalla sequenza afa, la vocale e dalla sequenza efe
 * e così via. Se una vocale è maiuscola, anche la sequenza di tre caratteri che
 * sostituisce la vocale deve essere definita da caratteri maiuscoli (ad
 * esempio, la vocale A viene sostituita dalla sequenza AFA).
 * 
 * Scrivere un programma che:
 * 
 * legga da standard input un testo su più righe;
 * termini la lettura quando, premendo la combinazione di tasti Ctrl+D, viene
 * inserito da standard input l'indicatore End-Of-File (EOF);
 * ristampi il testo letto dopo averlo tradotto in linguaggio farfallino.
 * Oltre al metodo main(), devono essere definiti ed utilizzati almeno i
 * seguenti metodi:
 * 
 * public static boolean isVocale(char c) che restituisce true se c è una vocale
 * e false altrimenti
 * public static String farfallino(String s) che restituisce la stringa s
 * trasformata in farfallino e che usa il metodo isVocale() nel processo
 * Esempio d'esecuzione:
 * $ java Farfallino
 * Inserisci un testo su più righe (termina con CTRL+D):
 * Questo è un testo di prova
 * da trasformare IN ALFABETO FARFALLINO
 * Risultato:
 * Qufuefestofo èfè ufun tefestofo difi profovafa
 * dafa trafasfoformafarefe IFIN AFALFAFABEFETOFO FAFARFAFALLIFINOFO
 */
public class Farfallino {

    public static boolean isVocale(char c) {
        String ns = "AÀÁEÈÉIÌÍOÒÓUÙÚaàáeèéiìíoòóuùú";

        if (ns.indexOf(c) != -1)
            return true;

        return false;
    }

    public static String farfallino(String s) {
        StringBuilder sb = new StringBuilder();
        // in questo for ciclo i singoli char della stringa:
        for (char c : s.toCharArray()) {

            if (isVocale(c)) {
                sb.append(Character.toLowerCase(c) + "f" + Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
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

        System.out.print("Testo in linguaggio farfallino: " + "\n" + farfallino(ris.toString()));
    }
}

// 1 prima brutta (funziona):
// public class Farfallino {
// public static void main(String[] args) {
// System.out.println("Inserisci un testo su più righe (termina con Ctrl+Z +
// invio su windows):");

// Scanner s = new Scanner(System.in);

// StringBuilder ris = new StringBuilder(); // nuovo testo modificato
// String vocali = "AÀÁEÈÉIÌÍOÒÓUÙÚaàáeèéiìíoòóuùú";

// while (s.hasNextLine()) {
// // stampa la nuova linea:
// String linea = s.nextLine();
// StringBuilder rigaCorrente = new StringBuilder(linea); // StringBuilder con
// la riga

// for (int i = 0; i < linea.length(); i++) {

// if (vocali.indexOf(rigaCorrente.charAt(i)) >= 0) {
// ris.append(rigaCorrente.charAt(i)).append("f").append(rigaCorrente.charAt(i));
// } else {
// ris.append(rigaCorrente.charAt(i));
// }
// }

// ris.append("\n"); // a capo a fine riga

// }

// System.out.println("Risultato farfallino:");
// System.out.println(ris);
// }
// }
