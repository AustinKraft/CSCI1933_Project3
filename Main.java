public class Main {

    public static void main(String[] args) {
        SparseIntMatrix mat1 = new SparseIntMatrix(1000,1000,"matrix1_data.txt");
        MatrixViewer.show(mat1);

        SparseIntMatrix mat2 = new SparseIntMatrix(1000,1000,"matrix2_data.txt");
        MatrixViewer.show(mat2);
        
        SparseIntMatrix mat2_noise = new SparseIntMatrix(1000,1000,"matrix2_noise.txt");
        mat2.minus(mat2_noise);
        MatrixViewer.show(mat2);

    }
}
