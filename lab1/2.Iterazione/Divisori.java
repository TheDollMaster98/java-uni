/**
 * Divisori
 * Scrivere un programma che legga da riga di comando un numero intero n e
 * stampi a video i divisori propri del numero letto, ovvero tutti i suoi
 * divisori escluso il numero stesso. Ad esempio, i divisori del numero 12 sono:
 * 1, 2, 3, 4, 6, 12; quindi i divisori propri di 12 sono: 1, 2, 3, 4, 6.
 * 
 * Esempio d'esecuzione:
 * $ java Divisori 6
 * Divisori di 6: 1 2 3
 * 
 * $ java Divisori 10
 * Divisori di 10: 1 2 5
 */
public class Divisori {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Errore, max 1 numero");
            return;
        }

        int n = Integer.parseInt(args[0]);

        System.out.print("Divisori di " + n + ": ");

        for (int i = 1; i <= n / 2; i++)
            if (n % i == 0)
                System.out.print(i + " ");

        System.out.println();
    }
}
