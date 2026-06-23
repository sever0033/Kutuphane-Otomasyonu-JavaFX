package com.mycompany.otomasyon;

public class Yayınevi {
    private int id;
    private String yayineviAdi;
    private String adres;
    private String webSitesi;

    // Yeni eklerken ID otomatik oluştuğu için ID'siz constructor
    public Yayınevi(String yayineviAdi, String adres, String webSitesi) {
        this.yayineviAdi = yayineviAdi;
        this.adres = adres;
        this.webSitesi = webSitesi;
    }

    // Veritabanından çekerken kullanacağımız ID'li constructor
    public Yayınevi(int id, String yayineviAdi, String adres, String webSitesi) {
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
}
