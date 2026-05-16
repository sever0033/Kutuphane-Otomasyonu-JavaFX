package com.mycompany.otomasyon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // İçini yazıcam
        stage.setTitle("Kütüphane Otomasyon Sistemi");

        // 1. Sol Taraf: Giriş Formu Elemanları
        Label lblAd = new Label("Kitap Adı:");
        TextField txtAd = new TextField();
        
        Label lblYazar = new Label("Yazar:");
        TextField txtYazar = new TextField();
        
        Label lblSayfa = new Label("Sayfa Sayısı:");
        TextField txtSayfa = new TextField();
        
        Label lblDurum = new Label("Durum:");
        ComboBox<String> comboDurum = new ComboBox<>();
        comboDurum.getItems().addAll("Mevcut", "Ödünç Verildi");
        comboDurum.setValue("Mevcut");

        Button btnEkle = new Button("Kitap Ekle");
        btnEkle.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold;");

        // Sol tarafı dikey olarak hizalayan form kutusu
        VBox formKutusu = new VBox(10); // 10px boşluk
        formKutusu.setStyle("-fx-padding: 20; -fx-background-color: #f5f6fa;");
        formKutusu.getChildren().addAll(lblAd, txtAd, lblYazar, txtYazar, lblSayfa, txtSayfa, lblDurum, comboDurum, btnEkle);

        // 2. Sağ Taraf: Kitap Listesi Tablosu (TableView)
        TableView<Kitap> tablo = new TableView<>();
        
        TableColumn<Kitap, Integer> colId = new TableColumn<>("ID");
        TableColumn<Kitap, String> colAd = new TableColumn<>("Kitap Adı");
        TableColumn<Kitap, String> colYazar = new TableColumn<>("Yazar");
        TableColumn<Kitap, Integer> colSayfa = new TableColumn<>("Sayfa");
        TableColumn<Kitap, String> colDurum = new TableColumn<>("Durum");

        tablo.getColumns().addAll(colId, colAd, colYazar, colSayfa, colDurum);
        // Kolonların Kitap sınıfındaki hangi değişkenle eşleşeceğini söylüyoruz
        colId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        colAd.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("kitapAdi"));
        colYazar.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("yazar"));
        colSayfa.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("sayfaSayisi"));
        colDurum.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("durum"));

        // Sağ tarafı genişleten kutu
        VBox tabloKutusu = new VBox(tablo);
        HBox.setHgrow(tabloKutusu, Priority.ALWAYS);

        // 3. Ana Düzen (Pencereleri yan yana birleştirme)
        HBox anaDuzen = new HBox(formKutusu, tabloKutusu);
        
        Scene scene = new Scene(anaDuzen, 800, 500);
        stage.setScene(scene);
        stage.show();
        // --- ŞIK VE MODERN TASARIM DOKUNUŞLARI (CSS) ---
        
        // 1. Ana Arka Plan Tonalitesi
        anaDuzen.setStyle("-fx-background-color: #f8f9fa; -fx-padding: 10;");

        // 2. Sol Form Paneli Tasarımı (Gölge ve Yuvarlatılmış Köşeler)
        formKutusu.setStyle(
            "-fx-background-color: #ffffff; " +
            "-fx-padding: 25; " +
            "-fx-spacing: 12; " +
            "-fx-border-radius: 10; " +
            "-fx-background-radius: 10; " +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.08), 10, 0, 0, 4);"
        );

        // 3. Etiketlerin (Label) Yazı Tipi Ayarları
        String labelStili = "-fx-font-family: 'Segoe UI'; -fx-font-weight: bold; -fx-text-fill: #4e5d6c; -fx-font-size: 13px;";
        lblAd.setStyle(labelStili);
        lblYazar.setStyle(labelStili);
        lblSayfa.setStyle(labelStili);
        lblDurum.setStyle(labelStili);

        // 4. Giriş Kutularının (TextField & ComboBox) Tasarımı
        String inputStili = "-fx-background-color: #f1f3f5; -fx-border-color: #ced4da; -fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 8; -fx-font-size: 13px;";
        txtAd.setStyle(inputStili);
        txtYazar.setStyle(inputStili);
        txtSayfa.setStyle(inputStili);
        comboDurum.setStyle(inputStili);
        comboDurum.setMaxWidth(Double.MAX_VALUE); // Kutuyu tam genişlik yapar

        // 5. Yeşil "Kitap Ekle" Butonunu Premium Yapma
        btnEkle.setStyle(
            "-fx-background-color: #2ecc71; " +
            "-fx-text-fill: white; " +
            "-fx-font-family: 'Segoe UI'; " +
            "-fx-font-weight: bold; " +
            "-fx-font-size: 14px; " +
            "-fx-padding: 10 20; " +
            "-fx-background-radius: 6; " +
            "-fx-cursor: hand;"
        );
        btnEkle.setMaxWidth(Double.MAX_VALUE);

        // Butonun üzerine mouse gelince renginin hafif koyulaşması (Hover Efekti)
        btnEkle.setOnMouseEntered(e -> btnEkle.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-family: 'Segoe UI'; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 6; -fx-cursor: hand;"));
        btnEkle.setOnMouseExited(e -> btnEkle.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-family: 'Segoe UI'; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 6; -fx-cursor: hand;"));

        // 6. Sağ Taraftaki Tabloyu (TableView) Modernleştirme
        tablo.setStyle("-fx-background-color: transparent; -fx-font-family: 'Segoe UI'; -fx-font-size: 13px;");
        colId.setStyle("-fx-alignment: CENTER;");
        colSayfa.setStyle("-fx-alignment: CENTER;");
        colDurum.setStyle("-fx-alignment: CENTER;");
        
        // Tablo sütunlarının ekrana dengeli yayılması için
        tablo.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabloyuYenile(tablo);
        // Butona tıklandığında çalışacak kodlar
        btnEkle.setOnAction(e -> {
            String ad = txtAd.getText();
            String yazar = txtYazar.getText();
            int sayfa = Integer.parseInt(txtSayfa.getText());
            String durum = comboDurum.getValue();

            // Yeni kitap nesnesini oluşturuyoruz
            Kitap eklenecekKitap = new Kitap(ad, yazar, sayfa, durum);
            KitapDAO kitapDao = new KitapDAO();

            // Veritabanına ekliyoruz
            if (kitapDao.kitapEkle(eklenecekKitap)) {
                System.out.println("Arayüzden yeni kitap eklendi!");
                tabloyuYenile(tablo); // Tabloyu anında güncelle
                
                // Form kutularını temizle
                txtAd.clear();
                txtYazar.clear();
                txtSayfa.clear();
            }
        });
    }
    // Veritabanındaki verileri çekip tabloyu yenileyen metot
    private void tabloyuYenile(TableView<Kitap> tablo) {
        KitapDAO kitapDao = new KitapDAO();
        tablo.getItems().clear(); // Önce tabloyu temizle
        tablo.getItems().addAll(kitapDao.tumKitaplariGetir()); // Güncel listeyi yükle
    }

    public static void main(String[] args) {
        launch();
    }
}