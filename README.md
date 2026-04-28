# Platformă e-learning

## Descriere generală

Proiectul modelează o platformă e-learning în care utilizatorii pot avea roluri diferite, precum cursanți sau lectori. Lectorii pot crea cursuri, module, lecții și quiz-uri, iar cursanții se pot înscrie la cursuri, pot parcurge conținutul și pot rezolva quiz-uri. Sistemul gestionează cursuri, utilizatori, înscrieri, quiz-uri, întrebări, răspunsuri, scoruri și certificate.

---

## 1.1 Lista acțiunilor / interogărilor posibile în sistem

1. Adaugă un curs nou în platformă.
2. Înregistrează un cursant nou.
3. Înregistrează un lector nou.
4. Înscrie un cursant la un curs.
5. Adaugă un modul într-un curs.
6. Adaugă o lecție într-un modul.
7. Adaugă un quiz pentru un curs.
8. Adaugă o întrebare într-un quiz.
9. Cursantul rezolvă un quiz.
10. Calculează și salvează scorul obținut de un cursant la un quiz.
11. Afișează cursurile la care este înscris un cursant.
12. Afișează rezultatele unui cursant la quiz-uri.
13. Afișează clasamentul cursanților după scor.
14. Caută cursuri după titlu.
15. Generează un certificat pentru un cursant care a finalizat un curs.

---

## 1.2 Lista tipurilor de obiecte din domeniu

1. `Utilizator`
2. `Cursant`
3. `Lector`
4. `Curs`
5. `ModulCurs`
6. `Lectie`
7. `Quiz`
8. `Intrebare`
9. `Raspuns`
10. `RezultatQuiz`
11. `Inscriere`
12. `Certificat`
13. 
---

## 2. Implementare Java

### 2.1 — Clase și OOP

- [x] Cel puțin **8 clase** care modelează obiectele definite la punctul 1
    - Am implementat clasele de domeniu: `Utilizator`, `Cursant`, `Lector`, `Curs`, `ModulCurs`, `Lectie`, `Quiz`, `Intrebare`, `Raspuns`, `RezultatQuiz`, `Inscriere`, `Certificat`.

- [x] Atribute `private` sau `protected`, cu getteri/setteri acolo unde e necesar
    - Clasele folosesc atribute `private` pentru încapsulare.
    - Clasa abstractă `Utilizator` folosește atribute `protected`, pentru a putea fi accesate în clasele derivate `Cursant` și `Lector`.

- [x] Metode `toString()`, `equals()` și `hashCode()` suprascrise la cel puțin **2 clase**
    - Metodele au fost suprascrise în clase precum `Cursant`, `Lector` și `Curs`.
    - Compararea obiectelor se face pe baza identificatorului `id`.

- [x] Cel puțin o **ierarhie de moștenire** (`extends`) cu minim **2 niveluri**
    - Am implementat ierarhia:
        - `Utilizator` → `Cursant`
        - `Utilizator` → `Lector`

- [x] Cel puțin o **clasă abstractă** sau o **interfață** folosită în ierarhie
    - Clasa `Utilizator` este abstractă.
    - Aceasta conține metoda abstractă `getRol()`, implementată diferit în `Cursant` și `Lector`.

- [x] Cel puțin o **clasă imutabilă**: atribute `final`, fără setteri, inițializate complet în constructor
    - Clasa `Certificat` este imutabilă.
    - Are atribute `final`, nu are setteri și este inițializată complet prin constructor.

- [x] Cel puțin **2 excepții custom** aruncate și tratate în cod
    - Am implementat excepțiile custom:
        - `CursNegasitException`
        - `QuizInvalidException`
    - Acestea sunt aruncate în servicii și tratate în `Main`.

---

### 2.2 — Colecții

- [x] Cel puțin **2 tipuri diferite de colecții** (`List`, `Set`, `Map`, `Queue`, etc.)
    - Am folosit `List` pentru stocarea cursurilor, utilizatorilor, quiz-urilor, înscrierilor și certificatelor.
    - Am folosit `Map` pentru indexarea rapidă după `id` sau `email`.
    - Am folosit `Set` / `TreeSet` pentru păstrarea cursurilor sortate.

- [x] Cel puțin una sortată — prin implementarea `Comparable` pe clasă sau prin `Comparator`
    - Am folosit un `Comparator<Curs>` în `CursService`.
    - Cursurile sunt sortate după titlu, iar în caz de egalitate după `id`.

- [x] Cel puțin un `Map` folosit pentru indexare sau grupare
    - În `CursService`, cursurile sunt indexate după `id` folosind `Map<Integer, Curs>`.
    - În `UtilizatorService`, utilizatorii sunt indexați după `id` și `email`.
    - În `InscriereService`, înscrierile sunt grupate după id-ul cursantului folosind `Map<Integer, List<Inscriere>>`.

---

### 2.3 — Servicii

- [x] Cel puțin **2 clase de serviciu** care expun operațiile sistemului
    - Am implementat mai multe clase de serviciu:
        - `CursService`
        - `UtilizatorService`
        - `QuizService`
        - `InscriereService`
        - `CertificatService`

- [x] Fiecare serviciu implementat ca **Singleton**
    - Fiecare serviciu are constructor privat și metodă statică `getInstance()`.
    - Astfel, există o singură instanță pentru fiecare serviciu pe durata rulării aplicației.

- [x] Serviciile expun cel puțin operațiile: **adaugă**, **șterge**, **caută după id/nume**, **listează toate**
    - `CursService` permite adăugarea, ștergerea, căutarea după `id`, căutarea după titlu și listarea cursurilor.
    - `UtilizatorService` permite adăugarea utilizatorilor, căutarea după `id` sau `email`, ștergerea și listarea utilizatorilor.
    - `QuizService` permite adăugarea, ștergerea, căutarea și listarea quiz-urilor.
    - `InscriereService` gestionează înscrierea cursanților la cursuri și listarea cursurilor unui cursant.
    - `CertificatService` gestionează generarea și listarea certificatelor.

- [x] O clasă `Main` care apelează **toate cele 10 acțiuni** definite la punctul 1, demonstrând funcționarea completă a sistemului
    - Clasa `Main` creează obiecte de test și apelează serviciile sistemului.
    - Sunt demonstrate operații precum înregistrarea unui lector, înregistrarea unui cursant, adăugarea unui curs, înscrierea la curs, crearea unui quiz, calcularea scorului și generarea unui certificat.

---

### 2.4 — Organizare și calitate

- [x] Codul organizat în sub-pachete logice:

```text
com.pao.elearning/
├── model/       ← clasele de domeniu
├── service/     ← serviciile singleton
├── exception/   ← excepțiile custom
└── Main.java