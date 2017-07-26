package questions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author      Mustafa Abdul-Kader <abdulkader.m01@mymail.sxu.edu>
 * @version     1.0
 * @since       1.0
 * https://github.com/meMuszr
 */
public class A3 implements IRunner {
	/**
	 *  map between name of friend to integer value, used treemap for sake of order
	 */
	private Map<String,Integer> nameToInt = new TreeMap<>();
	/**
	 *  map between integer value to name of friend
	 */
	private Map<Integer,String> intToName = new HashMap<>();
	/** 
	 * unique outputs
	 */
	private HashSet<String> uniqueOutput = new HashSet<>();
	/** 
	 * matrix of friends
	 */
	private int[][] effectiveFriends;
	
	/**
     * Gets the question ID
     * @return Question ID
     */

    public String getQuestionID() {
        return "A3";
    }
    public IRunner Run(String args) {
		// start disposable scanner reading file input
    	try (Scanner scanner = new Scanner(new FileReader(args))) {
			// use integer to establish how many lines of input we have
			String[][] input = new String[scanner.nextInt()][];
			// read line to start token on next line
			scanner.nextLine();
			// matrix is square size of friends
			effectiveFriends = new int[input.length][input.length];
			// populate nameToInt and intToName based on first name in line, add all names on line to input array
            for(int i = 0; i < input.length; i++) {
            	input[i] = scanner.nextLine().split(" ");
            	nameToInt.put(input[i][0],i);
            	intToName.put(i,input[i][0]);
			}
			// loop through each row in input and assign 1 if stronger and -1 if weaker
            for(String[] row : input) 
            	for(int i=1; i < row.length; i++) {
            		effectiveFriends[nameToInt.get(row[0])][nameToInt.get(row[i])] = 1;
            		effectiveFriends[nameToInt.get(row[i])][nameToInt.get(row[0])] = -1;
				}
			// for each friend in the keySet of nameToInt, find possible cycles
        	for(String friend : nameToInt.keySet()) findCycles(friend,"");
			return this;
			// catch exceptions
        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }
        return this;
    }
    private String findCycles(String start, String output) {
			// split output into space separated array
			String[] amount = output.split(" ");
			// if we get to length 3 then our chain is getting too long, return null
			if(amount.length == 3) return null;
			//get matrix row of friend
        	int[] strengths = effectiveFriends[nameToInt.get(start)];
			List<Integer> listOfBeatFriends = new ArrayList<>();
			// loop through row and add to list if they beat that friend
        	for(int i=0; i < strengths.length; i++)
        		if(strengths[i] == 1) 
					listOfBeatFriends.add(i);
        	// if they don't beat anyone, our cycle ends and it didn't work out. :( return null. 
        	if(listOfBeatFriends.size() == 0) return null;
			// return if they cycle 
			if(amount.length == 2 && listOfBeatFriends.contains(nameToInt.get(amount[0]))) return output + start;
			// Initialize string variable
			String curated = null;
			//loop through friends
			for(Integer in : listOfBeatFriends)
			// if we were returned a string, then we made a match!
        		if((curated = findCycles(intToName.get(in), output + start + " ")) != null) {
					//sort words and add to hashset, only print new entries
					String[] words = curated.split(" ");
        			Arrays.sort(words);
        			if(!uniqueOutput.contains(String.join("",words))) {
            			uniqueOutput.add(String.join("",words));
            			System.out.println(curated);
            			break;
        		}
        	}
        	return curated;
    }
    	
}
