import java.util.Scanner;

/*
 * Menu
 * Main test driver for the ZipRide Dispatch System.
 * This class tests all modules: Graph, Hash Tables, Heap Scheduling, and Sorting.
 */

public class Menu
{
    	public static void main(String[] args)
    	{
        	Scanner sc = new Scanner(System.in);

               /*
         	* =====================================================
         	* MODULE 1: GRAPH SETUP
         	* =====================================================
         	*/

        	Graph graph = new Graph(10);

        	graph.addLocation("CurtinUniversity");
        	graph.addLocation("Airport");
        	graph.addLocation("Restaurant");
        	graph.addLocation("Hospital");
        	graph.addLocation("ShoppingMall");
        	graph.addLocation("PetShop");
        	graph.addLocation("Park");
        	graph.addLocation("Beach");
        	graph.addLocation("IsolatedNode");

        	graph.addRoad("CurtinUniversity", "Airport", 17);
        	graph.addRoad("CurtinUniversity", "Restaurant", 9);
        	graph.addRoad("CurtinUniversity", "ShoppingMall", 11);
        	graph.addRoad("Restaurant", "Hospital", 6);
        	graph.addRoad("ShoppingMall", "Hospital", 7);
        	graph.addRoad("Airport", "Beach", 12);
        	graph.addRoad("Beach", "PetShop", 9);
        	graph.addRoad("PetShop", "Park", 5);
        	graph.addRoad("Park", "Hospital", 10);
        	graph.addRoad("ShoppingMall", "Park", 4);

        	/*
        	 * =====================================================
        	 * MODULE 2: PASSENGER HASH TABLE
        	 * =====================================================
        	 */
	
        	PassengerHashTable passengerTable = new PassengerHashTable(11);
        	passengerTable.insert(new Passenger(1001, "Jamie", "CurtinUniversity", 1));
        	passengerTable.insert(new Passenger(1012, "Samuel", "Airport", 2));
        	passengerTable.insert(new Passenger(1023, "Elson", "Hospital", 3));
        	passengerTable.insert(new Passenger(1034, "Allyn", "Restaurant", 1));
        	passengerTable.insert(new Passenger(1045, "Kelson", "ShoppingMall", 4));

        	/*
        	 * =====================================================
        	 * MODULE 2: DRIVER HASH TABLE
        	 * =====================================================
        	 */

        	DriverHashTable driverTable = new DriverHashTable(11);
        	driverTable.insert(new Driver(2001, "Kelven", "Airport", "Available"));
        	driverTable.insert(new Driver(2012, "Benjamin", "CurtinUniversity", "Available"));
        	driverTable.insert(new Driver(2023, "Jasper", "Hospital", "Busy"));
        	driverTable.insert(new Driver(2034, "Davin", "Restaurant", "Available"));
        	driverTable.insert(new Driver(2045, "Macus", "ShoppingMall", "Offline"));

        	/*
        	 * =====================================================
        	 * MODULE 3: HEAP + DISPATCH SYSTEM
        	 * =====================================================
        	 */

        	Heap heap = new Heap(20);

        	DispatchSystem dispatchSystem = new DispatchSystem(graph, passengerTable, driverTable, heap);

        	/*
        	 * =====================================================
        	 * MODULE 4: SORTING DATA
        	 * =====================================================
        	 */

        	PickupRecord[] records =
        	{
            		new PickupRecord("Jamie", "Kelven", 17, 63.6),
            		new PickupRecord("Samuel", "Benjamin", 17, 60.8),
            		new PickupRecord("Elson", "Davin", 6, 153.0),
            		new PickupRecord("Allyn", "Kelven", 1, 171.6),
            		new PickupRecord("Kelson", "Benjamin", 11, 87.3)
        	};

       		int choice = -1;

       		while(choice != 0)
        	{
            		System.out.println("\n====================================");
            		System.out.println(" ZIPRIDE DISPATCH SYSTEM ");
            		System.out.println("====================================");

            		System.out.println("1. Display Graph");
            		System.out.println("2. BFS Traversal");
            		System.out.println("3. DFS Cycle Detection");
            		System.out.println("4. Dijkstra Shortest Path");
            		System.out.println("5. Display Passenger Hash Table");
            		System.out.println("6. Display Driver Hash Table");
            		System.out.println("7. Create Pickup Request");
            		System.out.println("8. Dispatch Highest Priority Request");
            		System.out.println("9. Display Heap");
            		System.out.println("10. Merge Sort Pickup Records");
            		System.out.println("11. Quick Sort Pickup Records");
            		System.out.println("12. Sorting Benchmark");
            		System.out.println("0. Exit");
		
            		System.out.print("\nEnter choice: ");
            		choice = sc.nextInt();

            		switch(choice)
            		{
                		case 1:

                    		graph.printGraph();
                    		break;

                		case 2:

                    		graph.bfs("CurtinUniversity");
                    		break;

                		case 3:

                    		graph.dfsDetection();
                    		break;

                		case 4:

                    		graph.dijkstra("Airport", "Hospital");
                    		break;

                		case 5:

                    		passengerTable.displayTable();
                    		break;

                		case 6:

                    		driverTable.displayTable();
                    		break;

                		case 7:

                    		System.out.print("Enter Passenger ID: ");
                    		int passengerID = sc.nextInt();

                    		dispatchSystem.createPickupRequest(passengerID);
                    		break;

                		case 8:

                    		dispatchSystem.dispatchNextRequest();
                    		break;

                		case 9:

                    		heap.printHeap();
                    		break;

                		case 10:

                    		System.out.println("\nBefore Merge Sort:");
                    		Sorting.printRecords(records);

                    		long startMerge = System.nanoTime();

                    		Sorting.mergeSort(records);

                    		long endMerge = System.nanoTime();

                    		System.out.println("\nAfter Merge Sort:");
                    		Sorting.printRecords(records);

                    		System.out.println("\nMerge Sort Time: " + (endMerge - startMerge) + " ns");

                    		break;

                		case 11:

                    		PickupRecord[] quickRecords =
                    		{
                        		new PickupRecord("Jamie", "Ali", 15, 71.6),
                        		new PickupRecord("Alex", "Ben", 8, 129.0),
                        		new PickupRecord("Sarah", "David", 20, 53.0),
                        		new PickupRecord("Daniel", "Ali", 6, 171.6),
                        		new PickupRecord("Emily", "Ben", 12, 87.3)
                    		};

                    		System.out.println("\nBefore Quick Sort:");
                    		Sorting.printRecords(quickRecords);

                    		long startQuick = System.nanoTime();

                    		Sorting.quickSort(quickRecords);

                    		long endQuick = System.nanoTime();

                    		System.out.println("\nAfter Quick Sort:");
                    		Sorting.printRecords(quickRecords);

                    		System.out.println("\nQuick Sort Time: " + (endQuick - startQuick) + " ns");

                    		break;

		 		case 12:
		    	       /*
     				* MODULE 4: SORTING BENCHMARK
     				* Generates PickupRecord datasets of 100, 500, and 1000.
     				* EstimatedPickupTime T is calculated using Graph shortest path.
     				*/

    				runPickupRecordBenchmark(graph);

    				break;

			
                		case 0:

                    		System.out.println("\nExiting ZipRide Dispatch System.");
                    		break;

                		default:

                    		System.out.println("\nInvalid choice.");
            		}
        	}

        	sc.close();
    	}


	// helper method

	/*
	 * runPickupRecordBenchmark
	 * ----------------------------------------
	 * Runs sorting benchmark for PickupRecord arrays.
	 * It tests random, nearly sorted, and reversed datasets
	 * for sizes 100, 500, and 1000.
	 */

	public static void runPickupRecordBenchmark(Graph graph)
	{
    		int[] sizes = {100, 500, 1000};

    		System.out.println("\n===== PICKUP RECORD SORTING BENCHMARK =====");
    		System.out.println("Size | Condition | Merge Sort(ns) | Quick Sort(ns)");
    		System.out.println("--------------------------------------------------");

    		for(int i = 0; i < sizes.length; i++)
    		{
        		benchmarkCondition(graph, sizes[i], "Random");
        		benchmarkCondition(graph, sizes[i], "Nearly Sorted");
        		benchmarkCondition(graph, sizes[i], "Reversed");
    		}
	}

	/*
	 * benchmarkCondition
	 * ----------------------------------------
	 * Generates one dataset condition, copies it,
	 * then measures merge sort and quick sort time.
	 */

	public static void benchmarkCondition(Graph graph, int size, String condition)
	{
    		PickupRecord[] original = generatePickupRecords(graph, size, condition);
		PickupRecord[] mergeData = copyPickupRecords(original);
		PickupRecord[] quickData = copyPickupRecords(original);

    		long mergeStart = System.nanoTime();
    		Sorting.mergeSort(mergeData);
    		long mergeEnd = System.nanoTime();

    		long quickStart = System.nanoTime();
    		Sorting.quickSort(quickData);
    		long quickEnd = System.nanoTime();

    		System.out.println(size + " | " + condition + " | " + (mergeEnd - mergeStart) + " | " + (quickEnd - quickStart));
	}

	/*
	 * generatePickupRecords
	 * ----------------------------------------
	 * Generates PickupRecord data.
	 * EstimatedPickupTime is calculated using graph.getShortestTime().
	 */

	public static PickupRecord[] generatePickupRecords(Graph graph, int size, String condition)
	{
    		PickupRecord[] records = new PickupRecord[size];

    		String[] locations =
    		{
       	 		"CBD",
        		"Airport",
        		"University",
        		"Hospital",
        		"ShoppingMall",
        		"SuburbNorth",
        		"SuburbSouth",
        		"IndustrialPark"
    		};

    		for(int i = 0; i < size; i++)
    		{
        		int startIndex;
        		int endIndex;

        		if(condition.equals("Reversed"))
        		{
            			startIndex = i % locations.length;
            			endIndex = (locations.length - 1 - i) % locations.length;

            			if(endIndex < 0)
            			{
                			endIndex = endIndex + locations.length;
            			}
        		}
        		else
        		{
            			startIndex = (int)(Math.random() * locations.length);
            			endIndex = (int)(Math.random() * locations.length);
        		}

        		int time = graph.getShortestTime(locations[startIndex], locations[endIndex]);

       	 		if(time <= 0)
        		{		
            			time = 1;
        		}

        		records[i] = new PickupRecord("Passenger" + i, "Driver"+ i, + time, 0.0);
    		}

    		if(condition.equals("Nearly Sorted"))
    		{
       	 		Sorting.mergeSort(records);

       		 	// Displace at most 10% of the records.
        		int swaps = size / 10;

        		for(int i = 0; i < swaps; i++)
        		{
            			int index1 = (int)(Math.random() * size);
            			int index2 = (int)(Math.random() * size);

            			PickupRecord temp = records[index1];
            			records[index1] = records[index2];
            			records[index2] = temp;
        		}
   		 }

    		if(condition.equals("Reversed"))
    		{
        		Sorting.mergeSort(records);

        		// Reverse the sorted array to create worst-order input.
        		for(int i = 0; i < size / 2; i++)
        		{
            			PickupRecord temp = records[i];
            			records[i] = records[size - 1 - i];
            			records[size - 1 - i] = temp;
        		}
    		}

    		return records;
	}

	/*
 	* copyPickupRecords
 	* ----------------------------------------
 	* Copies a PickupRecord array so merge sort and quick sort
 	* receive the same original data.
 	*/
	
	public static PickupRecord[] copyPickupRecords(PickupRecord[] original)
	{
    		PickupRecord[] copy = new PickupRecord[original.length];

    		for(int i = 0; i < original.length; i++)
    		{
        		copy[i] = original[i];
    		}

    		return copy;
	}
}
