package tek.interview;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Test1 {

	static int func1(int A[]){
		BigInteger left=BigInteger.ZERO,right=BigInteger.ZERO;
		for(int i:A){
			right=right.add(BigInteger.valueOf(i));
		}
		for(int i=0;i<A.length;i++){
			BigInteger tmp=BigInteger.valueOf(A[i]);
			right=right.subtract(tmp);
			if(left.compareTo(right)==0)return i;
			left=left.add(tmp);
		}
		return -1;
	}
	
	static int func2(int A[]){
		int n1=0,n2=0;
		for(int n:A){
			if(n%2==0){
				n1++;
			}else{
				n2++;
			}
		}
		//Cn2=n*(n-1)/2...
		return n1*(n1-1)/2+n2*(n2-1)/2;
	}
	/**
	 * @author Simon.Niu
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n1=2147483646,n2=2147483646;
		int n3=n1+n2;
		System.out.println(n3);
		int A[]={2147483646,2222,-3,-5};
		System.out.println(func1(A));
		LinkedList<Integer> list=new LinkedList<Integer>();
		list.push(1);
		list.push(2);
		while(!list.isEmpty()){
			System.out.println(list.pop());
		}
		list.offer(1);
		list.offer(2);
		while(!list.isEmpty()){
			System.out.println(list.poll());
		}
	}

}
