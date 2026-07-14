import java.util.*;

class Solution {
    // Function to solve the linear house robber problem
    public long solve(List<Integer> arr) {
        int n = arr.size();
        
        // If there's only one house, return its value
        if (n == 1) return arr.get(0);

        // prev stores the maximum sum till the previous index
        long prev = arr.get(0);

        // prev2 stores the maximum sum till index before previous
        long prev2 = 0;

        // Iterate from the second house
        for (int i = 1; i < n; i++) {
            // Pick the current house and add value from prev2
            long pick = arr.get(i);
            if (i > 1) pick += prev2;

            // Skip the current house
            long nonPick = prev;

            // Choose maximum
            long cur_i = Math.max(pick, nonPick);

            // Update prev2 and prev
            prev2 = prev;
            prev = cur_i;
        }
        return prev;
    }

    // Function to solve the circular house robber problem
    public long robStreet(int n, int[] arr) {
        // Handle edge cases
        if (n == 0) return 0;
        if (n == 1) return arr[0];

        // arr1 excludes first house
        List<Integer> arr1 = new ArrayList<>();
        // arr2 excludes last house
        List<Integer> arr2 = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            if (i != 0) arr1.add(arr[i]);
            if (i != n - 1) arr2.add(arr[i]);
        }

        // Return maximum of both cases
        return Math.max(solve(arr1), solve(arr2));
    }
}

public class Main {
    public static void main(String[] args) {
        // Example input
        int[] arr = {1, 5, 1, 2, 6};
        int n = arr.length;

        // Create Solution object
        Solution sol = new Solution();

        // Output result
        System.out.println(sol.robStreet(n, arr));
    }
}