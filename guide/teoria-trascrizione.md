# Guida Teorica Completa — Programmazione II (Java 21)

**Università degli Studi di Milano (UNIMI) — Corso di Laurea in Informatica**  
_Basata sulle lezioni del Prof. Dragan Ahmetovic e sul testo "Program Development in Java" (PDJ) di Barbara Liskov e John Guttag._

---

## Indice dei Contenuti

1. [Introduzione: Decomposizione e Astrazione (PDJ 1)](#1-introduzione-decomposizione-e-astrazione-pdj-1)
2. [Il Paradigma Object-Oriented e Fondamenti di Java 21 (PDJ 2)](#2-il-paradigma-object-oriented-e-fondamenti-di-java-21-pdj-2)
3. [Astrazione Procedurale e Linguaggio di Specifica (PDJ 3)](#3-astrazione-procedurale-e-linguaggio-di-specifica-pdj-3)
4. [Gestione delle Eccezioni (PDJ 4)](#4-gestione-delle-eccezioni-pdj-4)
5. [Astrazione dei Tipi di Dato (ADT) e Implementazione (PDJ 5.1–5.4)](#5-astrazione-dei-tipi-di-dato-adt-e-implementazione-pdj-5154)
6. [Correttezza degli ADT: Funzione di Astrazione ($AF$) e Invariante di Rappresentazione ($RI$) (PDJ 5.5+)](#6-correttezza-degli-adt-funzione-di-astrazione-af-e-invariante-di-rappresentazione-ri-pdj-55)
7. [Gerarchie di Tipi, Principio di Sostituzione di Liskov (LSP) e Composizione (PDJ 7)](#7-gerarchie-di-tipi-principio-di-sostituzione-di-liskov-lsp-e-composizione-pdj-7)
8. [Astrazione di Iterazione e Classi Innestate / Nested Classes (PDJ 6)](#8-astrazione-di-iterazione-e-classi-innestate--nested-classes-pdj-6)
9. [Polimorfismo e Generics in Java (PDJ 8 + Java Generics)](#9-polimorfismo-e-generics-in-java-pdj-8--java-generics)
10. [Programmazione Funzionale in Java](#10-programmazione-funzionale-in-java)
11. [Ingegneria del Software e Design Pattern](#11-ingegneria-del-software-e-design-pattern)
12. [Appendice: Checklist Operativa per l'Esame (Teoria e Laboratorio)](#12-appendice-checklist-operativa-per-lesame-teoria-e-laboratorio)

---

## 1. Introduzione: Decomposizione e Astrazione (PDJ 1)

L'obiettivo dell'ingegneria del software e della programmazione su larga scala è costruire programmi **affidabili**, **comprensibili**, **modificabili** e **riutilizzabili**.

### 1.1 Dalla Programmazione Imperativa a quella Procedurale

- **Programmazione Imperativa**: Il programma è una sequenza lineare di comandi/istruzioni che modificano lo stato della memoria. Al crescere delle dimensioni del codice, emergono limiti critici:
  - **Comprensibilità**: Difficoltà nel seguire il flusso di un unico blocco monolitico.
  - **Riutilizzabilità**: Necessità di duplicare codice per eseguire operazioni simili.
  - **Modificabilità**: Cambiare un dettaglio (es. passare da 3 a 4 elementi) richiede di intervenire in decine di punti sparsi nel listato.
- **Programmazione Procedurale**: Risolve i limiti dell'imperativa scomponendo il programma in **procedure** (funzioni/metodi), ovvero unità logiche isolate e parametrizzabili.
- **Limiti aperti della Programmazione Procedurale** (che portano alla OOP):
  - Difficoltà nel mantenere coerente lo **stato globale/condiviso** (chiunque può modificare dati condivisi in modo errato).
  - Difficoltà nel coordinare team numerosi su migliaia di procedure.
  - Mancanza di incapsulamento tra dati e operazioni che li manipolano.

### 1.2 Decomposizione

La **decomposizione** è il processo di suddivisione di un problema complesso in sottoproblemi più semplici e gestibili. Un'ottima decomposizione produce moduli che siano:

1. **Logicamene coesi**: Ogni modulo svolge un singolo compito ben definito e coerente.
2. **Indipendenti**: Sviluppabili, testabili e comprensibili separatamente dal resto del sistema.
3. **Allo stesso livello di dettaglio**: Evitare di mescolare operazioni di altissimo livello con dettagli microscopici di basso livello nella stessa unità logica.
4. **Minimamente accoppiati**: Le dipendenze tra moduli devono essere ridotte allo stretto necessario.

### 1.3 Astrazione: Meccanismi e Tipologie

L'**astrazione** è un mapping **molti-a-uno** che consente di ignorare i dettagli irrilevanti (come una cosa è fatta) per concentrarsi sugli aspetti essenziali (cosa fa e su quali dati opera). Offre due vantaggi fondamentali:

- **Località**: Un'astrazione può essere implementata, compresa e verificata in modo disgiunto dal resto del programma. Chi la usa deve leggerne solo la specifica, non il codice interno.
- **Modificabilità**: L'implementazione interna può essere cambiata (es. per ottimizzare le prestazioni o correggere bug) senza impattare sul codice cliente, purché la specifica resti invariata.

#### I Due Meccanismi di Astrazione

1. **Astrazione per Parametrizzazione**: Generalizza un'operazione sostituendo valori specifici con **parametri formali**, rendendo il codice applicabile a un insieme più ampio di input (es. calcolare la media di un array di dimensione arbitraria $n$ anziché di 3 variabili fisse). È un'astrazione sui _dati_.
2. **Astrazione per Specifica**: Separa il **comportamento atteso ("cosa")** dall'**implementazione concreta ("come")**, descrivendo vincoli, input ed effetti tramite intestazioni e commenti formali/semiformali. È un'astrazione sulle _operazioni_.

#### I Quattro Tipi di Astrazione del Corso

1. **Astrazione Procedurale**: Definizione di sottoprogrammi (metodi) tramite parametrizzazione e specifica.
2. **Astrazione dei Tipi di Dato (ADT)**: Definizione di nuovi tipi di dato che incapsulano stato e comportamenti.
3. **Astrazione di Gerarchia dei Tipi**: Estensione e specializzazione di tipi esistenti tramite ereditarietà e interfacce.
4. **Astrazione di Iterazione**: Accesso uniforme e sequenziale agli elementi di una collezione senza esporne la rappresentazione interna.

---

## 2. Il Paradigma Object-Oriented e Fondamenti di Java 21 (PDJ 2)

### 2.1 Approccio Procedurale vs Object-Oriented

- **Linguaggi Procedurali**: Separano nettamente i **dati** (memorizzati in variabili o `struct`) dalle **azioni** (funzioni che ricevono i dati come parametri esterni).
- **Linguaggi Object-Oriented (OOP)**: I dati sono strutturati come **oggetti**, ciascuno dotato di:
  - **Stato**: Insieme di attributi (campi) che caratterizzano la singola istanza.
  - **Comportamento**: Insieme di metodi che agiscono (ispezionando o modificando) sullo stato dell'oggetto stesso.

### 2.2 I Principi Fondamentali della OOP

1. **Incapsulamento (_Encapsulation_)**: Lo stato interno dell'oggetto è nascosto all'esterno (`private`) e può essere ispezionato o modificato **esclusivamente** tramite i metodi esposti dall'oggetto stesso. Questo garantisce che l'oggetto mantenga sempre uno stato coerente.
2. **Ereditarietà (_Inheritance_)**: Meccanismo che consente a una classe di derivare attributi e comportamenti da una superclasse, creando gerarchie di tipi.
3. **Polimorfismo**: Capacità di un unico simbolo (nome di metodo o variabile) di assumere significati o forme diverse in base al contesto o al tipo concreto a runtime.

### 2.3 Classi, Oggetti, Package e Nomenclatura

- **Classe**: È l'archetipo o stampino (_blueprint_) che definisce quali attributi e metodi avranno gli oggetti di quel tipo.
- **Oggetto**: È una singola **istanza** concreta di una classe, dotata di valori specifici per i propri attributi.
- **Package**: Meccanismo per raggruppare classi correlate, gestire la visibilità e prevenire collisioni di nomi. I package formano una gerarchia ad albero rispecchiata dal file system (es. `it.unimi.di.prog2`).
  - Il percorso completo di una classe si chiama **Fully Qualified Name** (es. `java.util.ArrayList`).
  - Il package `java.lang` (che contiene `Object`, `String`, `System`, `Math`, le classi Wrapper) è importato **automaticamente** in ogni file Java.
  - Se non si dichiara alcun `package`, la classe appartiene al _package di default_ (senza nome).
- **Convenzioni di Nomenclatura Java**:
  - **Classi e Interfacce**: `PascalCase` (iniziale maiuscola, es. `StudenteMagistrale`). Il file sorgente deve avere lo stesso identico nome della classe `public` (es. `StudenteMagistrale.java`).
  - **Metodi e Variabili**: `camelCase` (iniziale minuscola, es. `calcolaMedia`).
  - **Costanti (`static final`)**: `UPPER_SNAKE_CASE` (es. `MAX_VOTO`).
  - **Package**: tutto minuscolo (es. `unimi.prog2`).

### 2.4 Compilazione ed Esecuzione (Bytecode e JVM)

Java è un linguaggio sia compilato che interpretato (tramite JIT):

1. Il compilatore `javac NomeClasse.java` verifica la sintassi e i tipi, traducendo il sorgente in **Bytecode** (file `NomeClasse.class`), un linguaggio intermedio indipendente dall'architettura hardware.
2. Il comando `java NomeClasse` avvia la **Java Virtual Machine (JVM)**, che esegue il bytecode sull'hardware specifico (_Write Once, Run Anywhere_).

### 2.5 Espressioni, Tipi e Type Safety

In Java ogni espressione viene valutata da sinistra verso destra e produce un **tipo** (noto a compile-time) e un **valore** (calcolato a runtime, salvo costanti).
Java è un linguaggio **strongly typed** e **type-safe**: il compilatore verifica staticamente la compatibilità di tutti i tipi, impedendo errori di tipo durante l'esecuzione.

### 2.6 Memoria (Stack vs Heap) e Garbage Collector

| Caratteristica     | Tipi Primitivi                                                       | Tipi Riferimento (Oggetti, Array, String)                                            |
| :----------------- | :------------------------------------------------------------------- | :----------------------------------------------------------------------------------- |
| **Tipi in Java**   | `byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean` | Qualsiasi `class`, `interface`, `record`, `enum`, array (`int[]`)                    |
| **Allocazione**    | Valore salvato direttamente nello **Stack**                          | Riferimento (puntatore) nello **Stack**; oggetto reale nello **Heap**                |
| **Creazione**      | Letterale (es. `int x = 5;`)                                         | Operatore `new` (es. `new Studente(...)`) o zucchero sintattico (`"ciao"`, `{1, 2}`) |
| **Valore Default** | `0`, `0.0`, `false`, `'\u0000'` (solo per attributi)                 | `null` (riferimento che non punta ad alcuna area nello Heap)                         |
| **Confronto `==`** | Confronta i valori effettivi                                         | Confronta gli indirizzi di memoria nello Stack (identità referenziale)               |

- **Aliasing**: Assegnando una variabile riferimento a un'altra (`Persona b = a;`), nello Stack viene copiato l'indirizzo. Entrambe le variabili puntano allo **stesso oggetto nello Heap**.
- **Garbage Collector (GC)**: Processo automatico della JVM che individua nello Heap gli oggetti non più raggiungibili da alcun riferimento attivo nello Stack e ne libera la memoria.

### 2.7 Mutabilità vs Immutabilità

- **Oggetti Immutabili** (es. `String`, `Integer`, `Double`): Una volta creati nello Heap, il loro stato interno non può mai essere modificato. Ogni operazione apparentemente modificante (es. `s = s + "!";`) crea in realtà un **nuovo oggetto** nello Heap e aggiorna il riferimento nello Stack.
- **Oggetti Mutabili** (es. `ArrayList`, array, classi con metodi mutatori): Il loro stato nello Heap può cambiare. In presenza di _aliasing_, una modifica effettuata tramite un riferimento è visibile anche da tutti gli altri riferimenti che puntano allo stesso oggetto.

### 2.8 Metodi, Passaggio Parametri, Overloading e Casting Implicito

- **Passaggio dei parametri**: In Java **tutti** i parametri sono passati **per valore**.
  - Per i primitivi viene copiato il valore (modifiche al parametro formale non alterano la variabile del chiamante).
  - Per i riferimenti viene copiato l'indirizzo di memoria: il metodo non può cambiare a quale oggetto punta la variabile del chiamante, ma **può modificare lo stato interno** dell'oggetto nello Heap se questo è mutabile.
- **Conversioni Implicite (_Widening_)**: Java converte automaticamente tipi numerici più ristretti in tipi più espressivi senza perdita di precisione (es. `int` $\to$ `long` $\to$ `float` $\to$ `double`).
- **Overloading**: Definizione di più metodi con lo **stesso nome** nella stessa classe (o gerarchia) ma con **lista dei parametri diversa** (per numero, tipo o ordine).
- **Most Specific Call**: In presenza di overloading e conversioni implicite, il compilatore sceglie il metodo che richiede il minor numero di conversioni. Se due metodi sono ugualmente specifici (es. `m(int, double)` e `m(double, int)` invocati con `m(1, 2)`), il compilatore genera un **errore di ambiguità**.

### 2.9 Collezioni Base, Autoboxing e I/O

- **Wrapping, Autoboxing e Unboxing**: Poiché le collezioni generiche (es. `ArrayList<E>`) accettano solo tipi riferimento, Java fornisce per ogni primitivo una classe Wrapper immutabile (`Integer`, `Double`, `Boolean`, `Character`, `Long`, ecc.).
  - **Autoboxing**: Conversione automatica `int` $\to$ `Integer` (tramite `Integer.valueOf(...)`).
  - **Unboxing**: Conversione automatica `Integer` $\to$ `int` (tramite `.intValue()`).
  - _Attenzione_: Confrontare due `Integer` con `==` confronta i riferimenti (salvo cache per valori tra $-128$ e $127$); usare sempre `.equals()`.
- **Input/Output Standard**:
  - **Output**: `System.out.println(...)` / `System.out.print(...)`.
  - **Argomenti da riga di comando**: Array `String[] args` nel `main` (`args[0]` è il primo argomento passato, non il nome del programma).
  - **Standard Input**: Classe `java.util.Scanner` inizializzata con `new Scanner(System.in)`. Metodi principali: `hasNextLine()`, `nextLine()`, `hasNextInt()`, `nextInt()`, `next()`. La fine dell'input (`EOF`) da terminale si invia con `Ctrl+D` (Linux/macOS) o `Ctrl+Z` (Windows).

---

## 3. Astrazione Procedurale e Linguaggio di Specifica (PDJ 3)

### 3.1 Separazione tra Specifica e Implementazione

Una specifica procedurale costituisce un **contratto** tra chi implementa il metodo e chi lo invoca:

- Il **chiamante** si impegna a rispettare le precondizioni.
- L'**implementatore** garantisce le postcondizioni (risultato ed effetti).

La specifica deve essere **dichiarativa** (spiegare _cosa_ fa la procedura) e **mai operazionale** (non deve descrivere gli step algoritmici interni o le strutture dati locali).

### 3.2 Il Linguaggio di Specifica di Liskov

Nel corso si adotta la notazione standard di Barbara Liskov tramite commenti strutturati posti immediatamente sopra l'intestazione del metodo:

```java
// REQUIRES: <precondizioni sul dominio degli input>
// MODIFIES: <input mutabili o stato di sistema modificati>
// EFFECTS: <postcondizioni: valore restituito, modifiche effettuate, eccezioni lanciate>
```

1. **`REQUIRES` (Precondizioni)**: Specifica i vincoli sugli input non già espressi dai tipi formali (es. `a != null`, `a` ordinato in modo crescente, `b != 0`).
   - _Regola di omissione_: Se non ci sono vincoli sul dominio (procedura totale), la clausola `REQUIRES` **va omessa**.
2. **`MODIFIES` (Effetti collaterali / Side Effects)**: Elenca ogni entità preesistente il cui stato viene alterato dal metodo. Include:
   - Parametri di tipo riferimento mutabili modificati (es. un array o una `List` ordinata sul posto).
   - Lo stato dell'oggetto corrente (`this`) nei metodi mutatori d'istanza.
   - Lo stato dell'ambiente/sistema, inclusi **`System.in`** (se il metodo legge da standard input, consumando token/righe) e **`System.out`** (se il metodo stampa a video).
   - _Regola di omissione_: Se il metodo non modifica nulla (è puro/osservazionale), la clausola `MODIFIES` **va omessa**.
3. **`EFFECTS` (Postcondizioni)**: Descrive l'effetto completo dell'esecuzione del metodo (valore restituito, stato finale degli oggetti elencati in `MODIFIES` ed eventuali eccezioni lanciate). **È sempre presente**.

_(Nota: JavaDoc usa tag come `@param`, `@return`, `@throws` per generare documentazione HTML, ma la notazione di Liskov è richiesta all'esame perché separa nettamente precondizioni ed effetti collaterali)._

### 3.3 Proprietà delle Buone Specifiche

- **Restrittività**: Deve escludere tutte le implementazioni scorrette, fornendo tutte le informazioni necessarie a evitare ambiguità pericolose.
- **Minimalità**: Deve vincolare il meno possibile l'implementazione, lasciando allo sviluppatore libertà nella scelta dell'algoritmo o delle ottimizzazioni.
- **Sottodeterminazione vs Determinismo**:
  - **Sottodeterminazione** (proprietà della _specifica_): Una specifica è sottodeterminata quando ammette più implementazioni valide che possono produrre risultati diversi per lo stesso input (es. "restituisce l'indice di una occorrenza di $x$ nell'array $a$": se $x$ compare più volte, una ricerca da sinistra restituisce la prima occorrenza, una ricerca binaria può restituirne un'altra).
  - **Determinismo** (proprietà dell'_implementazione_): Una specifica implementazione, a parità di input e stato iniziale, deve produrre sempre lo stesso identico risultato.
- **Chiarezza e Ridondanza**: Il linguaggio naturale può essere ambiguo; l'aggiunta di esempi concreti (ridondanza controllata) aiuta a chiarire la semantica.

### 3.4 Proprietà delle Procedure: Totalità vs Parzialità

- **Semplicità**: Una procedura dovrebbe svolgere un solo compito ben identificabile (se è difficile darle un nome conciso, probabilmente fa troppe cose).
- **Generalità**: Rendere la procedura riutilizzabile su un dominio ampio tramite parametrizzazione (es. incrementare di un parametro $k$ anziché di una costante fissa $10$).
- **Procedure Totali vs Procedure Parziali**:
  - **Procedura Totale**: È definita per **ogni** possibile combinazione di valori ammessi dai tipi dei suoi parametri. Non ha clausola `REQUIRES`.
  - **Procedura Parziale**: È definita solo per un sottoinsieme del dominio dei tipi d'ingresso, delimitato dalla clausola `REQUIRES`. Se il chiamante viola il `REQUIRES`, il comportamento della procedura è **indefinito** (può crashare, entrare in loop infinito o restituire risultati errati).
  - _Quando usare procedure parziali_: Quando controllare la precondizione sarebbe troppo costoso computazionalmente (es. verificare in $O(n)$ che un array sia ordinato prima di una ricerca binaria in $O(\log n)$) o quando il metodo è `private` e usato in un contesto strettamente controllato.

---

## 4. Gestione delle Eccezioni (PDJ 4)

### 4.1 Limiti delle Procedure Parziali e Meccanismi Alternativi

Affidarsi solo a procedure parziali rende il software fragile: se un chiamante viola un `REQUIRES` e la procedura restituisce un dato corrotto senza fermarsi, l'errore si **propaga** silenziosamente. L'obiettivo di un buon sistema è la **Graceful Degradation**: gestire gli errori in modo prevedibile senza corrompere lo stato del programma.

I meccanismi alternativi alle eccezioni presentano gravi limiti:

1. **Valori di return speciali** (es. restituire `-1` se non trovato, o `0` nella divisione per zero):
   - Impossibile se l'intero codominio è già utilizzato da valori validi (es. la divisione intera può produrre qualunque `int`).
   - Alto rischio di propagazione se il chiamante dimentica di controllare il valore speciale.
2. **Pattern Comma-Ok** (tipico di Go, restituire `(valore, boolean ok)`):
   - Non supportato nativamente in Java (un metodo ha un solo valore di ritorno).
   - Anche incapsulando il risultato in un oggetto coppia, il controllo resta a carico della memoria del chiamante.

### 4.2 Le Eccezioni in Java e Gerarchia `Throwable`

Le eccezioni sono oggetti (sottotipi di `java.lang.Throwable`) che interrompono immediatamente il flusso sequenziale del metodo quando si verifica una condizione anomala, separando la gestione degli errori dal codominio del metodo.

```text
                    java.lang.Throwable
                     /               \
            java.lang.Error     java.lang.Exception
              (Unchecked)          /             \
                         (Checked Exceptions)   java.lang.RuntimeException
                         es. IOException            (Unchecked Exceptions)
                             FileNotFoundException  es. NullPointerException
                                                        IllegalArgumentException
                                                        ArithmeticException
                                                        IndexOutOfBoundsException
                                                        NoSuchElementException
```

1. **Error** (_Unchecked_): Errori gravi e irrecuperabili della JVM o del sistema (es. `OutOfMemoryError`, `StackOverflowError`). Non vanno catturati.
2. **Checked Exceptions**: Sottoclassi di `Exception` che **non** ereditano da `RuntimeException`. Rappresentano condizioni eccezionali prevedibili e recuperabili (es. file mancante). Il compilatore **obbliga** a gestirle con `try-catch` oppure a dichiararle nella firma con `throws`.
3. **Unchecked Exceptions**: Sottoclassi di `RuntimeException`. Rappresentano violazioni di precondizioni o errori di programmazione (es. riferimento `null`, argomento invalido). Non richiedono obbligo sintattico di cattura o `throws` a compile-time, ma **vanno comunque documentate in `EFFECTS`**.

### 4.3 Costrutti Sintattici e Creazione di Eccezioni Custom

- **`throw`**: Lancia esplicitamente un'istanza di eccezione (`throw new IllegalArgumentException("Messaggio");`).
- **`throws`**: Dichiara nell'intestazione del metodo quali eccezioni possono essere propagate al chiamante.
- **`try - catch - finally`**:
  - `try`: racchiude il codice che può sollevare eccezioni.
  - `catch (TipoEccezione e)`: cattura e gestisce l'eccezione (dal tipo più specifico al più generico).
  - `finally`: blocco eseguito **sempre** (sia in caso di successo che di eccezione), usato per rilasciare risorse (es. `scanner.close()`). In alternativa si usa il costrutto _try-with-resources_: `try (Scanner sc = new Scanner(file)) { ... }`.

#### Definire Nuove Eccezioni

Si estende `Exception` (per creare una _checked exception_) o `RuntimeException` (per una _unchecked exception_), definendo i due costruttori standard:

```java
public class NegativeExponentException extends RuntimeException {
    public NegativeExponentException() {
        super();
    }
    public NegativeExponentException(String message) {
        super(message);
    }
}
```

### 4.4 Rendere Totale una Procedura Parziale e Masking vs Reflection

Lanciando un'eccezione per gli input non validi, quei casi rientrano nel comportamento definito del metodo: **il vincolo si sposta da `REQUIRES` a `EFFECTS`**, trasformando una procedura parziale in una **procedura totale**.

```java
// EFFECTS: Se b == 0 lancia ArithmeticException;
//          altrimenti restituisce il quoziente intero a / b.
public static int div(int a, int b) throws ArithmeticException {
    if (b == 0) throw new ArithmeticException("Divisione per zero");
    return a / b;
}
```

Quando un metodo $M_1$ invoca un metodo $M_2$ che può lanciare un'eccezione, $M_1$ ha due strategie possibili:

1. **Masking (Mascheramento)**: $M_1$ cattura l'eccezione localmente tramite `try-catch` e la gestisce autonomamente senza che il chiamante di $M_1$ se ne accorga. Si usa quando $M_1$ ha abbastanza contesto per risolvere il problema.
2. **Reflection (Riflessione)**: $M_1$ non gestisce l'eccezione ma la lascia propagare al proprio chiamante (dichiarandola con `throws`), oppure la cattura per rilanciare un'eccezione più astratta e significativa per il dominio di $M_1$. Si usa quando solo i livelli superiori hanno il contesto per decidere come reagire (es. chiedere un nuovo input all'utente).

---

## 5. Astrazione dei Tipi di Dato (ADT) e Implementazione (PDJ 5.1–5.4)

### 5.1 Cos'è un Tipo di Dato Astratto (ADT)

Un **ADT (_Abstract Data Type_)** è un tipo definito esclusivamente dal suo comportamento osservabile (l'insieme delle operazioni pubbliche e le loro specifiche), nascondendo completamente la **rappresentazione concreta** (gli attributi privati in memoria).
Questo garantisce che il codice cliente dipenda solo dall'interfaccia pubblica: se cambiamo la struttura dati interna (es. da due `ArrayList` parallele a una `HashMap`), il codice cliente continua a funzionare senza modifiche.

### 5.2 Classificazione dei Metodi di un ADT

Oltre alla distinzione tra **metodi d'istanza** (operano sullo stato di `this`) e **metodi di classe (`static`)** (non accedono a `this`, operano solo sui parametri), i metodi di un ADT si classificano in quattro categorie:

1. **Costruttori (_Creators_)**: Creano e inizializzano una nuova istanza del tipo. Possono essere sovraccaricati (_overloading_). Esistono anche:
   - _Copy Constructor_: costruttore che riceve un'istanza dello stesso tipo per crearne una copia (`public Studente(Studente other)`).
   - _Metodi Factory statici_: metodi `static` che restituiscono una nuova istanza della classe (es. `Integer.valueOf(5)`).
2. **Osservatori (_Observers_)**: Restituiscono informazioni sullo stato dell'oggetto senza modificarlo (es. getter, `size()`, predicati come `contains()`). Non hanno `MODIFIES`.
3. **Mutatori (_Mutators_)**: Modificano lo stato interno dell'oggetto (`MODIFIES: this`). Presenti **solo** negli ADT mutabili (es. `insert()`, `invecchia()`). _Nota_: Evitare di generare _setter_ indiscriminati che aggirano i vincoli di validità dello stato.
4. **Produttori (_Producers_)**: Prendono oggetti del tipo corrente e restituiscono una **nuova istanza** dello stesso tipo senza alterare gli operandi (es. `p1.add(p2)` nei polinomi o `String.concat()`). Sono l'unico modo per "trasformare" dati negli ADT immutabili.

### 5.3 Specifica e Processo di Sviluppo di una Classe

1. **Specifica della Classe**:
   - `// OVERVIEW:` descrive cosa modella il tipo astratto e **dichiara esplicitamente se le istanze sono mutabili o immutabili**.
   - Specifica dei costruttori e di tutti i metodi pubblici (`REQUIRES`, `MODIFIES`, `EFFECTS`). _(I semplici getter e i metodi standard di `Object` ridefiniti non necessitano di specifica se rispettano il contratto standard)._
2. **Scelta della Rappresentazione (_Rep_)**:
   - Definizione degli attributi `private`.
   - Criteri di scelta: **sufficienza** (deve poter rappresentare tutti gli stati astratti possibili), **non-ridondanza eccessiva**, **semplicità d'uso** ed **efficienza** computazionale/spaziale.
3. **Implementazione**:
   - Implementare prima i costruttori (validando subito gli input e lanciando eccezioni se invalidi), poi i metodi seguendo una strategia _bottom-up_ (prima i metodi semplici usati dagli altri).

### 5.4 Esposizione della Rappresentazione (_Representation Exposure_)

Si verifica **Representation Exposure** quando il codice esterno alla classe ottiene un riferimento diretto a un componente mutabile dello stato interno privato, potendolo modificare senza passare per i metodi della classe (violando così l'incapsulamento e l'invariante di rappresentazione).

**Cause principali e rimedi**:

1. **Attributi non `private`**: Dichiarare **sempre** gli attributi `private`.
2. **Restituire riferimenti a campi mutabili in un osservatore**: Se un attributo è una `List` o un array mutabile, un getter non deve fare `return this.lista;`, ma deve restituirne una **copia difensiva** (`return new ArrayList<>(this.lista);` oppure una vista non modificabile `Collections.unmodifiableList(...)`).
3. **Salvare direttamente un parametro mutabile nel costruttore/mutatore**: Se il costruttore riceve un array o una lista dall'esterno, il chiamante conserva il riferimento e può modificarlo da fuori! Occorre fare una **copia difensiva in ingresso** (`this.lista = new ArrayList<>(paramLista);`).

### 5.5 Tipi Mutabili vs Immutabili e `record` in Java 21

- **ADT Immutabili**: Preferibili ogni volta che non è strettamente necessaria la mutazione sul posto. Eliminano i rischi di aliasing pericoloso, semplificano il ragionamento sulla correttezza e sono intrinsecamente thread-safe. Gli attributi vengono marcati con **`final`** (ricordando che `final` blocca il riferimento nello Stack, non l'eventuale mutabilità dell'oggetto puntato nello Heap!).
- **Java 21 `record`**: Introdotti stabilmente da Java 16 e cardine di Java 21 per modellare **ADT immutabili puramente portatori di dati** (es. `public record Punto(int x, int y) {}`). Il compilatore genera automaticamente campi `private final`, costruttore canonico, metodi osservatori (`x()`, `y()`), `equals()`, `hashCode()` e `toString()`. È possibile definire un _costruttore compatto_ per validare le precondizioni.

### 5.6 I Quattro Metodi Fondamentali di `java.lang.Object`

Tutte le classi ereditano da `Object`. Ridefinirli (`@Override`) è essenziale per il corretto funzionamento nelle collezioni e nelle stampe:

1. **`public boolean equals(Object o)`**:
   - L'implementazione di default in `Object` verifica solo l'identità referenziale (`this == o`).
   - Deve soddisfare le proprietà di una **relazione di equivalenza**: **riflessiva** ($x.\text{equals}(x)$ è `true`), **simmetrica** ($x.\text{equals}(y) \iff y.\text{equals}(x)$), **transitiva** ($x.\text{equals}(y) \land y.\text{equals}(z) \implies x.\text{equals}(z)$), **coerente** nel tempo e tale che $x.\text{equals}(\text{null})$ sia sempre `false`.
   - Schema canonico:
     ```java
     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         Studente other = (Studente) o;
         return this.eta == other.eta && Objects.equals(this.matricola, other.matricola);
     }
     ```
2. **`public int hashCode()`**:
   - Restituisce un intero usato come codice hash nelle strutture basate su hash (`HashMap`, `HashSet`).
   - **Contratto obbligatorio**: Se `a.equals(b)` è `true`, allora `a.hashCode() == b.hashCode()` **deve** essere `true`. Non vale il viceversa (due oggetti diversi possono avere lo stesso hashCode: _collisione_).
   - Deve essere calcolato **esattamente sugli stessi attributi** usati in `equals`:
     ```java
     @Override
     public int hashCode() {
         return Objects.hash(matricola, eta);
     }
     ```
3. **`public T clone()`**:
   - Crea e restituisce una copia indipendente dell'oggetto. La classe deve implementare l'interfaccia marker `Cloneable`.
   - `super.clone()` esegue una **Shallow Copy** (copia bit a bit dello Stack: copia i valori dei primitivi e i _riferimenti_ degli oggetti). Se la classe contiene campi mutabili, è obbligatorio eseguire una **Deep Copy** clonando esplicitamente tali campi affinché originale e clone non condividano stato mutabile nello Heap.
4. **`public String toString()`**:
   - Restituisce una rappresentazione testuale chiara e informativa dell'oggetto. Invocato automaticamente da `System.out.println(obj)` e nella concatenazione con `+`.

---

## 6. Correttezza degli ADT: Funzione di Astrazione ($AF$) e Invariante di Rappresentazione ($RI$) (PDJ 5.5+)

Per dimostrare formalmente che l'implementazione concreta $C$ realizza correttamente l'astrazione $A$, Liskov introduce due strumenti teorici e pratici.

### 6.1 Funzione di Astrazione ($AF$)

La **Funzione di Astrazione** mappa ogni stato concreto valido $c \in C$ (ovvero una specifica configurazione degli attributi privati) nell'oggetto astratto $a \in A$ che esso rappresenta:
$$AF : C \to A$$

- **Dominio**: L'insieme degli stati concreti **validi** di $C$ (non tutte le combinazioni possibili dei bit degli attributi hanno un significato astratto).
- **Codominio**: Tutti gli oggetti astratti $a \in A$.
- **Proprietà Matematiche**:
  - **Suriettiva**: Ogni oggetto astratto $a \in A$ deve essere rappresentabile da almeno uno stato concreto $c \in C$.
  - **Non-iniettiva (molti-a-uno)**: Più rappresentazioni concrete distinte possono mappare allo stesso identico oggetto astratto. Ad esempio, per un insieme astratto di interi $\{1, 2\}$, sia l'array `[1, 2]` sia `[2, 1]` sono rappresentazioni concrete valide che mappano a $\{1, 2\}$.
- **Implementazione in Java**: Corrisponde al metodo **`toString()`**, che deve restituire una descrizione univoca dell'oggetto astratto rappresentato (senza esporre dettagli interni irrilevanti).

### 6.2 Invariante di Rappresentazione ($RI$)

L'**Invariante di Rappresentazione** è un predicato booleano definito sullo spazio concreto $C$ che stabilisce quali stati degli attributi sono validi e coerenti:
$$RI : C \to \{\text{True}, \text{False}\}$$

- **Relazione con $AF$**: Il dominio della funzione di astrazione $AF$ coincide esattamente con il sottoinsieme di stati concreti per cui $RI$ vale `True`:
  $$RI(c) = \text{True} \iff \exists a \in A \mid AF(c) = a$$
- **Struttura**: Si esprime come una congiunzione logica di predicati sugli attributi (es. `els != null` $\land$ `els` non contiene duplicati).
- **Quando deve valere**:
  - Deve essere vera **al termine di ogni costruttore**.
  - Deve essere preservata vera **al termine di ogni metodo pubblico** (mutatore o produttore).
  - _Nota_: Può essere temporaneamente violata **durante** l'esecuzione interna di un metodo mutatore, purché venga ripristinata prima del `return`.
  - _Attenzione_: Non può essere garantita se c'è _representation exposure_.
- **Implementazione in Java (`repOk()`) e Asserzioni**:
  Si implementa tramite il metodo `public boolean repOk()` e si verifica nei costruttori e alla fine dei metodi mutatori tramite l'istruzione `assert repOk();`:
  ```java
  public boolean repOk() {
      if (els == null) return false;
      for (int i = 0; i < els.size(); i++) {
          if (els.get(i) == null) return false;
          for (int j = i + 1; j < els.size(); j++) {
              if (els.get(i).equals(els.get(j))) return false;
          }
      }
      return true;
  }
  ```
  _Nota operativa_: Le asserzioni in Java sono disabilitate di default a runtime; per attivarle in fase di test/debug occorre eseguire la JVM con il flag **`java -ea NomeClasse`** (_enable assertions_).

### 6.3 Dimostrazione di Correttezza per Induzione sul Tipo di Dato

Per dimostrare che $RI$ vale sempre per ogni istanza raggiungibile di una classe si procede per **induzione sul tipo di dato**:

1. **Caso Base**: Dimostrare che tutti i costruttori (e i metodi factory) producono istanze per cui $RI(c) = \text{True}$.
2. **Passo Induttivo**: Assumendo come ipotesi induttiva che $RI(c) = \text{True}$ prima dell'invocazione del metodo, dimostrare che ogni metodo mutatore e produttore lascia l'oggetto (e l'eventuale nuovo oggetto restituito) in uno stato $c'$ tale che $RI(c') = \text{True}$. _(Gli osservatori non modificano lo stato, quindi preservano trivialmente $RI$)._

### 6.4 Invariante di Astrazione e Effetti Collaterali Benevoli

- **Invariante di Astrazione (AI)**: Proprietà che verifica la validità della _specifica_ stessa (che i metodi specificati mantengano coerente il concetto astratto modellato, indipendentemente dalla rappresentazione).
- **Effetti Collaterali Benevoli (_Benevolent Side Effects_)**: In un ADT **astrattamente immutabile**, la rappresentazione concreta può comunque subire modifiche interne purché queste non alterino il risultato della funzione di astrazione $AF$ (cioè l'oggetto astratto osservabile dall'esterno resta identico). Esempi classici: riordinare internamente un array durante una ricerca per velocizzare ricerche future, oppure memorizzare in cache un calcolo costoso.

---

## 7. Gerarchie di Tipi, Principio di Sostituzione di Liskov (LSP) e Composizione (PDJ 7)

### 7.1 Estensione, Sottotipazione, Tipo Apparente e Tipo Concreto

In Java una classe $A$ può estendere una classe $B$ (`class A extends B`) o implementare un'interfaccia $I$ (`class A implements I`). Si dice che $A$ è **sottotipo** di $B$, indicato con:
$$A \prec B$$
La relazione $\prec$ è **transitiva** ($A \prec B \land B \prec C \implies A \prec C$) e ha `Object` come radice universale.

Per la regola di assegnamento polimorfico, a una variabile di tipo $B$ può essere assegnato un oggetto di qualunque sottotipo $A \prec B$:

```java
Studente s = new StudenteMagistrale(...);
```

- **Tipo Apparente** (`Studente`): È il tipo dichiarato a compile-time. Determina **quali metodi e attributi sono visibili/invocabili** sul riferimento.
- **Tipo Concreto** (`StudenteMagistrale`): È la classe effettiva dell'oggetto istanziato nello Heap a runtime. Determina **quale implementazione del metodo viene eseguita**.

### 7.2 Principio di Sostituzione di Liskov (LSP)

> **Definizione (LSP)**: Se $T \prec S$, allora gli oggetti di tipo $S$ in un programma devono poter essere sostituiti con oggetti di tipo $T$ senza alterare alcuna delle proprietà desiderabili di correttezza del programma stesso.

Affinché una sottoclasse $T \prec S$ rispetti LSP, devono valere **tre regole**:

1. **Regola delle Segnature** _(verificata automaticamente dal compilatore)_:
   Per ogni metodo $m$ di $S$, deve esistere in $T$ un metodo $m'$ con segnatura compatibile:
   - Stesso nome e **stessi identici tipi dei parametri** (in Java i parametri sono invarianti).
   - Tipo di ritorno **covariante**: $m'$ può restituire lo stesso tipo di $m$ oppure un suo sottotipo ($R' \prec R$).
   - Eccezioni: $m'$ non può lanciare nuove _checked exceptions_ non dichiarate da $m$; può lanciarne in numero minore o di tipo più specifico (sottotipi).
2. **Regola dei Metodi** _(comportamentale, NON verificata dal compilatore)_:
   Il metodo ridefinito $m'$ in $T$ deve comportarsi in modo coerente con $m$ in $S$:
   - **Precondizioni indebolite** (o uguali): $m'$ può accettare un dominio di input più ampio di $m$ (`REQUIRES` meno restrittivo), ma non può aggiungere nuovi vincoli che rifiutino input validi per $S$.
   - **Postcondizioni rinforzate** (o uguali): $m'$ deve garantire almeno tutto ciò che prometteva $m$ (`EFFECTS` più forte o uguale).
3. **Regola delle Proprietà** _(NON verificata dal compilatore)_:
   $T$ deve preservare tutte le proprietà dimostrabili per $S$:
   - **Invarianti**: Tutte le invarianti di $S$ devono valere anche per $T$. Nella `repOk()` della sottoclasse occorre **sempre** invocare **`super.repOk()`** oltre a controllare i nuovi attributi.
   - **Proprietà evolutive**: I vincoli sulle transizioni di stato di $S$ devono essere rispettati anche da $T$ (es. se in `Studente` un esame verbalizzato non può essere cancellato, `StudenteMagistrale` non può introdurre un metodo `rimuoviEsame()`).

### 7.3 Classi Concrete, Classi Astratte e Interfacce

| Caratteristica             | Classe Concreta                     | Classe Astratta (`abstract class`)               | Interfaccia (`interface`)                     |
| :------------------------- | :---------------------------------- | :----------------------------------------------- | :-------------------------------------------- |
| **Istanziabile con `new`** | Sì                                  | No                                               | No                                            |
| **Attributi di stato**     | Sì (qualsiasi visibilità)           | Sì (qualsiasi visibilità)                        | No (solo costanti `public static final`)      |
| **Costruttori**            | Sì                                  | Sì (invocati con `super(...)` dalle sottoclassi) | No                                            |
| **Metodi**                 | Tutti implementati                  | Mix di metodi implementati e `abstract`          | Metodi astratti + metodi `default` e `static` |
| **Ereditarietà**           | Singola (`extends` una sola classe) | Singola (`extends` una sola classe)              | **Multipla** (`implements I1, I2, ...`)       |

- **Implementazione per comportamento nelle interfacce**: I metodi `default` di un'interfaccia, non avendo accesso a uno stato proprio, vengono implementati invocando gli altri metodi astratti dichiarati nell'interfaccia stessa.
- **Risoluzione dei conflitti nell'ereditarietà multipla**:
  1. Una classe ha sempre la precedenza su un'interfaccia (regola _class wins_).
  2. Se una classe implementa due interfacce che forniscono lo stesso metodo `default`, il compilatore genera errore e impone alla classe di ridefinire esplicitamente il metodo (eventualmente delegando con `Interfaccia1.super.metodo()`).

### 7.4 Visibilità e Dispatching Dinamico

- **Modificatori di Visibilità**:
  - `private`: visibile solo all'interno della classe stessa.
  - _package-private_ (nessuna keyword): visibile a tutte le classi dello stesso package.
  - `protected`: visibile alle classi dello stesso package **e** a tutte le sottoclassi (anche in package diversi). _Attenzione_: rendere `protected` un attributo espone la rappresentazione alle sottoclassi.
  - `public`: visibile ovunque.
- **Chiamata del costruttore della superclasse**: In una sottoclasse, la chiamata `super(...)` **deve essere obbligatoriamente la prima istruzione** del costruttore.
- **Meccanismo di Dispatching in Java**:
  1. **A Compile-Time (scelta della segnatura)**: Il compilatore guarda il **tipo apparente** del ricevente e i tipi apparenti degli argomenti per scegliere la segnatura più specifica (risolvendo l'eventuale _overloading_). Se il metodo non esiste nel tipo apparente, si ha errore di compilazione (serve un cast esplicito, es. `((Punto3D) p).getZ()`).
  2. **A Runtime (scelta dell'implementazione)**: La JVM guarda il **tipo concreto** dell'oggetto nello Heap ed esegue la versione più specifica (override) del metodo scelto a compile-time (_Dynamic Method Dispatch_).

### 7.5 Uguaglianza nelle Gerarchie, Astrazioni Ontologiche Errate e Composizione

- **Il problema di `equals` nelle gerarchie**:
  - Usando `getClass() != o.getClass()`, un `Punto3D(1, 2, 0)` non è mai uguale a un `Punto2D(1, 2)` (rigido, preserva simmetria e transitività).
  - Usando `o instanceof Punto2D` e confrontando solo `x` e `y` se l'altro oggetto è `Punto2D`, ma confrontando anche `z` se entrambi sono `Punto3D`, si preserva la simmetria ma si **viola la transitività**: $P_{3D}(1,2,3) = P_{2D}(1,2)$ e $P_{2D}(1,2) = P_{3D}(1,2,9)$, ma $P_{3D}(1,2,3) \neq P_{3D}(1,2,9)$!
- **Astrazioni Ontologiche Errate (`Quadrato extends Rettangolo`)**:
  Nel mondo reale (ontologicamente) un quadrato _è un_ rettangolo. Tuttavia, in un modello mutabile con `setBase(b)` e `setAltezza(h)`:
  - Se `Quadrato` non ridefinisce `setAltezza`, chiamandola si ottiene un quadrato con lati diversi (violazione dell'invariante di `Quadrato`).
  - Se `Quadrato` ridefinisce `setAltezza` modificando anche la base, viola la postcondizione di `Rettangolo` (secondo cui `setAltezza` lascia invariata la base), violando la **Regola dei Metodi di LSP**.
- **Soluzione: Composizione e Delega (_HAS-A_ invece di _IS-A_)**:
  Invece di estendere una classe rischiando di violare LSP o l'incapsulamento, la nuova classe contiene un'istanza privata della classe esistente come attributo e implementa i propri metodi delegando le chiamate ad essa.

---

## 8. Astrazione di Iterazione e Classi Innestate / Nested Classes (PDJ 6)

### 8.1 `Iterator<E>` vs `Iterable<E>`

L'astrazione di iterazione permette di scorrere sequenzialmente gli elementi di una struttura dati senza esporne la rappresentazione interna e senza richiedere indicizzazione.

- **`java.util.Iterator<E>`** (l'oggetto che compie l'iterazione e mantiene lo stato corrente):
  - `boolean hasNext()`: restituisce `true` se ci sono ancora elementi da visitare (metodo osservatore).
  - `E next()`: restituisce l'elemento successivo e avanza lo stato dell'iteratore; lancia `NoSuchElementException` se `!hasNext()` (metodo mutatore).
  - `default void remove()`: rimuove l'ultimo elemento restituito da `next()` (opzionale, spesso lancia `UnsupportedOperationException` per evitare esposizione/modifica non controllata).
- **`java.lang.Iterable<E>`** (il contenitore che produce iteratori indipendenti):
  - `Iterator<E> iterator()`: restituisce una **nuova istanza** di `Iterator<E>` posizionata all'inizio della sequenza.
  - Permette di avere più iterazioni contemporanee e indipendenti sullo stesso oggetto (es. cicli annidati).
  - Abilita il costrutto sintattico **`for (E elem : collezione)`** (_enhanced for-each_), che il compilatore traduce in un ciclo `while` basato su `iterator()`, `hasNext()` e `next()`.

> **Iteratori Standalone vs Contenitori `Iterable`**:
> Una classe implementa direttamente `Iterator<E>` quando l'iterazione è la sua unica ragion d'essere o è intrinsecamente sequenziale (es. `Range`, generatore di numeri primi, `Scanner`). Implementa invece `Iterable<E>` quando è un contenitore di dati su cui più client devono poter iterare indipendentemente (es. `IntSet`, `Studente`).

### 8.2 Le Quattro Tipologie di Classi Innestate (_Nested Classes_)

Per implementare un `Iterator<E>` in un contenitore `Iterable<E>` senza rendere pubblica la rappresentazione interna, si definisce una classe interna (che, facendo parte del codice della classe contenitore, ha pieno accesso ai suoi campi `private`):

1. **Static Nested Class**:
   - Dichiarata con `private static class MioIter implements Iterator<E>` dentro la classe esterna.
   - **Non** possiede un riferimento implicito all'istanza della classe esterna: per accedere agli attributi d'istanza del contenitore deve riceverne un riferimento esplicito nel costruttore (`new MioIter(this)`).
2. **Inner Class (non statica)**:
   - Dichiarata senza `static` (`private class MioIter implements Iterator<E>`).
   - Ogni sua istanza è legata a una specifica istanza della classe esterna, accessibile tramite `ClasseEsterna.this` (o direttamente accedendo ai suoi campi privati).
3. **Local Inner Class**:
   - Dichiarata direttamente **dentro il corpo del metodo `iterator()`** (senza modificatori di visibilità come `private` o `static`).
   - È visibile esclusivamente all'interno di quel metodo. All'esterno viene restituita tramite il tipo apparente dell'interfaccia `Iterator<E>`.
4. **Anonymous Inner Class (Classe Anonima)**:
   - Classe locale senza nome dichiarata e istanziata in un'unica espressione direttamente nel `return` di `iterator()`:
     ```java
     @Override
     public Iterator<String> iterator() {
         return new Iterator<String>() {
             private int index = 0;
             @Override
             public boolean hasNext() {
                 return index < esami.size();
             }
             @Override
             public String next() {
                 if (!hasNext()) throw new NoSuchElementException();
                 return esami.get(index++);
             }
         };
     }
     ```
   - _Nota importante_: Una classe locale o anonima può accedere alle variabili locali del metodo che la racchiude solo se queste sono **`final` o _effectively final_** (non vengono mai riassegnate).

---

## 9. Polimorfismo e Generics in Java (PDJ 8 + Java Generics)

### 9.1 Tassonomia del Polimorfismo

1. **Polimorfismo Ad-hoc**:
   - _Overloading_: stesso nome di metodo, parametri diversi (risolto a compile-time).
   - _Overriding_: ridefinizione di un metodo ereditato in una sottoclasse (risolto a runtime).
2. **Polimorfismo di Sottotipo (_Subtyping_ / Inclusione)**: Capacità di una variabile con tipo apparente $B$ di riferirsi a istanze di qualunque sottotipo $A \prec B$.
3. **Polimorfismo Parametrico**: Capacità di scrivere classi o metodi che operano in modo uniforme su tipi diversi specificati come parametri di tipo.

### 9.2 Polimorfismo Parametrico per Subtyping vs Generics

Prima dell'introduzione dei Generics (Java 5), le collezioni usavano `Object` come tipo apparente (_polimorfismo per subtyping_). Ciò comportava tre gravi difetti:

- Necessità di **casting espliciti** continui in lettura.
- Assenza di **type-safety** a compile-time (possibilità di inserire tipi eterogenei per errore).
- Rischio di **`ClassCastException` a runtime**.

I **Generics** risolvono questi problemi introducendo **parametri di tipo** tra parentesi angolari (`<T>`, `<E>`, `<K, V>`), garantendo il controllo statico dei tipi in fase di compilazione. L'operatore **Diamond (`<>`)** consente l'inferenza automatica del tipo in fase di istanziazione (`List<String> l = new ArrayList<>();`). L'uso di un tipo generico senza parametri (_Raw Type_, es. `new ArrayList()`) ricade nel comportamento pre-Java 5 su `Object` generando warning di compilazione.

### 9.3 Relazioni di Sottotipo e Varianza nei Generics

Dato $S \prec T$ (es. `Integer` $\prec$ `Number`, oppure `Dog` $\prec$ `Animal`):

1. **Covarianza del contenitore**: Se $G_1 \prec G_2$, allora $G_1\langle T \rangle \prec G_2\langle T \rangle$ (es. `ArrayList<Integer>` $\prec$ `List<Integer>`).
2. **Invarianza del parametro di tipo**: $G\langle S \rangle$ **NON è sottotipo** di $G\langle T \rangle$ (es. `List<Integer>` **non** è sottotipo di `List<Number>`, né `List<Dog>` è sottotipo di `List<Animal>`).
   - _Motivazione_: Se fosse permesso `List<Animal> animals = new ArrayList<Dog>();`, si potrebbe chiamare `animals.add(new Cat())`, corrompendo la lista di cani e violando la type-safety!
     _(Nota: Gli array in Java sono invece covarianti — `Dog[]` $\prec$ `Animal[]` — ma controllano il tipo a runtime lanciando `ArrayStoreException`)._

### 9.4 Bounded Type Parameters e Wildcards (`?`)

Per rendere flessibili i metodi che operano su tipi generici si usano i vincoli (_bounds_) e le _wildcards_:

- **Upper Bound (`<T extends SuperTipo>` o `<? extends SuperTipo>`)**:
  - Accetta `SuperTipo` e qualsiasi suo sottotipo (es. `List<? extends Number>` accetta `List<Integer>`, `List<Double>`, `List<Number>`).
  - **Effetto**: Si può **leggere** dalla struttura come `SuperTipo`, ma **non si può scrivere/aggiungere** alcun elemento (eccetto `null`), perché il compilatore non conosce quale sottotipo concreto sia effettivamente istanziato.
- **Lower Bound (`<? super SottoTipo>`)**:
  - Accetta `SottoTipo` e qualsiasi suo supertipo (es. `List<? super Integer>` accetta `List<Integer>`, `List<Number>`, `List<Object>`).
  - **Effetto**: Si possono **aggiungere** istanze di `SottoTipo`, ma in lettura si ottiene solo `Object`.
- **Unbounded Wildcard (`<?>`)**:
  - Accetta qualsiasi tipo generico (`List<?>`). Si legge solo come `Object` e non si può scrivere nulla (tranne `null`).
- **Principio PECS (_Producer Extends, Consumer Super_)**: Se una struttura parametrica _produce_ dati da leggere, usa `extends`; se _consuma_ dati da inserirvi dentro, usa `super`.

### 9.5 Funzionamento Interno: Type Erasure e Bridge Methods

Per mantenere piena retrocompatibilità con il bytecode preesistente, Java implementa i Generics tramite **Type Erasure** a tempo di compilazione:

1. Il compilatore verifica la correttezza di tutti i tipi generici.
2. Rimuove tutte le parentesi angolari e sostituisce ogni parametro di tipo `<T>` con il suo **upper bound** (o con **`Object`** se non ha bound).
3. Inserisce automaticamente i **cast espliciti** nei punti in cui i valori vengono estratti.
4. Genera eventuali **Bridge Methods** sintetici per garantire che il dispatching dinamico (overriding) continui a funzionare correttamente dopo la cancellazione dei tipi.

### 9.6 Ordinamento: `Comparable<T>` vs `Comparator<T>`

- **`Comparable<T>`** (in `java.lang`): Definisce l'**ordinamento naturale** interno alla classe stessa.
  - Metodo: `public int compareTo(T other)`
  - Contratto: Restituisce un intero $< 0$ se `this < other`, $0$ se `this` e `other` sono equivalenti nell'ordinamento, $> 0$ se `this > other`. _(È fortemente consigliato che `compareTo` sia coerente con `equals`: `x.compareTo(y) == 0` $\iff$ `x.equals(y)`)._
- **`Comparator<T>`** (in `java.util`): Definisce un criterio di ordinamento **esterno** alla classe, permettendo di ordinare gli stessi oggetti secondo criteri multipli o senza modificare la classe originale (es. `lista.sort(comparator)`).
  - Metodo: `public int compare(T o1, T o2)` (stessa convenzione sul segno dell'intero restituito).

---

## 10. Programmazione Funzionale in Java

### 10.1 Paradigma Dichiarativo e Funzionale

- **Programmazione Imperativa**: Specifica _come_ eseguire il calcolo passo dopo passo mutando variabili.
- **Programmazione Dichiarativa / Funzionale**: Specifica _cosa_ calcolare combinando funzioni in senso matematico.
- **Concetti Chiave**:
  - **Trasparenza Referenziale**: Un'espressione è referenzialmente trasparente se può essere sostituita dal suo valore risultante in qualsiasi punto del programma senza alterarne il comportamento.
  - **Funzioni Pure**: Funzioni prive di effetti collaterali (`MODIFIES` vuoto): il risultato dipende esclusivamente dai parametri in ingresso.
  - **Funzioni come Cittadini di Prima Classe e Funzioni di Ordine Superiore**: Possibilità di passare funzioni come argomenti ad altre funzioni, restituirle come risultato e assegnarle a variabili.
  - **Immutabilità**: Assenza di mutazione dello stato.

### 10.2 Interfacce Funzionali e Lambda Expressions

Poiché in Java ogni codice deve risiedere in una classe/oggetto, la programmazione funzionale (introdotta da Java 8) si poggia sulle **Interfacce Funzionali** (`@FunctionalInterface`): interfacce con **un solo metodo astratto** (più eventuali metodi `default` o `static`).

Principali interfacce funzionali in `java.util.function`:

- `Function<T, R>`: metodo `R apply(T t)` (trasforma un `T` in un `R`). Metodi `default` di composizione:
  - `f1.compose(f2)`: applica prima `f2`, poi `f1` ($f_1(f_2(x))$).
  - `f1.andThen(f2)`: applica prima `f1`, poi `f2` ($f_2(f_1(x))$).
- `Predicate<T>`: metodo `boolean test(T t)` (predicato booleano).
- `Consumer<T>`: metodo `void accept(T t)` (consuma un input senza restituire nulla).
- `Supplier<T>`: metodo `T get()` (fornisce un valore senza parametri in ingresso).
- `BiFunction<T, U, R>`: metodo `R apply(T t, U u)`.

#### Lambda Expressions e Currying

Le **Lambda Expressions** offrono una sintassi compatta per istanziare al volo un'interfaccia funzionale (sostituendo classi anonime verbose):

```java
// Forma espressione singola
Function<Integer, Integer> doppio = x -> x * 2;
Comparator<String> perLunghezza = (s1, s2) -> Integer.compare(s1.length(), s2.length());

// Forma blocco (richiede parentesi graffe e return esplicito)
Predicate<Integer> isPari = n -> {
    int resto = n % 2;
    return resto == 0;
};
```

- **Currying**: Tecnica funzionale che trasforma una funzione a più parametri in una sequenza di funzioni a parametro singolo che restituiscono altre funzioni (es. `Function<Integer, Function<Integer, Integer>> somma = a -> b -> a + b;`, invocabile con `somma.apply(2).apply(3)`).

### 10.3 Java Streams API

Uno **`Stream<T>`** (`java.util.stream`) è una sequenza di elementi su cui eseguire pipeline di elaborazioni dichiarative in modo conciso (e potenzialmente parallelizzabile).

- Si ottiene da una collezione con `lista.stream()` o da un array con `Arrays.stream(arr)`.
- **Operazioni Intermedie** (_lazy_, restituiscono un nuovo `Stream`): `filter(Predicate)`, `map(Function)`, `distinct()`, `sorted()`.
- **Operazioni Terminali** (_eager_, chiudono lo stream e producono un risultato o un effetto): `count()`, `forEach(Consumer)`, `anyMatch(Predicate)`, `allMatch(Predicate)`, `toList()`.
- _Nota_: Uno `Stream` **non è riutilizzabile** dopo l'esecuzione di un'operazione terminale (tentare di riutilizzarlo lancia `IllegalStateException`).

---

## 11. Ingegneria del Software e Design Pattern

### 11.1 Pattern Architetturali vs Design Pattern

- **Pattern Architetturali**: Organizzano l'intera struttura macroscopica del software separando i moduli per responsabilità, in particolare dividendo la **Business Logic** (gestione dei dati e delle regole di dominio) dalla **Presentation Logic** (interfaccia utente). Esempi: **MVC** (_Model-View-Controller_), **MVP** (_Model-View-Presenter_), **MVVM** (_Model-View-ViewModel_).
- **Design Pattern (GoF)**: Soluzioni progettuali standardizzate, riutilizzabili e indipendenti dallo specifico algoritmo per risolvere problemi ricorrenti di interazione tra classi e oggetti. Si dividono in tre famiglie: _Creational_, _Structural_ e _Behavioral_.

### 11.2 Singleton _(Creational Pattern)_

- **Scopo**: Garantire che di una classe esista **una sola istanza** nell'intera applicazione e fornire un punto di accesso globale controllato ad essa (es. gestore unico di connessione a un database o logger centralizzato).
- **Struttura**:
  1. Costruttore **`private`** (impedisce `new Singleton()` dall'esterno).
  2. Attributo **`private static`** che mantiene il riferimento all'unica istanza.
  3. Metodo factory **`public static getInstance()`** che crea l'istanza alla prima chiamata (_lazy initialization_) e restituisce sempre la stessa nelle chiamate successive.

```java
public class DatabaseManager {
    private static DatabaseManager instance;

    private DatabaseManager() { /* inizializzazione */ }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }
}
```

### 11.3 Decorator _(Structural Pattern)_

- **Scopo**: Aggiungere dinamicamente nuove responsabilità o comportamenti a un oggetto **già istanziato** a runtime, evitando l'esplosione combinatoria di sottoclassi che si avrebbe con l'ereditarietà statica.
- **Struttura**:
  1. Un'**interfaccia comune** (`Component`) che definisce le operazioni base.
  2. Una **classe concreta base** che implementa l'interfaccia comune.
  3. Una o più **classi Decorator** che implementano la **stessa interfaccia comune** e contengono per **composizione** un riferimento a un oggetto di tipo `Component`, estendendone i metodi per **delega**.

```java
public interface Punto {
    int getX();
    int getY();
}

public class PuntoBase implements Punto {
    private final int x, y;
    public PuntoBase(int x, int y) { this.x = x; this.y = y; }
    public int getX() { return x; }
    public int getY() { return y; }
}

public class PuntoColorato implements Punto {
    private final Punto componente; // Composizione
    private final String colore;

    public PuntoColorato(Punto componente, String colore) {
        this.componente = componente;
        this.colore = colore;
    }
    public int getX() { return componente.getX(); } // Delega
    public int getY() { return componente.getY(); } // Delega
    public String getColore() { return colore; }
}
```

### 11.4 Iterator e Observer _(Behavioral Patterns)_

- **Iterator**: Disaccoppia l'algoritmo di attraversamento di una collezione dalla struttura interna della collezione stessa (trattato nel Capitolo 8).
- **Observer**: Definisce una dipendenza **uno-a-molti** tra oggetti in modo che, quando l'oggetto osservato (_Subject / Publisher / Creator_) cambia stato o genera un evento, tutti gli oggetti iscritti (_Observers / Listeners / Subscribers_) vengano notificati e aggiornati automaticamente.
  - **Struttura**:
    1. Interfaccia `Listener` con metodo di callback (es. `void update(String evento)`).
    2. Classi `Subscriber` concrete che implementano `Listener`.
    3. Classe `Creator` che mantiene una collezione di `Listener`, espone metodi `subscribe(Listener)` e `unsubscribe(Listener)`, e al verificarsi dell'evento itera su tutti i listener registrati invocando `l.update(...)`.

---

## 12. Appendice: Checklist Operativa per l'Esame (Teoria e Laboratorio)

1. **Specifiche in ogni classe e metodo**:
   - Scrivi sempre `// OVERVIEW:` su ogni classe dichiarando se è **mutabile** o **immutabile**.
   - Su ogni metodo (tranne semplici getter e metodi standard di `Object` come `equals`, `hashCode`, `toString`, `clone`) scrivi `REQUIRES` (solo se parziale), `MODIFIES` (solo se modifica qualcosa, inclusi `this`, `System.in`, `System.out`) ed `EFFECTS` (sempre).
2. **Totalità tramite Eccezioni**:
   - Valida sempre i parametri nei costruttori e nei metodi pubblici. Se lanci un'eccezione per un input non valido, documentala in `EFFECTS` e **non** metterla in `REQUIRES`.
3. **Protezione della Rappresentazione**:
   - Tutti gli attributi devono essere `private` (e `final` se immutabili).
   - Esegui copie difensive di array e collezioni mutabili sia in ingresso (costruttori/mutatori) sia in uscita (osservatori) e dentro `clone()`.
4. **Invariante di Rappresentazione e Funzione di Astrazione**:
   - Implementa `public boolean repOk()` (chiamando `super.repOk()` nelle sottoclassi!) e invocalo con `assert repOk();` alla fine di ogni costruttore e metodo mutatore.
   - Implementa `toString()` come funzione di astrazione ($AF$) descrivendo l'entità astratta in modo chiaro e completo.
5. **Gerarchie e LSP**:
   - Verifica che ogni sottoclasse rispetti le tre regole di LSP (Segnature, Metodi, Proprietà). Se estendere una classe viola LSP (come `Quadrato` vs `Rettangolo`), usa **Composizione e Delega**.
   - Ricorda che nel costruttore di una sottoclasse `super(...)` deve essere la **prima istruzione**.
6. **Iteratori e Generics**:
   - Preferisci `Iterable<E>` + classe interna/anonima per `Iterator<E>` evitando di esporre `remove()` se può rompere l'invariante della classe.
   - Ricorda che i Generics sono **invarianti** (`List<Integer>` non è sottotipo di `List<Number>`): usa `<? extends T>` per leggere e `<? super T>` per scrivere.
