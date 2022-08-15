package se.jroc.game_of_life;

import java.util.Arrays;

public class GOLBoard {
    static final int STATE_ALIVE = 1;
    static final int STATE_DEAD = 0;
    private int[][] board;

    public GOLBoard(int size) {
        board = new int[size][size];
        setAllCellsToState(STATE_DEAD);
    }

    private void setAllCellsToState(int state) {
        for (int[] ints : board) {
            Arrays.fill(ints, state);
        }
    }


    public int getSize() {
        return board.length;
    }
    private int maxIndex() {
        return board.length - 1;
    }

    public int getCellState(int x, int y) {
        return board[x][y];
    }

    public void randomize() {
        for (int x = 0; x < getSize(); x++) {
            for (int y = 0; y < getSize(); y++) {
                if (Math.random() > 0.5) {
                    setAlive(x, y);
                } else {
                    setDead(x, y);
                }
            }
        }
    }

    public int countLiveNeighbourCells(int x, int y) {
        int count = 0;
        
        for (int xModifier = -1; xModifier <= 1; xModifier++) {
            for (int yModifier = -1; yModifier <= 1; yModifier++) {
                if (xModifier == 0 && yModifier == 0) {
                    continue;
                }
                if (isAlive(x + xModifier, y + yModifier)) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isEmpty() {
        for (int[] ints : board) {
            for (int anInt : ints) {
                if (anInt == STATE_ALIVE) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setAlive(int x, int y) throws IllegalArgumentException {
        if (outsideTheBoard(x, y)) {
            throw new IllegalArgumentException("x and y must be between 0 and " + maxIndex());
        }
        board[x][y] = STATE_ALIVE;
    }

    public boolean isAlive(int x, int y) {
        if (outsideTheBoard(x, y)) {
            return false;
        }
        return board[x][y] == STATE_ALIVE;
    }

    public void setDead(int x, int y) {
        if (outsideTheBoard(x, y)) {
            throw new IllegalArgumentException("x and y must be between 0 and " + maxIndex());
        }
        board[x][y] = STATE_DEAD;
    }

    public boolean isDead(int x, int y) {
        if (outsideTheBoard(x, y)) {
            return false;
        }
        return board[x][y] == STATE_DEAD;
    }

    private boolean outsideTheBoard(int x, int y) {
        return x < 0 || x > maxIndex() || y < 0 || y > maxIndex();
    }
    public void update() {
    }
}
