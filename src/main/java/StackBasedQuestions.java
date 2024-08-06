import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class StackBasedQuestions {
  // Concepts
  /**
   * 1. Nearest Greater to the Right (NGR).
   * let A: [3, 4, 2, 5, 10, 4]
   * ngr:  [4, 5, 5, 10, -1, -1] where -1 signifies no nearest greater to the right exist
   *
   * Brute force would require us to iterate inner loop from i+1 to n-1 in order to find ngr for a[i].
   * here inner loop's iterator is a function of 'i' thus stack could work.
   *
   * now below is how we observe stack. We iterate from end of array towards start i.e. from n-1 to 0
   *
   * idx   elem    Stack before | stack after
   * 5     4       []           [4] --> so ngr[5] = -1 as there is nothing to right of a[5] i.e. 4, then we insert 4 into the stack
   * 4    10      [4]          [10] --> 10 kicks out 4 as 4 <= 10 so ngr[4] = -1 and for any number left of 10, ngr could never be 4 so we pop 4
   * 3    5       [10]         [10, 5] ---> ngr[3] = 10 as 10 >= 5 (current top is greater than element at hand). Then we push 5
   * 2    2      [10, 5]      [10, 5, 2] ---> ngr[2] = 5 as 5 >= 2
   * 1    4      [10, 5, 2]   [10, 5, 4] ---> here 4 kicks out 2 as 2 <= 4 but we stop when stack.top becomes 5 as 5 >= 4, so ngr[1] = 5
   * 0    3     [10, 5, 4]    [10, 5, 4, 3] ----> ngr[0] = 4 as 4 >= 3
   */

  public int[] findNearestGreaterToRight(int[] a) {
    // 0. create an array to hold ngr values
    int[] ngr  = new int[a.length];
    // 1. first initialize a stack
    Deque<Integer> stack = new ArrayDeque<>();
    // 2. traverse from RIGHT to LEFT
    for (int i = a.length - 1; i >= 0; i--) {
      // continue popping elements from stack till we find greater element at top of stack than a[i]
      while (!stack.isEmpty() && stack.peek() <= a[i]) {
        stack.pop();
      }
      // check if stack is empty, in this case there is no NGR for a[i]
      if (stack.isEmpty()) {
        ngr[i] = -1; // marker value
      } else {
        ngr[i] = stack.peek(); // else the element at top of stack is the NGR for a[i[
      }
      // push a[i] into stack
      stack.push(a[i]);
    }

    System.out.println(Arrays.toString(ngr)); // print solution
    return ngr;
  }

  /**
   * 2. Nearest Greater to the LEFT (NGL)
   * a:   [3, 4, 2, 5, 10, 4]
   * ngl: [-1, -1, 4, -1, -1, 10]
   * the idea is same as NGL with a few change.
   * Here as we need to find Nearest Greater to LEFT, so we traverse from LEFT to RIGHT
   * remaining code remains same
   */

  public int[] findNearestGreaterToLeft(int[] a) {
    // 0. initialize ngl to hold solution
    int[] ngl = new int[a.length];

    // initialize stack
    Deque<Integer> stack = new ArrayDeque<>();
    // 1. traverse from LEFT to RIGHT as we need to find NGL this time
    for (int i = 0; i < a.length; i++) {
      while (!stack.isEmpty() && stack.peek() <= a[i]) {
        stack.pop();
      }

      if (stack.isEmpty()) {
        ngl[i] = -1; // marker value
      } else {
        ngl[i] = stack.peek();
      }

      stack.push(a[i]);
    }

    System.out.println(Arrays.toString(ngl));

    return ngl;
  }


  public static void main(String[] args) {
    int[] a = new int[]{3,4,2,5,10,4};
    StackBasedQuestions t = new StackBasedQuestions();
    t.findNearestGreaterToRight(a);
    t.findNearestGreaterToLeft(a);
  }
}
