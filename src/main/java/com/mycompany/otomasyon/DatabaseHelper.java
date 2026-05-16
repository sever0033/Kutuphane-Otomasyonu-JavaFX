package com.mycompany.otomasyon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {
    private static final String URL = "jdbc:sqlite:kutuphane.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Veritabanı bağlantı hatası: " + e.getMessage());
        }
        return conn;
    }

    public static void createTables() {
        String sqlKitaplar = "CREATE TABLE IF NOT EXISTS Kitaplar ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " kitapAdi TEXT NOT NULL,"
                + " yazar TEXT,"
                + " sayfaSayisi INTEGER,"
                + " durum TEXT DEFAULT 'Mevcut'"
                + ");";

        String sqlKullanicilar = "CREATE TABLE IF NOT EXISTS Kullanicilar ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " adSoyad TEXT NOT NULL,"
                + " telefon TEXT,"
                + " eposta TEXT"
                + ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute(sqlKitaplar);
            stmt.execute(sqlKullanicilar);
            System.out.println(">> TABLOLAR BAŞARIYLA OLUŞTURULDU <<");
            
        } catch (SQLException e) {
            System.out.println("Tablo oluşturma hatası: " + e.getMessage());
        }
    }
}