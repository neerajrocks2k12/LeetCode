import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    while (end < nums.length) {
      runningSum += nums[end];
      if (prefixSumToFreq.containsKey(runningSum - k)) {
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
    while (end < nums.length) {
      runningSum += nums[end];
      int remainder = runningSum % k;
      if (remainder < 0) { // important to handle the negative mod.
        remainder += k;
      }
      if (remainderToFreq.containsKey(remainder)) {
        count += remainderToFreq.get(remainder);
      }
      remainderToFreq.put(remainder, 1 + remainderToFreq.getOrDefault(remainder, 0));
      end++;
    }
    return count;
  }

  //  438. Find All Anagrams in a String
  public List<Integer> findAnagrams(String s, String p) {
    List<Integer> startIdxList = new ArrayList<>();
    int[] freq = new int[26];
    Set<Integer> chars = new HashSet<>();
    for (int i = 0; i < p.length(); i++) {
      freq[p.charAt(i) - 'a']++;
      chars.add(p.charAt(i) - 'a');
    }
    int count = chars.size();

    int start = 0, end = 0;
    while (end < s.length()) {
      char ch = s.charAt(end);
      if (chars.contains(ch - 'a')) {
        freq[ch - 'a']--;
        if (freq[ch - 'a'] == 0) {
          count--;
        }
      }
      while (count == 0) {
        if (end - start + 1 == p.length()) {
          startIdxList.add(start);
        }
        //shrink the substring
        char chSt = s.charAt(start);
        if (chars.contains(chSt - 'a')) {
          freq[chSt - 'a']++;
          if (freq[chSt - 'a'] > 0) {
            count++;
          }
        }
        start++;
      }
      end++;
    }
    return startIdxList;
  }

  // second way of doing the same crap
  public List<Integer> findAnagrams2(String s, String p) {
    List<Integer> result = new ArrayList<>();
    if (p.length() > s.length()) {
      return result;
    }

    char[] targetMap = new char[26];
    for (int i = 0; i < p.length(); i++) {
      targetMap[p.charAt(i) - 'a']++;
    }
    char[] current = new char[26];
    int k = p.length();
    int p1 = 0, p2 = 0;
    while (p2 < s.length()) {
      current[s.charAt(p2++) - 'a']++;

      if (p2 - p1 == k) {
        if (areEquals(targetMap, current)) {
          result.add(p1);
        }
        current[s.charAt(p1++) - 'a']--;
      }
    }
    if (areEquals(targetMap, current)) {
      result.add(p1);
    }
    return result;
  }

  private boolean areEquals(char[] a1, char[] a2) {
    for (int i = 0; i < 26; i++) {
      if (a1[i] != a2[i]) {
        return false;
      }
    }
    return true;
  }

  //  1208. Get Equal Substrings Within Budget
  public int equalSubstring(String s, String t, int maxCost) {
    int[] diff = new int[s.length()];
    for (int i = 0; i < s.length(); i++) {
      diff[i] = Math.abs(s.charAt(i) - t.charAt(i));
    }
    int start = 0, end = 0;
    int maxLength = 0;
    int sum = 0;
    while (end < diff.length) {
      sum += diff[end];
      while (sum > maxCost) {
        sum -= diff[start++];
      }
      maxLength = Math.max(maxLength, end - start + 1);
      end++;
    }

    return maxLength;
  }

  // another solution without extra space
  public int equalSubstringWithoutSpace(String s, String t, int maxCost) {
    int start = 0, end = 0;
    int maxLength = 0;
    int sum = 0;
    while (end < s.length()) {
      sum += (Math.abs(s.charAt(end) - t.charAt(end)));
      while (sum > maxCost) {
        sum -= (Math.abs(s.charAt(start) - t.charAt(start)));
        start++;
      }
      maxLength = Math.max(maxLength, end - start + 1);
      end++;
    }

    return maxLength;
  }

  //424. Longest Repeating Character Replacement
  public int characterReplacement(String s, int k) {
    char[] chars = s.toCharArray();
    int[] freq = new int[26]; //because only uppercase letters are possible
    int maxFreq = 0, currentLen, start = 0, end = 0, maxLength = 0;
    while (end < chars.length) {
      freq[chars[end] - 'A']++;
      maxFreq = Math.max(maxFreq, freq[chars[end] - 'A']);
      currentLen = end - start + 1;
      if (currentLen - maxFreq
          > k) {//meaning we have more extra characters than we can replace. then we need to shrink the substring
        freq[chars[start] - 'A']--;
        start++;
      }
      maxLength = Math.max(maxLength, end - start + 1);
      end++;
    }

    return maxLength;
  }

  //  1695. Maximum Erasure Value
  public int maximumUniqueSubarray(int[] nums) {
    Set<Integer> uniqueNums = new HashSet<>();
    int start = 0, end = 0;
    int maxSum = 0;
    int currentSum = 0;
    while (start < nums.length && end < nums.length) {
      if (!uniqueNums.contains(nums[end])) {
        currentSum += nums[end];
        maxSum = Math.max(maxSum, currentSum);
        uniqueNums.add(nums[end]);
        end++;
      } else {
        currentSum -= nums[start];
        uniqueNums.remove(nums[start++]);
      }
    }

    return maxSum;
  }

  /**
   * the key thing is that to check if all elements in current window are unique we simply check if
   * map.size() == windowSize
   */
  //  1695. Maximum Erasure Value (using hashmap)
  public int maximumUniqueSubarray2(int[] nums) {
    Map<Integer, Integer> t = new HashMap<>();
    int runningSum = 0, start = 0, end = 0;
    int maxSum = 0;
    while (end < nums.length) {
      runningSum += nums[end];
      t.put(nums[end], 1 + t.getOrDefault(nums[end], 0));
      while (t.size() < end - start + 1) {
        runningSum -= nums[start];
        if (t.containsKey(nums[start])) {
          t.put(nums[start], t.get(nums[start]) - 1);
          if (t.get(nums[start]) == 0) {
            t.remove(nums[start]);
          }
        }
        start++;
      }
      if (t.size() == end - start + 1) {
        maxSum = Math.max(maxSum, runningSum);
      }
      end++;
    }
    return maxSum;
  }

  //  930. Binary Subarrays With Sum
  //  prefix sum approach
  public int numSubarraysWithSum(int[] nums, int goal) {
    Map<Integer, Integer> t = new HashMap<>();
    t.put(0, 1);
    int runningSum = 0, count = 0;
    for (int num : nums) {
      runningSum += num;
      int key = runningSum - goal;
      if (t.containsKey(key)) {
        count += t.get(key);
      }
      t.put(runningSum, 1 + t.getOrDefault(runningSum, 0));
    }

    return count;
  }

  //difference approach
  public int numSubarraysWithSum2(int[] nums, int goal) {
    return atMost(nums, goal) - atMost(nums, goal - 1);
  }

  private int atMost(int[] nums, int k) {
    int sum = 0;
    int cunt = 0;
    int start = 0, end = 0;
    while (end < nums.length) {
      sum += nums[end];
      while (start <= end && sum > k) {
        sum -= nums[start++];
      }
      cunt += (end - start + 1);
      end++;
    }
    return cunt;
  }
}

