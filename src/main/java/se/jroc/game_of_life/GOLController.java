package se.jroc.game_of_life;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GOLController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Game of Life");
    }

    public void createBoard() {
        GOLBoard golBoard = new GOLBoard(30);
    }
}