package leet.easy;


/**
 * A message containing letters from A-Z is being encoded to numbers using the
 * following mapping:
 * 
 * 'A' -> 1 'B' -> 2 ... 'Z' -> 26 Given an encoded message containing digits,
 * determine the total number of ways to decode it.
 * 
 * For example, Given encoded message "12", it could be decoded as "AB" (1 2) or
 * "L" (12).
 * 
 * The number of ways decoding "12" is 2.
 */

public class DecodeWays {
	public static int numDecodings(String s){
	    if(s==null||s.length()==0||s.charAt(0)=='0')return 0;
	    int dp[] = new int[s.length()+1];
	    dp[0]=1;
	    dp[1]=1;
	    for(int i=1;i<s.length();i++){
	        if(s.charAt(i)=='0'){
	            if(s.charAt(i-1)=='1'||s.charAt(i-1)=='2'){
	                dp[i+1]=dp[i-1];
	            }else{
	                return 0;
	            }
	        }else{
	            if(s.charAt(i-1)=='1'||(s.charAt(i-1)=='2'&&s.charAt(i)<='6')){
	                dp[i+1]=dp[i]+dp[i-1];
	            }else{
	                dp[i+1]=dp[i];
	            }
	        }
	    }
	    return dp[dp.length-1];
	}
	public static void main(String args[]){
		System.out.print(numDecodings("26"));
	}
}