```
public abstract class Ele implements Comparable<Ele> {
    private final type var;

    protected Ele(type var) {
        if (var == null || var.isEmpty() || var.isBlank()) {
            throw new IllegalArgumentException("var cannot be null");
        }
        this.var = var;
    }

    public type getVar() / setVar(type var) {
            return var / this.var = var;;
    }

    // Metodo astratto: il padre dichiara "esiste un valore caratteristico"
    public abstract double valoreCaratteristico(); // es il costo

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // stesso riferimento
        if (o == null || !(o instanceof Ele)) return false;
        Ele other = (Ele) o; // cast in riga dedicata
        return nome.equals(other.nome); // confronto sul campo chiave
    }

    @Override
    public int hashCode() {
        // EFFECTS: restituisce un valore coerente con equals (stesso nome -> stesso
        // hash)
        // Chiave int: Integer.hashCode(numero)
        return Objects.hash(nome, numero); // esempio con piu' campi
    }

    @Override // thi < other: negativo, 0: uguali, this > other: positivo
    public int compareTo(Ele other) {
        return Double.compare(this.var(), other.var());
    }

    @Override // Viene chiamato automaticamente da System.out.println(oggetto).
    public String toString() {
        return " nome: " + nome + " valore: " + valore;
    }

    public static void main(String[] args) {
        // Crea istanze delle sottoclassi concrete
        SottoA a = new SottoA("Alpha", 10);
        SottoB b = new SottoB("Beta", "corto");
        System.out.println(a); // chiama toString() di SottoA
        // equals: confronto logico (non ==)
        System.out.println("Uguali? " + a.equals(b));
        // compareTo > 0 significa a > b secondo l'ordine naturale
        System.out.println("A > B? " + (a.compareTo(b) > 0));
    }
}

public class SottoA extends Elemento {
    private final int numero;

    public SottoA(String nome, int numero) {
        super(nome);
        this.numero = numero;
    }

    @Override
    public double valoreCaratteristico() {
        return numero; // esempio: il valore caratteristico è il numero
    }
}

public class Contenitore implements Iterable<Elemento> {
    private final List<Elemento> elementi = new ArrayList<>();

    public void aggiungi(Elemento e) {
        elementi.add(e);
    }

    @Override
    public Iterator<Elemento> iterator() {
        List<Elemento> presenti = new ArrayList<>();
        for (Elemento e : slots)
            if (e != null)
                presenti.add(e); // raccogli solo gli slot occupati
        Collections.sort(presenti); // ordina con compareTo
        return presenti.iterator(); // restituisce l'iteratore della lista ordinata
    }
}

public class Test {
    public static void main(String[] args) {
        Contenitore c = new Contenitore(Integer.parseInt(args[0]));
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // usa split(" ", N) dove N e' il numero di token fissi + 1:
            String[] tokens = line.split(" "); // leggo parola separata da spazi
            // "rimuovi" ha solo tokens[0] e tokens[1]: accedere a tokens[2] darebbe
            // ArrayIndexOutOfBoundsException
            switch (tokens[0]) {
                case "aggiungi" -> {
                    int pos = Integer.parseInt(tokens[1]);
                    try {
                        if (tokens[2].equals("SottoA")) {
                            int molt = Integer.parseInt(tokens[3]);
                            c.aggiungi(pos, new SottoA(tokens[2], molt));
                        } else if (tokens[2].equals("SottoB")) {
                            // ... costruzione SottoB
                        }
                    } catch (GiornoException | ElementoException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "rimuovi" -> {
                    try {
                        c.rimuovi(Integer.parseInt(tokens[1]));
                    } catch (GiornoException e) {
                        System.out.println(e.getMessage());
                    }
                }
                default -> System.out.println("Comando sconosciuto: " + tokens[0]);
            }
            // Dopo EOF: stampa il contenitore (ordine per posizione, usa toString)
            System.out.println(c);

            // Poi stampa gli elementi in ordine naturale (usa iterator -> Collections.sort)
            System.out.println("Elementi in ordine:");
            for (Elemento e : c)
                System.out.println("\t" + e); // \t obbligatorio: vedi esempio d'esecuzione

            scanner.close(); // chiudi lo scanner dopo EOF
        }
    }

}
// passa il messaggio a RuntimeException -> Exception -> Throwable dentro la classe
public class ElementoException extends RuntimeException {
    public ElementoException(String message) {
        super(message);
    }
}
```
