package com.mycompany.otomasyon;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainFrame {

    public void showMenu(Stage stage) {
        stage.setTitle("Kütüphane Otomasyonu - Ana Yönetim Paneli");

        // Üst Başlık Alanı
        VBox topContainer = new VBox();
        topContainer.setPadding(new Insets(15, 12, 15, 12));
        topContainer.setStyle("-fx-background-color: #2c3e50;");
        
        Label mainTitle = new Label("KÜTÜPHANE OTOMASYON SİSTEMİ");
        mainTitle.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        mainTitle.setStyle("-fx-text-fill: white;");
        topContainer.getChildren().add(mainTitle);
        topContainer.setAlignment(Pos.CENTER);

        // Ana Sekme Yapısı (TabPane)
        TabPane tabPane = new TabPane();

        // --- 1. SEKME: KİTAP YÖNETİMİ ---
        Tab tabKitap = new Tab("Kitap Yönetimi");
        tabKitap.setClosable(false);

        // Düzen ve Elemanlar (Form)
        GridPane kitapForm = new GridPane();
        kitapForm.setHgap(10); kitapForm.setVgap(10);
        kitapForm.setPadding(new Insets(10));

        kitapForm.add(new Label("Kitap Adı:"), 0, 0);
        TextField txtKitapAdi = new TextField();
        kitapForm.add(txtKitapAdi, 1, 0);

        // Dinamik Yazar Seçim Kutusu (ComboBox)
        kitapForm.add(new Label("Yazar Seçin:"), 0, 1);
        ComboBox<Yazar> comboYazarlar = new ComboBox<>();
        comboYazarlar.setPromptText("Yazar Seçiniz");
        kitapForm.add(comboYazarlar, 1, 1);

        // Dinamik Yayınevi Seçim Kutusu (ComboBox)
        kitapForm.add(new Label("Yayınevi Seçin:"), 0, 2);
        ComboBox<Yayinevi> comboYayinevleri = new ComboBox<>();
        comboYayinevleri.setPromptText("Yayınevi Seçiniz");
        kitapForm.add(comboYayinevleri, 1, 2);

        kitapForm.add(new Label("Baskı/Yıl:"), 0, 3);
        TextField txtBaski = new TextField();
        kitapForm.add(txtBaski, 1, 3);

        kitapForm.add(new Label("Adet:"), 0, 4);
        TextField txtAdet = new TextField("1"); // Varsayılan değer 1
        kitapForm.add(txtAdet, 1, 4);

        kitapForm.add(new Label("Barkod (Unique):"), 0, 5);
        TextField txtBarkod = new TextField();
        kitapForm.add(txtBarkod, 1, 5);

        Button btnKitapEkle = new Button("Kitap Ekle");
        kitapForm.add(btnKitapEkle, 1, 6);

        Label lblKitapDurum = new Label();
        kitapForm.add(lblKitapDurum, 1, 7);

        // Kitap Tablo Yapısı
        TableView<Kitap> kitapTablo = new TableView<>();

        TableColumn<Kitap, Integer> colKitapId = new TableColumn<>("ID");
        colKitapId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));

        TableColumn<Kitap, String> colKitapAdi = new TableColumn<>("Kitap Adı");
        colKitapAdi.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("kitapAdi"));

        TableColumn<Kitap, String> colKitapBaski = new TableColumn<>("Baskı");
        colKitapBaski.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("baski"));

        TableColumn<Kitap, Integer> colKitapAdet = new TableColumn<>("Adet");
        colKitapAdet.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("adet"));

        TableColumn<Kitap, String> colKitapBarkod = new TableColumn<>("Barkod");
        colKitapBarkod.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("barkod"));

        TableColumn<Kitap, String> colKitapDurum = new TableColumn<>("Durum");
        colKitapDurum.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("durum"));

        kitapTablo.getColumns().addAll(colKitapId, colKitapAdi, colKitapBaski, colKitapAdet, colKitapBarkod, colKitapDurum);

        // DAO Bağlantıları
        KitapDAO kitapDAO = new KitapDAO();
        YazarDAO kYazarDAO = new YazarDAO();
        YayineviDAO kYayineviDAO = new YayineviDAO();

        // Veritabanındaki yazar ve yayınevlerini ComboBox'lara dolduruyoruz
        comboYazarlar.getItems().addAll(kYazarDAO.tumYazarlariGetir());
        comboYayinevleri.getItems().addAll(kYayineviDAO.tumYayinevleriniGetir());
        
        // Mevcut kitapları tabloya listele
        kitapTablo.getItems().addAll(kitapDAO.tumKitaplariGetir());

        // BUTON AKSİYONU: Kitap Ekleme
        btnKitapEkle.setOnAction(e -> {
            String kitapAdi = txtKitapAdi.getText();
            Yazar secilenYazar = comboYazarlar.getValue();
            Yayinevi secilenYayinevi = comboYayinevleri.getValue();
            String baski = txtBaski.getText();
            String barkod = txtBarkod.getText();
            
            int adet;
            try {
                adet = Integer.parseInt(txtAdet.getText());
            } catch (NumberFormatException ex) {
                lblKitapDurum.setStyle("-fx-text-fill: red;");
                lblKitapDurum.setText("Adet alanı sayı olmalıdır!");
                return;
            }

            if (kitapAdi.isEmpty() || secilenYazar == null || secilenYayinevi == null || barkod.isEmpty()) {
                lblKitapDurum.setStyle("-fx-text-fill: red;");
                lblKitapDurum.setText("Lütfen boş alanları doldurun ve seçim yapın!");
                return;
            }

            // Kitap nesnesini ilişkisel ID'lerle oluşturuyoruz
            Kitap yeniKitap = new Kitap(secilenYazar.getId(), secilenYayinevi.getId(), kitapAdi, baski, adet, barkod, "Mevcut");
            
            if (kitapDAO.kitapEkle(yeniKitap)) {
                lblKitapDurum.setStyle("-fx-text-fill: green;");
                lblKitapDurum.setText("Kitap başarıyla eklendi!");

                // Formu temizle
                txtKitapAdi.clear(); txtBaski.clear(); txtBarkod.clear(); txtAdet.setText("1");
                comboYazarlar.setValue(null); comboYayinevleri.setValue(null);
                
                // Tabloyu güncelle
                kitapTablo.getItems().clear();
                kitapTablo.getItems().addAll(kitapDAO.tumKitaplariGetir());
            } else {
                lblKitapDurum.setStyle("-fx-text-fill: red;");
                lblKitapDurum.setText("Hata: Barkod benzersiz olmalıdır!");
            }
        });

        // Her Sekme Açıldığında ComboBox'ların Güncel Verileri Çekmesini Sağlıyoruz (Sihirli Dokunuş)
        tabKitap.setOnSelectionChanged(event -> {
            if (tabKitap.isSelected()) {
                comboYazarlar.getItems().clear();
                comboYayinevleri.getItems().clear();
                comboYazarlar.getItems().addAll(kYazarDAO.tumYazarlariGetir());
                comboYayinevleri.getItems().addAll(kYayineviDAO.tumYayinevleriniGetir());
            }
        });

        // Düzen Yerleşimi (Sol form, Sağ tablo)
        HBox kitapLayout = new HBox(20, kitapForm, kitapTablo);
        HBox.setHgrow(kitapTablo, Priority.ALWAYS);
        kitapLayout.setPadding(new Insets(15));
        tabKitap.setContent(kitapLayout);

        // --- 2. SEKME: YAZAR YÖNETİMİ ---
        Tab tabYazar = new Tab("Yazar Yönetimi");
        tabYazar.setClosable(false);
        
        GridPane yazarForm = new GridPane();
        yazarForm.setHgap(10); yazarForm.setVgap(10);
        yazarForm.setPadding(new Insets(10));

        yazarForm.add(new Label("Unvan (Title):"), 0, 0);
        TextField txtYazarTitle = new TextField();
        txtYazarTitle.setPromptText("Örn: Prof. Dr. veya Boş");
        yazarForm.add(txtYazarTitle, 1, 0);

        yazarForm.add(new Label("Ad (First Name):"), 0, 1);
        TextField txtYazarFirst = new TextField();
        yazarForm.add(txtYazarFirst, 1, 1);

        yazarForm.add(new Label("İkinci Ad (Middle Name):"), 0, 2);
        TextField txtYazarMiddle = new TextField();
        txtYazarMiddle.setPromptText("Yoksa boş bırakın");
        yazarForm.add(txtYazarMiddle, 1, 2);

        yazarForm.add(new Label("Soyad (Last Name):"), 0, 3);
        TextField txtYazarLast = new TextField();
        yazarForm.add(txtYazarLast, 1, 3);

        Button btnYazarEkle = new Button("Yazar Ekle");
        yazarForm.add(btnYazarEkle, 1, 4);

        Label lblYazarDurum = new Label();
        yazarForm.add(lblYazarDurum, 1, 5);

        TableView<Yazar> yazarTablo = new TableView<>();
        
        TableColumn<Yazar, Integer> colYazarId = new TableColumn<>("ID");
        colYazarId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        
        TableColumn<Yazar, String> colYazarTitle = new TableColumn<>("Unvan");
        colYazarTitle.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("title"));
        
        TableColumn<Yazar, String> colYazarFirst = new TableColumn<>("Ad");
        colYazarFirst.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("firstName"));
        
        TableColumn<Yazar, String> colYazarMiddle = new TableColumn<>("İkinci Ad");
        colYazarMiddle.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("middleName"));
        
        TableColumn<Yazar, String> colYazarLast = new TableColumn<>("Soyad");
        colYazarLast.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("lastName"));

        yazarTablo.getColumns().addAll(colYazarId, colYazarTitle, colYazarFirst, colYazarMiddle, colYazarLast);

        YazarDAO yazarDAO = new YazarDAO();
        yazarTablo.getItems().addAll(yazarDAO.tumYazarlariGetir());

        btnYazarEkle.setOnAction(e -> {
            String title = txtYazarTitle.getText();
            String first = txtYazarFirst.getText();
            String middle = txtYazarMiddle.getText();
            String last = txtYazarLast.getText();

            if (first.isEmpty() || last.isEmpty()) {
                lblYazarDurum.setStyle("-fx-text-fill: red;");
                lblYazarDurum.setText("Ad ve Soyad alanları boş bırakılamaz!");
                return;
            }

            Yazar yeniYazar = new Yazar(title, first, middle, last);
            if (yazarDAO.yazarEkle(yeniYazar)) {
                lblYazarDurum.setStyle("-fx-text-fill: green;");
                lblYazarDurum.setText("Yazar başarıyla eklendi!");
                txtYazarTitle.clear(); txtYazarFirst.clear(); txtYazarMiddle.clear(); txtYazarLast.clear();
                yazarTablo.getItems().clear();
                yazarTablo.getItems().addAll(yazarDAO.tumYazarlariGetir());
            } else {
                lblYazarDurum.setStyle("-fx-text-fill: red;");
                lblYazarDurum.setText("Yazar eklenirken bir hata oluştu.");
            }
        });

        HBox yazarLayout = new HBox(20, yazarForm, yazarTablo);
        HBox.setHgrow(yazarTablo, Priority.ALWAYS);
        yazarLayout.setPadding(new Insets(15));
        tabYazar.setContent(yazarLayout);

        // --- 3. SEKME: YAYINEVİ YÖNETİMİ ---
        Tab tabYayinevi = new Tab("Yayınevi Yönetimi");
        tabYayinevi.setClosable(false);

        GridPane yayineviForm = new GridPane();
        yayineviForm.setHgap(10); yayineviForm.setVgap(10);
        yayineviForm.setPadding(new Insets(10));

        yayineviForm.add(new Label("Yayınevi Adı:"), 0, 0);
        TextField txtYayineviAdi = new TextField();
        yayineviForm.add(txtYayineviAdi, 1, 0);

        yayineviForm.add(new Label("Adres:"), 0, 1);
        TextField txtYayineviAdres = new TextField();
        yayineviForm.add(txtYayineviAdres, 1, 1);

        yayineviForm.add(new Label("Web Sitesi:"), 0, 2);
        TextField txtYayineviWeb = new TextField();
        txtYayineviWeb.setPromptText("Örn: www.yayinevi.com");
        yayineviForm.add(txtYayineviWeb, 1, 2);

        Button btnYayineviEkle = new Button("Yayınevi Ekle");
        yayineviForm.add(btnYayineviEkle, 1, 3);

        Label lblYayineviDurum = new Label();
        yayineviForm.add(lblYayineviDurum, 1, 4);

        TableView<Yayinevi> yayineviTablo = new TableView<>();

        TableColumn<Yayinevi, Integer> colYayineviId = new TableColumn<>("ID");
        colYayineviId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));

        TableColumn<Yayinevi, String> colYayineviAdi = new TableColumn<>("Yayınevi Adı");
        colYayineviAdi.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("yayineviAdi"));

        TableColumn<Yayinevi, String> colYayineviAdres = new TableColumn<>("Adres");
        colYayineviAdres.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("adres"));

        TableColumn<Yayinevi, String> colYayineviWeb = new TableColumn<>("Web Sitesi");
        colYayineviWeb.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("webSitesi"));

        yayineviTablo.getColumns().addAll(colYayineviId, colYayineviAdi, colYayineviAdres, colYayineviWeb);

        YayineviDAO yayineviDAO = new YayineviDAO();
        yayineviTablo.getItems().addAll(yayineviDAO.tumYayinevleriniGetir());

        btnYayineviEkle.setOnAction(e -> {
            String adi = txtYayineviAdi.getText();
            String adres = txtYayineviAdres.getText();
            String web = txtYayineviWeb.getText();

            if (adi.isEmpty()) {
                lblYayineviDurum.setStyle("-fx-text-fill: red;");
                lblYayineviDurum.setText("Yayınevi adı boş bırakılamaz!");
                return;
            }

            Yayinevi yeniYayinevi = new Yayinevi(adi, adres, web);
            if (yayineviDAO.yayineviEkle(yeniYayinevi)) {
                lblYayineviDurum.setStyle("-fx-text-fill: green;");
                lblYayineviDurum.setText("Yayınevi başarıyla eklendi!");
                txtYayineviAdi.clear(); txtYayineviAdres.clear(); txtYayineviWeb.clear();
                yayineviTablo.getItems().clear();
                yayineviTablo.getItems().addAll(yayineviDAO.tumYayinevleriniGetir());
            } else {
                lblYayineviDurum.setStyle("-fx-text-fill: red;");
                lblYayineviDurum.setText("Yayınevi eklenirken bir hata oluştu.");
            }
        });

        HBox yayineviLayout = new HBox(20, yayineviForm, yayineviTablo);
        HBox.setHgrow(yayineviTablo, Priority.ALWAYS);
        yayineviLayout.setPadding(new Insets(15));
        tabYayinevi.setContent(yayineviLayout);

        // Sekmeleri ekle
        tabPane.getTabs().addAll(tabKitap, tabYazar, tabYayinevi);

        // Ana Düzenleyici ve Sahne Güncelleme Alanı
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topContainer);
        mainLayout.setCenter(tabPane);

        // SAHNEYİ PENCEREYE GİYDİRME VE GENİŞLETME
        Scene scene = new Scene(mainLayout, 900, 600);
        stage.setScene(scene);
        stage.setWidth(950);
        stage.setHeight(650);
        stage.centerOnScreen(); // Ekranın ortasına taşı
        stage.show();
    }
}