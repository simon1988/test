package leet.easy;
/**
 * Find the contiguous subarray within an array (containing at least one number)
 * which has the largest sum.
 * 
 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4], the contiguous subarray
 * [4,−1,2,1] has the largest sum = 6.
 * 
 * click to show more practice.
 * 
 * More practice: If you have figured out the O(n) solution, try coding another
 * solution using the divide and conquer approach, which is more subtle.
 */

public class MaximumSubarray {
	public int maxSubArray(int[] A) {
		int max = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = 0; i < A.length; i++) {
			sum += A[i];
			if (sum > max) {
				max = sum;
			}
			if (sum < 0) {
				sum = 0;
			}
		}
		return max;
	}
	public int maxSubArrayDP(int[] A) {  
	    if(A==null || A.length==0)  
	        return 0;  
	    int global = A[0];  
	    int local = A[0];  
	    for(int i=1;i<A.length;i++)  
	    {  
	        local = Math.max(A[i],local+A[i]);  
	        global = Math.max(local,global);  
	    }  
	    return global;  
	}
	/*
	 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
	 * For example, given the array [2,3,-2,4],
	 * the contiguous subarray [2,3] has the largest product = 6.
	 */
	public int maxProductSubarray(int[] A) {  
	    if(A==null || A.length==0)  
	        return 0;  
	    if(A.length == 1)  
	        return A[0];  
	    int max_local = A[0];  
	    int min_local = A[0];  
	    int global = A[0];  
	    for(int i=1;i<A.length;i++)  
	    {  
	        int max_copy = max_local;  
	        max_local = Math.max(Math.max(A[i]*max_local, A[i]), A[i]*min_local);  
	        min_local = Math.min(Math.min(A[i]*max_copy, A[i]), A[i]*min_local);  
	        global = Math.max(global, max_local);  
	    }  
	    return global;  
	}
}
