package leet;


/**
 * Given two binary strings, return their sum (also a binary string).
 * 
 * For example, a = "11" b = "1" Return "100".
 */

public class AddBinary {
	public String addBinary(String a, String b) {
		int i = a.length()-1;
		int j = b.length()-1;
		int carry = 0;
		
		StringBuffer sb = new StringBuffer();
		
		while(i>=0||j>=0||carry>0){
			int aValue = i>=0?(a.charAt(i)-'0'):0;
			int bValue = j>=0?(b.charAt(j)-'0'):0;
			sb.append((aValue+bValue+carry)%2);
			carry=(aValue+bValue+carry)/2;
			i--;
			j--;
		}
		return sb.reverse().toString();
	}
	
	public static void main(String args[]){
		AddBinary instance = new AddBinary();
		LeetUtil.print("100", instance.addBinary("11", "1"));
		LeetUtil.print("1011", instance.addBinary("101", "110"));
	}
}