package tek;

import java.math.BigInteger;
import java.util.TreeSet;

public class UberalInterview {

	public static void main(String args[]){
		System.out.println("question #1:");
		System.out.printf("Expected: 27 Result:%2d\n", question1(1));
		System.out.printf("Expected: 87 Result:%2d\n", question1(2));
		System.out.printf("Expected: 87 Result:%2d\n", question1(19));

		System.out.println("question #2:");
		System.out.printf("Count of numbers applicable from 1 to 100 are: %d\n", question2(100));
		
		System.out.println("question #3:");
		System.out.println("result for size of 6:");
		question3(6);
	}
	
	public static int question1(int n){
		BigInteger tn = BigInteger.valueOf(3);
		for(int i=0;i<n;i++){
			tn = BigInteger.valueOf(3).pow(tn.mod(BigInteger.valueOf(100)).intValue());
		}
		return tn.mod(BigInteger.valueOf(100)).intValue();
	}
	
	public static int question2(int n){
		int count = 0;
		for(int i=3;i<=n;i++){
			for(int j=1;j<2+(i/2);j++){
				int sum=0;
				int x = j;
				do{
					sum+=(x++);
					if(sum==i){
						count++;
						j=3+(i/2);
						break;
					}
				}while(sum<i);
			}
		}
		return count;
	}
	
	public static void question3(int n){
		int a[][] = new int[n][n];
		for(int x=1;x<n;x++){
			for(int y=0;y<x;y++){
				int axy[] = new int[x+y];
				for(int i=0;i<x;i++){
					axy[i] = a[i][y];
				}
				for(int i=0;i<y;i++){
					axy[x+i] = a[x][i];
				}
				a[x][y] = currentMinimumValue(axy);
				a[y][x] = a[x][y];
			}
		}
		
		for(int y=n-1;y>=0;y--){
			for(int x=0;x<n;x++){
				System.out.print(a[x][y]+" ");
			}
			System.out.println();
		}
	}
	private static int currentMinimumValue(int[] a){
		TreeSet<Integer> set = new TreeSet<Integer>();
		for(int i =0;i<a.length;i++){
			set.add(a[i]);
		}
		Integer[] sortedA = set.toArray(new Integer[0]);
		for(int i=0;i<sortedA.length;i++){
			if(sortedA[i]>i){
				return i;
			}
		}
		return a.length;
	}
	
}
