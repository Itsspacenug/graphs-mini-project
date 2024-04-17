import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Graph implements AirlineGraph{
	public int[][] graph;
	public Stack<Integer> stack;
	public static final int INF = Integer.MAX_VALUE;
	
	/*Initializes a newly created Graph object. Initializes an empty stack and adjacency matrix,
	which must be a 10 X 10 grid. This method should also fill in the graph by setting the points
	contained in the data file "connections.dat" to be the designated value.*/
	public Graph() throws IOException {
		graph = new int[SIZE][SIZE];
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
	public int findAirportCode(String airportCode) {
		for(int i=0; i<this.airportCode.length; i++) {
			if(Graph.airportCode[i].equals(airportCode)) {
				return i;
			}
		}
		return -1;
		 	
	}
	
	//Returns true if Point edge is connected, false otherwise.
	public boolean adjacent(Point edge) {
		int start = edge.x;
		int end = edge.y;
		return graph[start][end] >0;
		
	}
	
	/*Takes in an airport code and returns an array filled with the lowest
	cost to get from the source to every other city/airport.*/
	public int[] shortestPath(String source) {
		/*Path[] smallestWeight = new Path[SIZE];
		boolean[] weightFound = new boolean[SIZE];
		int src = findAirportCode(source);
		int costToCurrentIndex = 0;
		
		
		for(int i =0; i<SIZE ; i++) {
			smallestWeight[i] = new Path(Integer.MAX_VALUE, -1);
		}
		
		smallestWeight[0].cost = 0;
		smallestWeight[0].comingFrom = -1;
		while(true) {
			for(int i=0 ; i<SIZE ; i++) {
				costToCurrentIndex = graph[src][i];
				if(graph[src][i] != 0 && 
					!weightFound[i] &&
					graph[src][i] + costToCurrentIndex > smallestWeight[i].cost
				){
					 smallestWeight[i].cost = graph[src][i] + costToCurrentIndex;
					 smallestWeight[i].comingFrom = src;
				}
			}
			
			int nIndex =0;
			for(int c=0; c<SIZE ; c++) {
				if(!weightFound[src] && smallestWeight[src].cost < smallestWeight[nIndex].cost) {
					nIndex = c;
				}
			}
			
			src = nIndex;
			weightFound[src] = true;
			costToCurrentIndex = smallestWeight[src].cost;
			
			boolean done = true;
			for(int i=0; i<SIZE ; i++) {
				if(!weightFound[i]) {
					done = false;
				}
			}
			if(done) {
				break;
			}
		}
		return smallestWeight;*/
		int n = SIZE;
        int[] smallestWeight = new int[n];
        boolean[] visited = new boolean[n];

        // Initialize smallestWeight array with infinity and mark all vertices as unvisited
        Arrays.fill(smallestWeight, INF);
        Arrays.fill(visited, false);

        // Find the index of the source airport
        int sourceIndex = findAirportCode(source);

        // The cost to reach the source itself is 0
        smallestWeight[sourceIndex] = 0;

        // Iterate over all vertices
        for (int count = 0; count < n - 1; count++) {
            // Find the vertex with the smallest weight that has not been visited yet
            int minIndex = findMinIndex(smallestWeight, visited);
            visited[minIndex] = true;

            // Update the weights of adjacent vertices if the new weight is smaller
            for (int i = 0; i < n; i++) {
                if (!visited[i] && graph[minIndex][i] != 0 &&
                        smallestWeight[minIndex] != INF &&
                        smallestWeight[minIndex] + graph[minIndex][i] < smallestWeight[i]) {
                    smallestWeight[i] = smallestWeight[minIndex] + graph[minIndex][i];
                }
            }
        }

        return smallestWeight;
	}
	
	public int findMinIndex(int[] smallestWeight, boolean[] visited) {
        int min = INF;
        int minIndex = -1;

        for (int i = 0; i < smallestWeight.length; i++) {
            if (!visited[i] && smallestWeight[i] <= min) {
                min = smallestWeight[i];
                minIndex = i;
            }
        }

        return minIndex;
    }
	
	/*Takes in 2 airport codes and returns a String containing all the cities
	visited from <start> to <end> in the order visited followed by the
	total fare. This method should prioritize the cost of the trip.*/
	public String cheapestRoute (String start, String end) {
		Path[] costs = shortestPath(start);
		String str = "";
		int sNum = findAirportCode(start);
		int eNum = findAirportCode(end);
		int index = eNum;
		while(index != sNum) {
			str += costs[index].cost + " to ";
			index = costs[index].comingFrom;
		}
		return str;
		
	}
	
	/*Returns true if a path of length <length> exists between the two
	points in Point/Edge <p> OR returns the cost to go from start to
	finish. If a path exists, the index values for each airport visited is
	pushed onto the stack.*/
	public boolean findPath(int length, Point p){
		/*if length equals 1
			if adjacent(p)
				push the ending city onto the stack
				return true
		else
			for every node in the graph
				if (node is adj. to p.x) && findPath(length � 1, Point(node, p.y))
					push the current city/node onto the stack
					return true*/
		if(length == 1) {
			if(adjacent(p)) {
				stack.push(p.y);
				return true;
			}
		}
		else {
			for(String port : this.airportCode) {
				int loc = findAirportCode(port);
				Point current = new Point(loc,p.x);
				if(adjacent(current) && findPath(length-1, new Point(loc,p.y))) {
					stack.push(loc);
					return true;
				}
			}
		}
		return false;
	}
	
	/*If a path with the specified length exists, return a String containing all
	the cities visited from <start> to <end> in the order visited followed
	by the total fare. Return �There is no such connection!� otherwise.
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
	public String shortestPathToString(int[] smallestWeight, String source) {
        StringBuilder sb = new StringBuilder();
        sb.append("Shortest Path From ").append(source).append(": ");
        sb.append(Arrays.toString(smallestWeight));
        return sb.toString();
    }
}
class Path{
	public int cost;
	public int comingFrom;
	
	public Path(int cost, int comingFrom) {
		this.cost = cost;
		this.comingFrom = comingFrom;
	} 
}
