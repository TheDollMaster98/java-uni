## 13. Classi Nested (L12 — PDJ Cap. 6.5+)

### 13.1 Panoramica delle Classi Nested

Java permette di definire classi all'interno di altre classi. Ci sono quattro tipi:

| Tipo                     | static? | Accesso a membri esterni | Dove definita      |
| ------------------------ | ------- | ------------------------ | ------------------ |
| Static nested class      | Sì      | Solo membri static       | Corpo della classe |
| Inner class (non-static) | No      | Tutti i membri           | Corpo della classe |
| Local class              | No      | Tutti + variabili locali | Dentro un metodo   |
| Anonymous class          | No      | Tutti + variabili locali | Espressione inline |

### 13.2 Static Nested Class

Una classe statica annidata è associata alla classe esterna, non a un'istanza.
Non ha accesso ai membri di istanza della classe esterna.

```java
public class Universita {
    private String nome;
    private List<Studente> studenti = new ArrayList<>();

    // Static nested class
    public static class Studente {
        private String nome;
        private int matricola;

        public Studente(String nome, int matricola) {
            this.nome = nome;
            this.matricola = matricola;
        }

        public String getNome() { return nome; }
        public int getMatricola() { return matricola; }

        @Override
        public String toString() {
            return nome + " (" + matricola + ")";
        }
    }

    public Universita(String nome) {
        this.nome = nome;
    }

    public void iscrivi(Studente s) {
        studenti.add(s);
    }
}

// Utilizzo: non serve un'istanza di Universita
Universita.Studente s = new Universita.Studente("Alice", 12345);
```

### 13.3 Inner Class (Non-Static Nested Class)

Una inner class è associata a un'istanza della classe esterna.
Ha accesso a tutti i membri (anche private) dell'istanza esterna.

```java
public class Lista<E> {
    private Nodo<E> testa;
    private int dimensione;

    // Inner class: ha accesso ai membri di Lista
    private class Nodo<T> {
        T valore;
        Nodo<T> successivo;

        Nodo(T valore) {
            this.valore = valore;
        }
    }

    // Inner class: iteratore
    private class ListaIterator implements Iterator<E> {
        private Nodo<E> corrente = testa; // accede a 'testa' dell'istanza esterna

        @Override
        public boolean hasNext() {
            return corrente != null;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            E valore = corrente.valore;
            corrente = corrente.successivo;
            return valore;
        }
    }

    public void aggiungiInTesta(E elemento) {
        Nodo<E> nuovo = new Nodo<>(elemento);
        nuovo.successivo = testa;
        testa = nuovo;
        dimensione++;
    }

    public Iterator<E> iterator() {
        return new ListaIterator();
    }
}
```

Per creare un'istanza di una inner class dall'esterno (raro):

```java
Lista<String> lista = new Lista<>();
// Sintassi: istanzaEsterna.new InnerClass()
// Lista<String>.ListaIterator it = lista.new ListaIterator();
// (di solito la inner class è private, quindi non si fa)
```

### 13.4 Local Class

Una local class è definita all'interno di un metodo.
Ha accesso ai membri della classe esterna e alle variabili locali **effectively final**.

```java
public class Ordinamento {

    public static <T> Comparator<T> creaComparatore(
            Function<T, Comparable> chiave, boolean crescente) {

        // Local class definita dentro il metodo
        class ComparatorePersonalizzato implements Comparator<T> {
            // Può accedere a 'chiave' e 'crescente' (effectively final)
            @Override
            @SuppressWarnings("unchecked")
            public int compare(T a, T b) {
                int risultato = chiave.apply(a).compareTo(chiave.apply(b));
                return crescente ? risultato : -risultato;
            }
        }

        return new ComparatorePersonalizzato();
    }
}

// Utilizzo
Comparator<String> perLunghezza = Ordinamento.creaComparatore(
    String::length, true
);
List<String> parole = new ArrayList<>(List.of("ciao", "a", "mondo", "ok"));
parole.sort(perLunghezza);
// ["a", "ok", "ciao", "mondo"]
```

### 13.5 Anonymous Class

Una anonymous class è una local class senza nome, definita e istanziata in una espressione.
Molto usata prima di Java 8 (ora spesso sostituita dalle lambda).

```java
// Anonymous class che implementa un'interfaccia
Comparator<String> comparatore = new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return Integer.compare(a.length(), b.length());
    }
};

// Anonymous class che estende una classe
Runnable task = new Runnable() {
    @Override
    public void run() {
        System.out.println("Esecuzione in thread separato");
    }
};

// Uso inline
List<String> nomi = new ArrayList<>(List.of("Carlo", "Alice", "Bob"));
nomi.sort(new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return a.length() - b.length();
    }
});

// Con Java 8+, le anonymous class con un solo metodo astratto
// (interfacce funzionali) si scrivono meglio con le lambda:
nomi.sort((a, b) -> a.length() - b.length());
nomi.sort(Comparator.comparingInt(String::length));
```

### 13.6 Closure e Variabili Effectively Final

Le local class e anonymous class possono catturare variabili locali del metodo
che le contiene, ma solo se queste sono **effectively final** (assegnate una sola volta).

```java
public class Esempio {

    public Runnable creaContatore(int inizio) {
        // 'inizio' è effectively final (non viene riassegnato)
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("Valore: " + inizio); // OK: cattura 'inizio'
            }
        };
    }

    public Runnable errore() {
        int x = 0;
        x = 5; // x NON è effectively final (riassegnato)
        // return () -> System.out.println(x); // ERRORE DI COMPILAZIONE!
        return null;
    }
}

// La closure "cattura" il valore della variabile al momento della creazione
public List<Runnable> creaAzioni() {
    List<Runnable> azioni = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
        final int indice = i; // effectively final (nuova variabile ad ogni iterazione)
        azioni.add(() -> System.out.println("Azione " + indice));
    }
    return azioni;
    // NOTA: senza 'final int indice = i', usando direttamente 'i'
    // si avrebbe un errore perché 'i' viene riassegnato dal ciclo
}
```

### 13.7 Quando Usare Quale Tipo

- **Static nested class**: quando la classe interna non ha bisogno dell'istanza esterna
  (es. builder pattern, entry di una mappa, tipi ausiliari)

- **Inner class**: quando la classe interna ha bisogno dell'istanza esterna
  (es. iteratori che accedono allo stato della collezione)

- **Local class**: quando la classe serve solo in un metodo e ha bisogno di un nome
  (raro in pratica)

- **Anonymous class**: quando serve un'implementazione usa-e-getta di un'interfaccia
  (prima di Java 8; ora preferire le lambda per interfacce funzionali)

```java
// Esempio pratico: Iterator come inner class
public class Rubrica implements Iterable<String> {
    private String[] contatti;
    private int dim;

    // Inner class: accede a 'contatti' e 'dim' della Rubrica
    private class RubricaIterator implements Iterator<String> {
        private int pos = 0;

        @Override
        public boolean hasNext() {
            return pos < dim; // accede a dim dell'istanza esterna
        }

        @Override
        public String next() {
            if (!hasNext()) throw new NoSuchElementException();
            return contatti[pos++]; // accede a contatti dell'istanza esterna
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new RubricaIterator();
    }
}
```

---

## 14. Polimorfismo (L13 — PDJ Cap. 8)

### 14.1 Cos'è il Polimorfismo

Il **polimorfismo** (dal greco: "molte forme") è la capacità di un'entità
di assumere forme diverse. In Java si manifesta in tre varianti.

### 14.2 Polimorfismo Ad-Hoc

Il polimorfismo ad-hoc si realizza attraverso **overloading** e **overriding**.

#### Overloading (Sovraccarico)

Stesso nome, firme diverse. Risolto a **compile-time** (dispatching statico).

```java
public class Convertitore {
    public static String converti(int valore) {
        return Integer.toString(valore);
    }

    public static String converti(double valore) {
        return String.format("%.2f", valore);
    }

    public static String converti(boolean valore) {
        return valore ? "vero" : "falso";
    }

    public static String converti(int[] valori) {
        return Arrays.toString(valori);
    }
}

// Il compilatore sceglie il metodo basandosi sui tipi degli argomenti
Convertitore.converti(42);         // → "42" (chiama int)
Convertitore.converti(3.14);       // → "3.14" (chiama double)
Convertitore.converti(true);       // → "vero" (chiama boolean)
```

#### Overriding (Ridefinizione)

Stessa firma, classi diverse in gerarchia. Risolto a **runtime** (dispatching dinamico).

```java
public class Forma {
    public String descrizione() {
        return "Forma generica";
    }
}

public class Cerchio extends Forma {
    private double raggio;

    public Cerchio(double raggio) { this.raggio = raggio; }

    @Override
    public String descrizione() {
        return "Cerchio con raggio " + raggio;
    }
}

public class Triangolo extends Forma {
    @Override
    public String descrizione() {
        return "Triangolo";
    }
}

// Dispatching dinamico: il metodo eseguito dipende dal tipo concreto
Forma f1 = new Cerchio(5);
Forma f2 = new Triangolo();
f1.descrizione(); // → "Cerchio con raggio 5.0" (tipo concreto: Cerchio)
f2.descrizione(); // → "Triangolo" (tipo concreto: Triangolo)
```

### 14.3 Polimorfismo per Subtyping

Il polimorfismo per subtyping sfrutta il principio che un sottotipo
può essere usato ovunque sia atteso il supertipo.

```java
public interface Disegnabile {
    void disegna();
}

public class Cerchio implements Disegnabile {
    @Override
    public void disegna() {
        System.out.println("Disegno un cerchio");
    }
}

public class Quadrato implements Disegnabile {
    @Override
    public void disegna() {
        System.out.println("Disegno un quadrato");
    }
}

public class Testo implements Disegnabile {
    private String contenuto;

    public Testo(String contenuto) { this.contenuto = contenuto; }

    @Override
    public void disegna() {
        System.out.println("Disegno: " + contenuto);
    }
}

// Polimorfismo per subtyping: un unico metodo gestisce tutti i Disegnabili
public static void disegnaTutto(List<Disegnabile> elementi) {
    for (Disegnabile d : elementi) {
        d.disegna(); // dispatching dinamico
    }
}

List<Disegnabile> scene = List.of(
    new Cerchio(), new Quadrato(), new Testo("Hello"), new Cerchio()
);
disegnaTutto(scene);
// Disegno un cerchio
// Disegno un quadrato
// Disegno: Hello
// Disegno un cerchio
```

### 14.4 Tipo Apparente vs Tipo Concreto (Approfondimento)

```java
// Tipo apparente: quello dichiarato (usato dal compilatore)
// Tipo concreto: quello effettivo a runtime

Object obj = "stringa";
// Tipo apparente: Object → il compilatore permette solo metodi di Object
// Tipo concreto: String → a runtime, viene eseguito il metodo di String

obj.toString(); // OK: toString è in Object, ma esegue String.toString()
// obj.length(); // ERRORE: length non è in Object (tipo apparente)

// Cast per accedere ai metodi del tipo concreto
if (obj instanceof String s) {
    s.length(); // OK: dopo il cast, tipo apparente è String
}

// Esempio pratico con collezioni
List<Object> lista = new ArrayList<>();
lista.add("stringa");   // autoboxing + upcasting a Object
lista.add(42);           // autoboxing int → Integer → Object
lista.add(3.14);         // autoboxing double → Double → Object

for (Object o : lista) {
    // Tipo apparente: Object → possiamo usare solo metodi di Object
    System.out.println(o.toString()); // dispatching dinamico

    // Per operazioni specifiche: pattern matching
    if (o instanceof String s) {
        System.out.println("Stringa di lunghezza " + s.length());
    } else if (o instanceof Integer i) {
        System.out.println("Intero: " + i * 2);
    }
}
```

### 14.5 Polimorfismo Parametrico (Generics)

Il polimorfismo parametrico permette di scrivere codice che funziona
con qualsiasi tipo, parametrizzando classi e metodi.

```java
// Classe generica: funziona con QUALSIASI tipo T
public class Coppia<A, B> {
    private final A primo;
    private final B secondo;

    public Coppia(A primo, B secondo) {
        this.primo = primo;
        this.secondo = secondo;
    }

    public A getPrimo() { return primo; }
    public B getSecondo() { return secondo; }

    @Override
    public String toString() {
        return "(" + primo + ", " + secondo + ")";
    }
}

Coppia<String, Integer> voto = new Coppia<>("Analisi", 28);
Coppia<String, String> contatto = new Coppia<>("Alice", "alice@mail.com");

// Metodo generico
public static <T> T ultimo(List<T> lista) {
    if (lista.isEmpty()) throw new NoSuchElementException();
    return lista.get(lista.size() - 1);
}

String s = ultimo(List.of("a", "b", "c")); // → "c"
Integer n = ultimo(List.of(1, 2, 3));      // → 3
```

### 14.6 Covarianza e Controvarianza

- **Covarianza**: se S è sottotipo di T, allora Contenitore<S> è sottotipo di Contenitore<T>
  (Java: solo con wildcards `? extends T`)

- **Controvarianza**: se S è sottotipo di T, allora Contenitore<T> è sottotipo di Contenitore<S>
  (Java: solo con wildcards `? super T`)

- **Invarianza**: Contenitore<S> e Contenitore<T> non hanno relazione di sottotipo
  (Java: comportamento di default per i generics)

```java
// Gli array sono COVARIANTI in Java (design problematico)
Object[] arr = new String[3]; // compila
arr[0] = "ciao";              // OK
// arr[1] = 42;               // ArrayStoreException a RUNTIME!

// I generics sono INVARIANTI (più sicuro)
// List<Object> lista = new ArrayList<String>(); // ERRORE di compilazione

// Per ottenere covarianza: wildcards
List<? extends Number> numeri = new ArrayList<Integer>(); // OK
// numeri.add(42); // ERRORE: non posso aggiungere (non so il tipo esatto)
Number n = numeri.get(0); // OK: posso leggere come Number
```

### 14.7 Object come Tipo Universale

`Object` è il supertipo di tutte le classi Java. Ogni oggetto è un Object.

```java
// Prima dei generics (Java < 5), si usava Object
List lista = new ArrayList(); // raw type: contiene Object
lista.add("stringa");
lista.add(42);
String s = (String) lista.get(0); // cast necessario
// String err = (String) lista.get(1); // ClassCastException!

// Object fornisce metodi universali
Object obj = new Punto(3, 4);
obj.toString();     // "Punto[x=3.0, y=4.0]" (se ridefinito)
obj.equals(other);  // confronto
obj.hashCode();     // hash code
obj.getClass();     // Class<?>: informazione sul tipo a runtime
```

---

## 15. Generics (L14)

### 15.1 Classi Generiche

Una **classe generica** è parametrizzata su uno o più tipi:

```java
// Type parameter: T (nome convenzionale)
public class Scatola<T> {
    private T contenuto;

    public Scatola(T contenuto) {
        this.contenuto = contenuto;
    }

    public T getContenuto() { return contenuto; }
    public void setContenuto(T contenuto) { this.contenuto = contenuto; }

    public boolean isVuota() { return contenuto == null; }

    @Override
    public String toString() {
        return "Scatola[" + contenuto + "]";
    }
}

// Type argument: il tipo specifico fornito all'uso
Scatola<String> scatolaStringa = new Scatola<>("Ciao");
Scatola<Integer> scatolaIntero = new Scatola<>(42);

String s = scatolaStringa.getContenuto(); // nessun cast
Integer n = scatolaIntero.getContenuto();
```

Convenzioni per i nomi dei type parameter:

- `T` — Type (tipo generico)
- `E` — Element (parametro di collezioni)
- `K` — Key (chiave di mappe)
- `V` — Value (valore di mappe)
- `N` — Number
- `S, U` — secondo, terzo tipo

```java
// Più type parameters
public class Mappa<K, V> {
    private List<K> chiavi = new ArrayList<>();
    private List<V> valori = new ArrayList<>();

    public void put(K chiave, V valore) {
        int idx = chiavi.indexOf(chiave);
        if (idx >= 0) {
            valori.set(idx, valore);
        } else {
            chiavi.add(chiave);
            valori.add(valore);
        }
    }

    public V get(K chiave) {
        int idx = chiavi.indexOf(chiave);
        return (idx >= 0) ? valori.get(idx) : null;
    }
}
```

### 15.2 Metodi Generici

Un metodo può avere i propri type parameters, indipendenti dalla classe:

```java
public class Utility {

    // Metodo generico: <T> prima del tipo di ritorno
    public static <T> List<T> ripeti(T elemento, int volte) {
        List<T> lista = new ArrayList<>();
        for (int i = 0; i < volte; i++) {
            lista.add(elemento);
        }
        return lista;
    }

    public static <T> void scambia(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Il tipo viene inferito automaticamente
    public static <T> boolean contiene(T[] arr, T target) {
        for (T elem : arr) {
            if (Objects.equals(elem, target)) return true;
        }
        return false;
    }
}

// Utilizzo: il tipo T viene inferito dal compilatore
List<String> lista = Utility.ripeti("ciao", 3); // T = String
List<Integer> numeri = Utility.ripeti(42, 5);   // T = Integer

// Oppure specificato esplicitamente (raro)
List<Double> decimali = Utility.<Double>ripeti(3.14, 2);
```

### 15.3 Type Parameters vs Type Arguments

- **Type parameter** (parametro di tipo): la variabile di tipo nella dichiarazione (`T`, `E`, `K`)
- **Type argument** (argomento di tipo): il tipo concreto fornito all'utilizzo (`String`, `Integer`)

```java
// T è il type parameter
public class Lista<T> { ... }

// String è il type argument
Lista<String> lista = new Lista<>();
```

### 15.4 Bounds (Limiti sui Tipi)

I **bounds** restringono i tipi che possono essere usati come type argument.

#### Upper Bound (extends)

```java
// T deve essere un sottotipo di Number
public static <T extends Number> double somma(List<T> lista) {
    double totale = 0;
    for (T n : lista) {
        totale += n.doubleValue(); // posso usare metodi di Number
    }
    return totale;
}

somma(List.of(1, 2, 3));         // OK: Integer extends Number
somma(List.of(1.5, 2.5));        // OK: Double extends Number
// somma(List.of("a", "b"));     // ERRORE: String non extends Number

// Bounds multipli con &
public static <T extends Comparable<T> & Serializable> T massimo(List<T> lista) {
    T max = lista.get(0);
    for (T elem : lista) {
        if (elem.compareTo(max) > 0) max = elem;
    }
    return max;
}
```

### 15.5 Wildcards

Le **wildcards** (`?`) rappresentano un tipo sconosciuto:

#### Unbounded wildcard (?)

```java
// Accetta qualsiasi List, ma non posso aggiungere elementi
public static void stampa(List<?> lista) {
    for (Object elem : lista) {
        System.out.println(elem);
    }
}

stampa(List.of(1, 2, 3));        // OK
stampa(List.of("a", "b", "c"));  // OK
```

#### Upper bounded wildcard (? extends T)

```java
// Accetta List<Number> e List<sottotipo-di-Number>
public static double somma(List<? extends Number> lista) {
    double totale = 0;
    for (Number n : lista) {
        totale += n.doubleValue();
    }
    return totale;
}

somma(List.of(1, 2, 3));       // List<Integer> → OK
somma(List.of(1.5, 2.5));      // List<Double> → OK

// Posso LEGGERE come Number, ma NON posso SCRIVERE
List<? extends Number> numeri = new ArrayList<Integer>();
Number n = numeri.get(0);  // OK: leggere
// numeri.add(42);         // ERRORE: non posso scrivere
```

#### Lower bounded wildcard (? super T)

```java
// Accetta List<Integer>, List<Number>, List<Object>
public static void aggiungiNumeri(List<? super Integer> lista) {
    lista.add(1);   // OK: posso scrivere Integer
    lista.add(2);
    lista.add(3);
    // Integer n = lista.get(0); // ERRORE: posso solo leggere come Object
    Object o = lista.get(0); // OK
}

List<Number> numeri = new ArrayList<>();
aggiungiNumeri(numeri); // OK: Number super Integer
```

### 15.6 Principio PECS (Producer Extends, Consumer Super)

**PECS** è la regola per decidere quale wildcard usare:

- Se la struttura è un **Producer** (leggo da essa): usa `? extends T`
- Se la struttura è un **Consumer** (scrivo in essa): usa `? super T`

```java
// Esempio: copiare elementi da una sorgente a una destinazione
public static <T> void copia(
    List<? extends T> sorgente,   // PRODUCER: leggo da sorgente → extends
    List<? super T> destinazione  // CONSUMER: scrivo in destinazione → super
) {
    for (T elem : sorgente) {
        destinazione.add(elem);
    }
}

List<Integer> interi = List.of(1, 2, 3);
List<Number> numeri = new ArrayList<>();
copia(interi, numeri);  // OK: Integer extends Number, Number super Integer

// Esempio da Collections:
// Collections.sort(List<T>) usa Comparable<? super T>
// perché T potrebbe avere il compareTo definito in un supertipo
public static <T extends Comparable<? super T>> void sort(List<T> lista) {
    // ...
}
```

### 15.7 Type Erasure

A runtime, i generics vengono **cancellati** (type erasure). Il compilatore:

1. Sostituisce i type parameter con i loro bound (o Object se unbounded)
2. Inserisce cast dove necessario
3. Genera metodi bridge per preservare il polimorfismo

```java
// Cosa scrivi:
public class Scatola<T> {
    private T contenuto;
    public T getContenuto() { return contenuto; }
}
Scatola<String> s = new Scatola<>();
String val = s.getContenuto();

// Cosa viene eseguito dopo type erasure:
public class Scatola {
    private Object contenuto;
    public Object getContenuto() { return contenuto; }
}
Scatola s = new Scatola();
String val = (String) s.getContenuto(); // cast inserito dal compilatore
```

### 15.8 Limitazioni dei Generics (dovute a Type Erasure)

```java
// 1. Non si possono creare istanze di tipo generico
// new T() → ERRORE

// 2. Non si possono creare array di tipo generico
// T[] arr = new T[10]; → ERRORE
// Workaround:
@SuppressWarnings("unchecked")
T[] arr = (T[]) new Object[10];

// 3. Non si possono usare tipi primitivi
// List<int> → ERRORE
List<Integer> lista = new ArrayList<>(); // usa il wrapper

// 4. instanceof non funziona con generics parametrizzati
// if (obj instanceof List<String>) → ERRORE
if (obj instanceof List<?>) { } // OK con wildcard

// 5. Non si può fare overloading basato solo su type arguments
// void metodo(List<String> l) { }
// void metodo(List<Integer> l) { } // ERRORE: stessa erasure → List
```

### 15.9 Raw Types

I **raw types** sono l'utilizzo di classi generiche senza specificare type arguments.
Esistono per retrocompatibilità con codice pre-Java 5.

```java
// Raw type: List senza type argument
List lista = new ArrayList(); // WARNING: raw type
lista.add("stringa");
lista.add(42);  // nessun errore a compile time

String s = (String) lista.get(0); // cast necessario
// String err = (String) lista.get(1); // ClassCastException a runtime!

// SEMPRE preferire i generics
List<String> listaSicura = new ArrayList<>();
listaSicura.add("stringa");
// listaSicura.add(42); // ERRORE DI COMPILAZIONE → catturato subito
```

### 15.10 Comparable e Comparator

Due interfacce fondamentali per definire l'ordinamento:

```java
// Comparable<T>: ordinamento NATURALE (dentro la classe stessa)
public class Studente implements Comparable<Studente> {
    private String nome;
    private double media;

    public Studente(String nome, double media) {
        this.nome = nome;
        this.media = media;
    }

    // Ordinamento naturale: per media decrescente
    @Override
    public int compareTo(Studente other) {
        return Double.compare(other.media, this.media); // decrescente
    }
}

List<Studente> studenti = new ArrayList<>();
studenti.add(new Studente("Alice", 28.5));
studenti.add(new Studente("Bob", 25.0));
studenti.add(new Studente("Carlo", 30.0));
Collections.sort(studenti); // usa compareTo → ordina per media decrescente

// Comparator<T>: ordinamento CUSTOM (esterno alla classe)
Comparator<Studente> perNome = new Comparator<Studente>() {
    @Override
    public int compare(Studente a, Studente b) {
        return a.getNome().compareTo(b.getNome());
    }
};

// Con lambda
Comparator<Studente> perNomeLambda = (a, b) -> a.getNome().compareTo(b.getNome());

// Con method reference
Comparator<Studente> perNomeRef = Comparator.comparing(Studente::getNome);

// Composizione di Comparator
Comparator<Studente> perMediaPoiNome = Comparator
    .comparingDouble(Studente::getMedia).reversed()
    .thenComparing(Studente::getNome);

studenti.sort(perMediaPoiNome);
```

---

## 16. Programmazione Funzionale (L15)

### 16.1 Il Paradigma Funzionale

La **programmazione funzionale** è un paradigma che tratta le computazioni
come valutazione di funzioni matematiche, evitando stato mutabile ed effetti collaterali.

Concetti chiave:

- **Trasparenza referenziale**: un'espressione può essere sostituita dal suo valore
  senza cambiare il comportamento del programma
- **Funzioni pure**: dato lo stesso input, restituiscono sempre lo stesso output
  e non hanno effetti collaterali
- **Funzioni come cittadini di prima classe**: le funzioni sono valori che possono
  essere assegnati a variabili, passati come argomenti, restituiti da funzioni

### 16.2 Trasparenza Referenziale

```java
// Funzione PURA (referenzialmente trasparente)
public static int quadrato(int x) {
    return x * x; // stesso input → stesso output, nessun effetto collaterale
}
// quadrato(5) può essere SEMPRE sostituito con 25

// Funzione NON pura (NON referenzialmente trasparente)
private int contatore = 0;
public int prossimoId() {
    return ++contatore; // dipende dallo stato, ha effetti collaterali
}
// prossimoId() restituisce valori diversi ad ogni chiamata
```

### 16.3 Interfaccia Function<I, O>

Java 8 introduce le interfacce funzionali nel package `java.util.function`.
L'interfaccia `Function<T, R>` rappresenta una funzione da T a R.

```java
import java.util.function.Function;

// Creare funzioni come oggetti
Function<Integer, Integer> doppio = x -> x * 2;
Function<String, Integer> lunghezza = s -> s.length();
Function<Integer, String> inStringa = n -> "Numero: " + n;

// Applicare la funzione
int risultato = doppio.apply(5);       // 10
int len = lunghezza.apply("ciao");     // 4
String s = inStringa.apply(42);        // "Numero: 42"

// Usare funzioni come parametri
public static <T, R> List<R> mappa(List<T> lista, Function<T, R> funzione) {
    List<R> risultato = new ArrayList<>();
    for (T elem : lista) {
        risultato.add(funzione.apply(elem));
    }
    return risultato;
}

List<String> nomi = List.of("Alice", "Bob", "Carlo");
List<Integer> lunghezze = mappa(nomi, String::length); // [5, 3, 5]
List<String> maiuscoli = mappa(nomi, String::toUpperCase); // [ALICE, BOB, CARLO]
```

### 16.4 BiFunction e TriFunction

```java
import java.util.function.BiFunction;

// BiFunction<T, U, R>: funzione da (T, U) a R
BiFunction<Integer, Integer, Integer> somma = (a, b) -> a + b;
BiFunction<String, Integer, String> ripeti = (s, n) -> s.repeat(n);

somma.apply(3, 4);        // 7
ripeti.apply("ab", 3);    // "ababab"

// TriFunction non esiste in Java standard, ma si può definire:
@FunctionalInterface
public interface TriFunction<A, B, C, R> {
    R apply(A a, B b, C c);
}

TriFunction<Integer, Integer, Integer, Integer> sommaTre = (a, b, c) -> a + b + c;
sommaTre.apply(1, 2, 3); // 6
```

### 16.5 Espressioni Lambda

Le **lambda** sono funzioni anonime che implementano interfacce funzionali
(interfacce con un solo metodo astratto).

```java
// Sintassi lambda
// (parametri) -> espressione
// (parametri) -> { blocco di istruzioni; }

// Senza parametri
Runnable saluta = () -> System.out.println("Ciao!");

// Un parametro (parentesi opzionali)
Function<String, String> maiuscolo = s -> s.toUpperCase();

// Più parametri
BiFunction<Integer, Integer, Integer> max = (a, b) -> Math.max(a, b);

// Blocco con return esplicito
Function<Integer, String> classifica = voto -> {
    if (voto >= 28) return "Ottimo";
    else if (voto >= 24) return "Buono";
    else if (voto >= 18) return "Sufficiente";
    else return "Insufficiente";
};

// Method reference (scorciatoia per lambda)
Function<String, String> upper1 = s -> s.toUpperCase();     // lambda
Function<String, String> upper2 = String::toUpperCase;       // method reference

// Tipi di method reference:
// 1. Riferimento a metodo statico: Classe::metodoStatico
Function<String, Integer> parse = Integer::parseInt;

// 2. Riferimento a metodo di istanza (su tipo): Tipo::metodoIstanza
Function<String, String> upper = String::toUpperCase;

// 3. Riferimento a metodo di istanza (su oggetto): oggetto::metodo
String prefisso = "Sig. ";
Function<String, String> aggiungiPrefisso = prefisso::concat;

// 4. Riferimento a costruttore: Classe::new
Function<String, StringBuilder> creaSB = StringBuilder::new;
```

### 16.6 Composizione di Funzioni

`Function` fornisce metodi per comporre funzioni:

```java
Function<Integer, Integer> doppio = x -> x * 2;
Function<Integer, Integer> piuTre = x -> x + 3;

// compose: prima applica l'argomento, poi this
// doppio.compose(piuTre) = doppio(piuTre(x)) = (x + 3) * 2
Function<Integer, Integer> piuTrePoiDoppio = doppio.compose(piuTre);
piuTrePoiDoppio.apply(5); // (5 + 3) * 2 = 16

// andThen: prima applica this, poi l'argomento
// doppio.andThen(piuTre) = piuTre(doppio(x)) = x * 2 + 3
Function<Integer, Integer> doppioPpiuTre = doppio.andThen(piuTre);
doppioPpiuTre.apply(5); // 5 * 2 + 3 = 13

// Catena di composizioni
Function<String, String> pipeline = ((Function<String, String>) String::trim)
    .andThen(String::toLowerCase)
    .andThen(s -> s.replace(" ", "_"));

pipeline.apply("  Ciao Mondo  "); // "ciao_mondo"
```

### 16.7 Currying

Il **currying** è la trasformazione di una funzione con n argomenti
in una catena di n funzioni ciascuna con un argomento.

```java
// Funzione normale a 2 argomenti
BiFunction<Integer, Integer, Integer> somma = (a, b) -> a + b;
somma.apply(3, 4); // 7

// Versione curricata: funzione che restituisce una funzione
Function<Integer, Function<Integer, Integer>> sommaCurry = a -> b -> a + b;
sommaCurry.apply(3).apply(4); // 7

// Il vantaggio: applicazione parziale
Function<Integer, Integer> piuTre = sommaCurry.apply(3);
piuTre.apply(4);  // 7
piuTre.apply(10); // 13

// Esempio pratico: formattazione con prefisso
Function<String, Function<String, String>> formatta =
    prefisso -> messaggio -> prefisso + ": " + messaggio;

Function<String, String> errore = formatta.apply("ERRORE");
Function<String, String> info = formatta.apply("INFO");

errore.apply("File non trovato");  // "ERRORE: File non trovato"
info.apply("Connessione riuscita"); // "INFO: Connessione riuscita"

// Currying di funzione a 3 argomenti
Function<Integer, Function<Integer, Function<Integer, Integer>>> sommaTreCurry =
    a -> b -> c -> a + b + c;

sommaTreCurry.apply(1).apply(2).apply(3); // 6

// Applicazione parziale
Function<Integer, Function<Integer, Integer>> sommaConUno = sommaTreCurry.apply(1);
Function<Integer, Integer> sommaConUnoEDue = sommaConUno.apply(2);
sommaConUnoEDue.apply(3); // 6
```

### 16.8 Altre Interfacce Funzionali

```java
import java.util.function.*;

// Predicate<T>: T → boolean
Predicate<Integer> isPari = n -> n % 2 == 0;
isPari.test(4);  // true
isPari.test(3);  // false

// Composizione di predicati
Predicate<Integer> isPositivo = n -> n > 0;
Predicate<Integer> isPariEPositivo = isPari.and(isPositivo);
Predicate<Integer> isPariOPositivo = isPari.or(isPositivo);
Predicate<Integer> isDispari = isPari.negate();

// Consumer<T>: T → void (effetti collaterali)
Consumer<String> stampa = System.out::println;
stampa.accept("Ciao"); // stampa "Ciao"

// Supplier<T>: () → T (factory)
Supplier<List<String>> creaLista = ArrayList::new;
List<String> nuovaLista = creaLista.get();

// UnaryOperator<T>: T → T (caso speciale di Function<T,T>)
UnaryOperator<String> maiuscolo2 = String::toUpperCase;
```

### 16.9 Stream API

Gli **Stream** permettono di elaborare sequenze di dati in modo dichiarativo e funzionale.
Uno Stream non modifica la sorgente, produce un nuovo risultato.

```java
import java.util.stream.*;

List<String> nomi = List.of("Alice", "Bob", "Carlo", "Diana", "Eva");

// Pipeline: sorgente → operazioni intermedie → operazione terminale

// filter: filtra elementi secondo un predicato
List<String> nomiLunghi = nomi.stream()
    .filter(n -> n.length() > 3)
    .collect(Collectors.toList());
// ["Alice", "Carlo", "Diana"]

// map: trasforma ogni elemento
List<Integer> lunghezze = nomi.stream()
    .map(String::length)
    .collect(Collectors.toList());
// [5, 3, 5, 5, 3]

// distinct: rimuove duplicati
List<Integer> unici = lunghezze.stream()
    .distinct()
    .collect(Collectors.toList());
// [5, 3]

// sorted: ordina
List<String> ordinati = nomi.stream()
    .sorted()
    .collect(Collectors.toList());
// ["Alice", "Bob", "Carlo", "Diana", "Eva"]

// count: conta gli elementi
long quanti = nomi.stream()
    .filter(n -> n.length() > 3)
    .count();
// 3

// allMatch: verifica se tutti soddisfano il predicato
boolean tuttiCorpi = nomi.stream()
    .allMatch(n -> n.length() > 0);
// true

// anyMatch: verifica se almeno uno soddisfa
boolean qualcunoLungo = nomi.stream()
    .anyMatch(n -> n.length() > 4);
// true

// forEach: applica un'azione ad ogni elemento (terminale)
nomi.stream()
    .filter(n -> n.startsWith("A"))
    .forEach(System.out::println);
// Alice

// reduce: riduce lo stream a un singolo valore
int sommaLunghezze = nomi.stream()
    .map(String::length)
    .reduce(0, Integer::sum);
// 21

// Esempio complesso: pipeline multipla
Map<Integer, List<String>> perLunghezza = nomi.stream()
    .collect(Collectors.groupingBy(String::length));
// {3=[Bob, Eva], 5=[Alice, Carlo, Diana]}
```

### 16.10 Creazione di Stream

```java
// Da collezione
List<Integer> lista = List.of(1, 2, 3);
Stream<Integer> s1 = lista.stream();

// Da array
int[] arr = {1, 2, 3};
IntStream s2 = Arrays.stream(arr);

// Da valori
Stream<String> s3 = Stream.of("a", "b", "c");

// Stream infinito con generatore
Stream<Integer> naturali = Stream.iterate(0, n -> n + 1);
// ATTENZIONE: è infinito! Usare limit()
naturali.limit(10).forEach(System.out::println); // 0, 1, 2, ..., 9

// Stream con generate
Stream<Double> casuali = Stream.generate(Math::random);
casuali.limit(5).forEach(System.out::println);
```

---

## 17. Design Pattern (L16)

### 17.1 Cosa Sono i Design Pattern

I **design pattern** sono soluzioni ricorrenti a problemi comuni nella progettazione
del software. Ogni pattern descrive un problema, una soluzione e le conseguenze
dell'applicazione della soluzione.

Categorie principali:

- **Creazionali**: gestiscono la creazione di oggetti (Singleton, Factory, Builder, ...)
- **Strutturali**: gestiscono la composizione di classi e oggetti (Decorator, Adapter, Facade, ...)
- **Comportamentali**: gestiscono le interazioni tra oggetti (Observer, Strategy, Iterator, ...)

### 17.2 Pattern Architetturali (Cenni)

I pattern architetturali definiscono la struttura ad alto livello dell'applicazione:

- **MVC (Model-View-Controller)**: separa dati (Model), presentazione (View) e logica di controllo (Controller)
- **MVP (Model-View-Presenter)**: il Presenter media tra Model e View, la View è passiva
- **MVVM (Model-View-ViewModel)**: il ViewModel espone dati osservabili a cui la View si binda

```
MVC:
┌───────┐     ┌────────────┐     ┌──────┐
│ Model │ ←── │ Controller │ ←── │ View │
│       │ ──→ │            │ ──→ │      │
└───────┘     └────────────┘     └──────┘
  (dati)     (logica controllo)   (UI)
```

### 17.3 Singleton Pattern

Il **Singleton** garantisce che una classe abbia una sola istanza
e fornisce un punto di accesso globale ad essa.

Elementi:

- Costruttore **privato**: impedisce la creazione di istanze dall'esterno
- Campo **statico** che contiene l'unica istanza
- Metodo **statico** `getInstance()` per ottenere l'istanza

```java
public class Logger {
    // Unica istanza (eager initialization)
    private static final Logger INSTANCE = new Logger();

    // Log interno
    private List<String> messaggi = new ArrayList<>();

    // Costruttore PRIVATO: nessuno può fare new Logger()
    private Logger() { }

    // Punto di accesso globale
    public static Logger getInstance() {
        return INSTANCE;
    }

    public void log(String messaggio) {
        String timestamp = java.time.LocalDateTime.now().toString();
        messaggi.add("[" + timestamp + "] " + messaggio);
        System.out.println("[" + timestamp + "] " + messaggio);
    }

    public List<String> getLog() {
        return Collections.unmodifiableList(messaggi);
    }
}

// Utilizzo: stessa istanza ovunque
Logger.getInstance().log("Avvio applicazione");
Logger.getInstance().log("Connessione al database");

// Anche da classi diverse:
Logger logger = Logger.getInstance();
logger.log("Operazione completata");
```

Variante con **lazy initialization** (thread-safe):

```java
public class Database {
    private static Database instance;

    private Database() {
        // inizializzazione costosa
        System.out.println("Connessione al database...");
    }

    // Lazy: creata solo al primo uso
    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void query(String sql) {
        System.out.println("Esecuzione: " + sql);
    }
}
```

Variante con **enum** (il modo più robusto in Java):

```java
public enum Configurazione {
    INSTANCE;

    private final Map<String, String> proprietà = new HashMap<>();

    public void set(String chiave, String valore) {
        proprietà.put(chiave, valore);
    }

    public String get(String chiave) {
        return proprietà.get(chiave);
    }
}

Configurazione.INSTANCE.set("db.host", "localhost");
String host = Configurazione.INSTANCE.get("db.host");
```

### 17.4 Decorator Pattern

Il **Decorator** aggiunge responsabilità ad un oggetto in modo dinamico,
senza modificare la classe originale. È un'alternativa flessibile all'ereditarietà.

Struttura:

1. **Interfaccia comune** (Component): definisce le operazioni
2. **Classe base** (ConcreteComponent): implementazione di base
3. **Decoratore astratto** (Decorator): implementa l'interfaccia e contiene un riferimento al componente
4. **Decoratori concreti**: aggiungono comportamento

```java
// 1. Interfaccia comune
public interface Bevanda {
    String getDescrizione();
    double getCosto();
}

// 2. Classe base (componente concreto)
public class Caffe implements Bevanda {
    @Override
    public String getDescrizione() {
        return "Caffè";
    }

    @Override
    public double getCosto() {
        return 1.00;
    }
}

public class Te implements Bevanda {
    @Override
    public String getDescrizione() {
        return "Tè";
    }

    @Override
    public double getCosto() {
        return 0.80;
    }
}

// 3. Decoratore astratto
public abstract class BevandaDecorator implements Bevanda {
    protected final Bevanda bevandaDecorata;

    public BevandaDecorator(Bevanda bevanda) {
        this.bevandaDecorata = bevanda;
    }

    @Override
    public String getDescrizione() {
        return bevandaDecorata.getDescrizione();
    }

    @Override
    public double getCosto() {
        return bevandaDecorata.getCosto();
    }
}

// 4. Decoratori concreti
public class ConZucchero extends BevandaDecorator {
    public ConZucchero(Bevanda bevanda) {
        super(bevanda);
    }

    @Override
    public String getDescrizione() {
        return bevandaDecorata.getDescrizione() + " + Zucchero";
    }

    @Override
    public double getCosto() {
        return bevandaDecorata.getCosto() + 0.10;
    }
}

public class ConLatte extends BevandaDecorator {
    public ConLatte(Bevanda bevanda) {
        super(bevanda);
    }

    @Override
    public String getDescrizione() {
        return bevandaDecorata.getDescrizione() + " + Latte";
    }

    @Override
    public double getCosto() {
        return bevandaDecorata.getCosto() + 0.30;
    }
}

public class ConPanna extends BevandaDecorator {
    public ConPanna(Bevanda bevanda) {
        super(bevanda);
    }

    @Override
    public String getDescrizione() {
        return bevandaDecorata.getDescrizione() + " + Panna";
    }

    @Override
    public double getCosto() {
        return bevandaDecorata.getCosto() + 0.50;
    }
}

// Utilizzo: composizione dinamica
Bevanda ordine1 = new Caffe();
System.out.println(ordine1.getDescrizione()); // Caffè
System.out.println(ordine1.getCosto());       // 1.00

Bevanda ordine2 = new ConZucchero(new ConLatte(new Caffe()));
System.out.println(ordine2.getDescrizione()); // Caffè + Latte + Zucchero
System.out.println(ordine2.getCosto());       // 1.00 + 0.30 + 0.10 = 1.40

Bevanda ordine3 = new ConPanna(new ConZucchero(new ConZucchero(new Te())));
System.out.println(ordine3.getDescrizione()); // Tè + Zucchero + Zucchero + Panna
System.out.println(ordine3.getCosto());       // 0.80 + 0.10 + 0.10 + 0.50 = 1.50
```

Il Decorator è potente perché:

- I decoratori sono combinabili arbitrariamente
- Si possono aggiungere nuovi decoratori senza modificare il codice esistente
- L'oggetto viene "avvolto" a runtime (composizione dinamica)

### 17.5 Observer Pattern

L'**Observer** definisce una relazione uno-a-molti tra oggetti:
quando un oggetto (Subject/Mittente) cambia stato, tutti gli osservatori
registrati vengono automaticamente notificati.

Struttura:

1. **Listener/Observer** (interfaccia): definisce il metodo di callback
2. **Subject/Mittente**: mantiene la lista degli osservatori e li notifica
3. **ConcreteObserver/Ricevente**: implementa la reazione alla notifica

```java
// 1. Interfaccia Listener generica
@FunctionalInterface
public interface Listener<T> {
    void aggiornato(T evento);
}

// 2. Mittente (Subject/Observable)
public class Mittente<T> {
    private final List<Listener<T>> ascoltatori = new ArrayList<>();

    // Registra un ascoltatore
    public void subscribe(Listener<T> ascoltatore) {
        if (!ascoltatori.contains(ascoltatore)) {
            ascoltatori.add(ascoltatore);
        }
    }

    // Rimuove un ascoltatore
    public void unsubscribe(Listener<T> ascoltatore) {
        ascoltatori.remove(ascoltatore);
    }

    // Notifica tutti gli ascoltatori
    protected void notifyAll(T evento) {
        for (Listener<T> l : ascoltatori) {
            l.aggiornato(evento);
        }
    }
}

// 3. Classe concreta che estende Mittente
public class Termometro extends Mittente<Double> {
    private double temperatura;

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double nuovaTemp) {
        double vecchiaTemp = this.temperatura;
        this.temperatura = nuovaTemp;
        if (vecchiaTemp != nuovaTemp) {
            notifyAll(nuovaTemp); // notifica tutti gli osservatori
        }
    }
}

// 4. Osservatori concreti (Riceventi)
public class DisplayTemperatura implements Listener<Double> {
    private final String nome;

    public DisplayTemperatura(String nome) {
        this.nome = nome;
    }

    @Override
    public void aggiornato(Double temperatura) {
        System.out.printf("[%s] Temperatura aggiornata: %.1f°C%n", nome, temperatura);
    }
}

public class AllarmeTemperatura implements Listener<Double> {
    private final double soglia;

    public AllarmeTemperatura(double soglia) {
        this.soglia = soglia;
    }

    @Override
    public void aggiornato(Double temperatura) {
        if (temperatura > soglia) {
            System.out.printf("⚠ ALLARME: Temperatura %.1f°C supera soglia %.1f°C!%n",
                temperatura, soglia);
        }
    }
}

// Utilizzo
Termometro termometro = new Termometro();

DisplayTemperatura display1 = new DisplayTemperatura("Sala");
DisplayTemperatura display2 = new DisplayTemperatura("Cucina");
AllarmeTemperatura allarme = new AllarmeTemperatura(30.0);

termometro.subscribe(display1);
termometro.subscribe(display2);
termometro.subscribe(allarme);

termometro.setTemperatura(22.5);
// [Sala] Temperatura aggiornata: 22.5°C
// [Cucina] Temperatura aggiornata: 22.5°C

termometro.setTemperatura(31.0);
// [Sala] Temperatura aggiornata: 31.0°C
// [Cucina] Temperatura aggiornata: 31.0°C
// ⚠ ALLARME: Temperatura 31.0°C supera soglia 30.0°C!

termometro.unsubscribe(display2); // Cucina non riceve più notifiche

termometro.setTemperatura(25.0);
// [Sala] Temperatura aggiornata: 25.0°C
```

### 17.6 Observer con Lambda

Poiché `Listener<T>` è un'interfaccia funzionale (un solo metodo astratto),
si possono usare le lambda come osservatori:

```java
Termometro t = new Termometro();

// Observer con lambda
t.subscribe(temp -> System.out.println("Lambda: " + temp + "°C"));

// Observer con method reference
t.subscribe(System.out::println);

// Observer inline per logging
t.subscribe(temp -> {
    if (temp < 0) System.out.println("Sotto zero!");
    else if (temp > 35) System.out.println("Molto caldo!");
});

t.setTemperatura(-5);
// Lambda: -5.0°C
// -5.0
// Sotto zero!
```

### 17.7 Esempio Completo: Sistema di Notifiche

```java
// Evento personalizzato
public record Notifica(String tipo, String messaggio, java.time.LocalDateTime timestamp) {
    public Notifica(String tipo, String messaggio) {
        this(tipo, messaggio, java.time.LocalDateTime.now());
    }
}

// Sistema di notifiche (Mittente)
public class SistemaNotifiche extends Mittente<Notifica> {

    public void invia(String tipo, String messaggio) {
        Notifica n = new Notifica(tipo, messaggio);
        notifyAll(n);
    }
}

// Riceventi
public class EmailNotifier implements Listener<Notifica> {
    private final String email;

    public EmailNotifier(String email) { this.email = email; }

    @Override
    public void aggiornato(Notifica n) {
        System.out.printf("Email a %s: [%s] %s%n", email, n.tipo(), n.messaggio());
    }
}

public class LogNotifier implements Listener<Notifica> {
    @Override
    public void aggiornato(Notifica n) {
        System.out.printf("[LOG %s] %s: %s%n", n.timestamp(), n.tipo(), n.messaggio());
    }
}

// Utilizzo
SistemaNotifiche sistema = new SistemaNotifiche();
sistema.subscribe(new EmailNotifier("admin@example.com"));
sistema.subscribe(new LogNotifier());
sistema.subscribe(n -> {
    if (n.tipo().equals("ERRORE")) {
        System.out.println("URGENTE: " + n.messaggio());
    }
});

sistema.invia("INFO", "Sistema avviato");
sistema.invia("ERRORE", "Connessione database persa");
```

### 17.8 Riepilogo Design Pattern

| Pattern   | Tipo            | Problema                                  | Soluzione                                    |
| --------- | --------------- | ----------------------------------------- | -------------------------------------------- |
| Singleton | Creazionale     | Serve una sola istanza di una classe      | Costruttore privato + getInstance() statico  |
| Decorator | Strutturale     | Aggiungere comportamento senza modificare | Wrapper che implementa la stessa interfaccia |
| Observer  | Comportamentale | Notificare oggetti quando cambia lo stato | Lista di listener + notifica automatica      |

Principi comuni:

- **Programmare verso interfacce**, non implementazioni
- **Preferire la composizione** all'ereditarietà
- **Open/Closed Principle**: aperto all'estensione, chiuso alla modifica
