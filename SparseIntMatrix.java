/*
Answers to PartIV:
a.
b.
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.StringTokenizer;

public class SparseIntMatrix {
    private MatrixEntry firstEntry;
    private int numRows;
    private int numCols;
    private String inputFile;
    private MatrixEntry[] rowHeads;
    private MatrixEntry[] colHeads;

    public SparseIntMatrix(int numRows, int numCols){
        this.numRows = numRows;
        this.numCols = numCols;
        rowHeads = new MatrixEntry[numRows];
        colHeads = new MatrixEntry[numCols];
    }

    public SparseIntMatrix(int numRows, int numCols, String inputFile){
        this.numRows = numRows;
        this.numCols = numCols;
        rowHeads = new MatrixEntry[numRows];
        colHeads = new MatrixEntry[numCols];
        this.inputFile = inputFile;
        try {
            File f = new File(inputFile);
            Scanner fileInput = new Scanner(f);
            while (fileInput.hasNext()) {
                String input = fileInput.nextLine();
                String[] matrix = input.split(",");
                int row = Integer.valueOf(matrix[0]);
                int col = Integer.valueOf(matrix[1]);
                int data = Integer.valueOf(matrix[2]);
                rowHeads[row]= new MatrixEntry(row,col,data);
                colHeads[col] = new MatrixEntry(row,col,data);

            }
        }
        catch(FileNotFoundException e){
            System.out.println("Sorry, can't find file!");
            System.out.println(e.getMessage());
            System.exit(1);
        }

        int row_counter = 0;
        while(row_counter< numRows-1){
            rowHeads[row_counter].setNextRow(rowHeads[row_counter+1]);
            row_counter++;
        }
        int col_counter = 0;
        while(col_counter< numRows-1){
            colHeads[col_counter].setNextColumn(colHeads[col_counter+1]);
            col_counter++;
        }
    }

    public int getElement(int row, int col) {
        MatrixEntry x;
        if (row >= 0 && row <= numRows && col >= 0 && col <= numCols){
            if (rowHeads[row] != null) {
                x = rowHeads[row];
                for (int i = 0; i < numCols; i++) {
                    if (x.getColumn() == col) {
                        return x.getData(); //should we be returning the integer data at that point, or 1: I think it's the integer data at that point
                    }
                    x = x.getNextColumn();
                }
            }
        }
        return 0;
    }

    public boolean setElement(int row, int col, int data){
        MatrixEntry x;
        if (row >= 0 && row <= numRows && col >= 0 && col <= numCols){
            if (rowHeads[row] != null) {
                x = rowHeads[row];
                for (int i = 0; i < numCols; i++) {
                    if (x.getColumn() == col) {
                        x.setData(data);
                        return true;
                    }
                    x = x.getNextColumn();
                }
            }
        }
        return false;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getNumRows() {
        return numRows;
    }

    public boolean plus(SparseIntMatrix otherMat){
        if ((this.numRows == otherMat.numRows) && (this.numCols == otherMat.numCols)){
            for (int x = 0; x< numRows; x++){
                for (int y = 0; y<numCols; y++){
                    this.setElement(x,y,(this.getElement(x,y)+otherMat.getElement(x,y)));
                }
            }
            return true;
        }
        return false;
    }

    public boolean minus(SparseIntMatrix otherMat){
        if ((this.numRows == otherMat.numRows) && (this.numCols == otherMat.numCols)){
            for (int x = 0; x< numRows; x++){
                for (int y = 0; y<numCols; y++){
                    this.setElement(x,y,(this.getElement(x,y)-otherMat.getElement(x,y)));
                }
            }
            return true;
        }
        return false;
    }
}
