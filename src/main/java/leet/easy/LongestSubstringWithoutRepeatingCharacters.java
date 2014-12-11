package leet.easy;

import java.util.Arrays;

/**
 * Given a string, find the length of the longest substring without repeating
 * characters. For example, the longest substring without repeating letters for
 * "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring
 * is "b", with the length of 1.
 */

public class LongestSubstringWithoutRepeatingCharacters {
	public int lengthOfLongestSubstring(String s) {
        if(s==null||s.length()==0)return 0;
        int l=0,max=1;
        int[] a=new int[256];
        Arrays.fill(a, -1);
        for(int i=0;i<s.length();i++){
        	if(a[s.charAt(i)]>=l){
        		l=a[s.charAt(i)]+1;
        	}
        	max=Math.max(max, i-l+1);
        	a[s.charAt(i)]=i;
        }
        return max;
    }
}