package tokyo.ronin.openvpngui.help;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import tokyo.ronin.openvpngui.controllers.MainController;
import tokyo.ronin.openvpngui.console.ThreadRunner;
import tokyo.ronin.openvpngui.storage.ConfigModel;
import tokyo.ronin.openvpngui.storage.LocalStorage;

public class HBoxCell extends HBox {
    Label label = new Label();
    Button buttonCome = new Button();
    Button buttonDelete = new Button();

    static MainController mainController = MainController.getInstance();

    public HBoxCell(String labelText, ConfigModel configModel, MainController mainController) {
        super();

        label.setText(labelText);
        label.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(label, Priority.ALWAYS);

        buttonDelete.setText("Delete");
        buttonDelete.setOnAction(m -> {
            LocalStorage.remove(configModel);
            mainController.reRender();
        });

        if (configModel.isConnected()) {
            setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        buttonCome.setText("Connect");
        buttonCome.setOnAction(m -> {
            if (ThreadRunner.executeSessionStart(configModel)) {
                configModel.setConnected(true);
            }
        });

        this.getChildren()
                .addAll(label, buttonCome, buttonDelete);
    }

    public static ListView<HBoxCell> build() {
        List<ConfigModel> configModels = LocalStorage.get();
        List<HBoxCell> list = new ArrayList<>();
        for (ConfigModel configModel : configModels) {
            String fieldName = configModel.getFile() != null ? configModel.getFile()
                    .getName() : "";

            HBoxCell cell = new HBoxCell(fieldName, configModel, mainController);
            list.add(cell);
        }

        ListView<HBoxCell> listView = new ListView<>();
        ObservableList<HBoxCell> myObservableList = FXCollections.observableList(list);
        listView.setItems(myObservableList);
        return listView;
    }
}