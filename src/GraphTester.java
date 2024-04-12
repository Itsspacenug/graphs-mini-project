import java.awt.Point;
import java.io.IOException;

public class GraphTester {
	public static void main(String args[]) throws IOException{
		Graph map = new Graph();
    	System.out.println(map);
    	
    	int loc = map.findAirportCode("BOS");
    	int loc1 = map.findAirportCode("JFK");
    	/*System.out.println(loc + " " + loc1);
    	
    	boolean q = map.adjacent(new Point(loc,loc1));
    	System.out.println(q);*/
    	
    	boolean qq = map.findPath(map.SIZE, new Point(loc, loc1));
    	System.out.println(qq);
	}
}
