import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MatrixThread {
    public static void main(String[] args) {
        //check file path
        if (args.length == 0) {
            System.out.println("Error. Please Enter correct path.");
            System.exit(1);
        }
        //read file
        String file = args[0];
        Path p = Paths.get(file);
        Scanner fileIn = null;
        try {
            fileIn = new Scanner(p);
        } catch (Exception ex) {
            System.out.print("File does not exist");
            System.exit(1);
        }

        //create A and B matrix
        int Row = fileIn.nextInt();
        int Col = fileIn.nextInt();
        int[][] A = new int[Row][Col];
        int[][] B = new int[Row][Col];
        try {
            for (int r = 0; r < Row; r++) {
                for (int c = 0; c < Col; c++) {
                    A[r][c] = fileIn.nextInt();
                }
            }
            for (int r = 0; r < Row; r++) {
                for (int c = 0; c < Col; c++) {
                    B[r][c] = fileIn.nextInt();
                }
            }
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println("OUT OF BOUNDS");
            System.exit(1);
        }

        //
        int rowIsAdd = 0;
        int colIsAdd = 0;
        if (Row % 2 != 0) {
            rowIsAdd = 1;
        }
        if (Col % 2 != 0) {
            colIsAdd = 1;
        }
        int miniMatrixRowLength = Row/2 + rowIsAdd;
        int miniMatrixColLength = Col/2 + colIsAdd;

        //create 4 mini matrix for A and B matrix
        int[][] A00 = new int[miniMatrixRowLength][miniMatrixColLength];
        int[][] A01 = new int[miniMatrixRowLength][miniMatrixColLength];
        int[][] A10 = new int[miniMatrixRowLength][miniMatrixColLength];
        int[][] A11 = new int[miniMatrixRowLength][miniMatrixColLength];
        int[][] B00 = new int[miniMatrixRowLength][miniMatrixColLength];
        int[][] B01 = new int[miniMatrixRowLength][miniMatrixColLength];
        int[][] B10 = new int[miniMatrixRowLength][miniMatrixColLength];
        int[][] B11 = new int[miniMatrixRowLength][miniMatrixColLength];


        //put A and B data to mini matrix
        for (int r = 0; r < miniMatrixRowLength; r++) {
            for (int c = 0; c < miniMatrixColLength; c++) {
                A00[r][c] = A[r][c];
                A01[r][c] = A[r][c+miniMatrixColLength-colIsAdd];
                A10[r][c] = A[r+miniMatrixRowLength-rowIsAdd][c];
                A11[r][c] = A[r+miniMatrixRowLength-rowIsAdd][c+miniMatrixColLength-colIsAdd];
                B00[r][c] = B[r][c];
                B01[r][c] = B[r][c+miniMatrixColLength-colIsAdd];
                B10[r][c] = B[r+miniMatrixRowLength-rowIsAdd][c];
                B11[r][c] = B[r+miniMatrixRowLength-rowIsAdd][c+miniMatrixColLength-colIsAdd];
            }
        }


        addMatrix NW = new addMatrix(A00, B00);
        addMatrix NE = new addMatrix(A01, B01);
        addMatrix SW = new addMatrix(A10, B10);
        addMatrix SE = new addMatrix(A11, B11);
        NW.start();
        NE.start();
        SW.start();
        SE.start();

        //wait for thread.
        try{
            NW.join();
            NE.join();
            SW.join();
            SE.join();
        }catch(InterruptedException e){
            System.out.println("Interrupted");
        }


        //create mini matrix and read data.
        int[][] C00 = NW.getResult();
        int[][] C01 = NE.getResult();
        int[][] C10 = SW.getResult();
        int[][] C11 = SE.getResult();

        //create C matrix and read data from mini Matrix
        int[][] C = new int[Row][Col];
        for (int r = 0; r < miniMatrixRowLength; r++) {
            for (int c = 0; c < miniMatrixColLength; c++) {
                C[r][c] = C00[r][c];
                C[r][c+miniMatrixColLength-colIsAdd] = C01[r][c];
                C[r+miniMatrixRowLength-rowIsAdd][c] = C10[r][c];
                C[r+miniMatrixRowLength-rowIsAdd][c+miniMatrixColLength-colIsAdd] = C11[r][c];
            }
        }

        printMatrix(C);
    }


    //
    private static void printMatrix(int[][] matrix) {
        for(int r = 0; r < matrix.length; r++) {
            for(int c = 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
    }
}
