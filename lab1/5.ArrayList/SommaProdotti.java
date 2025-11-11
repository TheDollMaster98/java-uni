/**
 * Somma di prodotti
 * Scrivere un programma che legga da riga di comando una sequenza di lunghezza
 * pari di numeri interi. Data la sequenza, il programma deve moltiplicare
 * ciascun numero in una posizione di indice pari per il successivo numero in
 * posizione di indice dispari e sommare i prodotti ottenuti.
 * 
 * Esempio: Se 10 2 3 4 5 6 è la sequenza letta, allora la somma calcolata deve
 * essere 10*2 + 3*4 + 5*6 = 62.
 * 
 * Il programma deve infine stampare a video il valore della somma calcolata.
 * 
 * Oltre al metodo main(), deve essere definito ed utilizzato un metodo:
 * 
 * public static int calcola(int[] n) che restituisce la somma dei prodotti
 * ottenuti moltiplicando ciascun numero in una posizione di indice pari di n
 * per il successivo numero in posizione di indice dispari.
 * Esempio d'esecuzione:
 * $ java SommaProdotti 1 2 3 4 5 6
 * 1*2 + 3*4 + 5*6 = 2 + 12 + 30 = 44
 * La somma è 44
 * 
 * $ java SommaProdotti 7 3 1 8
 * La somma è 29
 */
public class SommaProdotti {
    public static void main(String[] args) throws Exception {

        if (args.length % 2 != 0) {
            throw new Exception("Errore: il numero di argomenti deve essere pari.");
        }
        double prod = 0.0;
        int count = 1;
        // variabili provvisoria che uso per resettare ogni 2.
        double temp = 1.0;

        // per prendere più elementi da riga di comando:
        for (String s : args) {
            try {
                double value = Double.parseDouble(s);

                temp *= value;
                // System.out.println("temp: " + temp);
                // System.out.println("value: " + value);
                // controllo se sto prendendo 2 numeri alla volta, quindi prendo sempre fino al
                // 2, 4 ecc.
                if (count % 2 == 0) {
                    prod += temp;
                    // System.out.println("prod: " + prod);
                    // System.out.println("----------");
                    // resetto ogni 2 numeri:
                    temp = 1;
                }
                count++;
                // System.out.println("prod finale: " + prod);
                // System.out.println("----------");
            } catch (Exception e) {
                throw new Exception("Errore: " + s + " non è maggiore di zero");
            }
        }

        // Calcolo e stampa della somma dei prodotti dopo aver processato tutti i numeri
        System.out.println("La somma è " + prod);
    }
}
