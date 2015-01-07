package algorithm;


public class SimpleAlgoSets {

	static boolean isPrime(int n) {
		if(n==1) return false;
	    //check if n is a multiple of 2
	    if (n%2==0&&n!=2) return false;
	    //if not, then just check the odds
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}
	
	
	public static void main(String[] args) {
		for(int i=0;i<=100;i++){
	        if(isPrime(i))System.out.print(i+" ");;
	    }
	}

}
