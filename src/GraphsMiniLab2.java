import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;
import java.util.Scanner;

/*************************************
 * Java Built-in Linked List Class:
 *
 *	-Constructor Examples
 *		LinkedList<String> list = new LinkedList<String>();
 *		LinkedList list2 = new LinkedList();
 *	
 *	-Add Example:
 *		list.add("red");
 *	
 *	-Some Relevant Methods:
 *		add(E e)
 *		add(int index, E element)
 *		addFirst()
 *		addLast()
 *		removeFirst()
 *		removeLast()
 *
 *		element() - Retrieves, but does not remove, the head (first element) of this list.
 *		getFirst() - Returns the first element in this list.
 *		getLast() - Returns the last element in this list.
 *		get(int index) - Returns the element at the specified position in this list.
 *
 *		set(int index, E element) - Replaces the element at the specified position in this list with the specified element.
 *
 *		size() - Returns the number of elements in this list
 *
 *************************************/

public class GraphsMiniLab2
{
	static final int numOfVertices = 10;
	static LinkedList[] adjList;
		
	public static void main (String[] args) throws IOException
	{
		adjList = new LinkedList[numOfVertices];
		
		//The above way of using the the Java LinkedList class requires
		//us to itialize each index of our array as a new LinkedList, or 
		//else it will consider it to be null.
		for(int x = 0; x<numOfVertices; x++)
		 {
		 	adjList[x] = new LinkedList();
		 }
		 
		/* 1) CREATE A Scanner using the file named "edgesB.txt" or "edgesC.txt"
		 *		-edgesB - the small Practice Traversal graph
		 *		-edgesC - the large Practice Traversal graph
		 * 2) USE the Scanner to fill in an Adjacency List or Matrix.
		 *		-Keep in mind that the order we travel matters.
		 *		-It would be best to have your list of connected/adjacent
		 *			vertices sorted.
		 * 3) PRINT the order of your list using DFT and BFT
		 */
		Scanner sc1 = new Scanner(new File("edgesB.txt"));
		ArrayList<String> edge1 = new ArrayList<>();
		printAdjList(edge1);
		Scanner sc2 = new Scanner(new File("edgesC.txt"));
		ArrayList<String> edge2 = new ArrayList<>();
		printAdjList(edge2);
		
		depthFirstTraversal();
	}
	
	static void printAdjList(ArrayList<String> edgeList){
		/* Print an adjacency list given
		 * 	a list of a Graph's edges.
		 *	
		 *	-Don't worry about sorting the Linked Lists
		 */
		 
		 LinkedList[] adjList = new LinkedList[numOfVertices];
		 
		//The above way of using the the Java LinkedList class requires
		//us to initialize each index of our array as a new LinkedList, or 
		//else it will consider it to be null.
		for(int x = 0; x < numOfVertices; x++)
		 {
		 	adjList[x] = new LinkedList();
		 }
		 
		for(String edge : edgeList) {
			Scanner scanner = new Scanner(edge.replace(',', ' '));
			int num = scanner.nextInt();
			int num2 = scanner.nextInt();
			adjList[num].add(num2);
		}
		
		/*for(int i =0; i < adjList.length; i++) {
			System.out.println(i + ": " + adjList[i].toString());
		}*/
	}
	public static void depthFirstTraversal(){
		/* Print out the order of your graph in 
		 * Depth First Traversal.
		 *
		 * CHOOSE one development method: 
		 *		a) Recursive
		 *		b) Using a Stack - You can use the built-in class.
		 */
		boolean[] visited = new boolean[adjList.length];
		Stack<Integer> stack = new Stack<>();
		stack.push(adjList)
	}
	
	private static void dft(int vertex, Boolean visited[]){
		/* If you choose to implement dft recursively, initiate the 
		 * search using the above method, but make this method do all the heavy lifting.
		 */
	}
	public static void breadthFirstTraversal(){
		/* Print out the order of your graph in
		 * Breadth First Traversal.
		 *	
		 *	-Be sure to use a queue to manage the order in which you visit the vertices.
		 */
		 
	}

}