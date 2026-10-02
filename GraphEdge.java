/* GraphEdge
 * This class represents one weighted edge in the ZipRide city graph.
 * Each edge stores the destination location and the driving time in minutes.
 */

public class GraphEdge
{
	private String destination;
	private int weight;

	// Constructor 
	public GraphEdge(String destination, int weight)
	{
		this.destination = destination;
		this.weight = weight;
	} 

	// getDestination 
	public String getDestination()
	{
		return destination; // return the destination location of this edge
	}

	// getWeight
	public int getWeight()
	{
		return weight; // return the driving time of this edge
	}

	// toString
	public String toString()
	{
		return destination + "(" + weight + " mins)";
		// return a readable format for for printing the edge
	}
}


