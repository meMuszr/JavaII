package questions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @author      Mustafa Abdul-Kader <abdulkader.m01@mymail.sxu.edu>
 * @version     1.0
 * @since       1.0
 * https://github.com/meMuszr
 */
public class N2 implements IRunner {
    /**
     * Gets the question ID
     * @return Question ID
     */   
    public String getQuestionID() {
        return "N2";
    }
    public IRunner Run(String args) {
        // start disposable scanner reading file input
    	try (Scanner scanner = new Scanner(new FileReader(args))) {
            // set amount of lines
            int lines = scanner.nextInt();
            // skip new line token
            scanner.nextLine();
            // loop through lines
            while(lines > 0) {
                // instantiate array list of words
                List<String> words = new ArrayList<>();
                // for each word add to words
                for(String word : scanner.nextLine().split(" ")) words.add(word);
                // find lowest word index
                int minIndex = findMinName(words);
                // remove at beginning and add at end
            	for(int j = 0; j < minIndex; j++) {
            		String element = words.get(0);
            		words.remove(0);
            		words.add(element);
                }
                // print alphabetically sorted friend list
                System.out.println(String.join(" ", words));
                // decrement lines
                lines--;
            }
            return this;
            // catch exceptions
        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }
        return this;
    }
    private int findMinName(List<String> names) {
        // initalize minVal as first
        String minVal = names.get(0);
        // set minIdx to 0 
        int minIdx = 0;
        // loop through list, if less than, set new minVal and update minIdx
        for(int i = 1; i < names.size(); i++) 
            if(names.get(i).compareTo(minVal) < 0) { 
        			minVal = names.get(i);
        			minIdx = i;
        	}
        // return index
        return minIdx;
    }
}
