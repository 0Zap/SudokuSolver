import java.util.ArrayList;
import java.util.HashMap;

public class SudokuBoard {
    private int[][] board;
    private ArrayList<Integer> openSpotsIndices;
    HashMap<Integer, ArrayList<Integer>> indexInfo;

    public SudokuBoard(int info) {
        this.board = generateBoard(info);
        this.indexInfo = generateIndexInfo();
        this.openSpotsIndices = getOpenSpots();
    }

    public int[][] getBoard() {
        return board;
    }

    public ArrayList<Integer> getOpenSpotsIndices() {
        return openSpotsIndices;
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

    private ArrayList<Integer> getOpenSpots(){
        ArrayList<Integer> emptySpots = new ArrayList<>();

        for (int row = 0; row <= 8 ; row++) {
            for (int col = 0; col <=8 ; col++) {
                int value = this.board[row][col];
                if (value == 0){
                    emptySpots.add((row * 9 + col));
                }

            }
        }
        return emptySpots;
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

    public void setCellValue(int index, int value){
        ArrayList<Integer> rowColInfo = indexInfo.get(index);
        int row = rowColInfo.get(0);
        int col = rowColInfo.get(1);

        this.board[row][col] = value;
    }
}
