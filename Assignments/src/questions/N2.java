package questions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Mustafa Abdul-Kader
 * CMPSC-203-01
 * Dr Mehta
 */
public class N2 implements IRunner {
	private int[][] effectiveFriends;
    public String getQuestionID() {
        return "N2";
    }
    public IRunner Run(String args) {
    	try (Scanner scanner = new Scanner(new FileReader(args))) {
            int lines = scanner.nextInt();
            scanner.nextLine();
            while(lines > 0) {
            	List<String> words = new ArrayList<>();
            	for(String word : scanner.nextLine().split(" ")) words.add(word);
            	int minIndex = findMinName(words);
            	for(int j = 0; j < minIndex; j++) {
            		String element = words.get(0);
            		words.remove(0);
            		words.add(element);
            	}
            	System.out.println(String.join(" ", words));
            lines--;
            }
            
            return this;
        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }
        return this;
    }
    private int findMinName(List<String> names) {
    	
        String minVal = names.get(0); 
        int minIdx = 0;
        for(int i = 1; i < names.size(); i++) {
        	if(names.get(i).compareTo(minVal) < 0) { 
        			minVal = names.get(i);
        			minIdx = i;
        	}
        }
        return minIdx;
    }
}
