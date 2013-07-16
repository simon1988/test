package tek.interview;

public class Test3 {

	public void odd(int n){
		if(n<2)return;
		for(int i=2;i<=n;i++){
			boolean isOdd = true;
			for(int j=2;j<=Math.sqrt(i);j++){
				if(i%j==0){
					isOdd=false;
					break;
				}
			}
			if(isOdd)System.out.print(i+" ");
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test3 test = new Test3();
		test.odd(100);
	}

}
