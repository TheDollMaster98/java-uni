import java.util.ArrayList;

/**
 * Somma unici
 * Scrivere un programma che legga da standard input una sequenza di valori e
 * stampi a video la somma dei valori letti che rappresentano numeri interi e
 * che compaiono nella sequenza una sola volta.
 * 
 * Oltre al metodo main(), deve essere definito ed utilizzato il seguente
 * metodo:
 * 
 * public static int occorrenze(ArrayList<Integer> numeri, int n) che
 * restituisce un valore int pari al numero di occorrenze di n in numeri.
 * Esempio d'esecuzione:
 * $ java SommaUnici 1 2 % 4 3 2 1 5
 * 12
 * 
 * $ java SommaUnici 4 3 5 4 2 2 3 2
 * 5
 * 
 * $ java SommaUnici 1 2 sarà zero 1 2
 * 0
 * 
 * $ java SommaUnici 1 2 3 2 2 2
 * 4
 * 
 * $ java SommaUnici che 10 4 7 12 4 12 sfortuna
 * 17
 */

public class SommaUnici {

    /**
     * Restituisce il numero di volte in cui il valore n compare
     * all'interno della lista numeri.
     * 
     * @param numeri lista di interi
     * @param n      numero da cercare
     * @return numero di occorrenze di n
     */
    public static int occorrenze(ArrayList<Integer> numeri, int n) {
        int count = 0;

        // Scorro tutta la lista e conto quante volte trovo n
        for (int i = 0; i < numeri.size(); i++) {
            if (numeri.get(i) == n) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        ArrayList<Integer> numeri = new ArrayList<Integer>();
        int somma = 0;

        // Lettura degli args:
        for (String s : args) {
            try {
                // se è un numero, lo converto in int:
                int n = Integer.parseInt(s);
                numeri.add(n);
            } catch (NumberFormatException e) {
                // errori blabla
            }
        }

        // Sommo solo i numeri che compaiono una volta sola:
        for (int n : numeri) {
            // controllo se esiste:
            if (occorrenze(numeri, n) == 1) {
                somma += n;
            }
        }

        System.out.println(somma);
    }
}
