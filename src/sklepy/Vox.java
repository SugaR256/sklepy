package sklepy;

import java.util.Objects;

public class Vox extends SklepMeblowy {

    private boolean czyDzienRabatowy;

    public Vox(String adres, String adresWWW, boolean czyMoznaKupowacNaRaty, boolean czyDzienRabatowy) {
        super(czyMoznaKupowacNaRaty, adres, adresWWW);
        this.czyDzienRabatowy = czyDzienRabatowy;
    }

    public double sprzedajProdukt(Produkt produkt, int ilosc) {
        if (super.sprawdzDostepnoscProduktu(produkt) >= ilosc) {
            super.aktualizujIloscProduktow(produkt, -ilosc);
            System.out.println("Sprzedaz zakonczona pomyslnie.");
        } else {
            System.out.println("Niewystarczajaca ilosc produktu na magazynie.");
        }

        double suma = produkt.getCena() * ilosc;

        if (czyDzienRabatowy) {
            suma = 0.75 * suma;
        }
        return suma;
    }

    public boolean czyJestOtwarty(String dzienTygodnia, int godzina) {
        if (Objects.equals(dzienTygodnia, DniTygodnia.NIEDZIELA)) {
            return godzina >= 10 && godzina <= 16;
        } else {
            return godzina >= 10 && godzina <= 20;
        }
    }


    public void setCzyDzienRabatowy(String dzienTygodnia) {
        this.czyDzienRabatowy = Objects.equals(dzienTygodnia, DniTygodnia.CZWARTEK) || Objects.equals(dzienTygodnia, DniTygodnia.WTOREK);
    }

    public boolean getCzyDzienRabatowy() {
        return this.czyDzienRabatowy;
    }
}
