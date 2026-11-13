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
