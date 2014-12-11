package leet.easy;


/**
 * Given a string S, find the longest palindromic substring in S. You may assume
 * that the maximum length of S is 1000, and there exists one unique longest
 * palindromic substring.
 */

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if(s==null||s.length()==0)return "";
        if(s.length()==1)return s;
        String max="";
        for(int i=0;i<s.length();i++){
            int l=i;
            int r=i;
            while(r<s.length()&&l>=0&&s.charAt(l)==s.charAt(r)){
                l--;
                r++;
            }
            if(r-l-1>max.length()){
                max=s.substring(l+1,r);
            }
        }
        for(int i=0;i<s.length();i++){
            int l=i;
            int r=i+1;
            while(r<s.length()&&l>=0&&s.charAt(l)==s.charAt(r)){
                l--;
                r++;
            }
            if(r-l-1>max.length()){
                max=s.substring(l+1,r);
            }
        }
        return max;
    }
}