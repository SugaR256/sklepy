package sklepy;

import java.util.HashMap;
import java.util.Map;

public abstract class Sklep {

    private String adres;
    private String adresWWW;
    private Pracownik[] pracownicy;

    // Aktualna ilość pracowników
    private int aktualnaIloscPracownikow;
    private int CAPACITY = 3;          // DO CELÓW TESTOWANIA wartość to 3: najlepiej ustawić na 10

    // To jest struktura która będzie każdemu produktowi
    // przyporządkowywać jego ilość w sklepie
    // Służy za taki jakby magazyn, ale używając tylko
    // metod poniżej nie trzeba nawet rozumieć tej struktury
    private final Map<Produkt, Integer> magazyn;

    public Sklep(String adres, String adresWWW) {
        this.adres = adres;
        this.adresWWW = adresWWW;
        pracownicy = new Pracownik[CAPACITY];
        magazyn = new HashMap<>();

    }

    // -------------- METODY ABSTRAKCYJNE - DO NADPISANIA --------------

    // UWAGA: za dni tygodnia wstawiajcie stałe statyczne z klasy 'DniTygodnia',
    // żeby wszędzie było tak samo.
    // Zaimplementujcie sobie kiedy sklep jest otwarty. Np. zabka to codziennie 6-23,
    // ale sklep budowlany to raczej nie. Godzina jest liczbą całkowitą z przedziału <0; 23>
    public abstract boolean czyJestOtwarty(String dzienTygodnia, int godzina);

    // Tutaj idzie cała logika sprzedawania produktów. Np. możecie uwzględnić jakiś rabat,
    // jeśli kwota zakupionych produktów przekracza jakąś kwotę, lub coś w stylu '2 w cenie 1'
    // (dla uproszczenia można sprzedać produkt jednego rodzaju w tej metodzie)
    // Możecie też zdecydować, czy jeśli jakiegoś produktu nie ma wystarczająco dużo, to sprzedajecie tyle ile
    // macie, czy też nie (Np. ktoś chce kupić cztery krzesła, a macie na magazynie ich mniej - to raczej
    // nie ma sensu, żeby je sprzedawać)
    // Pamiętajcie o używaniu metod 'aktualizujIloscProduktow' i 'sprawdzDostepnoscProduktu'
    public abstract double sprzedajProdukt(Produkt produkt, int ilosc);

    //------------------------------------------------------------------------

    // To uaktualnia stan produktu na 'magazynie'
    // Jeśli ilość jest dodatnia, to oznacza dostawę produktu
    // Jeśli ujemna oznacza sprzedaż, czyli zmniejszamy ilość produktów na 'magazynie'
    public void aktualizujIloscProduktow(Produkt produkt, int ilosc){

        int aktualnaIlosc = 0;

        // Jeśli ten produkt jest już na magazynie, to musimy to też uwzględnić
        if (magazyn.containsKey(produkt)){
            aktualnaIlosc =  magazyn.get(produkt);
        }
        magazyn.put(produkt, aktualnaIlosc + ilosc);
    }

    // Ta metoda służy sprawdzeniu, czy sklep dysponuje jakąś ilością produktów.
    // Powinna być użyta w funkcji sprzedajProdukt() do upewnienia się,
    // że można go sprzedać w tej ilości
    public int sprawdzDostepnoscProduktu(Produkt produkt){
        return magazyn.getOrDefault(produkt, 0);
    }

    public void zrekrutuj(Pracownik pracownik){
        zwiekszPojemnoscTablicy();
        pracownicy[aktualnaIloscPracownikow++] = pracownik;
    }

    private void zwiekszPojemnoscTablicy(){
        if (aktualnaIloscPracownikow >= CAPACITY){
            CAPACITY *= 2;
            Pracownik[] wiekszaTab = new Pracownik[CAPACITY];
            System.arraycopy(pracownicy, 0, wiekszaTab, 0, aktualnaIloscPracownikow);
            pracownicy = wiekszaTab;      // Zmieniamy referencję na nową, większą tablicę
        }
    }


    // ------------------- GETTERS & SETTERS -----------------------------

    // Wyświetla informacje na temat pracowników
    public void wyswietlPracownikow(){
        for (int i = 0; i < aktualnaIloscPracownikow; i++){
            System.out.println(pracownicy[i]);
        }
    }

    // Wyświetla produkty : ich nazwę, cenę oraz ilość na magazynie
    public void wyswietlOferteSklepu(){
        for (Map.Entry<Produkt, Integer> el : magazyn.entrySet()){
            System.out.println(el.getKey() + ", ilosc=" + el.getValue());
        }
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getAdresWWW() {
        return adresWWW;
    }

    public void setAdresWWW(String adresWWW) {
        this.adresWWW = adresWWW;
    }

    public int getAktualnaIloscPracownikow() {
        return aktualnaIloscPracownikow;
    }
}
