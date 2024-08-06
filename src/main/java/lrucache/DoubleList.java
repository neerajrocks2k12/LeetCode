package lrucache;

import lrucache.Node;

public class DoubleList {
  Node head, tail;
  int size = 0;

  public void addFirst(Node x) {
    if(head == null) {
      head = x;
      tail = x;
    } else {
      head.prev = x;
      x.next = head;
      head = x;
    }
    size++;
  }

  public void remove(Node x) {
    if(x.prev != null && x.next != null) {
      x.prev.next = x.next;
      x.next.prev = x.prev;
    } else if(x.prev == null) {
      head = head.next;
      if(head == null) {
        tail = head;
      }
    } else if(x.next == null) {
      tail = tail.prev;
      if(tail == null) {
        tail = head;
      }
    }
    size--;
  }

  public Node removeLast() {
    Node last = null;
    if(null != tail) {
      last = tail;
      tail = tail.prev;
      tail.next = null;
      size--;
    }
    if(tail == null) {
      tail = head;
    }
    return last;
  }
}
