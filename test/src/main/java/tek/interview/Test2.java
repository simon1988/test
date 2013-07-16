package tek.interview;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Test2 {

	static int func1(int n) {
		if (n <= 1)
			return 1;
		int a1 = 0, a2 = 1, an = 0;
		for (int i = 0; i < n; i++) {
			an = a1 + a2;
			a1 = a2;
			a2 = an;
		}
		return an;
	}

	static int func2(String S) {
		if (S.length() <= 1) {
			return 1;
		}
		boolean chars[] = new boolean[26];
		Arrays.fill(chars, true);
		for (char c : S.toCharArray()) {
			chars[c - 'a'] = !chars[c - 'a'];
		}
		int numOfFalse = 0;
		for (boolean b : chars) {
			if (!b) {
				numOfFalse++;
			}
		}
		return numOfFalse <= 1 ? 1 : 0;
	}

	static int func3(int K, int[] A) {
		Arrays.sort(A);
		int result = 0;
		int left = 0, right = A.length - 1;
		while (left <= right) {
			int sum = A[left] + A[right];
			if (sum == K) {
				if (A[left] == A[right]) {
					result = result + (right - left + 1) * (right - left + 1);
					break;
				} else {
					result += 2;
				}
				if (A[left + 1] == A[left]) {
					left++;
				} else if (A[right - 1] == A[right]) {
					right--;
				} else {
					left++;
					right--;
				}
			} else if (sum > K) {
				right--;
			} else {
				left++;
			}
		}
		return result;
	}

	class Tree {
		public Tree(int x) {
			this.x = x;
		}

		public int x;
		public Tree l;
		public Tree r;
	}

	LinkedList<Tree> queue = new LinkedList<Tree>();

	int visible(Tree T) {

		if (T == null) {
			return 0;
		}

		queue.offer(T);

		int result = 1;
		while (!queue.isEmpty()) {

			T = queue.poll();

			if (T.l != null) {
				queue.offer(T.l);
				if (T.l.x >= T.x) {
					result++;
				} else {
					T.l.x = T.x;
				}
			}
			if (T.r != null) {
				queue.offer(T.r);
				if (T.r.x >= T.x) {
					result++;
				} else {
					T.r.x = T.x;
				}
			}
		}
		return result;
	}

	void func5() {
		Tree T = new Tree(5);
		T.l = new Tree(3);
		T.l.l = new Tree(20);
		T.l.r = new Tree(21);
		T.r = new Tree(10);
		T.r.l = new Tree(1);

		System.out.println("Func2 result is " + visible(T));
	}

	/**
	 * @author Simon.Niu
	 * @param args
	 */
	public static void main(String[] args) {

		int A[] = { 1, 1, 1 };

		// System.out.println("Func1 result is "+func1(5));
		//		
		// System.out.println("Func2 result is "+func2("abcavcba"));

		// System.out.println("Func1 result is " + func3(2, A));
		Test2 t = new Test2();
		t.func5();

	}

}
