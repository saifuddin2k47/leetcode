class Solution {
    // Recursive helper function with memoization
    public int solve(int[] arr, int i, int[] dp) {
        // No element to pick
        if (i < 0) return 0;

        // Only one element
        if (i == 0) return arr[0];

        // Return memoized result
        if (dp[i] != -1) return dp[i];

        // Pick current and move 2 back
        int pick = arr[i] + solve(arr, i - 2, dp);

        // Don't pick current, move 1 back
        int notPick = solve(arr, i - 1, dp);

        // Memoize and return result
        return dp[i] = Math.max(pick, notPick);
    }

    // Main function to compute result
    public int maximumNonAdjacentSum(int[] arr) {
        int n = arr.length;

        // Initialize DP array
        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        // Start recursion
        return solve(arr, n - 1, dp);
    }
}

public class Main {
    public static void main(String[] args) {
        int[] arr = {2, 1, 4, 9};
        Solution obj = new Solution();

        // Print result
        System.out.println(obj.maximumNonAdjacentSum(arr));
    }
}