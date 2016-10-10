import java.util.*;
import java.io.*;
/**
 * The heap class contains methods that uses input from a file to create a heap, allow elements to be added and removed from the heap, 
 * create a sorted array from the heap, and then output the data to a text file.
 * @author Todd Hunter
 * @version 7/17/2016
 */
public class Heap {
	
	//initialization of the array lists which will hold the heap and the sorted data
	ArrayList<Integer> elements = new ArrayList<Integer>();
	ArrayList<Integer> heapSort = new ArrayList<Integer>();
	
	/**
	 * Constructor that takes in a filename and then stores that data to the elements arraylist which will become a heap.
	 * @param filename - incoming filename that holds the location of the data to be read in
	 * @throws IOException - throws exception if file location does not exist
	 */
	public Heap(String filename)throws IOException
    {
		Scanner dataFile = new Scanner(new File(filename));
		//initialize arraylist slot to 0; so that data being stored will begin in the 1 slot
        elements.add(0);
        //stores data to arraylist elements
		while(dataFile.hasNext())
        {
			elements.add(dataFile.nextInt());
		}
		//sets the 0 slot of the elements array to the number of elements added to the array
		elements.set(0, elements.size() - 1);
		dataFile.close();
	}
	/**
	 * outputResult outputs the result to a .txt file of the incoming filename string
	 * @param filename - incoming string that names the .txt file being output
	 * @throws IOException - throws exception if location does not exist
	 */
	public void outputResult(String filename) throws IOException{
		PrintWriter writer = new PrintWriter(filename);
		//sets the elements array to its final size, in case any add or remove methods were called
		elements.set(0, elements.size() -1 );
		for(int i = 0; i < elements.size(); i++){
			writer.println(elements.get(i));
		}
		writer.close();
	}
	/**
	 * siftUp will find the proper location for added data, and swap the elements accordingly to maintain heap structure
	 * @param indexOfNode - index of incoming node whose proper position must be found
	 */
	public void siftUp(int indexOfNode){
		
		//sets the value of the parent of the incoming index
		int parentValue = elements.get((indexOfNode)/2);
		//determines if swap is necessary, and excludes comparison of root to the size of the array stored in the 0 position
		if(elements.get(indexOfNode) > parentValue && indexOfNode > 1  ){
			int temp = parentValue;
			elements.set((indexOfNode)/2, elements.get(indexOfNode));
			elements.set(indexOfNode, parentValue);
			//recursive property allows the swap to be performed up the the root
			siftUp((indexOfNode)/2);
			}		
	}
	/**
	 * removeMaxElement removes the root of the heap, which contains the maximum value. Restores heap structure after removal
	 * @return int - returns value of removed element
	 */
	public int removeMaxElement(){
		int originalRoot = elements.get(1);
		//removes element from elements array slot 1, the max value
		elements.set(1, elements.get(elements.get(0)));
		elements.remove(elements.size() -1 );
		//sets the last element to the root
		elements.set(0, elements.get(0) -1 );
		//restores heap structure
		heapify(1);
		return originalRoot;
	}
	/**
	 * addElement adds a new element to the elements array, and then calls the siftUp method to store it in its proper place within the heap
	 * @param value - incoming value to be stored
	 */
	public void addElement(int value){
		//adds element to end of list, increments the size of the list, then siftsUp from its last position as necessary
		elements.add((elements.get(0) + 1), value);
		elements.set(0, elements.get(0) + 1);
		siftUp(elements.get(0));
	}
	/**
	 * heapSort uses the elements array in a heap structure to remove the max element and store it in a new array, and then restore
	 * the heap structure
	 */
	public void heapSort(){
		//while the "size" of the elements array is at least 1, add root to new array and then restore heap structure
		while(elements.get(0) > 0){
			heapSort.add(elements.get(1));
			elements.set(1, elements.get(elements.get(0)));
			elements.set(0, elements.get(0) - 1);
			//adds the last element of the elements array to the heapSort array and prevents the heapify method from running
			if(elements.get(0) == 1){
				heapSort.add(elements.get(1));
				elements.set(0, elements.get(0) - 1);
				break;
			
			}
			heapify(1);
			
		}
		//sets the elements array to the values of the heapSort array for uniform output when the outputResult method is called
		for(int i = 1; i <= heapSort.size(); i++){
			elements.set((i), heapSort.get(i-1));
		}
	}
	/**
	 *  buildHeap takes the original elements array and organizes it into a heap structure using the heapify method
	 */
	public void buildHeap()
    {
		//will heapify every parent node in the elements array
		for(int i = elements.get(0)/2; i > 0; i--)
			heapify(i);
	}
	/**
	 * heapify takes in the index position that needs to be heapified, and then creates or maintains a heap structure
	 * @param nodeIndex - incoming integer that is the index position to be heapified
	 */
	public void heapify(int nodeIndex)
    {
		//sets the children of the incoming index
		int leftIndex = nodeIndex * 2;
		int rightIndex = nodeIndex * 2 + 1;

		int temp = 0;
		//swaps the index and its left child if necessary and then recalls the heap function to restore heap structure after the swap
        if(elements.get(leftIndex) >= elements.get(nodeIndex) && elements.get(leftIndex) >= elements.get(rightIndex))
        {
			temp = elements.get(nodeIndex);
			elements.set(nodeIndex, elements.get(leftIndex));
			elements.set(leftIndex, temp);
            if(leftIndex <= elements.get(0)/2)
                heapify(leftIndex);
		}
        //same function as the previous statement, but for the indexes right child.
		else if(elements.get(rightIndex) >= elements.get(nodeIndex) && elements.get(rightIndex) >= elements.get(leftIndex))
        {
			temp = elements.get(nodeIndex);
			elements.set(nodeIndex, elements.get(rightIndex));
			elements.set(rightIndex, temp);
            if(rightIndex <= elements.get(0)/2)
                heapify(rightIndex);
        }
	}
}