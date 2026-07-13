import java.util.*;

class Solution {

    private Map<String, List<String>> parent = new HashMap<>();
    private List<List<String>> ans = new ArrayList<>();
    private String beginWord;
    private int wordLen;

    private void dfs(String word, List<String> path) {
        if (word.equals(beginWord)) {
            Collections.reverse(path);
            ans.add(new ArrayList<>(path));
            Collections.reverse(path);
            return;
        }

        for (String p : parent.getOrDefault(word, new ArrayList<>())) {
            path.add(p);
            dfs(p, path);
            path.remove(path.size() - 1);
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord,
                                          List<String> wordList) {

        this.beginWord = beginWord;
        this.wordLen = beginWord.length();

        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return ans;

        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);

        Map<String, Integer> level = new HashMap<>();
        level.put(beginWord, 0);

        dict.remove(beginWord);
        boolean found = false;

        while (!q.isEmpty() && !found) {
            int size = q.size();
            Set<String> usedThisLevel = new HashSet<>();

            while (size-- > 0) {
                String word = q.poll();
                int currLevel = level.get(word);

                char[] arr = word.toCharArray();
                for (int i = 0; i < wordLen; i++) {
                    char original = arr[i];
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        arr[i] = ch;
                        String next = new String(arr);

                        if (dict.contains(next)) {
                            if (!level.containsKey(next)) {
                                level.put(next, currLevel + 1);
                                q.offer(next);
                            }
                            parent.computeIfAbsent(next, k -> new ArrayList<>()).add(word);
                            if (next.equals(endWord)) found = true;
                            usedThisLevel.add(next);
                        }
                    }
                    arr[i] = original;
                }
            }

            for (String w : usedThisLevel) dict.remove(w);
        }

        if (found) {
            List<String> path = new ArrayList<>();
            path.add(endWord);
            dfs(endWord, path);
        }

        return ans;
    }
}
