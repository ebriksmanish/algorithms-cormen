/*
 * SD2x Homework #5
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MovieRatingsParser {

	public static TreeMap<String, PriorityQueue<Integer>> parseMovieRatings(List<UserMovieRating> allUsersRatings) {
		TreeMap<String, PriorityQueue<Integer>> result = new TreeMap<String, PriorityQueue<Integer>>();

		if (allUsersRatings != null) {
			for (UserMovieRating userMovieRating : allUsersRatings) {
				if(isValidRating(userMovieRating)) {
					String title = userMovieRating.getMovie().toLowerCase();
					PriorityQueue<Integer> ratings = result.containsKey(title) ? result.get(title) : new PriorityQueue<>();
					ratings.add(userMovieRating.getUserRating());
					result.putIfAbsent(title, ratings);
				}
			}
		}
		
		return result;
	}

	private static boolean isValidRating(UserMovieRating userMovieRating) {
		if (userMovieRating != null) {
			String title = userMovieRating.getMovie();
			int rating = userMovieRating.getUserRating();
			
			return title != null && !title.isEmpty() && rating > 0;
		}
		
		return false;
	}

}
