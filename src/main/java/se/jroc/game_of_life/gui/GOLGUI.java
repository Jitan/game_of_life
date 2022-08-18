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
    private final GraphicsContext graphicsContext;
    private final GUICallback guiCallback;
    private GOLBoard board;

    public GOLGUI(Stage primaryStage,
                  GUICallback guiCallback,
                  int cellSize,
                  GOLBoard board) {
        this.guiCallback = guiCallback;
        this.board = board;
        this.CANVAS_SIZE = cellSize * board.getSize();
        this.CELL_SIZE = cellSize;
        this.canvas = new Canvas(CANVAS_SIZE, CANVAS_SIZE);
        this.graphicsContext = canvas.getGraphicsContext2D();
        setUpStage(primaryStage);
        drawGrid();
    }

    private void setUpStage(Stage primaryStage) {
        graphicsContext.setFill(Color.BLACK);
        primaryStage.setTitle("Game of Life");
        primaryStage.setResizable(false);
        Button playButton = new Button("Play");
        Button stopButton = new Button("Stop");
        Button randomizeButton = new Button("Randomize");

        VBox verticalBox = new VBox();
        verticalBox.setSpacing(20);
        verticalBox.getChildren().add(canvas);
        HBox horizontalBox = new HBox();
        horizontalBox.getChildren().add(playButton);
        horizontalBox.getChildren().add(stopButton);
        horizontalBox.getChildren().add(randomizeButton);
        verticalBox.getChildren().add(horizontalBox);
        Scene scene = new Scene(verticalBox);

        primaryStage.setScene(scene);
        primaryStage.show();

        playButton.setOnAction(actionEvent -> guiCallback.playButtonClicked());
        stopButton.setOnAction(actionEvent -> guiCallback.stopButtonClicked());
        randomizeButton.setOnAction(actionEvent -> guiCallback.randomizeButtonClicked());
        canvas.setOnMouseClicked(
                event -> guiCallback.locationClicked(getX(event), getY(event)));
    }

    public void setNewBoard(GOLBoard board) {
        this.board = board;
        drawBoard();
    }

    private void drawBoard() {
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
        graphicsContext.setFill(Color.BLACK);
        drawRectangle(x, y);
    }

    private void setCellDead(int x, int y) {
        board.setDead(x, y);
        graphicsContext.setFill(Color.WHITE);
        drawRectangle(x, y);
    }

    private void drawRectangle(int xStart, int yStart) {
        graphicsContext.fillRect(xStart * CELL_SIZE, yStart * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    private void drawGrid() {
        for (int i = 0; i < CANVAS_SIZE; i += CELL_SIZE) {
            graphicsContext.strokeLine(i, 0, i, CANVAS_SIZE);
            graphicsContext.strokeLine(0, i, CANVAS_SIZE, i);
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
