package cs601.project1;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Scanner;

import cs601.project1.model.QuestionAnswer;
import cs601.project1.model.Review;
import cs601.project1.algorithms.InvertedIndex;
import cs601.project1.builder.InvertedIndexBuilder;

/**
 * AmazonReviews is a Driver Class. It implements functionalities like
 * reading data from file, feeding data to invertedIndex and parsing
 * the command input to perform appropriate operation.
 * 
 * @author kmkhetia
 *
 */ 

public class AmazonSearch {
	/**
	 * Main logic to read file, create invertedIndex and get command input from user.
	 * 
	 * Expected usage:
	 * 	java -cp project1.jar cs601.project1.AmazonSearch -reviews <review_file_name> -qa <qa_file_name>
	 * 
	 * Example:
	 * 	java -cp project1.jar cs601.project1.AmazonSearch -reviews reviews_Cell_Phones_and_Accessories_5.json -qa qa_Cell_Phones_and_Accessories.json
	 * 
	 * @param args
	 * @throws IOException, IllegalArgumentException
	 * @return void
	 */
	public static void main(String[] args) throws IOException {
		if(!validateInput(args)) {
			System.out.println("Invalid parameters passed to program! use below command. \n java -cp project1.jar cs601.project1.AmazonSearch -reviews <review_file_name> -qa <qa_file_name> \n");
			return;
		}
		
		try {
			// Creates invertedIndex for review.
			InvertedIndex reviewIndex = (new InvertedIndexBuilder())
					.setFilePath(Paths.get(args[1]))
					.setType(Review.class)
					.build();
			
			// Creates invertedIndex for question and answer.
			InvertedIndex qaIndex = (new InvertedIndexBuilder())
					.setFilePath(Paths.get(args[3]))
					.setType(QuestionAnswer.class)
					.build();
			Scanner scn = new Scanner(System.in);
			boolean exitFlag = false; // sets exit flag to false.
			while(!exitFlag) {
				System.out.print("\nCommand: ");
				String command[] = scn.nextLine().split(" ");
				exitFlag = parseCommand(command, reviewIndex, qaIndex);
			}
		} catch (NoSuchFileException e) {
			System.out.println("Filename " + e.getMessage() + " does not exist.");
		}
	}
	
	/**
	 * The function validates the input parameters passed 
	 * while executing the program.
	 * 
	 * @param args
	 * @return boolean
	 */
	private static boolean validateInput (String[] args) {
		if((args.length != 4) || !args[0].equals("-reviews") || !args[2].equals("-qa")) {
			return false;
		}
		return true;
	}
	
	/**
	 * The function helps in parsing the commands passed to performe various
	 * operations such as search, partialsearch, find, help and exit. The 
	 * function returns true on 'exit' command which terminates the input loop.
	 * 
	 * @param command, reviewIndex and qaIndex
	 * @return boolean
	 */
	public static boolean parseCommand (String[] command, InvertedIndex reviewIndex, InvertedIndex qaIndex) {
		if((command.length < 2 && !(command[0].toLowerCase().equals("help") || command[0].toLowerCase().equals("exit"))) || command.length > 2) {
			System.out.println("Invalid command! Please try using \'help\' command.");
			return false;
		}
		// Uses the command passed and performs operations accordingly.
		switch(command[0].toLowerCase()) {
			case "find":
				InvertedIndex.find(command[1].toUpperCase());
				return false;
			case "reviewsearch":
				reviewIndex.search(command[1]);
				return false;
			case "reviewpartialsearch":
				reviewIndex.partialSearch(command[1]);
				return false;
			case "qasearch":
				qaIndex.search(command[1]);
				return false;
			case "qapartialsearch":
				qaIndex.partialSearch(command[1]);
				return false;
			case "help":
				System.out.println("List of valid commands:"
						+ "\n\t1. find <TextToSearch>"
						+ "\n\t2. reviewsearch <TextToSearch>"
						+ "\n\t3. qasearch <TextToSearch>"
						+ "\n\t4. reviewpartialsearch <TextToSearch>"
						+ "\n\t5. qapartialsearch <TextToSearch>"
						+ "\n\t6. exit");
				return false;
			case "exit":
				System.out.println("Good Bye!");
				return true; // This will termiate the input loop in main function.
			default:
				System.out.println("not a valid command, try using \'help\' command.");
				return false;
		}
	}
}
