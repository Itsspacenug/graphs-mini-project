import java.util.Arrays;
import java.io.IOException;

public class AirlineRoutes
{

	public static void main(String args[]) throws IOException{
    	Graph map = new Graph();
    	System.out.println(map);
    	
    	System.out.println("CheapestRoute Tests:");

		System.out.println("\tCheapest Route From SFO to JFK");
    	String route = map.cheapestRoute("SFO", "JFK");
		System.out.println("\t"+route);

		System.out.println("\n\tCheapest Route From PHX to MIA");
    	route = map.cheapestRoute("PHX", "MIA");
		System.out.println("\t"+route);

		System.out.println("\n\tCheapest Route From HNL to BOS");
    	route = map.cheapestRoute("HNL", "BOS");
		System.out.println("\t"+route);

    	System.out.println("FindRoute Tests:");
    	
    	System.out.println("\tSFO to JFK, 1 hop");
    	route = map.findRoute(1, "SFO", "JFK");
		System.out.println("\t"+route);

    	System.out.println("\n\tPHX to MIA, 2 hops");
    	route = map.findRoute(2, "PHX", "MIA");
		System.out.println("\t"+route);

    	System.out.println("\n\tSFO to JFK, 4 hops");
    	route = map.findRoute(4, "SFO", "JFK");
		System.out.println("\t"+route);

    	System.out.println("\n\tHNL to BOS, 5 hops");
    	route = map.findRoute(5, "HNL", "BOS");
		System.out.println("\t"+route);


		


    }
}