package questions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author      Mustafa Abdul-Kader <abdulkader.m01@mymail.sxu.edu>
 * @version     1.0
 * @since       1.0
 * https://github.com/meMuszr
 */
public class A4 implements IRunner {
	/**
     * Gets the question ID
     * @return Question ID
     */
    public String getQuestionID() {
        return "A4";
    }
    // assume matrix is 10x10
    private final int matrixSize = 10;
    // initalize binary 2d array.
    boolean[][] binaryHoleArray = new boolean[matrixSize][matrixSize];


    public IRunner Run(String args) {
        // start disposable scanner reading file input
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            // parse input into array
            String line;
            for (int i = 0; i < 10; i++) {
                line = scanner.nextLine();
                for (int j = 0; j < 10; j++)
                    binaryHoleArray[i][j] = line.charAt(j) == '1';
            }
            // print checkHoles()
            System.out.println(checkHoles());
            // catch exceptions
        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }
        return this;
    }

    private int checkHoles() {
        // initalize count
        int total = 0;
        // loop through array, if we find a hole, call fillHoles() and increment total
        for (int i = 0; i < matrixSize; i++)
            for (int j = 0; j < matrixSize; j++)
                if (binaryHoleArray[i][j] == false) {
                    fillHoles(j, i);
                    total++;
                }
        return total;
    }

    private void fillHoles(int x, int y) {
        // check if coordinates are in boundaries
        if ((x > 0 && x < matrixSize - 1) && (y > 0 && y < matrixSize - 1)) {
            // set point to true
            binaryHoleArray[y][x] = true;
            // recursively fill surrounding zeros
            if (binaryHoleArray[y + 1][x] == false) fillHoles(x, y + 1);
            if (binaryHoleArray[y - 1][x] == false) fillHoles(x, y - 1);
            if (binaryHoleArray[y][x + 1] == false) fillHoles(x + 1, y);
            if (binaryHoleArray[y][x - 1] == false) fillHoles(x - 1, y);
        }
    }
}
