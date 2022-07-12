import java.util.ArrayList;
import java.util.HashMap;

public class SudokuBoard {
    private int[][] board;
    HashMap<Integer, ArrayList<Integer>> indexInfo;

    public SudokuBoard(int info) {
        this.board = generateBoard(info);
        this.indexInfo = generateIndexInfo();
    }

    public int[][] getBoard() {
        return board;
    }

    public HashMap<Integer, ArrayList<Integer>> getIndexInfo() {
        return indexInfo;
    }

    private HashMap<Integer, ArrayList<Integer>> generateIndexInfo(){

        HashMap<Integer, ArrayList<Integer>> indexInfo = new HashMap<>();
        // Index = row * 9 + col

        // There's 8 rows
        for (int row = 0; row <= 8; row++) {

            // There's 8 columns
            for (int col = 0; col <= 8; col++) {

                ArrayList<Integer> rowColInfo = new ArrayList<>();
                rowColInfo.add(0,row);
                rowColInfo.add(1,col);
                indexInfo.put((row * 9 + col),rowColInfo);
            }

        }

        return indexInfo;
    }

    private int[][] generateBoard(int info) {

        int[][] board = {
                {0, 0, 0, 0, 0, 3, 8, 4, 6},
                {9, 4, 0, 0, 0, 0, 7, 0, 3},
                {6, 2, 3, 8, 0, 0, 0, 0, 0},
                {0, 8, 2, 7, 6, 0, 9, 0, 4},
                {0, 3, 9, 0, 8, 0, 1, 0, 0},
                {4, 0, 0, 3, 9, 1, 2, 0, 0},
                {3, 0, 0, 0, 5, 2, 6, 9, 8},
                {0, 9, 4, 0, 0, 8, 0, 5, 0},
                {0, 6, 0, 0, 0, 9, 4, 7, 0}
        };
        return board;
    }
}
