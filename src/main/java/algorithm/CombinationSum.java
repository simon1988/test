package algorithm;

import java.util.HashMap;
import java.util.Map;

public class CombinationSum {
	public void testFootballCombinationSum() {
		int[] scores = { 6, 2, 1,3 };
		footballCombinationSum(6, scores);
	}

	public void footballCombinationSum(int total, int[] scores) {

		Map<Integer, Integer> scoresUsed = new HashMap<>();
		footballCombinationSumNoDup(scoresUsed, total, scores, 0);
	}

	private void footballCombinationSumNoDup(Map<Integer, Integer> scoresUsed,
			int total, int[] scores, int scoreStartIdx) {

		if (total == 0) {
			System.out.println(scoresUsed);
			return;
		}

		for (int i = scoreStartIdx; i < scores.length; i++) {
			if (total >= scores[i]) {
				// increase count
				Integer count = scoresUsed.get(scores[i]);
				if (count == null) {
					count = 0;
				}
				scoresUsed.put(scores[i], count + 1);

				footballCombinationSumNoDup(scoresUsed, total - scores[i],
						scores, i);

				// decrease count
				count = scoresUsed.get(scores[i]);
				scoresUsed.put(scores[i], count - 1);
			}
		}
	}
}
