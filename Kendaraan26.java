public class Kendaraan26 {
    String noTNKB;
    String nama;
    String jenis;
    int cc;
    int tahun;
    int bulanHrsBayar;

    public Kendaraan26(String noTNKB, String nama, String jenis, int cc, int tahun, int bulanHrsBayar) {
        this.noTNKB = noTNKB;
        this.nama = nama;
        this.jenis = jenis;
        this.cc = cc;
        this.tahun = tahun;
        this.bulanHrsBayar = bulanHrsBayar;
    }

    public String getNoTNKB() {
        return noTNKB;
    }

    public String getNama() {
        return nama;
    }

    public String getJenis() {
        return jenis;
    }

    public int getCc() {
        return cc;
    }

    public int getTahun() {
        return tahun;
    }

    public int getBulanHrsBayar() {
        return bulanHrsBayar;
    }
}
