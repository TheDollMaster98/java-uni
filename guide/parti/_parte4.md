---
# PARTE 7 – Preparazione Esame
---

## 25. Filtro teoria – 30+ domande e risposte

### A. OOP e Polimorfismo

**1. Cos'e' il polimorfismo?**
Il polimorfismo permette a variabili e metodi di assumere forme diverse a seconda del tipo dinamico dell'oggetto. Il dispatch dinamico seleziona a runtime il metodo del tipo dinamico.

**2. Differenza tra tipo statico e tipo dinamico?**
Il tipo statico e' quello dichiarato (compile-time), il tipo dinamico e' quello effettivo dell'oggetto (runtime, assegnato con `new`). Il compilatore controlla il tipo statico, il dispatch esegue il metodo del tipo dinamico.

**3. Cos'e' il dispatch dinamico?**
A runtime, Java chiama il metodo della classe effettiva dell'oggetto (tipo dinamico), non della classe dichiarata. Funziona per metodi d'istanza, NON per metodi static.

**4. Differenza tra overriding e overloading?**
Overriding: ridefinire un metodo ereditato (stessa firma). Overloading: definire piu' metodi con lo stesso nome ma parametri diversi. Override usa late binding (runtime), overload usa early binding (compile-time).

**5. Un metodo static puo' essere override?**
No. I metodi static subiscono hiding, non overriding. Vengono risolti con early binding (tipo statico).

**6. Cosa significa `final` su metodo/classe/variabile?**
Su metodo: non override-abile. Su classe: non estendibile. Su variabile: assegnabile una sola volta (costante per primitivi, riferimento fisso per oggetti).

### B. Classi Astratte e Interfacce

**7. Differenza tra classe astratta e interfaccia?**
Classe astratta: puo' avere stato (campi), costruttore, metodi concreti e astratti, singola ereditarieta'. Interfaccia: nessuno stato d'istanza, nessun costruttore, metodi astratti (+ default da Java 8), multipla implementazione.

**8. Si puo' istanziare una classe astratta?**
No. Si possono solo istanziare le sottoclassi concrete.

**9. Una classe astratta puo' avere costruttore?**
Si, e viene chiamato con `super()` dalle sottoclassi. Non puo' essere chiamato direttamente con `new`.

**10. Cosa sono i metodi default nelle interfacce?**
Metodi con implementazione di default introdotti in Java 8. Le classi che implementano l'interfaccia possono usare il default o fare override.

### C. Eccezioni

**11. Differenza tra checked e unchecked exception?**
Checked: extends `Exception` (direttamente), obbligatorio dichiararle con `throws` o catturarle. Unchecked: extends `RuntimeException`, non obbligatorio dichiararle. Checked = situazione recuperabile, Unchecked = errore logico del programmatore.

**12. Cosa succede se un metodo lancia una checked exception non dichiarata?**
Errore di compilazione. Il compilatore controlla che tutte le checked exceptions siano dichiarate o catturate.

**13. In un override, posso aggiungere nuove checked exceptions?**
No. L'override puo' dichiarare le stesse eccezioni del padre, un sottoinsieme, o nessuna. Non puo' aggiungerne di nuove.

**14. Cosa fa `finally`?**
Il blocco `finally` viene eseguito SEMPRE, sia che si verifichi un'eccezione sia che non si verifichi. Usato per rilasciare risorse.

**15. Cos'e' il try-with-resources?**
Sintassi che auto-chiude risorse `AutoCloseable` alla fine del blocco try: `try (Scanner s = new Scanner(...)) { ... }`.

### D. Specifiche e Astrazione

**16. Cosa sono REQUIRES, MODIFIES, EFFECTS?**
Clausole della specifica di un metodo. REQUIRES: precondizioni. MODIFIES: quali oggetti vengono modificati. EFFECTS: postcondizioni (cosa fa il metodo quando REQUIRES e' soddisfatto).

**17. Differenza tra metodo totale e parziale?**
Totale: funziona per ogni input (nessun REQUIRES restrittivo). Parziale: ha precondizioni specifiche nel REQUIRES. Un metodo parziale puo' essere reso totale aggiungendo controlli e lanciando eccezioni.

**18. Cos'e' un ADT?**
Abstract Data Type: tipo definito dalle sue operazioni (creatori, produttori, osservatori, mutatori) e non dalla sua rappresentazione interna.

**19. Cos'e' la funzione di astrazione (AF)?**
Mappatura dalla rappresentazione concreta (campi privati) al valore astratto che l'utente percepisce. Descrive il significato dei campi.

**20. Cos'e' l'invariante di rappresentazione (RI)?**
Condizione booleana che deve essere vera per OGNI istanza valida in OGNI momento. Garantisce la consistenza della rappresentazione interna.

### E. Iteratori e Collections

**21. Differenza tra Iterable e Iterator?**
`Iterable<T>` ha un metodo `iterator()` che restituisce un `Iterator<T>`. `Iterator<T>` ha `hasNext()`, `next()` e `remove()`. Iterable permette il for-each, Iterator esegue l'iterazione.

**22. Perche' una classe implementa Iterable?**
Per poter essere usata nel costrutto for-each: `for (T x : oggettoIterable) { ... }`.

**23. Come si rimuove un elemento durante l'iterazione senza ConcurrentModificationException?**
Usando `Iterator.remove()` dopo `next()`. NON usare `list.remove()` dentro un for-each.

**24. Differenza tra Comparable e Comparator?**
`Comparable<T>`: ordine naturale, metodo `compareTo(T)` nella classe stessa. `Comparator<T>`: ordine esterno, metodo `compare(T, T)` in oggetto separato o lambda.

**25. compareTo restituisce 0 implica equals true?**
Non necessariamente. Puo' esserci incoerenza intenzionale (es. due Cerimonie con nomi diversi ma stessa durata). Attenzione: `TreeSet` usa `compareTo` per uguaglianza!

### F. Contratti e Metodi Fondamentali

**26. Contratto di equals?**
Riflessivo (`x.equals(x)==true`), simmetrico, transitivo, consistente, `x.equals(null)==false`. Se si cambia equals si DEVE cambiare hashCode.

**27. Perche' cambiare hashCode quando si cambia equals?**
Contratto: se `x.equals(y)` allora `x.hashCode() == y.hashCode()`. Altrimenti `HashSet` e `HashMap` non funzionano correttamente.

**28. Differenza tra shallow copy e deep copy?**
Shallow: copia solo i riferimenti (oggetti interni condivisi). Deep: copia anche gli oggetti referenziati (copia indipendente).

### G. Principi di Progettazione

**29. Cos'e' il principio di Liskov (LSP)?**
Se S e' sottotipo di T, gli oggetti di T possono essere sostituiti con oggetti di S senza alterare la correttezza. La sottoclasse non puo' avere precondizioni piu' restrittive ne' postcondizioni meno restrittive.

**30. Composizione vs Ereditarieta'?**
Ereditarieta': relazione IS-A. Composizione: relazione HAS-A. Preferire composizione quando non serve polimorfismo: piu' flessibile e meno accoppiata.

**31. Cos'e' la rep exposure?**
Quando la rappresentazione interna (campi privati) viene esposta all'esterno, permettendo a codice esterno di violare l'RI. Si previene con copia difensiva.

**32. Cos'e' l'astrazione per specifica?**
Il chiamante usa il metodo basandosi SOLO sulla sua specifica (REQUIRES/EFFECTS), senza conoscere ne' dipendere dall'implementazione.

---

## 26. Pattern d'esame pratico

### Struttura ricorrente in TUTTI gli esami

```
1. Classe astratta base (Contenitore/Sorpresa/Evento)
   - abstract class ... implements Comparable<T>, Cloneable
   - Campi comuni private (nome, quantita, etc.)
   - Costruttore protected con validazione
   - Metodo astratto per calcolo (calcolaVolume/getCosto/getDurata)
   - compareTo basato sul calcolo
   - equals basato su nome
   - hashCode coerente con equals
   - clone
   - toString
   - AF e RI
   - main() di test

2. Sottoclassi concrete (Sfera/Cilindro, Cioccolatino/Giocattolo, Cerimonia/Gara)
   - extends classe base
   - Campi specifici private final
   - Costruttore con super() + validazione
   - @Override metodo astratto
   - toString specifico
   - AF specifica

3. Eccezione CHECKED
   - extends Exception
   - Costruttore con super(msg)

4. Eccezione UNCHECKED
   - extends RuntimeException
   - Costruttore con super(msg)

5. Classe collezione (Vetreria/Calendario/Olimpiade)
   - implements Iterable<T>
   - ArrayList<T> o T[] come rappresentazione
   - aggiungi/inserisci con controlli + lancia eccezioni
   - rimuovi/estrai/apri
   - iterator() con sort su copia
   - toString con formato esatto
   - AF e RI

6. Classe Main/Test
   - Scanner per input (stdin o args + stdin)
   - Parsing riga per riga con split
   - Switch su tipo/comando
   - try-catch per eccezioni checked
   - Stampa risultati
```

### Schema visivo

```
┌─────────────────────────┐
│  Classe Astratta        │  abstract, Comparable, equals(nome), compareTo(valore)
│  + metodo astratto      │  AF, RI, OVERVIEW, costruttore protected
└──────────┬──────────────┘
           │ extends
      ┌────┴────┐
      │ Sotto1  │  Sotto2   → implementano metodo astratto, toString, AF
      └─────────┘

┌─────────────────────────┐
│  Collezione             │  Iterable, iterator ordinato per ordine naturale
│  + aggiungi/estrai      │  AF, RI, OVERVIEW, eccezioni nei metodi
└─────────────────────────┘

┌──────────────────────┐
│  2 Eccezioni         │  1 checked (Exception), 1 unchecked (RuntimeException)
└──────────────────────┘

┌──────────────────────┐
│  Main / Test         │  Scanner + split + switch + try-catch
└──────────────────────┘
```

---

## 27. Soluzione completa: Simulazione Vetreria

### Specifica

- `Contenitore` abstract: Sfera, Cilindro, Cuboide
- `Vetreria`: collezione Iterable, con aggiungi, estrai, ottimizza
- `CapacityException` (unchecked), `LiquidsException` (checked)
- equals NON esplicitamente richiesto, compareTo per capienza
- Input da stdin: `liquido quantita Tipo [parametri]`

### Implementazione completa

```java
// === CapacityException.java ===
public class CapacityException extends RuntimeException {
    public CapacityException(String msg) { super(msg); }
}

// === LiquidsException.java ===
public class LiquidsException extends Exception {
    public LiquidsException(String msg) { super(msg); }
}
```

```java
// === Contenitore.java ===
import java.util.*;

public abstract class Contenitore implements Comparable<Contenitore>, Cloneable {
    // OVERVIEW: Un Contenitore e' un recipiente MUTABILE che puo' contenere
    //           un liquido fino alla sua capienza massima (volume).

    private String liquido;
    private double quantita;

    // AF(this): un contenitore che contiene 'quantita' unita' di 'liquido'.
    //           Se liquido == null, il contenitore e' vuoto.
    // RI(this): quantita >= 0 && quantita <= calcolaVolume()
    //           && (liquido == null) == (quantita == 0)

    protected Contenitore() {
        this.liquido = null;
        this.quantita = 0;
    }

    protected Contenitore(String liquido, double quantita) {
        if (quantita < 0) throw new IllegalArgumentException("Quantita' negativa");
        if (quantita > calcolaVolume()) {
            throw new CapacityException("Il contenitore ha capienza: "
                + calcolaVolume() + " ma il liquido ha un volume di: " + quantita);
        }
        this.liquido = (quantita > 0) ? liquido : null;
        this.quantita = quantita;
    }

    public abstract double calcolaVolume();

    /**
     * REQUIRES: -
     * MODIFIES: this, sorgente
     * EFFECTS: versa il liquido da sorgente in this.
     *          Se i liquidi sono incompatibili lancia LiquidsException.
     */
    public void versa(Contenitore sorgente) throws LiquidsException {
        if (this.liquido != null && sorgente.liquido != null
            && !this.liquido.equals(sorgente.liquido)) {
            throw new LiquidsException("liquidi incompatibili "
                + this.liquido + " e " + sorgente.liquido);
        }

        double spazio = calcolaVolume() - this.quantita;
        double daVersare = Math.min(sorgente.quantita, spazio);

        if (this.liquido == null) this.liquido = sorgente.liquido;
        this.quantita += daVersare;
        sorgente.quantita -= daVersare;
        if (sorgente.quantita == 0) sorgente.liquido = null;
    }

    @Override
    public int compareTo(Contenitore altro) {
        return Double.compare(this.calcolaVolume(), altro.calcolaVolume());
    }

    @Override
    public Contenitore clone() {
        try {
            return (Contenitore) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public String getLiquido() { return liquido; }
    public double getQuantita() { return quantita; }

    public static void main(String[] args) {
        Sfera sfera = new Sfera(2, "acqua", 25);
        Cuboide cuboide = new Cuboide(2, 3, 4);
        Cilindro cilindro = new Cilindro(3, 2, "alcool", 20);

        try { sfera.versa(cilindro); }
        catch (LiquidsException e) { System.out.println(e.getMessage()); }

        try { sfera.versa(cuboide); }
        catch (LiquidsException e) { System.out.println(e.getMessage()); }

        System.out.println(sfera);
        System.out.println(cuboide);
        System.out.println(cilindro);
    }
}
```

```java
// === Sfera.java ===
public class Sfera extends Contenitore {
    // OVERVIEW: contenitore sferico definito dal raggio

    private final double raggio;

    // AF(this): una sfera di raggio 'raggio'
    // RI(this): raggio > 0

    public Sfera(double raggio) {
        super();
        this.raggio = raggio;
    }

    public Sfera(double raggio, String liquido, double quantita) {
        this.raggio = raggio;
        if (quantita > calcolaVolume()) {
            throw new CapacityException("La sfera ha capienza: "
                + calcolaVolume() + " ma il liquido ha un volume di: " + quantita);
        }
    }

    @Override
    public double calcolaVolume() {
        return Math.PI * Math.pow(raggio, 3) * 4.0 / 3.0;
    }

    @Override
    public String toString() {
        return "Sfera - r: " + raggio
               + "\n\t(capienza: " + calcolaVolume()
               + " liquido: " + getLiquido() + " qty: " + getQuantita() + ")";
    }
}
```

```java
// === Cilindro.java ===
public class Cilindro extends Contenitore {
    // OVERVIEW: contenitore cilindrico definito da altezza e raggio

    private final double altezza;
    private final double raggio;

    // AF(this): un cilindro di altezza 'altezza' e raggio 'raggio'
    // RI(this): altezza > 0 && raggio > 0

    public Cilindro(double altezza, double raggio) {
        super();
        this.altezza = altezza;
        this.raggio = raggio;
    }

    public Cilindro(double altezza, double raggio, String liquido, double quantita) {
        this.altezza = altezza;
        this.raggio = raggio;
        if (quantita > calcolaVolume()) {
            throw new CapacityException("Il cilindro ha capienza: "
                + calcolaVolume() + " ma il liquido ha un volume di: " + quantita);
        }
    }

    @Override
    public double calcolaVolume() {
        return altezza * Math.PI * Math.pow(raggio, 2);
    }

    @Override
    public String toString() {
        return "Cilindro - h: " + altezza + " r: " + raggio
               + "\n\t(capienza: " + calcolaVolume()
               + " liquido: " + getLiquido() + " qty: " + getQuantita() + ")";
    }
}
```

```java
// === Cuboide.java ===
public class Cuboide extends Contenitore {
    // OVERVIEW: contenitore a forma di parallelepipedo

    private final double a, b, c;

    // AF(this): un cuboide con lati a, b, c
    // RI(this): a > 0 && b > 0 && c > 0

    public Cuboide(double a, double b, double c) {
        super();
        this.a = a; this.b = b; this.c = c;
    }

    public Cuboide(double a, double b, double c, String liquido, double quantita) {
        this.a = a; this.b = b; this.c = c;
        if (quantita > calcolaVolume()) {
            throw new CapacityException("Il cuboide ha capienza: "
                + calcolaVolume() + " ma il liquido ha un volume di: " + quantita);
        }
    }

    @Override
    public double calcolaVolume() { return a * b * c; }

    @Override
    public String toString() {
        return "Cuboide - a: " + a + " b: " + b + " c: " + c
               + "\n\t(capienza: " + calcolaVolume()
               + " liquido: " + getLiquido() + " qty: " + getQuantita() + ")";
    }
}
```

```java
// === Vetreria.java ===
import java.util.*;

public class Vetreria implements Iterable<Contenitore> {
    // OVERVIEW: Vetreria e' un insieme MUTABILE di Contenitori.

    private final List<Contenitore> contenitori;

    // AF(this): una vetreria contenente i contenitori in this.contenitori
    // RI(this): contenitori != null && per ogni c in contenitori: c != null

    public Vetreria() {
        contenitori = new ArrayList<>();
    }

    /**
     * MODIFIES: this
     * EFFECTS: aggiunge c a this.
     */
    public void aggiungi(Contenitore c) {
        if (c == null) throw new NullPointerException();
        contenitori.add(c);
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
                it.remove();   // rimozione sicura durante iterazione
            }
        }
        return estratta;
    }

    @Override
    public Iterator<Contenitore> iterator() {
        List<Contenitore> copia = new ArrayList<>(contenitori);
        Collections.sort(copia);   // ordina per capienza (ordine naturale)
        return copia.iterator();
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

```java
// === Main.java ===
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Vetreria v = new Vetreria();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String riga = sc.nextLine().trim();
            if (riga.isEmpty()) continue;

            String[] parti = riga.split("\\s+");
            String liquido = parti[0];
            double quantita = Double.parseDouble(parti[1]);
            String tipo = parti[2];

            try {
                Contenitore c = null;
                switch (tipo) {
                    case "Sfera":
                        double r = Double.parseDouble(parti[3]);
                        c = new Sfera(r, liquido, quantita);
                        break;
                    case "Cuboide":
                        double a = Double.parseDouble(parti[3]);
                        double b = Double.parseDouble(parti[4]);
                        double cc = Double.parseDouble(parti[5]);
                        c = new Cuboide(a, b, cc, liquido, quantita);
                        break;
                    case "Cilindro":
                        // ATTENZIONE: input "Cilindro raggio altezza"
                        // Il README dice "raggio r e altezza h"
                        double rr = Double.parseDouble(parti[3]);  // primo: raggio
                        double h = Double.parseDouble(parti[4]);   // secondo: altezza
                        c = new Cilindro(h, rr, liquido, quantita);
                        // Costruttore: Cilindro(altezza, raggio, ...)
                        break;
                }
                if (c != null) v.aggiungi(c);
            } catch (CapacityException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();

        System.out.println(v);
    }
}
```

**NOTA SUL CILINDRO:** L'input e' `Cilindro raggio altezza` ma il costruttore e' `Cilindro(altezza, raggio)`. Attenzione all'ordine dei parametri nel parsing!

---

## 28. Soluzione completa: Esame Dicembre – Calendario Sorprese

### Specifica

- `Sorpresa` abstract: Cioccolatino, Giocattolo
- `Calendario`: 31 caselle, inserisci, apri (rimuove), costo, iterator per costo
- `GiornoException` (checked), `SorpresaException` (unchecked)
- equals su **nome**, compareTo su **costo**
- Costo: Cioccolatino = `percentualeCacao / 10`, Giocattolo = `numero parole descrizione`

### Implementazione completa

```java
// === GiornoException.java ===
public class GiornoException extends Exception {
    public GiornoException(String msg) { super(msg); }
}

// === SorpresaException.java ===
public class SorpresaException extends RuntimeException {
    public SorpresaException(String msg) { super(msg); }
}
```

```java
// === Sorpresa.java ===
public abstract class Sorpresa implements Comparable<Sorpresa> {
    // OVERVIEW: Una Sorpresa e' definita dal suo nome.
    //           Due sorprese sono uguali se hanno lo stesso nome.

    private final String nome;

    // AF(this): una sorpresa di nome 'nome' con costo getCosto()
    // RI(this): nome != null && !nome.isEmpty()

    protected Sorpresa(String nome) {
        if (nome == null || nome.isEmpty())
            throw new IllegalArgumentException("Nome non valido");
        this.nome = nome;
    }

    public String getNome() { return nome; }
    public abstract double getCosto();

    @Override
    public int compareTo(Sorpresa altra) {
        return Double.compare(this.getCosto(), altra.getCosto());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sorpresa)) return false;
        Sorpresa s = (Sorpresa) o;
        return this.nome.equals(s.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }

    public static void main(String[] args) {
        Sorpresa s1 = new Cioccolatino("Lindt", 25);
        Sorpresa s2 = new Giocattolo("Yorick", "di infinita allegria");
        Sorpresa s3 = new Cioccolatino("Yorick", 1);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println("I primi due sono uguali? " + s1.equals(s2));
        System.out.println("Gli ultimi due sono uguali? " + s2.equals(s3));
        System.out.println("Il primo e' piu' grande del secondo? " + (s1.compareTo(s2) > 0));
    }
}
```

```java
// === Cioccolatino.java ===
public class Cioccolatino extends Sorpresa {
    // OVERVIEW: un cioccolatino con una percentuale di cacao

    private final int percentualeCacao;

    // AF(this): un cioccolatino di nome getNome() con cacao al percentualeCacao%
    // RI(this): percentualeCacao >= 0 && percentualeCacao <= 100

    public Cioccolatino(String nome, int percentualeCacao) {
        super(nome);
        this.percentualeCacao = percentualeCacao;
    }

    @Override
    public double getCosto() {
        return percentualeCacao / 10.0;
    }

    @Override
    public String toString() {
        return "Cioccolatino: " + getNome() + " (" + getCosto() + ") cacao: " + percentualeCacao + "%";
    }
}
```

```java
// === Giocattolo.java ===
public class Giocattolo extends Sorpresa {
    // OVERVIEW: un giocattolo con una descrizione

    private final String descrizione;

    // AF(this): un giocattolo di nome getNome() con descrizione 'descrizione'
    // RI(this): descrizione != null && !descrizione.isEmpty()

    public Giocattolo(String nome, String descrizione) {
        super(nome);
        this.descrizione = descrizione;
    }

    @Override
    public double getCosto() {
        return descrizione.split("\\s+").length;
    }

    @Override
    public String toString() {
        return "Giocattolo: " + getNome() + " (" + getCosto() + ") descrizione: " + descrizione;
    }
}
```

```java
// === Calendario.java ===
import java.util.*;

public class Calendario implements Iterable<Sorpresa> {
    // OVERVIEW: Un calendario di 31 giorni con caselle per sorprese

    private final Sorpresa[] caselle = new Sorpresa[31];

    // AF(this): un calendario dove il giorno i+1 contiene caselle[i]
    // RI(this): caselle != null && caselle.length == 31
    //           && non ci sono due sorprese equals tra loro

    /**
     * MODIFIES: this
     * EFFECTS: inserisce s nel giorno specificato.
     *          Lancia GiornoException (checked) se il giorno e' occupato.
     *          Lancia SorpresaException (unchecked) se la sorpresa e' gia' presente.
     */
    public void inserisci(int giorno, Sorpresa s) throws GiornoException {
        if (giorno < 1 || giorno > 31)
            throw new IllegalArgumentException("Giorno non valido");

        // Controlla se la sorpresa esiste gia'
        for (Sorpresa esistente : caselle) {
            if (esistente != null && esistente.equals(s))
                throw new SorpresaException("Questa sorpresa gia' presente nel calendario");
        }

        // Controlla se il giorno e' occupato
        if (caselle[giorno - 1] != null)
            throw new GiornoException("Una sorpresa gia' presente per il giorno");

        caselle[giorno - 1] = s;
    }

    /**
     * MODIFIES: this
     * EFFECTS: apre la casella del giorno, restituisce e rimuove la sorpresa.
     *          Lancia GiornoException se il giorno e' vuoto.
     */
    public Sorpresa apri(int giorno) throws GiornoException {
        if (giorno < 1 || giorno > 31)
            throw new IllegalArgumentException("Giorno non valido");
        if (caselle[giorno - 1] == null)
            throw new GiornoException("Nessuna sorpresa per il giorno");
        Sorpresa s = caselle[giorno - 1];
        caselle[giorno - 1] = null;
        return s;
    }

    public double costo() {
        double totale = 0;
        for (Sorpresa s : caselle) {
            if (s != null) totale += s.getCosto();
        }
        return totale;
    }

    @Override
    public Iterator<Sorpresa> iterator() {
        List<Sorpresa> ordinate = new ArrayList<>();
        for (Sorpresa s : caselle) {
            if (s != null) ordinate.add(s);
        }
        Collections.sort(ordinate);   // ordina per costo
        return ordinate.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Calendario delle sorprese (" + costo() + ")\n");
        for (Sorpresa s : this) {   // usa iterator → ordine per costo
            sb.append("\t").append(s).append("\n");
        }
        return sb.toString();
    }
}
```

```java
// === Test.java ===
import java.util.*;

public class Test {
    public static void main(String[] args) {
        Calendario cal = new Calendario();
        Scanner sc = new Scanner(System.in);

        System.out.println("Inserisci sorpresa o apri giorno (Ctrl+D termina)");
        System.out.println("\tinserisci <giorno> Cioccolatino <nome> <% cacao>");
        System.out.println("\tinserisci <giorno> Giocattolo <nome> <descrizione>");
        System.out.println("\tapri <giorno>");
        System.out.println();

        while (sc.hasNextLine()) {
            String riga = sc.nextLine().trim();
            if (riga.isEmpty()) continue;

            String[] parti = riga.split("\\s+");

            try {
                switch (parti[0]) {
                    case "inserisci":
                        int giorno = Integer.parseInt(parti[1]);
                        String tipo = parti[2];
                        String nome = parti[3];

                        switch (tipo) {
                            case "Cioccolatino":
                                int cacao = Integer.parseInt(parti[4]);
                                cal.inserisci(giorno, new Cioccolatino(nome, cacao));
                                break;
                            case "Giocattolo":
                                // La descrizione e' tutto dopo il nome
                                StringBuilder desc = new StringBuilder();
                                for (int i = 4; i < parti.length; i++) {
                                    if (i > 4) desc.append(" ");
                                    desc.append(parti[i]);
                                }
                                cal.inserisci(giorno, new Giocattolo(nome, desc.toString()));
                                break;
                        }
                        break;

                    case "apri":
                        int g = Integer.parseInt(parti[1]);
                        Sorpresa trovata = cal.apri(g);
                        System.out.println("\ttrovato: " + trovata);
                        break;
                }
            } catch (GiornoException | SorpresaException e) {
                System.out.println("\tEccezione: " + e.getMessage());
            }
        }
        sc.close();

        System.out.println();
        System.out.print(cal);
    }
}
```

---

## 29. Soluzione completa: Esame Gennaio – Olimpiadi Invernali

### Specifica

- `Evento` abstract: Cerimonia, Gara
- `Olimpiade`: 19 giorni, inserisci, rimuovi, toString per giorno, iterator per durata
- `GiornoException` (checked), `EventoException` (unchecked)
- equals su **nome**, compareTo su **durata**
- Durata: Cerimonia = 60 fisso, Gara = 5 \* numAtleti
- Vincolo speciale: Cerimonia apertura SOLO giorno 1, chiusura SOLO giorno 19
- Anno da args[0], comandi da stdin
- **toString() e iterator() hanno ordini DIVERSI!**

### Implementazione completa

```java
// === GiornoException.java ===
public class GiornoException extends Exception {
    public GiornoException(String msg) { super(msg); }
}

// === EventoException.java ===
public class EventoException extends RuntimeException {
    public EventoException(String msg) { super(msg); }
}
```

```java
// === Evento.java ===
public abstract class Evento implements Comparable<Evento> {
    // OVERVIEW: Un evento delle olimpiadi invernali, definito dal nome.
    //           Due eventi sono uguali se hanno lo stesso nome.

    private final String nome;

    // AF(this): un evento di nome 'nome' con durata getDurata()
    // RI(this): nome != null && !nome.isEmpty()

    protected Evento(String nome) {
        if (nome == null || nome.isEmpty())
            throw new IllegalArgumentException("Nome non valido");
        this.nome = nome;
    }

    public String getNome() { return nome; }
    public abstract int getDurata();

    @Override
    public int compareTo(Evento altro) {
        return Integer.compare(this.getDurata(), altro.getDurata());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evento)) return false;
        Evento e = (Evento) o;
        return this.nome.equals(e.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }

    public static void main(String[] args) {
        Evento e1 = new Cerimonia("Apertura2026", true);
        Evento e2 = new Gara("Slittino1", 15);
        Evento e3 = new Cerimonia("Slittino1", false);

        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);
        System.out.println("I primi due sono uguali? " + e1.equals(e2));
        System.out.println("Gli ultimi due sono uguali? " + e2.equals(e3));
        System.out.println("Il primo e' piu' grande del secondo? " + (e1.compareTo(e2) > 0));
    }
}
```

```java
// === Cerimonia.java ===
public class Cerimonia extends Evento {
    // OVERVIEW: una cerimonia di apertura o chiusura, durata 60 minuti

    private final boolean apertura;

    // AF(this): una cerimonia di (apertura?"apertura":"chiusura")
    //           di nome getNome() con durata 60
    // RI(this): (nessun vincolo aggiuntivo)

    public Cerimonia(String nome, boolean apertura) {
        super(nome);
        this.apertura = apertura;
    }

    public boolean isApertura() { return apertura; }

    @Override
    public int getDurata() { return 60; }

    @Override
    public String toString() {
        return "Cerimonia " + (apertura ? "apertura" : "chiusura")
               + " nome: " + getNome() + " durata: " + getDurata();
    }
}
```

```java
// === Gara.java ===
public class Gara extends Evento {
    // OVERVIEW: una gara con un certo numero di atleti, durata = 5 * atleti

    private final int numAtleti;

    // AF(this): una gara di nome getNome() con numAtleti atleti
    // RI(this): numAtleti > 0

    public Gara(String nome, int numAtleti) {
        super(nome);
        if (numAtleti <= 0)
            throw new IllegalArgumentException("Numero atleti non valido");
        this.numAtleti = numAtleti;
    }

    @Override
    public int getDurata() { return 5 * numAtleti; }

    @Override
    public String toString() {
        return "Gara nome: " + getNome() + " durata: " + getDurata()
               + " con " + numAtleti + " atleti";
    }
}
```

```java
// === Olimpiade.java ===
import java.util.*;

public class Olimpiade implements Iterable<Evento> {
    // OVERVIEW: Un'olimpiade invernale con 19 giorni di eventi

    private final int anno;
    private final Evento[] eventi = new Evento[19];

    // AF(this): un'olimpiade dell'anno 'anno' dove il giorno i+1 ha l'evento eventi[i]
    // RI(this): anno > 0 && eventi != null && eventi.length == 19
    //           && non ci sono due eventi equals tra loro
    //           && se eventi[0] e' Cerimonia, e' di apertura
    //           && se eventi[18] e' Cerimonia, e' di chiusura

    public Olimpiade(int anno) {
        this.anno = anno;
    }

    /**
     * MODIFIES: this
     * EFFECTS: inserisce e nel giorno specificato.
     *          Lancia GiornoException se il giorno e' occupato,
     *          o se e' Cerimonia apertura e giorno != 1,
     *          o se e' Cerimonia chiusura e giorno != 19.
     *          Lancia EventoException se l'evento e' gia' presente.
     */
    public void inserisci(int giorno, Evento e) throws GiornoException {
        if (giorno < 1 || giorno > 19)
            throw new IllegalArgumentException("Giorno non valido");

        // Controlla vincoli Cerimonia
        if (e instanceof Cerimonia) {
            Cerimonia cer = (Cerimonia) e;
            if (cer.isApertura() && giorno != 1)
                throw new GiornoException("cerimonia nel giorno sbagliato");
            if (!cer.isApertura() && giorno != 19)
                throw new GiornoException("cerimonia nel giorno sbagliato");
        }

        // Controlla se l'evento esiste gia'
        for (Evento esistente : eventi) {
            if (esistente != null && esistente.equals(e))
                throw new EventoException("evento esiste");
        }

        // Controlla se il giorno e' occupato
        if (eventi[giorno - 1] != null)
            throw new GiornoException("un evento gia' presente per il giorno");

        eventi[giorno - 1] = e;
    }

    /**
     * MODIFIES: this
     * EFFECTS: rimuove l'evento del giorno specificato.
     *          Lancia GiornoException se il giorno e' vuoto.
     */
    public void rimuovi(int giorno) throws GiornoException {
        if (giorno < 1 || giorno > 19)
            throw new IllegalArgumentException("Giorno non valido");
        if (eventi[giorno - 1] == null)
            throw new GiornoException("giorno non esiste");
        eventi[giorno - 1] = null;
    }

    // toString: ordine per GIORNO
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Olimpiade Invernale " + anno + ":\n");
        for (int i = 0; i < eventi.length; i++) {
            if (eventi[i] != null) {
                sb.append("\t").append(i + 1).append(": ").append(eventi[i]).append("\n");
            }
        }
        return sb.toString();
    }

    // iterator: ordine per DURATA (ordine naturale) - DIVERSO da toString!
    @Override
    public Iterator<Evento> iterator() {
        List<Evento> presenti = new ArrayList<>();
        for (Evento e : eventi) {
            if (e != null) presenti.add(e);
        }
        Collections.sort(presenti);
        return presenti.iterator();
    }
}
```

```java
// === Test.java ===
import java.util.*;

public class Test {
    public static void main(String[] args) {
        int anno = Integer.parseInt(args[0]);   // da riga di comando!
        Olimpiade olimpiade = new Olimpiade(anno);

        Scanner sc = new Scanner(System.in);
        System.out.println("Comandi possibili (Ctrl+D termina)");
        System.out.println("\taggiungi <giorno> Cerimonia <nome> <apertura / chiusura>");
        System.out.println("\taggiungi <giorno> Gara <nome> <numero atleti>");
        System.out.println("\trimuovi <giorno>");
        System.out.println();

        while (sc.hasNextLine()) {
            String riga = sc.nextLine().trim();
            if (riga.isEmpty()) continue;

            String[] parti = riga.split("\\s+");

            try {
                switch (parti[0]) {
                    case "aggiungi":
                        int giorno = Integer.parseInt(parti[1]);
                        String tipo = parti[2];
                        String nome = parti[3];

                        switch (tipo) {
                            case "Cerimonia":
                                boolean apertura = parti[4].equals("apertura");
                                olimpiade.inserisci(giorno, new Cerimonia(nome, apertura));
                                break;
                            case "Gara":
                                int atleti = Integer.parseInt(parti[4]);
                                olimpiade.inserisci(giorno, new Gara(nome, atleti));
                                break;
                        }
                        break;

                    case "rimuovi":
                        int g = Integer.parseInt(parti[1]);
                        olimpiade.rimuovi(g);
                        break;
                }
            } catch (GiornoException | EventoException e) {
                System.out.println("\tECCEZIONE: " + e.getMessage());
            }
        }
        sc.close();

        System.out.println();
        System.out.print(olimpiade);

        System.out.println("\nEventi in ordine di durata:");
        for (Evento e : olimpiade) {
            System.out.println("\t" + e);
        }
    }
}
```

---

## 30. ScalaValutazione, strategia e checklist

### Distribuzione punti (32 totali – Simulazione Vetreria)

```
Contenitore (classe base):           7.5 punti
├── classe astratta                   0.5
├── overview                          0.5
├── rappresentazione                  1.5
├── costruttore                       1.0
├── versa                             1.5
├── compare                           1.0
├── clone                             0.5
├── AF                                0.5
└── RI                                0.5

Ogni Sottoclasse (x3):               4.0 punti ciascuna = 12 totale
├── extends Contenitore               0.5
├── overview                          0.5
├── rappresentazione                  1.5
├── costruttore                       1.0
└── AF                                0.5

Vetreria (collezione):               8.5 punti
├── overview                          0.5
├── rappresentazione                  1.0
├── costruttore                       0.5
├── aggiungi                          1.0
├── estrai                            1.5
├── ottimizza                         1.5
├── iterator                          1.5
├── AF                                0.5
└── RI                                0.5

Eccezioni:                            2.0 punti
├── LiquidsException (checked)        1.0
└── CapacityException (unchecked)     1.0

Main:                                 2.0 punti
├── scanner                           0.5
├── splitting                         0.5
├── dispatching                       0.5
└── try-catch                         0.5

TOTALE: 7.5 + 12 + 8.5 + 2 + 2 = 32 punti
```

### Discrepanze note

- ScalaValutazione dice "extends Figura" → il README dice "extends Contenitore". **Segui il README.**
- ScalaValutazione inverte checked/unchecked → **il README ha ragione.**

### Punti facili (6-8 punti in pochi minuti)

| Cosa scrivere           | Dove               | Punti |
| ----------------------- | ------------------ | ----- |
| `abstract class`        | Classe padre       | 0.5   |
| `OVERVIEW`              | Ogni classe        | 0.5xN |
| `AF(this):`             | Ogni classe        | 0.5xN |
| `RI(this):`             | Padre + Collezione | 0.5x2 |
| Campi `private`         | Ogni classe        | 0.5xN |
| Campi `final`           | Sottoclassi        | 0.5xN |
| `extends` corretto      | Sottoclassi        | 0.5xN |
| `implements Comparable` | Classe padre       | 0.5   |
| `implements Iterable`   | Collezione         | 0.5   |

### Ordine di implementazione consigliato

```
1. ECCEZIONI                              [2-3 min, 2 pt]
   → 3 righe ciascuna, extends Exception / RuntimeException

2. CLASSE ASTRATTA                        [25-30 min, 7.5 pt]
   → abstract class + OVERVIEW + campi + AF + RI
   → Costruttore protected + controlli
   → Metodo astratto + compareTo + clone + equals + hashCode
   → toString

3. SOTTOCLASSI                            [15-20 min, 4 pt cad.]
   → extends + OVERVIEW + campi final + AF
   → Costruttore con super() + metodo astratto + toString

4. COLLEZIONE                             [25-30 min, 8.5 pt]
   → implements Iterable + OVERVIEW + AF + RI
   → aggiungi con eccezioni + estrai/rimuovi con Iterator.remove()
   → iterator() con copia + sort + toString

5. MAIN                                   [10-15 min, 2 pt]
   → Scanner + split + switch + try-catch
```

### Checklist pre-consegna

```
CLASSE ASTRATTA BASE:
[ ] Ha "abstract" nella dichiarazione
[ ] Ha OVERVIEW
[ ] Campi private
[ ] Costruttore protected con controlli
[ ] Ha almeno un metodo abstract
[ ] implements Comparable<T>
[ ] compareTo() con Double.compare o Integer.compare
[ ] clone() override come public (se richiesto)
[ ] equals() + hashCode() coerenti (se basato su nome)
[ ] AF e RI scritti come commenti
[ ] toString()

SOTTOCLASSI:
[ ] extends la classe base
[ ] Campi private final
[ ] Costruttore chiama super()
[ ] Metodo astratto implementato (@Override)
[ ] toString() con formato ESATTO del README
[ ] AF specifica

ECCEZIONI:
[ ] Checked: extends Exception
[ ] Unchecked: extends RuntimeException
[ ] Costruttore con super(msg)
[ ] Il tipo corrisponde al README

COLLEZIONE:
[ ] implements Iterable<T>
[ ] Struttura dati interna (ArrayList o array)
[ ] aggiungi/inserisci con controlli e eccezioni
[ ] estrai/rimuovi/apri funziona correttamente
[ ] iterator(): copia → sort → return copia.iterator()
[ ] toString() con formato ESATTO
[ ] AF e RI

MAIN/TEST:
[ ] Scanner per stdin
[ ] args[] se richiesto (es. anno Olimpiade)
[ ] split("\\s+") per dividere la riga
[ ] Switch per tipo di comando
[ ] try-catch per eccezioni
[ ] Ordine parametri corretto (es. Cilindro!)
[ ] Output corrisponde ESATTAMENTE all'esempio

CONFRONTO 3 ESAMI:
|                      | Simulazione    | Dicembre          | Gennaio                |
|----------------------|----------------|-------------------|------------------------|
| Tema                 | Vetreria       | Calendario        | Olimpiadi              |
| Padre astratto       | Contenitore    | Sorpresa          | Evento                 |
| Sottoclassi          | Sfera,Cil,Cub  | Cioccol,Giocatt   | Cerimonia,Gara         |
| Collezione           | Vetreria(List) | Calendario(arr31) | Olimpiade(arr19)       |
| equals su            | (non esplicito)| nome              | nome                   |
| compareTo su         | capienza       | costo             | durata                 |
| Metodo astratto      | calcolaVolume  | getCosto          | getDurata              |
| Checked exception    | LiquidsExc     | GiornoExc         | GiornoExc              |
| Unchecked exception  | CapacityExc    | SorpresaExc       | EventoExc              |
| Input Main           | stdin          | stdin             | args(anno) + stdin     |
| toString vs iterator | stesso ordine  | diverso           | DIVERSO (giorno/durata)|
| Regola speciale      | ottimizza      | -                 | Cerimonia g1/g19       |
```

**Consiglio finale:** Se riesci a scrivere tutti e 3 gli esami da zero senza consultare nulla, sei pronto per il 30.
