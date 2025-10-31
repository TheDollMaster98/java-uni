/*
 Sottostringhe palindrome
Scrivere un programma che legga da riga di comando una stringa senza spazi e stampi a schermo tutte le sottostringhe palindrome della stringa.

Oltre al metodo main(), devono essere definiti ed utilizzati almeno i seguenti metodi:

private static boolean isPalindroma(String s) che restituisce true se s è palindroma e false altrimenti;
Esempio d'esecuzione
$ java Palindrome sottotono
[ otto tt tot oto ono ]

$ java Palindrome banana
[ ana anana nan ana ]

$ java Palindrome ereggere
[ ere ereggere regger egge gg ere ]

$ java Palindrome Vaðlaheiðarvegavinnuverkfærageymsluskúrslyklakippuhringurinn
[ nn pp nn]

$ java Palindrome donaudampfschifffahrtselektrizitätenhauptbetriebswerkbauunterbeamtengesellschaft
[ ff fff ff ele izi tät uu ese ll ]
 */
public class Palindrome {
    private static boolean isPalindroma(String s) {

        String s1 = s.toLowerCase().trim();
        int n = s.length() / 2;

        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }

        return true;

    }

    public static void main(String[] args) {
        String s = args[0];
        System.out.print("[ ");

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j++) {

                String temp = s.substring(i, j);

                if (isPalindroma(temp)) // il metodo non stampa, restituisce risultato
                    System.out.print(temp + " "); // il main stampa - divido logica dalla presentazione
            }
        }

        System.out.println("]");
    }

}
