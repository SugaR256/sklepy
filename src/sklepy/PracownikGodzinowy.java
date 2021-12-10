package sklepy;

import java.time.LocalDate;
import java.util.Objects;

public class PracownikGodzinowy implements Pracownik{
    public PracownikGodzinowy(String imie, String nazwisko, LocalDate dataZatrudnienia, double wynagrodzenieZaGodzine) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataZatrudnienia = dataZatrudnienia;
        this.wynagrodzenieZaGodzine = wynagrodzenieZaGodzine;
    }

    private String imie;
    private String nazwisko;
    private LocalDate dataZatrudnienia;
    private double wynagrodzenieZaGodzine;

    @Override
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @Override
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Override
    public LocalDate getDataZatrudnienia() {
        return dataZatrudnienia;
    }

    public void setDataZatrudnienia(LocalDate dataZatrudnienia) {
        this.dataZatrudnienia = dataZatrudnienia;
    }

    public double getWynagrodzenieZaGodzine() {
        return wynagrodzenieZaGodzine;
    }

    public void setWynagrodzenieZaGodzine(double wynagrodzenieZaGodzine) {
        this.wynagrodzenieZaGodzine = wynagrodzenieZaGodzine;
    }

    @Override
    public String toString() {
        return "Pracownik {" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", dataZatrudnienia=" + dataZatrudnienia +
                ", wynagrodzenieZaGodzine=" + wynagrodzenieZaGodzine +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PracownikGodzinowy that = (PracownikGodzinowy) o;
        return Double.compare(that.wynagrodzenieZaGodzine, wynagrodzenieZaGodzine) == 0 && Objects.equals(imie, that.imie) && Objects.equals(nazwisko, that.nazwisko) && Objects.equals(dataZatrudnienia, that.dataZatrudnienia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imie, nazwisko, dataZatrudnienia, wynagrodzenieZaGodzine);
    }
}
