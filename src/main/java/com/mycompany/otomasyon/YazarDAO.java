package com.mycompany.otomasyon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class YazarDAO {

    // 1. METOT: Yeni Yazar Ekleme (Insert)
    public boolean yazarEkle(Yazar yazar) {
        String sql = "INSERT INTO Yazarlar(title, firstName, middleName, lastName) VALUES(?,?,?,?)";
        
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, yazar.getTitle());
            pstmt.setString(2, yazar.getFirstName());
            pstmt.setString(3, yazar.getMiddleName());
            pstmt.setString(4, yazar.getLastName());
            
            pstmt.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            System.out.println("Yazar eklenirken hata oluştu: " + e.getMessage());
            return false;
        }
    }

    // 2. METOT: Tüm Yazarları Listeleme (Select)
    public List<Yazar> tumYazarlariGetir() {
        List<Yazar> yazarListesi = new ArrayList<>();
        String sql = "SELECT * FROM Yazarlar";
        
        try (Connection conn = DatabaseHelper.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Yazar yazar = new Yazar(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("firstName"),
                    rs.getString("middleName"),
                    rs.getString("lastName")
                );
                yazarListesi.add(yazar);
            }
        } catch (SQLException e) {
            System.out.println("Yazarlar listelenirken hata oluştu: " + e.getMessage());
        }
        return yazarListesi;
    }

    // 3. METOT: Yazar Silme (Delete)
    public boolean yazarSil(int id) {
        String sql = "DELETE FROM Yazarlar WHERE id = ?";
        
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int etkilenenSatir = pstmt.executeUpdate();
            return etkilenenSatir > 0;
            
        } catch (SQLException e) {
            System.out.println("Yazar silinirken hata oluştu: " + e.getMessage());
            return false;
        }
    }

    // 4. METOT: Yazar Güncelleme (Update)
    public boolean yazarGuncelle(Yazar yazar) {
        String sql = "UPDATE Yazarlar SET title = ?, firstName = ?, middleName = ?, lastName = ? WHERE id = ?";
        
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, yazar.getTitle());
            pstmt.setString(2, yazar.getFirstName());
            pstmt.setString(3, yazar.getMiddleName());
            pstmt.setString(4, yazar.getLastName());
            pstmt.setInt(5, yazar.getId());
            
            int etkilenenSatir = pstmt.executeUpdate();
            return etkilenenSatir > 0;
            
        } catch (SQLException e) {
            System.out.println("Yazar güncellenirken hata oluştu: " + e.getMessage());
            return false;
        }
    }
}