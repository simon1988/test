package leet.hard;

/**
 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

Corner Cases:
A line other than the last line might contain only one word. What should you do in this case?
In this case, that line should be left-justified.
 */
import java.util.ArrayList;

public class TextJustification {
	public ArrayList<String> fullJustify(String[] words, int L) {
		ArrayList<String> ret = new ArrayList<String>();
		int length = words.length;
		if (length == 0) return ret;
		int start = 0, end = start;
		int len = 0;
		while (start < length) {
			StringBuffer line = new StringBuffer();
			while (end < length) {
				int sl = words[end].length();
				if (len + (end - start) + sl > L) {
					break;
				}
				len += sl;
				end++;
			}
			end--;
			if (end < start) {
				end = start;
			}
			if (start == end) {
				line.append(words[start]);
				int spaceCount = L - words[start].length();
				for (int i = 0; i < spaceCount; i++) {
					line.append(' ');
				}
				ret.add(line.toString());
			} else {
				boolean lastLine = end == length - 1;
				int spaceBase = lastLine ? 1 : (L - len) / (end - start);
				int bonus = lastLine ? 0 : L - len - spaceBase * (end - start);
				line.append(words[start]);
				for (int i = start + 1; i <= end; i++) {
					for (int j = 0; j < spaceBase; j++) {
						line.append(' ');
					}
					if (bonus > 0) {
						line.append(' ');
						bonus--;
					}
					line.append(words[i]);
				}
				if (lastLine) {
					for (int i = 0; i < L - len - (end - start); i++) {
						line.append(' ');
					}
				}
				ret.add(line.toString());
			}
			start = end + 1;
			end = start;
			len = 0;
		}
		return ret;
	}
}
