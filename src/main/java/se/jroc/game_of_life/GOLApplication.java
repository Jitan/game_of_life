package se.jroc.game_of_life;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class GOLApplication extends Application {
    private final int CELL_SIZE = 50;
    private final int BOARD_SIZE = 3;
    private GOLBoard board;
    private GOLGUI gui;

    @Override
    public void start(Stage primaryStage) {
        board = new GOLBoard(BOARD_SIZE);
        setUpTimer();
        gui = new GOLGUI(primaryStage, CELL_SIZE, board);
        System.out.println(board.getSize());
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
        int aliveCells = board.countLiveNeighbourCells(1, 1);
        System.out.println(aliveCells);
        board.update();
        gui.drawBoard(board);
    }
    public static void main(String[] args) {
        launch();
    }
}