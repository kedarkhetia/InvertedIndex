package cs601.project1;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import cs601.project1.model.QuestionAnswer;
import cs601.project1.model.Review;
import cs601.project1.algorithms.InvertedIndex;
import cs601.project1.fileio.FileIO;

public class AmazonReviews {

	public static void main(String[] args) throws IOException {
		Path inputFileQA = Paths.get(args[3]);
		Path inputFileReviews = Paths.get(args[1]);
		FileIO reader = new FileIO();
		InvertedIndex reviewIndex = new InvertedIndex();
		reader.addToInvertedIndex(inputFileReviews, Review.class, reviewIndex);
		InvertedIndex qaIndex = new InvertedIndex();
		reader.addToInvertedIndex(inputFileQA, QuestionAnswer.class, qaIndex);
		
		Scanner scn = new Scanner(System.in);
		
		while(true) {
			System.out.print("\nCommand: ");
			String command[] = scn.nextLine().split(" ");
			parseCommand(command, reviewIndex, qaIndex);
		}
	}
	
	public static void parseCommand(String[] command, InvertedIndex reviewIndex, InvertedIndex qaIndex) {
		switch(command[0]) {
			case "find":
				InvertedIndex.find(command[1]);
				break;
			case "reviewsearch":
				reviewIndex.search(command[1]);
				break;
			case "reviewpartialsearch":
				reviewIndex.partialSearch(command[1]);
				break;
			case "qasearch":
				qaIndex.search(command[1]);
				break;
			case "qapartialsearch":
				qaIndex.partialSearch(command[1]);
				break;
			case "help":
				System.out.println("List of valid commands:"
						+ "\n\t1. find <TextToSearch>"
						+ "\n\t2. reviewsearch <TextToSearch>"
						+ "\n\t3. qasearch <TextToSearch>"
						+ "\n\t4. reviewpartialsearch <TextToSearch>"
						+ "\n\t5. qapartialsearch <TextToSearch>");
				break;
			case "exit":
				System.out.println("Good Bye!");
				return;
			default:
				System.out.println("not a valid command, try using \'help\' command.");
				break;
		}
	}
}
