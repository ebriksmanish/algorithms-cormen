import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
/*
 * SD2x Homework #3
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */
public class Analyzer {
	
	/*
	 * Implement this method in Part 1
	 */
	public static List<Sentence> readFile(String filename) {
		List<Sentence> result = new ArrayList<>();
		try {
			BufferedReader bufferReader = new BufferedReader(new FileReader(filename));	
			String line;
			do {
				line = bufferReader.readLine();
				
				if (line != null && line.length() >= 2) {
					boolean isNegative = line.startsWith("-");
					int limit = isNegative ? 2 : 1;
					try {
						int score = Integer.parseInt(line.substring(0, limit));
	
						if (score <= 2 && score >= -2 && line.charAt(limit) == ' ') {
							result.add(new Sentence(score, line.substring(limit + 1)));
						}
					} catch(NumberFormatException e) {}
				}
				
			} while(line != null);
			
			bufferReader.close();
			return result;
		} catch (NullPointerException npException) {
			return result;
		} catch (IOException ioException) {
			return result;
		}
	}	
	/*
	 * Implement this method in Part 2
	 */
	public static Set<Word> allWords(List<Sentence> sentences) {

		/* IMPLEMENT THIS METHOD! */
		Set<Word> result = new HashSet<>();
		Map<String, Word> tempResult = new HashMap<>();

		if (sentences == null || sentences.isEmpty()) {
			return result;
		}
		
		Iterator<Sentence> iterator = sentences.iterator();
		while(iterator.hasNext()) {
			Sentence sentence = iterator.next();

			if (sentence != null) {
				String text = sentence.getText();
				String[] words = text.split(" ");
				
				for (int index = 0; index < words.length; index++) {
					String word = words[index];

					if (word.length() > 1 && word.matches("^[a-zA-Z].*")) {
						word = word.toLowerCase().split("[\\W]")[0];
						Word savedWord = tempResult.get(word);
						if (savedWord == null) {
							savedWord = new Word(word);
							tempResult.put(word, savedWord);
						}
						savedWord.increaseTotal(sentence.score);
					}
				}
			}
		}
		
		return new HashSet<>(tempResult.values());

	}
	
	/*
	 * Implement this method in Part 3
	 */
	public static Map<String, Double> calculateScores(Set<Word> words) {
		Map<String, Double> result = new HashMap<>();
		
		if (words == null || words.isEmpty()) {
			return result;
		}
		
		Iterator<Word> iterator = words.iterator();
		
		while (iterator.hasNext()) {
			Word word = (Word) iterator.next();
			if (word != null) {
				result.put(word.getText(), word.calculateScore());	
			}
		}
		
		return result;
	}

	
	/*
	 * Implement this method in Part 4
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
		
		if (wordScores == null || sentence == null || sentence.isEmpty()) {
			return 0;
		}
		

		String[] words = sentence.split(" ");
		double overallScore = 0;
		int validWordsCount = 0;
		for (int index = 0; index < words.length; index++) {
			String word = words[index];
			if (word.length() > 1 && word.matches("^[a-zA-Z].*")) {
				word = word.toLowerCase().split("[\\W]")[0];
				Double wordScore = wordScores.get(word);
				overallScore += wordScore == null ? 0 : wordScore;
				validWordsCount++;
			}
		}
		
		return validWordsCount > 0 ? overallScore / validWordsCount : 0;

	}
	
	/*
	 * This method is here to help you run your program. Y
	 * You may modify it as needed.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please specify the name of the input file");
			System.exit(0);
		}
		String filename = args[0];
		System.out.print("Please enter a sentence: ");
		Scanner in = new Scanner(System.in);
		String sentence = in.nextLine();
		in.close();
		List<Sentence> sentences = Analyzer.readFile(filename);
		Set<Word> words = Analyzer.allWords(sentences);
		Map<String, Double> wordScores = Analyzer.calculateScores(words);
		double score = Analyzer.calculateSentenceScore(wordScores, sentence);
		System.out.println("The sentiment score is " + score);
	}
}
