
/**
 * Used for sorting answer scores
 * @author zhuyund
 *
 */
public class MemAnswerScore implements Comparable<MemAnswerScore> {
	public Boolean label;
	public String id;
	public float score;
	public int compareTo(MemAnswerScore o) {
		if (o.score - this.score > 0)
			return 1;
		else if  (o.score - this.score < 0)
			return -1;
		else
			return 0;
	}
}
