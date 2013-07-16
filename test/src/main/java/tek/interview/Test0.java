package tek.interview;

import java.math.BigInteger;
import java.util.Scanner;

public class Test0 {

	private void func1(){
		Scanner scanner=new Scanner(System.in);
		int num=scanner.nextInt();
		scanner.close();
		int result=0;
		System.out.printf("%04x\n", num);
		for(int i=31;i>=0;i--){
			int j=(num>>i)&0x1;
			System.out.print(j);
			result+=j;
		}
		System.out.println("\nresult is "+result);
	}
	private void func1Better(){
		Scanner scanner=new Scanner(System.in);
		int num=scanner.nextInt();
		scanner.close();
		int result=0;
		System.out.printf("%04x\n", num);
		while(num!=0){
			num=num&(num-1);
			result++;
		}
		System.out.println("\nresult is "+result);
	}
	
	private void func2(){
		Scanner scanner=new Scanner(System.in);
		BigInteger num=scanner.nextBigInteger();
		scanner.close();
		System.out.println("bit length: "+num.bitLength());
		int result=0;
		if(num.compareTo(BigInteger.ZERO)<0){
			result=num.negate().bitCount();
		}else{
			result=num.bitCount();
		}
		
		//System.out.printf("%x\n", num);
//		for(int i=num.;i>=0;i--){
//			int j=(num>>i)&0x1;
//			System.out.print(j);
//			result+=j;
//		}
		System.out.println("\nresult is "+result);
	}

	/**
	 * @author Simon.Niu
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test0 test=new Test0();
		//test.func1();
		//test.func1Better();
		test.func2();
	}

}
