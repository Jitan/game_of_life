package se.jroc.game_of_life;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class GOLApplication extends Application {
    private final int CELL_SIZE = 20;
    private final int BOARD_SIZE = 32;
    private GOLBoard board;
    private GOLGUI gui;

    @Override
    public void start(Stage primaryStage) {
        board = new GOLBoard(BOARD_SIZE);
        setUpTimer();
        gui = new GOLGUI(board, primaryStage, CELL_SIZE);
    }

    private void setUpTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, 1000, 1000);
    }

    private void update() {
        board.update();
        gui.drawBoard(board);
    }
    public static void main(String[] args) {
        launch();
    }
}