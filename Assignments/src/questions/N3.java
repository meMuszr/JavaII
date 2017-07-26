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
 * See A4 for comments
 */
public class N3 implements IRunner {
    public String getQuestionID() {
        return "N3";
    }

    private final int matrixSize = 10;
    boolean[][] binaryHoleArray = new boolean[10][10];


    public IRunner Run(String args) {
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            String line;
            for (int i = 0; i < 10; i++) {
                line = scanner.nextLine();
                for (int j = 0; j < 10; j++)
                    binaryHoleArray[i][j] = line.charAt(j) == '1';
            }
            System.out.println(checkHoles());
        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }
        return this;
    }

    private int checkHoles() {
        int total = 0;
        for (int i = 0; i < matrixSize; i++)
            for (int j = 0; j < matrixSize; j++)
                if (binaryHoleArray[i][j] == false) {
                    fillHoles(j, i);
                    total++;
                }
        return total;
    }

    private boolean fillHoles(int x, int y) {
        if ((x > 0 && x < matrixSize - 1) && (y > 0 && y < matrixSize - 1)) {
            binaryHoleArray[y][x] = true;
            if (binaryHoleArray[y + 1][x] == false) fillHoles(x, y + 1);
            if (binaryHoleArray[y - 1][x] == false) fillHoles(x, y - 1);
            if (binaryHoleArray[y][x + 1] == false) fillHoles(x + 1, y);
            if (binaryHoleArray[y][x - 1] == false) fillHoles(x - 1, y);
        }
        return true;
    }
}
