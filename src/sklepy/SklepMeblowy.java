package sklepy;

abstract public class SklepMeblowy extends Sklep {

    boolean czyMoznaBracNaRaty;

    //KONSTRUKTOR:

    public SklepMeblowy(boolean czyMoznaBracNaRaty, String adres, String adresWWW) {

        super(adres, adresWWW);
        this.czyMoznaBracNaRaty = czyMoznaBracNaRaty;
    }

    // GETTERY:

    public boolean getCzyMoznaBracNaRaty() {
        return czyMoznaBracNaRaty;
    }

    //SETTERY:

    public void setCzyMoznaBracNaRaty(boolean czyMoznaBracNaRaty) {
        this.czyMoznaBracNaRaty = czyMoznaBracNaRaty;
    }


    //METODY:

    @Override
    public boolean czyJestOtwarty(String dzienTygodnia, int godzina) {
        if (dzienTygodnia == "Niedziela")
            return false;
        if (godzina < 9 || godzina > 21)
            return false;
        else
            return true;
    }

    @Override
    public double sprzedajProdukt(Produkt produkt, int ilosc) {
        if (super.sprawdzDostepnoscProduktu(produkt) >= ilosc) {
            super.aktualizujIloscProduktow(produkt, -ilosc);
            System.out.print("Sprzedaz zakonczona pomyslnie.\n");
        } else
            System.out.print("Niewystarczajaca ilosc produktu na magazynie.\n");

        double suma = produkt.getCena() * ilosc;
        return suma;
    }

    public void dostarczZamowienie() {
        new FirmaDostawcza("Poczta Polska", 1, "Kolorowa 5").dostarczMeble(this);
    }
}
