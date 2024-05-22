import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        boolean kontynuuj = true;
        Scanner scanner = new Scanner(System.in);
        
        while (kontynuuj) {
            wisielec hangman = new wisielec();
            hangman.startGry();
         
            System.out.print("Grasz dalej? tak/nie ");
            String odpowiedz = scanner.nextLine().trim().toLowerCase();
            if (!odpowiedz.equals("tak")) {
                kontynuuj = false;
            }

      } 
      scanner.close();
    }
}