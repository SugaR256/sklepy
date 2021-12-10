package test;

import sklepy.*;

import java.time.LocalDate;
import java.util.Random;

public class MainTest {

    public static void main(String[] args) {
        final Biedronka biedronka = new Biedronka("ul. Wittiga 12, Wrocław", "biedronka.pl", true);
        final Castorama castorama = new Castorama("ul. Wschodnia 1, Wrocław", "castorama.pl", false, "umywalki");
        final Ikea ikea = new Ikea("ul. Poznańska 9, Wrocław", "ikea.pl", true, true);
        final LeroyMerlin leroyMerlin = new LeroyMerlin("ul. Lerłowska 422, Poznań", "leroymerlin.pl", true, "wiertarki");
        final Lidl lidl = new Lidl("ul. Akademicka 6, Wrocław", "lidl.pl", true);
        final Vox vox = new Vox("ul. Meblowa 44, Gdańsk", "vox.pl", true, true);
        final Zabka zabka = new Zabka(false, "ul. Płazowa 4, Wrocław", "zabka.pl", true);

        final Pracownik[] pracownicy = wygenerujPracownikow();

        biedronka.zrekrutuj(pracownicy[0]);
        biedronka.zrekrutuj(pracownicy[10]);
        castorama.zrekrutuj(pracownicy[1]);
        castorama.zrekrutuj(pracownicy[11]);
        ikea.zrekrutuj(pracownicy[2]);
        ikea.zrekrutuj(pracownicy[12]);
        leroyMerlin.zrekrutuj(pracownicy[3]);
        leroyMerlin.zrekrutuj(pracownicy[13]);
        lidl.zrekrutuj(pracownicy[4]);
        lidl.zrekrutuj(pracownicy[14]);
        vox.zrekrutuj(pracownicy[5]);
        vox.zrekrutuj(pracownicy[15]);
        zabka.zrekrutuj(pracownicy[6]);
        zabka.zrekrutuj(pracownicy[16]);

        ikea.wyswietlPracownikow();

        Produkt sofa1 = new Produkt("sofa1", 1800);
        Produkt prysznic1 = new Produkt("prysznic1", 900);
        Produkt umywalka1 = new Produkt("umywalka1", 490);

        castorama.aktualizujIloscProduktow(sofa1, 12);
        castorama.aktualizujIloscProduktow(prysznic1, 3);
        castorama.aktualizujIloscProduktow(umywalka1, 19);

        castorama.wyswietlOferteSklepu();
        castorama.wystawka();

        castorama.dodajDzial("umywalki");
        castorama.dodajProduktDoDzialu(umywalka1, "umywalki");
        castorama.dodajDzial("prysznice");
        castorama.dodajProduktDoDzialu(prysznic1, "prysznice");
        castorama.dodajDzial("sofy");
        castorama.dodajProduktDoDzialu(sofa1, "sofy");

        System.out.println("Koszt 2 umywalek: " + castorama.sprzedajProdukt(umywalka1, 2));

        castorama.wyswietlOferteSklepu();

        System.out.println("Czy Castorama jest otwarta w niedzielę o 16? " + castorama.czyJestOtwarty(DniTygodnia.NIEDZIELA, 2));

        final FirmaDostawcza inpost = new FirmaDostawcza("Inpost", 3, "Krakowska 12, Warszawa");
        inpost.dostarczMaterialy(castorama);

        final Produkt hotdog = new Produkt("hotdog", 2.20);
        final Produkt paluszki = new Produkt("paluszki", 3.80);
        zabka.aktualizujIloscProduktow(paluszki, 30);
        zabka.aktualizujIloscProduktow(hotdog, 10);
        zabka.sprawdzDostepnoscProduktu(paluszki);
        zabka.sprzedajProdukt(hotdog, 1);

        final Produkt chleb = new Produkt("chleb", 5);
        biedronka.aktualizujIloscProduktow(chleb, 100);
        System.out.println( biedronka.sprzedajZKartaBiedronki(chleb, 3));
    }

    private static Pracownik[] wygenerujPracownikow() {
        Pracownik[] pracownicy = new Pracownik[20];
        pracownicy[0] = new PracownikEtatowy("Grzegorz", "Świąder", losowaData(), losowaPensja());
        pracownicy[1] = new PracownikEtatowy("Małgorzata", "Nowak", losowaData(), losowaPensja());
        pracownicy[2] = new PracownikEtatowy("Anna", "Kowalska", losowaData(), losowaPensja());
        pracownicy[3] = new PracownikEtatowy("Paweł", "Owsiak", losowaData(), losowaPensja());
        pracownicy[4] = new PracownikEtatowy("Konrad", "Łączny", losowaData(), losowaPensja());
        pracownicy[5] = new PracownikEtatowy("Zuzanna", "Szczupak", losowaData(), losowaPensja());
        pracownicy[6] = new PracownikEtatowy("Mikołaj", "Święty", losowaData(), losowaPensja());
        pracownicy[7] = new PracownikEtatowy("Patryk", "Wysocki", losowaData(), losowaPensja());
        pracownicy[8] = new PracownikEtatowy("Aniela", "Różnowartościowa", losowaData(), losowaPensja());
        pracownicy[9] = new PracownikEtatowy("Bartosz", "Monotoniczny", losowaData(), losowaPensja());
        pracownicy[10] = new PracownikGodzinowy("Wiktor", "Mętny", losowaData(), losoweWynagrodzenieGodzinowe());
        pracownicy[11] = new PracownikGodzinowy("Rupert", "Pomysłowy", losowaData(), losoweWynagrodzenieGodzinowe());
        pracownicy[12] = new PracownikGodzinowy("Bartosz", "Król", losowaData(), losoweWynagrodzenieGodzinowe());
        pracownicy[13] = new PracownikGodzinowy("Lech", "Poznański", losowaData(), losoweWynagrodzenieGodzinowe());
        pracownicy[14] = new PracownikGodzinowy("Fryderyk", "Kwaśnioch", losowaData(), losoweWynagrodzenieGodzinowe());
        pracownicy[15] = new PracownikGodzinowy("Artur", "Śmieszny", losowaData(), losoweWynagrodzenieGodzinowe());
        pracownicy[16] = new PracownikGodzinowy("Robert", "Kubitza", losowaData(), losoweWynagrodzenieGodzinowe());
        pracownicy[17] = new PracownikGodzinowy("Amelia", "Winna", losowaData(), losoweWynagrodzenieGodzinowe());
        pracownicy[18] = new PracownikGodzinowy("Konstancja", "Sokolska", losowaData(), losoweWynagrodzenieGodzinowe());
        pracownicy[19] = new PracownikGodzinowy("Dominika", "Kulczyk", losowaData(), losoweWynagrodzenieGodzinowe());
        return pracownicy;
    }

    private static final Random rng = new Random();

    private static LocalDate losowaData() {
        return LocalDate.of(rng.nextInt(13) + 2008, rng.nextInt(12) + 1, rng.nextInt(28) + 1);
    }

    private static int losowaPensja() {
        return rng.nextInt(3801) + 2400;
    }

    private static double losoweWynagrodzenieGodzinowe() {
        return 18.7 + (45 - 18.7) * rng.nextDouble();
    }
}
