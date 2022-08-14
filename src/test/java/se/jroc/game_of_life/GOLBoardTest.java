package se.jroc.game_of_life;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GOLBoardTest {

    @Test
    void itShouldInitializeTheBoardWithCorrectSize() {
        GOLBoard golBoard = new GOLBoard(30);
        assertEquals(golBoard.getSize(), 30);
    }

    @Test
    void itShouldCheckThatANewBoardIsEmpty() {
        GOLBoard golBoard = new GOLBoard(30);
        assertTrue(golBoard.isEmpty());
    }

    @Test
    void itShouldSetAllFieldsToDeadAtStart() {
        GOLBoard golBoard = new GOLBoard(30);
        for (int i = 0; i < golBoard.getSize(); i++) {
            for (int j = 0; j < golBoard.getSize(); j++) {
                assertEquals(golBoard.getCellState(i, j), GOLBoard.STATE_DEAD);
            }
        }
    }
    @Test
    void itShouldRandomizeTheGameBoard() {
        GOLBoard golBoard = new GOLBoard(30);
        golBoard.randomize();
    }

    @Test
    void itShouldCountTheNeighbourCells() {
        GOLBoard golBoard = new GOLBoard(30);
        golBoard.randomize();
        assertEquals(golBoard.countNeighbourCells(0, 0), 3);
    }
}