# Guida Completa Java – Preparazione Esame Prog2 (v2)

Guida ristrutturata e completa per superare l'esame di Programmazione 2.
Copre: fondamenti, OOP, eccezioni (checked/unchecked), classi astratte, interfacce, Iterable/Iterator, AF/RI, specifiche formali, generics, classi nested, programmazione funzionale, design pattern, composizione vs ereditarietà, filtro teoria e pattern d'esame con soluzioni complete.

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
8. [Ereditarietà e polimorfismo](#8-ereditarietà-e-polimorfismo)
9. [Classi astratte](#9-classi-astratte)
10. [Interfacce](#10-interfacce)

### PARTE 3 – Eccezioni

11. [Gestione eccezioni: checked vs unchecked](#11-gestione-eccezioni-checked-vs-unchecked)
12. [Eccezioni personalizzate e pattern d'esame](#12-eccezioni-personalizzate-e-pattern-desame)

### PARTE 4 – Collezioni e Iteratori

13. [Collezioni: List, Set, Map](#13-collezioni-list-set-map)
14. [Comparable e Comparator](#14-comparable-e-comparator)
15. [Iterable e Iterator](#15-iterable-e-iterator)

### PARTE 5 – Astrazione e Specifiche

16. [Specifiche formali (REQUIRES/MODIFIES/EFFECTS)](#16-specifiche-formali)
17. [AF – Funzione di Astrazione](#17-af--funzione-di-astrazione)
18. [RI – Invariante di Rappresentazione](#18-ri--invariante-di-rappresentazione)
19. [Progettazione ADT e Rep Exposure](#19-progettazione-adt-e-rep-exposure)

### PARTE 6 – Concetti Avanzati

20. [Clonazione e copia difensiva](#20-clonazione-e-copia-difensiva)
21. [Classi nested](#21-classi-nested)
22. [Programmazione funzionale](#22-programmazione-funzionale)
23. [Design pattern](#23-design-pattern)
24. [Composizione vs ereditarietà](#24-composizione-vs-ereditarietà)

### PARTE 7 – Preparazione Esame

25. [Filtro teoria – 30+ domande e risposte](#25-filtro-teoria--30-domande-e-risposte)
26. [Pattern d'esame pratico](#26-pattern-desame-pratico)
27. [Soluzione completa: Simulazione Vetreria](#27-soluzione-completa-simulazione-vetreria)
28. [ScalaValutazione, strategia e checklist](#28-scalavalutazione-strategia-e-checklist)

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

| Aspetto          | Checked Exception                         | Unchecked Exception                 |
| ---------------- | ----------------------------------------- | ----------------------------------- |
| Estende          | `Exception` (direttamente)                | `RuntimeException`                  |
| Compilatore      | OBBLIGA a dichiararle o catturarle        | NON obbliga                         |
| Dichiarazione    | `throws` nella firma del metodo           | Non necessario (ma possibile)       |
| Significato      | Situazione **prevedibile e recuperabile** | **Errore logico** del programmatore |
| Esempi dal corso | `LiquidsException`                        | `CapacityException`                 |

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
} catch (LiquidsException | CapacityException e) {
    System.out.println("Errore: " + e.getMessage());
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

### Pattern ricorrente nell'esame

| Aspetto     | Checked (extends Exception)                | Unchecked (extends RuntimeException)     |
| ----------- | ------------------------------------------ | ---------------------------------------- |
| Simulazione | `LiquidsException` (liquidi incompatibili) | `CapacityException` (capacita' superata) |

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
public void aggiungi(Contenitore c) {
    if (c == null) throw new NullPointerException();  // unchecked
    contenitori.add(c);
}

public void versa(Contenitore sorgente) throws LiquidsException {
    // checked: liquidi incompatibili
    if (this.liquido != null && sorgente.getLiquido() != null
        && !this.liquido.equals(sorgente.getLiquido()))
        throw new LiquidsException("incompatibili");
    // trasferimento...
}

// Nel MAIN: try-catch per gestire checked
try {
    sfera.versa(cilindro);
} catch (LiquidsException e) {
    System.out.println(e.getMessage());
}
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

### Iterator con array (struttura a posizioni fisse)

Quando la struttura interna e' un array con potenziali null:

```java
public class CollezioneArray implements Iterable<Elemento> {
    private final Elemento[] elementi = new Elemento[31];

    @Override
    public Iterator<Elemento> iterator() {
        // 1. Raccogli solo gli elementi non-null
        List<Elemento> presenti = new ArrayList<>();
        for (Elemento e : elementi) {
            if (e != null) presenti.add(e);
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

In alcuni casi, `toString()` mostra gli elementi nell'ordine della struttura interna (es. per posizione/giorno), mentre `iterator()` li restituisce nell'ordine naturale (es. per un valore calcolato). Questo e' un trabocchetto comune all'esame.

```java
public class CollezioneGiorni implements Iterable<Elemento> {
    private final Elemento[] elementi = new Elemento[19];

    // toString: ordine per POSIZIONE nell'array
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elementi.length; i++) {
            if (elementi[i] != null)
                sb.append("\t").append(i + 1).append(": ").append(elementi[i]).append("\n");
        }
        return sb.toString();
    }

    // iterator: ordine NATURALE (compareTo)
    @Override
    public Iterator<Elemento> iterator() {
        List<Elemento> presenti = new ArrayList<>();
        for (Elemento e : elementi) {
            if (e != null) presenti.add(e);
        }
        Collections.sort(presenti);
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

public class Agenda {
    private Appuntamento[] giorni;  // 31 caselle

    // AF(this): un'agenda di 31 giorni dove il giorno i+1 contiene
    //           giorni[i] (null se vuoto)
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

---

# PARTE 6 – Concetti Avanzati

---

## 20. Clonazione e copia difensiva

### Clone

```java
public class Punto implements Cloneable {
    private int x, y;

    @Override
    public Punto clone() {
        try {
            return (Punto) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();  // non dovrebbe mai succedere
        }
    }
}
```

### Deep copy vs Shallow copy

```java
// Shallow copy: clona l'oggetto ma NON i campi riferimento
// Deep copy: clona l'oggetto E tutti gli oggetti interni

public class Classe implements Cloneable {
    private ArrayList<String> lista;

    @Override
    public Classe clone() {
        try {
            Classe copia = (Classe) super.clone();
            copia.lista = new ArrayList<>(this.lista);  // deep copy della lista
            return copia;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
```

### Pattern clone nell'esame

```java
public abstract class Contenitore implements Comparable<Contenitore>, Cloneable {

    @Override
    public Contenitore clone() {
        try {
            return (Contenitore) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
```

Il clone nella classe astratta funziona per tutte le sottoclassi (Sfera, Cilindro, Cuboide) perche' `Object.clone()` copia tutti i campi, compresi quelli delle sottoclassi.

---

## 21. Classi nested

Java permette di definire classi dentro altre classi. Ci sono 4 tipi:

### 21.1 Classe Interna (Inner Class – non statica)

Ha accesso a **tutti** i membri dell'istanza esterna, compresi quelli `private`.

```java
public class Contenitore {
    private int valore = 42;

    public class Elemento {
        public int getValore() {
            return valore;  // accede al campo dell'istanza esterna
        }
    }
}

// Uso: serve un'ISTANZA della classe esterna
Contenitore c = new Contenitore();
Contenitore.Elemento e = c.new Elemento();
```

### 21.2 Classe Nested Statica

NON ha accesso all'istanza esterna.

```java
public class Vetreria {
    public static class Coppia {
        private final String nome;
        private final double volume;
        public Coppia(String nome, double volume) {
            this.nome = nome;
            this.volume = volume;
        }
    }
}

// Uso: NON serve un'istanza della classe esterna
Vetreria.Coppia coppia = new Vetreria.Coppia("Cuboide", 100.0);
```

### 21.3 Classe Locale

Definita **dentro un metodo**. Visibile solo in quel metodo.

```java
public Iterator<Contenitore> iterator() {
    class ContenitoreIterator implements Iterator<Contenitore> {
        private int indice = 0;

        @Override
        public boolean hasNext() { return indice < contenitori.size(); }

        @Override
        public Contenitore next() {
            if (!hasNext()) throw new NoSuchElementException();
            return contenitori.get(indice++);
        }
    }
    return new ContenitoreIterator();
}
```

### 21.4 Classe Anonima

Classe **senza nome**, creata inline. Usata spesso per Iterator e Comparator.

```java
public Iterator<Contenitore> iterator() {
    return new Iterator<Contenitore>() {
        private int i = 0;

        @Override
        public boolean hasNext() { return i < contenitori.size(); }

        @Override
        public Contenitore next() {
            if (!hasNext()) throw new NoSuchElementException();
            return contenitori.get(i++);
        }
    };
}
```

### Confronto rapido

| Tipo          | Contesto            | Accesso a istanza esterna          | Uso tipico           |
| ------------- | ------------------- | ---------------------------------- | -------------------- |
| Inner class   | Membro della classe | Si                                 | Accesso ai campi     |
| Static nested | Membro della classe | No                                 | Classi helper        |
| Locale        | Dentro un metodo    | Si (+ variabili effectively final) | Iterator             |
| Anonima       | Espressione         | Si (+ variabili effectively final) | Iterator, Comparator |

**Per il filtro teoria:**

- Inner class → ha riferimento implicito a `this` della classe esterna
- Static nested → **non** ha riferimento all'istanza esterna
- Anonime e locali → possono accedere solo a variabili locali **effectively final**

---

## 22. Programmazione funzionale

### Interfacce Funzionali

Un'interfaccia con **un solo metodo astratto** (SAM). Si puo' usare con lambdas.

```java
// Interfacce funzionali principali (java.util.function):
Predicate<T>      // T → boolean     (test)
Function<T, R>    // T → R           (trasformazione)
Consumer<T>       // T → void        (azione)
Supplier<T>       // () → T          (produttore)
UnaryOperator<T>  // T → T
BinaryOperator<T> // (T, T) → T
Comparator<T>     // (T, T) → int    (confronto)
```

### Lambda Expressions

```java
// Sintassi: (parametri) -> espressione  oppure  (parametri) -> { blocco }

// Comparator vecchio stile (classe anonima)
Comparator<String> comp1 = new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return a.length() - b.length();
    }
};

// Equivalente con lambda
Comparator<String> comp2 = (a, b) -> a.length() - b.length();

// Predicate
Predicate<Integer> positivo = n -> n > 0;
positivo.test(5);   // true

// Consumer
Consumer<String> stampa = s -> System.out.println(s);

// Function
Function<String, Integer> lunghezza = s -> s.length();
```

### Method References (::)

```java
// 1. Metodo statico:       Classe::metodoStatico
Function<String, Integer> f1 = Integer::parseInt;

// 2. Metodo di istanza:    oggetto::metodo
String str = "ciao";
Supplier<Integer> f2 = str::length;

// 3. Metodo di tipo:       Classe::metodo
Function<String, String> f3 = String::toUpperCase;

// 4. Costruttore:          Classe::new
Supplier<ArrayList<String>> f4 = ArrayList::new;
```

### Streams

Pipeline di operazioni su collezioni. Non modificano la collezione originale.

```java
List<String> nomi = List.of("Alice", "Bob", "Anna", "Carlo", "Ada");

List<String> risultato = nomi.stream()
    .filter(n -> n.startsWith("A"))     // filtra
    .map(String::toUpperCase)            // trasforma
    .sorted()                            // ordina
    .collect(Collectors.toList());       // raccogli
// risultato = ["ADA", "ALICE", "ANNA"]

// Operazioni intermedie:
// .filter(Predicate)  .map(Function)  .sorted()  .distinct()  .limit(n)  .skip(n)

// Operazioni terminali:
// .collect(Collectors.toList())  .forEach(Consumer)  .count()
// .reduce(identity, BinaryOp)   .findFirst()
// .anyMatch(Predicate)  .allMatch(Predicate)
// .min(Comparator)  .max(Comparator)
```

### Optional

```java
Optional<String> opt1 = Optional.of("ciao");
Optional<String> opt2 = Optional.empty();
Optional<String> opt3 = Optional.ofNullable(null);  // vuoto

opt1.isPresent();              // true
opt1.get();                    // "ciao"
opt1.orElse("default");       // "ciao"
opt1.ifPresent(System.out::println);  // stampa "ciao"
```

---

## 23. Design pattern

### 23.1 Iterator Pattern

Gia' usato in tutta la guida. Separa la logica di attraversamento dalla collezione.

```java
public class Vetreria implements Iterable<Contenitore> {
    @Override
    public Iterator<Contenitore> iterator() {
        return new Iterator<Contenitore>() { /* ... */ };
    }
}
for (Contenitore c : vetreria) { /* ... */ }
```

### 23.2 Template Method Pattern

La superclasse definisce lo **scheletro**, le sottoclassi personalizzano i passi.

```java
// Lo usiamo OGNI VOLTA nell'esame con la classe astratta!
public abstract class Contenitore {
    @Override
    public String toString() {
        return getNome() + ": " + calcolaVolume();  // chiama il metodo astratto
    }
    public abstract double calcolaVolume();  // "passo" personalizzabile
}
```

### 23.3 Strategy Pattern

Famiglia di algoritmi intercambiabili. **Comparator e' una Strategy.**

```java
lista.sort(Comparator.comparingDouble(Contenitore::calcolaVolume));           // strategia 1
lista.sort(Comparator.comparingDouble(Contenitore::calcolaVolume).reversed()); // strategia 2
```

### 23.4 Factory Method Pattern

Metodo che crea oggetti senza esporre la logica di costruzione.

```java
// Lo usiamo nel Main quando parsifichiamo l'input!
public static Contenitore crea(String tipo, String[] params) {
    return switch (tipo) {
        case "Cuboide" -> new Cuboide(...);
        case "Sfera"   -> new Sfera(...);
        case "Cilindro"-> new Cilindro(...);
        default -> throw new IllegalArgumentException("Tipo sconosciuto");
    };
}
```

### 23.5 Observer Pattern

Un oggetto (subject) notifica automaticamente i suoi osservatori quando cambia stato.

```java
public interface Observer {
    void update(String evento);
}

public class Subject {
    private List<Observer> observers = new ArrayList<>();
    public void addObserver(Observer o) { observers.add(o); }
    public void removeObserver(Observer o) { observers.remove(o); }
    protected void notifyObservers(String evento) {
        for (Observer o : observers) o.update(evento);
    }
}
```

### 23.6 Decorator Pattern

Aggiunge funzionalita' a un oggetto **senza modificare** la classe originale.

```java
// Esempio: InputStream → BufferedInputStream → DataInputStream
// Ogni strato aggiunge funzionalita'
```

### Riepilogo Pattern

| Pattern             | Dove lo usi all'esame                   | Concetto chiave                               |
| ------------------- | --------------------------------------- | --------------------------------------------- |
| **Iterator**        | `Iterable<T>` + `Iterator<T>`           | Separa attraversamento da struttura           |
| **Template Method** | `toString()` che chiama metodo astratto | Scheletro nell'astratta, passi nelle concrete |
| **Strategy**        | `Comparator` per ordinamento            | Algoritmo intercambiabile                     |
| **Factory Method**  | Switch nel Main per creare oggetti      | Creazione delegata                            |
| **Observer**        | Filtro teoria                           | Subject notifica observers                    |
| **Decorator**       | `BufferedReader(new FileReader(...))`   | Wrapper che aggiunge funzionalita'            |

---

## 24. Composizione vs ereditarieta'

### Ereditarieta' ("IS-A")

```java
public class Cuboide extends Contenitore { }  // Un Cuboide E' UN Contenitore
```

### Composizione ("HAS-A")

```java
public class Vetreria {
    private List<Contenitore> contenitori;  // Una Vetreria HA DEI Contenitori
}
```

### Quando usare cosa?

| Criterio      | Ereditarieta'                    | Composizione                            |
| ------------- | -------------------------------- | --------------------------------------- |
| Relazione     | IS-A (e' un tipo di)             | HAS-A (contiene/usa)                    |
| Accoppiamento | Forte (figlio dipende dal padre) | Debole (cambio interno senza rompere)   |
| Flessibilita' | Rigida (gerarchia fissa)         | Flessibile (componi a runtime)          |
| Riuso codice  | Eredita implementazione          | Delega a componente                     |
| Esempio esame | `Cuboide extends Contenitore`    | `Vetreria` contiene `List<Contenitore>` |

**Regola d'oro:** Preferisci la composizione. Usa l'ereditarieta' solo con vera relazione IS-A.

**Domanda tipica filtro:**
_"Perche' Vetreria non estende ArrayList?"_
→ Perche' Vetreria non E' una lista. Vetreria HA una lista. Estendere ArrayList esporrebbe metodi indesiderati e violerebbe l'incapsulamento.

### Ereditarieta' di specifica vs implementazione

| Tipo                            | Significato                | Esempio                             |
| ------------------------------- | -------------------------- | ----------------------------------- |
| **Di specifica** (interfaccia)  | Erediti SOLO il contratto  | `class X implements Comparable`     |
| **Di implementazione** (classe) | Erediti contratto + codice | `class Cuboide extends Contenitore` |

### Il problema della Fragile Base Class

```java
public class InstrumentedHashSet<E> extends HashSet<E> {
    private int addCount = 0;

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);  // PROBLEMA: HashSet.addAll() chiama add()!
    }
}

InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
s.addAll(List.of("a", "b", "c"));
s.getAddCount();  // Aspettato: 3, Ottenuto: 6!
// addAll aggiunge 3, poi super.addAll chiama add() 3 volte → +3
```

**Soluzione:** composizione (wrapper):

```java
public class InstrumentedSet<E> {
    private final Set<E> set;      // composizione
    private int addCount = 0;

    public InstrumentedSet(Set<E> set) { this.set = set; }

    public boolean add(E e) {
        addCount++;
        return set.add(e);         // delega, nessuna dipendenza
    }

    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return set.addAll(c);      // delega, non chiama il nostro add()
    }
}
```

### Principio di Liskov (LSP)

Se S e' sottotipo di T, gli oggetti di T possono essere sostituiti con oggetti di S senza alterare la correttezza.

In pratica:

- La sottoclasse NON puo' avere precondizioni piu' restrittive
- La sottoclasse NON puo' avere postcondizioni meno restrittive
- La sottoclasse DEVE preservare l'invariante del padre

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
1. Classe astratta base
   - abstract class ... implements Comparable<T>, Cloneable
   - Campi comuni private (nome, quantita, etc.)
   - Costruttore protected con validazione
   - Metodo astratto per calcolo (es. calcolaVolume)
   - compareTo basato sul calcolo
   - equals basato su nome
   - hashCode coerente con equals
   - clone
   - toString
   - AF e RI
   - main() di test

2. Sottoclassi concrete
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

5. Classe collezione
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

## 28. ScalaValutazione, strategia e checklist

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
