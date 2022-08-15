package se.jroc.game_of_life;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GOLGUI {

    private final int CANVAS_SIZE;
    private final int CELL_SIZE;
    private GraphicsContext gc;
    private final EventHandler<ActionEvent> playHandler;
    private final EventHandler<ActionEvent> stopHandler;
    private GOLBoard board;

    public GOLGUI(Stage primaryStage, EventHandler<ActionEvent> playHandler,
                  EventHandler<ActionEvent> stopHandler, int cellSize, GOLBoard board) {
        this.playHandler = playHandler;
        this.stopHandler = stopHandler;
        this.board = board;
        this.CANVAS_SIZE = cellSize * board.getSize();
        this.CELL_SIZE = cellSize;

        Canvas canvas = setUpCanvas(primaryStage);
        setUpMouseListener(canvas);
        drawGrid();
    }

    private Canvas setUpCanvas(Stage primaryStage) {
        primaryStage.setTitle("Game of Life");
        primaryStage.setResizable(false);
        Canvas canvas = new Canvas(CANVAS_SIZE, CANVAS_SIZE);
        gc = canvas.getGraphicsContext2D();

        VBox vbox = new VBox();
        vbox.setSpacing(20);

        Button playButton = new Button("Play");
        Button stopButton = new Button("Stop");

        vbox.getChildren().add(canvas);

        HBox hbox = new HBox();
        hbox.getChildren().add(playButton);
        hbox.getChildren().add(stopButton);

        playButton.setOnAction(playHandler);
        stopButton.setOnAction(stopHandler);

        vbox.getChildren().add(hbox);

        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();

        gc.setFill(Color.BLACK);

        return canvas;
    }



    private void setUpMouseListener(Canvas canvas) {
        canvas.setOnMouseClicked(event -> {
            int x = getX(event);
            int y = getY(event);
            System.out.println("Click @ x " + x + "  y " + y);

            if (board.isAlive(x, y)) {
                setCellDead(x, y);
            } else {
                setCellAlive(x, y);
            }
        });
    }

    public void drawBoard(GOLBoard board) {
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
        updateGUI(x, y);
    }

    private void setCellDead(int x, int y) {
        board.setDead(x, y);
        gc.setFill(Color.WHITE);
        updateGUI(x, y);
    }

    private void updateGUI(int x, int y) {
        drawRectangle(x, y);
        drawGrid();
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
