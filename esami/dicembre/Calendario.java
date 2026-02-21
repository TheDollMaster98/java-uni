import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Calendario implements Iterable<Sorpresa> {
    /**
     * Un `Calendario` delle sorprese è costituito da una serie di caselle, una per
     * giorno (dal giorno 1 al giorno 31), dentro le quali potrebbe esserci una
     * `Sorpresa`. Un `Calendario` espone le seguenti funzionalità:
     * 
     * - Inserire una `Sorpresa` nel `Calendario` per un certo giorno. Se quel
     * giorno contiene già una sorpresa viene lanciata una `GiornoException`
     * (checked). Se la specifica `Sorpresa` che si vuole inserire è già presente
     * nel `Calendario`, viene lanciata una `SorpresaException` (unchecked).
     * - Aprire la casella di un certo giorno, ottenendo la `Sorpresa` contenuta e
     * rimuovendola dal `Calendario`. Se nessuna `Sorpresa` è presente per il
     * giorno, viene lanciata la `GiornoException`.
     * - Restituire il costo del `Calendario` che è calcolato come la somma dei
     * costi di tutte le sorprese in esso contenute.
     * - Iterare le sorprese contenute nel `Calendario`, ordinate in base al loro
     * ordine naturale (ovvero il loro costo, vedi sotto).
     */

    final private Sorpresa[] calendario; // 31 giorni

    public Calendario() {
        this.calendario = new Sorpresa[31];
    }

    // check delle prenotazioni
    private void checkPrenotazione(int giorno, Sorpresa sorpresa) throws GiornoException {
        if (calendario[giorno - 1] != null) {
            throw new GiornoException("Giorno già occupato");
        }
        for (Sorpresa s : calendario) {
            if (s != null && s.equals(sorpresa)) {
                throw new SorpresaException("Sorpresa già presente nel calendario");
            }
        }
    }

    public void inserisci(int giorno, Sorpresa sorpresa) throws GiornoException {
        checkPrenotazione(giorno, sorpresa);
        calendario[giorno - 1] = sorpresa;
    }

    public Sorpresa apri(int giorno) throws GiornoException {
        if (calendario[giorno - 1] == null) {
            throw new GiornoException("Nessuna sorpresa presente per questo giorno");
        }
        Sorpresa sorpresa = calendario[giorno - 1];
        calendario[giorno - 1] = null; // rimuove la sorpresa
        return sorpresa;
    }

    public double costo() {
        double costoTotale = 0.0;
        for (Sorpresa s : calendario) {
            if (s != null) {
                costoTotale += s.getCosto();
            }
        }
        return costoTotale;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Calendario con costo totale: ").append(costo()).append("\n");
        for (int i = 0; i < calendario.length; i++) {
            if (calendario[i] != null) {
                sb.append("Giorno ").append(i + 1).append(": ").append(calendario[i].toString()).append("\n");
            }
        }
        return sb.toString();
    }

    // ordina per costo:
    @Override
    public Iterator<Sorpresa> iterator() {
        List<Sorpresa> sorprese = new ArrayList<>();
        for (Sorpresa s : calendario) {
            if (s != null) {
                sorprese.add(s);
            }
        }
        Collections.sort(sorprese);
        return sorprese.iterator();
    }
}
