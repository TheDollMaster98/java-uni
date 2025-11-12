import java.util.ArrayList;
import java.util.Scanner;

/**
 * Minimo, massimo e valor medio (ArrayList)
 * Scrivere un programma che riceva in input da standard input una sequenza di
 * lunghezza arbitraria di valori interi e deve stampare a video il valore
 * minimo, massimo e medio tra i valori letti.
 * 
 * Oltre al metodo main, devono essere definiti ed utilizzati almeno i seguenti
 * metodi:
 * 
 * public static int minimo(ArrayList<Integer> in) che restituisce il minimo
 * valore intero presente in in.
 * public static int massimo(ArrayList<Integer> in) che restituisce il massimo
 * valore intero presente in in.
 * public static float media(ArrayList<Integer> in) che restituisce un valore
 * reale pari alla media aritmetica dei valori interi presenti in in.
 * Esempio d'esecuzione:
 * $ java MinMaxAvg
 * Inserisci i numeri (Ctrl+D per terminare):
 * 1
 * 2
 * 3
 * 4
 * Minimo: 1
 * Massimo: 4
 * Valore medio: 2.5
 * 
 * $ java MinMaxAvg
 * Inserisci i numeri (Ctrl+D per terminare):
 * -1
 * 10
 * 6
 * Minimo: -1
 * Massimo: 10
 * Valore medio: 5
 */
public class MinMaxAvg2 {
    /**
     * @param in
     * @return estituisce il minimo valore intero presente in in.
     */
    public static int minimo(ArrayList<Integer> in) {
        int min = in.get(0);

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
    public static int massimo(ArrayList<Integer> in) {
        int max = in.get(0);

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
    public static float media(ArrayList<Integer> in) {
        int avg = 0;

        for (int i : in) {
            avg += i;
        }

        return avg / in.size();
    }

    public static void main(String[] args) throws Exception {

        Scanner s = new Scanner(System.in);
        // info sugli arraylist:
        // https://www.codecademy.com/learn/learn-java/modules/learn-java-arrays-and-arraylists/cheatsheet
        ArrayList<Integer> values = new ArrayList<Integer>();

        System.out.println("Inserisci i numeri (Ctrl+D o Ctrl+Z per terminare):");

        while (s.hasNextLine()) {
            String linea = s.nextLine();
            values.add(Integer.parseInt(linea));
        }
        s.close();

        System.out.println("Minimo: " + minimo(values));
        System.out.println("Massimo: " + massimo(values));
        System.out.println("Valore medio: " + media(values));
    }

}
