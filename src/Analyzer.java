import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.ButtonGroup;

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
		} catch (NullPointerException e) {
			return result;
		} catch (IOException e1) {
			return result;
		}
	}	
	/*
	 * Implement this method in Part 2
	 */
	public static Set<Word> allWords(List<Sentence> sentences) {

		/* IMPLEMENT THIS METHOD! */
		
		return null; // this line is here only so this code will compile if you don't modify it

	}
	
	/*
	 * Implement this method in Part 3
	 */
	public static Map<String, Double> calculateScores(Set<Word> words) {

		/* IMPLEMENT THIS METHOD! */
		
		return null; // this line is here only so this code will compile if you don't modify it

	}
	
	/*
	 * Implement this method in Part 4
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {

		/* IMPLEMENT THIS METHOD! */
		
		return 0; // this line is here only so this code will compile if you don't modify it

	}
	
	/*
	 * This method is here to help you run your program. Y
	 * You may modify it as needed.
	 */
	public static void main(String[] args) {
		System.out.println(("hola".substring(2)));
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
		System.out.println(sentences);
		Set<Word> words = Analyzer.allWords(sentences);
		Map<String, Double> wordScores = Analyzer.calculateScores(words);
		double score = Analyzer.calculateSentenceScore(wordScores, sentence);
		System.out.println("The sentiment score is " + score);
	}
}
