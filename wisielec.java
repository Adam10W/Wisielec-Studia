import java.util.Random;
import java.util.Scanner;

public class wisielec {
    private String haslo;
    private String podaneLitery;
    private String odgadnieteLitery = "";
    private int liczbaProb = 0;
    private final int maksymalnaIloscProb = 10;

// _______________________________________________________________

    public void rozpocznijGre() {
        haslo = losujSlowo();
        System.out.println("Witaj w grze Wisielec!");
        wyswietlStanGry();

        Scanner scanner = new Scanner(System.in);
        while (!wygrana(haslo, odgadnieteLitery) && !przegrana(liczbaProb)) {
            System.out.print("Podaj literę: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Podaj pojedynczą literę!");
                continue;
            }

            char litera = input.charAt(0);
            if (odgadnieteLitery.contains(String.valueOf(litera))) {
                System.out.println("Ta litera została już podana!");
                continue;
            }

            odgadnieteLitery += litera;
            if (czyJestLitera(haslo, litera)) {
                System.out.println("Zgadłeś literę!");
            } else {
                System.out.println("Nie ma tej litery w haśle.");
                liczbaProb++;
            }

            wyswietlStanGry();
        }

        if (wygrana(haslo, odgadnieteLitery)) {
            System.out.println("Gratulacje, wygrałeś! Hasło to: " + haslo);
        } else {
            System.out.println("Przegrałeś! Poprawne hasło to: " + haslo);
        }
        scanner.close();
    }

    private void wyswietlStanGry() {
        System.out.println("Hasło: " + ukryjNieodgadnieteLitery());
        System.out.println("Zgadnięte litery: " + odgadnieteLitery);
        System.out.println("Liczba prób: " + liczbaProb + "/" + maksymalnaIloscProb);
    }

    private String ukryjNieodgadnieteLitery() {
        StringBuilder result = new StringBuilder();
        for (char c : haslo.toCharArray()) {
            if (odgadnieteLitery.contains(String.valueOf(c))) {
                result.append(c);
            } else {
                result.append('_');
            }
            result.append(' ');
        }
        return result.toString();
    }
//  ____________________________________

    private String[] slowa = {"samolot", "blok", "kaszanka", "elokwencja", "telekomunikacja", "polowanie", "komputer", "drzewo", "dostawa", "telekomunikacja", "kolorowanka", "tulipan", "korona", "sukienka", "pszenica", "sport", "dziecko", "ryba", "niebo", "chmury"}; 

    private String losujSlowo() {
        Random rand = new Random();
        int index = rand.nextInt(slowa.length);
        return slowa[index]; 
    }

    private boolean czyJestLitera(String slowa, char litera) {
        return slowa.indexOf(litera) != -1; //
    }

    private boolean wygrana(String haslo, String odgadnieteLitery) {
        for (int i = 0; i < haslo.length(); i++) {
            if (!odgadnieteLitery.contains(String.valueOf(haslo.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    private boolean przegrana(int liczbaProb) {
        return liczbaProb == maksymalnaIloscProb;
    }
}
