//Memoization Approach

import java.util.*;

// Class to solve Ninja and his friends using memoization
class Solution {
    // Recursive function with memoization
    int solve(int i, int j1, int j2, int n, int m,
              int[][] grid, int[][][] dp) {
        // Out of boundary check
        if (j1 < 0 || j1 >= m || j2 < 0 || j2 >= m)
            return (int)(-1e9);
        
        // Base case: last row
        if (i == n - 1) {
            if (j1 == j2) return grid[i][j1];
            else return grid[i][j1] + grid[i][j2];
        }
        
        // If already computed return it
        if (dp[i][j1][j2] != -1) return dp[i][j1][j2];
        
        // Take chocolates from current cell(s)
        int maxi = (int)(-1e9);
        int curr = (j1 == j2) ? grid[i][j1] : grid[i][j1] + grid[i][j2];
        
        // Try all 9 moves
        for (int dj1 = -1; dj1 <= 1; dj1++) {
            for (int dj2 = -1; dj2 <= 1; dj2++) {
                int ans = curr + solve(i + 1, j1 + dj1, j2 + dj2,
                                       n, m, grid, dp);
                maxi = Math.max(maxi, ans);
            }
        }
        // Store result
        return dp[i][j1][j2] = maxi;
    }
    
    // Main function to call
    int maximumChocolates(int n, int m, int[][] grid) {
        int[][][] dp = new int[n][m][m];
        for (int[][] arr2d : dp) {
            for (int[] arr1d : arr2d) {
                Arrays.fill(arr1d, -1);
            }
        }
        return solve(0, 0, m - 1, n, m, grid, dp);
    }
}

// Driver class
public class Main {
    public static void main(String[] args) {
        int[][] grid = {
            {2, 3, 1, 2},
            {3, 4, 2, 2},
            {5, 6, 3, 5}
        };
        int n = grid.length, m = grid[0].length;
        Solution obj = new Solution();
        System.out.println(obj.maximumChocolates(n, m, grid));
    }
}


//Tabulation Approach

import java.util.*;

// Class to solve Ninja and his friends using tabulation
class Solution {
    int maximumChocolates(int n, int m, int[][] grid) {
        // 3D DP table
        int[][][] dp = new int[n][m][m];
        
        // Base case: last row
        for (int j1 = 0; j1 < m; j1++) {
            for (int j2 = 0; j2 < m; j2++) {
                if (j1 == j2) dp[n-1][j1][j2] = grid[n-1][j1];
                else dp[n-1][j1][j2] = grid[n-1][j1] + grid[n-1][j2];
            }
        }
        
        // Fill DP table bottom-up
        for (int i = n - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {
                    int maxi = (int)(-1e9);
                    int curr = (j1 == j2) ? grid[i][j1] 
                                          : grid[i][j1] + grid[i][j2];
                    // Try all 9 moves
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int newJ1 = j1 + dj1;
                            int newJ2 = j2 + dj2;
                            if (newJ1 >= 0 && newJ1 < m &&
                                newJ2 >= 0 && newJ2 < m) {
                                maxi = Math.max(maxi, curr + 
                                       dp[i+1][newJ1][newJ2]);
                            } else {
                                maxi = Math.max(maxi, (int)(-1e9));
                            }
                        }
                    }
                    dp[i][j1][j2] = maxi;
                }
            }
        }
        return dp[0][0][m-1];
    }
}

// Driver class
public class Main {
    public static void main(String[] args) {
        int[][] grid = {
            {2, 3, 1, 2},
            {3, 4, 2, 2},
            {5, 6, 3, 5}
        };
        int n = grid.length, m = grid[0].length;
        Solution obj = new Solution();
        System.out.println(obj.maximumChocolates(n, m, grid));
    }
}


//Space Optimization Approach

import java.util.*;

// Class to solve Ninja and his friends using space optimization
class Solution {
    int maximumChocolates(int n, int m, int[][] grid) {
        // Next row dp array
        int[][] next = new int[m][m];
        // Current row dp array
        int[][] curr = new int[m][m];
        
        // Base case: last row
        for (int j1 = 0; j1 < m; j1++) {
            for (int j2 = 0; j2 < m; j2++) {
                if (j1 == j2) next[j1][j2] = grid[n-1][j1];
                else next[j1][j2] = grid[n-1][j1] + grid[n-1][j2];
            }
        }
        
        // Fill DP table bottom-up
        for (int i = n - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {
                    int maxi = (int)(-1e9);
                    int currChoco = (j1 == j2) ? grid[i][j1] 
                                               : grid[i][j1] + grid[i][j2];
                    // Try all 9 moves
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            int newJ1 = j1 + dj1;
                            int newJ2 = j2 + dj2;
                            if (newJ1 >= 0 && newJ1 < m &&
                                newJ2 >= 0 && newJ2 < m) {
                                maxi = Math.max(maxi, currChoco + 
                                                next[newJ1][newJ2]);
                            } else {
                                maxi = Math.max(maxi, (int)(-1e9));
                            }
                        }
                    }
                    curr[j1][j2] = maxi;
                }
            }
            // Move current row to next row
            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {
                    next[j1][j2] = curr[j1][j2];
                }
            }
        }
        // Answer is starting position
        return next[0][m-1];
    }
}

// Driver class
public class Main {
    public static void main(String[] args) {
        int[][] grid = {
            {2, 3, 1, 2},
            {3, 4, 2, 2},
            {5, 6, 3, 5}
        };
        int n = grid.length, m = grid[0].length;
        Solution obj = new Solution();
        System.out.println(obj.maximumChocolates(n, m, grid));
    }
}
