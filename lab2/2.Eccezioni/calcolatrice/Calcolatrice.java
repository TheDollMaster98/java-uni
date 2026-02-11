
/**
 * Calcolatrice
 * Scrivere un programma che legga da standard input un numero reale, a, un
 * simbolo di operazione aritmetica (+, -, * o /), ed un secondo numero reale b.
 * Il programma deve calcolare e stampare su standard output il risultato
 * dell'operazione specificata tra i due numeri. Se l'operatore non è
 * riconosciuto il programma invece deve stampare Operatore non riconosciuto.
 * Similmente, se un operando non è un numero reale, il programma deve stampare
 * Gli operandi devono essere numeri reali e se non ci sono tutti gli input deve
 * stampare Non sono stati inseriti tutti gli input richiesti. Trattare questi
 * casi eccezionali utilizzando le opportune eccezioni. Inoltre,definire almeno
 * il metodo public static double calculate(double o1, double o2, char op)
 * throws InputMismatchException che calcola il risultato dell'operazione,
 * lanciando una InputMismatchException se l'operatore inserito non è valido.
 * 
 * Cosa succede se si cerca una divisione per 0? Trattare questo caso creando
 * una nuova eccezione DivisionByZeroException che estende ArithmeticException.
 * In tal caso stampare Non è possibile dividere per 0.
 * 
 * Esempio d'esecuzione:
 * $ java calcolatrice
 * Inserisci <operando1> <operatore> <operando2>. es: 4 + 3
 * 3.1 + 6.0
 * Il risultato è 9.1
 * 
 * $ java calcolatrice
 * Inserisci <operando1> <operatore> <operando2>. es: 4 + 3
 * 4 - 2
 * Il risultato è 2.0
 * 
 * $ java calcolatrice
 * Inserisci <operando1> <operatore> <operando2>. es: 4 + 3
 * 10 * 4.5
 * Il risultato è 45.0
 * 
 * $ java calcolatrice
 * Inserisci <operando1> <operatore> <operando2>. es: 4 + 3
 * 10 / 2
 * Il risultato è 5.0
 * 
 * $ java calcolatrice
 * Inserisci <operando1> <operatore> <operando2>. es: 4 + 3
 * 10 % 2
 * Operatore non riconosciuto 
 */

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Calcolatrice {
    public static double calculate(double o1, double o2, char op)
            throws InputMismatchException, DivisionByZeroException {
        switch (op) {
            case '+':
                return o1 + o2;
            case '-':
                return o1 - o2;
            case '*':
                return o1 * o2;
            case '/':
                if (o2 == 0) {
                    throw new DivisionByZeroException("Non è possibile dividere per 0.");
                }
                return o1 / o2;
            default:
                throw new InputMismatchException("Operatore non riconosciuto");
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Inserisci <operando1> <operatore> <operando2>. es: 4 + 3");

        double n1 = 0; // operando 1
        double n2 = 0; // operando 2
        String op = ""; // operatore

        try {
            n1 = s.nextDouble();
            op = s.next();
            n2 = s.nextDouble();
            double ris = calculate(n1, n2, op.charAt(0));
            System.out.println("Il risultato è " + ris);
        } catch (InputMismatchException e) {
            System.out.println("Operando non valido: è richiesto doubel");
        } catch (NoSuchElementException e) {
            System.out.println("Non sono stati inseriti tutti gli input richiesti.");
        } catch (DivisionByZeroException e) {
            System.out.println("Non è possibile dividere per 0.");
        } finally {
            s.close();
        }
    }
}
