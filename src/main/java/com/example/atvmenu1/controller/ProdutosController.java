package com.example.atvmenu1.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProdutosController {
    public static class HelloController {
        @FXML
        private Label welcomeText;

        @FXML
        protected void onHelloButtonClick() {
            welcomeText.setText("Welcome to JavaFX Application!");
        }
    }

}
