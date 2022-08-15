package se.jroc.game_of_life.gui;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import se.jroc.game_of_life.GOLBoard;

public class GOLGUI {

    private final int CANVAS_SIZE;
    private final int CELL_SIZE;
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final GUICallbackHandler guiCallbackHandler;
    private GOLBoard board;

    public GOLGUI(Stage primaryStage,
                  GUICallbackHandler guiCallbackHandler,
                  int cellSize,
                  GOLBoard board) {
        this.guiCallbackHandler = guiCallbackHandler;
        this.board = board;
        this.CANVAS_SIZE = cellSize * board.getSize();
        this.CELL_SIZE = cellSize;
        this.canvas = new Canvas(CANVAS_SIZE, CANVAS_SIZE);
        this.gc = canvas.getGraphicsContext2D();
        setUpStage(primaryStage);
        drawGrid();
    }

    private void setUpStage(Stage primaryStage) {
        gc.setFill(Color.BLACK);
        primaryStage.setTitle("Game of Life");
        primaryStage.setResizable(false);
        Button playButton = new Button("Play");
        Button stopButton = new Button("Stop");
        Button randomizeButton = new Button("Randomize");

        VBox vbox = new VBox();
        vbox.setSpacing(20);
        vbox.getChildren().add(canvas);
        HBox hbox = new HBox();
        hbox.getChildren().add(playButton);
        hbox.getChildren().add(stopButton);
        hbox.getChildren().add(randomizeButton);
        vbox.getChildren().add(hbox);
        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();

        playButton.setOnAction(actionEvent -> guiCallbackHandler.playButtonClicked());
        stopButton.setOnAction(actionEvent -> guiCallbackHandler.stopButtonClicked());
        randomizeButton.setOnAction(actionEvent -> guiCallbackHandler.randomizeButtonClicked());
        canvas.setOnMouseClicked(
                event -> guiCallbackHandler.locationClicked(getX(event), getY(event)));
    }

    public void setNewBoard(GOLBoard board) {
        this.board = board;
        drawBoard();
    }

    protected void drawBoard() {
        for (int x = 0; x < board.getSize(); x++) {
            for (int y = 0; y < board.getSize(); y++) {
                if (board.isAlive(x, y)) {
                    setCellAlive(x, y);
                } else {
                    setCellDead(x, y);
                }
            }
        }
        drawGrid();
    }

    private void setCellAlive(int x, int y) {
        board.setAlive(x, y);
        this.gc.setFill(Color.BLACK);
        drawRectangle(x, y);
    }

    private void setCellDead(int x, int y) {
        board.setDead(x, y);
        gc.setFill(Color.WHITE);
        drawRectangle(x, y);
    }

    private void drawRectangle(int xStart, int yStart) {
        this.gc.fillRect(xStart * CELL_SIZE, yStart * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    private void drawGrid() {
        for (int i = 0; i < CANVAS_SIZE; i += CELL_SIZE) {
            gc.strokeLine(i, 0, i, CANVAS_SIZE);
            gc.strokeLine(0, i, CANVAS_SIZE, i);
        }
    }

    private int getY(MouseEvent event) {
        int y = (int) event.getY();
        return y / CELL_SIZE;
    }

    private int getX(MouseEvent event) {
        int x = (int) event.getX();
        return x / CELL_SIZE;
    }
}
