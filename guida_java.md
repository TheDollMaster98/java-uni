# Guida Completa Java

## Indice

1. [Introduzione a Java](#introduzione-a-java)
2. [Installazione e Setup](#installazione-e-setup)
3. [Concetti Base](#concetti-base)
4. [Strutture di Controllo](#strutture-di-controllo)
5. [Classi e Oggetti](#classi-e-oggetti)
6. [Array e Collezioni](#array-e-collezioni)
7. [Ereditarietà e Polimorfismo](#ereditarietà-e-polimorfismo)
8. [Interfacce e Classi Astratte](#interfacce-e-classi-astratte)
9. [Gestione delle Eccezioni](#gestione-delle-eccezioni)
10. [Input/Output](#inputoutput)

## Introduzione a Java

Java è un linguaggio di programmazione orientato agli oggetti, creato da Sun Microsystems (ora parte di Oracle) nel 1995. Le sue caratteristiche principali sono:

- Indipendenza dalla piattaforma ("Write Once, Run Anywhere")
- Orientamento agli oggetti
- Gestione automatica della memoria (Garbage Collection)
- Ampia libreria standard
- Forte tipizzazione

## Installazione e Setup

1. **Installazione del JDK 21 (Java Development Kit)**

   - Scaricare il JDK 21 da [Oracle](https://www.oracle.com/java/technologies/downloads/#java21)
   - Installare il JDK 21
   - Configurare le variabili d'ambiente:
     1. `JAVA_HOME`: Puntare alla directory di installazione di JDK 21 (es: `C:\Program Files\Java\jdk-21`)
     2. `Path`: Aggiungere `%JAVA_HOME%\bin`

2. **Verifica dell'installazione**

```bash
java -version   # Dovrebbe mostrare "java version "21" o "21.x.x"
javac -version  # Dovrebbe mostrare "javac 21" o "21.x.x"
```

> **Nota**: Java 21 è una versione LTS (Long Term Support) che include molte nuove funzionalità come:
>
> - Pattern Matching per switch
> - Record Patterns
> - Virtual Threads
> - Sequenced Collections
> - String Templates (Preview)
> - Foreign Function & Memory API

## Concetti Base

### 1. Struttura Base di un Programma

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

### 2. Tipi di Dati Primitivi

- `byte`: 8 bit (-128 a 127)
- `short`: 16 bit (-32,768 a 32,767)
- `int`: 32 bit (-2^31 a 2^31-1)
- `long`: 64 bit (-2^63 a 2^63-1)
- `float`: 32 bit, numeri decimali
- `double`: 64 bit, numeri decimali
- `boolean`: true/false
- `char`: 16 bit, carattere Unicode

### 3. Variabili e Costanti

```java
// Dichiarazione di variabili
int numero = 42;
String testo = "Hello";

// Costanti
final double PI = 3.14159;
```

## Strutture di Controllo

### 1. Condizionali

```java
if (condizione) {
    // codice
} else if (altraCondizione) {
    // codice
} else {
    // codice
}

switch (variabile) {
    case valore1:
        // codice
        break;
    case valore2:
        // codice
        break;
    default:
        // codice
}
```

### 2. Cicli

```java
// For
for (int i = 0; i < 10; i++) {
    // codice
}

// While
while (condizione) {
    // codice
}

// Do-While
do {
    // codice
} while (condizione);

// For-each
for (Tipo elemento : collezione) {
    // codice
}
```

## Classi e Oggetti

### 1. Definizione di una Classe

```java
public class Persona {
    // Attributi
    private String nome;
    private int eta;

    // Costruttore
    public Persona(String nome, int eta) {
        this.nome = nome;
        this.eta = eta;
    }

    // Metodi
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
```

### 2. Creazione di Oggetti

```java
Persona persona1 = new Persona("Mario", 30);
```

## Array e Collezioni

### 1. Array

```java
// Dichiarazione e inizializzazione
int[] numeri = new int[5];
String[] nomi = {"Mario", "Luigi", "Peach"};

// Accesso agli elementi
numeri[0] = 42;
System.out.println(nomi[1]);
```

### 2. Collections Framework

```java
// ArrayList
ArrayList<String> lista = new ArrayList<>();
lista.add("elemento");

// HashMap
HashMap<String, Integer> mappa = new HashMap<>();
mappa.put("chiave", 42);

// HashSet
HashSet<String> set = new HashSet<>();
set.add("elemento");
```

## Ereditarietà e Polimorfismo

### 1. Ereditarietà

```java
public class Animale {
    protected String nome;

    public void mangia() {
        System.out.println("L'animale mangia");
    }
}

public class Cane extends Animale {
    @Override
    public void mangia() {
        System.out.println("Il cane mangia");
    }
}
```

### 2. Polimorfismo

```java
Animale animale = new Cane();
animale.mangia(); // Stampa "Il cane mangia"
```

## Interfacce e Classi Astratte

### 1. Interfacce

```java
public interface Volante {
    void vola();
    void atterra();
}

public class Uccello implements Volante {
    @Override
    public void vola() {
        System.out.println("L'uccello vola");
    }

    @Override
    public void atterra() {
        System.out.println("L'uccello atterra");
    }
}
```

### 2. Classi Astratte

```java
public abstract class Forma {
    abstract double calcolaArea();

    public void descrivi() {
        System.out.println("Sono una forma geometrica");
    }
}

public class Quadrato extends Forma {
    private double lato;

    @Override
    double calcolaArea() {
        return lato * lato;
    }
}
```

## Gestione delle Eccezioni

```java
try {
    // Codice che potrebbe generare un'eccezione
    int risultato = 10 / 0;
} catch (ArithmeticException e) {
    // Gestione dell'eccezione
    System.out.println("Errore: divisione per zero");
} finally {
    // Codice eseguito sempre
    System.out.println("Operazione completata");
}
```

## Input/Output

### 1. Input da Console

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
System.out.print("Inserisci un numero: ");
int numero = scanner.nextInt();
scanner.close();
```

### 2. File I/O

```java
// Lettura da file
try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
    String line;
    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }
}

// Scrittura su file
try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
    writer.write("Testo da scrivere");
}
```

## Best Practices

1. **Naming Conventions**

   - Classi: PascalCase (es. `PersonaGiuridica`)
   - Metodi e variabili: camelCase (es. `getNome()`)
   - Costanti: UPPER_SNAKE_CASE (es. `MAX_VALUE`)

2. **Principi SOLID**

   - Single Responsibility
   - Open/Closed
   - Liskov Substitution
   - Interface Segregation
   - Dependency Inversion

3. **Documentazione**

   - Utilizzare JavaDoc per documentare classi e metodi
   - Mantenere commenti chiari e pertinenti

4. **Gestione della Memoria**
   - Chiudere sempre le risorse (file, connessioni, etc.)
   - Utilizzare try-with-resources quando possibile
   - Evitare memory leaks

## Risorse Utili

- [Documentazione Ufficiale Java](https://docs.oracle.com/en/java/)
- [Oracle Java Tutorials](https://docs.oracle.com/javase/tutorial/)
- [Java Design Patterns](https://www.journaldev.com/1827/java-design-patterns-example-tutorial)
