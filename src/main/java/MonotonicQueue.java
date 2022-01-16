import java.util.ArrayDeque;
import java.util.Arrays;

public class MonotonicQueue {

  /**
   * A monotonic queue is supposed to have elements in increasing manner example: for array [5, 2,
   * 3, 1, 4] the monotonic increasing queue will be [1, 4] The core function to build a monotonic
   * increasing queue while pushing element e is to pop all elements s >= e (strict monotonicity
   * does not allow dupes hence equality is there) let us work the above array
   * idx    elem    Queue
   * 0      5       [5]
   * 1      2       [2] -> 2 kicks out 5 as 5 >= 2
   * 2      3      [2, 3] -> nothing kicked as 2 < 3
   * 3      1      [1] -> 1 kicks out 3, and 2 as both are >= 1
   * 4      4      [1,4] -> nothing kicked as 1 < 4
   * Now if we observe above set of operations (kicking out and
   * pushing in) we can make two observations for element being kicked out (like 5 in step 2), 2
   * (kicker) is the <b> nearest smaller element to the right </b> for 5 (being kicked out) and the
   * element left in the queue end before kicker is pushed is the <b> nearest smaller element to the
   * left </b> for the kicker. for 2 we have empty queue meaning there is no smaller element to its
   * right. Thus, monotonic increasing queue helps us in computing <b>nearest greater to the right
   * (kicking out step) and nearest greater to the left (pushing in step)</b>
   * <p>
   * Another important thing that increasing queue can tell is at any point of time we can find the
   * smallest element in the array (sub-array) by simply seeing the front of the increasing queue
   *
   * @param nums integer array for which the nearest greater to left/right are to be computed
   */
  public void increasingQueue(int[] nums) {
    // normal queue is also fine, I have taken doubly ended queue
    ArrayDeque<Integer> q = new ArrayDeque<>(); // deque is taken so that we can add/remove elements from both end
    int[] nsr = new int[nums.length];
    // default value to indicate no element smaller than current exists on the right side
    Arrays.fill(nsr, -1);
    int[] nsl = new int[nums.length];
    // default value to indicate no element smaller than current exists on the left side
    Arrays.fill(nsl, -1);
    //increasing queue logic
    for (int i = 0; i < nums.length; i++) {
      while (!q.isEmpty() && nums[q.peekLast()] >= nums[i]) { // poll s >= e
        //kicking out step
        nsr[q.peekLast()] = nums[i];
        q.pollLast();
      }
      // pushing in step
      nsl[i] = q.isEmpty() ? -1 : nums[q.peekLast()];
      q.offerLast(i);
//      System.out.println("Current min: " + (q.isEmpty() ? -1 : nums[q.peekFirst()]));
    }
    //print nsr and nsl
    System.out.println("NUMS: " + Arrays.toString(nums));
    System.out.println("NSR:  " + Arrays.toString(nsr));
    System.out.println("NSL: " + Arrays.toString(nsl));
  }

  /**
   * Monotonically Decreasing queue is supposed to have elements in decreasing order. for example:
   * [5, 2, 3, 1, 4] be the input array. For this the monotonically decreasing queue will be
   * [5, 4]. The core function, while pushing element <b>e</b> is to remove all s <= e from queue.
   * let us work with our example:
   * idx    elem   Q
   * 0      5     [5]
   * 1      2     [5, 2] since 5  > 2 so no kicking happens
   * 2      3     [5, 3] -> 2 is kicked out as 2 <= 3
   * 3      1     [5, 3, 1] -> since 3 > 1 so no kicking happens
   * 4      4     [5, 4] -> 1, 3 both kicked out as both are <= 4
   * Let us observe the relationship between the kicker and kickee, if we take step 3 where 2 is
   * kicked out by 3 then we can verify that 3 (kicker) is the <b>nearest greater element
   * to the right </b> of 2 (kickee). And similarly at the time of pushing an element the
   * element at the last of queue (if present) is the <b>nearest greater element to the left</b>
   * for the element being pushed in. Take step 2, when 2 is being pushed in, 5 is present before it
   * making 5 the nearest greater element to left of 2
   * @param nums
   */
  public void decreasingQueue(int[] nums) {
    ArrayDeque<Integer> q = new ArrayDeque<>();
    int[] ngl = new int[nums.length];
    int[] ngr  = new int[nums.length];
    Arrays.fill(ngl, -1);
    Arrays.fill(ngr, -1);

    //decreasing queue logic
    for(int i = 0; i < nums.length; i++) {
      //remove s <= e
      while(!q.isEmpty() && nums[q.peekLast()] <= nums[i]) {
        ngr[q.pollLast()] = nums[i];
      }
      ngl[i] = q.isEmpty() ? -1 : nums[q.peekLast()];
      q.offerLast(i);
      //System.out.println("Current Max: " + (q.isEmpty() ? -1 : nums[q.peekFirst()]));
    }
    System.out.println("NUMS: " + Arrays.toString(nums));
    System.out.println("NGR:  " + Arrays.toString(ngr));
    System.out.println("NGL: " + Arrays.toString(ngl));
  }
}
