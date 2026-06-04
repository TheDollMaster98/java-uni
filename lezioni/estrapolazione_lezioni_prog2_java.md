
# Estrapolazione Completa delle Lezioni — Programmazione 2 Java

## Struttura del Corso

1. Introduzione
2. Object Orientation
3. Astrazione Procedurale e Specifiche
4. Eccezioni
5. Astrazione dei Tipi
6. Implementazione ADT
7. RI e AF
8. IntSet e Poly
9. Gerarchia dei Tipi
10. Specifica, Ereditarietà, Composizione
11. Iterator
12. Nested Classes
13. Polimorfismo
14. Generics
15. Functional Programming
16. Design Pattern
17. Liskov Substitution Principle

---

# 1. Introduzione

Obiettivi:
- modularità
- riuso
- correttezza
- mantenibilità

Paradigma:
- object oriented
- data abstraction
- type abstraction

---

# 2. Object Orientation

Concetti:
- classe
- oggetto
- stato
- comportamento

Principi:
- encapsulation
- inheritance
- polymorphism

Differenza:
- classe = definizione
- oggetto = istanza

---

# 3. Astrazione Procedurale

Separazione:
- specifica
- implementazione

Specifica:
- requires
- effects
- modifies

Contratto:
- il chiamante rispetta precondizioni
- il metodo garantisce postcondizioni

---

# 4. Eccezioni

Gestione errori:
```java
try/catch/finally
```

Tipologie:
- checked
- unchecked

Uso corretto:
- non usare eccezioni per controllo di flusso

---

# 5. Astrazione dei Tipi

ADT:
- nasconde implementazione
- espone comportamento

Componenti:
- operazioni
- stato astratto
- specifica

Esempi:
- Set
- Stack
- Queue

---

# 6. Implementazione ADT

Separare:
- interfaccia
- implementazione

Tecniche:
- representation independence
- information hiding

---

# 7. Representation Invariant e Abstraction Function

RI:
- vincoli sulla rappresentazione

AF:
- mapping tra stato concreto e astratto

Esempio:
```java
AF(c) = insieme degli elementi contenuti nell'array
```

---

# 8. IntSet e Poly

IntSet:
- insert
- remove
- contains

Poly:
- polinomi
- somma
- moltiplicazione
- valutazione

Focus:
- correttezza struttura dati

---

# 9. Gerarchia dei Tipi

Subtype:
```java
Dog extends Animal
```

Dispatch dinamico:
- scelta metodo runtime

Classi astratte:
- comportamento parziale

Interfacce:
- contratto puro

---

# 10. Ereditarietà vs Composizione

Ereditarietà:
- relazione is-a

Composizione:
- relazione has-a

Preferire composizione quando:
- si vuole flessibilità
- evitare forte accoppiamento

---

# 11. Iterator

Astrazione iterazione:
```java
Iterator<T>
```

Metodi:
- hasNext()
- next()

Scopo:
- attraversamento indipendente dalla struttura dati

---

# 12. Nested Classes

Tipologie:
- inner
- static nested
- anonymous

Uso:
- forte coesione logica

---

# 13. Polimorfismo

Static type:
- tipo dichiarato

Dynamic type:
- tipo runtime

Binding:
- statico
- dinamico

---

# 14. Generics

Parametric polymorphism:
```java
List<T>
```

Vantaggi:
- type safety
- riuso

Wildcard:
```java
<? extends T>
<? super T>
```

Type erasure:
- generics rimossi runtime

---

# 15. Functional Programming

Lambda:
```java
(x) -> x + 1
```

Functional interface:
- una sola abstract method

Stream API:
- filter
- map
- reduce

Immutabilità:
- preferibile

---

# 16. Design Pattern

Pattern studiati:
- Singleton
- Factory
- Observer
- Strategy

Scopo:
- soluzioni standard riusabili

---

# 17. Liskov Substitution Principle

Definizione:
- subtype sostituibile senza alterare correttezza

Vincoli:
- non rafforzare precondizioni
- non indebolire postcondizioni

Problema classico:
- Square/Rectangle

---

# Concetti Chiave Ricorrenti

- Information Hiding
- Encapsulation
- Modularity
- Specification First
- Correctness
- Maintainability
- Reusability
- Loose Coupling
- High Cohesion

---

# Errori Tipici d'Esame

- Confondere specifica e implementazione
- Violare RI
- Esporre representation
- Usare ereditarietà sbagliata
- Ignorare LSP
- Usare cast inutili
- Non motivare design choices

---

# Strategia per l'Esame

1. Scrivere prima la specifica.
2. Definire RI e AF.
3. Validare encapsulation.
4. Motivare ereditarietà/composizione.
5. Verificare polimorfismo corretto.
6. Evitare side effects nascosti.
7. Gestire eccezioni coerentemente.
