import java.util.Scanner;

/**
 * Scrivere una classe Test che nel metodo main() legga da riga di comando
 * l'anno dell'Olimpiade e successivamente, da standard input delle istruzioni
 * nel formato:
 * 
 * aggiungi <giorno> Cerimonia <nome> <apertura / chiusura> per aggiungere una
 * nuova Cerimonia, di apertura o chiusura, nel giorno specificato e con il nome
 * specificato
 * aggiungi <giorno> Gara <nome> <numero atleti> per aggiungere una nuova Gara
 * nel giorno specificato, con il nome e il numero di atleti specificati
 * rimuovi <giorno> per rimuovere l'Evento del giorno specificato La lettura
 * termina quando viene inserito, con la combinazione Ctrl+D il comando
 * End-Of-File. Al termine della lettura, stampare su standard output
 * l'Olimpiade, indicando il suo anno e gli eventi previsti, ordinati per giorno
 * dell'Olimpiade. Successivamente, stampare su standard output gli eventi
 * ordinati nel loro ordine naturale, come da esempio d'esecuzione.
 */

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Olimpiade olimpiade = new Olimpiade(Integer.parseInt(args[0]));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(" ");
            if (tokens[0].equals("aggiungi")) {
                int giorno = Integer.parseInt(tokens[1]);
                if (tokens[2].equals("Cerimonia")) {
                    String nome = tokens[3];
                    String tipo = tokens[4];
                    try {
                        olimpiade.aggiungi(giorno, new Cerimonia(nome, tipo));
                    } catch (GiornoException e) {
                        System.out.println(e.getMessage());
                    } catch (EventoException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (tokens[2].equals("Gara")) {
                    String nome = tokens[3];
                    int numAtleti = Integer.parseInt(tokens[4]);
                    try {
                        olimpiade.aggiungi(giorno, new Gara(nome, numAtleti));
                    } catch (GiornoException e) {
                        System.out.println(e.getMessage());
                    } catch (EventoException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else if (tokens[0].equals("rimuovi")) {
                int giorno = Integer.parseInt(tokens[1]);
                try {
                    olimpiade.rimuovi(giorno);
                } catch (GiornoException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println(olimpiade);
        System.out.println("Eventi in ordine di durata:");
        for (Evento evento : olimpiade) {
            System.out.println("\t" + evento);
        }
    }
}
