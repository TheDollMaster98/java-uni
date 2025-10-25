/**
 * Mesi
 * Scrivere un programma che legga da riga di comando il nome di un mese (in
 * minuscolo). Il programma deve stampare a video il numero di giorni di quel
 * mese, assumendo che l'anno non sia bisestile.
 * 
 * Esempio d'esecuzione:
 * $ java Mesi ottobre
 * Il numero di giorni di ottobre è 31
 * 
 * $ java Mesi aprile
 * Il numero di giorni di aprile è 30
 * 
 * $ java Mesi catalogna
 * Il nome del mese non è corretto.
 */

public class Mesi {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Inserisci il nome di un mese come argomento.");
            return;
        }

        String mese = args[0].toLowerCase();
        int giorni = 0;

        switch (mese) {
            case "gennaio":
                giorni = 31;
                break;
            case "febbraio":
                giorni = 28;
                break;
            case "marzo":
                giorni = 31;
                break;
            case "aprile":
                giorni = 30;
                break;
            case "maggio":
                giorni = 31;
                break;
            case "giugno":
                giorni = 30;
                break;
            case "luglio":
                giorni = 31;
                break;
            case "agosto":
                giorni = 31;
                break;
            case "settembre":
                giorni = 30;
                break;
            case "ottobre":
                giorni = 31;
                break;
            case "novembre":
                giorni = 30;
                break;
            case "dicembre":
                giorni = 31;
                break;
            default:
                System.out.println("Il nome del mese non è corretto.");
                return;
        }

        System.out.println("Il numero di giorni di " + mese + " è " + giorni);
    }

}
