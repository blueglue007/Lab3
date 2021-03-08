public class addMatrix extends Thread {
    private int[][] X;
    private int[][] Y;
    int[][] C;
    public addMatrix(int[][] X, int[][] Y) {
        this.X = X;
        this.Y = Y;
        C = new int[X.length][X[0].length];
    }
    @Override
    public void run() {
        for (int r = 0; r < X.length; r++) {
            for (int c = 0; c < X[r].length; c++) {
                C[r][c] = X[r][c] + Y[r][c];
            }
        }

    }
    public int[][] getResult(){return C;}
}
