class DisjointSet {
    /* To store the ranks, parents and 
    sizes of different set of vertices */
    int[] rank, parent, size;

    // Constructor
    DisjointSet(int n) {
        rank = new int[n + 1];
        parent = new int[n + 1];
        size = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    // Function to find ultimate parent
    int findUPar(int node) {
        if (node == parent[node])
            return node;
        return parent[node] = findUPar(parent[node]);
    }

    // Function to implement union by rank
    void unionByRank(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v) return;
        if (rank[ulp_u] < rank[ulp_v]) {
            parent[ulp_u] = ulp_v;
        }
        else if (rank[ulp_v] < rank[ulp_u]) {
            parent[ulp_v] = ulp_u;
        }
        else {
            parent[ulp_v] = ulp_u;
            rank[ulp_u]++;
        }
    }

    // Function to implement union by size
    void unionBySize(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v) return;
        if (size[ulp_u] < size[ulp_v]) {
            parent[ulp_u] = ulp_v;
            size[ulp_v] += size[ulp_u];
        }
        else {
            parent[ulp_v] = ulp_u;
            size[ulp_u] += size[ulp_v];
        }
    }
}
class Solution {
    int maxRemove(int[][] stones) {
        // Code here
        int n=stones.length;
        int maxRow=0;
        int maxCol=0;
        for(int i=0;i<n;i++){
            maxRow=Math.max(maxRow,stones[i][0]);
            maxCol=Math.max(maxCol,stones[i][1]);
        }
        DisjointSet ds=new DisjointSet(maxRow+maxCol+1);
        HashMap<Integer,Integer>stoneNodes=new HashMap<>();
        for(int i=0;i<n;i++){
            int nodeRow=stones[i][0];
            int nodeCol=stones[i][1]+maxRow+1;
            ds.unionBySize(nodeRow,nodeCol);
            stoneNodes.put(nodeRow,1);
            stoneNodes.put(nodeCol,1);
        }
        int cnt=0;
        for(Map.Entry<Integer,Integer> it:stoneNodes.entrySet()){
            if(ds.findUPar(it.getKey())==it.getKey()){
                cnt++;
            }
        }
        return n-cnt;
    }
};
