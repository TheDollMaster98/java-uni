# Java Exam Pattern — template universale

Due classi che coprono tutti i pattern ricorrenti degli esami.
Adatta nomi, campi e calcoli al testo; la struttura rimane sempre questa.

---

## 1. Classe padre astratta

```java
import java.util.Objects;

/**
 * OVERVIEW: Elemento astratto definito da un nome univoco e un valore numerico.
 * Due elementi sono uguali se hanno lo stesso nome.
 * Ordine naturale basato sul valore.
 * Immutabile.
 *
 * AF (Abstraction Function): descrive cosa rappresenta lo stato concreto dell'oggetto.
 *      AF(e) = "nome=" + e.nome + ", valore=" + e.valore
 * RI (Representation Invariant): condizioni che devono essere sempre vere sui campi.
 *      nome != null && !nome.isBlank() && valore >= 0
 */
public abstract class Elemento implements Comparable<Elemento> {

    // private: nessuno accede direttamente al campo dall'esterno
    // final: il valore non cambia dopo la creazione (immutabilita')
    private final String nome;
    private final double valore;

    // protected: la classe e' abstract, non si istanzia direttamente.
    // Le sottoclassi chiamano super(...) che arriva qui.
    // public sarebbe tecnicamente sbagliato: inviterebbe a fare "new Elemento(...)"
    // che pero' il compilatore blocca comunque. protected e' piu' preciso.
    protected Elemento(String nome, double valore) {
        // Validazione: se i dati non sono validi, l'oggetto non nasce.
        // IllegalArgumentException e' unchecked: non serve dichiarare throws.
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("\tECCEZIONE: nome null o blank");
        if (valore < 0)
            throw new IllegalArgumentException("\tECCEZIONE: valore negativo");
        this.nome  = nome;
        this.valore = valore;
    }

    // Getter: unico modo per leggere il campo dall'esterno.
    // Niente setter: l'oggetto e' immutabile.
    public String getNome()   { return nome; }
    public double getValore() { return valore; }

    // Metodo astratto: il padre dichiara "esiste un valore caratteristico"
    // ma non sa come calcolarlo — ogni sottoclasse lo implementa con @Override.
    // Alternativa: se il valore e' gia' in un campo del padre (come 'valore'),
    // si omette il metodo astratto e si usa direttamente getValore().
    public abstract double valoreCaratteristico();

    // equals: definisce l'uguaglianza LOGICA tra oggetti.
    // Senza questo, Java confronta solo i riferimenti in memoria (come ==).
    // Il campo scelto come chiave di uguaglianza deve corrispondere al dominio
    // (es. "due elementi sono uguali se hanno lo stesso nome").
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                      // stesso riferimento
        if (o == null || !(o instanceof Elemento)) return false;
        Elemento other = (Elemento) o;                   // cast in riga dedicata
        return nome.equals(other.nome);                  // confronto sul campo chiave
    }

    // hashCode: deve essere COERENTE con equals.
    // Regola: se a.equals(b) == true, allora a.hashCode() == b.hashCode().
    // Usa gli stessi campi usati in equals. Objects.hash() gestisce null e combina
    // piu' campi in modo sicuro (internamente usa lo stesso * 31 visto in guida.md).
    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    // compareTo: definisce l'ordine naturale usato da Collections.sort().
    // Restituisce: negativo (this < other), zero (uguali), positivo (this > other).
    // Double.compare evita overflow rispetto a "this.x - other.x".
    // Integer.compare se il campo e' int.
    @Override
    public int compareTo(Elemento other) {
        return Double.compare(this.valoreCaratteristico(), other.valoreCaratteristico());
    }

    // toString: rappresentazione testuale dell'oggetto.
    // Viene chiamato automaticamente da System.out.println(oggetto).
    // Inizia con spazio cosi' le sottoclassi fanno "Tipo" + super.toString()
    // e il risultato e' "Tipo nome: X valore: Y" senza spazi di troppo.
    @Override
    public String toString() {
        return " nome: " + nome + " valore: " + valore;
    }

    // main() nella classe padre: richiesto da alcuni temi per testare le sottoclassi.
    // MAI fare "new Elemento(...)": e' abstract, il compilatore lo impedisce.
    public static void main(String[] args) {
        // Crea istanze delle sottoclassi concrete
        SottoA a = new SottoA("Alpha", 10);
        SottoB b = new SottoB("Beta", "corto");

        System.out.println(a);   // chiama toString() di SottoA
        System.out.println(b);   // chiama toString() di SottoB

        // equals: confronto logico (non ==)
        System.out.println("Uguali? " + a.equals(b));

        // compareTo > 0 significa a > b secondo l'ordine naturale
        System.out.println("A > B? " + (a.compareTo(b) > 0));
    }
}
```

---

## 2. Sottoclassi concrete

```java
/**
 * OVERVIEW: SottoA e' un Elemento con un moltiplicatore numerico.
 * Il valoreCaratteristico e' valore * moltiplicatore.
 *
 * AF (Abstraction Function): AF(s) = "SottoA" + AF_padre(s) + ", molt=" + s.moltiplicatore
 * RI (Representation Invariant): RI_padre(s) && moltiplicatore > 0
 */
public class SottoA extends Elemento {

    // Costante statica: uguale per tutte le istanze di SottoA.
    // static = appartiene alla classe, non all'oggetto.
    // final  = non cambia mai.
    // Usata per passare un valore fisso a super(): super(nome, COSTO_BASE)
    private static final double COSTO_BASE = 1.5;

    // Campo proprio di SottoA, non condiviso col padre.
    private final int moltiplicatore;

    public SottoA(String nome, int moltiplicatore) {
        // super() DEVE essere la prima riga: inizializza i campi del padre.
        // Si puo' passare una costante, un calcolo, o un valore fisso.
        super(nome, COSTO_BASE);

        // Validazione dei campi PROPRI di SottoA, dopo super().
        if (moltiplicatore <= 0)
            throw new IllegalArgumentException("\tECCEZIONE: moltiplicatore <= 0");
        this.moltiplicatore = moltiplicatore;
    }

    public int getMoltiplicatore() { return moltiplicatore; }

    // @Override del metodo astratto del padre.
    // @Override fa si' che il compilatore verifichi che il metodo esista davvero nel padre.
    // Senza @Override compilerebbe lo stesso, ma un refuso nel nome creerebbe un metodo nuovo
    // invece di sovrascrivere quello del padre — errore silenzioso.
    @Override
    public double valoreCaratteristico() {
        return getValore() * moltiplicatore;
    }

    // toString: prepend del tipo + super.toString() + campi propri.
    // super.toString() riusa il codice del padre senza riscriverlo.
    @Override
    public String toString() {
        return "SottoA" + super.toString() + " molt: " + moltiplicatore;
        // risultato: "SottoA nome: Alpha valore: 1.5 molt: 10"
    }
}
```

```java
/**
 * OVERVIEW: SottoB e' un Elemento con una descrizione testuale.
 * Il valoreCaratteristico e' il numero di parole della descrizione.
 *
 * AF (Abstraction Function): AF(s) = "SottoB" + AF_padre(s) + ", desc=" + s.descrizione
 * RI (Representation Invariant): RI_padre(s) && descrizione != null && !descrizione.isBlank()
 */
public class SottoB extends Elemento {

    private final String descrizione;

    public SottoB(String nome, String descrizione) {
        // Il valore e' calcolato dalla descrizione e passato al padre.
        // Si puo' chiamare un metodo statico privato per tenere super() alla prima riga.
        super(nome, calcolaValore(descrizione));
        if (descrizione == null || descrizione.isBlank())
            throw new IllegalArgumentException("\tECCEZIONE: descrizione non valida");
        this.descrizione = descrizione;
    }

    // Metodo statico privato: serve solo per calcolare il valore da passare a super().
    // Deve essere statico perche' viene chiamato prima che l'oggetto esista.
    private static double calcolaValore(String descrizione) {
        if (descrizione == null) return 0;
        return descrizione.split(" ").length;   // numero di parole
    }

    public String getDescrizione() { return descrizione; }

    @Override
    public double valoreCaratteristico() {
        return getValore();   // gia' calcolato nel costruttore e salvato nel padre
    }

    @Override
    public String toString() {
        return "SottoB" + super.toString() + " desc: " + descrizione;
    }
}
```

---

## 3. Test.java

```java
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        // args[0]: parametro da riga di comando (es: java Test 19).
        // Se il contenitore non ha parametri (es. Calendario fisso a 31 giorni),
        // si omette args e si fa direttamente: new Contenitore()
        Contenitore c = new Contenitore(Integer.parseInt(args[0]));

        Scanner scanner = new Scanner(System.in);

        // Ciclo fino a EOF (Ctrl+D su Unix, Ctrl+Z su Windows)
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            // split(" "): divide la riga in token separati da spazio.
            // Se un campo puo' contenere spazi (es. descrizione "di infinita allegria"),
            // usa split(" ", N) dove N e' il numero di token fissi + 1:
            //   String[] parts = line.split(" ", 4);
            //   String nome  = parts[3].split(" ")[0];
            //   String extra = parts[3].split(" ", 2)[1];
            String[] tokens = line.split(" ");

            // Leggi il comando PRIMA di accedere a tokens[2], tokens[3]...
            // "rimuovi" ha solo tokens[0] e tokens[1]: accedere a tokens[2] darebbe ArrayIndexOutOfBoundsException
            if (tokens[0].equals("aggiungi")) {
                int pos = Integer.parseInt(tokens[1]);

                // Parsing specifico DENTRO il branch del tipo
                if (tokens[2].equals("SottoA")) {
                    int molt = Integer.parseInt(tokens[3]);
                    try {
                        c.aggiungi(pos, new SottoA(tokens[2], molt));
                    } catch (GiornoException e)  { System.out.println(e.getMessage()); }
                      catch (ElementoException e) { System.out.println(e.getMessage()); }

                } else if (tokens[2].equals("SottoB")) {
                    // tokens[3] e' la descrizione (una parola); se fosse multi-parola
                    // si userebbe split(" ", 4) come spiegato sopra
                    try {
                        c.aggiungi(pos, new SottoB(tokens[3], tokens[4]));
                    } catch (GiornoException e)  { System.out.println(e.getMessage()); }
                      catch (ElementoException e) { System.out.println(e.getMessage()); }
                }

            } else if (tokens[0].equals("rimuovi")) {
                // try-catch separato per branch: "rimuovi" lancia solo GiornoException
                try {
                    c.rimuovi(Integer.parseInt(tokens[1]));
                } catch (GiornoException e) { System.out.println(e.getMessage()); }
            }
        }

        // Dopo EOF: stampa il contenitore (ordine per posizione, usa toString)
        System.out.println(c);

        // Poi stampa gli elementi in ordine naturale (usa iterator -> Collections.sort)
        System.out.println("Elementi in ordine:");
        for (Elemento e : c)
            System.out.println("\t" + e);   // \t obbligatorio: vedi esempio d'esecuzione
    }
}
```

---

## 4. Eccezioni

```java
// UNCHECKED — estende RuntimeException.
// Il compilatore NON obbliga chi chiama il metodo a fare try-catch.
// Si usa per errori logici: elemento duplicato, argomento non valido, ecc.
// Il metodo che la lancia NON deve dichiarare "throws ElementoException".
public class ElementoException extends RuntimeException {
    public ElementoException(String message) {
        super(message);   // passa il messaggio a RuntimeException -> Exception -> Throwable
    }
}
```

```java
// CHECKED — estende Exception.
// Il compilatore OBBLIGA chi chiama il metodo a fare try-catch (o a propagare con throws).
// Si usa per errori "prevedibili" che il programma deve gestire: posizione non valida, ecc.
// Il metodo che la lancia DEVE dichiarare "throws GiornoException" nella firma.
public class GiornoException extends Exception {
    public GiornoException(String message) {
        super(message);
    }
}
```

> Regola veloce: il testo dice "eccezione controllata" → `extends Exception`.
> Tutto il resto (o niente specificato) → `extends RuntimeException`.

---

## Riepilogo pattern

| Cosa                                                | Come                                     | Perche'                                              |
| --------------------------------------------------- | ---------------------------------------- | ---------------------------------------------------- |
| `abstract class Padre implements Comparable<Padre>` | Dichiarazione classe                     | Abilita ordinamento naturale                         |
| `private final` sui campi                           | Visibilita' + immutabilita'              | Sicurezza e correttezza                              |
| `protected` costruttore                             | Non `public`                             | La classe e' abstract, non si istanzia               |
| `abstract double valoreCaratteristico()`            | Metodo astratto                          | Ogni sottoclasse calcola in modo diverso             |
| `equals` su campo chiave                            | `instanceof` + cast + confronto          | Uguaglianza logica, non di riferimento               |
| `hashCode` con `Objects.hash(...)`                  | Stesso campo di `equals`                 | Coerenza obbligatoria                                |
| `compareTo` con `Double.compare(...)`               | Non sottrarre                            | Evita overflow                                       |
| `toString` con spazio iniziale                      | `" nome: " + ...`                        | Le sottoclassi fanno `"Tipo" + super.toString()`     |
| `private static final COSTANTE`                     | Nelle sottoclassi                        | Valore fisso passato a `super()`                     |
| `super(nome, COSTANTE)`                             | Prima riga costruttore                   | Inizializza i campi del padre                        |
| `@Override` su tutto                                | `valoreCaratteristico`, `toString`, ecc. | Il compilatore verifica che il metodo esista davvero |
