#include <bits/stdc++.h>
using namespace std;

// Disjoint Set (Union-Find)
class DisjointSet {
    vector<int> parent, size;

public:
    DisjointSet(int n) {
        parent.resize(n);
        size.resize(n, 1);
        for (int i = 0; i < n; i++)
            parent[i] = i;
    }

    int findUPar(int node) {
        if (node == parent[node])
            return node;
        return parent[node] = findUPar(parent[node]);
    }

    void unionBySize(int u, int v) {
        int pu = findUPar(u);
        int pv = findUPar(v);
        if (pu == pv) return;

        if (size[pu] < size[pv]) {
            parent[pu] = pv;
            size[pv] += size[pu];
        } else {
            parent[pv] = pu;
            size[pu] += size[pv];
        }
    }
};

class Solution {
public:
    vector<vector<string>> accMerge(vector<vector<string>>& arr) {
        int n = arr.size();
        DisjointSet ds(n);

        unordered_map<string, int> mailMap;

        // Step 1: Union accounts with same email
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < arr[i].size(); j++) {
                string mail = arr[i][j];

                if (mailMap.find(mail) == mailMap.end()) {
                    mailMap[mail] = i;
                } else {
                    ds.unionBySize(i, mailMap[mail]);
                }
            }
        }

        // Step 2: Group emails by parent
        vector<vector<string>> mergedMail(n);

        for (auto &it : mailMap) {
            string mail = it.first;
            int node = ds.findUPar(it.second);
            mergedMail[node].push_back(mail);
        }

        // Step 3: Build answer
        vector<vector<string>> ans;

        for (int i = 0; i < n; i++) {
            if (mergedMail[i].empty()) continue;

            sort(mergedMail[i].begin(), mergedMail[i].end());

            vector<string> temp;
            temp.push_back(arr[i][0]);   // name

            for (auto &mail : mergedMail[i])
                temp.push_back(mail);

            ans.push_back(temp);
        }

        return ans;
    }
};