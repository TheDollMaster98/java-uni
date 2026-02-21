import java.util.Scanner;

public class Test {
    /**
     * Scrivere una classe `Test` che nel metodo `main()` legga da **standard
     * input** delle istruzioni nel formato:
     * 
     * - `inserisci <giorno> Cioccolatino <nome> <% cacao>` per inserire un nuovo
     * `Cioccolatino` nel giorno specificato con il nome e percentuale cacao
     * specificati
     * - `inserisci <giorno> Giocattolo <nome> <descrizione>` per inserire un nuovo
     * `Giocattolo` nel giorno specificato con il nome e la descrizione specificata
     * - `apri <giorno>` per aprire la casella del giorno specificato, rimuovendo
     * l'eventuale `Sorpresa` contenuta dal `Calendario` e scrivendola su **standard
     * output**
     * - La lettura termina quando viene inserito, con la combinazione `Ctrl+D` il
     * comando `End-Of-File`. Al termine della lettura, stampare su **standard
     * output** il `Calendario`, indicando il suo costo e le sorprese contenute,
     * come da esempio d'esecuzione.
     * 
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calendario calendario = new Calendario();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ", 4);
            String command = parts[0];
            int giorno = Integer.parseInt(parts[1]);

            try {
                if (command.equals("inserisci")) {
                    // questi accessi stanno DENTRO inserisci, non fuori
                    String tipoSorpresa = parts[2];
                    String nome = parts[3].split(" ")[0];
                    String extra = parts[3].split(" ", 2)[1];

                    if (tipoSorpresa.equals("Cioccolatino")) {
                        double cacao = Double.parseDouble(extra.replace("%", ""));
                        calendario.inserisci(giorno, new Cioccolatino(nome, cacao));
                    } else if (tipoSorpresa.equals("Giocattolo")) {
                        calendario.inserisci(giorno, new Giocattolo(nome, extra));
                    }
                } else if (command.equals("apri")) {
                    System.out.println(calendario.apri(giorno));
                }
            } catch (GiornoException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(calendario);
        scanner.close();
    }
}
