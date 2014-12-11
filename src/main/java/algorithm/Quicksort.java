package algorithm;

import java.util.Arrays;

public class Quicksort {
	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	public void quicksort(int[] arr, int left, int right) {
		int pivot = arr[(left + right) >> 1];
		int i = left;
		int j = right;
		while (i <= j) {
			while (arr[i] < pivot)
				i++;
			while (arr[j] > pivot)
				j--;
			if (i <= j) {
				swap(arr, i++, j--);
			}
		}
		if (left < j) {
			quicksort(arr, left, j);
		}
		if (i < right) {
			quicksort(arr, i, right);
		}
	}
	
	public int quickSelect(int[] arr, int k) {
		if(k>=arr.length)return Integer.MIN_VALUE;
		return quickSelect(arr, k, 0, arr.length-1);
	}
	private int quickSelect(int[] arr, int k, int left, int right) {
		int pivot = arr[(left + right) >> 1];
		int i = left;
		int j = right;
		while (i <= j) {
			while (arr[i] < pivot)
				i++;
			while (arr[j] > pivot)
				j--;
			if (i <= j) {
				swap(arr, i++, j--);
			}
		}
		if(i-j==2){
			j++;
		}else{
			if(pivot==arr[i]){
				j++;
				i++;
			}
		}
		if (j-left==k) {
			return arr[j];
		}else if(j-left>k){
			return quickSelect(arr, k, left, j);
		}else{
			return quickSelect(arr, k-(j-left+1), i, right);
		}
	}
	public static void main(String args[]){
		int a[] = {5,2,3,2,4,2,1};
		System.out.println(new Quicksort().quickSelect(a, 5));
		new Quicksort().quicksort(a, 0, a.length-1);
		System.out.println(Arrays.toString(a));
	}
}
