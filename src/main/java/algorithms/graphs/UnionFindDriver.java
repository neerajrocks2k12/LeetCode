package algorithms.graphs;

import java.util.Arrays;

public class UnionFindDriver {

  public static void main(String[] args) {
    int[] a = {1,2,4,5};
    int j = Arrays.binarySearch(a, 2);
    System.out.println((-1 - j) + " " + j);
//    long start1 = System.currentTimeMillis();
//    int n = 9;
//    algorithms.graphs.UnionFindWithoutPathCompression unionFindWithoutPathCompression = new algorithms.graphs.UnionFindWithoutPathCompression(n);
//    System.out.println("Components: " + unionFindWithoutPathCompression.getCount());
//
//    unionFindWithoutPathCompression.union(0, 2);
//    System.out.println("Components: " + unionFindWithoutPathCompression.getCount());
//
//    System.out.println(unionFindWithoutPathCompression.connected(0, 2));
//    System.out.println(unionFindWithoutPathCompression.connected(0, 3));
//    unionFindWithoutPathCompression.union(2, 5);
//    System.out.println(unionFindWithoutPathCompression.connected(0, 5));
//    System.out.println("Components: " + unionFindWithoutPathCompression.getCount());
//
//    System.out.println(System.currentTimeMillis() - start1);
//    long start2 = System.currentTimeMillis();
//    System.out.println("-------------------------------------------");
//    UnionFind unionFind = new UnionFind(n);
//    System.out.println("Components: " + unionFind.getComponentCount());
//
//    unionFind.union(0, 2);
//    System.out.println("Components: " + unionFind.getComponentCount());
//
//    System.out.println(unionFind.connected(0, 2));
//    System.out.println(unionFind.connected(0, 3));
//    unionFind.union(2, 5);
//    System.out.println(unionFind.connected(0, 5));
//    System.out.println("Components: " + unionFind.getComponents());
//
//    System.out.println(System.currentTimeMillis() - start2);
  }
}
