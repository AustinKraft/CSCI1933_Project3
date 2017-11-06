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

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, can't find file!");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public int getElement(int row, int col) {
        int result = 0;
        if (row >= 0 && row <= numRows && col >= 0 && col <= numCols) {
            MatrixEntry x;
            if (rowHeads[row] != null){
                x = rowHeads[row];
                if (x.getColumn() == col){
                    result =  x.getData();
                }
                else{
                    while (x.getColumn()!= col && x.getNextColumn() != null) {
                        x = x.getNextColumn();
                    }
                    if (x.getRow() == row && x.getColumn() == col) {
                        result = x.getData();
                    }
                }
            }

        }
        return result;
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
            for (int x= 0; x<this.numRows; x++) {
                MatrixEntry this_helper = this.rowHeads[x];
                MatrixEntry other_helper = otherMat.rowHeads[x];
                if (other_helper != null) {
                    if (this_helper.getColumn() == other_helper.getColumn()) {
                        this_helper.setData(this_helper.getData() - other_helper.getData());
                        this.setElement(this_helper.getRow(), this_helper.getColumn(), this_helper.getData());
                    } else {
                        if (this_helper.getNextColumn() != null && other_helper.getNextColumn() != null) {
                            this_helper.getNextColumn();
                            other_helper.getNextColumn();
                        }
                    }
                }
                if (this_helper != null){
                    // System.out.println(this_helper.getRow() + " " + this_helper.getColumn() + " " + this.getElement(this_helper.getRow(), this_helper.getColumn()));
                }
            }
            return true;
        }
        return false;
    }
}
