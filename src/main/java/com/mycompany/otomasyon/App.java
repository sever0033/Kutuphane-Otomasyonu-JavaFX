package com.mycompany.otomasyon;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class App extends Application {

    private KullaniciDAO kullaniciDAO = new KullaniciDAO();

    @Override
    public void start(Stage primaryStage) {
        // Veritabanı tablolarını sıfırla ve oluştur
        DatabaseHelper.createTables();
        
        // Varsayılan admin kullanıcısını ekle
        Kullanici defaultAdmin = new Kullanici("admin", "1234", "Çiçek Sever", "05551112233", "cicek@tarsus.edu.tr");
        kullaniciDAO.kullaniciEkle(defaultAdmin);

        primaryStage.setTitle("Kütüphane Otomasyonu - Giriş");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label baslikLabel = new Label("Sistem Girişi");
        baslikLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        grid.add(baslikLabel, 0, 0, 2, 1);

        Label userLabel = new Label("Kullanıcı Adı:");
        grid.add(userLabel, 0, 1);

        TextField userTextField = new TextField();
        userTextField.setPromptText("Kullanıcı adınızı giriniz");
        grid.add(userTextField, 1, 1);

        Label pwLabel = new Label("Şifre:");
        grid.add(pwLabel, 0, 2);

        PasswordField pwBox = new PasswordField();
        pwBox.setPromptText("Şifrenizi giriniz");
        grid.add(pwBox, 1, 2);

        Button loginBtn = new Button("Giriş Yap");
        grid.add(loginBtn, 1, 4);

        Label hataLabel = new Label();
        hataLabel.setStyle("-fx-text-fill: red;");
        grid.add(hataLabel, 1, 5);

        // --- GİRİŞ BUTONU AKSİYONU ---
        loginBtn.setOnAction(e -> {
            String kAdi = userTextField.getText();
            String sifre = pwBox.getText();

            if (kullaniciDAO.girisYap(kAdi, sifre)) {
                System.out.println("Sisteme giriş yapıldı: " + kAdi);
                
                // KESİN ÇÖZÜM: Pencereyi kapatmıyoruz, sadece içeriğini MainFrame ile değiştiriyoruz
                MainFrame anaEkran = new MainFrame();
                anaEkran.showMenu(primaryStage);
                
            } else {
                hataLabel.setStyle("-fx-text-fill: red;");
                hataLabel.setText("Hatalı Kullanıcı Adı veya Şifre!");
            }
        });

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}