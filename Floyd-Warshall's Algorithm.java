import java.util.*;
import java.io.*;
/**
 * The main class contains the main() method, and reads in a hard coded txt file containing a group of nodes with weights to connected edges.
 * The class then prints out the shortest path (i,j,0) matrix, calculates its shortest any path, then prints out the SP(i,j,n) matrix.
 * @author Todd Hunter
 * @version 8/8/2016
 */
public class Main {

	public static void main(String[] args) throws IOException {
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
			//creates original adjacency matrix, and uses 10000000 value as representation for infinity
			int [][] firstMatrix = new int[count][count];
			for(int i = 0; i < count; i++){
				for(int j = 0; j < count; j++)
					firstMatrix[i][j] = 10000000;
			}
			//array to hold node character value in array
			String [] nodeInt = new String [count];
			//array holding string representation of nodes and their weights
			String fileLines [] = new String [count];
			count = 0;
			dataFile = new Scanner(new File("test.txt"));
			//fills fileLines array with nodes and weights from hardcoded txt file
			while(dataFile.hasNextLine()){
				String line = dataFile.nextLine();
				if(line.length() > 0){
				fileLines[count] = line;
				count ++;
				}
			}
			dataFile.close();
			
			dataFile = new Scanner(new File("test.txt"));
			count = 0;
			//initializes nodeInt array to the nodes character values
			while(dataFile.hasNextLine()){
				nodeInt[count] = String.valueOf(dataFile.nextLine().charAt(0));
				count++;
			}
			
			dataFile = new Scanner(new File("test.txt"));
			//fills original adjacency matrix to its actual values. Nodes touching themseleves get a 0 value, and nodes more than one order away
			//from each other remain at the "infinity" value
			for(int i = 0; i < fileLines.length; i++){
				String currLine = fileLines[i];
				String [] allVertices = currLine.split(",");
				for(int j = 1; j < allVertices.length;  j++){
					for(int k = 0; k < nodeInt.length; k++){
						if(allVertices[0].equals(nodeInt[k]))
							firstMatrix[k][k] = 0;
						if(allVertices[j].equals( nodeInt[k])){
							firstMatrix[i][k] = Integer.parseInt(allVertices[j-1]);
						}
					}
				}
			}
			//prints original adjacency matrix
			System.out.println("\t--------------- SP(i,j,0) Matrix --------------- ");
			for(int i = 0; i < firstMatrix.length; i++){
				System.out.print("|");
				for(int j = 0; j < firstMatrix[i].length; j++)
					System.out.printf("%12d", firstMatrix[i][j]);
				System.out.print("\t|");
				System.out.println();
			}
			//creates second matrix to hold shortest path using any nodes matrix (sp(i,j,n) matrix)
			//initializes to the original adjacency matrix
			int secondMatrix[][] = new int [firstMatrix.length][firstMatrix.length];
			for(int i = 0; i < secondMatrix.length; i++){
				for(int j = 0; j < secondMatrix[i].length; j++){
					secondMatrix[i][j] = firstMatrix[i][j];
				}
			}
			//stores shortest path using any path of nodes into the secondary matrix.
			//if original adjacency matrix contained the shortest path, it will not be changed
			for(int k = 0; k < count; k++){
				for(int i = 0; i < count; i++){
					for(int j = 0; j < count; j++){
						if(firstMatrix[i][k] + firstMatrix[k][j] < firstMatrix[i][j]){
							if(i != j)
								secondMatrix[i][j] = firstMatrix[i][k] + firstMatrix[k][j];
						}
					}
				}
			}
			//prints on sp(i,j,n) matrix
			System.out.println();
			System.out.println("\t -------------- SP(i,j,n) Matrix ---------------");
			for(int i = 0; i < secondMatrix.length; i++){
				System.out.print("|");
				for(int j = 0; j < secondMatrix[i].length; j++)
					System.out.printf("%12d", secondMatrix[i][j]);
				System.out.print("\t|");
				System.out.println();
			}
	}

}
