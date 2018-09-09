package cs601.project1.fileio;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import cs601.project1.algorithms.InvertedIndex;
import cs601.project1.model.AmazonDataStructure;

public class FileIO {
	
	public void addToInvertedIndex(Path inputFile, Class<?> template, InvertedIndex invertedIndex) throws IOException {
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
			//System.out.println("JsonSyntaxException Occured!");
		}
	}
	
}
