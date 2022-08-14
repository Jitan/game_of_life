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
}
