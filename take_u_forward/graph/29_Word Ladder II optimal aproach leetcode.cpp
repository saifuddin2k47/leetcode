class Solution {
    unordered_map<string, vector<string>> parent;
    unordered_set<string> dict;
    vector<vector<string>> ans;
    string beginWord;
    int wordLen;

    void dfs(string word, vector<string>& path) {
        if (word == beginWord) {
            reverse(path.begin(), path.end());
            ans.push_back(path);
            reverse(path.begin(), path.end());
            return;
        }

        for (auto &p : parent[word]) {
            path.push_back(p);
            dfs(p, path);
            path.pop_back();
        }
    }

public:
    vector<vector<string>> findLadders(string beginWord_, string endWord,
                                       vector<string>& wordList) {

        beginWord = beginWord_;
        wordLen = beginWord.size();
        dict = unordered_set<string>(wordList.begin(), wordList.end());

        if (!dict.count(endWord)) return {};

        queue<string> q;
        q.push(beginWord);

        unordered_map<string, int> level;
        level[beginWord] = 0;

        dict.erase(beginWord);
        bool found = false;

        while (!q.empty() && !found) {
            int sz = q.size();
            unordered_set<string> usedThisLevel;

            while (sz--) {
                string word = q.front();
                q.pop();
                int steps = level[word];

                string temp = word;
                for (int i = 0; i < wordLen; i++) {
                    char original = temp[i];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        temp[i] = ch;
                        if (dict.count(temp)) {
                            if (!level.count(temp)) {
                                level[temp] = steps + 1;
                                q.push(temp);
                            }
                            parent[temp].push_back(word);
                            if (temp == endWord) found = true;
                            usedThisLevel.insert(temp);
                        }
                    }
                    temp[i] = original;
                }
            }

            for (auto &w : usedThisLevel) dict.erase(w);
        }

        if (found) {
            vector<string> path;
            path.push_back(endWord);
            dfs(endWord, path);
        }

        return ans;
    }
};

