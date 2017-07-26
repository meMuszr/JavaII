package questions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author      Mustafa Abdul-Kader <abdulkader.m01@mymail.sxu.edu>
 * @version     1.0
 * @since       1.0
 * https://github.com/meMuszr
 */
public class N7 implements IRunner {
    /**
     * Gets the question ID
     * @return Question ID
     */       
    public String getQuestionID() {
        return "N7";
    }
    // set regex pattern 
    private final Pattern pattern = Pattern.compile("\\b[a-zA-Z]{4}\\b");

    public IRunner Run(String args) {
        // start disposable scanner reading file input
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            // set trials
            int trials = scanner.nextInt();
            // skip new line token
            scanner.nextLine();
            // loop through trials
            while (trials > 0) {
                // first we're reading a line, and replacing certain characters with empty character,
                // and then matching with pattern, we try and see if we found anything and print
                System.out.println(pattern.matcher(scanner.nextLine().replaceAll("[\',.]", "")).find() ? "upset" : "happy");
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
