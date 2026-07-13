// Memoizaton Solution 

import java.util.*;

class Solution {
    // Function to calculate Fibonacci using memoization
    public int fib(int n, int[] dp) {
        // If base case return n
        if (n <= 1) return n;

        // If already computed, return stored value
        if (dp[n] != -1) return dp[n];

        // Otherwise compute and store
        dp[n] = fib(n - 1, dp) + fib(n - 2, dp);
        return dp[n];
    }
}

public class Main {
    public static void main(String[] args) {
        int n = 10;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        Solution sol = new Solution();
        System.out.println(sol.fib(n, dp));
    }
}


// Tabulation Solution

class Solution {
    // Function to calculate Fibonacci using tabulation
    public int fib(int n) {
        // If n is 0 or 1, return n
        if (n <= 1) return n;

        // Create dp array
        int[] dp = new int[n + 1];

        // Initialize base cases
        dp[0] = 0;
        dp[1] = 1;

        // Fill dp array iteratively
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // Return final answer
        return dp[n];
    }
}

public class Main {
    public static void main(String[] args) {
        int n = 10;
        Solution sol = new Solution();
        System.out.println(sol.fib(n));
    }
}