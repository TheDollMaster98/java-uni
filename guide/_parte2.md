---
# PARTE 3 – Eccezioni
---

## 11. Gestione eccezioni: checked vs unchecked

### La gerarchia delle eccezioni

```
Throwable
├── Error (errori gravi della JVM – non gestire)
│   ├── OutOfMemoryError
│   └── StackOverflowError
└── Exception
    ├── IOException          ← CHECKED
    ├── SQLException         ← CHECKED
    ├── ... (tutte checked)
    └── RuntimeException     ← UNCHECKED
        ├── NullPointerException
        ├── IllegalArgumentException
        ├── IndexOutOfBoundsException
        ├── ClassCastException
        └── ... (tutte unchecked)
```

### Checked vs Unchecked – La differenza fondamentale

| Aspetto          | Checked Exception                         | Unchecked Exception                                         |
| ---------------- | ----------------------------------------- | ----------------------------------------------------------- |
| Estende          | `Exception` (direttamente)                | `RuntimeException`                                          |
| Compilatore      | OBBLIGA a dichiararle o catturarle        | NON obbliga                                                 |
| Dichiarazione    | `throws` nella firma del metodo           | Non necessario (ma possibile)                               |
| Significato      | Situazione **prevedibile e recuperabile** | **Errore logico** del programmatore                         |
| Esempi dal corso | `LiquidsException`, `GiornoException`     | `CapacityException`, `SorpresaException`, `EventoException` |

**Regola d'oro per l'esame:**

- **Checked** = il chiamante PUO' e DEVE gestire la situazione (liquidi incompatibili, giorno gia' occupato)
- **Unchecked** = il chiamante ha fatto un errore logico (capacita' superata, sorpresa duplicata)

### Sintassi try-catch-finally

```java
try {
    // codice che potrebbe lanciare eccezioni
    contenitore.versa(altro);
} catch (LiquidsException e) {
    // gestione eccezione checked
    System.out.println("Errore: " + e.getMessage());
} catch (IllegalArgumentException e) {
    // gestione eccezione unchecked (opzionale)
    System.out.println("Parametro non valido: " + e.getMessage());
} finally {
    // eseguito SEMPRE (con o senza eccezione)
    scanner.close();
}
```

### Multi-catch (Java 7+)

```java
try {
    // ...
} catch (GiornoException | EventoException e) {
    System.out.println("\tECCEZIONE: " + e.getMessage());
}
```

### try-with-resources (Java 7+)

Auto-chiude risorse `AutoCloseable` alla fine del blocco try:

```java
try (Scanner sc = new Scanner(System.in)) {
    while (sc.hasNextLine()) {
        // ...
    }
}  // sc.close() chiamato automaticamente
```

### Dichiarare eccezioni con `throws`

```java
// Metodo che lancia checked exception → DEVE dichiararla
public void versa(Contenitore altro) throws LiquidsException {
    if (!compatibili(this, altro)) {
        throw new LiquidsException("liquidi incompatibili");
    }
    // ...
}

// Metodo che lancia unchecked → NON deve dichiararla (ma puo')
public void aggiungi(double quantita) {
    if (quantita > calcolaVolume()) {
        throw new CapacityException("capacita' superata");
    }
    // ...
}
```

### Regole importanti per l'override

In un override, il metodo figlio:

- PUO' dichiarare le stesse eccezioni checked del padre, un sottoinsieme, o nessuna
- NON PUO' aggiungere nuove eccezioni checked (violerebbe il contratto del tipo statico)
- PUO' sempre aggiungere eccezioni unchecked

```java
class Padre {
    void metodo() throws IOException, SQLException { }
}

class Figlio extends Padre {
    @Override
    void metodo() throws IOException { }  // OK: sottoinsieme
    // void metodo() throws Exception { }  // ERRORE! Exception e' piu' ampia
}
```

---

## 12. Eccezioni personalizzate e pattern d'esame

### Come creare un'eccezione personalizzata

```java
// CHECKED: extends Exception
public class LiquidsException extends Exception {
    public LiquidsException(String msg) {
        super(msg);
    }
}

// UNCHECKED: extends RuntimeException
public class CapacityException extends RuntimeException {
    public CapacityException(String msg) {
        super(msg);
    }
}
```

Sono solo 3 righe per ciascuna. All'esame valgono 1 punto ciascuna (2 punti totali).

### Pattern ricorrente nei 3 esami

| Esame       | Checked (extends Exception)                         | Unchecked (extends RuntimeException)     |
| ----------- | --------------------------------------------------- | ---------------------------------------- |
| Simulazione | `LiquidsException` (liquidi incompatibili)          | `CapacityException` (capacita' superata) |
| Dicembre    | `GiornoException` (giorno occupato/vuoto)           | `SorpresaException` (sorpresa duplicata) |
| Gennaio     | `GiornoException` (giorno occupato/vuoto/sbagliato) | `EventoException` (evento duplicato)     |

**Schema:** errore di dominio recuperabile → **checked**; errore logico del chiamante → **unchecked**.

### ATTENZIONE: discrepanza ScalaValutazione vs README

La ScalaValutazione della simulazione ha le eccezioni **invertite** rispetto al README:

- ScalaValutazione dice: LiquidsException = unchecked, CapacityException = checked
- README dice: LiquidsException = checked (dominio), CapacityException = unchecked (logico)

**Segui SEMPRE il README** (il testo dell'esame). La ScalaValutazione ha un refuso.

### Come si usano nell'esame

```java
// Nel COSTRUTTORE: lancia unchecked per errori logici
protected Contenitore(String liquido, double quantita) {
    if (quantita > calcolaVolume()) {
        throw new CapacityException("troppo liquido");  // unchecked: no throws
    }
    this.liquido = liquido;
    this.quantita = quantita;
}

// In un METODO: lancia checked per situazioni di dominio
public void versa(Contenitore altro) throws LiquidsException {
    if (!this.liquido.equals(altro.liquido)) {
        throw new LiquidsException("incompatibili");    // checked: si throws
    }
    // trasferimento...
}

// Nella COLLEZIONE: mix di checked e unchecked
public void inserisci(int giorno, Sorpresa s) throws GiornoException {
    // checked: giorno occupato
    if (caselle[giorno - 1] != null)
        throw new GiornoException("giorno occupato");

    // unchecked: sorpresa duplicata
    for (Sorpresa esistente : caselle) {
        if (esistente != null && esistente.equals(s))
            throw new SorpresaException("gia' presente");
    }
    caselle[giorno - 1] = s;
}

// Nel MAIN: try-catch per gestire checked
try {
    calendario.inserisci(giorno, sorpresa);
} catch (GiornoException e) {
    System.out.println("\tEccezione: " + e.getMessage());
}
// Le unchecked (SorpresaException) vengono catturate nello stesso catch
// oppure separatamente
```

---

# PARTE 4 – Collezioni e Iteratori

---

## 13. Collezioni: List, Set, Map

### Gerarchia delle collezioni

```
Collection (interfaccia)
├── List (interfaccia) → ArrayList, LinkedList
├── Set (interfaccia)  → HashSet, TreeSet
└── Queue (interfaccia) → PriorityQueue

Map (interfaccia separata) → HashMap, TreeMap
```

**Regola: programma ad interfacce (usa `List`, `Set`, `Map` come tipo statico).**

```java
List<String> nomi = new ArrayList<>();   // corretto
ArrayList<String> nomi = new ArrayList<>(); // funziona ma meno flessibile
```

### List / ArrayList

```java
import java.util.*;

List<String> nomi = new ArrayList<>();

nomi.add("Mario");
nomi.add("Luigi");
nomi.add(0, "Peach");         // inserisce in posizione 0

String primo = nomi.get(0);   // "Peach"
int dim = nomi.size();         // 3

nomi.set(1, "Toad");          // sostituisce all'indice 1
nomi.remove(0);               // rimuove per indice
nomi.remove("Luigi");         // rimuove per valore

boolean c = nomi.contains("Mario");  // true
int idx = nomi.indexOf("Mario");     // indice o -1

// Iterare
for (String nome : nomi) {
    System.out.println(nome);
}

// Lista immutabile (Java 9+)
List<String> fissi = List.of("A", "B", "C");  // non modificabile!
```

**ATTENZIONE con iterazione e rimozione:**

```java
// ERRORE: ConcurrentModificationException
for (String s : nomi) {
    if (s.equals("Mario")) nomi.remove(s);
}

// CORRETTO: usa Iterator
Iterator<String> it = nomi.iterator();
while (it.hasNext()) {
    if (it.next().equals("Mario")) it.remove();
}

// CORRETTO: rimuovi con indice al contrario
for (int i = nomi.size() - 1; i >= 0; i--) {
    if (nomi.get(i).equals("Mario")) nomi.remove(i);
}
```

### Set / HashSet / TreeSet

```java
Set<String> colori = new HashSet<>();

colori.add("rosso");
colori.add("blu");
colori.add("rosso");          // IGNORATO – i set non hanno duplicati!

colori.size();                 // 2
colori.contains("blu");       // true
colori.remove("blu");

// TreeSet – ordinato
Set<Integer> numeri = new TreeSet<>();
numeri.add(30); numeri.add(10); numeri.add(20);
// Iterazione: 10, 20, 30 (ordinati!)
```

### Map / HashMap

```java
Map<String, Integer> voti = new HashMap<>();

voti.put("Mario", 28);
voti.put("Luigi", 30);
voti.put("Mario", 25);       // sovrascrive!

int voto = voti.get("Mario");            // 25
int safe = voti.getOrDefault("Peach", 0); // 0

voti.containsKey("Luigi");              // true
voti.containsValue(30);                 // true
voti.remove("Luigi");

// Iterare (IMPORTANTISSIMO PER L'ESAME)
for (Map.Entry<String, Integer> entry : voti.entrySet()) {
    System.out.println(entry.getKey() + " → " + entry.getValue());
}

// Solo chiavi o solo valori
for (String chiave : voti.keySet()) { }
for (int valore : voti.values()) { }
```

### Pattern: contare occorrenze

```java
Map<String, Integer> conteggio = new HashMap<>();
String[] parole = {"ciao", "mondo", "ciao"};
for (String p : parole) {
    conteggio.put(p, conteggio.getOrDefault(p, 0) + 1);
}
```

### Tabella riassuntiva

| Struttura   | Duplicati    | Ordinata | Accesso      | Quando usarla             |
| ----------- | ------------ | -------- | ------------ | ------------------------- |
| `ArrayList` | Si           | Si       | per indice   | Liste di dati             |
| `HashSet`   | No           | No       | `contains()` | Unicita', verifica rapida |
| `TreeSet`   | No           | Si       | `contains()` | Unicita' + ordinamento    |
| `HashMap`   | Chiave unica | No       | per chiave   | Associazioni chiave→val   |
| `TreeMap`   | Chiave unica | Si       | per chiave   | Associazioni ordinate     |

---

## 14. Comparable e Comparator

### Comparable – ordine naturale

```java
public interface Comparable<T> {
    int compareTo(T o);
    // negativo: this < o → this viene PRIMA
    // zero: this == o
    // positivo: this > o → this viene DOPO
}
```

Implementazione nell'esame:

```java
public abstract class Contenitore implements Comparable<Contenitore> {

    public abstract double calcolaVolume();

    @Override
    public int compareTo(Contenitore altro) {
        return Double.compare(this.calcolaVolume(), altro.calcolaVolume());
        // ordine CRESCENTE per volume
    }
}
```

**Per ordine DECRESCENTE:** inverti `this` e `altro`:

```java
return Double.compare(altro.calcolaVolume(), this.calcolaVolume());
```

**ATTENZIONE:** controlla SEMPRE l'esempio d'esecuzione del README per capire se l'ordine e' crescente o decrescente.

### Comparator – ordine esterno

```java
@FunctionalInterface
public interface Comparator<T> {
    int compare(T o1, T o2);
}
```

Quando usarlo: quando vuoi ordinare in modo diverso dall'ordine naturale.

### Comparable vs Comparator

| Aspetto           | `Comparable<T>`                  | `Comparator<T>`                           |
| ----------------- | -------------------------------- | ----------------------------------------- |
| Dove si definisce | **Dentro** la classe da ordinare | **Fuori** dalla classe (oggetto separato) |
| Metodo            | `compareTo(T o)`                 | `compare(T o1, T o2)`                     |
| Quanti ordini?    | **Uno** solo (ordine naturale)   | **Infiniti** (uno per ogni Comparator)    |
| Uso con sort      | `Collections.sort(lista)`        | `lista.sort(comparator)`                  |

### Esempio completo

```java
public class Studente implements Comparable<Studente> {
    private final String nome;
    private final int matricola;
    private final double media;

    public Studente(String nome, int matricola, double media) {
        this.nome = nome;
        this.matricola = matricola;
        this.media = media;
    }

    // ORDINE NATURALE: per matricola crescente
    @Override
    public int compareTo(Studente altro) {
        return Integer.compare(this.matricola, altro.matricola);
    }

    public String getNome() { return nome; }
    public double getMedia() { return media; }
}
```

```java
List<Studente> studenti = new ArrayList<>();
studenti.add(new Studente("Mario", 3, 27.5));
studenti.add(new Studente("Anna", 1, 29.0));
studenti.add(new Studente("Luigi", 2, 24.0));

// 1. Ordine naturale (Comparable)
Collections.sort(studenti);

// 2. Lambda: per nome
studenti.sort((a, b) -> a.getNome().compareTo(b.getNome()));

// 3. Method reference: per media
studenti.sort(Comparator.comparingDouble(Studente::getMedia));

// 4. Ordine inverso
studenti.sort(Comparator.comparingDouble(Studente::getMedia).reversed());

// 5. Multiplo: prima per media (desc), poi per nome (asc)
studenti.sort(
    Comparator.comparingDouble(Studente::getMedia).reversed()
              .thenComparing(Studente::getNome)
);
```

### Comparare tipi primitivi correttamente

```java
// NON fare: return this.valore - altro.valore (overflow possibile!)
// Esempio: Integer.MAX_VALUE - (-1) = overflow negativo!

// Usa i metodi compare delle wrapper classes:
Integer.compare(a, b);   // per int
Double.compare(a, b);    // per double
Long.compare(a, b);      // per long
```

---

## 15. Iterable e Iterator

Questo e' uno dei temi PIU' importanti per l'esame. Ogni esame richiede di implementare `Iterable`.

### Le due interfacce

```java
// Iterable<T> → la COLLEZIONE che puo' essere iterata
public interface Iterable<T> {
    Iterator<T> iterator();   // restituisce un Iterator
}

// Iterator<T> → l'OGGETTO che itera
public interface Iterator<T> {
    boolean hasNext();   // ci sono altri elementi?
    T next();            // restituisce il prossimo e avanza
    void remove();       // rimuove l'ultimo elemento restituito da next()
}
```

### Perche' implementare Iterable?

Per poter usare il costrutto **for-each**:

```java
// Se Vetreria implements Iterable<Contenitore>:
for (Contenitore c : vetreria) {
    System.out.println(c);
}

// Il for-each e' zucchero sintattico per:
Iterator<Contenitore> it = vetreria.iterator();
while (it.hasNext()) {
    Contenitore c = it.next();
    System.out.println(c);
}
```

### Pattern d'esame: iterator() con sort

Questo pattern si ripete in TUTTI gli esami:

```java
public class Vetreria implements Iterable<Contenitore> {
    private final List<Contenitore> contenitori = new ArrayList<>();

    @Override
    public Iterator<Contenitore> iterator() {
        // 1. Crea una COPIA (non modificare l'originale!)
        List<Contenitore> copia = new ArrayList<>(contenitori);

        // 2. Ordina la copia per ordine naturale (compareTo)
        Collections.sort(copia);

        // 3. Restituisci l'iterator della copia
        return copia.iterator();
    }
}
```

**Perche' una copia?** Se ordinassi direttamente `contenitori`, cambieresti l'ordine interno della collezione. La copia preserva l'ordine originale.

### Iterator con array (esami Dicembre e Gennaio)

Quando la struttura interna e' un array con potenziali null:

```java
public class Calendario implements Iterable<Sorpresa> {
    private final Sorpresa[] caselle = new Sorpresa[31];

    @Override
    public Iterator<Sorpresa> iterator() {
        // 1. Raccogli solo gli elementi non-null
        List<Sorpresa> presenti = new ArrayList<>();
        for (Sorpresa s : caselle) {
            if (s != null) presenti.add(s);
        }

        // 2. Ordina per ordine naturale
        Collections.sort(presenti);

        // 3. Restituisci l'iterator
        return presenti.iterator();
    }
}
```

### Iterator.remove() – rimozione sicura durante iterazione

```java
// Metodo estrai nella Vetreria: rimuove contenitori con un certo liquido
public Vetreria estrai(String liquido) {
    Vetreria estratta = new Vetreria();
    Iterator<Contenitore> it = contenitori.iterator();
    while (it.hasNext()) {
        Contenitore c = it.next();
        if (liquido.equals(c.getLiquido())) {
            estratta.aggiungi(c);
            it.remove();           // rimuove dall'originale SENZA ConcurrentModificationException
        }
    }
    return estratta;
}
```

### toString() e iterator() possono avere ordini DIVERSI

Nell'esame di Gennaio (Olimpiadi):

- `toString()` stampa gli eventi in ordine di **giorno** (ordine dell'array)
- `iterator()` restituisce gli eventi in ordine di **durata** (ordine naturale)

```java
public class Olimpiade implements Iterable<Evento> {
    private final Evento[] eventi = new Evento[19];

    // toString: ordine per GIORNO
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Olimpiade Invernale " + anno + ":\n");
        for (int i = 0; i < eventi.length; i++) {
            if (eventi[i] != null)
                sb.append("\t").append(i + 1).append(": ").append(eventi[i]).append("\n");
        }
        return sb.toString();
    }

    // iterator: ordine per DURATA (ordine naturale)
    @Override
    public Iterator<Evento> iterator() {
        List<Evento> presenti = new ArrayList<>();
        for (Evento e : eventi) {
            if (e != null) presenti.add(e);
        }
        Collections.sort(presenti);   // ordina per durata
        return presenti.iterator();
    }
}
```

### Implementare un Iterator personalizzato (classi anonime/locali)

```java
// Con classe anonima
public Iterator<Contenitore> iterator() {
    return new Iterator<Contenitore>() {
        private int i = 0;

        @Override
        public boolean hasNext() { return i < contenitori.size(); }

        @Override
        public Contenitore next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return contenitori.get(i++);
        }
    };
}
```

---

# PARTE 5 – Astrazione e Specifiche

---

## 16. Specifiche formali

### Formato REQUIRES / MODIFIES / EFFECTS

Ogni metodo pubblico dovrebbe avere una specifica:

```
REQUIRES: precondizioni (cosa deve essere vero PRIMA della chiamata)
MODIFIES: cosa viene modificato (this, parametri, System.out...)
EFFECTS:  cosa fa il metodo, cosa restituisce, quali eccezioni lancia
```

### Esempi dal corso

```java
/**
 * REQUIRES: -
 * MODIFIES: this
 * EFFECTS: aggiunge n alla memoria e restituisce il nuovo valore
 */
public double aggiungi(double n) {
    this.memoria += n;
    return this.memoria;
}

/**
 * REQUIRES: -
 * MODIFIES: this, sorgente
 * EFFECTS: versa il liquido da sorgente in this.
 *          Se i liquidi sono incompatibili lancia LiquidsException (nessuna modifica).
 *          Se this ha capienza sufficiente, tutto il liquido viene trasferito.
 *          Altrimenti this viene riempito e il resto rimane in sorgente.
 */
public void versa(Contenitore sorgente) throws LiquidsException { ... }
```

### Procedure totali vs parziali

- **Totale**: funziona per OGNI input (nessun REQUIRES restrittivo). Lancia eccezioni per casi invalidi.
- **Parziale**: ha un `REQUIRES` → se violato, comportamento indefinito.

```java
// PARZIALE (ha REQUIRES)
/**
 * REQUIRES: n > 0
 * EFFECTS: restituisce il fattoriale di n
 */
public static int fattoriale(int n) {
    if (n == 0) return 1;
    return n * fattoriale(n - 1);
}

// TOTALE (nessun REQUIRES, gestisce tutto)
/**
 * REQUIRES: -
 * EFFECTS: restituisce il fattoriale di n;
 *          lancia IllegalArgumentException se n < 0
 */
public static int fattoriale(int n) {
    if (n < 0) throw new IllegalArgumentException("n deve essere >= 0");
    if (n == 0) return 1;
    return n * fattoriale(n - 1);
}
```

All'esame: quasi tutti i metodi sono **totali**.

---

## 17. AF – Funzione di Astrazione

### Teoria

La **Funzione di Astrazione** mappa lo spazio di rappresentazione (campi privati) al valore astratto percepito dall'utente:

- **Suriettiva**: ogni valore astratto ha almeno una rappresentazione
- **Non necessariamente iniettiva**: piu' stati concreti possono dare lo stesso valore astratto

```
Esempio: Set implementato con ArrayList
Rep: [1, 2, 3]  →  AF  →  {1, 2, 3}
Rep: [3, 1, 2]  →  AF  →  {1, 2, 3}
Due stati concreti diversi, stesso valore astratto
```

### Come scrivere una buona AF

```java
public class Contenitore {
    private String liquido;
    private double quantita;

    // AF(this): un contenitore che contiene 'quantita' unita' di 'liquido'.
    //           Se liquido == null, il contenitore e' vuoto.
}

public class Sfera extends Contenitore {
    private double raggio;

    // AF(this): una sfera di raggio 'raggio' contenente i liquidi
    //           descritti dalla AF del supertipo Contenitore
}

public class Vetreria {
    private ArrayList<Contenitore> contenitori;

    // AF(this): l'insieme dei contenitori {contenitori.get(0), ..., contenitori.get(size-1)}
}

public class Calendario {
    private Sorpresa[] caselle;  // 31 caselle

    // AF(this): un calendario di 31 giorni dove il giorno i+1 contiene
    //           caselle[i] (null se vuoto)
}
```

---

## 18. RI – Invariante di Rappresentazione

### Teoria

L'**Invariante di Rappresentazione** e' un predicato booleano sullo stato concreto:

- Definisce il dominio dell'AF: solo gli stati per cui RI = true hanno significato
- Se l'RI e' violato, l'oggetto e' in uno stato corrotto

**Preservazione dell'RI:**

1. I **costruttori** devono stabilire l'RI (base induzione)
2. I **mutatori** devono preservare l'RI (passo induttivo)
3. La **rep exposure** deve essere impedita

### Esempi

```java
public class Contenitore {
    private String liquido;
    private double quantita;

    // RI(this): quantita >= 0
    //           && quantita <= calcolaVolume()
    //           && (liquido == null) == (quantita == 0)
}

public class Sfera extends Contenitore {
    private double raggio;

    // RI(this): raggio > 0 && RI del supertipo soddisfatto
}

public class Vetreria {
    private ArrayList<Contenitore> contenitori;

    // RI(this): contenitori != null
    //           && per ogni i: contenitori.get(i) != null
}

public class Calendario {
    private Sorpresa[] caselle;

    // RI(this): caselle != null && caselle.length == 31
    //           && non esistono due caselle con sorprese equals tra loro
}

public class Olimpiade {
    private Evento[] eventi;
    private int anno;

    // RI(this): eventi != null && eventi.length == 19
    //           && anno > 0
    //           && non esistono due eventi equals tra loro
    //           && se eventi[0] e' Cerimonia, e' di apertura
    //           && se eventi[18] e' Cerimonia, e' di chiusura
}
```

---

## 19. Progettazione ADT e Rep Exposure

### Le 4 cause di Rep Exposure e come prevenirle

| Causa                               | Esempio pericoloso           | Soluzione                              |
| ----------------------------------- | ---------------------------- | -------------------------------------- |
| Getter che restituisce riferimento  | `return elementi;`           | `return new ArrayList<>(elementi);`    |
| Costruttore che salva riferimento   | `this.lista = lista;`        | `this.lista = new ArrayList<>(lista);` |
| Metodo restituisce oggetto mutabile | `return contenitori.get(i);` | Restituire copia                       |
| Campo non privato                   | `public List<T> lista;`      | `private List<T> lista;`               |

### Copia difensiva

```java
public class Inventario {
    private final ArrayList<String> oggetti;

    public Inventario(ArrayList<String> oggetti) {
        this.oggetti = new ArrayList<>(oggetti);  // copia difensiva in input
    }

    public ArrayList<String> getOggetti() {
        return new ArrayList<>(oggetti);           // copia difensiva in output
    }
}
```

### Categorie di operazioni di un ADT

| Categoria       | Funzione                          | Esempio                 |
| --------------- | --------------------------------- | ----------------------- |
| **Creatore**    | Crea nuove istanze                | Costruttore             |
| **Produttore**  | Crea nuove istanze da esistenti   | `clone()`, `trasla()`   |
| **Osservatore** | Restituisce info senza modificare | `getVolume()`, `size()` |
| **Mutatore**    | Modifica lo stato                 | `aggiungi()`, `versa()` |
