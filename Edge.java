/**
 *  The edge class creates an edge composed of two nodes and a weight. implements Comparable so that its collection may be sorted
 * @author Todd Hunter
 * @version 8/1/2016
 */
public class Edge implements Comparable<Edge> {
	
	//fields that make up the edge
	int weight;
	String node1;
	String node2;
	
	/**
	 * the compareTo method overrides the compareTo method of the Comparable class.
	 * @param e incoming edge for comparison
	 * @return returns int value that is result of comparison. 1 if greater than, -1 if less than and 0 if equal.
	 */
	public int compareTo(Edge e){
		if(weight > e.weight)
				return 1;
		else if (weight < e.weight)
				return -1;
		else
				return 0;
	}
}
