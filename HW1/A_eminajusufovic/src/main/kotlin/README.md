# Zadaća 1A – Kotlin Programeri

## Opis projekta

Ovaj projekt predstavlja jednostavnu aplikaciju u **Kotlinu** koja modelira programere i njihove karakteristike. Cilj je vježbati **interfejse, nasljeđivanje, rad s kolekcijama** i implementaciju funkcija za filtriranje, grupisanje i statistiku podataka.

### Struktura i odnosi

* **Interfejs `Person`**
  Definiše osnovne metode `fullName()` i `country()`, koje vraćaju puno ime osobe i zemlju porijekla.

* **Klasa `Developer`**
  Apstraktna klasa koja nasljeđuje `Person` i sadrži zajedničke atribute svih programera: ime, prezime, godine iskustva, zemlju i listu programskih jezika. Također uključuje validaciju podataka (ime i prezime ne mogu biti prazni, godine iskustva ne mogu biti negativne, lista jezika ne smije biti prazna).

* **Klase `backendDeveloper` i `frontendDeveloper`**
  Nasljeđuju klasu `Developer` i dodaju specifičan atribut za framework koji koriste, što omogućava filtriranje i prikaz specifičnih informacija po tipu programera.

* **Funkcije za analizu**

  * `countDeveloper` i `countDeveloperManual` – broje koliko programera koristi svaki programski jezik (prva implementacija koristi Kotlin funkcije `groupingBy` i `eachCount`, druga ručno iterira kroz mapu).
  * `avgExpeByLanguage` i `avgExpManual` – računaju prosječno iskustvo po jeziku (prva koristi `groupBy` i `mapValues`, druga ručno koristi `mutableMap`).
  * `filterByFramework` – filtrira programere po korištenom framework-u.
  * `devInfo` – ispisuje kompletne informacije o programeru u čitljivom formatu.

# Pokretanje projekta 

1. Klonirati repozitorij i preći na granu `zadaca1`:
```bash
git clone -b zadaca1 https://github.com/fet-tk-course/homework1-eminajusufovic-1.git
```

2. Ući u direktorij projekta:
```bash
cd homework1-eminajusufovic-1/A_eminajusufovic
```

3. Otvoriti `src/main/kotlin` direktorij u IntelliJ IDEA ili bilo kojem Kotlin IDE-u.

4. Pokrenuti funkciju `main()` koja se nalazi u datoteci `Main.kt`.  
   - U IntelliJ IDEA kliknite desnim klikom na datoteku `Main.kt` i odaberite **Run 'Main.kt'**.

5. Program će prikazati ispis svih programera, broj programera po jeziku, prosječno iskustvo po jeziku i filtrirane liste po framework-u.


## Uporedna analiza pristupa

### 1. Grupisanje (`groupBy` / `groupingBy`)

* Omogućava elegantno i koncizno rješenje korištenjem ugrađenih Kotlin funkcija.
* Kod je kraći i čitljiviji.
* Pogodan za veće skupove podataka ili kada se želi koristiti funkcionalna paradigma.
* Primjeri: `countDeveloper`, `avgExpeByLanguage`.

### 2. Ručna implementacija (`manual`)

* Korištenje `mutableMap` i iteracija kroz listu.
* Veći broj linija koda, ali omogućava detaljnu kontrolu nad logikom.
* Dobar pristup za učenje osnovnih algoritamskih principa, posebno za razumijevanje grupiranja i prosječnog računanja.
* Primjeri: `countDeveloperManual`, `avgExpManual`.

### Zaključak

* Funkcije `groupBy` i `groupingBy` se preporučuju za produkcijska rješenja zbog čitljivosti i jednostavnosti.
* Ručna implementacija ima edukativnu vrijednost i pomaže u razumijevanju osnovnih principa programiranja.

## Korištenje AI alata

Tokom razvoja koda korišten je **ChatGPT** isključivo u svrhu savjetovanja:

* Savjeti za validaciju podataka (`IllegalArgumentException` umjesto generičkog `Throwable`).
* Upoznavanje sa funkcijama `getOrDefault` i `getOrPut` u Kotlinu.
* Objašnjenje razlika između funkcionalnog grupisanja (`groupBy`) i ručne iteracije.


## Primjer ispisa programa

```
All developers
Emina Jusufovic — Backend developer — jezici: Java, Python — framework: Ktor
Amila Residovic — Frontend developer — jezici: JavaScript, TypeScript — framework: React
Amina Hasic — Backend developer — jezici: HTML, CSS, JavaScript — framework: Vue.js
Armin Coralic — Frontend developer — jezici: Java, Kotlin — framework: Ktor
Adnan Hasic — Backend developer — jezici: Python, Java — framework: Django

Counting languages (groupBy)
java: 3 developers
python: 2 developers
javascript: 2 developers
typescript: 1 developers
html: 1 developers
css: 1 developers
kotlin: 1 developers

Counting languages (manual)
java: 3 developers
python: 2 developers
javascript: 2 developers
typescript: 1 developers
html: 1 developers
css: 1 developers
kotlin: 1 developers

Average experience by language (groupBy)
java: 3.0 years
python: 3.0 years
javascript: 2.0 years
typescript: 4.0 years
html: 2.0 years
css: 2.0 years
kotlin: 8.0 years

Average experience by language (manual)
java: 5.33 years
python: 4.0 years
javascript: 3.0 years
typescript: 4.0 years
html: 2.0 years
css: 2.0 years
kotlin: 8.0 years

Developers using Ktor framework:
Emina Jusufovic — Backend developer — jezici: Java, Python — framework: Ktor
Armin Coralic — Frontend developer — jezici: Java, Kotlin — framework: Ktor

Developers using Vue.js framework:
Amina Hasic — Backend developer — jezici: HTML, CSS, JavaScript — framework: Vue.js
```

```
```
