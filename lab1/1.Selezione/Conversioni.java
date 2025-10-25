/**
 * Scrivere un programma che esegua la conversione temporale specificata da riga
 * di comando. Permettere la conversione da e verso secondi, minuti, ore,
 * giorni, mesi e anni.
 * 
 * Esempio d'esecuzione:
 * $ java Conversioni 7200 minuti in giorni
 * 7200 minuti corrispondono a 5 giorni
 * 
 * $ java Conversioni 3618 secondi in ore
 * 3618 secondi corrispondono a 1.005 ore
 */
public class Conversioni {
    public static void main(String[] args) {

        double result = Double.parseDouble(args[0]); // double per permettere risultati con virgola

        String t_1 = args[1];
        // args[2] sarà sempre "in"
        String t_2 = args[3];

        switch (t_1) {
            case "minuti":
                result *= 60;
                break;
            case "ore":
                result *= 60 * 60;
                break;
            case "giorni":
                result *= 60 * 60 * 24;
                break;
            case "mesi":
                result *= 60 * 60 * 24 * 30;
                break;
            case "anni":
                result *= 60 * 60 * 24 * 365;
                break;
        }

        switch (t_2) {
            case "minuti":
                result /= 60;
                break;
            case "ore":
                result /= 60 * 60;
                break;
            case "giorni":
                result /= 60 * 60 * 24;
                break;
            case "mesi":
                result /= 60 * 60 * 24 * 30;
                break;
            case "anni":
                result /= 60 * 60 * 24 * 365;
                break;
        }

        System.out.println(args[0] + " " + args[1] + " corrispondono a " + result + " " + args[3]);
    }
}