package se.jroc.game_of_life;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GOLController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}