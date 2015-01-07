package tek;


public class Amazon1 {
    static int getMaxProfits(int[] prices) {
    	int local[][] = new int[prices.length+1][4];
    	int global[][] = new int[prices.length+1][4];
    	for(int i=1;i<prices.length+1;i++){
    		int profit=(i==1?0:prices[i-1]-prices[i-2]);
    		for(int j=1;j<=3;j++){
    			local[i][j] = Math.max(global[i-1][j-1]+(profit>0?profit:0), local[i-1][j]+profit);
    			global[i][j] = Math.max(local[i][j],global[i-1][j]);
    		}
    	}
    	return global[prices.length][3];
    }
	public static void main(String args[]){
		int a[] = {1,1,2,1,4,1,5,1,2};
		System.out.println(getMaxProfits(a));
	}
}
