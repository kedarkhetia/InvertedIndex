package cs601.project1.model;

/**
 * A class store each document along with the frequency of given word.
 * 
 * @author kmkhetia
 */
public class Tuple<e> implements Comparable<Tuple>{
	private e object;
	private int frequency;
	
	public e getObject() {
		return object;
	}
	public void setObject(e object) {
		this.object = object;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	@Override
	public int compareTo(Tuple tuple) {
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
	
	@Override 
	public String toString() {
		return object.toString();
	}
	
}
