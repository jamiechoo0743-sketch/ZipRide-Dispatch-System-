/*
 * Sorting
 * This class implements merge sort and quick sort for pickup records.
 * Records are sorted by EstimatedPickupTime in ascending order.
 */

public class Sorting
{
	// mergeSort
	// starts merge sort on the pickup record array.
     
    	public static void mergeSort(PickupRecord[] records)
    	{
        	mergeSortRecurse(records, 0, records.length - 1);
    	}

    	// mergeSortRecurse
    	// recursively divides the array into smaller parts.

    	private static void mergeSortRecurse(PickupRecord[] records, int leftIdx, int rightIdx)
    	{
		// Continue dividing while more than one element exist
        	if(leftIdx < rightIdx)
        	{
			// find middle index
            		int midIdx = (leftIdx + rightIdx) / 2;
			// sort left half
            		mergeSortRecurse(records, leftIdx, midIdx);
			// sort right half
            		mergeSortRecurse(records, midIdx + 1, rightIdx);
			// merge sorted halves together
            		merge(records, leftIdx, midIdx, rightIdx);
        	}
   	 }

	// merge
   	// merges two sorted subarrays into one sorted section.
    	private static void merge(PickupRecord[] records, int leftIdx, int midIdx, int rightIdx)
    	{
		// temporary array used during merging
        	PickupRecord[] temp = new PickupRecord[rightIdx - leftIdx + 1];
		// pointer for left subarray
        	int ii = leftIdx;
		// pointer for right subarray
        	int jj = midIdx + 1;
		// pointer for temp array
        	int kk = 0;
		
		// compare elements from both subarrays
        	while(ii <= midIdx && jj <= rightIdx)
        	{
			// insert smaller pickup time into temp array
            		if(records[ii].getEstimatedPickupTime() <= records[jj].getEstimatedPickupTime())
            		{
                		temp[kk] = records[ii];
                		ii++;
            		}
            		else
            		{
                		temp[kk] = records[jj];
                		jj++;
            		}

            		kk++;
        	}
		
		// copy remaining left subarray elements
        	while(ii <= midIdx)
        	{
            		temp[kk] = records[ii];
            		ii++;
            		kk++;
        	}

		// copy remaining right subarray elements
        	while(jj <= rightIdx)
        	{
            		temp[kk] = records[jj];
            		jj++;
            		kk++;
        	}

		//  copy sorted values back into original array
        	for(int i = leftIdx; i <= rightIdx; i++)
        	{
            		records[i] = temp[i - leftIdx];
        	}
    	}

	// quickSort
    	// Starts quick sort on the pickup record array.
    
    	public static void quickSort(PickupRecord[] records)
    	{
        	quickSortRecurse(records, 0, records.length - 1);
    	}

    	// quickSortRecurse
    	// recursively partitions and sorts the array.
     
    	private static void quickSortRecurse(PickupRecord[] records, int leftIdx, int rightIdx)
    	{
		// continue sorting while contains more than one element

        	if(leftIdx < rightIdx)
        	{
			// choose rightmost element as pivot
            		int pivotIdx = rightIdx;

			// partition array around pivot
            		int newPivotIdx = partition(records, leftIdx, rightIdx, pivotIdx);

			// sort left side of pivot
            		quickSortRecurse(records, leftIdx, newPivotIdx - 1);

			// sort right side of pivot
            		quickSortRecurse(records, newPivotIdx + 1, rightIdx);
        	}
    	}

    	// partition
     	// places records smaller than the pivot on the left and records larger than the pivot on the right.
     
    	private static int partition(PickupRecord[] records, int leftIdx, int rightIdx, int pivotIdx)
    	{
        	// store pivot value
		PickupRecord pivotValue = records[pivotIdx];

		// move pivot temporarily to end
        	records[pivotIdx] = records[rightIdx];
       	 	records[rightIdx] = pivotValue;
		
		// track position for smaller elements
        	int currentIdx = leftIdx;

        	// compare all elements against
		for(int i = leftIdx; i < rightIdx; i++)
        	{	
			// move smaller elements to left side
            		if(records[i].getEstimatedPickupTime() < pivotValue.getEstimatedPickupTime())
            		{
				// swap elements
                		PickupRecord temp = records[i];
                		records[i] = records[currentIdx];
                		records[currentIdx] = temp;

               	 		currentIdx++;
            		}
        	}

		// place pivot into correct sorted position
        	records[rightIdx] = records[currentIdx];
        	records[currentIdx] = pivotValue;

        	return currentIdx;
    	}

    	/*
     	* printRecords
     	* Prints all pickup records.
     	*/
	
    	public static void printRecords(PickupRecord[] records)
    	{
        	for(int i = 0; i < records.length; i++)
        	{
            		System.out.println(records[i]);
        	}
    	}
}
