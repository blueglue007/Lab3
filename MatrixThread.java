import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MatrixThread {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error. Please Enter correct path.");
            System.exit(1);
        }
        String file = args[0];
        Path p = Paths.get(file);
        Scanner fileIn = null;

        try {
            fileIn = new Scanner(p);
        } catch (Exception ex) {
            System.out.print("File does not exist");
            System.exit(1);
        }

        int Row = fileIn.nextInt();
        int Col = fileIn.nextInt();
        int[][] A = new int[Row][Col];
        int[][] B = new int[Row][Col];
        int[][] C = new int[Row][Col];

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


        int miniMatrixRowLength = Row/2;
        int miniMatrixColLength = Col/2;
        if (Row % 2 != 0) {
            miniMatrixRowLength ++;
        }
        if (Col % 2 != 0) {
            miniMatrixColLength ++;
        }
        int[][] A00 = new int[miniMatrixRowLength][miniMatrixColLength];
        int[][] A01 = new int[miniMatrixRowLength][miniMatrixColLength];
        int[][] A10 = new int[miniMatrixRowLength][miniMatrixColLength];
        int[][] A11 = new int[miniMatrixRowLength][miniMatrixColLength];
        int[][] B00 = new int[miniMatrixRowLength][miniMatrixColLength];
        int[][] B01 = new int[miniMatrixRowLength][miniMatrixColLength];
        int[][] B10 = new int[miniMatrixRowLength][miniMatrixColLength];
        int[][] B11 = new int[miniMatrixRowLength][miniMatrixColLength];

        for (int r = 0; r < miniMatrixRowLength; r++) {
            for (int c = 0; c < miniMatrixColLength; c++) {
                A00[r][c] = A[r][c];
                A01[r][c] = A[r][c+miniMatrixColLength];
                A10[r][c] = A[r+miniMatrixRowLength][c];
                A11[r][c] = A[r+miniMatrixRowLength][c+miniMatrixColLength];
                B00[r][c] = B[r][c];
                B01[r][c] = B[r][c+miniMatrixColLength];
                B10[r][c] = B[r+miniMatrixRowLength][c];
                B11[r][c] = B[r+miniMatrixRowLength][c+miniMatrixColLength];
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

        try{
            NW.join();
            NE.join();
            SW.join();
            SE.join();
        }catch(InterruptedException e){
            System.out.println("Interrupted");
        }

        int[][] C00 = NW.getResult();
        int[][] C01 = NE.getResult();
        int[][] C10 = SW.getResult();
        int[][] C11 = SE.getResult();


        for (int r = 0; r < miniMatrixRowLength; r++) {
            for (int c = 0; c < miniMatrixColLength; c++) {
                C[r][c] = C00[r][c];
                C[r][c+miniMatrixColLength] = C01[r][c];
                C[r+miniMatrixRowLength][c] = C10[r][c];
                C[r+miniMatrixRowLength][c+miniMatrixColLength] = C11[r][c];
            }
        }

        printMatrix(C);
    }
    
    private static void printMatrix(int[][] matrix) {
        for(int r = 0; r < matrix.length; r++) {
            for(int c = 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
    }
}
