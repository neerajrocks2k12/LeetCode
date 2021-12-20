import java.awt.Point;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class BinarySearch {
//  532. K-diff Pairs in an Array
  public int findPairs(int[] nums, int k) {
    Set<Point> set = new HashSet<>();
    Arrays.sort(nums);
    for(int i = 0; i < nums.length; i++) {
      int right = binSearch(nums, nums[i] + k);
      int left = binSearch(nums, nums[i] - k);
      if(right != -1 && right != i) {
        set.add(new Point(nums[i], nums[right]));
      }
      if(left != -1 && left != i) {
        set.add(new Point(nums[left], nums[i]));
      }
    }
    return set.size();
  }

  private int binSearch(int[] nums, int target) {
    int start = 0, end = nums.length;
    while(start < end) {
      int mid = start + (end - start) / 2;
      if(nums[mid] == target) {
        return mid;
      } else if(nums[mid] > target) {
        end = mid;
      } else if(nums[mid] < target) {
        start = mid + 1;
      }
    }
    return -1;
  }
}
