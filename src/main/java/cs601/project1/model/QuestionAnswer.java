package cs601.project1.model;

/**
 * A class to represent each question answer as structure of 
 * asin, questionType, question and answer.
 * 
 * @author kmkhetia
 */
public class QuestionAnswer extends AmazonDataStructure{
	private String questionType;
	private String question;
	private String answer;
	
	QuestionAnswer(String questionType, String asin, String question, String answer) {
		this.questionType = questionType;
		this.asin = asin;
		this.question = question;
		this.answer = answer;
	}
	
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@Override
	public String toString() {
		return "ASIN: " + this.asin + "\nQuestion: " + this.question + "\nAnswer: " + this.answer + "\n";
	}
}
