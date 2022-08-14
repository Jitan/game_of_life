package se.jroc.game_of_life;

import java.util.Arrays;

public class GOLBoard {
    static final int STATE_ALIVE = 1;
    static final int STATE_DEAD = 0;
    private int[][] board;

    public GOLBoard(int size) {
        board = new int[size][size];
        
        // loop through board and set all cells to dead
        for (int[] ints : board) {
            Arrays.fill(ints, STATE_DEAD);
        }
    }

    public int getSize() {
        return board.length;
    }

    public int getCellState(int i, int j) {
        return board[i][j];
    }

    public void randomize() {
        for (int[] ints : board) {
            Arrays.fill(ints, (int) (Math.random() * 2));
        }
    }

    public int countNeighbourCells(int i, int i1) {
        int count = 0;
        for (int j = i - 1; j <= i + 1; j++) {
            for (int k = i1 - 1; k <= i1 + 1; k++) {
                if (j >= 0 && j < board.length && k >= 0 && k < board.length) {
                    if (board[j][k] == STATE_ALIVE) {
                        count++;
                    }
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
}
