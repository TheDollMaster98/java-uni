# Teoria Java 21 — guida rapida per l'esame

Questa guida raccoglie i termini e le idee fondamentali del corso. È pensata
per il ripasso: per gli esercizi pratici e gli esempi completi vedi le altre
guide nella cartella `guide`.

**Nota sulle fonti:** la trascrizione automatica disponibile nella cartella
`lezioni` contiene attualmente la Lezione 1. La sezione dedicata a quella
lezione segue ciò che viene effettivamente detto dal docente; le altre sezioni
riassumono le slide L01–L16 e la teoria Java necessaria, ma non vengono
presentate come trascrizione letterale.

## 1. Ingegneria del software e astrazione

- **Modularità**: dividere il programma in componenti con responsabilità
  chiare e dipendenze limitate.
- **Astrazione**: descrivere ciò che serve sapere, nascondendo i dettagli non
  necessari.
- **Incapsulamento (encapsulation)**: raggruppare dati e operazioni e
  controllare l'accesso allo stato.
- **Information hiding**: nascondere le decisioni di implementazione che
  potrebbero cambiare.
- **Decomposizione top-down**: scomporre il problema in funzioni.
- **Decomposizione object-oriented**: scomporre il problema in oggetti che
  possiedono stato e comportamento.
- **Località**: una modifica dovrebbe richiedere cambiamenti in pochi moduli.
- **Riuso, coesione e basso accoppiamento**: un modulo deve avere una
  responsabilità coerente e dipendere il meno possibile dagli altri.

Un'astrazione ha una **specifica** (il contratto osservabile) e una
**implementazione** (come il contratto viene realizzato). Il client deve
dipendere dalla specifica, non dalla rappresentazione concreta.

## 2. Java e orientamento agli oggetti

### Termini base

- **Classe**: definizione di attributi e metodi.
- **Oggetto/istanza**: valore concreto creato da una classe.
- **Stato**: valori degli attributi dell'oggetto.
- **Comportamento**: operazioni offerte dai metodi.
- `this`: riferimento all'istanza corrente; `super`: parte della superclasse.
- `static`: membro associato alla classe, non alla singola istanza.
- `final`: variabile assegnabile una sola volta; una classe `final` non è
  estendibile; un metodo `final` non è ridefinibile.

La visibilità è controllata da:

| Modificatore                          | Visibile                     |
| ------------------------------------- | ---------------------------- |
| `private`                             | solo nella classe            |
| nessun modificatore (package-private) | nello stesso package         |
| `protected`                           | stesso package e sottoclassi |
| `public`                              | ovunque                      |

Gli oggetti sono creati nell'**heap**; le variabili locali e i riferimenti
vivono tipicamente nello **stack**. Un riferimento non è l'oggetto: più
riferimenti possono indicare lo stesso oggetto. `null` indica assenza di
riferimento.

### Tipi e uguaglianza

I tipi primitivi sono `byte`, `short`, `int`, `long`, `float`, `double`,
`char` e `boolean`. I **wrapper** (`Integer`, `Double`, ...) permettono di
usare valori primitivi nelle collezioni; **autoboxing/unboxing** converte
automaticamente tra primitivo e wrapper.

`String` è immutabile: un'operazione produce una nuova stringa. Per confrontare
il contenuto si usa `equals`, non `==` (`==` confronta riferimenti per oggetti).

Un cast può essere:

- **widening**: conversione sicura verso un tipo più generale;
- **narrowing**: conversione esplicita, possibile perdita di informazione o
  `ClassCastException` per riferimenti.

Ogni classe eredita da `Object`. I metodi da ricordare sono:

- `equals`: uguaglianza logica;
- `hashCode`: deve essere uguale quando `equals` è uguale;
- `toString`: rappresentazione testuale.

Se si ridefinisce `equals`, si deve ridefinire coerentemente anche `hashCode`.

### Overloading e overriding

- **Overloading (sovraccarico)**: stesso nome, parametri diversi; la scelta è
  fatta staticamente in compilazione.
- **Overriding (ridefinizione)**: una sottoclasse fornisce una nuova
  implementazione della stessa firma; la scelta del metodo d'istanza avviene
  dinamicamente a runtime. Usare `@Override`.

Il **tipo apparente/statico** è quello dichiarato dalla variabile e determina
quali metodi sono accessibili; il **tipo concreto/dinamico** è quello
dell'oggetto e determina quale override viene eseguito.

## 3. Astrazione procedurale e specifiche

Una procedura è descritta da un contratto:

- **REQUIRES**: precondizioni che il chiamante deve rispettare;
- **EFFECTS**: risultato e comportamento se le precondizioni sono vere;
- **MODIFIES**: oggetti o stato che possono essere modificati;
- **OVERVIEW**: descrizione generale del tipo o dell'operazione.

Una procedura **parziale** ha precondizioni; una **totale** è definita per ogni
input ammesso dal tipo. Il chiamante è responsabile delle `REQUIRES`, mentre
l'implementazione deve garantire gli `EFFECTS`.

Questo è un **design by contract**: non confondere ciò che il metodo promette
con il modo in cui lo realizza. La specifica deve essere indipendente dalla
rappresentazione e abbastanza precisa da permettere implementazioni diverse.

## 4. Eccezioni

Gerarchia essenziale:

```text
Throwable
├── Error                 (problemi gravi della JVM)
└── Exception
    ├── checked exception
    └── RuntimeException   (unchecked exception)
```

- **Checked**: estende `Exception` (ma non `RuntimeException`); il compilatore
  impone `catch` oppure `throws`. Rappresenta una situazione prevista che il
  chiamante può gestire.
- **Unchecked**: estende `RuntimeException`; spesso indica uso scorretto
  dell'API o errore logico (`IllegalArgumentException`, `NullPointerException`,
  indice fuori limite).
- `Error` non è normalmente da catturare.

`throw` lancia un'istanza; `throws` dichiara nella firma ciò che può propagarsi.

```java
public void inserisci(String s) throws MioErrore {
    if (s == null) throw new MioErrore("valore nullo");
}

try {
    operazione();
} catch (MioErrore e) {
    // gestione
} finally {
    // eseguito normalmente sempre
}
```

I `catch` vanno dal più specifico al più generale. `try-with-resources`
chiude automaticamente risorse `AutoCloseable`.

Un override non può aggiungere checked exception più generali o nuove rispetto
alla firma del padre; può dichiararne un sottoinsieme o nessuna. Le unchecked
non hanno questo vincolo.

## 5. ADT: astrazione sui tipi

Un **ADT (Abstract Data Type, tipo astratto di dato)** definisce valori e
operazioni tramite la specifica, senza esporre la rappresentazione. Esempi:
insieme, pila, coda, polinomio.

Categorie di operazioni:

- **creator**: crea un oggetto (costruttore o factory);
- **producer**: crea un valore a partire da altri valori;
- **mutator**: modifica l'oggetto;
- **observer**: osserva senza modificare.

Un tipo è **mutabile** se le sue operazioni possono cambiare lo stato; è
**immutabile** se, una volta creato, lo stato non cambia e i mutator producono
nuovi oggetti. L'immutabilità riduce aliasing e side effect.

### Representation independence

La stessa specifica può avere rappresentazioni diverse (array, lista, mappa).
Il client corretto non deve poter distinguere le rappresentazioni: questo è
il principio di **indipendenza dalla rappresentazione**.

Evitare la **rep exposure**:

- non restituire direttamente array o collezioni interne mutabili;
- non memorizzare direttamente riferimenti esterni se il client può modificarli;
- usare copia difensiva (`new ArrayList<>(lista)`) o viste non modificabili
  quando serve.

## 6. AF e RI

Per documentare un'implementazione di ADT si definiscono:

- **spazio concreto**: tutte le possibili rappresentazioni interne;
- **spazio astratto**: i valori descritti dalla specifica;
- **AF (Abstraction Function)**: mappa una rappresentazione concreta nel valore
  astratto;
- **RI (Representation Invariant)**: condizioni che ogni rappresentazione
  valida deve rispettare.

Esempio per un insieme rappresentato da `int[] a` e `int size`:

```text
RI: 0 <= size <= a.length
    i primi size elementi sono distinti
AF: l'insieme degli elementi a[0], ..., a[size-1]
```

Il costruttore deve stabilire RI; ogni mutator deve preservarlo; ogni observer
può assumere RI. `repOk()` è un controllo utile in fase di test, ma non
sostituisce la dimostrazione del contratto.

## 7. Ereditarietà, interfacce e LSP

- `extends`: relazione **is-a**, una classe eredita da una sola superclasse.
- `implements`: una classe realizza uno o più contratti di interfaccia.
- **Classe astratta**: non istanziabile, può contenere stato, implementazioni
  concrete e metodi astratti.
- **Interfaccia**: contratto; una classe può implementarne più di una.

Il **subtyping** permette di usare un sottotipo dove è atteso il supertipo.
Il **Principio di Sostituzione di Liskov (LSP)** richiede che la sostituzione
non rompa la correttezza del programma:

- non rafforzare le precondizioni;
- non indebolire le postcondizioni;
- preservare gli invarianti e il comportamento promesso;
- rispettare la compatibilità delle firme e delle eccezioni.

Se una sottoclasse deve violare le aspettative della superclasse, la relazione
non è una buona gerarchia. Il caso `Square extends Rectangle` è problematico
se `Rectangle` permette di cambiare larghezza e altezza indipendentemente.

**Composizione** (`has-a`) e **delegation** permettono di riusare un oggetto
senza ereditarne il contratto. In generale preferire composizione quando non
esiste un vero sottotipo o serve flessibilità.

## 8. Iterazione e collezioni

Collezioni principali:

- `List`: ordine, duplicati ammessi; `ArrayList` accesso indicizzato rapido,
  `LinkedList` inserimenti/rimozioni locali;
- `Set`: niente duplicati; `HashSet` usa hash, `TreeSet` mantiene ordine;
- `Map<K,V>`: associa chiavi a valori; le chiavi sono uniche.

`Comparable<T>` definisce l'ordine naturale con `compareTo`; `Comparator<T>`
definisce un ordine esterno con `compare`. Un risultato negativo, zero o
positivo indica rispettivamente minore, uguale o maggiore.

`Iterable<T>` espone `iterator()`. `Iterator<T>` separa l'attraversamento dalla
struttura:

```java
while (it.hasNext()) {
    T x = it.next();
}
```

`hasNext()` non deve avanzare; `next()` restituisce il prossimo elemento e
solleva `NoSuchElementException` se non esiste. `remove()` è opzionale.
L'iteratore può rappresentare anche un generatore o un filtro, non solo un
indice in un array. Modificare strutturalmente una collezione durante
l'iterazione può causare `ConcurrentModificationException`; usare l'iteratore
o le operazioni previste.

## 9. Classi nested

Una classe dichiarata dentro un'altra è una **nested class**:

- **static nested**: non contiene riferimento implicito all'istanza esterna;
- **inner**: è legata a un'istanza esterna e può accedere ai suoi membri;
- **local**: dichiarata dentro un metodo;
- **anonymous**: definita e istanziata in un'unica espressione.

Le classi local/anonymous che catturano variabili locali possono usare solo
variabili `final` o **effectively final**. Sono utili per implementazioni
strettamente collegate, iteratori e callback.

## 10. Generics e polimorfismo parametrico

I generics forniscono riuso e controllo statico:

```java
class Box<T> {
    private T value;
    T get() { return value; }
}
```

`T` è un **type parameter**; `String` in `Box<String>` è un **type argument**.
I generics Java sono invarianti: `List<Integer>` non è una
`List<Number>`.

Wildcards:

- `<?>`: tipo sconosciuto;
- `<? extends T>`: produce valori leggibili come `T`, non si inseriscono
  elementi (a parte `null`);
- `<? super T>`: può consumare `T`, la lettura restituisce al massimo `Object`.

**PECS**: _Producer Extends, Consumer Super_.

Un bound può restringere il tipo: `<T extends Comparable<T>>`. La **type
erasure** elimina le informazioni generiche a runtime: non si può usare
`new T()`, `T.class` o creare direttamente array di `T`; i tipi raw perdono
sicurezza e possono generare warning e `ClassCastException`.

## 11. Programmazione funzionale e Stream

Una funzione tende a essere:

- **pura**: stesso input, stesso output, nessun effetto collaterale;
- **referenzialmente trasparente**: può essere sostituita dal proprio risultato.

Una **functional interface** ha un solo metodo astratto (`@FunctionalInterface`).
Le interfacce più comuni sono `Function<T,R>`, `Predicate<T>`,
`Consumer<T>`, `Supplier<T>`, oltre a `BiFunction` e varianti primitive.
Le lambda sono implementazioni concise:

```java
Predicate<Integer> pari = n -> n % 2 == 0;
Function<Integer, Integer> doppio = n -> n * 2;
```

Uno **Stream** è una pipeline di elaborazione, non una collezione:

```java
lista.stream()
     .filter(x -> x > 0)       // intermedia
     .map(x -> x * 2)          // intermedia
     .toList();                // terminale
```

Le operazioni intermedie sono lazy; una terminale (`forEach`, `collect`,
`reduce`, `count`, `findFirst`, `toList`) avvia l'elaborazione. Evitare side
effect e modifiche concorrenti della sorgente. `map` trasforma elementi,
`filter` seleziona, `flatMap` appiattisce, `reduce` aggrega.

## 12. Design pattern fondamentali

Un **design pattern** è una soluzione generale e riutilizzabile a un problema
ricorrente, non una libreria da copiare.

- **Factory**: delega la creazione e nasconde la classe concreta.
- **Singleton**: garantisce una sola istanza e un punto di accesso; usarlo
  solo quando l'unicità è davvero parte del dominio.
- **Observer**: un soggetto notifica più osservatori quando cambia stato;
  riduce il collegamento tra produttore e destinatari.
- **Strategy**: incapsula algoritmi intercambiabili dietro un'interfaccia.
- **Decorator**: avvolge un oggetto con la stessa interfaccia aggiungendo
  comportamento, senza modificare la classe originale.
- **MVC** (cenno architetturale): separa modello, vista e controllo.

## 13. Checklist teorica d'esame

Quando si analizza una classe o una traccia, chiedersi:

1. Qual è la specifica? Quali sono `REQUIRES`, `EFFECTS` e `MODIFIES`?
2. Quale stato è osservabile e quale deve restare privato?
3. Quali sono AF e RI? Ogni costruttore e mutator preserva RI?
4. Ci sono alias o rep exposure? Serve una copia difensiva?
5. La relazione è davvero `is-a` o è meglio composizione?
6. Il codice rispetta LSP, `equals`/`hashCode` e le regole di override?
7. L'eccezione è checked o unchecked, e chi deve gestirla?
8. Il tipo statico e quello dinamico portano allo stesso dispatch atteso?
9. Per una collezione: ordine, duplicati, chiavi e costo delle operazioni?
10. Per un generic: è producer o consumer (PECS)?
11. Per uno stream: quali operazioni sono lazy e qual è la terminale?
12. La soluzione nasconde davvero la rappresentazione e limita l'accoppiamento?

### Parole da non confondere

| Coppia                      | Differenza                                      |
| --------------------------- | ----------------------------------------------- |
| classe / oggetto            | definizione / istanza                           |
| specifica / implementazione | cosa promette / come lo realizza                |
| `throw` / `throws`          | lancia / dichiara                               |
| checked / unchecked         | obbligo del compilatore / errore logico tipico  |
| overloading / overriding    | scelta statica / ridefinizione dinamica         |
| tipo statico / dinamico     | dichiarazione / classe reale dell'oggetto       |
| AF / RI                     | mappa concreto→astratto / validità del concreto |
| `extends` / `implements`    | eredita classe / realizza interfaccia           |
| `List` / `Set` / `Map`      | sequenza / insieme / chiave-valore              |
| `Iterator` / `Iterable`     | oggetto che attraversa / oggetto attraversabile |
| `? extends` / `? super`     | producer / consumer                             |

## 14. Glossario dei termini tecnici

Questi sono i termini che compaiono spesso nelle slide e nelle domande
teoriche, ma che non sempre vengono spiegati durante gli esercizi.

### A–D

- **Aliasing**: due o più variabili sono alias quando riferiscono lo stesso
  oggetto. Una modifica fatta tramite un alias è visibile anche agli altri.
- **API (Application Programming Interface)**: insieme di classi, metodi e
  contratti che un componente rende disponibili ai client.
- **Binding**: associazione tra una chiamata e il metodo eseguito. È statico
  se deciso a compile-time, dinamico se deciso a runtime.
- **Callback**: funzione o oggetto passato a un altro metodo, che lo
  richiama in seguito.
- **Casting**: conversione esplicita di un riferimento o valore a un altro
  tipo compatibile.
- **Closure**: funzione/lambda che “ricorda” e usa variabili del contesto in
  cui è stata creata.
- **Coesione (cohesion)**: quanto le responsabilità di un modulo sono
  collegate tra loro. Si desidera alta coesione.
- **Compile-time / runtime**: fase di compilazione / fase di esecuzione.
- **Composizione**: costruzione di un oggetto usando altri oggetti come
  componenti; esprime una relazione `has-a`.
- **Contratto**: insieme di precondizioni, postcondizioni e invarianti che
  definiscono il comportamento promesso.
- **Covarianza**: relazione in cui un tipo parametrizzato mantiene la
  direzione della relazione tra tipi; in Java si esprime soprattutto con
  `? extends`.
- **Dispatch dinamico**: scelta a runtime dell'override da chiamare in base
  al tipo concreto dell'oggetto.
- **Dispatch statico**: scelta a compile-time, tipica dell'overloading, in
  base al tipo apparente e ai parametri dichiarati.

### E–I

- **Effetto collaterale (side effect)**: modifica di uno stato esterno o
  interazione osservabile oltre al valore restituito da una funzione.
- **Encapsulation**: protezione dello stato interno tramite accessi controllati
  e operazioni pubbliche.
- **Factory method**: metodo che crea e restituisce oggetti, nascondendo al
  client il costruttore o la classe concreta.
- **Fall-through**: in uno `switch` classico, esecuzione dei casi successivi
  quando manca `break`.
- **Functional interface**: interfaccia con un solo metodo astratto,
  compatibile con una lambda.
- **Generics / parametric polymorphism**: definizione di classi o metodi
  indipendenti dal tipo concreto, ad esempio `List<T>`.
- **Heap**: area di memoria in cui vengono allocati gli oggetti.
- **Immutabilità**: proprietà di un oggetto il cui stato non cambia dopo la
  costruzione.
- **Information hiding**: nascondere le scelte interne che non devono essere
  conosciute o usate dal client.
- **Invariant (invariante)**: proprietà che deve rimanere vera durante tutta
  la vita di un oggetto.
- **Iterator invalidation**: situazione in cui una modifica strutturale rende
  non più valido lo stato di un iteratore.

### L–P

- **Lazy evaluation**: calcolo rimandato finché il risultato non è realmente
  richiesto; nelle Stream le operazioni intermedie sono lazy.
- **Late binding**: altro nome per il binding dinamico; la decisione sul
  metodo concreto è rimandata al runtime.
- **Liskov Substitution Principle (LSP)**: ogni sottotipo deve poter sostituire
  il supertipo senza violare le aspettative del client.
- **Method reference**: sintassi abbreviata per passare un metodo come
  funzione, ad esempio `String::length`.
- **Mutabilità osservabile**: cambiamento dello stato che può essere rilevato
  dal client attraverso gli observer.
- **Namespace**: spazio dei nomi che evita conflitti tra identificatori;
  package e classi contribuiscono a organizzarlo.
- **Overhead**: costo aggiuntivo, in memoria o tempo, introdotto da una
  tecnica rispetto all'operazione diretta.
- **Override**: ridefinizione di un metodo ereditato mantenendone la firma.
- **Package-private**: visibilità ottenuta omettendo il modificatore; accesso
  consentito solo nello stesso package.
- **Pipeline**: sequenza di trasformazioni, tipica degli Stream, composta da
  operazioni intermedie e una terminale.
- **Postcondizione**: proprietà che deve essere vera dopo l'esecuzione di un
  metodo riuscito.
- **Precondizione**: condizione che il chiamante deve garantire prima della
  chiamata.

### R–T

- **Raw type**: uso di un tipo generico senza argomento, come `List` invece di
  `List<String>`; riduce la sicurezza statica e produce warning.
- **Recursion**: definizione di un metodo in termini di sé stesso; deve avere
  un caso base.
- **Referential transparency (trasparenza referenziale)**: un'espressione
  può essere sostituita dal suo valore senza cambiare il comportamento.
- **Reflection**: meccanismo che permette di esaminare classi e membri a
  runtime; è potente ma riduce i controlli statici.
- **Representation exposure (rep exposure)**: il client ottiene accesso
  diretto alla rappresentazione interna e può violare RI.
- **Shallow copy**: copia dell'oggetto che mantiene gli stessi riferimenti
  interni.
- **Deep copy**: copia ricorsiva anche degli oggetti interni, senza condividere
  la rappresentazione mutabile.
- **Stack**: area associata tipicamente a chiamate di metodo, variabili locali
  e riferimenti.
- **Subtype / supertype**: sottotipo che può essere usato al posto del
  supertipo; ad esempio `ArrayList` è sottotipo di `List`.
- **Type erasure**: rimozione dei parametri generici durante la compilazione
  del bytecode Java.
- **Type safety**: garanzia, fornita soprattutto dal compilatore, che i valori
  siano usati con il tipo corretto.

### U–Z e parole che ricorrono nelle domande

- **Unchecked**: eccezione non controllata dal compilatore, normalmente
  derivata da `RuntimeException`.
- **Variance**: modo in cui un tipo parametrizzato si rapporta ai sottotipi;
  Java usa invariance per i generics, con wildcard per esprimere flessibilità.
- **Weak/strong coupling**: dipendenze numerose e rigide / dipendenze ridotte
  e sostituibili. Si preferisce weak coupling.
- **Wrapper**: oggetto che incapsula un primitivo, come `Integer` per `int`.
- **Abstraction leak**: dettaglio interno dell'implementazione che “trapela”
  nella specifica o diventa visibile al client.
- **Bottom-up**: progettazione che costruisce prima componenti semplici e poi
  li combina in strutture più grandi.
- **Client**: codice che usa un tipo o una procedura tramite la sua API.
- **Dominio**: insieme dei concetti e delle regole del problema da modellare.
- **Inizializzazione eager**: creazione o calcolo eseguito subito, anche se il
  risultato potrebbe non essere usato.
- **Metodo terminale**: operazione Stream che produce il risultato e avvia la
  pipeline (`count`, `reduce`, `toList`, `forEach`).
- **Metodo intermedio**: operazione Stream che restituisce un altro Stream
  (`filter`, `map`, `sorted`) e quindi può essere concatenata.
- **Side-effect free**: senza effetti collaterali; tipico obiettivo delle
  funzioni pure.
- **Sottotipo comportamentale**: sottotipo che rispetta non solo la firma, ma
  anche il comportamento e il contratto del supertipo.

## 15. Teoria del linguaggio e gestione della memoria

### Dal codice sorgente all'esecuzione

Java è un linguaggio **statically typed**: i tipi delle variabili vengono
controllati principalmente a compilazione.

```text
File .java
   ↓ javac
Bytecode .class
   ↓ JVM (class loader + verificatore + interprete/JIT)
Istruzioni eseguite sulla macchina
```

- **JDK (Java Development Kit)**: strumenti per sviluppare (`javac`, `javadoc`,
  debugger) e ambiente di esecuzione.
- **JRE (Java Runtime Environment)**: concetto storico per l'ambiente di
  esecuzione; nelle versioni moderne si usa normalmente il JDK per sviluppare
  e una JVM/JDK per eseguire.
- **JVM (Java Virtual Machine)**: macchina virtuale che carica ed esegue il
  bytecode. Per questo lo stesso `.class` può funzionare su sistemi diversi
  con una JVM adatta.
- **Bytecode**: istruzioni intermedie contenute nei file `.class`, non codice
  macchina specifico di Windows o Linux.
- **JIT (Just-In-Time compiler)**: la JVM può compilare a codice nativo le
  parti eseguite frequentemente per migliorarne le prestazioni.

Il **class loader** carica le classi quando servono; il verificatore controlla
che il bytecode sia valido e sicuro. L'ordine tipico è caricamento,
collegamento (verifica, preparazione e risoluzione) e inizializzazione.

### Tipi primitivi e riferimenti

Un valore Java può essere, semplificando:

- **primitivo**: contiene direttamente un valore (`int`, `double`, `boolean`,
  `char`...);
- **riferimento**: contiene un riferimento a un oggetto, oppure `null`.

```java
int a = 3;
int b = a;
b = 7;                 // a resta 3

String x = new String("ciao");
String y = x;
y = "altro";           // x continua a riferire l'oggetto originale
```

Nel secondo esempio `x` e `y` inizialmente indicano lo stesso oggetto: questo
è **aliasing**. Se l'oggetto fosse mutabile, una modifica fatta tramite `y`
sarebbe visibile anche tramite `x`.

`null` non è un oggetto: è un valore speciale per un riferimento. Usare un
riferimento `null` come se indicasse un oggetto provoca normalmente
`NullPointerException`.

### Stack, heap e aree della JVM

La suddivisione esatta dipende dalla JVM, ma il modello da ricordare è:

- **stack della thread**: ogni chiamata crea uno stack frame con parametri,
  variabili locali e informazioni per tornare al chiamante. Quando il metodo
  termina, il frame viene rimosso;
- **heap**: contiene gli oggetti e gli array creati con `new`. È gestito dal
  garbage collector;
- **method area/metaspace**: contiene informazioni sulle classi caricate,
  metadati e dati associati ai membri `static`;
- **program counter**: indica l'istruzione corrente della singola thread;
- **native method stack**: area usata dalle chiamate a codice nativo.

Questa è una spiegazione concettuale: lo standard Java non impone che ogni
variabile sia fisicamente in una precisa area. Non dire quindi che “un
primitivo è sempre nello stack”: una variabile locale, un campo di un oggetto
o una variabile `static` hanno contesti diversi.

Esempio concettuale:

```java
void f() {
    int n = 4;              // variabile locale nel frame di f
    Persona p = new Persona(); // oggetto nell'heap, p è un riferimento
}
```

Terminato `f`, il riferimento locale `p` scompare. L'oggetto può essere
raccolto quando non è più raggiungibile da alcun riferimento vivo.

### Garbage collector

Java usa la **gestione automatica della memoria**: il programmatore non chiama
`free` o `delete`.

- un oggetto è **raggiungibile** se può essere raggiunto da una root (thread,
  variabile locale viva, campo statico, riferimento JNI, ecc.);
- quando non è più raggiungibile è **garbage**;
- il **garbage collector (GC)** recupera memoria in momenti scelti dalla JVM,
  non necessariamente appena l'oggetto diventa garbage;
- `System.gc()` è solo una richiesta, non una garanzia;
- `finalize()` è deprecato e non va usato per liberare risorse.

Il GC recupera memoria degli oggetti, ma non chiude automaticamente file,
socket o connessioni nel momento corretto. Per queste risorse usare
`AutoCloseable` e `try-with-resources`.

Un programma può ancora avere **memory leak logici**: la memoria non viene
liberata perché il programma conserva inutilmente riferimenti (per esempio una
lista statica che continua a crescere), anche se non esiste `free` manuale.

### Passaggio dei parametri

Java è **sempre pass-by-value**:

- per un primitivo viene copiata la sua valore;
- per un oggetto viene copiato il riferimento, non l'oggetto.

```java
void cambia(int x) {
    x = 10;
}

void modifica(List<Integer> lista) {
    lista.add(1);           // modifica l'oggetto condiviso
    lista = new ArrayList<>(); // riassegna solo la copia locale
}
```

Il chiamante vede la modifica dell'oggetto fatta attraverso il riferimento, ma
non vede la riassegnazione del parametro. Java non ha passaggio per
riferimento delle variabili del chiamante.

### Costruzione e distruzione logica di un oggetto

Con `new` avvengono, concettualmente, allocazione, inizializzazione dei campi
ai valori di default, esecuzione degli inizializzatori e del costruttore.

Valori di default dei campi:

| Tipo | Default |
|---|---|
| tipi numerici | `0` (o `0.0`) |
| `char` | `'\u0000'` |
| `boolean` | `false` |
| riferimenti | `null` |

Le **variabili locali** invece devono essere inizializzate prima dell'uso:
non ricevono automaticamente questi valori dal compilatore.

Un costruttore non viene ereditato. Il costruttore della superclasse viene
eseguito prima del corpo del costruttore della sottoclasse (`super(...)` è
implicito come prima istruzione se possibile). Gli inizializzatori e i campi
vengono elaborati dall'alto verso il basso nella gerarchia e nell'ordine
testuale.

Java non ha un distruttore deterministico. La “distruzione” è il momento in
cui l'oggetto diventa non raggiungibile; il GC decide quando recuperarlo.

### `static`, `final` e costanti

- un campo di istanza appartiene a ogni oggetto;
- un campo `static` è condiviso dalla classe;
- un metodo `static` non ha `this` e non può accedere direttamente ai membri
  di istanza;
- un campo `static final` è una costante logica, normalmente dichiarata con
  nome maiuscolo.

`final` su un riferimento impedisce di riassegnare il riferimento, ma non
rende automaticamente immutabile l'oggetto:

```java
final List<String> nomi = new ArrayList<>();
nomi.add("Ada");       // possibile: cambia l'oggetto
// nomi = new ArrayList<>(); // impossibile: riferimento finale
```

Un blocco o metodo `static` può eseguire inizializzazione una volta quando la
classe viene inizializzata. L'ordine degli inizializzatori statici può essere
importante e dipende dall'ordine delle dichiarazioni.

### Stringhe, pool e immutabilità

Le stringhe literal possono essere conservate nello **string pool**:

```java
String a = "java";
String b = "java";
// a == b può risultare true perché condividono il literal
```

Questo non cambia la regola: `==` confronta riferimenti, `equals` confronta
contenuto. `new String("java")` crea esplicitamente un altro oggetto.

L'immutabilità di `String` rende sicura la condivisione: metodi come
`toUpperCase()` non modificano la stringa originale, ma restituiscono una
nuova stringa. Per molte concatenazioni usare `StringBuilder`.

### Array e memoria

Un array è un oggetto di lunghezza fissa e contiene elementi tutti dello stesso
tipo. La lunghezza è disponibile tramite `length`, non `length()`.

```java
int[] a = new int[3];    // [0, 0, 0]
String[] s = new String[2]; // [null, null]
```

Gli array di oggetti contengono riferimenti, non copie degli oggetti. Gli
indici validi sono da `0` a `length - 1`; fuori dall'intervallo si ottiene
`ArrayIndexOutOfBoundsException`. Gli array sono covarianti:
`String[]` è un `Object[]`, ma un inserimento di un oggetto non-String può
causare `ArrayStoreException` a runtime. Le collezioni generiche sono invece
invarianti e controllate diversamente.

### Eccezioni, errori e stack trace

Quando viene lanciata un'eccezione:

1. la JVM cerca un `catch` compatibile nel metodo corrente;
2. se non lo trova, risale lo stack dei chiamanti (**stack unwinding**);
3. esegue i `finally` incontrati;
4. se nessuno gestisce l'eccezione, termina la thread e stampa lo
   **stack trace**.

Lo stack trace mostra classe, metodo e riga in cui l'eccezione è stata
osservata/lanciata e la catena delle chiamate. Non va confuso con lo stack
memory: il primo è un'informazione diagnostica, il secondo è un'area di
memoria della JVM.

### Thread e visibilità: nozioni minime

Una **thread** è un flusso di esecuzione indipendente. Più thread possono
condividere gli oggetti nell'heap, quindi possono verificarsi:

- **race condition**: il risultato dipende dall'ordine non deterministico delle
  operazioni;
- **data race**: accesso concorrente allo stesso dato, almeno uno in scrittura,
  senza sincronizzazione corretta;
- **visibilità**: una thread potrebbe non vedere subito la scrittura di
  un'altra senza una relazione di sincronizzazione;
- **atomicità**: un'operazione non è osservabile a metà.

`synchronized` protegge una sezione critica e stabilisce anche relazioni di
visibilità; `volatile` garantisce visibilità e impedisce alcune riordinazioni,
ma non rende atomiche operazioni composte come `x++`. Per contatori
concorrenti usare, quando appropriato, classi atomiche (`AtomicInteger`) o
sincronizzazione.

Una classe è **thread-safe** se mantiene il proprio contratto anche quando
usata correttamente da più thread. L'immutabilità è una tecnica semplice per
ridurre i problemi di concorrenza.

### Parole chiave del linguaggio

- `this`: riferimento all'istanza corrente;
- `super`: accesso alla parte/metodi della superclasse;
- `new`: creazione di un oggetto o array;
- `null`: assenza di riferimento;
- `instanceof`: verifica compatibile del tipo runtime;
- `return`: restituisce un valore e termina il metodo;
- `break`: interrompe ciclo o `switch`;
- `continue`: passa all'iterazione successiva;
- `static`: appartiene alla classe;
- `final`: non riassegnabile/non estendibile/non ridefinibile a seconda del
  contesto;
- `abstract`: dichiara una classe o un metodo incompleto;
- `interface`: definisce un contratto implementabile;
- `enum`: definisce un insieme finito di costanti tipate;
- `record`: classe concisa per dati, con componenti finali e metodi generati;
- `assert`: controllo pensato per le asserzioni; può essere disabilitato,
  quindi non sostituisce la validazione del programma.

## 16. Lezione 1: concetti verificati dalla trascrizione

Questa parte segue la trascrizione disponibile. I sottotitoli sono
automatici, quindi alcuni nomi o frasi possono essere trascritti male, ma i
concetti ricorrenti sono riconoscibili.

### Programmazione imperativa e procedurale

- **Programmazione imperativa**: si descrive una sequenza di comandi che il
  calcolatore esegue in un ordine preciso. Un comando può assegnare valori,
  eseguire calcoli o modificare lo stato in memoria.
- **Programmazione procedurale**: il codice viene organizzato in procedure o
  funzioni, cioè blocchi di istruzioni riutilizzabili.
- **Parametri**: una procedura può lavorare su valori generici; i parametri
  vengono sostituiti dai valori effettivi quando la procedura viene chiamata.

La trascrizione sottolinea che `if`, `for` e molti costrutti Java sono simili a
quelli già visti nei linguaggi imperativi/procedurali, ma occorre imparare le
differenze specifiche del linguaggio.

### Decomposizione

La **decomposizione** divide un problema complesso in sottoproblemi più
semplici. I sottoproblemi corretti devono essere:

- **logicamente coesi**: tutte le operazioni interne servono alla stessa idea
  o responsabilità;
- **isolabili/indipendenti**: si possono progettare e sviluppare senza
  dipendere inutilmente dal resto;
- **componibili**: una volta riuniti, non devono lasciare parti mancanti;
- allo stesso **livello di dettaglio**: non mescolare un'operazione molto
  piccola con un'attività molto generale.

Esempio concettuale della lezione: lettura dei dati, esecuzione del calcolo e
scrittura del risultato sono sottoproblemi distinti. Il calcolo della media
deve restare un sottoproblema coerente, non essere mescolato con la lettura o
con la stampa.

### Astrazione

L'**astrazione** è il processo mentale che porta da un problema concreto a una
soluzione più generale e riutilizzabile. Decomporre soltanto il caso “media di
tre valori” separa il codice, ma non lo rende ancora generale; astrarre
significa ottenere una soluzione applicabile a un numero variabile di valori.

La trascrizione presenta due meccanismi principali:

1. **Astrazione per parametrizzazione**: individuare ciò che cambia,
   trasformarlo in parametro e definire una procedura valida per tutti i
   valori compatibili.
2. **Astrazione per specifica**: documentare che cosa fa un componente, in
   modo che chi lo usa non debba leggere tutta l'implementazione per capirlo.

Una specifica deve comunicare in modo **logico, coerente e costante** il
comportamento del codice. Descrive le entità coinvolte, i requisiti sui
parametri e l'operazione da eseguire, senza obbligare a conoscere i dettagli
interni.

### Progettare prima di implementare

La decomposizione e l'astrazione servono a ottenere codice:

- più facile da progettare;
- più facile da implementare;
- più facile da leggere e mantenere;
- riutilizzabile in problemi simili.

All'esame conviene quindi non aggiungere parti non richieste: prima si
individuano i sottoproblemi e il contratto di ciascuno, poi si implementano
solo le responsabilità necessarie.

## 17. Integrazioni dalle slide

Questi argomenti nelle slide sono importanti anche se compaiono meno spesso
negli esercizi.

### I quattro modi di fare astrazione

1. **Astrazione per parametrizzazione**: sostituire un valore concreto con un
   parametro, per esempio `somma(int a, int b)`.
2. **Astrazione per specifica**: usare un metodo conoscendone il contratto,
   senza sapere come è implementato.
3. **Astrazione sui dati/tipi**: definire un ADT con operazioni e proprietà,
   nascondendo la rappresentazione.
4. **Astrazione per iterazione**: attraversare una struttura tramite
   `Iterator`, senza conoscere come è memorizzata.

### Programmazione difensiva, masking e reflection

- **Programmazione difensiva**: controllare gli input e mantenere gli
  invarianti, invece di assumere che tutto il client sia corretto.
- **Exception masking**: un errore originale viene nascosto da un'altra
  eccezione, spesso lanciata dentro `finally`; è un comportamento da evitare.
  `try-with-resources` gestisce meglio anche le eccezioni di chiusura.
- **Reflection**: accesso a classi, metodi e campi a runtime. È utile a
  framework e strumenti, ma bypassa parte dei controlli statici e aumenta
  accoppiamento e fragilità.

### Factory method, copia e `equals`

Un **factory method** è spesso `static` e può avere un nome descrittivo,
restituire un sottotipo, riutilizzare un'istanza o nascondere il costruttore.
Non è obbligato a creare un oggetto nuovo a ogni chiamata.

`equals` deve rispettare:

- **riflessività**: `x.equals(x)` è vero;
- **simmetria**: `x.equals(y)` coincide con `y.equals(x)`;
- **transitività**: se `x == y` logico e `y == z`, allora `x == z` logico;
- **consistenza**: stesso risultato se lo stato osservato non cambia;
- **non-null**: `x.equals(null)` è falso.

`hashCode` deve essere coerente con `equals`: oggetti uguali devono avere lo
stesso hash. Il contrario non è richiesto. `toString` serve per una
rappresentazione leggibile e diagnostica, non per definire l'uguaglianza.

`clone()` usa normalmente una copia superficiale campo per campo. `Cloneable`
è una **marker interface** (non definisce metodi) e il suo design è
problematico; per tipi mutabili sono generalmente più chiari copy constructor
o factory method. Una copia profonda deve duplicare anche la rappresentazione
mutabile interna.

### Verifica della rappresentazione

`repOk()` è un metodo di supporto, spesso privato o usato nei test, che
controlla l'**RI**. La correttezza di un ADT si verifica a tre livelli:

1. ogni creator costruisce una rappresentazione che soddisfa RI;
2. ogni mutator/producer conserva RI e rispetta la specifica;
3. ogni observer restituisce il risultato descritto da AF e dalla specifica.

AF e RI non sono la stessa cosa: AF spiega il significato della
rappresentazione, RI stabilisce quali rappresentazioni sono ammissibili.

### Regole più precise del LSP

Nella **signature rule**, il sottotipo deve offrire una firma compatibile:
parametri coerenti, risultato compatibile e visibilità non più restrittiva.
Nella **methods rule**, non deve rafforzare le precondizioni né indebolire le
postcondizioni. Nella **properties rule**, deve conservare le proprietà
osservabili e gli invarianti del supertipo.

L'uguaglianza nelle gerarchie è delicata: una classe derivata che aggiunge
campi nel confronto può rompere la simmetria (`Base.equals(Child)` diverso da
`Child.equals(Base)`). Bisogna scegliere consapevolmente tra uguaglianza
basata sulla classe esatta e uguaglianza polimorfica, mantenendo sempre il
contratto di `equals`.

### Iterator come generatore o filtro

Un iteratore non serve solo a leggere un array. Può:

- **generare** valori calcolandoli progressivamente, anche senza conservarli
  tutti in memoria;
- **filtrare** una sorgente e rendere visibili solo gli elementi che
  soddisfano un predicato;
- nascondere una struttura complessa o attraversare solo una sua parte.

La specifica di `next()` deve chiarire l'ordine degli elementi, quando
`hasNext()` diventa falso e quale eccezione viene lanciata alla fine.
L'iteratore dovrebbe restare coerente con la collezione; modifiche strutturali
esterne possono invalidarlo.

### Delegation pattern

Con la **delegation** un oggetto inoltra un'operazione a un altro oggetto che
possiede la competenza necessaria:

```java
class Registro implements Iterable<String> {
    private final List<String> dati = new ArrayList<>();

    @Override
    public Iterator<String> iterator() {
        return dati.iterator();
    }
}
```

Il delegante espone il contratto desiderato senza ereditare tutta
l'implementazione del delegato. È una forma di composizione e riduce il
coupling rispetto a un'estensione impropria.

### Funzioni: composizione e currying

La **composizione** combina funzioni: l'uscita di una diventa l'ingresso
dell'altra. Con `Function`, `andThen` applica prima la funzione corrente,
mentre `compose` applica prima quella passata.

Il **currying** trasforma una funzione con più argomenti in una catena di
funzioni a un argomento:

```java
// (a, b) -> a + b diventa a -> (b -> a + b)
Function<Integer, Function<Integer, Integer>> somma =
    a -> b -> a + b;
```

È utile per fissare un parametro e ottenere una funzione specializzata.

### Pattern da riconoscere

- **Singleton**: una sola istanza; il costruttore è nascosto e l'accesso è
  centralizzato. Può introdurre stato globale e difficoltà nei test.
- **Decorator**: oggetto wrapper che implementa la stessa interfaccia e
  aggiunge comportamento prima o dopo la delega.
- **Observer**: un subject mantiene osservatori e li notifica; occorre
  gestire registrazione, rimozione e ordine delle notifiche.
- **Strategy**: algoritmo incapsulato in un'interfaccia, sostituibile senza
  cambiare il client.
- **MVC**: separazione architetturale tra dati/regole (Model), presentazione
  (View) e coordinamento degli input (Controller).
