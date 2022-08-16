package se.jroc.game_of_life;

import java.util.Arrays;

public class GOLBoard {
    static final int STATE_ALIVE = 1;
    static final int STATE_DEAD = 0;
    private final int[][] board;

    public GOLBoard(int size) {
        board = new int[size][size];
        setAllCellsToState(STATE_DEAD);
    }

    public GOLBoard tick() {
        GOLBoard newBoard = new GOLBoard(board.length);
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                int aliveNeighbours = countLiveNeighbourCells(x, y);
                if (isAlive(x, y) && (aliveNeighbours == 2 || aliveNeighbours == 3)) {
                    newBoard.setAlive(x, y);
                } else if(isDead(x,y) && aliveNeighbours == 3) {
                    newBoard.setAlive(x, y);
                } else {
                    newBoard.setDead(x, y);
                }
            }
        }
        return newBoard;
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

    public void setAlive(int x, int y) {
        if (insideTheBoard(x, y)) {
            board[x][y] = STATE_ALIVE;
        }
    }

    public boolean isAlive(int x, int y) {
        if (insideTheBoard(x, y)) {
            return board[x][y] == STATE_ALIVE;
        } else {
            return false;
        }
    }

    public void setDead(int x, int y){
        if (insideTheBoard(x, y)) {
            board[x][y] = STATE_DEAD;
        }
    }

    public boolean isDead(int x, int y) {
        if (insideTheBoard(x, y)) {
            return board[x][y] == STATE_DEAD;
        } else {
            return true;
        }
    }

    public boolean isEmpty() {
        for (int x = 0; x < getSize(); x++) {
            for (int y = 0; y < getSize(); y++) {
                if (isAlive(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getSize() {
        return board.length;
    }

    private int countLiveNeighbourCells(int x, int y) {
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

    private void setAllCellsToState(int state) {
        for (int[] ints : board) {
            Arrays.fill(ints, state);
        }
    }

    private int maxIndex() {
        return board.length - 1;
    }

    private boolean insideTheBoard(int x, int y) {
        return x >= 0 && x <= maxIndex() && y >= 0 && y <= maxIndex();
    }
}
