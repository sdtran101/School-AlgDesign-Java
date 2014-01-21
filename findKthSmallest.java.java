package hwk05;

public class hwk05 {
	
	public static void main(String[] args) {
		int arr1[] = {3, 5, 6, 8, 34, 54};
		int arr2[] = {4, 7, 9, 18, 134, 254};
		int arr3[] = {6, 3, 5, 2, 8, 4, 1};
		int k = 3;
		
		int smallest = findKthSmallest(arr1, arr1.length, arr2, arr2.length, k);
		System.out.println("Smallest " + k + " is: " + smallest);
		
		smallest = select(arr3, k, arr3.length);
		
		System.out.println("Smallest " + k + " is: " + smallest);
	}

	static int findKthSmallest(int l1[], int n, int l2[], int m, int k)	{
		if (n == 1 || m == 1) {
			if (n == 1) {
				if (m > k) {
					if (l2[k] < l1[0])
						return l2[k];
					else if (k != 0 && l2[k - 1] > l1[0])
						return l2[k - 1];
					else
						return l1[0];
				} else if (l2[m - 1] > l1[0])
					return l2[m - 1];
				else
					return l1[0];
			} else {
				if (n > k) {
					if (l1[k] < l2[0])
						return l1[k];
					else if (k != 0 && l1[k - 1] > l2[0])
						return l1[k - 1];
					else
						return l2[0];

				} else if (l1[n - 1] > l2[0])
					return l1[n - 1];
				else
					return l2[0];
			}
		}
		
		if (l1[n - 1] < l2[0]) {
			if (k < n)
				return l1[k];
			else
				return l2[k - n];

		} else if (l2[m - 1] < l1[0]) {
			if (k < m)
				return l2[k];
			else
				return l1[k - m];

		}

		int mid1 = (n / 2);
		int mid2 = (m / 2);

		if (l1[mid1] < l2[mid2]) {
			if (k < mid1 + mid2 + 1)
				return findKthSmallest(l1, n, l2, m - mid2, k);
				
			return findKthSmallest(l1, n - mid1, l2, m, k - mid1);

		} else {
			if (k < mid1 + mid2 + 1)
				return findKthSmallest(l2, m, l1, n - mid1, k);

			return findKthSmallest(l2, m - mid2, l1, n, k - mid2);
		}

	}
	
	static int select(int L[], int k, int n)
	{
	    int temp = 0;                       // tempory location for the swap
	    int i = 0;                          // index in to the list
	    
	    while (i <= k )                     // Sort to Kth position in array
	    {
	         for (int j=i+1; j < n; j++)    // check all the element past the current index in the array
	        {
	            if (L[i] > L[j])            // see if there is a smaller value
	            {         
	                temp = L[i];            // do the swap
	                L[i] = L[j];
	                L[j] = temp;
	            }
	        }
	        i++;
	    }
	    return L[i-1];                      // return the value at the Kth position
	}
	

}


