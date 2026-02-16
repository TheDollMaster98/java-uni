# Guida Completa Java – Preparazione Esame Prog2 (v3)

Guida ristrutturata e completa per superare l'esame di Programmazione 2.
Copre: fondamenti, OOP, eccezioni (checked/unchecked), classi astratte, interfacce, Iterable/Iterator, AF/RI, specifiche formali, generics, classi nested, programmazione funzionale, design pattern, composizione vs ereditarieta', filtro teoria e pattern d'esame con soluzioni complete.

---

## Indice

### PARTE 1 – Fondamenti Java

1. [Struttura di un programma](#1-struttura-di-un-programma)
2. [Tipi primitivi, wrapper e conversioni](#2-tipi-primitivi-wrapper-e-conversioni)
3. [Stringhe](#3-stringhe)
4. [Strutture di controllo](#4-strutture-di-controllo)
5. [Array](#5-array)
6. [Metodi e procedure](#6-metodi-e-procedure)

### PARTE 2 – Programmazione a Oggetti

7. [Classi e oggetti](#7-classi-e-oggetti)
8. [Ereditarieta' e polimorfismo](#8-ereditarietà-e-polimorfismo)
9. [Classi astratte](#9-classi-astratte)
10. [Interfacce](#10-interfacce)

### PARTE 3 – Eccezioni (approfondimento)

1. [Che cos'e' un'eccezione](#1-che-cose-uneccezione)
2. [Gerarchia delle eccezioni](#2-gerarchia-delle-eccezioni)
3. [Checked vs Unchecked exceptions](#3-checked-vs-unchecked-exceptions)
4. [try-catch-finally](#4-try-catch-finally)
5. [try-with-resources](#5-try-with-resources)
6. [throws nella firma del metodo](#6-throws-nella-firma-del-metodo)
7. [throw per lanciare un'eccezione](#7-throw-per-lanciare-uneccezione)
8. [Definire eccezioni personalizzate](#8-definire-eccezioni-personalizzate)
9. [Eccezioni e override: regole precise](#9-eccezioni-e-override-regole-precise)
10. [Pattern d'esame: eccezioni](#10-pattern-desame-eccezioni)

### PARTE 4 – Collezioni e Iteratori

13. [Collezioni: List, Set, Map](#13-collezioni-list-set-map)
14. [Comparable e Comparator](#14-comparable-e-comparator)
15. [Iterable e Iterator](#15-iterable-e-iterator)

### PARTE 5 – Astrazione e Specifiche

1. [Astrazione per specifica](#1-astrazione-per-specifica)
2. [Le clausole REQUIRES / MODIFIES / EFFECTS](#2-le-clausole-requires--modifies--effects)
3. [Metodi totali vs metodi parziali](#3-metodi-totali-vs-metodi-parziali)
4. [Abstract Data Type (ADT)](#4-abstract-data-type-adt)
5. [Funzione di Astrazione AF(this)](#5-funzione-di-astrazione-afthis)
6. [Invariante di Rappresentazione RI(this)](#6-invariante-di-rappresentazione-rithis)
7. [Rep Exposure](#7-rep-exposure)
8. [OVERVIEW di classe](#8-overview-di-classe)

### PARTE 6 – Concetti Avanzati

20. [Clonazione e copia difensiva](#20-clonazione-e-copia-difensiva)
21. [Classi nested](#21-classi-nested)
22. [Programmazione funzionale](#22-programmazione-funzionale)
23. [Design pattern](#23-design-pattern)
24. [Composizione vs ereditarieta'](#24-composizione-vs-ereditarietà)

### PARTE 7 – Preparazione Esame

25. [Filtro teoria – 30+ domande e risposte](#25-filtro-teoria--30-domande-e-risposte)
26. [Pattern d'esame pratico](#26-pattern-desame-pratico)
27. [Soluzione completa: Simulazione Vetreria](#27-soluzione-completa-simulazione-vetreria)
28. [Soluzione completa: Esame Dicembre – Calendario Sorprese](#28-soluzione-completa-esame-dicembre--calendario-sorprese)
29. [Soluzione completa: Esame Gennaio – Olimpiadi Invernali](#29-soluzione-completa-esame-gennaio--olimpiadi-invernali)
30. [ScalaValutazione, strategia e checklist](#30-scalavalutazione-strategia-e-checklist)

---

# PARTE 1 – Fondamenti Java

---

## 1. Struttura di un programma

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```

**Regole fondamentali:**

- Il nome del file **deve** coincidere con il nome della classe pubblica → `HelloWorld.java`
- Il metodo `main` è il punto di ingresso: `public static void main(String[] args)`
- `args` è un array di stringhe passate da riga di comando

**Compilazione ed esecuzione:**

```bash
javac HelloWorld.java     # compila → genera HelloWorld.class
java HelloWorld            # esegue
java HelloWorld arg1 arg2  # con argomenti da riga di comando
```

---

## 2. Tipi primitivi, wrapper e conversioni

### Tipi primitivi

| Tipo      | Dim    | Esempio             |
| --------- | ------ | ------------------- |
| `byte`    | 8 bit  | `byte b = 127;`     |
| `short`   | 16 bit | `short s = 30000;`  |
| `int`     | 32 bit | `int x = 42;`       |
| `long`    | 64 bit | `long l = 100L;`    |
| `float`   | 32 bit | `float f = 3.14f;`  |
| `double`  | 64 bit | `double d = 3.14;`  |
| `boolean` | 1 bit  | `boolean b = true;` |
| `char`    | 16 bit | `char c = 'A';`     |

### Wrapper classes (primitivi → oggetti)

```java
int x = 5;
Integer xObj = x;           // autoboxing
int y = xObj;               // unboxing

// Necessari per le collezioni generiche:
List<Integer> numeri = new ArrayList<>();  // NON puoi usare List<int>
```

| Primitivo | Wrapper     |
| --------- | ----------- |
| `int`     | `Integer`   |
| `double`  | `Double`    |
| `boolean` | `Boolean`   |
| `char`    | `Character` |
| `long`    | `Long`      |

### Variabili e costanti

```java
int x = 10;              // variabile
final double PI = 3.14;  // costante (non modificabile)
```

### Operatori

```java
// Aritmetici
+  -  *  /  %

// Confronto (restituiscono boolean)
==  !=  <  >  <=  >=

// Logici
&&  ||  !

// Assegnamento composto
+=  -=  *=  /=  %=

// Incremento/Decremento
x++  x--  ++x  --x
```

### Conversioni di tipo

```java
// String → int/double
int n = Integer.parseInt("42");
double d = Double.parseDouble("3.14");

// int/double → String
String s1 = String.valueOf(42);
String s2 = Integer.toString(42);
String s3 = "" + 42;              // modo rapido

// Casting tra tipi primitivi
double d = 3.99;
int x = (int) d;                  // 3 (tronca, non arrotonda!)
int y = (int) Math.round(d);      // 4

// char → int e viceversa
char c = 'A';
int ascii = (int) c;              // 65
char back = (char) 65;            // 'A'
```

---

## 3. Stringhe

```java
String s = "ciao";
s.length();              // 4
s.charAt(0);             // 'c'
s.substring(1, 3);       // "ia"
s.toUpperCase();         // "CIAO"
s.toLowerCase();         // "ciao"
s.equals("ciao");        // true  — NON usare == per confrontare stringhe!
s.contains("ia");        // true
s.indexOf("ia");         // 1
s.replace("c", "C");     // "Ciao"
s.split(" ");            // divide in array di parole
s.trim();                // rimuove spazi iniziali e finali
s.isEmpty();             // false
s.isBlank();             // false (controlla anche spazi bianchi)
s.startsWith("ci");      // true
s.endsWith("ao");        // true

// Concatenazione
String nome = "Mario";
String saluto = "Ciao " + nome;   // "Ciao Mario"

// StringBuilder (efficiente per concatenazioni ripetute)
StringBuilder sb = new StringBuilder();
sb.append("ciao");
sb.append(" mondo");
String risultato = sb.toString();  // "ciao mondo"
```

**ATTENZIONE:** Le stringhe in Java sono **immutabili**. Ogni modifica crea un nuovo oggetto.

```java
String a = "ciao";
String b = "ciao";
a == b;         // true (stesso literal pool) - MA NON AFFIDABILE!
a.equals(b);    // true ← USARE SEMPRE QUESTO
```

---

## 4. Strutture di controllo

### If / Else If / Else

```java
if (x > 0) {
    System.out.println("Positivo");
} else if (x < 0) {
    System.out.println("Negativo");
} else {
    System.out.println("Zero");
}
```

### Operatore ternario

```java
String msg = (x > 0) ? "positivo" : "non positivo";
```

### Switch classico e moderno

```java
// Classico (con break obbligatorio)
switch (mese) {
    case 1:
        System.out.println("Gennaio");
        break;
    case 2:
        System.out.println("Febbraio");
        break;
    default:
        System.out.println("Altro");
}

// Moderno con frecce (Java 14+) – no fall-through
switch (mese) {
    case 1 -> System.out.println("Gennaio");
    case 2 -> System.out.println("Febbraio");
    case 3, 4, 5 -> System.out.println("Primavera");
    default -> System.out.println("Altro");
}

// Switch espressione (Java 14+) – restituisce un valore
String nome = switch (mese) {
    case 1 -> "Gennaio";
    case 2 -> "Febbraio";
    default -> "Sconosciuto";
};
```

### Cicli

```java
// for classico
for (int i = 0; i < 10; i++) {
    System.out.println(i);
}

// while
int i = 0;
while (i < 10) {
    System.out.println(i);
    i++;
}

// do-while (esegue almeno una volta)
int i = 0;
do {
    System.out.println(i);
    i++;
} while (i < 10);

// for-each (su array e collezioni)
int[] numeri = {1, 2, 3, 4, 5};
for (int n : numeri) {
    System.out.println(n);
}
```

### break e continue

```java
for (int i = 0; i < 100; i++) {
    if (i == 50) break;       // esce dal ciclo
    if (i % 2 == 0) continue; // salta al prossimo ciclo
    System.out.println(i);
}
```

---

## 5. Array

```java
// Dichiarazione e inizializzazione
int[] numeri = new int[5];           // [0, 0, 0, 0, 0]
int[] valori = {10, 20, 30, 40};    // inizializzazione diretta
String[] nomi = new String[3];      // [null, null, null]

// Operazioni
numeri[0] = 42;                      // assegnamento
int x = numeri[0];                   // accesso
int len = numeri.length;             // lunghezza (proprietà, non metodo!)

// Array multidimensionali
int[][] matrice = new int[3][4];     // 3 righe, 4 colonne
int[][] m = {
    {1, 2, 3},
    {4, 5, 6}
};
```

### java.util.Arrays – utility

```java
import java.util.Arrays;

int[] a = {3, 1, 2};
Arrays.sort(a);                  // ordina: [1, 2, 3]
Arrays.toString(a);              // "[1, 2, 3]" (per stampare)
Arrays.equals(a, b);             // confronto contenuto
Arrays.fill(a, 0);               // riempie con 0
Arrays.copyOf(a, 5);             // copia con nuova dimensione
```

---

## 6. Metodi e procedure

### Metodo con ritorno vs procedura (void)

```java
// Metodo con ritorno
public static int somma(int a, int b) {
    return a + b;
}

// Procedura (void – senza ritorno)
public static void saluta(String nome) {
    System.out.println("Ciao " + nome);
}
```

### Metodo statico vs d'istanza

```java
public class Calcolatrice {
    // STATICO: si chiama sulla CLASSE
    public static int somma(int a, int b) {
        return a + b;
    }

    // D'ISTANZA: si chiama su un OGGETTO
    private int memoria = 0;
    public int aggiungi(int n) {
        this.memoria += n;
        return this.memoria;
    }
}

// Uso:
int r = Calcolatrice.somma(3, 4);     // statico → sulla classe
Calcolatrice c = new Calcolatrice();
c.aggiungi(5);                         // d'istanza → sull'oggetto
```

### Overloading (stesso nome, parametri diversi)

```java
public static int somma(int a, int b) { return a + b; }
public static double somma(double a, double b) { return a + b; }
public static int somma(int a, int b, int c) { return a + b + c; }
```

---

# PARTE 2 – Programmazione a Oggetti

---

## 7. Classi e oggetti

### Definire una classe

```java
public class Persona {
    // OVERVIEW: modella una persona con nome e età

    // Campi privati (rappresentazione)
    private String nome;
    private int eta;

    // Costruttore
    public Persona(String nome, int eta) {
        if (nome == null || nome.isEmpty()) throw new IllegalArgumentException("Nome non valido");
        if (eta < 0) throw new IllegalArgumentException("Età non valida");
        this.nome = nome;
        this.eta = eta;
    }

    // Getter (osservatori)
    public String getNome() { return nome; }
    public int getEta() { return eta; }

    // Setter (modificatori)
    public void setEta(int eta) {
        if (eta < 0) throw new IllegalArgumentException("Età non valida");
        this.eta = eta;
    }

    @Override
    public String toString() {
        return nome + " (" + eta + " anni)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;
        Persona p = (Persona) o;
        return this.nome.equals(p.nome) && this.eta == p.eta;
    }

    @Override
    public int hashCode() {
        return nome.hashCode() * 31 + eta;
    }
}
```

### Creare e usare oggetti

```java
Persona p1 = new Persona("Mario", 30);
System.out.println(p1);           // Mario (30 anni) → chiama toString()
System.out.println(p1.getNome()); // Mario
p1.setEta(31);
```

### Modificatori di accesso

| Modificatore | Classe | Package | Sottoclasse | Mondo |
| ------------ | ------ | ------- | ----------- | ----- |
| `private`    | Si     | No      | No          | No    |
| (default)    | Si     | Si      | No          | No    |
| `protected`  | Si     | Si      | Si          | No    |
| `public`     | Si     | Si      | Si          | Si    |

**Regola per Prog2: i campi devono essere SEMPRE `private`.**

### Mutabile vs Immutabile

```java
// MUTABILE: lo stato può cambiare dopo la creazione
public class ContoBancario {
    private double saldo;
    public ContoBancario(double saldo) { this.saldo = saldo; }
    public void deposita(double importo) { this.saldo += importo; }
    public double getSaldo() { return saldo; }
}

// IMMUTABILE: lo stato NON cambia dopo la creazione
public class Punto {
    private final int x;   // final!
    private final int y;   // final!
    public Punto(int x, int y) { this.x = x; this.y = y; }
    public int getX() { return x; }
    public int getY() { return y; }
    // Restituisce un NUOVO oggetto
    public Punto trasla(int dx, int dy) {
        return new Punto(x + dx, y + dy);
    }
}
```

### Record (Java 16+)

```java
// Equivale a una classe immutabile con toString, equals, hashCode automatici
public record Persona(String nome, int eta) {}

Persona p = new Persona("Mario", 30);
p.nome();     // "Mario" (getter senza "get")
p.eta();      // 30
```

---

## 8. Ereditarietà e polimorfismo

### Ereditarietà (`extends`)

```java
// Superclasse (padre)
public class Animale {
    private String nome;

    public Animale(String nome) { this.nome = nome; }
    public String getNome() { return nome; }
    public String verso() { return "..."; }

    @Override
    public String toString() {
        return nome + " fa " + verso();
    }
}

// Sottoclasse (figlio)
public class Cane extends Animale {
    public Cane(String nome) {
        super(nome);   // OBBLIGATORIO chiamare il costruttore del padre
    }

    @Override          // sovrascrive il metodo del padre
    public String verso() {
        return "bau!";
    }
}

public class Gatto extends Animale {
    public Gatto(String nome) { super(nome); }

    @Override
    public String verso() { return "miao!"; }
}
```

### Polimorfismo e dispatch dinamico

```java
// tipo statico: Animale, tipo dinamico: Cane
Animale a1 = new Cane("Fido");
Animale a2 = new Gatto("Felix");

a1.verso();    // "bau!"  → il metodo chiamato dipende dal TIPO DINAMICO
a2.verso();    // "miao!"

// Collezione polimorfa
List<Animale> animali = new ArrayList<>();
animali.add(new Cane("Rex"));
animali.add(new Gatto("Micio"));

for (Animale a : animali) {
    System.out.println(a);  // dispatch dinamico: chiama il toString giusto
}
```

**Dispatch dinamico:** a runtime, Java chiama il metodo della classe effettiva dell'oggetto (tipo dinamico), non della classe dichiarata (tipo statico). Funziona SOLO per metodi d'istanza, NON per metodi static.

| Membro         | Binding                       |
| -------------- | ----------------------------- |
| Metodo istanza | **Dinamico** (tipo effettivo) |
| Metodo statico | **Statico** (tipo dichiarato) |
| Campo          | **Statico** (tipo dichiarato) |
| Overloading    | **Statico** (tipo dichiarato) |

### `instanceof` e casting

```java
Animale a = new Cane("Rex");

if (a instanceof Cane) {
    Cane c = (Cane) a;       // downcasting sicuro
}

// Pattern matching (Java 16+)
if (a instanceof Cane c) {   // combina verifica e casting
    System.out.println(c.getNome());
}
```

### `super` – accesso al padre

```java
public class CaneGuida extends Cane {
    private String proprietario;

    public CaneGuida(String nome, String proprietario) {
        super(nome);
        this.proprietario = proprietario;
    }

    @Override
    public String toString() {
        return super.toString() + " (guida di " + proprietario + ")";
    }
}
```

### `final` nell'ereditarietà

```java
final class ClasseNonEstendibile { }       // nessuno può estenderla
class Base {
    final void metodoBloccato() { }        // nessuno può sovrascriverlo
}
```

---

## 9. Classi astratte

Le classi astratte **non possono essere istanziate** e servono come modello per le sottoclassi.

```java
public abstract class Forma {
    // OVERVIEW: modella una forma geometrica

    private final String nome;

    // AF(this): una forma di nome 'nome'
    // RI(this): nome != null && !nome.isEmpty()

    protected Forma(String nome) {
        if (nome == null || nome.isEmpty())
            throw new IllegalArgumentException("Nome non valido");
        this.nome = nome;
    }

    public String getNome() { return nome; }

    // Metodo ASTRATTO: ogni sottoclasse lo implementa
    public abstract double area();

    // Metodo CONCRETO: usa il metodo astratto (Template Method pattern)
    @Override
    public String toString() {
        return nome + " con area " + area();
    }
}
```

### Sottoclassi concrete

```java
public class Cerchio extends Forma {
    // OVERVIEW: un cerchio definito dal raggio

    private final double raggio;

    // AF(this): un cerchio di raggio 'raggio'
    // RI(this): raggio > 0

    public Cerchio(String nome, double raggio) {
        super(nome);   // chiama costruttore astratto
        if (raggio <= 0) throw new IllegalArgumentException("Raggio non positivo");
        this.raggio = raggio;
    }

    @Override
    public double area() {
        return Math.PI * raggio * raggio;
    }
}

public class Rettangolo extends Forma {
    private final double base, altezza;

    public Rettangolo(String nome, double base, double altezza) {
        super(nome);
        this.base = base;
        this.altezza = altezza;
    }

    @Override
    public double area() {
        return base * altezza;
    }
}
```

### Punti chiave classi astratte

- `abstract class` NON si puo' istanziare con `new`
- Puo' avere costruttore (chiamato con `super()` dai figli)
- Puo' avere metodi concreti E astratti
- Il costruttore dovrebbe essere `protected` (non `public`)
- Ereditarieta' singola: una classe puo' estendere UNA sola classe

---

## 10. Interfacce

Un'interfaccia definisce un **contratto**: cosa una classe deve saper fare, senza dire come.

```java
public interface Volante {
    void vola();                    // metodo astratto (implicitamente public abstract)
    double altitudine();

    // Metodo default (Java 8+): implementazione di default
    default String descrizione() {
        return "Oggetto volante a " + altitudine() + " metri";
    }

    // Metodo statico
    static boolean puoVolare(Object o) {
        return o instanceof Volante;
    }
}
```

### Implementazione

```java
public class Aereo implements Volante {
    private double quota;

    @Override
    public void vola() { this.quota += 100; }

    @Override
    public double altitudine() { return quota; }
}
```

### Differenza tra classe astratta e interfaccia

| Aspetto         | Classe astratta                  | Interfaccia                     |
| --------------- | -------------------------------- | ------------------------------- |
| Stato (campi)   | Si (campi d'istanza)             | No (solo costanti static final) |
| Costruttore     | Si                               | No                              |
| Metodi concreti | Si                               | Solo default (Java 8+)          |
| Ereditarieta'   | Singola (un solo extends)        | Multipla (implements A, B, C)   |
| Uso tipico      | Base comune con codice condiviso | Contratto (cosa saper fare)     |

### Interfacce fondamentali per l'esame

```java
// Comparable<T> → ordine naturale
public interface Comparable<T> {
    int compareTo(T o);
    // negativo: this < o
    // zero: this == o
    // positivo: this > o
}

// Iterable<T> → permette il for-each
public interface Iterable<T> {
    Iterator<T> iterator();
}

// Iterator<T> → scorre gli elementi
public interface Iterator<T> {
    boolean hasNext();
    T next();
    default void remove() { throw new UnsupportedOperationException(); }
}

// Cloneable → permette clone()
// (interfaccia marker, nessun metodo)
```

---

# PARTE 3 – Eccezioni

---

## 1. Che cos'e' un'eccezione

Un'**eccezione** e' un evento anomalo che interrompe il normale flusso di esecuzione di un programma. In Java, le eccezioni sono **oggetti**: vengono create con `new`, possiedono campi e metodi, e appartengono a una gerarchia di classi ben definita.

Il meccanismo delle eccezioni si fonda su tre operazioni fondamentali:

1. **Lanciare** (`throw`): il codice che rileva l'anomalia crea un oggetto eccezione e lo lancia.
2. **Propagare**: se il metodo corrente non gestisce l'eccezione, questa risale automaticamente la catena delle chiamate (lo **stack di chiamate**) fino a trovare un gestore.
3. **Catturare** (`catch`): un blocco di codice intercetta l'eccezione e la gestisce.

**Perche' usare le eccezioni?** Separano la logica di dominio dalla logica di gestione degli errori. Senza eccezioni, ogni metodo dovrebbe restituire codici di errore e ogni chiamante dovrebbe controllarli — un approccio fragile e poco leggibile.

### Lo stack trace

Quando un'eccezione non viene catturata, la JVM stampa lo **stack trace**: la sequenza di chiamate attive al momento del lancio, dalla piu' interna (dove e' stato eseguito il `throw`) alla piu' esterna (`main`).

```java
public class EsempioStackTrace {

    // metodo che lancia un'eccezione
    public static void metodoC() {
        // simuliamo un errore: accesso a posizione inesistente
        throw new ArrayIndexOutOfBoundsException("indice 10 non valido");
    }

    public static void metodoB() {
        metodoC();  // propaga l'eccezione verso l'alto
    }

    public static void metodoA() {
        metodoB();  // propaga l'eccezione verso l'alto
    }

    public static void main(String[] args) {
        metodoA();  // l'eccezione arriva qui e termina il programma
    }
}
```

**Output dello stack trace:**

```
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: indice 10 non valido
    at EsempioStackTrace.metodoC(EsempioStackTrace.java:5)
    at EsempioStackTrace.metodoB(EsempioStackTrace.java:9)
    at EsempioStackTrace.metodoA(EsempioStackTrace.java:13)
    at EsempioStackTrace.main(EsempioStackTrace.java:17)
```

**Come leggere lo stack trace:**

- La **prima riga** indica il tipo di eccezione e il messaggio.
- Le righe successive (`at ...`) mostrano la catena di chiamate, dalla piu' interna alla piu' esterna.
- Ogni riga indica **classe**, **metodo**, **file** e **numero di riga**.
- Il metodo `getMessage()` restituisce il messaggio passato al costruttore dell'eccezione.
- Il metodo `printStackTrace()` stampa lo stack trace su `System.err`.

---

## 2. Gerarchia delle eccezioni

Tutte le eccezioni in Java discendono dalla classe `Throwable`. La gerarchia ha due rami principali:

```
                        Throwable
                       /         \
                      /           \
                  Error          Exception
                 /    \          /    |    \
                /      \        /     |     \
  OutOfMemoryError  StackOverflow   IO    SQL    RuntimeException
                      Error      Exc.  Exc.     /    |    \     \
                                               /     |     \     \
                                     NullPointer  Illegal   Index  ClassCast
                                       Exc.      Argument  OutOf    Exc.
                                                   Exc.   Bounds
                                                           Exc.
```

**Diagramma dettagliato:**

```
java.lang.Throwable
├── java.lang.Error                          ← NON gestire (errori della JVM)
│   ├── OutOfMemoryError                     ← memoria esaurita
│   ├── StackOverflowError                   ← ricorsione infinita
│   └── VirtualMachineError                  ← errore critico della JVM
│
└── java.lang.Exception                      ← CHECKED (da gestire)
    ├── IOException                          ← errore di I/O
    ├── SQLException                         ← errore database
    ├── FileNotFoundException                ← file non trovato
    ├── ClassNotFoundException               ← classe non trovata
    ├── ... (tutte le sottoclassi dirette sono CHECKED)
    │
    └── java.lang.RuntimeException           ← UNCHECKED (errori logici)
        ├── NullPointerException             ← riferimento null
        ├── IllegalArgumentException         ← argomento non valido
        │   └── NumberFormatException        ← formato numero errato
        ├── IndexOutOfBoundsException        ← indice fuori range
        │   ├── ArrayIndexOutOfBoundsException
        │   └── StringIndexOutOfBoundsException
        ├── ClassCastException               ← cast illegale
        ├── ArithmeticException              ← es. divisione per zero
        │   └── DivisionByZeroException      ← (custom dal lab)
        ├── InputMismatchException           ← (java.util) input errato
        ├── NoSuchElementException           ← iteratore esaurito
        └── UnsupportedOperationException    ← operazione non supportata
```

### Regole della gerarchia

| Ramo                 | Significato                              | Il compilatore obbliga a gestirle? | Azione del programmatore            |
| -------------------- | ---------------------------------------- | ---------------------------------- | ----------------------------------- |
| `Error`              | Errore grave della JVM, irrecuperabile   | No                                 | **Non gestire**, non fare catch     |
| `Exception` (direttamente) | Situazione anomala recuperabile    | **Si** (checked)                   | Dichiarare con `throws` o catturare |
| `RuntimeException`   | Errore logico del programmatore          | No (unchecked)                     | Correggere il codice                |

**Regola fondamentale:** la posizione nella gerarchia determina se un'eccezione e' checked o unchecked. Se estende `Exception` direttamente (senza passare da `RuntimeException`) e' **checked**. Se estende `RuntimeException` e' **unchecked**.

---

## 3. Checked vs Unchecked exceptions

Questa distinzione e' il concetto piu' importante del capitolo e ricorre in ogni esame.

### Checked Exception (extends Exception)

Una **checked exception** rappresenta una situazione anomala **prevedibile e recuperabile**. Il compilatore **obbliga** il programmatore a gestirla: o la cattura con un `try-catch`, o la dichiara nella firma del metodo con `throws`.

```java
// Esempio: verificare la compatibilita' di liquidi e' una situazione
// di DOMINIO — il chiamante puo' e deve decidere cosa fare.
public class LiquidsException extends Exception {
    // extends Exception → CHECKED
    // Il compilatore OBBLIGA chi chiama a gestirla
    public LiquidsException(String msg) {
        super(msg);  // passa il messaggio alla classe Exception
    }
}
```

```java
// Esempio dal corso: GiornoException e' checked perche' un giorno
// gia' occupato e' una situazione di dominio prevedibile
public class GiornoException extends Exception {
    public GiornoException(String msg) {
        super(msg);  // il messaggio descrive il problema specifico
    }
}
```

**Se non gestisci una checked exception, il codice NON compila:**

```java
// ERRORE DI COMPILAZIONE: unhandled exception LiquidsException
public void esempio() {
    contenitore.versa(altro);  // versa() throws LiquidsException
    // Il compilatore si rifiuta di compilare perche'
    // LiquidsException non e' ne' catturata ne' dichiarata
}

// CORRETTO: dichiara con throws
public void esempio() throws LiquidsException {
    contenitore.versa(altro);  // ora la responsabilita' passa al chiamante
}

// CORRETTO: cattura con try-catch
public void esempio() {
    try {
        contenitore.versa(altro);
    } catch (LiquidsException e) {
        System.out.println("Errore: " + e.getMessage());
    }
}
```

### Unchecked Exception (extends RuntimeException)

Una **unchecked exception** rappresenta un **errore logico del programmatore**. Il compilatore **non obbliga** a dichiararla o catturarla. Non serve la clausola `throws` nella firma del metodo (anche se e' possibile aggiungerla per documentazione).

```java
// Esempio: superare la capacita' di un contenitore e' un errore LOGICO
// del chiamante — non avrebbe dovuto farlo
public class CapacityException extends RuntimeException {
    // extends RuntimeException → UNCHECKED
    // Il compilatore NON obbliga a gestirla
    public CapacityException(String msg) {
        super(msg);  // passa il messaggio alla classe RuntimeException
    }
}
```

```java
// Esempio dal corso: SorpresaException e' unchecked perche' inserire
// una sorpresa duplicata e' un errore logico del chiamante
public class SorpresaException extends RuntimeException {
    public SorpresaException(String msg) {
        super(msg);
    }
}

// EventoException segue la stessa logica
public class EventoException extends RuntimeException {
    public EventoException(String msg) {
        super(msg);
    }
}
```

**Le unchecked exception NON richiedono dichiarazione:**

```java
// Compila senza problemi anche senza try-catch e senza throws
public void aggiungi(double quantita) {
    if (quantita > calcolaVolume()) {
        // unchecked: il programmatore ha sbagliato, non serve obbligare
        // il chiamante a gestire questa situazione
        throw new CapacityException("capacita' superata");
    }
    this.quantita += quantita;
}
```

### Tabella comparativa completa

| Aspetto                | Checked (`extends Exception`)             | Unchecked (`extends RuntimeException`)              |
| ---------------------- | ----------------------------------------- | --------------------------------------------------- |
| **Compilatore**        | OBBLIGA a dichiararle (`throws`) o catturarle (`catch`) | NON obbliga                           |
| **Tipo di errore**     | Situazione di dominio prevedibile e recuperabile | Errore logico del programmatore              |
| **Dichiarazione**      | `throws` nella firma OBBLIGATORIO         | `throws` nella firma FACOLTATIVO                    |
| **Responsabilita'**    | Del chiamante (decide come gestire)       | Del programmatore (deve correggere il codice)       |
| **Esempi dal corso**   | `LiquidsException`, `GiornoException`     | `CapacityException`, `SorpresaException`, `EventoException` |
| **Esempi standard JDK** | `IOException`, `SQLException`, `FileNotFoundException` | `NullPointerException`, `IllegalArgumentException`, `ClassCastException` |

### Quando usare l'una o l'altra

La scelta dipende dalla **semantica dell'errore**:

```java
// CHECKED: il chiamante PUO' e DEVE prendere una decisione
// "i liquidi sono incompatibili" → il chiamante puo' scegliere di
// provare con un altro contenitore, segnalare all'utente, ecc.
public void versa(Contenitore sorgente) throws LiquidsException {
    if (!this.liquido.equals(sorgente.liquido)) {
        throw new LiquidsException("liquidi incompatibili");
    }
}

// UNCHECKED: il chiamante ha violato un vincolo logico
// "la capacita' e' superata" → il chiamante non avrebbe dovuto
// tentare di inserire piu' liquido di quello che il contenitore puo' contenere
public void riempi(double quantita) {
    if (quantita > calcolaVolume() - this.quantita) {
        throw new CapacityException("capacita' superata");
    }
}
```

**Regola pratica per l'esame:** leggi il README con attenzione. Se dice "lancia un'eccezione checked" o "deve essere dichiarata", usa `extends Exception`. Se dice "eccezione unchecked" o "errore logico", usa `extends RuntimeException`.

---

## 4. try-catch-finally

Il costrutto `try-catch-finally` e' il meccanismo principale per catturare e gestire le eccezioni.

### Sintassi base

```java
try {
    // codice che potrebbe lanciare eccezioni
    // se un'eccezione viene lanciata, il flusso salta
    // immediatamente al catch corrispondente
    double risultato = calcolatrice.calculate(10, 0, '/');
    System.out.println("Questo NON viene stampato se c'e' eccezione");
} catch (DivisionByZeroException e) {
    // questo blocco viene eseguito SOLO se viene lanciata
    // un'eccezione di tipo DivisionByZeroException (o sottotipo)
    System.out.println("Errore: " + e.getMessage());
} catch (InputMismatchException e) {
    // gestisce un tipo diverso di eccezione
    System.out.println("Input non valido: " + e.getMessage());
} finally {
    // questo blocco viene SEMPRE eseguito, sia che ci sia stata
    // un'eccezione sia che non ci sia stata
    System.out.println("Operazione terminata");
}
```

### Multi-catch con | (Java 7+)

Quando piu' eccezioni devono essere gestite nello stesso modo, si usa il **multi-catch** con l'operatore `|`:

```java
try {
    calendario.inserisci(giorno, sorpresa);
} catch (GiornoException | SorpresaException e) {
    // gestisce entrambe le eccezioni con lo stesso codice
    // 'e' ha come tipo il tipo comune piu' specifico
    System.out.println("\tECCEZIONE: " + e.getMessage());
}
```

**Vincoli del multi-catch:**

```java
// ERRORE: le eccezioni nel multi-catch NON possono essere
// in relazione di ereditarieta' tra loro
try {
    // ...
} catch (Exception | IOException e) {  // ERRORE DI COMPILAZIONE!
    // IOException e' sottotipo di Exception → ridondante
}

// CORRETTO: basta catturare quella piu' ampia
try {
    // ...
} catch (Exception e) {
    // cattura Exception e tutte le sue sottoclassi
}
```

### Ordine dei catch: dal piu' specifico al piu' generico

I blocchi `catch` vengono valutati **dall'alto verso il basso** e viene eseguito il **primo** che corrisponde al tipo dell'eccezione. Per questo motivo, le eccezioni piu' specifiche devono venire prima.

```java
// CORRETTO: ordine dal piu' specifico al piu' generico
try {
    String s = null;
    s.length();
} catch (NullPointerException e) {
    // piu' specifico: cattura SOLO NullPointerException
    System.out.println("Riferimento null");
} catch (RuntimeException e) {
    // meno specifico: cattura tutte le RuntimeException
    // che NON sono NullPointerException (gia' catturata sopra)
    System.out.println("Errore runtime generico");
} catch (Exception e) {
    // ancora piu' generico: cattura tutte le checked exception
    System.out.println("Eccezione generica");
}
```

```java
// ERRORE DI COMPILAZIONE: ordine sbagliato!
try {
    // ...
} catch (Exception e) {
    // questa cattura TUTTO (comprese le sottoclassi)
} catch (NullPointerException e) {
    // ERRORE: questo catch non sara' mai raggiungibile
    // perche' NullPointerException e' sottotipo di Exception
}
```

### Il blocco finally viene SEMPRE eseguito

Il blocco `finally` e' **garantito** nell'esecuzione, indipendentemente da cosa accade nel `try` o nel `catch`:

```java
public static void esempio() {
    Scanner s = new Scanner(System.in);
    try {
        int n = s.nextInt();
        System.out.println("Numero: " + n);
        // CASO 1: nessuna eccezione → finally eseguito dopo il try
        // CASO 2: eccezione catturata → finally eseguito dopo il catch
        // CASO 3: eccezione NON catturata → finally eseguito,
        //         poi l'eccezione viene propagata
    } catch (InputMismatchException e) {
        System.out.println("Input non valido");
        // ANCHE SE c'e' un return qui, finally viene eseguito!
        return;
    } finally {
        // SEMPRE eseguito: risorse da rilasciare
        s.close();
        System.out.println("Scanner chiuso");
    }
    // questa riga viene raggiunta SOLO se non c'e' stato return nel catch
}
```

**Casi in cui finally viene eseguito:**

| Situazione                            | `finally` eseguito? |
| ------------------------------------- | -------------------- |
| Nessuna eccezione                     | Si                   |
| Eccezione catturata dal `catch`       | Si                   |
| Eccezione NON catturata               | Si (poi propaga)     |
| `return` nel `try`                    | Si (prima del return)|
| `return` nel `catch`                  | Si (prima del return)|
| `System.exit()` nel try/catch         | **No** (unica eccezione) |

### Esempio completo dal lab: Calcolatrice

```java
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calcolatrice {

    // il metodo dichiara le eccezioni che puo' lanciare
    // InputMismatchException e' unchecked → il throws e' facoltativo
    // DivisionByZeroException e' unchecked → il throws e' facoltativo
    // qui vengono dichiarate per documentazione
    public static double calculate(double o1, double o2, char op)
            throws InputMismatchException, DivisionByZeroException {
        switch (op) {
            case '+': return o1 + o2;   // operazione valida
            case '-': return o1 - o2;   // operazione valida
            case '*': return o1 * o2;   // operazione valida
            case '/':
                // controllo esplicito PRIMA di eseguire la divisione
                if (o2 == 0)
                    throw new DivisionByZeroException("Non possibile dividere per 0.");
                return o1 / o2;
            default:
                // operatore non riconosciuto → errore di input
                throw new InputMismatchException("Operatore non riconosciuto");
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        try {
            // lettura degli operandi e dell'operatore dallo scanner
            double n1 = s.nextDouble();   // puo' lanciare InputMismatchException
            String op = s.next();
            double n2 = s.nextDouble();   // puo' lanciare InputMismatchException

            // calcolo del risultato (puo' lanciare le eccezioni dichiarate)
            double ris = calculate(n1, n2, op.charAt(0));
            System.out.println("Risultato: " + ris);

        } catch (InputMismatchException e) {
            // gestisce sia l'input non numerico sia l'operatore non valido
            System.out.println("Operando non valido");
        } catch (DivisionByZeroException e) {
            // gestisce la divisione per zero
            System.out.println(e.getMessage());
        } finally {
            // chiude lo scanner in OGNI caso
            s.close();
        }
    }
}
```

---

## 5. try-with-resources

### Il problema: dimenticare di chiudere le risorse

Prima di Java 7, il programmatore doveva chiudere manualmente le risorse nel blocco `finally`. Questo approccio era verboso e soggetto a errori:

```java
// Approccio vecchio: verbose e fragile
Scanner scanner = null;
try {
    scanner = new Scanner(new File("dati.txt"));
    while (scanner.hasNextLine()) {
        System.out.println(scanner.nextLine());
    }
} catch (FileNotFoundException e) {
    System.out.println("File non trovato");
} finally {
    // PROBLEMA: se scanner e' null (es. file non trovato),
    // scanner.close() lancia NullPointerException!
    if (scanner != null) {
        scanner.close();
    }
}
```

### La soluzione: try-with-resources (Java 7+)

Il costrutto `try-with-resources` chiude automaticamente le risorse alla fine del blocco `try`. La risorsa deve implementare l'interfaccia `AutoCloseable`.

```java
// L'interfaccia AutoCloseable ha un solo metodo:
public interface AutoCloseable {
    void close() throws Exception;
    // questo metodo viene chiamato automaticamente
    // alla fine del blocco try
}
```

**Sintassi:**

```java
// La risorsa viene dichiarata nelle parentesi tonde dopo 'try'
// e viene chiusa AUTOMATICAMENTE alla fine del blocco
try (Scanner scanner = new Scanner(new File("dati.txt"))) {
    // usa la risorsa normalmente
    while (scanner.hasNextLine()) {
        System.out.println(scanner.nextLine());
    }
    // scanner.close() viene chiamato AUTOMATICAMENTE qui
    // anche se viene lanciata un'eccezione
} catch (FileNotFoundException e) {
    System.out.println("File non trovato");
}
// non serve finally per chiudere la risorsa!
```

### Risorse multiple

Si possono dichiarare piu' risorse, separate da `;`. Vengono chiuse in **ordine inverso** rispetto alla dichiarazione:

```java
try (
    FileReader fr = new FileReader("input.txt");   // aperta per prima
    BufferedReader br = new BufferedReader(fr)      // aperta per seconda
) {
    String riga;
    while ((riga = br.readLine()) != null) {
        System.out.println(riga);
    }
    // chiusura automatica: prima br.close(), poi fr.close()
    // (ordine inverso rispetto alla dichiarazione)
}
```

### Vantaggi rispetto a try-catch-finally

| Aspetto              | try-catch-finally                  | try-with-resources                 |
| -------------------- | ---------------------------------- | ---------------------------------- |
| Chiusura risorsa     | Manuale nel `finally`              | Automatica                         |
| Rischio dimenticanza | Alto                               | Nullo                              |
| Codice               | Verboso                            | Conciso e leggibile                |
| Eccezioni in close() | Possono mascherare l'eccezione originale | Gestite come *suppressed exceptions* |
| Null check           | Necessario nel finally             | Non necessario                     |

### Esempio con Scanner nel corso

```java
// PRIMA: con finally (come nel lab Calcolatrice)
Scanner s = new Scanner(System.in);
try {
    double n1 = s.nextDouble();
    // ...
} catch (InputMismatchException e) {
    System.out.println("Errore");
} finally {
    s.close();  // chiusura manuale
}

// DOPO: con try-with-resources (equivalente, piu' pulito)
try (Scanner s = new Scanner(System.in)) {
    double n1 = s.nextDouble();
    // ...
} catch (InputMismatchException e) {
    System.out.println("Errore");
}
// s.close() viene chiamato automaticamente
```

---

## 6. throws nella firma del metodo

La clausola `throws` nella firma di un metodo **dichiara** che il metodo potrebbe lanciare un'eccezione di quel tipo. Non la lancia direttamente: informa il compilatore e il chiamante che devono essere pronti a gestirla.

### Regola fondamentale

- **Checked exception:** la clausola `throws` e' **OBBLIGATORIA**. Senza di essa il codice non compila.
- **Unchecked exception:** la clausola `throws` e' **FACOLTATIVA**. Si puo' aggiungere per documentazione.

```java
// OBBLIGATORIO: LiquidsException e' checked (extends Exception)
// senza "throws LiquidsException" il codice NON compila
public void versa(Contenitore sorgente) throws LiquidsException {
    if (this.liquido != null && sorgente.liquido != null
        && !this.liquido.equals(sorgente.liquido)) {
        throw new LiquidsException("liquidi incompatibili");
    }
    // logica di trasferimento...
    double spazio = calcolaVolume() - this.quantita;
    double daVersare = Math.min(sorgente.quantita, spazio);
    if (this.liquido == null) this.liquido = sorgente.liquido;
    this.quantita += daVersare;
    sorgente.quantita -= daVersare;
    if (sorgente.quantita == 0) sorgente.liquido = null;
}

// FACOLTATIVO: CapacityException e' unchecked (extends RuntimeException)
// il throws qui e' usato solo per documentazione
public void riempi(double quantita) {
    if (quantita > calcolaVolume()) {
        throw new CapacityException("capacita' superata");
    }
    this.quantita += quantita;
}
```

### Dichiarazioni multiple

Un metodo puo' dichiarare piu' eccezioni, separate da virgola:

```java
// questo metodo puo' lanciare due (o piu') eccezioni diverse
public static void prenota(String[] agenda, int ora, String nome)
        throws NullPointerException, AlreadyBookedException, TimeBusyException {
    // NullPointerException e' unchecked → il throws e' facoltativo
    if (agenda == null) throw new NullPointerException("Agenda non valida!");
    // ParameterException (supponiamo unchecked)
    if (nome == null || nome.equals("")) throw new ParameterException("nome non valido");
    if (ora < 13 || ora > 18) throw new ParameterException("orario non valido");
    // TimeBusyException → dichiarata nel throws
    if (agenda[ora - 13] != null) throw new TimeBusyException("Slot gia' prenotato");
    // AlreadyBookedException → dichiarata nel throws
    if (Arrays.asList(agenda).contains(nome))
        throw new AlreadyBookedException(nome + ", hai gia' prenotato!");
    agenda[ora - 13] = nome;
}
```

### Propagazione delle eccezioni

Quando un metodo dichiara `throws`, la responsabilita' di gestire l'eccezione passa al **chiamante**. Il chiamante ha due opzioni:

1. **Catturare** l'eccezione con `try-catch`
2. **Propagare** l'eccezione aggiungendo `throws` alla propria firma

```java
// Metodo che lancia
public void versa(Contenitore sorgente) throws LiquidsException {
    // ...
    throw new LiquidsException("liquidi incompatibili");
}

// OPZIONE 1: il chiamante cattura
public void eseguiVersamento() {
    try {
        contenitoreA.versa(contenitoreB);
    } catch (LiquidsException e) {
        System.out.println("Errore: " + e.getMessage());
    }
}

// OPZIONE 2: il chiamante propaga (aggiunge throws alla propria firma)
public void eseguiVersamento() throws LiquidsException {
    contenitoreA.versa(contenitoreB);
    // se versa() lancia LiquidsException, questa viene propagata
    // al chiamante di eseguiVersamento()
}
```

**Catena di propagazione tipica nell'esame:**

```
metodo() throws LiquidsException
    ↓ propaga
chiamante() throws LiquidsException
    ↓ propaga
main()  ← qui di solito si cattura con try-catch
```

---

## 7. throw per lanciare un'eccezione

L'istruzione `throw` crea e lancia **un'istanza** di eccezione. E' il meccanismo con cui il codice segnala che si e' verificata una situazione anomala.

### Sintassi

```java
throw new TipoEccezione("messaggio descrittivo");
//    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//    si crea un nuovo OGGETTO eccezione con new
//    e lo si lancia con throw
```

### Differenza tra throw e throws

| Aspetto     | `throw`                                    | `throws`                                    |
| ----------- | ------------------------------------------ | ------------------------------------------- |
| **Cosa e'** | Un'**istruzione** (statement)              | Una **clausola** nella firma del metodo     |
| **Dove**    | Dentro il corpo del metodo                 | Nella dichiarazione del metodo              |
| **Funzione**| **Lancia** un'eccezione concreta           | **Dichiara** che il metodo potrebbe lanciare|
| **Sintassi**| `throw new XException("msg");`             | `void metodo() throws XException { }`      |
| **Oggetto** | Richiede un **oggetto** eccezione (`new`)  | Richiede un **tipo** di eccezione           |

### Esempi dal corso

```java
// THROW: usato per controllare precondizioni e vincoli di dominio

// Controllo nel costruttore (unchecked → errore logico)
protected Contenitore(String liquido, double quantita) {
    if (quantita < 0) {
        // il programmatore non dovrebbe mai passare quantita' negativa
        throw new IllegalArgumentException("quantita' negativa");
    }
    if (quantita > calcolaVolume()) {
        // errore logico: capacita' superata
        throw new CapacityException("troppo liquido per il contenitore");
    }
    this.liquido = liquido;
    this.quantita = quantita;
}

// Controllo in un metodo di dominio (checked → situazione recuperabile)
public void versa(Contenitore sorgente) throws LiquidsException {
    if (this.liquido != null && sorgente.liquido != null
        && !this.liquido.equals(sorgente.liquido)) {
        // situazione di dominio: liquidi diversi → recuperabile
        throw new LiquidsException("liquidi incompatibili");
    }
    // se arriviamo qui, i liquidi sono compatibili...
}

// Controllo nello switch per input non riconosciuto
public static double calculate(double o1, double o2, char op) {
    switch (op) {
        case '+': return o1 + o2;
        case '-': return o1 - o2;
        case '*': return o1 * o2;
        case '/':
            if (o2 == 0) throw new DivisionByZeroException("Non possibile dividere per 0.");
            return o1 / o2;
        default:
            throw new InputMismatchException("Operatore non riconosciuto");
    }
}
```

### Pattern: controlli multipli con throw

Un singolo metodo puo' contenere piu' istruzioni `throw` per diverse condizioni di errore:

```java
// Pattern tipico nell'esame: piu' controlli sequenziali
public void inserisci(int giorno, Sorpresa s) throws GiornoException {
    // 1. Controllo unchecked: parametro null (errore logico)
    if (s == null) {
        throw new IllegalArgumentException("sorpresa null");
    }

    // 2. Controllo unchecked: giorno fuori range (errore logico)
    if (giorno < 1 || giorno > 31) {
        throw new IllegalArgumentException("giorno non valido");
    }

    // 3. Controllo checked: giorno occupato (situazione di dominio)
    if (caselle[giorno - 1] != null) {
        throw new GiornoException("giorno " + giorno + " gia' occupato");
    }

    // 4. Controllo unchecked: sorpresa duplicata (errore logico)
    for (Sorpresa esistente : caselle) {
        if (esistente != null && esistente.equals(s)) {
            throw new SorpresaException("sorpresa gia' presente");
        }
    }

    // se tutti i controlli passano, inserisci
    caselle[giorno - 1] = s;
}
```

---

## 8. Definire eccezioni personalizzate

In ogni esame di Programmazione 2 viene richiesto di definire due eccezioni personalizzate: una **checked** e una **unchecked**. Ciascuna vale circa **1 punto** nella ScalaValutazione.

### Eccezione checked personalizzata (extends Exception)

```java
/**
 * Eccezione CHECKED: rappresenta una situazione di dominio recuperabile.
 * Chi chiama un metodo che lancia questa eccezione e' OBBLIGATO
 * a gestirla (con try-catch o propagandola con throws).
 */
public class LiquidsException extends Exception {

    // Costruttore con messaggio: il pattern piu' comune
    public LiquidsException(String msg) {
        super(msg);  // passa il messaggio a Exception
        // getMessage() restituira' questo messaggio
    }
}
```

### Eccezione unchecked personalizzata (extends RuntimeException)

```java
/**
 * Eccezione UNCHECKED: rappresenta un errore logico del programmatore.
 * Il compilatore NON obbliga a gestirla.
 */
public class CapacityException extends RuntimeException {

    // Costruttore con messaggio
    public CapacityException(String msg) {
        super(msg);  // passa il messaggio a RuntimeException
    }
}
```

### Costruttori con super(msg)

La chiamata `super(msg)` e' fondamentale: passa il messaggio alla classe padre (`Exception` o `RuntimeException`), che a sua volta lo passa a `Throwable`. Il messaggio viene poi recuperato con `getMessage()`.

```java
// Il costruttore di Throwable salva il messaggio
public class Throwable {
    private String detailMessage;

    public Throwable(String message) {
        this.detailMessage = message;
    }

    public String getMessage() {
        return detailMessage;
    }
}
```

**Costruttori multipli** (facoltativi ma utili):

```java
// Eccezione custom dal lab con due costruttori
public class DivisionByZeroException extends ArithmeticException {

    // Costruttore senza argomenti
    public DivisionByZeroException() {
        super();  // nessun messaggio
    }

    // Costruttore con messaggio
    public DivisionByZeroException(String s) {
        super(s);  // passa il messaggio al padre ArithmeticException
    }
}
```

### Pattern d'esame ricorrente nei 3 esami

| Esame              | Checked (`extends Exception`)                 | Unchecked (`extends RuntimeException`)              |
| ------------------ | --------------------------------------------- | --------------------------------------------------- |
| Simulazione Vetreria | `LiquidsException` (liquidi incompatibili)  | `CapacityException` (capacita' superata)            |
| Dicembre           | `GiornoException` (giorno occupato)           | `SorpresaException` (sorpresa duplicata)            |
| Gennaio            | *(non presente)*                              | `EventoException` (evento duplicato)                |

**Lo schema e' costante:** errore di dominio recuperabile → `extends Exception` (checked); errore logico del chiamante → `extends RuntimeException` (unchecked).

### Template rapido: creare un'eccezione in 3 righe

All'esame, ogni eccezione personalizzata e' essenzialmente costituita da tre righe:

```java
// Template CHECKED:
public class NomeException extends Exception {
    public NomeException(String msg) { super(msg); }
}

// Template UNCHECKED:
public class NomeException extends RuntimeException {
    public NomeException(String msg) { super(msg); }
}
```

Sono **3 righe** per ciascuna e valgono **1 punto ciascuna** nella ScalaValutazione. Non dimenticarle.

---

## 9. Eccezioni e override: regole precise

Quando si fa l'override di un metodo in una sottoclasse, esistono regole precise riguardo alle eccezioni checked dichiarate. Queste regole derivano dal **principio di sostituibilita' di Liskov**: il codice scritto per il tipo del padre deve funzionare anche con un'istanza del figlio.

### Regola 1: l'override NON puo' aggiungere nuove checked exceptions

Se il metodo del padre dichiara certe checked exceptions, il metodo del figlio **non puo' dichiararne di nuove** (cioe' che il padre non dichiara). Il motivo e' che il chiamante e' stato scritto supponendo di dover gestire solo le eccezioni del padre: se il figlio ne lanciasse di nuove, il codice del chiamante si romperebbe.

```java
class Padre {
    // il padre dichiara IOException
    void leggi() throws IOException {
        // ...
    }
}

class Figlio extends Padre {
    @Override
    void leggi() throws IOException {
        // OK: stessa eccezione del padre
    }

    // ERRORE DI COMPILAZIONE:
    // @Override
    // void leggi() throws IOException, SQLException {
    //     // SQLException NON e' dichiarata nel padre!
    //     // Il chiamante che usa il tipo Padre non e'
    //     // preparato a gestire SQLException
    // }
}
```

**Perche'?** Consideriamo il codice del chiamante:

```java
// Il chiamante usa il tipo STATICO Padre
Padre p = new Figlio();  // tipo dinamico: Figlio
try {
    p.leggi();  // il compilatore guarda le throws di Padre
} catch (IOException e) {
    // gestisco IOException come dichiarato da Padre
}
// Se Figlio potesse aggiungere SQLException, questa NON verrebbe
// catturata dal catch qui sopra → il programma si romperebbe
```

### Regola 2: l'override puo' dichiarare un sottoinsieme, o nessuna

Il metodo del figlio puo' dichiarare **meno** eccezioni rispetto al padre, o **nessuna**. Il chiamante e' preparato a gestire le eccezioni del padre; se il figlio non le lancia, il `catch` del chiamante semplicemente non viene mai eseguito (nessun problema).

```java
class Padre {
    // il padre dichiara due checked exceptions
    void elabora() throws IOException, SQLException {
        // ...
    }
}

class FiglioA extends Padre {
    @Override
    void elabora() throws IOException {
        // OK: sottoinsieme delle eccezioni del padre
        // (solo IOException, senza SQLException)
    }
}

class FiglioB extends Padre {
    @Override
    void elabora() {
        // OK: nessuna eccezione → sottoinsieme vuoto
        // il padre ne dichiarava due, il figlio nessuna
    }
}

class FiglioC extends Padre {
    @Override
    void elabora() throws FileNotFoundException {
        // OK: FileNotFoundException extends IOException
        // quindi e' un SOTTOTIPO di un'eccezione del padre
        // (piu' specifico, non piu' generico)
    }
}
```

### Regola 3: le eccezioni unchecked sono libere

Le eccezioni unchecked (`RuntimeException` e sottotipi) **non sono soggette** a queste regole. Il figlio puo' sempre lanciare unchecked exception, indipendentemente da cosa dichiara il padre.

```java
class Padre {
    void metodo() throws IOException {
        // ...
    }
}

class Figlio extends Padre {
    @Override
    void metodo() throws IOException {
        // puo' lanciare eccezioni unchecked senza dichiararle
        if (condizione) {
            throw new IllegalArgumentException("errore");  // OK: unchecked
        }
        if (altraCondizione) {
            throw new CapacityException("superata");  // OK: unchecked
        }
    }
}
```

### Tabella riassuntiva delle regole

| Azione nell'override                                    | Consentita? |
| ------------------------------------------------------- | ----------- |
| Dichiarare le **stesse** checked exceptions del padre   | Si          |
| Dichiarare un **sottoinsieme** delle checked del padre  | Si          |
| Dichiarare **nessuna** checked exception                | Si          |
| Dichiarare un **sottotipo** di un'eccezione del padre   | Si          |
| Aggiungere **nuove** checked exceptions                 | **NO**      |
| Dichiarare un'eccezione **piu' ampia** di quella del padre | **NO**   |
| Aggiungere **unchecked** exceptions                     | Si (sempre) |

### Esempio pratico con la gerarchia del corso

```java
public abstract class Contenitore {
    // il metodo astratto dichiara LiquidsException (checked)
    public abstract void versa(Contenitore sorgente) throws LiquidsException;
}

public class Sfera extends Contenitore {
    @Override
    public void versa(Contenitore sorgente) throws LiquidsException {
        // OK: stessa eccezione del padre
        if (!compatibili(this, sorgente)) {
            throw new LiquidsException("incompatibili");
        }
        // ... logica di versamento ...
    }
}

public class Cilindro extends Contenitore {
    @Override
    public void versa(Contenitore sorgente) {
        // OK: nessuna eccezione dichiarata (sottoinsieme vuoto)
        // questo Cilindro accetta qualsiasi liquido
    }
}

// ERRORE:
// public class Cuboide extends Contenitore {
//     @Override
//     public void versa(Contenitore sorgente) throws LiquidsException, IOException {
//         // ERRORE: IOException NON era dichiarata nel padre!
//     }
// }
```

---

## 10. Pattern d'esame: eccezioni nella simulazione e negli esami

### Come vengono usate le eccezioni nel contesto d'esame

All'esame di Programmazione 2 le eccezioni compaiono in tre contesti:

1. **Definizione delle classi eccezione** (2 punti nella ScalaValutazione)
2. **Lancio nei metodi** (costruttori, metodi di dominio, metodi della collezione)
3. **Gestione nel Main** (try-catch durante la lettura dell'input)

### Pattern 1: eccezioni nel costruttore (unchecked)

I costruttori usano eccezioni **unchecked** per segnalare errori logici nei parametri:

```java
protected Contenitore(String liquido, double quantita) {
    // l'eccezione unchecked NON richiede throws nella firma
    if (quantita > calcolaVolume()) {
        throw new CapacityException("troppo liquido");
    }
    this.liquido = liquido;
    this.quantita = quantita;
}

// Il chiamante non e' obbligato a catturarla:
Contenitore c = new Sfera(5.0, "acqua", 1000.0);
// se 1000.0 supera la capacita' → CapacityException a runtime
```

### Pattern 2: eccezioni nei metodi di dominio (checked)

I metodi di dominio usano eccezioni **checked** per situazioni recuperabili:

```java
// la checked exception DEVE essere dichiarata con throws
public void versa(Contenitore sorgente) throws LiquidsException {
    if (this.liquido != null && sorgente.liquido != null
        && !this.liquido.equals(sorgente.liquido)) {
        throw new LiquidsException("liquidi incompatibili");
    }
    double spazio = calcolaVolume() - this.quantita;
    double daVersare = Math.min(sorgente.quantita, spazio);
    if (this.liquido == null) this.liquido = sorgente.liquido;
    this.quantita += daVersare;
    sorgente.quantita -= daVersare;
    if (sorgente.quantita == 0) sorgente.liquido = null;
}
```

### Pattern 3: mix checked/unchecked nella collezione

Nella classe che gestisce la collezione, si usano **entrambi** i tipi:

```java
public void inserisci(int giorno, Sorpresa s) throws GiornoException {
    // CHECKED: giorno occupato → il chiamante deve gestire
    if (caselle[giorno - 1] != null)
        throw new GiornoException("giorno occupato");

    // UNCHECKED: sorpresa duplicata → errore logico del chiamante
    for (Sorpresa esistente : caselle) {
        if (esistente != null && esistente.equals(s))
            throw new SorpresaException("gia' presente");
    }

    caselle[giorno - 1] = s;
}
```

### Pattern 4: try-catch nel Main

Il `main` cattura le eccezioni checked nel blocco di lettura dell'input:

```java
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Calendario calendario = new Calendario();

    while (sc.hasNextLine()) {
        String riga = sc.nextLine();
        // parsing della riga...
        try {
            calendario.inserisci(giorno, sorpresa);
            // se inserisci() lancia GiornoException (checked),
            // il catch la gestisce stampando il messaggio
        } catch (GiornoException e) {
            System.out.println("\tEccezione: " + e.getMessage());
        }
        // le unchecked (SorpresaException) NON vengono catturate qui
        // → propagano fino alla JVM e terminano il programma
        // (oppure possono essere catturate in un blocco separato)
    }
}
```

### Pattern 5: eccezioni multiple nel lab Ricevimento

Un esempio completo combinazione di diversi tipi di eccezioni:

```java
public class Ricevimento {

    // dichiara le eccezioni che il metodo puo' lanciare
    // (NullPointerException e' unchecked → non servirebbe nel throws)
    public static void prenota(String[] agenda, int ora, String nome)
            throws NullPointerException, AlreadyBookedException, TimeBusyException {

        // 1. Controllo agenda null → unchecked
        if (agenda == null)
            throw new NullPointerException("Agenda non valida!");

        // 2. Controllo parametri → unchecked
        if (nome == null || nome.equals(""))
            throw new ParameterException("nome non valido");
        if (ora < 13 || ora > 18)
            throw new ParameterException("orario non valido");

        // 3. Controllo slot occupato → checked (o unchecked, dipende dal README)
        if (agenda[ora - 13] != null)
            throw new TimeBusyException("Slot gia' prenotato");

        // 4. Controllo prenotazione duplicata → checked (o unchecked)
        if (Arrays.asList(agenda).contains(nome))
            throw new AlreadyBookedException(nome + ", hai gia' prenotato!");

        // se tutti i controlli passano, prenota
        agenda[ora - 13] = nome;
    }
}
```

### ATTENZIONE: discrepanza ScalaValutazione vs README

> **REGOLA FONDAMENTALE:** la ScalaValutazione puo' avere le eccezioni **invertite** (checked al posto di unchecked e viceversa) rispetto al README del testo d'esame. Segui **SEMPRE il README** come fonte autorevole.

Esempio concreto dalla simulazione Vetreria:

| Fonte            | `LiquidsException`        | `CapacityException`         |
| ---------------- | ------------------------- | --------------------------- |
| **ScalaValutazione** | unchecked (RuntimeException) | checked (Exception)     |
| **README**        | **checked** (Exception)   | **unchecked** (RuntimeException) |

Il README descrive il **significato semantico** delle eccezioni:
- `LiquidsException`: "liquidi incompatibili" → situazione di **dominio recuperabile** → **checked**
- `CapacityException`: "capacita' superata" → **errore logico** del chiamante → **unchecked**

La ScalaValutazione contiene un **refuso**. In fase d'esame, segui il README.

### Checklist eccezioni per l'esame

Prima di consegnare, verifica:

- [ ] Le due classi eccezione sono state create (`extends Exception` e `extends RuntimeException`)
- [ ] Ogni classe eccezione ha il costruttore con `super(msg)`
- [ ] I metodi che lanciano checked exceptions hanno `throws` nella firma
- [ ] I metodi che lanciano unchecked exceptions **non** hanno necessariamente `throws` (ma possono averlo)
- [ ] Il `Main` cattura le checked exceptions con `try-catch`
- [ ] Il messaggio passato all'eccezione e' significativo e leggibile
- [ ] L'ordine dei `catch` e' dal piu' specifico al piu' generico
- [ ] Le risorse (Scanner, ecc.) vengono chiuse nel `finally` o con `try-with-resources`
- [ ] Nell'override, non sono state aggiunte nuove checked exceptions

---

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
---

# PARTE 5 -- Astrazione e Specifiche

---

Questa parte tratta il nucleo teorico del corso di Programmazione 2: i concetti di astrazione, specifica formale dei metodi, tipi di dato astratti (ADT), funzione di astrazione (AF), invariante di rappresentazione (RI) e rep exposure. Si tratta di argomenti che il docente valuta esplicitamente sia nel filtro teoria sia nella prova pratica. AF e RI valgono **0.5 punti ciascuno per classe** nella scala di valutazione dell'esame: dimenticarli equivale a perdere punti certi.

---

## 1. Astrazione per specifica

### Definizione

L'**astrazione per specifica** e' il principio secondo cui il chiamante di un metodo utilizza quel metodo **esclusivamente sulla base della sua specifica** (cioe' della documentazione formale), senza conoscere ne' dipendere dall'implementazione interna.

In altre parole, la specifica costituisce un **contratto** tra chi implementa il metodo (l'implementatore) e chi lo usa (il chiamante):

- L'**implementatore** si impegna a garantire le postcondizioni (EFFECTS), a patto che le precondizioni (REQUIRES) siano soddisfatte.
- Il **chiamante** si impegna a soddisfare le precondizioni e puo' fare affidamento sulle postcondizioni.

Questo principio ha implicazioni fondamentali:

1. **Indipendenza dall'implementazione**: il chiamante non deve leggere il codice del metodo per usarlo.
2. **Sostituibilita'**: l'implementazione puo' cambiare senza invalidare il codice chiamante, purche' la specifica rimanga la stessa.
3. **Modularita'**: ogni modulo puo' essere sviluppato, testato e ragionato indipendentemente.

```java
// Il chiamante usa versa() basandosi SOLO sulla specifica,
// senza sapere come e' implementato il trasferimento del liquido.

/**
 * REQUIRES: -
 * MODIFIES: this, sorgente
 * EFFECTS: versa il liquido da sorgente in this.
 *          Se i liquidi sono incompatibili lancia LiquidsException (checked).
 *          Se this ha capienza sufficiente, tutto il liquido viene trasferito.
 *          Altrimenti this viene riempito fino alla capienza e il resto rimane in sorgente.
 */
public void versa(Contenitore sorgente) throws LiquidsException {
    // L'implementazione e' IRRILEVANTE per il chiamante.
    // Il chiamante sa solo che:
    //   - puo' passare qualsiasi Contenitore (nessun REQUIRES restrittivo)
    //   - this e sorgente verranno modificati
    //   - se i liquidi sono diversi, viene lanciata LiquidsException
    //   - altrimenti il liquido viene trasferito (totalmente o parzialmente)
}
```

**Conseguenza pratica per l'esame:** quando scrivi un metodo, la specifica DEVE essere completa: chi legge solo la specifica deve poter usare il metodo correttamente senza guardare il corpo.

---

## 2. Le clausole REQUIRES / MODIFIES / EFFECTS

### Formato standard

Ogni metodo pubblico viene documentato con tre clausole:

```
REQUIRES: precondizioni (cosa deve essere vero PRIMA della chiamata)
MODIFIES: quali oggetti vengono modificati (this, parametri, System.out, ...)
EFFECTS:  postcondizioni (cosa fa il metodo SE il REQUIRES e' soddisfatto)
```

### 2.1 REQUIRES -- Precondizioni

La clausola `REQUIRES` specifica le condizioni che devono essere vere **prima** della chiamata al metodo. Se il chiamante viola il REQUIRES, il comportamento del metodo e' **indefinito** (puo' accadere qualsiasi cosa).

```java
/**
 * REQUIRES: n >= 0
 * EFFECTS: restituisce il fattoriale di n
 */
public static long fattoriale(int n) {
    // Se il chiamante passa n = -3, il comportamento e' indefinito:
    // potrebbe andare in ricorsione infinita, restituire un valore sbagliato,
    // o lanciare un'eccezione. Non c'e' alcuna garanzia.
    if (n == 0) return 1;
    return n * fattoriale(n - 1);
}
```

Quando REQUIRES e' assente o vale `-` (trattino), significa che **non ci sono precondizioni**: il metodo funziona per qualsiasi input valido per i tipi dei parametri.

```java
/**
 * REQUIRES: -
 * MODIFIES: this
 * EFFECTS: aggiunge c a this.
 *          Se c e' null lancia NullPointerException (unchecked).
 */
public void aggiungi(Contenitore c) {
    // Nessun REQUIRES: il metodo gestisce internamente tutti i casi,
    // incluso c == null (lanciando un'eccezione).
    if (c == null) throw new NullPointerException();
    contenitori.add(c);
}
```

### 2.2 MODIFIES -- Oggetti modificati

La clausola `MODIFIES` elenca **tutti** gli oggetti il cui stato viene alterato durante l'esecuzione del metodo. Gli oggetti tipici sono:

| Oggetto        | Significato                                          |
| -------------- | ---------------------------------------------------- |
| `this`         | L'oggetto su cui viene invocato il metodo            |
| un parametro   | Un parametro passato al metodo (es. `sorgente`)      |
| `System.out`   | Il metodo produce output su console                  |

Se il metodo e' un **osservatore puro** (non modifica nulla), la clausola MODIFIES e' assente oppure vale `-`.

```java
/**
 * REQUIRES: -
 * MODIFIES: -
 * EFFECTS: restituisce il volume della sfera calcolato come (4/3) * pi * r^3
 */
public double calcolaVolume() {
    // Nessun MODIFIES: il metodo non altera nulla, restituisce solo un valore.
    return Math.PI * Math.pow(raggio, 3) * 4.0 / 3.0;
}

/**
 * REQUIRES: -
 * MODIFIES: this, sorgente
 * EFFECTS: versa il liquido da sorgente in this.
 *          Se i liquidi sono incompatibili lancia LiquidsException.
 */
public void versa(Contenitore sorgente) throws LiquidsException {
    // MODIFIES elenca sia this sia sorgente perche' entrambi cambiano:
    // this riceve liquido, sorgente lo perde.
    // ...
}
```

### 2.3 EFFECTS -- Postcondizioni

La clausola `EFFECTS` descrive cosa fa il metodo **quando il REQUIRES e' soddisfatto**. Include:

- Cosa viene restituito (se non void)
- Come cambia lo stato degli oggetti elencati in MODIFIES
- Quali eccezioni vengono lanciate e in quali condizioni
- Se viene lanciata un'eccezione, cosa succede allo stato (viene modificato oppure no?)

```java
/**
 * MODIFIES: this
 * EFFECTS: inserisce la sorpresa s nel giorno specificato.
 *          Se il giorno e' gia' occupato, lancia GiornoException (checked)
 *          e this non viene modificato.
 *          Se una sorpresa equals a s e' gia' presente nel calendario,
 *          lancia SorpresaException (unchecked) e this non viene modificato.
 */
public void inserisci(int giorno, Sorpresa s) throws GiornoException {
    // La specifica EFFECTS descrive COMPLETAMENTE il comportamento:
    // 1. Caso normale: la sorpresa viene inserita nella casella del giorno
    // 2. Caso eccezionale 1: giorno occupato -> GiornoException
    // 3. Caso eccezionale 2: sorpresa duplicata -> SorpresaException
    // 4. In entrambi i casi eccezionali, this non cambia (nessuna modifica parziale)

    if (giorno < 1 || giorno > 31)
        throw new IllegalArgumentException("Giorno non valido");

    for (Sorpresa esistente : caselle) {
        if (esistente != null && esistente.equals(s))
            throw new SorpresaException("Sorpresa gia' presente");
    }

    if (caselle[giorno - 1] != null)
        throw new GiornoException("Giorno gia' occupato");

    caselle[giorno - 1] = s;
}
```

### Riepilogo visivo

```
┌─────────────────────────────────────────────────────────┐
│  SPECIFICA DI UN METODO                                 │
│                                                         │
│  REQUIRES: cosa deve essere vero PRIMA della chiamata   │
│            (vincoli sul chiamante)                       │
│                                                         │
│  MODIFIES: quali oggetti cambiano stato                 │
│            (vincolo di trasparenza)                      │
│                                                         │
│  EFFECTS:  cosa succede DOPO la chiamata                │
│            SE il REQUIRES e' soddisfatto                │
│            (vincoli sull'implementatore)                 │
└─────────────────────────────────────────────────────────┘
```

---

## 3. Metodi totali vs metodi parziali

### Definizione

Un metodo si dice **totale** se e' definito per **ogni** input ammesso dai tipi dei parametri. In pratica, un metodo totale non ha un REQUIRES restrittivo: gestisce tutti i casi, eventualmente lanciando eccezioni per gli input non validi.

Un metodo si dice **parziale** se ha precondizioni nel REQUIRES che restringono il dominio degli input. Se il chiamante viola queste precondizioni, il comportamento e' **indefinito**.

| Caratteristica          | Metodo Totale                       | Metodo Parziale                             |
| ----------------------- | ----------------------------------- | ------------------------------------------- |
| REQUIRES                | Assente o `-`                       | Presente con condizioni restrittive         |
| Input non valido        | Lancia eccezione (comportamento definito) | Comportamento indefinito                    |
| Responsabilita' errori  | L'implementatore gestisce tutto     | Il chiamante deve garantire le precondizioni|
| Robustezza              | Alta                                | Bassa                                       |
| Nei metodi d'esame      | Quasi sempre usato                  | Raro                                        |

### Esempio di metodo parziale

```java
/**
 * REQUIRES: divisore != 0
 * EFFECTS: restituisce dividendo / divisore
 */
public static double dividi(double dividendo, double divisore) {
    // Se il chiamante passa divisore = 0, il risultato e' indefinito.
    // Il metodo NON controlla: si fida che il chiamante rispetti il REQUIRES.
    return dividendo / divisore;
}
```

### Esempio di metodo totale equivalente

```java
/**
 * REQUIRES: -
 * EFFECTS: restituisce dividendo / divisore.
 *          Se divisore == 0 lancia ArithmeticException (unchecked).
 */
public static double dividi(double dividendo, double divisore) {
    // Il metodo gestisce TUTTI i casi, incluso divisore == 0.
    // Non si fida del chiamante: controlla e lancia un'eccezione.
    if (divisore == 0) {
        throw new ArithmeticException("Divisione per zero");
    }
    return dividendo / divisore;
}
```

### Come rendere un metodo parziale totale

La trasformazione avviene in tre passi:

1. **Identificare** le precondizioni nel REQUIRES
2. **Aggiungere un controllo** nel corpo del metodo per ciascuna precondizione
3. **Lanciare un'eccezione appropriata** quando la precondizione e' violata
4. **Documentare l'eccezione** nella clausola EFFECTS
5. **Rimuovere** (o svuotare) la clausola REQUIRES

```java
// ===== PRIMA: metodo parziale =====
/**
 * REQUIRES: index >= 0 && index < size()
 * EFFECTS: restituisce l'elemento in posizione index
 */
public T get(int index) {
    return elementi[index];    // se index e' fuori range: ArrayIndexOutOfBoundsException
                               // (ma la specifica non lo definisce -> comportamento indefinito)
}

// ===== DOPO: metodo totale =====
/**
 * REQUIRES: -
 * EFFECTS: restituisce l'elemento in posizione index.
 *          Se index < 0 o index >= size(), lancia IndexOutOfBoundsException.
 */
public T get(int index) {
    if (index < 0 || index >= size()) {
        throw new IndexOutOfBoundsException("Indice " + index + " fuori range [0, " + size() + ")");
    }
    return elementi[index];
}
```

### Esempio dal corso: costruttore della Sfera

```java
// Il costruttore della Sfera e' TOTALE: gestisce tutti gli input,
// lanciando eccezioni per quelli non validi.

public class Sfera extends Contenitore {
    private final double raggio;

    /**
     * REQUIRES: -
     * EFFECTS: crea una sfera di raggio r con liquido e quantita' specificati.
     *          Se r <= 0 lancia IllegalArgumentException.
     *          Se quantita' > volume lancia CapacityException (unchecked).
     */
    public Sfera(double raggio, String liquido, double quantita) {
        // Controllo 1: raggio deve essere positivo
        if (raggio <= 0) {
            throw new IllegalArgumentException("Raggio deve essere positivo");
        }
        this.raggio = raggio;

        // Controllo 2: la quantita' non puo' superare il volume
        if (quantita > calcolaVolume()) {
            throw new CapacityException("La sfera ha capienza: "
                + calcolaVolume() + " ma il liquido ha un volume di: " + quantita);
        }
        // ...
    }
}
```

**Regola per l'esame:** all'esame quasi tutti i metodi sono **totali**. Si preferisce sempre controllare gli input e lanciare eccezioni piuttosto che delegare la responsabilita' al chiamante con un REQUIRES.

---

## 4. Abstract Data Type (ADT)

### Definizione

Un **Abstract Data Type** (Tipo di Dato Astratto) e' un tipo definito **dalle sue operazioni**, non dalla sua rappresentazione interna. L'utente dell'ADT conosce solo **cosa** puo' fare con quel tipo (attraverso le specifiche dei metodi), ma non **come** il tipo e' implementato internamente.

Questo principio e' alla base dell'information hiding: i campi sono `private`, e l'unico modo per l'esterno di interagire con l'oggetto e' attraverso i metodi pubblici.

```java
// L'utente di Vetreria sa che:
//   - puo' aggiungere Contenitori
//   - puo' estrarre Contenitori per tipo di liquido
//   - puo' iterare sui Contenitori in ordine di capienza
// Ma NON sa se internamente usa un ArrayList, un LinkedList, un array,
// un TreeSet, o qualsiasi altra struttura dati.

public class Vetreria implements Iterable<Contenitore> {
    // La rappresentazione interna e' NASCOSTA: l'utente non la vede.
    private final List<Contenitore> contenitori;

    // L'utente interagisce SOLO tramite le operazioni pubbliche.
    public void aggiungi(Contenitore c) { /* ... */ }
    public Vetreria estrai(String liquido) { /* ... */ }
    public Iterator<Contenitore> iterator() { /* ... */ }
}
```

### Categorie di operazioni di un ADT

Le operazioni di un ADT si classificano in quattro categorie:

| Categoria       | Descrizione                            | Input           | Output             | Esempio dal corso                    |
| --------------- | -------------------------------------- | --------------- | ------------------ | ------------------------------------ |
| **Creatore**    | Crea nuove istanze da zero             | Argomenti       | Nuova istanza      | `new Sfera(5.0)`, `new Vetreria()`  |
| **Produttore**  | Crea nuove istanze da istanze esistenti| Istanza + args  | Nuova istanza      | `clone()`, `estrai("acqua")`         |
| **Osservatore** | Restituisce informazioni senza modificare | Istanza      | Valore             | `calcolaVolume()`, `getLiquido()`    |
| **Mutatore**    | Modifica lo stato dell'istanza         | Istanza + args  | void (tipicamente) | `versa()`, `aggiungi()`, `inserisci()` |

```java
public abstract class Contenitore implements Comparable<Contenitore>, Cloneable {

    // ===== CREATORE: costruisce una nuova istanza da zero =====
    protected Contenitore(String liquido, double quantita) {
        // Crea un contenitore con il liquido e la quantita' specificati
        this.liquido = liquido;
        this.quantita = quantita;
    }

    // ===== PRODUTTORE: crea una nuova istanza da un'istanza esistente =====
    @Override
    public Contenitore clone() {
        // Restituisce una NUOVA istanza che e' una copia di this
        try {
            return (Contenitore) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    // ===== OSSERVATORE: restituisce informazioni senza modificare =====
    public abstract double calcolaVolume();   // non modifica nulla
    public String getLiquido() { return liquido; }
    public double getQuantita() { return quantita; }

    // ===== MUTATORE: modifica lo stato dell'istanza =====
    public void versa(Contenitore sorgente) throws LiquidsException {
        // Modifica sia this (riceve liquido) sia sorgente (perde liquido)
        // ...
    }
}
```

### ADT immutabile vs ADT mutabile

| Aspetto         | ADT Immutabile                             | ADT Mutabile                               |
| --------------- | ------------------------------------------ | ------------------------------------------ |
| Definizione     | Lo stato non cambia dopo la creazione      | Lo stato puo' cambiare dopo la creazione   |
| Mutatori        | **Nessuno**                                | Presenti                                   |
| Produttori      | Restituiscono **nuove** istanze            | Possono modificare this o restituire nuove |
| Thread safety   | Intrinsecamente thread-safe                | Richiede sincronizzazione                  |
| Esempio JDK     | `String`, `Integer`, `LocalDate`           | `ArrayList`, `StringBuilder`, `HashMap`    |
| Esempio esame   | `Sorpresa` (nome + costo fissi)            | `Contenitore` (liquido cambia con `versa`) |

```java
// IMMUTABILE: una volta creata, la Sorpresa non cambia mai stato.
// Non ha mutatori. Il nome e il costo sono fissati alla costruzione.
public abstract class Sorpresa implements Comparable<Sorpresa> {
    private final String nome;    // final: non riassegnabile

    protected Sorpresa(String nome) {
        this.nome = nome;         // stato fissato qui, mai piu' modificato
    }

    // Solo OSSERVATORI, nessun MUTATORE:
    public String getNome() { return nome; }
    public abstract double getCosto();
}
```

```java
// MUTABILE: il Contenitore cambia stato dopo la creazione.
// Il metodo versa() e' un MUTATORE che altera liquido e quantita'.
public abstract class Contenitore implements Comparable<Contenitore> {
    // OVERVIEW: Un Contenitore e' un recipiente MUTABILE che puo' contenere
    //           un liquido fino alla sua capienza massima.

    private String liquido;       // puo' cambiare (da "acqua" a null, ecc.)
    private double quantita;      // puo' cambiare con versa()

    public void versa(Contenitore sorgente) throws LiquidsException {
        // MUTATORE: modifica this.quantita, this.liquido,
        //           sorgente.quantita, sorgente.liquido
        // ...
    }
}
```

**Nota per l'OVERVIEW:** quando si documenta una classe, e' fondamentale specificare se il tipo e' **mutabile o immutabile**. Questo influenza il ragionamento del chiamante sulla condivisione dei riferimenti.

---

## 5. Funzione di Astrazione AF(this)

### Definizione formale

La **Funzione di Astrazione** (AF) e' una mappatura che associa ogni stato concreto (i valori dei campi privati) al **valore astratto** che l'utente percepisce.

```
AF: spazio di rappresentazione --> spazio astratto
```

In termini piu' semplici: i campi privati di un oggetto sono la "rappresentazione concreta". L'AF spiega **quale significato** hanno quei campi dal punto di vista dell'utente.

Proprieta' della AF:

- **Suriettiva**: ogni valore astratto ha almeno una rappresentazione concreta che lo produce.
- **Non necessariamente iniettiva**: stati concreti diversi possono corrispondere allo stesso valore astratto.

```
Esempio: un Set<Integer> implementato con ArrayList<Integer>

Stato concreto [1, 2, 3]  --AF-->  {1, 2, 3}
Stato concreto [3, 1, 2]  --AF-->  {1, 2, 3}
Stato concreto [2, 3, 1]  --AF-->  {1, 2, 3}

Tre stati concreti diversi, ma lo stesso valore astratto {1, 2, 3}.
La AF NON e' iniettiva: piu' rappresentazioni -> stesso significato.
```

### Come scrivere una buona AF

La AF deve:

1. Essere scritta come commento nella classe, subito dopo i campi privati
2. Usare il formato `AF(this): ...`
3. Riferirsi esplicitamente ai campi provati e spiegare il loro significato
4. Coprire tutti i casi significativi (es. quando un campo e' `null`)

```java
public abstract class Contenitore implements Comparable<Contenitore>, Cloneable {
    // OVERVIEW: Un Contenitore e' un recipiente MUTABILE che puo' contenere
    //           un liquido fino alla sua capienza massima (volume).

    private String liquido;       // il tipo di liquido contenuto (null se vuoto)
    private double quantita;      // la quantita' di liquido attualmente presente

    // AF(this): un contenitore che contiene 'quantita' unita' di 'liquido'.
    //           Se liquido == null, il contenitore e' vuoto.

    // La AF spiega il SIGNIFICATO dei campi:
    //   - liquido = quale liquido c'e' dentro (null = vuoto)
    //   - quantita = quanto liquido c'e'
    // L'utente non sa che esiste un campo String e un campo double:
    // sa solo che c'e' un contenitore con un certo liquido e una certa quantita'.
}
```

### Esempi concreti dal corso

#### AF nella sottoclasse (Sfera)

```java
public class Sfera extends Contenitore {
    private final double raggio;

    // AF(this): una sfera di raggio 'raggio'
    //
    // Nota: la AF della sottoclasse si riferisce solo ai campi PROPRI.
    // Il significato dei campi ereditati (liquido, quantita) e' gia' descritto
    // nella AF della superclasse Contenitore.
}
```

#### AF nella sottoclasse (Cilindro)

```java
public class Cilindro extends Contenitore {
    private final double altezza;
    private final double raggio;

    // AF(this): un cilindro di altezza 'altezza' e raggio 'raggio'
}
```

#### AF nella collezione (Vetreria)

```java
public class Vetreria implements Iterable<Contenitore> {
    private final List<Contenitore> contenitori;

    // AF(this): una vetreria contenente i contenitori in this.contenitori
    //
    // Nota: la AF mappa la lista interna al concetto astratto di "vetreria".
    // L'utente percepisce un "insieme di contenitori", non un ArrayList.
}
```

#### AF con array (Calendario)

```java
public class Calendario implements Iterable<Sorpresa> {
    private final Sorpresa[] caselle = new Sorpresa[31];

    // AF(this): un calendario dove il giorno i+1 contiene caselle[i]
    //
    // Nota: l'indice dell'array parte da 0, ma i giorni partono da 1.
    // La AF chiarisce questa corrispondenza: giorno 1 = caselle[0],
    // giorno 2 = caselle[1], ..., giorno 31 = caselle[30].
    // Se caselle[i] == null, il giorno i+1 e' vuoto.
}
```

#### AF con vincoli di dominio (Olimpiade)

```java
public class Olimpiade implements Iterable<Evento> {
    private final int anno;
    private final Evento[] eventi = new Evento[19];

    // AF(this): un'olimpiade dell'anno 'anno' dove il giorno i+1 ha l'evento eventi[i]
    //
    // L'anno qualifica l'olimpiade. L'array eventi ha significato analogo
    // al Calendario: giorno 1 = eventi[0], ..., giorno 19 = eventi[18].
}
```

#### AF nella classe astratta con ereditarieta' (Sorpresa)

```java
public abstract class Sorpresa implements Comparable<Sorpresa> {
    private final String nome;

    // AF(this): una sorpresa di nome 'nome' con costo getCosto()
    //
    // Il costo e' calcolato dal metodo astratto getCosto(), la cui
    // implementazione dipende dalla sottoclasse concreta.
    // getCosto() nella AF e' lecito perche' e' un osservatore.
}
```

```java
public class Cioccolatino extends Sorpresa {
    private final int percentualeCacao;

    // AF(this): un cioccolatino di nome getNome() con cacao al percentualeCacao%
}
```

```java
public class Giocattolo extends Sorpresa {
    private final String descrizione;

    // AF(this): un giocattolo di nome getNome() con descrizione 'descrizione'
}
```

### Errori comuni nella AF

| Errore                                        | Esempio sbagliato                              | Versione corretta                                           |
| --------------------------------------------- | ---------------------------------------------- | ----------------------------------------------------------- |
| Descrivere i tipi invece del significato      | `AF: liquido e' un String, quantita e' double` | `AF: contiene 'quantita' unita' di 'liquido'`              |
| Dimenticare i casi speciali                   | `AF: contiene 'liquido'`                       | `AF: contiene 'liquido'. Se null, e' vuoto.`               |
| Ripetere i campi senza dare significato       | `AF: this.raggio e' il raggio`                 | `AF: una sfera di raggio 'raggio'` (descrive il CONCETTO)  |

---

## 6. Invariante di Rappresentazione RI(this)

### Definizione formale

L'**Invariante di Rappresentazione** (RI) e' un predicato booleano che deve essere **vero per OGNI istanza valida della classe in OGNI momento** della sua vita (dopo la costruzione e dopo ogni mutazione).

```
RI: spazio di rappresentazione --> {true, false}
```

L'RI definisce il **dominio della funzione di astrazione**: solo gli stati concreti per cui RI = true hanno un significato astratto valido tramite la AF. Se l'RI e' violato, l'oggetto e' in uno stato **corrotto** e incoerente.

### La preservazione dell'RI

L'RI deve essere preservato in tre modi:

1. **I costruttori devono stabilirlo** (base dell'induzione): al termine del costruttore, l'RI deve essere vero.
2. **I mutatori devono preservarlo** (passo induttivo): se l'RI era vero prima della chiamata, deve essere vero anche dopo.
3. **La rep exposure non deve permetterne la violazione**: se l'esterno ottiene un riferimento ai campi interni, potrebbe modificarli e violare l'RI.

```java
public abstract class Contenitore {
    private String liquido;
    private double quantita;

    // RI(this): quantita >= 0
    //           && quantita <= calcolaVolume()
    //           && (liquido == null) == (quantita == 0)

    // L'RI dice:
    // 1. La quantita' non puo' essere negativa
    // 2. La quantita' non puo' superare il volume del contenitore
    // 3. Se il liquido e' null, la quantita' DEVE essere 0 (e viceversa)
    //    Cioe': un contenitore e' vuoto <=> non ha liquido E ha quantita 0

    // Il COSTRUTTORE stabilisce l'RI:
    protected Contenitore(String liquido, double quantita) {
        if (quantita < 0)
            throw new IllegalArgumentException("Quantita' negativa");
        if (quantita > calcolaVolume())
            throw new CapacityException("Capacita' superata");
        // Dopo questi controlli, l'RI e' soddisfatto:
        //   quantita >= 0 (controllato)
        //   quantita <= calcolaVolume() (controllato)
        this.liquido = (quantita > 0) ? liquido : null;
        this.quantita = quantita;
        // (liquido == null) == (quantita == 0): garantito dall'operatore ternario
    }

    // Il MUTATORE preserva l'RI:
    public void versa(Contenitore sorgente) throws LiquidsException {
        // Prima: RI(this) = true, RI(sorgente) = true
        double spazio = calcolaVolume() - this.quantita;
        double daVersare = Math.min(sorgente.quantita, spazio);
        // daVersare >= 0 (min di due non-negativi)
        // daVersare <= spazio, quindi this.quantita + daVersare <= calcolaVolume()
        if (this.liquido == null) this.liquido = sorgente.liquido;
        this.quantita += daVersare;
        sorgente.quantita -= daVersare;
        if (sorgente.quantita == 0) sorgente.liquido = null;
        // Dopo: RI(this) = true, RI(sorgente) = true
    }
}
```

### Come scrivere un buon RI

L'RI deve:

1. Essere scritto come commento nella classe, subito dopo la AF
2. Usare il formato `RI(this): ...`
3. Essere un predicato booleano: una congiunzione di condizioni che devono essere TUTTE vere
4. Catturare TUTTI i vincoli strutturali sulla rappresentazione

### Esempi concreti dal corso

#### RI nella sottoclasse (Sfera)

```java
public class Sfera extends Contenitore {
    private final double raggio;

    // RI(this): raggio > 0
    //
    // L'RI della sottoclasse si concentra sui campi PROPRI.
    // L'RI della superclasse (quantita >= 0, ecc.) si assume gia'
    // soddisfatto e viene preservato dalla superclasse stessa.
}
```

#### RI nella sottoclasse (Cilindro)

```java
public class Cilindro extends Contenitore {
    private final double altezza;
    private final double raggio;

    // RI(this): altezza > 0 && raggio > 0
    //
    // Entrambe le dimensioni devono essere strettamente positive
    // perche' un cilindro con altezza o raggio zero non ha senso fisico.
}
```

#### RI nella collezione (Vetreria)

```java
public class Vetreria implements Iterable<Contenitore> {
    private final List<Contenitore> contenitori;

    // RI(this): contenitori != null
    //           && per ogni c in contenitori: c != null
    //
    // Due vincoli:
    // 1. La lista stessa non e' null (il costruttore la inizializza)
    // 2. Nessun elemento della lista e' null (il metodo aggiungi lo controlla)
}
```

#### RI con vincoli di unicita' (Calendario)

```java
public class Calendario implements Iterable<Sorpresa> {
    private final Sorpresa[] caselle = new Sorpresa[31];

    // RI(this): caselle != null && caselle.length == 31
    //           && non ci sono due sorprese equals tra loro
    //
    // L'unicita' delle sorprese e' un vincolo di dominio: il calendario
    // non puo' contenere la stessa sorpresa in due giorni diversi.
    // Il metodo inserisci() controlla questo vincolo prima di inserire.
}
```

#### RI con vincoli complessi di dominio (Olimpiade)

```java
public class Olimpiade implements Iterable<Evento> {
    private final int anno;
    private final Evento[] eventi = new Evento[19];

    // RI(this): anno > 0
    //           && eventi != null && eventi.length == 19
    //           && non ci sono due eventi equals tra loro
    //           && se eventi[0] e' Cerimonia, e' di apertura
    //           && se eventi[18] e' Cerimonia, e' di chiusura
    //
    // L'RI cattura TUTTI i vincoli di dominio:
    // - Anno valido
    // - Struttura dell'array corretta
    // - Unicita' degli eventi (un evento non puo' apparire in due giorni)
    // - Vincolo posizionale: la cerimonia di apertura SOLO al giorno 1 (indice 0)
    //   e la cerimonia di chiusura SOLO al giorno 19 (indice 18)
}
```

### Il metodo repOk() -- Verifica dell'RI a runtime

In alcuni corsi viene richiesto un metodo `repOk()` che verifica l'RI programmaticamente. Non e' richiesto all'esame di Prog2, ma il concetto e' utile per il debug:

```java
/**
 * Verifica che l'invariante di rappresentazione sia soddisfatto.
 * Utile per il debug: si puo' chiamare alla fine di ogni costruttore
 * e di ogni mutatore con assert repOk().
 */
private boolean repOk() {
    if (contenitori == null) return false;
    for (Contenitore c : contenitori) {
        if (c == null) return false;
    }
    return true;
}
```

---

## 7. Rep Exposure

### Definizione

La **rep exposure** (esposizione della rappresentazione) si verifica quando il codice esterno ottiene un riferimento diretto alla rappresentazione interna (i campi privati) di un oggetto, potendo cosi' modificarla e **violare l'RI** senza passare attraverso i metodi della classe.

La rep exposure e' uno dei difetti piu' gravi in un ADT perche' rompe completamente l'incapsulamento: la classe perde il controllo sulla propria rappresentazione.

### Le cause della rep exposure

#### Causa 1: Getter che restituisce un riferimento alla struttura interna

```java
public class Vetreria {
    private final List<Contenitore> contenitori = new ArrayList<>();

    // PERICOLOSO: restituisce il riferimento diretto alla lista interna!
    public List<Contenitore> getContenitori() {
        return contenitori;   // l'esterno riceve la lista VERA
    }
}

// L'esterno puo' ora violare l'RI:
Vetreria v = new Vetreria();
v.aggiungi(new Sfera(3));
List<Contenitore> lista = v.getContenitori();

lista.add(null);    // VIOLA l'RI: "per ogni c in contenitori: c != null"
lista.clear();      // Svuota la vetreria senza passare per i metodi della classe
```

**Soluzione: copia difensiva in output**

```java
// SICURO: restituisce una COPIA della lista interna
public List<Contenitore> getContenitori() {
    return new ArrayList<>(contenitori);   // l'esterno riceve una copia
    // Modificare la copia NON modifica l'originale.
}

// Alternativa: lista non modificabile (Java Collections Framework)
public List<Contenitore> getContenitori() {
    return Collections.unmodifiableList(contenitori);
    // L'esterno vede la lista ma non puo' modificarla:
    // ogni tentativo di add/remove/set lancia UnsupportedOperationException.
}

// Alternativa Java 10+: List.copyOf()
public List<Contenitore> getContenitori() {
    return List.copyOf(contenitori);   // copia immutabile
}
```

#### Causa 2: Costruttore che salva il riferimento del parametro

```java
public class Vetreria {
    private final List<Contenitore> contenitori;

    // PERICOLOSO: salva il riferimento diretto al parametro!
    public Vetreria(List<Contenitore> lista) {
        this.contenitori = lista;   // l'esterno ha ancora il riferimento
    }
}

// L'esterno puo' ora violare l'RI:
List<Contenitore> miaLista = new ArrayList<>();
miaLista.add(new Sfera(3));
Vetreria v = new Vetreria(miaLista);

miaLista.add(null);   // VIOLA l'RI della Vetreria! Abbiamo aggiunto null
                       // senza passare per aggiungi(), che fa il controllo.
```

**Soluzione: copia difensiva in input**

```java
// SICURO: crea una COPIA del parametro
public Vetreria(List<Contenitore> lista) {
    this.contenitori = new ArrayList<>(lista);   // copia indipendente
    // Dopo questa riga, modifiche a 'lista' NON influenzano this.contenitori.
}
```

#### Causa 3: Metodo che restituisce un oggetto mutabile interno

```java
public class Calendario {
    private final Sorpresa[] caselle = new Sorpresa[31];

    // PERICOLOSO: restituisce la sorpresa interna senza copia.
    // Se Sorpresa fosse MUTABILE, l'esterno potrebbe modificarla.
    public Sorpresa getSorpresa(int giorno) {
        return caselle[giorno - 1];   // riferimento diretto all'oggetto interno
    }
}
```

Nota: nel corso, `Sorpresa` e' immutabile (campi `final`, nessun mutatore), quindi restituire il riferimento diretto e' sicuro in questo caso specifico. Ma se la classe interna fosse mutabile, sarebbe necessaria una copia (ad esempio con `clone()`).

#### Causa 4: Campi non privati

```java
// PERICOLOSO: il campo e' visibile dall'esterno!
public class Vetreria {
    List<Contenitore> contenitori = new ArrayList<>();   // package-private!
    // Qualsiasi classe nello stesso package puo' accedere a 'contenitori'
}

// PERICOLOSO: il campo e' pubblico!
public class Vetreria {
    public List<Contenitore> contenitori = new ArrayList<>();
    // TUTTO il codice puo' accedere a 'contenitori'
}
```

**Soluzione: dichiarare TUTTI i campi `private`**

```java
// SICURO: il campo e' accessibile solo dalla classe stessa
public class Vetreria {
    private final List<Contenitore> contenitori = new ArrayList<>();
    // Il modificatore 'final' impedisce la riassegnazione del riferimento,
    // ma NON impedisce la modifica del contenuto della lista.
    // Per questo servono ancora le copie difensive nei getter.
}
```

### Riepilogo delle difese contro la rep exposure

| Tecnica                            | Quando usarla                      | Esempio                                        |
| ---------------------------------- | ---------------------------------- | ----------------------------------------------- |
| Campi `private`                    | **SEMPRE**                         | `private final List<T> lista;`                  |
| Campi `final`                      | Sempre che possibile               | `private final double raggio;`                  |
| Copia difensiva in input           | Costruttori con parametri mutabili | `this.lista = new ArrayList<>(parametro);`      |
| Copia difensiva in output          | Getter di strutture mutabili       | `return new ArrayList<>(lista);`                |
| `Collections.unmodifiableList()`   | Vista read-only senza copia        | `return Collections.unmodifiableList(lista);`   |
| `List.copyOf()` (Java 10+)        | Copia immutabile concisa           | `return List.copyOf(lista);`                    |
| Restituire tipi immutabili         | Getter di campi immutabili         | `return nome;` (String e' immutabile)           |
| `clone()` su oggetti mutabili      | Getter/setter di oggetti mutabili  | `return elemento.clone();`                      |

### Nota sull'esame

Nell'esame di Prog2, i campi sono sempre `private` (e spesso `final` nelle sottoclassi). L'iterator restituisce una copia ordinata della collezione interna, il che impedisce la rep exposure sulla struttura principale. Tuttavia, se la collezione contiene oggetti **mutabili** (come `Contenitore`), l'iterator espone i riferimenti a quegli oggetti. Nel contesto dell'esame questo e' generalmente accettato, ma e' bene esserne consapevoli.

---

## 8. OVERVIEW di classe

### Definizione

L'**OVERVIEW** e' un commento posto all'inizio della classe (subito dopo la dichiarazione) che descrive in modo sintetico:

1. **Cosa rappresenta** la classe (il concetto astratto)
2. Se il tipo e' **mutabile o immutabile**
3. Eventuali **vincoli chiave** del tipo

L'OVERVIEW e' la prima cosa che un utente legge per capire cosa fa la classe. E' il livello piu' alto di documentazione, che precede AF e RI.

### Formato standard

```java
public class NomeClasse {
    // OVERVIEW: <descrizione breve del concetto che la classe rappresenta>.
    //           <mutabile o immutabile>.
    //           <vincoli fondamentali, se presenti>.
}
```

### Esempi completi dal corso

```java
public abstract class Contenitore implements Comparable<Contenitore>, Cloneable {
    // OVERVIEW: Un Contenitore e' un recipiente MUTABILE che puo' contenere
    //           un liquido fino alla sua capienza massima (volume).
    //           Due contenitori sono confrontabili per capienza.
}
```

```java
public class Sfera extends Contenitore {
    // OVERVIEW: Contenitore sferico definito dal raggio.
    //           Il volume e' calcolato come (4/3) * pi * raggio^3.
}
```

```java
public class Vetreria implements Iterable<Contenitore> {
    // OVERVIEW: Vetreria e' un insieme MUTABILE di Contenitori.
    //           Permette di aggiungere, estrarre e iterare
    //           i contenitori in ordine crescente di capienza.
}
```

```java
public abstract class Sorpresa implements Comparable<Sorpresa> {
    // OVERVIEW: Una Sorpresa e' definita dal suo nome.
    //           Due sorprese sono uguali se hanno lo stesso nome.
    //           Le sorprese sono confrontabili per costo.
}
```

```java
public class Calendario implements Iterable<Sorpresa> {
    // OVERVIEW: Un calendario di 31 giorni con caselle per sorprese.
    //           Ogni casella puo' contenere al piu' una sorpresa.
    //           Non possono esserci due sorprese equals tra loro.
}
```

```java
public class Olimpiade implements Iterable<Evento> {
    // OVERVIEW: Un'olimpiade invernale con 19 giorni di eventi.
    //           La cerimonia di apertura puo' essere solo al giorno 1,
    //           la cerimonia di chiusura solo al giorno 19.
}
```

### Struttura completa di documentazione di una classe

L'ordine canonico della documentazione all'interno di una classe e':

```java
public class EsempioCompleto implements Iterable<Elemento> {
    // OVERVIEW: <Cosa rappresenta la classe>. <Mutabile/Immutabile>.

    // === Campi privati (rappresentazione interna) ===
    private final List<Elemento> elementi;

    // AF(this): <mappatura dai campi al valore astratto>
    // RI(this): <predicato booleano che deve essere sempre vero>

    // === Costruttore(i) ===
    // === Metodi pubblici con specifica REQUIRES/MODIFIES/EFFECTS ===
    // === equals, hashCode, toString, compareTo, clone ===
    // === iterator() ===
    // === main() di test (se richiesto) ===
}
```

### Importanza per l'esame

L'OVERVIEW vale **0.5 punti per classe** nella scala di valutazione dell'esame. Insieme ad AF e RI, rappresenta punteggio garantito:

| Elemento   | Punti per classe | Classi tipiche | Punti totali tipici |
| ---------- | ---------------- | -------------- | ------------------- |
| OVERVIEW   | 0.5              | 5-6 classi     | 2.5 - 3.0          |
| AF(this)   | 0.5              | 4-5 classi     | 2.0 - 2.5          |
| RI(this)   | 0.5              | 2-3 classi     | 1.0 - 1.5          |
| **Totale** |                  |                | **5.5 - 7.0**      |

Questi sono **punti facili**: richiedono poche righe di codice e non dipendono dalla correttezza dell'implementazione. **Dimentiarli e' un errore inaccettabile.**

### Esempio completo: classe con OVERVIEW + AF + RI + spec

```java
import java.util.*;

public class Vetreria implements Iterable<Contenitore> {
    // OVERVIEW: Vetreria e' un insieme MUTABILE di Contenitori.
    //           Permette di aggiungere, estrarre per tipo di liquido
    //           e iterare i contenitori in ordine crescente di capienza.

    private final List<Contenitore> contenitori;

    // AF(this): una vetreria contenente i contenitori in this.contenitori
    // RI(this): contenitori != null && per ogni c in contenitori: c != null

    /**
     * EFFECTS: crea una vetreria vuota
     */
    public Vetreria() {
        contenitori = new ArrayList<>();
        // RI stabilito: contenitori != null, nessun elemento -> nessun null
    }

    /**
     * MODIFIES: this
     * EFFECTS: aggiunge c a this. Se c e' null lancia NullPointerException.
     */
    public void aggiungi(Contenitore c) {
        if (c == null) throw new NullPointerException();
        contenitori.add(c);
        // RI preservato: c != null e' garantito dal controllo
    }

    /**
     * MODIFIES: this
     * EFFECTS: estrae tutti i contenitori con il liquido specificato,
     *          li rimuove da this e restituisce una nuova Vetreria.
     */
    public Vetreria estrai(String liquido) {
        Vetreria estratta = new Vetreria();
        Iterator<Contenitore> it = contenitori.iterator();
        while (it.hasNext()) {
            Contenitore c = it.next();
            if (liquido.equals(c.getLiquido())) {
                estratta.aggiungi(c);
                it.remove();
            }
        }
        return estratta;
        // RI preservato sia per this sia per estratta
    }

    /**
     * EFFECTS: restituisce un iteratore sui contenitori
     *          in ordine crescente di capienza (ordine naturale).
     */
    @Override
    public Iterator<Contenitore> iterator() {
        List<Contenitore> copia = new ArrayList<>(contenitori);
        Collections.sort(copia);     // ordina la COPIA, non l'originale
        return copia.iterator();     // nessuna rep exposure: copia indipendente
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Vetreria con:\n");
        for (Contenitore c : this) {
            sb.append("\t").append(c).append("\n");
        }
        return sb.toString();
    }
}
```

---

### Riepilogo finale della Parte 5

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                     ASTRAZIONE E SPECIFICHE – CHECKLIST                    │
│                                                                           │
│  Per OGNI CLASSE scrivi:                                                  │
│    [x] OVERVIEW (cos'e', mutabile/immutabile)          → 0.5 punti       │
│    [x] AF(this) (significato dei campi)                → 0.5 punti       │
│    [x] RI(this) (vincoli booleani sui campi)           → 0.5 punti       │
│                                                                           │
│  Per OGNI METODO PUBBLICO scrivi:                                        │
│    [x] REQUIRES (precondizioni, o "-" se totale)                         │
│    [x] MODIFIES (this, parametri, o "-" se osservatore)                  │
│    [x] EFFECTS  (postcondizioni + eccezioni)                             │
│                                                                           │
│  Controlla:                                                               │
│    [x] Nessun campo e' public o package-private                          │
│    [x] I getter restituiscono copie, non riferimenti diretti             │
│    [x] I costruttori copiano i parametri mutabili                        │
│    [x] L'RI e' stabilito nei costruttori e preservato nei mutatori       │
│    [x] Tutti i metodi sono TOTALI (controllano input e lanciano          │
│        eccezioni invece di avere REQUIRES restrittivi)                   │
└─────────────────────────────────────────────────────────────────────────────┘
```


---

# PARTE 6 – Concetti Avanzati

---

## 20. Clonazione e copia difensiva

### Il metodo `clone()` e l'interfaccia `Cloneable`

In Java, `clone()` e' un metodo protetto definito in `Object`. Per poterlo usare, la classe deve:

1. **Implementare** l'interfaccia marker `Cloneable` (senza metodi)
2. **Fare override** di `clone()` rendendolo `public`
3. **Chiamare** `super.clone()` nel corpo del metodo

Se una classe invoca `super.clone()` senza implementare `Cloneable`, viene lanciata `CloneNotSupportedException`.

```java
public abstract class Contenitore implements Comparable<Contenitore>, Cloneable {

    private String liquido;
    private double quantita;

    // clone() restituisce una COPIA dell'oggetto
    @Override
    public Contenitore clone() {
        try {
            // super.clone() crea una copia campo per campo (shallow copy)
            return (Contenitore) super.clone();
        } catch (CloneNotSupportedException e) {
            // non puo' accadere perche' implementiamo Cloneable
            throw new AssertionError();
        }
    }
}
```

### Shallow copy vs Deep copy

La distinzione tra shallow copy e deep copy e' fondamentale per la correttezza delle copie:

```java
// SHALLOW COPY: copia solo i riferimenti
// L'oggetto originale e la copia CONDIVIDONO gli oggetti interni
public class Inventario implements Cloneable {
    private String nome;                    // String e' immutabile → ok
    private ArrayList<String> oggetti;      // ArrayList e' mutabile → problema!

    @Override
    public Inventario clone() {
        try {
            Inventario copia = (Inventario) super.clone();
            // PROBLEMA: copia.oggetti punta allo STESSO ArrayList!
            // Modificare copia.oggetti modifica anche l'originale
            return copia;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
```

```java
// DEEP COPY: copia anche gli oggetti referenziati
// L'originale e la copia sono completamente indipendenti
public class Inventario implements Cloneable {
    private String nome;
    private ArrayList<String> oggetti;

    @Override
    public Inventario clone() {
        try {
            Inventario copia = (Inventario) super.clone();
            // DEEP COPY: creo una NUOVA lista con gli stessi elementi
            copia.oggetti = new ArrayList<>(this.oggetti);
            return copia;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
```

**Regola pratica:**

| Tipo del campo       | Shallow copy sufficiente? | Serve deep copy?           |
| -------------------- | ------------------------- | -------------------------- |
| Tipo primitivo       | Si                        | No                         |
| `String`             | Si (immutabile)           | No                         |
| `final` immutabile   | Si                        | No                         |
| `ArrayList`, array   | **No**                    | **Si** (copiare la lista)  |
| Oggetto mutabile     | **No**                    | **Si** (clonare l'oggetto) |

### Quando usare `clone()` nell'esame

Nell'esame, `clone()` viene richiesto sulla classe astratta base. Poiche' i campi della classe base sono generalmente primitivi o `String` (immutabili), la shallow copy con `super.clone()` e' sufficiente.

```java
// Nella simulazione Vetreria:
// Contenitore ha campi: String liquido, double quantita
// Entrambi sono immutabili/primitivi → super.clone() basta

// Le sottoclassi (Sfera, Cilindro, Cuboide) hanno campi final primitivi
// → super.clone() basta anche per loro
```

### Copia difensiva

La **copia difensiva** e' un'alternativa a `clone()` per proteggere la rappresentazione interna di un ADT. Si usa nei costruttori e nei getter per impedire la **rep exposure**.

```java
public class Registro {
    private final List<String> studenti;

    // COSTRUTTORE: copia difensiva IN
    public Registro(List<String> studenti) {
        // NON fare: this.studenti = studenti;
        // Il chiamante potrebbe modificare la lista originale!
        this.studenti = new ArrayList<>(studenti);  // copia difensiva
    }

    // GETTER: copia difensiva OUT
    public List<String> getStudenti() {
        // NON fare: return studenti;
        // Il chiamante potrebbe modificare la lista interna!
        return new ArrayList<>(studenti);  // copia difensiva
    }

    // ALTERNATIVA: restituire vista non modificabile
    public List<String> getStudentiReadOnly() {
        return Collections.unmodifiableList(studenti);
        // il chiamante NON puo' fare add/remove
        // lancia UnsupportedOperationException se ci prova
    }
}
```

---

## 21. Classi nested

Java permette di definire classi all'interno di altre classi. Esistono quattro tipi di classi nested, ciascuno con caratteristiche e casi d'uso specifici.

### Classificazione

| Tipo                   | Dichiarazione              | Accesso alla classe esterna | Uso tipico                     |
| ---------------------- | -------------------------- | --------------------------- | ------------------------------ |
| Static nested class    | `static class Nested {}`   | Solo membri statici         | Raggruppamento logico          |
| Inner class (non-static) | `class Inner {}`         | Tutti i membri              | Accoppiamento forte con outer  |
| Local class            | Dentro un metodo           | Tutti + variabili locali final | Iterator personalizzati     |
| Anonymous class        | `new Interfaccia() {...}`  | Tutti + variabili locali final | Implementazioni al volo     |

### Static nested class

```java
public class Contenitore {
    // classe nested statica: non richiede un'istanza della classe esterna
    public static class Builder {
        private String liquido;
        private double quantita;

        public Builder liquido(String l) { this.liquido = l; return this; }
        public Builder quantita(double q) { this.quantita = q; return this; }
    }
}

// Uso: non serve un'istanza di Contenitore
Contenitore.Builder b = new Contenitore.Builder();
```

### Inner class (non-static)

```java
public class Vetreria {
    private List<Contenitore> contenitori = new ArrayList<>();

    // inner class: ha accesso ai campi della Vetreria
    private class VetreriaIterator implements Iterator<Contenitore> {
        private int indice = 0;

        @Override
        public boolean hasNext() {
            // accede direttamente a contenitori della classe esterna
            return indice < contenitori.size();
        }

        @Override
        public Contenitore next() {
            if (!hasNext()) throw new NoSuchElementException();
            return contenitori.get(indice++);
        }
    }

    public Iterator<Contenitore> iterator() {
        return new VetreriaIterator();  // richiede un'istanza di Vetreria
    }
}
```

### Local class (dentro un metodo)

```java
public Iterator<Contenitore> iterator() {
    // classe locale: visibile solo dentro questo metodo
    class IteratorOrdinato implements Iterator<Contenitore> {
        private final List<Contenitore> copia;
        private int i = 0;

        IteratorOrdinato() {
            this.copia = new ArrayList<>(contenitori);
            Collections.sort(this.copia);
        }

        @Override
        public boolean hasNext() { return i < copia.size(); }

        @Override
        public Contenitore next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copia.get(i++);
        }
    }
    return new IteratorOrdinato();
}
```

### Anonymous class (la piu' usata negli esami)

```java
// Classe anonima: implementa un'interfaccia senza darle un nome
public Iterator<Contenitore> iterator() {
    final List<Contenitore> copia = new ArrayList<>(contenitori);
    Collections.sort(copia);

    return new Iterator<Contenitore>() {
        private int i = 0;

        @Override
        public boolean hasNext() { return i < copia.size(); }

        @Override
        public Contenitore next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copia.get(i++);
        }
    };  // attenzione al punto e virgola!
}
```

### Variabili effectively final

Le classi locali e anonime possono accedere solo a variabili locali che sono **effectively final** (non modificate dopo l'inizializzazione):

```java
public void esempio() {
    int contatore = 0;  // effectively final solo se NON viene modificato

    // ERRORE: contatore viene modificato sotto → non e' effectively final
    // Runnable r = () -> System.out.println(contatore);
    // contatore++;

    // CORRETTO: la variabile non viene mai modificata
    final int limite = 10;
    Runnable r = () -> System.out.println(limite);  // OK
}
```

---

## 22. Programmazione funzionale

### Interfacce funzionali

Un'**interfaccia funzionale** e' un'interfaccia con un solo metodo astratto. Puo' essere annotata con `@FunctionalInterface`:

```java
@FunctionalInterface
public interface Comparator<T> {
    int compare(T o1, T o2);
    // puo' avere metodi default e static, ma UN SOLO metodo astratto
}

// Altre interfacce funzionali del JDK:
// Predicate<T>      → boolean test(T t)        → filtro
// Function<T,R>     → R apply(T t)             → trasformazione
// Consumer<T>       → void accept(T t)         → azione
// Supplier<T>       → T get()                  → produttore
// UnaryOperator<T>  → T apply(T t)             → trasformazione stesso tipo
// BinaryOperator<T> → T apply(T t1, T t2)      → riduzione
```

### Lambda expressions

Le **lambda** sono funzioni anonime che implementano un'interfaccia funzionale in modo conciso:

```java
// Sintassi: (parametri) -> corpo

// Forma completa
Comparator<String> comp1 = (String a, String b) -> {
    return a.compareTo(b);
};

// Forma abbreviata (tipi inferiti, corpo singola espressione)
Comparator<String> comp2 = (a, b) -> a.compareTo(b);

// Con un solo parametro: parentesi opzionali
Predicate<String> filtro = s -> s.length() > 3;

// Senza parametri
Supplier<String> fornitore = () -> "ciao";
```

### Esempi pratici dal corso

```java
List<Studente> studenti = new ArrayList<>();

// Ordinare con lambda (invece di implementare Comparator come classe)
studenti.sort((a, b) -> Double.compare(a.getMedia(), b.getMedia()));

// Equivalente con method reference
studenti.sort(Comparator.comparingDouble(Studente::getMedia));

// Ordine inverso
studenti.sort(Comparator.comparingDouble(Studente::getMedia).reversed());

// Ordinamento multiplo: prima per media decrescente, poi per nome
studenti.sort(
    Comparator.comparingDouble(Studente::getMedia).reversed()
              .thenComparing(Studente::getNome)
);
```

### Method references

Scorciatoia sintattica per lambda che chiamano un singolo metodo:

```java
// Tipo 1: riferimento a metodo statico
// Lambda: (s) -> Integer.parseInt(s)
Function<String, Integer> parser = Integer::parseInt;

// Tipo 2: riferimento a metodo d'istanza su parametro
// Lambda: (s) -> s.toUpperCase()
Function<String, String> upper = String::toUpperCase;

// Tipo 3: riferimento a metodo d'istanza su oggetto specifico
// Lambda: (s) -> System.out.println(s)
Consumer<String> printer = System.out::println;

// Tipo 4: riferimento a costruttore
// Lambda: () -> new ArrayList<>()
Supplier<List<String>> factory = ArrayList::new;
```

### Stream API (Java 8+)

Gli **Stream** permettono di elaborare collezioni in modo dichiarativo, concatenando operazioni:

```java
List<String> nomi = List.of("Mario", "Anna", "Luigi", "Alessandra", "Bo");

// Pipeline: sorgente → operazioni intermedie → operazione terminale
List<String> risultato = nomi.stream()           // crea lo stream
    .filter(n -> n.length() > 3)                  // intermedia: filtra
    .map(String::toUpperCase)                     // intermedia: trasforma
    .sorted()                                     // intermedia: ordina
    .collect(Collectors.toList());                // terminale: raccoglie
// risultato: ["ALESSANDRA", "ANNA", "LUIGI", "MARIO"]
```

### Operazioni intermedie (restituiscono Stream, lazy)

```java
stream.filter(x -> condizione)   // filtra elementi
stream.map(x -> trasformazione)  // trasforma ogni elemento
stream.sorted()                  // ordina (ordine naturale)
stream.sorted(comparator)        // ordina con comparator
stream.distinct()                // rimuove duplicati
stream.limit(n)                  // prende solo i primi n
stream.skip(n)                   // salta i primi n
stream.peek(x -> azione)         // esegue azione senza modificare (debug)
```

### Operazioni terminali (producono risultato, eager)

```java
stream.forEach(x -> azione)            // esegue azione su ogni elemento
stream.collect(Collectors.toList())    // raccoglie in lista
stream.count()                          // conta gli elementi
stream.reduce(identity, accumulator)   // riduce a un singolo valore
stream.min(comparator)                  // elemento minimo (Optional)
stream.max(comparator)                  // elemento massimo (Optional)
stream.anyMatch(predicate)              // almeno uno soddisfa?
stream.allMatch(predicate)              // tutti soddisfano?
stream.noneMatch(predicate)             // nessuno soddisfa?
stream.findFirst()                      // primo elemento (Optional)
stream.toArray()                        // converte in array
```

### Esempio completo: calcoli su una collezione

```java
List<Contenitore> contenitori = new ArrayList<>();
// ... aggiunta contenitori ...

// Volume totale di tutti i contenitori con acqua
double volumeTotale = contenitori.stream()
    .filter(c -> "acqua".equals(c.getLiquido()))   // solo quelli con acqua
    .mapToDouble(Contenitore::calcolaVolume)        // estrai volume
    .sum();                                         // somma

// Contenitore con volume massimo
Optional<Contenitore> maxVol = contenitori.stream()
    .max(Comparator.comparingDouble(Contenitore::calcolaVolume));

maxVol.ifPresent(c -> System.out.println("Max: " + c));

// Raggruppa contenitori per liquido
Map<String, List<Contenitore>> perLiquido = contenitori.stream()
    .filter(c -> c.getLiquido() != null)
    .collect(Collectors.groupingBy(Contenitore::getLiquido));
```

### Record (Java 16+)

I **record** sono classi immutabili con `toString()`, `equals()`, `hashCode()` e getter generati automaticamente:

```java
// Dichiarazione: una sola riga!
public record Punto(int x, int y) {}

// Equivale a scrivere:
// public final class Punto {
//     private final int x;
//     private final int y;
//     public Punto(int x, int y) { this.x = x; this.y = y; }
//     public int x() { return x; }          // getter SENZA "get"
//     public int y() { return y; }
//     public boolean equals(Object o) { ... }
//     public int hashCode() { ... }
//     public String toString() { ... }      // "Punto[x=3, y=5]"
// }

Punto p = new Punto(3, 5);
p.x();    // 3 (nota: NON getX())
p.y();    // 5
```

### Sealed classes (Java 17+)

Le **sealed classes** limitano quali classi possono estenderle:

```java
// solo Sfera, Cilindro e Cuboide possono estendere Contenitore
public sealed abstract class Contenitore
    permits Sfera, Cilindro, Cuboide {
    // ...
}

// le sottoclassi devono essere final, sealed o non-sealed
public final class Sfera extends Contenitore { }       // nessuno puo' estendere Sfera
public non-sealed class Cilindro extends Contenitore { } // chiunque puo' estendere Cilindro
```

---

## 23. Design pattern

I **design pattern** sono soluzioni ricorrenti a problemi comuni nella progettazione del software. Nel corso di Prog2 ne compaiono principalmente tre.

### Template Method

Il **Template Method** definisce lo scheletro di un algoritmo nella classe base, delegando alcuni passi alle sottoclassi. E' il pattern piu' frequente nell'esame.

```java
public abstract class Contenitore {
    // il Template Method usa il metodo astratto calcolaVolume()
    // che sara' implementato dalle sottoclassi
    @Override
    public String toString() {
        // questo metodo e' CONCRETO nella classe astratta
        // ma chiama calcolaVolume() che e' ASTRATTO
        return "Contenitore di volume " + calcolaVolume();
    }

    // passo delegato alle sottoclassi
    public abstract double calcolaVolume();
}

public class Sfera extends Contenitore {
    private final double raggio;

    @Override
    public double calcolaVolume() {
        // ogni sottoclasse fornisce la propria implementazione
        return Math.PI * Math.pow(raggio, 3) * 4.0 / 3.0;
    }
}
```

**Dove si vede nell'esame:**
- `toString()` nella classe base chiama `calcolaVolume()` / `getCosto()` / `getDurata()`
- `compareTo()` nella classe base chiama il metodo astratto
- Qualsiasi metodo concreto della classe astratta che invoca un metodo astratto

### Strategy

Lo **Strategy** incapsula un algoritmo in un oggetto separato, permettendo di cambiarlo a runtime. Il `Comparator` e' l'esempio classico.

```java
// L'interfaccia Comparator e' lo Strategy
List<Studente> studenti = new ArrayList<>();

// Strategy 1: ordina per nome
Comparator<Studente> perNome = (a, b) -> a.getNome().compareTo(b.getNome());
studenti.sort(perNome);

// Strategy 2: ordina per media
Comparator<Studente> perMedia = Comparator.comparingDouble(Studente::getMedia);
studenti.sort(perMedia);

// Strategy 3: ordina per media decrescente, poi nome crescente
Comparator<Studente> composto = perMedia.reversed().thenComparing(perNome);
studenti.sort(composto);
```

### Iterator

Il pattern **Iterator** fornisce un modo per accedere sequenzialmente agli elementi di una collezione senza esporre la struttura interna. E' il pattern piu' richiesto all'esame.

```java
// L'interfaccia Iterator definisce il contratto
public interface Iterator<T> {
    boolean hasNext();  // ci sono altri elementi?
    T next();           // restituisce il prossimo e avanza
    void remove();      // rimuove l'ultimo restituito (opzionale)
}

// La classe che implementa Iterable produce Iterator
public class Vetreria implements Iterable<Contenitore> {
    @Override
    public Iterator<Contenitore> iterator() {
        // il pattern: copia → ordina → restituisci iterator
        List<Contenitore> copia = new ArrayList<>(contenitori);
        Collections.sort(copia);
        return copia.iterator();
    }
}
```

### Factory Method (cenni)

Il **Factory Method** delega la creazione degli oggetti a un metodo, invece di usare `new` direttamente:

```java
public class ContenitoreFactory {
    // il metodo factory crea l'oggetto corretto in base al tipo
    public static Contenitore crea(String tipo, String[] parametri) {
        switch (tipo) {
            case "Sfera":
                double r = Double.parseDouble(parametri[0]);
                return new Sfera(r);
            case "Cilindro":
                double h = Double.parseDouble(parametri[0]);
                double rc = Double.parseDouble(parametri[1]);
                return new Cilindro(h, rc);
            case "Cuboide":
                double a = Double.parseDouble(parametri[0]);
                double b = Double.parseDouble(parametri[1]);
                double c = Double.parseDouble(parametri[2]);
                return new Cuboide(a, b, c);
            default:
                throw new IllegalArgumentException("Tipo sconosciuto: " + tipo);
        }
    }
}

// Nel main: invece di un lungo switch con new
Contenitore c = ContenitoreFactory.crea(tipo, parametri);
```

---

## 24. Composizione vs ereditarieta'

### Due modi di riusare il codice

| Aspetto        | Ereditarieta' (`extends`)        | Composizione (`has-a`)            |
| -------------- | -------------------------------- | --------------------------------- |
| Relazione      | IS-A ("e' un")                   | HAS-A ("ha un")                   |
| Accoppiamento  | Forte (figlio dipende dal padre) | Debole (oggetto delegato)         |
| Polimorfismo   | Si                               | Si (tramite interfacce)           |
| Flessibilita'  | Limitata (un solo extends)       | Alta (puo' comporre piu' oggetti) |
| Rischio        | Fragile base class problem       | Nessuno                           |

### Quando usare l'ereditarieta'

L'ereditarieta' e' appropriata quando esiste una **vera relazione IS-A** e il principio di Liskov e' rispettato:

```java
// CORRETTO: un Cane E' UN Animale → IS-A
public class Animale { }
public class Cane extends Animale { }

// CORRETTO: una Sfera E' UN Contenitore → IS-A
public abstract class Contenitore { }
public class Sfera extends Contenitore { }
```

### Quando preferire la composizione

La composizione e' preferibile quando la relazione e' HAS-A o quando si vuole maggiore flessibilita':

```java
// SBAGLIATO: Stack NON E' UN ArrayList
// (viola Liskov: Stack non dovrebbe esporre add(index, element))
public class Stack<T> extends ArrayList<T> {
    // eredita TUTTI i metodi di ArrayList, anche quelli non voluti
}

// CORRETTO: Stack USA un ArrayList internamente
public class Stack<T> {
    private final ArrayList<T> elementi = new ArrayList<>();  // composizione

    public void push(T elemento) {
        elementi.add(elemento);        // delega
    }

    public T pop() {
        if (elementi.isEmpty()) throw new EmptyStackException();
        return elementi.remove(elementi.size() - 1);  // delega
    }

    public T peek() {
        if (elementi.isEmpty()) throw new EmptyStackException();
        return elementi.get(elementi.size() - 1);     // delega
    }

    public boolean isEmpty() {
        return elementi.isEmpty();     // delega
    }
}
```

### Delegation (delega)

La **delega** e' il meccanismo con cui un oggetto passa le richieste a un altro oggetto contenuto:

```java
// La Vetreria USA una List<Contenitore> per delega
public class Vetreria implements Iterable<Contenitore> {
    // composizione: Vetreria HAS-A lista di contenitori
    private final List<Contenitore> contenitori = new ArrayList<>();

    // delega: i metodi di Vetreria chiamano metodi della lista
    public void aggiungi(Contenitore c) {
        contenitori.add(c);            // delega a ArrayList.add()
    }

    public int dimensione() {
        return contenitori.size();     // delega a ArrayList.size()
    }

    @Override
    public Iterator<Contenitore> iterator() {
        List<Contenitore> copia = new ArrayList<>(contenitori);
        Collections.sort(copia);
        return copia.iterator();       // delega a ArrayList.iterator()
    }
}
```

### Il principio di Liskov (LSP) e le sue implicazioni

Il **Liskov Substitution Principle** afferma: se `S` e' sottotipo di `T`, allora oggetti di tipo `T` possono essere sostituiti con oggetti di tipo `S` senza alterare la correttezza del programma.

**Implicazioni per le sottoclassi:**

1. **Precondizioni**: la sottoclasse non puo' avere precondizioni piu' restrittive
2. **Postcondizioni**: la sottoclasse non puo' avere postcondizioni piu' deboli
3. **Invarianti**: la sottoclasse deve preservare gli invarianti del padre
4. **Eccezioni**: la sottoclasse non puo' lanciare nuove checked exceptions

```java
// VIOLA LISKOV: la sottoclasse aggiunge una restrizione
public class Rettangolo {
    protected int larghezza, altezza;

    public void setLarghezza(int l) { this.larghezza = l; }
    public void setAltezza(int a) { this.altezza = a; }
    public int area() { return larghezza * altezza; }
}

public class Quadrato extends Rettangolo {
    // PROBLEMA: sovrascrive setLarghezza imponendo larghezza == altezza
    // il codice scritto per Rettangolo si rompe con un Quadrato!
    @Override
    public void setLarghezza(int l) {
        this.larghezza = l;
        this.altezza = l;  // vincolo aggiuntivo → viola Liskov
    }
}

// Il chiamante si aspetta che Rettangolo permetta lati diversi:
Rettangolo r = new Quadrato();
r.setLarghezza(5);
r.setAltezza(10);
// il chiamante si aspetta area() == 50, ma ottiene 100!
```

### Regola pratica per l'esame

Nell'esame di Prog2, l'ereditarieta' e' usata per la gerarchia richiesta dal testo:

- La **classe astratta base** usa ereditarieta' (Contenitore → Sfera/Cilindro/Cuboide)
- La **classe collezione** usa composizione (Vetreria HAS-A List\<Contenitore\>)

Questa combinazione e' il pattern standard di ogni esame.


---
# PARTE 7 – Preparazione Esame
---

## 25. Filtro teoria – 30+ domande e risposte

### A. OOP e Polimorfismo

**1. Cos'e' il polimorfismo?**
Il polimorfismo permette a variabili e metodi di assumere forme diverse a seconda del tipo dinamico dell'oggetto. Il dispatch dinamico seleziona a runtime il metodo del tipo dinamico.

**2. Differenza tra tipo statico e tipo dinamico?**
Il tipo statico e' quello dichiarato (compile-time), il tipo dinamico e' quello effettivo dell'oggetto (runtime, assegnato con `new`). Il compilatore controlla il tipo statico, il dispatch esegue il metodo del tipo dinamico.

**3. Cos'e' il dispatch dinamico?**
A runtime, Java chiama il metodo della classe effettiva dell'oggetto (tipo dinamico), non della classe dichiarata. Funziona per metodi d'istanza, NON per metodi static.

**4. Differenza tra overriding e overloading?**
Overriding: ridefinire un metodo ereditato (stessa firma). Overloading: definire piu' metodi con lo stesso nome ma parametri diversi. Override usa late binding (runtime), overload usa early binding (compile-time).

**5. Un metodo static puo' essere override?**
No. I metodi static subiscono hiding, non overriding. Vengono risolti con early binding (tipo statico).

**6. Cosa significa `final` su metodo/classe/variabile?**
Su metodo: non override-abile. Su classe: non estendibile. Su variabile: assegnabile una sola volta (costante per primitivi, riferimento fisso per oggetti).

### B. Classi Astratte e Interfacce

**7. Differenza tra classe astratta e interfaccia?**
Classe astratta: puo' avere stato (campi), costruttore, metodi concreti e astratti, singola ereditarieta'. Interfaccia: nessuno stato d'istanza, nessun costruttore, metodi astratti (+ default da Java 8), multipla implementazione.

**8. Si puo' istanziare una classe astratta?**
No. Si possono solo istanziare le sottoclassi concrete.

**9. Una classe astratta puo' avere costruttore?**
Si, e viene chiamato con `super()` dalle sottoclassi. Non puo' essere chiamato direttamente con `new`.

**10. Cosa sono i metodi default nelle interfacce?**
Metodi con implementazione di default introdotti in Java 8. Le classi che implementano l'interfaccia possono usare il default o fare override.

### C. Eccezioni

**11. Differenza tra checked e unchecked exception?**
Checked: extends `Exception` (direttamente), obbligatorio dichiararle con `throws` o catturarle. Unchecked: extends `RuntimeException`, non obbligatorio dichiararle. Checked = situazione recuperabile, Unchecked = errore logico del programmatore.

**12. Cosa succede se un metodo lancia una checked exception non dichiarata?**
Errore di compilazione. Il compilatore controlla che tutte le checked exceptions siano dichiarate o catturate.

**13. In un override, posso aggiungere nuove checked exceptions?**
No. L'override puo' dichiarare le stesse eccezioni del padre, un sottoinsieme, o nessuna. Non puo' aggiungerne di nuove.

**14. Cosa fa `finally`?**
Il blocco `finally` viene eseguito SEMPRE, sia che si verifichi un'eccezione sia che non si verifichi. Usato per rilasciare risorse.

**15. Cos'e' il try-with-resources?**
Sintassi che auto-chiude risorse `AutoCloseable` alla fine del blocco try: `try (Scanner s = new Scanner(...)) { ... }`.

### D. Specifiche e Astrazione

**16. Cosa sono REQUIRES, MODIFIES, EFFECTS?**
Clausole della specifica di un metodo. REQUIRES: precondizioni. MODIFIES: quali oggetti vengono modificati. EFFECTS: postcondizioni (cosa fa il metodo quando REQUIRES e' soddisfatto).

**17. Differenza tra metodo totale e parziale?**
Totale: funziona per ogni input (nessun REQUIRES restrittivo). Parziale: ha precondizioni specifiche nel REQUIRES. Un metodo parziale puo' essere reso totale aggiungendo controlli e lanciando eccezioni.

**18. Cos'e' un ADT?**
Abstract Data Type: tipo definito dalle sue operazioni (creatori, produttori, osservatori, mutatori) e non dalla sua rappresentazione interna.

**19. Cos'e' la funzione di astrazione (AF)?**
Mappatura dalla rappresentazione concreta (campi privati) al valore astratto che l'utente percepisce. Descrive il significato dei campi.

**20. Cos'e' l'invariante di rappresentazione (RI)?**
Condizione booleana che deve essere vera per OGNI istanza valida in OGNI momento. Garantisce la consistenza della rappresentazione interna.

### E. Iteratori e Collections

**21. Differenza tra Iterable e Iterator?**
`Iterable<T>` ha un metodo `iterator()` che restituisce un `Iterator<T>`. `Iterator<T>` ha `hasNext()`, `next()` e `remove()`. Iterable permette il for-each, Iterator esegue l'iterazione.

**22. Perche' una classe implementa Iterable?**
Per poter essere usata nel costrutto for-each: `for (T x : oggettoIterable) { ... }`.

**23. Come si rimuove un elemento durante l'iterazione senza ConcurrentModificationException?**
Usando `Iterator.remove()` dopo `next()`. NON usare `list.remove()` dentro un for-each.

**24. Differenza tra Comparable e Comparator?**
`Comparable<T>`: ordine naturale, metodo `compareTo(T)` nella classe stessa. `Comparator<T>`: ordine esterno, metodo `compare(T, T)` in oggetto separato o lambda.

**25. compareTo restituisce 0 implica equals true?**
Non necessariamente. Puo' esserci incoerenza intenzionale (es. due Cerimonie con nomi diversi ma stessa durata). Attenzione: `TreeSet` usa `compareTo` per uguaglianza!

### F. Contratti e Metodi Fondamentali

**26. Contratto di equals?**
Riflessivo (`x.equals(x)==true`), simmetrico, transitivo, consistente, `x.equals(null)==false`. Se si cambia equals si DEVE cambiare hashCode.

**27. Perche' cambiare hashCode quando si cambia equals?**
Contratto: se `x.equals(y)` allora `x.hashCode() == y.hashCode()`. Altrimenti `HashSet` e `HashMap` non funzionano correttamente.

**28. Differenza tra shallow copy e deep copy?**
Shallow: copia solo i riferimenti (oggetti interni condivisi). Deep: copia anche gli oggetti referenziati (copia indipendente).

### G. Principi di Progettazione

**29. Cos'e' il principio di Liskov (LSP)?**
Se S e' sottotipo di T, gli oggetti di T possono essere sostituiti con oggetti di S senza alterare la correttezza. La sottoclasse non puo' avere precondizioni piu' restrittive ne' postcondizioni meno restrittive.

**30. Composizione vs Ereditarieta'?**
Ereditarieta': relazione IS-A. Composizione: relazione HAS-A. Preferire composizione quando non serve polimorfismo: piu' flessibile e meno accoppiata.

**31. Cos'e' la rep exposure?**
Quando la rappresentazione interna (campi privati) viene esposta all'esterno, permettendo a codice esterno di violare l'RI. Si previene con copia difensiva.

**32. Cos'e' l'astrazione per specifica?**
Il chiamante usa il metodo basandosi SOLO sulla sua specifica (REQUIRES/EFFECTS), senza conoscere ne' dipendere dall'implementazione.

---

## 26. Pattern d'esame pratico

### Struttura ricorrente in TUTTI gli esami

```
1. Classe astratta base (Contenitore/Sorpresa/Evento)
   - abstract class ... implements Comparable<T>, Cloneable
   - Campi comuni private (nome, quantita, etc.)
   - Costruttore protected con validazione
   - Metodo astratto per calcolo (calcolaVolume/getCosto/getDurata)
   - compareTo basato sul calcolo
   - equals basato su nome
   - hashCode coerente con equals
   - clone
   - toString
   - AF e RI
   - main() di test

2. Sottoclassi concrete (Sfera/Cilindro, Cioccolatino/Giocattolo, Cerimonia/Gara)
   - extends classe base
   - Campi specifici private final
   - Costruttore con super() + validazione
   - @Override metodo astratto
   - toString specifico
   - AF specifica

3. Eccezione CHECKED
   - extends Exception
   - Costruttore con super(msg)

4. Eccezione UNCHECKED
   - extends RuntimeException
   - Costruttore con super(msg)

5. Classe collezione (Vetreria/Calendario/Olimpiade)
   - implements Iterable<T>
   - ArrayList<T> o T[] come rappresentazione
   - aggiungi/inserisci con controlli + lancia eccezioni
   - rimuovi/estrai/apri
   - iterator() con sort su copia
   - toString con formato esatto
   - AF e RI

6. Classe Main/Test
   - Scanner per input (stdin o args + stdin)
   - Parsing riga per riga con split
   - Switch su tipo/comando
   - try-catch per eccezioni checked
   - Stampa risultati
```

### Schema visivo

```
┌─────────────────────────┐
│  Classe Astratta        │  abstract, Comparable, equals(nome), compareTo(valore)
│  + metodo astratto      │  AF, RI, OVERVIEW, costruttore protected
└──────────┬──────────────┘
           │ extends
      ┌────┴────┐
      │ Sotto1  │  Sotto2   → implementano metodo astratto, toString, AF
      └─────────┘

┌─────────────────────────┐
│  Collezione             │  Iterable, iterator ordinato per ordine naturale
│  + aggiungi/estrai      │  AF, RI, OVERVIEW, eccezioni nei metodi
└─────────────────────────┘

┌──────────────────────┐
│  2 Eccezioni         │  1 checked (Exception), 1 unchecked (RuntimeException)
└──────────────────────┘

┌──────────────────────┐
│  Main / Test         │  Scanner + split + switch + try-catch
└──────────────────────┘
```

---

## 27. Soluzione completa: Simulazione Vetreria

### Specifica

- `Contenitore` abstract: Sfera, Cilindro, Cuboide
- `Vetreria`: collezione Iterable, con aggiungi, estrai, ottimizza
- `CapacityException` (unchecked), `LiquidsException` (checked)
- equals NON esplicitamente richiesto, compareTo per capienza
- Input da stdin: `liquido quantita Tipo [parametri]`

### Implementazione completa

```java
// === CapacityException.java ===
public class CapacityException extends RuntimeException {
    public CapacityException(String msg) { super(msg); }
}

// === LiquidsException.java ===
public class LiquidsException extends Exception {
    public LiquidsException(String msg) { super(msg); }
}
```

```java
// === Contenitore.java ===
import java.util.*;

public abstract class Contenitore implements Comparable<Contenitore>, Cloneable {
    // OVERVIEW: Un Contenitore e' un recipiente MUTABILE che puo' contenere
    //           un liquido fino alla sua capienza massima (volume).

    private String liquido;
    private double quantita;

    // AF(this): un contenitore che contiene 'quantita' unita' di 'liquido'.
    //           Se liquido == null, il contenitore e' vuoto.
    // RI(this): quantita >= 0 && quantita <= calcolaVolume()
    //           && (liquido == null) == (quantita == 0)

    protected Contenitore() {
        this.liquido = null;
        this.quantita = 0;
    }

    protected Contenitore(String liquido, double quantita) {
        if (quantita < 0) throw new IllegalArgumentException("Quantita' negativa");
        if (quantita > calcolaVolume()) {
            throw new CapacityException("Il contenitore ha capienza: "
                + calcolaVolume() + " ma il liquido ha un volume di: " + quantita);
        }
        this.liquido = (quantita > 0) ? liquido : null;
        this.quantita = quantita;
    }

    public abstract double calcolaVolume();

    /**
     * REQUIRES: -
     * MODIFIES: this, sorgente
     * EFFECTS: versa il liquido da sorgente in this.
     *          Se i liquidi sono incompatibili lancia LiquidsException.
     */
    public void versa(Contenitore sorgente) throws LiquidsException {
        if (this.liquido != null && sorgente.liquido != null
            && !this.liquido.equals(sorgente.liquido)) {
            throw new LiquidsException("liquidi incompatibili "
                + this.liquido + " e " + sorgente.liquido);
        }

        double spazio = calcolaVolume() - this.quantita;
        double daVersare = Math.min(sorgente.quantita, spazio);

        if (this.liquido == null) this.liquido = sorgente.liquido;
        this.quantita += daVersare;
        sorgente.quantita -= daVersare;
        if (sorgente.quantita == 0) sorgente.liquido = null;
    }

    @Override
    public int compareTo(Contenitore altro) {
        return Double.compare(this.calcolaVolume(), altro.calcolaVolume());
    }

    @Override
    public Contenitore clone() {
        try {
            return (Contenitore) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public String getLiquido() { return liquido; }
    public double getQuantita() { return quantita; }

    public static void main(String[] args) {
        Sfera sfera = new Sfera(2, "acqua", 25);
        Cuboide cuboide = new Cuboide(2, 3, 4);
        Cilindro cilindro = new Cilindro(3, 2, "alcool", 20);

        try { sfera.versa(cilindro); }
        catch (LiquidsException e) { System.out.println(e.getMessage()); }

        try { sfera.versa(cuboide); }
        catch (LiquidsException e) { System.out.println(e.getMessage()); }

        System.out.println(sfera);
        System.out.println(cuboide);
        System.out.println(cilindro);
    }
}
```

```java
// === Sfera.java ===
public class Sfera extends Contenitore {
    // OVERVIEW: contenitore sferico definito dal raggio

    private final double raggio;

    // AF(this): una sfera di raggio 'raggio'
    // RI(this): raggio > 0

    public Sfera(double raggio) {
        super();
        this.raggio = raggio;
    }

    public Sfera(double raggio, String liquido, double quantita) {
        this.raggio = raggio;
        if (quantita > calcolaVolume()) {
            throw new CapacityException("La sfera ha capienza: "
                + calcolaVolume() + " ma il liquido ha un volume di: " + quantita);
        }
    }

    @Override
    public double calcolaVolume() {
        return Math.PI * Math.pow(raggio, 3) * 4.0 / 3.0;
    }

    @Override
    public String toString() {
        return "Sfera - r: " + raggio
               + "\n\t(capienza: " + calcolaVolume()
               + " liquido: " + getLiquido() + " qty: " + getQuantita() + ")";
    }
}
```

```java
// === Cilindro.java ===
public class Cilindro extends Contenitore {
    // OVERVIEW: contenitore cilindrico definito da altezza e raggio

    private final double altezza;
    private final double raggio;

    // AF(this): un cilindro di altezza 'altezza' e raggio 'raggio'
    // RI(this): altezza > 0 && raggio > 0

    public Cilindro(double altezza, double raggio) {
        super();
        this.altezza = altezza;
        this.raggio = raggio;
    }

    public Cilindro(double altezza, double raggio, String liquido, double quantita) {
        this.altezza = altezza;
        this.raggio = raggio;
        if (quantita > calcolaVolume()) {
            throw new CapacityException("Il cilindro ha capienza: "
                + calcolaVolume() + " ma il liquido ha un volume di: " + quantita);
        }
    }

    @Override
    public double calcolaVolume() {
        return altezza * Math.PI * Math.pow(raggio, 2);
    }

    @Override
    public String toString() {
        return "Cilindro - h: " + altezza + " r: " + raggio
               + "\n\t(capienza: " + calcolaVolume()
               + " liquido: " + getLiquido() + " qty: " + getQuantita() + ")";
    }
}
```

```java
// === Cuboide.java ===
public class Cuboide extends Contenitore {
    // OVERVIEW: contenitore a forma di parallelepipedo

    private final double a, b, c;

    // AF(this): un cuboide con lati a, b, c
    // RI(this): a > 0 && b > 0 && c > 0

    public Cuboide(double a, double b, double c) {
        super();
        this.a = a; this.b = b; this.c = c;
    }

    public Cuboide(double a, double b, double c, String liquido, double quantita) {
        this.a = a; this.b = b; this.c = c;
        if (quantita > calcolaVolume()) {
            throw new CapacityException("Il cuboide ha capienza: "
                + calcolaVolume() + " ma il liquido ha un volume di: " + quantita);
        }
    }

    @Override
    public double calcolaVolume() { return a * b * c; }

    @Override
    public String toString() {
        return "Cuboide - a: " + a + " b: " + b + " c: " + c
               + "\n\t(capienza: " + calcolaVolume()
               + " liquido: " + getLiquido() + " qty: " + getQuantita() + ")";
    }
}
```

```java
// === Vetreria.java ===
import java.util.*;

public class Vetreria implements Iterable<Contenitore> {
    // OVERVIEW: Vetreria e' un insieme MUTABILE di Contenitori.

    private final List<Contenitore> contenitori;

    // AF(this): una vetreria contenente i contenitori in this.contenitori
    // RI(this): contenitori != null && per ogni c in contenitori: c != null

    public Vetreria() {
        contenitori = new ArrayList<>();
    }

    /**
     * MODIFIES: this
     * EFFECTS: aggiunge c a this.
     */
    public void aggiungi(Contenitore c) {
        if (c == null) throw new NullPointerException();
        contenitori.add(c);
    }

    /**
     * MODIFIES: this
     * EFFECTS: estrae tutti i contenitori con il liquido specificato,
     *          li rimuove da this e restituisce una nuova Vetreria.
     */
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

    @Override
    public Iterator<Contenitore> iterator() {
        List<Contenitore> copia = new ArrayList<>(contenitori);
        Collections.sort(copia);   // ordina per capienza (ordine naturale)
        return copia.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Vetreria con:\n");
        for (Contenitore c : this) {
            sb.append("\t").append(c).append("\n");
        }
        return sb.toString();
    }
}
```

```java
// === Main.java ===
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Vetreria v = new Vetreria();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String riga = sc.nextLine().trim();
            if (riga.isEmpty()) continue;

            String[] parti = riga.split("\\s+");
            String liquido = parti[0];
            double quantita = Double.parseDouble(parti[1]);
            String tipo = parti[2];

            try {
                Contenitore c = null;
                switch (tipo) {
                    case "Sfera":
                        double r = Double.parseDouble(parti[3]);
                        c = new Sfera(r, liquido, quantita);
                        break;
                    case "Cuboide":
                        double a = Double.parseDouble(parti[3]);
                        double b = Double.parseDouble(parti[4]);
                        double cc = Double.parseDouble(parti[5]);
                        c = new Cuboide(a, b, cc, liquido, quantita);
                        break;
                    case "Cilindro":
                        // ATTENZIONE: input "Cilindro raggio altezza"
                        // Il README dice "raggio r e altezza h"
                        double rr = Double.parseDouble(parti[3]);  // primo: raggio
                        double h = Double.parseDouble(parti[4]);   // secondo: altezza
                        c = new Cilindro(h, rr, liquido, quantita);
                        // Costruttore: Cilindro(altezza, raggio, ...)
                        break;
                }
                if (c != null) v.aggiungi(c);
            } catch (CapacityException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();

        System.out.println(v);
    }
}
```

**NOTA SUL CILINDRO:** L'input e' `Cilindro raggio altezza` ma il costruttore e' `Cilindro(altezza, raggio)`. Attenzione all'ordine dei parametri nel parsing!

---

## 28. Soluzione completa: Esame Dicembre – Calendario Sorprese

### Specifica

- `Sorpresa` abstract: Cioccolatino, Giocattolo
- `Calendario`: 31 caselle, inserisci, apri (rimuove), costo, iterator per costo
- `GiornoException` (checked), `SorpresaException` (unchecked)
- equals su **nome**, compareTo su **costo**
- Costo: Cioccolatino = `percentualeCacao / 10`, Giocattolo = `numero parole descrizione`

### Implementazione completa

```java
// === GiornoException.java ===
public class GiornoException extends Exception {
    public GiornoException(String msg) { super(msg); }
}

// === SorpresaException.java ===
public class SorpresaException extends RuntimeException {
    public SorpresaException(String msg) { super(msg); }
}
```

```java
// === Sorpresa.java ===
public abstract class Sorpresa implements Comparable<Sorpresa> {
    // OVERVIEW: Una Sorpresa e' definita dal suo nome.
    //           Due sorprese sono uguali se hanno lo stesso nome.

    private final String nome;

    // AF(this): una sorpresa di nome 'nome' con costo getCosto()
    // RI(this): nome != null && !nome.isEmpty()

    protected Sorpresa(String nome) {
        if (nome == null || nome.isEmpty())
            throw new IllegalArgumentException("Nome non valido");
        this.nome = nome;
    }

    public String getNome() { return nome; }
    public abstract double getCosto();

    @Override
    public int compareTo(Sorpresa altra) {
        return Double.compare(this.getCosto(), altra.getCosto());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sorpresa)) return false;
        Sorpresa s = (Sorpresa) o;
        return this.nome.equals(s.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }

    public static void main(String[] args) {
        Sorpresa s1 = new Cioccolatino("Lindt", 25);
        Sorpresa s2 = new Giocattolo("Yorick", "di infinita allegria");
        Sorpresa s3 = new Cioccolatino("Yorick", 1);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println("I primi due sono uguali? " + s1.equals(s2));
        System.out.println("Gli ultimi due sono uguali? " + s2.equals(s3));
        System.out.println("Il primo e' piu' grande del secondo? " + (s1.compareTo(s2) > 0));
    }
}
```

```java
// === Cioccolatino.java ===
public class Cioccolatino extends Sorpresa {
    // OVERVIEW: un cioccolatino con una percentuale di cacao

    private final int percentualeCacao;

    // AF(this): un cioccolatino di nome getNome() con cacao al percentualeCacao%
    // RI(this): percentualeCacao >= 0 && percentualeCacao <= 100

    public Cioccolatino(String nome, int percentualeCacao) {
        super(nome);
        this.percentualeCacao = percentualeCacao;
    }

    @Override
    public double getCosto() {
        return percentualeCacao / 10.0;
    }

    @Override
    public String toString() {
        return "Cioccolatino: " + getNome() + " (" + getCosto() + ") cacao: " + percentualeCacao + "%";
    }
}
```

```java
// === Giocattolo.java ===
public class Giocattolo extends Sorpresa {
    // OVERVIEW: un giocattolo con una descrizione

    private final String descrizione;

    // AF(this): un giocattolo di nome getNome() con descrizione 'descrizione'
    // RI(this): descrizione != null && !descrizione.isEmpty()

    public Giocattolo(String nome, String descrizione) {
        super(nome);
        this.descrizione = descrizione;
    }

    @Override
    public double getCosto() {
        return descrizione.split("\\s+").length;
    }

    @Override
    public String toString() {
        return "Giocattolo: " + getNome() + " (" + getCosto() + ") descrizione: " + descrizione;
    }
}
```

```java
// === Calendario.java ===
import java.util.*;

public class Calendario implements Iterable<Sorpresa> {
    // OVERVIEW: Un calendario di 31 giorni con caselle per sorprese

    private final Sorpresa[] caselle = new Sorpresa[31];

    // AF(this): un calendario dove il giorno i+1 contiene caselle[i]
    // RI(this): caselle != null && caselle.length == 31
    //           && non ci sono due sorprese equals tra loro

    /**
     * MODIFIES: this
     * EFFECTS: inserisce s nel giorno specificato.
     *          Lancia GiornoException (checked) se il giorno e' occupato.
     *          Lancia SorpresaException (unchecked) se la sorpresa e' gia' presente.
     */
    public void inserisci(int giorno, Sorpresa s) throws GiornoException {
        if (giorno < 1 || giorno > 31)
            throw new IllegalArgumentException("Giorno non valido");

        // Controlla se la sorpresa esiste gia'
        for (Sorpresa esistente : caselle) {
            if (esistente != null && esistente.equals(s))
                throw new SorpresaException("Questa sorpresa gia' presente nel calendario");
        }

        // Controlla se il giorno e' occupato
        if (caselle[giorno - 1] != null)
            throw new GiornoException("Una sorpresa gia' presente per il giorno");

        caselle[giorno - 1] = s;
    }

    /**
     * MODIFIES: this
     * EFFECTS: apre la casella del giorno, restituisce e rimuove la sorpresa.
     *          Lancia GiornoException se il giorno e' vuoto.
     */
    public Sorpresa apri(int giorno) throws GiornoException {
        if (giorno < 1 || giorno > 31)
            throw new IllegalArgumentException("Giorno non valido");
        if (caselle[giorno - 1] == null)
            throw new GiornoException("Nessuna sorpresa per il giorno");
        Sorpresa s = caselle[giorno - 1];
        caselle[giorno - 1] = null;
        return s;
    }

    public double costo() {
        double totale = 0;
        for (Sorpresa s : caselle) {
            if (s != null) totale += s.getCosto();
        }
        return totale;
    }

    @Override
    public Iterator<Sorpresa> iterator() {
        List<Sorpresa> ordinate = new ArrayList<>();
        for (Sorpresa s : caselle) {
            if (s != null) ordinate.add(s);
        }
        Collections.sort(ordinate);   // ordina per costo
        return ordinate.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Calendario delle sorprese (" + costo() + ")\n");
        for (Sorpresa s : this) {   // usa iterator → ordine per costo
            sb.append("\t").append(s).append("\n");
        }
        return sb.toString();
    }
}
```

```java
// === Test.java ===
import java.util.*;

public class Test {
    public static void main(String[] args) {
        Calendario cal = new Calendario();
        Scanner sc = new Scanner(System.in);

        System.out.println("Inserisci sorpresa o apri giorno (Ctrl+D termina)");
        System.out.println("\tinserisci <giorno> Cioccolatino <nome> <% cacao>");
        System.out.println("\tinserisci <giorno> Giocattolo <nome> <descrizione>");
        System.out.println("\tapri <giorno>");
        System.out.println();

        while (sc.hasNextLine()) {
            String riga = sc.nextLine().trim();
            if (riga.isEmpty()) continue;

            String[] parti = riga.split("\\s+");

            try {
                switch (parti[0]) {
                    case "inserisci":
                        int giorno = Integer.parseInt(parti[1]);
                        String tipo = parti[2];
                        String nome = parti[3];

                        switch (tipo) {
                            case "Cioccolatino":
                                int cacao = Integer.parseInt(parti[4]);
                                cal.inserisci(giorno, new Cioccolatino(nome, cacao));
                                break;
                            case "Giocattolo":
                                // La descrizione e' tutto dopo il nome
                                StringBuilder desc = new StringBuilder();
                                for (int i = 4; i < parti.length; i++) {
                                    if (i > 4) desc.append(" ");
                                    desc.append(parti[i]);
                                }
                                cal.inserisci(giorno, new Giocattolo(nome, desc.toString()));
                                break;
                        }
                        break;

                    case "apri":
                        int g = Integer.parseInt(parti[1]);
                        Sorpresa trovata = cal.apri(g);
                        System.out.println("\ttrovato: " + trovata);
                        break;
                }
            } catch (GiornoException | SorpresaException e) {
                System.out.println("\tEccezione: " + e.getMessage());
            }
        }
        sc.close();

        System.out.println();
        System.out.print(cal);
    }
}
```

---

## 29. Soluzione completa: Esame Gennaio – Olimpiadi Invernali

### Specifica

- `Evento` abstract: Cerimonia, Gara
- `Olimpiade`: 19 giorni, inserisci, rimuovi, toString per giorno, iterator per durata
- `GiornoException` (checked), `EventoException` (unchecked)
- equals su **nome**, compareTo su **durata**
- Durata: Cerimonia = 60 fisso, Gara = 5 \* numAtleti
- Vincolo speciale: Cerimonia apertura SOLO giorno 1, chiusura SOLO giorno 19
- Anno da args[0], comandi da stdin
- **toString() e iterator() hanno ordini DIVERSI!**

### Implementazione completa

```java
// === GiornoException.java ===
public class GiornoException extends Exception {
    public GiornoException(String msg) { super(msg); }
}

// === EventoException.java ===
public class EventoException extends RuntimeException {
    public EventoException(String msg) { super(msg); }
}
```

```java
// === Evento.java ===
public abstract class Evento implements Comparable<Evento> {
    // OVERVIEW: Un evento delle olimpiadi invernali, definito dal nome.
    //           Due eventi sono uguali se hanno lo stesso nome.

    private final String nome;

    // AF(this): un evento di nome 'nome' con durata getDurata()
    // RI(this): nome != null && !nome.isEmpty()

    protected Evento(String nome) {
        if (nome == null || nome.isEmpty())
            throw new IllegalArgumentException("Nome non valido");
        this.nome = nome;
    }

    public String getNome() { return nome; }
    public abstract int getDurata();

    @Override
    public int compareTo(Evento altro) {
        return Integer.compare(this.getDurata(), altro.getDurata());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evento)) return false;
        Evento e = (Evento) o;
        return this.nome.equals(e.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }

    public static void main(String[] args) {
        Evento e1 = new Cerimonia("Apertura2026", true);
        Evento e2 = new Gara("Slittino1", 15);
        Evento e3 = new Cerimonia("Slittino1", false);

        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);
        System.out.println("I primi due sono uguali? " + e1.equals(e2));
        System.out.println("Gli ultimi due sono uguali? " + e2.equals(e3));
        System.out.println("Il primo e' piu' grande del secondo? " + (e1.compareTo(e2) > 0));
    }
}
```

```java
// === Cerimonia.java ===
public class Cerimonia extends Evento {
    // OVERVIEW: una cerimonia di apertura o chiusura, durata 60 minuti

    private final boolean apertura;

    // AF(this): una cerimonia di (apertura?"apertura":"chiusura")
    //           di nome getNome() con durata 60
    // RI(this): (nessun vincolo aggiuntivo)

    public Cerimonia(String nome, boolean apertura) {
        super(nome);
        this.apertura = apertura;
    }

    public boolean isApertura() { return apertura; }

    @Override
    public int getDurata() { return 60; }

    @Override
    public String toString() {
        return "Cerimonia " + (apertura ? "apertura" : "chiusura")
               + " nome: " + getNome() + " durata: " + getDurata();
    }
}
```

```java
// === Gara.java ===
public class Gara extends Evento {
    // OVERVIEW: una gara con un certo numero di atleti, durata = 5 * atleti

    private final int numAtleti;

    // AF(this): una gara di nome getNome() con numAtleti atleti
    // RI(this): numAtleti > 0

    public Gara(String nome, int numAtleti) {
        super(nome);
        if (numAtleti <= 0)
            throw new IllegalArgumentException("Numero atleti non valido");
        this.numAtleti = numAtleti;
    }

    @Override
    public int getDurata() { return 5 * numAtleti; }

    @Override
    public String toString() {
        return "Gara nome: " + getNome() + " durata: " + getDurata()
               + " con " + numAtleti + " atleti";
    }
}
```

```java
// === Olimpiade.java ===
import java.util.*;

public class Olimpiade implements Iterable<Evento> {
    // OVERVIEW: Un'olimpiade invernale con 19 giorni di eventi

    private final int anno;
    private final Evento[] eventi = new Evento[19];

    // AF(this): un'olimpiade dell'anno 'anno' dove il giorno i+1 ha l'evento eventi[i]
    // RI(this): anno > 0 && eventi != null && eventi.length == 19
    //           && non ci sono due eventi equals tra loro
    //           && se eventi[0] e' Cerimonia, e' di apertura
    //           && se eventi[18] e' Cerimonia, e' di chiusura

    public Olimpiade(int anno) {
        this.anno = anno;
    }

    /**
     * MODIFIES: this
     * EFFECTS: inserisce e nel giorno specificato.
     *          Lancia GiornoException se il giorno e' occupato,
     *          o se e' Cerimonia apertura e giorno != 1,
     *          o se e' Cerimonia chiusura e giorno != 19.
     *          Lancia EventoException se l'evento e' gia' presente.
     */
    public void inserisci(int giorno, Evento e) throws GiornoException {
        if (giorno < 1 || giorno > 19)
            throw new IllegalArgumentException("Giorno non valido");

        // Controlla vincoli Cerimonia
        if (e instanceof Cerimonia) {
            Cerimonia cer = (Cerimonia) e;
            if (cer.isApertura() && giorno != 1)
                throw new GiornoException("cerimonia nel giorno sbagliato");
            if (!cer.isApertura() && giorno != 19)
                throw new GiornoException("cerimonia nel giorno sbagliato");
        }

        // Controlla se l'evento esiste gia'
        for (Evento esistente : eventi) {
            if (esistente != null && esistente.equals(e))
                throw new EventoException("evento esiste");
        }

        // Controlla se il giorno e' occupato
        if (eventi[giorno - 1] != null)
            throw new GiornoException("un evento gia' presente per il giorno");

        eventi[giorno - 1] = e;
    }

    /**
     * MODIFIES: this
     * EFFECTS: rimuove l'evento del giorno specificato.
     *          Lancia GiornoException se il giorno e' vuoto.
     */
    public void rimuovi(int giorno) throws GiornoException {
        if (giorno < 1 || giorno > 19)
            throw new IllegalArgumentException("Giorno non valido");
        if (eventi[giorno - 1] == null)
            throw new GiornoException("giorno non esiste");
        eventi[giorno - 1] = null;
    }

    // toString: ordine per GIORNO
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Olimpiade Invernale " + anno + ":\n");
        for (int i = 0; i < eventi.length; i++) {
            if (eventi[i] != null) {
                sb.append("\t").append(i + 1).append(": ").append(eventi[i]).append("\n");
            }
        }
        return sb.toString();
    }

    // iterator: ordine per DURATA (ordine naturale) - DIVERSO da toString!
    @Override
    public Iterator<Evento> iterator() {
        List<Evento> presenti = new ArrayList<>();
        for (Evento e : eventi) {
            if (e != null) presenti.add(e);
        }
        Collections.sort(presenti);
        return presenti.iterator();
    }
}
```

```java
// === Test.java ===
import java.util.*;

public class Test {
    public static void main(String[] args) {
        int anno = Integer.parseInt(args[0]);   // da riga di comando!
        Olimpiade olimpiade = new Olimpiade(anno);

        Scanner sc = new Scanner(System.in);
        System.out.println("Comandi possibili (Ctrl+D termina)");
        System.out.println("\taggiungi <giorno> Cerimonia <nome> <apertura / chiusura>");
        System.out.println("\taggiungi <giorno> Gara <nome> <numero atleti>");
        System.out.println("\trimuovi <giorno>");
        System.out.println();

        while (sc.hasNextLine()) {
            String riga = sc.nextLine().trim();
            if (riga.isEmpty()) continue;

            String[] parti = riga.split("\\s+");

            try {
                switch (parti[0]) {
                    case "aggiungi":
                        int giorno = Integer.parseInt(parti[1]);
                        String tipo = parti[2];
                        String nome = parti[3];

                        switch (tipo) {
                            case "Cerimonia":
                                boolean apertura = parti[4].equals("apertura");
                                olimpiade.inserisci(giorno, new Cerimonia(nome, apertura));
                                break;
                            case "Gara":
                                int atleti = Integer.parseInt(parti[4]);
                                olimpiade.inserisci(giorno, new Gara(nome, atleti));
                                break;
                        }
                        break;

                    case "rimuovi":
                        int g = Integer.parseInt(parti[1]);
                        olimpiade.rimuovi(g);
                        break;
                }
            } catch (GiornoException | EventoException e) {
                System.out.println("\tECCEZIONE: " + e.getMessage());
            }
        }
        sc.close();

        System.out.println();
        System.out.print(olimpiade);

        System.out.println("\nEventi in ordine di durata:");
        for (Evento e : olimpiade) {
            System.out.println("\t" + e);
        }
    }
}
```

---

## 30. ScalaValutazione, strategia e checklist

### Distribuzione punti (32 totali – Simulazione Vetreria)

```
Contenitore (classe base):           7.5 punti
├── classe astratta                   0.5
├── overview                          0.5
├── rappresentazione                  1.5
├── costruttore                       1.0
├── versa                             1.5
├── compare                           1.0
├── clone                             0.5
├── AF                                0.5
└── RI                                0.5

Ogni Sottoclasse (x3):               4.0 punti ciascuna = 12 totale
├── extends Contenitore               0.5
├── overview                          0.5
├── rappresentazione                  1.5
├── costruttore                       1.0
└── AF                                0.5

Vetreria (collezione):               8.5 punti
├── overview                          0.5
├── rappresentazione                  1.0
├── costruttore                       0.5
├── aggiungi                          1.0
├── estrai                            1.5
├── ottimizza                         1.5
├── iterator                          1.5
├── AF                                0.5
└── RI                                0.5

Eccezioni:                            2.0 punti
├── LiquidsException (checked)        1.0
└── CapacityException (unchecked)     1.0

Main:                                 2.0 punti
├── scanner                           0.5
├── splitting                         0.5
├── dispatching                       0.5
└── try-catch                         0.5

TOTALE: 7.5 + 12 + 8.5 + 2 + 2 = 32 punti
```

### Discrepanze note

- ScalaValutazione dice "extends Figura" → il README dice "extends Contenitore". **Segui il README.**
- ScalaValutazione inverte checked/unchecked → **il README ha ragione.**

### Punti facili (6-8 punti in pochi minuti)

| Cosa scrivere           | Dove               | Punti |
| ----------------------- | ------------------ | ----- |
| `abstract class`        | Classe padre       | 0.5   |
| `OVERVIEW`              | Ogni classe        | 0.5xN |
| `AF(this):`             | Ogni classe        | 0.5xN |
| `RI(this):`             | Padre + Collezione | 0.5x2 |
| Campi `private`         | Ogni classe        | 0.5xN |
| Campi `final`           | Sottoclassi        | 0.5xN |
| `extends` corretto      | Sottoclassi        | 0.5xN |
| `implements Comparable` | Classe padre       | 0.5   |
| `implements Iterable`   | Collezione         | 0.5   |

### Ordine di implementazione consigliato

```
1. ECCEZIONI                              [2-3 min, 2 pt]
   → 3 righe ciascuna, extends Exception / RuntimeException

2. CLASSE ASTRATTA                        [25-30 min, 7.5 pt]
   → abstract class + OVERVIEW + campi + AF + RI
   → Costruttore protected + controlli
   → Metodo astratto + compareTo + clone + equals + hashCode
   → toString

3. SOTTOCLASSI                            [15-20 min, 4 pt cad.]
   → extends + OVERVIEW + campi final + AF
   → Costruttore con super() + metodo astratto + toString

4. COLLEZIONE                             [25-30 min, 8.5 pt]
   → implements Iterable + OVERVIEW + AF + RI
   → aggiungi con eccezioni + estrai/rimuovi con Iterator.remove()
   → iterator() con copia + sort + toString

5. MAIN                                   [10-15 min, 2 pt]
   → Scanner + split + switch + try-catch
```

### Checklist pre-consegna

```
CLASSE ASTRATTA BASE:
[ ] Ha "abstract" nella dichiarazione
[ ] Ha OVERVIEW
[ ] Campi private
[ ] Costruttore protected con controlli
[ ] Ha almeno un metodo abstract
[ ] implements Comparable<T>
[ ] compareTo() con Double.compare o Integer.compare
[ ] clone() override come public (se richiesto)
[ ] equals() + hashCode() coerenti (se basato su nome)
[ ] AF e RI scritti come commenti
[ ] toString()

SOTTOCLASSI:
[ ] extends la classe base
[ ] Campi private final
[ ] Costruttore chiama super()
[ ] Metodo astratto implementato (@Override)
[ ] toString() con formato ESATTO del README
[ ] AF specifica

ECCEZIONI:
[ ] Checked: extends Exception
[ ] Unchecked: extends RuntimeException
[ ] Costruttore con super(msg)
[ ] Il tipo corrisponde al README

COLLEZIONE:
[ ] implements Iterable<T>
[ ] Struttura dati interna (ArrayList o array)
[ ] aggiungi/inserisci con controlli e eccezioni
[ ] estrai/rimuovi/apri funziona correttamente
[ ] iterator(): copia → sort → return copia.iterator()
[ ] toString() con formato ESATTO
[ ] AF e RI

MAIN/TEST:
[ ] Scanner per stdin
[ ] args[] se richiesto (es. anno Olimpiade)
[ ] split("\\s+") per dividere la riga
[ ] Switch per tipo di comando
[ ] try-catch per eccezioni
[ ] Ordine parametri corretto (es. Cilindro!)
[ ] Output corrisponde ESATTAMENTE all'esempio

CONFRONTO 3 ESAMI:
|                      | Simulazione    | Dicembre          | Gennaio                |
|----------------------|----------------|-------------------|------------------------|
| Tema                 | Vetreria       | Calendario        | Olimpiadi              |
| Padre astratto       | Contenitore    | Sorpresa          | Evento                 |
| Sottoclassi          | Sfera,Cil,Cub  | Cioccol,Giocatt   | Cerimonia,Gara         |
| Collezione           | Vetreria(List) | Calendario(arr31) | Olimpiade(arr19)       |
| equals su            | (non esplicito)| nome              | nome                   |
| compareTo su         | capienza       | costo             | durata                 |
| Metodo astratto      | calcolaVolume  | getCosto          | getDurata              |
| Checked exception    | LiquidsExc     | GiornoExc         | GiornoExc              |
| Unchecked exception  | CapacityExc    | SorpresaExc       | EventoExc              |
| Input Main           | stdin          | stdin             | args(anno) + stdin     |
| toString vs iterator | stesso ordine  | diverso           | DIVERSO (giorno/durata)|
| Regola speciale      | ottimizza      | -                 | Cerimonia g1/g19       |
```

**Consiglio finale:** Se riesci a scrivere tutti e 3 gli esami da zero senza consultare nulla, sei pronto per il 30.
