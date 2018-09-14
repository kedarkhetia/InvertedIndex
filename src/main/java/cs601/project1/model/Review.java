package cs601.project1.model;

/**
 * A class to represent each Review as structure of 
 * asin, reviewerId, reviewText and OverallScore.
 * 
 * @author kmkhetia
 */
public class Review extends AmazonDataStructure{
	private String reviewerID;
	private String reviewText;
	private double overall;
	
	Review(String reviewerID, String asin, String reviewText, double overall) {
		this.reviewerID = reviewerID;
		this.asin = asin;
		this.reviewText = reviewText;
		this.overall = overall;
	}
	
	/**
	 * Gets reviewerID value.
	 * 
	 * @return String
	 */
	public String getReviewerID() {
		return reviewerID;
	}
	
	/**
	 * Sets reviewerID value.
	 * 
	 * @param reviewerID
	 * @return void
	 */
	public void setReviewerID(String reviewerID) {
		this.reviewerID = reviewerID;
	}
	
	/**
	 * Gets reviewText value.
	 * 
	 * @return String
	 */
	public String getReviewText(){
		return reviewText;
	}
	
	/**
	 * Sets reviewText value.
	 * 
	 * @param reviewText
	 * @return void
	 */
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	
	/**
	 * Gets overAllScore value.
	 * 
	 * @return double
	 */
	public double getOverall(){
		return overall;
	}
	
	/**
	 * Sets overAllScore value.
	 * 
	 * @param overall
	 * @return void
	 */
	public void setOverall(double overall) {
		this.overall = overall;
	}
	
	/**
	 * Returns the string representation of Review Object.
	 * 
	 * @return String
	 */
	@Override
	public String toString(){
		return "ASIN: " + this.asin + "\nReviewId: " + this.reviewerID + "\nReviewText: " + this.reviewText + "\nOverall: " + this.overall + "\n";
	}
}
