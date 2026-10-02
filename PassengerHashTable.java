/* 
 * PassengerHashTable
 * This class implements a hash table for storing passenger records
 * It uses open adderssing with linear probing to handle collisions
 */

public class PassengerHashTable
{
	private HashEntry[] hashArray;
	private int count;
	private int tableSize;

	// Constructor 
	public PassengerHashTable(int tableSize)
	{
		this.tableSize = tableSize;
		this.hashArray = new HashEntry[tableSize];
		this.count = 0;

		// initialise every slot as an empty HashEntry
		for(int i = 0; i < tableSize; i++)
		{
			hashArray[i] = new HashEntry();
		}
	}

	// hash
	private int hash(int key)
	{
		// converts passengerID into valid table index
		return Math.abs(key) % tableSize;
	}

	// getLoadFactor
	public double getLoadFactor()
	{
		// calculate how full the hash table currently is
		return (double) count / (double) tableSize;
	}

	/* 
	 * insert
	 * insert a passenger into hash table
	 */

	public void insert(Passenger passenger)
	{
		if(passenger == null)
		{
			System.out.println("Cannot insert null passenger.");
			return;
		}

		int key = passenger.getPassengerID();
		int index = hash(key);
		int originalIndex = index;
		boolean inserted = false;

		// keep searching until an empty or slot is found
		while(!inserted)
		{
			if(hashArray[index].getState() == 0 || hashArray[index].getState() == -1)
			{
				hashArray[index] = new HashEntry(key, passenger);
				count++;
				inserted = true;

				System.out.println("Inserted passenger " + key + " at index " + index);
			}

			// update the old record instead of inserting duplicate
			else if(hashArray[index].getKey() == key)
			{
				hashArray[index].setValue(passenger);
				inserted = true;

				System.out.println("Passenger " + key + " already exists. Record updated.");
			}

			// move to the next index using linear probing when occur collision
			else 
			{
				System.out.println("Collision at index " + index + " for passenger " + key);
				index = (index + 1) % tableSize;

				// if probing returns to the original index, the table is full
				if(index == originalIndex)
				{
					System.out.println("Hash table is full. Insert failed");
					inserted = true;
				}
			}
		}
	}
	

	/*
	 *  search
	 *  search for a passenger by passenger ID
	 */

	public Passenger search(int passengerID)
	{
		int index = hash(passengerID);
		int originalIndex = index;
		boolean found = false;
		Passenger result = null;

		// continue probing until an empty alot is reached.
		while(hashArray[index].getState() != 0 && !found)
		{
			if(hashArray[index].getState() == 1 && hashArray[index].getKey() == passengerID)
			{
				result = (Passenger) hashArray[index].getValue();
				found = true;
			}
			else 
			{
				index = (index + 1) % tableSize;

				if(index == originalIndex)
				{
					found = true;
				}
			}

		}

		if(result == null)
		{
			System.out.println("Passenger " + passengerID + " not found");
		}
		else 
		{
			System.out.println("Passenger found: " + result);
		}

		return result;
	}

	/* 
	 * delete
	 * delete a passenger by passengerID
	 * The slot is marked as deleted instead of empty to avoid breaking the probing chain
	 */
	public void delete(int passengerID)
	{
		int index = hash(passengerID); 
		int originalIndex = index; // store original index to detect full table traversal
		boolean deleted = false; // tracks whether deletion was successful
		boolean stop = false; //controls loop termination
				  
		// continue searching until an empty slot is found or the search is stopped
		while(hashArray[index].getState() == 1 && !stop)
		{
			// check whether current slot contains the target passenger
			if(hashArray[index].getState() == 1 && hashArray[index].getKey() == passengerID)
			{
				// mark slot as deleted
				hashArray[index].setKey(-1);
				hashArray[index].setValue(null);
				hashArray[index].setState(-1);
				
				// reduce total record count
				count--;
				deleted = true;
				stop = true;

				System.out.println("Passenger " + passengerID + " deleted from index " + index);
			}
			else
			{
				// move to next slot using linear probing
				index = (index + 1) % tableSize;

				// stop if search returns to original index
				{
					stop = true;
				}
			}
		}

		// Display failure message if passenger was not found
		if(!deleted)
		{
			System.out.println("Passenger " + passengerID + " not found. Deleted failed!");
		}
	}

	/*
 	 * displayTable
 	 * Prints all hash table slots.
 	 * This helps demonstrate collisions and linear probing.
 	 */
	 public void displayTable()
	 {
   	 	System.out.println("\nPassenger Hash Table:");
    		System.out.println("Index | State | Record");
    		System.out.println("----------------------------------------");

    		// Loop through all table slots
    		for(int i = 0; i < tableSize; i++)
    		{
        		System.out.print(i + " | ");

       	 		// State 0 means empty slot
        		if(hashArray[i].getState() == 0)
       	 		{
            			System.out.println("Empty |");
        		}

        		// State -1 means deleted slot
        		else if(hashArray[i].getState() == -1)
        		{
            			System.out.println("Deleted |");
        		}

        		// State 1 means occupied slot
        		else
        		{
            			System.out.println("Used | " + hashArray[i].getValue());
        		}
    		}

    			// Print current load factor of the hash table
    			System.out.println("Load factor: " + getLoadFactor());
	}
}
