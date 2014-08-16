package leet;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public ArrayList<List<String>> anagrams(String[] strs) {
		ArrayList<List<String>> result = new ArrayList<List<String>>();
		HashMap<String,List<String>> map = new HashMap<String,List<String>>();
		for(String s:strs){
			char c[]=s.toCharArray();
			Arrays.sort(c);
			String anagram = new String(c);
			if(!map.containsKey(anagram)){
				map.put(anagram, new ArrayList<String>());
			}
			map.get(anagram).add(s);
		}
		for(Map.Entry<String, List<String>> entry : map.entrySet()){
			if(entry.getValue().size()>1){
				result.add(entry.getValue());
			}
		}
		return result;
	}
	
	public static void main(String args[]){
		Anagrams instance = new Anagrams();
		String[] strs = {"tea","and","ate","eat","den"};
		LeetUtil.print("[tea,ate,eat]", instance.anagrams(strs).toString());
	}
}