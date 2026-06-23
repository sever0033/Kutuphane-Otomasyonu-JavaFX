
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
        // İleride buraya kitap tablosunu ve butonlarını yerleştireceğiz
        tabKitap.setContent(vboxKitap);

        // --- 2. SEKME: YAZAR YÖNETİMİ ---
        Tab tabYazar = new Tab("Yazar Yönetimi");
        tabYazar.setClosable(false);
        VBox vboxYazar = new VBox(10);
        vboxYazar.setPadding(new Insets(20));
        vboxYazar.getChildren().add(new Label("Yazar Ekleme ve Yönetim Alanı"));
        tabYazar.setContent(vboxYazar);

        // --- 3. SEKME: YAYINEVİ YÖNETİMİ ---
        Tab tabYayinevi = new Tab("Yayınevi Yönetimi");
        tabYayinevi.setClosable(false);
        VBox vboxYayinevi = new VBox(10);
        vboxYayinevi.setPadding(new Insets(20));
        vboxYayinevi.getChildren().add(new Label("Yayınevi Ekleme ve Yönetim Alanı"));
        tabYayinevi.setContent(vboxYayinevi);

        // Sekmeleri panele ekle
        tabPane.getTabs().addAll(tabKitap, tabYazar, tabYayinevi);

        // Ana Düzenleyici (BorderPane)
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topContainer);
        mainLayout.setCenter(tabPane);

        Scene scene = new Scene(mainLayout, 900, 600);
        stage.setScene(scene);
        stage.show();
    }
}