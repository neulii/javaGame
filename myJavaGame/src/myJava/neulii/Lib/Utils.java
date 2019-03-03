package myJava.neulii.Lib;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Utils {

	public static int[] fillArrayWithRandomInts(int range, int length) {
		Set<Integer> randoms = new HashSet<Integer>();
		Random r = new Random();
		
		while(randoms.size()>= length) {
			randoms.add(r.nextInt(range));
		}
	
		int [] ints = new int[randoms.size()];
		
		//convert hashset in int array
		int counter = 0;
		for (Integer i : randoms) {
			ints[counter] = i;
			counter++;
		}
		
		return ints;
	}
}
