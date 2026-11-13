---
# PARTE 3 – Eccezioni
---

## 1. Che cos'e' un'eccezione

Un'**eccezione** e' un evento anomalo che interrompe il normale flusso di esecuzione di un programma. In Java, le eccezioni sono **oggetti**: vengono create con `new`, possiedono campi e metodi, e appartengono a una gerarchia di classi ben definita.

Il meccanismo delle eccezioni si fonda su tre operazioni fondamentali:

1. **Lanciare** (`throw`): il codice che rileva l'anomalia crea un oggetto eccezione e lo lancia.
2. **Propagare**: se il metodo corrente non gestisce l'eccezione, questa risale automaticamente la catena delle chiamate (lo **stack di chiamate**) fino a trovare un gestore.
3. **Catturare** (`catch`): un blocco di codice intercetta l'eccezione e la gestisce.

**Perche' usare le eccezioni?** Separano la logica di dominio dalla logica di gestione degli errori. Senza eccezioni, ogni metodo dovrebbe restituire codici di errore e ogni chiamante dovrebbe controllarli — un approccio fragile e poco leggibile.

### Lo stack trace

Quando un'eccezione non viene catturata, la JVM stampa lo **stack trace**: la sequenza di chiamate attive al momento del lancio, dalla piu' interna (dove e' stato eseguito il `throw`) alla piu' esterna (`main`).

```java
public class EsempioStackTrace {

    // metodo che lancia un'eccezione
    public static void metodoC() {
        // simuliamo un errore: accesso a posizione inesistente
        throw new ArrayIndexOutOfBoundsException("indice 10 non valido");
    }

    public static void metodoB() {
        metodoC();  // propaga l'eccezione verso l'alto
    }

    public static void metodoA() {
        metodoB();  // propaga l'eccezione verso l'alto
    }

    public static void main(String[] args) {
        metodoA();  // l'eccezione arriva qui e termina il programma
    }
}
```

**Output dello stack trace:**

```
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: indice 10 non valido
    at EsempioStackTrace.metodoC(EsempioStackTrace.java:5)
    at EsempioStackTrace.metodoB(EsempioStackTrace.java:9)
    at EsempioStackTrace.metodoA(EsempioStackTrace.java:13)
    at EsempioStackTrace.main(EsempioStackTrace.java:17)
```

**Come leggere lo stack trace:**

- La **prima riga** indica il tipo di eccezione e il messaggio.
- Le righe successive (`at ...`) mostrano la catena di chiamate, dalla piu' interna alla piu' esterna.
- Ogni riga indica **classe**, **metodo**, **file** e **numero di riga**.
- Il metodo `getMessage()` restituisce il messaggio passato al costruttore dell'eccezione.
- Il metodo `printStackTrace()` stampa lo stack trace su `System.err`.

---

## 2. Gerarchia delle eccezioni

Tutte le eccezioni in Java discendono dalla classe `Throwable`. La gerarchia ha due rami principali:

```
                        Throwable
                       /         \
                      /           \
                  Error          Exception
                 /    \          /    |    \
                /      \        /     |     \
  OutOfMemoryError  StackOverflow   IO    SQL    RuntimeException
                      Error      Exc.  Exc.     /    |    \     \
                                               /     |     \     \
                                     NullPointer  Illegal   Index  ClassCast
                                       Exc.      Argument  OutOf    Exc.
                                                   Exc.   Bounds
                                                           Exc.
```

**Diagramma dettagliato:**

```
java.lang.Throwable
├── java.lang.Error                          ← NON gestire (errori della JVM)
│   ├── OutOfMemoryError                     ← memoria esaurita
│   ├── StackOverflowError                   ← ricorsione infinita
│   └── VirtualMachineError                  ← errore critico della JVM
│
└── java.lang.Exception                      ← CHECKED (da gestire)
    ├── IOException                          ← errore di I/O
    ├── SQLException                         ← errore database
    ├── FileNotFoundException                ← file non trovato
    ├── ClassNotFoundException               ← classe non trovata
    ├── ... (tutte le sottoclassi dirette sono CHECKED)
    │
    └── java.lang.RuntimeException           ← UNCHECKED (errori logici)
        ├── NullPointerException             ← riferimento null
        ├── IllegalArgumentException         ← argomento non valido
        │   └── NumberFormatException        ← formato numero errato
        ├── IndexOutOfBoundsException        ← indice fuori range
        │   ├── ArrayIndexOutOfBoundsException
        │   └── StringIndexOutOfBoundsException
        ├── ClassCastException               ← cast illegale
        ├── ArithmeticException              ← es. divisione per zero
        │   └── DivisionByZeroException      ← (custom dal lab)
        ├── InputMismatchException           ← (java.util) input errato
        ├── NoSuchElementException           ← iteratore esaurito
        └── UnsupportedOperationException    ← operazione non supportata
```

### Regole della gerarchia

| Ramo                       | Significato                            | Il compilatore obbliga a gestirle? | Azione del programmatore            |
| -------------------------- | -------------------------------------- | ---------------------------------- | ----------------------------------- |
| `Error`                    | Errore grave della JVM, irrecuperabile | No                                 | **Non gestire**, non fare catch     |
| `Exception` (direttamente) | Situazione anomala recuperabile        | **Si** (checked)                   | Dichiarare con `throws` o catturare |
| `RuntimeException`         | Errore logico del programmatore        | No (unchecked)                     | Correggere il codice                |

**Regola fondamentale:** la posizione nella gerarchia determina se un'eccezione e' checked o unchecked. Se estende `Exception` direttamente (senza passare da `RuntimeException`) e' **checked**. Se estende `RuntimeException` e' **unchecked**.

---

## 3. Checked vs Unchecked exceptions

Questa distinzione e' il concetto piu' importante del capitolo e ricorre in ogni esame.

### Checked Exception (extends Exception)

Una **checked exception** rappresenta una situazione anomala **prevedibile e recuperabile**. Il compilatore **obbliga** il programmatore a gestirla: o la cattura con un `try-catch`, o la dichiara nella firma del metodo con `throws`.

```java
// Esempio: verificare la compatibilita' di liquidi e' una situazione
// di DOMINIO — il chiamante puo' e deve decidere cosa fare.
public class LiquidsException extends Exception {
    // extends Exception → CHECKED
    // Il compilatore OBBLIGA chi chiama a gestirla
    public LiquidsException(String msg) {
        super(msg);  // passa il messaggio alla classe Exception
    }
}
```

```java
// Esempio dal corso: GiornoException e' checked perche' un giorno
// gia' occupato e' una situazione di dominio prevedibile
public class GiornoException extends Exception {
    public GiornoException(String msg) {
        super(msg);  // il messaggio descrive il problema specifico
    }
}
```

**Se non gestisci una checked exception, il codice NON compila:**

```java
// ERRORE DI COMPILAZIONE: unhandled exception LiquidsException
public void esempio() {
    contenitore.versa(altro);  // versa() throws LiquidsException
    // Il compilatore si rifiuta di compilare perche'
    // LiquidsException non e' ne' catturata ne' dichiarata
}

// CORRETTO: dichiara con throws
public void esempio() throws LiquidsException {
    contenitore.versa(altro);  // ora la responsabilita' passa al chiamante
}

// CORRETTO: cattura con try-catch
public void esempio() {
    try {
        contenitore.versa(altro);
    } catch (LiquidsException e) {
        System.out.println("Errore: " + e.getMessage());
    }
}
```

### Unchecked Exception (extends RuntimeException)

Una **unchecked exception** rappresenta un **errore logico del programmatore**. Il compilatore **non obbliga** a dichiararla o catturarla. Non serve la clausola `throws` nella firma del metodo (anche se e' possibile aggiungerla per documentazione).

```java
// Esempio: superare la capacita' di un contenitore e' un errore LOGICO
// del chiamante — non avrebbe dovuto farlo
public class CapacityException extends RuntimeException {
    // extends RuntimeException → UNCHECKED
    // Il compilatore NON obbliga a gestirla
    public CapacityException(String msg) {
        super(msg);  // passa il messaggio alla classe RuntimeException
    }
}
```

```java
// Esempio dal corso: SorpresaException e' unchecked perche' inserire
// una sorpresa duplicata e' un errore logico del chiamante
public class SorpresaException extends RuntimeException {
    public SorpresaException(String msg) {
        super(msg);
    }
}

// EventoException segue la stessa logica
public class EventoException extends RuntimeException {
    public EventoException(String msg) {
        super(msg);
    }
}
```

**Le unchecked exception NON richiedono dichiarazione:**

```java
// Compila senza problemi anche senza try-catch e senza throws
public void aggiungi(double quantita) {
    if (quantita > calcolaVolume()) {
        // unchecked: il programmatore ha sbagliato, non serve obbligare
        // il chiamante a gestire questa situazione
        throw new CapacityException("capacita' superata");
    }
    this.quantita += quantita;
}
```

### Tabella comparativa completa

| Aspetto                 | Checked (`extends Exception`)                           | Unchecked (`extends RuntimeException`)                                   |
| ----------------------- | ------------------------------------------------------- | ------------------------------------------------------------------------ |
| **Compilatore**         | OBBLIGA a dichiararle (`throws`) o catturarle (`catch`) | NON obbliga                                                              |
| **Tipo di errore**      | Situazione di dominio prevedibile e recuperabile        | Errore logico del programmatore                                          |
| **Dichiarazione**       | `throws` nella firma OBBLIGATORIO                       | `throws` nella firma FACOLTATIVO                                         |
| **Responsabilita'**     | Del chiamante (decide come gestire)                     | Del programmatore (deve correggere il codice)                            |
| **Esempi dal corso**    | `LiquidsException`, `GiornoException`                   | `CapacityException`, `SorpresaException`, `EventoException`              |
| **Esempi standard JDK** | `IOException`, `SQLException`, `FileNotFoundException`  | `NullPointerException`, `IllegalArgumentException`, `ClassCastException` |

### Quando usare l'una o l'altra

La scelta dipende dalla **semantica dell'errore**:

```java
// CHECKED: il chiamante PUO' e DEVE prendere una decisione
// "i liquidi sono incompatibili" → il chiamante puo' scegliere di
// provare con un altro contenitore, segnalare all'utente, ecc.
public void versa(Contenitore sorgente) throws LiquidsException {
    if (!this.liquido.equals(sorgente.liquido)) {
        throw new LiquidsException("liquidi incompatibili");
    }
}

// UNCHECKED: il chiamante ha violato un vincolo logico
// "la capacita' e' superata" → il chiamante non avrebbe dovuto
// tentare di inserire piu' liquido di quello che il contenitore puo' contenere
public void riempi(double quantita) {
    if (quantita > calcolaVolume() - this.quantita) {
        throw new CapacityException("capacita' superata");
    }
}
```

**Regola pratica per l'esame:** leggi il README con attenzione. Se dice "lancia un'eccezione checked" o "deve essere dichiarata", usa `extends Exception`. Se dice "eccezione unchecked" o "errore logico", usa `extends RuntimeException`.

---

## 4. try-catch-finally

Il costrutto `try-catch-finally` e' il meccanismo principale per catturare e gestire le eccezioni.

### Sintassi base

```java
try {
    // codice che potrebbe lanciare eccezioni
    // se un'eccezione viene lanciata, il flusso salta
    // immediatamente al catch corrispondente
    double risultato = calcolatrice.calculate(10, 0, '/');
    System.out.println("Questo NON viene stampato se c'e' eccezione");
} catch (DivisionByZeroException e) {
    // questo blocco viene eseguito SOLO se viene lanciata
    // un'eccezione di tipo DivisionByZeroException (o sottotipo)
    System.out.println("Errore: " + e.getMessage());
} catch (InputMismatchException e) {
    // gestisce un tipo diverso di eccezione
    System.out.println("Input non valido: " + e.getMessage());
} finally {
    // questo blocco viene SEMPRE eseguito, sia che ci sia stata
    // un'eccezione sia che non ci sia stata
    System.out.println("Operazione terminata");
}
```

### Multi-catch con | (Java 7+)

Quando piu' eccezioni devono essere gestite nello stesso modo, si usa il **multi-catch** con l'operatore `|`:

```java
try {
    calendario.inserisci(giorno, sorpresa);
} catch (GiornoException | SorpresaException e) {
    // gestisce entrambe le eccezioni con lo stesso codice
    // 'e' ha come tipo il tipo comune piu' specifico
    System.out.println("\tECCEZIONE: " + e.getMessage());
}
```

**Vincoli del multi-catch:**

```java
// ERRORE: le eccezioni nel multi-catch NON possono essere
// in relazione di ereditarieta' tra loro
try {
    // ...
} catch (Exception | IOException e) {  // ERRORE DI COMPILAZIONE!
    // IOException e' sottotipo di Exception → ridondante
}

// CORRETTO: basta catturare quella piu' ampia
try {
    // ...
} catch (Exception e) {
    // cattura Exception e tutte le sue sottoclassi
}
```

### Ordine dei catch: dal piu' specifico al piu' generico

I blocchi `catch` vengono valutati **dall'alto verso il basso** e viene eseguito il **primo** che corrisponde al tipo dell'eccezione. Per questo motivo, le eccezioni piu' specifiche devono venire prima.

```java
// CORRETTO: ordine dal piu' specifico al piu' generico
try {
    String s = null;
    s.length();
} catch (NullPointerException e) {
    // piu' specifico: cattura SOLO NullPointerException
    System.out.println("Riferimento null");
} catch (RuntimeException e) {
    // meno specifico: cattura tutte le RuntimeException
    // che NON sono NullPointerException (gia' catturata sopra)
    System.out.println("Errore runtime generico");
} catch (Exception e) {
    // ancora piu' generico: cattura tutte le checked exception
    System.out.println("Eccezione generica");
}
```

```java
// ERRORE DI COMPILAZIONE: ordine sbagliato!
try {
    // ...
} catch (Exception e) {
    // questa cattura TUTTO (comprese le sottoclassi)
} catch (NullPointerException e) {
    // ERRORE: questo catch non sara' mai raggiungibile
    // perche' NullPointerException e' sottotipo di Exception
}
```

### Il blocco finally viene SEMPRE eseguito

Il blocco `finally` e' **garantito** nell'esecuzione, indipendentemente da cosa accade nel `try` o nel `catch`:

```java
public static void esempio() {
    Scanner s = new Scanner(System.in);
    try {
        int n = s.nextInt();
        System.out.println("Numero: " + n);
        // CASO 1: nessuna eccezione → finally eseguito dopo il try
        // CASO 2: eccezione catturata → finally eseguito dopo il catch
        // CASO 3: eccezione NON catturata → finally eseguito,
        //         poi l'eccezione viene propagata
    } catch (InputMismatchException e) {
        System.out.println("Input non valido");
        // ANCHE SE c'e' un return qui, finally viene eseguito!
        return;
    } finally {
        // SEMPRE eseguito: risorse da rilasciare
        s.close();
        System.out.println("Scanner chiuso");
    }
    // questa riga viene raggiunta SOLO se non c'e' stato return nel catch
}
```

**Casi in cui finally viene eseguito:**

| Situazione                      | `finally` eseguito?      |
| ------------------------------- | ------------------------ |
| Nessuna eccezione               | Si                       |
| Eccezione catturata dal `catch` | Si                       |
| Eccezione NON catturata         | Si (poi propaga)         |
| `return` nel `try`              | Si (prima del return)    |
| `return` nel `catch`            | Si (prima del return)    |
| `System.exit()` nel try/catch   | **No** (unica eccezione) |

### Esempio completo dal lab: Calcolatrice

```java
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calcolatrice {

    // il metodo dichiara le eccezioni che puo' lanciare
    // InputMismatchException e' unchecked → il throws e' facoltativo
    // DivisionByZeroException e' unchecked → il throws e' facoltativo
    // qui vengono dichiarate per documentazione
    public static double calculate(double o1, double o2, char op)
            throws InputMismatchException, DivisionByZeroException {
        switch (op) {
            case '+': return o1 + o2;   // operazione valida
            case '-': return o1 - o2;   // operazione valida
            case '*': return o1 * o2;   // operazione valida
            case '/':
                // controllo esplicito PRIMA di eseguire la divisione
                if (o2 == 0)
                    throw new DivisionByZeroException("Non possibile dividere per 0.");
                return o1 / o2;
            default:
                // operatore non riconosciuto → errore di input
                throw new InputMismatchException("Operatore non riconosciuto");
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        try {
            // lettura degli operandi e dell'operatore dallo scanner
            double n1 = s.nextDouble();   // puo' lanciare InputMismatchException
            String op = s.next();
            double n2 = s.nextDouble();   // puo' lanciare InputMismatchException

            // calcolo del risultato (puo' lanciare le eccezioni dichiarate)
            double ris = calculate(n1, n2, op.charAt(0));
            System.out.println("Risultato: " + ris);

        } catch (InputMismatchException e) {
            // gestisce sia l'input non numerico sia l'operatore non valido
            System.out.println("Operando non valido");
        } catch (DivisionByZeroException e) {
            // gestisce la divisione per zero
            System.out.println(e.getMessage());
        } finally {
            // chiude lo scanner in OGNI caso
            s.close();
        }
    }
}
```

---

## 5. try-with-resources

### Il problema: dimenticare di chiudere le risorse

Prima di Java 7, il programmatore doveva chiudere manualmente le risorse nel blocco `finally`. Questo approccio era verboso e soggetto a errori:

```java
// Approccio vecchio: verbose e fragile
Scanner scanner = null;
try {
    scanner = new Scanner(new File("dati.txt"));
    while (scanner.hasNextLine()) {
        System.out.println(scanner.nextLine());
    }
} catch (FileNotFoundException e) {
    System.out.println("File non trovato");
} finally {
    // PROBLEMA: se scanner e' null (es. file non trovato),
    // scanner.close() lancia NullPointerException!
    if (scanner != null) {
        scanner.close();
    }
}
```

### La soluzione: try-with-resources (Java 7+)

Il costrutto `try-with-resources` chiude automaticamente le risorse alla fine del blocco `try`. La risorsa deve implementare l'interfaccia `AutoCloseable`.

```java
// L'interfaccia AutoCloseable ha un solo metodo:
public interface AutoCloseable {
    void close() throws Exception;
    // questo metodo viene chiamato automaticamente
    // alla fine del blocco try
}
```

**Sintassi:**

```java
// La risorsa viene dichiarata nelle parentesi tonde dopo 'try'
// e viene chiusa AUTOMATICAMENTE alla fine del blocco
try (Scanner scanner = new Scanner(new File("dati.txt"))) {
    // usa la risorsa normalmente
    while (scanner.hasNextLine()) {
        System.out.println(scanner.nextLine());
    }
    // scanner.close() viene chiamato AUTOMATICAMENTE qui
    // anche se viene lanciata un'eccezione
} catch (FileNotFoundException e) {
    System.out.println("File non trovato");
}
// non serve finally per chiudere la risorsa!
```

### Risorse multiple

Si possono dichiarare piu' risorse, separate da `;`. Vengono chiuse in **ordine inverso** rispetto alla dichiarazione:

```java
try (
    FileReader fr = new FileReader("input.txt");   // aperta per prima
    BufferedReader br = new BufferedReader(fr)      // aperta per seconda
) {
    String riga;
    while ((riga = br.readLine()) != null) {
        System.out.println(riga);
    }
    // chiusura automatica: prima br.close(), poi fr.close()
    // (ordine inverso rispetto alla dichiarazione)
}
```

### Vantaggi rispetto a try-catch-finally

| Aspetto              | try-catch-finally                        | try-with-resources                   |
| -------------------- | ---------------------------------------- | ------------------------------------ |
| Chiusura risorsa     | Manuale nel `finally`                    | Automatica                           |
| Rischio dimenticanza | Alto                                     | Nullo                                |
| Codice               | Verboso                                  | Conciso e leggibile                  |
| Eccezioni in close() | Possono mascherare l'eccezione originale | Gestite come _suppressed exceptions_ |
| Null check           | Necessario nel finally                   | Non necessario                       |

### Esempio con Scanner nel corso

```java
// PRIMA: con finally (come nel lab Calcolatrice)
Scanner s = new Scanner(System.in);
try {
    double n1 = s.nextDouble();
    // ...
} catch (InputMismatchException e) {
    System.out.println("Errore");
} finally {
    s.close();  // chiusura manuale
}

// DOPO: con try-with-resources (equivalente, piu' pulito)
try (Scanner s = new Scanner(System.in)) {
    double n1 = s.nextDouble();
    // ...
} catch (InputMismatchException e) {
    System.out.println("Errore");
}
// s.close() viene chiamato automaticamente
```

---

## 6. throws nella firma del metodo

La clausola `throws` nella firma di un metodo **dichiara** che il metodo potrebbe lanciare un'eccezione di quel tipo. Non la lancia direttamente: informa il compilatore e il chiamante che devono essere pronti a gestirla.

### Regola fondamentale

- **Checked exception:** la clausola `throws` e' **OBBLIGATORIA**. Senza di essa il codice non compila.
- **Unchecked exception:** la clausola `throws` e' **FACOLTATIVA**. Si puo' aggiungere per documentazione.

```java
// OBBLIGATORIO: LiquidsException e' checked (extends Exception)
// senza "throws LiquidsException" il codice NON compila
public void versa(Contenitore sorgente) throws LiquidsException {
    if (this.liquido != null && sorgente.liquido != null
        && !this.liquido.equals(sorgente.liquido)) {
        throw new LiquidsException("liquidi incompatibili");
    }
    // logica di trasferimento...
    double spazio = calcolaVolume() - this.quantita;
    double daVersare = Math.min(sorgente.quantita, spazio);
    if (this.liquido == null) this.liquido = sorgente.liquido;
    this.quantita += daVersare;
    sorgente.quantita -= daVersare;
    if (sorgente.quantita == 0) sorgente.liquido = null;
}

// FACOLTATIVO: CapacityException e' unchecked (extends RuntimeException)
// il throws qui e' usato solo per documentazione
public void riempi(double quantita) {
    if (quantita > calcolaVolume()) {
        throw new CapacityException("capacita' superata");
    }
    this.quantita += quantita;
}
```

### Dichiarazioni multiple

Un metodo puo' dichiarare piu' eccezioni, separate da virgola:

```java
// questo metodo puo' lanciare due (o piu') eccezioni diverse
public static void prenota(String[] agenda, int ora, String nome)
        throws NullPointerException, AlreadyBookedException, TimeBusyException {
    // NullPointerException e' unchecked → il throws e' facoltativo
    if (agenda == null) throw new NullPointerException("Agenda non valida!");
    // ParameterException (supponiamo unchecked)
    if (nome == null || nome.equals("")) throw new ParameterException("nome non valido");
    if (ora < 13 || ora > 18) throw new ParameterException("orario non valido");
    // TimeBusyException → dichiarata nel throws
    if (agenda[ora - 13] != null) throw new TimeBusyException("Slot gia' prenotato");
    // AlreadyBookedException → dichiarata nel throws
    if (Arrays.asList(agenda).contains(nome))
        throw new AlreadyBookedException(nome + ", hai gia' prenotato!");
    agenda[ora - 13] = nome;
}
```

### Propagazione delle eccezioni

Quando un metodo dichiara `throws`, la responsabilita' di gestire l'eccezione passa al **chiamante**. Il chiamante ha due opzioni:

1. **Catturare** l'eccezione con `try-catch`
2. **Propagare** l'eccezione aggiungendo `throws` alla propria firma

```java
// Metodo che lancia
public void versa(Contenitore sorgente) throws LiquidsException {
    // ...
    throw new LiquidsException("liquidi incompatibili");
}

// OPZIONE 1: il chiamante cattura
public void eseguiVersamento() {
    try {
        contenitoreA.versa(contenitoreB);
    } catch (LiquidsException e) {
        System.out.println("Errore: " + e.getMessage());
    }
}

// OPZIONE 2: il chiamante propaga (aggiunge throws alla propria firma)
public void eseguiVersamento() throws LiquidsException {
    contenitoreA.versa(contenitoreB);
    // se versa() lancia LiquidsException, questa viene propagata
    // al chiamante di eseguiVersamento()
}
```

**Catena di propagazione tipica nell'esame:**

```
metodo() throws LiquidsException
    ↓ propaga
chiamante() throws LiquidsException
    ↓ propaga
main()  ← qui di solito si cattura con try-catch
```

---

## 7. throw per lanciare un'eccezione

L'istruzione `throw` crea e lancia **un'istanza** di eccezione. E' il meccanismo con cui il codice segnala che si e' verificata una situazione anomala.

### Sintassi

```java
throw new TipoEccezione("messaggio descrittivo");
//    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//    si crea un nuovo OGGETTO eccezione con new
//    e lo si lancia con throw
```

### Differenza tra throw e throws

| Aspetto      | `throw`                                   | `throws`                                     |
| ------------ | ----------------------------------------- | -------------------------------------------- |
| **Cosa e'**  | Un'**istruzione** (statement)             | Una **clausola** nella firma del metodo      |
| **Dove**     | Dentro il corpo del metodo                | Nella dichiarazione del metodo               |
| **Funzione** | **Lancia** un'eccezione concreta          | **Dichiara** che il metodo potrebbe lanciare |
| **Sintassi** | `throw new XException("msg");`            | `void metodo() throws XException { }`        |
| **Oggetto**  | Richiede un **oggetto** eccezione (`new`) | Richiede un **tipo** di eccezione            |

### Esempi dal corso

```java
// THROW: usato per controllare precondizioni e vincoli di dominio

// Controllo nel costruttore (unchecked → errore logico)
protected Contenitore(String liquido, double quantita) {
    if (quantita < 0) {
        // il programmatore non dovrebbe mai passare quantita' negativa
        throw new IllegalArgumentException("quantita' negativa");
    }
    if (quantita > calcolaVolume()) {
        // errore logico: capacita' superata
        throw new CapacityException("troppo liquido per il contenitore");
    }
    this.liquido = liquido;
    this.quantita = quantita;
}

// Controllo in un metodo di dominio (checked → situazione recuperabile)
public void versa(Contenitore sorgente) throws LiquidsException {
    if (this.liquido != null && sorgente.liquido != null
        && !this.liquido.equals(sorgente.liquido)) {
        // situazione di dominio: liquidi diversi → recuperabile
        throw new LiquidsException("liquidi incompatibili");
    }
    // se arriviamo qui, i liquidi sono compatibili...
}

// Controllo nello switch per input non riconosciuto
public static double calculate(double o1, double o2, char op) {
    switch (op) {
        case '+': return o1 + o2;
        case '-': return o1 - o2;
        case '*': return o1 * o2;
        case '/':
            if (o2 == 0) throw new DivisionByZeroException("Non possibile dividere per 0.");
            return o1 / o2;
        default:
            throw new InputMismatchException("Operatore non riconosciuto");
    }
}
```

### Pattern: controlli multipli con throw

Un singolo metodo puo' contenere piu' istruzioni `throw` per diverse condizioni di errore:

```java
// Pattern tipico nell'esame: piu' controlli sequenziali
public void inserisci(int giorno, Sorpresa s) throws GiornoException {
    // 1. Controllo unchecked: parametro null (errore logico)
    if (s == null) {
        throw new IllegalArgumentException("sorpresa null");
    }

    // 2. Controllo unchecked: giorno fuori range (errore logico)
    if (giorno < 1 || giorno > 31) {
        throw new IllegalArgumentException("giorno non valido");
    }

    // 3. Controllo checked: giorno occupato (situazione di dominio)
    if (caselle[giorno - 1] != null) {
        throw new GiornoException("giorno " + giorno + " gia' occupato");
    }

    // 4. Controllo unchecked: sorpresa duplicata (errore logico)
    for (Sorpresa esistente : caselle) {
        if (esistente != null && esistente.equals(s)) {
            throw new SorpresaException("sorpresa gia' presente");
        }
    }

    // se tutti i controlli passano, inserisci
    caselle[giorno - 1] = s;
}
```

---

## 8. Definire eccezioni personalizzate

In ogni esame di Programmazione 2 viene richiesto di definire due eccezioni personalizzate: una **checked** e una **unchecked**. Ciascuna vale circa **1 punto** nella ScalaValutazione.

### Eccezione checked personalizzata (extends Exception)

```java
/**
 * Eccezione CHECKED: rappresenta una situazione di dominio recuperabile.
 * Chi chiama un metodo che lancia questa eccezione e' OBBLIGATO
 * a gestirla (con try-catch o propagandola con throws).
 */
public class LiquidsException extends Exception {

    // Costruttore con messaggio: il pattern piu' comune
    public LiquidsException(String msg) {
        super(msg);  // passa il messaggio a Exception
        // getMessage() restituira' questo messaggio
    }
}
```

### Eccezione unchecked personalizzata (extends RuntimeException)

```java
/**
 * Eccezione UNCHECKED: rappresenta un errore logico del programmatore.
 * Il compilatore NON obbliga a gestirla.
 */
public class CapacityException extends RuntimeException {

    // Costruttore con messaggio
    public CapacityException(String msg) {
        super(msg);  // passa il messaggio a RuntimeException
    }
}
```

### Costruttori con super(msg)

La chiamata `super(msg)` e' fondamentale: passa il messaggio alla classe padre (`Exception` o `RuntimeException`), che a sua volta lo passa a `Throwable`. Il messaggio viene poi recuperato con `getMessage()`.

```java
// Il costruttore di Throwable salva il messaggio
public class Throwable {
    private String detailMessage;

    public Throwable(String message) {
        this.detailMessage = message;
    }

    public String getMessage() {
        return detailMessage;
    }
}
```

**Costruttori multipli** (facoltativi ma utili):

```java
// Eccezione custom dal lab con due costruttori
public class DivisionByZeroException extends ArithmeticException {

    // Costruttore senza argomenti
    public DivisionByZeroException() {
        super();  // nessun messaggio
    }

    // Costruttore con messaggio
    public DivisionByZeroException(String s) {
        super(s);  // passa il messaggio al padre ArithmeticException
    }
}
```

### Pattern d'esame ricorrente nei 3 esami

| Esame                | Checked (`extends Exception`)              | Unchecked (`extends RuntimeException`)   |
| -------------------- | ------------------------------------------ | ---------------------------------------- |
| Simulazione Vetreria | `LiquidsException` (liquidi incompatibili) | `CapacityException` (capacita' superata) |
| Dicembre             | `GiornoException` (giorno occupato)        | `SorpresaException` (sorpresa duplicata) |
| Gennaio              | _(non presente)_                           | `EventoException` (evento duplicato)     |

**Lo schema e' costante:** errore di dominio recuperabile → `extends Exception` (checked); errore logico del chiamante → `extends RuntimeException` (unchecked).

### Template rapido: creare un'eccezione in 3 righe

All'esame, ogni eccezione personalizzata e' essenzialmente costituita da tre righe:

```java
// Template CHECKED:
public class NomeException extends Exception {
    public NomeException(String msg) { super(msg); }
}

// Template UNCHECKED:
public class NomeException extends RuntimeException {
    public NomeException(String msg) { super(msg); }
}
```

Sono **3 righe** per ciascuna e valgono **1 punto ciascuna** nella ScalaValutazione. Non dimenticarle.

---

## 9. Eccezioni e override: regole precise

Quando si fa l'override di un metodo in una sottoclasse, esistono regole precise riguardo alle eccezioni checked dichiarate. Queste regole derivano dal **principio di sostituibilita' di Liskov**: il codice scritto per il tipo del padre deve funzionare anche con un'istanza del figlio.

### Regola 1: l'override NON puo' aggiungere nuove checked exceptions

Se il metodo del padre dichiara certe checked exceptions, il metodo del figlio **non puo' dichiararne di nuove** (cioe' che il padre non dichiara). Il motivo e' che il chiamante e' stato scritto supponendo di dover gestire solo le eccezioni del padre: se il figlio ne lanciasse di nuove, il codice del chiamante si romperebbe.

```java
class Padre {
    // il padre dichiara IOException
    void leggi() throws IOException {
        // ...
    }
}

class Figlio extends Padre {
    @Override
    void leggi() throws IOException {
        // OK: stessa eccezione del padre
    }

    // ERRORE DI COMPILAZIONE:
    // @Override
    // void leggi() throws IOException, SQLException {
    //     // SQLException NON e' dichiarata nel padre!
    //     // Il chiamante che usa il tipo Padre non e'
    //     // preparato a gestire SQLException
    // }
}
```

**Perche'?** Consideriamo il codice del chiamante:

```java
// Il chiamante usa il tipo STATICO Padre
Padre p = new Figlio();  // tipo dinamico: Figlio
try {
    p.leggi();  // il compilatore guarda le throws di Padre
} catch (IOException e) {
    // gestisco IOException come dichiarato da Padre
}
// Se Figlio potesse aggiungere SQLException, questa NON verrebbe
// catturata dal catch qui sopra → il programma si romperebbe
```

### Regola 2: l'override puo' dichiarare un sottoinsieme, o nessuna

Il metodo del figlio puo' dichiarare **meno** eccezioni rispetto al padre, o **nessuna**. Il chiamante e' preparato a gestire le eccezioni del padre; se il figlio non le lancia, il `catch` del chiamante semplicemente non viene mai eseguito (nessun problema).

```java
class Padre {
    // il padre dichiara due checked exceptions
    void elabora() throws IOException, SQLException {
        // ...
    }
}

class FiglioA extends Padre {
    @Override
    void elabora() throws IOException {
        // OK: sottoinsieme delle eccezioni del padre
        // (solo IOException, senza SQLException)
    }
}

class FiglioB extends Padre {
    @Override
    void elabora() {
        // OK: nessuna eccezione → sottoinsieme vuoto
        // il padre ne dichiarava due, il figlio nessuna
    }
}

class FiglioC extends Padre {
    @Override
    void elabora() throws FileNotFoundException {
        // OK: FileNotFoundException extends IOException
        // quindi e' un SOTTOTIPO di un'eccezione del padre
        // (piu' specifico, non piu' generico)
    }
}
```

### Regola 3: le eccezioni unchecked sono libere

Le eccezioni unchecked (`RuntimeException` e sottotipi) **non sono soggette** a queste regole. Il figlio puo' sempre lanciare unchecked exception, indipendentemente da cosa dichiara il padre.

```java
class Padre {
    void metodo() throws IOException {
        // ...
    }
}

class Figlio extends Padre {
    @Override
    void metodo() throws IOException {
        // puo' lanciare eccezioni unchecked senza dichiararle
        if (condizione) {
            throw new IllegalArgumentException("errore");  // OK: unchecked
        }
        if (altraCondizione) {
            throw new CapacityException("superata");  // OK: unchecked
        }
    }
}
```

### Tabella riassuntiva delle regole

| Azione nell'override                                       | Consentita? |
| ---------------------------------------------------------- | ----------- |
| Dichiarare le **stesse** checked exceptions del padre      | Si          |
| Dichiarare un **sottoinsieme** delle checked del padre     | Si          |
| Dichiarare **nessuna** checked exception                   | Si          |
| Dichiarare un **sottotipo** di un'eccezione del padre      | Si          |
| Aggiungere **nuove** checked exceptions                    | **NO**      |
| Dichiarare un'eccezione **piu' ampia** di quella del padre | **NO**      |
| Aggiungere **unchecked** exceptions                        | Si (sempre) |

### Esempio pratico con la gerarchia del corso

```java
public abstract class Contenitore {
    // il metodo astratto dichiara LiquidsException (checked)
    public abstract void versa(Contenitore sorgente) throws LiquidsException;
}

public class Sfera extends Contenitore {
    @Override
    public void versa(Contenitore sorgente) throws LiquidsException {
        // OK: stessa eccezione del padre
        if (!compatibili(this, sorgente)) {
            throw new LiquidsException("incompatibili");
        }
        // ... logica di versamento ...
    }
}

public class Cilindro extends Contenitore {
    @Override
    public void versa(Contenitore sorgente) {
        // OK: nessuna eccezione dichiarata (sottoinsieme vuoto)
        // questo Cilindro accetta qualsiasi liquido
    }
}

// ERRORE:
// public class Cuboide extends Contenitore {
//     @Override
//     public void versa(Contenitore sorgente) throws LiquidsException, IOException {
//         // ERRORE: IOException NON era dichiarata nel padre!
//     }
// }
```

---

## 10. Pattern d'esame: eccezioni nella simulazione e negli esami

### Come vengono usate le eccezioni nel contesto d'esame

All'esame di Programmazione 2 le eccezioni compaiono in tre contesti:

1. **Definizione delle classi eccezione** (2 punti nella ScalaValutazione)
2. **Lancio nei metodi** (costruttori, metodi di dominio, metodi della collezione)
3. **Gestione nel Main** (try-catch durante la lettura dell'input)

### Pattern 1: eccezioni nel costruttore (unchecked)

I costruttori usano eccezioni **unchecked** per segnalare errori logici nei parametri:

```java
protected Contenitore(String liquido, double quantita) {
    // l'eccezione unchecked NON richiede throws nella firma
    if (quantita > calcolaVolume()) {
        throw new CapacityException("troppo liquido");
    }
    this.liquido = liquido;
    this.quantita = quantita;
}

// Il chiamante non e' obbligato a catturarla:
Contenitore c = new Sfera(5.0, "acqua", 1000.0);
// se 1000.0 supera la capacita' → CapacityException a runtime
```

### Pattern 2: eccezioni nei metodi di dominio (checked)

I metodi di dominio usano eccezioni **checked** per situazioni recuperabili:

```java
// la checked exception DEVE essere dichiarata con throws
public void versa(Contenitore sorgente) throws LiquidsException {
    if (this.liquido != null && sorgente.liquido != null
        && !this.liquido.equals(sorgente.liquido)) {
        throw new LiquidsException("liquidi incompatibili");
    }
    double spazio = calcolaVolume() - this.quantita;
    double daVersare = Math.min(sorgente.quantita, spazio);
    if (this.liquido == null) this.liquido = sorgente.liquido;
    this.quantita += daVersare;
    sorgente.quantita -= daVersare;
    if (sorgente.quantita == 0) sorgente.liquido = null;
}
```

### Pattern 3: mix checked/unchecked nella collezione

Nella classe che gestisce la collezione, si usano **entrambi** i tipi:

```java
public void inserisci(int giorno, Sorpresa s) throws GiornoException {
    // CHECKED: giorno occupato → il chiamante deve gestire
    if (caselle[giorno - 1] != null)
        throw new GiornoException("giorno occupato");

    // UNCHECKED: sorpresa duplicata → errore logico del chiamante
    for (Sorpresa esistente : caselle) {
        if (esistente != null && esistente.equals(s))
            throw new SorpresaException("gia' presente");
    }

    caselle[giorno - 1] = s;
}
```

### Pattern 4: try-catch nel Main

Il `main` cattura le eccezioni checked nel blocco di lettura dell'input:

```java
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Calendario calendario = new Calendario();

    while (sc.hasNextLine()) {
        String riga = sc.nextLine();
        // parsing della riga...
        try {
            calendario.inserisci(giorno, sorpresa);
            // se inserisci() lancia GiornoException (checked),
            // il catch la gestisce stampando il messaggio
        } catch (GiornoException e) {
            System.out.println("\tEccezione: " + e.getMessage());
        }
        // le unchecked (SorpresaException) NON vengono catturate qui
        // → propagano fino alla JVM e terminano il programma
        // (oppure possono essere catturate in un blocco separato)
    }
}
```

### Pattern 5: eccezioni multiple nel lab Ricevimento

Un esempio completo combinazione di diversi tipi di eccezioni:

```java
public class Ricevimento {

    // dichiara le eccezioni che il metodo puo' lanciare
    // (NullPointerException e' unchecked → non servirebbe nel throws)
    public static void prenota(String[] agenda, int ora, String nome)
            throws NullPointerException, AlreadyBookedException, TimeBusyException {

        // 1. Controllo agenda null → unchecked
        if (agenda == null)
            throw new NullPointerException("Agenda non valida!");

        // 2. Controllo parametri → unchecked
        if (nome == null || nome.equals(""))
            throw new ParameterException("nome non valido");
        if (ora < 13 || ora > 18)
            throw new ParameterException("orario non valido");

        // 3. Controllo slot occupato → checked (o unchecked, dipende dal README)
        if (agenda[ora - 13] != null)
            throw new TimeBusyException("Slot gia' prenotato");

        // 4. Controllo prenotazione duplicata → checked (o unchecked)
        if (Arrays.asList(agenda).contains(nome))
            throw new AlreadyBookedException(nome + ", hai gia' prenotato!");

        // se tutti i controlli passano, prenota
        agenda[ora - 13] = nome;
    }
}
```

### ATTENZIONE: discrepanza ScalaValutazione vs README

> **REGOLA FONDAMENTALE:** la ScalaValutazione puo' avere le eccezioni **invertite** (checked al posto di unchecked e viceversa) rispetto al README del testo d'esame. Segui **SEMPRE il README** come fonte autorevole.

Esempio concreto dalla simulazione Vetreria:

| Fonte                | `LiquidsException`           | `CapacityException`              |
| -------------------- | ---------------------------- | -------------------------------- |
| **ScalaValutazione** | unchecked (RuntimeException) | checked (Exception)              |
| **README**           | **checked** (Exception)      | **unchecked** (RuntimeException) |

Il README descrive il **significato semantico** delle eccezioni:

- `LiquidsException`: "liquidi incompatibili" → situazione di **dominio recuperabile** → **checked**
- `CapacityException`: "capacita' superata" → **errore logico** del chiamante → **unchecked**

La ScalaValutazione contiene un **refuso**. In fase d'esame, segui il README.

### Checklist eccezioni per l'esame

Prima di consegnare, verifica:

- [ ] Le due classi eccezione sono state create (`extends Exception` e `extends RuntimeException`)
- [ ] Ogni classe eccezione ha il costruttore con `super(msg)`
- [ ] I metodi che lanciano checked exceptions hanno `throws` nella firma
- [ ] I metodi che lanciano unchecked exceptions **non** hanno necessariamente `throws` (ma possono averlo)
- [ ] Il `Main` cattura le checked exceptions con `try-catch`
- [ ] Il messaggio passato all'eccezione e' significativo e leggibile
- [ ] L'ordine dei `catch` e' dal piu' specifico al piu' generico
- [ ] Le risorse (Scanner, ecc.) vengono chiuse nel `finally` o con `try-with-resources`
- [ ] Nell'override, non sono state aggiunte nuove checked exceptions

---
