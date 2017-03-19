/**
 * Project 2 for CS 1180.
 * This program displays "Victory!" when the user has entered a magic square.
 * The program will prompt for the size of the matrix, and then proceed to loop
 * until the user enters valid magic square. Upon entering a valid magic square
 * the message "Victory!" will be displayed.
 */
package project2;

/**
 * @author Super Sir Simone Royal
 * CS-1180L-05
 * Instructor: Everybody
 * Project 2: Magic Square
 */
import java.util.Scanner;

public class Project2 {

    /**
     * Checks the value for row or column entered by the user.
     *
     * @param matrix - the reference to the matrix object to print.
     * @param position - the row or column position.
     * @return true if it is false, or false if it is invalid.
     */
    static boolean isValidPosition(final int[][] matrix, int position) {
        if (position > matrix.length - 1 || position < 0) {
            System.out.printf("Invalid input for position, please choose a "
                    + "number between 0 to %d (inclusive).\n", matrix.length - 1);
            return false;
        }
        return true;
    }

    /**
     * Checks the value to be within a range of 1 to n, where n depends on the
     * matrix size, and whether it is a duplicate or not.
     *
     * @param matrix - the reference to the matrix object to print.
     * @param value - the value to check.
     * @return true when it is a valid value.
     */
    static boolean isValidValue(final int[][] matrix, int value, int uRow, int uCol) {
        if (value < 1 || value > Math.pow(matrix.length, 2)) {
            System.out.printf("You can only user numbers betweeen 1 and %.0f "
                    + "for this square.\n", Math.pow(matrix.length, 2));
            return false;
        }
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix.length; column++) {
                if (matrix[row][column] == value && row == uRow && column == uCol) {
                    System.out.println("You already entered this number in this position.");
                } else if (matrix[row][column] == value) {
                    System.out.printf("\nYou already entered this number at location (%d, %d).\n", row, column);
                    System.out.printf("Setting location (%d, %d) to zero.\n", row, column);
                    matrix[row][column] = 0;
                }
            }
        }
        return true;
    }

    /**
     * Prints the matrix.
     *
     * @param matrix - the reference to the matrix object to print.
     */
    static void printMatrix(final int[][] matrix) {
        System.out.println(); // Format the matrix to not be cut off.
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix.length; column++) {
                System.out.printf("%d\t", matrix[row][column]);
            }
            System.out.println();
        }
    }

    /**
     * Checks the referenced object to see if it is a magic square.
     *
     * @param matrix - the reference to the matrix object to print.
     * @param MAGIC_CONSTANT - the correct sum for diagonals, columns, and rows.
     * @return true if the matrix is a magic square, else false.
     */
    static boolean isMagicSquare(final int[][] matrix, final int MAGIC_CONSTANT) {
        System.out.print("\nThe square currently looks like this:");
        printMatrix(matrix);
        int diagonalSum = 0, reversedDiagonalSum = 0, rowSum = 0, columnSum = 0;
        for (int row = 0; row < matrix.length; row++) {
            diagonalSum += matrix[row][row];
            reversedDiagonalSum += matrix[(matrix.length - 1) - row][(matrix.length - 1) - row];
            for (int column = 0; column < matrix.length; column++) {
                // Magic squares cannot contain ANY zeros.
                if (matrix[row][column] == 0) {
                    return false;
                }
                rowSum += matrix[row][column];
                columnSum += matrix[column][row];
            }
            if (MAGIC_CONSTANT == rowSum && MAGIC_CONSTANT == columnSum && matrix.length - 1 != row) {
                rowSum = 0;
                columnSum = 0;
            } else if (rowSum != MAGIC_CONSTANT || columnSum != MAGIC_CONSTANT) {
                return false;
            }
        }
        // Return the result of the boolean expression.
        return (MAGIC_CONSTANT == reversedDiagonalSum
                && MAGIC_CONSTANT == rowSum
                && MAGIC_CONSTANT == columnSum
                && MAGIC_CONSTANT == diagonalSum);
    }

    /**
     * Prompts user to enter the size of their magic square, then proceeds to
     * loop over and over until, the isMagicSquare() function returns true.
     *
     * @param args - arguments passed via the command line.
     */
    public static void main(String[] args) {
        int n; // Store the size of the matrix.
        Scanner input = new Scanner(System.in);
        // Loop until the user enters a valid number for the size of the magic
        // square.
        while (true) {
            System.out.print("Let's make a Magic Square! How big should it be? ");
            try {
                n = input.nextInt();
                if (n < 3 && n != 1) {
                    System.out.println("That would violate the laws of mathematics!\n");
                } else if (n >= 9) {
                    System.out.println("That's huge! Please enter a number less than 9.\n");
                } else {
                    System.out.println("Great!");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Please enter an numerical value.");
                input.next();
            }
        }

        // Formula to calculate the wanted sum, source:
        // http://mathworld.wolfram.com/MagicConstant.html
        final int MAGIC_CONSTANT = (int) ((0.5 * n) * (Math.pow(n, 2) + 1));
        int[][] matrix = new int[n][n];
        // Loop until the sum of each row, column, and diagonals equal the
        // magic constant.
        while (!isMagicSquare(matrix, MAGIC_CONSTANT)) {
            int row = -1, column = -1, value = -1;
            System.out.println("\nWhere do you want to put a new value?");
            do {
                System.out.print("Row: ");
                try {
                    row = input.nextInt();
                } catch (Exception e) {
                    input.next();
                }
            } while (!isValidPosition(matrix, row));
            do {
                System.out.print("Column: ");
                try {
                    column = input.nextInt();
                } catch (Exception e) {
                    input.next();
                }
            } while (!isValidPosition(matrix, column));
            do {
                System.out.print("What values should go there? ");
                try {
                    value = input.nextInt();
                } catch (Exception e) {
                    input.next();
                }
            } while (!isValidValue(matrix, value, row, column));
            matrix[row][column] = value;
        } // When the loop is finished, that means we have a magic square.
        System.out.println("\nVictory!");
    }
}