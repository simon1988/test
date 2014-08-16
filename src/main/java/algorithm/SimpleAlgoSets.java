package algorithm;


public class SimpleAlgoSets {

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
	
	public int theKthNumber(int[] a1, int[] a2 , int k){
		if(k>a1.length+a2.length||k<=0){
			throw new IllegalArgumentException("k");
		}
		int i=-1,j=-1;
		while(i+1<a1.length||j+1<a2.length){
			if(a1[i+1]<a2[j+1]){
				i++;
			}else{
				j++;
			}
			if(i+j==k-2){
				return Math.max(a1[i], a2[j]);
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		SimpleAlgoSets test = new SimpleAlgoSets();
		test.odd(100);
		int[] a1 = {1,2,4,6};
		int[] a2 = {3,5,7};
		System.out.println(test.theKthNumber(a1, a2 , 3));
	}

}
