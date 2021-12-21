import java.util.*;

public class SlidingWindowProblems {

  //  1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold
  public int numOfSubarrays(int[] arr, int k, int threshold) {
    // avg = sum / k => avg >= threshold <=> sum / k >= threshold
    // avg greater than threshold means sum >= threshold * k
    int count = 0;
    int start = 0, end = 0;
    int runningSum = 0;
    while (end < arr.length) {
      runningSum += arr[end];
      // window is hit
      if (end - start + 1 == k) {
        if (runningSum >= (k * threshold)) {
          count++;
        }
        //slide the window
        runningSum -= arr[start++];
      }
      end++;
    }

    return count;
  }

  //209. Minimum Size Subarray Sum
  public int minSubArrayLen(int target, int[] nums) {
    int start = 0, end = 0;
    int runningSum = 0;
    int minLength = nums.length + 1;
    while (end < nums.length) {
      runningSum += nums[end];
      while (runningSum >= target) {
        minLength = Math.min(minLength, end - start + 1);
        runningSum -= nums[start++];
      }
      end++;
    }
    return minLength == nums.length + 1 ? 0 : minLength;
  }

  //  713. Subarray Product Less Than K
  public int numSubarrayProductLessThanK(int[] nums, int k) {
    int count = 0;
    int start = 0, end = 0;
    int runningProduct = 1;
    while (end < nums.length) {
      runningProduct *= nums[end];
      while (start <= end && runningProduct >= k) {
        runningProduct /= nums[start++];
      }
      if (runningProduct < k) {
        count += (end - start + 1);
      }
      end++;
    }

    return count;
  }

  //  2110. Number of Smooth Descent Periods of a Stock
  public long getDescentPeriods(int[] prices) {
    long count = 0;
    int start = 0, end = 0;
    while (end < prices.length) {
      if (end > 0 && prices[end] != prices[end - 1] - 1) {
        start = end;
      }
      count += (end - start + 1);
      end++;
    }
    return count;
  }

//  560. Subarray Sum Equals K
  public int subarraySum(int[] nums, int k) {
    // idea is to use Prefix sum because we have negative elements present
    // straightforward sliding window won't work here
    // Let us assume we have a subarray A[start ... end] that has some == k. Now we can write sum of this sub array in terms
    // of prefix sum as follows sum[start ... end] = P[end] - P[start - 1] = k => P[start - 1] = P[end] - k
    // now if we can find P[end] - k, in our hashmap, then we can pair it with the current end and that would give us valid solution
    // because of negative numbers, we must store how many times a sum occurs
    Map<Integer, Integer> prefixSumToFreq = new HashMap<>();
    int end = 0, count = 0;
    int runningSum = 0;
    prefixSumToFreq.put(0, 1);
    while(end < nums.length) {
      runningSum += nums[end];
      if(prefixSumToFreq.containsKey(runningSum - k)) {
        count += prefixSumToFreq.get(runningSum - k);
      }
      prefixSumToFreq.put(runningSum, 1 + prefixSumToFreq.getOrDefault(runningSum, 0));
      end++;
    }

    return count;
  }

  //prefix Sum problem
//  974. Subarray Sums Divisible by K
  public int subarraysDivByK(int[] nums, int k) {
    Map<Integer, Integer> remainderToFreq = new HashMap<>();
    remainderToFreq.put(0, 1);
    int runningSum = 0, end = 0, count = 0;
    while(end < nums.length) {
      runningSum += nums[end];
      int remainder = runningSum % k;
      if(remainder < 0) { // important to handle the negative mod.
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
