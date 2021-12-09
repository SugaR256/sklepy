package sklepy;

import java.util.Objects;

public class LeroyMerlin extends SklepBudowniczy {

    private boolean czySaStanowiskaSamoobslugowe;
    private String dzialPromocyjny;

    private final double rabatDzial = .25;
    private final double rabat = .08;


    public LeroyMerlin(String adres, String adresWWW, boolean czySaKasySamoobslugowe, String dzialPromocyjny) {
        super(adres, adresWWW);
        this.czySaStanowiskaSamoobslugowe = czySaKasySamoobslugowe;
        this.dzialPromocyjny = dzialPromocyjny;
    }

    @Override
    public void wystawka() {
        System.out.println("W dniu dzisiejszym dział " + dzialPromocyjny + " podlega promocji - " + rabatDzial * 100 + "%");
    }

    @Override
    public boolean czyJestOtwarty(String dzienTygodnia, int godzina) {

        // Niedziela 10-20, reszta 7-21
        if (Objects.equals(dzienTygodnia, DniTygodnia.NIEDZIELA)) {
            return godzina >= 10 && godzina <= 20;
        } else {
            return godzina >= 7 && godzina <= 21;
        }
    }

    @Override
    public double sprzedajProdukt(Produkt produkt, int ilosc) {

        int dotychczasowaIlosc = sprawdzDostepnoscProduktu(produkt);

        // Nie mozemy sprzedac wiecej niz dostepna ilosc na sklepie
        if (ilosc > dotychczasowaIlosc) {
            ilosc = dotychczasowaIlosc;
        }

        // Zmniejszamy ilosc produktow na magazynie
        aktualizujIloscProduktow(produkt, -ilosc);

        double suma = ilosc * produkt.getCena();

        // sprawdza czy obowiazuje rabat dzialowy
        if (Objects.equals(dzialProduktu.get(produkt), dzialPromocyjny)) {
            suma -= suma * rabatDzial;
        }

        return suma;
    }


    // ------------------- GETTERS & SETTERS -----------------------------

    public boolean getCzySaKasySamoobslugowe() {
        return czySaStanowiskaSamoobslugowe;
    }

    public void setCzySaKasySamoobslugowe(boolean czySaKasySamoobslugowe) {
        this.czySaStanowiskaSamoobslugowe = czySaKasySamoobslugowe;
    }

    public void zmienDzialPromocyjny(String dzialPromocyjny) {
        this.dzialPromocyjny = dzialPromocyjny;
    }


}
