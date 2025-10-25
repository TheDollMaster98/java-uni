/**
 * Angoli di un triangolo
 * Scrivere un programma che legga da riga di comando le ampiezze di due angoli
 * di un triangolo e stampi, se possibile, l'ampiezza del terzo angolo.
 * 
 * Suggerimento: ricordatevi che in un triangolo la somma delle ampiezze degli
 * angoli interni è sempre 180°.
 * 
 * Esempio d'esecuzione:
 * $ java Angoli 50 60
 * Ampiezza terzo angolo = 70°
 * 
 * $ java Angoli 150 70
 * I due angoli non appartengono ad un triangolo
 */
public class Angoli {
    public static void main(String[] args) {
        // Check if one argument is passed
        if (args.length != 2) {
            System.out.println("Please provide exactly one integer argument for the grade.");
            return;
        }

        int angolo_1 = Integer.parseInt(args[0]);
        int angolo_2 = Integer.parseInt(args[1]);

        int sommaAngoliInterni = 180;
        int sommaAngoli = angolo_1 + angolo_2;
        int angolo_3 = sommaAngoliInterni - sommaAngoli;

        if (angolo_3 < 0) {
            System.out.println("I due angoli non appartengono ad un triangolo  ");
        } else {
            System.out.println("Ampiezza terzo angolo = " + angolo_3 + "°");
        }
    }
}
