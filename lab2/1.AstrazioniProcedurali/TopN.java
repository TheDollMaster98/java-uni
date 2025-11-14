import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * TopN
 * Scrivere un programma che trovi gli n numeri più grandi, dove n è inserito da
 * riga di comando, tra una serie di numeri inseriti da standard input.
 * 
 * A tal fine specificare, implementare e utilizzare le seguenti procedure
 * parziali:
 * 
 * private static int[] readIntArray() che legge da standard input una serie di
 * almeno n numeri interi diversi tra loro, fino a quando con ctrl+D non viene
 * terminata la lettura
 * 
 * public static int[] highest(int[] numbers, int n) che calcola e restituisce
 * un array con gli n interi più alti tra quelli inseriti a standard input
 * 
 * private static void printArray(int[] numbers) che stampa a standard output
 * l'array ottenuto dalla procedura precedente.
 * 
 * Esempio d'esecuzione:
 * $java TopN 2
 * Inserisci almeno 2 numeri interi diversi tra loro (CTRL+D per terminare):
 * 5
 * 2
 * 7
 * 9
 * Numeri più alti: [ 9, 7 ]
 * 
 * $java TopN 1
 * Inserisci almeno 1 numeri interi diversi tra loro (CTRL+D per terminare):
 * 734
 * 8
 * 3
 * 22
 * 2000
 * Numeri più alti: [ 2000 ]
 * 
 * In seguito, riflettete sulle seguenti questioni:
 * 
 * È possibile rendere ciascuna procedura totale? Senza l'uso di eccezioni?
 */
public class TopN {
    /**
     * 
     * @return legge da standard input una serie di
     *         almeno n numeri interi diversi tra loro, fino a quando con
     *         ctrl+D/ctrl+Z non viene terminata la lettura
     */
    private static int[] readIntArray() {
        // come prima cosa leggo e salvo sottoforma di stringa:
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> lista = new ArrayList<>();

        while (scanner.hasNextInt()) {
            int num = scanner.nextInt();
            if (!lista.contains(num)) {
                lista.add(num);
            }

        }

        scanner.close();

        int[] nums = new int[lista.size()]; // di default è lunga quanto la lista

        // modo brutto per convertire da lista ad array:
        for (int i = 0; i < lista.size(); i++) {
            nums[i] = lista.get(i);
        }

        return nums;
    }

    /**
     * 
     * @param numbers
     * @param n
     * @return calcola e restituisce
     *         un array con gli n interi più alti tra quelli inseriti a standard
     *         input
     */
    public static int[] highest(int[] numbers, int n) {

        // Copio l'array per non modificarlo
        int[] newArray = Arrays.copyOf(numbers, numbers.length);

        // Ordino l'array in ordine crescente
        Arrays.sort(newArray);

        // Prendo gli ultimi n (i più grandi) visto che ho usato sort:
        int[] result = new int[n];
        int index = newArray.length - 1;

        for (int i = 0; i < n; i++) {
            result[i] = newArray[index - i];
        }

        return result;
    }

    /**
     * 
     * @param numbers stampa a standard output l'array ottenuto dalla procedura
     *                precedente.
     */
    private static void printArray(int[] numbers) {
        System.out.print("I " + numbers.length + "numeri più alti: [ ");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i]);
            if (i < numbers.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" ]");
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        System.out.println("Inserisci almeno 2 numeri interi diversi tra loro (CTRL+D/Z per terminare):");
        int[] reader = readIntArray();
        int[] max = highest(reader, n);
        printArray(max);
    }
}
