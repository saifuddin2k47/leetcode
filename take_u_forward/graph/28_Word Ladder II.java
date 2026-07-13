import java.util.*;

class Solution {
    public ArrayList<ArrayList<String>> findSequences(String startWord,
                                                      String targetWord,
                                                      String[] wordList) {

        // Result list
        ArrayList<ArrayList<String>> ans = new ArrayList<>();

        // Store words in a HashSet for fast lookup
        HashSet<String> set = new HashSet<>(Arrays.asList(wordList));

        // Queue to store sequences
        Queue<ArrayList<String>> q = new LinkedList<>();
        q.offer(new ArrayList<>(List.of(startWord)));

        // Words used at current BFS level
        ArrayList<String> usedOnLevel = new ArrayList<>();
        usedOnLevel.add(startWord);

        int level = 1;

        while (!q.isEmpty()) {
            ArrayList<String> seq = q.poll();

            // Level change → remove previously used words
            if (seq.size() > level) {
                level = seq.size();
                for (String word : usedOnLevel) {
                    set.remove(word);
                }
                usedOnLevel.clear();
            }

            String word = seq.get(seq.size() - 1);

            // If target reached
            if (word.equals(targetWord)) {
                if (ans.isEmpty()) {
                    ans.add(new ArrayList<>(seq));
                } else if (ans.get(0).size() == seq.size()) {
                    ans.add(new ArrayList<>(seq));
                }
                continue;
            }

            // Try all transformations
            char[] wordArr = word.toCharArray();
            for (int i = 0; i < wordArr.length; i++) {
                char original = wordArr[i];

                for (char c = 'a'; c <= 'z'; c++) {
                    wordArr[i] = c;
                    String newWord = new String(wordArr);

                    if (set.contains(newWord)) {
                        seq.add(newWord);
                        q.offer(new ArrayList<>(seq));
                        usedOnLevel.add(newWord);
                        seq.remove(seq.size() - 1);
                    }
                }
                wordArr[i] = original;
            }
        }

        return ans;
    }
}