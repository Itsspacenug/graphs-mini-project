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
    int[] cameFrom;
	
	/*Initializes a newly created Graph object. Initializes an empty stack and adjacency matrix,
	which must be a 10 X 10 grid. This method should also fill in the graph by setting the points
	contained in the data file "connections.dat" to be the designated value.*/
	public Graph() throws IOException {
		graph = new int[SIZE][SIZE];
		Scanner sc = new Scanner(new File("connections.dat")); //takes in the file
		
		while(sc.hasNextLine()) { //goes through
			Scanner cut = new Scanner(sc.nextLine().replace(',', ' ')); //organizes the while line
			int row = cut.nextInt(); //cuts
			int col = cut.nextInt(); //cuts
			int val = cut.nextInt(); //cuts
			graph[row][col] = val; //adds
		}
		stack = new Stack<Integer>();
		cameFrom = new int[SIZE];
		Arrays.fill(cameFrom, -1); // Initialize predecessor array with -1
	}
	
	//Returns the index position of the specified airportCode.
	public int findAirportCode(String airportCode) {
		for (int i = 0; i < SIZE; i++) {
            if (AirlineGraph.airportCode[i].equals(airportCode)) { //each index
                return i; //returns specific code
            }
        }
        return -1; // Airport code not found
		 	
	}
	
	//Returns true if Point edge is connected, false otherwise.
	public boolean adjacent(Point edge) {
		return graph[edge.x][edge.y] != 0; //if on the graph returns true
		
	}
	
	/*Takes in an airport code and returns an array filled with the lowest
	cost to get from the source to every other city/airport.*/
	public int[] shortestPath(String source) {
		int n = SIZE;
        int[] smallestWeight = new int[n];
        boolean[] visited = new boolean[n];

        
        Arrays.fill(smallestWeight, INF); //initalize each with a big number so it can find the smallest value
        Arrays.fill(visited, false); //makes all false

        
        int sourceIndex = findAirportCode(source); //finds the index of the source airport

        
        smallestWeight[sourceIndex] = 0; //cost to 0

        
        for (int count = 0; count < n - 1; count++) { // goes over all vertices
            int minIndex = findMinIndex(smallestWeight, visited); //find the vertex with the smallest weight
            visited[minIndex] = true;

            // Update the weights of adjacent vertices if the new weight is smaller
            for (int i = 0; i < n; i++) {
                if (!visited[i] && graph[minIndex][i] != 0 &&
                        smallestWeight[minIndex] != INF &&
                        smallestWeight[minIndex] + graph[minIndex][i] < smallestWeight[i]
                   ){ //parameters to be the smallest cost
                    smallestWeight[i] = smallestWeight[minIndex] + graph[minIndex][i];
                    cameFrom[i] = minIndex; //adds where the cost came from
                }
            }
        }

        return smallestWeight;
	}
	
	public int findMinIndex(int[] smallestWeight, boolean[] visited) {
        int min = INF;
        int minIndex = -1;

        for (int i = 0; i < SIZE; i++) { //goes over each index
            if (!visited[i] && smallestWeight[i] <= min) { //if it hasnt been visited and is smaller than the set cost
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
		int[] costs = shortestPath(start);
		Stack<String> route = new Stack<String>();
		 //finds each index
		int sNum = findAirportCode(start);
		int eNum = findAirportCode(end);
		int index = eNum;
		while(index != sNum) { //while its not the beginning
			route.push(airportCode[index]); //adds to stack so it can forwards
			index = cameFrom[index]; //changes the index to where the cost came from
		}
		String str = route.pop();
		while(!route.isEmpty()) {
			str+= " to " +route.pop();
		}
		return str;
		
	}
	
	/*Returns true if a path of length <length> exists between the two
	points in Point/Edge <p> OR returns the cost to go from start to
	finish. If a path exists, the index values for each airport visited is
	pushed onto the stack.*/
	public boolean findPath(int length, Point p){
		//all from the instruction
		if (length == 1) {
            if (adjacent(p)) {
                stack.push(p.y);
                return true;
            } else {
                return false;
            }
        } else {
            for (int i = 0; i < SIZE; i++) {
                if (graph[p.x][i] != 0 && findPath(length - 1, new Point(i, p.y))) {
                    stack.push(i);
                    return true;
                }
            }
            return false;
        }
	}
	
	/*If a path with the specified length exists, return a String containing all
	the cities visited from <start> to <end> in the order visited followed
	by the total fare. Return �There is no such connection!� otherwise.
	This method should prioritize the length of the path rather than the
	cost.*/
	public String findRoute(int length, String start, String end) {
		int startIdx = findAirportCode(start); //index
        int endIdx = findAirportCode(end); //index
        if (startIdx == -1 || endIdx == -1) { //makes sure the input isnt wrong
            return "There is no such connection";
        }
        stack.clear(); //reuses the stack 
        if (findPath(length, new Point(startIdx, endIdx))) {//calls findPath to see if there is a path
            StringBuilder route = new StringBuilder();
            int prev = startIdx; //track the prev index
            int totalFare = 0; //tracks total toll 
            while (!stack.isEmpty()) { //while stack is not empty
                int curr = stack.pop(); //takes into stack
                route.append(AirlineGraph.city[curr]).append(" to "); //adds the name of city
                totalFare += graph[prev][curr]; //adds the total fare
                prev = curr;
            }
            route.append(AirlineGraph.city[endIdx]).append(" -> Total fare: ").append(totalFare); //adds the final fare
            return route.toString(); //prints
        } else { //if findPath is empty
            return "There is no such connection!";
        }
		
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
	// test methods to check if the methods work
	public String shortestPathToString(int[] smallestWeight, String source) {
		StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sb.append(graph[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
	
	//checks to see if the variable prints correctly
	public String cameFromToString(int[] cameFrom, String source) {
        StringBuilder sb = new StringBuilder();
        sb.append("Shortest Path Came From ").append(source).append(": ");
        sb.append(Arrays.toString(cameFrom));
        return sb.toString();
    }
}
