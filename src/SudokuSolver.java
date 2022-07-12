import java.util.ArrayList;
import java.util.HashMap;

public class SudokuSolver {

    public static void main(String[] args) {
//
        SudokuBoard board = new SudokuBoard(9);

        SudokuSolver solver = new SudokuSolver();
        ArrayList<Integer> tester = solver.getColumn(8,board);

        System.out.println(tester);
    }


    /**
     *
     * @param cellIndex refers to the selected cell which the column of will be retrieved
     * @param board refers to the board in which the cell is in
     * @return an ArrayList containing all values from the cells in this column
     */
    private ArrayList<Integer> getColumn(int cellIndex,SudokuBoard board){
        HashMap<Integer, ArrayList<Integer>> indexInfo = board.getIndexInfo();

        ArrayList<Integer> rowColInfoGivenIndex = indexInfo.get(cellIndex);
        int columnNumber = rowColInfoGivenIndex.get(1);

        ArrayList<Integer> cellColumnCollection = new ArrayList<>();

        for (int row = 0; row <= 8; row++) {
            cellColumnCollection.add(getCellValue(row,columnNumber,board));
        }

        return cellColumnCollection;
    }


    /**
     *
     * @param row refers to the row the cell is in
     * @param col refers to the column to cell is in
     * @param board refers to the board we are inspecting (int[][] board)
     * @return the value that the selected cell holds
     */
    private int getCellValue(int row, int col,SudokuBoard board){
        return board.getBoard()[row][col];
    }

}
