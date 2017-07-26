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
public class N1 implements IRunner {
    /**
     * Gets the question ID
     * @return Question ID
     */    
    public String getQuestionID() {
        return "N1";
    }

    public IRunner Run(String args) {
        // start disposable scanner reading file input
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            // skip token
            scanner.nextInt();
            // while we have an int
            while (scanner.hasNextInt()) {
                // get current area and initalize counter
                int currentValue = scanner.nextInt(),
                    counter = 0;
                    // loop i at 1 and increment, if less than the square root
                    //  of our current value (max width / height before it goes in opposite direction)
                    // if no remainder, valid rectangle, increment counter                
                for(int i = 1; i <= Math.sqrt(currentValue); i++) if(currentValue%i == 0) counter++;
                // print count of valid rectangles
                System.out.println(counter);
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
