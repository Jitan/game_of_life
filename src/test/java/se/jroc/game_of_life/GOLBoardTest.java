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
        assertFalse(golBoard.isDead(1, 1));
    }

    @Test
    void itShouldSetCellToDead() {
        GOLBoard golBoard = new GOLBoard(30);
        golBoard.setDead(1, 1);
        assertTrue(golBoard.isDead(1, 1));
        assertFalse(golBoard.isAlive(1, 1));
    }

    @Test
    void itShouldCreateAtLeastOneAliveCell() {
        GOLBoard golBoard = new GOLBoard(30);
        assertTrue(golBoard.isEmpty());
        golBoard.randomize();
        assertFalse(golBoard.isEmpty());
    }

    @Test
    void itShouldReturnANewBoardWithCorrectlyUpdatedCells() {
        GOLBoard golBoard = new GOLBoard(30);
        golBoard.setAlive(0, 1);
        golBoard.setAlive(1, 1);
        golBoard.setAlive(2, 1);
        GOLBoard newBoard = golBoard.applyEvolutionRulesAndGenerateNewBoard();
        assertTrue(newBoard.isAlive(1, 0));
        assertTrue(newBoard.isAlive(1, 1));
        assertTrue(newBoard.isAlive(1, 2));
        newBoard = newBoard.applyEvolutionRulesAndGenerateNewBoard();
        assertTrue(newBoard.isAlive(0, 1));
        assertTrue(newBoard.isAlive(1, 1));
        assertTrue(newBoard.isAlive(2, 1));
        newBoard = newBoard.applyEvolutionRulesAndGenerateNewBoard();
        assertTrue(newBoard.isAlive(1, 0));
        assertTrue(newBoard.isAlive(1, 1));
        assertTrue(newBoard.isAlive(1, 2));
    }
}