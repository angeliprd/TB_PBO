import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

//inheritance
public class PembelianTiket extends Tiket {

    //koneksi database
    Connection conn;
    String link = "jdbc:mysql://localhost:3306/dbtiket";
    
    Scanner input = new Scanner(System.in);

    String namaPembeli,tglPesan, asal, tujuan, tglBerangkat, jamBerangkat;
    Integer jmlhtiketMax = 20, tiket_tersedia, tiket_dipesan, total_harga;
    private String jmlhTiket;


    public void namaPembeli()
    {
        System.out.print("Nama Pembeli     : ");
        this.namaPembeli = input.nextLine();
    }
    public void tglPesan()
    {
        System.out.print("Tanggal Pemesanan    : ");
        this.tglPesan = input.nextLine();
    }

    public void asal()
    {
        System.out.print("Asal Pembeli    : ");
        this.tglPesan = input.nextLine();
    }

    public void tujuan()
    {
        System.out.print("Tujuan Pembeli    : ");
        this.tujuan = input.nextLine();
    }

    public void tglBerangkat()
    {
        System.out.print("Tanggal Berangkat   : ");
        this.tglBerangkat = input.nextLine();
    }

    public void jamBerangkat()
    {
        System.out.print("Jam Berangkat   : ");
        this.jamBerangkat = input.nextLine();
    }

    public void harga()
    {
        System.out.print("Harga            : ");
        this.total_harga = input.nextInt();
    }

    public void jumlah_Tiket()
    {
      
        System.out.print("Jumlah Tiket     : ");
        tiket_dipesan = input.nextInt();
        {
 
            try {
                if (tiket_dipesan <= 0 || tiket_dipesan > 10) {  
                        System.out.println("Tiket Tidak Tersedia");
                    }
                } catch (Exception nullpException) {
                    System.out.println("\nERROR!\n");
                }
        }
    }

    public void total_harga()
    {
        // Total harga yang harus dibayar 
        this.total_harga = this.total_harga * this.tiket_dipesan;
        System.out.print("\nTotal Harga       :  Rp" + this.total_harga + "");
    }

@Override //Pemesanan Tiket
    public void save() throws SQLException 
    {
        try
        {
            view();
            System.out.println("\n========= Pesan Tiket =========");
            namaPembeli();
            tglPesan();
            asal();
            tujuan();
            tglBerangkat();
            jamBerangkat();
            jmlhTiket();
            total_harga();


            conn = DriverManager.getConnection(link,"root","");
            Statement statement = conn.createStatement();
            String sql = "INSERT INTO tbtikeet (namaPembeli, tglPesan, jmlhtiket, asal, tujuan, tglBerangkat, harga, jmlhTiket) VALUES ('"+this.namaPembeli+"', '"+this.tglPesan+"', '"+this.asal+"','"+this.tujuan+"','"+this.tglBerangkat+"', '"+this.total_harga+"', '"+this.jmlhTiket+"')";
            statement.execute(sql);
            System.out.println("\nTiket Berhasil Dibeli");

        }

        //exception SQL
        catch(SQLException e)
        {
            System.err.println("\n Tiket Gagal Dibeli");
        }

        catch(InputMismatchException e)
        {
            System.out.println("\n Masukan Data Yang Benar!");
        }
    }

    private void jmlhTiket() {
}
    @Override
    public void delete() throws SQLException 
    {
        try
        {
            System.out.println("====== Pembatalan Pembelian Tiket ======");
            System.out.println("Nama pembeli : " );
            this.namaPembeli = input.next();

            String sql = "DELETE FROM tbtikeet WHERE nama = "+namaPembeli;
	        conn = DriverManager.getConnection(link, "root","");
	        Statement statement = conn.createStatement();
	        //ResultSet result = statement.executeQuery(sql);
	        
	        if(statement.executeUpdate(sql) > 0){
	            System.out.println("--- Berhasil Menghapus Tiket "+namaPembeli+"---");
	        }
        }

        catch(SQLException e){
	        System.out.println("Terjadi Kesalahan Penghapusan Data ");
	    }

        catch(Exception e){
            System.out.println("Masukan Nama Pemesan Yang Benar");
        }

    }

    
}


    
    

    

