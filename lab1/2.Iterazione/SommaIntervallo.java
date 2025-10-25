/**
 * Scrivere un programma che legga da riga di comando due numeri interi a e b e
 * stampi a video la somma dei numeri pari compresi tra a e b (estremi esclusi).
 * 
 * Esempio d'esecuzione:
 * $ java SommaIntervallo 1 9
 * Somma = 20
 */
public class SommaIntervallo {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Errore: inserisci 2 numeri");
            return;
        }

        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int SommaIntervallo = 0;

        for (int i = a; i < b; i++) {
            if (i % 2 == 0) {
                SommaIntervallo += i;
            }

        }

        System.out.println("Somma = " + SommaIntervallo);
    }

}
