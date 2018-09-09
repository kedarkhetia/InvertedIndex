package cs601.project1.algorithms;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import cs601.project1.model.AmazonDataStructure;
import cs601.project1.model.QuestionAnswer;
import cs601.project1.model.Review;
import cs601.project1.model.Tuple;

public class InvertedIndex {
	public static Map<String, List<AmazonDataStructure>> asinIndex = new HashMap<String, List<AmazonDataStructure>>();
	private Map<String, List<Tuple<AmazonDataStructure>>> invertedIndex = new HashMap<String, List<Tuple<AmazonDataStructure>>>();
	
	public void add(AmazonDataStructure element) {
		addToAsinIndex(element.getAsin(), element);
		if(element instanceof Review) {
			addToInvertedIndex(((Review) element).getReviewText(), element);
		}
		else if(element instanceof QuestionAnswer) {
			QuestionAnswer qa = (QuestionAnswer) element;
			addToInvertedIndex(qa.getQuestion() + " " + qa.getAnswer(), element);
		}
	}
	
	public void addToAsinIndex(String asin, AmazonDataStructure element) {
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
	
	public void addToInvertedIndex(String text, AmazonDataStructure element) {
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
	
	public static void find(String asin) {
		int count = 1;
		try {
			for(Object i : asinIndex.get(asin)) {
				System.out.println(count + ") " + i);
				count++;
			}
		} catch (NullPointerException e) {
			System.out.println("Apologies! Provided ASIN cannot be found, please try using different ASIN.");
		}
	}
	
	public void search(String text) {
		int count = 1;
		try {
			Collections.sort(invertedIndex.get(text.replaceAll("[^A-Za-z0-9 ]", "").toLowerCase()));
			for(Tuple<AmazonDataStructure> i : invertedIndex.get(text.replaceAll("[^A-Za-z0-9 ]", "").toLowerCase())) {
				System.out.println(count + ") " + i);
				count++;
			}
		} catch (NullPointerException e) {
			System.out.println("Apologies! Provided word does not exist in any document, please try using different word.");
		}
	}
	
	public void partialSearch(String text) {
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
		} catch (NullPointerException e) {
			System.out.println("Apologies! Provided word did not return any search result, please try using different word.");
		}
	}
}
