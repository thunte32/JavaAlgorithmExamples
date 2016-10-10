import java.util.*;
import java.io.*;
import java.sql.Array;
/**
 * The main class contains the main() method. It will read in a text file, and output the edges that make the minimal spanning tree, and
 * the total distance to traverse the MST.
 * @author Todd Hunter
 * @version 8/1/2016
 */
public class Main {
	
	//hashmap with the nodes as the key, and their last parent nodes as the value
	static HashMap<String, String> parentArray;
	
	public static void main(String[] args)throws IOException {
		//count for initializing arrays
		int count = 0;
		//hard coded location of text file to be read in
		Scanner dataFile = new Scanner(new File("test.txt"));
		//loop obtaining number of nodes, ignoring any spaces inbetween lines
		while(dataFile.hasNextLine()){
			String line = dataFile.nextLine();
			if(line.length() > 0){
			count ++;
			}
		}
		dataFile.close();
		
		//Initialize parent array so each node has itself as a parent
		parentArray = new HashMap<String, String>();
		dataFile = new Scanner(new File("test.txt"));
		while(dataFile.hasNextLine()){
			String line = String.valueOf(dataFile.next().charAt(0));
			parentArray.put(line, line);
		}
		dataFile.close();
		
		//array of strings that contain information about nodes, and their edges and weights from txt file
		String fileLines [] = new String [count];
		count = 0;
		dataFile = new Scanner(new File("test.txt"));
		while(dataFile.hasNextLine()){
			String line = dataFile.nextLine();
			if(line.length() > 0){
			fileLines[count] = line;
			count ++;
			}
		}
		dataFile.close();
		
		//Array list containing all edges
		ArrayList<Edge> myEdges = new ArrayList<Edge>();
		//hashmap with edges as key, and boolen as value. False if edge is not in list, is set to true once edge is added to list
		HashMap<String, Boolean> duplicate = new HashMap<String, Boolean>();
		for(int i = 0; i < fileLines.length; i++){
			String currLine = fileLines[i];
			String [] allVertices = currLine.split(",");
			for(int j = 1; j <= allVertices.length - 1; j = j+2){
				duplicate.put(allVertices[0] + allVertices[j+1], false);
				duplicate.put(allVertices[j+1] + allVertices[0], false);
			}
		}
	
		//Loop to fill array list with edges
		for(int i = 0; i < fileLines.length; i++){
			String currLine = fileLines[i];
			String [] allVertices = currLine.split(",");
			//loop adds edge if it and its reverse have not been marked as true in the duplicate hashmap
			for(int j = 1; j <= allVertices.length - 1; j = j+2){
				if(duplicate.get(allVertices[0] + allVertices[j+1]) != true && duplicate.get(allVertices[j+1] + allVertices[0]) != true){
				Edge myEdge = new Edge();
				myEdge.weight = Integer.parseInt(allVertices[j]);
				myEdge.node1 = allVertices[0];
				myEdge.node2 = allVertices[j+1];
				myEdges.add(myEdge);
				duplicate.put(allVertices[0] + allVertices[j+1], true);
				duplicate.put(allVertices[j+1] + allVertices[0], true);
				}
			}
		}
		//sorts myEdges array list
		Collections.sort(myEdges);
		//initialize accumulator
		int accumulator = 0;
		System.out.println("MST Edges:");
		//loop to print edge and add to accumulator if parents of the nodes are not equal
		for(int i = 0; i < myEdges.size(); i ++){
			if(!find(myEdges.get(i).node1).equals(find(myEdges.get(i).node2))){
				System.out.println(myEdges.get(i).node1 + myEdges.get(i).node2);
				accumulator = accumulator + myEdges.get(i).weight;
				//calls union method to update node 1 parent once connected to the MST
				union(parentArray.get(myEdges.get(i).node1), parentArray.get(myEdges.get(i).node2));
				
			}
		}
		System.out.println("Total Weight: " + accumulator);
	}
	/**
	 * find method takes in string and returns that strings value from parentArray hashmap
	 * @param n - incoming string value to be input as key in hashmap
	 * @return returns last parent input node
	 */
	public static String find(String n){
		return parentArray.get(n);
	}
	
	/**
	 * union method updates a nodes parent to the last parent of the its connecting node, connecting it to the MST
	 * @param s1 string value of node 1
	 * @param s2 string value of node 2
	 */
	public static void union(String s1, String s2){
		//updates last parent node of node 1, if is parent node has changed its last parent.
		for(HashMap.Entry<String, String> entry: parentArray.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			
			if(value == s1)
				parentArray.put(key, s2);
		}
	}
}
