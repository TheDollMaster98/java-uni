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

L'obiettivo dell'ingegneria del software e della programmazione su larga scala è costruire sistemi software dotati di **modularità** (suddivisione in componenti indipendenti), **riusabilità** (capacità di riutilizzare codice già scritto), **correttezza** e **manutenibilità** (facilità nel correggere errori o aggiungere funzionalità).

### 1.1 Dalla Programmazione Imperativa a quella Procedurale

**`Programmazione Imperativa`**: Paradigma (modello di programmazione) in cui il programma è costituito da una sequenza lineare di comandi/istruzioni eseguite in un ordine preciso che modificano direttamente lo stato della memoria (i valori salvati nelle variabili).

I limiti della programmazione imperativa pura al crescere delle dimensioni del software sono: **scarsa comprensibilità** (un unico listato troppo lungo), **difficoltà di riuso** (duplicazione del codice) e **scarsa modificabilità** (modificare un requisito richiede interventi sparsi ovunque).

**`Programmazione Procedurale`**: Evoluzione del paradigma imperativo (basato su comandi sequenziali) in cui le istruzioni vengono raggruppate in **procedure** (funzioni o sottoprogrammi), ovvero unità logiche isolate, riutilizzabili e parametrizzabili (adattabili a input diversi tramite parametri).

**Limiti aperti della programmazione procedurale** (che motivano il passaggio alla `OOP`, Programmazione Orientata agli Oggetti):

- Difficoltà nel mantenere coerente lo **stato condiviso/globale** (variabili accessibili da tutte le funzioni).

- Difficoltà nel coordinare molti sviluppatori su migliaia di procedure indipendenti.

- Assenza di **incapsulamento** (protezione e isolamento) tra dati e operazioni che li manipolano.

### 1.2 Decomposizione

**`Decomposizione`**: Processo progettuale che consiste nel suddividere un problema complesso in sottoproblemi più semplici (**moduli**, ovvero blocchi autonomi di codice), la cui combinazione risolve il problema originale.

Una buona decomposizione produce **moduli** (blocchi autonomi di codice) caratterizzati da:

1. **Alta coesione logica**: Ogni modulo svolge un singolo compito chiaro e ben definito.

2. **Indipendenza**: Ogni sottoproblema è isolabile, implementabile e testabile separatamente dagli altri.

3. **Stesso livello di dettaglio**: I sottoproblemi devono trovarsi a un livello di astrazione omogeneo (non mescolare operazioni ad alto livello con dettagli di basso livello).

4. **Basso accoppiamento** (`Low Coupling`, dipendenze minime tra i moduli).

### 1.3 Astrazione: Meccanismi, Benefici e Tipologie

**`Astrazione`**: Processo mentale e progettuale che costituisce un **mapping molti-a-uno** (più problemi concreti vengono ricondotti a un'unica soluzione generale), permettendo di trascurare i dettagli implementativi irrilevanti (_"come"_ è scritto il codice) per concentrarsi sugli aspetti essenziali (_"cosa"_ fa e su quali dati).

**Benefici fondamentali dell'astrazione**:

**`Località`**: Proprietà per cui un'astrazione può essere implementata, compresa e verificata in modo disgiunto (separato) dal resto del programma; chi la utilizza deve conoscerne esclusivamente la **specifica** (il contratto esterno), senza doverne leggere il codice interno.

**`Modificabilità`**: Proprietà per cui l'**implementazione** interna (il codice concreto) di un'astrazione può essere sostituita o ottimizzata senza alterare il comportamento dei programmi che la utilizzano, purché la **specifica** (il comportamento atteso) resti invariata.

#### I Due Meccanismi di Astrazione

**`Astrazione per Parametrizzazione`**: Meccanismo di astrazione (sui **dati**) che generalizza un modulo sostituendo valori concreti specifici con **parametri formali** (variabili segnaposto nell'intestazione del metodo), rendendo il codice applicabile a un insieme più ampio di input.

**`Astrazione per Specifica`**: Meccanismo di astrazione (sui **vincoli delle operazioni**) che separa il comportamento atteso (_"cosa fa"_) dall'**implementazione** concreta (_"come lo fa"_), descrivendo input, precondizioni ed effetti mediante intestazioni e commenti.

#### I Quattro Tipi di Astrazione del Corso

1. **`Astrazione Procedurale`**: Definizione di sottoprogrammi (metodi) tramite parametrizzazione e specifica.

2. **Astrazione dei Tipi di Dato** (`ADT`, Tipi di Dato Astratti): Definizione di nuovi tipi di dato che incapsulano stato e comportamenti.

3. **`Astrazione di Gerarchia dei Tipi`**: Estensione e specializzazione di tipi esistenti tramite **sottotipazione** (creazione di sottotipi).

4. **`Astrazione di Iterazione`**: Accesso sequenziale e uniforme agli elementi di una collezione senza esporne la **rappresentazione** interna (gli attributi privati).

## 2. Il Paradigma Object-Oriented e Fondamenti di Java 21 (PDJ 2)

### 2.1 Paradigma Object-Oriented, Classi e Oggetti

**`Programmazione Orientata agli Oggetti (OOP)`**: Paradigma (modello di programmazione) in cui i dati sono strutturati come **oggetti**, ciascuno dotato sia di uno **stato** (un insieme di attributi/variabili interne che ne caratterizzano le proprietà) sia di **comportamenti** (metodi/funzioni che ispezionano o modificano lo stato dell'oggetto stesso).

**`Classe`**: L'archetipo o stampino (**blueprint**, ovvero il modello astratto) che definisce la struttura (attributi) e i comportamenti (metodi) comuni a tutti gli oggetti di un determinato tipo.

**`Oggetto (Istanza)`**: Una singola entità concreta creata in memoria a **runtime** (durante l'esecuzione del programma) a partire da una classe, caratterizzata da valori specifici per i propri attributi.

### 2.2 I Pilastri della OOP e i Package

I quattro principi fondamentali della programmazione orientata agli oggetti sono:

1. **`Astrazione`**: rappresentare solo gli aspetti essenziali di un problema, nascondendo i dettagli non necessari. In Java si realizza tramite classi, interfacce, ADT e specifiche: chi usa un tipo deve conoscere **cosa** offre, non **come** è implementato.

2. **`Incapsulamento (Encapsulation)`**: racchiudere stato e operazioni in un oggetto e proteggere lo stato interno dall'accesso diretto. In Java si usano principalmente attributi `private`, metodi pubblici controllati, invarianti di rappresentazione (`RI`) e copie difensive.

3. **`Ereditarietà (Inheritance)`**: definire un nuovo tipo a partire da un tipo esistente, ereditandone membri e comportamento e specializzandolo. In Java si usa `extends` per le classi e `implements` per le interfacce. Deve rappresentare una relazione **IS-A** e rispettare il principio di sostituzione di Liskov (`LSP`); altrimenti è preferibile la composizione.

4. **`Polimorfismo (Polymorphism)`**: permettere di usare oggetti di tipi concreti diversi attraverso un tipo comune. Con il **subtyping** il metodo invocato può essere scelto a `runtime` tramite **dynamic dispatch**; con i **generics** si ottiene polimorfismo parametrico; con l'overloading si ottiene polimorfismo ad-hoc.

I pilastri non sono indipendenti: l'astrazione definisce il contratto, l'incapsulamento protegge la rappresentazione, l'ereditarietà organizza i tipi e il polimorfismo permette di sostituire implementazioni diverse dietro lo stesso contratto.

**`Incapsulamento (Encapsulation)`**: Principio secondo cui lo stato interno di un oggetto viene nascosto all'esterno (`private`, accessibile solo dentro la classe stessa), imponendo che ogni ispezione o modifica avvenga esclusivamente attraverso i metodi pubblici dell'oggetto stesso, garantendo così la coerenza dello stato.

**`Package`**: Costrutto organizzativo gerarchico di Java (corrispondente alle cartelle del file system) che raggruppa classi correlate, delimita la visibilità a livello di modulo ed evita conflitti di nomi identificando univocamente ogni classe tramite il suo `Fully Qualified Name` (nome completo comprensivo di tutti i package, es. `java.util.ArrayList`). Il package `java.lang` è importato automaticamente.

### 2.3 Compilazione, Bytecode, JVM ed Espressioni

**`Bytecode e JVM`**: Il compilatore `javac` traduce il codice sorgente Java (`.java`) in `Bytecode` (file `.class` contenente istruzioni intermedie indipendenti dall'architettura hardware). La **Java Virtual Machine (JVM)** (macchina virtuale eseguita dal comando `java`) interpreta ed esegue il `Bytecode` (codice intermedio) sull'hardware specifico (_Write Once, Run Anywhere_).

**`Espressione`**: Costrutto sintattico formato da **atomi** (variabili, costanti, letterali), **operatori** (es. `+`, `==`) e **invocazioni di metodi**. Viene valutata da sinistra verso destra e produce sempre un **tipo** (noto a **compile-time**, cioè durante la compilazione, per induzione) e un **valore** (calcolato a **runtime**, cioè durante l'esecuzione).

**`Type Safety (Sicurezza dei Tipi)`**: Proprietà di Java per cui il tipo di ogni espressione e variabile è determinato e verificato staticamente dal compilatore (a **compile-time**, prima dell'esecuzione), impedendo errori di tipo incompatibile durante l'esecuzione (a **runtime**).

### 2.4 Memoria (Stack vs Heap), Aliasing e Garbage Collector

**`Tipi Primitivi vs Tipi Riferimento`**:
> - **Tipi primitivi** (`byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean`): memorizzano il proprio valore direttamente nello `Stack` (area di memoria veloce e locale alle chiamate dei metodi, a dimensione fissa).
> - **Tipi riferimento** (oggetti, array, `String`): memorizzano nello `Stack` (memoria locale veloce) un **puntatore/riferimento** (indirizzo di memoria) e allocano i dati effettivi nello `Heap` (area di memoria dinamica globale in cui risiedono tutti gli oggetti creati con `new`).

**`Aliasing`**: Situazione in cui due o più variabili di tipo riferimento nello `Stack` (memoria locale veloce) contengono lo stesso indirizzo di memoria e puntano quindi al medesimo oggetto nello `Heap` (memoria dinamica globale degli oggetti).

**`Garbage Collector (GC)`**: Meccanismo automatico della `JVM` (Java Virtual Machine, macchina virtuale Java) che individua nello `Heap` (memoria dinamica globale degli oggetti) gli oggetti non più raggiungibili da alcun riferimento attivo nello `Stack` (memoria locale veloce) e ne libera automaticamente la memoria.

**`Oggetto Immutabile vs Mutabile`**: Un oggetto è **immutabile** se il suo stato interno nello `Heap` (memoria dinamica globale degli oggetti) non può essere modificato dopo l'**istanziazione** (creazione con `new`) (es. `String`, `Integer`); è **mutabile** se il suo stato nello `Heap` (memoria dinamica globale) può essere alterato tramite **metodi mutatori** (metodi che modificano gli attributi interni) (es. `ArrayList`, array).

### 2.5 Passaggio Parametri, Overloading e Wrapping

**`Passaggio dei Parametri per Valore`**: In Java tutti i parametri sono passati per valore: per i **primitivi** viene copiato il valore nello `Stack` (memoria locale veloce); per i **riferimenti** viene copiato l'indirizzo nello `Stack` (memoria locale veloce) — il metodo non può riassegnare il riferimento del chiamante, ma può mutare l'oggetto nello `Heap` (memoria dinamica globale degli oggetti) se quest'ultimo è mutabile.

**`Overloading (Sovraccaricamento)`**: Possibilità di definire più metodi con lo stesso nome all'interno della stessa classe (o gerarchia) purché differiscano nella lista dei **parametri formali** (per numero, tipo o ordine dei parametri).

**`Most Specific Call`**: Meccanismo di risoluzione a **compile-time** (durante la compilazione) dell'**overloading** (metodi con lo stesso nome ma parametri diversi) in presenza di conversioni implicite (**widening**, ovvero promozione automatica verso un tipo più capiente senza perdita di dati, es. `int` $\to$ `double`): il compilatore sceglie il metodo che richiede il minor numero di conversioni; se due metodi richiedono lo stesso numero di conversioni (es. `m(int, double)` e `m(double, int)` invocati con `m(1, 2)`), genera un errore di compilazione per **ambiguità**.

**`Wrapping, Autoboxing e Unboxing`**:
> - **`Wrapping`**: incapsulamento di un tipo primitivo (es. `int`) nella corrispondente classe `Wrapper` immutabile (classe oggetto che avvolge il primitivo, es. `Integer`, `Double`, `Boolean`).
> - **`Autoboxing`**: conversione automatica fatta dal compilatore da tipo primitivo a classe `Wrapper` (classe oggetto corrispondente, `int` $\to$ `Integer`).
> - **`Unboxing`**: conversione automatica inversa da classe `Wrapper` (classe oggetto corrispondente) a tipo primitivo (`Integer` $\to$ `int`, che avviene anche nei confronti con `==` tra un primitivo e un Wrapper).

## 3. Astrazione Procedurale e Linguaggio di Specifica (PDJ 3, 9)

### 3.1 Specifica Procedurale e Clausole di Liskov

**`Specifica Procedurale`**: Contratto formale o semiformale tra implementatore e chiamante di una procedura: definisce in modo **dichiarativo** (_"cosa"_ fa il metodo, mai _"come"_ è implementato, evitando **specifiche operazionali**, cioè la descrizione passo-passo del codice) le condizioni d'uso e gli effetti dell'esecuzione.

Nel corso si utilizza la notazione di Barbara Liskov tramite commenti strutturati:

- **`REQUIRES (Precondizioni)`**: Vincoli sul **dominio** (insieme dei valori ammessi in ingresso) non già espressi dai tipi formali (es. `a != null`, `b != 0`). Va **omessa** se non ci sono vincoli sul dominio.

- **`MODIFIES (Effetti collaterali / Side Effects)`**: Elenca ogni entità preesistente il cui stato viene modificato dal metodo, inclusi parametri mutabili, l'oggetto corrente (`this`, riferimento all'istanza su cui è invocato il metodo) e gli stream di I/O (canali di ingresso/uscita) di sistema (**`System.in`** se legge da standard input, **`System.out`** se stampa su standard output). Va **omessa** se il metodo non modifica nulla.

- **`EFFECTS (Postcondizioni)`**: Descrive il valore restituito, le modifiche apportate agli oggetti elencati in `MODIFIES` (effetti collaterali) ed eventuali eccezioni lanciate. È **sempre presente**.

- **`OVERVIEW`** (a livello di classe): Descrive lo scopo astratto della classe e dichiara esplicitamente se le sue istanze sono **mutabili** o **immutabili**.

### 3.2 Proprietà delle Specifiche

**`Restrittività (di una specifica)`**: Proprietà di una specifica di fornire vincoli sufficienti a escludere tutte le implementazioni scorrette o ambigue.

**`Minimalità (di una specifica)`**: Proprietà di una specifica di porre il minor numero possibile di vincoli sui dettagli implementativi (il codice interno), lasciando allo sviluppatore libertà nella scelta dell'algoritmo.

**`Sottodeterminazione (di una specifica)`**: Proprietà di una specifica che ammette più implementazioni valide che possono restituire risultati diversi a parità di input (es. restituire l'indice di _una_ occorrenza qualunque di un elemento duplicato in un array).

**`Determinismo (di un'implementazione)`**: Proprietà di una specifica implementazione (il codice concreto) per cui, a parità di input e stato iniziale, produce sempre lo stesso identico risultato.

**`Chiarezza e Ridondanza`**: La specifica deve essere semanticamente non ambigua (avere un unico significato chiaro per tutti gli sviluppatori); può includere **ridondanza** (ripetere lo stesso concetto in più forme, ad esempio affiancando esempi concreti alla descrizione testuale) per chiarire comportamenti complessi.

### 3.3 Proprietà delle Procedure: Totalità vs Parzialità

**`Semplicità (di una procedura)`**: Proprietà di una procedura che svolge un unico compito ben definito e coerente.

**`Generalità (di una procedura)`**: Proprietà di una procedura di essere applicabile a un insieme ampio di input grazie a un'opportuna **parametrizzazione** (uso di parametri formali al posto di valori fissi).

**`Procedura Totale`**: Procedura il cui comportamento è definito per **ogni** possibile valore ammesso dai tipi dei suoi parametri formali. Non presenta la clausola `REQUIRES` (precondizioni sul dominio).

**`Procedura Parziale`**: Procedura definita solo per un sottoinsieme del **dominio** (insieme dei valori possibili in ingresso) dei tipi d'ingresso, delimitato dalla clausola `REQUIRES` (precondizioni). Se il chiamante viola `REQUIRES`, il comportamento è **indefinito** (può crashare, entrare in loop infinito o produrre risultati errati).

## 4. Gestione delle Eccezioni (PDJ 4)

### 4.1 Robustezza e Limiti dei Meccanismi Alternativi

**`Graceful Degradation`**: Capacità di un programma di gestire condizioni anomale o errori in modo controllato e prevedibile, evitando crash improvvisi o la propagazione silente di dati corrotti.

**`Errori di Propagazione`**: Errore critico che si verifica quando una procedura segnala un fallimento restituendo un valore speciale del **codominio** (insieme dei valori di ritorno, es. `-1` o `0`) o tramite il pattern **Comma-Ok** (restituzione simultanea di valore ed esito booleano), e il chiamante dimentica di verificarlo, propagando un valore errato nei calcoli successivi.

### 4.2 Le Eccezioni in Java

**`Eccezione`**: Oggetto (sottotipo di `java.lang.Throwable`, la superclasse radice di tutti gli errori ed eccezioni in Java) che rappresenta una condizione anomala o eccezionale; il suo lancio (`throw`) interrompe immediatamente il flusso sequenziale della procedura, separando la gestione degli errori dal **codominio** normale (i valori di ritorno standard) del metodo e permettendo di **rendere totali procedure parziali** (spostando i vincoli da `REQUIRES`, le precondizioni, a `EFFECTS`, le postcondizioni).

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

**`Error vs Checked Exceptions vs Unchecked Exceptions`**:
> - **`Error (Unchecked, non controllata dal compilatore)`**: Errori fatali e irrecuperabili della `JVM` (Java Virtual Machine, macchina virtuale Java) o del sistema operativo (es. `OutOfMemoryError`, esaurimento della memoria).
> - **`Checked Exceptions (Eccezioni controllate)`**: Sottotipi di `Exception` (esclusa `RuntimeException`) che rappresentano errori prevedibili e recuperabili (es. `FileNotFoundException`). Il compilatore **obbliga** a gestirle con `try-catch` (cattura locale) o a dichiararle nella firma del metodo con `throws` (propagazione al chiamante).
> - **`Unchecked Exceptions (Eccezioni non controllate)`**: Sottotipi di `RuntimeException` (eccezioni a tempo di esecuzione) che rappresentano violazioni di precondizioni o bug di programmazione (es. `IllegalArgumentException`, `NullPointerException`).
> - **Regola di Liskov vs Java per `throws` (Trappola d'esame):** Sebbene il compilatore Java non imponga di dichiarare le **unchecked exceptions** (sottotipi di `RuntimeException`) nella firma, secondo la metodologia di Liskov (PDJ) **vanno dichiarate nella clausola `throws` e documentate in `EFFECTS` tutte le eccezioni lanciate esplicitamente**, incluse le **unchecked** (non controllate).

### 4.3 Strategia di Gestione: Masking vs Reflection

**`Masking (Mascheramento)`**: Strategia di gestione in cui un metodo cattura localmente (`try-catch`) un'eccezione sollevata da un sottoprogramma e la risolve autonomamente senza propagarla al proprio chiamante.

**`Reflection (Riflessione)`**: Strategia di gestione in cui un metodo non cattura localmente un'eccezione (o la cattura solo per rilanciare un'eccezione più astratta e significativa per il proprio livello), lasciandola propagare al metodo chiamante tramite la clausola `throws`.

## 5. Astrazione dei Tipi di Dato (ADT) e Implementazione (PDJ 5.1–5.4, 5.8)

### 5.1 Definizione di ADT, Rappresentazione e Adeguatezza

**`Tipo di Dato Astratto (ADT)`**: Tipo di dato definito esclusivamente dall'insieme dei suoi valori astratti e dalle operazioni pubbliche che lo manipolano, nascondendo completamente la **rappresentazione concreta** (gli attributi privati salvati in memoria).

**`Rappresentazione (Rep)`**: L'insieme degli attributi privati (`private`, visibili solo dentro la classe stessa) di una classe che memorizzano lo stato concreto di ogni istanza nello `Heap` (memoria dinamica globale degli oggetti).

**`Adeguatezza (Adequacy)`**: Proprietà di un `ADT` (Tipo di Dato Astratto) di esporre un insieme di operazioni sufficiente e appropriato a consentire ai **client** (chi usa la classe) di svolgere comodamente ed efficientemente tutti i compiti previsti dal dominio senza violare l'**incapsulamento** (la segretezza degli attributi interni).

### 5.2 Tassonomia dei Metodi di un ADT

- **`Metodi d'Istanza vs Metodi di Classe (static)`**: I metodi d'istanza operano sullo stato di un oggetto specifico (`this`, riferimento all'istanza corrente); i metodi `static` (statici) appartengono alla classe, non hanno accesso a `this` e operano esclusivamente sui parametri ricevuti.

- **`Costruttori (Creators)`**: Creano e inizializzano una nuova istanza del tipo. Includono il **Copy Constructor** (costruttore che accetta un'istanza dello stesso tipo per crearne una copia) e i **Metodi Factory statici** (metodi `static` che creano e restituiscono una nuova istanza della classe, utili nelle gerarchie perché non vincolano al nome fisso del costruttore).

- **`Osservatori (Observers)`**: Restituiscono informazioni sullo stato dell'oggetto senza modificarlo (es. getter, **predicati**, cioè metodi che restituiscono un booleano). Non hanno `MODIFIES`.

- **`Mutatori (Mutators)`**: Modificano lo stato interno dell'oggetto (`MODIFIES: this`). Esistono **solo** negli `ADT` (Tipi di Dato Astratti) mutabili.

- **`Produttori (Producers)`**: Partendo da istanze esistenti del tipo, creano e restituiscono una **nuova istanza** dello stesso tipo senza alterare gli operandi (tipici degli `ADT`, Tipi di Dato Astratti, immutabili).

### 5.3 Esposizione della Rappresentazione (Representation Exposure)

**`Representation Exposure (Esposizione della Rappresentazione)`**: Violazione dell'**incapsulamento** (segretezza dello stato interno) che si verifica quando il codice esterno alla classe ottiene un riferimento diretto a un oggetto mutabile facente parte della **rep** (rappresentazione privata), potendolo modificare senza passare per i metodi della classe e rompendo potenzialmente l'**invariante di rappresentazione** (la validità dello stato interno).

**Prevenzione**: Attributi sempre `private` (privati) + **Copia Difensiva** (**Defensive Copying**, ovvero duplicazione degli oggetti mutabili in ingresso nel costruttore/mutatore e in uscita negli osservatori/clone).

### 5.4 I Quattro Metodi Fondamentali di `java.lang.Object`

1. **`equals(Object o)`**: Verifica l'uguaglianza logica tra due oggetti. Deve formare una **relazione di equivalenza** (relazione che soddisfa le proprietà riflessiva, simmetrica e transitiva):
   - **`Riflessiva`**: `x.equals(x)` è `true` (ogni oggetto è uguale a se stesso).

   - **`Simmetrica`**: `x.equals(y)` $\iff$ `y.equals(x)` (l'ordine dei due oggetti nel confronto non cambia il risultato).

   - **`Transitiva`**: se `x.equals(y)` e `y.equals(z)`, allora `x.equals(z)`.

   - **`Coerente`**: restituisce sempre lo stesso valore se gli oggetti non vengono modificati.

   - **`Null-safe`**: `x.equals(null)` restituisce sempre `false`.

   > **Trappola d'esame — `equals` vs `similar` (Liskov vs Java) e Uguaglianza nelle Gerarchie:**
   >
   > - **Mutabili in Liskov vs Java:** Per Liskov, due oggetti mutabili sono `equals` _solo se sono lo stesso identico oggetto_ (`==`, equivalenza comportamentale nel tempo), mentre due oggetti mutabili distinti con lo stesso stato istantaneo si dicono **simili** (`similar`). In Java, molte collezioni (es. `ArrayList`, lista dinamica) implementano `equals` sullo stato corrente: di conseguenza, mutare un oggetto dopo averlo inserito in un `HashSet` (insieme basato su hash) o come chiave di una `HashMap` (mappa chiave-valore basata su hash) invalida la struttura dati.
   > - **Uguaglianza Conservativa vs Rilassata:** L'implementazione **conservativa** (`this.getClass() == o.getClass()`, confronto esatto della classe concreta) richiede lo stesso identico **tipo concreto** (classe reale dell'oggetto); l'implementazione **rilassata** (`o instanceof Supertipo`, controllo di appartenenza alla gerarchia) ammette il confronto tra supertipo e sottotipo ma può rompere **simmetria** o **transitività** se il sottotipo aggiunge attributi rilevanti (es. `Punto2D` vs `Punto3D`).

2. **`hashCode()`**: Restituisce un intero usato dalle strutture dati basate su hash (`HashMap`, mappa chiave-valore; `HashSet`, insieme senza duplicati).
   - **`Contratto equals–hashCode`**: Se `a.equals(b)` è `true`, allora **deve** valere `a.hashCode() == b.hashCode()`. Non vale il viceversa (due oggetti diversi possono avere lo stesso `hashCode`: **collisione**).

3. **`clone()`**: Restituisce una copia dell'oggetto (richiede `implements Cloneable`, interfaccia segnaposto per la clonazione).
   - **`Shallow Copy (Copia superficiale) vs Deep Copy (Copia profonda)`**: `super.clone()` esegue una **Shallow Copy** (copia i primitivi e i puntatori nello `Stack`, memoria locale veloce, condividendo gli oggetti mutabili nello `Heap`, memoria dinamica globale); una **Deep Copy** (copia profonda) clona ricorsivamente anche tutti gli oggetti mutabili nello `Heap` (memoria dinamica globale degli oggetti).

4. **`toString()`**: Restituisce una rappresentazione testuale chiara dell'oggetto astratto.

**`record (Java 21)`**: Costrutto sintattico (`public record Punto(int x, int y) {}`) per definire `ADT` (Tipi di Dato Astratti) **immutabili** puramente portatori di dati: genera automaticamente campi `private final` (privati e non riassegnabili), costruttore canonico, metodi osservatori (`x()`, `y()`), `equals()`, `hashCode()` e `toString()`.

## 6. Correttezza degli ADT: Funzione di Astrazione (AF) e Invariante di Rappresentazione (RI) (PDJ 5.5–5.7)

Il processo di sviluppo di un `ADT` (Tipo di Dato Astratto) segue tre fasi: **1) Specifica** (definizione astratta di dato e operazioni), **2) Scelta della Rappresentazione** (selezione degli attributi `private`), **3) Implementazione delle Procedure**.

### 6.1 Funzione di Astrazione ($AF$)

**`Funzione di Astrazione ($AF$)`**: Funzione matematica $AF : C \to A$ che associa a ogni stato concreto **valido** $c \in C$ (configurazione degli attributi privati nello `Heap`, memoria dinamica globale) l'oggetto astratto $a \in A$ (entità concettuale) che esso rappresenta.
>
> - **Dominio**: L'insieme degli stati concreti validi di $C$ (dove $RI(c) = \text{True}$).
> - **Codominio**: L'insieme di tutti gli oggetti astratti $a \in A$.
> - **Proprietà e Popolamento Completo (Fully Populates):** È **suriettiva** (ogni possibile valore astratto $a \in A$ ha almeno uno stato concreto valido $c \in C$ che lo rappresenta, ossia la classe _"popola completamente"_ l'`ADT`, Tipo di Dato Astratto) e **non-iniettiva / molti-a-uno** (più rappresentazioni concrete distinte possono corrispondere allo stesso oggetto astratto, es. `[1, 2]` e `[2, 1]` rappresentano entrambi l'insieme $\{1, 2\}$).
> - **Implementazione**: Corrisponde al metodo **`toString()`**.

### 6.2 Invariante di Rappresentazione ($RI$)

**`Invariante di Rappresentazione ($RI$)`**: **Predicato** booleano (funzione che restituisce `true` o `false`) $RI : C \to \{\text{True}, \text{False}\}$ definito sull'intero spazio degli stati concreti $C$, che restituisce $\text{True}$ se e solo se $c$ è una rappresentazione valida e coerente di un oggetto astratto:
>
> $$
> RI(c) = \text{True} \iff \exists a \in A \mid AF(c) = a
> $$
>
> - L'insieme $\{c \in C \mid RI(c) = \text{True}\}$ coincide esattamente con il **dominio della funzione di astrazione** $AF$ (insieme degli stati concreti validi).
> - **Struttura**: **Congiunzione logica** (combinazione di più condizioni unite dall'operatore `AND` / `&&`) di predicati sugli attributi.
> - **Validità**: Deve valere **al termine di ogni costruttore** e **al termine di ogni metodo pubblico** (può essere temporaneamente violata _durante_ l'esecuzione interna di un **mutatore**, metodo che modifica lo stato).
> - **Implementazione**: Metodo **`public boolean repOk()`**, verificato tramite **`assert repOk();`** (abilitato a **runtime**, durante l'esecuzione, con il flag della `JVM`, Java Virtual Machine, **`-ea`**, _enable assertions_).

### 6.3 Induzione sui Tipi di Dato, Invariante di Astrazione e Effetti Benevoli

**`Dimostrazione per Induzione sul Tipo di Dato`**: Tecnica formale per dimostrare che l'**invariante di rappresentazione** $RI$ (validità dello stato concreto) vale per ogni istanza raggiungibile di una classe:
>
> 1. **Caso Base**: Tutti i costruttori e i **metodi factory** (metodi statici creatori) producono stati $c$ tali che $RI(c) = \text{True}$.
> 2. **Passo Induttivo**: Assumendo $RI(c) = \text{True}$ prima della chiamata, ogni metodo **mutatore** (che altera lo stato) o **produttore** (che crea una nuova istanza) produce uno stato $c'$ tale che $RI(c') = \text{True}$.

**`Invariante di Astrazione ($AI$)`**: Vincolo di coerenza relativo alla **specifica** dell'`ADT` (Tipo di Dato Astratto) (indipendente dagli attributi privati della rappresentazione): verifica che le operazioni specificate mantengano sempre valido il concetto astratto modellato.

**`Effetti Collaterali Benevoli (Benevolent Side Effects)`**: Modifiche alla rappresentazione concreta interna ($c_1 \to c_2$, variazione degli attributi privati nello `Heap`, memoria dinamica globale) di un `ADT` (Tipo di Dato Astratto) **astrattamente immutabile** (implementato con rappresentazione mutabile e $AF$, Funzione di Astrazione, **molti-a-uno**, dove più stati concreti mappano allo stesso stato astratto) che preservano sia $RI$ (Invariante di Rappresentazione) sia il risultato della funzione di astrazione ($AF(c_1) = AF(c_2)$), ad esempio riordinare un array interno per velocizzare le ricerche o usare una cache interna.

## 7. Gerarchie di Tipi, Principio di Sostituzione di Liskov (LSP) e Composizione (PDJ 7)

### 7.1 La Relazione di Sottotipo ($\prec$), Proprietà e Dispatching

**`Relazione di Sottotipo ($T \prec S$)`**: Una classe o interfaccia $T$ che estende (`extends`) o implementa (`implements`) $S$ è un **sottotipo** di $S$, indicato con il simbolo $T \prec S$ (e reciprocamente $S$ è **supertipo** di $T$).

**`Proprietà Formali e Strutturali della Relazione $\prec$ (Fondamentali per il Quiz)`**:
> 1. **`Transitivita`**: Se $A \prec B$ e $B \prec C$, allora $A \prec C$ (es. `StudenteMagistrale` $\prec$ `Studente` $\prec$ `Persona` $\prec$ `Object` $\implies$ `StudenteMagistrale` $\prec$ `Object`).
> 2. **`Antisimmetria (Aciclicità)`**: Se $A \preceq B$ e $B \preceq A$, allora $A = B$ (non possono esistere cicli nelle gerarchie di tipi: $A \prec B \prec A$ è vietato dal compilatore).
> 3. **`Riflessività in senso lato ($T \preceq T$)`**: Ogni tipo è compatibile con se stesso; in senso stretto ($A \prec B$) indica un sottotipo proprio ($A \neq B$).
> 4. **`Elemento Massimo (Top Type)`**: La classe `java.lang.Object` è in cima alla gerarchia: per ogni tipo riferimento $T \neq \text{Object}$ vale $T \prec \text{Object}$.
> 5. **`Topologia della Gerarchia (Albero vs DAG)`**:
>    - Poiché Java ammette solo l'ereditarietà singola tra classi (`extends`), la gerarchia delle sole classi forma un **Albero** radicato in `Object`.
>    - Poiché Java ammette l'implementazione multipla di interfacce (`implements I1, I2`), la gerarchia complessiva dei tipi forma un **DAG** (**Directed Acyclic Graph**, grafo orientato aciclico).
>
> 6. **`Proprietà di Assegnabilità (Sostituzione)`**: Se $T \prec S$, allora è sempre valida l'assegnazione di un'espressione di tipo $T$ a una variabile o parametro formale di tipo $S$ (`S x = new T();`). Non vale il viceversa senza un **downcast** esplicito (conversione forzata verso un sottotipo più specifico, es. `(T) x`).

**`Tipo Apparente vs Tipo Concreto`**: Data l'assegnazione `S x = new T();` con $T \prec S$:
>
> - Il **Tipo Apparente** ($S$) è il tipo dichiarato a **compile-time** (durante la compilazione) e stabilisce **quali metodi sono invocabili** sul riferimento.
> - Il **Tipo Concreto** ($T$) è la classe reale dell'oggetto istanziato nello `Heap` (memoria dinamica globale degli oggetti) a **runtime** (durante l'esecuzione) e stabilisce **quale implementazione viene eseguita**.

**`Dynamic Method Dispatch (Dispatching Dinamico)`**: Meccanismo per cui a **compile-time** (durante la compilazione) il compilatore seleziona la **segnatura** (intestazione del metodo) più specifica basandosi sul **tipo apparente** (tipo dichiarato della variabile), mentre a **runtime** (durante l'esecuzione) la `JVM` (Java Virtual Machine, macchina virtuale Java) invoca l'implementazione ridefinita (**override**, riscrittura del metodo nella sottoclasse) più specifica basandosi sul **tipo concreto** (classe reale dell'oggetto nello `Heap`, memoria dinamica globale).
>
> - **Trappola d'esame — Costruttori e Override:** Se il costruttore della superclasse (`super(...)`, che deve essere la prima istruzione del costruttore della sottoclasse) invoca un metodo ridefinito dalla sottoclasse (es. `repOk()`), a **runtime** (durante l'esecuzione) viene eseguito l'**override** (versione ridefinita) della sottoclasse _prima_ che gli attributi specifici della sottoclasse siano inizializzati.

### 7.2 Il Principio di Sostituzione di Liskov (LSP)

**`Principio di Sostituzione di Liskov (LSP)`**: Se $T \prec S$ ($T$ è sottotipo di $S$), allora gli oggetti di tipo $S$ in un programma possono essere sostituiti con oggetti di tipo $T$ senza alterare alcuna delle proprietà desiderabili di correttezza del programma stesso.

Affinché $T \prec S$ rispetti `LSP` (Principio di Sostituzione di Liskov) devono valere **tre regole**:

**`1. Regola delle Segnature (LSP)`**: _(Verificata dal compilatore a compile-time)_ Per ogni metodo $m$ di $S$, deve esistere in $T$ un metodo $m'$ con **segnatura** (intestazione) compatibile:
>
> - **Nome e Parametri**: Stesso nome e **stessi tipi dei parametri formali** ($P_{m'} = P_m$, **invarianza** in Java, cioè i tipi dei parametri non possono cambiare; la teoria di Liskov ammetterebbe parametri **controvarianti** $P_m \prec P_{m'}$, cioè tipi più generali nella sottoclasse, ma Java non la supporta perché genererebbe **overloading**, sovraccaricamento).
> - **Tipo di ritorno covariante** (varia nella stessa direzione della gerarchia): Se $m$ restituisce $R_m$ e $m'$ restituisce $R_{m'}$, allora deve valere $R_{m'} \prec R_m$ oppure $R_{m'} = R_m$.
> - **Eccezioni**: $m'$ non può lanciare nuove **checked exceptions** (eccezioni controllate dal compilatore) non previste da $m$; per ogni eccezione $E_{m'}$ lanciata da $m'$, deve esistere un'eccezione $E_m$ in $m$ tale che $E_{m'} \prec E_m$ oppure $E_{m'} = E_m$.

**`2. Regola dei Metodi (LSP)`**: _(Comportamentale, NON verificata dal compilatore)_ Il metodo ridefinito $m'$ in $T$ deve preservare il comportamento di $m$ in $S$:
>
> - **Precondizioni indebolite** (o uguali): $\text{pre}_m \implies \text{pre}_{m'}$ (può accettare un **dominio**, cioè insieme di input, più ampio, mai più restrittivo).
> - **Postcondizioni rinforzate** (o uguali): $(\text{pre}_m \land \text{post}_{m'}) \implies \text{post}_m$ (sugli input validi per $m$, deve garantire almeno tutto ciò che prometteva $m$).

**`3. Regola delle Proprietà (LSP)`**: _(NON verificata dal compilatore)_ Il sottotipo $T \prec S$ deve conservare tutte le proprietà dimostrabili per $S$:
>
> - **Invarianti**: Tutte le invarianti di $S$ devono valere anche per $T$ (nella `repOk()` di $T$ occorre richiamare **`super.repOk()`**, il controllo dell'invariante della superclasse).
> - **Proprietà evolutive**: $T$ deve rispettare tutti i vincoli sulle transizioni di stato nel tempo ammesse da $S$.

### 7.3 Classi Concrete, Classi Astratte, Interfacce e Tipi di Supertipi

**`Classe Concreta vs Classe Astratta vs Interfaccia`**:
> - **`Classe Concreta`**: Classe completa istanziabile con `new` nello `Heap` (memoria dinamica globale), dotata di stato (attributi) e di tutti i metodi implementati.
> - **`Classe Astratta (abstract class)`**: Classe non istanziabile direttamente con `new` che modella un'implementazione parziale; può avere stato (attributi), costruttori (invocati dalle sottoclassi con `super(...)`) e metodi sia concreti sia `abstract` (privi di corpo). Supporta ereditarietà singola (`extends`).
> - **`Interfaccia (interface)`**: Contratto comportamentale puro privo di stato d'istanza (niente attributi non-costanti) e costruttori; dichiara segnature di metodi (e opzionalmente metodi `default` con implementazione predefinita e metodi `static`). Supporta **ereditarietà multipla** (`implements I1, I2`).

**`I Tre Tipi di Supertipi (PDJ 7.10)`**:
> 1. **Supertipi Completi:** Definiscono un'intera astrazione di dati con specifiche complete per tutti i metodi.
> 2. **Supertipi Incompleti:** Stabiliscono solo convenzioni sui nomi dei metodi, con specifiche troppo deboli per scrivere codice generico in termini del supertipo.
> 3. **Snippet (sempre interfacce):** Definiscono uno o pochissimi metodi con specifica precisa e completa (es. `Comparable`, `Cloneable`, `Iterable`), consentendo di scrivere codice **polimorfico** (riutilizzabile su tipi diversi).

**`Implementazione per Comportamento`**: Tecnica con cui i metodi `default` di un'interfaccia (o i metodi di una sottoclasse) vengono implementati invocando esclusivamente altri metodi pubblici/astratti del contratto stesso, senza accedere direttamente alla **rappresentazione** (gli attributi privati).

**`Modificatori di Visibilità`**:
> - `private` (privato): accessibile solo all'interno della classe stessa.
> - _package-private_ (visibilità di default, nessuna keyword): accessibile a tutte le classi dello stesso **package** (cartella/modulo).
> - `protected` (protetto): accessibile alle classi dello stesso **package** (cartella/modulo) **e** a tutte le sottoclassi (anche in altri package).
> - `public` (pubblico): accessibile ovunque.

### 7.4 Astrazioni Ontologiche Errate, Composizione e Delega

**`Astrazione Ontologica Errata`**: Discrepanza tra una relazione concettuale **IS-A** (_"è un"_ nel mondo reale) e la sottotipazione comportamentale in `OOP` (Programmazione Orientata agli Oggetti). Esempio canonico: un `Quadrato` _è_ ontologicamente un `Rettangolo`, ma in un modello mutabile con `setBase` e `setAltezza`, `Quadrato extends Rettangolo` viola `LSP` (Principio di Sostituzione di Liskov: o rompe l'invariante del quadrato o viola la postcondizione di `setAltezza` del rettangolo).

**`Composizione e Delega (HAS-A)`**: Tecnica progettuale alternativa all'ereditarietà in cui una classe contiene un riferimento privato a un'istanza di un'altra classe come attributo (**composizione**, relazione _"ha un"_) e ne riutilizza le funzionalità inoltrando le chiamate ad essa (**delega**), evitando di esporre la **rappresentazione** (gli attributi interni) della superclasse o violare `LSP` (Principio di Sostituzione di Liskov).

## 8. Astrazione di Iterazione e Classi Innestate / Nested Classes (PDJ 6)

### 8.1 `Iterator<E>` vs `Iterable<E>`

**`Astrazione di Iterazione`**: Meccanismo che permette di accedere sequenzialmente e uniformemente a tutti gli elementi di una struttura dati o sorgente senza esporne la **rappresentazione interna** (gli attributi privati nello `Heap`, memoria dinamica globale).

**`Iterator<E> (Generatore in PDJ) e Priming`**: Interfaccia implementata dall'oggetto che mantiene lo stato corrente della scansione e fornisce:
>
> - `boolean hasNext()` (**osservatore**, non modifica lo stato): restituisce `true` se esistono altri elementi da visitare.
> - `E next()` (**mutatore**, avanza lo stato della scansione): restituisce l'elemento successivo e avanza lo stato; lancia `NoSuchElementException` (eccezione di elemento inesistente) se `!hasNext()`.
> - `default void remove()` (**mutatore** opzionale): rimuove l'ultimo elemento restituito da `next()` (spesso lancia `UnsupportedOperationException`, eccezione di operazione non supportata).
> - **Priming di un generatore:** Tecnica che consiste nel consumare uno o più elementi dall'`Iterator` (iteratore) _prima_ del ciclo `while (it.hasNext())` (ad esempio per inizializzare un accumulatore o trovare il massimo).

**`Iterable<E>`**: Interfaccia implementata da una classe contenitore che espone il metodo `Iterator<E> iterator()` (chiamato _Iteratore_ in PDJ) per produrre nuove istanze indipendenti di `Iterator<E>` nello `Heap` (memoria dinamica globale) e abilitare il ciclo **`for-each`**.
>
> - **Trappola d'esame — Perché non restituire direttamente `lista.iterator()`:** Perché l'iteratore standard delle `Collection` (collezioni) Java implementa `remove()`, permettendo al codice **client** (chi usa la classe) di rimuovere elementi dalla collezione interna nello `Heap` (memoria dinamica globale) e violando l'**incapsulamento** (**Representation Exposure**, esposizione della rappresentazione interna).

- **`Iteratore Standalone`**: La classe implementa direttamente `Iterator<E>` quando l'iterazione è la sua funzionalità principale o intrinsecamente sequenziale (es. `Range`, generatore di numeri primi, `Scanner`).

- **`Contenitore Iterable`**: La classe implementa `Iterable<E>` quando contiene dati su cui devono poter iterare più **client** (utilizzatori) in modo indipendente.

### 8.2 Le Quattro Tipologie di Classi Innestate (Nested Classes)

**`Nested Classes (Classi Innestate)`**: Classi definite all'interno di un'altra classe contenitore; poiché fanno parte del codice della classe contenitore, hanno accesso diretto a tutti i suoi attributi `private` (privati) senza violare l'**incapsulamento** (segretezza dello stato interno):
>
> 1. **`Static Nested Class (Classe annidata statica)`**: Dichiarata con `static`; non possiede alcun riferimento implicito all'istanza esterna (per accedervi deve riceverne un riferimento esplicito nel costruttore).
> 2. **`Inner Class (Classe interna non statica)`**: Dichiarata senza `static`; ogni sua istanza nello `Heap` (memoria dinamica globale) è associata a una specifica istanza della classe esterna, accessibile tramite `ClasseEsterna.this`.
> 3. **`Local Inner Class (Classe interna locale)`**: Classe dichiarata localmente all'interno di un blocco/metodo; visibile solo in quel blocco e restituita all'esterno tramite un supertipo/interfaccia (es. `Iterator<E>`).
> 4. **`Anonymous Inner Class (Classe Anonima)`**: Classe locale priva di nome dichiarata e istanziata contestualmente in un'unica espressione (`new Interfaccia() { ... }`). Può accedere alle variabili locali del metodo solo se `final` (non modificabili) o **effectively final** (mai riassegnate dopo la prima inizializzazione).

## 9. Polimorfismo e Generics in Java (PDJ 8 + Java Generics)

### 9.1 Tassonomia del Polimorfismo

**`Polimorfismo`**: Capacità di associare a un unico simbolo (nome di metodo, variabile o tipo) dati di tipo diverso oppure comportamenti diversi in base al contesto.

1. **`Polimorfismo Ad-hoc`**: Procedure con comportamenti diversi in base ai parametri (`Overloading`, sovraccaricamento) o al tipo su cui sono ridefinite (`Overriding`, ridefinizione).

2. **`Polimorfismo di Sottotipo (Subtyping / Inclusion)`**: Capacità di assegnare istanze di sottotipi diversi a un **atomo** (variabile o parametro) avente come **tipo apparente** (tipo dichiarato a **compile-time**, in compilazione) un supertipo comune.

3. **`Polimorfismo Parametrico`**: Capacità di una procedura o di un tipo di operare in modo uniforme su tipi diversi specificati come parametri di tipo (`<T>`).

### 9.2 Generics, Relazioni di Sottotipo ($\prec$), Wildcards e Type Erasure

**`Generics e Operatore Diamond (<>)`**: Meccanismo di **polimorfismo parametrico** che parametrizza classi e metodi su uno o più tipi (`<T>`, `<E>`, `<K, V>`), garantendo la `type-safety` (sicurezza statica dei tipi) a **compile-time** (durante la compilazione) ed eliminando i **cast espliciti** (conversioni forzate di tipo) e i rischi di `ClassCastException` (errore di conversione di tipo a **runtime**, durante l'esecuzione) tipici del polimorfismo per subtyping su `Object`. L'operatore **Diamond (`<>`)** abilita l'**inferenza automatica del tipo** in istanziazione (deduzione del tipo dal lato sinistro dell'assegnamento).

**`Proprietà della Relazione di Sottotipo ($\prec$) nei Generics, Wildcards e Array (Domanda Frequente al Quiz)`**:
> Sia $S \prec T$ (ad esempio `Integer` $\prec$ `Number`, oppure `Dog` $\prec$ `Animal`) e siano $G_1 \prec G_2$ due tipi generici (ad esempio `ArrayList` $\prec$ `List`):
>
> 1. **`Covarianza del Tipo Base (Outer Type, il contenitore esterno)`**: A parità di parametro di tipo $V$, la gerarchia esterna si conserva:
>
>    $$
>    G_1 \prec G_2 \implies G_1\langle V \rangle \prec G_2\langle V \rangle
>    $$
>
>    _(Esempio: `ArrayList<Integer>` $\prec$ `List<Integer>` $\prec$ `Collection<Integer>`)._
>
> 2. **`Invarianza del Parametro di Tipo (Type Argument, il tipo tra parentesi angolari)`**: A parità di tipo contenitore $V$, la gerarchia interna **NON** si propaga:
>
>    $$
>    S \prec T \implies V\langle S \rangle \not\prec V\langle T \rangle \quad \text{e} \quad V\langle T \rangle \not\prec V\langle S \rangle
>    $$
>
>    _(Esempio: `List<Integer>` **non** è sottotipo di `List<Number>` — errore di compilazione! Ciò impedisce di inserire un `Double` in una `List<Integer>` tramite un riferimento `List<Number>`)._
>
> 3. **`Covarianza con Upper-Bounded Wildcard (? extends, carattere jolly con limite superiore)`**: Permette di **leggere** elementi come $T$ (vieta la scrittura, eccetto `null`):
>
>    $$
>    S \prec T \implies V\langle S \rangle \prec V\langle ? \text{ extends } T \rangle \quad \text{e} \quad V\langle ? \text{ extends } S \rangle \prec V\langle ? \text{ extends } T \rangle
>    $$
>
>    _(Esempio: `List<Integer>` $\prec$ `List<? extends Number>`)._
>
> 4. **`Controvarianza con Lower-Bounded Wildcard (? super, carattere jolly con limite inferiore)`**: Permette di **scrivere** elementi di tipo $S$ (lettura solo come `Object`):
>
>    $$
>    S \prec T \implies V\langle T \rangle \prec V\langle ? \text{ super } S \rangle \quad \text{e} \quad V\langle ? \text{ super } T \rangle \prec V\langle ? \text{ super } S \rangle
>    $$
>
>    _(Esempio: `List<Number>` $\prec$ `List<? super Integer>`)._
>
> 5. **`Unbounded Wildcard (<?>, carattere jolly senza vincoli)`**: È il supertipo di tutte le istanziazioni parametriche di $V$:
>
>    $$
>    \forall T: \quad V\langle T \rangle \prec V\langle ? \rangle, \quad V\langle ? \text{ extends } T \rangle \prec V\langle ? \rangle, \quad V\langle ? \text{ super } T \rangle \prec V\langle ? \rangle
>    $$
>
> 6. **`Raw Types (tipi grezzi senza parentesi angolari, per retrocompatibilità)`**: Ogni tipo parametrizzato è sottotipo del corrispondente _raw type_ non parametrizzato:
>
>    $$
>    \forall T: \quad V\langle T \rangle \prec V
>    $$
>
>    _(Esempio: `ArrayList<String>` $\prec$ `ArrayList`)._
>
> 7. **`Confronto con gli Array (Covarianza a Runtime)`**: A differenza dei `Generics` (invarianti), gli **array in Java sono covarianti** (seguono la gerarchia dei loro elementi):
>
>    $$
>    S \prec T \implies S[\,] \prec T[\,]
>    $$
>
>    _(Esempio: `Integer[]` $\prec$ `Number[]` compila, ma inserire un `Double` nello `Heap`, memoria dinamica globale, lancia `ArrayStoreException`, eccezione di salvataggio nell'array, a **runtime**, durante l'esecuzione)._
>
> 8. **`Regola PECS (Producer Extends, Consumer Super)`**: Usa `<? extends T>` quando la struttura _produce_ valori da leggere; usa `<? super T>` quando _consuma_ valori da inserire.

**`Type Erasure e Bridge Methods`**:
> - **Type Erasure** (cancellazione dei tipi): Processo eseguito dal compilatore Java a **compile-time** (durante la compilazione) per garantire la retrocompatibilità del `Bytecode` (file `.class` intermedio): rimuove i parametri di tipo generici sostituendoli con il loro **upper bound** (limite superiore) o `Object`, e inserisce **cast** (conversioni di tipo) automatici in lettura.
> - **Bridge Methods** (metodi ponte): Metodi sintetici generati automaticamente dal compilatore durante la **Type Erasure** (cancellazione dei tipi) per preservare il corretto funzionamento dell'**overriding** (ridefinizione dei metodi) a **runtime** (durante l'esecuzione).

### 9.3 Ordinamento e Polimorfismo: `Comparable<T>` vs `Comparator<T>`

**`Element Subtype Approach (Comparable<T>) vs Related Subtype Approach (Comparator<T>)`**:
> - **`Comparable<T>`** (**Element Subtype Approach**, approccio del sottotipo dell'elemento, `java.lang`): La classe stessa dell'elemento implementa l'interfaccia definendo il proprio **ordinamento naturale** tramite `int compareTo(T o)` ($< 0$ se `this < o`, $0$ se equivalenti, $> 0$ se `this > o`). Richiede **pre-planning** (progettazione preventiva) nella definizione della classe.
> - **`Comparator<T>`** (**Related Subtype Approach**, approccio del sottotipo correlato, `java.util`): Oggetto ausiliario esterno che definisce un criterio di ordinamento alternativo tramite `int compare(T o1, T o2)`, utilizzabile anche su classi preesistenti non modificabili.

## 10. Programmazione Funzionale in Java

### 10.1 Fondamenti del Paradigma Funzionale

**`Programmazione Dichiarativa vs Imperativa`**: La programmazione **imperativa** descrive _come_ eseguire il calcolo tramite sequenze di comandi che mutano lo stato in memoria; la programmazione **dichiarativa** (di cui quella **funzionale** è un'estensione) descrive _cosa_ calcolare mediante espressioni e composizione di funzioni matematiche.

**`Trasparenza Referenziale`**: Proprietà di un'espressione di poter essere sostituita dal suo valore risultante in qualunque punto del programma senza alterarne il comportamento.

**`Funzione Pura`**: Funzione **deterministica** (stesso input $\implies$ sempre stesso output) priva di **effetti collaterali** (**side effects**, ovvero clausola `MODIFIES` vuota) il cui risultato dipende esclusivamente dai parametri ricevuti in ingresso.

**`Cittadini di Prima Classe e Funzioni di Ordine Superiore`**: In un linguaggio funzionale puro, le funzioni sono **cittadini di prima classe** (possono essere passate come parametri, restituite come valori e assegnate a variabili come un qualunque dato) e abilitano le **funzioni di ordine superiore** (funzioni che accettano altre funzioni come argomenti o restituiscono una funzione come risultato).

### 10.2 Interfacce Funzionali, Lambda, Composizione, Currying e Stream

**`Interfaccia Funzionale (@FunctionalInterface)`**: Interfaccia Java che dichiara **un unico metodo astratto** (**SAM** — _Single Abstract Method_, più eventuali metodi `default` o `static`), fungendo da tipo bersaglio per le **lambda expressions** (funzioni anonime compatte) (es. `Function<T,R>`, `Predicate<T>`, `Consumer<T>`, `Supplier<T>`, `Comparator<T>`).

**`Lambda Expression`**: Costrutto sintattico compatto (`(parametri) -> espressione` oppure `(parametri) -> { blocco; }`) per istanziare al volo un'**interfaccia funzionale** (**SAM**, interfaccia con un solo metodo astratto) senza scrivere una classe anonima estesa.

**`Composizione di Funzioni (compose vs andThen)`**: Date due funzioni $f_1$ e $f_2$:
>
> - `f1.compose(f2)` applica prima $f_2$ e poi $f_1$: $(f_1 \circ f_2)(x) = f_1(f_2(x))$.
> - `f1.andThen(f2)` applica prima $f_1$ e poi $f_2$: $(f_2 \circ f_1)(x) = f_2(f_1(x))$.

**`Currying`**: Tecnica funzionale che trasforma una funzione a più argomenti in una sequenza concatenata di funzioni a un solo argomento ciascuna, dove ogni funzione restituisce la funzione successiva fino al risultato finale.

**`Java Stream<T>`**: Astrazione di sequenza di elementi su cui eseguire pipeline dichiarative (non riutilizzabili dopo l'operazione terminale) formate da una **sorgente**, **operazioni intermedie lazy** (pigre, ossia valutate solo al momento dell'operazione terminale: `filter`, `map`, `distinct`, `sorted`) e un'**operazione terminale eager** (immediata, che avvia il calcolo e chiude lo stream: `count`, `forEach`, `anyMatch`, `allMatch`).

## 11. Ingegneria del Software e Design Pattern (PDJ 15)

### 11.1 Pattern Architetturali vs Design Pattern

**`Pattern Architetturali`**: Metodologie per organizzare la macro-struttura di un sistema software separando i **moduli** (componenti) per responsabilità e minimizzando l'**accoppiamento** (le dipendenze reciproche), in particolare separando la **Business Logic** (logica di dominio, elaborazione e accesso ai dati) dalla **Presentation Logic** (interfaccia grafica/utente). Esempi: `MVC` (_Model-View-Controller_, Modello-Vista-Controllore), **MVP** (_Model-View-Presenter_), **MVVM** (_Model-View-ViewModel_).

**`Design Pattern (GoF)`**: Soluzioni progettuali standardizzate e riutilizzabili per risolvere problemi ricorrenti di interazione e strutturazione tra classi e oggetti (non sono algoritmi né librerie di codice pronto). Si classificano in tre categorie: **Creational** (creazionali), **Structural** (strutturali) e **Behavioral** (comportamentali).

### 11.2 I Pattern Trattati nel Corso

**`Singleton (Creational Pattern, pattern creazionale)`**: Garantisce che di una classe esista **una sola istanza** nell'intero programma nello `Heap` (memoria dinamica globale) e fornisce un punto di accesso globale controllato ad essa, rendendo il costruttore `private` (non invocabile dall'esterno) ed esponendo un metodo `public static getInstance()` che inizializza e restituisce un attributo `private static` (unico a livello di classe).

**`Decorator (Structural Pattern, pattern strutturale)`**: Consente di estendere o modificare dinamicamente i comportamenti di un singolo oggetto **già istanziato** a **runtime** (durante l'esecuzione), avvolgendolo per **composizione** (salvandolo come attributo privato) all'interno di classi decoratrici che implementano la stessa **interfaccia comune** e inoltrano le chiamate base per **delega** (inoltro dei metodi all'oggetto interno).

**`Iterator (Behavioral Pattern, pattern comportamentale)`**: Fornisce un modo standardizzato per accedere sequenzialmente agli elementi di una collezione senza esporne la **rappresentazione sottostante** (gli attributi privati).

**`Observer (Behavioral Pattern, pattern comportamentale)`**: Definisce una dipendenza **uno-a-molti** in cui un oggetto emettitore (**Creator / Subject**, il soggetto osservato) mantiene una lista di ascoltatori registrati tramite un'interfaccia comune (`Listener`, ascoltatore) e li notifica automaticamente invocando un metodo di **callback** (metodo richiamato automaticamente al verificarsi di un evento, es. `update()`) ogni volta che si verifica un evento o un cambio di stato.

## 12. Compendio Completo di Tutte le Definizioni, Formulario $\prec$ e Trappole d'Esame (L01–L16)

### 12.1 Formulario delle Proprietà della Relazione di Sottotipo ($\prec$)

Questa scheda riassume tutte le proprietà algebriche e di varianza associate al simbolo $\prec$ (relazione di sottotipo) richieste nei quiz teorici:

| Contesto                                       | Proprietà / Formula con $\prec$                                                                   | Verità / Spiegazione                                                                                                                        |
| :--------------------------------------------- | :------------------------------------------------------------------------------------------------ | :------------------------------------------------------------------------------------------------------------------------------------------ |
| **Proprietà Algebriche Base**                  | **`Transitivita`**: $A \prec B \land B \prec C \implies A \prec C$                    | **VERA** (proprietà fondamentale delle gerarchie: il sottotipo di un sottotipo è sottotipo del supertipo).                                  |
| **Proprietà Algebriche Base**                  | **`Antisimmetria`**: $A \preceq B \land B \preceq A \implies A = B$                   | **VERA** (nessun ciclo ammesso nelle gerarchie di tipi).                                                                                    |
| **Proprietà Algebriche Base**                  | **`Simmetria`**: $A \prec B \implies B \prec A$                                       | **FALSA** (la relazione è asimmetrica/antisimmetrica: un supertipo non è sottotipo del suo sottotipo).                                      |
| **Gerarchia Java**                             | **`Radice Universale`**: $\forall T \text{ ref}: T \preceq \text{Object}$             | **VERA** (`Object`, la classe base universale, è il supertipo massimo di ogni tipo riferimento).                                            |
| **Gerarchia Java**                             | **`Topologia`**: Classi vs Tipi completi                                              | Solo classi (`extends`): **Albero**. Classi + Interfacce: **DAG** (**Directed Acyclic Graph**, grafo orientato aciclico).                   |
| **LSP (Segnature in Java)**                    | $T \prec S \implies R_T \preceq R_S$, $E_T \preceq E_S$, $P_T = P_S$                | Ritorno **covariante** (stessa direzione della gerarchia), eccezioni $\preceq$, parametri **invarianti** (identici) in Java.                |
| **Generics (Outer Type, contenitore esterno)** | $G_1 \prec G_2 \implies G_1\langle V \rangle \prec G_2\langle V \rangle$            | **VERA** (**covarianza** del contenitore esterno: es. `ArrayList<String>` $\prec$ `List<String>`).                                          |
| **Generics (Inner Type, tipo tra angolari)**   | $S \prec T \implies V\langle S \rangle \prec V\langle T \rangle$                    | **FALSA** ($V\langle S \rangle \not\prec V\langle T \rangle$: i `Generics`, tipi parametrici, sono **invarianti** sul parametro interno). |
| **Wildcard `extends` (limite superiore)**      | $S \prec T \implies V\langle S \rangle \prec V\langle ? \text{ extends } T \rangle$ | **VERA** (**covarianza**: consente solo lettura come $T$).                                                                                  |
| **Wildcard `super` (limite inferiore)**        | $S \prec T \implies V\langle T \rangle \prec V\langle ? \text{ super } S \rangle$   | **VERA** (**controvarianza**: consente scrittura di elementi di tipo $S$).                                                                  |
| **Unbounded & Raw Types (tipi grezzi)**        | $V\langle T \rangle \prec V\langle ? \rangle$ e $V\langle T \rangle \prec V$        | **VERA** (`<?>` e i **raw types**, tipi generici usati senza angolari, sono supertipi di ogni $V\langle T \rangle$).                        |
| **Array Java**                                 | $S \prec T \implies S[\,] \prec T[\,]$                                              | **VERA** (gli array sono **covarianti**, con rischio di `ArrayStoreException`, errore di salvataggio, a **runtime**, in esecuzione).        |

### 12.2 Tabella Riepilogativa di Tutte le Definizioni (L01–L16)

| Lezione    | Termine / Concetto                                            | Definizione Essenziale per il Quiz                                                                                                                                                                                                                                                                                                                                                                                           |
| :--------- | :------------------------------------------------------------ | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **L01**    | **Imperativa vs Procedurale**                   | **Imperativa**: sequenza lineare di comandi che mutano la memoria. **Procedurale**: codice scomposto in procedure/funzioni riutilizzabili e **parametrizzate** (con parametri formali).                                                                                                                                                                                                                                      |
| **L01**    | **Decomposizione**                              | Suddivisione di un problema in **moduli** (blocchi di codice) **logicamente coesi**, **indipendenti**, **allo stesso livello di dettaglio** e con **basso accoppiamento** (dipendenze minime).                                                                                                                                                                                                                               |
| **L01**    | **Astrazione**                                  | **Mapping molti-a-uno** (più problemi concreti ricondotti a un'unica soluzione) che trascura il _"come"_ per concentrarsi sul _"cosa"_. Garantisce **Località** (isolamento) e **Modificabilità** (sostituibilità interna).                                                                                                                                                                                                  |
| **L01**    | **Parametrizzazione vs Specifica**              | **Parametrizzazione**: astrae sui **dati** (parametri formali). **Specifica**: astrae sui **vincoli dell'operazione** (contratto esterno).                                                                                                                                                                                                                                                                                   |
| **L02**    | **Classe vs Oggetto**                           | **Classe**: stampino (**blueprint**, modello astratto). **Oggetto**: singola istanza concreta nello `Heap` (memoria dinamica globale).                                                                                                                                                                                                                                                                                     |
| **L02**    | **Encapsulation (Incapsulamento)**              | Nascondere lo stato (`private`, privato) consentendo accesso/modifica solo tramite i metodi della classe.                                                                                                                                                                                                                                                                                                                    |
| **L02**    | **Bytecode, JVM, Type Safety**                  | `javac` $\to$ `.class` (`Bytecode`, codice intermedio) eseguito dalla `JVM` (Java Virtual Machine, macchina virtuale). **Type safety** (sicurezza dei tipi): tipi verificati staticamente a **compile-time** (in compilazione).                                                                                                                                                                                          |
| **L02**    | **Stack vs Heap & Aliasing**                    | **Primitivi**: valore nello `Stack` (memoria locale veloce). **Riferimenti**: puntatore nello `Stack` (memoria locale), dati nello `Heap` (memoria dinamica globale degli oggetti). **Aliasing**: più riferimenti puntano allo stesso oggetto nello `Heap` (memoria dinamica globale).                                                                                                                               |
| **L02**    | **Passaggio Parametri**                         | Sempre **per valore**: copia il valore (primitivi) o il puntatore nello `Stack` (memoria locale) (riferimenti).                                                                                                                                                                                                                                                                                                            |
| **L02**    | **Overloading & Most Specific Call**            | `Overloading` (sovraccaricamento): stesso nome, parametri diversi. Sceglie il metodo con **meno conversioni implicite** (**widening**, promozione di tipo); a parità dà errore di **ambiguità**.                                                                                                                                                                                                                           |
| **L02**    | **Boxing / Unboxing**                           | **Autoboxing**: primitivo $\to$ `Wrapper` (classe oggetto, es. `int` $\to$ `Integer`). **Unboxing**: `Wrapper` (classe oggetto) $\to$ primitivo.                                                                                                                                                                                                                                                                         |
| **L03**    | **Clausole di Liskov**                          | `REQUIRES` (precondizioni, omessa se totale), `MODIFIES` (effetti collaterali: include `this`, `System.in`, `System.out`; omessa se puro), `EFFECTS` (postcondizioni, sempre presente), `OVERVIEW` (scopo e mutabilità della classe).                                                                                                                                                                                        |
| **L03**    | **Restrittività vs Minimalità**                 | **Restrittività**: esclude implementazioni errate. **Minimalità**: pone meno vincoli possibili sull'implementazione.                                                                                                                                                                                                                                                                                                         |
| **L03**    | **Sottodeterminazione vs Determinismo**         | **Sottodeterminazione** (proprietà della specifica): ammette più risultati validi. **Determinismo** (proprietà dell'implementazione): stesso input $\implies$ sempre stesso output.                                                                                                                                                                                                                                          |
| **L03**    | **Procedura Totale vs Parziale**                | **Totale**: definita su tutto il **dominio** (insieme dei valori ammessi dai tipi) (no `REQUIRES`). **Parziale**: definita solo dove vale `REQUIRES` (precondizioni) (altrove comportamento indefinito).                                                                                                                                                                                                                     |
| **L04**    | **Graceful Degradation & Propagazione**         | **Graceful Degradation** (degrado controllato): gestione degli errori senza crash né **errori di propagazione** (rischio di valori speciali `-1` o pattern **Comma-Ok**, valore + booleano, ignorati dal chiamante).                                                                                                                                                                                                         |
| **L04**    | **Checked vs Unchecked Exceptions**             | **Checked** (controllate): obbligo di `try-catch` o `throws`. **Unchecked** (non controllate): `Error` e `RuntimeException` (in Liskov vanno comunque messe in `throws` ed `EFFECTS`).                                                                                                                                                                                                                                       |
| **L04**    | **Masking vs Reflection**                       | **Masking** (mascheramento): cattura e risolve localmente (`try-catch`). `Reflection` (riflessione): propaga al chiamante (`throws`).                                                                                                                                                                                                                                                                                      |
| **L05–06** | **ADT, Rep, Adeguatezza**                       | `ADT` (Tipo di Dato Astratto): definito solo dalle operazioni. **Rep** (rappresentazione): attributi `private`. **Adeguatezza** (**Adequacy**): operazioni sufficienti ed efficienti.                                                                                                                                                                                                                                      |
| **L05–06** | **4 Categorie Metodi ADT**                      | **Creators** (costruttori, **copy constructor** costruttore di copia, **factory** creatori statici), **Observers** (osservatori), **Mutators** (mutatori, solo negli ADT mutabili), **Producers** (produttori, nuova istanza senza mutare operandi).                                                                                                                                                                         |
| **L05–06** | **Representation Exposure**                     | **Esposizione della rappresentazione**: riferimento a stato mutabile interno esposto all'esterno; si previene con `private` (privato) e **copie difensive** (**defensive copying**, duplicazione oggetti).                                                                                                                                                                                                                   |
| **L05–06** | **`equals` / `similar` / `hashCode` / `clone`** | `equals` (riflessivo, simmetrico, transitivo, coerente); Liskov distingue `equals` (`==` sui mutabili) da `similar` (stato istantaneo); conservativo (`getClass()`) vs rilassato (`instanceof`); `a.equals(b)` $\implies$ `a.hashCode() == b.hashCode()`; **Shallow Copy** (copia superficiale dei soli puntatori nello `Stack`) vs **Deep Copy** (copia profonda degli oggetti nello `Heap`, memoria dinamica globale). |
| **L07–08** | **Funzione di Astrazione ($AF$)**               | $AF : C \to A$, **suriettiva** (copre tutti i valori astratti — **Fully Populates**, popolamento completo) e **molti-a-uno** (**non-iniettiva**). Implementata da **`toString()`**.                                                                                                                                                                                                                                          |
| **L07–08** | **Invariante di Rappresentazione ($RI$)**       | **Predicato** (funzione booleana `true`/`false`) $RI : C \to \{\text{True}, \text{False}\}$ che definisce il dominio di $AF$ (Funzione di Astrazione). Implementata da **`repOk()`** + **`assert repOk();`** (`java -ea`, abilita asserzioni a **runtime**, in esecuzione).                                                                                                                                                  |
| **L07–08** | **Induzione ADT, AI, Benevolent Side Effects**  | **Induzione**: base costruttori, passo mutatori/produttori. **AI** (Invariante di Astrazione): invariante di specifica. **Benevolent Side Effects** (effetti collaterali benevoli): mutazioni interne nello `Heap` invisibili ad $AF$ in `ADT` (Tipi di Dato Astratti) astrattamente immutabili.                                                                                                                         |
| **L09–10** | **Tipo Apparente vs Concreto & Dispatch**       | **Tipo Apparente** (**compile-time**, in compilazione): metodi invocabili. **Tipo Concreto** (**runtime**, in esecuzione): **override** (ridefinizione) eseguito (**Dynamic Dispatch**, dispatching dinamico). Attenzione a metodi overridden chiamati in `super(...)`.                                                                                                                                                      |
| **L09–10** | **Principio di Sostituzione di Liskov (LSP)**   | Regola delle **Segnature** (parametri **invarianti**, ritorno **covariante**, eccezioni $\le$), **Metodi** (precondizioni indebolite, postcondizioni rinforzate), **Proprietà** (`super.repOk()` e proprietà evolutive).                                                                                                                                                                                                     |
| **L09–10** | **3 Tipi di Supertipi & Classi/Interfacce**     | Supertipi **Completi**, **Incompleti**, **Snippet** (interfacce minimali). `abstract class` (classe astratta: stato, ereditarietà singola) vs `interface` (interfaccia: no stato, ereditarietà multipla).                                                                                                                                                                                                                    |
| **L09–10** | **Composizione e Delega**                       | **Composizione** (attributo interno) e **Delega** (inoltro chiamate): preferibile a `extends` se la relazione _IS-A_ ontologica viola `LSP` (Principio di Sostituzione di Liskov, es. `Quadrato` vs `Rettangolo`).                                                                                                                                                                                                         |
| **L11–12** | **`Iterator<E>`, `Iterable<E>`, Priming**       | `Iterator` (Generatore): stato scansione (`hasNext`, `next`, `remove`). `Iterable`: produce iteratori (`iterator()`). **Priming**: leggere prima del `while`. Non esporre `Collection.iterator()` perché ha `remove()` (viola l'**incapsulamento**).                                                                                                                                                                         |
| **L11–12** | **4 Nested Classes (Classi Innestate)**         | **Static Nested** (statica, no `Outer.this`), **Inner** (non statica, legata a `Outer.this`), **Local Inner** (locale nel metodo), **Anonymous Class** (anonima senza nome; variabili locali `final` o **effectively final**, mai riassegnate).                                                                                                                                                                              |
| **L13–14** | **3 Tipi di Polimorfismo & Subtype Approaches** | **Ad-hoc** (overload/override), **Subtyping** (sottotipi), **Parametrico** (Generics). **Element Subtype** (`Comparable<T>`, nella classe stessa) vs **Related Subtype** (`Comparator<T>`, oggetto esterno).                                                                                                                                                                                                                 |
| **L13–14** | **Generics, Varianza, PECS, Type Erasure**      | `Generics` (tipi parametrici) **invarianti** (`List<Integer>` $\not\prec$ `List<Number>`) vs **Array** **covarianti** (`ArrayStoreException` a **runtime**, in esecuzione). **PECS** (_Producer Extends, Consumer Super_): `<? extends T>` legge, `<? super T>` scrive. **Type Erasure** (cancellazione tipi a **compile-time**) + **Bridge Methods** (metodi ponte).                                                      |
| **L15**    | **Programmazione Funzionale**                   | **Trasparenza referenziale** (sostituibilità col valore), **Funzione pura** (no `MODIFIES`, nessun effetto collaterale), **SAM** (_Single Abstract Method_, un solo metodo astratto), **Lambda** (funzione anonima compatta), **`compose`** vs **`andThen`**, **Currying** (catena di funzioni unarie), **`Stream<T>`** (operazioni intermedie **lazy**, pigre + operazione terminale **eager**, immediata).                 |
| **L16**    | **Design Pattern**                              | **Architetturali** (MVC, MVP, MVVM) vs **GoF**: `Singleton` (**Creational**, istanza unica globale), `Decorator` (**Structural**, estensione dinamica a **runtime**), `Iterator` e `Observer` (**Behavioral**, notifica tramite **callback**).                                                                                                                                                                       |

## 13. Appendice: Checklist Operativa per l'Esame (Teoria e Laboratorio)

1. **Specifiche in ogni classe e metodo**:
   - Scrivi sempre `// OVERVIEW:` (panoramica della classe) su ogni classe dichiarando se è **mutabile** o **immutabile**.

   - Su ogni metodo (tranne semplici **getter**, osservatori diretti di un attributo, e metodi standard di `Object` come `equals`, `hashCode`, `toString`, `clone`) scrivi `REQUIRES` (precondizioni, solo se procedura parziale), `MODIFIES` (effetti collaterali, solo se modifica qualcosa, inclusi `this`, `System.in`, `System.out`) ed `EFFECTS` (postcondizioni, sempre).

2. **Totalità tramite Eccezioni**:
   - Valida sempre i parametri nei costruttori e nei metodi pubblici. Se lanci un'eccezione per un input non valido, documentala in `EFFECTS` (postcondizioni), dichiarala in `throws` (intestazione delle eccezioni lanciate) secondo lo stile Liskov e **non** metterla in `REQUIRES` (precondizioni).

3. **Protezione della Rappresentazione (Encapsulation / Incapsulamento)**:
   - Tutti gli attributi devono essere `private` (privati, accessibili solo nella classe) e `final` (non riassegnabili) se l'attributo è immutabile.

   - Esegui **copie difensive** (**defensive copying**, duplicazione di array e collezioni mutabili nello `Heap`, memoria dinamica globale) sia in ingresso (costruttori/mutatori) sia in uscita (osservatori) e dentro `clone()` per evitare **Representation Exposure** (esposizione della rappresentazione interna).

4. **Invariante di Rappresentazione ($RI$) e Funzione di Astrazione ($AF$)**:
   - Implementa `public boolean repOk()` (chiamando `super.repOk()`, l'invariante della superclasse, nelle sottoclassi!) e invocalo con `assert repOk();` (asserzione verificata a **runtime** con `java -ea`) alla fine di ogni costruttore e metodo **mutatore** (che altera lo stato).

   - Implementa `toString()` come **Funzione di Astrazione** ($AF$, mappatura dallo stato concreto nello `Heap` all'entità astratta) descrivendo l'entità astratta in modo chiaro e completo.

5. **Gerarchie e Principio di Sostituzione di Liskov (LSP)**:
   - Verifica che ogni sottoclasse rispetti le tre regole di `LSP` (Principio di Sostituzione di Liskov: **Segnature**, **Metodi**, **Proprietà**). Se estendere una classe viola `LSP` (come `Quadrato` vs `Rettangolo`), usa **Composizione** (attributo privato interno) e **Delega** (inoltro delle chiamate).

   - Ricorda che nel costruttore di una sottoclasse `super(...)` (chiamata al costruttore della superclasse) deve essere la **prima istruzione**.

6. **Iteratori e Generics**:
   - Preferisci `Iterable<E>` (contenitore iterabile) + classe interna o anonima (**Nested Class**) per `Iterator<E>` (iteratore) evitando di esporre `remove()` di una collezione interna per non rompere l'**invariante di rappresentazione** ($RI$) della classe.

   - Ricorda che i `Generics` (tipi parametrici) sono **invarianti** (`List<Integer>` $\not\prec$ `List<Number>`): applica la regola **PECS** (_Producer Extends, Consumer Super_) usando `<? extends T>` (wildcard con limite superiore) per leggere e `<? super T>` (wildcard con limite inferiore) per scrivere.
