package leet.easy;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Given an array of strings, return all groups of strings that are anagrams.
 * 
 * Note: All inputs will be in lower-case.
 * 
 * For example:
 * Input:　　["tea","and","ate","eat","den"]
 * Output:   ["tea","ate","eat"]
 */

public class Anagrams {
	public List<String> anagrams(String[] strs) {
        ArrayList<String> res = new ArrayList<String>();
        if(strs==null||strs.length==0)return res;
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        for(int i=0;i<strs.length;i++){
            char[] cs=strs[i].toCharArray();
            Arrays.sort(cs);
            String sortedStr = new String(cs);
            if(!map.containsKey(sortedStr)){
                map.put(sortedStr,new ArrayList<String>());
            }
            map.get(sortedStr).add(strs[i]);
        }
        for(List<String> list:map.values()){
            if(list.size()>1){
                res.addAll(list);
            }
        }
        return res;
    }
}