## 7. Astrazione sui Tipi: Implementazione (L06 — PDJ Cap. 5.2-5.4)

### 7.1 Implementazione dei Costruttori

L'implementazione di un tipo astratto deve rispettare la specifica.
I costruttori inizializzano la rappresentazione interna dell'oggetto.

```java
public class IntSet {
    // Rappresentazione interna (rep)
    private List<Integer> elementi;

    /**
     * EFFECTS: crea un nuovo IntSet vuoto
     */
    public IntSet() {
        elementi = new ArrayList<>();
    }

    // Costruttore privato per uso interno (es. clone)
    private IntSet(List<Integer> els) {
        elementi = new ArrayList<>(els);
    }
}
```

### 7.2 Factory Methods

I **factory methods** sono metodi statici che creano istanze della classe,
offrendo maggiore flessibilità rispetto ai costruttori.

Vantaggi:

- Possono avere nomi significativi
- Possono restituire sottotipi
- Possono restituire istanze cachate
- Non sono obbligati a creare un nuovo oggetto ogni volta

```java
public class Poly {
    private int[] coefficienti;
    private int grado;

    // Costruttore privato
    private Poly(int[] coefficienti) {
        this.coefficienti = coefficienti;
        this.grado = coefficienti.length - 1;
    }

    // Factory method: polinomio zero
    public static Poly zero() {
        return new Poly(new int[]{0});
    }

    // Factory method: monomio c*x^n
    public static Poly monomio(int c, int n) {
        if (n < 0) throw new IllegalArgumentException("Grado negativo");
        int[] coeff = new int[n + 1];
        coeff[n] = c;
        return new Poly(coeff);
    }

    // Factory method: da array di coefficienti
    public static Poly of(int... coefficienti) {
        if (coefficienti.length == 0) return zero();
        return new Poly(coefficienti.clone());
    }
}
```

### 7.3 Override di equals()

Il metodo `equals()` ereditato da `Object` confronta i riferimenti (identità).
Per i tipi astratti, spesso vogliamo confrontare lo stato (equivalenza).

**Contratto di equals** (da Object):

1. **Riflessività**: `x.equals(x)` → true
2. **Simmetria**: `x.equals(y) ↔ y.equals(x)`
3. **Transitività**: se `x.equals(y)` e `y.equals(z)` allora `x.equals(z)`
4. **Consistenza**: chiamate ripetute danno lo stesso risultato
5. **Non-null**: `x.equals(null)` → false

```java
public class IntSet {
    private List<Integer> elementi;

    @Override
    public boolean equals(Object obj) {
        // 1. Controllo identità
        if (this == obj) return true;

        // 2. Controllo tipo (include null check)
        if (!(obj instanceof IntSet)) return false;

        // 3. Cast
        IntSet other = (IntSet) obj;

        // 4. Confronto stato
        if (this.elementi.size() != other.elementi.size()) return false;

        // Verifica che tutti gli elementi di this siano in other
        for (int e : this.elementi) {
            if (!other.elementi.contains(e)) return false;
        }
        return true;
    }
}
```

Pattern moderno con `instanceof` pattern matching (Java 16+):

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj instanceof IntSet other) {  // pattern matching
        return this.size() == other.size()
            && this.elementi.containsAll(other.elementi);
    }
    return false;
}
```

### 7.4 Override di hashCode()

**Regole di hashCode**:

1. Se `x.equals(y)` allora `x.hashCode() == y.hashCode()` (obbligatorio)
2. Se `!x.equals(y)`, è preferibile che `x.hashCode() != y.hashCode()` (per performance)
3. Consistenza: chiamate ripetute sullo stesso oggetto invariato danno stesso risultato

Se si ridefinisce `equals`, si DEVE ridefinire `hashCode`.

```java
public class Punto {
    private final double x;
    private final double y;

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Punto other)) return false;
        return Double.compare(this.x, other.x) == 0
            && Double.compare(this.y, other.y) == 0;
    }
}

// Per IntSet: l'hashCode deve essere indipendente dall'ordine degli elementi
public class IntSet {
    @Override
    public int hashCode() {
        int hash = 0;
        for (int e : elementi) {
            hash += Integer.hashCode(e); // somma: ordine-indipendente
        }
        return hash;
    }
}
```

### 7.5 Override di toString()

Il metodo `toString()` restituisce una rappresentazione testuale dell'oggetto.
Di default restituisce `NomeClasse@hashCode`.

```java
public class IntSet {
    @Override
    public String toString() {
        if (elementi.isEmpty()) return "IntSet: {}";
        StringBuilder sb = new StringBuilder("IntSet: {");
        for (int i = 0; i < elementi.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(elementi.get(i));
        }
        sb.append("}");
        return sb.toString();
    }
}

public class Poly {
    @Override
    public String toString() {
        if (grado == 0) return "" + coefficienti[0];
        StringBuilder sb = new StringBuilder();
        for (int i = grado; i >= 0; i--) {
            if (coefficienti[i] == 0) continue;
            if (sb.length() > 0 && coefficienti[i] > 0) sb.append(" + ");
            if (sb.length() > 0 && coefficienti[i] < 0) sb.append(" - ");
            int c = Math.abs(coefficienti[i]);
            if (i == 0 || c != 1) sb.append(sb.length() == 0 ? coefficienti[i] : c);
            if (i == 1) sb.append("x");
            if (i > 1) sb.append("x^").append(i);
        }
        return sb.toString();
    }
}
```

### 7.6 Override di clone() — Shallow vs Deep Copy

Il metodo `clone()` crea una copia dell'oggetto. Esistono due tipi di copia:

- **Shallow copy** (copia superficiale): copia i riferimenti, non gli oggetti puntati
- **Deep copy** (copia profonda): copia ricorsivamente anche gli oggetti referenziati

```java
public class IntSet implements Cloneable {
    private List<Integer> elementi;

    // SHALLOW COPY: lista condivisa (PERICOLOSO per tipi mutabili!)
    public IntSet shallowCopy() {
        IntSet copia = new IntSet();
        copia.elementi = this.elementi; // stesso oggetto List!
        return copia;
    }

    // DEEP COPY: lista indipendente
    @Override
    public IntSet clone() {
        try {
            IntSet copia = (IntSet) super.clone();
            copia.elementi = new ArrayList<>(this.elementi); // nuova lista
            return copia;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // non dovrebbe mai succedere
        }
    }
}

// Esempio del pericolo della shallow copy
IntSet originale = new IntSet();
originale.insert(1);
originale.insert(2);

IntSet shallow = originale.shallowCopy();
shallow.insert(3);
// ATTENZIONE: anche originale.contains(3) == true! Stessa lista!

IntSet deep = originale.clone();
deep.insert(4);
// originale.contains(4) == false → liste indipendenti
```

Per tipi immutabili, la shallow copy è sufficiente (il contenuto non può cambiare).

```java
public class Poly {
    private final int[] coefficienti;

    // Copy constructor (alternativa a clone)
    public Poly(Poly other) {
        this.coefficienti = other.coefficienti.clone(); // copia array
        this.grado = other.grado;
    }
}
```

### 7.7 L'interfaccia Cloneable

`Cloneable` è un'interfaccia marker (senza metodi).
Se una classe implementa `Cloneable`, `Object.clone()` esegue una shallow copy campo per campo.
Se non la implementa, `Object.clone()` lancia `CloneNotSupportedException`.

Problemi con clone():

- Design problematico (interfaccia marker, eccezione checked)
- Difficile da implementare correttamente per gerarchie di classi
- Alternativa preferita: **copy constructor** o **factory method**

```java
// Copy constructor (alternativa preferita a clone)
public class Punto {
    private final double x, y;

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Copy constructor
    public Punto(Punto altro) {
        this.x = altro.x;
        this.y = altro.y;
    }

    // Factory method per copia
    public static Punto copyOf(Punto altro) {
        return new Punto(altro.x, altro.y);
    }
}
```

### 7.8 Defensive Copying

Quando un tipo mutabile riceve o restituisce riferimenti a strutture dati interne,
bisogna fare copie difensive per proteggere l'incapsulamento.

```java
public class Registro {
    private List<String> studenti;

    public Registro(List<String> studenti) {
        // DEFENSIVE COPY in ingresso
        this.studenti = new ArrayList<>(studenti);
    }

    public List<String> getStudenti() {
        // DEFENSIVE COPY in uscita
        return new ArrayList<>(studenti);
        // oppure: return Collections.unmodifiableList(studenti);
    }
}

// Senza defensive copying:
List<String> lista = new ArrayList<>(List.of("Alice", "Bob"));
Registro r = new Registro(lista);
lista.add("Intruso");  // VIOLA l'incapsulamento! Modifica stato interno di r
```

---

## 8. Funzione di Astrazione e Invariante di Rappresentazione (L07 — PDJ Cap. 5.5+)

### 8.1 Spazio Concreto e Spazio Astratto

Per ogni tipo astratto distinguiamo due spazi:

- **Spazio concreto (C)**: l'insieme dei possibili stati della rappresentazione interna
  (i valori effettivi dei campi private)

- **Spazio astratto (A)**: l'insieme dei valori astratti che il tipo può rappresentare
  (ciò che il cliente "vede" attraverso la specifica)

Esempio per IntSet:

- Spazio concreto: tutte le possibili `List<Integer>` (incluse liste con duplicati)
- Spazio astratto: tutti i possibili insiemi di interi (senza duplicati)

### 8.2 Funzione di Astrazione (AF)

La **funzione di astrazione** AF: C → A mappa ogni stato concreto al valore astratto
che rappresenta.

Proprietà di AF:

- **Suriettiva**: ogni valore astratto è rappresentato da almeno uno stato concreto
- **Non iniettiva** (in generale): più stati concreti possono rappresentare lo stesso valore astratto

```java
public class IntSet {
    private List<Integer> elementi;

    // AF(c) = { c.elementi.get(i) | 0 <= i < c.elementi.size() }
    //
    // Esempio: se elementi = [3, 1, 2]
    //   AF([3, 1, 2]) = {1, 2, 3}
    //
    // AF non è iniettiva:
    //   AF([1, 2, 3]) = {1, 2, 3}  (stesso valore astratto)
    //   AF([3, 2, 1]) = {1, 2, 3}  (l'ordine non conta per un insieme)
}
```

```java
public class Poly {
    private int[] coefficienti;  // coefficienti[i] = coeff di x^i
    private int grado;

    // AF(c) = c.coefficienti[0] + c.coefficienti[1]*x + ... + c.coefficienti[c.grado]*x^c.grado
    //
    // Esempio: se coefficienti = [1, 0, 3], grado = 2
    //   AF([1, 0, 3], 2) = 1 + 3x²
}
```

### 8.3 Invariante di Rappresentazione (RI)

L'**invariante di rappresentazione** RI: C → boolean è un predicato che definisce
quali stati concreti sono "legali" (validi).

Solo gli stati concreti che soddisfano RI sono rappresentazioni valide del tipo astratto.

```java
public class IntSet {
    private List<Integer> elementi;

    // RI: elementi != null
    //     && per ogni i,j con 0 <= i < j < elementi.size():
    //        elementi.get(i) != elementi.get(j)
    // (no duplicati e lista non null)

    private boolean repOk() {
        if (elementi == null) return false;
        Set<Integer> visti = new HashSet<>();
        for (int e : elementi) {
            if (!visti.add(e)) return false; // duplicato trovato
        }
        return true;
    }
}
```

```java
public class Poly {
    private int[] coefficienti;
    private int grado;

    // RI: coefficienti != null
    //     && coefficienti.length >= 1
    //     && grado == coefficienti.length - 1
    //     && (grado == 0 || coefficienti[grado] != 0)
    // (il coefficiente di grado massimo non è zero, tranne per il polinomio zero)

    private boolean repOk() {
        if (coefficienti == null || coefficienti.length < 1) return false;
        if (grado != coefficienti.length - 1) return false;
        if (grado > 0 && coefficienti[grado] == 0) return false;
        return true;
    }
}
```

### 8.4 Il metodo repOk()

Il metodo `repOk()` verifica programmaticamente l'invariante di rappresentazione.
Si usa per il debugging: si può chiamare alla fine di ogni costruttore e mutator.

```java
public class IntSet {
    private List<Integer> elementi;

    private boolean repOk() {
        if (elementi == null) return false;
        for (int i = 0; i < elementi.size(); i++) {
            for (int j = i + 1; j < elementi.size(); j++) {
                if (elementi.get(i).equals(elementi.get(j))) return false;
            }
        }
        return true;
    }

    public IntSet() {
        elementi = new ArrayList<>();
        assert repOk();  // verifica RI dopo costruzione
    }

    public void insert(int x) {
        if (!contains(x)) {
            elementi.add(x);
        }
        assert repOk();  // verifica RI dopo mutazione
    }

    public void remove(int x) {
        elementi.remove(Integer.valueOf(x));
        assert repOk();  // verifica RI dopo mutazione
    }
}
```

### 8.5 Tre Livelli di Verifica

Per dimostrare che un'implementazione preserva il RI, verifichiamo tre livelli:

#### 1. Creator (Costruttori e Factory Methods)

Devono creare oggetti che soddisfano il RI.

```java
// Dopo il costruttore: elementi = [], nessun duplicato → RI soddisfatto ✓
public IntSet() {
    elementi = new ArrayList<>();
    assert repOk();
}
```

#### 2. Mutator e Producer (Metodi che modificano o creano)

Assumendo che il RI vale prima della chiamata, deve valere anche dopo.

```java
// Assumendo RI vale (no duplicati):
// insert aggiunge x solo se non presente → RI preservato ✓
public void insert(int x) {
    if (!contains(x)) {     // verifica prima di aggiungere
        elementi.add(x);
    }
    assert repOk();
}
```

#### 3. Observer (Metodi che leggono)

Assumendo che il RI vale, devono restituire risultati consistenti con la AF.

```java
// Assumendo RI vale:
// size() restituisce il numero di elementi distinti (= dimensione lista senza duplicati)
// Questo è consistente con AF che mappa a un insieme
public int size() {
    return elementi.size(); // corretto perché RI garantisce no duplicati
}
```

### 8.6 Esempio Completo con AF e RI

```java
/**
 * OVERVIEW: Intervallo mutabile [lo, hi] di interi.
 *           Un Intervallo tipico è [lo, hi] con lo <= hi.
 */
public class Intervallo {
    private int lo;
    private int hi;

    // AF(c) = l'insieme { x ∈ Z | c.lo <= x <= c.hi }
    // RI: lo <= hi

    private boolean repOk() {
        return lo <= hi;
    }

    /**
     * REQUIRES: a <= b
     * EFFECTS: crea l'intervallo [a, b]
     */
    public Intervallo(int a, int b) {
        if (a > b) throw new IllegalArgumentException("a > b");
        this.lo = a;
        this.hi = b;
        assert repOk();
    }

    /**
     * MODIFIES: this
     * EFFECTS: estende this per includere x
     *          this_post = [min(lo, x), max(hi, x)]
     */
    public void estendi(int x) {
        if (x < lo) lo = x;
        if (x > hi) hi = x;
        assert repOk(); // RI preservato ✓
    }

    /**
     * EFFECTS: restituisce true se x ∈ this
     */
    public boolean contiene(int x) {
        return lo <= x && x <= hi;
    }

    /**
     * EFFECTS: restituisce la dimensione dell'intervallo (hi - lo + 1)
     */
    public int dimensione() {
        return hi - lo + 1;
    }

    @Override
    public String toString() {
        return "[" + lo + ", " + hi + "]";
    }
}
```

### 8.7 Record Types in Java (Java 16+)

I **record** sono classi immutabili concise, con AF e RI impliciti.

```java
// Equivale a: classe immutabile con campi final, costruttore, equals, hashCode, toString
public record Punto(double x, double y) {
    // Costruttore compatto per validazione (impone RI)
    public Punto {
        if (Double.isNaN(x) || Double.isNaN(y)) {
            throw new IllegalArgumentException("Coordinate NaN");
        }
    }

    // Metodi aggiuntivi
    public double distanzaDaOrigine() {
        return Math.sqrt(x * x + y * y);
    }

    public Punto trasla(double dx, double dy) {
        return new Punto(x + dx, y + dy); // nuovo oggetto (immutabile)
    }
}

// Il compilatore genera automaticamente:
// - costruttore Punto(double x, double y)
// - metodi x() e y() (accessor, non getX/getY)
// - equals(), hashCode(), toString()

Punto p = new Punto(3, 4);
System.out.println(p);        // Punto[x=3.0, y=4.0]
System.out.println(p.x());    // 3.0
Punto p2 = new Punto(3, 4);
System.out.println(p.equals(p2)); // true
```

```java
// Record con validazione complessa
public record Intervallo(int lo, int hi) {
    // RI: lo <= hi
    public Intervallo {
        if (lo > hi) throw new IllegalArgumentException("lo > hi");
    }

    public boolean contiene(int x) {
        return lo <= x && x <= hi;
    }

    public int dimensione() {
        return hi - lo + 1;
    }
}
```

---

## 9. IntSet e Poly (L08 — PDJ Cap. 5.1-5.7)

### 9.1 IntSet — Implementazione Completa

IntSet è un esempio archetipico di tipo astratto **mutabile** nel corso PDJ.

```java
/**
 * OVERVIEW: IntSet è un insieme mutabile e illimitato di interi.
 *           Un IntSet tipico è {x1, x2, ..., xn}.
 */
public class IntSet {

    // Rappresentazione
    private List<Integer> elementi;

    // AF(c) = { c.elementi.get(i) | 0 <= i < c.elementi.size() }
    // RI: c.elementi != null
    //     && per ogni 0 <= i < j < c.elementi.size():
    //        !c.elementi.get(i).equals(c.elementi.get(j))

    private boolean repOk() {
        if (elementi == null) return false;
        Set<Integer> seen = new HashSet<>();
        for (int e : elementi) {
            if (!seen.add(e)) return false;
        }
        return true;
    }

    // --- Creator ---

    /**
     * EFFECTS: inizializza this come insieme vuoto {}
     */
    public IntSet() {
        elementi = new ArrayList<>();
        assert repOk();
    }

    // --- Mutator ---

    /**
     * MODIFIES: this
     * EFFECTS: aggiunge x a this
     *          this_post = this_pre ∪ {x}
     */
    public void insert(int x) {
        if (!contains(x)) {
            elementi.add(x);
        }
        assert repOk();
    }

    /**
     * MODIFIES: this
     * EFFECTS: rimuove x da this
     *          this_post = this_pre \ {x}
     */
    public void remove(int x) {
        int index = elementi.indexOf(x);
        if (index >= 0) {
            // Ottimizzazione: swap con ultimo e rimuovi ultimo (O(1) vs O(n))
            int last = elementi.size() - 1;
            elementi.set(index, elementi.get(last));
            elementi.remove(last);
        }
        assert repOk();
    }

    // --- Observer ---

    /**
     * EFFECTS: restituisce true se x ∈ this
     */
    public boolean contains(int x) {
        return elementi.contains(x);
    }

    /**
     * EFFECTS: restituisce |this|
     */
    public int size() {
        return elementi.size();
    }

    /**
     * EFFECTS: restituisce true se this è vuoto
     */
    public boolean isEmpty() {
        return elementi.isEmpty();
    }

    /**
     * REQUIRES: this non è vuoto (size() > 0)
     * EFFECTS: restituisce un elemento arbitrario di this
     */
    public int choose() {
        if (isEmpty()) throw new IllegalStateException("IntSet vuoto");
        return elementi.get(elementi.size() - 1);
    }

    // --- Override ---

    @Override
    public String toString() {
        return "IntSet: " + elementi.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof IntSet other)) return false;
        if (this.size() != other.size()) return false;
        for (int e : this.elementi) {
            if (!other.contains(e)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (int e : elementi) hash += Integer.hashCode(e);
        return hash;
    }

    /**
     * EFFECTS: restituisce un iteratore sugli elementi di this
     */
    public Iterator<Integer> elements() {
        return Collections.unmodifiableList(
            new ArrayList<>(elementi)
        ).iterator();
    }
}
```

### 9.2 Utilizzo di IntSet

```java
IntSet s = new IntSet();
System.out.println(s);            // IntSet: []
System.out.println(s.isEmpty());  // true

s.insert(3);
s.insert(7);
s.insert(3);   // duplicato, ignorato
s.insert(1);
System.out.println(s);            // IntSet: [3, 7, 1]
System.out.println(s.size());     // 3
System.out.println(s.contains(7)); // true
System.out.println(s.contains(5)); // false

s.remove(7);
System.out.println(s);            // IntSet: [3, 1]
System.out.println(s.choose());   // 1 (ultimo elemento)

// Iterazione
Iterator<Integer> it = s.elements();
while (it.hasNext()) {
    System.out.println(it.next());
}
```

### 9.3 Poly — Implementazione Completa

Poly è un esempio archetipico di tipo astratto **immutabile** nel corso PDJ.

```java
/**
 * OVERVIEW: Poly rappresenta un polinomio immutabile a coefficienti interi.
 *           Un Poly tipico è c0 + c1*x + c2*x² + ... + cn*xⁿ.
 */
public class Poly {

    // Rappresentazione
    private final int[] trms;  // trms[i] = coefficiente di x^i
    private final int deg;     // grado del polinomio

    // AF(c) = c.trms[0] + c.trms[1]*x + c.trms[2]*x² + ... + c.trms[c.deg]*x^c.deg
    // RI: c.trms != null
    //     && c.trms.length >= 1
    //     && c.deg == c.trms.length - 1
    //     && (c.deg == 0 || c.trms[c.deg] != 0)

    private boolean repOk() {
        if (trms == null || trms.length < 1) return false;
        if (deg != trms.length - 1) return false;
        if (deg > 0 && trms[deg] == 0) return false;
        return true;
    }

    // --- Creator ---

    /**
     * EFFECTS: inizializza this come polinomio zero (0)
     */
    public Poly() {
        trms = new int[]{0};
        deg = 0;
        assert repOk();
    }

    /**
     * REQUIRES: n >= 0
     * EFFECTS: inizializza this come monomio c*x^n
     *          se c == 0, this è il polinomio zero
     */
    public Poly(int c, int n) {
        if (n < 0) throw new IllegalArgumentException("Grado negativo");
        if (c == 0) {
            trms = new int[]{0};
            deg = 0;
        } else {
            trms = new int[n + 1];
            trms[n] = c;
            deg = n;
        }
        assert repOk();
    }

    // Costruttore privato
    private Poly(int[] coefficienti) {
        // Trova il grado reale (elimina zeri in coda)
        int d = coefficienti.length - 1;
        while (d > 0 && coefficienti[d] == 0) d--;
        trms = new int[d + 1];
        System.arraycopy(coefficienti, 0, trms, 0, d + 1);
        deg = d;
        assert repOk();
    }

    // --- Producer ---

    /**
     * REQUIRES: q != null
     * EFFECTS: restituisce this + q
     */
    public Poly add(Poly q) {
        int maxDeg = Math.max(this.deg, q.deg);
        int[] risultato = new int[maxDeg + 1];
        for (int i = 0; i <= maxDeg; i++) {
            risultato[i] = this.coeff(i) + q.coeff(i);
        }
        return new Poly(risultato);
    }

    /**
     * EFFECTS: restituisce this - q
     */
    public Poly sub(Poly q) {
        return add(q.minus());
    }

    /**
     * REQUIRES: q != null
     * EFFECTS: restituisce this * q
     */
    public Poly mul(Poly q) {
        if (this.deg == 0 && this.trms[0] == 0) return new Poly();
        if (q.deg == 0 && q.trms[0] == 0) return new Poly();
        int[] risultato = new int[this.deg + q.deg + 1];
        for (int i = 0; i <= this.deg; i++) {
            for (int j = 0; j <= q.deg; j++) {
                risultato[i + j] += this.trms[i] * q.trms[j];
            }
        }
        return new Poly(risultato);
    }

    /**
     * EFFECTS: restituisce -this
     */
    public Poly minus() {
        int[] risultato = new int[trms.length];
        for (int i = 0; i < trms.length; i++) {
            risultato[i] = -trms[i];
        }
        return new Poly(risultato);
    }

    // --- Observer ---

    /**
     * EFFECTS: restituisce il grado di this
     */
    public int degree() {
        return deg;
    }

    /**
     * REQUIRES: d >= 0
     * EFFECTS: restituisce il coefficiente del termine x^d
     */
    public int coeff(int d) {
        if (d < 0) throw new IllegalArgumentException("Grado negativo");
        if (d > deg) return 0;
        return trms[d];
    }

    @Override
    public String toString() {
        if (deg == 0) return "" + trms[0];
        StringBuilder sb = new StringBuilder();
        for (int i = deg; i >= 0; i--) {
            if (trms[i] == 0) continue;
            if (sb.length() > 0) {
                sb.append(trms[i] > 0 ? " + " : " - ");
            } else if (trms[i] < 0) {
                sb.append("-");
            }
            int absC = Math.abs(trms[i]);
            if (i == 0 || absC != 1) sb.append(absC);
            if (i == 1) sb.append("x");
            else if (i > 1) sb.append("x^").append(i);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Poly other)) return false;
        return Arrays.equals(this.trms, other.trms);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(trms);
    }
}
```

### 9.4 Utilizzo di Poly

```java
Poly zero = new Poly();            // 0
Poly p1 = new Poly(3, 2);          // 3x²
Poly p2 = new Poly(1, 0);          // 1
Poly p3 = new Poly(2, 1);          // 2x

Poly somma = p1.add(p3).add(p2);   // 3x² + 2x + 1
System.out.println(somma);          // 3x^2 + 2x + 1
System.out.println(somma.degree()); // 2
System.out.println(somma.coeff(2)); // 3
System.out.println(somma.coeff(1)); // 2
System.out.println(somma.coeff(0)); // 1

// Immutabilità: p1 NON è stato modificato
System.out.println(p1);             // 3x^2

// Prodotto
Poly prodotto = p1.mul(p3);         // 3x² * 2x = 6x³
System.out.println(prodotto);       // 6x^3

// Negazione
Poly negato = somma.minus();        // -3x² - 2x - 1
```

### 9.5 AF e RI a confronto

| Aspetto    | IntSet                          | Poly                            |
| ---------- | ------------------------------- | ------------------------------- |
| Mutabilità | Mutabile                        | Immutabile                      |
| Rep        | List&lt;Integer&gt; elementi    | int[] trms, int deg             |
| AF         | Insieme degli elementi in lista | Polinomio con coefficienti trms |
| RI         | No duplicati, lista non null    | trms non null, grado coerente   |
| Mutator    | insert, remove                  | Nessuno                         |
| Producer   | Nessuno                         | add, sub, mul, minus            |

---

## 10. Gerarchie e Principio di Sostituzione (L09 — PDJ Cap. 7-7.7)

### 10.1 Gerarchia di Tipi

In Java, le gerarchie di tipi si costruiscono con **extends** (classi) e **implements** (interfacce).

```java
// Gerarchia semplice
public abstract class Forma {
    public abstract double area();
    public abstract double perimetro();
}

public class Cerchio extends Forma {
    private double raggio;

    public Cerchio(double raggio) {
        this.raggio = raggio;
    }

    @Override
    public double area() {
        return Math.PI * raggio * raggio;
    }

    @Override
    public double perimetro() {
        return 2 * Math.PI * raggio;
    }
}

public class Rettangolo extends Forma {
    private double base, altezza;

    public Rettangolo(double base, double altezza) {
        this.base = base;
        this.altezza = altezza;
    }

    @Override
    public double area() {
        return base * altezza;
    }

    @Override
    public double perimetro() {
        return 2 * (base + altezza);
    }
}
```

### 10.2 Principio di Sostituzione di Liskov (LSP)

Il **Liskov Substitution Principle (LSP)** afferma che:

> Se S è un sottotipo di T, allora gli oggetti di tipo T possono essere sostituiti
> con oggetti di tipo S senza alterare la correttezza del programma.

In altre parole: ovunque ci sia un `T`, posso mettere un `S` e il programma funziona correttamente.

```java
// Se LSP è rispettato, questo codice funziona con qualsiasi Forma
public static void stampaInfo(Forma f) {
    System.out.println("Area: " + f.area());
    System.out.println("Perimetro: " + f.perimetro());
}

stampaInfo(new Cerchio(5));       // funziona
stampaInfo(new Rettangolo(3, 4)); // funziona
```

### 10.3 Le tre regole del LSP

#### Regola 1: Regola della Firma (Signature Rule)

Il sottotipo deve supportare tutti i metodi del supertipo, con firme compatibili:

- **Controvarianza** degli argomenti: il sottotipo può accettare argomenti più generali
- **Covarianza** del tipo di ritorno: il sottotipo può restituire un tipo più specifico

```java
class Supertipo {
    public Number elabora(Integer input) { return 0; }
}

class Sottotipo extends Supertipo {
    // Covarianza ritorno: Integer è sottotipo di Number → OK
    @Override
    public Integer elabora(Integer input) { return 42; }
}
```

#### Regola 2: Regola dei Metodi (Methods Rule)

Il sottotipo deve soddisfare la specifica dei metodi del supertipo:

- **Precondizioni**: il sottotipo può avere precondizioni più deboli (uguali o meno restrittive)
- **Postcondizioni**: il sottotipo può avere postcondizioni più forti (uguali o più restrittive)

```java
class Contenitore {
    /**
     * REQUIRES: x >= 0
     * EFFECTS: restituisce true se x è valido
     */
    public boolean isValido(int x) {
        return x >= 0;
    }
}

class ContenitoreEsteso extends Contenitore {
    // OK: precondizione più debole (accetta anche negativi)
    // OK: postcondizione più forte (verifica range più stretto per i positivi)
    @Override
    public boolean isValido(int x) {
        // accetta tutti gli input (precondizione più debole)
        return x >= 0 && x <= 100; // postcondizione più forte per i validi
    }
}
```

#### Regola 3: Regola delle Proprietà (Properties Rule)

Il sottotipo deve preservare le invarianti del supertipo.
Se il supertipo ha una proprietà (es. immutabilità), il sottotipo deve mantenerla.

```java
// VIOLAZIONE: il supertipo è immutabile, il sottotipo introduce mutazione
public class StringaImmutabile {
    protected final String valore;

    public StringaImmutabile(String s) {
        this.valore = s;
    }

    public String getValore() { return valore; }
}

// VIOLAZIONE della regola delle proprietà!
// Se il supertipo garantisce immutabilità, il sottotipo non può romperla
```

### 10.4 Classi Astratte

Una **classe astratta** non può essere istanziata direttamente.
Può contenere metodi astratti (senza implementazione) e concreti.

```java
public abstract class Veicolo {
    private String targa;

    public Veicolo(String targa) {
        this.targa = targa;
    }

    // Metodo concreto: ereditato da tutti i sottotipi
    public String getTarga() { return targa; }

    // Metodo astratto: DEVE essere implementato dai sottotipi concreti
    public abstract double calcolaBollo();

    // Metodo concreto con comportamento di default
    public String tipo() {
        return this.getClass().getSimpleName();
    }
}

public class Auto extends Veicolo {
    private int cavalli;

    public Auto(String targa, int cavalli) {
        super(targa);
        this.cavalli = cavalli;
    }

    @Override
    public double calcolaBollo() {
        return cavalli * 3.0;
    }
}

public class Moto extends Veicolo {
    private int cilindrata;

    public Moto(String targa, int cilindrata) {
        super(targa);
        this.cilindrata = cilindrata;
    }

    @Override
    public double calcolaBollo() {
        return cilindrata * 0.05;
    }
}
```

### 10.5 Interfacce

Un'**interfaccia** definisce un contratto che le classi implementatrici devono rispettare.
Può contenere:

- Metodi astratti (impliciti)
- Metodi default (Java 8+)
- Metodi statici
- Costanti (public static final)

```java
public interface Comparable<T> {
    int compareTo(T other);
}

public interface Stampabile {
    void stampa();

    // Metodo default (Java 8+)
    default void stampaConBordo() {
        System.out.println("=================");
        stampa();
        System.out.println("=================");
    }
}

// Implementazione multipla di interfacce
public class Studente implements Comparable<Studente>, Stampabile {
    private String nome;
    private double media;

    public Studente(String nome, double media) {
        this.nome = nome;
        this.media = media;
    }

    @Override
    public int compareTo(Studente other) {
        return Double.compare(this.media, other.media);
    }

    @Override
    public void stampa() {
        System.out.println(nome + " (media: " + media + ")");
    }
}
```

### 10.6 Extends vs Implements

| Caratteristica        | extends (classi) | implements (interfacce) |
| --------------------- | ---------------- | ----------------------- |
| Ereditarietà multipla | No (solo 1)      | Sì (multiple)           |
| Stato (campi)         | Sì               | Solo costanti           |
| Costruttori           | Sì               | No                      |
| Metodi concreti       | Sì               | Solo default            |
| Keyword               | extends          | implements              |

```java
// Una classe può estendere UNA classe e implementare MOLTE interfacce
public class ArrayList<E> extends AbstractList<E>
    implements List<E>, RandomAccess, Cloneable, Serializable {
    // ...
}
```

### 10.7 Dispatching Dinamico nelle Gerarchie

```java
Veicolo[] flotta = {
    new Auto("AB123CD", 100),
    new Moto("EF456GH", 600),
    new Auto("IJ789KL", 150)
};

double totaleBollo = 0;
for (Veicolo v : flotta) {
    // Dispatching dinamico: il metodo corretto viene chiamato
    // basandosi sul tipo concreto di ciascun veicolo
    System.out.println(v.tipo() + " " + v.getTarga() + ": " + v.calcolaBollo());
    totaleBollo += v.calcolaBollo();
}
System.out.println("Totale bollo: " + totaleBollo);
```

---

## 11. Specifica, Ereditarietà e Composizione (L10 — PDJ Cap. 7.8+)

### 11.1 Visibilità nelle Gerarchie

I modificatori di accesso controllano la visibilità dei membri nelle gerarchie:

```java
public class Base {
    public int pubblico;        // visibile ovunque
    protected int protetto;     // visibile nelle sottoclassi e nello stesso package
    int packageLevel;           // visibile solo nello stesso package
    private int privato;        // visibile solo in Base
}

public class Derivata extends Base {
    public void metodo() {
        pubblico = 1;    // OK
        protetto = 2;    // OK (sottoclasse)
        // packageLevel = 3;  // OK solo se stesso package
        // privato = 4;   // ERRORE: non visibile
    }
}
```

Regola di overriding: il metodo ridefinito non può avere visibilità più restrittiva.

```java
class Animale {
    protected void verso() { }
}

class Cane extends Animale {
    @Override
    public void verso() { }     // OK: public è meno restrittivo di protected

    // @Override
    // private void verso() { }  // ERRORE: private è più restrittivo
}
```

### 11.2 Il Problema Square/Rectangle

Un classico esempio di violazione del LSP:

```java
public class Rettangolo {
    protected double base;
    protected double altezza;

    public void setBase(double b) { this.base = b; }
    public void setAltezza(double h) { this.altezza = h; }
    public double area() { return base * altezza; }
}

// VIOLAZIONE LSP: Quadrato non è un buon sottotipo di Rettangolo
public class Quadrato extends Rettangolo {
    @Override
    public void setBase(double b) {
        this.base = b;
        this.altezza = b; // forza altezza = base
    }

    @Override
    public void setAltezza(double h) {
        this.base = h;
        this.altezza = h; // forza base = altezza
    }
}

// Il problema:
Rettangolo r = new Quadrato();
r.setBase(5);
r.setAltezza(3);
// Per un Rettangolo ci aspettiamo area = 5 * 3 = 15
// Ma per un Quadrato: area = 3 * 3 = 9 → SORPRESA!
```

Soluzione: **non usare ereditarietà Quadrato extends Rettangolo**.
Usare composizione o un'interfaccia comune Forma.

### 11.3 Composizione vs Ereditarietà

**Ereditarietà** ("is-a"): il sottotipo È un supertipo.
**Composizione** ("has-a"): un tipo HA un'istanza di un altro tipo.

```java
// EREDITARIETÀ (is-a): un Cane È un Animale
public class Cane extends Animale {
    // eredita tutti i metodi e attributi di Animale
}

// COMPOSIZIONE (has-a): un'Auto HA un Motore
public class Auto {
    private Motore motore;  // composizione
    private List<Ruota> ruote;

    public Auto(Motore motore) {
        this.motore = motore;
        this.ruote = new ArrayList<>();
    }

    public void accendi() {
        motore.avvia(); // delega al motore
    }
}
```

### 11.4 Quando usare Ereditarietà vs Composizione

**Preferisci la composizione** a meno che:

1. La relazione "is-a" sia genuina e permanente
2. Il sottotipo rispetti pienamente il LSP
3. Il supertipo sia progettato per l'ereditarietà (non sia un dettaglio implementativo)

```java
// CATTIVO USO DI EREDITARIETÀ: Stack che estende ArrayList
// Stack NON è una ArrayList (viola is-a)
public class StackMale<E> extends ArrayList<E> {
    public void push(E item) { add(item); }
    public E pop() { return remove(size() - 1); }
    // MA: il cliente può usare add(index, element), remove(index), get(index)...
    // che violano la semantica di uno Stack!
}

// BUON USO DI COMPOSIZIONE: Stack che usa ArrayList internamente
public class StackBene<E> {
    private ArrayList<E> elementi = new ArrayList<>();

    public void push(E item) { elementi.add(item); }
    public E pop() {
        if (isEmpty()) throw new IllegalStateException("Stack vuoto");
        return elementi.remove(elementi.size() - 1);
    }
    public E peek() {
        if (isEmpty()) throw new IllegalStateException("Stack vuoto");
        return elementi.get(elementi.size() - 1);
    }
    public boolean isEmpty() { return elementi.isEmpty(); }
    public int size() { return elementi.size(); }
    // I metodi di ArrayList NON sono esposti al cliente
}
```

### 11.5 Equality nelle Gerarchie

Implementare equals() correttamente nelle gerarchie è complesso.
Bisogna mantenere simmetria e transitività.

```java
// Approccio 1: usare getClass() (più restrittivo)
public class Punto {
    private final int x, y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Punto punto = (Punto) o;
        return x == punto.x && y == punto.y;
    }
}

public class PuntoColorato extends Punto {
    private final String colore;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PuntoColorato that = (PuntoColorato) o;
        return Objects.equals(colore, that.colore);
    }
}

// Con getClass(): un Punto non è MAI uguale a un PuntoColorato
// (anche se hanno stesse coordinate)

// Approccio 2: usare instanceof + canEqual (pattern Scala)
// Più flessibile ma più complesso
```

### 11.6 Delegation Pattern

Il **delegation pattern** è una forma di composizione dove un oggetto delega
operazioni a un altro oggetto.

```java
public interface Printer {
    void print(String messaggio);
}

public class ConsolePrinter implements Printer {
    @Override
    public void print(String messaggio) {
        System.out.println(messaggio);
    }
}

public class FilePrinter implements Printer {
    private final String percorso;

    public FilePrinter(String percorso) {
        this.percorso = percorso;
    }

    @Override
    public void print(String messaggio) {
        // scrive su file
        try (FileWriter fw = new FileWriter(percorso, true)) {
            fw.write(messaggio + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

// Delegation: Logger delega la stampa al Printer
public class Logger {
    private Printer printer; // delegato

    public Logger(Printer printer) {
        this.printer = printer;
    }

    public void log(String messaggio) {
        String timestamp = java.time.LocalDateTime.now().toString();
        printer.print("[" + timestamp + "] " + messaggio); // delega
    }

    public void setPrinter(Printer printer) {
        this.printer = printer; // posso cambiare strategia a runtime
    }
}

// Utilizzo
Logger logger = new Logger(new ConsolePrinter());
logger.log("Avvio applicazione");

logger.setPrinter(new FilePrinter("app.log"));
logger.log("Errore critico"); // ora scrive su file
```

---

## 12. Astrazione sull'Iterazione (L11 — PDJ Cap. 6-6.4)

### 12.1 Interfaccia Iterator

L'interfaccia `Iterator<E>` del package `java.util` definisce il protocollo
per attraversare una collezione di elementi:

```java
public interface Iterator<E> {
    /**
     * EFFECTS: restituisce true se ci sono altri elementi da visitare
     */
    boolean hasNext();

    /**
     * REQUIRES: hasNext() == true
     * MODIFIES: this
     * EFFECTS: restituisce il prossimo elemento e avanza l'iteratore
     */
    E next();

    /**
     * REQUIRES: next() è stato chiamato almeno una volta
     * MODIFIES: this e la collezione sottostante
     * EFFECTS: rimuove l'ultimo elemento restituito da next()
     */
    default void remove() {
        throw new UnsupportedOperationException("remove");
    }
}
```

### 12.2 Interfaccia Iterable

L'interfaccia `Iterable<E>` permette a una classe di essere usata nel for-each:

```java
public interface Iterable<E> {
    Iterator<E> iterator();
}

// Se una classe implementa Iterable, si può usare nel for-each
public class MiaCollezione<E> implements Iterable<E> {
    private List<E> elementi = new ArrayList<>();

    public void aggiungi(E e) { elementi.add(e); }

    @Override
    public Iterator<E> iterator() {
        return elementi.iterator(); // delega alla lista interna
    }
}

// Utilizzo con for-each
MiaCollezione<String> col = new MiaCollezione<>();
col.aggiungi("primo");
col.aggiungi("secondo");

for (String s : col) {  // possibile perché implementa Iterable
    System.out.println(s);
}
```

### 12.3 Implementazione di un Iteratore Custom

```java
/**
 * OVERVIEW: IntSet è un insieme mutabile di interi.
 */
public class IntSet implements Iterable<Integer> {
    private List<Integer> elementi;

    // ... costruttore, insert, remove, etc ...

    @Override
    public Iterator<Integer> iterator() {
        return new IntSetIterator();
    }

    // Iteratore come classe interna privata
    private class IntSetIterator implements Iterator<Integer> {
        private int indice = 0;

        @Override
        public boolean hasNext() {
            return indice < elementi.size();
        }

        @Override
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return elementi.get(indice++);
        }
    }
}

// Utilizzo
IntSet s = new IntSet();
s.insert(1); s.insert(2); s.insert(3);

for (int x : s) {
    System.out.println(x);
}
```

### 12.4 Iteratore come Generatore

Un iteratore può generare elementi "al volo" senza averli memorizzati:

```java
/**
 * Iteratore che genera i primi n numeri di Fibonacci.
 */
public class FibonacciIterator implements Iterator<Long> {
    private final int limite;
    private int contatore = 0;
    private long a = 0, b = 1;

    public FibonacciIterator(int n) {
        this.limite = n;
    }

    @Override
    public boolean hasNext() {
        return contatore < limite;
    }

    @Override
    public Long next() {
        if (!hasNext()) throw new NoSuchElementException();
        long risultato = a;
        long temp = a + b;
        a = b;
        b = temp;
        contatore++;
        return risultato;
    }
}

// Classe Iterable wrapper
public class Fibonacci implements Iterable<Long> {
    private final int n;

    public Fibonacci(int n) { this.n = n; }

    @Override
    public Iterator<Long> iterator() {
        return new FibonacciIterator(n);
    }
}

// Utilizzo
for (long f : new Fibonacci(10)) {
    System.out.print(f + " "); // 0 1 1 2 3 5 8 13 21 34
}
```

### 12.5 Iteratore con Filtro

```java
/**
 * Iteratore che filtra gli elementi pari da un altro iteratore.
 */
public class PariIterator implements Iterator<Integer> {
    private final Iterator<Integer> sorgente;
    private Integer prossimo = null;

    public PariIterator(Iterator<Integer> sorgente) {
        this.sorgente = sorgente;
        avanza();
    }

    private void avanza() {
        prossimo = null;
        while (sorgente.hasNext()) {
            int candidato = sorgente.next();
            if (candidato % 2 == 0) {
                prossimo = candidato;
                return;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return prossimo != null;
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        Integer risultato = prossimo;
        avanza();
        return risultato;
    }
}
```

### 12.6 Specifica di Iteratori

Nella specifica di un metodo che restituisce un iteratore:

```java
/**
 * OVERVIEW: IntSet è un insieme mutabile di interi.
 */
public class IntSet implements Iterable<Integer> {

    /**
     * EFFECTS: restituisce un iteratore che produce tutti gli elementi
     *          di this (ciascuno esattamente una volta), in ordine arbitrario.
     *
     *          REQUIRES: this non deve essere modificato durante l'iterazione
     */
    @Override
    public Iterator<Integer> iterator() {
        return new IntSetIterator();
    }

    /**
     * EFFECTS: restituisce un iteratore che produce gli elementi
     *          di this in ordine crescente
     */
    public Iterator<Integer> sortedIterator() {
        List<Integer> sorted = new ArrayList<>(elementi);
        Collections.sort(sorted);
        return sorted.iterator();
    }
}
```

### 12.7 ConcurrentModificationException

Se si modifica una collezione durante l'iterazione (senza usare `Iterator.remove()`),
si ottiene un `ConcurrentModificationException`:

```java
List<String> lista = new ArrayList<>(List.of("a", "b", "c", "d"));

// ERRORE: modifica durante for-each
for (String s : lista) {
    if (s.equals("b")) {
        lista.remove(s); // ConcurrentModificationException!
    }
}

// CORRETTO: uso di Iterator.remove()
Iterator<String> it = lista.iterator();
while (it.hasNext()) {
    String s = it.next();
    if (s.equals("b")) {
        it.remove(); // OK: rimozione attraverso l'iteratore
    }
}

// ALTERNATIVA: removeIf (Java 8+)
lista.removeIf(s -> s.equals("b"));
```

### 12.8 Delegazione a Collection Interne

Il pattern più comune: l'iteratore del tipo astratto delega
all'iteratore della collezione interna.

```java
public class Registro implements Iterable<String> {
    private final List<String> studenti = new ArrayList<>();

    public void iscrivi(String nome) { studenti.add(nome); }

    // Delegazione: il nostro iteratore è quello della lista
    // MA restituiamo una copia non modificabile per proteggere la rep
    @Override
    public Iterator<String> iterator() {
        return Collections.unmodifiableList(
            new ArrayList<>(studenti)
        ).iterator();
    }
}
```
