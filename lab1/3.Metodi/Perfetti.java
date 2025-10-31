/**
 * Definizione: Un numero naturale è perfetto se è uguale alla somma dei suoi
 * divisori propri (per esempio, 6 è perfetto perché 6 = 1 + 2 + 3).
 * 
 * Scrivere un programma che legga da riga di comando un numero intero n e
 * stampi se n è perfetto oppure no.
 * 
 * Oltre al metodo main(), il programma deve definire e utilizzare i seguenti
 * metodi:
 * 
 * public static int sommaDivisoriPropri(int n) che restituisce la somma dei
 * divisori propri di n. Se n non ha divisori propri il metodo restituisce 0;
 * public static boolean perfetto(int n) che restituisce true se n è perfetto e
 * false altrimenti, utilizzando il metodo sommaDivisoriPropri().
 * Esempio d'esecuzione:
 * $ java Perfetti 0
 * 0 non è perfetto
 * 
 * $ java Perfetti 1
 * 1 non è perfetto
 * 
 * $ java Perfetti 6
 * 6 è perfetto
 * 
 * $ java Perfetti 28
 * 28 è perfetto
 */
public class Perfetti {

    public static void main(String[] args) {
        int num = Integer.parseInt(args[0]);
        if (perfetto(num)) {
            System.out.println(num + " è perfetto");
        } else {
            System.out.println(num + " non è perfetto");
        }
    }

    /**
     * public static boolean perfetto(int n) che restituisce true se n è perfetto
     * é false altrimenti, utilizzando il metodo sommaDivisoriPropri().
     * Nella formula di Euclide-Eulero:
     * 2^p − 1 deve essere primo
     * dove p è un numero primo
     * 
     * @param n
     * @return
     */
    public static boolean perfetto(int n) {
        // Un numero perfetto deve essere positivo
        if (n <= 0) {
            return false;
        }
        // Un numero è perfetto se è uguale alla somma dei suoi divisori propri
        return n == sommaDivisoriPropri(n);
    }

    /**
     * public static int sommaDivisoriPropri(int n) che restituisce la somma dei
     * divisori propri di n. Se n non ha divisori propri il metodo restituisce 0;
     * 
     * @param n
     * @return
     */
    public static int sommaDivisoriPropri(int n) {
        int somma = 0;
        // Cerchiamo i divisori propri da 1 fino a n-1
        for (int i = 1; i < n; i++) {
            // Se i è un divisore proprio (divide n senza resto)
            if (n % i == 0) {
                somma += i;
            }
        }
        return somma;
    }

}
