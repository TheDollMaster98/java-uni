/**
 * Un Evento delle olimpiadi invernali è definito dal suo nome e due eventi sono
 * considerati uguali se hanno lo stesso nome. Un Evento può restituire la sua
 * durata in minuti che dipende dal tipo di Evento e gli eventi sono
 * naturalmente ordinati in base alla loro durata.
 */

public abstract class Evento implements Comparable<Evento> {
    private final String nome;
    private final int durata;

    protected Evento(String nome, int durata) {
        this.nome = nome;
        this.durata = durata;
    }

    public String getNome() {
        return nome;
    }

    public int getDurata() {
        return durata;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) obj;
        return nome.equals(other.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }

    @Override
    public int compareTo(Evento other) {
        return Integer.compare(this.durata, other.durata);
    }

    @Override
    public String toString() {
        return "Evento: " + nome;
    }

    /**
     * Scrivere nella classe Evento un metodo main() per:
     * 
     * Creare una Cerimonia di apertura di nome Apertura2026 e scrivere le
     * informazioni su di essa su standard output.
     * Creare una Gara di nome Slittino1 con 15 atleti e scrivere le informazioni su
     * di essa su standard output.
     * Creare una Cerimonia di chiusura di nome Slittino1 e scrivere le informazioni
     * su di essa su standard output.
     * Scrivere su standard input il risultato del controllo dell'uguaglianza tra i
     * primi due oggetti e tra gli ultimi due oggetti.
     * Scrivere su standard input se il primo oggetto è più grande del secondo
     * oggetto.
     * 
     * @param args
     */
    public static void main(String[] args) {
        Cerimonia apertura = new Cerimonia("Apertura2026", "apertura");
        System.out.println(apertura);

        Gara slittino1 = new Gara("Slittino1", 15);
        System.out.println(slittino1);

        Cerimonia chiusura = new Cerimonia("Slittino1", "chiusura");
        System.out.println(chiusura);

        System.out.println("I primi due sono uguali? " + apertura.equals(slittino1)); // false
        System.out.println("Gli ultimi due sono uguali? " + slittino1.equals(chiusura)); // true
        System.out.println("Il primo e' piu' grande del secondo? " + (apertura.compareTo(slittino1) > 0)); // false
    }
}
