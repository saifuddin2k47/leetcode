// Memoization Approach

#include <bits/stdc++.h>
using namespace std;

// Recursive function to calculate the maximum points for the ninja training
int f(int day, int last, vector<vector<int>> &points, vector<vector<int>> &dp) {
  // If the result for this day and last activity is already calculated, return it
  if (dp[day][last] != -1) return dp[day][last];

  // Base case: When we reach the first day (day == 0)
  if (day == 0) {
    int maxi = 0;
    // Calculate the maximum points for the first day by choosing an activity
    // different from the last one
    for (int i = 0; i <= 2; i++) {
      if (i != last)
        maxi = max(maxi, points[0][i]);
    }
    // Store the result in dp array and return it
    return dp[day][last] = maxi;
  }

  int maxi = 0;
  // Iterate through the activities for the current day
  for (int i = 0; i <= 2; i++) {
    if (i != last) {
      // Calculate the points for the current activity and add it to the
      // maximum points obtained so far (recursively calculated)
      int activity = points[day][i] + f(day - 1, i, points, dp);
      maxi = max(maxi, activity);
    }
  }

  // Store the result in dp array and return it
  return dp[day][last] = maxi;
}

// Function to find the maximum points for ninja training
int ninjaTraining(int n, vector<vector<int>> &points) {
  // Create a memoization table (dp) to store intermediate results
  vector<vector<int>> dp(n, vector<int>(4, -1));
  // Start the recursive calculation from the last day with no previous activity
  return f(n - 1, 3, points, dp);
}

int main() {
  // Define the points matrix
  vector<vector<int>> points = {{10, 40, 70},
                                 {20, 50, 80},
                                 {30, 60, 90}};

  int n = points.size();  // Get the number of days
  // Call the ninjaTraining function to find the maximum points and print the result
  cout << ninjaTraining(n, points);
}

// Tabulation Approach

import java.util.*;

class Solution {
    // Recursive function to calculate the maximum points for the ninja training
    public int f(int day, int last, int[][] points, int[][] dp) {
        // If the result for this day and last activity is already calculated, return it
        if (dp[day][last] != -1) return dp[day][last];

        // Base case: When we reach the first day (day == 0)
        if (day == 0) {
            int maxi = 0;
            // Calculate the maximum points for the first day by choosing an activity
            // different from the last one
            for (int i = 0; i <= 2; i++) {
                if (i != last)
                    maxi = Math.max(maxi, points[0][i]);
            }
            // Store the result in dp array and return it
            return dp[day][last] = maxi;
        }

        int maxi = 0;
        // Iterate through the activities for the current day
        for (int i = 0; i <= 2; i++) {
            if (i != last) {
                // Calculate the points for the current activity and add it to the
                // maximum points obtained so far (recursively calculated)
                int activity = points[day][i] + f(day - 1, i, points, dp);
                maxi = Math.max(maxi, activity);
            }
        }

        // Store the result in dp array and return it
        return dp[day][last] = maxi;
    }

    // Function to find the maximum points for ninja training
    public int ninjaTraining(int n, int[][] points) {
        // Create a memoization table (dp) to store intermediate results
        int[][] dp = new int[n][4];
        for (int[] row : dp) {
            Arrays.fill(row, -1); // Initialize the dp array with -1
        }
        // Start the recursive calculation from the last day with no previous activity
        return f(n - 1, 3, points, dp);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        // Define the points matrix
        int[][] points = {{10, 40, 70},
                          {20, 50, 80},
                          {30, 60, 90}};
        int n = points.length;  // Get the number of days
        // Call the ninjaTraining function to find the maximum points and print the result
        System.out.println(sol.ninjaTraining(n, points));
    }
}


// Space Optimizatiom Approach

import java.util.*;

class Solution {
    // Function to find the maximum points for ninja training
    public int ninjaTraining(int n, int[][] points) {
        // Initialize a vector to store the maximum points for the previous day's activities
        int[] prev = new int[4];

        // Initialize the DP table for the first day (day 0)
        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        // Iterate through the days starting from day 1
        for (int day = 1; day < n; day++) {
            // Create a temporary array to store the maximum points for the current day's activities
            int[] temp = new int[4];
            for (int last = 0; last < 4; last++) {
                temp[last] = 0;
                // Iterate through the tasks for the current day
                for (int task = 0; task <= 2; task++) {
                    if (task != last) {
                        // Calculate the points for the current activity and add it to the
                        // maximum points obtained on the previous day (stored in prev)
                        temp[last] = Math.max(temp[last], points[day][task] + prev[task]);
                    }
                }
            }
            // Update prev with the maximum points for the current day
            prev = temp;
        }

        // The maximum points for the last day with any activity can be found in prev[3]
        return prev[3];
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        // Define the points matrix
        int[][] points = {{10, 40, 70},
                          {20, 50, 80},
                          {30, 60, 90}};
        int n = points.length;  // Get the number of days
        // Call the ninjaTraining function to find the maximum points and print the result
        System.out.println(sol.ninjaTraining(n, points));
    }
}
