/*
 * PickupRequest
 * This class represents one pickup request in the ZipRide Dispatch System.
 * It stores the passenger, assigned driver, estimated pickup time and priority score.
 */

public class PickupRequest
{
	private Passenger passenger;
	private Driver assignedDriver;
	private int estimatedPickupTime;
	private double priority;

	// Constructor
	public PickupRequest(Passenger passenger, Driver assignedDriver, int estimatedPickupTime)
	{
		this.passenger = passenger;
		this.assignedDriver = assignedDriver;
		this.estimatedPickupTime = estimatedPickupTime;
		this.priority = calculatePriority();
	}

	// calculatePriority
	private double calculatePriority()
	{
		int membershipTier = passenger.getMembershipTier();
		
		// Estimated pickup time is 0 = driver already at the passenger pickup location.
		// use 1 instead to avoid division by zero
		int timeValue = estimatedPickupTime;
		if(timeValue <= 0)
		{
			timeValue = 1;
		}

		return (6 - membershipTier) + (1000.0 / timeValue);
	}

	// return the passenger linked to this pickup request
	public Passenger getPassenger()
	{
		return passenger;
	}

	// return the driver assigned to this pickup request
	public Driver getAssignedDriver()
	{
		return assignedDriver;
	}

	// return the estimated pickup time in minutes
	public int getEstimatedPickupTime()
	{
		return estimatedPickupTime;
	}

	// return the calculated priority score
	public double getPriority()
	{
		return priority;
	}

	// return pickup request details in readable format
	public String toString()
	{
		return "Passenger: " + passenger.getName() + " | Driver: " + assignedDriver.getName() +
			" | Pickup Time: " + estimatedPickupTime + " mins | Priority: " + priority;
	}
}

