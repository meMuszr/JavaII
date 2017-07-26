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
public class N8 implements IRunner {
     /**
     * Gets the question ID
     * @return Question ID
     */     
    public String getQuestionID() {
        return "N8";
    }
    public IRunner Run(String args) {
        // start disposable scanner reading file input        
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            // initalize hashmap to act as a histogram
            Map<Integer,Integer> histogram = new HashMap<>();
            // set trials 
            int trials = scanner.nextInt();
            // loop through trials
            while(trials > 0) {
                // set amoutn of candles
                int candles = scanner.nextInt();
                // loop through candles and add to histogram, if exists increment quantity of those height of candles
                for(int i = 0; i < candles; i++){
                    int x = scanner.nextInt();
                    histogram.put(x, histogram.containsKey(x) ? histogram.get(x) + 1 : 1);
                }
                // find max value
                int maxValue = 0;
                for(int element : histogram.keySet()){
                maxValue = maxValue < element ? element : maxValue;
                }
                // get quantity of those candles
                System.out.println(histogram.get(maxValue));
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
