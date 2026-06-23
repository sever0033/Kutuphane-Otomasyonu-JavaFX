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
        VBox vboxKitap = new VBox(10);
        vboxKitap.setPadding(new Insets(20));
        vboxKitap.getChildren().add(new Label("Kitap Ekleme, Listeleme ve Güncelleme Alanı"));
        tabKitap.setContent(vboxKitap);

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