package tokyo.ronin.openvpngui.controllers;

import java.io.File;
import java.util.List;

import javafx.scene.input.DragEvent;
import tokyo.ronin.openvpngui.storage.ConfigModel;
import tokyo.ronin.openvpngui.storage.LocalStorage;

public class CredentialsController {
    private static final MainController main = MainController.getInstance();
    private static File file;

    public static void addFileToStorageAndCloseCred() {
        ConfigModel configModel = new ConfigModel().setFile(file)
                .setLogin(main.getLogin().getText())
                .setPassword(main.getPass().getText());

        LocalStorage.add(configModel);
        cleanCredentials();
        main.closeCredentials();
    }

    private static void cleanCredentials() {
        file = null;
        main.getFileLabelName().setText("");
        main.getLogin().setText("");
        main.getPass().setText("");
    }

    public static void handleFile(DragEvent dragEvent) {
        List<File> list = dragEvent.getDragboard().getFiles();
        if (list.size() != 1) {
            return;
        }
        file = list.get(0);
        main.getFileLabelName()
                .setText("Settings File:" + System.lineSeparator() + file.getName());
    }
}
