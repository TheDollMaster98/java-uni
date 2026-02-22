# Checklist esame Java - dall'inizio alla fine

## FASE 1: Leggere il testo e identificare le classi

- [ ] Quante classi ci sono? Quale e' la classe padre?
- [ ] La classe padre e' **astratta**?
- [ ] Quali campi ha il padre? Quali sono comuni a tutti?
- [ ] Ci sono valori che cambiano per sottoclasse? -> metodo **astratto**
- [ ] C'e' un contenitore (Calendario, Olimpiade, ecc.)?
- [ ] Quali eccezioni servono? Checked o unchecked?
- [ ] C'e' un Test con main?

---

## FASE 2: Classe padre astratta

### Dichiarazione

```java
abstract public class Padre implements Comparable<Padre> {
//OVERVIEW: ...
```

- [ ] `abstract` -- la classe non puo' essere istanziata direttamente, serve come "modello" per le sottoclassi. Se il testo dice "ci sono diversi tipi di X", il padre e' astratto.
- [ ] `implements Comparable<Padre>` -- permette di confrontare due oggetti tra loro (ordine naturale). Serve per `Collections.sort()` e per il `compareTo`. Se il testo chiede un ordinamento, serve Comparable.
- [ ] OVERVIEW -- commento che descrive cosa rappresenta la classe (es. "modella un Evento delle olimpiadi invernali (immutabile)")

### Campi

- [ ] Campi `final public` (non private con getter) -- `final` = il valore non cambia dopo la creazione (immutabile). `public` = accessibile direttamente senza getter.
- [ ] Solo i dati **comuni a tutte** le sottoclassi (es. `nome`)
- [ ] NON salvare dati che variano per sottoclasse (es. durata) -> usa metodo astratto

### Costruttore

- [ ] Validazione: `if(nome == null || nome.equals(""))` -> `IllegalArgumentException` -- controlla sempre che i parametri siano validi
- [ ] Messaggio con `"\tECCEZIONE: ..."`
- [ ] MODIFIES/EFFECTS -- commenti che spiegano cosa modifica e cosa fa il metodo

### Metodo astratto

```java
abstract public int valore();
//EFFECTS: restituisce ...
```

- [ ] `abstract` = il padre dichiara il metodo ma NON lo implementa. Ogni sottoclasse DEVE implementarlo con `@Override`. Serve quando il valore cambia da sottoclasse a sottoclasse (es. durata 60 per Cerimonia, calcolata per Gara).

### equals

```java
@Override
public boolean equals(Object o) {
    if(o == null) return false;        // se l'altro e' null, non sono uguali
    if(o == this) return true;         // se e' lo stesso oggetto in memoria, sono uguali
    if(!(o instanceof Padre)) return false;  // se non e' dello stesso tipo, non sono uguali
    if(((Padre) o).nome.equals(this.nome)) return true;  // confronta il campo chiave
    return false;
}
```

- [ ] `equals` -- serve per confrontare due oggetti per **uguaglianza logica** (es. "stesso nome" = stesso evento). Senza equals, Java confronta solo i riferimenti in memoria.
- [ ] `instanceof` controlla se l'oggetto e' di quel tipo O di una sottoclasse. NON usare `getClass()` che confronta il tipo esatto.

### hashCode

```java
@Override
public int hashCode() {
    return java.util.Objects.hash(getClass(), nome);
}
```

- [ ] `hashCode` -- restituisce un numero intero che rappresenta l'oggetto. Se due oggetti sono `equals`, DEVONO avere lo stesso `hashCode`. Serve per HashMap, HashSet, ecc.
- [ ] `Objects.hash()` -- metodo di utilita' che calcola l'hash da piu' campi.

### compareTo

```java
@Override
public int compareTo(Padre p) {
    return Integer.compare(this.valore(), p.valore());
}
```

- [ ] `compareTo` -- definisce l'**ordine naturale** tra oggetti. Restituisce: negativo (this < altro), zero (uguali), positivo (this > altro). Serve per `Collections.sort()`.
- [ ] `Integer.compare(a, b)` -- confronta due interi in modo sicuro. MAI fare `a - b` perche' puo' dare overflow.

### toString

```java
@Override
public String toString() {
    return " nome: " + nome + " valore: " + valore();
}
```

- [ ] `toString` -- restituisce una rappresentazione testuale dell'oggetto. Viene chiamato automaticamente da `System.out.println(oggetto)`.
- [ ] Inizia con **spazio** cosi' le sottoclassi fanno `"Tipo" + super.toString()` e il risultato e' "Tipo nome: X valore: Y".

### main() del padre

- [ ] Crea istanze delle sottoclassi (mai del padre, e' astratto!)
- [ ] Stampa ogni oggetto (chiama `toString` automaticamente)
- [ ] Confronta con `equals` e stampa con testo descrittivo
- [ ] Confronta con `compareTo` e stampa con testo descrittivo

---

## FASE 3: Sottoclassi

### Per ogni sottoclasse:

- [ ] `extends Padre` -- eredita tutti i campi e metodi del padre
- [ ] Campi propri `final public`
- [ ] Costruttore chiama `super(nome)` -- `super()` chiama il costruttore del padre. Passi SOLO i campi del padre, non quelli della sottoclasse.
- [ ] Validazione parametri propri nel costruttore
- [ ] Implementa il metodo astratto con `@Override` -- `@Override` indica che stai implementando/sovrascrivendo un metodo del padre. Il compilatore controlla che esista davvero.

```java
@Override
public int durata() { return 60; }  // valore fisso per questa sottoclasse
```

- [ ] NON creare metodi statici con lo stesso nome di metodi del padre
- [ ] `toString`: `"NomeTipo" + super.toString() + " campoExtra: " + campo` -- `super.toString()` chiama il toString del padre, cosi' non ripeti il codice.
- [ ] OVERVIEW, MODIFIES/EFFECTS

### Errore comune: metodo statico che nasconde il padre

```java
// SBAGLIATO - "static" crea un metodo NUOVO che nasconde quello del padre
// il padre non lo vede, Collections.sort() non lo usa
private static int durata() { return 60; }

// GIUSTO - @Override implementa il metodo astratto del padre
// il padre lo vede, Collections.sort() lo usa tramite compareTo
@Override
public int durata() { return 60; }
```

---

## FASE 4: Eccezioni

- [ ] **Checked** (`extends Exception`): il chiamante e' OBBLIGATO a gestirle con try-catch o throws. Usale per errori "prevedibili" che il programma deve gestire (es. giorno non valido).
- [ ] **Unchecked** (`extends RuntimeException`): il try-catch e' opzionale. Usale per errori di programmazione o logici (es. evento duplicato, argomento null).
- [ ] Messaggio con `"\tECCEZIONE: ..."` come prefisso
- [ ] Leggere il testo: se dice "eccezione controllata" = checked, altrimenti di solito e' unchecked

```java
// Checked: il compilatore ti OBBLIGA a fare try-catch
public class GiornoException extends Exception {
    public GiornoException(String message) { super(message); }
}

// Unchecked: il compilatore NON ti obbliga, ma puoi comunque fare try-catch
public class EventoException extends RuntimeException {
    public EventoException(String message) { super(message); }
}
```

---

## FASE 5: Contenitore iterabile

### Dichiarazione

```java
public class Contenitore implements Iterable<Padre> {
//OVERVIEW: ...
```

- [ ] `implements Iterable<Padre>` -- permette di usare il for-each: `for (Padre p : contenitore)`. Obbliga a implementare il metodo `iterator()`.

### Campi

- [ ] Array (o lista) per gli elementi, `final` -- l'array stesso non cambia (final), ma il suo contenuto si'
- [ ] Eventuali altri campi (anno, nome, ecc.), `final`

### Costruttore

- [ ] Inizializza array con dimensione corretta: `new Padre[dimensione]`
- [ ] Validazione parametri

### aggiungi(int posizione, Padre elemento)

- [ ] Controlla posizione valida (range) -> eccezione
- [ ] Controlla posizione gia' occupata -> eccezione
- [ ] Controlla duplicato nell'array (ciclo con `equals`) -> eccezione
- [ ] Controlla vincoli speciali (es. cerimonia apertura solo giorno 1) -> eccezione
- [ ] Assegna `array[posizione] = elemento`

### rimuovi(int posizione)

- [ ] Controlla posizione valida -> eccezione
- [ ] Controlla posizione vuota (niente da rimuovere) -> eccezione
- [ ] `array[posizione] = null`

### toString

```java
@Override
public String toString() {
    // StringBuilder e' piu' efficiente di concatenare stringhe con +
    StringBuilder sb = new StringBuilder("Contenitore anno:\n");
    for (int i = 0; i < array.length; i++)
        if (array[i] != null)
            sb.append("\t" + (i+1) + ":" + array[i] + "\n");
    return sb.toString();
}
```

- [ ] `StringBuilder` -- classe per costruire stringhe pezzo per pezzo. `append()` aggiunge in fondo, `toString()` restituisce la stringa finale.
- [ ] Stampa solo le posizioni occupate (non null)
- [ ] Tab + posizione + elemento

### iterator (ordine naturale)

```java
@Override
public Iterator<Padre> iterator() {
    // 1. Raccogli tutti gli elementi non null in una lista
    List<Padre> lista = new ArrayList<>();
    for (Padre p : array)
        if (p != null) lista.add(p);

    // 2. Ordina la lista usando compareTo (ordine naturale)
    Collections.sort(lista);

    // 3. Restituisci l'iteratore della lista
    return lista.iterator();
}
```

- [ ] `Iterator<Padre>` -- oggetto che permette di scorrere una collezione uno alla volta. Il for-each lo usa dietro le quinte.
- [ ] `ArrayList` -- lista dinamica (cresce da sola). La usi per raccogliere gli elementi non null.
- [ ] `Collections.sort(lista)` -- ordina la lista usando il `compareTo` degli elementi. Per questo serve `Comparable` nella classe padre.
- [ ] `lista.iterator()` -- restituisce un iteratore pronto da usare. Non serve creare un iteratore custom.

### AF e RI

```java
// AF(campo1, campo2, ...) = descrizione di cosa rappresenta l'oggetto
//   es. AF(eventi, anno) = "le olimpiadi invernali dell'anno 'anno' con gli eventi in 'eventi'"

// RI: condizioni che devono SEMPRE essere vere per tutto il ciclo di vita dell'oggetto
//   es. RI: anno > 0, eventi != null, nessun duplicato in eventi
```

- [ ] Scrivili! AF = cosa rappresenta, RI = cosa deve essere sempre vero

---

## FASE 6: Test.java

```java
import java.util.Scanner;  // per leggere da stdin

public class Test {
    public static void main(String[] args) {
        // args = parametri da RIGA DI COMANDO (es. java Test 2026)
        int param = Integer.parseInt(args[0]);
        // Scanner = legge da STDIN (tastiera / file reindirizzato)
        Scanner scanner = new Scanner(System.in);
        Contenitore c = new Contenitore(param);

        // Ciclo di lettura: continua finche' c'e' input
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // split(" ") divide la riga in token separati da spazio
            String[] tokens = line.split(" ");
            String comando = tokens[0];
            int posizione = Integer.parseInt(tokens[1]);

            // try-catch DENTRO il while: se un comando fallisce,
            // il programma continua con il prossimo comando
            try {
                if (comando.equals("aggiungi")) {
                    // parsing dei token specifici DENTRO il branch
                    // perche' "rimuovi" non ha tokens[2], tokens[3], ecc.
                    String tipo = tokens[2];
                    if (tipo.equals("Tipo1")) {
                        c.aggiungi(posizione, new Tipo1(tokens[3], ...));
                    } else if (tipo.equals("Tipo2")) {
                        c.aggiungi(posizione, new Tipo2(tokens[3], ...));
                    }
                } else if (comando.equals("rimuovi")) {
                    c.rimuovi(posizione);
                }
            } catch (CheckedException e) {
                // stampa il messaggio dell'eccezione e continua
                System.out.println(e.getMessage());
            }
        }

        // Dopo EOF: stampa il contenitore con toString (ordine per posizione)
        System.out.println(c);
        // Intestazione per l'iterazione ordinata
        System.out.println("Elementi in ordine:");
        // for-each usa iterator() -> ordine naturale (compareTo)
        for (Padre p : c) {
            System.out.println("\t" + p);  // tab davanti a ogni elemento
        }
    }
}
```

### Checklist Test:

- [ ] `args[0]` = riga di comando (passato quando lanci `java Test 2026`)
- [ ] `Scanner(System.in)` = legge da stdin (tastiera o file)
- [ ] `split(" ")` = divide stringa in array di token
- [ ] `Integer.parseInt()` = converte stringa in int
- [ ] Parsing dei campi specifici **dentro** il branch del comando (non prima)
- [ ] try-catch **dentro** il while (un errore non ferma il programma)
- [ ] Dopo il while: `println(contenitore)` chiama il `toString`
- [ ] Intestazione prima dell'iterazione
- [ ] `"\t"` davanti a ogni elemento iterato
- [ ] Il for-each (`for (Padre p : c)`) chiama `iterator()` del contenitore automaticamente

---

## GLOSSARIO RAPIDO

| Parola chiave    | A cosa serve                                                                  |
| ---------------- | ----------------------------------------------------------------------------- |
| `abstract`       | La classe/metodo non ha implementazione. Le sottoclassi DEVONO implementarlo. |
| `final`          | Il valore non puo' cambiare dopo l'assegnazione (campo immutabile).           |
| `static`         | Appartiene alla CLASSE, non all'oggetto. Non puo' essere `@Override`.         |
| `extends`        | La sottoclasse eredita campi e metodi dal padre.                              |
| `implements`     | La classe si impegna a implementare i metodi dell'interfaccia.                |
| `@Override`      | Indica che stai sovrascrivendo un metodo del padre. Il compilatore verifica.  |
| `super(...)`     | Chiama il costruttore del padre. DEVE essere la prima riga del costruttore.   |
| `super.metodo()` | Chiama la versione del metodo definita nel padre.                             |
| `instanceof`     | Controlla se un oggetto e' di un certo tipo (o sottotipo).                    |
| `Comparable`     | Interfaccia: obbliga a implementare `compareTo` per l'ordine naturale.        |
| `Iterable`       | Interfaccia: obbliga a implementare `iterator` per il for-each.               |
| `Iterator`       | Oggetto che scorre una collezione. Ha `hasNext()` e `next()`.                 |
| `throws`         | Dichiara che un metodo PUO' lanciare un'eccezione (obbligatorio per checked). |
| `throw`          | Lancia effettivamente un'eccezione: `throw new MiaException("msg")`.          |

### Commenti di specifica (OVERVIEW, REQUIRES, MODIFIES, EFFECTS)

Il prof li usa su **ogni classe e metodo pubblico**. Sono la "documentazione formale" del codice.

#### OVERVIEW -- sulla classe
```java
abstract public class Evento implements Comparable<Evento> {
//OVERVIEW: modella un Evento delle olimpiadi invernali (immutabile)
```
- Si scrive subito dopo la dichiarazione della classe
- Dice **cosa rappresenta** la classe e se e' mutabile o immutabile

#### REQUIRES -- precondizioni (facoltativo)
```java
public void aggiungi(int giorno, Evento e) throws GiornoException {
//REQUIRES: giorno >= 1 && giorno <= 19, e != null
```
- Condizioni che DEVONO essere vere **prima** di chiamare il metodo
- Se non sono vere, il comportamento e' indefinito
- Se il metodo valida tutto con eccezioni, REQUIRES puo' non servire

#### MODIFIES -- cosa viene modificato
```java
public void aggiungi(int giorno, Evento e) throws GiornoException {
//MODIFIES: this
```
- Elenca gli oggetti che il metodo **modifica**
- Se il metodo non modifica nulla (e' una query), non si mette
- Tipico: `this` per metodi che cambiano lo stato dell'oggetto

#### EFFECTS -- cosa fa il metodo
```java
public void aggiungi(int giorno, Evento e) throws GiornoException {
//MODIFIES: this
//EFFECTS: aggiunge l'evento e al giorno specificato
//         se giorno non valido o gia' occupato lancia GiornoException
//         se evento gia' presente lancia EventoException
```
- Descrive il **comportamento** del metodo
- Include i casi di eccezione (quando e quale eccezione viene lanciata)
- E' la parte piu' importante: spiega COSA fa, non COME lo fa

#### Esempio completo su un costruttore
```java
public Evento(String nome) throws IllegalArgumentException {
//MODIFIES: this
//EFFECTS: inizializza this con nome evento
//         se nome null o vuoto lancia IllegalArgumentException
    if(nome == null || nome.equals(""))
        throw new IllegalArgumentException("\tECCEZIONE: nome null o vuoto");
    this.nome = nome;
}
```

#### Esempio completo su un metodo
```java
public void rimuovi(int giorno) throws GiornoException {
//MODIFIES: this
//EFFECTS: rimuove l'evento al giorno specificato
//         se giorno non valido o vuoto lancia GiornoException
    if(giorno < 1 || giorno > 19)
        throw new GiornoException("\tECCEZIONE: giorno non valido");
    if(eventi[giorno - 1] == null)
        throw new GiornoException("\tECCEZIONE: nessun evento al giorno");
    eventi[giorno - 1] = null;
}
```

### Esempi dei metodi principali

#### `compareTo` -- definisce l'ordine naturale

```java
// Nella classe padre (es. Evento):
@Override
public int compareTo(Evento e) {
    return Integer.compare(this.durata(), e.durata());
}

// Uso:
Evento a = new Gara("Sci", 15);     // durata 75
Evento b = new Cerimonia("Ape", true); // durata 60
a.compareTo(b);  // positivo -> a > b (75 > 60)
b.compareTo(a);  // negativo -> b < a (60 < 75)
a.compareTo(a);  // zero     -> uguali
```

#### `equals` -- confronto per uguaglianza logica

```java
// Nella classe padre (es. Evento):
@Override
public boolean equals(Object o) {
    if(o == null) return false;
    if(o == this) return true;
    if(!(o instanceof Evento)) return false;
    return ((Evento) o).nome.equals(this.nome);
}

// Uso:
Evento a = new Gara("Sci", 15);
Evento b = new Cerimonia("Sci", true);
a.equals(b);  // true  -> stesso nome "Sci", anche se tipi diversi
a.equals(null); // false
a.equals("ciao"); // false -> non e' un Evento
```

#### `hashCode` -- codice numerico dell'oggetto

```java
// Nella classe padre (es. Evento):
@Override
public int hashCode() {
    return java.util.Objects.hash(getClass(), nome);
}

// Uso (dietro le quinte in HashMap/HashSet):
Evento a = new Gara("Sci", 15);
a.hashCode();  // restituisce un int (es. 123456)
// Regola: se a.equals(b) e' true, allora a.hashCode() == b.hashCode()
```

#### `iterator` -- scorre la collezione in ordine

```java
// Nel contenitore (es. Olimpiade):
@Override
public Iterator<Evento> iterator() {
    List<Evento> lista = new ArrayList<>();
    for (Evento e : eventi)
        if (e != null) lista.add(e);
    Collections.sort(lista);  // usa compareTo
    return lista.iterator();
}

// Uso esplicito (raro, di solito usi for-each):
Iterator<Evento> it = olimpiade.iterator();
while (it.hasNext()) {           // c'e' un prossimo elemento?
    Evento e = it.next();        // dammi il prossimo elemento
    System.out.println(e);
}

// Uso con for-each (comune, fa la stessa cosa):
for (Evento e : olimpiade) {     // chiama iterator() automaticamente
    System.out.println(e);
}
```

#### `hasNext` e `next` -- metodi dell'Iterator

```java
// hasNext() -> restituisce true se ci sono ancora elementi
// next()    -> restituisce il prossimo elemento e avanza

Iterator<Evento> it = olimpiade.iterator();
it.hasNext();  // true (se c'e' almeno un elemento)
it.next();     // restituisce il PRIMO elemento
it.hasNext();  // true (se ce ne sono altri)
it.next();     // restituisce il SECONDO elemento
it.hasNext();  // false (se non ce ne sono piu')
// it.next();  // ERRORE! NoSuchElementException
```

#### `toString` -- rappresentazione testuale

```java
// Nella classe padre:
@Override
public String toString() {
    return " nome: " + nome + " durata: " + durata();
}

// Nella sottoclasse:
@Override
public String toString() {
    return "Gara" + super.toString() + " con " + numAtleti + " atleti";
}
// Risultato: "Gara nome: Sci durata: 75 con 15 atleti"

// Uso:
System.out.println(evento);  // chiama toString() automaticamente
String s = "Evento: " + evento;  // chiama toString() automaticamente
```

#### `Collections.sort` -- ordina una lista

```java
List<Evento> lista = new ArrayList<>();
lista.add(new Gara("Sci", 15));        // durata 75
lista.add(new Cerimonia("Ape", true)); // durata 60
lista.add(new Gara("Bob", 10));        // durata 60

Collections.sort(lista);  // ordina usando compareTo
// Risultato: [Cerimonia(60), Gara Bob(60), Gara Sci(75)]
```

#### `instanceof` -- controlla il tipo

```java
Evento e = new Gara("Sci", 15);
e instanceof Evento;  // true  (Gara E' un Evento)
e instanceof Gara;    // true  (e' proprio una Gara)
e instanceof Cerimonia; // false (non e' una Cerimonia)
e instanceof Object;  // true  (tutto e' Object in Java)

// Uso tipico in equals:
if(!(o instanceof Evento)) return false;
// Uso tipico in aggiungi per vincoli speciali:
if(evento instanceof Cerimonia) {
    Cerimonia c = (Cerimonia) evento;  // cast per accedere ai campi di Cerimonia
    if(c.isApertura() && giorno != 1) throw new GiornoException("...");
}
```

---

## ERRORI FREQUENTI DA EVITARE

1. **Salvare nel padre dati che variano per sottoclasse** -> usa metodo astratto
2. **Creare metodi `static` con lo stesso nome di metodi ereditati** -> `static` nasconde (hide) il padre, `@Override` lo sovrascrive
3. **`new ClasseAstratta()`** -> non si puo' istanziare una classe astratta
4. **Sottrazione per compareTo** (`a - b`) -> usa `Integer.compare(a, b)`
5. **`getClass()` in equals** -> usa `instanceof`
6. **Dimenticare `\t` nell'output** -> controlla l'esempio d'esecuzione
7. **Parsing dei tokens prima di sapere il comando** -> `tokens[3]` puo' non esistere per "rimuovi"
8. **try-catch fuori dal while** -> un'eccezione ferma tutto il programma
9. **Dimenticare checked/unchecked** -> leggi se il testo dice "eccezione controllata" o no
10. **Campo non `final`** -> se l'oggetto e' immutabile, tutti i campi sono `final`
