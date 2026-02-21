public abstract class Sorpresa implements Comparable<Sorpresa> {
    /**
     * Una `Sorpresa` è definita dal suo nome e due sorprese sono considerate uguali
     * se hanno lo stesso nome. Inoltre, una `Sorpresa` può restituire il suo costo,
     * e le sorprese sono naturalmente ordinate in base al loro costo. Il costo si
     * calcola in maniera dipendente dal tipo della `Sorpresa`:
     * 
     * - Per le sorprese di tipo `Cioccolatino`, definite anche dalla loro
     * percentuale di cacao, il costo è un decimo della percentuale di cacao.
     * - Per le sorprese di tipo `Giocattolo`, definite anche da una loro
     * descrizione, il costo è pari al numero delle parole della descrizione.
     * 
     */
    private final String nome;
    private final double costo;

    protected Sorpresa(String nome, double costo) {
        this.nome = nome;
        this.costo = costo;
    }

    // GETTER:
    public String getNome() {
        return this.nome;
    }

    public double getCosto() {
        return costo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Sorpresa)) {
            return false;
        }
        Sorpresa s = (Sorpresa) obj;
        return this.nome.equals(s.getNome());
    }

    @Override
    public int hashCode() {
        return this.nome.hashCode();
    }

    @Override
    public int compareTo(Sorpresa o) {
        return Double.compare(this.costo, o.getCosto());
    }

    @Override
    public String toString() {
        return String.format("Sorpresa %s con costo %.2f", getNome(), getCosto());
    }

    /**
     * 
     * Scrivere nella classe `Sorpresa` un metodo `main()` per:
     * 
     * 1. Creare un `Cioccolatino` di nome `Lindt` con 25% di cacao e scrivere le
     * informazioni su di esso su **standard output**.
     * 2. Creare un `Giocattolo` di nome `Yorick` con come descrizione `di infinita
     * allegria` e scrivere le informazioni su di esso su **standard output**.
     * 3. Creare un `Cioccolatino` di nome `Yorick` con 1% di cacao e scrivere le
     * informazioni su di esso su **standard output**.
     * 4. Scrivere su **standard input** il risultato del controllo dell'uguaglianza
     * tra i primi due oggetti e tra gli ultimi due oggetti.
     * 5. Scrivere su **standard input** se il primo oggetto è più grande del
     * secondo oggetto.
     * 
     */
    public static void main(String[] args) {
        Sorpresa cioccolatino = new Cioccolatino("Lindt", 25);
        System.out.println(cioccolatino.toString());

        Sorpresa giocattolo = new Giocattolo("Yorick", "di infinita allegria");
        System.out.println(giocattolo.toString());

        Sorpresa cioccolatino2 = new Cioccolatino("Yorick", 1);
        System.out.println(cioccolatino2.toString());

        System.out.println(cioccolatino.equals(giocattolo)); // false
        System.out.println(giocattolo.equals(cioccolatino2)); // true

        System.out.println(cioccolatino.compareTo(giocattolo) > 0); // true
    }
}
