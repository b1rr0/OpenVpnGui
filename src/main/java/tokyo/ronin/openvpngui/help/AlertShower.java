package tokyo.ronin.openvpngui.help;

import javafx.scene.control.Alert;
import tokyo.ronin.openvpngui.console.ConsoleCommands;

public class AlertShower {
    public static void showList() {
        showAlert("Open3Vpn statuses", String.join("", ConsoleCommands.activeSessions()));
    }

    public static void check() {
        showAlert("Open3Vpn version", ConsoleCommands.version());
    }

    private static void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane()
                .setPrefSize(700, 600);
        alert.setTitle("Open3Vpn");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
