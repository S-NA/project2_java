package project2;

import java.util.Scanner;
import java.util.BitSet;

public class Project2 {

    static int checkInputRange(int value, int n) {
        if (value >= 1 && value <= Math.pow(n, 2)) {
            return value;
        }
        System.out.printf("You can only user numbers betweeen 1 and %.0f for this square.\n", Math.pow(n, 2));
        return 0;
    }

    static void printSquareMatrix(final int[][] squareMatrix) {
        System.out.println();
        for (int row = 0; row < squareMatrix.length; row++) {
            for (int column = 0; column < squareMatrix.length; column++) {
                System.out.printf("%d ", squareMatrix[row][column]);
            }
            System.out.println();
        }
    }

    static boolean containsDuplicates(final int[][] squareMatrix) {
        BitSet foundNumbers = new BitSet();
        for (int row = 0; row < squareMatrix.length; row++) {
            for (int column = 0; column < squareMatrix.length; column++) {
                if (foundNumbers.get(squareMatrix[row][column])) {
                    return true;
                }
                foundNumbers.set(squareMatrix[row][column]);
            }
        }
        return false;
    }

    // Requirements for it to be complete.
    // Each number in the matrix is unquie.
    // It has the same sum in all directions.
    static Boolean isMagicSquare(final int[][] squareMatrix, final int MAGIC_CONSTANT) {
        // No dupes.
        if (containsDuplicates(squareMatrix)) {
            return false; // a square matrix cannot contain any duplicates.
        }
        int diagonalSum = 0, reversedDiagonalSum = 0, rowSum = 0, columnSum = 0;
        for (int row = 0; row < squareMatrix.length; row++) {
            for (int column = 0; column < squareMatrix.length; column++) {
                rowSum += squareMatrix[row][column];
                columnSum += squareMatrix[column][row];
                if (row == column) {
                    diagonalSum += squareMatrix[row][column];
                }
                if (row + column == squareMatrix.length - 1) {
                    reversedDiagonalSum += squareMatrix[(squareMatrix.length - 1) - row][(squareMatrix.length - 1) - column];
                }
            }
            if (rowSum == MAGIC_CONSTANT && columnSum == MAGIC_CONSTANT && row != squareMatrix.length - 1) {
                rowSum = 0;
                columnSum = 0;
            } else if (rowSum != MAGIC_CONSTANT || columnSum != MAGIC_CONSTANT) {
                return false;
            }
        }
        return (reversedDiagonalSum == MAGIC_CONSTANT
                && rowSum == MAGIC_CONSTANT
                && columnSum == MAGIC_CONSTANT
                && diagonalSum == MAGIC_CONSTANT);
    }

    static Boolean isValidPosition(int row, int column, int n) {
        return ((row >= 0 && row < n) && (column >= 0 && column < n));
    }

    public static void main(String[] args) {
        int n;
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Let's make a Magic Square! How big should it be? ");
            n = input.nextInt();
            if (n < 3) {
                System.out.println("That would violate the laws of mathematics!");
            } else if (n > 8) {
                System.out.println("That's huge! Please enter a number less than 9.");
            } else {
                break;
            }
        }

        final int MAGIC_CONSTANT = (int) ((0.5 * n) * (Math.pow(n, 2) + 1));
        System.out.println("Great!");
        int[][] squareMatrix = new int[n][n];

        while (!isMagicSquare(squareMatrix, MAGIC_CONSTANT)) {
            System.out.println("The square currently looks like this:");
            printSquareMatrix(squareMatrix);

            System.out.println("Where do you want to put a new value?");
            System.out.print("Row: ");
            int row = input.nextInt();
            System.out.print("Column: ");
            int column = input.nextInt();
            if (isValidPosition(row, column, n)) {
                System.out.print("What values should go there? ");
                int value = input.nextInt();
                if (checkInputRange(value, n) != 0) {
                    squareMatrix[row][column] = value;
                }
            } else {
                System.out.println("Invalid row or column.");
            }
        }
        printSquareMatrix(squareMatrix);
        System.out.println("Victory!");
    }
}