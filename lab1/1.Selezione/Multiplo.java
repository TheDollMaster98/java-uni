/**
 * Scrivere un programma che legga da riga di comando due numeri interi e
 * verifichi se il primo numero è multiplo del secondo.
 * 
 * Esempio d'esecuzione:
 * $ java Multiplo 15 10
 * 15 non è multiplo di 10
 * 
 * $ java Multiplo 20 2
 * 20 è multiplo di 2
 */

public class Multiplo {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);

        if (a % b == 0) {
            System.out.println(a + " è multiplo di " + b);
        } else {
            System.out.println(a + " non è multiplo di " + b);
        }
    }
}
