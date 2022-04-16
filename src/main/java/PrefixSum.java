import java.util.Map;
import java.util.HashMap;

public class PrefixSum {
//  974. Subarray Sums Divisible by K
  public int subarraysDivByK(int[] nums, int k) {
    Map<Integer, Integer> remainderToFreq = new HashMap<>();
    remainderToFreq.put(0, 1);
    int runningSum = 0, end = 0, count = 0;
    while(end < nums.length) {
      runningSum += nums[end];
      int remainder = runningSum % k;
      if(remainder < 0) {
        remainder += k;
      }
      if(remainderToFreq.containsKey(remainder)) {
        count += remainderToFreq.get(remainder);
      }
      remainderToFreq.put(remainder, 1 + remainderToFreq.getOrDefault(remainder, 0));
      end++;
    }
    return count;
  }
  
}
