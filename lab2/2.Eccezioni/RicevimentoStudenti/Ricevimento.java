import java.util.Arrays;

public class Ricevimento {

    public static void prenota(String[] agenda, int ora, String nome)
            throws NullPointerException, AlreadyBookedException, TimeBusyException, IllegalArgumentException {
        // MODIFIES: agenda
        // EFFECTS: data l'ora e il nome prenota assegnerà lo slot corrispondente in
        // agenda throws NullPointerException se agenda è null;
        // TimeBusyException se lo slot è già prenotato o AlreadyBookedException se
        // `nome` ha già prenotato un orario diverso;
        // IllegalArgumentException se 'orario' non valido (<13 o >18) o 'nome' vuoto

        // agenda nulla
        if (agenda == null) {
            throw new NullPointerException("Agenda non valida!");
        }

        // agenda piena
        if (agenda.length != 6) {
            throw new IllegalArgumentException("L'agenda non ha 6 slot");
        }
        // studente nullo o vuoto
        if (nome == null || nome.equals("")) {
            throw new ParameterException("nome non valido");
        }

        // orario non valido
        if (ora < 13 | ora > 18) {
            throw new ParameterException("orario non valido");
        }

        // orario già prenotato
        if (agenda[ora - 13] != null) {
            throw new TimeBusyException("Slot già stato prenotato");
        }

        // se c'è già la prenotazione della stessa persona
        if (Arrays.asList(agenda).contains(nome)) {
            throw new AlreadyBookedException(nome + ", hai già prenotato!");
        }
        // sottraggo con l'orari minimo perché l'agenza parte dalle 13, quindi da 13 a
        // 18 sono gli slot liberi. es: se è 13, allora 13-13=0, se è 14, allora
        // 14-13=1, e così via fino a 18-13=5
        agenda[ora - 13] = nome;
    }

    /**
     * restituisce gli orari ancora disponibili (lanciando eccezioni nei casi
     * corrispondenti ai primi due punti del primo metodo).
     * 
     * @param args
     */
    public static int[] orariDisponibili(String[] agenda) throws NullPointerException, IllegalArgumentException {
        // agenda nulla
        if (agenda == null) {
            throw new NullPointerException("Agenda non valida!");
        }
        // agenda piena
        if (agenda.length != 6) {
            throw new IllegalArgumentException("L'agenda non ha 6 slot");
        }
        // tengo il conto di quanti slot sono liberi:
        int count = 0;

        for (int i = 0; i < agenda.length; i++) {
            if (agenda[i] == null) {
                count++;
            }
        }

        int[] orari = new int[count];

        for (int i = 0, j = 0; i < agenda.length; i++)
            if (agenda[i] == null) {
                orari[j++] = i + 13;
            }

        return orari;
    }
}
