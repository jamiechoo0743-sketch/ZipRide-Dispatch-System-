/*
 * HashEntry
 * This class represents one entry inside the hash table.
 * Each entry stores a passenger or driver object and its state.
 * State meanings: 0 = empty slot, 1 = occupied slot, -1 = deleted slot
 */

public class HashEntry
{
	private int key;
	private Object value;
	private int state;

	// Default Constructor
	// Create an empty hash entry
	public HashEntry()
	{
		// initialise empty entry
		this.key = -1;
		this.value = null;

		// state 0 means empty
		this.state = 0;
	}

	// Constructor 
	// Create an occupied hash entry
	public HashEntry(int key, Object value)
	{
		this.key = key;
		this.value = value;

		// state 1 means occupied
		this.state = 1;
	}

	// getKey
	public int getKey()
	{
		return key;
	}

	// setKey
	public void setKey(int key)
	{
		// update the key stored in this entry
		this.key = key;
	}

	// getValue
	public Object getValue()
	{
		return value;
	}

	// setValue
	public void setValue(Object value)
	{
		this.value = value;
	}

	// getState
	public int getState()
	{
		return state;
	}

	// setState
	public void setState(int state)
	{
		this.state = state;
	}
}
