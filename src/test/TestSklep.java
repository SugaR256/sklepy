package test;

import sklepy.DniTygodnia;
import sklepy.Produkt;
import sklepy.Sklep;

// Przykładowa klasa
public class TestSklep extends Sklep {

    private final double rabat = .05;
    private final double minCena = 100;
    private final int gratisSztuka = 5;

    public TestSklep(String adres, String adresWWW){
        super(adres, adresWWW);
    }

    @Override
    public boolean czyJestOtwarty(String dzienTygodnia, int godzina) {
        switch (dzienTygodnia) {
            case DniTygodnia.SOBOTA:
            case DniTygodnia.NIEDZIELA:
                return false;
                // Reszta dni tygodnia, sklep otwarty od 6 do 22
            default:
                return (godzina >= 6 && godzina <= 22);
        }
    }

    @Override
    public double sprzedajProdukt(Produkt produkt, int ilosc) {

        // Sprawdzamy czy produkt jest w dostępnej ilości
        int dotychczasowaIlosc = sprawdzDostepnoscProduktu(produkt);
        if (ilosc > dotychczasowaIlosc){
            // Sprzedajemy tylko tyle produktu, ile mamy
            ilosc = dotychczasowaIlosc;
        }

        // Ilość musi być ujemna w tym przypadku! Bo zmniejsza się ilość na 'magazynie'
        aktualizujIloscProduktow(produkt, (-1) * ilosc);

        // Co piąta sztuka jest gratis
        ilosc = ilosc - (ilosc / gratisSztuka);

        double suma = ilosc * produkt.getCena();
        // Dajemy rabat powyżej pewnej ceny
        if (suma >= minCena){
            suma -= rabat * suma;
        }
        return suma;
    }
}
