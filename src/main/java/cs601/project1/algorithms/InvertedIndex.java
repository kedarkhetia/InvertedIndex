package cs601.project1.algorithms;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import cs601.project1.model.AmazonDataStructure;
import cs601.project1.model.QuestionAnswer;
import cs601.project1.model.Review;

/**
 * InvertedIndex class implements Inverted Index data structure
 * which is used for efficient searching based on keywords. It supports
 * operations such as add to InvertedIndex, find asin, search text and
 * partially search text.
 * 
 * @author kmkhetia
 *
 */
public class InvertedIndex {
	// Static map for find function which stores the documents based on ASIN as index key.
	private static Map<String, List<AmazonDataStructure>> asinIndex = new HashMap<String, List<AmazonDataStructure>>();
	// Map to store inverted index for any document.
	private Map<String, List<Tuple<AmazonDataStructure>>> invertedIndex = new HashMap<String, List<Tuple<AmazonDataStructure>>>();
	
	/**
	 * Tuple class is used to store the frequency of text
	 * appearing in an object. It also implements comparable
	 * interface for sorting objects of type tuple.
	 * 
	 * @author kmkhetia
	 *
	 */
	static class Tuple<e> implements Comparable<Tuple>{
		private e object;
		private int frequency;
		
		/**
		 * Sets the object value.
		 * 
		 * @param object
		 * @return void
		 */
		public void setObject (e object) {
			this.object = object;
		}
		
		/**
		 * Sets the frequency value.
		 * 
		 * @param frequency
		 * @return void
		 */
		public void setFrequency (int frequency) {
			this.frequency = frequency;
		}
		
		/**
		 * It compares two tuple values.
		 * 
		 * @param tuple
		 * @return int
		 */
		@Override
		public int compareTo (Tuple tuple) {
			if(frequency == tuple.frequency) {
				return 0;
			}
			else if(frequency > tuple.frequency) {
				return -1;
			}
			else {
				return 1;
			}
		}
		
		/**
		 * The function returns the string representation of an object.
		 * 
		 * @return String
		 */
		@Override 
		public String toString() {
			return object.toString();
		}
		
	}
	
	/**
	 * The operation helps to add document to invertedIndex and asinIndex
	 * 
	 * @param element
	 * @return void
	 */
	public void add (AmazonDataStructure element) {
		addToAsinIndex(element.getAsin(), element);
		if(element instanceof Review) {
			addToInvertedIndex(((Review) element).getReviewText(), element);
		}
		else if(element instanceof QuestionAnswer) {
			QuestionAnswer qa = (QuestionAnswer) element;
			addToInvertedIndex(qa.getQuestion() + " " + qa.getAnswer(), element);
		}
	}
	
	/**
	 * This operation sorts documents based on 
	 * the frequency of the word appering in that
	 * document.
	 * 
	 * @return void
	 */
	public void sort() {
		for(String i : invertedIndex.keySet()) {
			Collections.sort(invertedIndex.get(i));
		}
	}
	
	/**
	 * The operation helps to add document to asinIndex if it 
	 * doesn't exist, otherwise it updates the index with 
	 * new document.
	 * 
	 * @param asin and element
	 * @return void
	 */
	public void addToAsinIndex (String asin, AmazonDataStructure element) {
		if(asinIndex.containsKey(asin)) {
			if(!asinIndex.get(asin).contains(element)) {
				asinIndex.get(asin).add(element);
			}
		}
		else {
			List<AmazonDataStructure> list = new LinkedList<AmazonDataStructure>();
			list.add(element);
			asinIndex.put(asin, list);
		}
	}
	
	/**
	 * The operation helps to add document to invertedIndex if it 
	 * doesn't exist, otherwise it updates the index with 
	 * new document. It also calculates the frequency of each word
	 * appearing in document.
	 * 
	 * @param text and element
	 * @return void
	 */
	public void addToInvertedIndex (String text, AmazonDataStructure element) {
		String[] splitText = text.replaceAll("[^A-Za-z0-9 ]", "").toLowerCase().split(" ");
		Map<String, Integer> frequency = new HashMap<String, Integer>(); 
		for(String i : splitText) {
			if(frequency.containsKey(i)) {
				frequency.put(i, frequency.get(i) + 1);
			}
			else {
				frequency.put(i, 1);
			}
		}
		for(Map.Entry<String, Integer> i : frequency.entrySet()) {
			Tuple<AmazonDataStructure> tuple = new Tuple<AmazonDataStructure>();
			tuple.setObject(element);
			tuple.setFrequency(i.getValue());
			if(invertedIndex.containsKey(i.getKey())) {
				invertedIndex.get(i.getKey()).add(tuple);
			}
			else {
				List<Tuple<AmazonDataStructure>> list = new LinkedList<Tuple<AmazonDataStructure>>();
				list.add(tuple);
				invertedIndex.put(i.getKey(), list);
			}
		}
	}
	
	/**
	 * The operation implements find operation on asinIndex 
	 * for searching any document for given ASIN.
	 * 
	 * @param asin
	 * @return void
	 */
	public static void find (String asin) {
		int count = 1;
		try {
			for(Object i : asinIndex.get(asin)) {
				System.out.println(count + ") " + i);
				count++;
			}
		} catch(NullPointerException e) {
			System.out.println("Apologies! Provided ASIN cannot be found, please try using different ASIN.");
		}
	}
	
	/**
	 * The operation implements search operation for a given text.
	 * It prints documents that contains given word. 
	 * 
	 * @param text
	 * @return void
	 */
	public void search (String text) {
		int count = 1;
		try {
			for(Tuple<AmazonDataStructure> i : invertedIndex.get(text.replaceAll("[^A-Za-z0-9 ]", "").toLowerCase())) {
				System.out.println(count + ") " + i);
				count++;
			}
		} catch(NullPointerException e) {
			System.out.println("Apologies! Provided word does not exist in any document, please try using different word.");
		}
	}
	
	/**
	 * The operation implements the partialsearch functionality.
	 * It prints documents that contains either the entire word 
	 * or even if the words are partially appearing in other words.
	 * For example 'he' word appears in 'help'
	 * 
	 * @param text
	 * @return void
	 */
	public void partialSearch (String text) {
		int count = 1;
		try {
			for(String i : invertedIndex.keySet()) {
				if(i.contains(text.replaceAll("[^A-Za-z0-9 ]", "").toLowerCase())) {
					for(Tuple<AmazonDataStructure> j : invertedIndex.get(i)) {
						System.out.println(count + ") " + j);
						count++;
					}
				}
			}
			if(count == 1) {
				throw new NullPointerException();
			}
		} catch(NullPointerException e) {
			System.out.println("Apologies! Provided word did not return any search result, please try using different word.");
		}
	}
}
