---
# PARTE 4 -- Collezioni e Iteratori
---

## 1. Il Java Collections Framework

Il **Java Collections Framework** (JCF) e' l'architettura unificata fornita dal package `java.util` per la rappresentazione e la manipolazione di gruppi di oggetti. Esso definisce un insieme di **interfacce**, **implementazioni** e **algoritmi** che consentono di operare su collezioni in maniera generica e polimorfica.

### Gerarchia delle interfacce

La gerarchia si articola in due radici principali: `Collection<E>` e `Map<K,V>`. La prima modella gruppi di elementi singoli; la seconda modella associazioni chiave-valore.

```
Iterable<E>
  |
Collection<E>          (interfaccia radice per elementi)
  |-- List<E>          (sequenza ordinata, duplicati ammessi)
  |     |-- ArrayList<E>
  |     |-- LinkedList<E>
  |
  |-- Set<E>           (insieme senza duplicati)
  |     |-- HashSet<E>
  |     |-- TreeSet<E>     (ordinato)
  |     |-- LinkedHashSet<E>
  |
  |-- Queue<E>         (coda FIFO)
        |-- PriorityQueue<E>
        |-- Deque<E>   (coda doppia)
              |-- ArrayDeque<E>
              |-- LinkedList<E>

Map<K,V>               (interfaccia separata, NON estende Collection)
  |-- HashMap<K,V>
  |-- TreeMap<K,V>
  |-- LinkedHashMap<K,V>
```

### Principi fondamentali

- **Programmare ad interfacce**: il tipo statico della variabile deve essere l'interfaccia (`List`, `Set`, `Map`), non l'implementazione concreta. Questo garantisce flessibilita' e basso accoppiamento.
- **Generics**: tutte le collezioni sono parametrizzate con tipi generici (`<E>`, `<K,V>`) per garantire type safety a compile-time.
- Le collezioni accettano solo **oggetti** (non tipi primitivi). Per `int`, `double`, ecc. si usano i wrapper: `Integer`, `Double`, ecc.

```java
// CORRETTO: tipo statico = interfaccia
List<String> nomi = new ArrayList<>();

// FUNZIONA ma meno flessibile: tipo statico = implementazione
ArrayList<String> nomi = new ArrayList<>();

// ERRORE: i tipi primitivi non sono ammessi
// List<int> numeri = new ArrayList<>();  // NON COMPILA

// CORRETTO: si usa il wrapper Integer
List<Integer> numeri = new ArrayList<>();
```

### Metodi comuni a tutte le Collection

| Metodo               | Descrizione                                  |
| -------------------- | -------------------------------------------- |
| `add(E e)`           | Aggiunge un elemento                         |
| `remove(Object o)`   | Rimuove un elemento (primo trovato)          |
| `contains(Object o)` | Verifica se l'elemento e' presente           |
| `size()`             | Numero di elementi                           |
| `isEmpty()`          | Restituisce `true` se la collezione e' vuota |
| `clear()`            | Rimuove tutti gli elementi                   |
| `iterator()`         | Restituisce un `Iterator<E>`                 |
| `toArray()`          | Converte in array                            |

---

## 2. ArrayList

`ArrayList<E>` e' l'implementazione piu' utilizzata dell'interfaccia `List<E>`. Internamente si basa su un **array dinamico** che viene ridimensionato automaticamente quando la capacita' e' esaurita. L'accesso per indice avviene in tempo **O(1)**, mentre l'inserimento e la rimozione in posizione arbitraria richiedono **O(n)** a causa dello spostamento degli elementi.

### Creazione e operazioni fondamentali

```java
import java.util.*;

// Creazione di un ArrayList vuoto
List<String> nomi = new ArrayList<>();

// Creazione con capacita' iniziale (ottimizzazione, NON dimensione)
List<String> nomi2 = new ArrayList<>(50);

// Creazione a partire da un'altra collezione (copia)
List<String> copia = new ArrayList<>(nomi);

// Lista immutabile (Java 9+): NON si puo' modificare
List<String> fissi = List.of("Alice", "Bob", "Carlo");
```

### Aggiunta di elementi

```java
List<String> nomi = new ArrayList<>();

nomi.add("Mario");              // aggiunge in coda: ["Mario"]
nomi.add("Luigi");              // aggiunge in coda: ["Mario", "Luigi"]
nomi.add(0, "Peach");           // inserisce in posizione 0: ["Peach", "Mario", "Luigi"]
nomi.addAll(List.of("Toad", "Yoshi"));  // aggiunge tutti: ["Peach","Mario","Luigi","Toad","Yoshi"]
```

### Accesso e modifica

```java
String primo = nomi.get(0);     // "Peach" -- accesso per indice O(1)
nomi.set(1, "Wario");           // sostituisce "Mario" con "Wario" all'indice 1
int dim = nomi.size();          // 5 -- numero di elementi
```

### Ricerca

```java
boolean presente = nomi.contains("Luigi");   // true -- usa equals() internamente
int posizione = nomi.indexOf("Luigi");       // 2 -- indice della prima occorrenza, -1 se assente
int ultima = nomi.lastIndexOf("Luigi");      // ultima occorrenza
```

### Rimozione

```java
nomi.remove(0);                 // rimuove per INDICE: rimuove "Peach"
nomi.remove("Luigi");           // rimuove per VALORE: rimuove la prima occorrenza di "Luigi"

// ATTENZIONE con List<Integer>:
List<Integer> numeri = new ArrayList<>(List.of(10, 20, 30));
numeri.remove(Integer.valueOf(20));  // rimuove il VALORE 20
numeri.remove(0);                    // rimuove l'elemento all'INDICE 0
// Se scrivi numeri.remove(20) con int, Java lo interpreta come indice!
```

### Iterazione

```java
// 1. For-each (il piu' comune e leggibile)
for (String nome : nomi) {
    System.out.println(nome);
}

// 2. For classico con indice
for (int i = 0; i < nomi.size(); i++) {
    System.out.println(nomi.get(i));
}

// 3. Con Iterator esplicito (necessario per rimozione sicura)
Iterator<String> it = nomi.iterator();
while (it.hasNext()) {
    String nome = it.next();
    System.out.println(nome);
}

// 4. forEach con lambda (Java 8+)
nomi.forEach(nome -> System.out.println(nome));
nomi.forEach(System.out::println);  // method reference equivalente
```

### Generics e type safety

```java
// SENZA generics (pre-Java 5) -- pericoloso
List vecchia = new ArrayList();
vecchia.add("stringa");
vecchia.add(42);                    // nessun errore a compile-time
String s = (String) vecchia.get(1); // ClassCastException a RUNTIME!

// CON generics -- sicuro
List<String> moderna = new ArrayList<>();
moderna.add("stringa");
// moderna.add(42);                 // ERRORE DI COMPILAZIONE! Type safety garantita
String s = moderna.get(0);          // nessun cast necessario
```

---

## 3. LinkedList

`LinkedList<E>` implementa sia `List<E>` sia `Deque<E>`. Internamente e' una **lista doppiamente concatenata**: ogni nodo contiene un riferimento al predecessore e al successore. L'inserimento e la rimozione in testa e in coda avvengono in **O(1)**, ma l'accesso per indice richiede **O(n)** perche' si deve scorrere la catena.

### Differenze con ArrayList

| Operazione                      | `ArrayList`    | `LinkedList`  |
| ------------------------------- | -------------- | ------------- |
| Accesso per indice `get(i)`     | **O(1)**       | O(n)          |
| Inserimento in coda `add(e)`    | O(1) ammortiz. | **O(1)**      |
| Inserimento in testa `add(0,e)` | O(n)           | **O(1)**      |
| Rimozione in testa              | O(n)           | **O(1)**      |
| Rimozione in coda               | O(1)           | **O(1)**      |
| Uso di memoria                  | Piu' compatto  | Overhead nodi |

### Quando usare LinkedList

- Quando le operazioni dominanti sono inserimento/rimozione in **testa o coda**.
- Quando serve una struttura **Deque** (coda a doppia estremita').
- **Nella pratica**: `ArrayList` e' quasi sempre preferibile per le prestazioni della cache CPU. Nell'esame, si usa praticamente sempre `ArrayList`.

```java
import java.util.*;

// Come List
List<String> lista = new LinkedList<>();
lista.add("A");
lista.add("B");
lista.add(0, "Z");       // inserimento in testa: O(1)
String primo = lista.get(0);  // "Z" -- ma accesso O(n)!

// Come Deque (coda doppia)
Deque<String> coda = new LinkedList<>();
coda.addFirst("primo");   // inserisce in testa
coda.addLast("ultimo");   // inserisce in coda
String testa = coda.peekFirst();  // osserva la testa senza rimuovere
String rimosso = coda.pollFirst(); // rimuove e restituisce la testa

// Come Stack (pila LIFO)
Deque<Integer> stack = new LinkedList<>();
stack.push(10);           // push = addFirst
stack.push(20);
int top = stack.pop();    // pop = removeFirst, restituisce 20
int cima = stack.peek();  // peek = peekFirst, osserva senza rimuovere
```

### L'interfaccia Deque

`Deque<E>` (Double-Ended Queue) estende `Queue<E>` e definisce operazioni su entrambe le estremita':

| Operazione      | Testa (First)   | Coda (Last)    |
| --------------- | --------------- | -------------- |
| Inserimento     | `addFirst(e)`   | `addLast(e)`   |
| Rimozione       | `removeFirst()` | `removeLast()` |
| Osservazione    | `getFirst()`    | `getLast()`    |
| Senza eccezione | `peekFirst()`   | `peekLast()`   |
| Senza eccezione | `pollFirst()`   | `pollLast()`   |

---

## 4. HashSet

`HashSet<E>` implementa l'interfaccia `Set<E>` e garantisce l'**unicita' degli elementi**: nessun duplicato e' ammesso. Internamente si basa su una **tabella hash** (`HashMap`), il che garantisce operazioni `add`, `remove` e `contains` in tempo **O(1)** ammortizzato. L'ordine di iterazione **non e' garantito** e puo' variare tra esecuzioni diverse.

### Operazioni fondamentali

```java
import java.util.*;

Set<String> colori = new HashSet<>();

colori.add("rosso");       // aggiunge "rosso" -- restituisce true
colori.add("blu");         // aggiunge "blu"
colori.add("rosso");       // IGNORATO: "rosso" gia' presente -- restituisce false

System.out.println(colori.size());        // 2 (non 3!)
System.out.println(colori.contains("blu")); // true -- O(1) grazie alla hash table
colori.remove("blu");      // rimuove "blu"

// Iterazione: ordine NON prevedibile
for (String c : colori) {
    System.out.println(c);  // l'ordine puo' variare!
}
```

### Relazione con equals() e hashCode()

`HashSet` determina l'unicita' degli elementi usando **due metodi** di `Object`:

1. `hashCode()` -- calcola il bucket (posizione nella tabella hash)
2. `equals()` -- risolve le collisioni (verifica uguaglianza effettiva nel bucket)

**Contratto fondamentale:** se `x.equals(y)` allora `x.hashCode() == y.hashCode()`. Se si viola questo contratto, `HashSet` non funziona correttamente.

```java
public class Studente {
    private final String nome;
    private final int matricola;

    public Studente(String nome, int matricola) {
        this.nome = nome;
        this.matricola = matricola;
    }

    // Due studenti sono uguali se hanno la stessa matricola
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Studente)) return false;
        Studente s = (Studente) o;
        return this.matricola == s.matricola;
    }

    // OBBLIGATORIO: coerente con equals
    // Se due studenti hanno stessa matricola, devono avere stesso hashCode
    @Override
    public int hashCode() {
        return Integer.hashCode(matricola);
    }
}

// Ora HashSet funziona correttamente:
Set<Studente> studenti = new HashSet<>();
studenti.add(new Studente("Mario", 123));
studenti.add(new Studente("Mario Rossi", 123));  // stessa matricola → equals → NON aggiunto
System.out.println(studenti.size());  // 1
```

**Errore comune all'esame:** sovrascrivere `equals()` senza sovrascrivere `hashCode()`. In tal caso `HashSet` potrebbe contenere duplicati "logici" perche' li mette in bucket diversi.

---

## 5. TreeSet

`TreeSet<E>` implementa `Set<E>` (piu' precisamente `NavigableSet<E>` che estende `SortedSet<E>`). Mantiene gli elementi **ordinati** secondo l'ordine naturale (`Comparable`) o secondo un `Comparator` fornito al costruttore. Internamente si basa su un albero **Red-Black**, garantendo operazioni `add`, `remove` e `contains` in **O(log n)**.

### Ordinamento naturale con Comparable

```java
import java.util.*;

// TreeSet con ordinamento naturale (gli elementi devono implementare Comparable)
Set<Integer> numeri = new TreeSet<>();
numeri.add(30);
numeri.add(10);
numeri.add(20);
numeri.add(10);      // ignorato: duplicato

// L'iterazione avviene in ordine CRESCENTE
for (int n : numeri) {
    System.out.print(n + " ");  // stampa: 10 20 30
}
```

### Ordinamento con Comparator

```java
// TreeSet con Comparator personalizzato: ordine DECRESCENTE
Set<Integer> decrescenti = new TreeSet<>(Comparator.reverseOrder());
decrescenti.add(30);
decrescenti.add(10);
decrescenti.add(20);

for (int n : decrescenti) {
    System.out.print(n + " ");  // stampa: 30 20 10
}

// TreeSet con Comparator per stringhe per lunghezza
Set<String> perLunghezza = new TreeSet<>(Comparator.comparingInt(String::length));
perLunghezza.add("ciao");     // lunghezza 4
perLunghezza.add("a");        // lunghezza 1
perLunghezza.add("mondo");    // lunghezza 5
perLunghezza.add("sole");     // lunghezza 4 → ATTENZIONE!

System.out.println(perLunghezza.size());  // 3, NON 4!
// "sole" NON viene aggiunto perche' compareTo("ciao") == 0 (stessa lunghezza)
```

### ATTENZIONE: TreeSet usa compareTo per l'uguaglianza!

Questa e' una trappola frequente all'esame e nel filtro teoria. `TreeSet` **non usa `equals()`** per determinare se due elementi sono uguali: usa **`compareTo()` (o `compare()` del Comparator)**. Se `compareTo()` restituisce `0`, `TreeSet` considera i due elementi uguali, anche se `equals()` restituirebbe `false`.

```java
public class Cerimonia extends Evento {
    // Due cerimonie diverse (nomi diversi) ma stessa durata (60 minuti)

    @Override
    public int getDurata() { return 60; }  // tutte le cerimonie durano 60

    // equals si basa sul NOME
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Evento)) return false;
        return this.getNome().equals(((Evento) o).getNome());
    }
}

// Problema con TreeSet:
Evento apertura = new Cerimonia("Apertura", true);   // durata 60
Evento chiusura = new Cerimonia("Chiusura", false);  // durata 60

// compareTo confronta per durata: apertura.compareTo(chiusura) == 0
// TreeSet pensa che siano lo stesso elemento!
TreeSet<Evento> set = new TreeSet<>();
set.add(apertura);
set.add(chiusura);     // NON aggiunto! TreeSet crede sia duplicato
System.out.println(set.size());  // 1, non 2!

// Soluzione: usare un Comparator che differenzi anche a parita' di durata
TreeSet<Evento> setCorretto = new TreeSet<>(
    Comparator.comparingInt(Evento::getDurata)
              .thenComparing(Evento::getNome)  // distingue per nome a parita' di durata
);
setCorretto.add(apertura);
setCorretto.add(chiusura);
System.out.println(setCorretto.size());  // 2, corretto!
```

**Per il filtro teoria:** `TreeSet` e `TreeMap` sono detti "inconsistenti con `equals`" quando `compareTo()` restituisce `0` per oggetti che `equals()` considera diversi. La documentazione Java avverte esplicitamente che l'ordine imposto da un `Comparator`/`Comparable` dovrebbe essere "consistent with equals".

---

## 6. HashMap

`HashMap<K,V>` implementa l'interfaccia `Map<K,V>` e permette di associare **chiavi** a **valori**. Ogni chiave e' unica: inserire una chiave gia' esistente sovrascrive il valore precedente. Internamente si basa su una tabella hash, garantendo operazioni `put`, `get`, `containsKey` e `remove` in **O(1)** ammortizzato. L'ordine di iterazione **non e' garantito**.

### Operazioni fondamentali

```java
import java.util.*;

Map<String, Integer> voti = new HashMap<>();

// Inserimento: put(chiave, valore)
voti.put("Mario", 28);       // associa "Mario" -> 28
voti.put("Luigi", 30);       // associa "Luigi" -> 30
voti.put("Mario", 25);       // SOVRASCRIVE: "Mario" -> 25 (la chiave esiste gia')

// Accesso: get(chiave)
int votoMario = voti.get("Mario");              // 25
Integer votoPeach = voti.get("Peach");           // null (chiave assente)
int votoSafe = voti.getOrDefault("Peach", 0);   // 0 (valore di default se assente)

// Verifica esistenza
boolean haLuigi = voti.containsKey("Luigi");     // true
boolean haTrenta = voti.containsValue(30);       // true

// Rimozione
voti.remove("Luigi");         // rimuove l'associazione "Luigi" -> 30

// Dimensione
int dimensione = voti.size(); // numero di coppie chiave-valore
```

### Iterazione su una Map

L'iterazione su una `Map` richiede di scegliere cosa iterare: le chiavi, i valori, o le coppie chiave-valore (entry).

```java
Map<String, Integer> voti = new HashMap<>();
voti.put("Mario", 28);
voti.put("Luigi", 30);
voti.put("Peach", 27);

// 1. Iterando sulle CHIAVI con keySet()
for (String chiave : voti.keySet()) {
    System.out.println(chiave + " -> " + voti.get(chiave));
}

// 2. Iterando sui VALORI con values()
for (int valore : voti.values()) {
    System.out.println("Voto: " + valore);
}

// 3. Iterando sulle ENTRY con entrySet() -- IL PIU' EFFICIENTE
for (Map.Entry<String, Integer> entry : voti.entrySet()) {
    String chiave = entry.getKey();
    int valore = entry.getValue();
    System.out.println(chiave + " -> " + valore);
}

// 4. Con forEach e lambda (Java 8+)
voti.forEach((chiave, valore) -> System.out.println(chiave + " -> " + valore));
```

### Pattern: conteggio occorrenze

```java
// Contare quante volte compare ogni parola
String testo = "il gatto e il cane e il topo";
String[] parole = testo.split("\\s+");

Map<String, Integer> conteggio = new HashMap<>();
for (String p : parole) {
    // Se la parola non esiste: getOrDefault restituisce 0, quindi 0+1 = 1
    // Se la parola esiste: restituisce il conteggio attuale, quindi +1
    conteggio.put(p, conteggio.getOrDefault(p, 0) + 1);
}
// conteggio = {il=3, gatto=1, e=2, cane=1, topo=1}

// Alternativa con merge (Java 8+)
Map<String, Integer> conteggio2 = new HashMap<>();
for (String p : parole) {
    conteggio2.merge(p, 1, Integer::sum);  // se presente, somma 1
}
```

### Pattern: raggruppamento

```java
// Raggruppare studenti per media
Map<String, List<String>> gruppati = new HashMap<>();

// Per ogni studente, aggiungilo alla lista della sua fascia
String fascia = media >= 27 ? "alto" : "basso";
gruppati.computeIfAbsent(fascia, k -> new ArrayList<>()).add(nome);
// computeIfAbsent: se la chiave non esiste, crea la lista; poi aggiunge
```

### Tabella riassuntiva delle implementazioni Map

| Implementazione | Ordine chiavi         | Complessita' | Quando usarla          |
| --------------- | --------------------- | ------------ | ---------------------- |
| `HashMap`       | Nessun ordine         | O(1) amm.    | Caso generale          |
| `TreeMap`       | Ordine naturale       | O(log n)     | Chiavi ordinate        |
| `LinkedHashMap` | Ordine di inserimento | O(1) amm.    | Preservare inserimento |

---

## 7. Generics

I **Generics** (tipi generici), introdotti in Java 5, consentono di parametrizzare classi, interfacce e metodi con **parametri di tipo**. Questo meccanismo garantisce **type safety** a compile-time eliminando la necessita' di cast espliciti e spostando il rilevamento degli errori di tipo dalla fase di esecuzione alla fase di compilazione.

### Sintassi base

```java
// Classe generica con un parametro di tipo T
public class Coppia<T> {
    private T primo;    // T verra' sostituito dal tipo concreto
    private T secondo;

    public Coppia(T primo, T secondo) {
        this.primo = primo;
        this.secondo = secondo;
    }

    public T getPrimo() { return primo; }
    public T getSecondo() { return secondo; }
}

// Uso con tipo concreto
Coppia<String> coppia1 = new Coppia<>("ciao", "mondo");
String s = coppia1.getPrimo();  // nessun cast necessario

Coppia<Integer> coppia2 = new Coppia<>(1, 2);
int n = coppia2.getPrimo();     // unboxing automatico
```

### Parametri di tipo multipli

```java
// Classe generica con due parametri: K (chiave) e V (valore)
public class Associazione<K, V> {
    private final K chiave;
    private final V valore;

    public Associazione(K chiave, V valore) {
        this.chiave = chiave;
        this.valore = valore;
    }

    public K getChiave() { return chiave; }
    public V getValore() { return valore; }
}

Associazione<String, Integer> voto = new Associazione<>("Mario", 28);
String nome = voto.getChiave();   // "Mario"
int punti = voto.getValore();     // 28
```

### Metodi generici

```java
// Un metodo puo' essere generico indipendentemente dalla classe
public class Utility {

    // <T> prima del tipo di ritorno dichiara il parametro generico del metodo
    public static <T> T primoNonNull(T a, T b) {
        return (a != null) ? a : b;
    }

    // Il tipo viene inferito dagli argomenti
    public static <T> List<T> creaLista(T... elementi) {
        List<T> lista = new ArrayList<>();
        for (T e : elementi) {
            lista.add(e);
        }
        return lista;
    }
}

// Uso: il compilatore inferisce T dagli argomenti
String risultato = Utility.primoNonNull(null, "default");   // T = String
List<Integer> numeri = Utility.creaLista(1, 2, 3);          // T = Integer
```

### Type Erasure (cancellazione del tipo a runtime)

I generics in Java sono implementati tramite **type erasure**: il compilatore verifica i tipi a compile-time, poi **cancella** le informazioni di tipo nel bytecode. A runtime, `List<String>` e `List<Integer>` sono entrambe semplicemente `List`.

```java
// A compile-time:
List<String> stringhe = new ArrayList<>();
List<Integer> numeri = new ArrayList<>();

// A runtime (dopo type erasure):
// Entrambe sono semplicemente ArrayList (senza parametro di tipo)

// Conseguenze pratiche:
// 1. Non si puo' fare instanceof con parametro di tipo
// if (obj instanceof List<String>) { }  // ERRORE DI COMPILAZIONE

// 2. Non si puo' creare un array di tipo generico
// T[] array = new T[10];               // ERRORE DI COMPILAZIONE

// 3. Non si puo' usare T in espressioni statiche
// static T campo;                       // ERRORE DI COMPILAZIONE
```

### Bounded Types (tipi limitati)

Si puo' vincolare un parametro di tipo a essere sottotipo di una certa classe o interfaccia usando `extends` (sia per classi sia per interfacce).

```java
// T deve implementare Comparable<T> -- upper bound
public class Ordinatore<T extends Comparable<T>> {

    public T massimo(T a, T b) {
        // Possiamo chiamare compareTo perche' T extends Comparable<T>
        return (a.compareTo(b) >= 0) ? a : b;
    }

    public void ordina(List<T> lista) {
        Collections.sort(lista);  // possibile perche' T e' Comparable
    }
}

// Uso: String implementa Comparable<String> → OK
Ordinatore<String> ordStr = new Ordinatore<>();
String max = ordStr.massimo("a", "z");  // "z"

// Uso: Integer implementa Comparable<Integer> → OK
Ordinatore<Integer> ordInt = new Ordinatore<>();
int maxN = ordInt.massimo(10, 20);  // 20

// Object NON implementa Comparable → ERRORE DI COMPILAZIONE
// Ordinatore<Object> ordObj = new Ordinatore<>();  // ERRORE
```

### Bounded types multipli

```java
// T deve estendere Comparable E implementare Cloneable
public class Elaboratore<T extends Comparable<T> & Cloneable> {
    // T ha sia compareTo sia clone
}
// NOTA: la classe va prima dell'interfaccia nel bound multiplo
// T extends ClasseBase & Interfaccia1 & Interfaccia2
```

### Wildcards

```java
// ? extends T -- upper bounded wildcard (lettura)
public static double somma(List<? extends Number> numeri) {
    double totale = 0;
    for (Number n : numeri) {
        totale += n.doubleValue();
    }
    return totale;
}
// Accetta List<Integer>, List<Double>, List<Number>, ...

// ? super T -- lower bounded wildcard (scrittura)
public static void aggiungiInteri(List<? super Integer> lista) {
    lista.add(1);
    lista.add(2);
}
// Accetta List<Integer>, List<Number>, List<Object>

// ? -- unbounded wildcard
public static void stampa(List<?> lista) {
    for (Object o : lista) {
        System.out.println(o);
    }
}
// Accetta qualsiasi List

// REGOLA PECS: Producer Extends, Consumer Super
// Se leggi dalla collezione: ? extends T
// Se scrivi nella collezione: ? super T
```

---

## 8. L'interfaccia Comparable\<T\>

L'interfaccia `Comparable<T>` definisce l'**ordine naturale** di una classe. E' dichiarata nel package `java.lang` e contiene un unico metodo: `compareTo(T o)`. Quando una classe implementa `Comparable`, i suoi oggetti possono essere ordinati con `Collections.sort()`, inseriti in `TreeSet`/`TreeMap` e confrontati in modo naturale.

### Il contratto di compareTo

```java
public interface Comparable<T> {
    int compareTo(T o);
}
```

Il valore restituito ha il seguente significato:

| Valore restituito | Significato                   |
| ----------------- | ----------------------------- |
| Negativo (< 0)    | `this` viene **prima** di `o` |
| Zero (== 0)       | `this` e `o` sono equivalenti |
| Positivo (> 0)    | `this` viene **dopo** `o`     |

### Implementazione nell'esame: ordine per valore calcolato

```java
// PATTERN D'ESAME: la classe astratta implementa Comparable
public abstract class Contenitore implements Comparable<Contenitore> {

    public abstract double calcolaVolume();

    @Override
    public int compareTo(Contenitore altro) {
        // Double.compare evita problemi con NaN e overflow
        // Ordine CRESCENTE: this prima, altro dopo
        return Double.compare(this.calcolaVolume(), altro.calcolaVolume());
    }
}
```

```java
public abstract class Sorpresa implements Comparable<Sorpresa> {

    public abstract double getCosto();

    @Override
    public int compareTo(Sorpresa altra) {
        // Double.compare per confronto sicuro di double
        return Double.compare(this.getCosto(), altra.getCosto());
    }
}
```

```java
public abstract class Evento implements Comparable<Evento> {

    public abstract int getDurata();

    @Override
    public int compareTo(Evento altro) {
        // Integer.compare per confronto sicuro di int
        return Integer.compare(this.getDurata(), altro.getDurata());
    }
}
```

### Perche' usare Double.compare e Integer.compare

```java
// NON fare MAI la sottrazione diretta per confrontare:
// return this.valore - altro.valore;

// Problema 1: overflow con int
int a = Integer.MAX_VALUE;  // 2147483647
int b = -1;
int diff = a - b;           // overflow! Diventa NEGATIVO invece che positivo

// Problema 2: confronto impreciso con double
double x = 0.1 + 0.2;      // 0.30000000000000004
double y = 0.3;             // 0.3
// x - y != 0, ma sono "logicamente uguali"

// Soluzione: usare i metodi compare delle wrapper classes
Integer.compare(a, b);      // corretto, gestisce overflow
Double.compare(x, y);       // corretto, gestisce NaN e -0.0
Long.compare(l1, l2);       // per long
```

### Ordine decrescente

```java
@Override
public int compareTo(Contenitore altro) {
    // Per ordine DECRESCENTE: inverti this e altro
    return Double.compare(altro.calcolaVolume(), this.calcolaVolume());
}
```

### Coerenza con equals

Si raccomanda (ma non e' obbligatorio) che `compareTo` sia **coerente con `equals`**: cioe' `x.compareTo(y) == 0` se e solo se `x.equals(y)`. Se questa coerenza non e' rispettata, le strutture dati basate su confronto (`TreeSet`, `TreeMap`) si comportano diversamente da quelle basate su hash (`HashSet`, `HashMap`).

```java
// Esempio di INCOERENZA intenzionale (frequente all'esame):
// equals() confronta per NOME
// compareTo() confronta per COSTO/DURATA/VOLUME
public abstract class Sorpresa implements Comparable<Sorpresa> {
    private final String nome;

    // Due sorprese con nomi diversi ma stesso costo:
    // equals() → false (nomi diversi)
    // compareTo() → 0 (stesso costo)
    // → TreeSet le considera uguali, HashSet le considera diverse!
}
```

---

## 9. L'interfaccia Comparator\<T\>

`Comparator<T>` e' un'interfaccia funzionale (un solo metodo astratto) che definisce un **ordine esterno** a una classe. A differenza di `Comparable`, il `Comparator` non e' implementato dalla classe da ordinare, ma viene fornito come oggetto separato (o lambda) al momento dell'ordinamento. Questo permette di definire **infiniti criteri di ordinamento** per la stessa classe.

### Dichiarazione e metodo compare

```java
@FunctionalInterface
public interface Comparator<T> {
    int compare(T o1, T o2);
    // Stessa semantica di compareTo:
    // negativo → o1 prima di o2
    // zero → equivalenti
    // positivo → o1 dopo o2
}
```

### Creazione di Comparator: dalla classe anonima alla lambda

```java
// 1. Classe anonima (pre-Java 8)
Comparator<String> perLunghezza1 = new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return Integer.compare(a.length(), b.length());
    }
};

// 2. Lambda expression (Java 8+) -- equivalente e piu' concisa
Comparator<String> perLunghezza2 = (a, b) -> Integer.compare(a.length(), b.length());

// 3. Metodo factory Comparator.comparingInt (Java 8+) -- PREFERIBILE
Comparator<String> perLunghezza3 = Comparator.comparingInt(String::length);
```

### Metodi factory statici di Comparator

```java
// Comparator.comparing: crea un comparator a partire da un estrattore di chiave
Comparator<Studente> perNome = Comparator.comparing(Studente::getNome);
Comparator<Studente> perMatricola = Comparator.comparingInt(Studente::getMatricola);
Comparator<Studente> perMedia = Comparator.comparingDouble(Studente::getMedia);

// Comparator.naturalOrder e reverseOrder
Comparator<String> naturale = Comparator.naturalOrder();    // ordine naturale (A, B, C)
Comparator<String> inverso = Comparator.reverseOrder();     // ordine inverso (Z, Y, X)
```

### Concatenazione con thenComparing

`thenComparing` permette di definire un **ordinamento secondario** (e terziario, ecc.) in caso di parita' del primo criterio.

```java
// Ordina per media DECRESCENTE, poi per nome CRESCENTE a parita' di media
Comparator<Studente> composto = Comparator
    .comparingDouble(Studente::getMedia)
    .reversed()                                // media decrescente
    .thenComparing(Studente::getNome);          // nome crescente

List<Studente> studenti = new ArrayList<>();
studenti.add(new Studente("Mario", 1, 28.0));
studenti.add(new Studente("Anna",  2, 30.0));
studenti.add(new Studente("Luigi", 3, 28.0));

studenti.sort(composto);
// Anna (30.0), Luigi (28.0), Mario (28.0) -- a parita' di media, ordine per nome
```

### reversed()

```java
// Invertire un Comparator gia' esistente
Comparator<Contenitore> perVolume = Comparator.comparingDouble(Contenitore::calcolaVolume);
Comparator<Contenitore> perVolumeDesc = perVolume.reversed();  // ordine inverso
```

### Uso con Collections.sort, List.sort e TreeSet

```java
List<Contenitore> lista = new ArrayList<>();
// ... aggiunta elementi ...

// Con Collections.sort (metodo statico)
Collections.sort(lista);                             // ordine naturale (Comparable)
Collections.sort(lista, perVolumeDesc);              // ordine personalizzato (Comparator)

// Con List.sort (metodo di istanza, Java 8+)
lista.sort(Comparator.comparingDouble(Contenitore::calcolaVolume));
lista.sort(Comparator.comparingDouble(Contenitore::calcolaVolume).reversed());

// Con TreeSet: il Comparator va passato al costruttore
TreeSet<Contenitore> ordinati = new TreeSet<>(perVolumeDesc);
ordinati.addAll(lista);
// L'iterazione avviene in ordine di volume decrescente
```

### Comparator vs Comparable: quando usare cosa

| Situazione                                                 | Usa                         |
| ---------------------------------------------------------- | --------------------------- |
| Ordine "ovvio" e unico della classe (es. numeri, stringhe) | `Comparable`                |
| La classe dell'esame ha un ordine naturale definito        | `Comparable`                |
| Serve un ordine diverso dall'ordine naturale               | `Comparator`                |
| Serve un ordine per `TreeSet`/`TreeMap`                    | `Comparator` al costruttore |
| `toString()` e `iterator()` devono usare ordini diversi    | `Comparable` + `Comparator` |

---

## 10. L'interfaccia Iterable\<T\>

L'interfaccia `Iterable<T>` (package `java.lang`) e' la **radice** della gerarchia delle collezioni iterabili. Definisce un unico metodo astratto, `iterator()`, che restituisce un `Iterator<T>`. Qualsiasi classe che implementa `Iterable<T>` puo' essere usata nel costrutto **for-each**.

### Dichiarazione

```java
public interface Iterable<T> {
    Iterator<T> iterator();
}
```

### Perche' implementare Iterable

Implementare `Iterable<T>` permette alla propria classe di essere usata nel for-each, che e' zucchero sintattico per l'uso esplicito di un `Iterator`:

```java
// Se MiaCollezione implements Iterable<Elemento>:
MiaCollezione collezione = new MiaCollezione();

// FOR-EACH (zucchero sintattico)
for (Elemento e : collezione) {
    System.out.println(e);
}

// Il compilatore lo traduce in:
Iterator<Elemento> it = collezione.iterator();
while (it.hasNext()) {
    Elemento e = it.next();
    System.out.println(e);
}
```

### Implementare Iterable in una classe custom

Ogni esame richiede di implementare `Iterable` nella classe "collezione" (Vetreria, Calendario, Olimpiade). Il pattern e' sempre lo stesso:

```java
import java.util.*;

public class Biblioteca implements Iterable<Libro> {
    // La collezione interna (struttura dati privata)
    private final List<Libro> libri = new ArrayList<>();

    public void aggiungi(Libro libro) {
        if (libro == null) throw new NullPointerException("Libro null");
        libri.add(libro);
    }

    // Implementazione di Iterable<Libro>
    @Override
    public Iterator<Libro> iterator() {
        // Pattern d'esame: copia → sort → return copia.iterator()
        List<Libro> copia = new ArrayList<>(libri);   // 1. copia difensiva
        Collections.sort(copia);                       // 2. ordina per ordine naturale
        return copia.iterator();                       // 3. restituisci iterator della copia
    }
}

// Ora si puo' usare nel for-each:
Biblioteca bib = new Biblioteca();
bib.aggiungi(new Libro("Java", 2023));
bib.aggiungi(new Libro("Algoritmi", 2020));

for (Libro l : bib) {
    System.out.println(l);  // iterazione in ordine naturale (per anno, ad esempio)
}
```

### Iterable con array e slot null

Quando la struttura interna e' un array con posizioni potenzialmente nulle (esami Dicembre e Gennaio), bisogna filtrare i null prima di ordinare:

```java
public class Calendario implements Iterable<Sorpresa> {
    private final Sorpresa[] caselle = new Sorpresa[31];

    @Override
    public Iterator<Sorpresa> iterator() {
        // 1. Raccogli solo gli elementi NON null
        List<Sorpresa> presenti = new ArrayList<>();
        for (Sorpresa s : caselle) {
            if (s != null) presenti.add(s);
        }
        // 2. Ordina la copia
        Collections.sort(presenti);
        // 3. Restituisci l'iterator
        return presenti.iterator();
    }
}
```

---

## 11. L'interfaccia Iterator\<T\>

L'interfaccia `Iterator<T>` (package `java.util`) rappresenta l'**oggetto che effettua l'iterazione**. Mantiene internamente un cursore che tiene traccia della posizione corrente nell'attraversamento.

### Dichiarazione

```java
public interface Iterator<T> {
    boolean hasNext();   // restituisce true se ci sono altri elementi
    T next();            // restituisce l'elemento corrente e avanza il cursore
    void remove();       // rimuove l'ultimo elemento restituito da next()
}
```

### Uso esplicito di Iterator

```java
List<String> nomi = new ArrayList<>(List.of("Alice", "Bob", "Carlo"));

Iterator<String> it = nomi.iterator();
while (it.hasNext()) {
    String nome = it.next();        // restituisce l'elemento e avanza
    System.out.println(nome);
}

// Dopo l'ultimo next(), hasNext() restituisce false
// Chiamare next() quando hasNext() e' false lancia NoSuchElementException
```

### Iterator.remove()

Il metodo `remove()` puo' essere chiamato **una sola volta** dopo ogni chiamata a `next()`. Rimuove dalla collezione sottostante l'ultimo elemento restituito da `next()`.

```java
List<String> nomi = new ArrayList<>(List.of("Alice", "Bob", "Carlo", "Anna"));

Iterator<String> it = nomi.iterator();
while (it.hasNext()) {
    String nome = it.next();
    if (nome.startsWith("A")) {
        it.remove();   // rimuove "Alice" e poi "Anna" dalla lista originale
    }
}
System.out.println(nomi);  // [Bob, Carlo]
```

**Regole per `remove()`:**

- Si puo' chiamare solo **dopo** `next()`.
- Si puo' chiamare **una sola volta** per ogni `next()`.
- Chiamare `remove()` senza aver chiamato `next()` lancia `IllegalStateException`.
- Non tutti gli `Iterator` supportano `remove()`: quelli che non lo supportano lanciano `UnsupportedOperationException`.

### ConcurrentModificationException

Se si modifica la collezione **direttamente** (non tramite l'Iterator) durante un'iterazione, Java lancia `ConcurrentModificationException`. Questo meccanismo si chiama **fail-fast**.

```java
List<String> nomi = new ArrayList<>(List.of("Alice", "Bob", "Carlo"));

// ERRORE: modifica diretta durante iterazione for-each
for (String nome : nomi) {
    if (nome.equals("Bob")) {
        nomi.remove(nome);  // ConcurrentModificationException!
    }
}

// ERRORE: anche con Iterator, se modifichi la lista direttamente
Iterator<String> it = nomi.iterator();
while (it.hasNext()) {
    String nome = it.next();
    if (nome.equals("Bob")) {
        nomi.remove(nome);  // ConcurrentModificationException!
        // Bisogna usare it.remove(), NON nomi.remove()
    }
}
```

**Spiegazione tecnica:** `ArrayList` mantiene un contatore `modCount` che viene incrementato ad ogni modifica strutturale (add, remove). L'`Iterator` salva il valore di `modCount` al momento della creazione (`expectedModCount`). Ad ogni chiamata a `next()`, l'Iterator verifica che `modCount == expectedModCount`. Se sono diversi, la collezione e' stata modificata al di fuori dell'Iterator e viene lanciata `ConcurrentModificationException`. Il metodo `Iterator.remove()` aggiorna sia `modCount` sia `expectedModCount`, mantenendoli sincronizzati.

---

## 12. Implementare iterator() con pattern d'esame

Il pattern ricorrente in **tutti gli esami** per l'implementazione di `iterator()` segue tre passi:

1. **Copia**: creare una copia della struttura dati interna (per non alterare l'originale)
2. **Sort**: ordinare la copia secondo l'ordine richiesto
3. **Return**: restituire l'iterator della copia

```
COPIA  →  SORT  →  RETURN copia.iterator()
```

### Caso 1: struttura interna List (Simulazione Vetreria)

```java
public class Vetreria implements Iterable<Contenitore> {
    private final List<Contenitore> contenitori;

    public Vetreria() { contenitori = new ArrayList<>(); }

    public void aggiungi(Contenitore c) {
        if (c == null) throw new NullPointerException();
        contenitori.add(c);
    }

    public Vetreria estrai(String liquido) {
        Vetreria estratta = new Vetreria();
        Iterator<Contenitore> it = contenitori.iterator();
        while (it.hasNext()) {
            Contenitore c = it.next();
            if (liquido.equals(c.getLiquido())) {
                estratta.aggiungi(c);
                it.remove();   // rimozione sicura durante iterazione
            }
        }
        return estratta;
    }

    // PATTERN D'ESAME: copia → sort → return iterator della copia
    @Override
    public Iterator<Contenitore> iterator() {
        List<Contenitore> copia = new ArrayList<>(contenitori);  // 1. COPIA
        Collections.sort(copia);                                  // 2. SORT (ordine naturale: capienza)
        return copia.iterator();                                  // 3. RETURN
    }
}
```

### Caso 2: struttura interna array con null (Esame Dicembre)

Quando la rappresentazione e' un array con slot potenzialmente vuoti, il primo passo include il filtraggio dei null:

```java
public class Calendario implements Iterable<Sorpresa> {
    private final Sorpresa[] caselle = new Sorpresa[31];

    public void inserisci(int giorno, Sorpresa s) throws GiornoException {
        if (giorno < 1 || giorno > 31)
            throw new IllegalArgumentException("Giorno non valido");
        for (Sorpresa esistente : caselle) {
            if (esistente != null && esistente.equals(s))
                throw new SorpresaException("Sorpresa gia' presente");
        }
        if (caselle[giorno - 1] != null)
            throw new GiornoException("Giorno occupato");
        caselle[giorno - 1] = s;
    }

    @Override
    public Iterator<Sorpresa> iterator() {
        List<Sorpresa> ordinate = new ArrayList<>();
        for (Sorpresa s : caselle) {
            if (s != null) ordinate.add(s);       // 1. COPIA (solo non-null)
        }
        Collections.sort(ordinate);                // 2. SORT (per costo)
        return ordinate.iterator();                // 3. RETURN
    }
}
```

### Caso 3: toString() e iterator() con ordini DIVERSI (Esame Gennaio)

Questo e' un caso particolarmente insidioso che ricorre nell'esame di Gennaio. `toString()` stampa gli eventi nell'ordine della struttura dati (per posizione/giorno nell'array), mentre `iterator()` li restituisce in un ordine diverso (per durata, cioe' l'ordine naturale definito da `compareTo`).

> **ATTENZIONE PER L'ESAME:** non dare per scontato che `toString()` e `iterator()` usino lo stesso ordine. Leggi attentamente il testo e l'esempio di esecuzione nel README per capire quale ordine e' richiesto in ciascun caso.

```java
public class Olimpiade implements Iterable<Evento> {
    private final int anno;
    private final Evento[] eventi = new Evento[19];

    public Olimpiade(int anno) { this.anno = anno; }

    // toString: ordine per GIORNO (posizione nell'array)
    // NON usa iterator() -- scorre direttamente l'array
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Olimpiade Invernale " + anno + ":\n");
        for (int i = 0; i < eventi.length; i++) {
            if (eventi[i] != null)
                sb.append("\t").append(i + 1).append(": ").append(eventi[i]).append("\n");
        }
        return sb.toString();
    }

    // iterator: ordine per DURATA (ordine naturale di Evento)
    // DIVERSO da toString!
    @Override
    public Iterator<Evento> iterator() {
        List<Evento> presenti = new ArrayList<>();
        for (Evento e : eventi) {
            if (e != null) presenti.add(e);
        }
        Collections.sort(presenti);           // ordina per durata (compareTo)
        return presenti.iterator();
    }
}
```

```java
// Nel Main, si vede la differenza:
Olimpiade olimpiade = new Olimpiade(2026);
// ... inserimento eventi ...

// toString stampa per GIORNO:
System.out.print(olimpiade);
// Output:
// Olimpiade Invernale 2026:
//     1: Cerimonia apertura nome: Apertura durata: 60
//     3: Gara nome: Slittino durata: 75
//     7: Gara nome: SciAlpino durata: 100

// iterator itera per DURATA:
for (Evento e : olimpiade) {
    System.out.println("\t" + e);
}
// Output:
//     Cerimonia apertura nome: Apertura durata: 60     (durata piu' breve)
//     Gara nome: Slittino durata: 75
//     Gara nome: SciAlpino durata: 100                 (durata piu' lunga)
```

### Perche' si crea una copia?

La copia e' fondamentale per due ragioni:

1. **Non alterare l'ordine interno**: ordinando direttamente `contenitori`, si modificherebbe l'ordine della struttura dati originale. Chiamate successive a `toString()` o ad altre operazioni che dipendono dall'ordine di inserimento sarebbero compromesse.
2. **Protezione dall'esterno**: l'Iterator restituito opera sulla copia. Modifiche alla collezione originale dopo la creazione dell'Iterator non causano `ConcurrentModificationException` (perche' la copia e' indipendente).

### Variante: iterator con Comparator personalizzato

Se l'ordine dell'iteratore non corrisponde all'ordine naturale, si passa un `Comparator` a `Collections.sort`:

```java
@Override
public Iterator<Evento> iterator() {
    List<Evento> presenti = new ArrayList<>();
    for (Evento e : eventi) {
        if (e != null) presenti.add(e);
    }
    // Ordine per NOME invece che per durata
    Collections.sort(presenti, Comparator.comparing(Evento::getNome));
    return presenti.iterator();
}
```

---

## 13. Collections utility

La classe `java.util.Collections` e' una classe di utilita' (tutti metodi statici) che fornisce algoritmi e factory methods per operare su collezioni. Si noti la differenza: `Collection` (singolare) e' l'interfaccia, `Collections` (plurale) e' la classe di utilita'.

### Ordinamento

```java
List<Integer> numeri = new ArrayList<>(List.of(30, 10, 20));

// Ordine naturale (Comparable) -- CRESCENTE per numeri
Collections.sort(numeri);
System.out.println(numeri);  // [10, 20, 30]

// Ordine con Comparator
Collections.sort(numeri, Comparator.reverseOrder());
System.out.println(numeri);  // [30, 20, 10]

// Equivalente con List.sort (Java 8+)
numeri.sort(Comparator.naturalOrder());
```

### Liste non modificabili

```java
// unmodifiableList: crea una VISTA non modificabile di una lista esistente
List<String> originale = new ArrayList<>(List.of("A", "B", "C"));
List<String> nonModificabile = Collections.unmodifiableList(originale);

// nonModificabile.add("D");       // UnsupportedOperationException!
// nonModificabile.set(0, "Z");    // UnsupportedOperationException!

// ATTENZIONE: modificando l'originale, cambia anche la vista
originale.add("D");
System.out.println(nonModificabile);  // [A, B, C, D] -- la vista riflette la modifica!

// Per una copia veramente immutabile (Java 10+):
List<String> copia = List.copyOf(originale);  // copia indipendente e immutabile
```

### Liste speciali

```java
// emptyList: lista vuota immutabile (utile come valore di default)
List<String> vuota = Collections.emptyList();
// vuota.add("X");  // UnsupportedOperationException!

// singletonList: lista con un solo elemento, immutabile
List<String> singola = Collections.singletonList("unico");
// singola.add("X");  // UnsupportedOperationException!

// nCopies: lista immutabile con n copie dello stesso elemento
List<String> cinqueA = Collections.nCopies(5, "A");
System.out.println(cinqueA);  // [A, A, A, A, A]
```

### Inversione e mescolamento

```java
List<Integer> numeri = new ArrayList<>(List.of(1, 2, 3, 4, 5));

// reverse: inverte l'ordine degli elementi
Collections.reverse(numeri);
System.out.println(numeri);  // [5, 4, 3, 2, 1]

// shuffle: mescola casualmente gli elementi
Collections.shuffle(numeri);
System.out.println(numeri);  // [3, 1, 5, 2, 4] (ordine casuale)

// shuffle con seme per riproducibilita'
Collections.shuffle(numeri, new Random(42));
```

### Ricerca e statistiche

```java
List<Integer> numeri = new ArrayList<>(List.of(10, 20, 30, 40, 50));

// min e max (richiedono Comparable o Comparator)
int minimo = Collections.min(numeri);    // 10
int massimo = Collections.max(numeri);   // 50

// max con Comparator personalizzato
String piuLunga = Collections.max(
    List.of("a", "ccc", "bb"),
    Comparator.comparingInt(String::length)
);  // "ccc"

// frequency: conta le occorrenze di un elemento
int volte = Collections.frequency(List.of(1, 2, 1, 3, 1), 1);  // 3

// binarySearch: cerca in una lista ORDINATA (restituisce indice o negativo)
Collections.sort(numeri);
int indice = Collections.binarySearch(numeri, 30);  // 2
int assente = Collections.binarySearch(numeri, 25); // negativo (non trovato)
```

### Swap e fill

```java
List<String> lista = new ArrayList<>(List.of("A", "B", "C", "D"));

// swap: scambia due elementi per indice
Collections.swap(lista, 0, 3);
System.out.println(lista);  // [D, B, C, A]

// fill: riempie tutta la lista con lo stesso valore
Collections.fill(lista, "X");
System.out.println(lista);  // [X, X, X, X]
```

### Riassunto dei metodi piu' usati all'esame

| Metodo                         | Descrizione                | All'esame       |
| ------------------------------ | -------------------------- | --------------- |
| `Collections.sort(list)`       | Ordina per ordine naturale | In `iterator()` |
| `Collections.sort(list, comp)` | Ordina con Comparator      | Ordini multipli |
| `Collections.unmodifiableList` | Vista non modificabile     | Rep exposure    |
| `Collections.emptyList()`      | Lista vuota immutabile     | Valori default  |
| `Collections.reverse(list)`    | Inverte l'ordine           | Raro            |
| `Collections.min/max(list)`    | Minimo/massimo             | Filtro teoria   |

---

## 14. Rimozione sicura durante iterazione con Iterator.remove()

La rimozione di elementi da una collezione **durante un'iterazione** e' uno dei punti piu' critici e piu' frequenti all'esame. Se eseguita in modo scorretto, causa `ConcurrentModificationException`. L'unico modo sicuro e' usare il metodo `remove()` dell'`Iterator`.

### Il problema

```java
List<String> nomi = new ArrayList<>(List.of("Alice", "Bob", "Anna", "Carlo"));

// SBAGLIATO: ConcurrentModificationException
// Il for-each usa implicitamente un Iterator.
// Chiamare nomi.remove() modifica la lista al di fuori dell'Iterator,
// invalidando lo stato interno dell'Iterator (modCount != expectedModCount).
for (String nome : nomi) {
    if (nome.startsWith("A")) {
        nomi.remove(nome);   // ConcurrentModificationException alla prossima iterazione!
    }
}
```

### La soluzione: Iterator.remove()

```java
List<String> nomi = new ArrayList<>(List.of("Alice", "Bob", "Anna", "Carlo"));

// CORRETTO: usa Iterator esplicito + it.remove()
Iterator<String> it = nomi.iterator();
while (it.hasNext()) {
    String nome = it.next();       // PRIMA chiama next()
    if (nome.startsWith("A")) {
        it.remove();                // POI rimuove l'elemento appena restituito da next()
    }
}
System.out.println(nomi);  // [Bob, Carlo]
```

### Pattern d'esame: estrai con rimozione (Simulazione Vetreria)

Il metodo `estrai` della Vetreria e' l'esempio piu' completo di rimozione sicura durante iterazione. Rimuove dalla collezione originale tutti i contenitori con un certo liquido e li restituisce in una nuova Vetreria.

```java
public class Vetreria implements Iterable<Contenitore> {
    private final List<Contenitore> contenitori;

    public Vetreria() { contenitori = new ArrayList<>(); }

    public void aggiungi(Contenitore c) {
        if (c == null) throw new NullPointerException();
        contenitori.add(c);
    }

    /**
     * MODIFIES: this
     * EFFECTS: estrae tutti i contenitori con il liquido specificato,
     *          li rimuove da this e restituisce una nuova Vetreria contenente
     *          gli elementi estratti.
     */
    public Vetreria estrai(String liquido) {
        Vetreria estratta = new Vetreria();                 // nuova Vetreria per i risultati
        Iterator<Contenitore> it = contenitori.iterator();  // Iterator esplicito
        while (it.hasNext()) {
            Contenitore c = it.next();                      // 1. next() per ottenere l'elemento
            if (liquido.equals(c.getLiquido())) {
                estratta.aggiungi(c);                       // 2. aggiunge alla Vetreria estratta
                it.remove();                                // 3. it.remove() rimuove dall'originale
                // ATTENZIONE: si usa it.remove(), NON contenitori.remove(c)
            }
        }
        return estratta;
    }

    @Override
    public Iterator<Contenitore> iterator() {
        List<Contenitore> copia = new ArrayList<>(contenitori);
        Collections.sort(copia);
        return copia.iterator();
    }
}
```

### Rimozione con array: caso diverso

Quando la struttura interna e' un array (Calendario, Olimpiade), la rimozione non richiede `Iterator.remove()` perche' si accede per indice:

```java
public class Calendario implements Iterable<Sorpresa> {
    private final Sorpresa[] caselle = new Sorpresa[31];

    // Qui la rimozione e' per indice: basta mettere a null
    public Sorpresa apri(int giorno) throws GiornoException {
        if (giorno < 1 || giorno > 31)
            throw new IllegalArgumentException("Giorno non valido");
        if (caselle[giorno - 1] == null)
            throw new GiornoException("Nessuna sorpresa per il giorno");
        Sorpresa s = caselle[giorno - 1];
        caselle[giorno - 1] = null;    // rimozione diretta per indice
        return s;
    }
}
```

### Alternative alla rimozione con Iterator (meno usate all'esame)

```java
List<String> nomi = new ArrayList<>(List.of("Alice", "Bob", "Anna", "Carlo"));

// Alternativa 1: ciclo for all'indietro (evita problemi di indice)
for (int i = nomi.size() - 1; i >= 0; i--) {
    if (nomi.get(i).startsWith("A")) {
        nomi.remove(i);
    }
}

// Alternativa 2: removeIf con Predicate (Java 8+) -- la piu' moderna
nomi.removeIf(nome -> nome.startsWith("A"));

// Alternativa 3: creare una nuova lista senza gli elementi da rimuovere
List<String> filtrati = new ArrayList<>();
for (String nome : nomi) {
    if (!nome.startsWith("A")) {
        filtrati.add(nome);
    }
}
```

### Riepilogo: cosa fare e cosa NON fare

| Operazione                                        | Risultato                         |
| ------------------------------------------------- | --------------------------------- |
| `for-each` + `lista.remove()`                     | `ConcurrentModificationException` |
| `Iterator` + `lista.remove()`                     | `ConcurrentModificationException` |
| `Iterator` + `it.remove()`                        | **CORRETTO**                      |
| `for` con indice all'indietro + `lista.remove(i)` | **CORRETTO**                      |
| `lista.removeIf(predicate)`                       | **CORRETTO** (Java 8+)            |
| `for-each` + `lista.add()`                        | `ConcurrentModificationException` |
| Iterazione su copia, modifica su originale        | **CORRETTO** (ma attenzione)      |

**Per l'esame:** usa sempre `Iterator` + `it.remove()`. E' il pattern atteso dalla commissione.

---

### Riepilogo finale della Parte 4

| Concetto                | Interfaccia/Classe   | Metodo chiave             | Pattern d'esame                       |
| ----------------------- | -------------------- | ------------------------- | ------------------------------------- |
| Lista ordinata          | `List` / `ArrayList` | `add`, `get`, `set`       | Struttura interna della collezione    |
| Insieme senza duplicati | `Set` / `HashSet`    | `add`, `contains`         | equals/hashCode coerenti              |
| Insieme ordinato        | `TreeSet`            | `add`, `first`, `last`    | Attenzione: usa compareTo!            |
| Mappa chiave-valore     | `Map` / `HashMap`    | `put`, `get`, `entrySet`  | Conteggio, raggruppamento             |
| Ordine naturale         | `Comparable<T>`      | `compareTo(T)`            | Nella classe astratta base            |
| Ordine esterno          | `Comparator<T>`      | `compare(T, T)`           | Lambda, `comparing`, `thenComparing`  |
| Collezione iterabile    | `Iterable<T>`        | `iterator()`              | `copia → sort → return iterator`      |
| Cursore di iterazione   | `Iterator<T>`        | `hasNext`, `next`         | Rimozione sicura con `it.remove()`    |
| Utilita'                | `Collections`        | `sort`, `unmodifiable...` | `Collections.sort(copia)` in iterator |
| Tipi generici           | `<T>`, `<K,V>`       | --                        | `Comparable<T>`, `Iterable<T>`        |
