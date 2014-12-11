package leet.moderate;

/**
 * Follow up for "Unique Paths":
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 *
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 *
 * [
 *  [0,0,0],
 *  [0,1,0],
 *  [0,0,0]
 * ]
 * 
 * The total number of unique paths is 2.
 *
 * Note: m and n will be at most 100.
 *
 */


public class UniquePaths {
	public int uniquePaths(int m, int n) {
		if (m == 0 || n == 0)
			return 0;
		int[][] map = new int[m][n];
		for (int i = 0; i < m; i++) {
			map[i][0] = 1;
		}
		for (int j = 0; j < n; j++) {
			map[0][j] = 1;
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				map[i][j] = map[i - 1][j] + map[i][j - 1];
			}
		}
		return map[m - 1][n - 1];
	}
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		if (m == 0)
			return 0;
		int n = obstacleGrid[0].length;
		int[][] map = new int[m][n];
		map[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
		for (int i = 1; i < m; i++) {
			map[i][0] = obstacleGrid[i][0] == 1 ? 0 : map[i - 1][0];
		}
		for (int j = 1; j < n; j++) {
			map[0][j] = obstacleGrid[0][j] == 1 ? 0 : map[0][j - 1];
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				map[i][j] = obstacleGrid[i][j] == 1 ? 0 : map[i - 1][j]
						+ map[i][j - 1];
			}
		}
		return map[m - 1][n - 1];
	}
	/**
	 * Given a m x n grid filled with non-negative numbers, find a path from top
	 * left to bottom right which minimizes the sum of all numbers along its path.
	 * 
	 * Note: You can only move either down or right at any point in time.
	 */
	public int minPathSum(int[][] grid) {
		int[][] path = new int[grid.length][grid[0].length];
		path[0][0] = grid[0][0];
		for (int i = 1; i < grid.length; i++) {
			path[i][0] = path[i - 1][0] + grid[i][0];
		}
		for (int j = 1; j < grid[0].length; j++) {
			path[0][j] = path[0][j - 1] + grid[0][j];
		}
		for (int i = 1; i < grid.length; i++)
			for (int j = 1; j < grid[0].length; j++) {
				path[i][j] = Math.min(path[i - 1][j], path[i][j - 1])
						+ grid[i][j];
			}
		return path[grid.length - 1][grid[0].length - 1];
	}
}
