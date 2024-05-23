import java.util.Random;
import java.util.Scanner;

public class wisielec {
    private String haslo;

    private String uzyteLitery = "";
    private int liczbaProb = 0;
    private final int maksymalnaIloscProb = 10;

    private long stoperStart;
    private long stoperStop;

    private static int liczbaZwyciestw = 0;
    private static int liczbaPorazek = 0;

    
    private Scanner scanner;

    public wisielec(Scanner scanner) {
        this.scanner = scanner;
    }

// _______________________________________________________________

    public void startGry() {
        int poziomTrudnosci = wybierzPoziomTrudnosci();

        haslo = losujSlowo(poziomTrudnosci);
        wyswietlStanGry();
        stoperStart = System.nanoTime();

        while (!wygrana(haslo, uzyteLitery) && !przegrana(liczbaProb)) {
            System.out.print("Podaj literę: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Podaj pojedynczą literę!");
                System.out.println("__________________________");
                continue;
            }

            char litera = input.charAt(0);
            if (uzyteLitery.contains(String.valueOf(litera))) {
                System.out.println("Ta litera została już podana!");
                System.out.println("__________________________");
                continue;
            }

            uzyteLitery += litera;
            if (czyJestLitera(haslo, litera)) {
                System.out.println("Zgadłeś literę!");
                System.out.println("__________________________");
            } else {
                System.out.println("Nie ma tej litery w haśle.");
                System.out.println("__________________________");
                liczbaProb++;
            }

            wyswietlStanGry();
        }
        stoperStop = System.nanoTime();

        if (wygrana(haslo, uzyteLitery)) {
            System.out.println("Wygrałeś! Hasło to: " + haslo);
            liczbaZwyciestw++;
        } else {
            System.out.println("Przegrałeś! Poprawne hasło to: " + haslo);
            liczbaPorazek++;
        }
        wyswietlStatystyki();
    }

    // ___________________________________

    private void wyswietlStatystyki() {
        long czasGry = (stoperStop - stoperStart) / 1_000_000_000; 
        System.out.println("Statystyki gry:");
        System.out.println("Czas: " + czasGry + " s");
        if (czasGry < 20) {
            System.out.println("Jesteś szybki jak struś! Pytanie tylko czy celnie trafiasz!");
        }
        System.out.println("Liczba prób: " + liczbaProb);
        System.out.println("Użyte litery: " + uzyteLitery);
        System.out.println("Zwycięstwa: " + liczbaZwyciestw);
        System.out.println("Porażki: " + liczbaPorazek);
    }

    // ___________________________________


    private int wybierzPoziomTrudnosci() {
        System.out.println("Wybierz poziom trudności:");
        System.out.println("1:Łatwy");
        System.out.println("2:Średni");
        System.out.println("3:Trudny");
        int poziom = 0;
        while (poziom < 1 || poziom > 3) {
            System.out.print("Podaj numer poziomu (1-3): ");
            try {
                poziom = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Niepoprawny wybór. Spróbuj ponownie.");
            }
        }
        return poziom;
    }


    // ___________________________________

    private void wyswietlStanGry() {
        System.out.println("Hasło: " + ukryjNieodgadnieteLitery());
        System.out.println("Użyte litery: " + uzyteLitery);
        System.out.println("Liczba prób: " + liczbaProb + "/" + maksymalnaIloscProb);
    }

    private String ukryjNieodgadnieteLitery() {
        StringBuilder result = new StringBuilder();
        for (char c : haslo.toCharArray()) {
            if (uzyteLitery.contains(String.valueOf(c))) {
                result.append(c);
            } else {
                result.append('_');
            }
            result.append(' ');
        }
        return result.toString();
    }
//  ____________________________________

    private String[] latweSlowa = {"samolot", "blok", "komputer", "drzewo", "dostawa", "tulipan", "korona", "sukienka", "pszenica", "sport", "dziecko", "ryba", "niebo", "chmury"}; 
    
    private String[] srednieSlowa = {"kaszanka", "polowanie", "komputer", "kolorowanka", "tortury", "emigrant", "magazyn"}; 
    
    private String[] trudneSlowa = {"elokwencja", "telekomunikacja", "certyfikat", "wartownik", "przedsionek", "zmartwienie"}; 

    private String losujSlowo(int poziomTrudnosci) {
        Random rand = new Random();
        String[] wybranaTablica;
        switch (poziomTrudnosci) {
            case 1:
            wybranaTablica = latweSlowa;
                break;
            case 2:
            wybranaTablica = srednieSlowa;
                break;
            case 3:
            wybranaTablica = trudneSlowa;
                break;
            default:
                throw new IllegalArgumentException("Niepoprawny poziom trudności");
        }
        int index = rand.nextInt(wybranaTablica.length);
        return wybranaTablica[index];
    }

    private boolean czyJestLitera(String slowa, char litera) {
        return slowa.indexOf(litera) != -1; //
    }

    private boolean wygrana(String haslo, String uzyteLitery) {
        for (int i = 0; i < haslo.length(); i++) {
            if (!uzyteLitery.contains(String.valueOf(haslo.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    private boolean przegrana(int liczbaProb) {
        return liczbaProb == maksymalnaIloscProb;
    }
}
