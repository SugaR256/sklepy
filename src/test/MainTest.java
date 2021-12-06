package test;

import sklepy.DniTygodnia;
import sklepy.Pracownik;
import sklepy.Produkt;
import sklepy.Sklep;

import java.util.Date;

public class MainTest {


    // To nie jest oczywiscie wzorcowe main ale I'm tired XD
    public static void main(String[] args) {

        Sklep testSklep = new TestSklep("Wrocław, 3 Maja", "https://sklep.pl");

        Pracownik p1 = new Pracownik("Adam", "Kowalski", new Date(), 1000);
        Pracownik p2 = new Pracownik("Ewa", "Kowalska", new Date(), 1500);
        Pracownik p3 = new Pracownik("Janusz", "Nowak", new Date(), 2000);
        Pracownik p4 = new Pracownik("Paweł", "Pawłowski", new Date(), 2500);

        testSklep.zrekrutuj(p1);
        testSklep.zrekrutuj(p2);
        testSklep.zrekrutuj(p3);
        testSklep.zrekrutuj(p4);

        testSklep.wyswietlPracownikow();

        Produkt chleb = new Produkt("Chleb", 2.00);
        Produkt mleko = new Produkt("Mleko", 2.50);

        testSklep.aktualizujIloscProduktow(chleb, 8);
        testSklep.aktualizujIloscProduktow(mleko, 5);
        // Łącznie 10 chlebów
        testSklep.aktualizujIloscProduktow(chleb, 2);

        testSklep.wyswietlOferteSklepu();

        System.out.println("Kupuje chleb");
        // Uwzględniając, że co piąty chleb jest za darmo płacimy jak za 4
        // 4 x 2 = 8
        System.out.println("Kup chleb " + testSklep.sprzedajProdukt(chleb, 5));
        // Sprzedaje tyle chlebów, ile zostało, czyli 5
        System.out.println("Kup chleb " + testSklep.sprzedajProdukt(chleb, 10));

        System.out.println("Kupuje mleko");

        System.out.println("Kup mleko " + testSklep.sprzedajProdukt(mleko, 5));

        testSklep.wyswietlOferteSklepu();

        System.out.println(testSklep.czyJestOtwarty(DniTygodnia.PONIEDZIALEK, 12));    // true
        System.out.println(testSklep.czyJestOtwarty(DniTygodnia.WTOREK, 1));           // false
        System.out.println(testSklep.czyJestOtwarty(DniTygodnia.NIEDZIELA, 12));       // false

    }

}
