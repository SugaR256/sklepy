package sklepy;

import java.util.Objects;

public class Castorama extends SklepBudowniczy {

    private boolean czySaStanowiskaSamoobslugowe;
    private String dzialPromocyjny;

    private final double rabatDzial = .20;
    private final double rabat = .05;
    private final double minCena = 200;


    public Castorama(String adres, String adresWWW, boolean czySaKasySamoobslugowe, String dzialPromocyjny) {
        super(adres, adresWWW);
        this.czySaStanowiskaSamoobslugowe = czySaKasySamoobslugowe;
        this.dzialPromocyjny = dzialPromocyjny;
    }

    @Override
    public boolean czyJestOtwarty(String dzienTygodnia, int godzina) {
        // Niedziela 10-18, reszta 6-21
        if (Objects.equals(dzienTygodnia, DniTygodnia.NIEDZIELA)) {
            return godzina >= 10 && godzina <= 18;
        } else {
            return godzina >= 6 && godzina <= 21;
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


        // obowiazuje rabat na konkretny dzial
        if (Objects.equals(dzialProduktu.get(produkt), dzialPromocyjny))
            suma -= suma * rabatDzial;


        // Rabaty sie sumuja
        // Obowiazuje rabat powyzej minimalnej ceny
        if (suma >= minCena) {
            suma -= suma * rabat;
        }

        return suma;
    }

    @Override
    public void wystawka() {
        System.out.println("W dniu dzisiejszym dział " + dzialPromocyjny + " podlega promocji - " + rabatDzial * 100 + "%");
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