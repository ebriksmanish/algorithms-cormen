/*
 * SD2x Homework #5
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeMap;


public class MovieRatingsProcessor {

	public static List<String> getAlphabeticalMovies(TreeMap<String, PriorityQueue<Integer>> movieRatings) {
		if (movieRatings == null) {
			return new ArrayList<>();
		}
		return new ArrayList<>(movieRatings.keySet());
	}

	public static List<String> getAlphabeticalMoviesAboveRating(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		List<String> movies = new ArrayList<>();

		if (movieRatings != null) {
			for (Entry<String, PriorityQueue<Integer>> ratingEntry : movieRatings.entrySet()) {
				int movieRating = ratingEntry.getValue().peek();
				if (rating < movieRating) {
					movies.add(ratingEntry.getKey());
				}
			}
		}
		
		return movies;
	}
	
	public static TreeMap<String, Integer> removeAllRatingsBelow(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		
		TreeMap<String, Integer> movies = new TreeMap<>();
		
		if (movieRatings != null) {
			Iterator<String> movieRatingsIterator = movieRatings.keySet().iterator();
			while(movieRatingsIterator.hasNext()) {
				String title = movieRatingsIterator.next();
				PriorityQueue<Integer> movieRatingsQueue = movieRatings.get(title);
				Iterator<Integer> movieRatingsQueueIterator = movieRatingsQueue.iterator();
				boolean belowRating = true;
				while(movieRatingsQueueIterator.hasNext() && belowRating) {
					int movieRating = movieRatingsQueueIterator.next();
					belowRating = movieRating < rating;
					if (belowRating) {
						movies.put(title, movies.getOrDefault(title, 0) + 1);
						movieRatingsQueueIterator.remove();
					}
				}
				
				if (movieRatingsQueue.isEmpty()) {
					movieRatingsIterator.remove();
				}
			}
		}
		
		return movies;
	}
}
