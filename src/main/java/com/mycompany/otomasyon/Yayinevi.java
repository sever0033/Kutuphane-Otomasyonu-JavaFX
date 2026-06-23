package com.mycompany.otomasyon;

public class Yayinevi {
    private int id;
    private String yayineviAdi;
    private String adres;
    private String webSitesi;

    // 1. Boş Constructor
    public Yayinevi() {}

    // 2. Parametreli Constructor (Yayınevi Ekleken Kullanılan - ID'siz)
    public Yayinevi(String yayineviAdi, String adres, String webSitesi) {
        this.yayineviAdi = yayineviAdi;
        this.adres = adres;
        this.webSitesi = webSitesi;
    }

    // 3. Parametreli Constructor (YayineviDAO Veritabanından Çekerken Kullanılan - ID'li)
    public Yayinevi(int id, String yayineviAdi, String adres, String webSitesi) {
        this.id = id;
        this.yayineviAdi = yayineviAdi;
        this.adres = adres;
        this.webSitesi = webSitesi;
    }

    // Getter ve Setter Metotları
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getYayineviAdi() { return yayineviAdi; }
    public void setYayineviAdi(String yayineviAdi) { this.yayineviAdi = yayineviAdi; }

    public String getAdres() { return adres; }
    public void setAdres(String adres) { this.adres = adres; }

    public String getWebSitesi() { return webSitesi; }
    public void setWebSitesi(String webSitesi) { this.webSitesi = webSitesi; }

    // JavaFX ComboBox İçi Temiz Gösterim Metodu
    @Override
    public String toString() {
        return yayineviAdi;
    }
}