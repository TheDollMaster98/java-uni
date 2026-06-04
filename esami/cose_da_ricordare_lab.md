# Cose da ricordare - Esame Lab

Checklist rapida per la parte pratica.

## 1. Leggere il testo

- [ ] Quante classi ci sono?
- [ ] Qual e' la classe padre?
- [ ] La classe padre e' astratta?
- [ ] Ci sono campi comuni a tutti?
- [ ] Ci sono valori che cambiano per sottoclasse?
- [ ] C'e' un contenitore da implementare?
- [ ] Quali eccezioni servono?
- [ ] C'e' un `main` di test?

## 2. Classe padre astratta

- [ ] `abstract public class Padre implements Comparable<Padre>`
- [ ] OVERVIEW chiara
- [ ] Campi comuni, spesso `final public` nel tema d'esame
- [ ] Costruttore con validazioni
- [ ] Metodo astratto per il valore variabile tra sottoclassi
- [ ] `equals` coerente con il campo chiave
- [ ] `hashCode` coerente con `equals`
- [ ] `compareTo` con `Integer.compare(...)` o simile
- [ ] `toString` riusato dalle sottoclassi con `super.toString()`

## 3. Sottoclassi

- [ ] `extends Padre`
- [ ] Campi propri `final public`
- [ ] `super(...)` nel costruttore
- [ ] Validazione dei parametri specifici
- [ ] `@Override` del metodo astratto
- [ ] `toString` completo e leggibile

## 4. Eccezioni

- [ ] Checked = `extends Exception`
- [ ] Unchecked = `extends RuntimeException`
- [ ] Messaggi chiari, meglio con prefisso `\tECCEZIONE: ...`
- [ ] Nel testo controlla se chiedono eccezioni controllate o no

## 5. Contenitore iterabile

- [ ] `implements Iterable<Padre>`
- [ ] Array o lista `final`
- [ ] `aggiungi(...)` con controlli su posizione, duplicati, vincoli speciali
- [ ] `rimuovi(...)` con controlli
- [ ] `toString()` con `StringBuilder`
- [ ] `iterator()` che ordina con `Collections.sort(...)`
- [ ] AF e RI scritti bene

## 6. Test.java

- [ ] `Scanner(System.in)` per leggere input
- [ ] `args[0]` per parametri da riga di comando
- [ ] `split(" ")` o `split("\\s+")` per parsare i comandi
- [ ] `try-catch` dentro il ciclo di lettura
- [ ] `for-each` sul contenitore per testare `iterator()`

## 7. Esempi ricorrenti nei temi d'esame

### `compareTo` nella classe padre

```java
@Override
public int compareTo(Padre other) {
	return Integer.compare(this.valore(), other.valore());
}
```

- Usa `Integer.compare(...)` invece di sottrarre due valori.
- Serve quando il tema chiede un ordine naturale e quando il contenitore deve restituire elementi ordinati.
- Quando usarlo: quando il testo dice che gli oggetti devono essere ordinati per un criterio interno, ad esempio durata, anno, capienza o valore.

### `equals` e `hashCode`

```java
@Override
public boolean equals(Object o) {
	if (o == this) return true;
	if (o == null || !(o instanceof Padre)) return false;
	Padre other = (Padre) o;
	return this.nome.equals(other.nome);
}

@Override
public int hashCode() {
	return java.util.Objects.hash(nome);
}
```

- `equals` confronta l'uguaglianza logica.
- `hashCode` deve essere coerente con `equals`.
- Quando usarli: sempre insieme se la classe deve funzionare bene in `HashMap`, `HashSet` o quando il tema chiede confronto logico tra oggetti.

### `toString`

```java
@Override
public String toString() {
	return "Padre: " + nome + " valore: " + valore();
}
```

- Viene usato da `System.out.println(oggetto)`.
- Nei temi serve per stampare padre, sottoclassi e contenitore.
- Quando usarlo: quando vuoi una stampa leggibile dell'oggetto o quando il testo chiede esplicitamente l'output della classe.

### `Iterable` e `Iterator`

```java
@Override
public Iterator<Padre> iterator() {
	ArrayList<Padre> lista = new ArrayList<>();
	for (Padre p : array) {
		if (p != null) {
			lista.add(p);
		}
	}
	Collections.sort(lista);
	return lista.iterator();
}
```

- `Iterable` abilita il `for-each`.
- L'iteratore restituisce gli elementi ordinati con `Collections.sort(...)`.
- Quando usarli: quando il tema dice di scorrere gli elementi del contenitore oppure di restituirli in ordine naturale.

### Uso dell'iteratore nel `main`

```java
for (Padre p : contenitore) {
	System.out.println("\t" + p);
}

Iterator<Padre> it = contenitore.iterator();
while (it.hasNext()) {
	System.out.println(it.next());
}
```

- Il `for-each` chiama `iterator()` automaticamente.
- Il ciclo con `while` serve quando vuoi usare l'iteratore esplicito.
- Quando usarlo: il `for-each` è la scelta standard; l'iteratore esplicito serve se devi controllare manualmente `hasNext()` / `next()`.

### `Scanner` + `split` + `try-catch`

```java
Scanner scanner = new Scanner(System.in);
while (scanner.hasNextLine()) {
	String line = scanner.nextLine();
	String[] tokens = line.split(" ");

	try {
		if (tokens[0].equals("aggiungi")) {
			int giorno = Integer.parseInt(tokens[1]);
			// parsing del tipo solo dopo aver capito il comando
		} else if (tokens[0].equals("rimuovi")) {
			int giorno = Integer.parseInt(tokens[1]);
			// rimozione
		}
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
}
```

- `Scanner(System.in)` legge lo standard input.
- `split(" ")` separa i token della riga.
- Il `try-catch` va dentro il ciclo per non fermare tutto al primo errore.
- Quando usarli: per il `main` che legge i comandi del tema, di solito fino a EOF, separando il comando dai parametri.

### `Comparable` + `Collections.sort`

```java
ArrayList<Padre> lista = new ArrayList<>();
Collections.sort(lista);
```

- Funziona solo se la classe padre implementa `Comparable<Padre>`.
- Nei temi usati di frequente per ordinare il contenuto del contenitore.
- Quando usarlo: quando il contenitore deve ordinare automaticamente con l'ordine naturale degli elementi.

### `Comparator` quando serve un ordine alternativo

```java
lista.sort(Comparator.comparing(Padre::getNome));
```

- Non sempre compare nei temi, ma è utile se il testo chiede un ordinamento diverso da quello naturale.
- Quando usarlo: quando hai bisogno di un ordine alternativo, ad esempio per nome invece che per durata.

### `aggiungi(...)` e `rimuovi(...)`

```java
public void aggiungi(int posizione, Padre elemento) {
	if (posizione < 0 || posizione >= array.length) {
		throw new IllegalArgumentException("Posizione non valida");
	}
	if (array[posizione] != null) {
		throw new IllegalArgumentException("Posizione occupata");
	}
	array[posizione] = elemento;
}

public void rimuovi(int posizione) {
	if (posizione < 0 || posizione >= array.length) {
		throw new IllegalArgumentException("Posizione non valida");
	}
	if (array[posizione] == null) {
		throw new IllegalArgumentException("Nessun elemento da rimuovere");
	}
	array[posizione] = null;
}
```

- Sono i due metodi che nei temi modellano quasi sempre la mutazione del contenitore.
- Quasi sempre hanno controlli su posizione, duplicati e vincoli del testo.
- Quando usarli: quando il tema chiede inserimento o rimozione in una struttura posizionata o indicizzata.

### `instanceof` per gestire vincoli sulle sottoclassi

```java
if (elemento instanceof Sottoclasse) {
	Sottoclasse s = (Sottoclasse) elemento;
	// controlli specifici
}
```

- Serve quando una regola vale solo per un certo tipo di sottoclasse.
- Nei temi è tipico per cerimonie, gare o eventi speciali.
- Quando usarlo: quando il comportamento o i vincoli dipendono dal tipo concreto dell'elemento inserito.

### `HashMap` se il tema usa chiave -> valore

```java
HashMap<Integer, Padre> mappa = new HashMap<>();
mappa.put(chiave, valore);
mappa.get(chiave);
mappa.containsKey(chiave);
mappa.values();
mappa.keySet();
```

- È una struttura comune quando il contenitore non è un array ma una mappa di posizioni o identificativi.
- Quando usarla: quando il tema associa una chiave a un valore, ad esempio giorno -> evento oppure nome -> oggetto.

## 8. Template completi da copiare (versioni GENERICHE)

Questi esempi sono neutri e facili da adattare a qualunque tema d'esame: sostituisci i nomi delle classi (`Elemento`, `Contenitore`) e i campi con quelli richiesti dal tema.

### A. Esempio generico: `Elemento` + `Contenitore`

#### `Elemento` astratta (Comparable)

```java
public abstract class Elemento implements Comparable<Elemento> {
	private final String id;
	private final int valore;

	protected Elemento(String id, int valore) {
		this.id = id;
		this.valore = valore;
	}

	public String getId() { return id; }
	public int getValore() { return valore; }

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || !(obj instanceof Elemento)) return false;
		Elemento other = (Elemento) obj;
		return id.equals(other.id);
	}

	@Override
	public int hashCode() {
		return java.util.Objects.hash(id);
	}

	@Override
	public int compareTo(Elemento other) {
		return Integer.compare(this.valore, other.valore);
	}

	@Override
	public String toString() {
		return "Elemento(" + id + ") valore=" + valore;
	}
}
```

- Uso: `compareTo` per ordine naturale, `equals`/`hashCode` per confronti e mappe, `toString` per output leggibile.

#### `Contenitore` iterabile (posizionato o indicizzato)

```java
public class Contenitore implements Iterable<Elemento> {
	private final Elemento[] slot;

	public Contenitore(int size) {
		this.slot = new Elemento[size];
	}

	public void aggiungi(int pos, Elemento e) {
		if (pos < 1 || pos > slot.length) throw new IllegalArgumentException("Posizione non valida");
		if (slot[pos - 1] != null) throw new IllegalArgumentException("Posizione occupata");
		for (Elemento x : slot) if (x != null && x.equals(e)) throw new IllegalArgumentException("Elemento duplicato");
		slot[pos - 1] = e;
	}

	public void rimuovi(int pos) {
		if (pos < 1 || pos > slot.length) throw new IllegalArgumentException("Posizione non valida");
		if (slot[pos - 1] == null) throw new IllegalArgumentException("Nessun elemento");
		slot[pos - 1] = null;
	}

	@Override
	public Iterator<Elemento> iterator() {
		List<Elemento> presenti = new ArrayList<>();
		for (Elemento e : slot) if (e != null) presenti.add(e);
		Collections.sort(presenti); // usa compareTo di Elemento
		return presenti.iterator();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Contenitore:\n");
		for (int i = 0; i < slot.length; i++) {
			sb.append("\t").append(i + 1).append(": ");
			sb.append(slot[i] == null ? "-" : slot[i].toString()).append('\n');
		}
		return sb.toString();
	}
}
```

- Uso: `aggiungi`/`rimuovi` con validazioni coerenti con il testo, `iterator()` che restituisce gli elementi ordinati.

#### `main` generico con parsing comandi

```java
Scanner scanner = new Scanner(System.in);
Contenitore c = new Contenitore(Integer.parseInt(args[0]));

while (scanner.hasNextLine()) {
	String line = scanner.nextLine().trim();
	if (line.isEmpty()) continue;
	String[] parts = line.split(" ");

	try {
		String cmd = parts[0];
		if (cmd.equals("aggiungi")) {
			int pos = Integer.parseInt(parts[1]);
			// costruisci l'Elemento concreto in base a parts[2..]
			// es: if (parts[2].equals("TipoA")) new TipoA(parts[3], Integer.parseInt(parts[4]));
		} else if (cmd.equals("rimuovi")) {
			c.rimuovi(Integer.parseInt(parts[1]));
		}
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
}

System.out.println(c);
for (Elemento e : c) System.out.println("\t" + e);
```

### B. Esempio alternativo generico: `Voce` + `Casella`

Questo schema è utile se il tema usa `inserisci`/`apri` invece di `aggiungi`/`rimuovi`.

#### `Voce` astratta

```java
public abstract class Voce implements Comparable<Voce> {
	private final String nome;
	private final double peso;

	protected Voce(String nome, double peso) {
		this.nome = nome;
		this.peso = peso;
	}

	public String getNome() { return nome; }
	public double getPeso() { return peso; }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || !(o instanceof Voce)) return false;
		return nome.equals(((Voce) o).getNome());
	}

	@Override
	public int hashCode() { return nome.hashCode(); }

	@Override
	public int compareTo(Voce other) { return Double.compare(this.peso, other.peso); }

	@Override
	public String toString() { return nome + " (" + peso + ")"; }
}
```

#### `Casella` iterabile

```java
public class Casella implements Iterable<Voce> {
	private final Voce[] slots;

	public Casella(int giorni) { this.slots = new Voce[giorni]; }

	public void inserisci(int giorno, Voce v) {
		if (giorno < 1 || giorno > slots.length) throw new IllegalArgumentException("Giorno non valido");
		if (slots[giorno - 1] != null) throw new IllegalArgumentException("Giorno occupato");
		for (Voce x : slots) if (x != null && x.equals(v)) throw new IllegalArgumentException("Duplicato");
		slots[giorno - 1] = v;
	}

	public Voce apri(int giorno) {
		if (giorno < 1 || giorno > slots.length) throw new IllegalArgumentException("Giorno non valido");
		if (slots[giorno - 1] == null) throw new IllegalArgumentException("Vuoto");
		Voce r = slots[giorno - 1];
		slots[giorno - 1] = null;
		return r;
	}

	@Override
	public Iterator<Voce> iterator() {
		List<Voce> presenti = new ArrayList<>();
		for (Voce v : slots) if (v != null) presenti.add(v);
		Collections.sort(presenti);
		return presenti.iterator();
	}
}
```

- Uso: adatta `Voce`/`Casella` ai nomi e ai campi del tema, conserva le validazioni e l'iterazione ordinata.

## 9. Errori comuni

- [ ] Non fare parsing dei token prima di sapere il comando
