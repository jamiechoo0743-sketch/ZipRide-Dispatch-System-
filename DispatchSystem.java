/*
 * DispatchSystem
 * This class integrates the Graph, PassengerHashTable, DriverHashTable and MaxHeap modules.
 * It creates pickup requests by finding the nearest available driver, calculating estimated pickup time
 * and inserting the request into the max heap scheduler.
 */

public class DispatchSystem
{
	private Graph graph;
	private PassengerHashTable passengerTable;
	private DriverHashTable driverTable;
	private Heap heap;

	// Constructor
	// stores references to the main system modules.
	public DispatchSystem(Graph graph, PassengerHashTable passengerTable, DriverHashTable driverTable, Heap heap)
	{
		this.graph = graph;
		this.passengerTable = passengerTable;
		this.driverTable = driverTable;
		this.heap = heap;
	}

	/* 
	 * createPickupRequest
	 * creates a pickup request for a passenger
	 */

	public void createPickupRequest(int passengerID)
	{
		// search for passenger using passenger ID
		Passenger passenger = passengerTable.search(passengerID);

		// stop if passenegr does not exist
		if(passenger == null)
		{
			System.out.println("Pickup request failed! Passenger not found!");
			return;
		}

		// get all available drivers
		Driver[] availableDrivers = driverTable.getAvailableDrivers();

		// stop if no available drivers exist
		if(availableDrivers.length == 0)
		{
			System.out.println("Pickup request failed! No available drivers!");
			return;
		}

		// stores the nearest available driver
		Driver nearestDriver = null;

		// stores the shortest estimated pickup time
		int shortestTime = -1;

		// check all available drivers
		for(int i = 0; i < availableDrivers.length; i++)
		{
			// current driver being checked 
			Driver currentDriver = availableDrivers[i];

			// calculate shortest travel time form driver to passenger
			int time = graph.getShortestTime(currentDriver.getCurrentLocation(), passenger.getPickupLocation());

			// continue only if route exists
			if(time != -1)
			{
				// update nearest driver if 
				// 1. No shortest time exist yet
				// 2. Current driver has shorter pickup time
				if(shortestTime == -1 || time < shortestTime)
				{
					shortestTime = time;
					nearestDriver = currentDriver;
				}
			}
		}

		// stop if no reachable driver is found
		if(nearestDriver == null)
		{
			System.out.println("Pickup request failed! No reachable driver!");
		}

		else 
		{
			// create pickup request using passenger, nearest driver and estimated pickup time
			PickupRequest request = new PickupRequest(passenger, nearestDriver, shortestTime);

			System.out.println("\nNearestdriver selected: ");
			System.out.println(nearestDriver);

			System.out.println("Estimated pickup time: " + shortestTime + " minutes");

			// Insert pickup request into heap
			heap.insert(request);
		}
	}


	/* 
	 * dispatchNextRequest
	 * Dispatches the highest-priority pickup request from the heap
	 */

	// get highest-priority request from heap
	public void dispatchNextRequest()
	{	
		PickupRequest request = heap.extractPriority();

		// continue only if a request exists
		if(request != null)
		{
			// get assigned driver from request
			Driver driver = request.getAssignedDriver();

			// update driver status to Busy
			driver.setAvailabilityStatus("Busy");

			System.out.println("Dispatched request: ");
			System.out.println(request);

			System.out.println("Driver status updated to Busy.");
		}
	}
}

