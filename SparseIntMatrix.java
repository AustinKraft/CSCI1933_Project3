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
    private int numRows;
    private int numCols;
    private String inputFile;
    private MatrixEntry[] rowHeads;
    //private MatrixEntry[] colHeads;

    public SparseIntMatrix(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        rowHeads = new MatrixEntry[numRows];
        //colHeads = new MatrixEntry[numCols];
    }

    public SparseIntMatrix(int numRows, int numCols, String inputFile) {
        this.numRows = numRows;
        this.numCols = numCols;
        rowHeads = new MatrixEntry[numRows];
        //colHeads = new MatrixEntry[numCols];
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
                MatrixEntry temp = new MatrixEntry(row, col, data);

                if (rowHeads[row]!=null){
                    if (col<rowHeads[row].getColumn()){
                        temp.setNextColumn(rowHeads[row]);
                        rowHeads[row]=temp;
                    }
                    else if (col>rowHeads[row].getColumn()){
                        MatrixEntry helper = rowHeads[row];
                        while (helper.getNextColumn()!= null && helper.getNextColumn().getColumn()<col){
                            helper = helper.getNextColumn();
                        }
                        helper.setNextColumn(temp);
                    }
                }
                else{
                    rowHeads[row] = temp;

                }/* We don't really need colHeads
                if (colHeads[col]!=null){
                    if (col<colHeads[col].getRow()){
                        temp.setNextColumn(colHeads[col]);
                        colHeads[col]=temp;
                    }
                    else{
                        colHeads[col].setNextRow(temp);
                    }
                }
                else{
                    colHeads[col] = temp;

                }*/
            }
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, can't find file!");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public int getElement(int row, int col) {
        MatrixEntry x;
        if (row >= 0 && row <= numRows && col >= 0 && col <= numCols) {
            if (rowHeads[row]!=null){
                x = rowHeads[row];
                if (x.getColumn() == col){
                    return x.getData();
                }
                else{
                    while (x.getNextColumn()!= null && x.getColumn()!= col) {
                        x = x.getNextColumn();
                    }
                    return x.getData();
                }
            }
        }
        return 0;

    }

    public boolean setElement(int row, int col, int data) {
        MatrixEntry entryToSet = new MatrixEntry(row, col, data);
        if (rowHeads[row]!=null){
            if (col<rowHeads[row].getColumn()){
                entryToSet.setNextColumn(rowHeads[row]);
                rowHeads[row]= entryToSet;
                return true;
            }
            else if (col>rowHeads[row].getColumn()){
                MatrixEntry helper = rowHeads[row];
                while (helper.getNextColumn()!= null && helper.getNextColumn().getColumn()<col){
                    helper = helper.getNextColumn();
                }
                helper.setNextColumn(entryToSet);
                return true;
            }
        }
        else{
            rowHeads[row] = entryToSet;
            return true;

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
