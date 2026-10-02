/*
 * PickupRecord
 * This class represents one completed pickup record.
 * It is used for sorting pickup records by estimated pickup time.
 */

public class PickupRecord
{
    // Stores passenger name
    private String passengerName;

    // Stores assigned driver name
    private String driverName;

    // Stores estimated pickup time in minutes
    private int estimatedPickupTime;

    // Stores request priority score
    private double priority;

    /*
     * Constructor
     * Creates a pickup record with passenger name, driver name, estimated pickup time, and priority.
     */
    public PickupRecord(String passengerName, String driverName, int estimatedPickupTime, double priority)
    {
        // Store passenger name
        this.passengerName = passengerName;

        // Store driver name
        this.driverName = driverName;

        // Store estimated pickup time
        this.estimatedPickupTime = estimatedPickupTime;

        // Store request priority
        this.priority = priority;
    }

    // getEstimatedPickupTime
    public int getEstimatedPickupTime()
    {
        return estimatedPickupTime;
    }

    // Returns pickup record details in readable format.
    public String toString()
    {
        return passengerName + " | Driver: " + driverName +  " | Time: " + estimatedPickupTime + " mins | Priority: " + priority;
    }
}
