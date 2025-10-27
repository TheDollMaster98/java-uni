/**
 * Scrivere un programma che legga da riga di comando un numero reale raggio e
 * stampi i valori di circonferenza e area di un cerchio di raggio raggio.
 * 
 * Oltre al metodo main(), il programma deve definire e utilizzare i seguenti
 * metodi:
 * 
 * public static double CalcolaArea(double raggio) che restituisce il valore
 * dell'area del cerchio associato;
 * public static double CalcolaCirconferenza(double raggio) che restituisce il
 * valore della circonferenza del cerchio associato.
 * Esempio d'esecuzione:
 * $ java Cerchio 10.5
 * Area del cerchio: 346.36059005827474
 * Circonferenza del cerchio: 65.97344572538566
 * 
 * $ java Cerchio 3.6
 * Area del cerchio: 40.71504079052372
 * Circonferenza del cerchio: 22.61946710584651
 */

public class Cerchio {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Errore, inserire un numero");
            return;
        }

        double raggio = Double.parseDouble(args[0]);

        System.out.println("Area del cerchio: " + CalcolaArea(raggio));
        System.out.println("Circonferenza del cerchio: " + CalcolaCirconferenza(raggio));
    }

    public static double CalcolaArea(double raggio) {

        return raggio * Math.pow(Math.PI, 2);
    }

    public static double CalcolaCirconferenza(double raggio) {
        return 2 * Math.PI * raggio;
    }

}
