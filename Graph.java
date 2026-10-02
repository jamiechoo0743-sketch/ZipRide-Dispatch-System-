/* 
 * Graph
 * This class represents the city road network for the ZipRide Dispatch System.
 * It uses an adjacency list to store locations and weighted undirected roads.
 */

public class Graph
{
	private String[] locations;
	private GraphEdge[][] adjacencyList;
	private int[] edgeCounts;
	private int locationCount;
	private int maxLocations;

	// Constructor
	public Graph(int maxLocations)
	{
		// store maximum graph size
		this.maxLocations = maxLocations;
		// stores all location names
		this.locations = new String[maxLocations];
		// stores all edges connected to each location
		this.adjacencyList = new GraphEdge[maxLocations][maxLocations];
		// stores the number of edges for each location 
		this.edgeCounts = new int[maxLocations];
		// graph starts with 0 locations
		this.locationCount = 0;
	}

	// addLocation
	// add a new location into the graph
	public void addLocation(String location)
	{
		if(location == null || location.equals(""))
		{
			// check fotr null or empty location name
			System.out.println("Invalid location name. ");
		}
		
		else if(findLocationIndex(location) != -1)
		{
			// prevent duplicate locations
			System.out.println(location + " already exists.");
		}

		else if(locationCount >= maxLocations)
		{
			// ensure graph does not exceed maximum size
			System.out.println("Graph is full. Cannot add more locations.");
		}

		else 
		{
			// insert new location into locations array
			locations[locationCount] = location;

			// initialise edge count for the new location
			edgeCounts[locationCount] = 0;

			// increase total location count 
			locationCount++;

			System.out.println("Location added: " + location);
		}
	}

	// addRoad
	// adds a weighted undirected road between two locations
	public void addRoad(String from, String to, int weight)
	{
		// find the array indexes of both locations
		int fromIndex = findLocationIndex(from);
		int toIndex = findLocationIndex(to);

		// check whether both locations exist in the graph
		if(fromIndex == -1 || toIndex == -1)
		{
			System.out.println("Cannot add road because one or both locations do not exists.");
		}
		
		// check whether the road weight is valid 
		else if(weight <= 0)
		{
			System.out.println("Invalid road weight.");
		}

		else
		{	
			// add edge from first lcoation to second location
			adjacencyList[fromIndex][edgeCounts[fromIndex]] = new GraphEdge(to, weight);
			// increase edge count for first location
			edgeCounts[fromIndex]++;
			
			// add edge from second location back to first location because this is an undirected graph
			adjacencyList[toIndex][edgeCounts[toIndex]] = new GraphEdge(from, weight);
			// increase edge count for second location
			edgeCounts[toIndex]++;

			System.out.println("Road added: " + from + " <-> " + to + " (" + weight + "mins)");
		}
	}


	// printGraph
	// Prints the adjacency list structure of the graph
	public void printGraph()
	{
		System.out.println("\nZipeRide City Graph: ");

		// loop through all locations in the graph
		for(int i = 0; i< locationCount; i++)
		{
			// print the current location name
			System.out.print(locations[i] + "-> ");
		
			// loop through all connected edges for this location
			for(int j = 0; j < edgeCounts[i]; j++)
			{
				// print the current location name
				System.out.print(adjacencyList[i][j]);

				// print comma between edges except the last edge
				if(j < edgeCounts[i] - 1)
				{
					System.out.print(", ");
				}
			}

		// move to the next next line after print one location
		System.out.println();
		}
	}

	// findLocationIndex
	// Finds the array index of a location
	private int findLocationIndex(String location)
	{
		// default value if location is not found
		int index = -1;

		// search through all stored locations
		for(int i = 0; i< locationCount; i++)
		{
			// check whether the current location matches
			if(locations[i].equals(location))
			{
				// store the matching index
				index = i;
			}
		}

		// return the found index or -1 if not found
	 	return index;
	}


	/*
 	* bfs
 	* Performs Breadth-First Search from a starting location
 	* Print all reachable lcoations grouped by level
 	* BFS visits visits nearby locations first before it moves further away
 	* Useful to show how location are connected by road levels
 	*/

	public void bfs(String startLocation)
	{
		int startIndex = findLocationIndex(startLocation);

		// check whether the starting location exists
		if(startIndex == -1)
		{
			System.out.println("Start location does not exist.");
		}
		else
		{
			boolean[] visited = new boolean[maxLocations];
			int[] queue = new int[maxLocations];
			int[] level = new int[maxLocations];

			int front = 0;
			int rear = 0;
			int maxLevel = 0;

			// mark start location as visited
			visited[startIndex] = true;

			// start location is level 0
			level[startIndex] = 0;

			// add start location into queue
			queue[rear] = startIndex;
			rear++;

			while(front < rear)
			{
				int currentIndex = queue[front];
				front++;

				// visit all neighbours of current location
				for(int i = 0; i < edgeCounts[currentIndex]; i++)
				{
					String neighbourName = adjacencyList[currentIndex][i].getDestination();
					int neighbourIndex = findLocationIndex(neighbourName);

					// if neighbour has not been visited then add it to queue
					if(!visited[neighbourIndex])
					{
						visited[neighbourIndex] = true;
						level[neighbourIndex] = level[currentIndex] + 1;

						if(level[neighbourIndex] > maxLevel)
						{
							maxLevel = level[neighbourIndex];
						}

						queue[rear] = neighbourIndex;
						rear++;
					}
				}
			}

			System.out.println("\nBFS from " + startLocation + ":");
	
			// print locations level by level
			for(int currentLevel = 0; currentLevel <= maxLevel; currentLevel++)
			{
				System.out.print("Level " + currentLevel + ": ");
				
				// used to format commas correctly
				boolean firstPrinted = true;

				// check all locations
				for(int i = 0; i< locationCount; i++)
				{
					// print locations that belong to the current BFS level
					if(visited[i] && level[i] == currentLevel)
					{
						// print comma between locations
						if(!firstPrinted)
						{
							System.out.print(", ");
						}
	
						// print location name
						System.out.print(locations[i]);
						firstPrinted = false;
					}
				}

				System.out.println();
			}
		}
	}


	/*
	 * dfsDetection
	 * Start Depth-First Search to detect whether the graph contains a cycle
	 */

	public void dfsDetection()
	{
		// keep track of visited locations
		boolean[] visited = new boolean[maxLocations];
		int[] path = new int[maxLocations];

		// check every location in the graph
		for(int i = 0; i < locationCount; i++)
		{
			// start DFS only if the location has not been visited
			if(!visited[i])
			{
				if(dfsRecursive(i, visited, -1, path, 0))
				{
					System.out.println("\nCycle detected in the graph.");
					return;
				}
			}
		}

		System.out.println("\nNo cycle detected in the graph.");
	}

	/* dfsRecursive
	 * Recursive helper method for DFS cycle detection.
	 */

	// currentIndex = current location being visited
	// parentIndex = previous location visited before current location
	
	private boolean dfsRecursive(int currentIndex, boolean[] visited, int parentIndex, int[] path, int depth)
	{
		// mark currrent lcoation as visited
		visited[currentIndex] = true;

		// store current location into traversal path
		path[depth] = currentIndex;

		// check all neighbouring lcoations
		for(int i = 0; i < edgeCounts[currentIndex]; i++)
		{
			String neighbourName = adjacencyList[currentIndex][i].getDestination();
			int neighbourIndex = findLocationIndex(neighbourName);

			// if neighbour has not been visited, continue DFS recursively
			if(!visited[neighbourIndex])
			{
				if(dfsRecursive(neighbourIndex, visited, currentIndex, path, depth + 1))
				{
					return true;
				}
			}

			// if neighbour is already visited and is not the parent, a cycle exists
			else if(neighbourIndex != parentIndex)
			{
				System.out.println("\nCycle detected in the graph.");
				System.out.print("Cycle members: ");

				// print traversal path
				for(int j = 0; j <= depth; j++)
				{
					System.out.print(locations[path[j]]);

					if(j < depth)
					{
						System.out.print(" -> ");
					}
				}

				System.out.println(" -> " + locations[neighbourIndex]);

				return true;
			}
		}

		return false;
	}


	/* 
	 * dijkstra
	 * Find the shortest friving path between two locations using Dijkstra's shortest path algorithm
	 */

	public void dijkstra(String startLocation, String endLocation)
	{
		int startIndex = findLocationIndex(startLocation);
		int endIndex = findLocationIndex(endLocation);

		// check whether both locations exist
		if(startIndex == -1 || endIndex == -1)
		{
			System.out.println("Start or Destination location does not exist.");
		}
		else
		{
			int[] distance = new int[maxLocations];
			boolean[] visited = new boolean[maxLocations];
			int[] previous = new int[maxLocations];

			// initialise all distances as very large values
			for(int i = 0; i < maxLocations; i++)
			{
				distance[i] = Integer.MAX_VALUE;
				visited[i] = false;
				previous[i] = -1;
			}

			// distance from start location to itself is 0
			distance[startIndex] = 0;

			// repeat for all locations
			// each round selects the unvisited location with the smallest known distance
			for(int i = 0; i < locationCount; i++)
			{
				int currentIndex = getSmallestDistanceIndex(distance, visited);

				// if there is not reachable unvisited location remains then stop the algorithm
				if(currentIndex == -1)
				{
					break;
				}

				visited[currentIndex] = true;

				// check all neighbouring locations of ythe current location
				for(int j = 0; j < edgeCounts[currentIndex]; j++)
				{
					String neighbourName = adjacencyList[currentIndex][j].getDestination();
					int neighbourIndex = findLocationIndex(neighbourName);
					int roadWeight = adjacencyList[currentIndex][j].getWeight();

					// calculate possible new distance through current location
					if(!visited[neighbourIndex] && distance[currentIndex] != Integer.MAX_VALUE)
					{
						int newDistance = distance[currentIndex] + roadWeight;

						// update the distance and remember the previous location if thi
						// route is shorter
						if(newDistance < distance[neighbourIndex])
						{
							distance[neighbourIndex] = newDistance;
							previous[neighbourIndex] = currentIndex;
						}
					}
				}
			}

			printShortestPath(startIndex, endIndex, distance, previous);
		}
	}


	/*
	 * getSmallestDistanceIndex
	 * Helper method to find unvisited location with the smallest current distance value
	 */

	private int getSmallestDistanceIndex(int[] distance, boolean[] visited)
	{
		int smallestDistance = Integer.MAX_VALUE;
		int smallestIndex = -1;

		for(int i = 0; i < locationCount; i++)
		{
			// only consider locations that have not been visited
			if(!visited[i] && distance[i] < smallestDistance)
			{
				smallestDistance = distance[i];
				smallestIndex = i;
			}
		}

		return smallestIndex;
	}


	/*
	 * printShortestPath
	 * Helper method to print the shortest path result from Dijkstra
	 */

	private void printShortestPath(int startIndex, int endIndex, int[] distance, int[] previous)
	{
		if(distance[endIndex] == Integer.MAX_VALUE)
		{
			System.out.println("\nNo path found from " + locations[startIndex] + " to " + locations[endIndex]);
		}
		else
		{
			int[] path = new int[maxLocations];
			int pathCount = 0;
			int currentIndex = endIndex;

			// trace backwards from destionation to start using the previous array
			while(currentIndex != -1)
			{
				path[pathCount] = currentIndex;
				pathCount++;
				currentIndex = previous[currentIndex];
			}

			System.out.println("\nShortest path from " + locations[startIndex] + " to " + locations[endIndex] + ":");

			// print path in correct order
			// the path was stored backwards so print from end to start
			for(int i = pathCount - 1; i >= 0; i--)
			{
				System.out.print(locations[path[i]]);

				if(i != 0)
				{
					System.out.print(" -> ");
				}
			}

			System.out.println("\nTotal driving time: " + distance[endIndex] + " minutes");
		}
	}

	/* 
	 * getShortestTime
	 * Calculate and return the shortest driving time between two locations by using Dijkstra
	 */

	public int getShortestTime(String startLocation, String endLocation)
	{
    		// find indexes of start and destination locations
    		int startIndex = findLocationIndex(startLocation);
    		int endIndex = findLocationIndex(endLocation);

    		// return -1 if either location does not exist
    		if(startIndex == -1 || endIndex == -1)
    		{
        		return -1;
    		}

    		// stores shortest known distances
    		int[] distance = new int[maxLocations];

    		// tracks visited locations
    		boolean[] visited = new boolean[maxLocations];
			
    		// initialise all distances as infinity and mark all locations as unvisited
   	 	for(int i = 0; i < maxLocations; i++)
    		{
        		distance[i] = Integer.MAX_VALUE;
        		visited[i] = false;
    		}

    		// distance from start location to itself is 0
    		distance[startIndex] = 0;

    		// repeat Dijkstra process for all locations
    		for(int i = 0; i < locationCount; i++)
    		{
        		// find unvisited location with smallest distance
        		int currentIndex = getSmallestDistanceIndex(distance, visited);

        		// stop if no reachable unvisited location remains
        		if(currentIndex == -1)
        		{
            			break;
        		}

        		// mark current location as visited
        		visited[currentIndex] = true;

        		// check all neighbouring locations
        		for(int j = 0; j < edgeCounts[currentIndex]; j++)
       		 	{
           		 	// get neighbour location name
            			String neighbourName = adjacencyList[currentIndex][j].getDestination();

            			// find neighbour index
            			int neighbourIndex =
                		findLocationIndex(neighbourName);

            			// get driving time between locations
            			int roadWeight = adjacencyList[currentIndex][j].getWeight();

            			// continue only if neighbour has not been visited and current location is reachable
            			if(!visited[neighbourIndex] && distance[currentIndex] != Integer.MAX_VALUE)
            			{
                				// calculate possible shorter distance
                				int newDistance = distance[currentIndex] + roadWeight;

                				// update shortest distance if new route is shorter
                				if(newDistance < distance[neighbourIndex])
                				{
                    					distance[neighbourIndex] = newDistance;
                				}
            			}
        		}
    		}

    		// return -1 if destination cannot be reached
    		if(distance[endIndex] == Integer.MAX_VALUE)
    		{
        		return -1;
    		}

    		// return shortest driving time
    		else
    		{
       			 return distance[endIndex];
   		}
	}
}

