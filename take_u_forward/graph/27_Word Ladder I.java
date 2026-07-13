import java.util.*;

class Pair {
    String first;
    int second;

    Pair(String first, int second) {
        this.first = first;
        this.second = second;
    }
}

class Solution {
    public int wordLadderLength(String startWord, String targetWord,
                                String[] wordList) {

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(startWord, 1));

        Set<String> set = new HashSet<>();
        for (String word : wordList) {
            set.add(word);
        }

        set.remove(startWord);

        while (!q.isEmpty()) {
            Pair p = q.poll();
            String word = p.first;
            int steps = p.second;

            if (word.equals(targetWord))
                return steps;

            char[] arr = word.toCharArray();

            for (int i = 0; i < arr.length; i++) {
                char original = arr[i];

                for (char ch = 'a'; ch <= 'z'; ch++) {
                    arr[i] = ch;
                    String newWord = new String(arr);

                    if (set.contains(newWord)) {
                        set.remove(newWord);
                        q.add(new Pair(newWord, steps + 1));
                    }
                }
                arr[i] = original;
            }
        }

        return 0;
    }
}
