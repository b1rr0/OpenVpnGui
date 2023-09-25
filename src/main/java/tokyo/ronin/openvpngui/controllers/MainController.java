package tokyo.ronin.openvpngui.controllers;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import tokyo.ronin.openvpngui.help.AlertShower;
import tokyo.ronin.openvpngui.help.HBoxCell;
import tokyo.ronin.openvpngui.console.ConsoleCommands;


public class MainController implements Initializable {

    @FXML
    private Button openCredButton;
    @FXML
    private Button checkButton;

    @FXML
    private TextField login;

    @FXML
    private TextField pass;

    @FXML
    private Label fileLabelName;

    @FXML
    private AnchorPane dragPane;

    @FXML
    private AnchorPane paneWithCredentials;

    @FXML
    private Button addCredButton;

    @FXML
    private Button backSettingButton;

    @FXML
    private BorderPane board;

    @FXML
    private Button connectionsButton;

    @FXML
    private Button stopAll;

    public MainController() {
        if (instance == null) instance = this;
    }

    static MainController instance;

    public static MainController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkButton.setOnAction(action -> AlertShower.check());
        connectionsButton.setOnAction(event -> AlertShower.showList());
        openCredButton.setOnAction(event -> openCredentials());
        backSettingButton.setOnAction(actionEvent -> closeCredentials());
        stopAll.setOnAction(event1 -> stopAll());
        addCredButton.setOnAction(event -> CredentialsController.addFileToStorageAndCloseCred());
        dragPane.setOnDragEntered(CredentialsController::handleFile);
        reRender();
    }

    public void openCredentials() {
        paneWithCredentials.visibleProperty()
                .set(true);
    }

    public void closeCredentials() {
        paneWithCredentials.visibleProperty()
                .set(false);
        reRender();
    }

    public void reRender() {
        ListView<HBoxCell> listView = HBoxCell.build();
        board.setCenter(listView);
    }

    private static void stopAll() {
        ConsoleCommands.stopSessions(ConsoleCommands.activeSessions());
        AlertShower.showList();
    }

    public Button getOpenCredButton() {
        return openCredButton;
    }

    public MainController setOpenCredButton(Button openCredButton) {
        this.openCredButton = openCredButton;
        return this;
    }

    public Button getCheckButton() {
        return checkButton;
    }

    public MainController setCheckButton(Button checkButton) {
        this.checkButton = checkButton;
        return this;
    }

    public TextField getLogin() {
        return login;
    }

    public MainController setLogin(TextField login) {
        this.login = login;
        return this;
    }

    public TextField getPass() {
        return pass;
    }

    public MainController setPass(TextField pass) {
        this.pass = pass;
        return this;
    }

    public Label getFileLabelName() {
        return fileLabelName;
    }

    public MainController setFileLabelName(Label fileLabelName) {
        this.fileLabelName = fileLabelName;
        return this;
    }

    public AnchorPane getDragPane() {
        return dragPane;
    }

    public MainController setDragPane(AnchorPane dragPane) {
        this.dragPane = dragPane;
        return this;
    }

    public AnchorPane getPaneWithCredentials() {
        return paneWithCredentials;
    }

    public MainController setPaneWithCredentials(AnchorPane paneWithCredentials) {
        this.paneWithCredentials = paneWithCredentials;
        return this;
    }

    public Button getAddCredButton() {
        return addCredButton;
    }

    public MainController setAddCredButton(Button addCredButton) {
        this.addCredButton = addCredButton;
        return this;
    }

    public Button getBackSettingButton() {
        return backSettingButton;
    }

    public MainController setBackSettingButton(Button backSettingButton) {
        this.backSettingButton = backSettingButton;
        return this;
    }

    public BorderPane getBoard() {
        return board;
    }

    public MainController setBoard(BorderPane board) {
        this.board = board;
        return this;
    }

    public Button getConnectionsButton() {
        return connectionsButton;
    }

    public MainController setConnectionsButton(Button connectionsButton) {
        this.connectionsButton = connectionsButton;
        return this;
    }

    public Button getStopAll() {
        return stopAll;
    }

    public MainController setStopAll(Button stopAll) {
        this.stopAll = stopAll;
        return this;
    }

    public static void setInstance(MainController instance) {
        MainController.instance = instance;
    }

//
}