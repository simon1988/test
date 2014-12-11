package leet.easy;


/**
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * 
 * 1 is read off as "one 1" or 11. 11 is read off as "two 1s" or 21. 21 is read
 * off as "one 2, then one 1" or 1211.
 * 
 * Given an integer n, generate the nth sequence.
 * 
 * Note: The sequence of integers will be represented as a string.
 */

public class CountandSay {
	public String countAndSay(int n) {
	    if(n<1)return "";
	    String res="1";
	    for(int i=1;i<n;i++){
	        StringBuilder cur=new StringBuilder();
	        int count=1;
	        for(int j=0;j<res.length();j++){
	            if(j+1<res.length()&&res.charAt(j)==res.charAt(j+1)){
	                count++;
	            }else{
	                cur.append(count);
	                cur.append(res.charAt(j));
	                count=1;
	            }
	        }
	        res=cur.toString();
	    }
	    return res;
	}
}