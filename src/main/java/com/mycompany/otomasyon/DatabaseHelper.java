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
        String sqlYayinevleri = "CREATE TABLE IF NOT EXISTS Yayinevleri ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " yayineviAdi TEXT NOT NULL,"
                + " adres TEXT,"
                + " webSitesi TEXT"
                + ");";  

        String sqlYazarlar = "CREATE TABLE IF NOT EXISTS Yazarlar ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " title TEXT,"
                + " firstName TEXT NOT NULL,"
                + " middleName TEXT,"
                + " lastName TEXT NOT NULL"
                + ");";

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

        String sqlKullanicilar = "CREATE TABLE IF NOT EXISTS Kullanicilar ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " username TEXT NOT NULL UNIQUE,"
                + " password TEXT NOT NULL,"
                + " adSoyad TEXT NOT NULL,"
                + " telefon TEXT,"
                + " eposta TEXT"
                + ");";

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
            
            // SQLite üzerinde ilişkileri aktif ediyoruz
            stmt.execute("PRAGMA foreign_keys = ON;");
            
            // Çakışma yaratabilecek eski tabloları tamamen uçuruyoruz
            stmt.execute("DROP TABLE IF EXISTS Kitaplar;");
            stmt.execute("DROP TABLE IF EXISTS Kullanicilar;");
            stmt.execute("DROP TABLE IF EXISTS OduncIslemleri;");

            // Yeni ve doğru kolonlara sahip tabloları sıfırdan kuruyoruz
            stmt.execute(sqlYayinevleri);
            stmt.execute(sqlYazarlar);
            stmt.execute(sqlKitaplar);
            stmt.execute(sqlKullanicilar);
            stmt.execute(sqlOdunc);
            
            System.out.println(">> TABLOLAR BAŞARIYLA SIFIRLANDI VE OLUŞTURULDU <<");
            
        } catch (SQLException e) {
            System.out.println("Tablo oluşturma hatası: " + e.getMessage());
        }
    }
} 