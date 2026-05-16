package com.mycompany.otomasyon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
 DatabaseHelper.createTables();
 // TEST: Veritabanına kitap eklemeyi deniyoruz
        KitapDAO kitapDao = new KitapDAO();
        Kitap yeniKitap = new Kitap("Nutuk", "Mustafa Kemal Atatürk", 600, "Mevcut");
        
        if(kitapDao.kitapEkle(yeniKitap)) {
            System.out.println(">> BAŞARIYLA KİTAP EKLENDİ <<");
        }
        
        // TEST: Eklenen kitapları veritabanından çekip ekrana yazdırıyoruz
        System.out.println("--- Kütüphanedeki Kitaplar ---");
        for (Kitap k : kitapDao.tumKitaplariGetir()) {
            System.out.println("ID: " + k.getId() + " | Kitap: " + k.getKitapAdi() + " | Yazar: " + k.getYazar());
        }
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}