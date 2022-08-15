package se.jroc.game_of_life;

import javafx.application.Application;
import javafx.stage.Stage;
import se.jroc.game_of_life.gui.GOLGUI;
import se.jroc.game_of_life.gui.GUICallbackHandler;

import java.util.Timer;
import java.util.TimerTask;

public class GOLApplication extends Application {
    private final int CELL_SIZE = 20;
    private final int BOARD_SIZE = 32;
    private GOLBoard board;
    private GOLGUI gui;
    private Timer timer;

    @Override
    public void start(Stage primaryStage) {
        board = new GOLBoard(BOARD_SIZE);
        board.randomize();

        GUICallbackHandler guiCallbackHandler = new GUICallbackHandler() {
            @Override
            public void locationClicked(int x, int y) {
                System.out.println("Click @ X:" + x + " / Y:" + y);
            }
            @Override
            public void playButton() {
                startTicks();
            }

            @Override
            public void stopButton() {
                stopTicks();
            }
        };


        gui = new GOLGUI(primaryStage,
                guiCallbackHandler,
                CELL_SIZE,
                board);
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
        gui.setNewBoard(board);
    }
    public static void main(String[] args) {
        launch();
    }
}