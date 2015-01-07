package tek;

import java.util.Scanner;

public class Amazon3 {
	public static void main(String args[]){
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		if(n<=0)return;
		int input[] = new int[n];
		for(int i=0;i<n;i++){
			input[i]=scanner.nextInt();
		}
		scanner.close();
		int l=0,r=n-1;
		while(r-l>1){
			int m = (l+r)/2;
			if(input[m]-input[l]<input[r]-input[m]){
				l=m;
			}else{
				r=m;
			}
		}
		System.out.println((input[l]+input[l+1])/2);
	}
}
