/** This file gives classes and folders access to each other*/
module com.example.muhammad_chambers_c195_pa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.muhammad.chambers.c195.pa to javafx.fxml;
    exports com.example.muhammad.chambers.c195.pa;
    exports com.example.muhammad.chambers.c195.pa.controller;
    opens com.example.muhammad.chambers.c195.pa.controller to javafx.fxml;
    exports com.example.muhammad.chambers.c195.pa.model;
    opens com.example.muhammad.chambers.c195.pa.model to javafx.fxml;
}