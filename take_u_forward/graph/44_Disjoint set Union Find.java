/*Complete the function below*/
class GfG {
    
    int find(int par[], int x) {
        // Path compression
        if (par[x] != x) {
            par[x] = find(par, par[x]);
        }
        return par[x];
    }

    void unionSet(int par[], int x, int z) {
        int px = find(par, x);  // root of x
        int pz = find(par, z);  // root of z

        if (px != pz) {
            par[px] = pz;  // merge x's root into z's root
        }
    }
}
