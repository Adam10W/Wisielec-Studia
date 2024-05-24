# 2. Gra w wisielca
## Opis

Projekt zaliczeniowy jako "Gra w wisielca" jest programem polegającym na odtworzeniu popularnej gry o nazwie "wisielec". Polega ona na odgadywaniu różnych słów, przy ograniczonej ilości prób. Można wykorzystać go na kilka sposobów, np: rozrywka i nauka nowych słów - angielski/niemiecki.


### Instalacja

Nie ma potrzeby pobierania dodatkowych paczek!
Oto instrukcja jak zaintalować grę w wisielca:
- otworzyć terminal BASH
- sklonować repozytorium za pomocą komendy: 'git clone https://github.com/Adam10W/Wisielec-Studia'
- gra powinna znajdować się w wybranym miejscu docelowym


### Instrukcja obsługi gry

#### Aby uruchomić grę należy:
- otworzyć terminal wpisać: 'javac wisielec.java Main.java'
- następnie: 'java.Main'
LUB
- otworzyć klasę Main, następnie kliknąć "run"
LUB
- kliknąć PPM w klasię Main, następnie kliknąć w "run Java"

#### Rozgrywka:
- Po uruchomieniu gry zostaniemy zapytani jaką bazę słów chcemy używać. Polski, angielski lub niemiecki. Wyboru dokonujemy zgodnie z poleceniami w konsoli tzn. 1:Polski 2:Angielski 3:Niemiecki. Wybrany numer wpisujemy do konsoli.
- Następnie dokonujemy wyboru poziomu trudności rozgrywki. Od Łatwy-Średni-Trudny, każdy poziom ma określoną ilość maksymalnych prób. Wyboru dokonujemy analogicznie do języków... wpisujemy w konosli od 1 do 3.
- Po wybraniu poziomu trudności rozpoczynamy rozgrywkę. W konsoli pojawia się nam hasło oraz liczba litery które owe hasło posiada. Niżej mamy również podaną tablicę liter, których użyliśmy. Pod nią mamy dostęp do liości prób którą wykorzystaliśmy oraz ile nam jeszcze zostało.
- Aby rozpocząć odgadywanie słowa wystarczy, że wpiszemy literę z klawiatury. (Program jest zabezpieczony przed wpisywaniem innych wartości)* 
- Odgadując wybraną przez nas literę zostaniemy poinformowani o poprawności naszej odpowiedzi. *TAK->nasza litera zastępuję puste pole* | *NIE->litery dalej pozostają nie odkryte*
- Rozgrywka toczy się do momentu odgadnięcia słowa lub wykorzystania wszystkich prób (zależnie od wybranego poziomu).
- Pod koniec rozgrywki otrzymujemy statystyki w ich skład wchodzą: **Czas gry podany w sekundach**, **liczba prób**, **użyte litery**, **zwycięstwa i porażki**.
- Gra samoistnie rozpoczyna kolejną rozgrywkę.


### Funkcjonalności

- liczenie statystyk takich jak: **Czas gry podany w sekundach**, **liczba prób**, **użyte litery**, **zwycięstwa i porażki**
- możliwośc edytowania bazy słów: **słowa polskie**, **słowa angielskie**, **słowa niemieckie**
- możliwość wyboru poziomu trudności: **łatwy**, **średni** i **trudny**
- wybór **losowego** słowa z bazy słów