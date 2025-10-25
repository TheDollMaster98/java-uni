/**
 * Parentesi
 * Scrivere un programma che legga da riga di comando una stringa composta da
 * parentesi quadre aperte [ e parentesi quadre chiuse ]. Il programma stampa
 * bilanciata se la stringa ha le parentesi bilanciate o non bilanciata
 * altrimenti. Le parentesi sono bilanciate se a ogni parentesi aperta
 * corrisponde una chiusa. NOTA: È indispensabile mettere la stringa di
 * parentesi tra virgolette per permetterne la lettura da riga di comando
 * 
 * Esempio d'esecuzione:
 * $ java Parentesi "[[]][]"
 * Bilanciate
 * 
 * $ java Parentesi "[[]"
 * Non bilanciate
 * 
 * $ java Parentesi "[[][]]"
 * Bilanciate
 * 
 * $ java Parentesi "[]]["
 * Non bilanciate
 */
public class Parentesi {
    public static void main(String[] args) {
        String s = args[0];
        // uso il contatore per tenere traccia delle parentesi:
        int counter = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[')
                counter++;
            else // se non è [ allora è ]
                counter--;

            if (counter < 0) // chiusure > aperture = non bilanciata
                break;

        }

        if (counter == 0)
            System.out.println("Bilanciate");
        else
            System.out.println("Non bilanciate");

    }
}
