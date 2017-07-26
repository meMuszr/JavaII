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
public class A5 implements IRunner {
    
    // class to store the gain / loss of each plate, implements Comparable so we can sort based on sum
    private static class Plate implements Comparable<Plate> {
        private int Sum;
        private int Gain;
        private int Loss;

        public Plate(int gain, int loss) {
            Gain = gain;
            Loss = -loss;
            Sum = gain + loss;
        }

        public int getSum() {
            return Sum;
        }

        public int getGain() {
            return Gain;
        }

        public int getLoss() {
            return Loss;
        }
        // sort by Sum
        public int compareTo(Plate o) {
            if (this.Sum == o.getSum()) return 0;
            return this.Sum < o.getSum() ? -1 : 1;
        }
    }
	/**
     * Gets the question ID
     * @return Question ID
     */
    public String getQuestionID() {
        return "A5";
    }


    public IRunner Run(String args) {
        // start disposable scanner reading file input        
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            // get number of plates and how many are washed
            int plates = scanner.nextInt(), washed = scanner.nextInt();
            // create arraylist of plates
            List<Plate> plateArr = new ArrayList<>();
            // parse input into plates
            for (int i = 0; i < plates; i++) plateArr.add(new Plate(scanner.nextInt(), scanner.nextInt()));
            // sort in reverse order
            Collections.sort(plateArr, Collections.reverseOrder());
            // initalize max cost
            int maxCost = 0;
            // loop through plates, grab the highest sums first 
            // and get their gain and when we take all the ones we wash,
            // grab our loss from the rest of plates 
            for (Plate p : plateArr) maxCost += (washed-- > 0) ? p.getGain() : p.getLoss();
            System.out.println(maxCost);
            // catch exceptions
        } catch (InputMismatchException e) { 
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }
        return this;
    }
}
