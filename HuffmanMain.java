import java.util.*;
import java.io.*;
/**
 * The main class contains the main() method, and the methods and logic necessary to process a text file into a list of characters and their
 * corresponding Huffman code value.
 * @author Todd Hunter
 * @version 7/25/2016
 */
public class Main {
	//Hash map to store letter and its huffman code. Stored outside to printEncoding class to protect it from recursion
	static HashMap<String, String> huffmanCode = new HashMap<String, String>();
	
	public static void main(String[] args)throws IOException {
		
		//Hard coded location for text file that is to be converted to huffman code.
		Scanner dataFile = new Scanner(new File("test.txt"));
		//hashmap that will store individual characters and their frequencies in the txt file.
		HashMap<String, Integer> frequencies = new HashMap<String, Integer>();
		
		//fills frequencies hashmap with characters as the key and number of occurances as the values for the keys
		while(dataFile.hasNext()){
			String line = dataFile.next();
			for(int i = 0; i < line.length(); i ++){
				if(frequencies.get(String.valueOf(line.charAt(i))) == null){
					frequencies.put(String.valueOf(line.charAt(i)), 1);
				}
				else
					frequencies.put(String.valueOf(line.charAt(i)), frequencies.get(String.valueOf(line.charAt(i))) + 1);
			}
		}
		dataFile.close();
		
		//Linked  list to contain nodes that create binary tree for huffman encoding process
		LinkedList<Node> myCandidates = new LinkedList<Node>();
		
		//adds nodes with corresponding letters and frequencies from the frequencies hashmap to the linked list
		for(String c : frequencies.keySet()){
			Node node = new Node();
			node.setFrequency(frequencies.get(c));
			node.setLetter(c);		
			myCandidates.add(node);
		}
		
		//organizes linked list in format for huffman encoding
		//smallest values will be on lowest levels, and left child will be less than  or equal to right child
		for(int i = myCandidates.size(); i > 1; i--){
			Node myNode1 = returnMin(myCandidates);
			Node myNode2 = returnMin(myCandidates);
			Node parent = new Node();
			if(myNode1.getFrequency() < myNode2.getFrequency()){
				parent.setLeft(myNode1);
				parent.setRight(myNode2);
			}
			else{
				parent.setLeft(myNode2);
				parent.setRight(myNode1);
			}
			parent.setFrequency(myNode1.getFrequency() + myNode2.getFrequency());
			myCandidates.add(parent);
		}
		
		//calls printEncoding method sending in root to begin huffman encoding process. Sends in blank string as root does
		//not have a corresponding letter value
		printEncoding(myCandidates.get(0), "");
		System.out.println("Letters and their corresponding huffman code: " + huffmanCode.toString());
		
		//count variables created for comparison of bits saved by huffman encoding
		int count1 = 0;
		int count2 = 0;
		//count1 equals total number of characters in text file, multiplied by 16
		//(assuming that each character is allotted 8 bits)
		for(String c : frequencies.keySet()){
			count1 += frequencies.get(c) * 8;
		}
		//count2 equals gets huffman code bits by taking the frequency multiplied by bits alloted from encoding process
		for(String c: huffmanCode.keySet()){
			int freq = 0;
			for(String d : frequencies.keySet()){
				if(c == d){
					freq = frequencies.get(d);
				}
			}
			count2 += huffmanCode.get(c).length() * freq;
		}
		//compares amount saved from huffman encoding
		System.out.println("Initial size: " + count1 + " bits." + "\t Size after Huffman encoding: " + count2 + " bits");
		System.out.println("Bits saved: " + (count1 - count2) + " bits");
	}
	
	/**
	 * returnMin method takes in a linked list of nodes, and returns then removes the minimum node
	 * @param myL - Linked list of nodes
	 * @return - returns minimum node value (based on frequency)
	 */
	public static Node returnMin(LinkedList<Node> myL){
		Node minNode = new Node();
		minNode = myL.getFirst();
		int index = 0;
		//finds minimum node based on frequency
		for(int i = 0; i < myL.size(); i++){
			if(myL.get(i).getFrequency() < minNode.getFrequency()){
				minNode = myL.get(i);
				index = i;
			}
		}
		//returns and removes minimum node
		Node temp = minNode;
		myL.remove(index);
		return temp;
	}
	/**
	 * printEncoding method begins at the root node and goes to its left and right nodes recursively until it hits the leaf nodes.
	 * It records its path with 0 for a left path and 1 for a right path, and adds this and the letter value of the leaf node, once
	 * the leaf node is reached.
	 * @param root - incoming node whose left and right child will be searched if they contain children.
	 * @param code - incoming code that has previous route, by 0's and 1's, stored.
	 */
	public static void printEncoding(Node root, String code){
		//recursively calls itself if root is not a leaf node. adds 0 if going left, and 1 if going right
		if(root.getLeft() != null && root.getRight()!= null){
			printEncoding(root.getLeft(), code + "0");
			printEncoding(root.getRight(), code + "1");
		}
		//leaf node has been reached, adds letter and its path code to huffmanCode hashmap
		else{
			huffmanCode.put(root.getLetter(), code);
		}
	}
}
