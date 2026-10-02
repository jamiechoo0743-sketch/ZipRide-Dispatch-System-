/* 
 * Passenger
 * This class stores passenger information for the ZipRide Dispatch System.
 * Passenger recoeds will be stored inside the hash table for fast lookup.
 */

public class Passenger
{
	private int passengerID;
	private String name;
	private String pickupLocation;
	private int membershipTier;
	
	// Constructor
	public Passenger(int passengerID, String name, String pickupLocation, int membershipTier)
	{
		this.passengerID = passengerID;
		this.name = name;
		this.pickupLocation = pickupLocation;
		this.membershipTier = membershipTier;
	}

	// getPassengerID
	public int getPassengerID()
	{
		return passengerID;
	}

	// getName
	public String getName()
	{
		return name;
	}

	// getPickupLocation
	public String getPickupLocation()
	{
		return pickupLocation;
	}

	// getMembershipTier
	// Tier 1 is the highset priority and tier 5 is the lowest
	public int getMembershipTier()
	{
		return membershipTier;
	}

	// return passenger details in readble format
	public String toString()
	{
		return "Passenger ID: " + passengerID + ", Name: " + name + ", Pcikup: " + ", Tier: " + membershipTier;
	}
}
