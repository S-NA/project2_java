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
import java.util.BitSet;

public class Project2 {

    /**
     * Checks the input to be between a valid range.
     * @param value - the input to check.
     * @param n - the size our matrix.
     * @return the value if it is value, otherwise return 0.
     */
    static Boolean validInput(int row, int column, int value, int n) {
        if (((row >= n || row < 0) || (column >= n || column < 0)) && (value < 1 || value > Math.pow(n, 2))) {
            System.out.printf("Invalid row or column entered, please choose a value under %d.\n", n);
            System.out.printf("You can only user numbers betweeen 1 and %.0f for this square.\n", Math.pow(n, 2));
            return false;
        } else if ((row >= n || row < 0) || (column >= n || column < 0)) {
            System.out.printf("Invalid row or column entered, please choose a value under %d.\n", n);
            return false;
        } else if (value < 1 || value > Math.pow(n, 2)) {
            System.out.printf("You can only user numbers betweeen 1 and %.0f for this square.\n", Math.pow(n, 2));
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * Prints the matrix.
     * @param matrix - the reference to the matrix object to print.
     */
    static void printMatrix(final int[][] matrix) {
        System.out.println();
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix.length; column++) {
                System.out.printf("%d ", matrix[row][column]);
            }
            System.out.println();
        }
    }
    
    /**
     * Checks the referenced object to see if it contains any duplicates.
     * @param matrix - the reference to the matrix object to print.
     * @return true or depending on whether duplicates exist (true if they do).
     */
    static boolean containsDuplicates(final int[][] matrix) {
        BitSet foundNumbers = new BitSet();
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix.length; column++) {
                if (foundNumbers.get(matrix[row][column])) {
                    return true;
                }
                foundNumbers.set(matrix[row][column]);
            }
        }
        return false;
    }

    /**
     * Checks the referenced object to see if it is a magic square.
     * @param matrix - the reference to the matrix object to print.
     * @param MAGIC_CONSTANT - the correct sum for diagonals, columns, and rows.
     * @return true if the matrix is a magic square, else false.
     */
    static Boolean isMagicSquare(final int[][] matrix, final int MAGIC_CONSTANT) {
        // Call our duplicate checking function if true, return false.
        if (containsDuplicates(matrix)) {
            return false;
        }
        int diagonalSum = 0, reversedDiagonalSum = 0, rowSum = 0, columnSum = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix.length; column++) {
                rowSum += matrix[row][column];
                columnSum += matrix[column][row];
                if (row == column) {
                    diagonalSum += matrix[row][column];
                }
                if (row + column == matrix.length - 1) {
                    reversedDiagonalSum += matrix[(matrix.length - 1) - row][(matrix.length - 1) - column];
                }
            }
            if (MAGIC_CONSTANT == rowSum && MAGIC_CONSTANT == columnSum && matrix.length - 1 != row) {
                rowSum = 0;
                columnSum = 0;
            } else if (rowSum != MAGIC_CONSTANT || columnSum != MAGIC_CONSTANT) {
                return false;
            }
        }
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
        int n;
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Let's make a Magic Square! How big should it be? ");
            n = input.nextInt();
            if (n < 3 && n != 1) {
                System.out.println("That would violate the laws of mathematics!\n");
            } else if (n > 9) {
                System.out.println("That's huge! Please enter a number less than 9.\n");
            } else {
                System.out.println("Great!");
                break;
            }
        }
        
        // Formula to calculate the wanted sum, source:
        // http://mathworld.wolfram.com/MagicConstant.html
        final int MAGIC_CONSTANT = (int) ((0.5 * n) * (Math.pow(n, 2) + 1));
        int[][] matrix = new int[n][n];

        while (!isMagicSquare(matrix, MAGIC_CONSTANT)) {
            System.out.print("\nThe square currently looks like this:");
            printMatrix(matrix);
            System.out.println("\nWhere do you want to put a new value?");
            System.out.print("Row: ");
            int row = input.nextInt();
            System.out.print("Column: ");
            int column = input.nextInt();
            System.out.print("What values should go there? ");
            int value = input.nextInt();
            if (validInput(row, column, value, n)) {
                matrix[row][column] = value;
            }
        }
        printMatrix(matrix);
        System.out.println("Victory!");
    }
}