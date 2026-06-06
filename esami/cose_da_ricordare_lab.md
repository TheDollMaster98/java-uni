# Cose da ricordare - Esame Lab

Checklist rapida per la parte pratica.

## 1. Leggere il testo

- [ ] Quante classi ci sono?
- [ ] Qual e' la classe padre?
- [ ] La classe padre e' astratta?
- [ ] Ci sono campi comuni a tutti?
- [ ] Ci sono valori che cambiano per sottoclasse? → metodo astratto OPPURE campo passato via `super()`
- [ ] C'e' un contenitore da implementare?
- [ ] Quali eccezioni servono? Checked o unchecked?
- [ ] Ci sono vincoli su tipi specifici in `aggiungi`? (es. "cerimonia solo al giorno 1") → `instanceof`
- [ ] C'e' un `main` di test? C'e' anche un `main` nella classe padre?

## 2. Classe padre astratta

- [ ] `abstract public class Padre implements Comparable<Padre>`
- [ ] OVERVIEW chiara
- [ ] Campi comuni, `private final`
- [ ] Costruttore `protected` (non `public`: la classe e' astratta, non si istanzia direttamente)
- [ ] Validazione: `if (nome == null || nome.isBlank()) throw new IllegalArgumentException(...)`
- [ ] Metodo astratto per il valore variabile tra sottoclassi — OPPURE campo `durata` passato da `super()`
- [ ] `equals` coerente con il campo chiave
- [ ] `hashCode` coerente con `equals`
- [ ] `compareTo` con `Integer.compare(...)` o `Double.compare(...)`
- [ ] `toString` riusato dalle sottoclassi con `super.toString()`
- [ ] `AF` e `RI` come commenti nella classe
- [ ] `main()` nel padre SE il tema lo chiede (crea istanze delle sottoclassi, stampa, confronta)

## 3. Sottoclassi

- [ ] `extends Padre`
- [ ] Costanti proprie `private static final` (es. `private static final int DURATA = 60`)
- [ ] Campi propri `private final`
- [ ] `super(...)` nel costruttore — spesso si passa la costante: `super(nome, DURATA)`
- [ ] Validazione dei parametri specifici nel costruttore
- [ ] `@Override` del metodo astratto (se presente nel padre)
- [ ] `AF` come commento nella classe
- [ ] `toString` che chiama `super.toString()` e aggiunge i campi propri

## 4. Eccezioni

- [ ] Checked = `extends Exception` — serve `throws NomeEccezione` nella firma del metodo
- [ ] Unchecked = `extends RuntimeException` — non serve `throws`
- [ ] Messaggi con prefisso `"\tECCEZIONE: ..."` (tab + ECCEZIONE) — controlla l'esempio d'esecuzione
- [ ] Nel testo controlla se chiedono eccezioni controllate o no

## 5. Contenitore iterabile

- [ ] `implements Iterable<Padre>`
- [ ] Array o lista `private final`
- [ ] `aggiungi(...)` con controlli su posizione, duplicati, vincoli speciali
- [ ] Se ci sono vincoli su tipi specifici: usa `instanceof` + cast dentro `aggiungi`
- [ ] `rimuovi(...)` con controlli + restituzione dell'elemento rimosso
- [ ] `toString()` con `StringBuilder`
- [ ] `iterator()` che ordina con `Collections.sort(...)`
- [ ] `AF` e `RI` come commenti nella classe

## 6. Test.java

- [ ] `Scanner(System.in)` per leggere input
- [ ] `args[0]` per parametri da riga di comando
- [ ] `split(" ")` o `split("\\s+")` per parsare i comandi
- [ ] `try-catch` dentro il ciclo di lettura (non fuori)
- [ ] Catch separati se ci sono piu' tipi di eccezione (`GiornoException`, `EventoException`, ...)
- [ ] `System.out.println(e.getMessage())` per stampare l'eccezione
- [ ] Dopo il ciclo: `println(contenitore)` poi intestazione poi `for-each`
- [ ] `"\t"` davanti a ogni elemento iterato
- [ ] `for-each` sul contenitore per testare `iterator()`

---

## 7. Snippet ricorrenti

### `protected` costruttore nel padre

```java
// Il costruttore del padre e' protected perche' la classe e' abstract:
// non si puo' fare "new Padre(...)" direttamente,
// ma le sottoclassi devono poter chiamare super(...)
protected Padre(String nome, int durata) {
    if (nome == null || nome.isBlank())
        throw new IllegalArgumentException("\tECCEZIONE: nome null o blank");
    this.nome = nome;
    this.durata = durata;
}
```

### Pattern `static final` nelle sottoclassi (alternativa al metodo astratto)

Quando la durata (o altro valore) e' fissa per una sottoclasse o calcolata dai suoi campi,
si usa una costante `static final` e la si passa a `super()`:

```java
public class Cerimonia extends Padre {
    private static final int DURATA = 60;           // costante della sottoclasse
    private static final String APERTURA = "apertura";
    private static final String CHIUSURA = "chiusura";

    private final String tipo;

    public Cerimonia(String nome, String tipo) {
        super(nome, DURATA);    // passa la costante al padre
        if (!tipo.equals(APERTURA) && !tipo.equals(CHIUSURA))
            throw new IllegalArgumentException("\tECCEZIONE: tipo non valido");
        this.tipo = tipo;
    }
}

public class Gara extends Padre {
    private static final int MINUTI_PER_ATLETA = 5; // costante della sottoclasse
    private final int numAtleti;

    public Gara(String nome, int numAtleti) {
        super(nome, numAtleti * MINUTI_PER_ATLETA);  // calcolo passato al padre
        if (numAtleti <= 0)
            throw new IllegalArgumentException("\tECCEZIONE: numAtleti <= 0");
        this.numAtleti = numAtleti;
    }
}
```

- Quando usarlo: quando il tema dice "la durata e' X minuti fissi" (costante) o "la durata e' X per ogni Y" (calcolo).
- Differenza dal metodo astratto: il valore viene memorizzato nel campo `durata` del padre invece di essere calcolato on-demand.

### `compareTo` nel padre

```java
@Override
public int compareTo(Padre other) {
    return Integer.compare(this.getDurata(), other.getDurata());
    // oppure Double.compare(...) se il campo e' double
    // MAI usare this.x - other.x: rischio overflow
}
```

### `equals` e `hashCode`

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || !(o instanceof Padre)) return false;
    Padre other = (Padre) o;   // cast in riga dedicata, non dentro return
    return this.nome.equals(other.nome);
}

@Override
public int hashCode() {
    return java.util.Objects.hash(nome);
}
```

- Usali sempre insieme.
- Usa solo campi `final` stabili.

### `toString` nel padre e nelle sottoclassi

```java
// Nel padre: inizia con spazio cosi' le sottoclassi fanno "Tipo" + super.toString()
@Override
public String toString() {
    return " nome: " + nome + " durata: " + durata;
}

// Nella sottoclasse: prepend del tipo + super + campi propri
@Override
public String toString() {
    return "Cerimonia " + tipo + super.toString();
    // risultato: "Cerimonia apertura nome: X durata: 60"
}
```

### `instanceof` in `aggiungi` per vincoli su tipi specifici

Quando il tema dice "se l'evento e' una Cerimonia di apertura, puo' stare solo al giorno 1":

```java
public void aggiungi(int giorno, Padre elemento) throws GiornoException {
    // controlli standard (posizione, duplicato) ...

    // vincoli specifici sul tipo concreto dell'elemento
    if (elemento instanceof Cerimonia) {
        Cerimonia c = (Cerimonia) elemento;   // cast dopo instanceof
        if (c.getTipo().equals("apertura") && giorno != 1)
            throw new GiornoException("\tECCEZIONE: cerimonia apertura solo al giorno 1");
        if (c.getTipo().equals("chiusura") && giorno != 19)
            throw new GiornoException("\tECCEZIONE: cerimonia chiusura solo all'ultimo giorno");
    }

    slots[giorno - 1] = elemento;
}
```

- `instanceof` controlla il tipo concreto dell'oggetto a runtime.
- Fai sempre il cast in una variabile dedicata prima di accedere ai metodi della sottoclasse.

### Formato dei messaggi di eccezione

Controlla sempre l'esempio d'esecuzione nel testo! Il prof usa tipicamente:

```java
throw new GiornoException("\tECCEZIONE: un evento gia' presente per il giorno");
throw new EventoException("\tECCEZIONE: evento esiste");
```

- Il tab (`\t`) indenta il messaggio di errore nell'output — spesso richiesto per abbinarsi all'esempio.
- Scrivi messaggi descrittivi, non generici.

### `main()` nella classe astratta

Il tema puo' chiedere un `main()` nella classe padre per testare le sottoclassi:

```java
public static void main(String[] args) {
    // MAI "new Padre(...)" — e' abstract!
    Cerimonia apertura = new Cerimonia("Apertura2026", "apertura");
    System.out.println(apertura);

    Gara sci = new Gara("Sci1", 15);
    System.out.println(sci);

    // equals
    System.out.println("Uguali? " + apertura.equals(sci));

    // compareTo: negativo = apertura < sci, positivo = apertura > sci
    System.out.println("Apertura > Sci? " + (apertura.compareTo(sci) > 0));
}
```

### `iterator()` ordinato

```java
@Override
public Iterator<Padre> iterator() {
    List<Padre> presenti = new ArrayList<>();
    for (Padre p : slots)
        if (p != null) presenti.add(p);
    Collections.sort(presenti);   // usa compareTo di Padre
    return presenti.iterator();
}
```

### `Scanner` + `split` + try-catch

```java
Scanner scanner = new Scanner(System.in);
while (scanner.hasNextLine()) {
    String line = scanner.nextLine();
    String[] tokens = line.split(" ");
    if (tokens[0].equals("aggiungi")) {
        int giorno = Integer.parseInt(tokens[1]);
        // parsing specifico DENTRO il branch: "rimuovi" non ha tokens[2]
        if (tokens[2].equals("Cerimonia")) {
            try {
                c.aggiungi(giorno, new Cerimonia(tokens[3], tokens[4]));
            } catch (GiornoException e)  { System.out.println(e.getMessage()); }
              catch (EventoException e)  { System.out.println(e.getMessage()); }
        } else if (tokens[2].equals("Gara")) {
            try {
                c.aggiungi(giorno, new Gara(tokens[3], Integer.parseInt(tokens[4])));
            } catch (GiornoException e)  { System.out.println(e.getMessage()); }
              catch (EventoException e)  { System.out.println(e.getMessage()); }
        }
    } else if (tokens[0].equals("rimuovi")) {
        try {
            c.rimuovi(Integer.parseInt(tokens[1]));
        } catch (GiornoException e) { System.out.println(e.getMessage()); }
    }
}
```

- try-catch **dentro ogni branch**, non uno solo esterno: "rimuovi" cattura solo `GiornoException`, "aggiungi" cattura entrambe.
- `split(" ")` senza `\\s+`, niente `.trim()` — esattamente come negli esami.

### `aggiungi` e `rimuovi` (pattern base)

```java
public void aggiungi(int pos, Padre e) throws GiornoException {
    if (pos < 1 || pos > slots.length)
        throw new GiornoException("\tECCEZIONE: posizione non valida");
    if (slots[pos - 1] != null)
        throw new GiornoException("\tECCEZIONE: un evento gia' presente per il giorno");
    for (Padre x : slots)
        if (x != null && x.equals(e))
            throw new EventoException("\tECCEZIONE: evento esiste");
    // instanceof per vincoli su tipi specifici (vedi sopra)
    slots[pos - 1] = e;
}

public void rimuovi(int pos) throws GiornoException {
    if (pos < 1 || pos > slots.length)
        throw new GiornoException("\tECCEZIONE: posizione non valida");
    if (slots[pos - 1] == null)
        throw new GiornoException("\tECCEZIONE: giorno non esiste");
    slots[pos - 1] = null;
}
```

### `Comparable` + `Collections.sort`

```java
Collections.sort(lista);
// oppure: lista.sort(null);  // null = ordine naturale (compareTo)
```

- Funziona solo se la classe padre implementa `Comparable<Padre>`.

---

## 8. Errori comuni

- [ ] Non fare il parsing dei token prima di sapere il comando (`tok[2]` per "rimuovi" non esiste)
- [ ] Dimenticare `AF` e `RI` come commenti nella classe
- [ ] Off-by-one: array indicizzato da 0, posizioni nel tema da 1 → usare `slots[pos-1]`
- [ ] Lanciare un'eccezione checked senza dichiararla con `throws` nella firma
- [ ] Implementare `equals` senza `hashCode` o viceversa
- [ ] Dimenticare `@Override` su `compareTo`, `equals`, `hashCode`, `toString`, `iterator`
- [ ] `iterator()` che modifica l'array originale invece di lavorare su una copia
- [ ] Costruttore del padre `public` invece di `protected` (non e' un errore fatale ma e' impreciso)
- [ ] Metodo `static` con lo stesso nome di un metodo del padre — `static` nasconde, non sovrascrive
- [ ] `try-catch` fuori dal ciclo while: un'eccezione ferma tutto il programma
- [ ] Dimenticare il `\t` davanti agli elementi nell'output iterato

---

## 9. Scaletta d'esame: mappa pratica per ogni voce di valutazione

- **Classe astratta / Elemento**
  - `abstract public class Elemento implements Comparable<Elemento>`
  - OVERVIEW come commento `/** ... */`
  - Campi `private final`, costruttore `protected`
  - Metodo astratto OPPURE campo passato da `super()` con `static final` nelle sottoclassi
  - `compareTo`: `Integer.compare(...)` o `Double.compare(...)`
  - `clone` (se richiesto): copia difensiva dei campi mutabili
  - AF e RI come commenti

- **Sottoclassi (Cuboide / Sfera / Cerimonia / ecc.)**
  - `extends Elemento` + OVERVIEW
  - Costanti `private static final` (es. `DURATA`, `MINUTI_PER_ATLETA`)
  - Campi propri `private final`
  - Costruttore: `super(nome, COSTANTE)` + validazioni
  - `@Override` del metodo astratto (se presente)
  - AF come commento

- **Contenitore iterabile**
  - `implements Iterable<Elemento>` + OVERVIEW
  - Array `private final`, slot `null` = libero
  - `aggiungi`: posizione, duplicato, vincoli `instanceof`
  - `rimuovi`: posizione, slot vuoto, restituzione elemento
  - `iterator`: copia + sort + `lista.iterator()`
  - AF e RI come commenti

- **Eccezioni**
  - Unchecked = `extends RuntimeException`
  - Checked = `extends Exception` + `throws` nella firma
  - Messaggi con `\tECCEZIONE: ...`

- **Main / Test.java**
  - `args[0]` per parametro riga di comando
  - `Scanner(System.in)` fino a EOF
  - `split(" ")` + parsing dentro il branch del comando
  - `try-catch` dentro il ciclo, catch separati se servono
  - Dopo EOF: `println(contenitore)` + intestazione + `for-each` con `\t`

---

## 10. Template completi per l'esame

Adatta i nomi (`Elemento`, `Cerimonia`, `Gara`, `Contenitore`) a quelli del testo.
Compila con `javac *.java`, esegui con `java Test <parametro>`.

---

### `Elemento.java` — classe astratta, Comparable, equals/hashCode

```java
import java.util.Objects;

/**
 * OVERVIEW: Elemento astratto con un nome univoco e una durata.
 * Due elementi sono uguali se hanno lo stesso nome.
 * Ordine naturale basato sulla durata.
 * Immutabile.
 *
 * AF:  AF(e) = "nome=" + e.nome + ", durata=" + e.durata
 * RI:  nome != null && !nome.isBlank() && durata >= 0
 */
public abstract class Elemento implements Comparable<Elemento> {

    private final String nome;
    private final int durata;

    /**
     * @param nome   non null, non blank
     * @param durata >= 0
     * @throws IllegalArgumentException se i parametri violano il RI
     */
    protected Elemento(String nome, int durata) {
        // protected: la classe e' abstract, nessuno la istanzia direttamente
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("\tECCEZIONE: nome null o blank");
        if (durata < 0)
            throw new IllegalArgumentException("\tECCEZIONE: durata < 0");
        this.nome = nome;
        this.durata = durata;
    }

    public String getNome()  { return nome; }
    public int    getDurata() { return durata; }

    // Due elementi sono uguali se hanno lo stesso nome.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Elemento)) return false;
        Elemento other = (Elemento) o;
        return nome.equals(other.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    // Ordine naturale: durata crescente.
    @Override
    public int compareTo(Elemento other) {
        return Integer.compare(this.durata, other.durata);
    }

    // toString base: inizia con spazio cosi' le sottoclassi fanno "Tipo" + super.toString()
    @Override
    public String toString() {
        return " nome: " + nome + " durata: " + durata;
    }

    /**
     * main() richiesto dal tema: testa le sottoclassi.
     * MAI istanziare la classe astratta ("new Elemento" non compila).
     */
    public static void main(String[] args) {
        Cerimonia apertura = new Cerimonia("Apertura2026", "apertura");
        System.out.println(apertura);

        Gara sci = new Gara("Sci1", 15);
        System.out.println(sci);

        System.out.println("Uguali? " + apertura.equals(sci));
        System.out.println("Apertura > Sci? " + (apertura.compareTo(sci) > 0));
    }
}
```

---

### `Cerimonia.java` e `Gara.java` — sottoclassi concrete

```java
/**
 * OVERVIEW: Cerimonia e' un Elemento con tipo (apertura/chiusura) e durata fissa.
 *
 * AF:  AF(c) = "Cerimonia " + c.tipo + AF_padre(c)
 * RI:  RI_padre(c) && tipo in {"apertura", "chiusura"}
 */
public class Cerimonia extends Elemento {

    // Costante: la durata e' sempre 60 per qualsiasi Cerimonia
    private static final int DURATA = 60;
    private static final String APERTURA = "apertura";
    private static final String CHIUSURA = "chiusura";

    private final String tipo;

    public Cerimonia(String nome, String tipo) {
        super(nome, DURATA);   // passa la costante al padre
        if (!tipo.equals(APERTURA) && !tipo.equals(CHIUSURA))
            throw new IllegalArgumentException("\tECCEZIONE: tipo non valido");
        this.tipo = tipo;
    }

    public String getTipo() { return tipo; }

    @Override
    public String toString() {
        return "Cerimonia " + tipo + super.toString();
        // risultato: "Cerimonia apertura nome: X durata: 60"
    }
}
```

```java
/**
 * OVERVIEW: Gara e' un Elemento con numero di atleti. Durata = 5 min * numAtleti.
 *
 * AF:  AF(g) = "Gara" + AF_padre(g) + " con " + g.numAtleti + " atleti"
 * RI:  RI_padre(g) && numAtleti > 0
 */
public class Gara extends Elemento {

    private static final int MINUTI_PER_ATLETA = 5;

    private final int numAtleti;

    public Gara(String nome, int numAtleti) {
        super(nome, numAtleti * MINUTI_PER_ATLETA);  // calcolo passato al padre
        if (numAtleti <= 0)
            throw new IllegalArgumentException("\tECCEZIONE: numAtleti <= 0");
        this.numAtleti = numAtleti;
    }

    public int getNumAtleti() { return numAtleti; }

    @Override
    public String toString() {
        return "Gara" + super.toString() + " con " + numAtleti + " atleti";
        // risultato: "Gara nome: X durata: 75 con 15 atleti"
    }
}
```

---

### `Contenitore.java` — implements Iterable

```java
import java.util.*;

/**
 * OVERVIEW: Contenitore indicizzato di Elemento, posizioni 1..size.
 * Un solo elemento per posizione, nessun duplicato (stesso nome).
 * Mutabile.
 *
 * AF:  AF(c) = "Contenitore anno " + c.anno + ":\n" +
 *              { "\t" + i + ": " + slots[i-1] | slots[i-1] != null }
 * RI:  slots != null && anno > 0
 *      && per ogni i != j: slots[i] != null && slots[j] != null => !slots[i].equals(slots[j])
 */
public class Contenitore implements Iterable<Elemento> {

    private final int anno;
    private final Elemento[] slots;

    public Contenitore(int anno, int size) {
        if (anno <= 0 || size <= 0)
            throw new IllegalArgumentException("\tECCEZIONE: parametri non validi");
        this.anno = anno;
        this.slots = new Elemento[size];
    }

    /**
     * Aggiunge un elemento in posizione pos (1-based).
     *
     * @throws GiornoException  se pos non valida o gia' occupata (checked)
     * @throws EventoException  se elemento gia' presente (unchecked)
     */
    public void aggiungi(int pos, Elemento e) throws GiornoException {
        if (pos < 1 || pos > slots.length)
            throw new GiornoException("\tECCEZIONE: posizione non valida");
        if (slots[pos - 1] != null)
            throw new GiornoException("\tECCEZIONE: un evento gia' presente per il giorno");
        for (Elemento x : slots)
            if (x != null && x.equals(e))
                throw new EventoException("\tECCEZIONE: evento esiste");

        // vincoli su tipi specifici con instanceof
        if (e instanceof Cerimonia) {
            Cerimonia c = (Cerimonia) e;
            if (c.getTipo().equals("apertura") && pos != 1)
                throw new GiornoException("\tECCEZIONE: cerimonia apertura solo al giorno 1");
            if (c.getTipo().equals("chiusura") && pos != slots.length)
                throw new GiornoException("\tECCEZIONE: cerimonia chiusura solo all'ultimo giorno");
        }

        slots[pos - 1] = e;
    }

    /**
     * Rimuove l'elemento in posizione pos.
     *
     * @throws GiornoException se pos non valida o slot vuoto (checked)
     */
    public void rimuovi(int pos) throws GiornoException {
        if (pos < 1 || pos > slots.length)
            throw new GiornoException("\tECCEZIONE: posizione non valida");
        if (slots[pos - 1] == null)
            throw new GiornoException("\tECCEZIONE: giorno non esiste");
        slots[pos - 1] = null;
    }

    /** Iteratore sugli elementi presenti, ordinati per durata (ordine naturale). */
    @Override
    public Iterator<Elemento> iterator() {
        List<Elemento> presenti = new ArrayList<>();
        for (Elemento e : slots)
            if (e != null) presenti.add(e);
        Collections.sort(presenti);   // usa compareTo di Elemento
        return presenti.iterator();
    }

    /** Stampa per giorno (ordine posizione), non per durata. */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Contenitore anno " + anno + ":\n");
        for (int i = 0; i < slots.length; i++)
            if (slots[i] != null)
                sb.append("\t").append(i + 1).append(": ").append(slots[i]).append('\n');
        return sb.toString();
    }
}
```

---

### Eccezioni

**Unchecked** — non serve `throws` nella firma:

```java
// EventoException.java
public class EventoException extends RuntimeException {
    public EventoException(String msg) { super(msg); }
}
```

**Checked** — serve `throws GiornoException` nella firma di ogni metodo che la lancia:

```java
// GiornoException.java
public class GiornoException extends Exception {
    public GiornoException(String msg) { super(msg); }
}
```

---

### `Test.java` — main con Scanner, split, catch separati

```java
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        // anno da riga di comando: java Test 2026
        Scanner scanner = new Scanner(System.in);
        Contenitore c = new Contenitore(Integer.parseInt(args[0]), 19);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" ");
            if (tokens[0].equals("aggiungi")) {
                int giorno = Integer.parseInt(tokens[1]);
                // parsing DENTRO il branch: "rimuovi" non ha tokens[2]
                if (tokens[2].equals("Cerimonia")) {
                    try {
                        c.aggiungi(giorno, new Cerimonia(tokens[3], tokens[4]));
                    } catch (GiornoException e)  { System.out.println(e.getMessage()); }
                      catch (EventoException e)  { System.out.println(e.getMessage()); }
                } else if (tokens[2].equals("Gara")) {
                    try {
                        c.aggiungi(giorno, new Gara(tokens[3], Integer.parseInt(tokens[4])));
                    } catch (GiornoException e)  { System.out.println(e.getMessage()); }
                      catch (EventoException e)  { System.out.println(e.getMessage()); }
                }
            } else if (tokens[0].equals("rimuovi")) {
                try {
                    c.rimuovi(Integer.parseInt(tokens[1]));
                } catch (GiornoException e) { System.out.println(e.getMessage()); }
            }
        }

        // Dopo EOF: stampa per posizione (toString)
        System.out.println(c);
        // Poi per ordine naturale (iterator)
        System.out.println("Eventi in ordine di durata:");
        for (Elemento e : c)
            System.out.println("\t" + e);
    }
}
```

Compilazione ed esecuzione:

```bash
javac *.java
java Test 2026
```

Esempio di comandi da stdin:

```
aggiungi 1 Cerimonia Apertura2026 apertura
aggiungi 3 Gara Sci1 10
rimuovi 3
aggiungi 19 Cerimonia Chiusura2026 chiusura
```
