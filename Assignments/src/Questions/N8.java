package Questions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Mustafa Abdul-Kader
 * CMPSC-203-01
 * Dr Mehta
 * ${Date}
 */
public class N8 implements IRunner {
    public String getQuestionID() {
        return "N8";
    }
    public IRunner Run(String args) {
        try (Scanner scanner = new Scanner(new FileReader(args))) {
            Map<Integer,Integer> histogram = new HashMap<>();
            int trials = scanner.nextInt();
            while(trials > 0) {
                int candles = scanner.nextInt();
                for(int i = 0; i < candles; i++){
                    int x = scanner.nextInt();
                    histogram.put(x, histogram.containsKey(x) ? histogram.get(x) + 1 : 1);
                }
                int maxValue = 0;
                for(int element : histogram.keySet()){
                maxValue = maxValue < element ? element : maxValue;
                }
                System.out.println(histogram.get(maxValue));
                trials--;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }
        return this;
    }
}
