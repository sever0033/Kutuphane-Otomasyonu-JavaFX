package com.mycompany.otomasyon;

public class Kitap {
    private int id;
    private int yazarId;       // Yeni: Yazarlar tablosundaki ID
    private int yayineviId;    // Yeni: Yayinevleri tablosundaki ID
    private String kitapAdi;
    private String baski;      // Yeni: Baskı sayısı/bilgisi
    private int adet;          // Yeni: Stok adeti
    private String barkod;     // Yeni: Benzersiz barkod numarası
    private String durum;

    // 1. Constructor: Yeni kitap eklerken (ID henüz veritabanında oluşmadığı için ID'siz)
    public Kitap(int yazarId, int yayineviId, String kitapAdi, String baski, int adet, String barkod, String durum) {
        this.yazarId = yazarId;
        this.yayineviId = yayineviId;
        this.kitapAdi = kitapAdi;
        this.baski = baski;
        this.adet = adet;
        this.barkod = barkod;
        this.durum = durum;
    }

    // 2. Constructor: Veritabanından verileri çekerken (ID dahil tüm bilgilerle)
    public Kitap(int id, int yazarId, int yayineviId, String kitapAdi, String baski, int adet, String barkod, String durum) {
        this.id = id;
        this.yazarId = yazarId;
        this.yayineviId = yayineviId;
        this.kitapAdi = kitapAdi;
        this.baski = baski;
        this.adet = adet;
        this.barkod = barkod;
        this.durum = durum;
    }

    // Getter ve Setter Metotları
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getYazarId() { return yazarId; }
    public void setYazarId(int yazarId) { this.yazarId = yazarId; }

    public int getYayineviId() { return yayineviId; }
    public void setYayineviId(int yayineviId) { this.yayineviId = yayineviId; }

    public String getKitapAdi() { return kitapAdi; }
    public void setKitapAdi(String kitapAdi) { this.kitapAdi = kitapAdi; }

    public String getBaski() { return baski; }
    public void setBaski(String baski) { this.baski = baski; }

    public int getAdet() { return adet; }
    public void setAdet(int adet) { this.adet = adet; }

    public String getBarkod() { return barkod; }
    public void setBarkod(String barkod) { this.barkod = barkod; }

    public String getDurum() { return durum; }
    public void setDurum(String durum) { this.durum = durum; }
}