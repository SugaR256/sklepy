package sklepy;

import java.util.Objects;

public class Zabka extends Sklep {

    boolean czyJestMozliwoscSprzedazyProduktowNaCieplo;
    boolean czyJestKasaSamoobslugowa;

    // KONSTRUKTORY:


    public Zabka(boolean czyJestMozliwoscSprzedazyProduktowNaCieplo, String adres, String adresWWW, boolean czyJestKasaSamoobslugowa) {
        super(adres, adresWWW);
        this.czyJestMozliwoscSprzedazyProduktowNaCieplo = czyJestMozliwoscSprzedazyProduktowNaCieplo;
        this.czyJestKasaSamoobslugowa = czyJestKasaSamoobslugowa;
    }

    // GETTERY:

    public boolean getCzyJestMozliwoscSprzedazyProduktowNaCieplo() {
        return czyJestMozliwoscSprzedazyProduktowNaCieplo;
    }

    public boolean getCzyJestKasaSamoobslugowa() {
        return czyJestKasaSamoobslugowa;
    }

    //SETTERY:

    public void setCzyJestMozliwoscSprzedazyProduktowNaCieplo(boolean czyJestMozliwoscSprzedazyProduktowNaCieplo) {
        this.czyJestMozliwoscSprzedazyProduktowNaCieplo = czyJestMozliwoscSprzedazyProduktowNaCieplo;
    }

    public void setCzyJestKasaSamoobslugowa(boolean czyJestKasaSamoobslugowa) {
        this.czyJestKasaSamoobslugowa = czyJestKasaSamoobslugowa;
    }

    //METODY:
    @Override
    public boolean czyJestOtwarty(String dzienTygodnia, int godzina) {
        return godzina >= 6 && godzina <= 23;
    }

    @Override
    public double sprzedajProdukt(Produkt produkt, int ilosc) {
        if (super.sprawdzDostepnoscProduktu(produkt) >= ilosc) {
            if (Objects.equals(produkt.getNazwa(), "hotdog") || Objects.equals(produkt.getNazwa(), "panini") || Objects.equals(produkt.getNazwa(), "kawa")) {
                if (!czyJestMozliwoscSprzedazyProduktowNaCieplo) {
                    System.out.print("Mozliwosc sprzedazy produktow na cieplo nie jest aktualnie mozliwa.\n");
                    return 0;
                }
            }
            super.aktualizujIloscProduktow(produkt, -ilosc);
        } else
            System.out.print("Niewystarczajaca ilosc produktu na magazynie.\n");

        return produkt.getCena() * ilosc;
    }

    public double sprzedajProduktZAplikacja(Produkt produkt, int ilosc) {
        sprzedajProdukt(produkt, ilosc);
        System.out.print("Punkty w aplikacji zostaly naliczone.\n");
        return produkt.getCena() * ilosc;
    }
}
