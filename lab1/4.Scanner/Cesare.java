import java.util.Scanner;

/**
 * Il cifrario di Cesare
 * Giulio Cesare usava per le sue corrispondenze riservate un codice di
 * sostituzione molto semplice, nel quale la lettera chiara viene sostituita
 * dalla lettera che la segue di tre posti nell’alfabeto: la lettera A è
 * sostituita dalla D, la B dalla E, e così via fino alle ultime lettere che
 * sono cifrate con le prime.
 * 
 * Chiaro: A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
 * 
 * Cifrato: D E F G H I J K L M N O P Q R S T U V W X Y Z A B C
 * 
 * Più in generale si dice cifrario di Cesare un codice nel quale ogni lettera
 * del messaggio chiaro viene spostata di un numero fisso k di posti (non
 * necessariamente tre), dove k è detto chiave di cifratura.
 * 
 * Scrivere un programma che:
 * 
 * legga da riga di comando un numero intero k (la chiave di cifratura);
 * legga da standard input un messaggio in chiaro su più righe, terminando la
 * lettura quando, premendo la combinazione di tasti Ctrl+D, viene inserito da
 * standard input l'indicatore End-Of-File (EOF);
 * stampi il messaggio cifrato (ottenuto con chiave di cifratura k)
 * corrispondente al messaggio in chiaro letto.
 * Oltre al metodo main(), il programma deve definire e utilizzare i seguenti
 * metodi:
 * 
 * public static string leggiTesto() che legge da standard input un testo su più
 * righe terminato dall'indicatore EOF, restituendo un valore string in cui è
 * memorizzato il testo letto;
 * 
 * public static char cifraCarattere(char c, int chiave) che restituisce il
 * carattere cifrato partendo da c, ottenuto con chiave di cifratura chiave.Il
 * valore cifrato deve essere minuscolo se c è minuscolo e maiuscolo se c è
 * maiuscolo;
 * public static String CifraTesto(String s, int chiave) che restituisce una
 * String ottenuta cifrando ogni carattere di s tramite la funzione
 * CifraCarattere().
 * 
 * Esempio d'esecuzione:
 * $ java Cesare 1
 * Inserisci un testo su più righe (termina con CTRL D):
 * Testo di esempio
 * diviso su righe diverse
 * Testo cifrato:
 * Uftup ej ftfnqjp
 * ejwjtp tv sjhif ejwfstf
 * 
 * $ java Cesare -2
 * Inserisci un testo su più righe (termina con CTRL D):
 * AbC
 * 
 * dEf
 * Testo cifrato:
 * YzA
 * 
 * bCd
 */
public class Cesare {

    /**
     * Legge il testo da cifrare dallo standard input.
     * Usa StringBuilder per efficienza nella concatenazione.
     * 
     * @return Una stringa contenente tutto il testo letto, con le righe separate da
     *         \n
     */
    public static String leggiTesto() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder ris = new StringBuilder();

        while (scanner.hasNextLine()) {
            ris.append(scanner.nextLine()).append("\n");
        }
        scanner.close();

        return ris.toString();
    }

    /**
     * public static char cifraCarattere(char c, int chiave) che restituisce il
     * carattere cifrato partendo da c, ottenuto con chiave di cifratura chiave.
     * Il valore cifrato deve essere minuscolo se c è minuscolo e maiuscolo se c è
     * maiuscolo;
     * public static String CifraTesto(String s, int chiave) che restituisce una
     * String ottenuta cifrando ogni carattere di s tramite la funzione
     * CifraCarattere().
     * 
     * @param c
     * @param chiave
     * @return
     */

    /**
     * Cifra un singolo carattere usando il cifrario di Cesare.
     * Mantiene maiuscole e minuscole, lascia invariati i caratteri non alfabetici.
     * 
     * @param c      Il carattere da cifrare
     * @param chiave Di quante posizioni spostare il carattere (può essere negativa)
     * @return Il carattere cifrato
     */
    public static char cifraCarattere(char c, int chiave) {
        // Alfabeto ha 26 lettere
        int range = 'Z' - 'A' + 1;

        // Normalizza la chiave nel range [0, 25]
        int clipped = chiave % range;
        if (clipped < 0) {
            clipped += range;
        }

        // Determina se è minuscola o maiuscola
        char base;
        if (c >= 'a' && c <= 'z') {
            base = 'a';
        } else if (c >= 'A' && c <= 'Z') {
            base = 'A';
        } else {
            return c; // Non è una lettera, ritorna invariato
        }

        // Formula: (posizione_lettera + spostamento) % 26 + carattere_base
        return (char) (((c - base + clipped) % range) + base);
    }

    /**
     * Cifra una stringa intera applicando il cifrario di Cesare.
     * 
     * @param s      La stringa da cifrare
     * @param chiave La chiave di cifratura
     * @return La stringa cifrata
     */
    public static String CifraTesto(String s, int chiave) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            res.append(cifraCarattere(s.charAt(i), chiave));
        }

        return res.toString();
    }

    public static void main(String[] args) {
        // Controllo argomenti
        if (args.length != 1) {
            System.out.println("Uso: java Cesare <chiave>");
            System.out.println("Esempio: java Cesare 3");
            return;
        }

        // Parsing della chiave con gestione errori
        int chiave;
        try {
            chiave = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Errore: la chiave deve essere un numero intero");
            return;
        }

        System.out.println("Inserisci un testo su più righe (termina con CTRL D):");
        String testo = leggiTesto();

        String testoCifrato = CifraTesto(testo, chiave);

        System.out.println("Testo cifrato:");
        System.out.println(testoCifrato);
    }
}
