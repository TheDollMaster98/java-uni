import java.util.HashMap;
import java.util.Map;

/**
 * Numeri Romani
 * Scrivere un programma che legga da riga di comando un numero decimale oppure
 * un numero romano. Se il numero letto è decimale convertirlo in un numero
 * romano e viceversa.
 * 
 * Progettare, specificare e implementare delle procedure appropriate a tale
 * scopo.
 * 
 * Di quali procedure dovrei avere bisogno? Di quali vincoli dovrò tenere conto
 * nella specifica di queste procedure? Posso rendere le procedure totali
 * risolvendo questi vincoli? Come?
 * 
 * Esempio d'esecuzione:
 * $ java NumeriRomani 1995
 * MCMXCV
 * 
 * $ java NumeriRomani MCMXCV
 * 1995
 */
public class NumeriRomani {
    // mettendo "static" ora fanno parte della classe!
    final static int[] numArabi = {
            1, // I
            4, // IV
            5, // V
            9, // IX
            10, // X
            40, // XL
            50, // L
            90, // XC
            100, // C
            400, // CD
            500, // D
            1000 // M
    };

    final static String[] romani = {
            "I", // 1
            "IV", // 4
            "V", // 5
            "IX", // 9
            "X", // 10
            "XL", // 40
            "L", // 50
            "XC", // 90
            "C", // 100
            "CD", // 400
            "D", // 500
            "M" // 1k
    };

    /**
     * 
     * @param num
     */
    /**
     * REQUIRES: 1 <= num <= 3999
     * EFFECTS: restituisce la rappresentazione in numeri romani di num
     */
    public static String toRoman(int num) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numArabi.length; i++) {
            while (num >= numArabi[i]) {
                num -= numArabi[i];
                sb.append(romani[i]);
            }
        }

        return sb.toString();
    }

    public static void numeriArabi(String num) {

    }

    public static void main(String[] args) {
        // int n = Integer.parseInt(args[0]);
        // numeriRomani(args[0]);
    }

};