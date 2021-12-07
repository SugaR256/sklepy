package sklepy;

class Zabka extends Sklep {

    boolean czyJestMozliwoscSprzedazyProduktowNaCieplo;
    boolean czyKlientMaAplikacje;
    boolean czyJestKasaSamoobslugowa;

    // KONSTRUKTORY:


    public Zabka(boolean czyJestMozliwoscSprzedazyProduktowNaCieplo, String adres, String adresWWW, boolean czyKlientMaAplikacje, boolean czyJestKasaSamoobslugowa) {
        super(adres, adresWWW);
        this.czyJestMozliwoscSprzedazyProduktowNaCieplo = czyJestMozliwoscSprzedazyProduktowNaCieplo;
        this.czyKlientMaAplikacje = czyKlientMaAplikacje;
        this.czyJestKasaSamoobslugowa = czyJestKasaSamoobslugowa;
    }

    // GETTERY:

    public boolean getCzyJestMozliwoscSprzedazyProduktowNaCieplo() {
        return czyJestMozliwoscSprzedazyProduktowNaCieplo;
    }

    public boolean getCzyKlientMaAplikacje() {
        return czyKlientMaAplikacje;
    }

    public boolean getCzyJestKasaSamoobslugowa() {
        return czyJestKasaSamoobslugowa;
    }

    //SETTERY:

    public void setCzyJestMozliwoscSprzedazyProduktowNaCieplo(boolean czyJestMozliwoscSprzedazyProduktowNaCieplo) {
        this.czyJestMozliwoscSprzedazyProduktowNaCieplo = czyJestMozliwoscSprzedazyProduktowNaCieplo;
    }

    public void setCzyKlientMaAplikacje(boolean czyKlientMaAplikacje) {
        this.czyKlientMaAplikacje = czyKlientMaAplikacje;
    }

    public void setCzyJestKasaSamoobslugowa(boolean czyJestKasaSamoobslugowa) {
        this.czyJestKasaSamoobslugowa = czyJestKasaSamoobslugowa;
    }

    //METODY:


    @Override
    public boolean czyJestOtwarty(String dzienTygodnia, int godzina) {
        if (godzina < 6 || godzina > 23) {
            return false;
        } else return true;
    }

    @Override
    public double sprzedajProdukt(Produkt produkt, int ilosc) {

        if (super.sprawdzDostepnoscProduktu(produkt) >= ilosc) {
            if (produkt.getNazwa() == "hotdog" || produkt.getNazwa() == "panini" || produkt.getNazwa() == "kawa") {
                if (!czyJestMozliwoscSprzedazyProduktowNaCieplo) {
                    System.out.print("Mozliwosc sprzedazy produktow na cieplo nie jest aktualnie mozliwa.\n");
                    return 0;
                }
            }
            super.aktualizujIloscProduktow(produkt, -ilosc);
        } else
            System.out.print("Niewystarczajaca ilosc produktu na magazynie.\n");

        double suma = produkt.getCena() * ilosc;
        return suma;
    }

    public double sprzedajProduktZAplikacja(Produkt produkt, int ilosc) {
        sprzedajProdukt(produkt, ilosc);
        System.out.print("Punkty w aplikacji zostaly naliczone.\n");
        double suma = produkt.getCena() * ilosc;
        return suma;
    }
}
