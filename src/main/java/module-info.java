 module com.example.atvmenu1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.atvmenu1.controller to javafx.fxml;
    exports com.example.atvmenu1;
}