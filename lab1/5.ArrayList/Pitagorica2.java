import java.util.ArrayList;

/**
 * Scrivere un programma che legga da riga di comando un numero intero n e
 * stampi a video la corrispondente tavola pitagorica n x n.
 * 
 * Oltre al metodo main(), devono essere definiti ed utilizzati almeno i
 * seguenti metodi:
 * 
 * public static int[] creaRigaPitagorica(int m, int n) che restituisce un array
 * nel quale cui sono memorizzati gli n valori della m-esima riga della tavola
 * pitagorica;
 * public static int[][] creaTavolaPitagorica(int n) che restituisce una matrice
 * in cui sono memorizzati i valori di una tavola pitagorica n x n, utilizzando
 * il metodo precedente per creare le diverse righe;
 * Esempio d'esecuzione:
 * $ java Pitagorica 5
 * 1 2 3 4 5
 * 2 4 6 8 10
 * 3 6 9 12 15
 * 4 8 12 16 20
 * 5 10 15 20 25
 * 
 * $ java Pitagorica 10
 * 1 2 3 4 5 6 7 8 9 10
 * 2 4 6 8 10 12 14 16 18 20
 * 3 6 9 12 15 18 21 24 27 30
 * 4 8 12 16 20 24 28 32 36 40
 * 5 10 15 20 25 30 35 40 45 50
 * 6 12 18 24 30 36 42 48 54 60
 * 7 14 21 28 35 42 49 56 63 70
 * 8 16 24 32 40 48 56 64 72 80
 * 9 18 27 36 45 54 63 72 81 90
 * 10 20 30 40 50 60 70 80 90 100
 */
public class Pitagorica2 {
    /**
     * @param m riga della tavola pitagorica (colonna)
     * @param n valori della m-esina riga della tavola pitagorica (riga)
     * @return restituisce un array nel quale cui sono memorizzati gli n valori
     *         della m-esima riga della tavola pitagorica
     */

    public static ArrayList<Integer> creaRigaPitagorica(int m, int n) {

        ArrayList<Integer> riga = new ArrayList<Integer>(); // n è la lunghezza della riga

        // m*1, m*2, m*3, ..., m*n
        for (int i = 0; i < n; i++) {
            riga.add(m * (i + 1)); // in teoria m il valore effettivo da sommare
        }

        return riga;
    }

    /**
     * @param n dimensioni matrice
     * @return restituisce una matrice
     *         in cui sono memorizzati i valori di una tavola pitagorica n x n,
     *         utilizzando
     *         il metodo precedente per creare le diverse righe
     */
    public static ArrayList<ArrayList<Integer>> creaTavolaPitagorica(int n) {

        ArrayList<ArrayList<Integer>> tavola = new ArrayList<ArrayList<Integer>>();

        /**
         * tavola[i][j]
         * i è la riga
         * j è la colonna, nel nostro caso è creaRigaPitagorica(i+1, n)
         * 
         * colonne (j)
         * -------- 0 - 1 - 2 - 3
         * riga 0 [ a | b | c | d ]
         * riga 1 [ e | f | g | h ]
         * riga 2 [ i | l | m | n ]
         * -------- ↑
         * -------- i (riga)
         * 
         */
        for (int i = 0; i < n; i++) {
            tavola.add(creaRigaPitagorica(i + 1, n));
        }

        return tavola;
    }

    /**
     * Scrivere un programma che legga da riga di comando un numero intero n e
     * stampi a video la corrispondente tavola pitagorica n x n.
     * 
     * @param args
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        ArrayList<ArrayList<Integer>> tavola = creaTavolaPitagorica(n);
        // stampa formattata
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // System.out.printf("%4d", tavola[i][j]);
                System.out.printf("%4d", tavola.get(i).get(j));
            }
            System.out.println();
        }
    }

}
