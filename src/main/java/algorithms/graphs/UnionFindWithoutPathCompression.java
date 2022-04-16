package algorithms.graphs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UnionFindWithoutPathCompression {
  private int count;
  private int[] parent;

  public UnionFindWithoutPathCompression(int n) {
    parent = new int[n];
    this.count = n;

    initParentArr();
  }

  private void initParentArr() {
    if(null == parent) {
      return;
    }
    for(int i = 0; i < parent.length; i++) {
      parent[i] = i;
    }
  }

  public void union(int p, int q) {
    int rootP = find(p);
    int rootQ = find(q);

    if(rootP == rootQ) {
      return;
    }
    parent[rootP] = rootQ;
    count--; // one component less for every merge we invoke
  }

  public int find(int p) {
    while(parent[p] != p) {
      p = parent[p];
    }
    return p;
  }

  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }
}
