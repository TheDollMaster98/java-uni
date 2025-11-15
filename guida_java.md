# Guida Completa Java – Da Zero a Hero (Versione Estesa per Prog2)

Guida strutturata in stile **Markdown per GitHub**, progettata per chi vuole padroneggiare Java per Programmazione 2 e per il lavoro reale.  
Include: concetti base, OOP, collezioni, Map/Set, eccezioni, AF/RI, specifiche, gerarchie.

---

## Indice

1. [Introduzione a Java](#introduzione-a-java)
2. [Installazione e Setup](#installazione-e-setup)
3. [Concetti Base](#concetti-base)
4. [Strutture di Controllo](#strutture-di-controllo)
5. [Classi e Oggetti](#classi-e-oggetti)
6. [Array e Collezioni](#array-e-collezioni)
   - Array
   - List / ArrayList
   - Set / HashSet
   - Map / HashMap
   - Iterazioni corrette
7. [Ereditarietà e Polimorfismo](#ereditarietà-e-polimorfismo)
8. [Interfacce e Classi Astratte](#interfacce-e-classi-astratte)
9. [Gestione delle Eccezioni](#gestione-delle-eccezioni)
10. [Input/Output](#inputoutput)
11. [Best Practices](#best-practices)
12. [Sezione Speciale – Java per Prog2 (Zero to Hero)](#sezione-speciale--java-per-prog2-zero-to-hero)
13. [Risorse Utili](#risorse-utili)

---

## Introduzione a Java

Java è un linguaggio OOP (Object-Oriented Programming) con:

- **WORA** (Write Once, Run Anywhere)
- **Garbage Collection**
- **Tipizzazione statica e forte**
- Ecosistema vastissimo
- Compatibilità retroattiva molto alta

Java è uno dei linguaggi più usati nel mondo enterprise.

---

## Installazione e Setup

### 1. Installare JDK 21

Puoi usare Oracle o Adoptium (consigliato).

Configura:

- `JAVA_HOME`
- aggiungi `%JAVA_HOME%/bin` al `PATH`

### 2. Verifica installazione

```bash
java -version
javac -version
```

### Novità Java 21 (overview veloce)

- Pattern Matching per `switch`
- Record Patterns
- Virtual Threads (Project Loom)
- Sequenced Collections
- String Templates (Preview)

---

## Concetti Base

### Struttura base

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
```

### Tipi primitivi

- `int`, `long`
- `float`, `double`
- `boolean`
- `char`

### Variabili

```java
int x = 10;
final double PI = 3.14;
```

---

## Strutture di Controllo

### If / Else

```java
if (x > 0) {
    ...
} else {
    ...
}
```

### Switch moderno

```java
switch (giorno) {
    case 1 -> System.out.println("Lunedì");
    case 2 -> System.out.println("Martedì");
    default -> System.out.println("Altro");
}
```

### Cicli

```java
for (int i = 0; i < 10; i++) {}

while (cond) {}

for (String s : lista) {} // foreach
```

---

## Classi e Oggetti

### Definire una classe

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

### Creare oggetti

```java
Persona p = new Persona("Mario", 30);
```

---

## Array e Collezioni

Java ha un sistema di collezioni molto più rigido e tipizzato rispetto a JS, Python, Go, Dart.

### ARRAY – dimensione fissa

```java
int[] numeri = new int[3];
numeri[0] = 10;

int[] altri = { 1, 2, 3 };
```

---

### LIST / ARRAYLIST – sequenza dinamica ordinata

`List` è un’interfaccia.  
`ArrayList` è l’implementazione più usata.

```java
import java.util.List;
import java.util.ArrayList;

List<String> nomi = new ArrayList<>();
nomi.add("Mario");
nomi.add("Luigi");

String primo = nomi.get(0);
nomi.remove(1);
```

Caratteristiche:

- accetta duplicati
- mantiene ordine
- utile per collezioni sequenziali

---

### SET / HASHSET – insieme senza duplicati

```java
import java.util.Set;
import java.util.HashSet;

Set<String> s = new HashSet<>();
s.add("ciao");
s.add("ciao"); // ignorato
```

Caratteristiche:

- niente duplicati
- ordine non garantito
- lookup molto veloce (`contains`)

---

### MAP / HASHMAP – chiave → valore

```java
import java.util.Map;
import java.util.HashMap;

Map<String, Integer> utenti = new HashMap<>();

utenti.put("loris", 28);
utenti.put("marco", 31);

int eta = utenti.get("loris");     // 28
boolean esiste = utenti.containsKey("marco");
```

- Chiavi **uniche**
- Valori qualsiasi
- Lookup O(1) medio

#### Attenzione: `get()` può restituire `null` se la chiave non esiste.

#### Iterare una Map (modo corretto)

```java
for (Map.Entry<String, Integer> entry : utenti.entrySet()) {
    System.out.println(entry.getKey() + " -> " + entry.getValue());
}
```

#### Aggiornare valori

Uso comune in esercizi Prog2: contare occorrenze.

```java
m.put(k, m.getOrDefault(k, 0) + 1);
```

---

### Differenze rapide

| Struttura | Dimensione | Duplicati    | Ordinamento | Uso                        |
| --------- | ---------- | ------------ | ----------- | -------------------------- |
| Array     | fissa      | sì           | sì          | performance, basso livello |
| ArrayList | dinamica   | sì           | sì          | liste mutate spesso        |
| List      | dipende    | sì           | sì          | programmare ad interfacce  |
| HashSet   | dinamica   | no           | no          | garantire unicità          |
| HashMap   | dinamica   | chiave unica | no          | lookup veloce              |

---

## Ereditarietà e Polimorfismo

### Ereditarietà base

```java
class Animale { void verso() {} }

class Cane extends Animale {
    @Override void verso() { System.out.println("bau"); }
}
```

### Polimorfismo

```java
Animale a = new Cane();
a.verso(); // bau
```

---

## Interfacce e Classi Astratte

### Interfaccia

```java
interface Volante {
    void vola();
}
```

### Classe astratta

```java
abstract class Forma {
    abstract double area();
}
```

---

## Gestione delle Eccezioni

### Try / Catch

```java
try {
    int x = 1 / 0;
} catch (ArithmeticException e) {
    System.out.println("Errore");
} finally {
    System.out.println("Sempre eseguito");
}
```

### Checked vs Unchecked

- checked → devi gestirle (`IOException`, `Exception`)
- unchecked → errori di logica (`NullPointerException`, `IllegalArgumentException`)

---

## Input/Output

### Scanner (console)

```java
import java.util.Scanner;

Scanner sc = new Scanner(System.in);
int x = sc.nextInt();
sc.close();
```

### Lettura file

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

try (BufferedReader r = new BufferedReader(new FileReader("file.txt"))) {
    String line;
    while ((line = r.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

---

## Best Practices

- camelCase per variabili/metodi
- PascalCase per classi
- chiudi sempre le risorse
- programma ad interfacce (`List`, `Map`, `Set`)
- usa eccezioni per errori logici
- mantieni tutto `private` nella rappresentazione

---

## Sezione Speciale – Java per Prog2 (Zero to Hero)

Questa è la parte più rilevante se devi passare l’esame.

Prog2 non valuta Java:  
valuta **progettazione di tipi di dato**, correttezza, specifiche, AF/RI.

---

### Cosa studierai davvero

- **Specifiche formali** (`REQUIRES`, `MODIFIES`, `EFFECTS`)
- **Funzione di Astrazione (AF)**
- **Invariante di Rappresentazione (RI)**
- Perché una classe è **corretta**
- Mutabilità e immutabilità
- Gerarchie e principio di sostituzione di Liskov
- Composizione vs ereditarietà

---

### ADT – Abstract Data Type

Un ADT è definito dal **comportamento**, non dall’implementazione.

Esempio: Stack

- `push`
- `pop`
- `top`
- `isEmpty`

Implementazione interna irrilevante.

---

### Specifiche dei metodi

Struttura:

```
REQUIRES: condizioni da rispettare all’ingresso
MODIFIES: campi modificati
EFFECTS: cosa fa, cosa restituisce, quali eccezioni può lanciare
```

Esempio:

```
REQUIRES: n > 0
MODIFIES: this
EFFECTS: aggiunge n; se n <= 0 lancia IllegalArgumentException
```

---

### AF – Funzione di Astrazione

Descrive come lo stato interno (array, lista…) rappresenta il valore astratto.

Esempio:

```text
AF(this): sequenza degli elementi nella lista 'elementi'
```

---

### RI – Invariante di Rappresentazione

Condizioni che DEVONO sempre essere vere internamente.

Esempio:

```text
RI(this): elementi != null && elementi non contiene null
```

---

### Mutabili vs Immutabili

Tipi mutabili:

- stato modificabile
- metodi che cambiano i campi

Tipi immutabili:

- stato fissato nel costruttore
- i metodi ritornano nuovi oggetti

---

### Minimale Java necessario per Prog2

Serve solo:

- Classi
- Costruttori
- Campi privati
- Array
- List/ArrayList
- Map/HashMap
- Eccezioni
- Ereditarietà base

Non serve:

- Streams
- Thread
- Swing
- Pattern Design complessi

---

## Risorse Utili

- Documentazione Java
- Oracle Java Tutorials
- Java Collections Overview
- Slide e esercizi di Programmazione 2
