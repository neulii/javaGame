package myJava.neulii.Lib;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Utils {

	public static int[] getArrayWithRandomInts(int range, int length) {
		
		if(length > range)
			throw new IllegalArgumentException("Parameter Fehler");
		
		Set<Integer> randoms = new HashSet<Integer>();
		Random r = new Random();
		
		while(randoms.size()< length) {
			int number = r.nextInt(range); 
			
//			System.out.println(randoms.size());
			randoms.add(number);
			
		}
	
		int [] ints = new int[randoms.size()];
		
//		System.out.println(ints.length);
		
		//convert hashset in int array
		int counter = 0;
		for (Integer i : randoms) {
			ints[counter] = i;
			counter++;
		}
		
		
		return ints;
	}
	
	public static void printArrayToConsole(int array[]) {
		
		for (int i : array) {
			System.out.println(i);
		}
	}
}
