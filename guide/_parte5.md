---
# PARTE 5 -- Astrazione e Specifiche
---

Questa parte tratta il nucleo teorico del corso di Programmazione 2: i concetti di astrazione, specifica formale dei metodi, tipi di dato astratti (ADT), funzione di astrazione (AF), invariante di rappresentazione (RI) e rep exposure. Si tratta di argomenti che il docente valuta esplicitamente sia nel filtro teoria sia nella prova pratica. AF e RI valgono **0.5 punti ciascuno per classe** nella scala di valutazione dell'esame: dimenticarli equivale a perdere punti certi.

---

## 1. Astrazione per specifica

### Definizione

L'**astrazione per specifica** e' il principio secondo cui il chiamante di un metodo utilizza quel metodo **esclusivamente sulla base della sua specifica** (cioe' della documentazione formale), senza conoscere ne' dipendere dall'implementazione interna.

In altre parole, la specifica costituisce un **contratto** tra chi implementa il metodo (l'implementatore) e chi lo usa (il chiamante):

- L'**implementatore** si impegna a garantire le postcondizioni (EFFECTS), a patto che le precondizioni (REQUIRES) siano soddisfatte.
- Il **chiamante** si impegna a soddisfare le precondizioni e puo' fare affidamento sulle postcondizioni.

Questo principio ha implicazioni fondamentali:

1. **Indipendenza dall'implementazione**: il chiamante non deve leggere il codice del metodo per usarlo.
2. **Sostituibilita'**: l'implementazione puo' cambiare senza invalidare il codice chiamante, purche' la specifica rimanga la stessa.
3. **Modularita'**: ogni modulo puo' essere sviluppato, testato e ragionato indipendentemente.

```java
// Il chiamante usa versa() basandosi SOLO sulla specifica,
// senza sapere come e' implementato il trasferimento del liquido.

/**
 * REQUIRES: -
 * MODIFIES: this, sorgente
 * EFFECTS: versa il liquido da sorgente in this.
 *          Se i liquidi sono incompatibili lancia LiquidsException (checked).
 *          Se this ha capienza sufficiente, tutto il liquido viene trasferito.
 *          Altrimenti this viene riempito fino alla capienza e il resto rimane in sorgente.
 */
public void versa(Contenitore sorgente) throws LiquidsException {
    // L'implementazione e' IRRILEVANTE per il chiamante.
    // Il chiamante sa solo che:
    //   - puo' passare qualsiasi Contenitore (nessun REQUIRES restrittivo)
    //   - this e sorgente verranno modificati
    //   - se i liquidi sono diversi, viene lanciata LiquidsException
    //   - altrimenti il liquido viene trasferito (totalmente o parzialmente)
}
```

**Conseguenza pratica per l'esame:** quando scrivi un metodo, la specifica DEVE essere completa: chi legge solo la specifica deve poter usare il metodo correttamente senza guardare il corpo.

---

## 2. Le clausole REQUIRES / MODIFIES / EFFECTS

### Formato standard

Ogni metodo pubblico viene documentato con tre clausole:

```
REQUIRES: precondizioni (cosa deve essere vero PRIMA della chiamata)
MODIFIES: quali oggetti vengono modificati (this, parametri, System.out, ...)
EFFECTS:  postcondizioni (cosa fa il metodo SE il REQUIRES e' soddisfatto)
```

### 2.1 REQUIRES -- Precondizioni

La clausola `REQUIRES` specifica le condizioni che devono essere vere **prima** della chiamata al metodo. Se il chiamante viola il REQUIRES, il comportamento del metodo e' **indefinito** (puo' accadere qualsiasi cosa).

```java
/**
 * REQUIRES: n >= 0
 * EFFECTS: restituisce il fattoriale di n
 */
public static long fattoriale(int n) {
    // Se il chiamante passa n = -3, il comportamento e' indefinito:
    // potrebbe andare in ricorsione infinita, restituire un valore sbagliato,
    // o lanciare un'eccezione. Non c'e' alcuna garanzia.
    if (n == 0) return 1;
    return n * fattoriale(n - 1);
}
```

Quando REQUIRES e' assente o vale `-` (trattino), significa che **non ci sono precondizioni**: il metodo funziona per qualsiasi input valido per i tipi dei parametri.

```java
/**
 * REQUIRES: -
 * MODIFIES: this
 * EFFECTS: aggiunge c a this.
 *          Se c e' null lancia NullPointerException (unchecked).
 */
public void aggiungi(Contenitore c) {
    // Nessun REQUIRES: il metodo gestisce internamente tutti i casi,
    // incluso c == null (lanciando un'eccezione).
    if (c == null) throw new NullPointerException();
    contenitori.add(c);
}
```

### 2.2 MODIFIES -- Oggetti modificati

La clausola `MODIFIES` elenca **tutti** gli oggetti il cui stato viene alterato durante l'esecuzione del metodo. Gli oggetti tipici sono:

| Oggetto      | Significato                                     |
| ------------ | ----------------------------------------------- |
| `this`       | L'oggetto su cui viene invocato il metodo       |
| un parametro | Un parametro passato al metodo (es. `sorgente`) |
| `System.out` | Il metodo produce output su console             |

Se il metodo e' un **osservatore puro** (non modifica nulla), la clausola MODIFIES e' assente oppure vale `-`.

```java
/**
 * REQUIRES: -
 * MODIFIES: -
 * EFFECTS: restituisce il volume della sfera calcolato come (4/3) * pi * r^3
 */
public double calcolaVolume() {
    // Nessun MODIFIES: il metodo non altera nulla, restituisce solo un valore.
    return Math.PI * Math.pow(raggio, 3) * 4.0 / 3.0;
}

/**
 * REQUIRES: -
 * MODIFIES: this, sorgente
 * EFFECTS: versa il liquido da sorgente in this.
 *          Se i liquidi sono incompatibili lancia LiquidsException.
 */
public void versa(Contenitore sorgente) throws LiquidsException {
    // MODIFIES elenca sia this sia sorgente perche' entrambi cambiano:
    // this riceve liquido, sorgente lo perde.
    // ...
}
```

### 2.3 EFFECTS -- Postcondizioni

La clausola `EFFECTS` descrive cosa fa il metodo **quando il REQUIRES e' soddisfatto**. Include:

- Cosa viene restituito (se non void)
- Come cambia lo stato degli oggetti elencati in MODIFIES
- Quali eccezioni vengono lanciate e in quali condizioni
- Se viene lanciata un'eccezione, cosa succede allo stato (viene modificato oppure no?)

```java
/**
 * MODIFIES: this
 * EFFECTS: inserisce la sorpresa s nel giorno specificato.
 *          Se il giorno e' gia' occupato, lancia GiornoException (checked)
 *          e this non viene modificato.
 *          Se una sorpresa equals a s e' gia' presente nel calendario,
 *          lancia SorpresaException (unchecked) e this non viene modificato.
 */
public void inserisci(int giorno, Sorpresa s) throws GiornoException {
    // La specifica EFFECTS descrive COMPLETAMENTE il comportamento:
    // 1. Caso normale: la sorpresa viene inserita nella casella del giorno
    // 2. Caso eccezionale 1: giorno occupato -> GiornoException
    // 3. Caso eccezionale 2: sorpresa duplicata -> SorpresaException
    // 4. In entrambi i casi eccezionali, this non cambia (nessuna modifica parziale)

    if (giorno < 1 || giorno > 31)
        throw new IllegalArgumentException("Giorno non valido");

    for (Sorpresa esistente : caselle) {
        if (esistente != null && esistente.equals(s))
            throw new SorpresaException("Sorpresa gia' presente");
    }

    if (caselle[giorno - 1] != null)
        throw new GiornoException("Giorno gia' occupato");

    caselle[giorno - 1] = s;
}
```

### Riepilogo visivo

```
┌─────────────────────────────────────────────────────────┐
│  SPECIFICA DI UN METODO                                 │
│                                                         │
│  REQUIRES: cosa deve essere vero PRIMA della chiamata   │
│            (vincoli sul chiamante)                       │
│                                                         │
│  MODIFIES: quali oggetti cambiano stato                 │
│            (vincolo di trasparenza)                      │
│                                                         │
│  EFFECTS:  cosa succede DOPO la chiamata                │
│            SE il REQUIRES e' soddisfatto                │
│            (vincoli sull'implementatore)                 │
└─────────────────────────────────────────────────────────┘
```

---

## 3. Metodi totali vs metodi parziali

### Definizione

Un metodo si dice **totale** se e' definito per **ogni** input ammesso dai tipi dei parametri. In pratica, un metodo totale non ha un REQUIRES restrittivo: gestisce tutti i casi, eventualmente lanciando eccezioni per gli input non validi.

Un metodo si dice **parziale** se ha precondizioni nel REQUIRES che restringono il dominio degli input. Se il chiamante viola queste precondizioni, il comportamento e' **indefinito**.

| Caratteristica         | Metodo Totale                             | Metodo Parziale                              |
| ---------------------- | ----------------------------------------- | -------------------------------------------- |
| REQUIRES               | Assente o `-`                             | Presente con condizioni restrittive          |
| Input non valido       | Lancia eccezione (comportamento definito) | Comportamento indefinito                     |
| Responsabilita' errori | L'implementatore gestisce tutto           | Il chiamante deve garantire le precondizioni |
| Robustezza             | Alta                                      | Bassa                                        |
| Nei metodi d'esame     | Quasi sempre usato                        | Raro                                         |

### Esempio di metodo parziale

```java
/**
 * REQUIRES: divisore != 0
 * EFFECTS: restituisce dividendo / divisore
 */
public static double dividi(double dividendo, double divisore) {
    // Se il chiamante passa divisore = 0, il risultato e' indefinito.
    // Il metodo NON controlla: si fida che il chiamante rispetti il REQUIRES.
    return dividendo / divisore;
}
```

### Esempio di metodo totale equivalente

```java
/**
 * REQUIRES: -
 * EFFECTS: restituisce dividendo / divisore.
 *          Se divisore == 0 lancia ArithmeticException (unchecked).
 */
public static double dividi(double dividendo, double divisore) {
    // Il metodo gestisce TUTTI i casi, incluso divisore == 0.
    // Non si fida del chiamante: controlla e lancia un'eccezione.
    if (divisore == 0) {
        throw new ArithmeticException("Divisione per zero");
    }
    return dividendo / divisore;
}
```

### Come rendere un metodo parziale totale

La trasformazione avviene in tre passi:

1. **Identificare** le precondizioni nel REQUIRES
2. **Aggiungere un controllo** nel corpo del metodo per ciascuna precondizione
3. **Lanciare un'eccezione appropriata** quando la precondizione e' violata
4. **Documentare l'eccezione** nella clausola EFFECTS
5. **Rimuovere** (o svuotare) la clausola REQUIRES

```java
// ===== PRIMA: metodo parziale =====
/**
 * REQUIRES: index >= 0 && index < size()
 * EFFECTS: restituisce l'elemento in posizione index
 */
public T get(int index) {
    return elementi[index];    // se index e' fuori range: ArrayIndexOutOfBoundsException
                               // (ma la specifica non lo definisce -> comportamento indefinito)
}

// ===== DOPO: metodo totale =====
/**
 * REQUIRES: -
 * EFFECTS: restituisce l'elemento in posizione index.
 *          Se index < 0 o index >= size(), lancia IndexOutOfBoundsException.
 */
public T get(int index) {
    if (index < 0 || index >= size()) {
        throw new IndexOutOfBoundsException("Indice " + index + " fuori range [0, " + size() + ")");
    }
    return elementi[index];
}
```

### Esempio dal corso: costruttore della Sfera

```java
// Il costruttore della Sfera e' TOTALE: gestisce tutti gli input,
// lanciando eccezioni per quelli non validi.

public class Sfera extends Contenitore {
    private final double raggio;

    /**
     * REQUIRES: -
     * EFFECTS: crea una sfera di raggio r con liquido e quantita' specificati.
     *          Se r <= 0 lancia IllegalArgumentException.
     *          Se quantita' > volume lancia CapacityException (unchecked).
     */
    public Sfera(double raggio, String liquido, double quantita) {
        // Controllo 1: raggio deve essere positivo
        if (raggio <= 0) {
            throw new IllegalArgumentException("Raggio deve essere positivo");
        }
        this.raggio = raggio;

        // Controllo 2: la quantita' non puo' superare il volume
        if (quantita > calcolaVolume()) {
            throw new CapacityException("La sfera ha capienza: "
                + calcolaVolume() + " ma il liquido ha un volume di: " + quantita);
        }
        // ...
    }
}
```

**Regola per l'esame:** all'esame quasi tutti i metodi sono **totali**. Si preferisce sempre controllare gli input e lanciare eccezioni piuttosto che delegare la responsabilita' al chiamante con un REQUIRES.

---

## 4. Abstract Data Type (ADT)

### Definizione

Un **Abstract Data Type** (Tipo di Dato Astratto) e' un tipo definito **dalle sue operazioni**, non dalla sua rappresentazione interna. L'utente dell'ADT conosce solo **cosa** puo' fare con quel tipo (attraverso le specifiche dei metodi), ma non **come** il tipo e' implementato internamente.

Questo principio e' alla base dell'information hiding: i campi sono `private`, e l'unico modo per l'esterno di interagire con l'oggetto e' attraverso i metodi pubblici.

```java
// L'utente di Vetreria sa che:
//   - puo' aggiungere Contenitori
//   - puo' estrarre Contenitori per tipo di liquido
//   - puo' iterare sui Contenitori in ordine di capienza
// Ma NON sa se internamente usa un ArrayList, un LinkedList, un array,
// un TreeSet, o qualsiasi altra struttura dati.

public class Vetreria implements Iterable<Contenitore> {
    // La rappresentazione interna e' NASCOSTA: l'utente non la vede.
    private final List<Contenitore> contenitori;

    // L'utente interagisce SOLO tramite le operazioni pubbliche.
    public void aggiungi(Contenitore c) { /* ... */ }
    public Vetreria estrai(String liquido) { /* ... */ }
    public Iterator<Contenitore> iterator() { /* ... */ }
}
```

### Categorie di operazioni di un ADT

Le operazioni di un ADT si classificano in quattro categorie:

| Categoria       | Descrizione                               | Input          | Output             | Esempio dal corso                      |
| --------------- | ----------------------------------------- | -------------- | ------------------ | -------------------------------------- |
| **Creatore**    | Crea nuove istanze da zero                | Argomenti      | Nuova istanza      | `new Sfera(5.0)`, `new Vetreria()`     |
| **Produttore**  | Crea nuove istanze da istanze esistenti   | Istanza + args | Nuova istanza      | `clone()`, `estrai("acqua")`           |
| **Osservatore** | Restituisce informazioni senza modificare | Istanza        | Valore             | `calcolaVolume()`, `getLiquido()`      |
| **Mutatore**    | Modifica lo stato dell'istanza            | Istanza + args | void (tipicamente) | `versa()`, `aggiungi()`, `inserisci()` |

```java
public abstract class Contenitore implements Comparable<Contenitore>, Cloneable {

    // ===== CREATORE: costruisce una nuova istanza da zero =====
    protected Contenitore(String liquido, double quantita) {
        // Crea un contenitore con il liquido e la quantita' specificati
        this.liquido = liquido;
        this.quantita = quantita;
    }

    // ===== PRODUTTORE: crea una nuova istanza da un'istanza esistente =====
    @Override
    public Contenitore clone() {
        // Restituisce una NUOVA istanza che e' una copia di this
        try {
            return (Contenitore) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    // ===== OSSERVATORE: restituisce informazioni senza modificare =====
    public abstract double calcolaVolume();   // non modifica nulla
    public String getLiquido() { return liquido; }
    public double getQuantita() { return quantita; }

    // ===== MUTATORE: modifica lo stato dell'istanza =====
    public void versa(Contenitore sorgente) throws LiquidsException {
        // Modifica sia this (riceve liquido) sia sorgente (perde liquido)
        // ...
    }
}
```

### ADT immutabile vs ADT mutabile

| Aspetto       | ADT Immutabile                        | ADT Mutabile                               |
| ------------- | ------------------------------------- | ------------------------------------------ |
| Definizione   | Lo stato non cambia dopo la creazione | Lo stato puo' cambiare dopo la creazione   |
| Mutatori      | **Nessuno**                           | Presenti                                   |
| Produttori    | Restituiscono **nuove** istanze       | Possono modificare this o restituire nuove |
| Thread safety | Intrinsecamente thread-safe           | Richiede sincronizzazione                  |
| Esempio JDK   | `String`, `Integer`, `LocalDate`      | `ArrayList`, `StringBuilder`, `HashMap`    |
| Esempio esame | `Sorpresa` (nome + costo fissi)       | `Contenitore` (liquido cambia con `versa`) |

```java
// IMMUTABILE: una volta creata, la Sorpresa non cambia mai stato.
// Non ha mutatori. Il nome e il costo sono fissati alla costruzione.
public abstract class Sorpresa implements Comparable<Sorpresa> {
    private final String nome;    // final: non riassegnabile

    protected Sorpresa(String nome) {
        this.nome = nome;         // stato fissato qui, mai piu' modificato
    }

    // Solo OSSERVATORI, nessun MUTATORE:
    public String getNome() { return nome; }
    public abstract double getCosto();
}
```

```java
// MUTABILE: il Contenitore cambia stato dopo la creazione.
// Il metodo versa() e' un MUTATORE che altera liquido e quantita'.
public abstract class Contenitore implements Comparable<Contenitore> {
    // OVERVIEW: Un Contenitore e' un recipiente MUTABILE che puo' contenere
    //           un liquido fino alla sua capienza massima.

    private String liquido;       // puo' cambiare (da "acqua" a null, ecc.)
    private double quantita;      // puo' cambiare con versa()

    public void versa(Contenitore sorgente) throws LiquidsException {
        // MUTATORE: modifica this.quantita, this.liquido,
        //           sorgente.quantita, sorgente.liquido
        // ...
    }
}
```

**Nota per l'OVERVIEW:** quando si documenta una classe, e' fondamentale specificare se il tipo e' **mutabile o immutabile**. Questo influenza il ragionamento del chiamante sulla condivisione dei riferimenti.

---

## 5. Funzione di Astrazione AF(this)

### Definizione formale

La **Funzione di Astrazione** (AF) e' una mappatura che associa ogni stato concreto (i valori dei campi privati) al **valore astratto** che l'utente percepisce.

```
AF: spazio di rappresentazione --> spazio astratto
```

In termini piu' semplici: i campi privati di un oggetto sono la "rappresentazione concreta". L'AF spiega **quale significato** hanno quei campi dal punto di vista dell'utente.

Proprieta' della AF:

- **Suriettiva**: ogni valore astratto ha almeno una rappresentazione concreta che lo produce.
- **Non necessariamente iniettiva**: stati concreti diversi possono corrispondere allo stesso valore astratto.

```
Esempio: un Set<Integer> implementato con ArrayList<Integer>

Stato concreto [1, 2, 3]  --AF-->  {1, 2, 3}
Stato concreto [3, 1, 2]  --AF-->  {1, 2, 3}
Stato concreto [2, 3, 1]  --AF-->  {1, 2, 3}

Tre stati concreti diversi, ma lo stesso valore astratto {1, 2, 3}.
La AF NON e' iniettiva: piu' rappresentazioni -> stesso significato.
```

### Come scrivere una buona AF

La AF deve:

1. Essere scritta come commento nella classe, subito dopo i campi privati
2. Usare il formato `AF(this): ...`
3. Riferirsi esplicitamente ai campi provati e spiegare il loro significato
4. Coprire tutti i casi significativi (es. quando un campo e' `null`)

```java
public abstract class Contenitore implements Comparable<Contenitore>, Cloneable {
    // OVERVIEW: Un Contenitore e' un recipiente MUTABILE che puo' contenere
    //           un liquido fino alla sua capienza massima (volume).

    private String liquido;       // il tipo di liquido contenuto (null se vuoto)
    private double quantita;      // la quantita' di liquido attualmente presente

    // AF(this): un contenitore che contiene 'quantita' unita' di 'liquido'.
    //           Se liquido == null, il contenitore e' vuoto.

    // La AF spiega il SIGNIFICATO dei campi:
    //   - liquido = quale liquido c'e' dentro (null = vuoto)
    //   - quantita = quanto liquido c'e'
    // L'utente non sa che esiste un campo String e un campo double:
    // sa solo che c'e' un contenitore con un certo liquido e una certa quantita'.
}
```

### Esempi concreti dal corso

#### AF nella sottoclasse (Sfera)

```java
public class Sfera extends Contenitore {
    private final double raggio;

    // AF(this): una sfera di raggio 'raggio'
    //
    // Nota: la AF della sottoclasse si riferisce solo ai campi PROPRI.
    // Il significato dei campi ereditati (liquido, quantita) e' gia' descritto
    // nella AF della superclasse Contenitore.
}
```

#### AF nella sottoclasse (Cilindro)

```java
public class Cilindro extends Contenitore {
    private final double altezza;
    private final double raggio;

    // AF(this): un cilindro di altezza 'altezza' e raggio 'raggio'
}
```

#### AF nella collezione (Vetreria)

```java
public class Vetreria implements Iterable<Contenitore> {
    private final List<Contenitore> contenitori;

    // AF(this): una vetreria contenente i contenitori in this.contenitori
    //
    // Nota: la AF mappa la lista interna al concetto astratto di "vetreria".
    // L'utente percepisce un "insieme di contenitori", non un ArrayList.
}
```

#### AF con array (Calendario)

```java
public class Calendario implements Iterable<Sorpresa> {
    private final Sorpresa[] caselle = new Sorpresa[31];

    // AF(this): un calendario dove il giorno i+1 contiene caselle[i]
    //
    // Nota: l'indice dell'array parte da 0, ma i giorni partono da 1.
    // La AF chiarisce questa corrispondenza: giorno 1 = caselle[0],
    // giorno 2 = caselle[1], ..., giorno 31 = caselle[30].
    // Se caselle[i] == null, il giorno i+1 e' vuoto.
}
```

#### AF con vincoli di dominio (Olimpiade)

```java
public class Olimpiade implements Iterable<Evento> {
    private final int anno;
    private final Evento[] eventi = new Evento[19];

    // AF(this): un'olimpiade dell'anno 'anno' dove il giorno i+1 ha l'evento eventi[i]
    //
    // L'anno qualifica l'olimpiade. L'array eventi ha significato analogo
    // al Calendario: giorno 1 = eventi[0], ..., giorno 19 = eventi[18].
}
```

#### AF nella classe astratta con ereditarieta' (Sorpresa)

```java
public abstract class Sorpresa implements Comparable<Sorpresa> {
    private final String nome;

    // AF(this): una sorpresa di nome 'nome' con costo getCosto()
    //
    // Il costo e' calcolato dal metodo astratto getCosto(), la cui
    // implementazione dipende dalla sottoclasse concreta.
    // getCosto() nella AF e' lecito perche' e' un osservatore.
}
```

```java
public class Cioccolatino extends Sorpresa {
    private final int percentualeCacao;

    // AF(this): un cioccolatino di nome getNome() con cacao al percentualeCacao%
}
```

```java
public class Giocattolo extends Sorpresa {
    private final String descrizione;

    // AF(this): un giocattolo di nome getNome() con descrizione 'descrizione'
}
```

### Errori comuni nella AF

| Errore                                   | Esempio sbagliato                              | Versione corretta                                         |
| ---------------------------------------- | ---------------------------------------------- | --------------------------------------------------------- |
| Descrivere i tipi invece del significato | `AF: liquido e' un String, quantita e' double` | `AF: contiene 'quantita' unita' di 'liquido'`             |
| Dimenticare i casi speciali              | `AF: contiene 'liquido'`                       | `AF: contiene 'liquido'. Se null, e' vuoto.`              |
| Ripetere i campi senza dare significato  | `AF: this.raggio e' il raggio`                 | `AF: una sfera di raggio 'raggio'` (descrive il CONCETTO) |

---

## 6. Invariante di Rappresentazione RI(this)

### Definizione formale

L'**Invariante di Rappresentazione** (RI) e' un predicato booleano che deve essere **vero per OGNI istanza valida della classe in OGNI momento** della sua vita (dopo la costruzione e dopo ogni mutazione).

```
RI: spazio di rappresentazione --> {true, false}
```

L'RI definisce il **dominio della funzione di astrazione**: solo gli stati concreti per cui RI = true hanno un significato astratto valido tramite la AF. Se l'RI e' violato, l'oggetto e' in uno stato **corrotto** e incoerente.

### La preservazione dell'RI

L'RI deve essere preservato in tre modi:

1. **I costruttori devono stabilirlo** (base dell'induzione): al termine del costruttore, l'RI deve essere vero.
2. **I mutatori devono preservarlo** (passo induttivo): se l'RI era vero prima della chiamata, deve essere vero anche dopo.
3. **La rep exposure non deve permetterne la violazione**: se l'esterno ottiene un riferimento ai campi interni, potrebbe modificarli e violare l'RI.

```java
public abstract class Contenitore {
    private String liquido;
    private double quantita;

    // RI(this): quantita >= 0
    //           && quantita <= calcolaVolume()
    //           && (liquido == null) == (quantita == 0)

    // L'RI dice:
    // 1. La quantita' non puo' essere negativa
    // 2. La quantita' non puo' superare il volume del contenitore
    // 3. Se il liquido e' null, la quantita' DEVE essere 0 (e viceversa)
    //    Cioe': un contenitore e' vuoto <=> non ha liquido E ha quantita 0

    // Il COSTRUTTORE stabilisce l'RI:
    protected Contenitore(String liquido, double quantita) {
        if (quantita < 0)
            throw new IllegalArgumentException("Quantita' negativa");
        if (quantita > calcolaVolume())
            throw new CapacityException("Capacita' superata");
        // Dopo questi controlli, l'RI e' soddisfatto:
        //   quantita >= 0 (controllato)
        //   quantita <= calcolaVolume() (controllato)
        this.liquido = (quantita > 0) ? liquido : null;
        this.quantita = quantita;
        // (liquido == null) == (quantita == 0): garantito dall'operatore ternario
    }

    // Il MUTATORE preserva l'RI:
    public void versa(Contenitore sorgente) throws LiquidsException {
        // Prima: RI(this) = true, RI(sorgente) = true
        double spazio = calcolaVolume() - this.quantita;
        double daVersare = Math.min(sorgente.quantita, spazio);
        // daVersare >= 0 (min di due non-negativi)
        // daVersare <= spazio, quindi this.quantita + daVersare <= calcolaVolume()
        if (this.liquido == null) this.liquido = sorgente.liquido;
        this.quantita += daVersare;
        sorgente.quantita -= daVersare;
        if (sorgente.quantita == 0) sorgente.liquido = null;
        // Dopo: RI(this) = true, RI(sorgente) = true
    }
}
```

### Come scrivere un buon RI

L'RI deve:

1. Essere scritto come commento nella classe, subito dopo la AF
2. Usare il formato `RI(this): ...`
3. Essere un predicato booleano: una congiunzione di condizioni che devono essere TUTTE vere
4. Catturare TUTTI i vincoli strutturali sulla rappresentazione

### Esempi concreti dal corso

#### RI nella sottoclasse (Sfera)

```java
public class Sfera extends Contenitore {
    private final double raggio;

    // RI(this): raggio > 0
    //
    // L'RI della sottoclasse si concentra sui campi PROPRI.
    // L'RI della superclasse (quantita >= 0, ecc.) si assume gia'
    // soddisfatto e viene preservato dalla superclasse stessa.
}
```

#### RI nella sottoclasse (Cilindro)

```java
public class Cilindro extends Contenitore {
    private final double altezza;
    private final double raggio;

    // RI(this): altezza > 0 && raggio > 0
    //
    // Entrambe le dimensioni devono essere strettamente positive
    // perche' un cilindro con altezza o raggio zero non ha senso fisico.
}
```

#### RI nella collezione (Vetreria)

```java
public class Vetreria implements Iterable<Contenitore> {
    private final List<Contenitore> contenitori;

    // RI(this): contenitori != null
    //           && per ogni c in contenitori: c != null
    //
    // Due vincoli:
    // 1. La lista stessa non e' null (il costruttore la inizializza)
    // 2. Nessun elemento della lista e' null (il metodo aggiungi lo controlla)
}
```

#### RI con vincoli di unicita' (Calendario)

```java
public class Calendario implements Iterable<Sorpresa> {
    private final Sorpresa[] caselle = new Sorpresa[31];

    // RI(this): caselle != null && caselle.length == 31
    //           && non ci sono due sorprese equals tra loro
    //
    // L'unicita' delle sorprese e' un vincolo di dominio: il calendario
    // non puo' contenere la stessa sorpresa in due giorni diversi.
    // Il metodo inserisci() controlla questo vincolo prima di inserire.
}
```

#### RI con vincoli complessi di dominio (Olimpiade)

```java
public class Olimpiade implements Iterable<Evento> {
    private final int anno;
    private final Evento[] eventi = new Evento[19];

    // RI(this): anno > 0
    //           && eventi != null && eventi.length == 19
    //           && non ci sono due eventi equals tra loro
    //           && se eventi[0] e' Cerimonia, e' di apertura
    //           && se eventi[18] e' Cerimonia, e' di chiusura
    //
    // L'RI cattura TUTTI i vincoli di dominio:
    // - Anno valido
    // - Struttura dell'array corretta
    // - Unicita' degli eventi (un evento non puo' apparire in due giorni)
    // - Vincolo posizionale: la cerimonia di apertura SOLO al giorno 1 (indice 0)
    //   e la cerimonia di chiusura SOLO al giorno 19 (indice 18)
}
```

### Il metodo repOk() -- Verifica dell'RI a runtime

In alcuni corsi viene richiesto un metodo `repOk()` che verifica l'RI programmaticamente. Non e' richiesto all'esame di Prog2, ma il concetto e' utile per il debug:

```java
/**
 * Verifica che l'invariante di rappresentazione sia soddisfatto.
 * Utile per il debug: si puo' chiamare alla fine di ogni costruttore
 * e di ogni mutatore con assert repOk().
 */
private boolean repOk() {
    if (contenitori == null) return false;
    for (Contenitore c : contenitori) {
        if (c == null) return false;
    }
    return true;
}
```

---

## 7. Rep Exposure

### Definizione

La **rep exposure** (esposizione della rappresentazione) si verifica quando il codice esterno ottiene un riferimento diretto alla rappresentazione interna (i campi privati) di un oggetto, potendo cosi' modificarla e **violare l'RI** senza passare attraverso i metodi della classe.

La rep exposure e' uno dei difetti piu' gravi in un ADT perche' rompe completamente l'incapsulamento: la classe perde il controllo sulla propria rappresentazione.

### Le cause della rep exposure

#### Causa 1: Getter che restituisce un riferimento alla struttura interna

```java
public class Vetreria {
    private final List<Contenitore> contenitori = new ArrayList<>();

    // PERICOLOSO: restituisce il riferimento diretto alla lista interna!
    public List<Contenitore> getContenitori() {
        return contenitori;   // l'esterno riceve la lista VERA
    }
}

// L'esterno puo' ora violare l'RI:
Vetreria v = new Vetreria();
v.aggiungi(new Sfera(3));
List<Contenitore> lista = v.getContenitori();

lista.add(null);    // VIOLA l'RI: "per ogni c in contenitori: c != null"
lista.clear();      // Svuota la vetreria senza passare per i metodi della classe
```

**Soluzione: copia difensiva in output**

```java
// SICURO: restituisce una COPIA della lista interna
public List<Contenitore> getContenitori() {
    return new ArrayList<>(contenitori);   // l'esterno riceve una copia
    // Modificare la copia NON modifica l'originale.
}

// Alternativa: lista non modificabile (Java Collections Framework)
public List<Contenitore> getContenitori() {
    return Collections.unmodifiableList(contenitori);
    // L'esterno vede la lista ma non puo' modificarla:
    // ogni tentativo di add/remove/set lancia UnsupportedOperationException.
}

// Alternativa Java 10+: List.copyOf()
public List<Contenitore> getContenitori() {
    return List.copyOf(contenitori);   // copia immutabile
}
```

#### Causa 2: Costruttore che salva il riferimento del parametro

```java
public class Vetreria {
    private final List<Contenitore> contenitori;

    // PERICOLOSO: salva il riferimento diretto al parametro!
    public Vetreria(List<Contenitore> lista) {
        this.contenitori = lista;   // l'esterno ha ancora il riferimento
    }
}

// L'esterno puo' ora violare l'RI:
List<Contenitore> miaLista = new ArrayList<>();
miaLista.add(new Sfera(3));
Vetreria v = new Vetreria(miaLista);

miaLista.add(null);   // VIOLA l'RI della Vetreria! Abbiamo aggiunto null
                       // senza passare per aggiungi(), che fa il controllo.
```

**Soluzione: copia difensiva in input**

```java
// SICURO: crea una COPIA del parametro
public Vetreria(List<Contenitore> lista) {
    this.contenitori = new ArrayList<>(lista);   // copia indipendente
    // Dopo questa riga, modifiche a 'lista' NON influenzano this.contenitori.
}
```

#### Causa 3: Metodo che restituisce un oggetto mutabile interno

```java
public class Calendario {
    private final Sorpresa[] caselle = new Sorpresa[31];

    // PERICOLOSO: restituisce la sorpresa interna senza copia.
    // Se Sorpresa fosse MUTABILE, l'esterno potrebbe modificarla.
    public Sorpresa getSorpresa(int giorno) {
        return caselle[giorno - 1];   // riferimento diretto all'oggetto interno
    }
}
```

Nota: nel corso, `Sorpresa` e' immutabile (campi `final`, nessun mutatore), quindi restituire il riferimento diretto e' sicuro in questo caso specifico. Ma se la classe interna fosse mutabile, sarebbe necessaria una copia (ad esempio con `clone()`).

#### Causa 4: Campi non privati

```java
// PERICOLOSO: il campo e' visibile dall'esterno!
public class Vetreria {
    List<Contenitore> contenitori = new ArrayList<>();   // package-private!
    // Qualsiasi classe nello stesso package puo' accedere a 'contenitori'
}

// PERICOLOSO: il campo e' pubblico!
public class Vetreria {
    public List<Contenitore> contenitori = new ArrayList<>();
    // TUTTO il codice puo' accedere a 'contenitori'
}
```

**Soluzione: dichiarare TUTTI i campi `private`**

```java
// SICURO: il campo e' accessibile solo dalla classe stessa
public class Vetreria {
    private final List<Contenitore> contenitori = new ArrayList<>();
    // Il modificatore 'final' impedisce la riassegnazione del riferimento,
    // ma NON impedisce la modifica del contenuto della lista.
    // Per questo servono ancora le copie difensive nei getter.
}
```

### Riepilogo delle difese contro la rep exposure

| Tecnica                          | Quando usarla                      | Esempio                                       |
| -------------------------------- | ---------------------------------- | --------------------------------------------- |
| Campi `private`                  | **SEMPRE**                         | `private final List<T> lista;`                |
| Campi `final`                    | Sempre che possibile               | `private final double raggio;`                |
| Copia difensiva in input         | Costruttori con parametri mutabili | `this.lista = new ArrayList<>(parametro);`    |
| Copia difensiva in output        | Getter di strutture mutabili       | `return new ArrayList<>(lista);`              |
| `Collections.unmodifiableList()` | Vista read-only senza copia        | `return Collections.unmodifiableList(lista);` |
| `List.copyOf()` (Java 10+)       | Copia immutabile concisa           | `return List.copyOf(lista);`                  |
| Restituire tipi immutabili       | Getter di campi immutabili         | `return nome;` (String e' immutabile)         |
| `clone()` su oggetti mutabili    | Getter/setter di oggetti mutabili  | `return elemento.clone();`                    |

### Nota sull'esame

Nell'esame di Prog2, i campi sono sempre `private` (e spesso `final` nelle sottoclassi). L'iterator restituisce una copia ordinata della collezione interna, il che impedisce la rep exposure sulla struttura principale. Tuttavia, se la collezione contiene oggetti **mutabili** (come `Contenitore`), l'iterator espone i riferimenti a quegli oggetti. Nel contesto dell'esame questo e' generalmente accettato, ma e' bene esserne consapevoli.

---

## 8. OVERVIEW di classe

### Definizione

L'**OVERVIEW** e' un commento posto all'inizio della classe (subito dopo la dichiarazione) che descrive in modo sintetico:

1. **Cosa rappresenta** la classe (il concetto astratto)
2. Se il tipo e' **mutabile o immutabile**
3. Eventuali **vincoli chiave** del tipo

L'OVERVIEW e' la prima cosa che un utente legge per capire cosa fa la classe. E' il livello piu' alto di documentazione, che precede AF e RI.

### Formato standard

```java
public class NomeClasse {
    // OVERVIEW: <descrizione breve del concetto che la classe rappresenta>.
    //           <mutabile o immutabile>.
    //           <vincoli fondamentali, se presenti>.
}
```

### Esempi completi dal corso

```java
public abstract class Contenitore implements Comparable<Contenitore>, Cloneable {
    // OVERVIEW: Un Contenitore e' un recipiente MUTABILE che puo' contenere
    //           un liquido fino alla sua capienza massima (volume).
    //           Due contenitori sono confrontabili per capienza.
}
```

```java
public class Sfera extends Contenitore {
    // OVERVIEW: Contenitore sferico definito dal raggio.
    //           Il volume e' calcolato come (4/3) * pi * raggio^3.
}
```

```java
public class Vetreria implements Iterable<Contenitore> {
    // OVERVIEW: Vetreria e' un insieme MUTABILE di Contenitori.
    //           Permette di aggiungere, estrarre e iterare
    //           i contenitori in ordine crescente di capienza.
}
```

```java
public abstract class Sorpresa implements Comparable<Sorpresa> {
    // OVERVIEW: Una Sorpresa e' definita dal suo nome.
    //           Due sorprese sono uguali se hanno lo stesso nome.
    //           Le sorprese sono confrontabili per costo.
}
```

```java
public class Calendario implements Iterable<Sorpresa> {
    // OVERVIEW: Un calendario di 31 giorni con caselle per sorprese.
    //           Ogni casella puo' contenere al piu' una sorpresa.
    //           Non possono esserci due sorprese equals tra loro.
}
```

```java
public class Olimpiade implements Iterable<Evento> {
    // OVERVIEW: Un'olimpiade invernale con 19 giorni di eventi.
    //           La cerimonia di apertura puo' essere solo al giorno 1,
    //           la cerimonia di chiusura solo al giorno 19.
}
```

### Struttura completa di documentazione di una classe

L'ordine canonico della documentazione all'interno di una classe e':

```java
public class EsempioCompleto implements Iterable<Elemento> {
    // OVERVIEW: <Cosa rappresenta la classe>. <Mutabile/Immutabile>.

    // === Campi privati (rappresentazione interna) ===
    private final List<Elemento> elementi;

    // AF(this): <mappatura dai campi al valore astratto>
    // RI(this): <predicato booleano che deve essere sempre vero>

    // === Costruttore(i) ===
    // === Metodi pubblici con specifica REQUIRES/MODIFIES/EFFECTS ===
    // === equals, hashCode, toString, compareTo, clone ===
    // === iterator() ===
    // === main() di test (se richiesto) ===
}
```

### Importanza per l'esame

L'OVERVIEW vale **0.5 punti per classe** nella scala di valutazione dell'esame. Insieme ad AF e RI, rappresenta punteggio garantito:

| Elemento   | Punti per classe | Classi tipiche | Punti totali tipici |
| ---------- | ---------------- | -------------- | ------------------- |
| OVERVIEW   | 0.5              | 5-6 classi     | 2.5 - 3.0           |
| AF(this)   | 0.5              | 4-5 classi     | 2.0 - 2.5           |
| RI(this)   | 0.5              | 2-3 classi     | 1.0 - 1.5           |
| **Totale** |                  |                | **5.5 - 7.0**       |

Questi sono **punti facili**: richiedono poche righe di codice e non dipendono dalla correttezza dell'implementazione. **Dimentiarli e' un errore inaccettabile.**

### Esempio completo: classe con OVERVIEW + AF + RI + spec

```java
import java.util.*;

public class Vetreria implements Iterable<Contenitore> {
    // OVERVIEW: Vetreria e' un insieme MUTABILE di Contenitori.
    //           Permette di aggiungere, estrarre per tipo di liquido
    //           e iterare i contenitori in ordine crescente di capienza.

    private final List<Contenitore> contenitori;

    // AF(this): una vetreria contenente i contenitori in this.contenitori
    // RI(this): contenitori != null && per ogni c in contenitori: c != null

    /**
     * EFFECTS: crea una vetreria vuota
     */
    public Vetreria() {
        contenitori = new ArrayList<>();
        // RI stabilito: contenitori != null, nessun elemento -> nessun null
    }

    /**
     * MODIFIES: this
     * EFFECTS: aggiunge c a this. Se c e' null lancia NullPointerException.
     */
    public void aggiungi(Contenitore c) {
        if (c == null) throw new NullPointerException();
        contenitori.add(c);
        // RI preservato: c != null e' garantito dal controllo
    }

    /**
     * MODIFIES: this
     * EFFECTS: estrae tutti i contenitori con il liquido specificato,
     *          li rimuove da this e restituisce una nuova Vetreria.
     */
    public Vetreria estrai(String liquido) {
        Vetreria estratta = new Vetreria();
        Iterator<Contenitore> it = contenitori.iterator();
        while (it.hasNext()) {
            Contenitore c = it.next();
            if (liquido.equals(c.getLiquido())) {
                estratta.aggiungi(c);
                it.remove();
            }
        }
        return estratta;
        // RI preservato sia per this sia per estratta
    }

    /**
     * EFFECTS: restituisce un iteratore sui contenitori
     *          in ordine crescente di capienza (ordine naturale).
     */
    @Override
    public Iterator<Contenitore> iterator() {
        List<Contenitore> copia = new ArrayList<>(contenitori);
        Collections.sort(copia);     // ordina la COPIA, non l'originale
        return copia.iterator();     // nessuna rep exposure: copia indipendente
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Vetreria con:\n");
        for (Contenitore c : this) {
            sb.append("\t").append(c).append("\n");
        }
        return sb.toString();
    }
}
```

---

### Riepilogo finale della Parte 5

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                     ASTRAZIONE E SPECIFICHE – CHECKLIST                    │
│                                                                           │
│  Per OGNI CLASSE scrivi:                                                  │
│    [x] OVERVIEW (cos'e', mutabile/immutabile)          → 0.5 punti       │
│    [x] AF(this) (significato dei campi)                → 0.5 punti       │
│    [x] RI(this) (vincoli booleani sui campi)           → 0.5 punti       │
│                                                                           │
│  Per OGNI METODO PUBBLICO scrivi:                                        │
│    [x] REQUIRES (precondizioni, o "-" se totale)                         │
│    [x] MODIFIES (this, parametri, o "-" se osservatore)                  │
│    [x] EFFECTS  (postcondizioni + eccezioni)                             │
│                                                                           │
│  Controlla:                                                               │
│    [x] Nessun campo e' public o package-private                          │
│    [x] I getter restituiscono copie, non riferimenti diretti             │
│    [x] I costruttori copiano i parametri mutabili                        │
│    [x] L'RI e' stabilito nei costruttori e preservato nei mutatori       │
│    [x] Tutti i metodi sono TOTALI (controllano input e lanciano          │
│        eccezioni invece di avere REQUIRES restrittivi)                   │
└─────────────────────────────────────────────────────────────────────────────┘
```
