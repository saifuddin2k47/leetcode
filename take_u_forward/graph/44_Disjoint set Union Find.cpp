int find(int par[], int x) {
    // If x is not its own parent, find its parent recursively
    if (par[x] != x) {
        par[x] = find(par, par[x]);   // Path compression
    }
    return par[x];
}

void unionSet(int par[], int x, int z) {
    int px = find(par, x);   // find root of x
    int pz = find(par, z);   // find root of z

    if (px != pz) {
        par[px] = pz;   // attach root of x to root of z
    }
}
