package leet.easy;

/**
 * 
 * Given an unsorted array of nonnegative integers, find a continous subarray which adds to a given number.

	Examples:
	
	Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33
	Ouptut: Sum found between indexes 2 and 4
	
	Input: arr[] = {1, 4, 0, 0, 3, 10, 5}, sum = 7
	Ouptut: Sum found between indexes 1 and 4
	
	Input: arr[] = {1, 4}, sum = 0
	Output: No subarray found
 *
 */
public class FindSubarrayWithGivenSum {
	public static boolean search(int a[], int sum){
		if(a==null||sum<=0)return false;
		int l=0;
		int cur=0;
		for(int i=0;i<a.length;i++){
			cur=cur+a[i];
			while(cur>sum){
				cur=cur-a[l];
				l++;
			}
			if(cur==sum){
				System.out.println("l:"+l+"r:"+i);
				return true;
			}
		}
		return false;
	}
	public static void main(String args[]){
		int arr[] = {15, 2, 4, 8, 9, 5, 10, 23};
		search(arr,23);
	}
}
