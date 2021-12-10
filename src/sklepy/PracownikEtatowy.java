package sklepy;

import java.time.LocalDate;
import java.util.Objects;

public class PracownikEtatowy implements Pracownik {

    private String imie;
    private String nazwisko;
    private LocalDate dataZatrudnienia;
    private double pensja;

    public PracownikEtatowy(String imie, String nazwisko, LocalDate dataZatrudnienia, double pensja) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataZatrudnienia = dataZatrudnienia;
        this.pensja = pensja;
    }

    // -------- GETTERS & SETTERS --------------

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public LocalDate getDataZatrudnienia() {
        return dataZatrudnienia;
    }

    public void setDataZatrudnienia(LocalDate dataZatrudnienia) {
        this.dataZatrudnienia = dataZatrudnienia;
    }

    public double getPensja() {
        return pensja;
    }

    public void setPensja(double pensja) {
        this.pensja = pensja;
    }

    @Override
    public String toString() {
        return "Pracownik {" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", dataZatrudnienia=" + dataZatrudnienia +
                ", pensja=" + pensja +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PracownikEtatowy that = (PracownikEtatowy) o;
        return Double.compare(that.pensja, pensja) == 0 && Objects.equals(imie, that.imie) && Objects.equals(nazwisko, that.nazwisko) && Objects.equals(dataZatrudnienia, that.dataZatrudnienia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imie, nazwisko, dataZatrudnienia, pensja);
    }
}
