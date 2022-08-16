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

    @Test
    void itShouldRandomizeTheBoard() {
        GOLBoard golBoard = new GOLBoard(30);
        golBoard.randomize();
        assertFalse(golBoard.isEmpty());
    }

    @Test
    void itShouldReturnANewBoardWithCorrectlyUpdatedCells() {
        GOLBoard golBoard = new GOLBoard(30);
        golBoard.setAlive(0, 1);
        golBoard.setAlive(1, 1);
        golBoard.setAlive(2, 1);
        GOLBoard newBoard = golBoard.tick();
        assertTrue(newBoard.isAlive(1, 0));
        assertTrue(newBoard.isAlive(1, 1));
        assertTrue(newBoard.isAlive(1, 2));
        newBoard = newBoard.tick();
        assertTrue(newBoard.isAlive(0, 1));
        assertTrue(newBoard.isAlive(1, 1));
        assertTrue(newBoard.isAlive(2, 1));
        newBoard = newBoard.tick();
        assertTrue(newBoard.isAlive(1, 0));
        assertTrue(newBoard.isAlive(1, 1));
        assertTrue(newBoard.isAlive(1, 2));
    }

    @Test
    void itShouldCountLiveNeighbourCellsWhenThereIsNoNeighbour() {
        GOLBoard golBoard = new GOLBoard(30);
        assertEquals(golBoard.countLiveNeighbourCells(1, 1), 0);
    }

    @Test
    void itShouldCountLiveNeighbourCellsWhenThereIsOneNeighbour() {
        GOLBoard golBoard = new GOLBoard(30);
        golBoard.setAlive(0, 0);
        assertEquals(golBoard.countLiveNeighbourCells(1, 1), 1);
    }

    @Test
    void itShouldCountLiveNeighbourCellsWhenThereIsTwoNeighbours() {
        GOLBoard golBoard = new GOLBoard(30);
        golBoard.setAlive(0, 0);
        golBoard.setAlive(1, 0);
        assertEquals(golBoard.countLiveNeighbourCells(1, 1), 2);
    }

    @Test
    void itShouldCountLiveNeighbourCellsWhenThereIsThreeNeighbours() {
        GOLBoard golBoard = new GOLBoard(30);
        golBoard.setAlive(0, 0);
        golBoard.setAlive(1, 0);
        golBoard.setAlive(2, 0);
        assertEquals(golBoard.countLiveNeighbourCells(1, 1), 3);
    }

    @Test
    void itShouldCountLiveNeighbourCellsWhenThereIsFourNeighbours() {
        GOLBoard golBoard = new GOLBoard(30);
        golBoard.setAlive(0, 0);
        golBoard.setAlive(1, 0);
        golBoard.setAlive(2, 0);
        golBoard.setAlive(0, 1);
        assertEquals(golBoard.countLiveNeighbourCells(1, 1), 4);
    }

    @Test
    void itShouldCountLiveNeighbourCellsWhenThereIsFiveNeighbours() {
        GOLBoard golBoard = new GOLBoard(30);
        golBoard.setAlive(0, 0);
        golBoard.setAlive(1, 0);
        golBoard.setAlive(2, 0);
        golBoard.setAlive(0, 1);
        golBoard.setAlive(2, 1);
        assertEquals(golBoard.countLiveNeighbourCells(1, 1), 5);
    }

    @Test
    void itShouldCountLiveNeighbourCellsWhenThereIsSixNeighbours() {
        GOLBoard golBoard = new GOLBoard(30);
        golBoard.setAlive(0, 0);
        golBoard.setAlive(1, 0);
        golBoard.setAlive(2, 0);
        golBoard.setAlive(0, 1);
        golBoard.setAlive(2, 1);
        golBoard.setAlive(0, 2);
        assertEquals(golBoard.countLiveNeighbourCells(1, 1), 6);
    }

    @Test
    void itShouldCountLiveNeighbourCellsWhenThereIsSevenNeighbours() {
        GOLBoard golBoard = new GOLBoard(30);
        golBoard.setAlive(0, 0);
        golBoard.setAlive(1, 0);
        golBoard.setAlive(2, 0);
        golBoard.setAlive(0, 1);
        golBoard.setAlive(2, 1);
        golBoard.setAlive(0, 2);
        golBoard.setAlive(1, 2);
        assertEquals(golBoard.countLiveNeighbourCells(1, 1), 7);
    }

    @Test
    void itShouldCountLiveNeighbourCellsWhenThereIsEightNeighbours() {
        GOLBoard golBoard = new GOLBoard(30);
        golBoard.setAlive(0, 0);
        golBoard.setAlive(1, 0);
        golBoard.setAlive(2, 0);
        golBoard.setAlive(0, 1);
        golBoard.setAlive(2, 1);
        golBoard.setAlive(0, 2);
        golBoard.setAlive(1, 2);
        golBoard.setAlive(2, 2);
        assertEquals(golBoard.countLiveNeighbourCells(1, 1), 8);
    }
}