package com.mycompany.otomasyon;

public class Yazar {
    private int id;
    private String title; // Mr, Mrs, Dr, Prof vb.
    private String firstName;
    private String middleName; // Boş bırakılabilir
    private String lastName;

    // ID'siz constructor
    public Yazar(String title, String firstName, String middleName, String lastName) {
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    // ID'li constructor
    public Yazar(int id, String title, String firstName, String middleName, String lastName) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    // Arayüzde veya ComboBox'ta yazar ismini düzgün göstermek için toString() metodu
    @Override
    public String toString() {
        String tamAd = (title != null ? title + " " : "") + firstName;
        if (middleName != null && !middleName.trim().isEmpty()) {
            tamAd += " " + middleName;
        }
        tamAd += " " + lastName;
        return tamAd;
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
}
