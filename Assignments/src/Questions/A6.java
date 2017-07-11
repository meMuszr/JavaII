package Questions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Mustafa Abdul-Kader
 * CMPSC-203-01
 * Dr Mehta
 * ${Date}
 */
public class A6 implements IRunner {
    public String getQuestionID() {
        return "A6";
    }

    private char[][] Reservoir;

    public IRunner Run(String args) {
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            int trials = scanner.nextInt();
            while (trials > 0) {
                int height = scanner.nextInt();
                scanner.nextLine();
                Reservoir = new char[height][];
                for (int i = 0; i < height; i++) Reservoir[i] = scanner.nextLine().toCharArray();
                System.out.println(checkReservoir() ? "yes" : "no");
                trials--;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }
        return this;
    }

    private boolean checkReservoir() {
        boolean valid = true;
        for (int i = 0; i < Reservoir.length; i++)
            for (int j = 0; j < Reservoir[i].length; j++)
                valid &= (i != Reservoir.length - 1 && j != 0 && j != Reservoir.length - 1) ? checkInside(i, j) : Reservoir[i][j] == 'B';
        return valid;
    }

    private boolean checkInside(int y, int x) {
        switch (Reservoir[y++][x]) {
            case 'B':
                return Reservoir[y][x] == 'B';
            case 'W':
                return y == Reservoir.length - 1 ? Reservoir[y][x] == 'B' : Reservoir[y][x] == 'W' || Reservoir[y][x] == 'B';
            case 'A':
                return y == Reservoir.length - 1 ? Reservoir[y][x] == 'B' : Reservoir[y - 1][x + 1] != 'W' && Reservoir[y - 1][x - 1] != 'W';
            default:
                return false;
        }
    }
}
