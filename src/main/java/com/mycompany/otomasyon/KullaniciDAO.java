package com.mycompany.otomasyon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KullaniciDAO {

    // Giriş Kontrol Metodu (Login)
    public boolean girisYap(String username, String password) {
        String sql = "SELECT * FROM Kullanicilar WHERE username = ? AND password = ?";
        
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Eğer eşleşen bir satır bulunduysa true döner
            
        } catch (SQLException e) {
            System.out.println("Giriş yapılırken hata oluştu: " + e.getMessage());
            return false;
        }
    }

    // Yeni Kullanıcı Ekleme (Arayüzden kullanıcı eklemek için)
    public boolean kullaniciEkle(Kullanici kullanici) {
        String sql = "INSERT INTO Kullanicilar(username, password, adSoyad, telefon, eposta) VALUES(?,?,?,?,?)";
        
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, kullanici.getUsername());
            pstmt.setString(2, kullanici.getPassword());
            pstmt.setString(3, kullanici.getAdSoyad());
            pstmt.setString(4, kullanici.getTelefon());
            pstmt.setString(5, kullanici.getEposta());
            
            pstmt.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            System.out.println("Kullanıcı eklenirken hata oluştu: " + e.getMessage());
            return false;
        }
    }
}