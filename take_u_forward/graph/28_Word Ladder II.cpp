class Solution {
  public:
    vector<vector<string>> findSequences(string beginWord, string endWord,
                                         vector<string>& wordList) {
        
        unordered_set<string> st(wordList.begin(), wordList.end());

        queue<vector<string>> q;
        q.push({beginWord});

        vector<string> usedOnLevel;
        usedOnLevel.push_back(beginWord);

        int level = 1;
        vector<vector<string>> ans;

        while (!q.empty()) {
            vector<string> seq = q.front();
            q.pop();

            // If we moved to next level
            if (seq.size() > level) {
                level = seq.size();
                for (string &word : usedOnLevel) {
                    st.erase(word);
                }
                usedOnLevel.clear();
            }

            string word = seq.back();

            // If target reached
            if (word == endWord) {
                if (ans.empty()) {
                    ans.push_back(seq);
                } else if (ans[0].size() == seq.size()) {
                    ans.push_back(seq);
                }
                continue; // do not expand further
            }

            for (int i = 0; i < word.size(); i++) {
                char original = word[i];

                for (char c = 'a'; c <= 'z'; c++) {
                    word[i] = c;
                    if (st.count(word)) {
                        seq.push_back(word);
                        q.push(seq);
                        usedOnLevel.push_back(word);
                        seq.pop_back();
                    }
                }

                word[i] = original;
            }
        }

        return ans;
    }
};
