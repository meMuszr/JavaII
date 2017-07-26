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
public class N5 implements IRunner {
    /**
     * Gets the question ID
     * @return Question ID
     */   
    public String getQuestionID() {
        return "N5";
    }
    public IRunner Run(String args) {
        // start disposable scanner reading file input
    	try (Scanner scanner = new Scanner(new FileReader(args))) {
            // set amount of trials
            int trials = scanner.nextInt();
            // loop through trials
            while (trials > 0) {
                // print whether legs are valid 
            	System.out.println(validLegs(scanner.nextInt(),scanner.nextInt(),scanner.nextInt()) ? "YES" : "NO");
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
    private boolean validLegs(int cats, int dogs, int legs) {
        // loop through dogs
    	for(int i = 0; i < dogs; i++) {
            // set cats that are on dogs back
            int catsOnBack = 0;
            // if there's cats left over, loop
    		while(cats >= 0) {
                // if legs are equivalent to the amount they should be, return
                if(legs == 4 * (cats-- + dogs)) return true;
                // increment cats on back
                catsOnBack++;
                // if they reached two, we need to switch to different dog (break)
    			if(catsOnBack == 2) break;
    		}
        }
        // if we made it here, then it was never meant to be :(
    	return false;
    }
}
