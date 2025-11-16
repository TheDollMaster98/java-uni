import java.time.DateTimeException;

/**
 * Formato Ora
 * Scrivere un programma che legga da riga di comando un orario espresso in 24
 * ore e lo converta in orario espresso in 12 ore. Il formato corretto è hh:mm,
 * dove hh deve essere un numero di due cifre inferiore a 24 e mm è un numero a
 * due cifre inferiore a 60 (gli zeri iniziali sono richiesti per ore e minuti
 * inferiori a 10). Generare e gestire un'eccezione di tipo DateTimeException se
 * il formato non è corretto.
 * 
 * Oltre al metodo main(), deve essere specificata, implementata ed utilizzata
 * almeno la seguente procedura:
 * 
 * public static String converti(String s) throws DateTimeException
 * Esempio d'esecuzione:
 * $ java FormatoOra 14:30
 * 2:30 PM
 * 
 * $ java FormatoOra 09:30
 * 9:30 AM
 * 
 * $ java FormatoOra 13-23
 * Formato ora non valido 13-23
 */
public class FormatoOra {

    private static boolean isNumeric(String s) {
        // se la stringa è nulla o vuota
        if (s == null || s.isEmpty())
            return false;

        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Scrivere un programma che legga da riga di comando un orario espresso in 24
     * ore e lo converta in orario espresso in 12 ore. Il formato corretto è hh:mm,
     * dove hh deve essere un numero di due cifre inferiore a 24 e mm è un numero a
     * due cifre inferiore a 60 (gli zeri iniziali sono richiesti per ore e minuti
     * inferiori a 10). Generare e gestire un'eccezione di tipo DateTimeException se
     * il formato non è corretto.
     * 
     * OVERVIEW: converto le ore, lancio un eccezione per il Formato ora non valido:
     * + s *
     * 
     * @param s
     * @return
     * @throws DateTimeException
     */
    public static String converti(String s) throws DateTimeException {
        // 1. deve contenere ':'
        if (!s.contains(":")) {
            throw new DateTimeException("Formato ora non valido: " + s);
        }

        // divido HH:MM e max 2 elementi
        String[] orario = s.split(":", 2);

        // deve esserci 2 parti (min ed h)
        if (orario.length != 2) {
            throw new DateTimeException("Formato ora non valido: " + s);
        }

        // devono esserci max 2 numeri
        if (orario[0].length() > 2 || orario[1].length() > 2) {
            throw new DateTimeException("Formato ora non valido: " + s);
        }

        // devono contenere SOLO numeri
        if (!isNumeric(orario[0]) || !isNumeric(orario[1])) {
            throw new DateTimeException("Formato ora non valido: " + s);
        }

        int ore = Integer.parseInt(orario[0]); // ore
        int minuti = Integer.parseInt(orario[1]); // minuti

        StringBuilder orarioConvertito = new StringBuilder();
        // pomeriggio:
        if (ore > 13) {
            orarioConvertito.append(ore - 12 + ":" + minuti + " PM");
        } else { // mattina:
            orarioConvertito.append(ore + ":" + minuti + " AM");
        }

        return orarioConvertito.toString();

    }

    // il try/catch va SEMPRE nel main
    public static void main(String[] args) {

        try {
            System.out.println(converti(args[0]));
        } catch (DateTimeException e) {
            throw e;
        }

    }
}
