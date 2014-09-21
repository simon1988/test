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
	public static void main(String args[]){
		int a[] = {1,2,3,4,4,1};
		new Quicksort().quicksort(a, 0, a.length-1);
		System.out.println(Arrays.toString(a));
	}
}
