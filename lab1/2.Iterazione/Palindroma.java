/**
 * Parola palindroma
 * Scrivere un programma che legga da riga di comando una stringa senza spazi e
 * stampi a video il messaggio Palindroma nel caso in cui la stringa letta sia
 * palindroma e Non palindroma altrimenti.
 * 
 * Esempio d'esecuzione:
 * $ java Palindroma anna
 * Palindroma
 * 
 * $ java Palindroma anni
 * Non palindroma
 * 
 * $ java Palindroma osso
 * Palindroma
 */
public class Palindroma {
    public static void main(String[] args) {
        String s = args[0].toLowerCase().trim();
        int n = s.length() / 2;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != s.charAt(n - i - 1)) {
                System.out.println("Non Palindroma");
                return;
            }
        }

        System.out.println("Palindroma");
    }
}
