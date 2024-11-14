import java.util.Scanner;

public class buah {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Selamat datang di Superindo \nSelamat berbelanja!\n");
        System.out.println("List Buah");
        System.out.println("1. Apel     : 30000/Kg");
        System.out.println("2. Rambutan : 27000/Kg");
        System.out.println("3. Jeruk    : 15000/Kg");
        System.out.println("4. Stroberi : 35000/Kg");
        System.out.println("5. Mangga   : 18000/Kg");
        
        String[] nama = {"Apel", "Rambutan", "Jeruk", "Stroberi", "Mangga"}
        double[][] array = {{1, 30000}, {2, 27000}, {3, 15000}, {4, 35000}, {5, 18000}};
        double totalHarga = 0;
        while (true) {
            System.out.print("Masukkan Kode Buah (0 untuk selesai): ");
            int kode = sc.nextInt();
            
            if (kode == 0) {
                break;
            }
            
            if (kode < 1 || kode > 5) {
                System.out.println("Buah tidak ditemukan. Silakan masukkan lagi.");
                continue;
            }

            System.out.print("Masukkan Berat Buah (dalam Kg): ");
            double berat = sc.nextDouble();
            
        
            double hargaPerKg = array[kode - 1][1];
            double harga = hargaPerKg * berat;
        
            totalHarga += harga;

            System.out.printf("Harga untuk %s Kg buah dengan kode %d adalah: Rp%.2f\n\n", berat, kode, harga);
        }
        
        System.out.printf("Total harga keseluruhan adalah: Rp%.2f\n", totalHarga);
        sc.close();
    }
}
