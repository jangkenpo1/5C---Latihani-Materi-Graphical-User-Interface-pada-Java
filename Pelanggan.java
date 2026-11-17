public class Pelanggan {
    private String nama;
    private int nomorKomputer;
    private int durasiMenit;
    private static final int TARIF_PER_JAM = 5000;

    public Pelanggan(String nama, int nomorKomputer, int durasiMenit) {
        this.nama = nama;
        this.nomorKomputer = nomorKomputer;
        this.durasiMenit = durasiMenit;
    }

    public String getNama() {
        return nama;
    }

    public int getNomorKomputer() {
        return nomorKomputer;
    }

    public int getDurasiMenit() {
        return durasiMenit;
    }

    public int hitungBiaya() {
        double jam = durasiMenit / 60.0;
        return (int) Math.ceil(jam * TARIF_PER_JAM);
    }

    public String getInfo() {
        return "PC-" + nomorKomputer + " | " + nama + " | " + durasiMenit + " menit | Rp" + hitungBiaya();
    }
}
