import java.util.ArrayList;
import java.util.List;

/**
 * Numeri abbondanti
 * Definizione: Un numero naturale è abbondante se è inferiore alla somma dei
 * suoi divisori propri (per esempio, 12 è abbondante perché la somma dei suoi
 * divisori propri (1, 2, 3, 4, 6) è 16).
 * 
 * Scrivere un programma che legga da riga di comando un numero intero soglia e
 * stampi tutti i numeri abbondanti inferiori a soglia. Se soglia <= 0 il
 * programma deve stampare La soglia inserita non è positiva.
 * 
 * Oltre al metodo main(), il programma deve specificare, implementare e
 * utilizzare le seguenti procedure parziali:
 * 
 * public static List<Integer> elencoDivisoriPropri(int n) che restituisce una
 * List di Integer contenente tutti i divisori propri di n;
 * 
 * public static int sommaDivisoriPropri(int n) che restituisce la somma dei
 * divisori propri di n. Se n non ha divisori propri la funzione restituisce 0;
 * 
 * public static boolean abbondante(int n) che restituisce true se n è
 * abbondante, false altrimenti;
 * 
 * public static List<Integer> numeriAbbondanti(int limite) che restituisce
 * tutti i numeri abbondanti inferiori a limite;
 * 
 * public static void stampaNumeriAbbondanti(List<Integer> numeri) che stampa i
 * numeri abbondanti trovati su standard output;
 * 
 * Esempio d'esecuzione:
 * $ java Abbondanti 20
 * Numeri abbondanti: 12 18
 * 
 * $ java Abbondanti 30
 * Numeri abbondanti: 12 18 20 24
 * 
 * $ java Abbondanti -3
 * La soglia inserita non è positiva
 */
public class Abbondanti {
    /**
     * OVERVIEW: restituisce una List di Integer contenente tutti i divisori propri
     * di n;
     * 
     * @param n
     * @return
     */
    public static List<Integer> elencoDivisoriPropri(int n) {
        List<Integer> divisori = new ArrayList<Integer>();
        // per definizione 1 è sempre divisore proprio (se n > 1)
        if (n > 1) {
            divisori.add(1);
        }

        // parto da 2 perché 1 non viene contanto, poi mi fermo a metà perché non ci
        // sono più divisori poi
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                divisori.add(i);
            }
        }

        return divisori;

    }

    /**
     * OVERVIEW: restituisce la somma dei
     * divisori propri di n. Se n non ha divisori propri la funzione restituisce 0;
     * 
     * @param n
     * @return
     */
    public static int sommaDivisoriPropri(int n) {
        // uso elencoDivisoriPropri visto che ho già i divisori e non ripeto nulla
        List<Integer> divisori = elencoDivisoriPropri(n);

        int sum = 0;
        for (int d : divisori) {
            sum += d;
        }

        return sum;
    }

    /**
     * OVERVIEW: restituisce true se n è abbondante, false altrimenti;
     * 
     * @param n
     * @return
     */
    public static boolean abbondante(int n) {

        if (n <= 0) {
            return false;
        }

        int sum = sommaDivisoriPropri(n);

        // un numero è abbondante se è inferiore alla somma dei suoi divisori propri
        return sum > n;
    }

    /**
     * OVERVIEW: restituisce tutti i numeri abbondanti inferiori a limite;
     * 
     * @param limite
     * @return
     */
    public static List<Integer> numeriAbbondanti(int limite) {
        List<Integer> result = new ArrayList<Integer>();

        // vogliamo tutti i numeri abbondanti STRICTLY inferiori a limite
        for (int i = 1; i < limite; i++) {
            // riuso lei per cercare n abbondanti
            if (abbondante(i)) {
                result.add(i);
            }
        }

        return result;
    }

    /**
     * OVERVIEW: stampa i numeri abbondanti trovati su standard output;
     * 
     * @param numeri
     */
    public static void stampaNumeriAbbondanti(List<Integer> numeri) {

        if (numeri.size() == 0) {
            System.out.print("Nessun numero abbondante ");
        } else {
            System.out.print("Numeri abbondanti: ");
        }

        for (int i = 0; i < numeri.size(); i++) {
            System.out.print(numeri.get(i));
            if (i < numeri.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    /**
     * OVERVIEW: Numeri abbondanti
     * Definizione: Un numero naturale è abbondante se è inferiore alla somma dei
     * suoi divisori propri (per esempio, 12 è abbondante perché la somma dei suoi
     * divisori propri (1, 2, 3, 4, 6) è 16).
     * 
     * Scrivere un programma che legga da riga di comando un numero intero soglia e
     * stampi tutti i numeri abbondanti inferiori a soglia. Se soglia <= 0 il
     * programma deve stampare La soglia inserita non è positiva.
     * 
     * @param args
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Nessuna soglia specificata.");
            return;
        }

        int soglia = Integer.parseInt(args[0]);

        if (soglia <= 0) {
            System.out.println("La soglia inserita non è positiva");
            return;
        }

        List<Integer> abbondanti = numeriAbbondanti(soglia);
        stampaNumeriAbbondanti(abbondanti);
    }
}
