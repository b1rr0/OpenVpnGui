module tokyo.ronin.openvpngui {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens tokyo.ronin.openvpngui to javafx.fxml;
    exports tokyo.ronin.openvpngui;
    exports tokyo.ronin.openvpngui.storage;
    exports tokyo.ronin.openvpngui.controllers;
    opens tokyo.ronin.openvpngui.controllers to javafx.fxml;
}