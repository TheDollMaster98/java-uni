# Esercizio Pratica: Distributore di Bevande

## Consegna

Un `Distributore` contiene **10 slot** (da 1 a 10), ognuno puo' contenere una `Bevanda`.

Una `Bevanda` e' definita dal suo **nome**. Due bevande sono uguali se hanno lo stesso nome.
Le bevande sono naturalmente ordinate per **prezzo**.

### Tipi di bevanda

- **Caffe**: ha un numero di capsule (int). Il prezzo e' `0.5 * numeroCapsule`.
- **Te**: ha una temperatura (double). Il prezzo e' `temperatura / 20`.

### Funzionalita' del Distributore

- **Inserire** una bevanda in uno slot.
  - Se lo slot e' gia' occupato -> `SlotException` (checked)
  - Se la bevanda e' gia' nel distributore -> `BevandaException` (unchecked)
- **Erogare** da uno slot: restituisce la bevanda e la rimuove.
  - Se lo slot e' vuoto -> `SlotException`
- **Incasso totale**: somma dei prezzi di tutte le bevande.
- **Iterare** le bevande ordinate per prezzo.

### Classe Test

Leggere da standard input:

- `inserisci <slot> Caffe <nome> <capsule>`
- `inserisci <slot> Te <nome> <temperatura>`
- `eroga <slot>`
- Su EOF -> stampare il distributore con incasso e bevande contenute.

---

## Da completare

### 1. Bevanda.java

```java
// TODO: che interfaccia deve implementare per l'ordinamento?
public abstract class Bevanda __________ {

    // TODO: campi (cosa e' comune a TUTTE le bevande?)

    // TODO: costruttore (che visibilita' deve avere?)

    // TODO: getter

    // TODO: equals e hashCode (basati su quale campo?)

    // TODO: compareTo (confronta cosa?)

    // TODO: toString
}
```

### 2. Caffe.java

```java
public class Caffe extends Bevanda {

    // TODO: campo specifico (quale dato ha solo il Caffe?)

    // TODO: costruttore
    //       Quanti parametri riceve? Chi calcola il prezzo?

    // TODO: getter del campo specifico

    // TODO: toString
}
```

### 3. Te.java

```java
public class Te extends Bevanda {

    // TODO: campo specifico (quale dato ha solo il Te?)

    // TODO: costruttore
    //       Quanti parametri riceve? Chi calcola il prezzo?

    // TODO: getter del campo specifico

    // TODO: toString
}
```

### 4. SlotException.java

```java
// TODO: checked -> extends cosa?
public class SlotException __________ {
    // TODO: costruttore con messaggio
}
```

### 5. BevandaException.java

```java
// TODO: unchecked -> extends cosa?
public class BevandaException __________ {
    // TODO: costruttore con messaggio
}
```

### 6. Distributore.java

```java
// TODO: che interfaccia deve implementare per l'iterazione?
public class Distributore __________ {

    // TODO: rappresentazione (che tipo? quanti elementi?)

    // TODO: RI (Representation Invariant)

    // TODO: AF (Abstraction Function)

    // TODO: costruttore

    // TODO: inserisci(int slot, Bevanda bevanda)
    //       Throws cosa?
    //       Slot occupato -> quale eccezione?
    //       Bevanda gia' presente -> quale eccezione?

    // TODO: eroga(int slot)
    //       Throws cosa?
    //       Slot vuoto -> quale eccezione?

    // TODO: incasso() - somma dei prezzi

    // TODO: iterator() - i 3 passi: raccogli, ordina, ritorna

    // TODO: toString
}
```

### 7. Test.java

```java
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        // TODO: crea scanner e distributore

        // TODO: while (scanner.hasNextLine())
        //       - leggi la riga
        //       - split
        //       - leggi comando e slot
        //       - try-catch per le eccezioni
        //       - se "inserisci": leggi tipo, nome, parametro specifico
        //         ATTENZIONE: accedi a parts[2], parts[3] ecc. solo dentro questo ramo!
        //       - se "eroga": eroga e stampa

        // TODO: fuori dal while, stampa il distributore
    }
}
```

---

## Domande guida

Prima di scrivere codice, rispondi a queste domande:

1. Il campo `capsule` va in `Bevanda` o in `Caffe`?
2. Il costruttore di `Caffe` deve ricevere il prezzo come parametro?
3. Puoi scrivere `new Bevanda(...)` nel Test?
4. Se nel Test leggi `eroga 5`, quanti elementi ha `parts` dopo lo split?
5. Cosa succede se metti `parts[2]` fuori dall'if di `inserisci`?
