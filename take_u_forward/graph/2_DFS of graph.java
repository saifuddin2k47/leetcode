class Solution {
    public static void df(int node, boolean vis[], ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> ls){
        vis[node] = true;
        ls.add(node);

        for(Integer it : adj.get(node)) {
            if(!vis[it]) {
                df(it, vis, adj, ls);
            }
        }
    }
    
    public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
        boolean vis[] = new boolean[adj.size()];
        ArrayList<Integer> ls = new ArrayList<>();
        
        df(0, vis, adj, ls);
        return ls;
    }
}
