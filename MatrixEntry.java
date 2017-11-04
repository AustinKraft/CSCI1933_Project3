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
<<<<<<< HEAD
<<<<<<< HEAD
        nextColumn = el;

=======
        nextColumn.setRow(el.getRow());
        nextColumn.setColumn(el.getColumn());
        nextColumn.setData(el.getData());
>>>>>>> parent of 5690ff9... setnext methods
=======
        if (el.getColumn() == this.col+1) {
            nextColumn = el;
        }
>>>>>>> parent of d256794... Junk
    }
    public MatrixEntry getNextRow(){
        return nextRow;
    }
    public MatrixEntry getNextColumn(){
        return nextColumn;
    }
    public void setNextRow(MatrixEntry el){
<<<<<<< HEAD
<<<<<<< HEAD
        nextRow = el;
=======
        nextRow.setRow(el.getRow());
        nextRow.setColumn(el.getColumn());
        nextRow.setData(el.getData());
>>>>>>> parent of 5690ff9... setnext methods
=======
        if (el.getRow() == this.row + 1){
            nextRow = el;
        }
>>>>>>> parent of d256794... Junk
    }

    public static void main(String[] args) {
        MatrixEntry el = new MatrixEntry(1,1,10);
        MatrixEntry el1 = new MatrixEntry(1,2, 11);
        el.setNextColumn(el1);
        MatrixEntry el2 = new MatrixEntry(2,1,12);
        el.setNextRow(el2);

        System.out.println(el.getNextRow().getData());
        System.out.println(el.getNextColumn().getData());
    }

}
