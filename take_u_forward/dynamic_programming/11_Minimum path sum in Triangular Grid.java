// //Memoization Approach

import java.util.*;

class Solution {
    // Recursive function with memoization
    public int solve(int i, int j, List<List<Integer>> triangle, int n, int[][] dp) {
        // If value already computed
        if (dp[i][j] != -1)
            return dp[i][j];

        // If at bottom row
        if (i == n - 1)
            return triangle.get(i).get(j);

        // Compute both downward and diagonal moves
        int down = triangle.get(i).get(j) + solve(i + 1, j, triangle, n, dp);
        int diag = triangle.get(i).get(j) + solve(i + 1, j + 1, triangle, n, dp);

        // Store and return min path sum
        return dp[i][j] = Math.min(down, diag);
    }

    // Function to start the process
    public int minimumPathSum(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return solve(0, 0, triangle, n, dp);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution obj = new Solution();
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(1));
        triangle.add(Arrays.asList(2, 3));
        triangle.add(Arrays.asList(3, 6, 7));
        triangle.add(Arrays.asList(8, 9, 6, 10));
        System.out.println(obj.minimumPathSum(triangle));
    }
}


//Tabulation Approach

import java.util.*;

class Solution {
    // Function to calculate minimum path sum using tabulation
    public int minimumPathSum(List<List<Integer>> triangle, int n) {
        // Create dp array
        int[][] dp = new int[n][n];

        // Fill last row
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle.get(n - 1).get(j);
        }

        // Fill rest of dp from bottom to top
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                // Take min of down and diagonal
                int down = triangle.get(i).get(j) + dp[i + 1][j];
                int diag = triangle.get(i).get(j) + dp[i + 1][j + 1];
                dp[i][j] = Math.min(down, diag);
            }
        }

        // Return top element
        return dp[0][0];
    }
}

public class Main {
    public static void main(String[] args) {
        // Define triangle
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(1));
        triangle.add(Arrays.asList(2, 3));
        triangle.add(Arrays.asList(3, 6, 7));
        triangle.add(Arrays.asList(8, 9, 6, 10));

        // Get size
        int n = triangle.size();

        // Create object and print result
        Solution solver = new Solution();
        System.out.println(solver.minimumPathSum(triangle, n));
    }
}


//Space Optimization Approach

import java.util.*;

class Solution {
    // Function to find the minimum path sum
    public int minimumPathSum(List<List<Integer>> triangle, int n) {
        
        // Create an array to store the next row
        int[] front = new int[n];
        
        // Create an array to store the current row
        int[] cur = new int[n];

        // Initialize front with last row of triangle
        for (int j = 0; j < n; j++) {
            front[j] = triangle.get(n - 1).get(j);
        }

        // Traverse rows from bottom to top
        for (int i = n - 2; i >= 0; i--) {
            
            // Traverse elements in current row
            for (int j = i; j >= 0; j--) {
                
                // Calculate path going down
                int down = triangle.get(i).get(j) + front[j];
                
                // Calculate path going diagonal
                int diagonal = triangle.get(i).get(j) + front[j + 1];
                
                // Store minimum in current row
                cur[j] = Math.min(down, diagonal);
            }

            // Update front row with current row
            front = cur.clone();
        }

        // Return top element (minimum path sum)
        return front[0];
    }
}

public class Main {
    public static void main(String[] args) {
        // Define triangle
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(1));
        triangle.add(Arrays.asList(2, 3));
        triangle.add(Arrays.asList(3, 6, 7));
        triangle.add(Arrays.asList(8, 9, 6, 10));

        // Get number of rows
        int n = triangle.size();

        // Create Solution object
        Solution sol = new Solution();

        // Print the result
        System.out.println(sol.minimumPathSum(triangle, n));
    }
}
