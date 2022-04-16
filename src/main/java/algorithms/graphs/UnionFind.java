package algorithms.graphs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UnionFind {
  int componentCount;
  int[] size;
  int[] parent;

  public UnionFind(int nodes) {
    this.componentCount = nodes;
    this.size = new int[nodes];
    this.parent = new int[nodes];

    initVals();
  }

  private void initVals() {
    for(int i = 0; i < parent.length; i++) {
      parent[i] = i;
      size[i] = 1;
    }
  }

  public void union(int p, int q) {
    int rootP = find(p);
    int rootQ = find(q);

    // already connected | part of same component
    if(rootP == rootQ) {
      return;
    }
    // merge smaller tree under larger
    if(size[rootP] < size[rootQ]) {
      parent[rootP] = rootQ;
      size[rootQ] += size[rootP];
    } else {
      parent[rootQ] = rootP;
      size[rootP] += size[rootQ];
    }

    componentCount--;
  }

  public int find(int x) {
    while(parent[x] != x) {
      parent[x] = parent[parent[x]];
      x = parent[x];
    }
    return x;
  }

  public int getComponents() {
    return this.componentCount;
  }

  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }
}
