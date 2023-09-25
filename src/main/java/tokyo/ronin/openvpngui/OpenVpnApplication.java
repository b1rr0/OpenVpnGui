package tokyo.ronin.openvpngui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpenVpnApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OpenVpnApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Open3Vpn!");
        stage.setScene(scene);
        stage.minWidthProperty().set(675);
        stage.minHeightProperty().set(450);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}