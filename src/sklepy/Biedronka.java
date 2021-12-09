package sklepy;

import java.util.Objects;

public class Biedronka extends Supermarket {
    final private double rabat = 0.5;
    private boolean czyMaKasySamoobslugowe;

    public Biedronka(String adres, String adresWWW, boolean czyMaKasySamoobslugowe) {
        super(adres, adresWWW);
        this.czyMaKasySamoobslugowe = czyMaKasySamoobslugowe;
    }

    @Override
    public void otworzGazetke() {
        // Zmiana ceny produktu w gazetce o ustalony rabat
        for (Produkt produkt : super.getGazetka()) {
            produkt.setCena(produkt.getCena() * rabat);
        }
    }

    @Override
    public boolean czyJestOtwarty(String dzienTygodnia, int godzina) {
        // W niedziele otwarta: 8-20, w rezte dni: 6-23
        if (Objects.equals(dzienTygodnia, DniTygodnia.NIEDZIELA)) {
            return godzina >= 8 && godzina <= 20;
        } else {
            return godzina >= 6 && godzina <= 23;
        }
    }

    @Override
    public double sprzedajProdukt(Produkt produkt, int ilosc) {
        int aktualnaIlosc = super.sprawdzDostepnoscProduktu(produkt);
        double cena;

        if (ilosc > aktualnaIlosc) {
            ilosc = aktualnaIlosc;
        }

        super.aktualizujIloscProduktow(produkt, -ilosc);

        cena = produkt.getCena() * ilosc;

        return cena;
    }

    public double sprzedajZKartaBiedronki(Produkt produkt, int ilosc) {

        // Jesli klient posiada karte to doliczany jest dodatkowy rabat
        double cena = sprzedajProdukt(produkt, ilosc) * 0.8;

        // Minimalna cena za jaka sklep moze sprzedac produkt
        // nie moze byc mniejsza niz 40% jego wartosci
        double minCena = produkt.getCena() * 0.4 * ilosc;

        // jesli cena produktu po rabatach jest mniejsza niz
        // minimalna cena to ustawiamy cene na minimalna cene
        if (cena < minCena * ilosc) {
            cena = minCena;
        }
        return cena;
    }

    // getter & setters
    public double getRabat() {
        return rabat;
    }

    public boolean isCzyMaKasySamoobslugowe() {
        return czyMaKasySamoobslugowe;
    }

    public void setCzyMaKasySamoobslugowe(boolean czyMaKasySamoobslugowe) {
        this.czyMaKasySamoobslugowe = czyMaKasySamoobslugowe;
    }

}