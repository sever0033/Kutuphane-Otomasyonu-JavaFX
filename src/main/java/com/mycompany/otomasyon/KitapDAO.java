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
        String sql = "INSERT INTO Kitaplar(yazarId, yayineviId, kitapAdi, baski, adet, barkod, durum) VALUES(?,?,?,?,?,?,?)";
        
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, kitap.getYazarId());
            pstmt.setInt(2, kitap.getYayineviId());
            pstmt.setString(3, kitap.getKitapAdi());
            pstmt.setString(4, kitap.getBaski());
            pstmt.setInt(5, kitap.getAdet());
            pstmt.setString(6, kitap.getBarkod());
            pstmt.setString(7, kitap.getDurum());
            
            pstmt.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            System.out.println("Kitap eklenirken hata oluştu: " + e.getMessage());
            return false;
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
                    rs.getInt("yazarId"),
                    rs.getInt("yayineviId"),
                    rs.getString("kitapAdi"),
                    rs.getString("baski"),
                    rs.getInt("adet"),
                    rs.getString("barkod"),
                    rs.getString("durum")
                );
                kitapListesi.add(kitap);
            }
        } catch (SQLException e) {
            System.out.println("Kitaplar listelenirken hata oluştu: " + e.getMessage());
        }
        return kitapListesi;
    }

    // 3. METOT: Veritabanından Kitap Silme (Delete)
    public boolean kitapSil(int id) {
        String sql = "DELETE FROM Kitaplar WHERE id = ?";
        
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int etkilenenSatir = pstmt.executeUpdate();
            return etkilenenSatir > 0;
            
        } catch (SQLException e) {
            System.out.println("Kitap silinirken hata oluştu: " + e.getMessage());
            return false;
        }
    }

    // 4. METOT: Kitap Güncelleme (Update)
    public boolean kitapGuncelle(Kitap kitap) {
        String sql = "UPDATE Kitaplar SET yazarId = ?, yayineviId = ?, kitapAdi = ?, baski = ?, adet = ?, barkod = ?, durum = ? WHERE id = ?";
        
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, kitap.getYazarId());
            pstmt.setInt(2, kitap.getYayineviId());
            pstmt.setString(3, kitap.getKitapAdi());
            pstmt.setString(4, kitap.getBaski());
            pstmt.setInt(5, kitap.getAdet());
            pstmt.setString(6, kitap.getBarkod());
            pstmt.setString(7, kitap.getDurum());
            pstmt.setInt(8, kitap.getId());
            
            int etkilenenSatir = pstmt.executeUpdate();
            return etkilenenSatir > 0;
            
        } catch (SQLException e) {
            System.out.println("Kitap güncellenirken hata oluştu: " + e.getMessage());
            return false;
        }
    }
}