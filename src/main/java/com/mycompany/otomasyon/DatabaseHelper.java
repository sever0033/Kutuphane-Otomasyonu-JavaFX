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
        // 1. Yayınevleri Tablosu
        String sqlYayinevleri = "CREATE TABLE IF NOT EXISTS Yayinevleri ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " yayineviAdi TEXT NOT NULL,"
                + " adres TEXT,"
                + " webSitesi TEXT"
                + ");";  

        // 2. Yazarlar Tablosu
        String sqlYazarlar = "CREATE TABLE IF NOT EXISTS Yazarlar ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " title TEXT,"
                + " firstName TEXT NOT NULL,"
                + " middleName TEXT,"
                + " lastName TEXT NOT NULL"
                + ");";

        // 3. Kitaplar Tablosu (Yeni ilişkisel yapımız)
        String sqlKitaplar = "CREATE TABLE IF NOT EXISTS Kitaplar ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " yazarId INTEGER,"
                + " yayineviId INTEGER,"
                + " kitapAdi TEXT NOT NULL,"
                + " baski TEXT,"
                + " adet INTEGER DEFAULT 1,"
                + " barkod TEXT UNIQUE,"
                + " durum TEXT DEFAULT 'Mevcut',"
                + " FOREIGN KEY(yazarId) REFERENCES Yazarlar(id),"
                + " FOREIGN KEY(yayineviId) REFERENCES Yayinevleri(id)"
                + ");";

        // 4. Kullanıcılar Tablosu (Giriş bilgileri için username/password alanları eklendi)
        String sqlKullanicilar = "CREATE TABLE IF NOT EXISTS Kullanicilar ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " username TEXT NOT NULL UNIQUE,"
                + " password TEXT NOT NULL,"
                + " adSoyad TEXT NOT NULL,"
                + " telefon TEXT,"
                + " eposta TEXT"
                + ");";

        // 5. Ödünç İşlemleri Tablosu
        String sqlOdunc = "CREATE TABLE IF NOT EXISTS OduncIslemleri ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " kitapId INTEGER,"
                + " kullaniciId INTEGER,"
                + " alisTarihi TEXT,"
                + " iadeTarihi TEXT,"
                + " FOREIGN KEY(kitapId) REFERENCES Kitaplar(id),"
                + " FOREIGN KEY(kullaniciId) REFERENCES Kullanicilar(id)"
                + ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            
            // SQLite üzerinde yabancı anahtar (FOREIGN KEY) kısıtlamalarını aktif ediyoruz
            stmt.execute("PRAGMA foreign_keys = ON;");
            
            // Eski Kitaplar tablosu eski yapıda kaldıysa çakışmayı önlemek için sıfırlıyoruz
            stmt.execute("DROP TABLE IF EXISTS Kitaplar;");

            // Sırasıyla tüm tabloları veritabanında çalıştırıp oluşturuyoruz
            stmt.execute(sqlYayinevleri);
            stmt.execute(sqlYazarlar);
            stmt.execute(sqlKitaplar);
            stmt.execute(sqlKullanicilar);
            stmt.execute(sqlOdunc);
            
            System.out.println(">> TABLOLAR BAŞARIYLA OLUŞTURULDU <<");
            
        } catch (SQLException e) {
            System.out.println("Tablo oluşturma hatası: " + e.getMessage());
        }
    }
}