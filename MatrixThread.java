
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


        int miniMetrixRowLenght = Row/2;
        int miniMetrixColLenght = Col/2;
        if (Row % 2 != 0) {
            miniMetrixRowLenght ++;
        }
        if (Col % 2 != 0) {
            miniMetrixColLenght ++;
        }
        int[][] ANW = new int[miniMetrixRowLenght][miniMetrixColLenght];
        int[][] ANE = new int[miniMetrixRowLenght][miniMetrixColLenght];
        int[][] ASW = new int[miniMetrixRowLenght][miniMetrixColLenght];
        int[][] ASE = new int[miniMetrixRowLenght][miniMetrixColLenght];
        int[][] BNW = new int[miniMetrixRowLenght][miniMetrixColLenght];
        int[][] BNE = new int[miniMetrixRowLenght][miniMetrixColLenght];
        int[][] BSW = new int[miniMetrixRowLenght][miniMetrixColLenght];
        int[][] BSE = new int[miniMetrixRowLenght][miniMetrixColLenght];

        for (int r = 0; r < miniMetrixRowLenght; r++) {
            for (int c = 0; c < miniMetrixColLenght; c++) {
                ANW[r][c] = A[r][c];
                ANE[r][c] = A[r][c+miniMetrixColLenght];
                ASW[r][c] = A[r+miniMetrixRowLenght][c];
                ASE[r][c] = A[r+miniMetrixRowLenght][c+miniMetrixColLenght];
                BNW[r][c] = B[r][c];
                BNE[r][c] = B[r][c+miniMetrixColLenght];
                BSW[r][c] = B[r+miniMetrixRowLenght][c];
                BSE[r][c] = B[r+miniMetrixRowLenght][c+miniMetrixColLenght];
            }
        }







        addMatrix NE = new addMatrix(ANE, BNE);
        addMatrix NW = new addMatrix(ANW, BNW);
        addMatrix SE = new addMatrix(ASE, BSE);
        addMatrix SW = new addMatrix(ASW, BSW);
        NE.start();
        NW.start();
        SE.start();
        SW.start();

        int[][] CNW = NW.getResult();
        int[][] CNE = NE.getResult();
        int[][] CSW = SW.getResult();
        int[][] CSE = SE.getResult();



        for (int r = 0; r < miniMetrixRowLenght; r++) {
            for (int c = 0; c < miniMetrixColLenght; c++) {
                C[r][c] = CNW[r][c];
                C[r][c+miniMetrixColLenght] = CNE[r][c];
                C[r+miniMetrixRowLenght][c] = CSW[r][c];
                C[r+miniMetrixRowLenght][c+miniMetrixColLenght] = CSE[r][c];
            }
        }

        printMatrix(CNW);
        System.out.println();
        printMatrix(CNE);
        System.out.println();
        printMatrix(CSW);
        System.out.println();
        printMatrix(CSE);
        System.out.println();
        printMatrix(C);
        System.out.println();
        printMatrix(A);
        System.out.println();
        printMatrix(B);
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
