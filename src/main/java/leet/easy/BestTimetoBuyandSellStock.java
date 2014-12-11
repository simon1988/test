package leet.easy;


/**
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * If you were only permitted to complete at most one transaction (ie, buy one
 * and sell one share of the stock), design an algorithm to find the maximum
 * profit.
 */
public class BestTimetoBuyandSellStock {
	public int maxProfit(int[] prices) {
		int min = prices[0];
		int maxProfit = 0;
		for(int price:prices){
			min=price<min?price:min;
			maxProfit=Math.max(maxProfit, price-min);
		}
		return maxProfit;
	}
	public int maxProfit2(int[] prices) {
		int min = prices[0];
		int maxProfit = 0;
		int secondMaxProfit = 0;
		for(int price:prices){
			min=price<min?price:min;
			if(maxProfit>price-min){
				secondMaxProfit=Math.max(secondMaxProfit, price-min);
			}else{
				secondMaxProfit = maxProfit;
				maxProfit= price-min;
			}
		}
		return maxProfit+secondMaxProfit;
	}
	public int maxProfitDP(int[] prices) {  
	    if(prices==null || prices.length==0)  
	        return 0;  
	    int local = 0;  
	    int global = 0;  
	    for(int i=0;i<prices.length-1;i++)  
	    {  
	        local = Math.max(local+prices[i+1]-prices[i],0);  
	        global = Math.max(local, global);  
	    }  
	    return global;  
	}
	public int maxProfit2DP(int[] prices) {  
	    if(prices==null || prices.length==0)  
	        return 0;  
	    int[] local = new int[3];  
	    int[] global = new int[3];  
	    for(int i=0;i<prices.length-1;i++)  
	    {  
	        int diff = prices[i+1]-prices[i];  
	        for(int j=2;j>=1;j--)  
	        {  
	            local[j] = Math.max(global[j-1]+(diff>0?diff:0), local[j]+diff);  
	            global[j] = Math.max(local[j],global[j]);  
	        }  
	    }  
	    return global[2];  
	} 
}