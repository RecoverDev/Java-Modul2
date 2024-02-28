package ru.specialist.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebView;

public class BrowserController {

    @FXML
    private Button btnGo;

    @FXML
    private TextArea address;

    @FXML
    private WebView view;

    @FXML
    void redirect(ActionEvent event) {
        view.getEngine().load(address.getText());
    }

}
