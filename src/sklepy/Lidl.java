package sklepy;

import java.util.Objects;

public class Lidl extends Supermarket {
    private boolean czyMaKasySamoobslugowe;

    public Lidl(String adres, String adresWWW, boolean czyMaKasySamoobslugowe) {
        super(adres, adresWWW);
        this.czyMaKasySamoobslugowe = czyMaKasySamoobslugowe;
    }

    // Nie wiem co zrobic z ta metoda bo chcialem, zeby Biedronka i
    // Lidl sie od siebie roznily, dlatego biedronka ma rabat na produkty w gazetce, a Lidl
    // 2 w cenie 1, ale teraz ta metoda jest chyba bezuzyteczna
    @Override
    public void otworzGazetke() {
        for (Produkt produkt : super.getGazetka()) {
            produkt.setCena(produkt.getCena());
        }
    }

    @Override
    public boolean czyJestOtwarty(String dzienTygodnia, int godzina) {
        //W niedziele zamkniety, w reszte dni: 6-22
        if (Objects.equals(dzienTygodnia, DniTygodnia.NIEDZIELA))
            return false;
        else
            return (godzina >= 6 && godzina <= 22);

    }

    public double sprzedajProdukt(Produkt produkt, int ilosc) {
        int aktualnaIlosc = super.sprawdzDostepnoscProduktu(produkt);
        // Minimalna cena za jaka sklep moze sprzedac produkt
        // nie moze byc mniejsza niz 40% jego wartosci
        double minCena = produkt.getCena() * 0.4 * ilosc;
        double cena;

        if (ilosc > aktualnaIlosc) {
            ilosc = aktualnaIlosc;
        }

        super.aktualizujIloscProduktow(produkt, -ilosc);

        // Jesli produkt nalezy do gazetki oraz jego ilosc jest wieksza lub rowna 2
        // wtedy jesli jego ilosc jest parzysta cena produktu wynosi 50%, a jezeli
        // nieparzysta cena produktu wynosi 50% oraz dodajemy jeden produkt po normalnej cenie
        if (super.getGazetka().contains(produkt)) {
            if (ilosc >= 2) {
                if (ilosc % 2 == 1) {
                    ilosc -= 1;
                    cena = (ilosc * produkt.getCena() * 0.5) + produkt.getCena();
                } else {
                    cena = ilosc * produkt.getCena() * 0.5;
                }
            } else cena = produkt.getCena() * ilosc;
        } else cena = produkt.getCena() * ilosc;

        // jesli cena produktu po rabatach jest mniejsza niz
        // minimalna cena to ustawiamy cene na minimalna cene
        if (cena < minCena) {
            cena = minCena;
        }
        return cena;
    }

    public boolean isCzyMaKasySamoobslugowe() {
        return czyMaKasySamoobslugowe;
    }

    public void setCzyMaKasySamoobslugowe(boolean czyMaKasySamoobslugowe) {
        this.czyMaKasySamoobslugowe = czyMaKasySamoobslugowe;
    }

}