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
 * Mustafa Abdul-Kader
 * CMPSC-203-01
 * Dr Mehta
 */
public class A3 implements IRunner {
	private Map<String,Integer> nameToInt = new TreeMap<>();
	private Map<Integer,String> intToName = new HashMap<>();
	private HashSet<String> uniqueOutput = new HashSet<>();
	private int[][] effectiveFriends;
    public String getQuestionID() {
        return "A3";
    }
    public IRunner Run(String args) {
    	try (Scanner scanner = new Scanner(new FileReader(args))) {
            String[][] input = new String[scanner.nextInt()][];
            scanner.nextLine();
            effectiveFriends = new int[input.length][input.length];
            for(int i = 0; i < input.length; i++) {
            	input[i] = scanner.nextLine().split(" ");
            	nameToInt.put(input[i][0],i);
            	intToName.put(i,input[i][0]);
            }
            for(String[] row : input) 
            	for(int i=1; i < row.length; i++) {
            		effectiveFriends[nameToInt.get(row[0])][nameToInt.get(row[i])] = 1;
            		effectiveFriends[nameToInt.get(row[i])][nameToInt.get(row[0])] = -1;
            	}
        	for(String friend : nameToInt.keySet()) findCycles(friend,"");
            
            return this;
        } catch (InputMismatchException e) {
            System.out.println("Error with input");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
        }
        return this;
    }
    private String findCycles(String start, String output) {
        	String[] amount = output.split(" ");
    		if(amount.length == 3) return null;
        	int[] strengths = effectiveFriends[nameToInt.get(start)];
        	List<Integer> listOfBeatFriends = new ArrayList<>();
        	for(int i=0; i < strengths.length; i++) {
        		if(strengths[i] == 1) { 
        			listOfBeatFriends.add(i);
        		}
        	}
        	String curated = null;
        	
        	if(listOfBeatFriends.size() == 0) return output += "";
    		if(amount.length == 2 && listOfBeatFriends.contains(nameToInt.get(amount[0]))) return output + start;
    		
        	for(Integer in : listOfBeatFriends) { 
        		curated = findCycles(intToName.get(in), output + start + " ");
        		if(curated != null) {
        			String[] words = curated.split(" ");
        			Arrays.sort(words);
        			if(!uniqueOutput.contains(String.join("",words))) {
            			uniqueOutput.add(String.join("",words));
            			System.out.println(curated);
            			break;
        			}
        			else return null;
        		}

        	}
        	return curated;
    }
    	
}
