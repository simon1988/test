package leet;


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
        String ans = "1";
        n--;
        while (n > 0) {
            n--;
            StringBuilder builder = new StringBuilder();
            int count = 1;
            for (int i = 1; i < ans.length(); i++) {
                if (ans.charAt(i - 1) == ans.charAt(i)) {
                    count++;
                } else {
                    builder.append(count);
                    builder.append(ans.charAt(i - 1));
                    count = 1;
                }
            }
            builder.append(count);
            builder.append(ans.charAt(ans.length() - 1));
            ans = builder.toString();
        }
        return ans;
    }
}