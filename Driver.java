/*
 * Driver
 * This class stores driver information for thr ZipRide Dispatch System.
 * Driver records will be stored inside the hash table for fast lookup.
 */

public class Driver
{
	private int driverID;
	private String name;
	private String currentLocation;
	private String availabilityStatus;

	// Constructor
	public Driver(int driverID, String name, String currentLocation, String availabilityStatus)
	{
		this.driverID = driverID;
		this.name = name;
		this.currentLocation = currentLocation;
		this.availabilityStatus = availabilityStatus;
	}

	// getDriverID
	public int getDriverID()
	{
		return driverID;
	}

	// getName
	public String getName()
	{
		return name;
	}

	// getCurrentLocation
	public String getCurrentLocation()
	{
		return currentLocation;
	}

	// getAvailabilityStatus
	public String getAvailabilityStatus()
	{
		// returns available, busy or offline
		return availabilityStatus;
	}

	// setAvailabilityStatus
	public void setAvailabilityStatus(String availabilityStatus)
	{
		// updates the driver's availability status
		this.availabilityStatus = availabilityStatus;
	}

	// return driver details in readable format
	public String toString()
	{
		return "Driver ID " + driverID + ", Name: " + name + ", Location: " + currentLocation + ", Status: " + availabilityStatus;
	}
}
