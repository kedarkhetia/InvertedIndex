package cs601.project1.model;

/**
 * Tuple class is used to store the frequency of text
 * appearing in an object. It also implements comparable
 * interface for sorting objects of type tuple.
 * 
 * @author kmkhetia
 *
 */
public class Tuple<e> implements Comparable<Tuple>{
	private e object;
	private int frequency;
	
	/**
	 * Sets the object value.
	 * 
	 * @param object
	 * @return void
	 */
	public void setObject(e object) {
		this.object = object;
	}
	
	/**
	 * Sets the frequency value.
	 * 
	 * @param frequency
	 * @return void
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	/**
	 * It compares two tuple values.
	 * 
	 * @param tuple
	 * @return int
	 */
	@Override
	public int compareTo(Tuple tuple) {
		return tuple.frequency - frequency;
	}
	
	/**
	 * The function returns the string representation of an object.
	 * 
	 * @return String
	 */
	@Override 
	public String toString(){
		return object.toString();
	}
	
}
