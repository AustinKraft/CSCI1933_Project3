public class MatrixEntry {
    private int row;
    private int col;
    private int data;
    public MatrixEntry(int row, int col, int data){
        this.row = row;
        this.col = col;
        this.data = data;

    }
    public int getColumn(){
        return col;
    }
    public void setColumn(int col){
        this.col = col;
    }
    public int getRow(){
        return row;
    }
    public void setRow(int col){
        this.row = row;
    }
    public int getData(){
        return data;
    }
    public void setData(int data){
        this.data = data;
    }
    public void setNextColumn(MatrixEntry el){
        el.setRow(this.row+1);
        el.setColumn(this.col);
    }
    public MatrixEntry getNextRow(){
        MatrixEntry next = new MatrixEntry(this.row, this.col+1, this.data);
        return next;
    }
    public MatrixEntry getNextColumn(){
        MatrixEntry next = new MatrixEntry(this.row + 1, this.col, this.data);
        return next;
    }
    public void setNextRow(MatrixEntry el){
        el.setRow(this.row);
        el.setColumn(this.col+1);
    }

}
