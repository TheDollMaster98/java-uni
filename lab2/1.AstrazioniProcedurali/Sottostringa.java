/**
 * Sottostringa
 * Scrivere una programa che legga da riga di comando due stringhe. Se la
 * seconda stringa è contenuta nella prima, scrivere a standard output
 * <stringa2> è sottostringa di <stringa1>, altrimenti <stringa2> non è
 * sottostringa di <stringa1>, come da esempi d'esecuzione.
 * 
 * A tal fine specificare, implementare e utilizzare la seguente procedura
 * parziale:
 * 
 * public static boolean sottoStringa(String testo, String parola) che
 * restituisce se true se parola è sottostringa di testo e false altrimenti.
 * Infine, scrivere una seconda versione del programma rendendo la procedura
 * totale aggiornando opportunamente le specifiche.
 * 
 * Esempio d'esecuzione:
 * $ java Sottostringa sfigmomanometro mano
 * mano è sottostringa di sfigmomanometro
 * 
 * $ java Sottostringa paretimologia parete
 * parete non è sottostringa di paretimologia
 * 
 * $ java Sottostringa Maiuscoletto mai
 * mai è sottostringa di Maiuscoletto
 * Notate l'ultimo esempio. Avete considerato questo caso nella specifica della
 * procedura parziale? Avete davvero reso la procedura totale? Se no sistemate
 * la procedura e le specifiche
 */

public class Sottostringa {
    /**
     * 
     * @param testo  stringa
     * @param parola sottostringa
     * @return restituisce se true se parola è sottostringa di testo e false
     *         altrimenti.
     *         Infine, scrivere una seconda versione del programma rendendo la
     *         procedura
     *         totale aggiornando opportunamente le specifiche.
     */
    public static boolean sottoStringa(String testo, String parola) {

        if (parola.contains(testo)) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Errore, deve avere 2 parole in ingresso!");
            return;
        }

        String parola = args[0];
        String sottoStringa = args[1];
        if (sottoStringa(sottoStringa, parola)) {
            System.out.println(sottoStringa + " è sottostringa di " + parola);
        } else {
            System.out.println(sottoStringa + " non è sottostringa di " + parola);
        }

    }
}
