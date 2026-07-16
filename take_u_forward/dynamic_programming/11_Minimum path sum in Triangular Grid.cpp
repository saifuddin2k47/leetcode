//Memoization Approach

#include <bits/stdc++.h>
using namespace std;

// Class to solve the problem
class Solution {
public:
    // Recursive function with memoization
    int solve(int i, int j, vector<vector<int>> &triangle, int n, vector<vector<int>> &dp) {
        // If value already computed
        if (dp[i][j] != -1)
            return dp[i][j];

        // If at bottom row
        if (i == n - 1)
            return triangle[i][j];

        // Compute both downward and diagonal moves
        int down = triangle[i][j] + solve(i + 1, j, triangle, n, dp);
        int diag = triangle[i][j] + solve(i + 1, j + 1, triangle, n, dp);

        // Store and return min path sum
        return dp[i][j] = min(down, diag);
    }

    // Function to start the process
    int minimumPathSum(vector<vector<int>> &triangle) {
        int n = triangle.size();
        vector<vector<int>> dp(n, vector<int>(n, -1));
        return solve(0, 0, triangle, n, dp);
    }
};

// Main function
int main() {
    Solution obj;
    vector<vector<int>> triangle{
        {1},
        {2, 3},
        {3, 6, 7},
        {8, 9, 6, 10}
    };
    cout << obj.minimumPathSum(triangle);
    return 0;
}


//Tabulation Approach

#include <bits/stdc++.h>
using namespace std;

// Class to solve the triangle minimum path sum
class Solution {
public:
    // Function to compute the minimum path sum using tabulation
    int minimumPathSum(vector<vector<int>> &triangle, int n) {
        // Create a 2D dp array to store intermediate results
        vector<vector<int>> dp(n, vector<int>(n, 0));

        // Initialize the last row of dp with triangle values
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle[n - 1][j];
        }

        // Traverse from second-last row to the top
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                // Calculate sum from down and diagonal paths
                int down = triangle[i][j] + dp[i + 1][j];
                int diag = triangle[i][j] + dp[i + 1][j + 1];

                // Store the minimum of the two paths
                dp[i][j] = min(down, diag);
            }
        }

        // Return the minimum path sum from top
        return dp[0][0];
    }
};

// Main function to run the code
int main() {
    // Define the triangle
    vector<vector<int>> triangle{
        {1},
        {2, 3},
        {3, 6, 7},
        {8, 9, 6, 10}
    };

    // Get the number of rows
    int n = triangle.size();

    // Create object of the Solution class
    Solution solver;

    // Print the result
    cout << solver.minimumPathSum(triangle, n);

    return 0;
}


//Space Optimization Approach

#include <bits/stdc++.h>
using namespace std;

// Define a class to hold the solution
class Solution {
public:
    // Function to calculate the minimum path sum in triangle
    int minimumPathSum(vector<vector<int>> &triangle, int n) {
        
        // Create an array to store the values of the row below
        vector<int> front(n, 0);
        
        // Create an array to store the values of the current row
        vector<int> cur(n, 0);
        
        // Initialize front with values from the last row of the triangle
        for (int j = 0; j < n; j++) {
            front[j] = triangle[n - 1][j];
        }

        // Traverse the triangle from bottom to top
        for (int i = n - 2; i >= 0; i--) {
            
            // Traverse each element in the current row
            for (int j = i; j >= 0; j--) {
                
                // Calculate sum when going down
                int down = triangle[i][j] + front[j];
                
                // Calculate sum when going diagonal
                int diagonal = triangle[i][j] + front[j + 1];
                
                // Store the minimum of the two in current row
                cur[j] = min(down, diagonal);
            }

            // Move current row values to front for next iteration
            front = cur;
        }

        // Return the final answer from the top of triangle
        return front[0];
    }
};

// Main function to test the solution
int main() {
    
    // Create the triangle
    vector<vector<int>> triangle{
        {1},
        {2, 3},
        {3, 6, 7},
        {8, 9, 6, 10}
    };

    // Get number of rows
    int n = triangle.size();

    // Create object of Solution
    Solution obj;

    // Call the function and print result
    cout << obj.minimumPathSum(triangle, n);

    return 0;
}
