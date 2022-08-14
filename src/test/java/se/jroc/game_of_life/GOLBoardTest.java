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
    void itShouldSetCellToAlive() {
        GOLBoard golBoard = new GOLBoard(30);
        golBoard.setAlive(1, 1);
        assertTrue(golBoard.isAlive(1, 1));
    }

    @Test
    void itShouldSetCellToDead() {
        GOLBoard golBoard = new GOLBoard(30);
        golBoard.setDead(1, 1);
        assertTrue(golBoard.isDead(1, 1));
    }

}