//Memoization

import java.util.*;

class Solution {
    // Recursive helper to find min path sum starting at (row, col)
    private int dfs(int row, int col, int[][] matrix, int[][] dp) {
        // If column is out of bounds, return large value to ignore path
        if (col < 0 || col >= matrix[0].length) {
            return Integer.MAX_VALUE;
        }

        // If at last row, return matrix value at (row, col)
        if (row == matrix.length - 1) {
            return matrix[row][col];
        }

        // If already computed, return stored result
        if (dp[row][col] != -1) {
            return dp[row][col];
        }

        // Recursively compute min path sums from next row: down, down-left, down-right
        int down = dfs(row + 1, col, matrix, dp);
        int downLeft = dfs(row + 1, col - 1, matrix, dp);
        int downRight = dfs(row + 1, col + 1, matrix, dp);

        // Calculate minimum path sum for current cell
        int ans = matrix[row][col] + Math.min(down, Math.min(downLeft, downRight));

        // Store result and return
        dp[row][col] = ans;
        return ans;
    }

    // Main function to find minimum falling path sum
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        // Initialize dp with -1 for uncomputed states
        int[][] dp = new int[n][m];
        for (int[] row : dp) Arrays.fill(row, -1);

        int minSum = Integer.MAX_VALUE;

        // Try starting from each element in the first row
        for (int col = 0; col < m; col++) {
            minSum = Math.min(minSum, dfs(0, col, matrix, dp));
        }

        return minSum;
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 4, 3, 1},
            {2, 3, -1, -1},
            {1, 1, -1, 8}
        };

        Solution sol = new Solution();
        System.out.println("Minimum Falling Path Sum: " + sol.minFallingPathSum(matrix));
    }
}


//Tabulation

import java.util.*;

class Solution {
    // Function to find minimum falling path sum using bottom-up DP
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        // Create dp array same size as matrix
        int[][] dp = new int[n][m];

        // Initialize last row of dp same as matrix last row
        for (int col = 0; col < m; col++) {
            dp[n - 1][col] = matrix[n - 1][col];
        }

        // Iterate from second last row up to first row
        for (int row = n - 2; row >= 0; row--) {
            for (int col = 0; col < m; col++) {
                // Initialize minimum path sum as large number
                int down = dp[row + 1][col];

                int downLeft = (col > 0) ? dp[row + 1][col - 1] : Integer.MAX_VALUE;

                int downRight = (col < m - 1) ? dp[row + 1][col + 1] : Integer.MAX_VALUE;

                // Calculate minimum path sum for current cell
                dp[row][col] = matrix[row][col] + Math.min(down,
                        Math.min(downLeft, downRight));
            }
        }

        // Find minimum among first row elements
        int minSum = Integer.MAX_VALUE;
        for (int val : dp[0]) {
            minSum = Math.min(minSum, val);
        }
        return minSum;
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 4, 3, 1},
            {2, 3, -1, -1},
            {1, 1, -1, 8}
        };

        Solution sol = new Solution();
        System.out.println("Minimum Falling Path Sum: " + sol.minFallingPathSum(matrix));
    }
}


//Space Optimization

import java.util.*;

class Solution {
    // Function to find minimum falling path sum using bottom-up DP with O(m) space
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        // Initialize dp with last row of matrix
        int[] dp = Arrays.copyOf(matrix[n - 1], m);

        // Iterate from second last row up to first row
        for (int row = n - 2; row >= 0; row--) {
            int[] curr = new int[m];
            for (int col = 0; col < m; col++) {
                int down = dp[col];
                int downLeft = (col > 0) ? dp[col - 1] : Integer.MAX_VALUE;
                int downRight = (col < m - 1) ? dp[col + 1] : Integer.MAX_VALUE;

                // Calculate min path sum for current cell
                curr[col] = matrix[row][col] + Math.min(down, Math.min(downLeft, downRight));
            }
            dp = curr;  // update dp for next iteration
        }

        // Find minimum in dp (first row after iteration)
        int minSum = Integer.MAX_VALUE;
        for (int val : dp) {
            minSum = Math.min(minSum, val);
        }
        return minSum;
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 4, 3, 1},
            {2, 3, -1, -1},
            {1, 1, -1, 8}
        };

        Solution sol = new Solution();
        System.out.println("Minimum Falling Path Sum: " + sol.minFallingPathSum(matrix));
    }
}
