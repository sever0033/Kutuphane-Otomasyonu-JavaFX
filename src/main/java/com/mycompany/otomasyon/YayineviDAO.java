
package com.mycompany.otomasyon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class YayineviDAO {

    // 1. METOT: Yeni Yayınevi Ekleme (Insert)
    public boolean yayineviEkle(Yayinevi yayinevi) {
        String sql = "INSERT INTO Yayinevleri(yayineviAdi, adres, webSitesi) VALUES(?,?,?)";
        
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, yayinevi.getYayineviAdi());
            pstmt.setString(2, yayinevi.getAdres());
            pstmt.setString(3, yayinevi.getWebSitesi());
            
            pstmt.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            System.out.println("Yayınevi eklenirken hata oluştu: " + e.getMessage());
            return false;
        }
    }

    // 2. METOT: Tüm Yayınevlerini Listeleme (Select)
    public List<Yayinevi> tumYayinevleriniGetir() {
        List<Yayinevi> yayineviListesi = new ArrayList<>();
        String sql = "SELECT * FROM Yayinevleri";
        
        try (Connection conn = DatabaseHelper.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Yayinevi yayinevi = new Yayinevi(
                    rs.getInt("id"),
                    rs.getString("yayineviAdi"),
                    rs.getString("adres"),
                    rs.getString("webSitesi")
                );
                yayineviListesi.add(yayinevi);
            }
        } catch (SQLException e) {
            System.out.println("Yayınevleri listelenirken hata oluştu: " + e.getMessage());
        }
        return yayineviListesi;
    }

    // 3. METOT: Yayınevi Silme (Delete)
    public boolean yayineviSil(int id) {
        String sql = "DELETE FROM Yayinevleri WHERE id = ?";
        
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int etkilenenSatir = pstmt.executeUpdate();
            return etkilenenSatir > 0;
            
        } catch (SQLException e) {
            System.out.println("Yayınevi silinirken hata oluştu: " + e.getMessage());
            return false;
        }
    }

    // 4. METOT: Yayınevi Güncelleme (Update)
    public boolean yayineviGuncelle(Yayinevi yayinevi) {
        String sql = "UPDATE Yayinevleri SET yayineviAdi = ?, adres = ?, webSitesi = ? WHERE id = ?";
        
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, yayinevi.getYayineviAdi());
            pstmt.setString(2, yayinevi.getAdres());
            pstmt.setString(3, yayinevi.getWebSitesi());
            pstmt.setInt(4, yayinevi.getId());
            
            int etkilenenSatir = pstmt.executeUpdate();
            return etkilenenSatir > 0;
            
        } catch (SQLException e) {
            System.out.println("Yayınevi güncellenirken hata oluştu: " + e.getMessage());
            return false;
        }
    }
}