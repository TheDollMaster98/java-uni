import java.util.Scanner;

/**
 * Minimo, massimo e valor medio (Array)
 * Scrivere un programma che riceva in input da riga di comando un intero n. Il
 * programma deve leggere da standard input una sequenza di n valori interi e
 * deve stampare a video il valore minimo, massimo e medio tra i valori letti.
 * 
 * Oltre al metodo main, devono essere definiti ed utilizzati almeno i seguenti
 * metodi:
 * 
 * public static int minimo(int[] in) che restituisce il minimo valore intero
 * presente in in.
 * public static int massimo(int[] in) che restituisce il massimo valore intero
 * presente in in.
 * public static float media(int[] in) che restituisce un valore reale pari alla
 * media aritmetica dei valori interi presenti in in.
 * Esempio d'esecuzione:
 * $ java MinMaxAvg 4
 * Inserisci i 4 numeri:
 * 1
 * 2
 * 3
 * 4
 * Minimo: 1
 * Massimo: 4
 * Valore medio: 2.5
 * 
 * $ java MinMaxAvg 3
 * Inserisci i 3 numeri:
 * -1
 * 10
 * 6
 * Minimo: -1
 * Massimo: 10
 * Valore medio: 5
 */
public class MinMaxAvg {

    /**
     * @param in
     * @return estituisce il minimo valore intero presente in in.
     */
    public static int minimo(int[] in) {
        int min = in[0];

        for (int i : in) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }

    /**
     * 
     * @param in restituisce il massimo valore intero presente in in.
     * @return
     */
    public static int massimo(int[] in) {
        int max = in[0];

        for (int i : in) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    /**
     * 
     * @param in
     * @return estituisce un valore reale pari alla media aritmetica dei valori
     *         interi presenti in in.
     */
    public static float media(int[] in) {
        int avg = 0;

        for (int i : in) {
            avg += i;
        }

        return avg / in.length;
    }

    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            throw new Exception("Devono esserci dei numeri!");
        }

        int n = Integer.parseInt(args[0]); // tengo conto di quanti argomenti prendo, in modo da salvarli poi nei valori
        Scanner s = new Scanner(System.in);
        int[] values = new int[n];

        System.out.println("Inserisci i " + n + " numeri:");

        for (int i = 0; i < n; i++) {
            values[i] = s.nextInt();
        }

        s.close();

        System.out.println("Minimo: " + minimo(values));
        System.out.println("Massimo: " + massimo(values));
        System.out.println("Valore medio: " + media(values));
    }
}
