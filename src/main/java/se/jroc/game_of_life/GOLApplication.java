package se.jroc.game_of_life;

import javafx.application.Application;
import javafx.stage.Stage;
import se.jroc.game_of_life.gui.GOLGUI;
import se.jroc.game_of_life.gui.GUICallback;

import java.util.Timer;
import java.util.TimerTask;

public class GOLApplication extends Application {
    private final int CELL_SIZE = 15;
    private final int BOARD_SIZE = 40;
    private final int TICK_PERIOD = 80;
    private GOLBoard board;
    private GOLGUI gui;
    private Timer timer;

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage) {
        board = new GOLBoard(BOARD_SIZE);
        board.randomize();
        GUICallback guiCallbackHandler = createCallbacks();
        gui = new GOLGUI(primaryStage,
                guiCallbackHandler,
                CELL_SIZE,
                board);
        startTicks();
//        System.out.println("Board Size: " + board.getSize());
//        System.out.println("Cell Size: " + CELL_SIZE);
    }

    private void startTicks() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, 500, TICK_PERIOD);
    }

    private void stopTicks() {
        timer.cancel();
    }

    private void update() {
        board = board.applyEvolutionRulesAndGenerateNewBoard();
        gui.setNewBoard(board);
    }

    private GUICallback createCallbacks() {
        return new GUICallback() {
            @Override
            public void locationClicked(int x, int y) {
//                System.out.println("Click @ X:" + x + " / Y:" + y);
                if (board.isAlive(x, y)) {
                    board.setDead(x, y);
                } else {
                    board.setAlive(x, y);
                }
                update();
            }

            @Override
            public void playButtonClicked() {
                startTicks();
            }

            @Override
            public void stopButtonClicked() {
                stopTicks();
            }

            @Override
            public void randomizeButtonClicked() {
                board.randomize();
                update();
            }
        };
    }
}