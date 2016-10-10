import java.io.IOException;

import javax.swing.JOptionPane;
/**
 * The main class contains the main() driver
 * It has a hard coded value for the location of the data file to be opened, and for the output .txt file to be stored to
 * @author Todd Hunter
 * @version 7/17/2016
 */
public class Main {

	public static void main(String[] args) throws IOException {
		//Initializes heap with values from input file
		//change "test.txt" to name of file to be read in
		Heap myHeap = new Heap("test.txt");
		
		//tests buildHeap method of Heap class and outputs results to file
		//change "BuildHeapResult.txt" to name of file to be stored as
		myHeap.buildHeap();
		myHeap.outputResult("BuildHeapResult.txt");
		
		//tests the removeMaxElement, addElement, and heapSort methods of the Heap class and outputs results to new file
		//change "AfterHeapSortResult.txt" to name of file to be stored as
		myHeap.removeMaxElement();
		myHeap.removeMaxElement();
		
		myHeap.addElement(30);
		
		myHeap.heapSort();
		
		myHeap.outputResult("AfterHeapSortResult.txt");
	}

}
