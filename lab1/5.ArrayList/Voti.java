import java.util.ArrayList;
import java.util.Scanner;

/**
 * Filtra voti
 * Scrivere un programma che:
 * 
 * legga da standard input una sequenza di valori compresi tra 0 e 30,
 * terminando la lettura quando si preme ctrl+D. I valori numerici interi
 * maggiori o uguali a 18 corrispondono a voti sufficienti;
 * stampi a video le due sottosequenze di valori che corrisponodo
 * rispettivamente a voti insufficienti e sufficienti.
 * Oltre al metodo main(), devono essere definiti ed utilizzati almeno i
 * seguenti metodi:
 * 
 * public static ArrayList<Integer> leggiNumeri() che restituisce una ArrayList
 * in cui sono memorizzati i valori specificati su standard input;
 * 
 * public static ArrayList<Integer> filtraVotiSufficienti(ArrayList<Integer> l)
 * che restituisca la sottosequenza dei voti sufficienti di l.
 * 
 * public static ArrayList<Integer> filtraVotiInsufficienti(ArrayList<Integer>
 * l)
 * che restituisca la sottosequenza dei voti insufficienti di l.
 * 
 * Sarebbe possibile fare un'unico metodo filtraVoti() per sostituire i due
 * precedenti? Come lo fareste e che parametri avrebbe? E se volessimo fare un
 * metodo per svolgere un qualunque filtraggio arbitrario (es: restituire i voti
 * pari, o quelli divisibili per 5)? Cosa servirebbe per poter fare questo tipo
 * di operazioni?
 * 
 * Esempio d'esecuzione:
 * $ java Voti
 * Inserisci i voti:
 * 19
 * 16
 * 30
 * 5
 * Voti sufficienti: [19 30]
 * Voti insufficienti: [16 5]
 */

public class Voti {
    /**
     * 
     * @return restituisce una ArrayList
     *         in cui sono memorizzati i valori specificati su standard input;
     */
    public static ArrayList<Integer> leggiNumeri() {

        ArrayList<Integer> voti = new ArrayList<Integer>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci i voti (Ctrl+D o Ctrl+Z per terminare):");

        // scanner che legge solo numeri int:
        while (scanner.hasNextInt()) {
            Integer voto = scanner.nextInt();
            if (voto >= 0 && voto <= 100)
                voti.add(voto);
            else
                System.out.println("Voto non valido: inserisci un valore tra 0 e 100.");

        }
        scanner.close();

        return voti;
    }

    /**
     * 
     * @param l voti
     * @return restituisca la sottosequenza dei voti sufficienti di l.
     */
    public static ArrayList<Integer> filtraVotiSufficienti(ArrayList<Integer> l) {

        ArrayList<Integer> votiSufficienti = new ArrayList<Integer>();

        for (Integer voto : l)
            if (voto >= 18)
                votiSufficienti.add(voto);

        return votiSufficienti;
    }

    /**
     * 
     * @param l
     * @return restituisca la sottosequenza dei voti insufficienti di l
     */
    public static ArrayList<Integer> filtraVotiInsufficienti(ArrayList<Integer> l) {
        ArrayList<Integer> votiInsufficienti = new ArrayList<Integer>();

        for (Integer voto : l)
            if (voto < 18)
                votiInsufficienti.add(voto);

        return votiInsufficienti;
    }

    /**
     * Sarebbe possibile fare un'unico metodo filtraVoti() per sostituire i due
     * precedenti? Come lo fareste e che parametri avrebbe? E se volessimo fare un
     * metodo per svolgere un qualunque filtraggio arbitrario (es: restituire i voti
     * pari, o quelli divisibili per 5)? Cosa servirebbe per poter fare questo tipo
     * di operazioni?
     * 
     * @param l
     * @param suff indica se si vuole un voto sufficiente (true) o insufficiente
     *             (false)
     * @return
     */
    public static ArrayList<Integer> filtraVoti(ArrayList<Integer> voti, boolean suff) {
        ArrayList<Integer> votiFiltrati = new ArrayList<Integer>();

        for (Integer voto : voti) {
            if (suff == true && voto >= 18 || suff == false && voto < 18)
                votiFiltrati.add(voto);
        }

        return votiFiltrati;

    }

    public static void main(String[] args) {

        ArrayList<Integer> voti = leggiNumeri();

        System.out.println("Voti sufficienti: " + filtraVotiSufficienti(voti));
        System.out.println("Voti Insufficienti: " + filtraVotiInsufficienti(voti));
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Voti sufficienti dinamici: " + filtraVoti(voti, true));
        System.out.println("Voti Insufficienti dinamici: " + filtraVoti(voti, false));
    }
}
