/* 
 * Heap
 * This class implements an array based max heap for scheduling pickup request.
 * The requewst with the highest priority score is always stored at the root.
 */

public class Heap
{
	private PickupRequest[] heap;
	private int count;
	
	public Heap(int maxSize)
	{

		// Constructor 
		// create heap array with given maximum size
		this.heap = new PickupRequest[maxSize];

		// heap starts empty
		this.count = 0;
	}

	/* 
	 * insert
	 * add a pickup request into the heap
	 * after insertion, the request will move upward if its priority is higher than its parent
	 */
	public void insert(PickupRequest request)
	{
		// check for null request
		if(request == null)
		{
			System.out.println("Cannot insert null pickup request.");
		}

		// check whether heap is already full
		else if(count >= heap.length)
		{
			System.out.println("Heap is full. Cannot insert request.");
		}

		else
		{
			// insert request at the next available position
			heap[count] = request;

			// increase heap size
			count++;

			// restore max heap property
			trickleUp(count - 1);

			System.out.println("\nInserted pickup request: ");
			System.out.println(request);
			printHeap();
		}
	}

	// return the highest-priority pickup request without removing it
	public PickupRequest peek()
	{
		PickupRequest result = null;

		// check whether heap is empty
		if(count == 0)
		{
			System.out.println("Heap is empty.");
		}

		else
		{
			// root of max heap always stores highest priority request
			result = heap[0];
			System.out.println("Highest priority request: " + result);
		}

		return result;
	}

	/*
	 * extractPriority
	 * remove and return the highest-priority pickup request
	 * the last request is moved to the root and then moved download until the heap property is restored
	 */
	public PickupRequest extractPriority()
	{
		PickupRequest result = null;

		// check whether heap is empty
		if(count == 0)
		{
			System.out.println("Heap is empty. Cannot extract.");
		}

		else 
		{
			// store highest priority request
			result = heap[0];

			// reduce heap size
			count--;

			// move last request to root position
			if(count > 0)
			{
				heap[0] = heap[count];
			}

			// remove duplicate reference at old last position
			heap[count] = null;

			// restore heap structure
			if(count > 0)
			{
				trickleDown(0);
			}

			System.out.println("\nExtracted highest priority request: ");
			System.out.println(result);
			printHeap();
		}

		return result;
	}

	
	/* 
	 * trickleUp	
	 * Move a new inserted request upward while its priority is higher than its parent priority
	 */

	private void trickleUp(int currentIndex)
	{
		// calculate parent index
		int parentIndex = (currentIndex - 1) / 2;

		// continue swapping while child priority is larger than parent
		while(currentIndex > 0 && heap[currentIndex].getPriority() > heap[parentIndex].getPriority())
		{
			// swap children and parent
			PickupRequest temp = heap[parentIndex];
			heap[parentIndex] = heap[currentIndex];
			heap[currentIndex] = temp;

			// move upward in heap
			currentIndex = parentIndex;

			// recalculate parent index
			parentIndex = (currentIndex - 1) / 2;
		}
	}


	/*
	 * trickleDown
	 * Move a request downward if one of its children has higher priority.
	 */

	private void trickleDown(int currentIndex)
	{
		boolean keepGoing = true;

		while(keepGoing)
		{
			// calculate left child index
			int leftChildIndex = currentIndex * 2 + 1;

			// calculate right child index
			int rightChildIndex = leftChildIndex + 1;

			// assume current node is largest initially
			int largerChildIndex = currentIndex;

			// compare with left child 
			if(leftChildIndex < count && heap[leftChildIndex].getPriority() > heap[largerChildIndex].getPriority())
			{
				largerChildIndex = leftChildIndex;
			}

			// compare with right child
                        if(rightChildIndex < count && heap[rightChildIndex].getPriority() > heap[largerChildIndex].getPriority())
                        {
                                largerChildIndex = rightChildIndex;
                        }

			// swap if child has larger priority
			if(largerChildIndex != currentIndex)
			{
				PickupRequest temp = heap[currentIndex];
				heap[currentIndex] = heap[largerChildIndex];
				heap[largerChildIndex] = temp;

				// continue moving downward
				currentIndex = largerChildIndex;
			}

			else
			{
				// stop when heap property is satisfied
				keepGoing = false;
			}
		}
	}

	/*
	 * printHeap
	 * print the heap array after each insert or extraction
	 */

	public void printHeap()
	{
		System.out.println("Current Heap Array: ");
		if(count == 0)
		{
			System.out.println("[Empty]");
		}

		else
		{
			for(int i = 0; i < count; i++)
			{
				System.out.println("Index " + i + ": " + heap[i]);
			}
		}
	}
	

	/* 
	 * isEmpty
	 * return true if the heap has no request
	 */

	public boolean isEmpty()
	{
		return count == 0;
	}

	/*
	 * getCount
	 * return the number of request currently stored in the heap
	 */

	public int getCount()
	{
		return count;
	}
}

