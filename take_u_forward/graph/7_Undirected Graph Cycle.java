class Pair{
    int first, second;
    Pair(int first,int second){
        this.first=first;
        this.second=second;
    }
}
class Solution {
    
    public boolean checkForCycle(int src,int V, ArrayList<ArrayList<Integer>>adj,boolean[]vis){
        vis[src]=true;
        Queue<Pair>q=new LinkedList<>();
        q.add(new Pair(src,-1));
        while(!q.isEmpty()){
            int node=q.peek().first;
            int parent=q.peek().second;
            q.remove();
            
            for(int adjacentNode: adj.get(node)){
                if(vis[adjacentNode]==false){
                    vis[adjacentNode]=true;
                    q.add(new Pair(adjacentNode,node));
                }
                else if(parent!=adjacentNode){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isCycle(int V, int[][] edges) {
        // Code here
        
        ArrayList<ArrayList<Integer>>adj=new ArrayList<>();
        for(int i=0;i<V;i++){
            adj.add(new ArrayList<>());
        }
        
        for(int i=0;i<edges.length;i++){
            int u=edges[i][0];
            int v=edges[i][1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        
        boolean vis[] = new boolean[V];
        for(int i=0;i<V;i++){
            vis[i]=false;
        }
        for(int i=0;i<V;i++){
            if(vis[i]==false){
                if(checkForCycle(i,V,adj,vis)) return true;
            }
        }
        return false;
    }
}