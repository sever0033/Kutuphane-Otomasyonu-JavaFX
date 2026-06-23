package com.mycompany.otomasyon;

public class Yazar {
    private int id;
    private String title;
    private String firstName;
    private String middleName;
    private String lastName;

    // 1. Boş Constructor
    public Yazar() {}

    // 2. Parametreli Constructor (Yazar Ekleken Kullanılan - ID'siz)
    public Yazar(String title, String firstName, String middleName, String lastName) {
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    // 3. Parametreli Constructor (YazarDAO Veritabanından Çekerken Kullanılan - ID'li)
    public Yazar(int id, String title, String firstName, String middleName, String lastName) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    // Getter ve Setter Metotları
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    // JavaFX ComboBox İçi Temiz Gösterim Metodu
    @Override
    public String toString() {
        String unvan = (title != null && !title.isEmpty()) ? title + " " : "";
        String ikinciAd = (middleName != null && !middleName.isEmpty()) ? middleName + " " : "";
        return unvan + firstName + " " + ikinciAd + lastName;
    }
}