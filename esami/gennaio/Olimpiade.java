
/**
 * Una Olimpiade invernale è definita dall'anno in cui avviene e dagli eventi
 * calendarizzati durante i 19 giorni della stessa, uno per giorno (dal giorno 1
 * al giorno 19). Una Olimpiade espone le seguenti funzionalità:
 * 
 * Inserire un Evento per un certo giorno. Se quel giorno contiene già un Evento
 * viene lanciata una GiornoException (checked). Se lo specifico Evento che si
 * vuole inserire è già presente, viene lanciata una EventoException
 * (unchecked). Se l'Evento che si vuole inserire è una Cerimonia di apertura e
 * il giorno non è il primo, viene lanciata una GiornoException, e similmente se
 * la Cerimonia è di chiusura e il giorno non è l'ultimo.
 * Rimuovere l'Evento di un certo giorno. Se nessun Evento è presente per il
 * giorno specificato, viene lanciata la GiornoException.
 * Iterare gli eventi dell'Olimpiade, ordinati in base al loro ordine naturale
 * (ovvero la loro durata). Inoltre, la funzione di astrazione di Olimpiade
 * dovrà restituire una rappresentazione della stessa, specificando il suo anno
 * e di seguito, in ordine di giorno, gli eventi previsti (come da esempio
 * d'esecuzione).
 * Scrivere una classe Test che nel metodo main() legga da riga di comando
 * l'anno dell'Olimpiade e successivamente, da standard input delle istruzioni
 * nel formato:
 * 
 * aggiungi <giorno> Cerimonia <nome> <apertura / chiusura> per aggiungere una
 * nuova Cerimonia, di apertura o chiusura, nel giorno specificato e con il nome
 * specificato
 * aggiungi <giorno> Gara <nome> <numero atleti> per aggiungere una nuova Gara
 * nel giorno specificato, con il nome e il numero di atleti specificati
 * rimuovi <giorno> per rimuovere l'Evento del giorno specificato La lettura
 * termina quando viene inserito, con la combinazione Ctrl+D il comando
 * End-Of-File. Al termine della lettura, stampare su standard output
 * l'Olimpiade, indicando il suo anno e gli eventi previsti, ordinati per giorno
 * dell'Olimpiade. Successivamente, stampare su standard output gli eventi
 * ordinati nel loro ordine naturale, come da esempio d'esecuzione.
 * Esempio d'esecuzione:
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;

public class Olimpiade implements Iterable<Evento> {
    private final int anno;
    private final Evento[] eventi;

    public Olimpiade(int anno) {
        this.anno = anno;
        this.eventi = new Evento[19];
    }

    @Override
    public Iterator<Evento> iterator() {
        List<Evento> eventiOrdinati = new ArrayList<>();
        for (Evento e : eventi) {
            if (e != null) {
                eventiOrdinati.add(e);
            }
        }
        Collections.sort(eventiOrdinati);
        return eventiOrdinati.iterator();
    }

    public void rimuovi(int giorno) throws GiornoException {
        if (giorno < 1 || giorno > 19) {
            throw new IllegalArgumentException("Giorno non valido: " + giorno);
        }
        if (eventi[giorno - 1] == null) {
            throw new GiornoException("Nessun evento presente per il giorno: " + giorno);
        }
        eventi[giorno - 1] = null;
    }

    public void aggiungi(int giorno, Evento evento) throws GiornoException, EventoException {
        if (giorno < 1 || giorno > 19) {
            throw new IllegalArgumentException("Giorno non valido: " + giorno);
        }
        if (eventi[giorno - 1] != null) {
            throw new GiornoException("Giorno già occupato: " + giorno);
        }
        for (Evento e : eventi) {
            if (e != null && e.equals(evento)) {
                throw new EventoException("Evento già presente: " + evento.getNome());
            }
        }
        if (evento instanceof Cerimonia) {
            Cerimonia cerimonia = (Cerimonia) evento;
            if (cerimonia.getTipo().equals("apertura") && giorno != 1) {
                throw new GiornoException("La cerimonia di apertura deve essere il primo giorno");
            }
            if (cerimonia.getTipo().equals("chiusura") && giorno != 19) {
                throw new GiornoException("La cerimonia di chiusura deve essere l'ultimo giorno");
            }
        }
        eventi[giorno - 1] = evento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Olimpiade Invernale ").append(anno).append(":\n");
        for (int i = 0; i < eventi.length; i++) {
            if (eventi[i] != null) {
                sb.append("\t").append(i + 1).append(": ").append(eventi[i]).append("\n");
            }
        }
        return sb.toString();
    }

}
