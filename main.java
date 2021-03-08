import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class main {


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
        int[][] ANW = new int[Row/2][Col/2];
        int[][] ANE = new int[Row/2][Col/2];
        int[][] ASW = new int[Row/2][Col/2];
        int[][] ASE = new int[Row/2][Col/2];
        int[][] BNW = new int[Row/2][Col/2];
        int[][] BNE = new int[Row/2][Col/2];
        int[][] BSW = new int[Row/2][Col/2];
        int[][] BSE = new int[Row/2][Col/2];
        int[][] CNW = new int[Row/2][Col/2];
        int[][] CNE = new int[Row/2][Col/2];
        int[][] CSW = new int[Row/2][Col/2];
        int[][] CSE = new int[Row/2][Col/2];
        try {
            for (int r = 0; r < Row; r++) {
                for (int c = 0; c < Col; c++) {
                    A[r][c] = fileIn.nextInt();
                    if( r < Row/2 && c < Col/2) {
                        A
                    }
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








        addMatrix NE = new addMatrix(A, B);
        //addMatrix NW = new addMatrix(ANW, BNW);
        //addMatrix SE = new addMatrix(ASE, BSE);
        //addMatrix SW = new addMatrix(ASW, BSW);
        NE.start();
        //NW.start();
        //SE.start();
        //SW.start();

        //System.out.println(NE.getResult());

        printMatrix(NE.getResult());
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
