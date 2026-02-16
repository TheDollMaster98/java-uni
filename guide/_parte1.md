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
