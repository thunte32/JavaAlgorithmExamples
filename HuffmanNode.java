/**
 * The node class contains stores information for individual nodes, to be placed in a linked list
 * @author Todd Hunter
 * @version 7/25/2016
 *
 */
		
public class Node {
	//fields of the node
	int frequency = 0;
	String letter = "";
	Node left = null;
	Node right = null;
	
	//no args constructor
	public Node(){
		frequency = 0;
		letter = "";
		left = null;
		right = null;
	}
	/**
	 * sets frequency of node
	 * @param inFreq - takings incoming int as frequency
	 */
	public void setFrequency(int inFreq){
		frequency = inFreq;
	}
	/**
	 * sets letter value of node
	 * @param inLetter - takes incoming string as letter
	 */
	public void setLetter(String inLetter){
		letter = inLetter;
	}
	/**
	 * sets left child node of this node
	 * @param inNode - incoming node to become left child
	 */
	public void setLeft(Node inNode){
		left = inNode;
	}
	/**
	 * sets right child node of this node
	 * @param inNode - incoming node to become right child
	 */
	public void setRight(Node inNode){
		right = inNode;
	}
	/**
	 * gets the frequency of this node
	 * @return - returns frequency of this node as int
	 */
	public int getFrequency(){
		return frequency;
	}
	/**
	 * gets letter stored in this node
	 * @return - returns letter of this node as a string
	 */
	public String getLetter(){
		return letter;
	}
	/**
	 * gets left child of this node
	 * @return - returns node that is left child of this node
	 */
	public Node getLeft(){
		return left;
	}
	/**
	 * gets right child of this node
	 * @return - returns node that is right child of this node
	 */
	public Node getRight(){
		return right;
	}
}
