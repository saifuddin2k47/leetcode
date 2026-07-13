import java.util.*;

class Solution {

    // Topological Sort using Kahn's Algorithm
    private List<Integer> topoSort(int V, List<List<Integer>> adj) {
        int[] indegree = new int[V];

        for (int i = 0; i < V; i++) {
            for (int v : adj.get(i)) {
                indegree[v]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        List<Integer> topo = new ArrayList<>();

        while (!q.isEmpty()) {
            int node = q.poll();
            topo.add(node);

            for (int v : adj.get(node)) {
                if (--indegree[v] == 0) {
                    q.add(v);
                }
            }
        }

        return topo;
    }

    // MAIN FUNCTION (as required)
    public String findOrder(String[] words) {
        int N = words.length;

        // 1️⃣ Collect unique characters
        Set<Character> set = new HashSet<>();
        for (String w : words) {
            for (char c : w.toCharArray()) {
                set.add(c);
            }
        }

        int K = set.size();

        // Map characters to indices
        Map<Character, Integer> map = new HashMap<>();
        Map<Integer, Character> rev = new HashMap<>();
        int idx = 0;
        for (char c : set) {
            map.put(c, idx);
            rev.put(idx, c);
            idx++;
        }

        // Build graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            String s1 = words[i];
            String s2 = words[i + 1];
            int len = Math.min(s1.length(), s2.length());
            boolean found = false;

            for (int j = 0; j < len; j++) {
                if (s1.charAt(j) != s2.charAt(j)) {
                    adj.get(map.get(s1.charAt(j)))
                       .add(map.get(s2.charAt(j)));
                    found = true;
                    break;
                }
            }

            // ❗ Invalid prefix case
            if (!found && s1.length() > s2.length()) {
                return "";
            }
        }

        // Topological sort
        List<Integer> topo = topoSort(K, adj);

        // ❗ Cycle detection
        if (topo.size() != K) {
            return "";
        }

        // Build answer
        StringBuilder ans = new StringBuilder();
        for (int node : topo) {
            ans.append(rev.get(node));
        }

        return ans.toString();
    }
}
