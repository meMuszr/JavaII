package questions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * @author      Mustafa Abdul-Kader <abdulkader.m01@mymail.sxu.edu>
 * @version     1.0
 * @since       1.0
 * https://github.com/meMuszr
 */
public class A7 implements IRunner {
    // DOES NOT ALLOW DISTINCT NUMBERS


    /**
     * Gets the question ID
     * @return Question ID
     */    
    public String getQuestionID() {
        return "A7";
    }

    public IRunner Run(String args) {
        // start disposable scanner reading file input
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            // get trials
            int trials = scanner.nextInt();
            // loop through trials
            while (trials != 0) {
                // token skipping
                scanner.nextInt();
                scanner.nextLine();
                // parse line to string array
                String[] strNumbers = scanner.nextLine().split(" ");
                // create integer array
                int[] numbers = new int[strNumbers.length];
                // grab first value
                int score = Integer.parseInt(strNumbers[0]);
                // find min value and store in score
                for (int j = 1; j < strNumbers.length; j++) {
                    int value = Integer.parseInt(strNumbers[j]);
                    if (value < score) score = value;
                }
                // print min multipied by amount left in array
                System.out.println(score * (numbers.length - 1));
                // decrement trials
                trials--;
            }
            // catch exceptions
        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }
        return this;
    }
}
