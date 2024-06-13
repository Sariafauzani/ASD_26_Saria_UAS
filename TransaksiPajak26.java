import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class TransaksiPajak26 {
    private int kode;
    private long nominalBayar;
    private long denda;
    private int bulanBayar;
    private Kendaraan26 kendaraan;

    public TransaksiPajak26(int kode, long nominalBayar, long denda, int bulanBayar, Kendaraan26 kendaraan) {
        this.kode = kode;
        this.nominalBayar = nominalBayar;
        this.denda = denda;
        this.bulanBayar = bulanBayar;
        this.kendaraan = kendaraan;
    }

    public int getKode() {
        return kode;
    }

    public long getNominalBayar() {
        return nominalBayar;
    }

    public long getDenda() {
        return denda;
    }

    public int getBulanBayar() {
        return bulanBayar;
    }

    public Kendaraan26 getKendaraan() {
        return kendaraan;
    }

    private static final int MAX_KENDARAAN = 100; // Batas maksimal kendaraan
    private static final int MAX_TRANSAKSI = 100; // Batas maksimal transaksi

    private static Kendaraan26[] daftarKendaraan = new Kendaraan26[MAX_KENDARAAN];
    private static TransaksiPajak26[] daftarTransaksi = new TransaksiPajak26[MAX_TRANSAKSI];
    private static int countKendaraan = 0;
    private static int countTransaksi = 0;
    private static Scanner sc26 = new Scanner(System.in);

    public static void main(String[] args) {
    
        tambahKendaraan(new Kendaraan26("S 4567 YV", "Basko", "Mobil", 2000, 2012, 4));
        tambahKendaraan(new Kendaraan26("N 4511 VS", "Arta", "Mobil", 2500, 2015, 3));
        tambahKendaraan(new Kendaraan26("AB 4321 A", "Wisnu", "Motor", 125, 2010, 2));
        tambahKendaraan(new Kendaraan26("B 1234 AG", "Sisa", "Motor", 150, 2020, 1));

        int menu;
        do {
            System.out.println("\n=============================================");
            System.out.println("                    M E N U                  ");
            System.out.println("=============================================");
            System.out.println("1. Daftar Kendaraan");
            System.out.println("2. Bayar Pajak");
            System.out.println("3. Tampilkan seluruh transaksi");
            System.out.println("4. Urutkan Transaksi berdasarkan nama Pemilik");
            System.out.println("5. Keluar");
            System.out.print("Pilih (1-5): ");
            menu = sc26.nextInt();
            sc26.nextLine();

            switch (menu) {
                case 1:
                    System.out.println("============================================");
                    System.out.println("              DAFTAR KENDARAAN              ");
                    System.out.println("============================================");
                    tampilkanDaftarKendaraan();
                    break;
                case 2:
                    System.out.println("============================================");
                    System.out.println("          MASUKKAN DATA PEMBAYARAN          ");
                    System.out.println("============================================");
                    bayarPajak();
                    break;
                case 3:
                    System.out.println("============================================");
                    System.out.println("      DAFTAR TRANSAKSI PEMBAYARAN PAJAK     ");
                    System.out.println("============================================");
                    tampilkanSemuaTransaksi();
                    break;
                case 4:
                    System.out.println("============================================");
                    System.out.println("      DAFTAR TRANSAKSI PEMBAYARAN PAJAK     ");
                    System.out.println("============================================");
                    urutkanTransaksiNamaPemilik();
                    break;
                case 5:
                    System.out.println("============================================");
                    System.out.println("Terima kasih telah menggunakan layanan kami.");
                    System.out.println("============================================");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }

        } while (menu != 5);

        sc26.close();
    }

    private static void tambahKendaraan(Kendaraan26 kendaraan) {
        daftarKendaraan[countKendaraan++] = kendaraan;
    }

    private static void tampilkanDaftarKendaraan() {
        System.out.println("| Nomor TNKB | Nama Pemilik | Jenis | CC Kendaraan | Tahun | Bulan Harus Bayar |");
        for (int i = 0; i < countKendaraan; i++) {
            Kendaraan26 kendaraan = daftarKendaraan[i];
            System.out.printf("| %-11s| %-13s| %-6s| %-13d| %-6d| %-18d|%n",
                    kendaraan.getNoTNKB(), kendaraan.getNama(), kendaraan.getJenis(),
                    kendaraan.getCc(), kendaraan.getTahun(), kendaraan.getBulanHrsBayar());
        }
    }

    private static void bayarPajak() {
        System.out.print("Masukkan Nomer TNKB: ");
        String noTNKB = sc26.nextLine();
        System.out.print("Bulan Bayar: ");
        int bulanBayar = sc26.nextInt();
        sc26.nextLine();

        Kendaraan26 kendaraanTerpilih = cariKendaraan(noTNKB);

        if (kendaraanTerpilih != null) {
            long nominalBayar = hitungNominalBayar(kendaraanTerpilih, bulanBayar);
            long denda = hitungDenda(kendaraanTerpilih, bulanBayar);

            TransaksiPajak26 transaksi = new TransaksiPajak26(countTransaksi + 1, nominalBayar, denda, bulanBayar, kendaraanTerpilih);
            daftarTransaksi[countTransaksi++] = transaksi;
    
            System.out.println("| Kode | Nomor TNKB | Nama Pemilik | Bulan Bayar | Nominal Bayar | Denda |");
            System.out.printf("| %-5d| %-11s| %-13s| %-12d| %-14d| %-6d|%n",
                    transaksi.getKode(), transaksi.getKendaraan().getNoTNKB(), transaksi.getKendaraan().getNama(),
                    transaksi.getBulanBayar(), transaksi.getNominalBayar(), transaksi.getDenda());
        } else {
            System.out.println("Kendaraan dengan nomor TNKB tersebut tidak ditemukan.");
        }
    }

    private static Kendaraan26 cariKendaraan(String noTNKB) {
        for (int i = 0; i < countKendaraan; i++) {
            if (daftarKendaraan[i].getNoTNKB().equalsIgnoreCase(noTNKB)) {
                return daftarKendaraan[i];
            }
        }
        return null;
    }

    private static long hitungNominalBayar(Kendaraan26 kendaraan, int bulanBayar) {
        long tarif = 0;
        if (kendaraan.getJenis().equalsIgnoreCase("Motor")) {
            if (kendaraan.getCc() < 100) {
                tarif = 100000;
            } else if (kendaraan.getCc() >= 100 && kendaraan.getCc() <= 250) {
                tarif = 250000;
            } else {
                tarif = 500000;
            }
        } else if (kendaraan.getJenis().equalsIgnoreCase("Mobil")) {
            if (kendaraan.getCc() < 1000) {
                tarif = 750000;
            } else if (kendaraan.getCc() >= 1000 && kendaraan.getCc() <= 2500) {
                tarif = 1000000;
            } else {
                tarif = 1500000;
            }
        }

        long totalNominal = tarif * bulanBayar;
        return totalNominal;
    }

    private static long hitungDenda(Kendaraan26 kendaraan, int bulanBayar) {
        long denda = 0;
        int bulanHarusBayar = kendaraan.getBulanHrsBayar();

        if (bulanBayar > bulanHarusBayar) {
            int telat = bulanBayar - bulanHarusBayar;
            if (telat <= 3) {
                denda = 50000 * telat;
            } else {
                denda = 50000 * 3 + 50000 * (telat - 3);
            }
        }

        return denda;
    }

    private static void tampilkanSemuaTransaksi() {
        System.out.println("| Kode | Nomor TNKB | Nama Pemilik | Bulan Bayar | Nominal Bayar | Denda |");
        for (int i = 0; i < countTransaksi; i++) {
            TransaksiPajak26 transaksi = daftarTransaksi[i];
            System.out.printf("| %-5d| %-11s| %-13s| %-12d| %-14d| %-6d|%n",
                    transaksi.getKode(), transaksi.getKendaraan().getNoTNKB(), transaksi.getKendaraan().getNama(),
                    transaksi.getBulanBayar(), transaksi.getNominalBayar(), transaksi.getDenda());
        }

        System.out.println("Semua transaksi berhasil dicetak.");
        long totalPendapatan = hitungTotalPendapatan();
        System.out.println("+++++++++++++++++++++++++++");
        System.out.println("Total Pendapatan: " + totalPendapatan);
        System.out.println("+++++++++++++++++++++++++++");
    }
    
    private static long hitungTotalPendapatan() {
        long totalPendapatan = 0;
        for (int i = 0; i < countTransaksi; i++) {
            totalPendapatan += daftarTransaksi[i].getNominalBayar() + daftarTransaksi[i].getDenda();
        }
        return totalPendapatan;
    }
    

    private static void urutkanTransaksiNamaPemilik() {
        Arrays.sort(daftarTransaksi, 0, countTransaksi, new Comparator<TransaksiPajak26>() {
            @Override
            public int compare(TransaksiPajak26 t1, TransaksiPajak26 t2) {
                String namaPemilik1 = t1.getKendaraan().getNama();
                String namaPemilik2 = t2.getKendaraan().getNama();
                return namaPemilik1.compareTo(namaPemilik2);
            }
        });

        System.out.println("| Kode | Nomor TNKB | Nama Pemilik | Bulan Bayar | Nominal Bayar | Denda |");
        for (int i = 0; i < countTransaksi; i++) {
            TransaksiPajak26 transaksi = daftarTransaksi[i];
            System.out.printf("| %-4d| %-11s| %-13s| %-12d| %-14d| %-6d|%n",
                    transaksi.getKode(), transaksi.getKendaraan().getNoTNKB(), transaksi.getKendaraan().getNama(),
                    transaksi.getBulanBayar(), transaksi.getNominalBayar(), transaksi.getDenda());
        }
    }
}