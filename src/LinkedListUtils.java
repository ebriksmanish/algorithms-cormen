

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/*
 * SD2x Homework #1
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class LinkedListUtils {
	
	public static void insertSorted(LinkedList<Integer> list, int value) {
		if (list == null) {
			return;
		}

		if (list.isEmpty()) {
			list.add(value);
			return;
		}
		
		ListIterator<Integer> iterator = list.listIterator();
		int indexToAdd = 0;
		while(iterator.hasNext()) {
			int currentNumber = iterator.next();
			if (value > currentNumber) {
				indexToAdd = iterator.nextIndex();
			} else {
				break;
			}
		}
		
		list.add(indexToAdd, value);
	}
	

	public static void removeMaximumValues(LinkedList<String> list, int N) {
		for (int index = 0; index < N; index++) {
			int maxValueIndex = LinkedListUtils.getMaxValueIndex(list);
			
			if (maxValueIndex >= 0) {
				String currentMaxValue = list.get(maxValueIndex);
				boolean removedMaxValue;
				do {
					removedMaxValue = list.remove(currentMaxValue);
				} while(removedMaxValue);
			}
		}
	}
	
	public static int getMaxValueIndex(LinkedList<String> list) {
		if (list == null) {
			return -1;
		}
		if (list.isEmpty()) {
			return -1;
		}
		int maxValueIndex = 0;
		String maxValue = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			String value = list.get(i);
			
			if(value.compareTo(maxValue) > 0) {
				maxValueIndex = i;
				maxValue = value;
				i++;
			}
		}
		return maxValueIndex;
	}
	
	public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) {

		if (one == null || two == null) {
			return false;
		}
		
		Iterator<Integer> listIterator = one.iterator();
		boolean match = false;

		while (listIterator.hasNext() && !match) {
			Integer listInteger = (Integer) listIterator.next();
			Iterator<Integer> sequenceIterator = two.iterator();

			while (sequenceIterator.hasNext()) {
				Integer sequenceInteger = (Integer) sequenceIterator.next();
				
				if (listInteger == sequenceInteger) {		
					match = true;
					if (listIterator.hasNext()) {
						listInteger = listIterator.next();
					}
				} else {
					match = false;
					break;
				}
			}
		}

		return match;
	}
}
