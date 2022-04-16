package lrucache;

import java.util.HashMap;
import java.util.Map;
public class LRUCache {
  int k;
  DoubleList dl;
  Map<Integer, Node> t = new HashMap<>();
  public LRUCache(int capacity) {
    k = capacity;
    dl = new DoubleList();
  }

  public int get(int key) {
    if(!t.containsKey(key)) {
      return -1;
    }
    put(key, t.get(key).value);
    return t.get(key).value;
  }

  public void put(int key, int value) {
    Node x = new Node(key, value);
    if(t.containsKey(key)) {
      dl.remove(t.get(key));
      dl.addFirst(x);
      t.put(key, x);
    } else {
      if(dl.size == k) {
        Node last = dl.removeLast();
        t.remove(last.key);
      }
      dl.addFirst(x);
      t.put(key, x);
    }
  }
}