# Guida Completa Java 21 – Preparazione Esame Prog2

Guida strutturata per padroneggiare Java 21 e superare l'esame di Programmazione 2.  
Copre: fondamenti, OOP, collezioni, eccezioni, I/O (Scanner, file, args), classi astratte, interfacce, iteratori, AF/RI, specifiche formali, classi nested, programmazione funzionale, design pattern, composizione vs ereditarietà e pattern d'esame.

---

## Indice

1. [Fondamenti](#1-fondamenti)
2. [Strutture di Controllo](#2-strutture-di-controllo)
3. [Metodi e Procedure](#3-metodi-e-procedure)
4. [Classi e Oggetti (OOP)](#4-classi-e-oggetti-oop)
5. [Array](#5-array)
6. [Collezioni: List, Set, Map](#6-collezioni-list-set-map)
7. [Ereditarietà e Polimorfismo](#7-ereditarietà-e-polimorfismo)
8. [Classi Astratte](#8-classi-astratte)
9. [Interfacce](#9-interfacce)
10. [Gestione Eccezioni](#10-gestione-eccezioni)
11. [Lettura Input: args, Scanner, File](#11-lettura-input-args-scanner-file)
12. [Gestione File I/O Completa](#12-gestione-file-io-completa)
13. [Iteratori e Iterable](#13-iteratori-e-iterable)
14. [Comparable e Comparator](#14-comparable-e-comparator)
15. [Clonazione e Copia Difensiva](#15-clonazione-e-copia-difensiva)
16. [Specifiche Formali (REQUIRES/MODIFIES/EFFECTS)](#16-specifiche-formali)
17. [AF – Funzione di Astrazione](#17-af--funzione-di-astrazione)
18. [RI – Invariante di Rappresentazione](#18-ri--invariante-di-rappresentazione)
19. [Progettazione ADT Completa](#19-progettazione-adt-completa)
20. [Pattern d'Esame e Checklist](#20-pattern-desame-e-checklist)
21. [Novità Java 21](#21-novità-java-21)
22. [Cheat Sheet Rapido](#22-cheat-sheet-rapido)
23. [🔴 TEORIA – Filtro 30 Domande](#23--teoria--filtro-30-domande)
24. [🔴 Analisi Esami Reali (Simulazione + 2 Appelli)](#24--analisi-esami-reali-simulazione--2-appelli)
25. [Classi Nested (Interne)](#25-classi-nested-interne)
26. [Programmazione Funzionale](#26-programmazione-funzionale)
27. [Design Pattern](#27-design-pattern)
28. [Composizione vs Ereditarietà](#28-composizione-vs-ereditarietà)

---

## 1. Fondamenti

### Struttura minima di un programma Java

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

### Compilazione ed esecuzione

```bash
javac HelloWorld.java     # compila → genera HelloWorld.class
java HelloWorld            # esegue
java HelloWorld arg1 arg2  # con argomenti da riga di comando
```

### Tipi primitivi

| Tipo      | Dimensione | Esempio             |
| --------- | ---------- | ------------------- |
| `byte`    | 8 bit      | `byte b = 127;`     |
| `short`   | 16 bit     | `short s = 30000;`  |
| `int`     | 32 bit     | `int x = 42;`       |
| `long`    | 64 bit     | `long l = 100L;`    |
| `float`   | 32 bit     | `float f = 3.14f;`  |
| `double`  | 64 bit     | `double d = 3.14;`  |
| `boolean` | 1 bit      | `boolean b = true;` |
| `char`    | 16 bit     | `char c = 'A';`     |

### Wrapper classes (primitivi → oggetti)

```java
int x = 5;
Integer xObj = x;           // autoboxing
int y = xObj;               // unboxing

// Necessari per le collezioni generiche:
List<Integer> numeri = new ArrayList<>();  // non puoi usare List<int>
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

### Stringhe

```java
String s = "ciao";
s.length();              // 4
s.charAt(0);             // 'c'
s.substring(1, 3);       // "ia"
s.toUpperCase();         // "CIAO"
s.toLowerCase();         // "ciao"
s.equals("ciao");        // true  ⚠️ NON usare == per confrontare stringhe!
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

**⚠️ ATTENZIONE:** Le stringhe in Java sono **immutabili**. Ogni modifica crea un nuovo oggetto.

```java
String a = "ciao";
String b = "ciao";
a == b;         // true (stesso literal pool) - MA NON AFFIDABILE!
a.equals(b);    // true ← USARE SEMPRE QUESTO
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

## 2. Strutture di Controllo

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

### Switch classico

```java
switch (mese) {
    case 1:
        System.out.println("Gennaio");
        break;      // ⚠️ senza break "cade" nel case successivo (fall-through)
    case 2:
        System.out.println("Febbraio");
        break;
    default:
        System.out.println("Altro");
}
```

### Switch moderno (Java 14+) – con frecce

```java
switch (mese) {
    case 1 -> System.out.println("Gennaio");
    case 2 -> System.out.println("Febbraio");
    case 3, 4, 5 -> System.out.println("Primavera");
    default -> System.out.println("Altro");
}
```

### Switch con espressione (Java 14+) – restituisce un valore

```java
String nome = switch (mese) {
    case 1 -> "Gennaio";
    case 2 -> "Febbraio";
    default -> "Sconosciuto";
};
```

### Ciclo for

```java
for (int i = 0; i < 10; i++) {
    System.out.println(i);
}
```

### Ciclo while

```java
int i = 0;
while (i < 10) {
    System.out.println(i);
    i++;
}
```

### Ciclo do-while (esegue almeno una volta)

```java
int i = 0;
do {
    System.out.println(i);
    i++;
} while (i < 10);
```

### Ciclo for-each (su array e collezioni)

```java
int[] numeri = {1, 2, 3, 4, 5};
for (int n : numeri) {
    System.out.println(n);
}

List<String> nomi = List.of("Anna", "Marco");
for (String nome : nomi) {
    System.out.println(nome);
}
```

### break e continue

```java
for (int i = 0; i < 100; i++) {
    if (i == 50) break;      // esce dal ciclo
    if (i % 2 == 0) continue; // salta al prossimo ciclo
    System.out.println(i);
}
```

---

## 3. Metodi e Procedure

### Metodo con ritorno

```java
public static int somma(int a, int b) {
    return a + b;
}
```

### Procedura (void – senza ritorno)

```java
public static void saluta(String nome) {
    System.out.println("Ciao " + nome);
}
```

### Differenza tra metodo statico e d'istanza

```java
public class Calcolatrice {
    // Metodo STATICO: si chiama sulla CLASSE, non su un oggetto
    public static int somma(int a, int b) {
        return a + b;
    }

    // Metodo d'ISTANZA: si chiama su un OGGETTO
    private int memoria = 0;
    public int aggiungi(int n) {
        this.memoria += n;
        return this.memoria;
    }
}

// Uso:
int r = Calcolatrice.somma(3, 4);          // statico → sulla classe

Calcolatrice c = new Calcolatrice();
c.aggiungi(5);                              // d'istanza → sull'oggetto
```

### Overloading (stesso nome, parametri diversi)

```java
public static int somma(int a, int b) { return a + b; }
public static double somma(double a, double b) { return a + b; }
public static int somma(int a, int b, int c) { return a + b + c; }
```

### `this` – riferimento all'oggetto corrente

```java
public class Persona {
    private String nome;

    public Persona(String nome) {
        this.nome = nome;      // this.nome = campo, nome = parametro
    }
}
```

---

## 4. Classi e Oggetti (OOP)

### Definire una classe

```java
public class Persona {
    // OVERVIEW: modella una persona con nome e età

    // Rappresentazione (campi privati)
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

    // Override di toString
    @Override
    public String toString() {
        return nome + " (" + eta + " anni)";
    }

    // Override di equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;
        Persona p = (Persona) o;
        return this.nome.equals(p.nome) && this.eta == p.eta;
    }

    // Override di hashCode (obbligatorio se ridefinisci equals)
    @Override
    public int hashCode() {
        return nome.hashCode() * 31 + eta;
    }
}
```

### Creare e usare oggetti

```java
Persona p1 = new Persona("Mario", 30);
Persona p2 = new Persona("Luigi", 25);

System.out.println(p1);           // Mario (30 anni) → chiama toString()
System.out.println(p1.getNome()); // Mario
p1.setEta(31);
```

### Modificatori di accesso

| Modificatore | Classe | Package | Sottoclasse | Mondo |
| ------------ | ------ | ------- | ----------- | ----- |
| `private`    | ✅     | ❌      | ❌          | ❌    |
| (default)    | ✅     | ✅      | ❌          | ❌    |
| `protected`  | ✅     | ✅      | ✅          | ❌    |
| `public`     | ✅     | ✅      | ✅          | ✅    |

**Regola d'oro per Prog2: i campi devono essere SEMPRE `private`.**

### Mutabile vs Immutabile

```java
// MUTABILE: lo stato può essere modificato dopo la creazione
public class ContoBancario {
    private double saldo;

    public ContoBancario(double saldo) { this.saldo = saldo; }
    public void deposita(double importo) { this.saldo += importo; } // modifica lo stato
    public double getSaldo() { return saldo; }
}

// IMMUTABILE: lo stato NON può essere modificato dopo la creazione
public class Punto {
    private final int x;   // final!
    private final int y;   // final!

    public Punto(int x, int y) { this.x = x; this.y = y; }
    public int getX() { return x; }
    public int getY() { return y; }

    // Restituisce un NUOVO oggetto, non modifica this
    public Punto trasla(int dx, int dy) {
        return new Punto(x + dx, y + dy);
    }
}
```

### Record (Java 16+) – tipo immutabile conciso

```java
// Equivale a una classe immutabile con nome, eta, toString, equals, hashCode
public record Persona(String nome, int eta) {}

// Uso:
Persona p = new Persona("Mario", 30);
p.nome();     // "Mario" (getter senza "get")
p.eta();      // 30
```

---

## 5. Array

### Dichiarazione e inizializzazione

```java
int[] numeri = new int[5];           // [0, 0, 0, 0, 0]
int[] valori = {10, 20, 30, 40};    // inizializzazione diretta
String[] nomi = new String[3];      // [null, null, null]
```

### Operazioni comuni

```java
numeri[0] = 42;                      // assegnamento
int x = numeri[0];                   // accesso
int len = numeri.length;             // lunghezza (proprietà, non metodo!)

// Iterazione
for (int i = 0; i < numeri.length; i++) {
    System.out.println(numeri[i]);
}

// For-each
for (int n : numeri) {
    System.out.println(n);
}
```

### Array multidimensionali

```java
int[][] matrice = new int[3][4];     // 3 righe, 4 colonne
matrice[0][0] = 1;

int[][] m = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

for (int i = 0; i < m.length; i++) {
    for (int j = 0; j < m[i].length; j++) {
        System.out.print(m[i][j] + " ");
    }
    System.out.println();
}
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
Arrays.asList(a);                // converte in List (⚠️ con int[] non funziona bene, usa Integer[])
```

---

## 6. Collezioni: List, Set, Map

### Gerarchia delle collezioni (semplificata)

```
Collection (interfaccia)
├── List (interfaccia) → ArrayList, LinkedList
├── Set (interfaccia) → HashSet, TreeSet
└── Queue (interfaccia) → PriorityQueue

Map (interfaccia separata) → HashMap, TreeMap
```

**Regola d'oro: programma ad interfacce (usa `List`, `Set`, `Map` come tipo).**

```java
List<String> nomi = new ArrayList<>();   // ✅ corretto
ArrayList<String> nomi = new ArrayList<>(); // ⚠️ funziona ma meno flessibile
```

---

### List / ArrayList

```java
import java.util.List;
import java.util.ArrayList;

List<String> nomi = new ArrayList<>();

// Aggiungere
nomi.add("Mario");
nomi.add("Luigi");
nomi.add(0, "Peach");         // inserisce in posizione 0

// Leggere
String primo = nomi.get(0);   // "Peach"
int dim = nomi.size();         // 3

// Modificare
nomi.set(1, "Toad");          // sostituisce all'indice 1

// Rimuovere
nomi.remove(0);               // rimuove per indice
nomi.remove("Luigi");         // rimuove per valore (prima occorrenza)

// Cercare
boolean c = nomi.contains("Mario");  // true
int idx = nomi.indexOf("Mario");     // indice o -1

// Iterare
for (String nome : nomi) {
    System.out.println(nome);
}

for (int i = 0; i < nomi.size(); i++) {
    System.out.println(nomi.get(i));
}

// Lista immutabile (Java 9+)
List<String> fissi = List.of("A", "B", "C");  // non modificabile!
```

**⚠️ Attenzione con l'iterazione e la rimozione:**

```java
// ❌ ERRORE: ConcurrentModificationException
for (String s : nomi) {
    if (s.equals("Mario")) nomi.remove(s);
}

// ✅ CORRETTO: usa Iterator
Iterator<String> it = nomi.iterator();
while (it.hasNext()) {
    if (it.next().equals("Mario")) it.remove();
}

// ✅ CORRETTO: rimuovi con indice al contrario
for (int i = nomi.size() - 1; i >= 0; i--) {
    if (nomi.get(i).equals("Mario")) nomi.remove(i);
}
```

---

### Set / HashSet

```java
import java.util.Set;
import java.util.HashSet;

Set<String> colori = new HashSet<>();

colori.add("rosso");
colori.add("blu");
colori.add("rosso");          // IGNORATO – i set non hanno duplicati!

colori.size();                 // 2
colori.contains("blu");       // true
colori.remove("blu");

// Iterare (ordine NON garantito)
for (String c : colori) {
    System.out.println(c);
}
```

### TreeSet – Set ordinato

```java
import java.util.TreeSet;

Set<Integer> numeri = new TreeSet<>();
numeri.add(30);
numeri.add(10);
numeri.add(20);
// Iterazione: 10, 20, 30 (ordinati!)
```

---

### Map / HashMap

```java
import java.util.Map;
import java.util.HashMap;

Map<String, Integer> voti = new HashMap<>();

// Inserire
voti.put("Mario", 28);
voti.put("Luigi", 30);
voti.put("Mario", 25);       // sovrascrive il valore precedente!

// Leggere
int voto = voti.get("Mario");           // 25
int safe = voti.getOrDefault("Peach", 0); // 0 (default se chiave assente)

// Verificare
voti.containsKey("Luigi");              // true
voti.containsValue(30);                 // true

// Rimuovere
voti.remove("Luigi");

// Dimensione
voti.size();

// Iterare (IMPORTANTISSIMO PER L'ESAME)
for (Map.Entry<String, Integer> entry : voti.entrySet()) {
    System.out.println(entry.getKey() + " → " + entry.getValue());
}

// Solo chiavi
for (String chiave : voti.keySet()) {
    System.out.println(chiave);
}

// Solo valori
for (int valore : voti.values()) {
    System.out.println(valore);
}
```

### Pattern comune: contare occorrenze

```java
Map<String, Integer> conteggio = new HashMap<>();
String[] parole = {"ciao", "mondo", "ciao", "java", "ciao"};

for (String p : parole) {
    conteggio.put(p, conteggio.getOrDefault(p, 0) + 1);
}
// {ciao=3, mondo=1, java=1}
```

### Pattern comune: raggruppare elementi

```java
Map<String, List<String>> gruppi = new HashMap<>();

// Aggiungere un elemento a una lista dentro la mappa
String chiave = "frutta";
String valore = "mela";

if (!gruppi.containsKey(chiave)) {
    gruppi.put(chiave, new ArrayList<>());
}
gruppi.get(chiave).add(valore);

// Modo più elegante con computeIfAbsent:
gruppi.computeIfAbsent(chiave, k -> new ArrayList<>()).add(valore);
```

### Tabella riassuntiva

| Struttura   | Duplicati    | Ordinata | Accesso      | Quando usarla            |
| ----------- | ------------ | -------- | ------------ | ------------------------ |
| `ArrayList` | Sì           | Sì       | per indice   | Liste di dati            |
| `HashSet`   | No           | No       | `contains()` | Unicità, verifica rapida |
| `TreeSet`   | No           | Sì       | `contains()` | Unicità + ordinamento    |
| `HashMap`   | Chiave unica | No       | per chiave   | Associazioni chiave→val  |
| `TreeMap`   | Chiave unica | Sì       | per chiave   | Associazioni ordinate    |

---

## 7. Ereditarietà e Polimorfismo

### Ereditarietà (`extends`)

```java
// Superclasse (padre)
public class Animale {
    private String nome;

    public Animale(String nome) {
        this.nome = nome;
    }

    public String getNome() { return nome; }

    public String verso() {
        return "...";
    }

    @Override
    public String toString() {
        return nome + " fa " + verso();
    }
}

// Sottoclasse (figlio)
public class Cane extends Animale {

    public Cane(String nome) {
        super(nome);           // ⚠️ OBBLIGATORIO chiamare il costruttore del padre
    }

    @Override                  // sovrascrive il metodo del padre
    public String verso() {
        return "bau!";
    }
}

public class Gatto extends Animale {
    public Gatto(String nome) {
        super(nome);
    }

    @Override
    public String verso() {
        return "miao!";
    }
}
```

### Polimorfismo

```java
Animale a1 = new Cane("Fido");     // tipo statico: Animale, tipo dinamico: Cane
Animale a2 = new Gatto("Felix");

a1.verso();    // "bau!"  → il metodo chiamato dipende dal TIPO DINAMICO
a2.verso();    // "miao!"

// Collezione polimorfa
List<Animale> animali = new ArrayList<>();
animali.add(new Cane("Rex"));
animali.add(new Gatto("Micio"));

for (Animale a : animali) {
    System.out.println(a);  // polimorfismo: chiama il toString corretto
}
```

### `instanceof` e casting

```java
Animale a = new Cane("Rex");

if (a instanceof Cane) {
    Cane c = (Cane) a;       // downcasting sicuro
}

// Pattern matching con instanceof (Java 16+)
if (a instanceof Cane c) {   // combina verifica e casting
    System.out.println(c.getNome());
}
```

### `super` – accesso al padre

```java
public class CaneGuida extends Cane {
    private String proprietario;

    public CaneGuida(String nome, String proprietario) {
        super(nome);                    // costruttore del padre
        this.proprietario = proprietario;
    }

    @Override
    public String toString() {
        return super.toString() + " (guida di " + proprietario + ")";
        //     ^^^^^^^^^^^^^^^^ chiama il toString del padre
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

## 8. Classi Astratte

Le classi astratte **non possono essere istanziate** e servono come modello per le sottoclassi.

```java
// Classe astratta
public abstract class Contenitore implements Comparable<Contenitore>, Cloneable {
    // OVERVIEW: modella un contenitore di laboratorio con capienza e liquido

    // Rappresentazione (campi PRIVATI – visibilità rep)
    private String liquido;       // null se vuoto
    private double quantita;      // volume di liquido contenuto

    // AF(this): un contenitore che contiene 'quantita' unità di 'liquido'.
    //           Se liquido == null, il contenitore è vuoto.
    // RI(this): quantita >= 0 && quantita <= calcolaVolume()
    //           && (liquido == null) == (quantita == 0)

    /**
     * REQUIRES: -
     * MODIFIES: this
     * EFFECTS: inizializza un nuovo Contenitore con il liquido e la quantità dati;
     *          lancia CapacityException se quantita > capienza
     */
    protected Contenitore(String liquido, double quantita) {
        if (quantita < 0) throw new IllegalArgumentException("Quantità negativa");
        // Nota: calcolaVolume() viene chiamato sulla sottoclasse (polimorfismo)
        if (quantita > calcolaVolume()) {
            throw new CapacityException("Quantità " + quantita + " supera capienza " + calcolaVolume());
        }
        this.liquido = (quantita > 0) ? liquido : null;
        this.quantita = quantita;
    }

    // Metodo ASTRATTO: ogni sottoclasse lo implementa
    public abstract double calcolaVolume();

    // Metodi CONCRETI: logica comune a tutte le sottoclassi
    public double getCapienza() { return calcolaVolume(); }
    public String getLiquido() { return liquido; }
    public double getQuantita() { return quantita; }

    // Setter protetti: usabili solo dalle sottoclassi e dalla stessa classe
    protected void setLiquido(String liquido) { this.liquido = liquido; }
    protected void setQuantita(double quantita) { this.quantita = quantita; }

    /**
     * REQUIRES: -
     * MODIFIES: this, altro
     * EFFECTS: versa il liquido da this in altro; se i liquidi sono incompatibili
     *          lancia LiquidsException; altrimenti trasferisce il volume possibile
     */
    public void versa(Contenitore altro) throws LiquidsException {
        // Controllo compatibilità liquidi
        if (this.liquido != null && altro.liquido != null
                && !this.liquido.equals(altro.liquido)) {
            throw new LiquidsException("liquidi incompatibili " + this.liquido + " e " + altro.liquido);
        }

        // Calcolo quanto posso versare
        double spazioDisponibile = altro.calcolaVolume() - altro.quantita;
        double daVersare = Math.min(this.quantita, spazioDisponibile);

        // Impostazione liquido di destinazione
        if (altro.liquido == null) {
            altro.liquido = this.liquido;
        }

        // Trasferimento
        altro.quantita += daVersare;
        this.quantita -= daVersare;

        // Se this è vuoto, rimuovi il liquido
        if (this.quantita == 0) {
            this.liquido = null;
        }
    }

    // Ordinamento naturale per capienza
    @Override
    public int compareTo(Contenitore altro) {
        return Double.compare(this.calcolaVolume(), altro.calcolaVolume());
    }

    // Clone
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

### Sottoclassi concrete

```java
public class Sfera extends Contenitore {
    // OVERVIEW: contenitore sferico definito dal raggio

    private final double raggio;

    // AF(this): una sfera di raggio 'raggio' con volume 4/3 * PI * r^3
    // RI(this): raggio > 0

    public Sfera(double raggio, String liquido, double quantita) {
        super(liquido, quantita);   // chiama costruttore astratto
        if (raggio <= 0) throw new IllegalArgumentException("Raggio non positivo");
        this.raggio = raggio;
    }

    @Override
    public double calcolaVolume() {
        return Math.PI * Math.pow(raggio, 3) * 4.0 / 3.0;
    }

    @Override
    public String toString() {
        return "Sfera - r: " + raggio + "\n\t(capienza: " + calcolaVolume()
               + " liquido: " + getLiquido() + " qty: " + getQuantita() + ")";
    }
}

public class Cilindro extends Contenitore {
    // OVERVIEW: contenitore cilindrico definito da altezza e raggio

    private final double altezza;
    private final double raggio;

    // AF(this): un cilindro di altezza 'altezza' e raggio 'raggio' con volume h * PI * r^2
    // RI(this): altezza > 0 && raggio > 0

    public Cilindro(double altezza, double raggio, String liquido, double quantita) {
        super(liquido, quantita);
        if (altezza <= 0 || raggio <= 0) throw new IllegalArgumentException("Parametri non positivi");
        this.altezza = altezza;
        this.raggio = raggio;
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

public class Cuboide extends Contenitore {
    // OVERVIEW: contenitore a parallelepipedo rettangolo definito da 3 lati

    private final double a, b, c;

    // AF(this): un cuboide di lati a, b, c con volume a * b * c
    // RI(this): a > 0 && b > 0 && c > 0

    public Cuboide(double a, double b, double c, String liquido, double quantita) {
        super(liquido, quantita);
        if (a <= 0 || b <= 0 || c <= 0) throw new IllegalArgumentException("Lati non positivi");
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double calcolaVolume() {
        return a * b * c;
    }

    @Override
    public String toString() {
        return "Cuboide - a: " + a + " b: " + b + " c: " + c
               + "\n\t(capienza: " + calcolaVolume()
               + " liquido: " + getLiquido() + " qty: " + getQuantita() + ")";
    }
}
```

### Regole classi astratte

1. Non si possono creare istanze: `new Contenitore(...)` → **ERRORE di compilazione**
2. Possono avere metodi astratti (`abstract`) E concreti
3. Le sottoclassi **DEVONO** implementare tutti i metodi astratti (altrimenti devono essere `abstract`)
4. Il costruttore è `protected` (usato solo da `super()`)

### Diagramma tipico d'esame

```
                   Contenitore (abstract)
                   implements Comparable, Cloneable
                  /          |          \
              Sfera      Cilindro     Cuboide
```

---

## 9. Interfacce

Un'interfaccia definisce un **contratto** che le classi devono rispettare.

```java
// Definizione
public interface Volante {
    void vola();                     // metodo astratto (implicitamente public abstract)
    double getAltitudine();

    // Metodo con implementazione di default (Java 8+)
    default String descrizione() {
        return "Oggetto volante";
    }

    // Metodo statico
    static boolean puoVolare(Object o) {
        return o instanceof Volante;
    }
}

// Implementazione (una classe può implementare MOLTE interfacce)
public class Aereo implements Volante, Comparable<Aereo> {
    private double altitudine;

    @Override
    public void vola() {
        altitudine += 100;
    }

    @Override
    public double getAltitudine() {
        return altitudine;
    }

    @Override
    public int compareTo(Aereo altro) {
        return Double.compare(this.altitudine, altro.altitudine);
    }
}
```

### Interfacce più importanti per l'esame

| Interfaccia     | Da implementare       | Scopo                   |
| --------------- | --------------------- | ----------------------- |
| `Comparable<T>` | `compareTo(T o)`      | Ordinamento naturale    |
| `Iterable<T>`   | `iterator()`          | Usabile nel for-each    |
| `Iterator<T>`   | `hasNext()`, `next()` | Scorrere una collezione |
| `Cloneable`     | `clone()`             | Clonazione di oggetti   |

### Differenze tra Interfaccia e Classe Astratta

| Aspetto         | Interfaccia                    | Classe Astratta        |
| --------------- | ------------------------------ | ---------------------- |
| Ereditarietà    | Multipla (`implements A, B`)   | Singola (`extends A`)  |
| Campi           | Solo costanti (`static final`) | Qualsiasi              |
| Costruttori     | No                             | Sì                     |
| Metodi concreti | Solo `default` (Java 8+)       | Sì                     |
| Quando usarla   | Contratto / capacità           | Modello base con stato |

---

## 10. Gestione Eccezioni

### Gerarchia delle eccezioni

```
Throwable
├── Error (errori di sistema, non gestibili: OutOfMemoryError, StackOverflowError)
└── Exception
    ├── Checked Exceptions (DEVI gestirle o dichiararle)
    │   ├── IOException
    │   ├── FileNotFoundException
    │   └── Le tue eccezioni extends Exception
    └── RuntimeException (Unchecked – NON sei obbligato a dichiararle)
        ├── NullPointerException
        ├── IllegalArgumentException
        ├── InputMismatchException
        ├── IndexOutOfBoundsException
        ├── ArithmeticException
        └── Le tue eccezioni extends RuntimeException
```

### Checked vs Unchecked – quando usarle

| Tipo          | Extends            | Dichiarata con throws | Obbligo gestione | Quando                            |
| ------------- | ------------------ | --------------------- | ---------------- | --------------------------------- |
| **Checked**   | `Exception`        | Sì, obbligatorio      | Sì               | Errori prevedibili e recuperabili |
| **Unchecked** | `RuntimeException` | Opzionale             | No               | Errori di programmazione / logica |

### Try / Catch / Finally

```java
try {
    int risultato = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Errore: " + e.getMessage());
} catch (Exception e) {
    System.out.println("Errore generico: " + e.getMessage());
} finally {
    System.out.println("Eseguito sempre (pulizia risorse)");
}
```

### Try-with-resources (Java 7+) – chiusura automatica

```java
// Le risorse che implementano AutoCloseable vengono chiuse automaticamente
try (Scanner sc = new Scanner(System.in)) {
    int n = sc.nextInt();
} catch (Exception e) {
    System.out.println(e.getMessage());
}
// sc viene chiuso automaticamente qui, anche in caso di eccezione
```

### Lanciare eccezioni

```java
public void setEta(int eta) {
    if (eta < 0) {
        throw new IllegalArgumentException("Età non può essere negativa");
    }
    this.eta = eta;
}
```

### Dichiarare eccezioni nella firma

```java
// Checked: DEVI dichiararla con throws
public double dividi(double a, double b) throws DivideByZeroException {
    if (b == 0) throw new DivideByZeroException("Divisione per zero");
    return a / b;
}

// Unchecked: la dichiarazione throws è OPZIONALE ma consigliata per chiarezza
public void setNome(String nome) throws IllegalArgumentException {
    if (nome == null) throw new IllegalArgumentException("Nome nullo");
    this.nome = nome;
}
```

### Creare eccezioni personalizzate

```java
// Eccezione CHECKED (devi gestirla con try-catch o throws)
public class LiquidsException extends Exception {      // ← extends Exception!
    public LiquidsException() {
        super();
    }
    public LiquidsException(String messaggio) {
        super(messaggio);
    }
}

// Eccezione UNCHECKED (non obbligo di gestione)
public class CapacityException extends RuntimeException {  // ← extends RuntimeException!
    public CapacityException(String messaggio) {
        super(messaggio);
    }
}
```

### ⚠️ ATTENZIONE: checked e unchecked nella simulazione Vetreria

Dalla scala di valutazione:

- `LiquidsException` → **checked** (extends `Exception`)
- `CapacityException` → **unchecked** (extends `RuntimeException`)

**Regola mnemonica dell'esame:**

- Hai sbagliato qualcosa nel codice (parametri errati, divisione per zero) → **unchecked**
- Situazione eccezionale ma prevedibile nel dominio → **checked**

### Pattern d'esame: propagazione eccezioni

```java
public class Calcolatrice {
    private double memoria;

    // Le eccezioni della divisione si propagano dal metodo div → al metodo operate
    public double operate(char op, double operando)
            throws DivideByZeroException, InputMismatchException {
        switch (op) {
            case '+': return memoria += operando;
            case '-': return memoria -= operando;
            case '*': return memoria *= operando;
            case '/':
                if (operando == 0) throw new DivideByZeroException();
                return memoria /= operando;
            default:
                throw new InputMismatchException("Operatore non valido: " + op);
        }
    }
}
```

---

## 11. Lettura Input: args, Scanner, File

Questa sezione è **cruciale per l'esame**. Ci sono 3 fonti di input da padroneggiare.

### 11.1 Argomenti da riga di comando (`args`)

```java
// Esecuzione: java Programma 400 500
public static void main(String[] args) {
    // args[0] = "400", args[1] = "500" → sono SEMPRE stringhe!

    // Convertire in numeri
    double costoCasa = Double.parseDouble(args[0]);
    double costoCibo = Double.parseDouble(args[1]);

    // Controllare se ci sono argomenti
    if (args.length == 0) {
        System.out.println("Nessun argomento fornito");
        return;
    }

    // Argomenti opzionali
    double valore = (args.length > 0) ? Double.parseDouble(args[0]) : 0;
}
```

### 11.2 Scanner – lettura da standard input

```java
import java.util.Scanner;

Scanner sc = new Scanner(System.in);
```

#### Leggere tipi specifici

```java
// Leggere un intero
System.out.print("Inserisci un numero: ");
int n = sc.nextInt();

// Leggere un double
double d = sc.nextDouble();

// Leggere una parola (fino a spazio/invio)
String parola = sc.next();

// Leggere un'intera riga (con spazi)
String riga = sc.nextLine();

// ⚠️ TRAPPOLA CLASSICA: nextInt/nextDouble NON consumano il newline!
int x = sc.nextInt();
sc.nextLine();           // ← consuma il \n residuo
String nome = sc.nextLine(); // ora funziona correttamente
```

#### Controllare se c'è input disponibile

```java
sc.hasNext()       // c'è qualcosa da leggere?
sc.hasNextInt()    // il prossimo token è un intero?
sc.hasNextDouble() // il prossimo token è un double?
sc.hasNextLine()   // c'è un'altra riga?
```

#### Pattern 1: Leggere fino a CTRL+D (EOF) – usatissimo all'esame

```java
Scanner sc = new Scanner(System.in);
ArrayList<String> righe = new ArrayList<>();

while (sc.hasNextLine()) {     // termina con CTRL+D (Linux/Mac) o CTRL+Z (Windows)
    String riga = sc.nextLine();
    righe.add(riga);
}
sc.close();
```

#### Pattern 2: Leggere coppie di valori fino a EOF

```java
// Come nell'esercizio Famiglia
Scanner sc = new Scanner(System.in);
while (sc.hasNext()) {
    double reddito = sc.nextDouble();
    int persone = sc.nextInt();
    // ... elabora
}
sc.close();
```

#### Pattern 3: Leggere fino a un carattere di stop

```java
// Come nell'esercizio CalcolatriceConMemoria
Scanner sc = new Scanner(System.in);
while (sc.hasNext()) {
    char operatore = sc.next().charAt(0);
    if (operatore == '=') break;    // condizione di stop

    double operando = sc.nextDouble();
    // ... elabora
}
sc.close();
```

#### Pattern 4: Parsing di righe formattate (TIPICO DELL'ESAME)

```java
// Input: "acqua 1000 Cuboide 10 10 9" (come nella simulazione Vetreria)
Scanner sc = new Scanner(System.in);
while (sc.hasNextLine()) {
    String riga = sc.nextLine().trim();
    if (riga.isEmpty()) continue;      // salta righe vuote

    String[] parti = riga.split("\\s+");  // split su uno o più spazi

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
                double h = Double.parseDouble(parti[3]);
                double rr = Double.parseDouble(parti[4]);
                c = new Cilindro(h, rr, liquido, quantita);
                break;
            default:
                System.out.println("Tipo sconosciuto: " + tipo);
        }
        if (c != null) vetreria.aggiungi(c);
    } catch (CapacityException e) {
        System.out.println(e.getMessage());
    }
}
sc.close();
```

#### Pattern 5: Gestione errori durante la lettura

```java
Scanner sc = new Scanner(System.in);
while (sc.hasNext()) {
    try {
        String nome = sc.next();
        int ora = sc.nextInt();
        // ... elabora
    } catch (InputMismatchException e) {
        System.out.println("Input non valido, riprova");
        sc.nextLine();   // ← svuota il buffer dell'input errato
    }
}
```

### 11.3 Scanner da stringa

```java
// Utile per testare o per parsare stringhe complesse
Scanner sc = new Scanner("Mario 30 Luigi 25");
while (sc.hasNext()) {
    String nome = sc.next();
    int eta = sc.nextInt();
    System.out.println(nome + " ha " + eta + " anni");
}
```

---

## 12. Gestione File I/O Completa

### 12.1 Leggere un file di testo (riga per riga)

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Modo classico con try-with-resources
try (BufferedReader br = new BufferedReader(new FileReader("dati.txt"))) {
    String riga;
    while ((riga = br.readLine()) != null) {
        System.out.println(riga);
    }
} catch (IOException e) {
    System.err.println("Errore lettura file: " + e.getMessage());
}
```

### 12.2 Leggere un file con Scanner

```java
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

try {
    Scanner sc = new Scanner(new File("dati.txt"));
    while (sc.hasNextLine()) {
        String riga = sc.nextLine();
        System.out.println(riga);
    }
    sc.close();
} catch (FileNotFoundException e) {
    System.err.println("File non trovato: " + e.getMessage());
}
```

### 12.3 Scrivere su un file di testo

```java
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

// Scrittura (sovrascrive il file)
try (PrintWriter pw = new PrintWriter(new FileWriter("output.txt"))) {
    pw.println("Riga 1");
    pw.println("Riga 2");
    pw.printf("Valore: %d%n", 42);
} catch (IOException e) {
    System.err.println("Errore scrittura: " + e.getMessage());
}

// Scrittura in APPEND (aggiunge in fondo)
try (PrintWriter pw = new PrintWriter(new FileWriter("output.txt", true))) {
    pw.println("Nuova riga aggiunta");
} catch (IOException e) {
    System.err.println("Errore: " + e.getMessage());
}
```

### 12.4 BufferedWriter (alternativa)

```java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
    bw.write("Prima riga");
    bw.newLine();
    bw.write("Seconda riga");
    bw.newLine();
} catch (IOException e) {
    System.err.println("Errore: " + e.getMessage());
}
```

### 12.5 java.nio.file – API moderna (Java 7+)

```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.io.IOException;

Path percorso = Path.of("dati.txt");

// Leggere TUTTE le righe in una lista
try {
    List<String> righe = Files.readAllLines(percorso);
    for (String riga : righe) {
        System.out.println(riga);
    }
} catch (IOException e) {
    e.printStackTrace();
}

// Leggere tutto come singola stringa (Java 11+)
try {
    String contenuto = Files.readString(percorso);
    System.out.println(contenuto);
} catch (IOException e) {
    e.printStackTrace();
}

// Scrivere su file (Java 11+)
try {
    Files.writeString(Path.of("output.txt"), "Contenuto del file\n");
} catch (IOException e) {
    e.printStackTrace();
}

// Scrivere una lista di righe
try {
    List<String> righe = List.of("Riga 1", "Riga 2", "Riga 3");
    Files.write(Path.of("output.txt"), righe);
} catch (IOException e) {
    e.printStackTrace();
}

// Append
try {
    Files.writeString(Path.of("output.txt"), "Nuova riga\n", StandardOpenOption.APPEND);
} catch (IOException e) {
    e.printStackTrace();
}
```

### 12.6 Operazioni su file e directory

```java
import java.nio.file.Files;
import java.nio.file.Path;

Path p = Path.of("miofile.txt");

Files.exists(p);                   // il file esiste?
Files.isDirectory(p);              // è una directory?
Files.isRegularFile(p);            // è un file regolare?
Files.size(p);                     // dimensione in byte
Files.createFile(p);               // crea file vuoto
Files.createDirectory(Path.of("nuovaDir"));  // crea directory
Files.delete(p);                   // elimina file
Files.copy(p, Path.of("copia.txt")); // copia file
Files.move(p, Path.of("nuovo.txt")); // sposta/rinomina
```

### 12.7 Esempio completo: leggere dati da file e elaborarli

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeggiDati {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Uso: java LeggiDati <file>");
            return;
        }

        ArrayList<Famiglia> famiglie = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String riga;
            while ((riga = br.readLine()) != null) {
                String[] parti = riga.trim().split("\\s+");  // split su uno o più spazi
                if (parti.length >= 2) {
                    double reddito = Double.parseDouble(parti[0]);
                    int dimensione = Integer.parseInt(parti[1]);
                    famiglie.add(new Famiglia(reddito, dimensione));
                }
            }
        } catch (IOException e) {
            System.err.println("Errore lettura file: " + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            System.err.println("Formato numerico non valido: " + e.getMessage());
            return;
        }

        System.out.println("Lette " + famiglie.size() + " famiglie da file.");
    }
}
```

### 12.8 Esempio completo: leggere e scrivere (trasformazione file)

```java
import java.io.*;

public class TrasformaFile {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Uso: java TrasformaFile <input> <output>");
            return;
        }

        try (
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            PrintWriter pw = new PrintWriter(new FileWriter(args[1]))
        ) {
            String riga;
            int numRiga = 1;
            while ((riga = br.readLine()) != null) {
                pw.println(numRiga + ": " + riga.toUpperCase());
                numRiga++;
            }
            System.out.println("Trasformazione completata. " + (numRiga - 1) + " righe scritte.");
        } catch (FileNotFoundException e) {
            System.err.println("File non trovato: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Errore I/O: " + e.getMessage());
        }
    }
}
```

### Tabella riassuntiva I/O

| Classe           | Tipo      | Uso                       | Chiusura           |
| ---------------- | --------- | ------------------------- | ------------------ |
| `Scanner`        | Lettura   | Input console/file/string | `close()`          |
| `BufferedReader` | Lettura   | File (righe)              | try-with-resources |
| `FileReader`     | Lettura   | File (caratteri)          | try-with-resources |
| `PrintWriter`    | Scrittura | File (formattato)         | try-with-resources |
| `BufferedWriter` | Scrittura | File (buffered)           | try-with-resources |
| `FileWriter`     | Scrittura | File (caratteri)          | try-with-resources |
| `Files` (nio)    | Entrambi  | Operazioni veloci         | Automatica         |

---

## 13. Iteratori e Iterable

**Fondamentale per l'esame!** La simulazione Vetreria richiede di restituire un iteratore ordinato.

### Iterable – rende un oggetto usabile nel for-each

```java
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;

public class Vetreria implements Iterable<Contenitore> {
    // OVERVIEW: collezione di contenitori di laboratorio

    // Rappresentazione
    private final ArrayList<Contenitore> contenitori;

    // AF(this): l'insieme dei contenitori {contenitori.get(0), ..., contenitori.get(size-1)}
    // RI(this): contenitori != null && per ogni i: contenitori.get(i) != null

    public Vetreria() {
        contenitori = new ArrayList<>();
    }

    /**
     * REQUIRES: -
     * MODIFIES: this
     * EFFECTS: aggiunge c alla vetreria; lancia NullPointerException se c è null
     */
    public void aggiungi(Contenitore c) {
        if (c == null) throw new NullPointerException("Contenitore nullo");
        contenitori.add(c);
    }

    /**
     * REQUIRES: -
     * MODIFIES: this
     * EFFECTS: rimuove da this e restituisce una nuova Vetreria contenente
     *          tutti i contenitori con il liquido specificato
     */
    public Vetreria estraiPerLiquido(String liquido) {
        Vetreria estratta = new Vetreria();
        Iterator<Contenitore> it = contenitori.iterator();
        while (it.hasNext()) {
            Contenitore c = it.next();
            if (c.getLiquido() != null && c.getLiquido().equals(liquido)) {
                estratta.aggiungi(c);
                it.remove();    // rimuove dalla vetreria originale
            }
        }
        return estratta;
    }

    /**
     * REQUIRES: -
     * MODIFIES: -
     * EFFECTS: restituisce un iteratore ai contenitori ordinati per capienza
     */
    @Override
    public Iterator<Contenitore> iterator() {
        // Crea una COPIA ordinata (non modifica l'ordine interno)
        ArrayList<Contenitore> ordinati = new ArrayList<>(contenitori);
        Collections.sort(ordinati);  // usa compareTo di Contenitore
        return ordinati.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Vetreria con:\n");
        for (Contenitore c : this) {  // funziona grazie a Iterable!
            sb.append("\t" + c + "\n");
        }
        return sb.toString();
    }
}
```

### Uso nel for-each

```java
Vetreria v = new Vetreria();
v.aggiungi(new Sfera(5, "acqua", 300));
v.aggiungi(new Cilindro(4, 5, "alcool", 200));

// Funziona perché Vetreria implementa Iterable<Contenitore>
for (Contenitore c : v) {
    System.out.println(c);
}
```

### Implementare un Iterator manualmente (opzionale)

```java
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RangeIterator implements Iterator<Integer> {
    private int corrente;
    private final int fine;

    public RangeIterator(int inizio, int fine) {
        this.corrente = inizio;
        this.fine = fine;
    }

    @Override
    public boolean hasNext() {
        return corrente < fine;
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        return corrente++;
    }
}
```

### Classe Iterable con Iterator interno (pattern comune)

```java
public class Range implements Iterable<Integer> {
    private final int inizio;
    private final int fine;

    public Range(int inizio, int fine) {
        this.inizio = inizio;
        this.fine = fine;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int corrente = inizio;

            @Override
            public boolean hasNext() { return corrente < fine; }

            @Override
            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                return corrente++;
            }
        };
    }
}

// Uso:
for (int n : new Range(1, 10)) {
    System.out.println(n);  // 1, 2, 3, ..., 9
}
```

---

## 14. Comparable e Comparator

### Comparable – ordinamento naturale (nella classe stessa)

```java
public class Studente implements Comparable<Studente> {
    private String nome;
    private int matricola;

    public Studente(String nome, int matricola) {
        this.nome = nome;
        this.matricola = matricola;
    }

    // Ordinamento per matricola (ordine naturale)
    @Override
    public int compareTo(Studente altro) {
        return Integer.compare(this.matricola, altro.matricola);
        // Restituisce:
        // < 0 se this < altro
        // = 0 se this == altro
        // > 0 se this > altro
    }
}

// Uso:
List<Studente> studenti = new ArrayList<>();
studenti.add(new Studente("Mario", 3));
studenti.add(new Studente("Luigi", 1));
studenti.add(new Studente("Anna", 2));
Collections.sort(studenti);   // ordina per matricola: 1, 2, 3
```

### Comparator – ordinamento personalizzato (esterno alla classe)

```java
import java.util.Comparator;

// Ordinare per nome (ordine diverso da quello naturale)
List<Studente> studenti = new ArrayList<>();
studenti.sort(Comparator.comparing(s -> s.getNome()));

// Lambda esplicita
studenti.sort((a, b) -> a.getNome().compareTo(b.getNome()));

// Per ordine inverso
studenti.sort(Comparator.comparing(Studente::getMatricola).reversed());
```

### Comparare tipi primitivi correttamente

```java
// ❌ NON fare: return this.valore - altro.valore (overflow possibile!)

// ✅ Usa i metodi compare delle wrapper classes:
Integer.compare(a, b);
Double.compare(a, b);
Long.compare(a, b);

// Per le stringhe
"abc".compareTo("def");    // negativo (abc < def)
```

---

## 15. Clonazione e Copia Difensiva

### Copia difensiva (preferita in Prog2)

```java
public class Inventario {
    private final ArrayList<String> oggetti;

    public Inventario(ArrayList<String> oggetti) {
        // Copia difensiva: NON salvare il riferimento diretto!
        this.oggetti = new ArrayList<>(oggetti);
    }

    public ArrayList<String> getOggetti() {
        // Copia difensiva: NON restituire il riferimento diretto!
        return new ArrayList<>(oggetti);
    }
}
```

### Perché la copia difensiva?

```java
// SENZA copia difensiva → BUG!
ArrayList<String> lista = new ArrayList<>(List.of("A", "B"));
Inventario inv = new Inventario(lista);  // salva il RIFERIMENTO

lista.add("C");  // modifica anche l'inventario! ← ESPONE LA REP
System.out.println(inv.getOggetti());    // [A, B, C] ← RI potenzialmente violato!

// CON copia difensiva → SICURO
// La lista interna è una copia indipendente
```

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

---

## 16. Specifiche Formali e Astrazione Procedurale

### Cos'è l'astrazione procedurale (L03)

Un'**astrazione procedurale** è un metodo visto come una "scatola nera": il chiamante conosce solo **cosa** fa (specifica), non **come** lo fa (implementazione).

Due principi fondamentali:

1. **Astrazione per parametrizzazione**: i parametri rendono il metodo generale (non lavora su dati fissi, ma su input arbitrari)
2. **Astrazione per specifica**: la specifica separa il "cosa" dal "come". Chi chiama si affida alla specifica, chi implementa può cambiare l'implementazione senza rompere i clienti.

> **Domanda filtro:** "Cosa significa astrazione per specifica?"  
> → Significa che il chiamante usa il metodo basandosi SOLO sulla sua specifica (REQUIRES/EFFECTS), senza conoscere né dipendere dall'implementazione.

### Formato delle specifiche

Ogni metodo pubblico dovrebbe avere una specifica con:

```
REQUIRES: precondizioni (cosa deve essere vero PRIMA della chiamata)
MODIFIES: cosa viene modificato (this, parametri, System.out...)
EFFECTS:  cosa fa il metodo, cosa restituisce, quali eccezioni lancia
```

### Esempi

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
 * MODIFIES: this
 * EFFECTS: divide la memoria per divisore e restituisce il risultato;
 *          lancia DivideByZeroException se divisore == 0
 */
public double dividi(double divisore) throws DivideByZeroException {
    if (divisore == 0) throw new DivideByZeroException();
    this.memoria /= divisore;
    return this.memoria;
}

/**
 * REQUIRES: -
 * MODIFIES: -
 * EFFECTS: restituisce true se la famiglia è sotto la soglia di povertà
 *          con i costi dati; lancia InputMismatchException se costoCasa o
 *          costoCibo non sono positivi
 */
public boolean sottoSogliaPoverta(double costoCasa, double costoCibo)
        throws InputMismatchException {
    if (costoCasa <= 0 || costoCibo <= 0)
        throw new InputMismatchException("Costi non positivi");
    return (costoCasa + costoCibo * dimensione) > (reddito / 2.0);
}
```

### Procedure totali vs parziali

- **Totale**: funziona per OGNI input (gestisce tutti i casi con eccezioni). Non ha REQUIRES (o REQUIRES: -).
- **Parziale**: ha dei `REQUIRES` → il chiamante deve garantire le precondizioni. Se il chiamante viola il REQUIRES, il comportamento è **indefinito** (il metodo può fare qualsiasi cosa).

```java
// Procedura PARZIALE (ha REQUIRES)
/**
 * REQUIRES: n > 0
 * EFFECTS: restituisce il fattoriale di n
 */
public static int fattoriale(int n) {
    if (n == 0) return 1;
    return n * fattoriale(n - 1);
}

// Procedura TOTALE (nessun REQUIRES, gestisce tutto)
/**
 * REQUIRES: -
 * EFFECTS: restituisce il fattoriale di n;
 *          lancia IllegalArgumentException se n < 0
 */
public static int fattoriale(int n) throws IllegalArgumentException {
    if (n < 0) throw new IllegalArgumentException("n deve essere >= 0");
    if (n == 0) return 1;
    return n * fattoriale(n - 1);
}
```

**⚠️ Perché preferire le specifiche totali?**

- Più sicure: il metodo gestisce tutti i casi
- Più robuste: nessun comportamento indefinito
- All'esame: quasi tutti i metodi sono **totali** (lanciano eccezioni per input invalidi)

### Il contratto chiamante-implementatore

La specifica è un **contratto**:

- **Chiamante** si impegna a soddisfare il REQUIRES
- **Implementatore** si impegna a garantire l'EFFECTS (se REQUIRES è soddisfatto)
- Se il chiamante viola il REQUIRES → l'implementatore non ha obblighi (comportamento indefinito)
- L'implementatore è libero di cambiare l'implementazione, purché rispetti la specifica

---

## 17. AF – Funzione di Astrazione

### Teoria (L06-L07)

La **Funzione di Astrazione** (AF) è una funzione che mappa lo **spazio di rappresentazione** (Rep) allo **spazio astratto**:

$$AF: Rep \to Astratto$$

- **Spazio di rappresentazione (Rep)**: l'insieme di tutti i possibili stati dei campi dell'oggetto
- **Spazio astratto**: l'insieme dei valori concettuali che il tipo rappresenta
- Il **dominio** dell'AF è ristretto dall'RI: solo gli stati concreti che soddisfano l'RI hanno un significato astratto

**Proprietà dell'AF:**

- **Suriettiva**: ogni valore astratto ha almeno una rappresentazione concreta
- **Non necessariamente iniettiva**: più stati concreti diversi possono rappresentare lo stesso valore astratto

```
Esempio: un Set implementato con ArrayList
Rep: [1, 2, 3]  →  AF  →  {1, 2, 3}
Rep: [3, 1, 2]  →  AF  →  {1, 2, 3}
Due stati concreti diversi, stesso valore astratto → AF NON iniettiva
```

**Perché serve l'AF?**
Per ragionare sulla **correttezza** dell'implementazione: un metodo è corretto se, applicando l'AF allo stato concreto risultante, si ottiene il valore astratto descritto dalla specifica.

### Come scrivere una buona AF

L'AF deve:

1. Descrivere **ogni campo** e il suo ruolo nel valore astratto
2. Gestire i **casi limite** (collezione vuota, null, ecc.)
3. Essere sufficientemente precisa da distinguere stati astratti diversi

### Esempi

```java
public class Pila {
    private ArrayList<Integer> elementi;

    private int top;

    // AF(this): sequenza [elementi.get(0), ..., elementi.get(top-1)]
    //           dove elementi.get(top-1) è il top della pila.
    //           Se top == 0, la pila è vuota.
}

public class Famiglia {
    private double reddito;
    private int dimensione;

    // AF(this): una famiglia composta da 'dimensione' persone
    //           con reddito complessivo 'reddito'
}

public class Contenitore {
    private String liquido;
    private double quantita;

    // AF(this): un contenitore che contiene 'quantita' unità di 'liquido'.
    //           Se liquido == null, il contenitore è vuoto.
}

public class Vetreria {
    private ArrayList<Contenitore> contenitori;

    // AF(this): l'insieme dei contenitori {contenitori.get(0), ..., contenitori.get(size-1)}
}

public class Sfera extends Contenitore {
    private double raggio;

    // AF(this): una sfera di raggio 'raggio' contenente i liquidi
    //           descritti dalla AF del supertipo Contenitore
}
```

**Regola:** l'AF deve permettere di capire chiaramente il significato astratto della rappresentazione.

### Come usare l'AF per dimostrare la correttezza

Per verificare che un metodo sia corretto:

1. **Parti** dallo stato concreto pre-metodo (che soddisfa l'RI)
2. **Applica** l'AF per ottenere il valore astratto iniziale
3. **Esegui** il metodo → ottieni nuovo stato concreto
4. **Applica** l'AF al nuovo stato concreto
5. **Verifica** che il valore astratto ottenuto corrisponda a quanto descritto dalla specifica (EFFECTS)

```
Esempio: metodo push(5) su una Pila
1. Stato concreto prima: elementi = [1, 2], top = 2
2. AF → pila astratta: [1, 2] (top = 2)
3. Esecuzione: elementi = [1, 2, 5], top = 3
4. AF → pila astratta: [1, 2, 5] (top = 5)
5. La specifica dice "aggiunge l'elemento in cima" → ✅ corretto
```

---

## 18. RI – Invariante di Rappresentazione

### Teoria (L06-L07)

L'**Invariante di Rappresentazione** è un predicato booleano sullo stato concreto:

$$RI: Rep \to boolean$$

- Definisce il **dominio dell'AF**: solo gli stati concreti per cui $RI = true$ hanno un significato astratto
- Se l'RI è violato, l'oggetto è in uno stato **corrotto** e l'AF non è definita
- L'RI è il **cuore della correttezza**: se ogni metodo preserva l'RI, il tipo è corretto

**Ragionamento induttivo sulla preservazione dell'RI:**

1. I **costruttori** devono stabilire l'RI (base dell'induzione)
2. I **mutatori** devono preservare l'RI (passo induttivo): se l'RI vale prima, deve valere anche dopo
3. Gli **osservatori** non modificano lo stato → l'RI è automaticamente preservato
4. La **rep exposure** deve essere impedita (altrimenti altri possono violare l'RI dall'esterno)

### Esempi

```java
public class Pila {
    private ArrayList<Integer> elementi;
    private int top;

    // RI(this): elementi != null
    //           && 0 <= top <= elementi.size()
    //           && per ogni i in [0, top): elementi.get(i) != null

    // Metodo per verificare l'RI (opzionale ma utile per debug)
    private boolean repOk() {
        if (elementi == null) return false;
        if (top < 0 || top > elementi.size()) return false;
        for (int i = 0; i < top; i++) {
            if (elementi.get(i) == null) return false;
        }
        return true;
    }
}

public class Famiglia {
    private double reddito;
    private int dimensione;

    // RI(this): dimensione > 0
}

public class Contenitore {
    private String liquido;
    private double quantita;

    // RI(this): quantita >= 0
    //           && quantita <= calcolaVolume()
    //           && (liquido == null) == (quantita == 0)
}

public class Sfera extends Contenitore {
    private double raggio;

    // RI(this): raggio > 0 && RI del supertipo è soddisfatto
}

public class Vetreria {
    private ArrayList<Contenitore> contenitori;

    // RI(this): contenitori != null
    //           && per ogni i: contenitori.get(i) != null
}
```

### Come garantire il RI

1. Verificare nel **costruttore** (lanciando eccezioni se violato)
2. Verificare nei **mutatori** (setter, add, remove...)
3. NON esporre la rappresentazione direttamente (copia difensiva)

### `repOk()` – Quando chiamarlo

```java
private boolean repOk() {
    // verifica tutte le condizioni dell'RI
    return condizione1 && condizione2 && ...;
}
```

**Quando si usa:**

- **In debug/testing**: `assert repOk()` alla fine di ogni costruttore e mutatore
- **NON in produzione**: i controlli costano tempo → si disabilitano con `-da` (disable assertions)
- **All'esame**: scrivilo se richiesto, ma non chiamarlo nei metodi (a meno che non sia chiesto)

### Rep Exposure – Le 4 cause e come prevenirle

| Causa                                           | Esempio                      | Soluzione                              |
| ----------------------------------------------- | ---------------------------- | -------------------------------------- |
| Getter che restituisce riferimento              | `return elementi;`           | `return new ArrayList<>(elementi);`    |
| Costruttore che salva riferimento               | `this.lista = lista;`        | `this.lista = new ArrayList<>(lista);` |
| Metodo che restituisce oggetto mutabile interno | `return contenitori.get(i);` | Restituire copia o rendere immutabile  |
| Campo non private                               | `public List<String> nomi;`  | Rendere `private`                      |

### Beneficenza (Benevolent Side Effects) – L06

Un **effetto collaterale benevolo** è una modifica allo stato concreto che **non cambia il valore astratto** (l'AF restituisce lo stesso risultato).

```java
public class IntSet {
    private List<Integer> elements;
    private Integer cachedSize = null;  // cache

    public int size() {
        if (cachedSize == null) {
            cachedSize = elements.size();  // effetto collaterale benevolo!
        }
        return cachedSize;
    }
    // Lo stato concreto cambia (cachedSize), ma l'AF è la stessa
    // → è un benevolent side effect, perfettamente legittimo
}
```

> **Domanda filtro:** "Un metodo osservatore può modificare lo stato concreto?"  
> → Sì, se è un **benevolent side effect** (non cambia il valore astratto).

---

## 19. Progettazione ADT Completa

### Template completo per una classe d'esame

```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

/**
 * OVERVIEW: modella una [DESCRIZIONE] {mutabile/immutabile}
 */
public class NomeClasse implements Comparable<NomeClasse>, Iterable<Elemento> {
    // ⚠️ NOTA: all'esame Comparable va sulla CLASSE ASTRATTA (es. Contenitore)
    //          mentre Iterable va sulla COLLEZIONE (es. Vetreria)
    //          Qui sono uniti in un unico template di riferimento.

    // ===== RAPPRESENTAZIONE =====
    // Campi PRIVATI (visibilità rep)
    private final ArrayList<Elemento> elementi;    // final se campo immutabile
    private int contatore;

    // AF(this): [descrizione dello stato astratto in termini dei campi]
    // RI(this): [condizioni che DEVONO sempre valere sulla rappresentazione]

    // ===== COSTRUTTORE =====
    /**
     * REQUIRES: -
     * MODIFIES: this
     * EFFECTS: inizializza un nuovo NomeClasse [descrizione];
     *          lancia IllegalArgumentException se [condizione]
     */
    public NomeClasse(/* parametri */) throws IllegalArgumentException {
        // Verifico precondizioni (per garantire RI)
        // Inizializzo i campi
        this.elementi = new ArrayList<>();
        this.contatore = 0;
    }

    // ===== METODI (osservatori, produttori, mutatori) =====

    /**
     * REQUIRES: -
     * MODIFIES: this
     * EFFECTS: aggiunge elem alla collezione;
     *          lancia NullPointerException se elem è null
     */
    public void aggiungi(Elemento elem) {
        if (elem == null) throw new NullPointerException("Elemento nullo");
        elementi.add(elem);
        contatore++;
    }

    /**
     * REQUIRES: -
     * MODIFIES: -
     * EFFECTS: restituisce il numero di elementi
     */
    public int getContatore() {
        return contatore;
    }

    // ===== compareTo =====
    @Override
    public int compareTo(NomeClasse altro) {
        return Integer.compare(this.contatore, altro.contatore);
    }

    // ===== iterator =====
    @Override
    public Iterator<Elemento> iterator() {
        ArrayList<Elemento> copia = new ArrayList<>(elementi);
        Collections.sort(copia);
        return copia.iterator();
    }

    // ===== toString =====
    @Override
    public String toString() {
        return "NomeClasse con " + contatore + " elementi";
    }

    // ===== equals + hashCode (se necessario) =====
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NomeClasse)) return false;
        NomeClasse nc = (NomeClasse) o;
        return this.elementi.equals(nc.elementi);
    }

    @Override
    public int hashCode() {
        return elementi.hashCode();
    }

    // ===== repOk (opzionale, per debug) =====
    private boolean repOk() {
        if (elementi == null) return false;
        if (contatore != elementi.size()) return false;
        for (Elemento e : elementi) {
            if (e == null) return false;
        }
        return true;
    }
}
```

---

## 20. Pattern d'Esame e Checklist

### Checklist pre-consegna (dalla scala di valutazione)

Per **ogni classe** verifica i seguenti punti (con i punti assegnati dalla scala):

#### Classe astratta (es. Contenitore)

- [ ] Dichiarata `abstract` (0.5 pt)
- [ ] `OVERVIEW` presente (0.5 pt)
- [ ] Rappresentazione corretta (1.5 pt)
  - [ ] Visibilità: campi `private` (0.5 pt)
  - [ ] Mutabilità: `final` dove appropriato (0.5 pt)
  - [ ] Tipi appropriati (0.5 pt)
- [ ] Costruttore con specifica e controlli (1 pt)
- [ ] Metodo `versa()` / metodo principale (1.5 pt)
  - [ ] Specifica (0.5 pt)
  - [ ] Controlli (0.5 pt)
  - [ ] Implementazione (0.5 pt)
- [ ] `compareTo` corretto (1 pt)
  - [ ] `implements Comparable` + signature (0.5 pt)
  - [ ] Implementazione (0.5 pt)
- [ ] `clone()` (0.5 pt)
- [ ] AF scritta (0.5 pt)
- [ ] RI scritto (0.5 pt)

#### Sottoclassi (es. Sfera, Cilindro, Cuboide) — 4 pt ciascuna

- [ ] `extends` corretto (0.5 pt)
- [ ] `OVERVIEW` (0.5 pt)
- [ ] Rappresentazione corretta (1.5 pt)
- [ ] Costruttore con specifica e controlli (1 pt)
- [ ] AF (0.5 pt)

#### Collezione (es. Vetreria) — 8.5 pt

- [ ] `OVERVIEW` (0.5 pt)
- [ ] Rappresentazione corretta (1 pt)
- [ ] Costruttore/inizializzazione (0.5 pt)
- [ ] Metodo `aggiungi()` con specifica e implementazione (1 pt)
- [ ] Metodo `estrai()` con specifica, controlli e implementazione (1.5 pt)
- [ ] Metodo `ottimizza()` / altro metodo richiesto (1.5 pt)
- [ ] `iterator()` corretto (1.5 pt)
  - [ ] `implements Iterable` + signature corretta (0.5 pt)
  - [ ] Ordinamento (sort) (0.5 pt)
  - [ ] Restituisce iterator (0.5 pt)
- [ ] AF (0.5 pt)
- [ ] RI (0.5 pt)

#### Eccezioni — 2 pt

- [ ] Checked corretta (extends Exception) (1 pt)
- [ ] Unchecked corretta (extends RuntimeException) (1 pt)

#### Main — 2 pt

- [ ] Scanner per lettura standard input (0.5 pt)
- [ ] Splitting/parsing della riga (0.5 pt)
- [ ] Dispatching (switch/if per tipo) (0.5 pt)
- [ ] Try-catch per errori (0.5 pt)

---

### Pattern tipico del main dell'esame

```java
public static void main(String[] args) {
    // 1. Inizializzazione strutture dati
    Vetreria vetreria = new Vetreria();

    // 2. Lettura da standard input
    Scanner sc = new Scanner(System.in);
    while (sc.hasNextLine()) {
        String riga = sc.nextLine().trim();
        if (riga.isEmpty()) continue;

        String[] parti = riga.split("\\s+");   // splitting

        try {
            String liquido = parti[0];
            double quantita = Double.parseDouble(parti[1]);
            String tipo = parti[2];

            Contenitore c = null;

            // 3. Dispatching per tipo
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
                    double h = Double.parseDouble(parti[3]);
                    double rr = Double.parseDouble(parti[4]);
                    c = new Cilindro(h, rr, liquido, quantita);
                    break;
            }

            if (c != null) vetreria.aggiungi(c);

        } catch (CapacityException e) {
            // 4. Try-catch per gestire errori
            System.out.println(e.getMessage());
        }
    }
    sc.close();

    // 5. Output risultati
    System.out.println(vetreria);

    // 6. Estrarre per liquido e stampare
    // (raccogliere prima i tipi di liquido in un Set per evitare duplicati)
    Set<String> liquidi = new HashSet<>();
    for (Contenitore c : vetreria) {
        if (c.getLiquido() != null) liquidi.add(c.getLiquido());
    }

    for (String liquido : liquidi) {
        Vetreria estratta = vetreria.estraiPerLiquido(liquido);
        System.out.println(estratta);
    }
}
```

### Pattern: Estrarre sottoinsiemi da una collezione

```java
public Vetreria estraiPerLiquido(String liquido) {
    Vetreria estratta = new Vetreria();
    Iterator<Contenitore> it = contenitori.iterator();
    while (it.hasNext()) {
        Contenitore c = it.next();
        if (c.getLiquido() != null && c.getLiquido().equals(liquido)) {
            estratta.aggiungi(c);
            it.remove();     // rimuove dall'originale
        }
    }
    return estratta;
}
```

### Pattern: Gerarchia di classi (come nella simulazione)

```
                   Contenitore (abstract)
                   implements Comparable, Cloneable
                  /          |          \
              Sfera      Cilindro     Cuboide
```

Ogni sottoclasse:

1. `extends Contenitore`
2. Ha la propria rappresentazione (raggio, altezza, lati...)
3. Implementa `calcolaVolume()`
4. Ha il proprio `toString()`
5. Ha OVERVIEW, AF, RI, costruttore con specifica

### Pattern: Metodo `ottimizza` (1.5 punti nella Vetreria)

Il metodo `ottimizza` della Vetreria combina contenitori con **lo stesso liquido** per minimizzare il numero di contenitori usati. La strategia: versa il liquido dai più piccoli nei più grandi.

```java
/**
 * MODIFIES: this
 * EFFECTS: combina i contenitori con lo stesso liquido versando
 *          dai più piccoli ai più grandi, rimuovendo i contenitori svuotati.
 *          Alla fine, per ogni liquido, si hanno il minor numero
 *          possibile di contenitori.
 */
public void ottimizza() {
    // Raggruppa per liquido
    Map<String, List<Contenitore>> perLiquido = new HashMap<>();
    for (Contenitore c : contenitori) {
        if (c.getLiquido() != null) {
            perLiquido.computeIfAbsent(c.getLiquido(), k -> new ArrayList<>()).add(c);
        }
    }

    for (List<Contenitore> gruppo : perLiquido.values()) {
        // Ordina per capienza DECRESCENTE (i più grandi prima)
        gruppo.sort((a, b) -> Double.compare(b.getCapienza(), a.getCapienza()));

        // Versa dai più piccoli nei più grandi
        for (int i = 0; i < gruppo.size(); i++) {
            Contenitore destinazione = gruppo.get(i);
            for (int j = i + 1; j < gruppo.size(); j++) {
                Contenitore sorgente = gruppo.get(j);
                if (sorgente.getQuantita() <= 0) continue;

                double spazioLibero = destinazione.getCapienza() - destinazione.getQuantita();
                double daVersare = Math.min(spazioLibero, sorgente.getQuantita());

                if (daVersare > 0) {
                    destinazione.setQuantita(destinazione.getQuantita() + daVersare);
                    sorgente.setQuantita(sorgente.getQuantita() - daVersare);
                }
            }
        }
    }

    // Rimuovi contenitori svuotati
    contenitori.removeIf(c -> c.getQuantita() <= 0);
}
```

> **Nota:** Il metodo `ottimizza` è menzionato nella scala di valutazione (1.5 punti) ma **non** nel README della simulazione. Probabilmente viene dato come traccia aggiuntiva il giorno dell'esame. Il principio è: **raggruppare per liquido, versare dai piccoli nei grandi, rimuovere i vuoti**.

---

### Errori comuni da evitare

| Errore                                         | Correzione                                             |
| ---------------------------------------------- | ------------------------------------------------------ |
| Campi `public`                                 | Usare `private` SEMPRE                                 |
| Confrontare stringhe con `==`                  | Usare `.equals()`                                      |
| Non chiudere Scanner/file                      | Usare try-with-resources                               |
| `nextInt()` seguito da `nextLine()`            | Aggiungere `sc.nextLine()` dopo `nextInt()`            |
| RI non verificato nel costruttore              | Aggiungere controlli + eccezioni                       |
| Restituire riferimento a campo mutabile        | Copia difensiva nel getter                             |
| Dimenticare `@Override`                        | Annotare sempre i metodi sovrascritti                  |
| `throws` su eccezione unchecked senza motivo   | `throws` è obbligatorio solo per checked               |
| Non implementare `compareTo` per l'ordinamento | `implements Comparable<T>` + `compareTo`               |
| `return a - b` nel compareTo                   | Usare `Integer.compare(a, b)` o `Double.compare(a, b)` |
| Rimuovere da lista durante for-each            | Usare `Iterator.remove()`                              |
| Dimenticare `super()` nel costruttore figlio   | Il costruttore del padre è obbligatorio                |
| AF/RI mancanti                                 | Scriverli SEMPRE come commento nei campi               |

---

## 21. Novità Java 21

### Switch con pattern matching

```java
static String formatta(Object obj) {
    return switch (obj) {
        case Integer i -> "Intero: " + i;
        case String s  -> "Stringa: " + s;
        case Double d  -> "Double: " + d;
        case null      -> "null";
        default        -> "Tipo sconosciuto";
    };
}
```

### Record patterns

```java
record Punto(int x, int y) {}

static void stampa(Object obj) {
    if (obj instanceof Punto(int x, int y)) {
        System.out.println("x=" + x + ", y=" + y);
    }
}
```

### Sealed classes (classi sigillate)

```java
public sealed class Forma permits Cerchio, Rettangolo, Triangolo {}

public final class Cerchio extends Forma { }
public final class Rettangolo extends Forma { }
public non-sealed class Triangolo extends Forma { }
```

### Sequenced Collections (Java 21)

```java
List<String> lista = new ArrayList<>(List.of("a", "b", "c"));

lista.getFirst();     // "a"
lista.getLast();      // "c"
lista.reversed();     // vista inversa: ["c", "b", "a"]

lista.addFirst("z");
lista.addLast("w");
```

### Text Blocks (Java 15+)

```java
String json = """
        {
            "nome": "Mario",
            "eta": 30
        }
        """;
```

### `var` – inferenza di tipo (Java 10+)

```java
var nome = "Mario";                    // String
var numeri = new ArrayList<Integer>(); // ArrayList<Integer>
var mappa = new HashMap<String, List<Integer>>();

// ⚠️ Usare con moderazione: il tipo deve essere ovvio dal contesto
```

---

## 22. Cheat Sheet Rapido

### Conversioni veloci

```java
String → int:      Integer.parseInt("42")
String → double:   Double.parseDouble("3.14")
int → String:      String.valueOf(42)  oppure  "" + 42
char → int:        (int) 'A'  → 65
int → char:        (char) 65  → 'A'
Array → List:      Arrays.asList(array)    // lista fissa
List → Array:      list.toArray(new String[0])
```

### Strutture dati – quando usarle

```
Devo memorizzare una sequenza ordinata?         → ArrayList
Devo garantire unicità?                         → HashSet
Devo associare chiave → valore?                 → HashMap
Devo ordinare gli elementi?                     → TreeSet / Collections.sort()
Devo contare occorrenze?                        → HashMap<K, Integer>
Devo raggruppare per categoria?                 → HashMap<K, List<V>>
```

### Template eccezione personalizzata

```java
// Checked
public class MiaEccezione extends Exception {
    public MiaEccezione(String msg) { super(msg); }
}

// Unchecked
public class MiaEccezione extends RuntimeException {
    public MiaEccezione(String msg) { super(msg); }
}
```

### Template lettura standard input (fino a EOF)

```java
Scanner sc = new Scanner(System.in);
while (sc.hasNextLine()) {
    String riga = sc.nextLine();
    String[] parti = riga.split("\\s+");
    // ... elabora
}
sc.close();
```

### Template lettura file

```java
try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {
    String riga;
    while ((riga = br.readLine()) != null) {
        // ... elabora riga
    }
} catch (IOException e) {
    System.err.println(e.getMessage());
}
```

### Template scrittura file

```java
try (PrintWriter pw = new PrintWriter(new FileWriter(nomeFile))) {
    pw.println("contenuto");
} catch (IOException e) {
    System.err.println(e.getMessage());
}
```

### Template toString, equals, hashCode

```java
@Override
public String toString() {
    return campo1 + " - " + campo2;
}

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof MiaClasse)) return false;
    MiaClasse mc = (MiaClasse) o;
    return this.campo1.equals(mc.campo1) && this.campo2 == mc.campo2;
}

@Override
public int hashCode() {
    return campo1.hashCode() * 31 + campo2;
}
```

### Come vengono CHIAMATI nel Main/Test

| Metodo        | Come si attiva                                                          | Esempio nel Main               |
| ------------- | ----------------------------------------------------------------------- | ------------------------------ |
| `toString()`  | **Automatico** da `println` e `+`                                       | `System.out.println(oggetto);` |
| `equals()`    | **Esplicito** con `.equals()`, oppure interno a `contains()`/`remove()` | `if (a.equals(b))`             |
| `compareTo()` | **Automatico** da `Collections.sort()` dentro `iterator()`              | `Collections.sort(lista);`     |
| `hashCode()`  | **Mai diretto** — usato da `HashSet`/`HashMap` internamente             | `set.add(obj);`                |

```java
// toString() → NON devi chiamarlo, lo chiama println:
System.out.println(sfera);         // chiama sfera.toString()
System.out.println("Info: " + s);  // anche la concatenazione con + lo chiama
System.out.println(vetreria);      // chiama vetreria.toString() → che stampa ogni contenitore

// equals() → lo chiami tu ESPLICITAMENTE:
if (c1.equals(g1)) { ... }         // usa l'equals del padre (confronto per nome)
// Ma anche list.contains(obj), list.remove(obj) lo usano internamente

// compareTo() → NON lo chiami, lo usa Collections.sort():
@Override
public Iterator<Sorpresa> iterator() {
    ArrayList<Sorpresa> copia = new ArrayList<>(/* ... */);
    Collections.sort(copia);       // ← chiama compareTo() automaticamente!
    return copia.iterator();
}

// hashCode() → MAI chiamato direttamente, lo usano HashSet/HashMap internamente
```

### Comandi terminale

```bash
javac MiaClasse.java              # compila
java MiaClasse                     # esegue
java MiaClasse arg1 arg2           # con argomenti
javac -encoding UTF-8 *.java       # compila tutti i file con encoding UTF-8
javac *.java && java Main          # compila tutto ed esegue Main
```

### Imports più usati

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Collections;
import java.util.InputMismatchException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
```

---

## 23. 🔴 TEORIA – Filtro 30 Domande

Il filtro teorico è la prima parte dell'esame: **30 domande** a cui devi rispondere correttamente per accedere alla parte pratica. Ecco **tutti gli argomenti** che possono uscire, con risposte pronte.

---

### 23.1 ADT – Tipi di Dato Astratti

**Cos'è un ADT?**  
Un tipo di dato definito dal suo **comportamento** (operazioni), non dalla sua implementazione. L'utente conosce solo l'interfaccia, non come è fatto dentro.

**Categorie di operazioni di un ADT:**

| Tipo            | Descrizione                                         | Esempio                         |
| --------------- | --------------------------------------------------- | ------------------------------- |
| **Creatori**    | Creano nuovi oggetti del tipo                       | Costruttori                     |
| **Produttori**  | Creano nuovi oggetti a partire da oggetti esistenti | `trasla()` in Punto immutabile  |
| **Mutatori**    | Modificano lo stato dell'oggetto                    | `add()`, `set()`                |
| **Osservatori** | Restituiscono info senza modificare                 | `get()`, `size()`, `toString()` |

**Tipo mutabile vs immutabile:**

- **Mutabile**: ha mutatori, lo stato cambia dopo la creazione
- **Immutabile**: nessun mutatore, stato fissato nel costruttore, metodi restituiscono nuovi oggetti

**Vantaggi dell'immutabilità:**

- Thread-safe (nessuna race condition)
- Sicuri come chiavi di Map o in Set
- Nessun problema di aliasing
- Più facili da ragionare

**Mutabilità e problemi:**

- **Aliasing**: due variabili che puntano allo stesso oggetto mutabile → una modifica è visibile da entrambe
- **Sharing**: passare oggetti mutabili a più parti del codice → modifiche inattese
- Relazione con l'RI: se un oggetto mutabile viene condiviso, l'RI può essere violato dall'esterno (rep exposure)

```java
List<Integer> originale = new ArrayList<>(List.of(1, 2, 3));
List<Integer> alias = originale;  // aliasing!
alias.add(4);  // modifica visibile anche tramite 'originale'!
```

**Adeguatezza di un ADT:**

Un ADT è **adeguato** se fornisce operatori sufficienti per eseguire tutte le operazioni ragionevoli sui valori astratti. In pratica: deve essere possibile ottenere, creare e manipolare ogni valore astratto attraverso gli operatori forniti.

> **Domanda filtro:** "Cosa significa che un ADT è adeguato?"
> → Ha operatori sufficienti per raggiungere tutti i valori astratti e per osservare tutte le proprietà rilevanti.

---

### 23.2 Specifiche: Precondizioni e Postcondizioni

**Precondizione (REQUIRES):** cosa deve essere vero PRIMA della chiamata.  
**Postcondizione (EFFECTS):** cosa garantisce il metodo SE le precondizioni sono soddisfatte.

**Forza di una specifica:**

- Una specifica è **più forte** se ha precondizioni **più deboli** (accetta più input) E postcondizioni **più forti** (garantisce di più)
- Una specifica è **più debole** nel caso opposto

```
Specifica A: REQUIRES: x > 0   EFFECTS: restituisce x * 2
Specifica B: REQUIRES: x != 0  EFFECTS: restituisce x * 2 e stampa il risultato

B è PIÙ FORTE di A perché:
- precondizione più debole (accetta anche x < 0)
- postcondizione più forte (fa di più: stampa)
```

**Principio di sostituzione (Liskov):**  
Una sottoclasse può **rafforzare** la postcondizione e **indebolire** la precondizione, MAI il contrario.

```
✅ Sottotipo può: REQUIRES più debole, EFFECTS più forte
❌ Sottotipo NON può: REQUIRES più forte, EFFECTS più debole
```

**Procedura totale vs parziale:**

- **Totale**: definita per ogni input (nessun REQUIRES, gestisce tutto con eccezioni)
- **Parziale**: ha REQUIRES → comportamento **indefinito** se violato (non puoi contare su nulla)

**Specifica deterministica vs non deterministica:**

- **Deterministica**: per ogni input, l'output è unico e predicibile
- **Non deterministica**: la specifica ammette più output possibili (es. "restituisce un elemento dell'insieme")

---

### 23.3 Funzione di Astrazione (AF) – Domande tipiche

**Cos'è l'AF?**  
Una funzione che mappa lo **stato concreto** (campi dell'oggetto) allo **stato astratto** (cosa rappresenta concettualmente).

**L'AF è suriettiva?**  
**Sì**, sempre. Ogni valore astratto ha almeno una rappresentazione concreta.

**L'AF è iniettiva?**  
**Non necessariamente**. Più stati concreti diversi possono rappresentare lo stesso valore astratto.

```
Esempio: un Set implementato con ArrayList
[1, 2, 3] e [3, 1, 2] → stesso insieme astratto {1, 2, 3}
Due stati concreti diversi, stesso valore astratto → AF NON iniettiva
```

**Perché serve l'AF?**  
Per poter ragionare sulla correttezza: verificare che le operazioni preservino il significato astratto.

---

### 23.4 Invariante di Rappresentazione (RI) – Domande tipiche

**Cos'è l'RI?**  
Una condizione booleana che DEVE essere vera per OGNI stato concreto valido. Se l'RI è falso, l'oggetto è **corrotto**.

**Dove va verificato?**

- Nel **costruttore** (alla fine)
- In ogni **mutatore** (alla fine)
- NON negli osservatori (non cambiano lo stato)

**Rep exposure (esposizione della rappresentazione):**  
Quando il codice esterno può accedere e modificare lo stato interno, violando l'RI.

```java
// ❌ REP EXPOSURE!
public class Pila {
    private ArrayList<Integer> elementi;
    public ArrayList<Integer> getElementi() {
        return elementi;  // restituisce il RIFERIMENTO diretto!
    }
}
// Un utente esterno può fare: pila.getElementi().clear() → RI violato!

// ✅ CORRETTO: copia difensiva
public ArrayList<Integer> getElementi() {
    return new ArrayList<>(elementi);  // restituisce una COPIA
}
```

**Cause comuni di rep exposure:**

1. Getter che restituisce riferimento a campo mutabile
2. Costruttore che salva riferimento diretto al parametro
3. Campi non `private`
4. Campi mutabili non protetti da `final` o copia difensiva

---

### 23.5 equals e hashCode – Contratto

**Regole del contratto equals:**

1. **Riflessivo**: `x.equals(x)` → true
2. **Simmetrico**: `x.equals(y)` ↔ `y.equals(x)`
3. **Transitivo**: se `x.equals(y)` e `y.equals(z)` → `x.equals(z)`
4. **Consistente**: chiamate multiple danno lo stesso risultato
5. `x.equals(null)` → **sempre false**

**Regola fondamentale equals/hashCode:**

> Se `a.equals(b)` è true → `a.hashCode() == b.hashCode()` DEVE essere true.  
> Il contrario NON è obbligatorio (due oggetti diversi possono avere lo stesso hashCode).

**⚠️ Se ridefinisci equals DEVI ridefinire hashCode**, altrimenti HashMap/HashSet non funzionano.

**Domanda trabocchetto: uguaglianza tra tipi diversi nella gerarchia**

Dagli esami reali: due oggetti di tipo diverso (es. `Cioccolatino` e `Giocattolo`) possono essere `equals` se condividono lo stesso campo discriminante (es. il `nome`).

```java
// Dall'esame Dicembre: Sorpresa — equals basato sul NOME
Cioccolatino c = new Cioccolatino("Yorick", 1);   // costo 0.1
Giocattolo g = new Giocattolo("Yorick", "...");   // costo 3.0
c.equals(g);  // TRUE! Stesso nome → sono "uguali"

// Dall'esame Gennaio: Evento — equals basato sul NOME
Gara g = new Gara("Slittino1", 15);
Cerimonia c = new Cerimonia("Slittino1", false);
c.equals(g);  // TRUE!
```

**Come implementare equals nella gerarchia (nella classe padre):**

```java
public abstract class Sorpresa {
    private String nome;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sorpresa)) return false;  // ← usa il tipo PADRE
        Sorpresa s = (Sorpresa) o;
        return this.nome.equals(s.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();  // coerente con equals
    }
}
```

---

### 23.6 Comparable – Contratto

**Regole:**

1. `sgn(x.compareTo(y)) == -sgn(y.compareTo(x))` → antisimmetria
2. Transitivo
3. Se `x.compareTo(y) == 0` → `sgn(x.compareTo(z)) == sgn(y.compareTo(z))`
4. **Raccomandato** (non obbligatorio): coerenza con equals → `x.compareTo(y) == 0` ↔ `x.equals(y)`

**⚠️ Pattern d'esame: compareTo e equals INCOERENTI**

Dagli esami: `equals` confronta il **nome**, `compareTo` confronta il **costo/durata**. Quindi:

```java
Cioccolatino c1 = new Cioccolatino("Lindt", 25);   // costo 2.5
Giocattolo g1 = new Giocattolo("Yorick", "di infinita allegria"); // costo 3.0
c1.compareTo(g1);  // < 0 (2.5 < 3.0) → Lindt è "minore" di Yorick
c1.equals(g1);     // false (nomi diversi)

// Ma: Cioccolatino "Yorick" ed Giocattolo "Yorick"
// equals: TRUE (stesso nome)
// compareTo: potrebbe essere != 0 (costi diversi)
// → equals e compareTo INCOERENTI (ma è lecito!)
```

---

### 23.7 Ereditarietà e Polimorfismo – Teoria

**Tipo statico vs tipo dinamico:**

```java
Animale a = new Cane("Rex");
// Tipo STATICO: Animale (dichiarato)
// Tipo DINAMICO: Cane (creato con new)
// Il metodo chiamato dipende dal tipo DINAMICO (late binding)
```

**Regole di override (sovrascrittura):**

1. Stessa signature (nome + parametri)
2. Tipo di ritorno uguale o **covariante** (sottotipo)
3. Visibilità uguale o **più ampia** (private → protected → public)
4. Non può lanciare eccezioni checked **nuove o più ampie**
5. Annotazione `@Override` (consigliata, non obbligatoria)

**Override vs Overload:**

|             | Override                 | Overload                    |
| ----------- | ------------------------ | --------------------------- |
| Signature   | Stessa                   | Diversa (parametri diversi) |
| Classe      | Figlio sovrascrive padre | Stessa classe o figlio      |
| Binding     | Dinamico (a runtime)     | Statico (a compile-time)    |
| `@Override` | Sì                       | No                          |

**Principio di Liskov (LSP) – Le 3 regole formali:**

> Dove usi un oggetto del tipo padre, puoi usare un oggetto del tipo figlio **senza cambiare il comportamento atteso**.

Il LSP si articola in **3 regole**:

**1. Regola della signature:**

- Il sottotipo deve avere tutti i metodi del supertipo, con la stessa signature
- In Java: garantita dal compilatore (override richiede stessa signature)
- Eccezione: tipo di ritorno **covariante** (sottotipo del ritorno del padre)

**2. Regola dei metodi (pre/postcondizioni):**

- Il sottotipo può **indebolire** le precondizioni (accettare di più)
- Il sottotipo può **rafforzare** le postcondizioni (garantire di più)
- MAI il contrario

```
Padre: REQUIRES: x > 0    EFFECTS: restituisce risultato > 0
Figlio: REQUIRES: x != 0   EFFECTS: restituisce risultato > 10
                ↑ più debole (OK)          ↑ più forte (OK)

Padre: REQUIRES: x != 0   EFFECTS: restituisce risultato > 0
Figlio: REQUIRES: x > 0    EFFECTS: restituisce risultato >= 0
               ↑ più forte (❌ VIOLA LSP!)     ↑ più debole (❌ VIOLA LSP!)
```

**3. Regola delle proprietà (invarianti):**

- Il sottotipo deve preservare tutti gli **invarianti** del supertipo
- Le proprietà di **evoluzione** devono essere preservate (es. se il padre è immutabile, il figlio deve esserlo)

**Esempio concreto di violazione LSP:**

```java
// Viola LSP: Quadrato come figlio di Rettangolo
public class Rettangolo {
    protected int larghezza, altezza;
    public void setLarghezza(int l) { larghezza = l; }
    public void setAltezza(int a) { altezza = a; }
    public int area() { return larghezza * altezza; }
}

public class Quadrato extends Rettangolo {
    @Override
    public void setLarghezza(int l) { larghezza = l; altezza = l; } // forza quadrato
    @Override
    public void setAltezza(int a) { larghezza = a; altezza = a; }
}

// Codice che si aspetta un Rettangolo:
Rettangolo r = new Quadrato();
r.setLarghezza(5);
r.setAltezza(3);
r.area();  // Mi aspetto 15, ottengo 9! → LSP violato!
```

**Dispatch dinamico (late binding):**

Quando si invoca un metodo su un riferimento, Java cerca il metodo partendo dal **tipo dinamico** (la classe effettiva dell'oggetto) e risale la gerarchia.

```java
Contenitore c = new Sfera("Mappamondo", 5.0);
System.out.println(c.toString());
// 1. Tipo statico: Contenitore
// 2. Tipo dinamico: Sfera
// 3. Java cerca toString() in Sfera → se non c'è, risale a Contenitore
// 4. Contenitore.toString() chiama calcolaVolume() → dispatch dinamico!
//    → calcolaVolume() di SFERA viene chiamato (non quello astratto)
```

**⚠️ Overloading è risolto STATICAMENTE (a compile-time), override DINAMICAMENTE (a runtime).**

```java
void metodo(Contenitore c) { System.out.println("Contenitore"); }
void metodo(Sfera s)       { System.out.println("Sfera"); }

Contenitore c = new Sfera("X", 1);
metodo(c);  // Stampa "Contenitore"! (overloading → tipo STATICO decide)
c.toString();  // Chiama Sfera.toString()! (override → tipo DINAMICO decide)
```

---

### 23.8 Classi Astratte vs Interfacce – Confronto Teorico

| Domanda                    | Classe Astratta                      | Interfaccia                 |
| -------------------------- | ------------------------------------ | --------------------------- |
| Può avere stato (campi)?   | ✅ Sì, qualsiasi                     | ❌ Solo `static final`      |
| Può avere costruttori?     | ✅ Sì                                | ❌ No                       |
| Ereditarietà multipla?     | ❌ Solo singola                      | ✅ Multipla                 |
| Può avere metodi concreti? | ✅ Sì                                | ✅ Solo `default` (Java 8+) |
| Può essere istanziata?     | ❌ No                                | ❌ No                       |
| Quando usarla?             | Modello base con **stato condiviso** | **Contratto** / capacità    |

**Domanda tipica: "Perché Contenitore/Sorpresa/Evento è abstract e non un'interfaccia?"**  
Perché ha **stato** (campi come `nome`, `liquido`, `quantita`) e **logica condivisa** (metodi concreti come `versa`, `equals`, `compareTo`). Un'interfaccia non può avere campi d'istanza.

---

### 23.9 Eccezioni – Teoria

**Checked vs Unchecked: quando usare quale?**

| Situazione            | Tipo              | Perché                             |
| --------------------- | ----------------- | ---------------------------------- |
| File non trovato      | Checked           | Errore esterno, recuperabile       |
| Parametro nullo       | Unchecked         | Bug nel codice chiamante           |
| Divisione per zero    | Checked/Unchecked | Dipende dal contesto               |
| Indice fuori range    | Unchecked         | Bug nel codice                     |
| Liquidi incompatibili | Checked           | Situazione prevedibile nel dominio |
| Capienza superata     | Unchecked         | Errore di parametri del chiamante  |
| Giorno già occupato   | Checked           | Situazione di dominio recuperabile |
| Evento già esistente  | Unchecked         | Errore logico del chiamante        |

**Regole di propagazione:**

- Checked: DEVI gestirla (`try-catch`) o dichiararla (`throws`)
- Unchecked: si propaga automaticamente, `throws` opzionale
- Un metodo che chiama un metodo con checked exception DEVE gestirla o rilanciarla

**Finally:**

- Eseguito SEMPRE, anche se c'è un `return` nel try o nel catch
- Usato per chiusura risorse (meglio: try-with-resources)

**Multi-catch (Java 7+):**

```java
try {
    // ...
} catch (IOException | NumberFormatException e) {
    System.out.println(e.getMessage());
}
```

---

### 23.10 Generics – Teoria completa

**Classe generica:**

```java
public class Coppia<T, U> {
    private T primo;
    private U secondo;
    public Coppia(T primo, U secondo) {
        this.primo = primo;
        this.secondo = secondo;
    }
}
Coppia<String, Integer> c = new Coppia<>("Mario", 30);
```

**Bounded type parameters:**

```java
// T deve essere Comparable (o sottotipo)
public static <T extends Comparable<T>> T max(List<T> list) {
    T result = list.get(0);
    for (T elem : list) {
        if (elem.compareTo(result) > 0) result = elem;
    }
    return result;
}

// Bounded multipli: T deve implementare sia Comparable che Serializable
public static <T extends Comparable<T> & Serializable> void metodo(T elem) { ... }
// NB: extends si usa anche per interfacce nei bounded; la classe va PRIMA
```

**Wildcards:**

```java
List<?>                // qualsiasi tipo (solo lettura, non puoi aggiungere)
List<? extends Number> // Number o sottotipi (lettura sicura, scrittura NO)
List<? super Integer>  // Integer o supertipi (scrittura sicura, lettura come Object)
```

**PECS: Producer Extends, Consumer Super**

- Se **leggi** dalla collezione (la collezione **produce** valori) → `? extends T`
- Se **scrivi** nella collezione (la collezione **consuma** valori) → `? super T`
- Se devi sia leggere che scrivere → nessun wildcard, usa tipo esatto

```java
// Esempio concreto PECS:
public static <T> void copia(List<? extends T> src, List<? super T> dest) {
    for (T elem : src) {   // src produce → extends
        dest.add(elem);     // dest consuma → super
    }
}
```

**Covarianza, Controvarianza, Invarianza:**

| Concetto           | Significato                                             | Esempio                                                               |
| ------------------ | ------------------------------------------------------- | --------------------------------------------------------------------- |
| **Covarianza**     | Se `B extends A`, allora `F<B>` è sottotipo di `F<A>`   | Array: `String[]` è sottotipo di `Object[]` ✅                        |
| **Controvarianza** | Se `B extends A`, allora `F<A>` è sottotipo di `F<B>`   | `Comparator<Object>` può essere usato dove serve `Comparator<String>` |
| **Invarianza**     | `F<B>` NON è sottotipo di `F<A>` anche se `B extends A` | `List<String>` NON è sottotipo di `List<Object>` ❌                   |

**I generics sono INVARIANTI, gli array sono COVARIANTI.**

```java
// Array covarianti → compila ma può crashare a runtime!
Object[] arr = new String[3];
arr[0] = 42;  // ❌ ArrayStoreException a runtime!

// Generics invarianti → il compilatore ti protegge
List<Object> lista = new ArrayList<String>();  // ❌ NON COMPILA!

// Wildcards forniscono varianza use-site:
List<? extends Number> covar = new ArrayList<Integer>();  // ✅ covarianza
List<? super Integer> contravar = new ArrayList<Number>(); // ✅ controvarianza
```

**Perché i generics sono invarianti?** Per sicurezza dei tipi. Se `List<String>` fosse sottotipo di `List<Object>`, potresti fare:

```java
List<String> ls = new ArrayList<>();
List<Object> lo = ls;     // se fosse permesso...
lo.add(42);               // aggiungi Integer in una lista di String!
String s = ls.get(0);     // ClassCastException!
```

**Type erasure – dettagli:**

A compilazione, il compilatore:

1. **Sostituisce T** con il suo bound (o `Object` se unbounded)
2. **Inserisce cast** automatici dove necessario
3. **Genera bridge methods** per preservare il polimorfismo

```java
// Codice sorgente:
public class Box<T extends Number> {
    private T valore;
    public T get() { return valore; }
}
// Dopo erasure diventa:
public class Box {
    private Number valore;         // T → Number (bound)
    public Number get() { return valore; }
}

// Quando usi Box<Integer>:
Box<Integer> b = new Box<>();
Integer i = b.get();  // il compilatore inserisce: (Integer) b.get()
```

**Limitazioni dei generics (da sapere per il filtro):**

| Non puoi fare                | Perché                                    |
| ---------------------------- | ----------------------------------------- |
| `new T()`                    | T non esiste a runtime                    |
| `new T[10]`                  | non si possono creare array generici      |
| `x instanceof T`             | T cancellato a runtime                    |
| `T.class`                    | T non ha un Class object                  |
| `List<int>` (tipi primitivi) | i generics supportano solo Object         |
| `throw new T()`              | le eccezioni non possono essere generiche |
| `static T campo`             | T è per-istanza, static è per-classe      |

### 23.10.1 Polimorfismo – Tassonomia

Tre forme di polimorfismo in Java:

| Tipo                       | Meccanismo                            | Esempio                                |
| -------------------------- | ------------------------------------- | -------------------------------------- |
| **Sottotipo** (inclusione) | Ereditarietà + dispatch dinamico      | `Contenitore c = new Sfera(...)`       |
| **Parametrico**            | Generics                              | `List<T>`, `<T extends Comparable<T>>` |
| **Ad-hoc** (overloading)   | Metodi con stessa nome, diversa firma | `print(int)`, `print(String)`          |

- **Sottotipo**: risolto a **runtime** (late binding / dispatch dinamico)
- **Parametrico**: risolto a **compile-time** (type erasure)
- **Ad-hoc**: risolto a **compile-time** (il compilatore sceglie il metodo)

---

### 23.11 Collections Framework – Teoria

**Domande comuni:**

| Domanda                               | Risposta                                                                              |
| ------------------------------------- | ------------------------------------------------------------------------------------- |
| ArrayList vs LinkedList?              | ArrayList: accesso O(1), inserimento O(n). LinkedList: accesso O(n), inserimento O(1) |
| HashSet vs TreeSet?                   | HashSet: O(1) non ordinato. TreeSet: O(log n) ordinato                                |
| HashMap vs TreeMap?                   | HashMap: O(1) non ordinato. TreeMap: O(log n) chiavi ordinate                         |
| Cosa serve per usare TreeSet/TreeMap? | Elementi devono essere `Comparable` o passare un `Comparator`                         |
| Cosa serve per usare HashSet/HashMap? | Implementare correttamente `equals` e `hashCode`                                      |
| `Collections.unmodifiableList()`      | Restituisce una vista non modificabile (ma l'originale può ancora cambiare!)          |
| `List.of()`, `Set.of()`, `Map.of()`   | Restituiscono collezioni **veramente** immutabili (Java 9+)                           |

**Iterazione e modifica:**

```java
// ❌ ConcurrentModificationException
for (String s : lista) {
    lista.remove(s);
}

// ✅ Usa Iterator.remove()
Iterator<String> it = lista.iterator();
while (it.hasNext()) {
    if (condizione(it.next())) it.remove();
}
```

---

### 23.12 `Object` – Metodi fondamentali

| Metodo           | Default                       | Quando ridefinire               |
| ---------------- | ----------------------------- | ------------------------------- |
| `toString()`     | `NomeClasse@hashCode`         | Sempre (per debugging/stampa)   |
| `equals(Object)` | Confronto riferimento (`==`)  | Quando serve uguaglianza logica |
| `hashCode()`     | Basato sull'indirizzo         | Quando ridefinisci equals       |
| `clone()`        | Shallow copy                  | Quando serve copia              |
| `getClass()`     | Restituisce la classe runtime | Raramente                       |

---

### 23.13 Modificatori – Teoria

**`final`:**

- Su variabile → costante, non riassegnabile
- Su metodo → non sovrascrivibile
- Su classe → non estendibile
- Su campo riferimento → il riferimento non cambia, MA l'oggetto puntato SÌ!

```java
final ArrayList<String> lista = new ArrayList<>();
lista.add("ciao");     // ✅ ok! Modifichi l'oggetto, non il riferimento
lista = new ArrayList<>();  // ❌ errore! Non puoi riassegnare
```

**`static`:**

- Su campo → condiviso tra TUTTE le istanze (uno solo in memoria)
- Su metodo → si chiama sulla classe, non sull'oggetto (non ha `this`)
- Su classe interna → non ha riferimento all'istanza esterna

**`abstract`:**

- Su metodo → nessun corpo, le sottoclassi DEVONO implementarlo
- Su classe → non istanziabile, può contenere metodi astratti

**`protected`:**

- Accessibile dalla stessa classe, stesso package e dalle sottoclassi
- Tipico per i costruttori delle classi astratte

---

### 23.14 Domande Trabocchetto Comuni

**1. `==` vs `.equals()` per le stringhe:**

```java
String a = new String("ciao");
String b = new String("ciao");
a == b;        // false (oggetti diversi in heap)
a.equals(b);   // true (stesso contenuto)

String c = "ciao";
String d = "ciao";
c == d;        // true (stesso literal nel string pool) – MA NON AFFIDABILE
```

**2. Passaggio parametri in Java:**

- Java passa SEMPRE per **valore**
- Per i primitivi: copia del valore
- Per gli oggetti: copia del **riferimento** (NON dell'oggetto)

```java
void modifica(ArrayList<String> lista) {
    lista.add("nuovo");  // ✅ modifica l'oggetto originale (stesso riferimento)
    lista = new ArrayList<>();  // ❌ NON cambia il riferimento del chiamante
}
```

**3. Autoboxing e null:**

```java
Integer x = null;
int y = x;    // ❌ NullPointerException! (unboxing di null)
```

**4. Array di tipi generici:**

```java
List<String>[] array = new List<String>[10];  // ❌ NON si può!
List<String>[] array = new List[10];           // ⚠️ warning, ma compila
```

**5. `instanceof` con null:**

```java
null instanceof String;   // false (mai true per null)
```

**6. Override di equals con tipo sbagliato:**

```java
// ❌ SBAGLIATO: questo è OVERLOAD, non OVERRIDE!
public boolean equals(Persona p) { ... }

// ✅ CORRETTO: il parametro deve essere Object
@Override
public boolean equals(Object o) { ... }
```

**7. Immutabilità di String:**

```java
String s = "ciao";
s.toUpperCase();           // NON modifica s!
System.out.println(s);     // "ciao" (invariata)

s = s.toUpperCase();       // ORA s = "CIAO" (nuovo oggetto)
```

**8. Costruttore della sottoclasse:**

```java
class Padre {
    Padre(int x) { }
}
class Figlio extends Padre {
    Figlio() {
        // ❌ ERRORE: non esiste Padre() senza argomenti!
        // Devi chiamare: super(valore);
    }
}
```

**9. `this()` e `super()` nel costruttore:**

- DEVONO essere la **prima istruzione**
- Non puoi usarli entrambi nello stesso costruttore

**10. Classe astratta con tutti i metodi concreti:**

```java
abstract class Esempio {
    void metodo() { }   // metodo concreto
}
// È lecito! Una classe abstract SENZA metodi abstract
// NON può essere istanziata comunque (è il punto di abstract)
```

---

### 23.15 Quiz di Auto-Valutazione (30 domande tipo esame)

Provale **senza guardare le risposte** sopra:

1. Qual è la differenza tra tipo statico e tipo dinamico?
2. Se `a.equals(b)` è true, `a.hashCode() == b.hashCode()` è sempre true?
3. Se `a.hashCode() == b.hashCode()`, `a.equals(b)` è sempre true?
4. Cos'è la rep exposure? Fai un esempio.
5. L'AF è iniettiva? È suriettiva? Perché?
6. Che differenza c'è tra eccezione checked e unchecked?
7. Cosa succede se fai `return` dentro un `try` con un `finally`?
8. Quando DEVI ridefinire `hashCode()`?
9. Qual è la differenza tra `Comparable` e `Comparator`?
10. Puoi creare un array di tipo generico in Java? Perché?
11. Cos'è il principio di sostituzione di Liskov?
12. Una sottoclasse può avere precondizioni più forti del padre?
13. Cosa stampa: `new String("a") == new String("a")`?
14. Java è pass-by-value o pass-by-reference?
15. `final ArrayList<String> l = new ArrayList<>();` – puoi fare `l.add("x")`?
16. Che differenza c'è tra `List.of()` e `Collections.unmodifiableList()`?
17. Cosa succede se chiami `remove()` durante un for-each?
18. Puoi avere una classe abstract senza metodi abstract?
19. Cos'è il type erasure dei generics?
20. `Integer x = null; int y = x;` – cosa succede?
21. Qual è la differenza tra override e overload?
22. Quando usi `protected` in un costruttore?
23. Cosa significa che `compareTo` e `equals` sono "incoerenti"?
24. Perché `Contenitore` è una classe astratta e non un'interfaccia?
25. Cos'è una copia difensiva e quando serve?
26. Cosa vuol dire che un metodo è una procedura totale?
27. Cosa stampa `"ciao".toUpperCase()` senza assegnamento?
28. Puoi usare `this()` e `super()` insieme in un costruttore?
29. Qual è la differenza tra `ArrayList` e `LinkedList` in termini di complessità?
30. In una gerarchia, `equals` va implementato nel padre o nel figlio? Perché?

---

## 24. 🔴 Analisi Esami Reali (Simulazione + 2 Appelli)

Questa sezione analizza la **struttura comune** dei 3 esami disponibili per farti capire lo schema che il prof ripete.

---

### 24.1 Confronto strutturale dei 3 esami

| Elemento                    | Simulazione (Vetreria)     | Dicembre (Calendario)    | Gennaio (Olimpiadi)     |
| --------------------------- | -------------------------- | ------------------------ | ----------------------- |
| **Classe astratta**         | `Contenitore`              | `Sorpresa`               | `Evento`                |
| **Sottoclasse 1**           | `Sfera`                    | `Cioccolatino`           | `Cerimonia`             |
| **Sottoclasse 2**           | `Cilindro`                 | `Giocattolo`             | `Gara`                  |
| **Sottoclasse 3**           | `Cuboide`                  | —                        | —                       |
| **Collezione**              | `Vetreria`                 | `Calendario`             | `Olimpiade`             |
| **equals basato su**        | _(non esplicito)_          | `nome`                   | `nome`                  |
| **compareTo basato su**     | `capienza`                 | `costo`                  | `durata`                |
| **Eccezione checked**       | `LiquidsException`         | `GiornoException`        | `GiornoException`       |
| **Eccezione unchecked**     | `CapacityException`        | `SorpresaException`      | `EventoException`       |
| **Struttura interna coll.** | `ArrayList`                | Array fisso (31 caselle) | Array fisso (19 giorni) |
| **Operazione inserimento**  | `aggiungi`                 | `inserisci`              | `aggiungi/inserisci`    |
| **Operazione rimozione**    | `estrai` (per liquido)     | `apri` (per giorno)      | `rimuovi` (per giorno)  |
| **Operazione extra**        | `ottimizza`                | —                        | —                       |
| **Iterator**                | Ordinato per capienza      | Ordinato per costo       | Ordinato per durata     |
| **Main lettura**            | stdin split                | stdin split              | args + stdin split      |
| **Main dispatching**        | switch su tipo contenitore | switch su tipo sorpresa  | switch su tipo evento   |

---

### 24.2 Lo SCHEMA che si ripete SEMPRE

```
1. CLASSE ASTRATTA (padre della gerarchia)
   - nome (String) → usato per equals
   - metodo astratto che calcola un valore → usato per compareTo
   - implements Comparable<T>
   - equals + hashCode basati sul nome
   - compareTo basato sul valore calcolato
   - toString()
   - AF, RI, OVERVIEW

2. SOTTOCLASSI (2-3 sottoclassi concrete)
   - extends padre
   - campi aggiuntivi specifici
   - implementano il metodo astratto del padre
   - proprio toString()
   - AF, OVERVIEW

3. COLLEZIONE (contiene oggetti del padre)
   - implements Iterable<T>
   - ArrayList o Array interno
   - aggiungi/inserisci con controlli + eccezioni
   - rimuovi/estrai con controlli + eccezioni
   - metodo extra (ottimizza, costo totale, ecc.)
   - iterator() ordinato (usa Collections.sort)
   - toString(), AF, RI

4. ECCEZIONI (2)
   - 1 checked (extends Exception) → errore di dominio recuperabile
   - 1 unchecked (extends RuntimeException) → errore logico

5. MAIN / TEST
   - Lettura da stdin con Scanner
   - Split della riga
   - Switch/if per dispatching tipo
   - Try-catch per gestire eccezioni
   - Stampa risultato
```

---

### 24.3 Esame Dicembre: Calendario delle Sorprese – Analisi

**Gerarchia:**

```
              Sorpresa (abstract)
              implements Comparable<Sorpresa>
             /                    \
      Cioccolatino              Giocattolo
      (% cacao)                 (descrizione)
      costo = cacao/10          costo = n. parole
```

**Punti chiave:**

- `equals` basato sul **nome** → un Cioccolatino "Yorick" è uguale a un Giocattolo "Yorick"
- `compareTo` basato sul **costo** → incoerente con equals (è lecito)
- `Calendario` ha array fisso di 31 caselle (non ArrayList!)
- `inserisci` lancia `GiornoException` (checked) se giorno occupato, `SorpresaException` (unchecked) se sorpresa già presente
- `apri` restituisce e rimuove la sorpresa
- Il costo del calendario è la somma dei costi
- Iterator ordinato per costo (ordine naturale di Sorpresa)

**Main (Test):**

```java
// Parsing:
// "inserisci 4 Cioccolatino Venchi 35"
// "inserisci 13 Giocattolo Trippy Gioia e Mistero" ← descrizione multi-parola!
// "apri 4"

// Attenzione: per Giocattolo la descrizione può essere MULTI-PAROLA
// → devi prendere tutto da parti[4] in poi e unirlo
```

**Implementazione Calendario:**

```java
public class Calendario implements Iterable<Sorpresa> {
    // OVERVIEW: calendario delle sorprese con 31 caselle

    private Sorpresa[] caselle;  // Array di 31 elementi (indice 0 = giorno 1)

    // AF(this): calendario dove caselle[i] è la sorpresa del giorno i+1,
    //           null se il giorno è vuoto
    // RI(this): caselle != null && caselle.length == 31

    public Calendario() {
        caselle = new Sorpresa[31];
    }

    public void inserisci(int giorno, Sorpresa s)
            throws GiornoException {  // SorpresaException è unchecked, throws non obbligatorio
        if (giorno < 1 || giorno > 31)
            throw new IllegalArgumentException("Giorno non valido");
        if (caselle[giorno - 1] != null)
            throw new GiornoException("Una sorpresa già presente per il giorno");
        // Controlla se la stessa sorpresa è già nel calendario
        for (Sorpresa presente : caselle) {
            if (presente != null && presente.equals(s))
                throw new SorpresaException("Questa sorpresa già presente nel calendario");
        }
        caselle[giorno - 1] = s;
    }

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
        ArrayList<Sorpresa> ordinate = new ArrayList<>();
        for (Sorpresa s : caselle) {
            if (s != null) ordinate.add(s);
        }
        Collections.sort(ordinate);  // ordina per costo (ordine naturale)
        return ordinate.iterator();
    }

    @Override
    public String toString() {
        // ⚠️ toString() può avere un ordine DIVERSO dall'iterator()
        // Es. in Olimpiade: toString() per giorno, iterator() per durata
        StringBuilder sb = new StringBuilder("Calendario:\n");
        for (int i = 0; i < caselle.length; i++) {
            if (caselle[i] != null) sb.append("\tGiorno " + (i + 1) + ": " + caselle[i] + "\n");
        }
        return sb.toString();
    }
}
```

---

### 24.4 Esame Gennaio: Olimpiadi Invernali – Analisi

**Gerarchia:**

```
              Evento (abstract)
              implements Comparable<Evento>
             /                    \
        Cerimonia                  Gara
        (apertura/chiusura)       (numAtleti)
        durata = 60 fisso         durata = 5 * atleti
```

**Punti chiave:**

- `equals` basato sul **nome** (anche qui Cerimonia e Gara possono essere equals)
- `compareTo` basato sulla **durata**
- `Olimpiade` ha array di 19 giorni + anno
- Regola speciale: Cerimonia di apertura SOLO giorno 1, chiusura SOLO giorno 19
- `toString()` dell'Olimpiade mostra gli eventi **in ordine di giorno** (non di durata!)
- `iterator()` restituisce gli eventi in ordine di **durata** (ordine naturale)
- ⚠️ **toString() e iterator() hanno ordini DIVERSI!** Questo è un trabocchetto comune
- Args da riga di comando: `java Test 2026` → l'anno

**Differenze rispetto a Dicembre:**

- Ha `rimuovi` invece di `apri` (non restituisce la sorpresa)
- Logica extra: cerimonia apertura/chiusura con vincoli di giorno
- `toString()` e `iterator()` producono ordini DIVERSI (giorno vs durata)

**Main (Test):**

```java
public static void main(String[] args) {
    int anno = Integer.parseInt(args[0]);   // ← da riga di comando!
    Olimpiade olimpiade = new Olimpiade(anno);

    Scanner sc = new Scanner(System.in);
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

    // Stampa olimpiade (ordine per giorno → usa toString)
    System.out.println(olimpiade);

    // Stampa eventi per ordine naturale (durata → usa iterator)
    System.out.println("Eventi in ordine di durata:");
    for (Evento e : olimpiade) {
        System.out.println("\t" + e);
    }
}
```

---

### 24.5 Come prepararsi: ricetta d'esame

**Prima dell'esame (teoria):**

1. Studia le 30 domande della sezione 23.15
2. Assicurati di sapere: equals/hashCode contratto, checked vs unchecked, AF/RI, rep exposure, LSP, override vs overload
3. Rispondi al quiz senza guardare le risposte

**Prima dell'esame (pratica):**

1. Riscrivi la simulazione Vetreria da zero senza guardare nulla
2. Riscrivi l'esame di Dicembre (Calendario) da zero senza guardare nulla
3. Riscrivi l'esame di Gennaio (Olimpiadi) da zero senza guardare nulla

**Lo schema da memorizzare:**

```
┌─────────────────────┐
│  Classe Astratta    │  abstract, Comparable, equals(nome), compareTo(valore)
│  + metodo astratto  │  AF, RI, OVERVIEW, costruttore protected
└────────┬────────────┘
         │ extends
    ┌────┴────┐
    │ Sotto1  │  Sotto2   → implementano metodo astratto, toString, AF
    └─────────┘

┌─────────────────────┐
│  Collezione         │  Iterable, iterator ordinato per ordine naturale
│  + aggiungi/estrai  │  AF, RI, OVERVIEW, eccezioni nei metodi
└─────────────────────┘

┌──────────────────┐
│  2 Eccezioni     │  1 checked (extends Exception), 1 unchecked (RuntimeException)
└──────────────────┘

┌──────────────────┐
│  Main / Test     │  Scanner + split + switch + try-catch
└──────────────────┘
```

**Tempo consigliato:**
| Parte | Tempo |
|-------|-------|
| Filtro teoria (30 domande) | 15-20 min |
| Classe astratta + eccezioni | 30 min |
| Sottoclassi (2-3) | 20 min |
| Collezione + iterator | 30 min |
| Main/Test | 15 min |
| Rilettura + AF/RI/OVERVIEW | 15 min |

> **Consiglio finale:** Se riesci a scrivere tutti e 3 gli esami da zero senza guardare nulla, **hai il 30**. ✅

---

## 25. Classi Nested (Interne)

> 📌 **Slide L12** – Argomento che può uscire nel filtro teoria.

Java permette di definire classi dentro altre classi. Ci sono 4 tipi:

### 25.1 Classe Interna (Inner Class – non statica)

Ha accesso a **tutti** i membri dell'istanza esterna, compresi quelli `private`.

```java
public class Contenitore {
    private int valore = 42;

    // Classe interna: legata a un'istanza di Contenitore
    public class Elemento {
        public int getValore() {
            return valore;  // ✅ accede al campo dell'istanza esterna
        }
    }
}

// Uso: serve un'ISTANZA della classe esterna
Contenitore c = new Contenitore();
Contenitore.Elemento e = c.new Elemento();
```

### 25.2 Classe Nested Statica (Static Nested Class)

NON ha accesso all'istanza esterna. È essenzialmente una classe top-level "nascosta" nel namespace.

```java
public class Vetreria {
    private List<Contenitore> contenitori = new ArrayList<>();

    // Classe nested statica: NON ha riferimento a Vetreria
    public static class Coppia {
        private final String nome;
        private final double volume;

        public Coppia(String nome, double volume) {
            this.nome = nome;
            this.volume = volume;
        }

        public String getNome() { return nome; }
        public double getVolume() { return volume; }
    }
}

// Uso: NON serve un'istanza della classe esterna
Vetreria.Coppia coppia = new Vetreria.Coppia("Cuboide", 100.0);
```

### 25.3 Classe Locale (Local Class)

Definita **dentro un metodo**. Rara, ma può uscire nel filtro.

```java
public Iterator<Contenitore> iterator() {
    // Classe locale: visibile solo in questo metodo
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

### 25.4 Classe Anonima (Anonymous Class)

Classe **senza nome**, creata al volo. Usata spesso con `Iterator`.

```java
public Iterator<Contenitore> iterator() {
    return new Iterator<Contenitore>() {     // ← classe anonima
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

### 25.5 Confronto rapido

| Tipo          | Contesto            | Accesso a istanza esterna             | Uso tipico                           |
| ------------- | ------------------- | ------------------------------------- | ------------------------------------ |
| Inner class   | Membro della classe | ✅ Sì                                 | Quando serve accesso ai campi        |
| Static nested | Membro della classe | ❌ No                                 | Classi helper, `Coppia`, record-like |
| Locale        | Dentro un metodo    | ✅ Sì (+ variabili effectively final) | Iterator                             |
| Anonima       | Espressione         | ✅ Sì (+ variabili effectively final) | Iterator, Comparator                 |

**⚠️ Per il filtro teoria:**

- Inner class → ha riferimento implicito a `this` della classe esterna
- Static nested → **non** ha riferimento all'istanza esterna
- Anonime e locali → possono accedere solo a variabili locali **effectively final**
- `static` su classe interna → la rende nested statica (non ha accesso ai campi dell'istanza)

---

## 26. Programmazione Funzionale

> 📌 **Slide L15** – Lambdas, interfacce funzionali, streams, method references.

### 26.1 Interfacce Funzionali

Un'interfaccia con **un solo metodo astratto** (SAM – Single Abstract Method). Si può usare con lambdas.

```java
// Interfacce funzionali principali (pacchetto java.util.function):
Predicate<T>      // T → boolean     (test)
Function<T, R>    // T → R           (trasformazione)
Consumer<T>       // T → void        (azione)
Supplier<T>       // () → T          (produttore)
UnaryOperator<T>  // T → T           (caso speciale di Function)
BinaryOperator<T> // (T, T) → T
Comparator<T>     // (T, T) → int    (confronto)
```

### 26.2 Lambda Expressions

Sintassi compatta per implementare interfacce funzionali:

```java
// Sintassi: (parametri) -> espressione  oppure  (parametri) -> { blocco }

// Comparator con classe anonima (vecchio stile)
Comparator<String> comp1 = new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return a.length() - b.length();
    }
};

// Equivalente con lambda
Comparator<String> comp2 = (a, b) -> a.length() - b.length();

// Lambda con blocco
Comparator<String> comp3 = (a, b) -> {
    if (a.length() != b.length()) return a.length() - b.length();
    return a.compareTo(b);
};

// Predicate
Predicate<Integer> positivo = n -> n > 0;
System.out.println(positivo.test(5));   // true

// Consumer
Consumer<String> stampa = s -> System.out.println(s);
stampa.accept("Ciao");

// Function
Function<String, Integer> lunghezza = s -> s.length();
System.out.println(lunghezza.apply("Java"));  // 4
```

### 26.3 Method References (::)

Scorciatoia per lambdas che chiamano un singolo metodo:

```java
// 4 tipi di method reference:

// 1. Riferimento a metodo statico:       Classe::metodoStatico
Function<String, Integer> f1 = Integer::parseInt;       // s -> Integer.parseInt(s)

// 2. Riferimento a metodo di istanza:    oggetto::metodo
String str = "ciao";
Supplier<Integer> f2 = str::length;                     // () -> str.length()

// 3. Riferimento a metodo di tipo:       Classe::metodo
Function<String, String> f3 = String::toUpperCase;      // s -> s.toUpperCase()

// 4. Riferimento a costruttore:          Classe::new
Supplier<ArrayList<String>> f4 = ArrayList::new;        // () -> new ArrayList<>()
```

### 26.4 Streams

Pipeline di operazioni su collezioni. **Non modificano** la collezione originale.

```java
List<String> nomi = List.of("Alice", "Bob", "Anna", "Carlo", "Ada");

// Pipeline: sorgente → operazioni intermedie → operazione terminale
List<String> risultato = nomi.stream()
    .filter(n -> n.startsWith("A"))     // intermedia: filtra
    .map(String::toUpperCase)            // intermedia: trasforma
    .sorted()                            // intermedia: ordina
    .collect(Collectors.toList());       // terminale: raccogli
// risultato = ["ADA", "ALICE", "ANNA"]

// Operazioni intermedie comuni:
// .filter(Predicate)  → filtra elementi
// .map(Function)      → trasforma ogni elemento
// .sorted()           → ordina (naturale o con Comparator)
// .distinct()         → rimuove duplicati
// .limit(n)           → prende solo i primi n
// .skip(n)            → salta i primi n

// Operazioni terminali comuni:
// .collect(Collectors.toList())  → raccoglie in una lista
// .forEach(Consumer)             → esegue azione su ogni elemento
// .count()                       → conta gli elementi
// .reduce(identity, BinaryOp)    → riduce a un singolo valore
// .findFirst()                   → Optional del primo elemento
// .anyMatch(Predicate)           → true se almeno uno matcha
// .allMatch(Predicate)           → true se tutti matchano
// .min(Comparator) / .max(Comparator) → Optional del min/max
```

**Esempi pratici (stile esame):**

```java
// Somma dei volumi dei contenitori con volume > 100
double somma = contenitori.stream()
    .mapToDouble(Contenitore::calcolaVolume)
    .filter(v -> v > 100)
    .sum();

// Raggruppare per tipo
Map<String, List<Contenitore>> perTipo = contenitori.stream()
    .collect(Collectors.groupingBy(c -> c.getClass().getSimpleName()));

// Trovare il contenitore con volume massimo
Optional<Contenitore> max = contenitori.stream()
    .max(Comparator.comparingDouble(Contenitore::calcolaVolume));
```

### 26.5 Optional

Wrapper che può contenere o meno un valore. Evita `NullPointerException`.

```java
Optional<String> opt1 = Optional.of("ciao");       // contiene "ciao"
Optional<String> opt2 = Optional.empty();           // vuoto
Optional<String> opt3 = Optional.ofNullable(null);  // vuoto (non lancia eccezione)

// Uso sicuro
opt1.isPresent();              // true
opt1.get();                    // "ciao" (⚠️ lancia eccezione se vuoto!)
opt1.orElse("default");       // "ciao" (o "default" se vuoto)
opt1.ifPresent(System.out::println);  // stampa "ciao"
```

---

## 27. Design Pattern

> 📌 **Slide L16** – Pattern classici che possono uscire nel filtro teoria.

### 27.1 Iterator Pattern

**Già usato in tutta la guida.** Separa la logica di attraversamento dalla collezione.

```java
// La collezione implementa Iterable<T>
public class Vetreria implements Iterable<Contenitore> {
    @Override
    public Iterator<Contenitore> iterator() {
        return new Iterator<Contenitore>() { /* ... */ };
    }
}

// Uso trasparente
for (Contenitore c : vetreria) { /* ... */ }
```

### 27.2 Template Method Pattern

La superclasse definisce lo **scheletro** dell'algoritmo, le sottoclassi personalizzano i passi.

```java
// Lo usiamo OGNI VOLTA nell'esame con la classe astratta!
public abstract class Contenitore {
    private final String nome;

    // Template method: toString usa calcolaVolume() che è astratto
    @Override
    public String toString() {
        return nome + ": " + calcolaVolume();  // chiama il metodo astratto
    }

    // Il "passo" personalizzabile dalle sottoclassi
    public abstract double calcolaVolume();
}

public class Cuboide extends Contenitore {
    @Override
    public double calcolaVolume() {
        return lunghezza * larghezza * altezza;  // implementazione specifica
    }
}
```

> **All'esame:** `toString()` nella classe astratta che chiama `calcolaVolume()` è un Template Method.

### 27.3 Strategy Pattern

Definisce una famiglia di algoritmi intercambiabili. In Java: **Comparator è una Strategy**.

```java
// La "strategia" è il criterio di ordinamento
List<Contenitore> lista = new ArrayList<>(vetreria.getContenitori());

// Strategia 1: ordina per volume
lista.sort(Comparator.comparingDouble(Contenitore::calcolaVolume));

// Strategia 2: ordina per nome
lista.sort(Comparator.comparing(Contenitore::getNome));

// Strategia 3: ordina per volume decrescente
lista.sort(Comparator.comparingDouble(Contenitore::calcolaVolume).reversed());
```

### 27.4 Factory Method Pattern

Un metodo che crea oggetti senza esporre la logica di costruzione.

```java
// Lo usiamo nel Main quando parsifichiamo l'input!
public static Contenitore creaContenitore(String tipo, String nome, String[] params) {
    return switch (tipo) {
        case "Cuboide" -> new Cuboide(nome,
            Double.parseDouble(params[0]),
            Double.parseDouble(params[1]),
            Double.parseDouble(params[2]));
        case "Sfera" -> new Sfera(nome, Double.parseDouble(params[0]));
        case "Cilindro" -> new Cilindro(nome,
            Double.parseDouble(params[0]),
            Double.parseDouble(params[1]));
        default -> throw new IllegalArgumentException("Tipo sconosciuto: " + tipo);
    };
}
```

### 27.5 Observer Pattern (cenni)

Un oggetto (subject) notifica automaticamente i suoi osservatori quando cambia stato.

```java
// Esempio concettuale (viene chiesto nella teoria)
public interface Observer {
    void update(String evento);
}

public class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) { observers.add(o); }
    public void removeObserver(Observer o) { observers.remove(o); }

    protected void notifyObservers(String evento) {
        for (Observer o : observers) {
            o.update(evento);
        }
    }
}
```

### 27.6 Decorator Pattern (cenni)

Aggiunge funzionalità a un oggetto **senza modificare** la classe originale. Wrapper che delega al componente interno.

```java
// Esempio: InputStream → BufferedInputStream → DataInputStream
// Ogni "strato" aggiunge funzionalità

// Struttura base:
public abstract class ContenitoreDecorator extends Contenitore {
    protected Contenitore wrapped;

    public ContenitoreDecorator(Contenitore wrapped) {
        super(wrapped.getNome());
        this.wrapped = wrapped;
    }

    @Override
    public double calcolaVolume() {
        return wrapped.calcolaVolume();  // delega
    }
}
```

### 27.7 Riepilogo Pattern per il filtro teoria

| Pattern             | Dove lo usi all'esame                              | Concetto chiave                               |
| ------------------- | -------------------------------------------------- | --------------------------------------------- |
| **Iterator**        | `Iterable<T>` + `Iterator<T>`                      | Separa attraversamento da struttura           |
| **Template Method** | `toString()` astratto che chiama `calcolaVolume()` | Scheletro nell'astratta, passi nelle concrete |
| **Strategy**        | `Comparator` per ordinamento                       | Algoritmo intercambiabile                     |
| **Factory Method**  | Switch nel Main per creare oggetti                 | Creazione delegata                            |
| **Observer**        | Filtro teoria                                      | Subject notifica observers                    |
| **Decorator**       | `BufferedReader(new FileReader(...))`              | Wrapper che aggiunge funzionalità             |

---

## 28. Composizione vs Ereditarietà

> 📌 **Slide L10** – Domanda frequente nel filtro teoria.

### Ereditarietà ("IS-A")

```java
// Un Cuboide È UN Contenitore
public class Cuboide extends Contenitore {
    // eredita tutto da Contenitore
}
```

### Composizione ("HAS-A")

```java
// Una Vetreria HA DEI Contenitori (non è un Contenitore)
public class Vetreria {
    private List<Contenitore> contenitori;  // composizione: contiene oggetti
}
```

### Quando usare cosa?

| Criterio      | Ereditarietà                     | Composizione                            |
| ------------- | -------------------------------- | --------------------------------------- |
| Relazione     | IS-A (è un tipo di)              | HAS-A (contiene/usa)                    |
| Accoppiamento | Forte (figlio dipende dal padre) | Debole (cambio interno non rompe)       |
| Flessibilità  | Rigida (gerarchia fissa)         | Flessibile (componi a runtime)          |
| Riuso codice  | Eredita implementazione          | Delega a componente                     |
| Esempio esame | `Cuboide extends Contenitore`    | `Vetreria` contiene `List<Contenitore>` |

**Regola d'oro:** Preferisci la composizione. Usa l'ereditarietà solo quando c'è una vera relazione "è un tipo di" e il principio di Liskov è rispettato.

**⚠️ Domanda tipica del filtro:**  
_"Perché Vetreria non estende ArrayList?"_  
→ Perché Vetreria non È una lista. Vetreria HA una lista. Estendere ArrayList esporrebbe metodi che non dovrebbero essere disponibili (es. `remove(int index)`) e violerebbe l'incapsulamento.

### Ereditarietà di specifica vs Ereditarietà di implementazione

| Tipo                            | Significato                             | Esempio                                 |
| ------------------------------- | --------------------------------------- | --------------------------------------- |
| **Di specifica** (interfaccia)  | Erediti SOLO il contratto (cosa fare)   | `class Sfera implements Contenitore`    |
| **Di implementazione** (classe) | Erediti contratto + codice (come farlo) | `class Cuboide extends ContenitoreBase` |

- **Di specifica** → più flessibile, nessuna dipendenza dall'implementazione del padre
- **Di implementazione** → più comoda (riuso codice), ma crea accoppiamento forte
- Java supporta ereditarietà singola (una sola classe padre) ma implementazione multipla di interfacce
- **Meglio**: ereditarietà di specifica (interfacce) + composizione per riusare codice

### Il problema della Fragile Base Class

Quando la classe padre cambia implementazione, le classi figlie possono rompersi **anche se il padre non cambia la sua specifica pubblica**.

```java
// Versione 1 del padre
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
        return super.addAll(c);  // ⚠️ PROBLEMA: HashSet.addAll() chiama add()!
    }

    public int getAddCount() { return addCount; }
}

InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
s.addAll(List.of("a", "b", "c"));
s.getAddCount();  // Mi aspetto 3, ottengo 6!
// Perché: addAll() aggiunge 3, poi super.addAll() chiama add() 3 volte → +3
```

**Perché succede?** Il figlio dipende dall'**implementazione interna** del padre (che `addAll` chiama `add`). Se il padre cambia questa implementazione, il figlio si rompe → questo è il problema della **fragile base class**.

**Soluzione:** usare composizione (wrapper/decorator):

```java
public class InstrumentedSet<E> {
    private final Set<E> set;  // composizione, non ereditarietà
    private int addCount = 0;

    public InstrumentedSet(Set<E> set) { this.set = set; }

    public boolean add(E e) {
        addCount++;
        return set.add(e);  // delega → nessuna dipendenza dall'implementazione
    }

    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return set.addAll(c);  // delega → non chiama il nostro add()
    }
}
```

### Dispatch dinamico e gerarchie

Il **dispatch dinamico** è il meccanismo per cui Java, a runtime, seleziona il metodo da eseguire in base al **tipo effettivo** dell'oggetto, non al tipo della variabile.

**Come funziona internamente:**

1. A **compile-time**: il compilatore verifica che il metodo esista nel **tipo statico**
2. A **runtime**: la JVM cerca il metodo nella vtable del **tipo dinamico**
3. Se non lo trova, risale nella gerarchia (padre, nonno, ecc.)

```java
abstract class Forma {
    abstract double area();

    // Metodo concreto che usa dispatch dinamico
    String descrivi() {
        return "Forma con area " + area(); // ← area() sarà dinamico!
    }
}

class Cerchio extends Forma {
    double raggio;
    Cerchio(double r) { raggio = r; }
    @Override double area() { return Math.PI * raggio * raggio; }
}

Forma f = new Cerchio(5);
f.descrivi();  // "Forma con area 78.53..." → area() chiama Cerchio.area()!
```

**⚠️ Attenzione**: il dispatch dinamico si applica SOLO ai metodi d'istanza.

| Membro         | Binding                       | Esempio                      |
| -------------- | ----------------------------- | ---------------------------- |
| Metodo istanza | **Dinamico** (tipo effettivo) | `obj.toString()`             |
| Metodo statico | **Statico** (tipo dichiarato) | `Classe.metodo()`            |
| Campo          | **Statico** (tipo dichiarato) | `obj.campo` → tipo statico!  |
| Overloading    | **Statico** (tipo dichiarato) | Compilatore sceglie la firma |
