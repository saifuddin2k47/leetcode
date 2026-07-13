class Solution {
  public:
    int wordLadderLength(string startWord, string targetWord,
                         vector<string>& wordList) {

        // Queue for BFS: {currentWord, steps}
        queue<pair<string, int>> q;
        q.push({startWord, 1});

        // Store all words in a set for fast lookup
        unordered_set<string> st(wordList.begin(), wordList.end());

        // Remove startWord if present
        if (st.find(startWord) != st.end())
            st.erase(startWord);

        while (!q.empty()) {
            auto it = q.front();
            q.pop();

            string word = it.first;
            int steps = it.second;

            // If target reached
            if (word == targetWord)
                return steps;

            // Try changing each character
            for (int i = 0; i < word.size(); i++) {
                char original = word[i];

                for (char ch = 'a'; ch <= 'z'; ch++) {
                    word[i] = ch;

                    if (st.find(word) != st.end()) {
                        st.erase(word);
                        q.push({word, steps + 1});
                    }
                }

                // Restore original character
                word[i] = original;
            }
        }

        // No transformation sequence found
        return 0;
    }
};
