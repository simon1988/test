package leet;


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
	
	public static void main(String args[]){
		BestTimetoBuyandSellStock instance = new BestTimetoBuyandSellStock();
		int[] prices = {1,-7,0,6,-8,4,-9,5};
		LeetUtil.print(14, instance.maxProfit(prices));
	}
}