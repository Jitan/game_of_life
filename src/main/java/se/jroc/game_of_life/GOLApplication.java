package se.jroc.game_of_life;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class GOLApplication extends Application {
    private final int CELL_SIZE = 20;
    private final int BOARD_SIZE = 32;
    private GOLBoard board;
    private GOLGUI gui;
    private EventHandler<ActionEvent> playHandler;
    private EventHandler<ActionEvent> stopHandler;
    private Timer timer;

    @Override
    public void start(Stage primaryStage) {
        board = new GOLBoard(BOARD_SIZE);
        board.randomize();

        playHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                startTicks();
            }
        };

        stopHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stopTicks();
            }
        };

        gui = new GOLGUI(primaryStage, playHandler, stopHandler, CELL_SIZE, board);
        startTicks();
        System.out.println("Board Size: " + board.getSize());
        System.out.println("Cell Size: " + CELL_SIZE);
    }

    private void stopTicks() {
        timer.cancel();
    }

    private void startTicks() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, 1000, 1000);
    }

    private void update() {
        board = board.tick();
        gui.drawBoard(board);
    }
    public static void main(String[] args) {
        launch();
    }
}