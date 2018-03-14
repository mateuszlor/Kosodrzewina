# Kosodrzewina

[![Build Status](https://travis-ci.org/mateuszlor/Kosodrzewina.svg?branch=master)](https://travis-ci.org/mateuszlor/Kosodrzewina)

Kosodrzewina jest projektem służącym do zarządzania wyporzyczeniami samochodów dostawyczych typu laweta
osobom trzecim. Program stworzony został w technologii Java z wykorzystaniem framworka Spring Boot,
co czyni z systemu aplikację webową. Głównymi założeniami projektu jest wyporzyczanie samochodów dostawczych, sumowanie dochodów i wydatków, 
a także serwisowanie posiadanych pojazdów. Domyślnie aplikacja ma funkcjonować w sieci lokalnej na małym serwerze stojącym 
na Orange PI. 

W projekcie wykorzystano następujące technologie: <br>
<ul>Maven - służy do zarządzania zależnościami</ul>
<ul>Spring Boot - technologia służąca do tworzenia aplikacji webowych</ul>
<ul>Spring Security - framework wspomagjący zarządzanie dostępem do projektu</ul>
<ul>Thymeleaf - silnik template</ul>
<ul>Bootstrap - framework służący do tworzenia interfejsów użytkownika</ul>

**Travis CI:** 

Program korzysta z technologii Travis CI służącej do budowania i testowania aplikacji korzystającej z repozytorium Git.
Skonfigurowany projekt z narzędziem można znaleźć pod adresem:
https://travis-ci.org/mateuszlor/Kosodrzewina

**Theme:**

W aplikacji wykorzystano darmowy template GUI o nazwie AdminLTE. Możliwości motywu można podziwiać pod adresem:
https://adminlte.io/themes/AdminLTE/index2.html

**Baza danych:**

Projekt wykorzystuję baze danych MySQL w wersji 5.7.17. Konfiguracja bazy danych znajduje się w pliku **application.properties**<br>
Nazwa bazy danych to <strong>transport</strong>.<br>

**Twórcy:**

Lukasz 'Vertig0' Kluch<br>
Sebastian 'Nex0Zero' Graf<br>
Mateusz 'FTH' Lorencki
