package sklepy;

import java.util.Objects;

abstract public class SklepMeblowy extends Sklep {

    boolean czyMoznaKupowacNaRaty;

    //KONSTRUKTOR:

    public SklepMeblowy(boolean czyMoznaKupowacNaRaty, String adres, String adresWWW) {

        super(adres, adresWWW);
        this.czyMoznaKupowacNaRaty = czyMoznaKupowacNaRaty;
    }

    // GETTERY:

    public boolean getCzyMoznaKupowacNaRaty() {
        return czyMoznaKupowacNaRaty;
    }

    //SETTERY:

    public void setCzyMoznaKupowacNaRaty(boolean czyMoznaKupowacNaRaty) {
        this.czyMoznaKupowacNaRaty = czyMoznaKupowacNaRaty;
    }


    //METODY:

    @Override
    public boolean czyJestOtwarty(String dzienTygodnia, int godzina) {
        if (Objects.equals(dzienTygodnia, "Niedziela")) {
            return false;
        }
        return godzina >= 9 && godzina <= 21;
    }

    @Override
    public double sprzedajProdukt(Produkt produkt, int ilosc) {
        if (super.sprawdzDostepnoscProduktu(produkt) >= ilosc) {
            super.aktualizujIloscProduktow(produkt, -ilosc);
            System.out.print("Sprzedaz zakonczona pomyslnie.\n");
        } else  {
            System.out.print("Niewystarczajaca ilosc produktu na magazynie.\n");
        }

        return produkt.getCena() * ilosc;
    }

    public void dostarczZamowienie() {
        new FirmaDostawcza("Poczta Polska", 1, "Kolorowa 5").dostarczMeble(this);
    }
}
