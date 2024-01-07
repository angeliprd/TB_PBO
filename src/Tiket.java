import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

//interface
public class Tiket implements Database  {

    //koneksi ke database
    Connection conn;
    String link = "jdbc:mysql://localhost:3306/dbtiket";
    Scanner input = new Scanner(System.in);

    String namaPembeli;
    String jmlhTiket; 
    String tglPesan;
    String asal;
    String tujuan;
    String tglBerangkat;
    String jamBerangkat;
    String harga;

    public void method()
    {

    }

    //implementasi dari interface

    @Override
    public void insert() throws SQLException 
    {
        System.out.print("\n=======Tambah Jadwal Travel========");
        System.out.print("\nNama Pembeli    : ");
        this.namaPembeli = input.nextLine();
        System.out.print("Jumlah Tiket      : ");
        this.jmlhTiket = input.nextLine();
        System.out.print("Tanggal Pemesanan Tiket    : ");
        this.tglPesan = input.nextLine();
        System.out.print("Tanggal Berangkat    : ");
        this.asal = input.nextLine();
        System.out.print("Asal     : ");
        this.tujuan = input.nextLine();
        System.out.print("Tujuan   : ");
        this.tglBerangkat = input.nextLine();
        System.out.print("Jam Berangkat     : ");
        this.jamBerangkat = input.nextLine();
        System.out.print("Harga Tiket            : ");
        this.harga = input.nextLine();
    
        String sql = "INSERT INTO tbtikeet (namaPembeli, jmlhTiket, tglPesan, asal, tujuan, tglBerangkat, jamBerangkat, harga) VALUES ('"+namaPembeli+"','"+jmlhTiket+"','"+tglPesan+"','"+asal+"','"+tujuan+"','"+tglBerangkat+"','"+jamBerangkat+"', '"+harga+"' )";
        conn = DriverManager.getConnection(link,"root","");
	    Statement statement = conn.createStatement();
	    extracted(sql, statement);
	    System.out.println("Input Data Berhasil!");
        
        statement.close();
    }

    private void extracted(String sql, Statement statement) throws SQLException {
        statement.execute(sql);
    }

    @Override
    public void view() throws SQLException 
    {
        //mengakses data yang berada di database dbtiket
        String sql = "SELECT * FROM dbtiket";
        conn = DriverManager.getConnection(link,"root","");
        Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);

        //perulangan
        while (result.next())
        {
        System.out.print("\nNama Pembeli    : ");
        System.out.println(result.getString("nama"));
        System.out.print("Jumlah Tiket      : ");
        System.out.println(result.getString("jmlhTiket"));
        System.out.print("Tanggal Pesan     : ");
        System.out.println(result.getString("tglPesan"));
        System.out.print("\nAsal   : ");
        System.out.println(result.getString("asal"));
        System.out.print("\nTujuan    : ");
        System.out.println(result.getString("tujuan"));
        System.out.print("Tanggal Berangkat    : ");
        System.out.println(result.getString("tglBerangkat"));
        System.out.print("Jam Berangkat    : ");
        System.out.println(result.getString("JamBerangkat"));
        System.out.print("Harga             : ");
        System.out.println(result.getInt("harga"));
        }
        
        statement.close();
    }
    
    @Override
    public void update() throws SQLException 
    {
        //try
        try
        {
            view();
            Integer pil;
            System.out.print("\n======== Ubah Pembelian Tiket ========");
            System.out.print("\nNama Pembeli : ");
            String ubah = input.nextLine();

            //mengakses data database dbtiket pada tabel tbtikeet
            String sql = "SELECT * FROM tbtikeet WHERE nama ='"+ubah+"'";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            //percabangan  if
            if (result.next())
            {
                System.out.println("\nData yang akan diubah \n1.Jumlah Tiket\n2.Asal\n3.Tujuan\n4.Tanggal Berangkat\n5.Jam Berangkat");
                System.out.print("Pilihan Anda [1/2] : ");
                pil = input.nextInt();
                input.nextLine();
        
                //percabangan switch case
                switch (pil)
                {
                    case 1:
                        System.out.print("\nJumlah Tiket ["+result.getString("jumlah_tiket")+"]\t: ");
                        String ubah1 = input.nextLine();
                        //update data  database dbtiket pada tabel tbtikeet
                        sql = "UPDATE tbtikeet SET jumlah_tiket = '"+ubah1+"' WHERE nama ='"+ubah+"'";
                        if(statement.executeUpdate(sql) > 0)
                        {
                            System.out.println("Jumlah Tiket Berhasil Diubah!");
                        }
                    break;
                    case 2:
                        System.out.print("\nAsal  ["+result.getInt("asal")+"]\t: ");
                        Integer ubah2 = input.nextInt();
                        //update data  database dbtiket pada tabel tbtikeet
                        sql = "UPDATE tbtikeet SET asal = "+ubah2+" WHERE nama ='"+ubah+"'";
                        input.nextLine();
                        if(statement.executeUpdate(sql) > 0)
                        {
                            System.out.println("Asal Pembeli Tiket Berhasil Diubah");
                        }
                    break;
                    case 3:
                        System.out.print("\nTujuan  ["+result.getInt("tujuan")+"]\t: ");
                        Integer ubah3 = input.nextInt();
                        //update data  database dbtiket pada tabel tbtikeet
                        sql = "UPDATE tbtikeet SET tujuan = "+ubah3+" WHERE nama ='"+ubah+"'";
                        input.nextLine();
                        if(statement.executeUpdate(sql) > 0)
                        {
                            System.out.println("Tujuan Pembeli Tiket Berhasil Diubah");
                        }
                    break;
                    case 4:
                        System.out.print("\nTanggal Berangkat  ["+result.getInt("tanggal_berangkat")+"]\t: ");
                        Integer ubah4 = input.nextInt();
                        //update data  database dbtiket pada tabel tbtikeet
                        sql = "UPDATE tbtikeet SET tanggal_berangkat = "+ubah4+" WHERE nama ='"+ubah+"'";
                        input.nextLine();
                        if(statement.executeUpdate(sql) > 0)
                        {
                            System.out.println("Tanggal Berangkat Berhasil Diubah!");
                        }
                    break;
                    case 5:
                        System.out.print("\nJam Berangkat  ["+result.getInt("jam_berangkat")+"]\t: ");
                        Integer ubah5 = input.nextInt();
                        //update data  database dbtiket pada tabel tbtikeet
                        sql = "UPDATE tbtikeet SET jam_berangkat = "+ubah5+" WHERE nama ='"+ubah+"'";
                        input.nextLine();
                        if(statement.executeUpdate(sql) > 0)
                        {
                            System.out.println("Jam Berangkat Berhasil Diubah!");
                        }
                    break;
                    case 6:
                        System.out.print("\nTotal Harga ["+result.getInt("total_harga")+"]\t: ");
                        Integer ubah6 = input.nextInt();
                        //update data  database dbtiket pada tabel tbtikeet
                        sql = "UPDATE tbtikeet SET total_harga = "+ubah6+" WHERE nama ='"+ubah+"'";
                        input.nextLine();
                        if(statement.executeUpdate(sql) > 0)
                        {
                            System.out.println("Total Harga Berhasil Diubah!");
                        }
                    break;

                    default :
                        System.out.println("\n\t***ERROR***");
                        System.out.println("Input Angka 1 atau 2 Saja!");
                    break;
                }
            }

            else
            {
                System.out.println("**Error!**");
            }
        }

        //exeption SQL 
        catch (SQLException e)
        {
            System.err.println("Update Data Gagal");
        }

        //exception input tidak sesuai jenis data
        catch (InputMismatchException e)
        {
            System.err.println("Gagal! Masukkan Data yang Benar");
        }
    

    }   
    public void save() throws SQLException 
    {
        
    }

    public void delete() throws SQLException 
    {
        
    } 
}


