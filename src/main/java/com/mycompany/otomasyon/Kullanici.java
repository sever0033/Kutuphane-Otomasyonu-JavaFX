package com.mycompany.otomasyon;

public class Kullanici {
    private int id;
    private String username;
    private String password;
    private String adSoyad;
    private String telefon;
    private String eposta;

    // ID'siz Constructor (Yeni kullanıcı kaydı için)
    public Kullanici(String username, String password, String adSoyad, String telefon, String eposta) {
        this.username = username;
        this.password = password;
        this.adSoyad = adSoyad;
        this.telefon = telefon;
        this.eposta = eposta;
    }

    // ID'li Constructor (Veritabanından çekmek için)
    public Kullanici(int id, String username, String password, String adSoyad, String telefon, String eposta) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.adSoyad = adSoyad;
        this.telefon = telefon;
        this.eposta = eposta;
    }

    // Getter ve Setter Metotları
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAdSoyad() { return adSoyad; }
    public void setAdSoyad(String adSoyad) { this.adSoyad = adSoyad; }

    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }

    public String getEposta() { return eposta; }
    public void setEposta(String eposta) { this.eposta = eposta; }
}