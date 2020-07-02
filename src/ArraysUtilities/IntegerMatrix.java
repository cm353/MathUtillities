package ArraysUtilities;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class IntegerMatrix {

    private final int[][] integerMatrix;

    public IntegerMatrix(int[][] integerMatrix) throws IllegalArgumentException {
        if (!IntegerMatrix.checkGenuineMatrix(integerMatrix))
            throw new IllegalArgumentException("passed matrix not valid");
        this.integerMatrix = integerMatrix;
    }

    // checks if passed two dimensional int array meets requirements of a matrix:
    // no empty matrix; 2D array must be quadratic
    public static boolean checkGenuineMatrix(int[][] integerMatrix) {
        Set<Integer> rowLength = new HashSet<>();
        for (int[] rowVectors : integerMatrix)
            rowLength.add(rowVectors.length);
        return rowLength.size() == 1 && integerMatrix[0].length > 0;
    }

    // returns deep copy of the array of the instance on which the getter was invoked to ensure effective immutabillity
    public int[][] getIntegerMatrix() {
        int[][] resultMatrix = new int[integerMatrix.length][integerMatrix[0].length];
        for (int row = 0; row < resultMatrix.length; row++) {
            System.arraycopy(integerMatrix[row], 0, resultMatrix[row], 0, resultMatrix[row].length);
        }
        return resultMatrix;
    }

    public IntegerMatrix unityMatrix(boolean signumDetermination) {
        int[][] resultingUnityMatrix = new int[integerMatrix.length][integerMatrix[0].length];
        int signum = signumDetermination ? 1 : -1;
        for (int row = 0; row < integerMatrix.length; row++) {
            for (int column = 0; column < integerMatrix[row].length; column++) {
                resultingUnityMatrix[row][column] = signum;
            }
        }
        return new IntegerMatrix(resultingUnityMatrix);
    }

    public IntegerMatrix flipSignum() {
        int[][] resultMatrix = new int[integerMatrix.length][integerMatrix[0].length];
        for (int row = 0; row < resultMatrix.length; row++) {
            for (int column = 0; column < resultMatrix[row].length; column++) {
                resultMatrix[row][column] = integerMatrix[row][column] * -1;
            }
        }
        return new IntegerMatrix(resultMatrix);
    }

    public IntegerMatrix transponseIntegerMatrices() {
        int[][] resultMatrix = new int[integerMatrix[0].length][integerMatrix.length];
        for (int row = 0; row < integerMatrix.length; row++) {
            for (int column = 0; column < integerMatrix[row].length; column++) {
                resultMatrix[column][row] = integerMatrix[row][column];
            }
        }
        return new IntegerMatrix(resultMatrix);
    }

    public IntegerMatrix addIntegerMatrices(IntegerMatrix matrix) throws ArithmeticException {
        if (this.integerMatrix.length != matrix.integerMatrix.length || this.integerMatrix[0].length != matrix.integerMatrix[0].length)
            throw new ArithmeticException("size of matrix to add does not match to matrix operated on");
        int[][] resultMatrix = new int[integerMatrix.length][integerMatrix[0].length];
        for (int row = 0; row < resultMatrix.length; row++) {
            for (int column = 0; column < resultMatrix[row].length; column++) {
                resultMatrix[row][column] = integerMatrix[row][column] + matrix.integerMatrix[row][column];
            }
        }
        return new IntegerMatrix(resultMatrix);
    }

    public IntegerMatrix subtractIntegerMatrices(IntegerMatrix matrix) throws ArithmeticException {
        return this.addIntegerMatrices(matrix.flipSignum());
    }

    public IntegerMatrix multiplyIntegerMatrices(IntegerMatrix matrix) throws ArithmeticException {
        if (this.integerMatrix[0].length != matrix.integerMatrix.length)
            throw new ArithmeticException("number of rows of this matrix does not match with number of columns of passed matrix");
        int newColumns = matrix.integerMatrix[0].length;
        int newRows = this.integerMatrix.length;
        int[][] resultMatrix = new int[newRows][newColumns];
        for (int row = 0; row < resultMatrix.length; row++) {
            for (int column = 0; column < resultMatrix[row].length; column++) {
                for (int i = 0; i < this.integerMatrix[row].length; i++) {
                    resultMatrix[row][column] += this.integerMatrix[row][i] * matrix.integerMatrix[i][column];
                }
            }
        }
        return new IntegerMatrix(resultMatrix);
    }

    public static IntegerMatrix randomMatrix(int rowCount, int columnCount, int bound) throws IllegalArgumentException {
        if (rowCount < 1 || columnCount < 1)
            throw new IllegalArgumentException("Matrix to create must have at least one row and one column ");
        if (bound <= 0)
            throw new IllegalArgumentException("Bound must be positive");
        int[][] matrixInteger = new int[rowCount][columnCount];
        Random rand = new Random();
        for (int row = 0; row < matrixInteger.length; row++) {
            for (int column = 0; column < matrixInteger[row].length; column++) {
                matrixInteger[row][column] = rand.nextInt(bound);
            }
        }
        return new IntegerMatrix(matrixInteger);
    }

    public void printIntegerMatrix() {
        for (int[] row : integerMatrix) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

}
