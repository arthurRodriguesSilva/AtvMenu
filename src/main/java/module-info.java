 module com.example.atvmenu1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires mysql.connector.j;
    requires java.sql;


     opens com.example.atvmenu1 to javafx.fxml;
     opens com.example.atvmenu1.controller to javafx.fxml;
     opens com.example.atvmenu1.model to javafx.base;

    exports com.example.atvmenu1;
}