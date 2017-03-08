package project2;

import java.util.Scanner;
import java.util.Arrays;

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
        boolean[] bitmap = new boolean[squareMatrix.length];
        Arrays.fill(bitmap, false);
        for (int row = 0; row < squareMatrix.length; row++) {
            for (int column = 0; column < squareMatrix.length; column++) {
                if (!bitmap[squareMatrix[row][column]]) {
                    bitmap[squareMatrix[row][column]] = true;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    // Requirements for it to be complete.
    // Each number in the matrix is unquie.
    // It has the same sum in all directions.
    static Boolean isMagicSquare(final int[][] squareMatrix) {
        // No dupes.
        if (containsDuplicates(squareMatrix)) {
            return false; // a square matrix cannot contain any duplicates.
        }
        //For diagonal elements
        int sum = 0;
        for (int row = 0; row < squareMatrix.length; row++) {
            for (int column = 0; column < squareMatrix.length; column++) {
                if (row == column) {
                    sum = sum + squareMatrix[row][column];
                }
            }
        }

        //For Rows
        int flag = 0;
        for (int row = 0; row < squareMatrix.length; row++) {
            int sum1 = 0;
            for (int column = 0; column < squareMatrix.length; column++) {
                sum1 = sum1 + squareMatrix[row][column];
            }
            if (sum == sum1) {
                flag = 1;
            } else {
                flag = 0;
                break;
            }
        }

        //For Columns
        for (int row = 0; row < squareMatrix.length; row++) {
            int sum2 = 0;
            for (int column = 0; column < squareMatrix.length; column++) {
                sum2 = sum2 + squareMatrix[column][row];
            }
            if (sum == sum2) {
                flag = 1;
            } else {
                flag = 0;
                break;
            }
        }
        return flag == 1;
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
            if (n > 8 || n < 3) {
                System.out.println("That would violate the laws of mathematics!");
            } else {
                break;
            }
        }
        System.out.println("Great!");
        int[][] squareMatrix = new int[n][n];

        while (!isMagicSquare(squareMatrix)) {
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