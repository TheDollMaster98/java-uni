# Mini Guida: Contenitore Iterabile (pattern da esame)

Questo pattern e' richiesto in quasi tutti gli esami. Lo schema e' sempre lo stesso.

---

## Lo schema base

Hai una classe che **contiene** una collezione di oggetti e deve:

1. Aggiungere/rimuovere elementi (con eccezioni)
2. Iterare gli elementi in un certo ordine
3. Avere toString(), AF, RI

```java
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Contenitore implements Iterable<Elemento> {

    // --- RAPPRESENTAZIONE ---
    private final Elemento[] elementi;  // oppure List<Elemento>

    // --- RI (Representation Invariant) ---
    // elementi != null, nessun elemento duplicato (per nome), ecc.

    // --- AF (Abstraction Function) ---
    // AF(elementi) = il contenitore con gli elementi non null presenti nell'array

    // --- COSTRUTTORE ---
    public Contenitore() {
        this.elementi = new Elemento[dimensione];
    }

    // --- METODI ---
    // aggiungi, rimuovi, ecc.

    // --- ITERATORE ---
    @Override
    public Iterator<Elemento> iterator() {
        // 1. raccogli gli elementi
        List<Elemento> lista = new ArrayList<>();
        for (Elemento e : elementi) {
            if (e != null) {
                lista.add(e);
            }
        }
        // 2. ordina (funziona perche' Elemento implements Comparable)
        Collections.sort(lista);
        // 3. ritorna l'iteratore della lista ordinata
        return lista.iterator();
    }

    // --- TOSTRING ---
    @Override
    public String toString() { ... }
}
```

---

## I 3 pezzi che servono

### 1. La classe contenitore: `implements Iterable<T>`

```java
public class Calendario implements Iterable<Sorpresa> {
```

Questo obbliga la classe ad avere il metodo `iterator()`.

### 2. L'elemento: `implements Comparable<T>`

```java
public abstract class Sorpresa implements Comparable<Sorpresa> {

    @Override
    public int compareTo(Sorpresa o) {
        return Double.compare(this.costo, o.getCosto());
    }
}
```

Questo definisce l'**ordine naturale** degli elementi. `Collections.sort()` usa questo metodo.

### 3. Il metodo `iterator()`: raccoglie, ordina, ritorna

```java
@Override
public Iterator<Sorpresa> iterator() {
    List<Sorpresa> lista = new ArrayList<>();
    for (Sorpresa s : calendario) {
        if (s != null) lista.add(s);
    }
    Collections.sort(lista);
    return lista.iterator();
}
```

Sono sempre 3 passi:

1. **Raccogli** gli elementi validi in una lista
2. **Ordina** la lista (usa `compareTo` automaticamente)
3. **Ritorna** `lista.iterator()`

---

## Come funziona `compareTo`

`compareTo` ritorna:

- **negativo** se `this` e' piu' piccolo di `o`
- **zero** se sono uguali
- **positivo** se `this` e' piu' grande di `o`

Per confrontare numeri, usa sempre `Double.compare()` o `Integer.compare()`:

```java
// ordinamento per costo (crescente)
return Double.compare(this.costo, o.getCosto());

// ordinamento per costo (decrescente)
return Double.compare(o.getCosto(), this.costo);

// ordinamento per nome (alfabetico)
return this.nome.compareTo(o.getNome());
```

---

## AF e RI - cosa scrivere

### RI (Representation Invariant)

Le condizioni che la rappresentazione deve SEMPRE rispettare. Scrivile come commento:

```java
// RI: calendario != null,
//     calendario.length == 31,
//     non ci sono due sorprese con lo stesso nome
```

### AF (Abstraction Function)

Come si "legge" la rappresentazione concreta:

```java
// AF: calendario[i] rappresenta la sorpresa del giorno i+1,
//     null indica un giorno senza sorpresa
```

---

## Checklist da esame

Quando ti trovi un contenitore iterabile all'esame, segui questa lista:

- [ ] `implements Iterable<TipoElemento>` sulla classe contenitore
- [ ] `implements Comparable<TipoElemento>` sulla classe elemento
- [ ] Metodo `compareTo()` nell'elemento
- [ ] Metodo `iterator()` nel contenitore (raccogli, ordina, ritorna)
- [ ] Import: `Iterator`, `List`, `ArrayList`, `Collections`
- [ ] Eccezioni: checked (`extends Exception`) e unchecked (`extends RuntimeException`)
- [ ] AF e RI come commenti
- [ ] `equals()` e `hashCode()` coerenti nell'elemento
- [ ] Campi `private final`
- [ ] Costruttore con visibilita' corretta (`protected` se astratto)
