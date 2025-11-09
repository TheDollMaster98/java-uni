/**
 * Medie
 * Scrivere un programma che legga da riga di comando una sequenza di numeri
 * reali > 0. Il programma deve stampare a video il risultato della media
 * aritmetica dei valori inseriti.
 * 
 * Esempio d'esecuzione:
 * $ java Medie 4 6 8
 * Media aritmetica: 6
 * 
 * $ java Medie 3 5 2 6 1
 * Media aritmetica: 3.4
 */
public class Medie {
    public static void main(String[] args) throws Exception {
        double sum = 0.0;
        int count = 0;

        // per prendere più elementi da riga di comando:
        for (String s : args) {
            try {
                double v = Double.parseDouble(s);
                if (v <= 0) {
                    throw new Exception("Il numero deve essere maggiore di zero!");
                }
                sum += v;
                count++;
            } catch (Exception e) {
                throw new Exception("Errore: " + s + " non è maggiore di zero");
            }
        }

        // Calcolo e stampa della media dopo aver processato tutti i numeri
        double media = (count > 0) ? sum / count : 0.0;
        System.out.println("Media aritmetica: " + media);
    }
}