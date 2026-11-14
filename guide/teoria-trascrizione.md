# Guida Teorica Completa — Programmazione II (Java 21)

**Università degli Studi di Milano (UNIMI) — Corso di Laurea in Informatica**

_Basata sulle lezioni del Prof. Dragan Ahmetovic, sulle slide ufficiali e sul testo "Program Development in Java" (PDJ) di Barbara Liskov e John Guttag._

## Indice dei Contenuti

1.  [Introduzione: Decomposizione e Astrazione (PDJ 1)](#1-introduzione-decomposizione-e-astrazione-pdj-1)

2.  [Il Paradigma Object-Oriented e Fondamenti di Java 21 (PDJ 2)](#2-il-paradigma-object-oriented-e-fondamenti-di-java-21-pdj-2)

3.  [Astrazione Procedurale e Linguaggio di Specifica (PDJ 3, 9)](#3-astrazione-procedurale-e-linguaggio-di-specifica-pdj-3-9)

4.  [Gestione delle Eccezioni (PDJ 4)](#4-gestione-delle-eccezioni-pdj-4)

5.  [Astrazione dei Tipi di Dato (ADT) e Implementazione (PDJ 5.1–5.4, 5.8)](#5-astrazione-dei-tipi-di-dato-adt-e-implementazione-pdj-5154-58)

6.  [Correttezza degli ADT: Funzione di Astrazione (AF) e Invariante di Rappresentazione (RI) (PDJ 5.5–5.7)](#6-correttezza-degli-adt-funzione-di-astrazione-af-e-invariante-di-rappresentazione-ri-pdj-5557)

7.  [Gerarchie di Tipi, Principio di Sostituzione di Liskov (LSP) e Composizione (PDJ 7)](#7-gerarchie-di-tipi-principio-di-sostituzione-di-liskov-lsp-e-composizione-pdj-7)

8.  [Astrazione di Iterazione e Classi Innestate / Nested Classes (PDJ 6)](#8-astrazione-di-iterazione-e-classi-innestate--nested-classes-pdj-6)

9.  [Polimorfismo e Generics in Java (PDJ 8 + Java Generics)](#9-polimorfismo-e-generics-in-java-pdj-8--java-generics)

10. [Programmazione Funzionale in Java](#10-programmazione-funzionale-in-java)

11. [Ingegneria del Software e Design Pattern (PDJ 15)](#11-ingegneria-del-software-e-design-pattern-pdj-15)

12. [Compendio Completo di Tutte le Definizioni, Formulario $\prec$ e Trappole d'Esame (L01–L16)](#12-compendio-completo-di-tutte-le-definizioni-formulario-prec-e-trappole-desame-l01l16)

13. [Appendice: Checklist Operativa per l'Esame (Teoria e Laboratorio)](#13-appendice-checklist-operativa-per-lesame-teoria-e-laboratorio)

## 1. Introduzione: Decomposizione e Astrazione (PDJ 1)

L'obiettivo dell'ingegneria del software e della programmazione su larga scala è costruire sistemi software dotati di **modularità**, **riusabilità**, **correttezza** e **manutenibilità**.

### 1.1 Dalla Programmazione Imperativa a quella Procedurale

> **Definizione — Programmazione Imperativa:** Paradigma di programmazione in cui il programma è costituito da una sequenza lineare di comandi/istruzioni eseguite in un ordine preciso che modificano direttamente lo stato della memoria.

I limiti della programmazione imperativa pura al crescere delle dimensioni del software sono: **scarsa comprensibilità** (un unico listato lungo), **difficoltà di riuso** (duplicazione del codice) e **scarsa modificabilità** (modificare un requisito richiede interventi sparsi ovunque).

> **Definizione — Programmazione Procedurale:** Evoluzione del paradigma imperativo in cui le istruzioni vengono raggruppate in **procedure** (funzioni/sottoprogrammi), ovvero unità logiche isolate, riutilizzabili e parametrizzabili.

**Limiti aperti della programmazione procedurale** (che motivano il passaggio alla OOP):

- Difficoltà nel mantenere coerente lo **stato condiviso/globale**.

- Difficoltà nel coordinare molti sviluppatori su migliaia di procedure indipendenti.

- Assenza di incapsulamento tra dati e operazioni che li manipolano.

### 1.2 Decomposizione

> **Definizione — Decomposizione:** Processo progettuale che consiste nel suddividere un problema complesso in sottoproblemi più semplici (**moduli**), la cui combinazione risolve il problema originale.

Una buona decomposizione produce moduli caratterizzati da:

1. **Alta coesione logica**: Ogni modulo svolge un singolo compito chiaro e ben definito.

2. **Indipendenza**: Ogni sottoproblema è isolabile, implementabile e testabile separatamente dagli altri.

3. **Stesso livello di dettaglio**: I sottoproblemi devono trovarsi a un livello di astrazione omogeneo (non mescolare operazioni ad alto livello con dettagli di basso livello).

4. **Basso accoppiamento (_Low Coupling_)**: Dipendenze minime tra i moduli.

### 1.3 Astrazione: Meccanismi, Benefici e Tipologie

> **Definizione — Astrazione:** Processo mentale e progettuale che costituisce un **mapping molti-a-uno** (più problemi concreti vengono mappati a un'unica soluzione generale), permettendo di trascurare i dettagli implementativi irrilevanti (_"come"_) per concentrarsi sugli aspetti essenziali (_"cosa"_ e su quali dati).

**Benefici fondamentali dell'astrazione**:

> **Definizione — Località:** Proprietà per cui un'astrazione può essere implementata, compresa e verificata in modo disgiunto dal resto del programma; chi la utilizza deve conoscerne esclusivamente la specifica.

> **Definizione — Modificabilità:** Proprietà per cui l'implementazione interna di un'astrazione può essere sostituita o ottimizzata senza alterare il comportamento dei programmi che la utilizzano, purché la specifica resti invariata.

#### I Due Meccanismi di Astrazione

> **Definizione — Astrazione per Parametrizzazione:** Meccanismo di astrazione (sui **dati**) che generalizza un modulo sostituendo valori concreti specifici con **parametri formali**, rendendo il codice applicabile a un insieme più ampio di input.

> **Definizione — Astrazione per Specifica:** Meccanismo di astrazione (sui **vincoli delle operazioni**) che separa il comportamento atteso (_"cosa fa"_) dall'implementazione concreta (_"come lo fa"_), descrivendo input, precondizioni ed effetti mediante intestazioni e commenti.

#### I Quattro Tipi di Astrazione del Corso

1. **Astrazione Procedurale**: Definizione di sottoprogrammi (metodi) tramite parametrizzazione e specifica.

2. **Astrazione dei Tipi di Dato (ADT)**: Definizione di nuovi tipi di dato che incapsulano stato e comportamenti.

3. **Astrazione di Gerarchia dei Tipi**: Estensione e specializzazione di tipi esistenti tramite sottotipazione.

4. **Astrazione di Iterazione**: Accesso sequenziale e uniforme agli elementi di una collezione senza esporne la rappresentazione interna.

## 2. Il Paradigma Object-Oriented e Fondamenti di Java 21 (PDJ 2)

### 2.1 Paradigma Object-Oriented, Classi e Oggetti

> **Definizione — Programmazione Orientata agli Oggetti (OOP):** Paradigma di programmazione in cui i dati sono strutturati come **oggetti**, ciascuno dotato sia di uno **stato** (un insieme di attributi che ne caratterizzano le proprietà) sia di **comportamenti** (metodi che ispezionano o modificano lo stato dell'oggetto stesso).

> **Definizione — Classe:** L'archetipo o stampino (_blueprint_) che definisce la struttura (attributi) e i comportamenti (metodi) comuni a tutti gli oggetti di un determinato tipo.

> **Definizione — Oggetto (Istanza):** Una singola entità concreta creata in memoria a runtime a partire da una classe, caratterizzata da valori specifici per i propri attributi.

### 2.2 I Pilastri della OOP e i Package

> **Definizione — Incapsulamento (_Encapsulation_):** Principio secondo cui lo stato interno di un oggetto viene nascosto all'esterno (`private`), imponendo che ogni ispezione o modifica avvenga esclusivamente attraverso i metodi pubblici dell'oggetto stesso, garantendo così la coerenza dello stato.

> **Definizione — Package:** Costrutto organizzativo gerarchico di Java che raggruppa classi correlate, delimita la visibilità a livello di modulo ed evita conflitti di nomi identificando univocamente ogni classe tramite il suo **Fully Qualified Name** (es. `java.util.ArrayList`). Il package `java.lang` è importato automaticamente.

### 2.3 Compilazione, Bytecode, JVM ed Espressioni

> **Definizione — Bytecode e JVM:** Il compilatore `javac` traduce il codice sorgente Java (`.java`) in **Bytecode** (`.class`), un codice intermedio indipendente dall'hardware. La **Java Virtual Machine (JVM)** (`java`) interpreta ed esegue il bytecode sull'architettura specifica (_Write Once, Run Anywhere_).

> **Definizione — Espressione:** Costrutto sintattico formato da **atomi** (variabili, costanti, letterali), **operatori** e **invocazioni di metodi**. Viene valutata da sinistra verso destra e produce sempre un **tipo** (noto a compile-time per induzione) e un **valore** (calcolato a runtime).

> **Definizione — Type Safety (Sicurezza dei Tipi):** Proprietà di Java per cui il tipo di ogni espressione e variabile è determinato e verificato staticamente dal compilatore, impedendo errori di tipo durante l'esecuzione.

### 2.4 Memoria (Stack vs Heap), Aliasing e Garbage Collector

> **Definizione — Tipi Primitivi vs Tipi Riferimento:** I **tipi primitivi** (`byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean`) memorizzano il proprio valore direttamente nello **Stack** (memoria veloce e limitata). I **tipi riferimento** (oggetti, array, `String`) memorizzano nello **Stack** un puntatore (indirizzo) e allocano i dati effettivi nello **Heap** (memoria dinamica).

> **Definizione — Aliasing:** Situazione in cui due o più variabili di tipo riferimento nello Stack contengono lo stesso indirizzo di memoria e puntano quindi al medesimo oggetto nello Heap.

> **Definizione — Garbage Collector (GC):** Meccanismo automatico della JVM che individua nello Heap gli oggetti non più raggiungibili da alcun riferimento attivo nello Stack e ne dealloca la memoria.

> **Definizione — Oggetto Immutabile vs Mutabile:** Un oggetto è **immutabile** se il suo stato interno nello Heap non può essere modificato dopo l'istanziazione (es. `String`, `Integer`); è **mutabile** se il suo stato può essere alterato tramite metodi mutatori (es. `ArrayList`, array).

### 2.5 Passaggio Parametri, Overloading e Wrapping

> **Definizione — Passaggio dei Parametri per Valore:** In Java tutti i parametri sono passati per valore: per i primitivi viene copiato il valore nello Stack; per i riferimenti viene copiato l'indirizzo nello Stack (il metodo non può riassegnare il riferimento del chiamante, ma può mutare l'oggetto nello Heap se mutabile).

> **Definizione — Overloading (Sovraccaricamento):** Possibilità di definire più metodi con lo stesso nome all'interno della stessa classe (o gerarchia) purché differiscano nella lista dei parametri formali (per numero, tipo o ordine).

> **Definizione — Most Specific Call:** Meccanismo di risoluzione a compile-time dell'overloading in presenza di conversioni implicite (_widening_, es. `int` $\to$ `double`): il compilatore sceglie il metodo che richiede il minor numero di conversioni; se due metodi richiedono lo stesso numero di conversioni (es. `m(int, double)` e `m(double, int)` invocati con `m(1, 2)`), genera un errore di compilazione per **ambiguità**.

> **Definizione — Wrapping, Autoboxing e Unboxing:** Il **wrapping** è l'incapsulamento di un tipo primitivo nella corrispondente classe Wrapper immutabile (`int` $\to$ `Integer`, `double` $\to$ `Double`, ecc.). L'**autoboxing** è la conversione implicita da primitivo a Wrapper; l'**unboxing** è la conversione implicita inversa da Wrapper a primitivo (avviene anche nei confronti con `==` tra primitivo e Wrapper).

## 3. Astrazione Procedurale e Linguaggio di Specifica (PDJ 3, 9)

### 3.1 Specifica Procedurale e Clausole di Liskov

> **Definizione — Specifica Procedurale:** Contratto formale o semiformale tra implementatore e chiamante di una procedura: definisce in modo **dichiarativo** (_"cosa"_, mai _"come"_, evitando specifiche operazionali) le condizioni d'uso e gli effetti dell'esecuzione.

Nel corso si utilizza la notazione di Barbara Liskov tramite commenti strutturati:

- **`REQUIRES` (Precondizioni)**: Vincoli sul dominio degli input non già espressi dai tipi formali (es. `a != null`, `b != 0`). Va **omessa** se non ci sono vincoli sul dominio.

- **`MODIFIES` (Effetti collaterali / Side Effects)**: Elenca ogni entità preesistente il cui stato viene modificato dal metodo, inclusi parametri mutabili, l'oggetto corrente (`this`) e gli stream di sistema (**`System.in`** se legge da standard input, **`System.out`** se stampa su standard output). Va **omessa** se il metodo non modifica nulla.

- **`EFFECTS` (Postcondizioni)**: Descrive il valore restituito, le modifiche apportate agli oggetti elencati in `MODIFIES` ed eventuali eccezioni lanciate. È **sempre presente**.

- **`OVERVIEW`** _(a livello di classe)_: Descrive lo scopo astratto della classe e dichiara esplicitamente se le sue istanze sono **mutabili** o **immutabili**.

### 3.2 Proprietà delle Specifiche

> **Definizione — Restrittività (di una specifica):** Proprietà di una specifica di fornire vincoli sufficienti a escludere tutte le implementazioni scorrette o ambigue.

> **Definizione — Minimalità (di una specifica):** Proprietà di una specifica di porre il minor numero possibile di vincoli sui dettagli implementativi, lasciando allo sviluppatore libertà nella scelta dell'algoritmo.

> **Definizione — Sottodeterminazione (di una specifica):** Proprietà di una specifica che ammette più implementazioni valide che possono restituire risultati diversi a parità di input (es. restituire l'indice di _una_ occorrenza di un elemento duplicato in un array).

> **Definizione — Determinismo (di un'implementazione):** Proprietà di una specifica implementazione per cui, a parità di input e stato iniziale, produce sempre lo stesso identico risultato.

> **Definizione — Chiarezza e Ridondanza:** La specifica deve essere semanticamente non ambigua per tutti gli sviluppatori; può includere **ridondanza** (es. esempi concreti oltre alla descrizione testuale) per chiarire comportamenti complessi.

### 3.3 Proprietà delle Procedure: Totalità vs Parzialità

> **Definizione — Semplicità (di una procedura):** Proprietà di una procedura che svolge un unico compito ben definito e coerente.

> **Definizione — Generalità (di una procedura):** Proprietà di una procedura di essere applicabile a un insieme ampio di input grazie a un'opportuna parametrizzazione.

> **Definizione — Procedura Totale:** Procedura il cui comportamento è definito per **ogni** possibile valore ammesso dai tipi dei suoi parametri formali. Non presenta la clausola `REQUIRES`.

> **Definizione — Procedura Parziale:** Procedura definita solo per un sottoinsieme del dominio dei tipi d'ingresso, delimitato dalla clausola `REQUIRES`. Se il chiamante viola `REQUIRES`, il comportamento è **indefinito** (può crashare, entrare in loop infinito o produrre risultati errati).

## 4. Gestione delle Eccezioni (PDJ 4)

### 4.1 Robustezza e Limiti dei Meccanismi Alternativi

> **Definizione — Graceful Degradation:** Capacità di un programma di gestire condizioni anomale o errori in modo controllato e prevedibile, evitando crash improvvisi o la propagazione silente di dati corrotti.

> **Definizione — Errori di Propagazione:** Errore critico che si verifica quando una procedura segnala un fallimento restituendo un valore speciale del codominio (es. `-1` o `0`) o tramite il pattern _Comma-Ok_, e il chiamante dimentica di verificarlo, propagando un valore errato nei calcoli successivi.

### 4.2 Le Eccezioni in Java

> **Definizione — Eccezione:** Oggetto (sottotipo di `java.lang.Throwable`) che rappresenta una condizione anomala o eccezionale; il suo lancio (`throw`) interrompe immediatamente il flusso sequenziale della procedura, separando la gestione degli errori dal codominio normale del metodo e permettendo di **rendere totali procedure parziali** (spostando i vincoli da `REQUIRES` a `EFFECTS`).

```
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

> **Definizione — Error vs Checked Exceptions vs Unchecked Exceptions:**
>
> - **`Error` (Unchecked)**: Errori fatali e irrecuperabili della JVM/sistema (es. `OutOfMemoryError`).
> - **Checked Exceptions**: Sottotipi di `Exception` (esclusa `RuntimeException`) che rappresentano errori prevedibili e recuperabili (es. `FileNotFoundException`). Il compilatore **obbliga** a gestirle con `try-catch` o a dichiararle nella firma con `throws`.
> - **Unchecked Exceptions**: Sottotipi di `RuntimeException` che rappresentano violazioni di precondizioni o bug di programmazione (es. `IllegalArgumentException`, `NullPointerException`).
> - **Regola di Liskov vs Java per `throws` (Trappola d'esame):** Sebbene il compilatore Java non imponga di dichiarare le _unchecked exceptions_ nella firma, secondo la metodologia di Liskov (PDJ) **vanno dichiarate nella clausola `throws` e documentate in `EFFECTS` tutte le eccezioni lanciate esplicitamente**, incluse le _unchecked_.

### 4.3 Strategia di Gestione: Masking vs Reflection

> **Definizione — Masking (Mascheramento):** Strategia di gestione in cui un metodo cattura localmente (`try-catch`) un'eccezione sollevata da un sottoprogramma e la risolve autonomamente senza propagarla al proprio chiamante.

> **Definizione — Reflection (Riflessione):** Strategia di gestione in cui un metodo non cattura localmente un'eccezione (o la cattura solo per rilanciare un'eccezione più astratta e significativa), lasciandola propagare al metodo chiamante tramite la clausola `throws`.

## 5. Astrazione dei Tipi di Dato (ADT) e Implementazione (PDJ 5.1–5.4, 5.8)

### 5.1 Definizione di ADT, Rappresentazione e Adeguatezza

> **Definizione — Tipo di Dato Astratto (ADT):** Tipo di dato definito esclusivamente dall'insieme dei suoi valori astratti e dalle operazioni pubbliche che lo manipolano, nascondendo completamente la rappresentazione concreta in memoria.

> **Definizione — Rappresentazione (_Rep_):** L'insieme degli attributi privati (`private`) di una classe che memorizzano lo stato concreto di ogni istanza nello Heap.

> **Definizione — Adeguatezza (_Adequacy_):** Proprietà di un ADT di esporre un insieme di operazioni sufficiente e appropriato a consentire ai client di svolgere comodamente ed efficientemente tutti i compiti previsti dal dominio senza violare l'incapsulamento.

### 5.2 Tassonomia dei Metodi di un ADT

- **Metodi d'Istanza vs Metodi di Classe (`static`)**: I metodi d'istanza operano sullo stato di un oggetto specifico (`this`); i metodi `static` appartengono alla classe, non hanno accesso a `this` e operano esclusivamente sui parametri ricevuti.

- **Costruttori (_Creators_)**: Creano e inizializzano una nuova istanza del tipo. Includono il **Copy Constructor** (costruttore che accetta un'istanza dello stesso tipo per crearne una copia) e i **Metodi Factory statici** (metodi `static` che restituiscono una nuova istanza della classe, utili nelle gerarchie perché non vincolano al nome fisso del costruttore).

- **Osservatori (_Observers_)**: Restituiscono informazioni sullo stato dell'oggetto senza modificarlo (es. getter, predicati). Non hanno `MODIFIES`.

- **Mutatori (_Mutators_)**: Modificano lo stato interno dell'oggetto (`MODIFIES: this`). Esistono **solo** negli ADT mutabili.

- **Produttori (_Producers_)**: Partendo da istanze esistenti del tipo, creano e restituiscono una **nuova istanza** dello stesso tipo senza alterare gli operandi (tipici degli ADT immutabili).

### 5.3 Esposizione della Rappresentazione (_Representation Exposure_)

> **Definizione — Representation Exposure:** Violazione dell'incapsulamento che si verifica quando il codice esterno alla classe ottiene un riferimento diretto a un oggetto mutabile facente parte della rappresentazione interna, potendolo modificare senza passare per i metodi della classe e rompendo potenzialmente l'invariante di rappresentazione.

**Prevenzione**: Attributo sempre `private` + **Copia Difensiva (_Defensive Copying_)** sia quando si salvano parametri mutabili nel costruttore/mutatore sia quando si restituiscono campi mutabili negli osservatori o in `clone()`.

### 5.4 I Quattro Metodi Fondamentali di `java.lang.Object`

1. **`equals(Object o)`**: Verifica l'uguaglianza logica tra due oggetti. Deve formare una **relazione di equivalenza**:
   - **Riflessiva**: `x.equals(x)` è `true`.

   - **Simmetrica**: `x.equals(y)` $\iff$ `y.equals(x)`.

   - **Transitiva**: se `x.equals(y)` e `y.equals(z)`, allora `x.equals(z)`.

   - **Coerente**: restituisce sempre lo stesso valore se gli oggetti non vengono modificati.

   - **Null-safe**: `x.equals(null)` restituisce sempre `false`.

   > **Trappola d'esame — `equals` vs `similar` (Liskov vs Java) e Uguaglianza nelle Gerarchie:**
   >
   > - **Mutabili in Liskov vs Java:** Per Liskov, due oggetti mutabili sono `equals` _solo se sono lo stesso identico oggetto_ (`==`, equivalenza comportamentale nel tempo), mentre due oggetti mutabili distinti con lo stesso stato istantaneo si dicono **simili** (`similar`). In Java, molte collezioni (es. `ArrayList`) implementano `equals` sullo stato corrente: di conseguenza, mutare un oggetto dopo averlo inserito in un `HashSet` o come chiave di una `HashMap` invalida la struttura dati.
   > - **Uguaglianza Conservativa vs Rilassata:** L'implementazione _conservativa_ (`this.getClass() == o.getClass()`) richiede lo stesso identico tipo concreto; l'implementazione _rilassata_ (`o instanceof Supertipo`) ammette il confronto tra supertipo e sottotipo ma può rompere **simmetria** o **transitività** se il sottotipo aggiunge attributi rilevanti (es. `Punto2D` vs `Punto3D`).

2. **`hashCode()`**: Restituisce un intero usato dalle strutture dati hash (`HashMap`, `HashSet`).
   - **Contratto `equals`–`hashCode`**: Se `a.equals(b)` è `true`, allora **deve** valere `a.hashCode() == b.hashCode()`. Non vale il viceversa (due oggetti diversi possono avere lo stesso hashCode: **collisione**).

3. **`clone()`**: Restituisce una copia dell'oggetto (richiede `implements Cloneable`).
   - **Shallow Copy vs Deep Copy**: `super.clone()` esegue una **Shallow Copy** (copia i primitivi e i riferimenti nello Stack, condividendo gli oggetti mutabili nello Heap); una **Deep Copy** clona ricorsivamente anche tutti i campi mutabili nello Heap.

4. **`toString()`**: Restituisce una rappresentazione testuale chiara dell'oggetto astratto.

> **Definizione — `record` (Java 21):** Costrutto sintattico (`public record Punto(int x, int y) {}`) per definire ADT **immutabili** puramente portatori di dati: genera automaticamente campi `private final`, costruttore canonico, metodi osservatori (`x()`, `y()`), `equals()`, `hashCode()` e `toString()`.

## 6. Correttezza degli ADT: Funzione di Astrazione (AF) e Invariante di Rappresentazione (RI) (PDJ 5.5–5.7)

Il processo di sviluppo di un ADT segue tre fasi: **1) Specifica** (definizione astratta di dato e operazioni), **2) Scelta della Rappresentazione** (selezione degli attributi `private`), **3) Implementazione delle Procedure**.

### 6.1 Funzione di Astrazione ($AF$)

> **Definizione — Funzione di Astrazione (**$AF$**):** Funzione matematica $AF : C \to A$ che associa a ogni stato concreto **valido** $c \in C$ (configurazione degli attributi privati) l'oggetto astratto $a \in A$ che esso rappresenta.
>
> - **Dominio**: L'insieme degli stati concreti validi di $C$.
> - **Codominio**: L'insieme di tutti gli oggetti astratti $a \in A$.
> - **Proprietà e Popolamento Completo (_Fully Populates_):** È **suriettiva** (una classe _popola completamente_ un ADT se per ogni $a \in A$ esiste almeno un $c \in C$ valido tale che $AF(c) = a$) e **non-iniettiva / molti-a-uno** (più rappresentazioni concrete distinte possono corrispondere allo stesso oggetto astratto, es. `[1, 2]` e `[2, 1]` rappresentano entrambi $\{1, 2\}$).
> - **Implementazione**: Corrisponde al metodo **`toString()`**.

### 6.2 Invariante di Rappresentazione ($RI$)

> **Definizione — Invariante di Rappresentazione (**$RI$**):** Predicato booleano $RI : C \to \{\text{True}, \text{False}\}$ definito sull'intero spazio degli stati concreti $C$, che restituisce $\text{True}$ se e solo se $c$ è una rappresentazione valida e coerente di un oggetto astratto:
>
> $$
> RI(c) = \text{True} \iff \exists a \in A \mid AF(c) = a
> $$
>
> - L'insieme $\{c \in C \mid RI(c) = \text{True}\}$ coincide esattamente con il **dominio della funzione di astrazione** $AF$.
> - **Struttura**: Congiunzione logica di predicati sugli attributi.
> - **Validità**: Deve valere **al termine di ogni costruttore** e **al termine di ogni metodo pubblico** (può essere temporaneamente violata _durante_ l'esecuzione interna di un mutatore).
> - **Implementazione**: Metodo **`public boolean repOk()`**, verificato tramite **`assert repOk();`** (abilitato a runtime con il flag JVM **`-ea`**).

### 6.3 Induzione sui Tipi di Dato, Invariante di Astrazione e Effetti Benevoli

> **Definizione — Dimostrazione per Induzione sul Tipo di Dato:** Tecnica per dimostrare che $RI$ vale per ogni istanza raggiungibile di una classe:
>
> 1. **Caso Base**: Tutti i costruttori e i metodi factory producono stati $c$ tali che $RI(c) = \text{True}$.
> 2. **Passo Induttivo**: Assumendo $RI(c) = \text{True}$ prima della chiamata, ogni metodo mutatore o produttore produce uno stato $c'$ tale che $RI(c') = \text{True}$.

> **Definizione — Invariante di Astrazione (**$AI$**):** Vincolo di coerenza relativo alla **specifica** dell'ADT (indipendente dalla rappresentazione): verifica che le operazioni specificate mantengano sempre valido il concetto astratto modellato.

> **Definizione — Effetti Collaterali Benevoli (_Benevolent Side Effects_):** Modifiche alla rappresentazione concreta interna ($c_1 \to c_2$) di un ADT **astrattamente immutabile** (implementato con rappresentazione mutabile e $AF$ molti-a-uno) che preservano sia $RI$ sia il risultato della funzione di astrazione ($AF(c_1) = AF(c_2)$), ad esempio riordinare un array interno per velocizzare le ricerche o usare una cache.

## 7. Gerarchie di Tipi, Principio di Sostituzione di Liskov (LSP) e Composizione (PDJ 7)

### 7.1 La Relazione di Sottotipo ($\prec$), Proprietà e Dispatching

> **Definizione — Relazione di Sottotipo (**$T \prec S$**):** Una classe o interfaccia $T$ che estende (`extends`) o implementa (`implements`) $S$ è un **sottotipo** di $S$, indicato con il simbolo $T \prec S$ (e reciprocamente $S$ è **supertipo** di $T$).

> **Definizione — Proprietà Formali e Strutturali della Relazione $\prec$ (Fondamentali per il Quiz):**
>
> 1. **Transitivita**: Se $A \prec B$ e $B \prec C$, allora $A \prec C$ (es. `StudenteMagistrale` $\prec$ `Studente` $\prec$ `Persona` $\prec$ `Object` $\implies$ `StudenteMagistrale` $\prec$ `Object`).
> 2. **Antisimmetria (Aciclicità)**: Se $A \preceq B$ e $B \preceq A$, allora $A = B$. Non possono esistere cicli nelle gerarchie di tipi ($A \prec B \prec A$ è vietato dal compilatore).
> 3. **Riflessività in senso lato ($T \preceq T$)**: Ogni tipo è compatibile con se stesso; in senso stretto ($A \prec B$) indica un sottotipo proprio ($A \neq B$).
> 4. **Elemento Massimo (`Top` Type)**: La classe `java.lang.Object` è in cima alla gerarchia: per ogni tipo riferimento $T \neq \text{Object}$ vale $T \prec \text{Object}$.
> 5. **Topologia della Gerarchia (Albero vs DAG)**:
>    - Poiché Java ammette solo l'ereditarietà singola tra classi (`extends`), la gerarchia delle sole classi forma un **Albero** radicato in `Object`.
>    - Poiché Java ammette l'implementazione multipla di interfacce (`implements I1, I2`), la gerarchia complessiva dei tipi forma un **DAG** (_Directed Acyclic Graph_, grafo orientato aciclico).
> 6. **Proprietà di Assegnabilità (Sostituzione)**: Se $T \prec S$, allora è sempre valida l'assegnazione di un'espressione di tipo $T$ a una variabile o parametro formale di tipo $S$ (`S x = new T();`). Non vale il viceversa senza un _downcast_ esplicito.

> **Definizione — Tipo Apparente vs Tipo Concreto:** Data l'assegnazione `S x = new T();` con $T \prec S$:
>
> - Il **Tipo Apparente** ($S$) è il tipo dichiarato a compile-time e stabilisce **quali metodi sono invocabili** sul riferimento.
> - Il **Tipo Concreto** ($T$) è la classe reale dell'oggetto istanziato nello Heap a runtime e stabilisce **quale implementazione viene eseguita**.

> **Definizione — Dynamic Method Dispatch (Dispatching Dinamico):** Meccanismo per cui a **compile-time** il compilatore seleziona la segnatura più specifica basandosi sul _tipo apparente_, mentre a **runtime** la JVM invoca l'implementazione ridefinita (_override_) più specifica basandosi sul _tipo concreto_ dell'oggetto.
>
> - **Trappola d'esame — Costruttori e Override:** Se il costruttore della superclasse (`super(...)`, che deve essere la prima istruzione del costruttore della sottoclasse) invoca un metodo ridefinito dalla sottoclasse (es. `repOk()`), a runtime viene eseguito l'override della sottoclasse _prima_ che gli attributi specifici della sottoclasse siano inizializzati.

### 7.2 Il Principio di Sostituzione di Liskov (LSP)

> **Definizione — Principio di Sostituzione di Liskov (LSP):** Se $T \prec S$, allora gli oggetti di tipo $S$ in un programma possono essere sostituiti con oggetti di tipo $T$ senza alterare alcuna delle proprietà desiderabili di correttezza del programma stesso.

Affinché $T \prec S$ rispetti LSP devono valere **tre regole**:

> **Definizione — 1. Regola delle Segnature (LSP):** _(Verificata dal compilatore)_ Per ogni metodo $m$ di $S$, deve esistere in $T$ un metodo $m'$ con segnatura compatibile:
>
> - **Nome e Parametri**: Stesso nome e **stessi tipi dei parametri formali** ($P_{m'} = P_m$, **invarianza** in Java; la teoria di Liskov ammetterebbe parametri controvarianti $P_m \prec P_{m'}$, ma Java non la supporta).
> - **Tipo di ritorno covariante**: Se $m$ restituisce $R_m$ e $m'$ restituisce $R_{m'}$, allora deve valere $R_{m'} \prec R_m$ oppure $R_{m'} = R_m$.
> - **Eccezioni**: $m'$ non può lanciare nuove _checked exceptions_ non previste da $m$; per ogni eccezione $E_{m'}$ lanciata da $m'$, deve esistere un'eccezione $E_m$ in $m$ tale che $E_{m'} \prec E_m$ oppure $E_{m'} = E_m$.

> **Definizione — 2. Regola dei Metodi (LSP):** _(Comportamentale, NON verificata dal compilatore)_ Il metodo ridefinito $m'$ in $T$ deve preservare il comportamento di $m$ in $S$:
>
> - **Precondizioni indebolite** (o uguali): $\text{pre}_m \implies \text{pre}_{m'}$ (può accettare un dominio di input più ampio, mai più restrittivo).
> - **Postcondizioni rinforzate** (o uguali): $(\text{pre}_m \land \text{post}_{m'}) \implies \text{post}_m$ (sugli input validi per $m$, deve garantire almeno tutto ciò che prometteva $m$).

> **Definizione — 3. Regola delle Proprietà (LSP):** _(NON verificata dal compilatore)_ Il sottotipo $T \prec S$ deve conservare tutte le proprietà dimostrabili per $S$:
>
> - **Invarianti**: Tutte le invarianti di $S$ devono valere anche per $T$ (nella `repOk()` di $T$ occorre richiamare **`super.repOk()`**).
> - **Proprietà evolutive**: $T$ deve rispettare tutti i vincoli sulle transizioni di stato ammesse da $S$.

### 7.3 Classi Concrete, Classi Astratte, Interfacce e Tipi di Supertipi

> **Definizione — Classe Concreta vs Classe Astratta vs Interfaccia:**
>
> - **Classe Concreta**: Classe completa istanziabile con `new`, dotata di stato e di tutti i metodi implementati.
> - **Classe Astratta (`abstract class`)**: Classe non istanziabile direttamente che modella un'implementazione parziale; può avere stato, costruttori (invocati con `super(...)`) e metodi sia concreti sia `abstract`. Supporta ereditarietà singola.
> - **Interfaccia (`interface`)**: Contratto comportamentale puro privo di stato d'istanza e costruttori; dichiara segnature di metodi (e opzionalmente metodi `default` e `static`). Supporta **ereditarietà multipla** (`implements I1, I2`).

> **Definizione — I Tre Tipi di Supertipi (PDJ 7.10):**
>
> 1. **Supertipi Completi:** Definiscono un'intera astrazione di dati con specifiche complete per tutti i metodi.
> 2. **Supertipi Incompleti:** Stabiliscono solo convenzioni sui nomi dei metodi, con specifiche troppo deboli per scrivere codice generico in termini del supertipo.
> 3. **Snippet (sempre interfacce):** Definiscono uno o pochissimi metodi con specifica precisa e completa (es. `Comparable`, `Cloneable`, `Iterable`), consentendo di scrivere codice polimorfico riutilizzabile.

> **Definizione — Implementazione per Comportamento:** Tecnica con cui i metodi `default` di un'interfaccia (o i metodi di una sottoclasse) vengono implementati invocando esclusivamente altri metodi pubblici/astratti del contratto stesso, senza accedere direttamente alla rappresentazione.

> **Definizione — Modificatori di Visibilità:**
>
> - `private`: accessibile solo all'interno della classe stessa.
> - _package-private_ (nessuna keyword): accessibile a tutte le classi dello stesso package.
> - `protected`: accessibile alle classi dello stesso package **e** a tutte le sottoclassi (anche in altri package).
> - `public`: accessibile ovunque.

### 7.4 Astrazioni Ontologiche Errate, Composizione e Delega

> **Definizione — Astrazione Ontologica Errata:** Discrepanza tra una relazione concettuale _IS-A_ del mondo reale e la sottotipazione comportamentale in OOP. Esempio canonico: un `Quadrato` _è_ ontologicamente un `Rettangolo`, ma in un modello mutabile con `setBase` e `setAltezza`, `Quadrato extends Rettangolo` viola **LSP** (o rompe l'invariante del quadrato o viola la postcondizione di `setAltezza` del rettangolo).

> **Definizione — Composizione e Delega (_HAS-A_):** Tecnica progettuale alternativa all'ereditarietà in cui una classe contiene un riferimento privato a un'istanza di un'altra classe come attributo (**composizione**) e ne riutilizza le funzionalità inoltrando le chiamate ad essa (**delega**), evitando di esporre la rappresentazione della superclasse o violare LSP.

## 8. Astrazione di Iterazione e Classi Innestate / Nested Classes (PDJ 6)

### 8.1 `Iterator<E>` vs `Iterable<E>`

> **Definizione — Astrazione di Iterazione:** Meccanismo che permette di accedere sequenzialmente e uniformemente a tutti gli elementi di una struttura dati o sorgente senza esporne la rappresentazione interna.

> **Definizione — `Iterator<E>` (Generatore in PDJ) e Priming:** Interfaccia implementata dall'oggetto che mantiene lo stato corrente della scansione e fornisce:
>
> - `boolean hasNext()` (osservatore): restituisce `true` se esistono altri elementi da visitare.
> - `E next()` (mutatore): restituisce l'elemento successivo e avanza lo stato; lancia `NoSuchElementException` se `!hasNext()`.
> - `default void remove()` (mutatore opzionale): rimuove l'ultimo elemento restituito da `next()` (spesso lancia `UnsupportedOperationException`).
> - **Priming di un generatore:** Tecnica che consiste nel consumare uno o più elementi dall'`Iterator` _prima_ del ciclo `while (it.hasNext())` (ad esempio per inizializzare un accumulatore o trovare il massimo).

> **Definizione — `Iterable<E>`:** Interfaccia implementata da una classe contenitore che espone il metodo `Iterator<E> iterator()` (chiamato _Iteratore_ in PDJ) per produrre nuove istanze indipendenti di `Iterator<E>` e abilitare il ciclo **`for-each`**.
>
> - **Trappola d'esame — Perché non restituire direttamente `lista.iterator()`:** Perché l'iteratore standard delle `Collection` Java implementa `remove()`, permettendo al codice client di rimuovere elementi dalla collezione interna e violando l'incapsulamento (_Representation Exposure_).

- **Iteratore Standalone**: La classe implementa direttamente `Iterator<E>` quando l'iterazione è la sua funzionalità principale o intrinsecamente sequenziale (es. `Range`, generatore di numeri primi, `Scanner`).

- **Contenitore `Iterable`**: La classe implementa `Iterable<E>` quando contiene dati su cui devono poter iterare più client in modo indipendente.

### 8.2 Le Quattro Tipologie di Classi Innestate (_Nested Classes_)

> **Definizione — Nested Classes (Classi Innestate):** Classi definite all'interno di un'altra classe contenitore; poiché fanno parte del codice della classe contenitore, hanno accesso diretto a tutti i suoi attributi `private` senza violare l'incapsulamento:
>
> 1. **Static Nested Class**: Dichiarata con `static`; non possiede alcun riferimento implicito all'istanza esterna (per accedervi deve riceverne un riferimento esplicito nel costruttore).
> 2. **Inner Class (non statica)**: Dichiarata senza `static`; ogni sua istanza è associata a una specifica istanza della classe esterna, accessibile tramite `ClasseEsterna.this`.
> 3. **Local Inner Class**: Classe dichiarata localmente all'interno di un blocco/metodo; visibile solo in quel blocco e restituita all'esterno tramite un supertipo/interfaccia (es. `Iterator<E>`).
> 4. **Anonymous Inner Class (Classe Anonima)**: Classe locale priva di nome dichiarata e istanziata contestualmente in un'unica espressione (`new Interfaccia() { ... }`). Può accedere alle variabili locali del metodo solo se `final` o _effectively final_.

## 9. Polimorfismo e Generics in Java (PDJ 8 + Java Generics)

### 9.1 Tassonomia del Polimorfismo

> **Definizione — Polimorfismo:** Capacità di associare a un unico simbolo (nome di metodo, variabile o tipo) dati di tipo diverso oppure comportamenti diversi in base al contesto.

1. **Polimorfismo Ad-hoc**: Procedure con comportamenti diversi in base ai parametri (**Overloading**) o al tipo su cui sono ridefinite (**Overriding**).

2. **Polimorfismo di Sottotipo (_Subtyping_ / Inclusion)**: Capacità di assegnare istanze di sottotipi diversi a un atomo avente come tipo apparente un supertipo comune.

3. **Polimorfismo Parametrico**: Capacità di una procedura o di un tipo di operare in modo uniforme su tipi diversi specificati come parametri di tipo.

### 9.2 Generics, Relazioni di Sottotipo ($\prec$), Wildcards e Type Erasure

> **Definizione — Generics e Operatore Diamond (`<>`):** Meccanismo di polimorfismo parametrico che parametrizza classi e metodi su uno o più tipi (`<T>`, `<E>`, `<K, V>`), garantendo la **type-safety** a compile-time ed eliminando i cast espliciti e i rischi di `ClassCastException` tipici del polimorfismo per subtyping su `Object`. L'operatore **Diamond (`<>`)** abilita l'inferenza automatica del tipo in istanziazione.

> **Definizione — Proprietà della Relazione di Sottotipo ($\prec$) nei Generics, Wildcards e Array (Domanda Frequente al Quiz):**
>
> Sia $S \prec T$ (ad esempio `Integer` $\prec$ `Number`, oppure `Dog` $\prec$ `Animal`) e siano $G_1 \prec G_2$ due tipi generici (ad esempio `ArrayList` $\prec$ `List`):
>
> 1. **Covarianza del Tipo Base (_Outer Type_)**: A parità di parametro di tipo $V$, la gerarchia esterna si conserva:
>    $$
>    G_1 \prec G_2 \implies G_1\langle V \rangle \prec G_2\langle V \rangle
>    $$
>    _(Esempio: `ArrayList<Integer>` $\prec$ `List<Integer>` $\prec$ `Collection<Integer>`)._
> 2. **Invarianza del Parametro di Tipo (_Type Argument_)**: A parità di tipo contenitore $V$, la gerarchia interna **NON** si propaga:
>    $$
>    S \prec T \implies V\langle S \rangle \not\prec V\langle T \rangle \quad \text{e} \quad V\langle T \rangle \not\prec V\langle S \rangle
>    $$
>    _(Esempio: `List<Integer>` **non** è sottotipo di `List<Number>` — errore di compilazione! Ciò impedisce di inserire un `Double` in una `List<Integer>` tramite un riferimento `List<Number>`)._
> 3. **Covarianza con Upper-Bounded Wildcard (`? extends`)**: Permette di **leggere** elementi come $T$ (vieta la scrittura, eccetto `null`):
>    $$
>    S \prec T \implies V\langle S \rangle \prec V\langle ? \text{ extends } T \rangle \quad \text{e} \quad V\langle ? \text{ extends } S \rangle \prec V\langle ? \text{ extends } T \rangle
>    $$
>    _(Esempio: `List<Integer>` $\prec$ `List<? extends Number>`)._
> 4. **Controvarianza con Lower-Bounded Wildcard (`? super`)**: Permette di **scrivere** elementi di tipo $S$ (lettura solo come `Object`):
>    $$
>    S \prec T \implies V\langle T \rangle \prec V\langle ? \text{ super } S \rangle \quad \text{e} \quad V\langle ? \text{ super } T \rangle \prec V\langle ? \text{ super } S \rangle
>    $$
>    _(Esempio: `List<Number>` $\prec$ `List<? super Integer>`)._
> 5. **Unbounded Wildcard (`<?>`)**: È il supertipo di tutte le istanziazioni parametriche di $V$:
>    $$
>    \forall T: \quad V\langle T \rangle \prec V\langle ? \rangle, \quad V\langle ? \text{ extends } T \rangle \prec V\langle ? \rangle, \quad V\langle ? \text{ super } T \rangle \prec V\langle ? \rangle
>    $$
> 6. **Raw Types (Retrocompatibilità)**: Ogni tipo parametrizzato è sottotipo del corrispondente _raw type_ non parametrizzato:
>    $$
>    \forall T: \quad V\langle T \rangle \prec V
>    $$
>    _(Esempio: `ArrayList<String>` $\prec$ `ArrayList`)._
> 7. **Confronto con gli Array (Covarianza a Runtime)**: A differenza dei Generics, gli **array in Java sono covarianti**:
>    $$
>    S \prec T \implies S[\,] \prec T[\,]
>    $$
>    _(Esempio: `Integer[]` $\prec$ `Number[]` compila, ma inserire un `Double` lancia `ArrayStoreException` a runtime)._
> 8. **Regola PECS (_Producer Extends, Consumer Super_)**: Usa `<? extends T>` quando la struttura _produce_ valori da leggere; usa `<? super T>` quando _consuma_ valori da inserire.

> **Definizione — Type Erasure e Bridge Methods:** Processo eseguito dal compilatore Java per garantire la retrocompatibilità del bytecode: rimuove i parametri di tipo generici sostituendoli con il loro upper bound (o `Object`), inserisce cast automatici in lettura e genera **bridge methods** sintetici per preservare l'overriding a runtime.

### 9.3 Ordinamento e Polimorfismo: `Comparable<T>` vs `Comparator<T>`

> **Definizione — Element Subtype Approach (`Comparable<T>`) vs Related Subtype Approach (`Comparator<T>`):**
>
> - **`Comparable<T>`** (_Element Subtype Approach_, `java.lang`): La classe stessa implementa l'interfaccia definendo il proprio **ordinamento naturale** tramite `int compareTo(T o)` ($< 0$ se `this < o`, $0$ se equivalenti, $> 0$ se `this > o`). Richiede _pre-planning_ nella definizione della classe.
> - **`Comparator<T>`** (_Related Subtype Approach_, `java.util`): Oggetto ausiliario esterno che definisce un criterio di ordinamento alternativo tramite `int compare(T o1, T o2)`, utilizzabile anche su classi preesistenti non modificabili.

## 10. Programmazione Funzionale in Java

### 10.1 Fondamenti del Paradigma Funzionale

> **Definizione — Programmazione Dichiarativa vs Imperativa:** La programmazione **imperativa** descrive _come_ eseguire il calcolo tramite sequenze di comandi che mutano lo stato; la programmazione **dichiarativa** (di cui quella **funzionale** è un'estensione) descrive _cosa_ calcolare mediante espressioni e composizione di funzioni matematiche.

> **Definizione — Trasparenza Referenziale:** Proprietà di un'espressione di poter essere sostituita dal suo valore risultante in qualunque punto del programma senza alterarne il comportamento.

> **Definizione — Funzione Pura:** Funzione deterministica priva di effetti collaterali (`MODIFIES` vuoto) il cui risultato dipende esclusivamente dai parametri ricevuti in ingresso.

> **Definizione — Cittadini di Prima Classe e Funzioni di Ordine Superiore:** In un linguaggio funzionale puro, le funzioni sono **cittadini di prima classe** (possono essere passate come parametri, restituite e assegnate a variabili) e abilitano le **funzioni di ordine superiore** (funzioni che accettano o restituiscono altre funzioni).

### 10.2 Interfacce Funzionali, Lambda, Composizione, Currying e Stream

> **Definizione — Interfaccia Funzionale (`@FunctionalInterface`):** Interfaccia Java che dichiara **un unico metodo astratto** (_Single Abstract Method_ — SAM, più eventuali metodi `default` o `static`), fungendo da tipo bersaglio per le lambda expressions (es. `Function<T,R>`, `Predicate<T>`, `Consumer<T>`, `Supplier<T>`, `Comparator<T>`).

> **Definizione — Lambda Expression:** Costrutto sintattico compatto (`(parametri) -> espressione` oppure `(parametri) -> { blocco; }`) per istanziare al volo un'interfaccia funzionale senza scrivere una classe anonima.

> **Definizione — Composizione di Funzioni (`compose` vs `andThen`):** Date due funzioni $f_1$ e $f_2$:
>
> - `f1.compose(f2)` applica prima $f_2$ e poi $f_1$: $(f_1 \circ f_2)(x) = f_1(f_2(x))$.
> - `f1.andThen(f2)` applica prima $f_1$ e poi $f_2$: $(f_2 \circ f_1)(x) = f_2(f_1(x))$.

> **Definizione — Currying:** Tecnica funzionale che trasforma una funzione a più argomenti in una sequenza concatenata di funzioni a un solo argomento ciascuna, dove ogni funzione restituisce la funzione successiva fino al risultato finale.

> **Definizione — Java `Stream<T>`:** Astrazione di sequenza di elementi su cui eseguire pipeline dichiarative (non riutilizzabili dopo l'operazione terminale) formate da una **sorgente**, **operazioni intermedie _lazy_** (`filter`, `map`, `distinct`, `sorted`) e un'**operazione terminale _eager_** (`count`, `forEach`, `anyMatch`, `allMatch`).

## 11. Ingegneria del Software e Design Pattern (PDJ 15)

### 11.1 Pattern Architetturali vs Design Pattern

> **Definizione — Pattern Architetturali:** Metodologie per organizzare la macro-struttura di un sistema software separando i moduli per responsabilità e minimizzando l'accoppiamento, in particolare separando la **Business Logic** (logica di dominio e accesso ai dati) dalla **Presentation Logic** (interfaccia utente). Esempi: **MVC** (_Model-View-Controller_), **MVP** (_Model-View-Presenter_), **MVVM** (_Model-View-ViewModel_).

> **Definizione — Design Pattern (GoF):** Soluzioni progettuali standardizzate e riutilizzabili per risolvere problemi ricorrenti di interazione e strutturazione tra classi e oggetti (non sono algoritmi né librerie di codice pronto). Si classificano in tre categorie: **Creational**, **Structural** e **Behavioral**.

### 11.2 I Pattern Trattati nel Corso

> **Definizione — Singleton (_Creational Pattern_):** Garantisce che di una classe esista **una sola istanza** nell'intero programma e fornisce un punto di accesso globale controllato ad essa, rendendo il costruttore `private` ed esponendo un metodo `public static getInstance()` che inizializza e restituisce un attributo `private static`.

> **Definizione — Decorator (_Structural Pattern_):** Consente di estendere o modificare dinamicamente i comportamenti di un singolo oggetto **già istanziato** a runtime, avvolgendolo per **composizione** all'interno di classi decoratrici che implementano la stessa **interfaccia comune** e inoltrano le chiamate base per **delega**.

> **Definizione — Iterator (_Behavioral Pattern_):** Fornisce un modo standardizzato per accedere sequenzialmente agli elementi di una collezione senza esporne la rappresentazione sottostante.

> **Definizione — Observer (_Behavioral Pattern_):** Definisce una dipendenza **uno-a-molti** in cui un oggetto emettitore (_Creator / Subject_) mantiene una lista di ascoltatori registrati tramite un'interfaccia comune (`Listener`) e li notifica automaticamente invocando un metodo di callback (es. `update()`) ogni volta che si verifica un evento o un cambio di stato.

## 12. Compendio Completo di Tutte le Definizioni, Formulario $\prec$ e Trappole d'Esame (L01–L16)

### 12.1 Formulario delle Proprietà della Relazione di Sottotipo ($\prec$)

Questa scheda riassume tutte le proprietà algebriche e di varianza associate al simbolo $\prec$ richieste nei quiz teorici:

| Contesto                      | Proprietà / Formula con $\prec$                                                     | Verità / Spiegazione                                                                           |
| ----------------------------- | ----------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------- |
| **Proprietà Algebriche Base** | **Transitivita**: $A \prec B \land B \prec C \implies A \prec C$                    | **VERA** (proprietà fondamentale delle gerarchie).                                             |
| **Proprietà Algebriche Base** | **Antisimmetria**: $A \preceq B \land B \preceq A \implies A = B$                   | **VERA** (nessun ciclo ammesso nelle gerarchie).                                               |
| **Proprietà Algebriche Base** | **Simmetria**: $A \prec B \implies B \prec A$                                       | **FALSA** (la relazione è asimmetrica/antisimmetrica).                                         |
| **Gerarchia Java**            | **Radice Universale**: $\forall T \text{ ref}: T \preceq \text{Object}$             | **VERA** (`Object` è supertipo di ogni tipo riferimento).                                      |
| **Gerarchia Java**            | **Topologia**: Classi vs Tipi completi                                              | Solo classi (`extends`): **Albero**. Classi + Interfacce: **DAG**.                             |
| **LSP (Segnature in Java)**   | $T \prec S \implies R_T \preceq R_S$, $E_T \preceq E_S$, $P_T = P_S$                | Ritorno **covariante**, eccezioni $\preceq$, parametri **invarianti** in Java.                 |
| **Generics (Outer Type)**     | $G_1 \prec G_2 \implies G_1\langle V \rangle \prec G_2\langle V \rangle$            | **VERA** (es. `ArrayList<String>` $\prec$ `List<String>`).                                     |
| **Generics (Inner Type)**     | $S \prec T \implies V\langle S \rangle \prec V\langle T \rangle$                    | **FALSA** ($V\langle S \rangle \not\prec V\langle T \rangle$: i Generics sono **invarianti**). |
| **Wildcard `extends`**        | $S \prec T \implies V\langle S \rangle \prec V\langle ? \text{ extends } T \rangle$ | **VERA** (covarianza; solo lettura come $T$).                                                  |
| **Wildcard `super`**          | $S \prec T \implies V\langle T \rangle \prec V\langle ? \text{ super } S \rangle$   | **VERA** (controvarianza; scrittura di $S$).                                                   |
| **Unbounded & Raw**           | $V\langle T \rangle \prec V\langle ? \rangle$ e $V\langle T \rangle \prec V$        | **VERA** (`<?>` e raw type sono supertipi di ogni $V\langle T \rangle$).                       |
| **Array Java**                | $S \prec T \implies S[\,] \prec T[\,]$                                              | **VERA** (gli array sono **covarianti**, rischio `ArrayStoreException`).                       |

### 12.2 Tabella Riepilogativa di Tutte le Definizioni (L01–L16)

| Lezione    | Termine / Concetto                              | Definizione Essenziale per il Quiz                                                                                                                                                                                                                                                  |
| ---------- | ----------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **L01**    | **Imperativa vs Procedurale**                   | **Imperativa**: sequenza lineare di comandi che mutano la memoria. **Procedurale**: codice scomposto in procedure/funzioni riutilizzabili e parametrizzate.                                                                                                                         |
| **L01**    | **Decomposizione**                              | Suddivisione di un problema in moduli **logicamente coesi**, **indipendenti**, **allo stesso livello di dettaglio** e con **basso accoppiamento**.                                                                                                                                  |
| **L01**    | **Astrazione**                                  | Mapping **molti-a-uno** che trascura il _"come"_ per concentrarsi sul _"cosa"_. Garantisce **Località** e **Modificabilità**.                                                                                                                                                       |
| **L01**    | **Parametrizzazione vs Specifica**              | **Parametrizzazione**: astrae sui **dati** (parametri formali). **Specifica**: astrae sui **vincoli dell'operazione** (contratto).                                                                                                                                                  |
| **L02**    | **Classe vs Oggetto**                           | **Classe**: stampino (_blueprint_). **Oggetto**: singola istanza concreta nello Heap.                                                                                                                                                                                               |
| **L02**    | **Encapsulation**                               | Nascondere lo stato (`private`) consentendo accesso/modifica solo tramite i metodi della classe.                                                                                                                                                                                    |
| **L02**    | **Bytecode, JVM, Type Safety**                  | `javac` $\to$ `.class` (**bytecode**) eseguito dalla **JVM**. **Type safety**: tipi verificati staticamente a compile-time.                                                                                                                                                         |
| **L02**    | **Stack vs Heap & Aliasing**                    | **Primitivi**: valore nello Stack. **Riferimenti**: puntatore nello Stack, dati nello Heap. **Aliasing**: più riferimenti puntano allo stesso oggetto nello Heap.                                                                                                                   |
| **L02**    | **Passaggio Parametri**                         | Sempre **per valore**: copia il valore (primitivi) o il puntatore (riferimenti).                                                                                                                                                                                                    |
| **L02**    | **Overloading & Most Specific Call**            | Stesso nome, parametri diversi. Sceglie il metodo con **meno conversioni implicite**; a parità dà errore di **ambiguità**.                                                                                                                                                          |
| **L02**    | **Boxing / Unboxing**                           | **Autoboxing**: primitivo $\to$ Wrapper. **Unboxing**: Wrapper $\to$ primitivo.                                                                                                                                                                                                     |
| **L03**    | **Clausole di Liskov**                          | `REQUIRES` (omessa se totale), `MODIFIES` (include `this`, `System.in`, `System.out`; omessa se puro), `EFFECTS` (sempre presente), `OVERVIEW` (scopo e mutabilità).                                                                                                                |
| **L03**    | **Restrittività vs Minimalità**                 | **Restrittività**: esclude implementazioni errate. **Minimalità**: pone meno vincoli possibili sull'implementazione.                                                                                                                                                                |
| **L03**    | **Sottodeterminazione vs Determinismo**         | **Sottodeterminazione** (_specifica_): ammette più risultati validi. **Determinismo** (_implementazione_): stesso input $\implies$ sempre stesso output.                                                                                                                            |
| **L03**    | **Procedura Totale vs Parziale**                | **Totale**: definita su tutto il dominio dei tipi (no `REQUIRES`). **Parziale**: definita solo dove vale `REQUIRES` (altrove comportamento indefinito).                                                                                                                             |
| **L04**    | **Graceful Degradation & Propagazione**         | Gestione controllata degli errori senza crash né **errori di propagazione** (rischio di `-1` o _Comma-Ok_ ignorati).                                                                                                                                                                |
| **L04**    | **Checked vs Unchecked Exceptions**             | **Checked**: obbligo di `try-catch` o `throws`. **Unchecked**: `Error` e `RuntimeException` (in Liskov vanno comunque messe in `throws` ed `EFFECTS`).                                                                                                                              |
| **L04**    | **Masking vs Reflection**                       | **Masking**: cattura e risolve localmente (`try-catch`). **Reflection**: propaga al chiamante (`throws`).                                                                                                                                                                           |
| **L05–06** | **ADT, Rep, Adeguatezza**                       | **ADT**: definito solo dalle operazioni. **Rep**: attributi `private`. **Adeguatezza**: operazioni sufficienti ed efficienti.                                                                                                                                                       |
| **L05–06** | **4 Categorie Metodi ADT**                      | **Creators** (costruttori, copy constructor, factory), **Observers**, **Mutators** (solo mutabili), **Producers** (nuova istanza senza mutare operandi).                                                                                                                            |
| **L05–06** | **Representation Exposure**                     | Riferimento a stato mutabile esposto all'esterno; si previene con `private` e **copie difensive**.                                                                                                                                                                                  |
| **L05–06** | **`equals` / `similar` / `hashCode` / `clone`** | `equals` (riflessivo, simmetrico, transitivo, coerente); Liskov distingue `equals` (`==` sui mutabili) da `similar` (stato istantaneo); conservativo (`getClass()`) vs rilassato (`instanceof`); `a.equals(b)` $\implies$ `a.hashCode() == b.hashCode()`; _Shallow_ vs _Deep Copy_. |
| **L07–08** | **Funzione di Astrazione (**$AF$**)**           | $AF : C \to A$, suriettiva (_Fully Populates_) e molti-a-uno. Implementata da **`toString()`**.                                                                                                                                                                                     |
| **L07–08** | **Invariante di Rappresentazione (**$RI$**)**   | Predicato $RI : C \to \{\text{True}, \text{False}\}$ che definisce il dominio di $AF$. Implementata da **`repOk()`** + **`assert repOk();`** (`java -ea`).                                                                                                                          |
| **L07–08** | **Induzione ADT, AI, Benevolent Side Effects**  | **Induzione**: base costruttori, passo mutatori/produttori. **AI**: invariante di specifica. **Benevolent Side Effects**: mutazioni interne invisibili ad $AF$ in tipi astrattamente immutabili.                                                                                    |
| **L09–10** | **Tipo Apparente vs Concreto & Dispatch**       | **Apparente** (compile-time): metodi invocabili. **Concreto** (runtime): override eseguito (_Dynamic Dispatch_). Attenzione a metodi overridden chiamati in `super(...)`.                                                                                                           |
| **L09–10** | **Principio di Sostituzione di Liskov (LSP)**   | Regola delle **Segnature** (parametri invarianti, ritorno covariante, eccezioni $\le$), **Metodi** (pre indebolite, post rinforzate), **Proprietà** (`super.repOk()` e proprietà evolutive).                                                                                        |
| **L09–10** | **3 Tipi di Supertipi & Classi/Interfacce**     | Supertipi **Completi**, **Incompleti**, **Snippet** (interfacce minimali). `abstract class` (stato, ereditarietà singola) vs `interface` (no stato, ereditarietà multipla).                                                                                                         |
| **L09–10** | **Composizione e Delega**                       | Preferibile a `extends` se la relazione _IS-A_ ontologica viola LSP (`Quadrato` vs `Rettangolo`).                                                                                                                                                                                   |
| **L11–12** | **`Iterator<E>`, `Iterable<E>`, Priming**       | `Iterator` (_Generatore_): stato scansione (`hasNext`, `next`, `remove`). `Iterable`: produce iteratori (`iterator()`). **Priming**: leggere prima del `while`. Non esporre `Collection.iterator()` perché ha `remove()`.                                                           |
| **L11–12** | **4 Nested Classes**                            | **Static Nested** (no `Outer.this`), **Inner** (legata a `Outer.this`), **Local Inner** (nel metodo), **Anonymous Class** (senza nome; variabili locali `final`/effectively final).                                                                                                 |
| **L13–14** | **3 Tipi di Polimorfismo & Subtype Approaches** | **Ad-hoc**, **Subtyping**, **Parametrico**. **Element Subtype** (`Comparable<T>`) vs **Related Subtype** (`Comparator<T>`).                                                                                                                                                         |
| **L13–14** | **Generics, Varianza, PECS, Type Erasure**      | Generics **invarianti** (`List<Integer>` $\not\prec$ `List<Number>`) vs Array **covarianti** (`ArrayStoreException`). **PECS**: `<? extends T>` legge, `<? super T>` scrive. **Type Erasure** + **Bridge Methods**.                                                                 |
| **L15**    | **Programmazione Funzionale**                   | **Trasparenza referenziale**, **Funzione pura** (no `MODIFIES`), SAM, Lambda, **`compose`** vs **`andThen`**, **Currying**, **`Stream<T>`** (lazy intermediate + eager terminal).                                                                                                   |
| **L16**    | **Design Pattern**                              | **Architetturali** (MVC, MVP, MVVM) vs **GoF**: **Singleton** (_Creational_), **Decorator** (_Structural_), **Iterator** e **Observer** (_Behavioral_).                                                                                                                             |

## 13. Appendice: Checklist Operativa per l'Esame (Teoria e Laboratorio)

1. **Specifiche in ogni classe e metodo**:
   - Scrivi sempre `// OVERVIEW:` su ogni classe dichiarando se è **mutabile** o **immutabile**.

   - Su ogni metodo (tranne semplici getter e metodi standard di `Object` come `equals`, `hashCode`, `toString`, `clone`) scrivi `REQUIRES` (solo se parziale), `MODIFIES` (solo se modifica qualcosa, inclusi `this`, `System.in`, `System.out`) ed `EFFECTS` (sempre).

2. **Totalità tramite Eccezioni**:
   - Valida sempre i parametri nei costruttori e nei metodi pubblici. Se lanci un'eccezione per un input non valido, documentala in `EFFECTS`, dichiarala in `throws` secondo lo stile Liskov e **non** metterla in `REQUIRES`.

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
   - Preferisci `Iterable<E>` + classe interna/anonima per `Iterator<E>` evitando di esporre `remove()` di una collezione interna per non rompere l'invariante della classe.

   - Ricorda che i Generics sono **invarianti** (`List<Integer>` $\not\prec$ `List<Number>`): usa `<? extends T>` per leggere e `<? super T>` per scrivere.
