import java.util.*;

class DisjointSet {
    int[] parent, size;

    DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
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
}

class Solution {
    public ArrayList<ArrayList<String>> accMerge(String[][] arr) {

        int n = arr.length;
        DisjointSet ds = new DisjointSet(n);

        HashMap<String, Integer> mailMap = new HashMap<>();

        // Step 1: Union accounts with same email
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < arr[i].length; j++) {
                String mail = arr[i][j];

                if (!mailMap.containsKey(mail)) {
                    mailMap.put(mail, i);
                } else {
                    ds.unionBySize(i, mailMap.get(mail));
                }
            }
        }

        // Step 2: Group emails by parent
        ArrayList<ArrayList<String>> mergedMail = new ArrayList<>();
        for (int i = 0; i < n; i++)
            mergedMail.add(new ArrayList<>());

        for (Map.Entry<String, Integer> it : mailMap.entrySet()) {
            String mail = it.getKey();
            int node = ds.findUPar(it.getValue());
            mergedMail.get(node).add(mail);
        }

        // Step 3: Build answer
        ArrayList<ArrayList<String>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (mergedMail.get(i).isEmpty()) continue;

            Collections.sort(mergedMail.get(i));

            ArrayList<String> temp = new ArrayList<>();
            temp.add(arr[i][0]);   // name

            for (String mail : mergedMail.get(i)) {
                temp.add(mail);
            }

            ans.add(temp);
        }

        return ans;
    }
}
