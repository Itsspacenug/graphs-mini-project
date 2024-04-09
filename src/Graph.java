import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Graph {
	private int[][] graph;
	private Stack<Integer> stack;
	
	/*Initializes a newly created Graph object. Initializes an empty stack and adjacency matrix,
	which must be a 10 X 10 grid. This method should also fill in the graph by setting the points
	contained in the data file "connections.dat" to be the designated value.*/
	public Graph() throws IOException {
		graph = new int[10][10];
		Scanner sc = new Scanner(new File("connections.dat"));
		
		while(sc.hasNextLine()) {
			Scanner cut = new Scanner(sc.nextLine().replace(',', ' '));
			int row = cut.nextInt();
			int col = cut.nextInt();
			int val = cut.nextInt();
			graph[row][col] = val;
		}
		stack = new Stack<Integer>();
	}
	
	//Returns the index position of the specified airportCode.
	private int findAirportCode(String airportCode) {
		return 0;
		 	
	}
	
	//Returns true if Point edge is connected, false otherwise.
	private boolean adjacent(Point edge) {
		return false;
		
	}
	
	/*Takes in an airport code and returns an array filled with the lowest
	cost to get from the source to every other city/airport.*/
	public int[] shortestPath(String source) {
		return null;
		
	}
	
	/*Takes in 2 airport codes and returns a String containing all the cities
	visited from <start> to <end> in the order visited followed by the
	total fare. This method should prioritize the cost of the trip.*/
	public String cheapestRoute (String start, String end) {
		return end;
		
	}
	
	/*Returns true if a path of length <length> exists between the two
	points in Point/Edge <p> OR returns the cost to go from start to
	finish. If a path exists, the index values for each airport visited is
	pushed onto the stack.*/
	private boolean findPath(int length, Point p){
		/*if length equals 1
			if adjacent(p)
				push the ending city onto the stack
				return true
		else
			for every node in the graph
				if (node is adj. to p.x) && findPath(length – 1, Point(node, p.y))
					push the current city/node onto the stack
					return true*/
		return false;
	}
	
	/*If a path with the specified length exists, return a String containing all
	the cities visited from <start> to <end> in the order visited followed
	by the total fare. Return “There is no such connection!” otherwise.
	This method should prioritize the length of the path rather than the
	cost.*/
	public String findRoute(int length, String start, String end) {
		return end;
		
	}
	
	//Returns a String representation of this Graph object.
	public String toString() {
		for(int r=0 ; r< graph.length; r++) {
			for(int c=0; c< graph[r].length ; c++) {
				System.out.print(graph[r][c] + " ");
			}
			System.out.println();
		}
		return"";
	}
}
