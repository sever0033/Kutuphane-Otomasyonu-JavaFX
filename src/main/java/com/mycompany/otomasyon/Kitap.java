
package com.mycompany.otomasyon;

public class Kitap {
    private int id;
    private String kitapAdi;
    private String yazar;
    private int sayfaSayisi;
    private String durum;

    // 1. Constructor (Yeni kitap eklerken ID otomatik oluştuğu için ID'siz versiyon)
    public Kitap(String kitapAdi, String yazar, int sayfaSayisi, String durum) {
        this.kitapAdi = kitapAdi;
        this.yazar = yazar;
        this.sayfaSayisi = sayfaSayisi;
        this.durum = durum;
    }

    // 2. Constructor (Veritabanından kitapları çekerken ID ile birlikte almak için)
    public Kitap(int id, String kitapAdi, String yazar, int sayfaSayisi, String durum) {
        this.id = id;
        this.kitapAdi = kitapAdi;
        this.yazar = yazar;
        this.sayfaSayisi = sayfaSayisi;
        this.durum = durum;
    }

    // Getter ve Setter Metotları (Verilere güvenli erişim için)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getKitapAdi() { return kitapAdi; }
    public void setKitapAdi(String kitapAdi) { this.kitapAdi = kitapAdi; }

    public String getYazar() { return yazar; }
    public void setYazar(String yazar) { this.yazar = yazar; }

    public int getSayfaSayisi() { return sayfaSayisi; }
    public void setSayfaSayisi(int sayfaSayisi) { this.sayfaSayisi = sayfaSayisi; }

    public String getDurum() { return durum; }
    public void setDurum(String durum) { this.durum = durum; }
}