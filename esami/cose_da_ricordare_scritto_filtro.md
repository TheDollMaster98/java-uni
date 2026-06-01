# Cose da ricordare - Esame Scritto Filtro

Checklist rapida per la parte teorica.

## 1. Astrazione e specifica

- [ ] Differenza tra astrazione per parametrizzazione e per specifica
- [ ] REQUIRES / MODIFIES / EFFECTS
- [ ] Metodo totale vs metodo parziale
- [ ] Specifica chiara: cosa fa, non come lo fa

## 2. AF, RI, rep exposure

- [ ] AF = funzione di astrazione: rappresentazione -> oggetto astratto
- [ ] RI = invariante di rappresentazione: condizioni sempre vere
- [ ] `repOk()` per controllare RI
- [ ] Evitare rep exposure con copie difensive o oggetti immutabili

## 3. OOP e sostituibilita'

- [ ] LSP: il sottotipo deve poter sostituire il supertipo
- [ ] Regole su signature, pre/postcondizioni e invarianti
- [ ] Overriding dinamico vs overloading statico
- [ ] Dispatch dinamico sui metodi di istanza

## 4. Classi astratte e interfacce

- [ ] Quando usare classe astratta
- [ ] Quando usare interfaccia
- [ ] `Comparable`, `Iterable`, `Iterator`, `Cloneable`
- [ ] `abstract`, `final`, `static`, `protected`

## 5. Generics e varianza

- [ ] Generics invarianti: `List<String>` non e' `List<Object>`
- [ ] PECS: producer extends, consumer super
- [ ] Covarianza, controvarianza, invarianza
- [ ] Type erasure e limiti: niente `new T()`, `T.class`, `instanceof T`

## 6. Eccezioni

- [ ] Checked = `extends Exception`
- [ ] Unchecked = `extends RuntimeException`
- [ ] `throw`, `throws`, `try-catch-finally`, `try-with-resources`
- [ ] Ordine dei catch: da specifico a generico

## 7. Collezioni e iteratori

- [ ] `List`, `Set`, `Map`
- [ ] `Iterator` e `Iterable`
- [ ] `Comparable` e `Comparator`
- [ ] Uso corretto del for-each e dell'iteratore manuale

## 8. Ereditarieta' e composizione

- [ ] IS-A vs HAS-A
- [ ] Spec inheritance vs implementation inheritance
- [ ] Fragile base class
- [ ] Preferire composizione quando la relazione non e' vera ereditarieta'

## 9. Java 21 e temi da ripasso

- [ ] `record`
- [ ] `Stream` e `Optional` se trattati nel corso/laboratorio
- [ ] `switch` moderno con frecce
- [ ] pattern matching di base se presente nei materiali
