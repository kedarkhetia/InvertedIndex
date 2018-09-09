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
	
	public String getReviewerID() {
		return reviewerID;
	}
	public void setReviewerID(String reviewerID) {
		this.reviewerID = reviewerID;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public double getOverall() {
		return overall;
	}
	public void setOverall(double overall) {
		this.overall = overall;
	}
	
	@Override
	public String toString() {
		return "ASIN: " + this.asin + "\nReviewId: " + this.reviewerID + "\nReviewText: " + this.reviewText + "\nOverall: " + this.overall + "\n";
	}
}
