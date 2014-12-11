package leet.easy;


/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Determine if you are able to reach the last index.
 * 
 * For example: A = [2,3,1,1,4], return true.
 * 
 * A = [3,2,1,0,4], return false.
 */

public class JumpGame {
	public boolean canJump(int[] A) {
		if (A.length <= 1)
			return true;
		int curMax = 0;
		int max = 0;
		for (int i = 0; i < A.length - 1; i++) {
			if (i > max)
				break;
			curMax = A[i] + i;
			if (curMax > max) {
				max = curMax;
			}
			if (max >= A.length - 1)
				return true;
		}
		return false;
	}
	public int jump(int[] A) {
		int ret = 0;
		int last = 0;
		int curr = 0;
		for (int i = 0; i < A.length; ++i) {
			if (i > last) {
				last = curr;
				++ret;
			}
			curr = Math.max(curr, i + A[i]);
		}
		return ret;
	}
	public boolean canJumpDP(int[] A) {  
	    if(A==null || A.length==0)  
	        return false;  
	    int reach = 0;  
	    for(int i=0;i<=reach&&i<A.length;i++)  
	    {  
	        reach = Math.max(A[i]+i,reach);  
	    }  
	    if(reach<A.length-1)  
	        return false;  
	    return true;  
	}
	public int jumpDP(int[] A) {  
	    if(A==null || A.length==0)  
	        return 0;  
	    int lastReach = 0;  
	    int reach = 0;  
	    int step = 0;  
	    for(int i=0;i<=reach&&i<A.length;i++){
	        if(i>lastReach){  
	            step++;  
	            lastReach = reach;  
	        }  
	        reach = Math.max(reach,A[i]+i);  
	    }  
	    if(reach<A.length-1)  
	        return 0;  
	    return step;  
	}
}