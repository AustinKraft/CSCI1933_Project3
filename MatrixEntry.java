public class MatrixEntry {
    private int row;
    private int col;
    private int data;
    private MatrixEntry nextRow;
    private MatrixEntry nextColumn;

    public MatrixEntry(int row, int col, int data){
        this.row = row;
        this.col = col;
        this.data = data;
        nextRow = null;
        nextColumn = null;

    }
    public int getColumn(){
        return this.col;
    }
    public void setColumn(int col){
        this.col = col;
    }
    public int getRow(){
        return this.row;
    }
    public void setRow(int col){
        this.row = row;
    }
    public int getData(){
        return this.data;
    }
    public void setData(int data){
        this.data = data;
    }
    public void setNextColumn(MatrixEntry el){
        nextColumn = el;
        }
    public MatrixEntry getNextRow(){
        return nextRow;
    }
    public MatrixEntry getNextColumn(){
        return nextColumn;
    }
    public void setNextRow(MatrixEntry el){
        nextRow = el;
    }


}
