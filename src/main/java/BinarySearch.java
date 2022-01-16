import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BinarySearch {

  //  532. K-diff Pairs in an Array
  public int findPairs(int[] nums, int k) {
    Set<Point> set = new HashSet<>();
    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++) {
      int right = binSearch(nums, nums[i] + k);
      int left = binSearch(nums, nums[i] - k);
      if (right != -1 && right != i) {
        set.add(new Point(nums[i], nums[right]));
      }
      if (left != -1 && left != i) {
        set.add(new Point(nums[left], nums[i]));
      }
    }
    return set.size();
  }

  private int binSearch(int[] nums, int target) {
    int start = 0, end = nums.length;
    while (start < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] > target) {
        end = mid;
      } else if (nums[mid] < target) {
        start = mid + 1;
      }
    }
    return -1;
  }

  //436. Find Right Interval
  public int[] findRightInterval(int[][] intervals) {
    List<int[]> starts = new ArrayList<>();
    int i = 0;
    for (int[] interval : intervals) {
      starts.add(new int[]{interval[0], i++});
    }
    Collections.sort(starts, (a, b) -> a[0] - b[0]);
    int[] result = new int[intervals.length];
    i = 0;
    for (int[] interval : intervals) {
      //binsearch here
      int val = interval[1]; //end
      int idx = binSearch(starts, val);
      result[i++] = (idx == -1 ? idx : starts.get(idx)[1]);
    }
    return result;
  }

  //finding "smallest" element greater than or equal to target
  private int binSearch(List<int[]> arr, int target) {
    int start = 0, end = arr.size(); // [start, end)
    while (start < end) {
      int mid = start + (end - start) / 2;
      if (arr.get(mid)[0] == target) {
        return mid;
      } else if (arr.get(mid)[0] < target) {
        start = mid + 1;
      } else if (arr.get(mid)[0] > target) {
        end = mid;
      }
    }
    return start == arr.size() ? -1 : start;
  }
}
