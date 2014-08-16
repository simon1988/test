package leet;


/**
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete at most two
 * transactions.
 * 
 * Note: You may not engage in multiple transactions at the same time (ie, you
 * must sell the stock before you buy again).
 */

public class BestTimetoBuyandSellStockIII {
	public int maxProfit(int[] prices) {
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
	
	public static void main(String args[]){
		BestTimetoBuyandSellStockIII instance = new BestTimetoBuyandSellStockIII();
		int[] prices = {1,-7,0,6,-8,4,-9,5};
		LeetUtil.print(27, instance.maxProfit(prices));
	}
}