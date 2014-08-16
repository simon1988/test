package leet;


/**
 * Say you have an array for which the ith element is the price of a given stock
 * on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete as many
 * transactions as you like (ie, buy one and sell one share of the stock
 * multiple times). However, you may not engage in multiple transactions at the
 * same time (ie, you must sell the stock before you buy again).
 */

public class BestTimetoBuyandSellStockII {
	public int maxProfit(int[] prices) {
		int last = prices[0];
		int maxProfit = 0;
		for(int price:prices){
			if(price>last){
				maxProfit=maxProfit+price-last;
			}

			last=price;
		}
		return maxProfit;
	}
	
	public static void main(String args[]){
		BestTimetoBuyandSellStockII instance = new BestTimetoBuyandSellStockII();
		int[] prices = {1,-7,0,6,-8,4,-9,5};
		LeetUtil.print(39, instance.maxProfit(prices));
	}
}