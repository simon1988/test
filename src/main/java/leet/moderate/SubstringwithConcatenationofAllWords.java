package leet.moderate;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * You are given a string, S, and a list of words, L, that are all of the same
 * length. Find all starting indices of substring(s) in S that is a
 * concatenation of each word in L exactly once and without any intervening
 * characters.
 * 
 * For example, given: S: "barfoothefoobarman" L: ["foo", "bar"]
 * 
 * You should return the indices: [0,9]. (order does not matter).
 */

public class SubstringwithConcatenationofAllWords {
    public ArrayList<Integer> findSubstring(String S, String[] L) {
        HashMap<String, Integer> dict = new HashMap<String, Integer>();
        for (String l : L) {
            Integer count = dict.get(l);
            if (count == null) {
                dict.put(l, 1);
            } else {
                dict.put(l, count + 1);
            }
        }
        int wordSize = L[0].length();
        int windowSize = wordSize * L.length;

        ArrayList<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i <= S.length() - windowSize; i++) {
            HashMap<String, Integer> temp = new HashMap<String, Integer>();
            boolean skip = false;
            for (int j = 0; j < windowSize; j += wordSize) {
                String word = S.substring(i + j, i + j + wordSize);
                Integer count = dict.get(word);
                if (count == null) {
                    skip = true;
                    break;
                }
                Integer tempCount = temp.get(word);
                if (tempCount == null) {
                    temp.put(word, 1);
                } else if (count == tempCount) {
                    skip = true;
                    break;
                } else {
                    temp.put(word, tempCount + 1);
                }
            }
            if (!skip && dict.equals(temp)) {
                ans.add(i);
            }
        }
        return ans;
    }
}