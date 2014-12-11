package leet.easy;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a +
 * b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * 
 * Note:
 * 
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ? b ?
 * c) The solution set must not contain duplicate triplets.
 * 
 * For example, given array S = {-1 0 1 2 -1 -4},
 * 
 * A solution set is: (-1, 0, 1) (-1, -1, 2)
 */

public class ThreeSum {
	public List<List<Integer>> threeSum(int[] num){
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num==null||num.length<3)return res;
        Arrays.sort(num);
        for(int i=0;i<num.length-2;i++){
            if(i>0&&num[i]==num[i-1])continue;
            res.addAll(twoSum(num,i+1,0-num[i]));
        }
        return res;
    }
    private List<List<Integer>> twoSum(int[] num,int l,int target){
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        int r=num.length-1;
        while(l<r){
            int cur=num[l]+num[r];
            if(cur==target){
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(0-target);
                list.add(num[l]);
                list.add(num[r]);
                res.add(list);
                l++;
                r--;
                while(l<r&&num[l]==num[l-1])l++;
                while(l<r&&num[r]==num[r+1])r--;
            }else if(cur>target){
                r--;
            }else{
                l++;
            }
        }
        return res;
    }
//    Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
//    Return the sum of the three integers. You may assume that each input would have exactly one solution.
//
//    For example, given array S = {-1 2 1 -4}, and target = 1.
//
//    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
    public int threeSumClosest(int[] num, int target) {
        Arrays.sort(num);
        int minSum = num[0] + num[1] + num[2];
        for (int i = 0; i < num.length - 2; i++) {
            if (i > 0 && num[i] == num[i - 1]) {
                continue;
            }
            int twoSum = target - num[i];
            int begin = i + 1;
            int end = num.length - 1;
            while (begin < end) {
                int sum = num[begin] + num[end];
                if (Math.abs(twoSum - sum) < Math.abs(target - minSum)) {
                    minSum = sum + num[i];
                }
                if (sum < twoSum) {
                    begin++;
                } else if (sum > twoSum) {
                    end--;
                } else {
                    return target;
                }
            }
        }
        return minSum;
    }
    public static void main(String args[]){
    	ThreeSum t=new ThreeSum();
    	int a[] ={-1,0,1};
    	System.out.println(t.threeSum(a));
    }
}