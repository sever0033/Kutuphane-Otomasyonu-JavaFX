package com.mycompany.otomasyon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KitapDAO {

    // 1. METOT: Veritabanına Yeni Kitap Ekleme (Insert)
    public boolean kitapEkle(Kitap kitap) {
        String sql = "INSERT INTO Kitaplar(kitapAdi, yazar, sayfaSayisi, durum) VALUES(?,?,?,?)";
        
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, kitap.getKitapAdi());
            pstmt.setString(2, kitap.getYazar());
            pstmt.setInt(3, kitap.getSayfaSayisi());
            pstmt.setString(4, kitap.getDurum());
            
            pstmt.executeUpdate();
            return true; // Ekleme başarılıysa true döner
            
        } catch (SQLException e) {
            System.out.println("Kitap eklenirken hata oluştu: " + e.getMessage());
            return false; // Hata varsa false döner
        }
    }

    // 2. METOT: Veritabanındaki Tüm Kitapları Listeleme (Select)
    public List<Kitap> tumKitaplariGetir() {
        List<Kitap> kitapListesi = new ArrayList<>();
        String sql = "SELECT * FROM Kitaplar";
        
        try (Connection conn = DatabaseHelper.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Kitap kitap = new Kitap(
                    rs.getInt("id"),
                    rs.getString("kitapAdi"),
                    rs.getString("yazar"),
                    rs.getInt("sayfaSayisi"),
                    rs.getString("durum")
                );
                kitapListesi.add(kitap);
            }
        } catch (SQLException e) {
            System.out.println("Kitaplar listelenirken hata oluştu: " + e.getMessage());
        }
        return kitapListesi;
    }
    // Veritabanından, arayüzden gönderilecek olan ID'ye göre kitap silen metot
    public boolean kitapSil(int id) {
        String sql = "DELETE FROM Kitaplar WHERE id = ?";
        
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Soru işaretinin yerine silinecek kitabın ID numarasını koyuyoruz
            pstmt.setInt(1, id);
            
            int etkilenenSatir = pstmt.executeUpdate();
            return etkilenenSatir > 0; // Satır başarıyla silindiyse true döner
            
        } catch (SQLException e) {
            System.out.println("Kitap silinirken hata oluştu: " + e.getMessage());
            return false;
        }
    }
}