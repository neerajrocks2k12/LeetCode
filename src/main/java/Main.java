import lrucache.LRUCache;

public class Main {

  public static void main(String[] args) {
    LRUCache lruCache = new LRUCache(3);
    lruCache.put(1,1);
    lruCache.put(2,2);
    lruCache.put(3,3);
    lruCache.put(4,4);
    System.out.println(lruCache.get(4));
    System.out.println(lruCache.get(3));
    System.out.println(lruCache.get(2));
    System.out.println(lruCache.get(1));
    lruCache.put(5,5);

    System.out.println(lruCache.get(1));
    System.out.println(lruCache.get(2));
    System.out.println(lruCache.get(3));
    System.out.println(lruCache.get(4));
    System.out.println(lruCache.get(5));
  }
}
/*
["lrucache.LRUCache","put","put","put","put","get","get","get","get","put","get","get","get","get","get"]
[[3],[1,1],[2,2],[3,3],[4,4],[4],[3],[2],[1],[5,5],[1],[2],[3],[4],[5]]
 */