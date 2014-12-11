package leet.moderate;


import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers.
 * 
 * If such arrangement is not possible, it must rearrange it as the lowest
 * possible order (ie, sorted in ascending order).
 * 
 * The replacement must be in-place, do not allocate extra memory.
 * 
 * Here are some examples. 
 * 
 * Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 -> 1,3,2
 * 3,2,1 -> 1,2,3
 * 1,1,5 -> 1,5,1
 */

public class NextPermutation {
	public void nextPermutation(int[] num){  
	    if(num==null || num.length==0)  
	        return;  
	    int i = num.length-2;  
	    while(i>=0 && num[i]>=num[i+1])  
	    {  
	        i--;  
	    }  
	    if(i>=0)  
	    {  
	        int j=i+1;  
	        while(j<num.length && num[j]>num[i])  
	        {  
	            j++;  
	        }  
	        j--;  
	        int temp = num[i];  
	        num[i] = num[j];  
	        num[j] = temp;  
	    }  
	    reverse(num, i+1);  
	}  
	private void reverse(int[] num, int index){  
	    int l = index;  
	    int r = num.length-1;  
	    while(l<r)  
	    {  
	        int temp = num[l];  
	        num[l] = num[r];  
	        num[r] = temp;  
	        l++;  
	        r--;  
	    }  
	}
}