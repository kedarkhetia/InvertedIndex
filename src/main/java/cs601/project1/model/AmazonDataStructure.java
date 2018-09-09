package cs601.project1.model;

/**
 * AmazonDataStructure is an abstract class that acts as a parent class
 * for Review and QuestionAnswer classes.
 * 
 * @author kmkhetia
 */
public abstract class AmazonDataStructure {
	String asin;

	public String getAsin() {
		return asin;
	}
	public void setAsin(String asin) {
		this.asin = asin;
	}
}
