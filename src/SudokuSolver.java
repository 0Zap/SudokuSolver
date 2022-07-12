import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SudokuSolver {

    public static void main(String[] args) {
//
        SudokuBoard board = new SudokuBoard(9);

        SudokuSolver solver = new SudokuSolver();
//        ArrayList<Integer> tester = solver.getBlock(8, board);
//
//        boolean testy = solver.isValidMove(11, 6, board);

        System.out.println(solver.solveV2(board));
        System.out.println(solver.getCellValue(80,board));
        System.out.println(Arrays.deepToString(board.getBoard()).replace("],", "],\n"));
    }


    private boolean solve(SudokuBoard board) {

        for (int cellIndex = 0; cellIndex < 80; cellIndex++) {
            for (int numberAttempt = 1; numberAttempt <= 9; numberAttempt++) {
                if (isValidMove(cellIndex, numberAttempt, board)) {
                    board.setCellValue(cellIndex, numberAttempt);

                    if (solve(board)) {
                        return true;
                    } else {
                        board.setCellValue(cellIndex, 0);
                    }
                }
            }
        }
        return true;
    }

    private boolean solveV2(SudokuBoard board){
        for (int everyCellIndex = 0; everyCellIndex <= 80 ; everyCellIndex++) {
            if (getCellValue(everyCellIndex,board) == 0){
                System.out.println("Cell: " + everyCellIndex + " is empty.");

                for (int numberAttempt = 1; numberAttempt <= 9; numberAttempt++) {
                    if (isValidMove(everyCellIndex,numberAttempt,board)){
                        System.out.println("Valid move found at index: " + everyCellIndex + " with value: " + numberAttempt);
                        board.setCellValue(everyCellIndex,numberAttempt);

                        if (solveV2(board)){
                            return true;
                        } else{
                            System.out.println("Setting cell:" + everyCellIndex + " back to 0");
                            board.setCellValue(everyCellIndex,0);
                        }
                    }
                }
                System.out.println("Could not find a valid number for cell:" + everyCellIndex);
                return false;
            }

        }
        return true;
    }


    private boolean isValidMove(int cellIndex, int cellValue, SudokuBoard board) {
        ArrayList<Integer> columnValues = getColumn(cellIndex, board);
        ArrayList<Integer> rowValues = getRow(cellIndex, board);
        ArrayList<Integer> blockValues = getBlock(cellIndex, board);
        ArrayList<Integer> emptySpots = board.getOpenSpotsIndices();

        return (!blockValues.contains(cellValue) && !rowValues.contains(cellValue) && !columnValues.contains(cellValue) && emptySpots.contains(cellIndex));

    }


    /**
     * @param cellIndex refers to the selected cell which the column of will be retrieved
     * @param board     refers to the board in which the cell is in
     * @return an ArrayList containing all values from the cells in this column
     */
    private ArrayList<Integer> getColumn(int cellIndex, SudokuBoard board) {

        // Retrieving which column the given cell is in
        HashMap<Integer, ArrayList<Integer>> indexInfo = board.getIndexInfo();
        ArrayList<Integer> rowColInfoGivenIndex = indexInfo.get(cellIndex);
        int columnNumber = rowColInfoGivenIndex.get(1);

        // Filling information about the entire column
        ArrayList<Integer> cellColumnCollection = new ArrayList<>();
        for (int row = 0; row <= 8; row++) {
            cellColumnCollection.add(getCellValue(row, columnNumber, board));
        }

        return cellColumnCollection;
    }

    /**
     * @param cellIndex refers to the selected cell which the row of will be retrieved
     * @param board     refers to the board in which the cell is in
     * @return an ArrayList containing all values from the cells in this row
     */
    private ArrayList<Integer> getRow(int cellIndex, SudokuBoard board) {

        // Retrieving which row the given cell is in
        HashMap<Integer, ArrayList<Integer>> indexInfo = board.getIndexInfo();
        ArrayList<Integer> rowColInfoGivenIndex = indexInfo.get(cellIndex);
        int rowNumber = rowColInfoGivenIndex.get(0);

        // Filling information about the entire row
        ArrayList<Integer> cellRowCollection = new ArrayList<>();
        for (int col = 0; col <= 8; col++) {
            cellRowCollection.add(getCellValue(rowNumber, col, board));
        }

        return cellRowCollection;
    }

    /**
     * @param cellIndex refers to the selected cell which the block it is in will be retrieved
     * @param board     refers to the board which the cell lies in
     * @return a list of all cell values in a block, dependent of which cellIndex has been selected
     */
    private ArrayList<Integer> getBlock(int cellIndex, SudokuBoard board) {

        ArrayList<Integer> blockValues = new ArrayList<>();

        // Retrieving which row and column the given cell is in
        HashMap<Integer, ArrayList<Integer>> indexInfo = board.getIndexInfo();
        ArrayList<Integer> rowColInfoGivenIndex = indexInfo.get(cellIndex);
        int rowNumber = rowColInfoGivenIndex.get(0);
        int colNumber = rowColInfoGivenIndex.get(1);

        // Blocks 1-3
        if (rowNumber >= 0 && rowNumber <= 2) {

            if (colNumber >= 0 && colNumber <= 2) {
                // col[0-2] row[0-2] block 1
                for (int row = 0; row <= 2; row++) {
                    for (int col = 0; col <= 2; col++) {
                        blockValues.add(getCellValue(row, col, board));
                    }
                }
            }

            if (colNumber >= 3 && colNumber <= 5) {
                // col[3-5] row[0-2] block 2

                for (int row = 0; row <= 2; row++) {
                    for (int col = 3; col <= 5; col++) {
                        blockValues.add(getCellValue(row, col, board));
                    }
                }
            }

            if (colNumber >= 6 && colNumber <= 8) {
                // col[6-8] row[0-2] block 3

                for (int row = 0; row <= 2; row++) {
                    for (int col = 6; col <= 8; col++) {
                        blockValues.add(getCellValue(row, col, board));
                    }
                }
            }

        }

        // Blocks 4-6
        if (rowNumber >= 3 && rowNumber <= 5) {

            if (colNumber >= 0 && colNumber <= 2) {
                // col[0-2] row[3-5] block 4
                for (int row = 3; row <= 5; row++) {
                    for (int col = 0; col <= 2; col++) {
                        blockValues.add(getCellValue(row, col, board));
                    }
                }
            }

            if (colNumber >= 3 && colNumber <= 5) {
                // col[3-5] row[3-5] block 5
                for (int row = 3; row <= 5; row++) {
                    for (int col = 3; col <= 5; col++) {
                        blockValues.add(getCellValue(row, col, board));
                    }
                }
            }

            if (colNumber >= 6 && colNumber <= 8) {
                // col[6-8] row[3-5] block 6
                for (int row = 3; row <= 5; row++) {
                    for (int col = 6; col <= 8; col++) {
                        blockValues.add(getCellValue(row, col, board));
                    }
                }
            }

        }

        // Blocks 7-9
        if (rowNumber >= 6 && rowNumber <= 8) {

            if (colNumber >= 0 && colNumber <= 2) {
                // col[0-2] row[6-8] block 7
                for (int row = 6; row <= 8; row++) {
                    for (int col = 0; col <= 2; col++) {
                        blockValues.add(getCellValue(row, col, board));
                    }
                }
            }

            if (colNumber >= 3 && colNumber <= 5) {
                // col[3-5] row[6-8] block 8
                for (int row = 6; row <= 8; row++) {
                    for (int col = 3; col <= 5; col++) {
                        blockValues.add(getCellValue(row, col, board));
                    }
                }
            }

            if (colNumber >= 6 && colNumber <= 8) {
                // col[6-8] row[6-8] block 9
                for (int row = 6; row <= 8; row++) {
                    for (int col = 6; col <= 8; col++) {
                        blockValues.add(getCellValue(row, col, board));
                    }
                }
            }


        }


        return blockValues;
    }


    /**
     * @param row   refers to the row the cell is in
     * @param col   refers to the column to cell is in
     * @param board refers to the board we are inspecting (int[][] board)
     * @return the value that the selected cell holds
     */
    private int getCellValue(int row, int col, SudokuBoard board) {
        return board.getBoard()[row][col];
    }

    private int getCellValue(int index, SudokuBoard board) {
        HashMap<Integer, ArrayList<Integer>> info = board.getIndexInfo();
        ArrayList<Integer> rowColInfo = info.get(index);
        return board.getBoard()[rowColInfo.get(0)][rowColInfo.get(1)];
    }


}
