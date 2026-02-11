import java.util.Arrays;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        String[] agenda = new String[6]; // di default sappiamo che l'agenda ha 6 slot
        Scanner scanner = new Scanner(System.in);
        // fintanto che ho spazio vado avanti:
        while (Ricevimento.orariDisponibili(agenda).length > 0) {
            int[] o = Ricevimento.orariDisponibili(agenda);

            System.out.println("Puoi prenotare un appuntamento alle " + Arrays.toString(o));

            System.out.println("Qual è il tuo nome?");
            String nome = scanner.next();
            System.out.println("A che ora vorresti l'appuntamento?");
            int orario = scanner.nextInt();
            // Lancio effettivamente i metodi + check errori:
            try {
                Ricevimento.prenota(agenda, orario, nome);
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
                System.exit(0);
                // ParameterException è una IllegalArgumentException per gestire il caso sotto
            } catch (TimeBusyException | AlreadyBookedException | ParameterException e) {
                System.out.println(e.getMessage());
                // Questa eccezione può avvenire se l'orario o nome non è valido
                // (in tal caso non porta a termine del programma) o se l'agenda non ha gli slot
                // corretti (in tal caso bisogna terminare)
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.exit(0);
            }

        }

        s.close();
        // check posti occupati:
        for (int i = 0; i < agenda.length; i++) {
            System.out.println("Alle " + (i + 13) + " è fissato l'appuntamento di " + agenda[i]);
        }

    }

}
