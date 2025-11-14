# Guida Completa Java

Guida strutturata in stile **Markdown per GitHub**, mantenendo il contenuto originale e arricchendo con spiegazioni aggiuntive + differenze tra Array, ArrayList, List, Map, Set.

---

## Indice

1. [Introduzione a Java](#introduzione-a-java)
2. [Installazione e Setup](#installazione-e-setup)
3. [Concetti Base](#concetti-base)
4. [Strutture di Controllo](#strutture-di-controllo)
5. [Classi e Oggetti](#classi-e-oggetti)
6. [Array e Collezioni](#array-e-collezioni)
7. [Ereditarietà e Polimorfismo](#ereditarietà-e-polimorfismo)
8. [Interfacce e Classi Astratte](#interfacce-e-classi-astratte)
9. [Gestione delle Eccezioni](#gestione-delle-eccezioni)
10. [Input/Output](#inputoutput)
11. [Best Practices](#best-practices)
12. [Risorse Utili](#risorse-utili)

---

## Introduzione a Java

Java è un linguaggio orientato agli oggetti creato da Sun Microsystems (ora Oracle). Le sue caratteristiche principali sono:

- **WORA**: Write Once, Run Anywhere
- **Garbage Collection** automatica
- **Tipizzazione statica e forte**
- **OOP completo** (incapsulamento, ereditarietà, polimorfismo)
- Grande ecosistema e libreria standard

---

## Installazione e Setup

### 1. Installazione JDK 21

Scarica da [Oracle](https://www.oracle.com/java/technologies/downloads/#java21) o Adoptium.

Configura le variabili d'ambiente:

- `JAVA_HOME`
- `PATH` → aggiungi `%JAVA_HOME%/bin`

### 2. Verifica

```bash
java -version
javac -version
```

### Novità Java 21

- Pattern Matching per `switch`
- Record Patterns
- Virtual Threads (Project Loom)
- Sequenced Collections
- String Templates (Preview)

---

## Concetti Base

### Struttura base di un programma

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```

### Tipi primitivi

- byte, short, int, long
- float, double
- boolean
- char

### Variabili

```java
int x = 10;
final double PI = 3.14;
```

---

## Strutture di Controllo

### If, Else, Switch

```java
if (x > 0) {...}
else {...}
```

### Cicli

```java
for (int i = 0; i < 10; i++) {...}
while(cond) {...}
for (Tipo x : collezione) {...}
```

---

## Classi e Oggetti

### Classe

```java
public class Persona {
    private String nome;
    private int eta;

    public Persona(String nome, int eta) {
        this.nome = nome;
        this.eta = eta;
    }

    public String getNome() { return nome; }
}
```

### Oggetto

```java
Persona p = new Persona("Mario", 30);
```

---

## Array e Collezioni

### ARRAY

- dimensione fissa
- veloce
- contiene primitivi o oggetti

```java
int[] nums = new int[3];
nums[0] = 10;
```

### ARRAYLIST

- dinamico
- solo oggetti (autoboxing per primitivi)

```java
ArrayList<Integer> lista = new ArrayList<>();
lista.add(10);
```

### LIST (INTERFACCIA)

- contratto generale
- implementato da ArrayList, LinkedList…

```java
List<String> nomi = new ArrayList<>();
```

### HASHMAP (chiave → valore)

```java
HashMap<String, Integer> m = new HashMap<>();
m.put("anni", 20);
```

### HASHSET (insieme senza duplicati)

```java
HashSet<String> s = new HashSet<>();
s.add("ciao");
```

### Differenze rapide

| Struttura | Dimensione | Duplicati      | Ordinamento | Quando usarla                |
| --------- | ---------- | -------------- | ----------- | ---------------------------- |
| Array     | fissa      | sì             | sì          | alta performance             |
| ArrayList | dinamica   | sì             | sì          | liste mutate spesso          |
| List      | dipende    | sì             | sì          | programmazione ad interfacce |
| HashSet   | dinamica   | no             | no          | evitare duplicati            |
| HashMap   | dinamica   | chiave univoca | no          | lookup veloce                |

---

## Ereditarietà e Polimorfismo

### Ereditarietà

```java
class Animale { void verso(){} }
class Cane extends Animale { @Override void verso(){ System.out.println("bau"); } }
```

### Polimorfismo

```java
Animale a = new Cane();
a.verso();
```

---

## Interfacce e Classi Astratte

### Interfacce

```java
interface Volante { void vola(); }
```

### Classi astratte

```java
abstract class Forma { abstract double area(); }
```

---

## Gestione delle Eccezioni

```java
try {
    int x = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("errore");
} finally {
    System.out.println("done");
}
```

- **Checked** → vanno gestite
- **Unchecked (RuntimeException)** → errori di logica

---

## Input/Output

### Input da console

```java
Scanner sc = new Scanner(System.in);
int x = sc.nextInt();
```

### File

```java
try (BufferedReader r = new BufferedReader(new FileReader("file.txt"))) {
    String line;
    while ((line = r.readLine()) != null)
        System.out.println(line);
}
```

---

## Best Practices

- camelCase per variabili e metodi
- PascalCase per classi
- APRI-CHIUDI risorse sempre
- Programma ad interfacce (List invece di ArrayList)
- Evita duplicazioni

---

## Sezione Speciale – Java per Prog2 (Zero to Hero)

Guida pensata per chi arriva da JavaScript / TypeScript / Dart / Go e deve capire rapidamente **cosa serve davvero** per Programmazione 2.

### Cosa studierai realmente in Prog2

Prog2 NON è un corso di "Java applicato". È un corso di **progettazione di tipi di dato (ADT)**.

L’uso di Java è funzionale a studiare:

- **Specifiche formali** di metodi e tipi (`REQUIRES`, `MODIFIES`, `EFFECTS`)
- **Astrazione** e definizione di tipi di dato astratti (ADT)
- **Funzione di Astrazione (AF)**
- **Invariante di Rappresentazione (RI)**
- Progettazione di classi corrette, sicure e non corrotte
- Mutabilità vs Immutabilità
- Implementazioni corrette di oggetti
- Gerarchie e sottotipi corretti

In altre parole:

> Prog2 valuta se sai PROGETTARE, non programmare.

Java è solo il mezzo.

---

### Differenze per chi arriva da JS / TS / Dart / Go

Ecco un confronto rapido:

| Concetto   | JS/TS                              | Dart            | Go                  | Java                        | Note per Prog2                           |
| ---------- | ---------------------------------- | --------------- | ------------------- | --------------------------- | ---------------------------------------- |
| Tipi       | dinamici (TS statico ma non reale) | statici         | statici             | statici e rigidi            | Java è molto più formale                 |
| OOP        | prototipale                        | classico        | no OOP classica     | OOP pura                    | serve per gerarchie e specifiche         |
| Metodi     | funzioni libere                    | membri classici | funzioni + receiver | metodi su classi            | in Prog2 devi progettare metodi corretti |
| Eccezioni  | rare                               | presenti        | error-return        | eccezioni molto strutturate | usate molto in specifiche                |
| Mutabilità | ovunque                            | controllata     | strutture mutate    | molto controllata           | centrale in AF/RI                        |

---

### 1. Cos’è un Tipo di Dato Astratto (ADT)

Un ADT è un tipo definito:

- dal **comportamento**
- non dall’implementazione

Esempio: `Stack`

- Operazioni: `push`, `pop`, `top`, `isEmpty`
- Non importa **come** è memorizzato dentro (array? linked list?)
- Importa **che comportamento ha**

Prog2 ti fa progettare tanti ADT.

---

### 2. Specifiche dei Metodi (il cuore dell'esame)

Ogni metodo in Prog2 deve avere una specifica formale composta da:

#### `REQUIRES:`

Condizioni che devono essere vere PRIMA della chiamata.

#### `MODIFIES:`

Quali campi interni del tipo vengono modificati.

#### `EFFECTS:`

Effetti del metodo:

- cosa ritorna
- quali eccezioni lancia
- come cambia lo stato dell’oggetto

Esempio:

```
REQUIRES: n > 0
MODIFIES: this
EFFECTS: aggiunge n a this; se n <= 0 lancia IllegalArgumentException
```

---

### 3. Funzione di Astrazione (AF)

Descrive **come** la rappresentazione interna diventa il valore astratto visto dall’esterno.

Esempio:
Interno:

```
ArrayList<Integer> elementi
```

Valore astratto:

```
sequenza ordinata di interi
```

AF spiega "come leggere" lo stato interno.

---

### 4. Invariante di Rappresentazione (RI)

È una condizione che DEVE essere sempre vera nella struttura interna.

Esempio:

```
RI(this): elementi != null && elementi non contiene null
```

Serve a garantire che l’oggetto non sia corrotto.

---

### 5. Mutabilità vs Immutabilità

Prog2 ti fa progettare:

- **tipi mutabili** (lista personalizzata, set, stack…)
- **tipi immutabili** (intervalli, punti, date…)

L’immutabilità è importantissima per AF/RI.

---

### 6. Java minimo indispensabile per Prog2

Ti serve solo:

- classi
- costruttori
- campi `private`
- getter
- array
- ArrayList
- eccezioni (`throw`, `throws`)
- ereditarietà base

Non serve:

- thread
- I/O avanzato
- GUI
- librerie speciali

---

### 7. HashMap, HashSet, List — spiegazione semplice per Prog2

#### **HashMap** → Mappa chiave-valore

Serve per associare una chiave a un valore.

Esempi utili in Prog2:

- mappa parola → occorrenze
- mappa mese → numero giorni
- mappa id → oggetto

Funzionamento:

- le chiavi sono uniche
- inserimento/lettura è molto veloce

Quando SI usa:

- lookup veloce
- vuoi mappare “X → Y”

Quando NON si usa:

- quando serve ordine
- quando vuoi una sequenza indicizzata

---

#### **HashSet** → Insieme di valori unici

Serve quando ti interessa sapere se un valore esiste.

Esempi Prog2:

- insieme di interi già visti
- raccolta senza duplicati

Non ha indice.

---

#### **List / ArrayList** → Sequenza ordinata

Usa `ArrayList` il 99% delle volte.

Quando usarlo:

- vuoi una lista ordinata
- vuoi accedere via indice

Quando non usarlo:

- quando vuoi unicità → usa Set
- quando vuoi chiavi → usa HashMap

---

### 8. Eccezioni: perché esistono in Prog2

In Prog2 devi:

- lanciare eccezioni quando `REQUIRES` non è rispettato
- progettare metodi sicuri
- specificare quali eccezioni vengono sollevate

Esempio:

```java
if (n <= 0) throw new IllegalArgumentException();
```

---

### 9. Riepilogo "Zero to Hero" per arrivare pronti all’esame

#### Devi sapere:

- scrivere classi mutabili e immutabili corrette
- scrivere AF & RI
- scrivere specifiche REQUIRES/MODIFIES/EFFECTS
- capire differenza tra rappresentazione e valore astratto
- usare array e ArrayList
- progettare gerarchie semplici

#### NON devi sapere:

- programmazione avanzata
- framework
- OOP complessa
- design patterns
- Java moderno

Prog2 è ALGORITMI + PROGETTAZIONE.

---

## Risorse Utili

- [Documentazione Ufficiale Java](https://docs.oracle.com/en/java/)

- [Oracle Java Tutorials](https://docs.oracle.com/javase/tutorial/)

- [Java Design Patterns](https://www.journaldev.com/1827/java-design-patterns-example-tutorial)

- [Documentazione Ufficiale Java](https://docs.oracle.com/en/java/)

- [Oracle Java Tutorials](https://docs.oracle.com/javase/tutorial/)

- [Java Design Patterns](https://www.journaldev.com/1827/java-design-patterns-example-tutorial)
