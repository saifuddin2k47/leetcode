class Solution {
private:
    // Kahn's Algorithm for Topological Sort
    vector<int> topoSort(int K, vector<vector<int>> &adj) {
        vector<int> indegree(K, 0);

        // Calculate indegree
        for (int i = 0; i < K; i++) {
            for (int v : adj[i]) {
                indegree[v]++;
            }
        }

        queue<int> q;
        for (int i = 0; i < K; i++) {
            if (indegree[i] == 0)
                q.push(i);
        }

        vector<int> topo;
        while (!q.empty()) {
            int node = q.front();
            q.pop();
            topo.push_back(node);

            for (int v : adj[node]) {
                if (--indegree[v] == 0)
                    q.push(v);
            }
        }

        return topo; // cycle check is done in caller
    }

public:
    string findOrder(vector<string> &words) {
        int N = words.size();

        // Collect all unique characters
        unordered_set<char> st;
        for (auto &w : words)
            for (char c : w)
                st.insert(c);

        int K = st.size();

        // Map characters to indices
        unordered_map<char, int> mp;
        unordered_map<int, char> rev;
        int idx = 0;
        for (char c : st) {
            mp[c] = idx;
            rev[idx] = c;
            idx++;
        }

        vector<vector<int>> adj(K);

        // Build graph
        for (int i = 0; i < N - 1; i++) {
            string &s1 = words[i];
            string &s2 = words[i + 1];
            int len = min(s1.size(), s2.size());
            bool diffFound = false;

            for (int j = 0; j < len; j++) {
                if (s1[j] != s2[j]) {
                    adj[mp[s1[j]]].push_back(mp[s2[j]]);
                    diffFound = true;
                    break;
                }
            }

            // Invalid prefix case
            if (!diffFound && s1.size() > s2.size())
                return "";
        }

        // Topological sort
        vector<int> topo = topoSort(K, adj);

        // Cycle detected
        if (topo.size() != K)
            return "";

        // Convert to string
        string ans = "";
        for (int node : topo)
            ans += rev[node];

        return ans;
    }
};