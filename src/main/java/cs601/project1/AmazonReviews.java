package cs601.project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import cs601.project1.model.AmazonDataStructure;
import cs601.project1.model.QuestionAnswer;
import cs601.project1.model.Review;
import cs601.project1.algorithms.InvertedIndex;

public class AmazonReviews {

	public static void main(String[] args) throws IOException {
		int mill1 = (int) System.currentTimeMillis();
		if(!validateInput(args)) {
			throw new IllegalArgumentException("Invalid parameters passed to program! use below command. \n java -cp project1.jar cs601.project1.AmazonSearch -reviews <review_file_name> -qa <qa_file_name> \n");
		}
		Path inputFileQA = Paths.get(args[3]);
		Path inputFileReviews = Paths.get(args[1]);
		InvertedIndex reviewIndex = new InvertedIndex();
		addToInvertedIndex(inputFileReviews, Review.class, reviewIndex);
		InvertedIndex qaIndex = new InvertedIndex();
		addToInvertedIndex(inputFileQA, QuestionAnswer.class, qaIndex);
		int mill2 = (int) System.currentTimeMillis();
		System.out.println("Time: " + ((mill2-mill1)/1000));
		Scanner scn = new Scanner(System.in);
		boolean exitFlag = false;
		while(!exitFlag) {
			System.out.print("\nCommand: ");
			String command[] = scn.nextLine().split(" ");
			exitFlag = parseCommand(command, reviewIndex, qaIndex);
		}
	}
	
	private static boolean validateInput(String[] args) {
		if((args.length != 4) || !args[0].equals("-reviews") || !args[2].equals("-qa")) {
			return false;
		}
		return true;
	}

	public static boolean parseCommand(String[] command, InvertedIndex reviewIndex, InvertedIndex qaIndex) {
		if((command.length < 2 && !(command[0].equals("help") || command[0].equals("exit"))) || command.length > 2) {
			System.out.println("Invalid command! Please try using \'help\' command.");
			return false;
		}
		switch(command[0]) {
			case "find":
				InvertedIndex.find(command[1]);
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
						+ "\n\t5. qapartialsearch <TextToSearch>");
				return false;
			case "exit":
				System.out.println("Good Bye!");
				return true;
			default:
				System.out.println("not a valid command, try using \'help\' command.");
				return false;
		}
	}
	
	public static void addToInvertedIndex(Path inputFile, Class<?> template, InvertedIndex invertedIndex) throws IOException {
		BufferedReader in = Files.newBufferedReader(inputFile, StandardCharsets.ISO_8859_1);
		String data;
		try {
			while((data = in.readLine()) != null) {
				Gson gson = new Gson();
				Object templateObject = gson.fromJson(data, template);
				invertedIndex.add((AmazonDataStructure) templateObject);
				//System.out.println(data);
			}
		} catch (JsonSyntaxException e) {
			//Ignoring JsonSyntaxException
			//System.out.println(e.getMessage());
		}
	}
}
