package com.example.atvmenu1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                HelloApplication.class.getResource("/fxml/MainLayout.fxml")
        );

        Scene scene = new Scene(loader.load(), 800, 600);
        stage.setTitle("Sistema com Menu Fixo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
