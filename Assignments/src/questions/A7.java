package questions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Mustafa Abdul-Kader
 * CMPSC-203-01
 * Dr Mehta
 */
public class A7 implements IRunner {
    // DOES NOT ALLOW DISTINCT NUMBERS
    public String getQuestionID() {
        return "A7";
    }

    public IRunner Run(String args) {
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            int trials = scanner.nextInt();
            while (trials != 0) {
                scanner.nextInt();
                scanner.nextLine();
                String[] strNumbers = scanner.nextLine().split(" ");
                int[] numbers = new int[strNumbers.length];
                int score = Integer.parseInt(strNumbers[0]);
                for (int j = 1; j < strNumbers.length; j++) {
                    int value = Integer.parseInt(strNumbers[j]);
                    if (value < score) score = value;
                }
                System.out.println(score * (numbers.length - 1));
                trials--;
            }
            while (scanner.hasNextInt()) {
                int currentValue = scanner.nextInt();
                int counter = 0;
                for (int i = 1; i <= Math.sqrt(currentValue); i++)
                    if (currentValue % i == 0) counter++;
                System.out.println(counter);
            }
        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }
        return this;
    }
}
