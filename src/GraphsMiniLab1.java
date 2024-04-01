import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;

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

public class GraphsMiniLab1
{
	final static int numOfVertices = 10;
		
	public static void main (String[] args) throws IOException
	{
		/* 1) Create a scanner and parse through a file named "edgesA.txt" 
		 * 2) Store the edges in a String ArrayList
		 * 3) Pass the list of edges into the 2 methods below
		 */
		Scanner input = new Scanner(new File("edgesA.txt"));
		ArrayList<String> edges = new ArrayList<>();
		
		while(input.hasNextLine()) {
			edges.add(input.nextLine());
		}
		printAdjMatrix(edges);
		printAdjList(edges);
	}
	
	static void printAdjMatrix(ArrayList<String> edgeList){
		/* Print an adjacency matrix given
		 * 	a list of a Graph's edges.
		 */
		
		boolean[][] matrix = new boolean[numOfVertices][numOfVertices];
		for(String edge : edgeList) {
			Scanner scanner = new Scanner(edge.replace(',', ' '));
			int num = scanner.nextInt();
			int num2 = scanner.nextInt();
			matrix[num][num2] = true;
		}
		for(int r = 0; r < matrix.length; r++) {
			for(int c = 0; c < matrix[r].length; c++) {
				System.out.println(matrix[r][c]? "1" : "0");
			}
			System.out.println();
		}
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
	}

}