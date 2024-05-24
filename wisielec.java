import java.util.Random;
import java.util.Scanner;

public class wisielec {
    private String haslo;

    private String uzyteLitery = "";
    private int liczbaProb = 0;
    private int maksymalnaIloscProb;

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
        int jezyk = wybierzJezyk();
        int poziomTrudnosci = wybierzPoziomTrudnosci();

        haslo = losujSlowo(jezyk);
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
        long czasGry = (stoperStop - stoperStart) / 1000000000; 
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


    private int wybierzJezyk() {
        System.out.println("Wybierz język słów:");
        System.out.println("1:Polski");
        System.out.println("2:Angielski");
        System.out.println("3:Niemiecki");
        int wybranyJezyk = 0;
        while (wybranyJezyk < 1 || wybranyJezyk > 3) {
            System.out.print("Podaj numer języka (1-3): ");
            try {
                wybranyJezyk = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Niepoprawny wybór.");
            }
        }
        return wybranyJezyk;
    }

    private int wybierzPoziomTrudnosci() {
        System.out.println("Wybierz poziom trudności:");
        System.out.println("1: Łatwy (12 prób)");
        System.out.println("2: Średni (10 prób)");
        System.out.println("3: Trudny (6 prób)");
        int poziom = 0;
        while (poziom < 1 || poziom > 3) {
            System.out.print("Podaj numer poziomu trudności (1-3): ");
            try {
                poziom = Integer.parseInt(scanner.nextLine());
                switch(poziom) {
                    case 1:
                        maksymalnaIloscProb = 12;
                        break;
                    case 2:
                        maksymalnaIloscProb = 10;
                        break;
                    case 3:
                        maksymalnaIloscProb = 6;
                        break;
                    default:
                        System.out.println("Niepoprawny wybór. Spróbuj ponownie.");
                }
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

    private String[] polskieSlowa = {"samolot", "blok", "komputer", "drzewo", "dostawa", "tulipan", "korona", "sukienka", "pszenica", "sport", "dziecko", "ryba", "niebo", "chmury"}; 
    
    private String[] angielskieSlowa = {"favourite", "meet", "describe", "fundation", "work", "mood", "inspire"}; 
    
    private String[] niemieckieSlowa = {"frau", "mensch", "haus", "prozent", "stadt", "schule"}; 

    private String losujSlowo(int jezyk) {
        Random rand = new Random();
        String[] wybranaTablica;
        switch (jezyk) {
            case 1:
            wybranaTablica = polskieSlowa;
                break;
            case 2:
            wybranaTablica = angielskieSlowa;
                break;
            case 3:
            wybranaTablica = niemieckieSlowa;
                break;
            default:
                throw new IllegalArgumentException("Niepoprawny język");
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
