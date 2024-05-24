import java.util.Scanner;
public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            wisielec hangman = new wisielec(scanner);
            hangman.startGry();
            
            System.out.println("Rozpoczynam nową grę!");
        }
    }
}
