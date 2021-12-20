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
    while(end < nums.length) {
      runningSum += nums[end];
      while(runningSum >= target) {
        minLength = Math.min(minLength, end - start + 1);
        runningSum -= nums[start++];
      }
      end++;
    }
    return minLength == nums.length + 1 ? 0 : minLength;
  }
}
