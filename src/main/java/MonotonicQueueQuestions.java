import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class MonotonicQueueQuestions {
  // 901. Online Stock Span
  // whenever you have question involving data stream that could be solved using NSL or NSR
  // try using MIQ, similarly when question could be solved using NGL or NGL but is a data stream then use MDQ
  public class StockSpanner {
    List<Integer> t;
    int i;
    ArrayDeque<Integer> mdq;
    public StockSpanner() {
      t = new ArrayList<>();
      mdq = new ArrayDeque<>();
      i = -1;
    }

    public int next(int price) {
      t.add(price);
      i++;
      while(!mdq.isEmpty() && t.get(mdq.peekLast()) <= price) {
        mdq.pollLast();
      }
      Integer ans = null;
      if (mdq.isEmpty()) {
        ans = i + 1;
      } else {
        ans = i - mdq.peekLast();
      }
      mdq.offerLast(i);

      return ans;
    }
  }
}
