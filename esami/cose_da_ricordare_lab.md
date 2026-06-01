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

## 7. Errori comuni

- [ ] Non usare `static` al posto di `@Override`
- [ ] Non fare `a - b` in `compareTo`
- [ ] Non usare `getClass()` in `equals` se vuoi accettare sottotipi
- [ ] Non dimenticare `\t` nell'output
- [ ] Non fare parsing dei token prima di sapere il comando
