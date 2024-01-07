import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Class Program
public class App
{
    //Koneksi ke Database
    static Connection conn;

    static String link = "jdbc:mysql://localhost:3306/dbtiket";
    static Scanner input = new Scanner(System.in);

    //method main
        public static void main(String[] args) throws Exception 
    {
        System.out.println("\n===========ANGEL TIKET.COM===========");
        System.out.println("\n               WELCOME               ");
        System.out.println("\n            SILAHKAN LOGIN               ");
        admin();

        menu();
        
        DateFormat formatTanggal = new SimpleDateFormat("dd MMMM yyyy");
        DateFormat formatJam = new SimpleDateFormat("HH:mm:ss");
        Date tanggal = new Date();

        //date
        System.out.println("====================================");
        System.out.println("Dibuat pada     : "+formatTanggal.format(tanggal)+" =");
        System.out.println("Diupdate pada   : "+formatJam.format(tanggal)+" WIB    =");
        System.out.println("====================================");
    }

    private static void admin() throws SQLException
    {
        //Membuat Objek HashMap Baru
        Map<String, String> Login = new HashMap<String, String>();

        //Membaca Data di database dbtiket tabel login
        String inputUsername, inputPassword;
        String sql = "SELECT * FROM login";
        boolean relogin = true;
        conn = DriverManager.getConnection(link,"root","");
        Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);

        //perulangan while
        while (result.next())
        {
            //mengambil nilai di database dan menyimpannya ke dalam variable
            String username = result.getString("username");
            String password = result.getString("password");

            //input key dan value 
            Login.put(username, password);
        }
        System.out.println("======================================");

        //perulangan while
        while (relogin)
        {

            System.out.print("Username : ");
            inputUsername = input.nextLine();
            System.out.print("Password : ");
            inputPassword = input.nextLine();

            if (Login.get(inputUsername).equals(inputPassword)==true)
            {
                System.out.println("----Login Anda Berhasil----");
                relogin = false;
            }

            if (Login.get(inputUsername).equals(inputPassword)==false)
            {
                System.out.println("----Login Anda Gagal----");
                System.out.println("Masukkan Username atau Password yang Benar!\n");
                relogin = true;
            }
        }
        statement.close();
    }

    private static void menu() throws SQLException 
    {
        boolean DaftarMenu = true;
        boolean TanyaKembali = true;
        Integer pilihan;
        String pertanyaan;

        //perulangan while
        while (DaftarMenu)
        {
            System.out.println("====================================");
            System.out.println("                MENU                ");
            System.out.println("====================================");
            System.out.println("1.Lihat Riwayat Pembelian Tiket");
            System.out.println("2.Masukan Data Pembeli Tiket");
            System.out.println("3.Ubah Data Pembelian tiket");
            System.out.println("4.Tambah Data Pembelian Tiket");
            System.out.println("5.Batalkan Pembelian Tiket");
            System.out.println("6.Keluar Dari Program");
            System.out.print("Pilihan Anda (1/2/3/4/5/6): ");
            
            pilihan = input.nextInt();
            input.nextLine();
            Tiket tiket = new Tiket();
            PembelianTiket pembelianTiket = new PembelianTiket();

            //percabangan switch case
            switch (pilihan) 
            {
                case 1:
                    tiket.view();
                    TanyaKembali = true;
                break;
                          
                case 2:
                    tiket.insert();
                    TanyaKembali= true;
                break;

                case 3:
                    tiket.update();
                    TanyaKembali = true;
                break;

                case 4:
                    pembelianTiket.save();
                    TanyaKembali = true;
                break;

                case 5:
                    pembelianTiket.delete();
                    TanyaKembali = true;
                break;

                case 6:
                    TanyaKembali = false;
                    DaftarMenu = false;
                break;

                default :
                    System.out.println("Inputan Angka  1/2/3/4/5/6!");
                break;
            }
            
            //perulangan while
            while (TanyaKembali)
            {
                System.out.print("\nKembali  [y/n]? ");
                pertanyaan = input.nextLine();
                //percabangan if else if
                if (pertanyaan.equalsIgnoreCase("n")) //method string 
                {
                    DaftarMenu = false;
                    TanyaKembali = false;
                }
                else if (pertanyaan.equalsIgnoreCase("y")) //method string
                {
                    DaftarMenu = true;
                    TanyaKembali = false;
                }
                else 
                {
                    System.out.println("Inputkan 'y' atau 'n' saja");
                }
            }
        }
        System.out.println("\n\n\t\tSelesai\n");
    }
}