# Guida Teorica — Programmazione II (Java)

**Corso**: Programmazione II — Università degli Studi di Milano
**Libro di riferimento**: _Program Development in Java_ (PDJ) — B. Liskov, J. Guttag

---

## Indice

1. [Introduzione (L01 — PDJ Cap. 1)](#1-introduzione-l01--pdj-cap-1)
2. [Object Orientation (L02 — PDJ Cap. 2)](#2-object-orientation-l02--pdj-cap-2)
3. [Compendio Sintassi Java (L02b)](#3-compendio-sintassi-java-l02b)
4. [Astrazione Procedurale (L03 — PDJ Cap. 3, 9)](#4-astrazione-procedurale-l03--pdj-cap-3-9)
5. [Eccezioni (L04 — PDJ Cap. 4)](#5-eccezioni-l04--pdj-cap-4)
6. [Astrazione sui Tipi: Specifica (L05 — PDJ Cap. 5-5.2, 5.8)](#6-astrazione-sui-tipi-specifica-l05--pdj-cap-5-52-58)
7. [Astrazione sui Tipi: Implementazione (L06 — PDJ Cap. 5.2-5.4)](#7-astrazione-sui-tipi-implementazione-l06--pdj-cap-52-54)
8. [Funzione di Astrazione e Invariante di Rappresentazione (L07 — PDJ Cap. 5.5+)](#8-funzione-di-astrazione-e-invariante-di-rappresentazione-l07--pdj-cap-55)
9. [IntSet e Poly (L08 — PDJ Cap. 5.1-5.7)](#9-intset-e-poly-l08--pdj-cap-51-57)
10. [Gerarchie e Principio di Sostituzione (L09 — PDJ Cap. 7-7.7)](#10-gerarchie-e-principio-di-sostituzione-l09--pdj-cap-7-77)
11. [Specifica, Ereditarietà e Composizione (L10 — PDJ Cap. 7.8+)](#11-specifica-ereditarietà-e-composizione-l10--pdj-cap-78)
12. [Astrazione sull'Iterazione (L11 — PDJ Cap. 6-6.4)](#12-astrazione-sulliterazione-l11--pdj-cap-6-64)
13. [Classi Nested (L12 — PDJ Cap. 6.5+)](#13-classi-nested-l12--pdj-cap-65)
14. [Polimorfismo (L13 — PDJ Cap. 8)](#14-polimorfismo-l13--pdj-cap-8)
15. [Generics (L14)](#15-generics-l14)
16. [Programmazione Funzionale (L15)](#16-programmazione-funzionale-l15)
17. [Design Pattern (L16)](#17-design-pattern-l16)

---

## 1. Introduzione (L01 — PDJ Cap. 1)

### 1.1 Obiettivi dell'Ingegneria del Software

L'ingegneria del software si occupa della progettazione, sviluppo e manutenzione di programmi di qualità.
Un programma di qualità deve soddisfare diversi criteri:

- **Correttezza**: il programma fa ciò che è specificato
- **Robustezza**: gestisce situazioni impreviste senza crash
- **Efficienza**: usa le risorse in modo ragionevole
- **Manutenibilità**: è facile da modificare e estendere
- **Leggibilità**: il codice è comprensibile da altri programmatori

### 1.2 Decomposizione

La **decomposizione** è il processo di suddivisione di un problema complesso in sotto-problemi più semplici.
Esistono due approcci principali:

#### Decomposizione per funzionalità (Top-Down)

Si parte dal problema principale e lo si suddivide in funzioni/procedure più piccole.
Ogni funzione risolve una parte specifica del problema.

```java
// Decomposizione per funzionalità: programma per gestire voti studenti
public class GestoreVoti {
    public static double calcolaMedia(int[] voti) {
        int somma = 0;
        for (int v : voti) somma += v;
        return (double) somma / voti.length;
    }

    public static int trovaMassimo(int[] voti) {
        int max = voti[0];
        for (int v : voti) {
            if (v > max) max = v;
        }
        return max;
    }

    public static boolean promosso(double media) {
        return media >= 18;
    }

    public static void main(String[] args) {
        int[] voti = {25, 30, 18, 22, 28};
        double media = calcolaMedia(voti);
        System.out.println("Media: " + media);
        System.out.println("Massimo: " + trovaMassimo(voti));
        System.out.println("Promosso: " + promosso(media));
    }
}
```

#### Decomposizione per dati (Object-Oriented)

Si identificano le entità (oggetti) del dominio e si assegnano responsabilità (metodi) a ciascuna.

```java
// Decomposizione per dati: stesse funzionalità, approccio OO
public class Studente {
    private String nome;
    private int[] voti;

    public Studente(String nome, int[] voti) {
        this.nome = nome;
        this.voti = voti.clone();
    }

    public double calcolaMedia() {
        int somma = 0;
        for (int v : voti) somma += v;
        return (double) somma / voti.length;
    }

    public int votoMassimo() {
        int max = voti[0];
        for (int v : voti) {
            if (v > max) max = v;
        }
        return max;
    }

    public boolean isPromosso() {
        return calcolaMedia() >= 18;
    }
}
```

### 1.3 Astrazione

L'**astrazione** è il processo di identificare gli aspetti rilevanti di un'entità, ignorando i dettagli irrilevanti.
Permette di ragionare a un livello più alto, senza preoccuparsi dell'implementazione.

Si distinguono due forme fondamentali:

#### Astrazione per parametrizzazione

Sostituiamo valori specifici con parametri, rendendo il codice riutilizzabile.

```java
// Senza parametrizzazione: funzione specifica
public static double areaCerchio5() {
    return Math.PI * 5 * 5;
}

// Con parametrizzazione: funzione generica
public static double areaCerchio(double raggio) {
    return Math.PI * raggio * raggio;
}
```

#### Astrazione per specifica

Separiamo il **cosa** fa una procedura dal **come** lo fa.
L'utilizzatore conosce solo la specifica (contratto), non l'implementazione.

```java
// Specifica: ordina un array di interi in ordine crescente
// L'utilizzatore sa COSA fa, non COME lo fa
// Implementazione potrebbe essere bubble sort, merge sort, quicksort...
public static void ordina(int[] arr) {
    // implementazione nascosta
    Arrays.sort(arr);
}
```

### 1.4 I quattro tipi di astrazione

Il libro PDJ identifica quattro tipi fondamentali di astrazione:

1. **Astrazione procedurale**: astrae su una sequenza di operazioni, definendo una procedura con una specifica chiara.
   L'utilizzatore invoca la procedura senza conoscere i dettagli implementativi.

2. **Astrazione sui dati (tipi di dato astratto)**: astrae su una struttura dati, esponendo solo le operazioni
   consentite e nascondendo la rappresentazione interna.

3. **Astrazione sull'iterazione**: astrae sul meccanismo di attraversamento di una collezione,
   nascondendo come gli elementi vengono visitati.

4. **Gerarchie di tipo (polimorfismo)**: astrae su famiglie di tipi correlati, permettendo
   di trattare oggetti di tipi diversi in modo uniforme.

```java
// 1. Astrazione procedurale
public static int massimo(int a, int b) {
    return (a >= b) ? a : b;
}

// 2. Astrazione sui dati
public class Pila<E> {
    private List<E> elementi = new ArrayList<>();

    public void push(E elem) { elementi.add(elem); }
    public E pop() { return elementi.remove(elementi.size() - 1); }
    public boolean isEmpty() { return elementi.isEmpty(); }
    // La rappresentazione interna (ArrayList) è nascosta
}

// 3. Astrazione sull'iterazione
for (String s : collezione) {
    System.out.println(s);
    // Non ci interessa COME la collezione viene attraversata
}

// 4. Gerarchia di tipo
public interface Forma {
    double area();
}
public class Cerchio implements Forma {
    private double raggio;
    public double area() { return Math.PI * raggio * raggio; }
}
public class Rettangolo implements Forma {
    private double base, altezza;
    public double area() { return base * altezza; }
}
// Possiamo trattare Cerchio e Rettangolo uniformemente come Forma
```

### 1.5 Località e Modificabilità

Due proprietà fondamentali del software ben progettato:

- **Località**: l'effetto di una modifica è contenuto in un'area limitata del codice.
  Se cambio l'implementazione di un modulo, non devo modificare il resto del programma.

- **Modificabilità**: il software può essere facilmente adattato a nuovi requisiti.
  Buona decomposizione e astrazione favoriscono la modificabilità.

```java
// Buona località: se cambio l'implementazione di ordinamento, il main non cambia
public class Ordinamento {
    // Posso cambiare da bubble sort a quicksort senza toccare chi usa ordina()
    public static void ordina(int[] arr) {
        // implementazione interna sostituibile
        Arrays.sort(arr);
    }
}

public class Main {
    public static void main(String[] args) {
        int[] dati = {5, 3, 1, 4, 2};
        Ordinamento.ordina(dati); // Non dipende dall'implementazione
    }
}
```

### 1.6 Il ruolo delle specifiche

Le specifiche sono contratti tra chi implementa e chi usa un modulo software.
Definiscono il comportamento atteso senza vincolare l'implementazione.

Una buona specifica include:

- **Precondizioni**: cosa deve essere vero prima della chiamata
- **Postcondizioni**: cosa sarà vero dopo la chiamata
- **Effetti collaterali**: quali modifiche visibili vengono effettuate

```java
// Specifica formale (stile PDJ)
// REQUIRES: arr != null, arr.length > 0
// EFFECTS: restituisce l'elemento minimo di arr
public static int minimo(int[] arr) {
    int min = arr[0];
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] < min) min = arr[i];
    }
    return min;
}
```

### 1.7 Paradigmi di programmazione

I principali paradigmi di programmazione sono:

- **Programmazione imperativa/procedurale**: il programma è una sequenza di istruzioni
  che modificano lo stato. Linguaggi: C, Pascal.

- **Programmazione orientata agli oggetti**: il programma è un insieme di oggetti
  che interagiscono tra loro scambiandosi messaggi (meodi). Linguaggi: Java, C++, Python.

- **Programmazione funzionale**: il programma è una composizione di funzioni pure
  senza effetti collaterali. Linguaggi: Haskell, Lisp, (Java con lambda).

- **Programmazione logica**: il programma è un insieme di regole logiche.
  Linguaggi: Prolog.

Java supporta prevalentemente il paradigma OO, ma dalla versione 8 incorpora anche
elementi di programmazione funzionale (lambda, streams).

---

## 2. Object Orientation (L02 — PDJ Cap. 2)

### 2.1 Programmazione Orientata agli Oggetti

La programmazione orientata agli oggetti (OOP) si basa su quattro principi fondamentali:

1. **Incapsulamento**: nascondere i dettagli interni di un oggetto
2. **Astrazione**: esporre solo le interfacce necessarie
3. **Ereditarietà**: creare nuovi tipi basandosi su tipi esistenti
4. **Polimorfismo**: trattare oggetti diversi in modo uniforme

### 2.2 Classi e Istanze

Una **classe** è un modello (template) che definisce la struttura e il comportamento
di un tipo di oggetto. Un'**istanza** (oggetto) è una realizzazione concreta di una classe.

```java
// Definizione di classe
public class Punto {
    // Attributi (stato)
    private double x;
    private double y;

    // Costruttore
    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Metodi (comportamento)
    public double distanzaDaOrigine() {
        return Math.sqrt(x * x + y * y);
    }

    public double distanzaDa(Punto altro) {
        double dx = this.x - altro.x;
        double dy = this.y - altro.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void trasla(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

// Creazione di istanze
Punto p1 = new Punto(3, 4);
Punto p2 = new Punto(0, 0);
System.out.println(p1.distanzaDa(p2)); // 5.0
```

### 2.3 Attributi e Metodi

Gli **attributi** rappresentano lo stato di un oggetto. I **metodi** definiscono le operazioni
che un oggetto può compiere.

- **Attributi di istanza**: appartengono a ciascuna istanza (ogni oggetto ha la sua copia)
- **Attributi di classe (static)**: condivisi tra tutte le istanze della classe
- **Metodi di istanza**: operano sullo stato dell'oggetto specifico
- **Metodi di classe (static)**: non dipendono da un'istanza specifica

```java
public class ContoBancario {
    // Attributo di classe (condiviso)
    private static int contatoreConti = 0;
    private static final double TASSO_INTERESSE = 0.02;

    // Attributi di istanza
    private final int numeroConto;
    private String titolare;
    private double saldo;

    public ContoBancario(String titolare, double saldoIniziale) {
        this.titolare = titolare;
        this.saldo = saldoIniziale;
        this.numeroConto = ++contatoreConti; // usa e incrementa il contatore
    }

    // Metodo di istanza
    public void deposita(double importo) {
        if (importo > 0) saldo += importo;
    }

    public boolean preleva(double importo) {
        if (importo > 0 && saldo >= importo) {
            saldo -= importo;
            return true;
        }
        return false;
    }

    public double getSaldo() { return saldo; }

    // Metodo di classe
    public static int getNumeroConti() {
        return contatoreConti;
    }
}
```

### 2.4 Package e Import

I **package** organizzano le classi in namespace gerarchici, evitando conflitti di nome
e raggruppando classi correlate.

```java
// Dichiarazione del package (prima riga del file)
package com.universita.modello;

// Import di classi specifiche
import java.util.ArrayList;
import java.util.List;

// Import di tutte le classi di un package (sconsigliato)
import java.util.*;

public class Corso {
    private String nome;
    private List<String> studenti = new ArrayList<>();

    public Corso(String nome) {
        this.nome = nome;
    }

    public void aggiungiStudente(String studente) {
        studenti.add(studente);
    }
}
```

Struttura tipica dei package:

```
src/
  com/
    universita/
      modello/
        Studente.java
        Corso.java
      servizi/
        GestoreIscrizioni.java
      util/
        Validatore.java
```

### 2.5 Modificatori di Accesso

Java fornisce quattro livelli di visibilità:

| Modificatore | Classe | Package | Sottoclasse | Mondo |
| ------------ | ------ | ------- | ----------- | ----- |
| `private`    | Sì     | No      | No          | No    |
| (default)    | Sì     | Sì      | No          | No    |
| `protected`  | Sì     | Sì      | Sì          | No    |
| `public`     | Sì     | Sì      | Sì          | Sì    |

```java
public class Esempio {
    private int privato;       // solo dentro questa classe
    int packageLevel;          // classi nello stesso package
    protected int protetto;    // classi nello stesso package + sottoclassi
    public int pubblico;       // accessibile ovunque
}
```

**Regola d'oro**: rendere gli attributi `private` ed esporre metodi getter/setter solo se necessario.

### 2.6 Stack e Heap

In Java, la memoria è divisa in due aree principali:

- **Stack**: contiene le variabili locali e i riferimenti agli oggetti. Ogni thread ha il proprio stack.
  Le variabili di tipo primitivo (int, double, boolean...) sono memorizzate direttamente nello stack.

- **Heap**: contiene gli oggetti creati con `new`. Lo heap è condiviso tra tutti i thread.
  Le variabili di tipo riferimento (oggetti, array) contengono nello stack un riferimento (puntatore)
  all'oggetto effettivo nello heap.

```java
public static void main(String[] args) {
    int x = 42;                    // x nello Stack (valore primitivo)
    String s = "ciao";             // s nello Stack (riferimento), "ciao" nello Heap
    Punto p = new Punto(3, 4);     // p nello Stack (riferimento), oggetto Punto nello Heap

    int y = x;      // copia del VALORE: y = 42, indipendente da x
    Punto q = p;     // copia del RIFERIMENTO: q punta allo STESSO oggetto di p
    q.trasla(1, 1);  // modifica l'oggetto: sia p che q vedono la modifica!
}
```

### 2.7 Overloading

L'**overloading** (sovraccarico) permette di definire più metodi con lo stesso nome
ma firma diversa (numero o tipo dei parametri). La scelta del metodo avviene
a **tempo di compilazione** (dispatching statico).

```java
public class Calcolatrice {
    // Overloading: stesso nome, parametri diversi
    public int somma(int a, int b) {
        return a + b;
    }

    public double somma(double a, double b) {
        return a + b;
    }

    public int somma(int a, int b, int c) {
        return a + b + c;
    }

    public String somma(String a, String b) {
        return a + b; // concatenazione
    }
}

Calcolatrice calc = new Calcolatrice();
calc.somma(1, 2);          // chiama somma(int, int) → 3
calc.somma(1.5, 2.5);      // chiama somma(double, double) → 4.0
calc.somma(1, 2, 3);       // chiama somma(int, int, int) → 6
calc.somma("ab", "cd");    // chiama somma(String, String) → "abcd"
```

### 2.8 Dispatching Statico e Dinamico

- **Dispatching statico**: il metodo viene scelto a tempo di compilazione
  basandosi sul tipo apparente (dichiarato) e sulla firma. Si applica all'overloading.

- **Dispatching dinamico**: il metodo viene scelto a tempo di esecuzione
  basandosi sul tipo concreto (effettivo) dell'oggetto. Si applica all'overriding.

```java
class Animale {
    public String verso() { return "..."; }
}

class Cane extends Animale {
    @Override
    public String verso() { return "Bau!"; }
}

class Gatto extends Animale {
    @Override
    public String verso() { return "Miao!"; }
}

// Dispatching dinamico
Animale a = new Cane();    // tipo apparente: Animale, tipo concreto: Cane
a.verso();                 // → "Bau!" (dispatching dinamico: usa il tipo concreto)

Animale b = new Gatto();
b.verso();                 // → "Miao!"

Animale[] animali = { new Cane(), new Gatto(), new Cane() };
for (Animale anim : animali) {
    System.out.println(anim.verso()); // il metodo corretto viene scelto a runtime
}
```

### 2.9 Collections Framework

Java fornisce il **Collections Framework** nel package `java.util`, con interfacce
e implementazioni per le strutture dati più comuni.

#### List (lista ordinata, ammette duplicati)

```java
List<String> lista = new ArrayList<>();  // implementazione con array dinamico
lista.add("primo");
lista.add("secondo");
lista.add("primo");  // duplicati ammessi
lista.get(0);         // "primo" — accesso per indice
lista.size();         // 3
lista.remove(1);      // rimuove "secondo"
lista.contains("primo"); // true

// Anche LinkedList
List<String> linked = new LinkedList<>(); // implementazione con lista concatenata
```

#### Set (insieme, NO duplicati)

```java
Set<Integer> insieme = new HashSet<>();
insieme.add(1);
insieme.add(2);
insieme.add(1);  // ignorato, già presente
insieme.size();   // 2
insieme.contains(1); // true

// TreeSet: ordinato
Set<Integer> ordinato = new TreeSet<>();
ordinato.add(3);
ordinato.add(1);
ordinato.add(2);
// iterazione: 1, 2, 3 (ordinato)
```

#### Map (dizionario chiave-valore)

```java
Map<String, Integer> mappa = new HashMap<>();
mappa.put("Alice", 28);
mappa.put("Bob", 25);
mappa.get("Alice");     // 28
mappa.containsKey("Bob"); // true
mappa.size();            // 2

// Iterazione su una Map
for (Map.Entry<String, Integer> entry : mappa.entrySet()) {
    System.out.println(entry.getKey() + " → " + entry.getValue());
}
```

### 2.10 Autoboxing e Unboxing

Java converte automaticamente tra tipi primitivi e i corrispondenti wrapper:

| Primitivo | Wrapper   |
| --------- | --------- |
| int       | Integer   |
| double    | Double    |
| boolean   | Boolean   |
| char      | Character |
| long      | Long      |
| float     | Float     |

```java
// Autoboxing: int → Integer
List<Integer> numeri = new ArrayList<>();
numeri.add(42);          // autoboxing: 42 → Integer.valueOf(42)
numeri.add(17);

// Unboxing: Integer → int
int primo = numeri.get(0); // unboxing: Integer → int

// Attenzione: unboxing di null causa NullPointerException!
Integer n = null;
int x = n; // NullPointerException!
```

### 2.11 Generics (Introduzione)

I **generics** permettono di parametrizzare classi e metodi rispetto a un tipo.
Garantiscono type safety a tempo di compilazione.

```java
// Senza generics (pre Java 5): tutto è Object
List lista = new ArrayList();
lista.add("stringa");
lista.add(42);  // nessun errore a compile time
String s = (String) lista.get(1); // ClassCastException a runtime!

// Con generics: tipo specificato
List<String> listaSicura = new ArrayList<>();
listaSicura.add("stringa");
// listaSicura.add(42);  // ERRORE DI COMPILAZIONE — catturato subito
String s2 = listaSicura.get(0); // nessun cast necessario
```

---

## 3. Compendio Sintassi Java (L02b)

### 3.1 Strutture di Selezione

#### If / Else

```java
int voto = 25;

if (voto >= 30) {
    System.out.println("Ottimo");
} else if (voto >= 24) {
    System.out.println("Buono");
} else if (voto >= 18) {
    System.out.println("Sufficiente");
} else {
    System.out.println("Insufficiente");
}
```

#### Operatore Ternario

```java
String risultato = (voto >= 18) ? "Promosso" : "Bocciato";
```

#### Switch Classico (con break)

```java
int giorno = 3;
String nomeGiorno;

switch (giorno) {
    case 1:
        nomeGiorno = "Lunedì";
        break;
    case 2:
        nomeGiorno = "Martedì";
        break;
    case 3:
        nomeGiorno = "Mercoledì";
        break;
    case 4:
        nomeGiorno = "Giovedì";
        break;
    case 5:
        nomeGiorno = "Venerdì";
        break;
    case 6:
        nomeGiorno = "Sabato";
        break;
    case 7:
        nomeGiorno = "Domenica";
        break;
    default:
        nomeGiorno = "Non valido";
        break;
}
```

#### Switch con Arrow (Java 14+)

Il nuovo switch con `->` non richiede `break` e non ha fall-through:

```java
String nomeGiorno = switch (giorno) {
    case 1 -> "Lunedì";
    case 2 -> "Martedì";
    case 3 -> "Mercoledì";
    case 4 -> "Giovedì";
    case 5 -> "Venerdì";
    case 6 -> "Sabato";
    case 7 -> "Domenica";
    default -> "Non valido";
};

// Switch con blocchi
String tipo = switch (giorno) {
    case 1, 2, 3, 4, 5 -> {
        System.out.println("Giorno lavorativo");
        yield "Feriale";
    }
    case 6, 7 -> {
        System.out.println("Weekend");
        yield "Festivo";
    }
    default -> "Non valido";
};
```

### 3.2 Cicli

#### While

```java
int i = 0;
while (i < 10) {
    System.out.println(i);
    i++;
}
```

#### Do-While

Esegue il corpo almeno una volta, poi verifica la condizione:

```java
Scanner scanner = new Scanner(System.in);
int scelta;
do {
    System.out.println("1. Opzione A");
    System.out.println("2. Opzione B");
    System.out.println("0. Esci");
    scelta = scanner.nextInt();

    switch (scelta) {
        case 1 -> System.out.println("Hai scelto A");
        case 2 -> System.out.println("Hai scelto B");
        case 0 -> System.out.println("Arrivederci");
        default -> System.out.println("Scelta non valida");
    }
} while (scelta != 0);
```

#### For classico

```java
for (int i = 0; i < 10; i++) {
    System.out.println("Iterazione " + i);
}

// Contare al contrario
for (int i = 9; i >= 0; i--) {
    System.out.println(i);
}

// Passo personalizzato
for (int i = 0; i < 100; i += 5) {
    System.out.println(i); // 0, 5, 10, 15, ...
}
```

#### For-Each (Enhanced For)

```java
String[] nomi = {"Alice", "Bob", "Carlo"};
for (String nome : nomi) {
    System.out.println("Ciao " + nome);
}

List<Integer> numeri = List.of(1, 2, 3, 4, 5);
for (int n : numeri) {
    System.out.println(n * n);
}
```

#### Break e Continue

```java
// break: interrompe il ciclo
for (int i = 0; i < 100; i++) {
    if (i == 10) break;  // esce dal ciclo quando i == 10
    System.out.println(i);
}

// continue: salta all'iterazione successiva
for (int i = 0; i < 20; i++) {
    if (i % 2 != 0) continue;  // salta i dispari
    System.out.println(i);  // stampa solo i pari
}
```

### 3.3 ArrayList e Operazioni

```java
import java.util.ArrayList;
import java.util.Collections;

// Creazione
ArrayList<String> lista = new ArrayList<>();

// Aggiunta
lista.add("primo");          // aggiunge in fondo
lista.add(0, "zero");       // aggiunge in posizione 0

// Accesso
String elem = lista.get(0);  // "zero"
int dim = lista.size();       // 2

// Modifica
lista.set(0, "nuovo");       // sostituisce elemento in posizione 0

// Rimozione
lista.remove(0);             // rimuove per indice
lista.remove("primo");       // rimuove per valore (prima occorrenza)

// Ricerca
boolean c = lista.contains("primo");  // true/false
int idx = lista.indexOf("primo");     // indice o -1

// Ordinamento
ArrayList<Integer> numeri = new ArrayList<>();
numeri.add(3); numeri.add(1); numeri.add(2);
Collections.sort(numeri);    // [1, 2, 3]

// Conversione Array ↔ ArrayList
String[] arr = lista.toArray(new String[0]);
ArrayList<String> daArray = new ArrayList<>(List.of("a", "b", "c"));

// Svuotamento
lista.clear();
boolean vuota = lista.isEmpty();  // true
```

### 3.4 Iterazione sulle Liste

```java
List<String> frutti = new ArrayList<>(List.of("mela", "banana", "arancia"));

// 1. For classico con indice
for (int i = 0; i < frutti.size(); i++) {
    System.out.println(i + ": " + frutti.get(i));
}

// 2. For-each
for (String frutto : frutti) {
    System.out.println(frutto);
}

// 3. Iterator esplicito
Iterator<String> it = frutti.iterator();
while (it.hasNext()) {
    String frutto = it.next();
    if (frutto.equals("banana")) {
        it.remove();  // rimozione sicura durante iterazione
    }
}

// 4. forEach con lambda (Java 8+)
frutti.forEach(frutto -> System.out.println(frutto));
frutti.forEach(System.out::println);  // method reference
```

### 3.5 Input/Output con Scanner

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);

// Lettura di tipi diversi
System.out.print("Inserisci un intero: ");
int intero = scanner.nextInt();

System.out.print("Inserisci un double: ");
double decimale = scanner.nextDouble();

scanner.nextLine(); // consuma il newline rimasto

System.out.print("Inserisci una stringa: ");
String riga = scanner.nextLine();

System.out.print("Inserisci una parola: ");
String parola = scanner.next(); // legge fino allo spazio

// Verifica disponibilità
if (scanner.hasNextInt()) {
    int n = scanner.nextInt();
}

scanner.close();
```

### 3.6 Output Formattato

```java
// println: stampa con a capo
System.out.println("Ciao mondo");

// print: stampa senza a capo
System.out.print("Senza ");
System.out.print("a capo");

// printf: output formattato
String nome = "Alice";
int eta = 22;
double media = 27.5;

System.out.printf("Nome: %s, Età: %d, Media: %.2f%n", nome, eta, media);
// Output: Nome: Alice, Età: 22, Media: 27.50

// Formattatori comuni:
// %s → stringa
// %d → intero
// %f → decimale (%.2f per 2 cifre decimali)
// %n → newline (platform-independent)
// %b → booleano
// %c → carattere
// %e → notazione scientifica

System.out.printf("%-20s | %5d | %8.2f%n", "Alice", 25, 27.5);
System.out.printf("%-20s | %5d | %8.2f%n", "Bob", 30, 29.0);
// Alice                |    25 |    27.50
// Bob                  |    30 |    29.00
```

---

## 4. Astrazione Procedurale (L03 — PDJ Cap. 3, 9)

### 4.1 Cos'è l'Astrazione Procedurale

L'**astrazione procedurale** consiste nel definire un'operazione attraverso una specifica
(contratto) che descrive cosa fa, separandola da come lo fa.

Chi usa la procedura conosce solo la specifica.
Chi implementa la procedura è libero di scegliere l'algoritmo, purché rispetti la specifica.

Vantaggi:

- **Località**: posso modificare l'implementazione senza cambiare il codice chiamante
- **Modificabilità**: posso sostituire l'algoritmo (es. da bubble sort a quicksort)
- **Riusabilità**: la specifica documenta l'uso della procedura

### 4.2 Specifiche: REQUIRES, MODIFIES, EFFECTS

Il formato di specifica usato nel libro PDJ include tre clausole:

- **REQUIRES** (precondizioni): cosa deve essere vero PRIMA della chiamata.
  Se la clausola REQUIRES è violata, il comportamento è indefinito.

- **MODIFIES** (input modificati): elenca gli input che vengono modificati dalla procedura.
  Se assente, la procedura non ha effetti collaterali.

- **EFFECTS** (postcondizioni): descrive cosa fa la procedura,
  ASSUMENDO che la clausola REQUIRES sia soddisfatta.

```java
/**
 * Cerca un elemento in un array ordinato usando ricerca binaria.
 *
 * REQUIRES: arr != null, arr è ordinato in ordine crescente
 * EFFECTS: restituisce l'indice di target in arr, oppure -1 se non presente
 */
public static int ricercaBinaria(int[] arr, int target) {
    int low = 0, high = arr.length - 1;
    while (low <= high) {
        int mid = (low + high) / 2;
        if (arr[mid] == target) return mid;
        else if (arr[mid] < target) low = mid + 1;
        else high = mid - 1;
    }
    return -1;
}

/**
 * Ordina un array di interi in ordine crescente.
 *
 * MODIFIES: arr
 * EFFECTS: riordina gli elementi di arr in ordine crescente
 */
public static void ordina(int[] arr) {
    Arrays.sort(arr);
}

/**
 * Rimuove tutti gli elementi negativi dalla lista.
 *
 * REQUIRES: lista != null
 * MODIFIES: lista
 * EFFECTS: rimuove da lista tutti gli elementi < 0
 */
public static void rimuoviNegativi(List<Integer> lista) {
    lista.removeIf(n -> n < 0);
}
```

### 4.3 La clausola OVERVIEW

Per moduli più complessi, si usa anche la clausola **OVERVIEW** che fornisce una
descrizione ad alto livello del modulo (classe o package).

```java
/**
 * OVERVIEW: Classe di utilità per operazioni su array di interi.
 * Fornisce metodi statici per ricerca, ordinamento e statistiche.
 * Tutti i metodi assumono array non null se non diversamente specificato.
 */
public class ArrayUtils {

    /**
     * EFFECTS: restituisce la somma degli elementi di arr
     *          se arr è vuoto, restituisce 0
     */
    public static int somma(int[] arr) {
        int s = 0;
        for (int x : arr) s += x;
        return s;
    }

    /**
     * REQUIRES: arr.length > 0
     * EFFECTS: restituisce la media aritmetica degli elementi di arr
     */
    public static double media(int[] arr) {
        return (double) somma(arr) / arr.length;
    }

    /**
     * REQUIRES: arr.length > 0
     * EFFECTS: restituisce il valore massimo in arr
     */
    public static int massimo(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }
}
```

### 4.4 Procedure Totali vs Parziali

- **Procedura totale**: definita per TUTTI i possibili input.
  NON ha clausola REQUIRES (o è implicita: `true`).
  Deve gestire ogni caso, inclusi quelli "anomali".

- **Procedura parziale**: definita solo per un SOTTOINSIEME degli input.
  Ha una clausola REQUIRES non triviale.
  Se le precondizioni non sono soddisfatte, il comportamento è indefinito.

```java
// PROCEDURA PARZIALE: comportamento indefinito se arr è vuoto
/**
 * REQUIRES: arr != null, arr.length > 0
 * EFFECTS: restituisce l'elemento minimo di arr
 */
public static int minimoParzialeUnsafe(int[] arr) {
    int min = arr[0]; // se arr è vuoto → ArrayIndexOutOfBoundsException
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] < min) min = arr[i];
    }
    return min;
}

// PROCEDURA TOTALE: gestisce tutti i casi
/**
 * EFFECTS: se arr è null o vuoto, lancia IllegalArgumentException
 *          altrimenti restituisce l'elemento minimo di arr
 */
public static int minimoTotale(int[] arr) {
    if (arr == null || arr.length == 0) {
        throw new IllegalArgumentException("Array vuoto o null");
    }
    int min = arr[0];
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] < min) min = arr[i];
    }
    return min;
}
```

### 4.5 Programmazione Difensiva

La **programmazione difensiva** consiste nel rendere le procedure totali, verificando
le precondizioni e lanciando eccezioni appropriate quando non sono soddisfatte.

Vantaggi:

- Errori rilevati immediatamente (fail fast)
- Messaggi di errore chiari e localizzati
- Debugging più semplice

```java
/**
 * EFFECTS: se importo <= 0, lancia IllegalArgumentException
 *          se importo > saldo, lancia IllegalStateException
 *          altrimenti decrementa saldo di importo
 */
public void preleva(double importo) {
    if (importo <= 0) {
        throw new IllegalArgumentException("Importo deve essere positivo: " + importo);
    }
    if (importo > saldo) {
        throw new IllegalStateException("Saldo insufficiente: " + saldo + " < " + importo);
    }
    saldo -= importo;
}
```

### 4.6 Astrazione Procedurale come Contratto

La specifica crea un **contratto** tra due parti:

- **Chiamante** (client): si impegna a rispettare le precondizioni (REQUIRES)
- **Implementatore** (supplier): si impegna a garantire le postcondizioni (EFFECTS)

```
Contratto:
┌───────────────┐                   ┌──────────────────┐
│   Chiamante   │  ──── chiama ───→ │  Implementatore  │
│               │                   │                  │
│ Obblighi:     │                   │ Obblighi:        │
│ - REQUIRES    │                   │ - EFFECTS        │
│               │                   │ - MODIFIES       │
│ Diritti:      │                   │ Diritti:         │
│ - EFFECTS     │                   │ - REQUIRES       │
│   garantiti   │                   │   soddisfatti    │
└───────────────┘                   └──────────────────┘
```

Se il chiamante viola REQUIRES, l'implementatore non ha alcun obbligo.
Se il chiamante rispetta REQUIRES, l'implementatore deve garantire EFFECTS.

### 4.7 Benefici dell'Astrazione Procedurale

```java
// Senza astrazione: codice duplicato e difficile da mantenere
public class ReportVendite {
    public void generaReport() {
        // ... calcola totale vendite (10 righe di codice)
        // ... calcola totale vendite per regione (15 righe)
        // ... stesso calcolo ripetuto in 5 posti diversi
    }
}

// Con astrazione procedurale: codice pulito e manutenibile
public class CalcoloVendite {
    /**
     * REQUIRES: vendite != null
     * EFFECTS: restituisce la somma di tutti gli importi in vendite
     */
    public static double totale(List<Double> vendite) {
        return vendite.stream().mapToDouble(Double::doubleValue).sum();
    }

    /**
     * REQUIRES: vendite != null, regione != null
     * EFFECTS: restituisce la somma degli importi per la regione specificata
     */
    public static double totalPerRegione(Map<String, List<Double>> vendite, String regione) {
        return totale(vendite.getOrDefault(regione, List.of()));
    }
}
```

---

## 5. Eccezioni (L04 — PDJ Cap. 4)

### 5.1 Gerarchia delle Eccezioni in Java

In Java, tutte le eccezioni derivano dalla classe `Throwable`:

```
Throwable
├── Error                    (errori gravi, NON da catturare)
│   ├── OutOfMemoryError
│   ├── StackOverflowError
│   └── ...
└── Exception                (eccezioni gestibili)
    ├── RuntimeException     (UNCHECKED)
    │   ├── NullPointerException
    │   ├── ArrayIndexOutOfBoundsException
    │   ├── IllegalArgumentException
    │   ├── IllegalStateException
    │   ├── ClassCastException
    │   ├── ArithmeticException
    │   └── ...
    ├── IOException          (CHECKED)
    ├── FileNotFoundException (CHECKED)
    ├── SQLException         (CHECKED)
    └── ...
```

### 5.2 Checked vs Unchecked Exceptions

#### Checked Exceptions (Controllate)

Sottoclassi di `Exception` (ma NON di `RuntimeException`).
Il compilatore **obbliga** a gestirle: o con try-catch, o dichiarandole con `throws`.

```java
// CHECKED: il compilatore obbliga a gestire IOException
public String leggiFile(String percorso) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(percorso));
    String contenuto = reader.readLine();
    reader.close();
    return contenuto;
}

// Chi chiama DEVE gestire l'eccezione:
// Opzione 1: try-catch
try {
    String s = leggiFile("dati.txt");
} catch (IOException e) {
    System.err.println("Errore lettura: " + e.getMessage());
}

// Opzione 2: propagare con throws
public void elabora() throws IOException {
    String s = leggiFile("dati.txt");
}
```

#### Unchecked Exceptions (Non controllate)

Sottoclassi di `RuntimeException`.
Il compilatore **non obbliga** a gestirle. Indicano tipicamente errori di programmazione.

```java
// UNCHECKED: il compilatore non obbliga a gestirle
public static int dividi(int a, int b) {
    return a / b; // può lanciare ArithmeticException se b == 0
}

// Possiamo gestirle, ma non siamo obbligati
try {
    int risultato = dividi(10, 0);
} catch (ArithmeticException e) {
    System.err.println("Divisione per zero!");
}

// Esempi comuni di unchecked exception
String s = null;
s.length();          // NullPointerException

int[] arr = {1, 2};
arr[5] = 10;         // ArrayIndexOutOfBoundsException

Object x = "stringa";
Integer n = (Integer) x; // ClassCastException
```

### 5.3 Lanciare Eccezioni: throw e throws

```java
// throw: lancia un'eccezione
public void setEta(int eta) {
    if (eta < 0 || eta > 150) {
        throw new IllegalArgumentException("Età non valida: " + eta);
    }
    this.eta = eta;
}

// throws: dichiara che un metodo può lanciare eccezioni checked
public void salvaFile(String percorso, String contenuto) throws IOException {
    FileWriter writer = new FileWriter(percorso);
    writer.write(contenuto);
    writer.close();
}
```

### 5.4 Try-Catch-Finally

```java
// try-catch base
try {
    int risultato = 10 / 0;
} catch (ArithmeticException e) {
    System.err.println("Errore aritmetico: " + e.getMessage());
}

// Multi-catch
try {
    String s = null;
    s.length();
} catch (NullPointerException e) {
    System.err.println("Riferimento null: " + e.getMessage());
} catch (Exception e) {
    System.err.println("Errore generico: " + e.getMessage());
}

// Catch multiplo con | (Java 7+)
try {
    // codice che può lanciare diverse eccezioni
    rischiosa();
} catch (IOException | SQLException e) {
    System.err.println("Errore I/O o database: " + e.getMessage());
}

// finally: eseguito SEMPRE (con o senza eccezione)
FileWriter writer = null;
try {
    writer = new FileWriter("output.txt");
    writer.write("dati");
} catch (IOException e) {
    System.err.println("Errore scrittura: " + e.getMessage());
} finally {
    // Pulizia risorse: eseguito SEMPRE
    if (writer != null) {
        try {
            writer.close();
        } catch (IOException e) {
            // ignora errore di chiusura
        }
    }
}
```

### 5.5 Try-With-Resources (Java 7+)

Gestisce automaticamente la chiusura delle risorse che implementano `AutoCloseable`:

```java
// La risorsa viene chiusa automaticamente alla fine del blocco try
try (BufferedReader reader = new BufferedReader(new FileReader("dati.txt"))) {
    String riga;
    while ((riga = reader.readLine()) != null) {
        System.out.println(riga);
    }
} catch (IOException e) {
    System.err.println("Errore: " + e.getMessage());
}
// reader.close() chiamato automaticamente, anche se c'è un'eccezione

// Più risorse
try (FileReader fr = new FileReader("input.txt");
     BufferedReader br = new BufferedReader(fr);
     FileWriter fw = new FileWriter("output.txt")) {
    String riga;
    while ((riga = br.readLine()) != null) {
        fw.write(riga + "\n");
    }
}
```

### 5.6 Eccezioni Personalizzate (Custom Exceptions)

```java
// Eccezione checked personalizzata
public class SaldoInsufficienteException extends Exception {
    private final double saldo;
    private final double importo;

    public SaldoInsufficienteException(double saldo, double importo) {
        super("Saldo insufficiente: " + saldo + " < " + importo);
        this.saldo = saldo;
        this.importo = importo;
    }

    public double getSaldo() { return saldo; }
    public double getImporto() { return importo; }
}

// Eccezione unchecked personalizzata
public class ValoreNonValidoException extends RuntimeException {
    public ValoreNonValidoException(String messaggio) {
        super(messaggio);
    }
}

// Utilizzo
public class ContoBancario {
    private double saldo;

    /**
     * EFFECTS: se importo <= 0, lancia ValoreNonValidoException
     *          se importo > saldo, lancia SaldoInsufficienteException
     *          altrimenti decrementa saldo di importo
     */
    public void preleva(double importo) throws SaldoInsufficienteException {
        if (importo <= 0) {
            throw new ValoreNonValidoException("Importo non valido: " + importo);
        }
        if (importo > saldo) {
            throw new SaldoInsufficienteException(saldo, importo);
        }
        saldo -= importo;
    }
}
```

### 5.7 Reflecting e Masking

**Reflecting** (riflettere): lanciare un'eccezione dello stesso tipo di quella catturata,
propagandola verso l'alto con eventuale informazioni aggiuntive.

```java
public void elaboraDati() throws IOException {
    try {
        leggiFile("dati.txt");
    } catch (FileNotFoundException e) {
        // Reflecting: rilancio con più contesto
        throw new FileNotFoundException("File dati non trovato: " + e.getMessage());
    }
}
```

**Masking** (mascherare): catturare un'eccezione e NON propagarla, gestendola internamente
o convertendola in un tipo diverso.

```java
// Masking: cattura e gestisce internamente
public int leggiIntero(String input) {
    try {
        return Integer.parseInt(input);
    } catch (NumberFormatException e) {
        // Masking: l'eccezione è gestita, restituiamo un valore di default
        return 0;
    }
}

// Masking con conversione di tipo (Exception Translation)
public Studente cercaStudente(int id) throws StudenteNonTrovatoException {
    try {
        return database.query("SELECT * FROM studenti WHERE id = " + id);
    } catch (SQLException e) {
        // Convertiamo SQLException (dettaglio implementativo) in un'eccezione di dominio
        throw new StudenteNonTrovatoException("Studente " + id + " non trovato", e);
    }
}
```

### 5.8 Eccezioni nelle Specifiche

Le eccezioni fanno parte della specifica di un metodo. La clausola EFFECTS deve
documentare quando e quali eccezioni vengono lanciate:

```java
/**
 * EFFECTS: se x < 0, lancia NegativeNumberException
 *          altrimenti restituisce la radice quadrata di x
 */
public static double radice(double x) throws NegativeNumberException {
    if (x < 0) throw new NegativeNumberException("radice(" + x + ")");
    return Math.sqrt(x);
}
```

Regole d'uso nelle specifiche:

1. Le eccezioni unchecked nella specifica indicano precondizioni violate
2. Le eccezioni checked indicano situazioni eccezionali ma prevedibili
3. Una procedura totale specifica eccezioni per tutti i casi anomali (no REQUIRES)
4. Una procedura parziale usa REQUIRES e non specifica eccezioni per precondizioni

---

## 6. Astrazione sui Tipi: Specifica (L05 — PDJ Cap. 5-5.2, 5.8)

### 6.1 Tipo Apparente vs Tipo Concreto

In Java, ogni variabile ha due tipi:

- **Tipo apparente** (tipo dichiarato/statico): il tipo con cui la variabile è dichiarata.
  Determina quali metodi possono essere chiamati (verificato dal compilatore).

- **Tipo concreto** (tipo effettivo/dinamico): il tipo reale dell'oggetto a runtime.
  Determina quale implementazione del metodo viene eseguita.

```java
// Tipo apparente: List<String>
// Tipo concreto: ArrayList<String>
List<String> lista = new ArrayList<>();

// Il compilatore permette solo metodi di List (tipo apparente)
lista.add("ciao");      // OK: add è in List
lista.get(0);            // OK: get è in List
// lista.trimToSize();   // ERRORE: trimToSize è solo in ArrayList

// Tipo apparente: Object
// Tipo concreto: String
Object obj = "una stringa";
// obj.length();         // ERRORE: length non è in Object
((String) obj).length(); // OK: cast esplicito down-casting
```

### 6.2 Tipo Astratto di Dato (ADT)

Un **tipo astratto di dato** (ADT) è definito dalla sua specifica, non dall'implementazione.
Lo specificare COSA si può fare con un tipo (operazioni), nascondendo COME è implementato.

Componenti della specifica di un ADT:

1. **Nome del tipo** e se è mutabile o immutabile
2. **Costruttori**: creano nuove istanze
3. **Metodi**: operazioni disponibili sulle istanze
4. **Invarianti**: proprietà sempre vere per tutte le istanze

### 6.3 Categorie di Metodi

I metodi di un tipo astratto si classificano in quattro categorie:

#### Creator (Costruttori/Creatori)

Creano nuove istanze del tipo. Includono costruttori Java e factory methods.

```java
public class IntSet {
    // Creator: costruttore
    public IntSet() { ... }

    // Creator: factory method
    public static IntSet empty() { return new IntSet(); }

    // Creator: factory method con parametri
    public static IntSet of(int... elements) {
        IntSet s = new IntSet();
        for (int e : elements) s.insert(e);
        return s;
    }
}
```

#### Producer (Produttori)

Creano una NUOVA istanza dello stesso tipo a partire da istanze esistenti.
Non modificano l'oggetto su cui sono chiamati.

```java
public class Poly {
    // Producer: restituisce un NUOVO Poly = this + q
    public Poly add(Poly q) {
        // crea e restituisce un nuovo polinomio
        Poly risultato = new Poly();
        // ... calcola somma ...
        return risultato;
    }

    // Producer: restituisce un NUOVO Poly = -this
    public Poly minus() {
        Poly risultato = new Poly();
        // ... nega i coefficienti ...
        return risultato;
    }
}
```

#### Mutator (Mutatori)

Modificano lo stato dell'oggetto su cui sono chiamati. Presenti solo nei tipi mutabili.

```java
public class IntSet {
    // Mutator: modifica this aggiungendo un elemento
    public void insert(int x) {
        if (!contains(x)) {
            elementi.add(x);
        }
    }

    // Mutator: modifica this rimuovendo un elemento
    public void remove(int x) {
        elementi.remove(Integer.valueOf(x));
    }
}
```

#### Observer (Osservatori)

Restituiscono informazioni sullo stato dell'oggetto senza modificarlo.

```java
public class IntSet {
    // Observer: restituisce la dimensione
    public int size() {
        return elementi.size();
    }

    // Observer: verifica se un elemento è presente
    public boolean contains(int x) {
        return elementi.contains(x);
    }

    // Observer: verifica se l'insieme è vuoto
    public boolean isEmpty() {
        return elementi.isEmpty();
    }
}
```

### 6.4 Specifica Completa di un Tipo

Una specifica completa di un tipo include:

```java
/**
 * OVERVIEW: IntSet è un insieme mutabile di interi, senza limiti di dimensione.
 *           Un IntSet tipico è {x1, x2, ..., xn}.
 *           Gli elementi non sono duplicati.
 */
public class IntSet {

    // --- Creator ---

    /**
     * EFFECTS: crea un nuovo IntSet vuoto
     */
    public IntSet() { ... }

    // --- Mutator ---

    /**
     * MODIFIES: this
     * EFFECTS: aggiunge x a this, ossia this_post = this_pre ∪ {x}
     */
    public void insert(int x) { ... }

    /**
     * MODIFIES: this
     * EFFECTS: rimuove x da this, ossia this_post = this_pre \ {x}
     */
    public void remove(int x) { ... }

    // --- Observer ---

    /**
     * EFFECTS: restituisce true se x ∈ this, false altrimenti
     */
    public boolean contains(int x) { ... }

    /**
     * EFFECTS: restituisce la cardinalità di this
     */
    public int size() { ... }

    /**
     * REQUIRES: this non è vuoto (this.size() > 0)
     * EFFECTS: restituisce un elemento arbitrario di this
     */
    public int choose() { ... }

    /**
     * EFFECTS: restituisce una rappresentazione stringa di this
     */
    @Override
    public String toString() { ... }
}
```

### 6.5 Encapsulation (Incapsulamento)

L'**encapsulation** è il principio di nascondere la rappresentazione interna di un tipo,
esponendo solo le operazioni definite nella specifica.

Benefici:

- **Information hiding**: i dettagli interni sono irraggiungibili dall'esterno
- **Indipendenza dalla rappresentazione**: posso cambiare la struttura interna
  senza impattare il codice cliente
- **Integrità dei dati**: il tipo controlla come i propri dati vengono modificati

```java
// MALE: attributi pubblici, nessun incapsulamento
public class PuntoMale {
    public double x;  // chiunque può modificare!
    public double y;
}
PuntoMale p = new PuntoMale();
p.x = -999; // nessun controllo

// BENE: attributi privati con getter (e setter solo se necessario)
public class PuntoBene {
    private double x;
    private double y;

    public PuntoBene(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    // Se il tipo è immutabile: NESSUN setter!
    // Se il tipo è mutabile: setter con validazione
    public void setX(double x) {
        if (Double.isNaN(x)) throw new IllegalArgumentException("x non può essere NaN");
        this.x = x;
    }
}
```

### 6.6 Tipi Mutabili vs Immutabili

#### Tipo Mutabile

Lo stato dell'oggetto può cambiare dopo la creazione. Ha metodi mutator.

```java
// IntSet: tipo MUTABILE
IntSet s = new IntSet();
s.insert(1);   // s = {1}
s.insert(2);   // s = {1, 2}
s.remove(1);   // s = {2}
// Lo stato di s cambia nel tempo
```

Vantaggi dei tipi mutabili:

- Efficienza (modifica in-place)
- Modello naturale per entità che cambiano (es. conto bancario)

Svantaggi:

- Aliasing: più riferimenti allo stesso oggetto vedono le modifiche
- Difficile da ragionarci nei programmi concorrenti

#### Tipo Immutabile

Lo stato dell'oggetto NON può cambiare dopo la creazione. NON ha metodi mutator.
Le operazioni restituiscono nuovi oggetti.

```java
// Poly: tipo IMMUTABILE
Poly p1 = new Poly(3, 2);    // 3x²
Poly p2 = new Poly(1, 0);    // 1
Poly p3 = p1.add(p2);        // nuovo Poly: 3x² + 1
// p1 e p2 NON sono stati modificati

// String in Java è immutabile
String s1 = "ciao";
String s2 = s1.toUpperCase(); // s2 = "CIAO", s1 è ancora "ciao"
```

Vantaggi dei tipi immutabili:

- Nessun problema di aliasing
- Thread-safe nativamente
- Possono essere usati come chiavi di Map e elementi di Set

### 6.7 Esempio: Specifica di Poly

```java
/**
 * OVERVIEW: Poly rappresenta un polinomio immutabile a coefficienti interi.
 *           Un Poly tipico è c0 + c1*x + c2*x² + ... + cn*xⁿ.
 *           Il polinomio zero è rappresentato come 0 (grado 0).
 */
public class Poly {

    // --- Creator ---

    /**
     * EFFECTS: crea il polinomio zero (0)
     */
    public Poly() { ... }

    /**
     * REQUIRES: n >= 0
     * EFFECTS: crea il polinomio c*xⁿ
     */
    public Poly(int c, int n) { ... }

    // --- Producer ---

    /**
     * REQUIRES: q != null
     * EFFECTS: restituisce this + q
     */
    public Poly add(Poly q) { ... }

    /**
     * REQUIRES: q != null
     * EFFECTS: restituisce this * q
     */
    public Poly mul(Poly q) { ... }

    /**
     * EFFECTS: restituisce -this
     */
    public Poly minus() { ... }

    // --- Observer ---

    /**
     * EFFECTS: restituisce il grado del polinomio
     *          (0 per il polinomio zero)
     */
    public int degree() { ... }

    /**
     * REQUIRES: d >= 0
     * EFFECTS: restituisce il coefficiente del termine di grado d
     */
    public int coeff(int d) { ... }
}
```

### 6.8 Principio di Rep Independence

Il codice cliente deve dipendere SOLO dalla specifica del tipo, MAI dalla
rappresentazione interna. Questo permette di cambiare l'implementazione
senza impattare il codice che usa il tipo.

```java
// Specifica di IntSet (il cliente vede solo questa)
public class IntSet {
    public IntSet() { ... }
    public void insert(int x) { ... }
    public void remove(int x) { ... }
    public boolean contains(int x) { ... }
    public int size() { ... }
}

// Implementazione A: con ArrayList
private List<Integer> elementi = new ArrayList<>();

// Implementazione B: con boolean[] (bit array)
private boolean[] presenti = new boolean[1000];
private int dimensione = 0;

// Implementazione C: con HashSet
private Set<Integer> elementi = new HashSet<>();

// IL CODICE CLIENTE È LO STESSO per tutte le implementazioni:
IntSet s = new IntSet();
s.insert(42);
if (s.contains(42)) { ... }
```

## 7. Astrazione sui Tipi: Implementazione (L06 — PDJ Cap. 5.2-5.4)

### 7.1 Implementazione dei Costruttori

L'implementazione di un tipo astratto deve rispettare la specifica.
I costruttori inizializzano la rappresentazione interna dell'oggetto.

```java
public class IntSet {
    // Rappresentazione interna (rep)
    private List<Integer> elementi;

    /**
     * EFFECTS: crea un nuovo IntSet vuoto
     */
    public IntSet() {
        elementi = new ArrayList<>();
    }

    // Costruttore privato per uso interno (es. clone)
    private IntSet(List<Integer> els) {
        elementi = new ArrayList<>(els);
    }
}
```

### 7.2 Factory Methods

I **factory methods** sono metodi statici che creano istanze della classe,
offrendo maggiore flessibilità rispetto ai costruttori.

Vantaggi:

- Possono avere nomi significativi
- Possono restituire sottotipi
- Possono restituire istanze cachate
- Non sono obbligati a creare un nuovo oggetto ogni volta

```java
public class Poly {
    private int[] coefficienti;
    private int grado;

    // Costruttore privato
    private Poly(int[] coefficienti) {
        this.coefficienti = coefficienti;
        this.grado = coefficienti.length - 1;
    }

    // Factory method: polinomio zero
    public static Poly zero() {
        return new Poly(new int[]{0});
    }

    // Factory method: monomio c*x^n
    public static Poly monomio(int c, int n) {
        if (n < 0) throw new IllegalArgumentException("Grado negativo");
        int[] coeff = new int[n + 1];
        coeff[n] = c;
        return new Poly(coeff);
    }

    // Factory method: da array di coefficienti
    public static Poly of(int... coefficienti) {
        if (coefficienti.length == 0) return zero();
        return new Poly(coefficienti.clone());
    }
}
```

### 7.3 Override di equals()

Il metodo `equals()` ereditato da `Object` confronta i riferimenti (identità).
Per i tipi astratti, spesso vogliamo confrontare lo stato (equivalenza).

**Contratto di equals** (da Object):

1. **Riflessività**: `x.equals(x)` → true
2. **Simmetria**: `x.equals(y) ↔ y.equals(x)`
3. **Transitività**: se `x.equals(y)` e `y.equals(z)` allora `x.equals(z)`
4. **Consistenza**: chiamate ripetute danno lo stesso risultato
5. **Non-null**: `x.equals(null)` → false

```java
public class IntSet {
    private List<Integer> elementi;

    @Override
    public boolean equals(Object obj) {
        // 1. Controllo identità
        if (this == obj) return true;

        // 2. Controllo tipo (include null check)
        if (!(obj instanceof IntSet)) return false;

        // 3. Cast
        IntSet other = (IntSet) obj;

        // 4. Confronto stato
        if (this.elementi.size() != other.elementi.size()) return false;

        // Verifica che tutti gli elementi di this siano in other
        for (int e : this.elementi) {
            if (!other.elementi.contains(e)) return false;
        }
        return true;
    }
}
```

Pattern moderno con `instanceof` pattern matching (Java 16+):

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj instanceof IntSet other) {  // pattern matching
        return this.size() == other.size()
            && this.elementi.containsAll(other.elementi);
    }
    return false;
}
```

### 7.4 Override di hashCode()

**Regole di hashCode**:

1. Se `x.equals(y)` allora `x.hashCode() == y.hashCode()` (obbligatorio)
2. Se `!x.equals(y)`, è preferibile che `x.hashCode() != y.hashCode()` (per performance)
3. Consistenza: chiamate ripetute sullo stesso oggetto invariato danno stesso risultato

Se si ridefinisce `equals`, si DEVE ridefinire `hashCode`.

```java
public class Punto {
    private final double x;
    private final double y;

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Punto other)) return false;
        return Double.compare(this.x, other.x) == 0
            && Double.compare(this.y, other.y) == 0;
    }
}

// Per IntSet: l'hashCode deve essere indipendente dall'ordine degli elementi
public class IntSet {
    @Override
    public int hashCode() {
        int hash = 0;
        for (int e : elementi) {
            hash += Integer.hashCode(e); // somma: ordine-indipendente
        }
        return hash;
    }
}
```

### 7.5 Override di toString()

Il metodo `toString()` restituisce una rappresentazione testuale dell'oggetto.
Di default restituisce `NomeClasse@hashCode`.

```java
public class IntSet {
    @Override
    public String toString() {
        if (elementi.isEmpty()) return "IntSet: {}";
        StringBuilder sb = new StringBuilder("IntSet: {");
        for (int i = 0; i < elementi.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(elementi.get(i));
        }
        sb.append("}");
        return sb.toString();
    }
}

public class Poly {
    @Override
    public String toString() {
        if (grado == 0) return "" + coefficienti[0];
        StringBuilder sb = new StringBuilder();
        for (int i = grado; i >= 0; i--) {
            if (coefficienti[i] == 0) continue;
            if (sb.length() > 0 && coefficienti[i] > 0) sb.append(" + ");
            if (sb.length() > 0 && coefficienti[i] < 0) sb.append(" - ");
            int c = Math.abs(coefficienti[i]);
            if (i == 0 || c != 1) sb.append(sb.length() == 0 ? coefficienti[i] : c);
            if (i == 1) sb.append("x");
            if (i > 1) sb.append("x^").append(i);
        }
        return sb.toString();
    }
}
```

### 7.6 Override di clone() — Shallow vs Deep Copy

Il metodo `clone()` crea una copia dell'oggetto. Esistono due tipi di copia:

- **Shallow copy** (copia superficiale): copia i riferimenti, non gli oggetti puntati
- **Deep copy** (copia profonda): copia ricorsivamente anche gli oggetti referenziati

```java
public class IntSet implements Cloneable {
    private List<Integer> elementi;

    // SHALLOW COPY: lista condivisa (PERICOLOSO per tipi mutabili!)
    public IntSet shallowCopy() {
        IntSet copia = new IntSet();
        copia.elementi = this.elementi; // stesso oggetto List!
        return copia;
    }

    // DEEP COPY: lista indipendente
    @Override
    public IntSet clone() {
        try {
            IntSet copia = (IntSet) super.clone();
            copia.elementi = new ArrayList<>(this.elementi); // nuova lista
            return copia;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // non dovrebbe mai succedere
        }
    }
}

// Esempio del pericolo della shallow copy
IntSet originale = new IntSet();
originale.insert(1);
originale.insert(2);

IntSet shallow = originale.shallowCopy();
shallow.insert(3);
// ATTENZIONE: anche originale.contains(3) == true! Stessa lista!

IntSet deep = originale.clone();
deep.insert(4);
// originale.contains(4) == false → liste indipendenti
```

Per tipi immutabili, la shallow copy è sufficiente (il contenuto non può cambiare).

```java
public class Poly {
    private final int[] coefficienti;

    // Copy constructor (alternativa a clone)
    public Poly(Poly other) {
        this.coefficienti = other.coefficienti.clone(); // copia array
        this.grado = other.grado;
    }
}
```

### 7.7 L'interfaccia Cloneable

`Cloneable` è un'interfaccia marker (senza metodi).
Se una classe implementa `Cloneable`, `Object.clone()` esegue una shallow copy campo per campo.
Se non la implementa, `Object.clone()` lancia `CloneNotSupportedException`.

Problemi con clone():

- Design problematico (interfaccia marker, eccezione checked)
- Difficile da implementare correttamente per gerarchie di classi
- Alternativa preferita: **copy constructor** o **factory method**

```java
// Copy constructor (alternativa preferita a clone)
public class Punto {
    private final double x, y;

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Copy constructor
    public Punto(Punto altro) {
        this.x = altro.x;
        this.y = altro.y;
    }

    // Factory method per copia
    public static Punto copyOf(Punto altro) {
        return new Punto(altro.x, altro.y);
    }
}
```

### 7.8 Defensive Copying

Quando un tipo mutabile riceve o restituisce riferimenti a strutture dati interne,
bisogna fare copie difensive per proteggere l'incapsulamento.

```java
public class Registro {
    private List<String> studenti;

    public Registro(List<String> studenti) {
        // DEFENSIVE COPY in ingresso
        this.studenti = new ArrayList<>(studenti);
    }

    public List<String> getStudenti() {
        // DEFENSIVE COPY in uscita
        return new ArrayList<>(studenti);
        // oppure: return Collections.unmodifiableList(studenti);
    }
}

// Senza defensive copying:
List<String> lista = new ArrayList<>(List.of("Alice", "Bob"));
Registro r = new Registro(lista);
lista.add("Intruso");  // VIOLA l'incapsulamento! Modifica stato interno di r
```

---

## 8. Funzione di Astrazione e Invariante di Rappresentazione (L07 — PDJ Cap. 5.5+)

### 8.1 Spazio Concreto e Spazio Astratto

Per ogni tipo astratto distinguiamo due spazi:

- **Spazio concreto (C)**: l'insieme dei possibili stati della rappresentazione interna
  (i valori effettivi dei campi private)

- **Spazio astratto (A)**: l'insieme dei valori astratti che il tipo può rappresentare
  (ciò che il cliente "vede" attraverso la specifica)

Esempio per IntSet:

- Spazio concreto: tutte le possibili `List<Integer>` (incluse liste con duplicati)
- Spazio astratto: tutti i possibili insiemi di interi (senza duplicati)

### 8.2 Funzione di Astrazione (AF)

La **funzione di astrazione** AF: C → A mappa ogni stato concreto al valore astratto
che rappresenta.

Proprietà di AF:

- **Suriettiva**: ogni valore astratto è rappresentato da almeno uno stato concreto
- **Non iniettiva** (in generale): più stati concreti possono rappresentare lo stesso valore astratto

```java
public class IntSet {
    private List<Integer> elementi;

    // AF(c) = { c.elementi.get(i) | 0 <= i < c.elementi.size() }
    //
    // Esempio: se elementi = [3, 1, 2]
    //   AF([3, 1, 2]) = {1, 2, 3}
    //
    // AF non è iniettiva:
    //   AF([1, 2, 3]) = {1, 2, 3}  (stesso valore astratto)
    //   AF([3, 2, 1]) = {1, 2, 3}  (l'ordine non conta per un insieme)
}
```

```java
public class Poly {
    private int[] coefficienti;  // coefficienti[i] = coeff di x^i
    private int grado;

    // AF(c) = c.coefficienti[0] + c.coefficienti[1]*x + ... + c.coefficienti[c.grado]*x^c.grado
    //
    // Esempio: se coefficienti = [1, 0, 3], grado = 2
    //   AF([1, 0, 3], 2) = 1 + 3x²
}
```

### 8.3 Invariante di Rappresentazione (RI)

L'**invariante di rappresentazione** RI: C → boolean è un predicato che definisce
quali stati concreti sono "legali" (validi).

Solo gli stati concreti che soddisfano RI sono rappresentazioni valide del tipo astratto.

```java
public class IntSet {
    private List<Integer> elementi;

    // RI: elementi != null
    //     && per ogni i,j con 0 <= i < j < elementi.size():
    //        elementi.get(i) != elementi.get(j)
    // (no duplicati e lista non null)

    private boolean repOk() {
        if (elementi == null) return false;
        Set<Integer> visti = new HashSet<>();
        for (int e : elementi) {
            if (!visti.add(e)) return false; // duplicato trovato
        }
        return true;
    }
}
```

```java
public class Poly {
    private int[] coefficienti;
    private int grado;

    // RI: coefficienti != null
    //     && coefficienti.length >= 1
    //     && grado == coefficienti.length - 1
    //     && (grado == 0 || coefficienti[grado] != 0)
    // (il coefficiente di grado massimo non è zero, tranne per il polinomio zero)

    private boolean repOk() {
        if (coefficienti == null || coefficienti.length < 1) return false;
        if (grado != coefficienti.length - 1) return false;
        if (grado > 0 && coefficienti[grado] == 0) return false;
        return true;
    }
}
```

### 8.4 Il metodo repOk()

Il metodo `repOk()` verifica programmaticamente l'invariante di rappresentazione.
Si usa per il debugging: si può chiamare alla fine di ogni costruttore e mutator.

```java
public class IntSet {
    private List<Integer> elementi;

    private boolean repOk() {
        if (elementi == null) return false;
        for (int i = 0; i < elementi.size(); i++) {
            for (int j = i + 1; j < elementi.size(); j++) {
                if (elementi.get(i).equals(elementi.get(j))) return false;
            }
        }
        return true;
    }

    public IntSet() {
        elementi = new ArrayList<>();
        assert repOk();  // verifica RI dopo costruzione
    }

    public void insert(int x) {
        if (!contains(x)) {
            elementi.add(x);
        }
        assert repOk();  // verifica RI dopo mutazione
    }

    public void remove(int x) {
        elementi.remove(Integer.valueOf(x));
        assert repOk();  // verifica RI dopo mutazione
    }
}
```

### 8.5 Tre Livelli di Verifica

Per dimostrare che un'implementazione preserva il RI, verifichiamo tre livelli:

#### 1. Creator (Costruttori e Factory Methods)

Devono creare oggetti che soddisfano il RI.

```java
// Dopo il costruttore: elementi = [], nessun duplicato → RI soddisfatto ✓
public IntSet() {
    elementi = new ArrayList<>();
    assert repOk();
}
```

#### 2. Mutator e Producer (Metodi che modificano o creano)

Assumendo che il RI vale prima della chiamata, deve valere anche dopo.

```java
// Assumendo RI vale (no duplicati):
// insert aggiunge x solo se non presente → RI preservato ✓
public void insert(int x) {
    if (!contains(x)) {     // verifica prima di aggiungere
        elementi.add(x);
    }
    assert repOk();
}
```

#### 3. Observer (Metodi che leggono)

Assumendo che il RI vale, devono restituire risultati consistenti con la AF.

```java
// Assumendo RI vale:
// size() restituisce il numero di elementi distinti (= dimensione lista senza duplicati)
// Questo è consistente con AF che mappa a un insieme
public int size() {
    return elementi.size(); // corretto perché RI garantisce no duplicati
}
```

### 8.6 Esempio Completo con AF e RI

```java
/**
 * OVERVIEW: Intervallo mutabile [lo, hi] di interi.
 *           Un Intervallo tipico è [lo, hi] con lo <= hi.
 */
public class Intervallo {
    private int lo;
    private int hi;

    // AF(c) = l'insieme { x ∈ Z | c.lo <= x <= c.hi }
    // RI: lo <= hi

    private boolean repOk() {
        return lo <= hi;
    }

    /**
     * REQUIRES: a <= b
     * EFFECTS: crea l'intervallo [a, b]
     */
    public Intervallo(int a, int b) {
        if (a > b) throw new IllegalArgumentException("a > b");
        this.lo = a;
        this.hi = b;
        assert repOk();
    }

    /**
     * MODIFIES: this
     * EFFECTS: estende this per includere x
     *          this_post = [min(lo, x), max(hi, x)]
     */
    public void estendi(int x) {
        if (x < lo) lo = x;
        if (x > hi) hi = x;
        assert repOk(); // RI preservato ✓
    }

    /**
     * EFFECTS: restituisce true se x ∈ this
     */
    public boolean contiene(int x) {
        return lo <= x && x <= hi;
    }

    /**
     * EFFECTS: restituisce la dimensione dell'intervallo (hi - lo + 1)
     */
    public int dimensione() {
        return hi - lo + 1;
    }

    @Override
    public String toString() {
        return "[" + lo + ", " + hi + "]";
    }
}
```

### 8.7 Record Types in Java (Java 16+)

I **record** sono classi immutabili concise, con AF e RI impliciti.

```java
// Equivale a: classe immutabile con campi final, costruttore, equals, hashCode, toString
public record Punto(double x, double y) {
    // Costruttore compatto per validazione (impone RI)
    public Punto {
        if (Double.isNaN(x) || Double.isNaN(y)) {
            throw new IllegalArgumentException("Coordinate NaN");
        }
    }

    // Metodi aggiuntivi
    public double distanzaDaOrigine() {
        return Math.sqrt(x * x + y * y);
    }

    public Punto trasla(double dx, double dy) {
        return new Punto(x + dx, y + dy); // nuovo oggetto (immutabile)
    }
}

// Il compilatore genera automaticamente:
// - costruttore Punto(double x, double y)
// - metodi x() e y() (accessor, non getX/getY)
// - equals(), hashCode(), toString()

Punto p = new Punto(3, 4);
System.out.println(p);        // Punto[x=3.0, y=4.0]
System.out.println(p.x());    // 3.0
Punto p2 = new Punto(3, 4);
System.out.println(p.equals(p2)); // true
```

```java
// Record con validazione complessa
public record Intervallo(int lo, int hi) {
    // RI: lo <= hi
    public Intervallo {
        if (lo > hi) throw new IllegalArgumentException("lo > hi");
    }

    public boolean contiene(int x) {
        return lo <= x && x <= hi;
    }

    public int dimensione() {
        return hi - lo + 1;
    }
}
```

---

## 9. IntSet e Poly (L08 — PDJ Cap. 5.1-5.7)

### 9.1 IntSet — Implementazione Completa

IntSet è un esempio archetipico di tipo astratto **mutabile** nel corso PDJ.

```java
/**
 * OVERVIEW: IntSet è un insieme mutabile e illimitato di interi.
 *           Un IntSet tipico è {x1, x2, ..., xn}.
 */
public class IntSet {

    // Rappresentazione
    private List<Integer> elementi;

    // AF(c) = { c.elementi.get(i) | 0 <= i < c.elementi.size() }
    // RI: c.elementi != null
    //     && per ogni 0 <= i < j < c.elementi.size():
    //        !c.elementi.get(i).equals(c.elementi.get(j))

    private boolean repOk() {
        if (elementi == null) return false;
        Set<Integer> seen = new HashSet<>();
        for (int e : elementi) {
            if (!seen.add(e)) return false;
        }
        return true;
    }

    // --- Creator ---

    /**
     * EFFECTS: inizializza this come insieme vuoto {}
     */
    public IntSet() {
        elementi = new ArrayList<>();
        assert repOk();
    }

    // --- Mutator ---

    /**
     * MODIFIES: this
     * EFFECTS: aggiunge x a this
     *          this_post = this_pre ∪ {x}
     */
    public void insert(int x) {
        if (!contains(x)) {
            elementi.add(x);
        }
        assert repOk();
    }

    /**
     * MODIFIES: this
     * EFFECTS: rimuove x da this
     *          this_post = this_pre \ {x}
     */
    public void remove(int x) {
        int index = elementi.indexOf(x);
        if (index >= 0) {
            // Ottimizzazione: swap con ultimo e rimuovi ultimo (O(1) vs O(n))
            int last = elementi.size() - 1;
            elementi.set(index, elementi.get(last));
            elementi.remove(last);
        }
        assert repOk();
    }

    // --- Observer ---

    /**
     * EFFECTS: restituisce true se x ∈ this
     */
    public boolean contains(int x) {
        return elementi.contains(x);
    }

    /**
     * EFFECTS: restituisce |this|
     */
    public int size() {
        return elementi.size();
    }

    /**
     * EFFECTS: restituisce true se this è vuoto
     */
    public boolean isEmpty() {
        return elementi.isEmpty();
    }

    /**
     * REQUIRES: this non è vuoto (size() > 0)
     * EFFECTS: restituisce un elemento arbitrario di this
     */
    public int choose() {
        if (isEmpty()) throw new IllegalStateException("IntSet vuoto");
        return elementi.get(elementi.size() - 1);
    }

    // --- Override ---

    @Override
    public String toString() {
        return "IntSet: " + elementi.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof IntSet other)) return false;
        if (this.size() != other.size()) return false;
        for (int e : this.elementi) {
            if (!other.contains(e)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int e : elementi) hash += Integer.hashCode(e);
        return hash;
    }

    /**
     * EFFECTS: restituisce un iteratore sugli elementi di this
     */
    public Iterator<Integer> elements() {
        return Collections.unmodifiableList(
            new ArrayList<>(elementi)
        ).iterator();
    }
}
```

### 9.2 Utilizzo di IntSet

```java
IntSet s = new IntSet();
System.out.println(s);            // IntSet: []
System.out.println(s.isEmpty());  // true

s.insert(3);
s.insert(7);
s.insert(3);   // duplicato, ignorato
s.insert(1);
System.out.println(s);            // IntSet: [3, 7, 1]
System.out.println(s.size());     // 3
System.out.println(s.contains(7)); // true
System.out.println(s.contains(5)); // false

s.remove(7);
System.out.println(s);            // IntSet: [3, 1]
System.out.println(s.choose());   // 1 (ultimo elemento)

// Iterazione
Iterator<Integer> it = s.elements();
while (it.hasNext()) {
    System.out.println(it.next());
}
```

### 9.3 Poly — Implementazione Completa

Poly è un esempio archetipico di tipo astratto **immutabile** nel corso PDJ.

```java
/**
 * OVERVIEW: Poly rappresenta un polinomio immutabile a coefficienti interi.
 *           Un Poly tipico è c0 + c1*x + c2*x² + ... + cn*xⁿ.
 */
public class Poly {

    // Rappresentazione
    private final int[] trms;  // trms[i] = coefficiente di x^i
    private final int deg;     // grado del polinomio

    // AF(c) = c.trms[0] + c.trms[1]*x + c.trms[2]*x² + ... + c.trms[c.deg]*x^c.deg
    // RI: c.trms != null
    //     && c.trms.length >= 1
    //     && c.deg == c.trms.length - 1
    //     && (c.deg == 0 || c.trms[c.deg] != 0)

    private boolean repOk() {
        if (trms == null || trms.length < 1) return false;
        if (deg != trms.length - 1) return false;
        if (deg > 0 && trms[deg] == 0) return false;
        return true;
    }

    // --- Creator ---

    /**
     * EFFECTS: inizializza this come polinomio zero (0)
     */
    public Poly() {
        trms = new int[]{0};
        deg = 0;
        assert repOk();
    }

    /**
     * REQUIRES: n >= 0
     * EFFECTS: inizializza this come monomio c*x^n
     *          se c == 0, this è il polinomio zero
     */
    public Poly(int c, int n) {
        if (n < 0) throw new IllegalArgumentException("Grado negativo");
        if (c == 0) {
            trms = new int[]{0};
            deg = 0;
        } else {
            trms = new int[n + 1];
            trms[n] = c;
            deg = n;
        }
        assert repOk();
    }

    // Costruttore privato
    private Poly(int[] coefficienti) {
        // Trova il grado reale (elimina zeri in coda)
        int d = coefficienti.length - 1;
        while (d > 0 && coefficienti[d] == 0) d--;
        trms = new int[d + 1];
        System.arraycopy(coefficienti, 0, trms, 0, d + 1);
        deg = d;
        assert repOk();
    }

    // --- Producer ---

    /**
     * REQUIRES: q != null
     * EFFECTS: restituisce this + q
     */
    public Poly add(Poly q) {
        int maxDeg = Math.max(this.deg, q.deg);
        int[] risultato = new int[maxDeg + 1];
        for (int i = 0; i <= maxDeg; i++) {
            risultato[i] = this.coeff(i) + q.coeff(i);
        }
        return new Poly(risultato);
    }

    /**
     * EFFECTS: restituisce this - q
     */
    public Poly sub(Poly q) {
        return add(q.minus());
    }

    /**
     * REQUIRES: q != null
     * EFFECTS: restituisce this * q
     */
    public Poly mul(Poly q) {
        if (this.deg == 0 && this.trms[0] == 0) return new Poly();
        if (q.deg == 0 && q.trms[0] == 0) return new Poly();
        int[] risultato = new int[this.deg + q.deg + 1];
        for (int i = 0; i <= this.deg; i++) {
            for (int j = 0; j <= q.deg; j++) {
                risultato[i + j] += this.trms[i] * q.trms[j];
            }
        }
        return new Poly(risultato);
    }

    /**
     * EFFECTS: restituisce -this
     */
    public Poly minus() {
        int[] risultato = new int[trms.length];
        for (int i = 0; i < trms.length; i++) {
            risultato[i] = -trms[i];
        }
        return new Poly(risultato);
    }

    // --- Observer ---

    /**
     * EFFECTS: restituisce il grado di this
     */
    public int degree() {
        return deg;
    }

    /**
     * REQUIRES: d >= 0
     * EFFECTS: restituisce il coefficiente del termine x^d
     */
    public int coeff(int d) {
        if (d < 0) throw new IllegalArgumentException("Grado negativo");
        if (d > deg) return 0;
        return trms[d];
    }

    @Override
    public String toString() {
        if (deg == 0) return "" + trms[0];
        StringBuilder sb = new StringBuilder();
        for (int i = deg; i >= 0; i--) {
            if (trms[i] == 0) continue;
            if (sb.length() > 0) {
                sb.append(trms[i] > 0 ? " + " : " - ");
            } else if (trms[i] < 0) {
                sb.append("-");
            }
            int absC = Math.abs(trms[i]);
            if (i == 0 || absC != 1) sb.append(absC);
            if (i == 1) sb.append("x");
            else if (i > 1) sb.append("x^").append(i);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Poly other)) return false;
        return Arrays.equals(this.trms, other.trms);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(trms);
    }
}
```

### 9.4 Utilizzo di Poly

```java
Poly zero = new Poly();            // 0
Poly p1 = new Poly(3, 2);          // 3x²
Poly p2 = new Poly(1, 0);          // 1
Poly p3 = new Poly(2, 1);          // 2x

Poly somma = p1.add(p3).add(p2);   // 3x² + 2x + 1
System.out.println(somma);          // 3x^2 + 2x + 1
System.out.println(somma.degree()); // 2
System.out.println(somma.coeff(2)); // 3
System.out.println(somma.coeff(1)); // 2
System.out.println(somma.coeff(0)); // 1

// Immutabilità: p1 NON è stato modificato
System.out.println(p1);             // 3x^2

// Prodotto
Poly prodotto = p1.mul(p3);         // 3x² * 2x = 6x³
System.out.println(prodotto);       // 6x^3

// Negazione
Poly negato = somma.minus();        // -3x² - 2x - 1
```

### 9.5 AF e RI a confronto

| Aspetto    | IntSet                          | Poly                            |
| ---------- | ------------------------------- | ------------------------------- |
| Mutabilità | Mutabile                        | Immutabile                      |
| Rep        | List&lt;Integer&gt; elementi    | int[] trms, int deg             |
| AF         | Insieme degli elementi in lista | Polinomio con coefficienti trms |
| RI         | No duplicati, lista non null    | trms non null, grado coerente   |
| Mutator    | insert, remove                  | Nessuno                         |
| Producer   | Nessuno                         | add, sub, mul, minus            |

---

## 10. Gerarchie e Principio di Sostituzione (L09 — PDJ Cap. 7-7.7)

### 10.1 Gerarchia di Tipi

In Java, le gerarchie di tipi si costruiscono con **extends** (classi) e **implements** (interfacce).

```java
// Gerarchia semplice
public abstract class Forma {
    public abstract double area();
    public abstract double perimetro();
}

public class Cerchio extends Forma {
    private double raggio;

    public Cerchio(double raggio) {
        this.raggio = raggio;
    }

    @Override
    public double area() {
        return Math.PI * raggio * raggio;
    }

    @Override
    public double perimetro() {
        return 2 * Math.PI * raggio;
    }
}

public class Rettangolo extends Forma {
    private double base, altezza;

    public Rettangolo(double base, double altezza) {
        this.base = base;
        this.altezza = altezza;
    }

    @Override
    public double area() {
        return base * altezza;
    }

    @Override
    public double perimetro() {
        return 2 * (base + altezza);
    }
}
```

### 10.2 Principio di Sostituzione di Liskov (LSP)

Il **Liskov Substitution Principle (LSP)** afferma che:

> Se S è un sottotipo di T, allora gli oggetti di tipo T possono essere sostituiti
> con oggetti di tipo S senza alterare la correttezza del programma.

In altre parole: ovunque ci sia un `T`, posso mettere un `S` e il programma funziona correttamente.

```java
// Se LSP è rispettato, questo codice funziona con qualsiasi Forma
public static void stampaInfo(Forma f) {
    System.out.println("Area: " + f.area());
    System.out.println("Perimetro: " + f.perimetro());
}

stampaInfo(new Cerchio(5));       // funziona
stampaInfo(new Rettangolo(3, 4)); // funziona
```

### 10.3 Le tre regole del LSP

#### Regola 1: Regola della Firma (Signature Rule)

Il sottotipo deve supportare tutti i metodi del supertipo, con firme compatibili:

- **Controvarianza** degli argomenti: il sottotipo può accettare argomenti più generali
- **Covarianza** del tipo di ritorno: il sottotipo può restituire un tipo più specifico

```java
class Supertipo {
    public Number elabora(Integer input) { return 0; }
}

class Sottotipo extends Supertipo {
    // Covarianza ritorno: Integer è sottotipo di Number → OK
    @Override
    public Integer elabora(Integer input) { return 42; }
}
```

#### Regola 2: Regola dei Metodi (Methods Rule)

Il sottotipo deve soddisfare la specifica dei metodi del supertipo:

- **Precondizioni**: il sottotipo può avere precondizioni più deboli (uguali o meno restrittive)
- **Postcondizioni**: il sottotipo può avere postcondizioni più forti (uguali o più restrittive)

```java
class Contenitore {
    /**
     * REQUIRES: x >= 0
     * EFFECTS: restituisce true se x è valido
     */
    public boolean isValido(int x) {
        return x >= 0;
    }
}

class ContenitoreEsteso extends Contenitore {
    // OK: precondizione più debole (accetta anche negativi)
    // OK: postcondizione più forte (verifica range più stretto per i positivi)
    @Override
    public boolean isValido(int x) {
        // accetta tutti gli input (precondizione più debole)
        return x >= 0 && x <= 100; // postcondizione più forte per i validi
    }
}
```

#### Regola 3: Regola delle Proprietà (Properties Rule)

Il sottotipo deve preservare le invarianti del supertipo.
Se il supertipo ha una proprietà (es. immutabilità), il sottotipo deve mantenerla.

```java
// VIOLAZIONE: il supertipo è immutabile, il sottotipo introduce mutazione
public class StringaImmutabile {
    protected final String valore;

    public StringaImmutabile(String s) {
        this.valore = s;
    }

    public String getValore() { return valore; }
}

// VIOLAZIONE della regola delle proprietà!
// Se il supertipo garantisce immutabilità, il sottotipo non può romperla
```

### 10.4 Classi Astratte

Una **classe astratta** non può essere istanziata direttamente.
Può contenere metodi astratti (senza implementazione) e concreti.

```java
public abstract class Veicolo {
    private String targa;

    public Veicolo(String targa) {
        this.targa = targa;
    }

    // Metodo concreto: ereditato da tutti i sottotipi
    public String getTarga() { return targa; }

    // Metodo astratto: DEVE essere implementato dai sottotipi concreti
    public abstract double calcolaBollo();

    // Metodo concreto con comportamento di default
    public String tipo() {
        return this.getClass().getSimpleName();
    }
}

public class Auto extends Veicolo {
    private int cavalli;

    public Auto(String targa, int cavalli) {
        super(targa);
        this.cavalli = cavalli;
    }

    @Override
    public double calcolaBollo() {
        return cavalli * 3.0;
    }
}

public class Moto extends Veicolo {
    private int cilindrata;

    public Moto(String targa, int cilindrata) {
        super(targa);
        this.cilindrata = cilindrata;
    }

    @Override
    public double calcolaBollo() {
        return cilindrata * 0.05;
    }
}
```

### 10.5 Interfacce

Un'**interfaccia** definisce un contratto che le classi implementatrici devono rispettare.
Può contenere:

- Metodi astratti (impliciti)
- Metodi default (Java 8+)
- Metodi statici
- Costanti (public static final)

```java
public interface Comparable<T> {
    int compareTo(T other);
}

public interface Stampabile {
    void stampa();

    // Metodo default (Java 8+)
    default void stampaConBordo() {
        System.out.println("=================");
        stampa();
        System.out.println("=================");
    }
}

// Implementazione multipla di interfacce
public class Studente implements Comparable<Studente>, Stampabile {
    private String nome;
    private double media;

    public Studente(String nome, double media) {
        this.nome = nome;
        this.media = media;
    }

    @Override
    public int compareTo(Studente other) {
        return Double.compare(this.media, other.media);
    }

    @Override
    public void stampa() {
        System.out.println(nome + " (media: " + media + ")");
    }
}
```

### 10.6 Extends vs Implements

| Caratteristica        | extends (classi) | implements (interfacce) |
| --------------------- | ---------------- | ----------------------- |
| Ereditarietà multipla | No (solo 1)      | Sì (multiple)           |
| Stato (campi)         | Sì               | Solo costanti           |
| Costruttori           | Sì               | No                      |
| Metodi concreti       | Sì               | Solo default            |
| Keyword               | extends          | implements              |

```java
// Una classe può estendere UNA classe e implementare MOLTE interfacce
public class ArrayList<E> extends AbstractList<E>
    implements List<E>, RandomAccess, Cloneable, Serializable {
    // ...
}
```

### 10.7 Dispatching Dinamico nelle Gerarchie

```java
Veicolo[] flotta = {
    new Auto("AB123CD", 100),
    new Moto("EF456GH", 600),
    new Auto("IJ789KL", 150)
};

double totaleBollo = 0;
for (Veicolo v : flotta) {
    // Dispatching dinamico: il metodo corretto viene chiamato
    // basandosi sul tipo concreto di ciascun veicolo
    System.out.println(v.tipo() + " " + v.getTarga() + ": " + v.calcolaBollo());
    totaleBollo += v.calcolaBollo();
}
System.out.println("Totale bollo: " + totaleBollo);
```

---

## 11. Specifica, Ereditarietà e Composizione (L10 — PDJ Cap. 7.8+)

### 11.1 Visibilità nelle Gerarchie

I modificatori di accesso controllano la visibilità dei membri nelle gerarchie:

```java
public class Base {
    public int pubblico;        // visibile ovunque
    protected int protetto;     // visibile nelle sottoclassi e nello stesso package
    int packageLevel;           // visibile solo nello stesso package
    private int privato;        // visibile solo in Base
}

public class Derivata extends Base {
    public void metodo() {
        pubblico = 1;    // OK
        protetto = 2;    // OK (sottoclasse)
        // packageLevel = 3;  // OK solo se stesso package
        // privato = 4;   // ERRORE: non visibile
    }
}
```

Regola di overriding: il metodo ridefinito non può avere visibilità più restrittiva.

```java
class Animale {
    protected void verso() { }
}

class Cane extends Animale {
    @Override
    public void verso() { }     // OK: public è meno restrittivo di protected

    // @Override
    // private void verso() { }  // ERRORE: private è più restrittivo
}
```

### 11.2 Il Problema Square/Rectangle

Un classico esempio di violazione del LSP:

```java
public class Rettangolo {
    protected double base;
    protected double altezza;

    public void setBase(double b) { this.base = b; }
    public void setAltezza(double h) { this.altezza = h; }
    public double area() { return base * altezza; }
}

// VIOLAZIONE LSP: Quadrato non è un buon sottotipo di Rettangolo
public class Quadrato extends Rettangolo {
    @Override
    public void setBase(double b) {
        this.base = b;
        this.altezza = b; // forza altezza = base
    }

    @Override
    public void setAltezza(double h) {
        this.base = h;
        this.altezza = h; // forza base = altezza
    }
}

// Il problema:
Rettangolo r = new Quadrato();
r.setBase(5);
r.setAltezza(3);
// Per un Rettangolo ci aspettiamo area = 5 * 3 = 15
// Ma per un Quadrato: area = 3 * 3 = 9 → SORPRESA!
```

Soluzione: **non usare ereditarietà Quadrato extends Rettangolo**.
Usare composizione o un'interfaccia comune Forma.

### 11.3 Composizione vs Ereditarietà

**Ereditarietà** ("is-a"): il sottotipo È un supertipo.
**Composizione** ("has-a"): un tipo HA un'istanza di un altro tipo.

```java
// EREDITARIETÀ (is-a): un Cane È un Animale
public class Cane extends Animale {
    // eredita tutti i metodi e attributi di Animale
}

// COMPOSIZIONE (has-a): un'Auto HA un Motore
public class Auto {
    private Motore motore;  // composizione
    private List<Ruota> ruote;

    public Auto(Motore motore) {
        this.motore = motore;
        this.ruote = new ArrayList<>();
    }

    public void accendi() {
        motore.avvia(); // delega al motore
    }
}
```

### 11.4 Quando usare Ereditarietà vs Composizione

**Preferisci la composizione** a meno che:

1. La relazione "is-a" sia genuina e permanente
2. Il sottotipo rispetti pienamente il LSP
3. Il supertipo sia progettato per l'ereditarietà (non sia un dettaglio implementativo)

```java
// CATTIVO USO DI EREDITARIETÀ: Stack che estende ArrayList
// Stack NON è una ArrayList (viola is-a)
public class StackMale<E> extends ArrayList<E> {
    public void push(E item) { add(item); }
    public E pop() { return remove(size() - 1); }
    // MA: il cliente può usare add(index, element), remove(index), get(index)...
    // che violano la semantica di uno Stack!
}

// BUON USO DI COMPOSIZIONE: Stack che usa ArrayList internamente
public class StackBene<E> {
    private ArrayList<E> elementi = new ArrayList<>();

    public void push(E item) { elementi.add(item); }
    public E pop() {
        if (isEmpty()) throw new IllegalStateException("Stack vuoto");
        return elementi.remove(elementi.size() - 1);
    }
    public E peek() {
        if (isEmpty()) throw new IllegalStateException("Stack vuoto");
        return elementi.get(elementi.size() - 1);
    }
    public boolean isEmpty() { return elementi.isEmpty(); }
    public int size() { return elementi.size(); }
    // I metodi di ArrayList NON sono esposti al cliente
}
```

### 11.5 Equality nelle Gerarchie

Implementare equals() correttamente nelle gerarchie è complesso.
Bisogna mantenere simmetria e transitività.

```java
// Approccio 1: usare getClass() (più restrittivo)
public class Punto {
    private final int x, y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Punto punto = (Punto) o;
        return x == punto.x && y == punto.y;
    }
}

public class PuntoColorato extends Punto {
    private final String colore;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PuntoColorato that = (PuntoColorato) o;
        return Objects.equals(colore, that.colore);
    }
}

// Con getClass(): un Punto non è MAI uguale a un PuntoColorato
// (anche se hanno stesse coordinate)

// Approccio 2: usare instanceof + canEqual (pattern Scala)
// Più flessibile ma più complesso
```

### 11.6 Delegation Pattern

Il **delegation pattern** è una forma di composizione dove un oggetto delega
operazioni a un altro oggetto.

```java
public interface Printer {
    void print(String messaggio);
}

public class ConsolePrinter implements Printer {
    @Override
    public void print(String messaggio) {
        System.out.println(messaggio);
    }
}

public class FilePrinter implements Printer {
    private final String percorso;

    public FilePrinter(String percorso) {
        this.percorso = percorso;
    }

    @Override
    public void print(String messaggio) {
        // scrive su file
        try (FileWriter fw = new FileWriter(percorso, true)) {
            fw.write(messaggio + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

// Delegation: Logger delega la stampa al Printer
public class Logger {
    private Printer printer; // delegato

    public Logger(Printer printer) {
        this.printer = printer;
    }

    public void log(String messaggio) {
        String timestamp = java.time.LocalDateTime.now().toString();
        printer.print("[" + timestamp + "] " + messaggio); // delega
    }

    public void setPrinter(Printer printer) {
        this.printer = printer; // posso cambiare strategia a runtime
    }
}

// Utilizzo
Logger logger = new Logger(new ConsolePrinter());
logger.log("Avvio applicazione");

logger.setPrinter(new FilePrinter("app.log"));
logger.log("Errore critico"); // ora scrive su file
```

---

## 12. Astrazione sull'Iterazione (L11 — PDJ Cap. 6-6.4)

### 12.1 Interfaccia Iterator

L'interfaccia `Iterator<E>` del package `java.util` definisce il protocollo
per attraversare una collezione di elementi:

```java
public interface Iterator<E> {
    /**
     * EFFECTS: restituisce true se ci sono altri elementi da visitare
     */
    boolean hasNext();

    /**
     * REQUIRES: hasNext() == true
     * MODIFIES: this
     * EFFECTS: restituisce il prossimo elemento e avanza l'iteratore
     */
    E next();

    /**
     * REQUIRES: next() è stato chiamato almeno una volta
     * MODIFIES: this e la collezione sottostante
     * EFFECTS: rimuove l'ultimo elemento restituito da next()
     */
    default void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
```

### 12.2 Interfaccia Iterable

L'interfaccia `Iterable<E>` permette a una classe di essere usata nel for-each:

```java
public interface Iterable<E> {
    Iterator<E> iterator();
}

// Se una classe implementa Iterable, si può usare nel for-each
public class MiaCollezione<E> implements Iterable<E> {
    private List<E> elementi = new ArrayList<>();

    public void aggiungi(E e) { elementi.add(e); }

    @Override
    public Iterator<E> iterator() {
        return elementi.iterator(); // delega alla lista interna
    }
}

// Utilizzo con for-each
MiaCollezione<String> col = new MiaCollezione<>();
col.aggiungi("primo");
col.aggiungi("secondo");

for (String s : col) {  // possibile perché implementa Iterable
    System.out.println(s);
}
```

### 12.3 Implementazione di un Iteratore Custom

```java
/**
 * OVERVIEW: IntSet è un insieme mutabile di interi.
 */
public class IntSet implements Iterable<Integer> {
    private List<Integer> elementi;

    // ... costruttore, insert, remove, etc ...

    @Override
    public Iterator<Integer> iterator() {
        return new IntSetIterator();
    }

    // Iteratore come classe interna privata
    private class IntSetIterator implements Iterator<Integer> {
        private int indice = 0;

        @Override
        public boolean hasNext() {
            return indice < elementi.size();
        }

        @Override
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return elementi.get(indice++);
        }
    }
}

// Utilizzo
IntSet s = new IntSet();
s.insert(1); s.insert(2); s.insert(3);

for (int x : s) {
    System.out.println(x);
}
```

### 12.4 Iteratore come Generatore

Un iteratore può generare elementi "al volo" senza averli memorizzati:

```java
/**
 * Iteratore che genera i primi n numeri di Fibonacci.
 */
public class FibonacciIterator implements Iterator<Long> {
    private final int limite;
    private int contatore = 0;
    private long a = 0, b = 1;

    public FibonacciIterator(int n) {
        this.limite = n;
    }

    @Override
    public boolean hasNext() {
        return contatore < limite;
    }

    @Override
    public Long next() {
        if (!hasNext()) throw new NoSuchElementException();
        long risultato = a;
        long temp = a + b;
        a = b;
        b = temp;
        contatore++;
        return risultato;
    }
}

// Classe Iterable wrapper
public class Fibonacci implements Iterable<Long> {
    private final int n;

    public Fibonacci(int n) { this.n = n; }

    @Override
    public Iterator<Long> iterator() {
        return new FibonacciIterator(n);
    }
}

// Utilizzo
for (long f : new Fibonacci(10)) {
    System.out.print(f + " "); // 0 1 1 2 3 5 8 13 21 34
}
```

### 12.5 Iteratore con Filtro

```java
/**
 * Iteratore che filtra gli elementi pari da un altro iteratore.
 */
public class PariIterator implements Iterator<Integer> {
    private final Iterator<Integer> sorgente;
    private Integer prossimo = null;

    public PariIterator(Iterator<Integer> sorgente) {
        this.sorgente = sorgente;
        avanza();
    }

    private void avanza() {
        prossimo = null;
        while (sorgente.hasNext()) {
            int candidato = sorgente.next();
            if (candidato % 2 == 0) {
                prossimo = candidato;
                return;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return prossimo != null;
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        Integer risultato = prossimo;
        avanza();
        return risultato;
    }
}
```

### 12.6 Specifica di Iteratori

Nella specifica di un metodo che restituisce un iteratore:

```java
/**
 * OVERVIEW: IntSet è un insieme mutabile di interi.
 */
public class IntSet implements Iterable<Integer> {

    /**
     * EFFECTS: restituisce un iteratore che produce tutti gli elementi
     *          di this (ciascuno esattamente una volta), in ordine arbitrario.
     *
     *          REQUIRES: this non deve essere modificato durante l'iterazione
     */
    @Override
    public Iterator<Integer> iterator() {
        return new IntSetIterator();
    }

    /**
     * EFFECTS: restituisce un iteratore che produce gli elementi
     *          di this in ordine crescente
     */
    public Iterator<Integer> sortedIterator() {
        List<Integer> sorted = new ArrayList<>(elementi);
        Collections.sort(sorted);
        return sorted.iterator();
    }
}
```

### 12.7 ConcurrentModificationException

Se si modifica una collezione durante l'iterazione (senza usare `Iterator.remove()`),
si ottiene un `ConcurrentModificationException`:

```java
List<String> lista = new ArrayList<>(List.of("a", "b", "c", "d"));

// ERRORE: modifica durante for-each
for (String s : lista) {
    if (s.equals("b")) {
        lista.remove(s); // ConcurrentModificationException!
    }
}

// CORRETTO: uso di Iterator.remove()
Iterator<String> it = lista.iterator();
while (it.hasNext()) {
    String s = it.next();
    if (s.equals("b")) {
        it.remove(); // OK: rimozione attraverso l'iteratore
    }
}

// ALTERNATIVA: removeIf (Java 8+)
lista.removeIf(s -> s.equals("b"));
```

### 12.8 Delegazione a Collection Interne

Il pattern più comune: l'iteratore del tipo astratto delega
all'iteratore della collezione interna.

```java
public class Registro implements Iterable<String> {
    private final List<String> studenti = new ArrayList<>();

    public void iscrivi(String nome) { studenti.add(nome); }

    // Delegazione: il nostro iteratore è quello della lista
    // MA restituiamo una copia non modificabile per proteggere la rep
    @Override
    public Iterator<String> iterator() {
        return Collections.unmodifiableList(
            new ArrayList<>(studenti)
        ).iterator();
    }
}
```

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
