package se.jroc.game_of_life;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class GOLApplication extends Application {

    private static final int CELL_SIZE = 20;
    private static final int BOARD_SIZE = 640;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game of Life");
        Group root = new Group();
        Canvas canvas = new Canvas(BOARD_SIZE, BOARD_SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        gc.setFill(Color.BLACK);


        //draw a grid
        for (int i = 0; i < BOARD_SIZE; i += CELL_SIZE) {
            gc.strokeLine(i, 0, i, BOARD_SIZE);
            gc.strokeLine(0, i, BOARD_SIZE, i);
        }
        
        // detect mouseclick inside grid
        canvas.setOnMouseClicked(event -> {
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (x > 0 && x < BOARD_SIZE && y > 0 && y < BOARD_SIZE) {
                int xStart = x / CELL_SIZE;
                int yStart = y / CELL_SIZE;
                gc.fillRect(xStart * CELL_SIZE, yStart * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        });

        GOLBoard golBoard = new GOLBoard(BOARD_SIZE / CELL_SIZE);


    }
    
    public static void main(String[] args) {
        launch();
    }
}