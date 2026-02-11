import java.util.Scanner;
import java.util.InputMismatchException;

public class CalcolatriceConMemoria {

    double mem = 0;

    // reset calcolatrice:
    public CalcolatriceConMemoria() {
        this.mem = 0;
    }

    // setta il nuovo valore:
    public CalcolatriceConMemoria(double mem) {
        this.mem = mem;
    }

    // restituisce il valore correntemente in memoria
    public double getMem() {
        return this.mem;
    }

    // operazioni:
    public double add(double op2) {
        this.mem += op2;
        return this.mem;
    }

    public double sub(double op2) {
        this.mem -= op2;
        return this.mem;

    }

    public double mul(double op2) {
        this.mem *= op2;
        return this.mem;
    }

    public double div(double op2) throws DivideByZeroException {

        if (op2 == 0) {
            throw new DivideByZeroException("Non divisibile per zero");
        }
        this.mem /= op2;
        return this.mem;

    }

    public double operate(char operator, double op2) throws InputMismatchException, DivideByZeroException {
        switch (operator) {
            case '+':
                add(op2);
                return this.mem;
            case '-':
                sub(op2);
                return this.mem;
            case '*':
                mul(op2);
                return this.mem;
            case '/':
                div(op2);
                return this.mem;
            default:
                throw new InputMismatchException("Operatore non riconosciuto");
        }
    }

    public static void printHead() { // metodo per non dover ripetere la scrittura in più punti.
        // MODIFIES: System.out
        // EFFECTS: stampa l'intestazione delle operazioni
        System.out.println("Inserisci <operatore> <operando>. es: + 3");
        System.out.println("Per terminare inserire il carattere '='");
    }

    public static void main(String[] args) {
        CalcolatriceConMemoria calcolatrice;

        if (args.length > 0) {
            calcolatrice = new CalcolatriceConMemoria(Integer.parseInt(args[0]));
        } else {
            calcolatrice = new CalcolatriceConMemoria();
        }

        System.out.println("Calcolatrice inizializzata, valore: " + calcolatrice.getMem());
        printHead();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            // prendo il primo carattere per capire l'operazione
            char op = scanner.next().charAt(0);
            // blocco con "="
            if (op == '=') {
                break;
            }

            double num = scanner.nextDouble();
            // printHead();

            try {
                calcolatrice.operate(op, num);
                System.out.println("valore in memoria: " + calcolatrice.getMem());
            } catch (DivideByZeroException e) {
                // TODO: handle exception
                System.out.println(e.getMessage());
                printHead();
            }
        }

        scanner.close();

        System.out.println("Il risultato finale e' " + calcolatrice.getMem());

    }
}
