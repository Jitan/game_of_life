package se.jroc.game_of_life;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GOLGUI {

    private final int CANVAS_SIZE;
    private final int CELL_SIZE;
    private GraphicsContext gc;
    private GOLBoard board;

    public GOLGUI(GOLBoard board, Stage primaryStage, int cellSize) {
        this.board = board;
        CANVAS_SIZE = cellSize * board.getSize();
        CELL_SIZE = cellSize;

        Canvas canvas = setUpCanvas(primaryStage);
        gc = canvas.getGraphicsContext2D();
        setUpMouseListener(canvas);
        drawGrid();
    }

    private Canvas setUpCanvas(Stage primaryStage) {
        primaryStage.setTitle("Game of Life");
        primaryStage.setResizable(false);
        Group root = new Group();
        Canvas canvas = new Canvas(CANVAS_SIZE, CANVAS_SIZE);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        gc.setFill(Color.BLACK);
        return canvas;
    }

    private void setUpMouseListener(Canvas canvas) {
        canvas.setOnMouseClicked(event -> {
            int x = getX(event);
            int y = getY(event);
            System.out.println(x + " " + y);

            if (board.isAlive(x, y)) {
                setCellDead(x, y);
            } else {
                setCellAlive(x, y);
            }
        });
    }

    private void updateGUI(int x, int y) {
        drawRectangle(x, y);
        drawGrid();
    }

    public void drawBoard(GOLBoard board) {
        board.getSize();
        drawGrid();
    }

    private void drawGrid() {
        for (int i = 0; i < CANVAS_SIZE; i += CELL_SIZE) {
            gc.strokeLine(i, 0, i, CANVAS_SIZE);
            gc.strokeLine(0, i, CANVAS_SIZE, i);
        }
    }

    private void setCellDead(int x, int y) {
        System.out.println("Cell is dead");
        board.setDead(x, y);
        gc.setFill(Color.WHITE);
        updateGUI(x, y);
    }

    private void setCellAlive(int x, int y) {
        System.out.println("Cell is alive");
        board.setAlive(x, y);
        this.gc.setFill(Color.BLACK);
        updateGUI(x, y);
    }

    private int getY(MouseEvent event) {
        int y = (int) event.getY();
        return y / CELL_SIZE;
    }

    private int getX(MouseEvent event) {
        int x = (int) event.getX();
        return x / CELL_SIZE;
    }

    private void drawRectangle(int xStart, int yStart) {
        this.gc.fillRect(xStart * CELL_SIZE, yStart * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }
}
