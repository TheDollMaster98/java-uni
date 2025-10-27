/**
 * Numeri primi
 * Scrivere un programma che legga da riga di comando un numero intero soglia e
 * stampi tutti i numeri primi inferiori a soglia. Se soglia <= 0 il programma
 * deve stampare La soglia inserita non è positiva.
 * 
 * Oltre al metodo main(), il programma deve definire e utilizzare i seguenti
 * metodi:
 * 
 * public static boolean primo(int n) che restituisce true se n è primo e false
 * altrimenti;
 * Esempio d'esecuzione:
 * $ java Primi -3
 * La soglia inserita non è positiva
 * 
 * $ java Primi 5
 * Numeri primi inferiori a 5
 * 2 3
 * 
 * $ java Primi 12
 * Numeri primi inferiori a 12
 * 2 3 5 7 11
 */

public class Primi {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Massimo un valore");
            return;
        }

        int soglia = Integer.parseInt(args[0]);

        if (soglia <= 0) {
            System.out.println("La soglia inserita non è positiva");
            return;
        }

        System.out.println("Numeri primi inferiori a " + soglia);

        // Controlla ogni numero da 2 fino a soglia-1
        for (int i = 2; i < soglia; i++) {
            if (primo(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public static boolean primo(int n) {
        // 1 non è primo per definizione
        if (n <= 1) {
            return false;
        }

        // Controlla se n è divisibile per qualche numero da 2 fino alla radice quadrata
        // di n
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
