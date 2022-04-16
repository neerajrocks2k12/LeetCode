###Algo notes
"I can solve one question at one point of time but to solve when I am stressed during an interview, I really need a good grip so I can drive my own way rather than depending upon my memory"

#### SLIDING WINDOW
A very important POINT. Sliding Window DOES NOT WORK (in general) FOR NEGATIVE + POSITIVE NUMBERS, it will work if only one type of numbers are there. Also, if EXACT K is given then sliding window may not work, try to use the trick EXACT (K) = At_Most(K) - At_Most(K-1)  See https://leetcode.com/problems/subarrays-with-k-different-integers and https://leetcode.com/problems/count-number-of-nice-subarrays (both don’t work with usual sliding window)

https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/ (Very GOOD QUESTION WHERE DP TIMES OUT BUT a clever trick reduces it to sliding window maximum length sum subarray problem also see https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/discuss/935935/Java-Detailed-Explanation-O(N)-Prefix-SumMap-Longest-Target-Sub-Array)

	• https://leetcode.com/problems/number-of-sub-arrays-of-size-k-and-average-greater-than-or-equal-to-threshold/ (fixed size sliding window => translates to greater than equal to sum condition (avg * k) 
	• https://leetcode.com/problems/contains-duplicate-iii/ (very important => you could only think the core but was not able to solve the full) => concepts involved SortedSet, sliding window, range query | bucket sort => RANGE query part was you didn't know. Read on balanced trees and solve a few questions. Revise https://medium.com/@yzhua3/leetcode-contains-duplicate-iii-18f5fee8a741
	• https://leetcode.com/problems/minimum-size-subarray-sum/
	• https://leetcode.com/problems/sliding-window-maximum (very important. Monotonic queue)
	• https://leetcode.com/problems/squares-of-a-sorted-array/ (good two pointer usage. Square and sort (O(nlog(n)) is easy. Two pointer one is not obvious. 
	• https://leetcode.com/problems/permutation-in-string/ (CHUTIYE TUJHE YE KARA HUA QUESTION SOLVE NAI HUA, CHUTIA HAI TU BHADVE)
https://leetcode.com/problems/subarray-product-less-than-k/ **(Very important. Remember the number of new subarrays when jth element is included in current window is =  j-i+1. 
Example: {1,2,3} let us say we add {4} to above set then new sub arrays that we can make are => {1,2,3,4}, {2,3,4}, {3,4}, {4} = 3 - 0 + 1 = 4**
    **Revise this question as you had no clue on this trick**  
	• https://leetcode.com/problems/subarrays-with-k-different-integers (Very important. Sliding window does not work directly because EXACT K scenario is there)  
	• https://leetcode.com/problems/subarrays-with-k-different-integers/discuss/523136/JavaC%2B%2BPython-Sliding-Window (VERY IMPORTANT> READ FIRST COMMENT)  
	• https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/ (VERY IMPORTANT)  
	• https://leetcode.com/problems/continuous-subarray-sum/ (Prefix sum revise the mod trick)  
	• https://leetcode.com/problems/count-number-of-nice-subarrays/discuss/508217/C%2B%2B%3A-Visual-explanation.-O(1)-space.-Two-pointers [READ ]  
	https://leetcode.com/problems/vowels-of-all-substrings/ (good problem to see when not to do sliding window. This is just counting problem)  
	https://leetcode.com/problems/longest-repeating-character-replacement/ (revise)  
	https://leetcode.com/problems/frequency-of-the-most-frequent-element/ (Sorting +sliding window You could only think half solution, )  
TODO 02-12-21  
https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/  
https://leetcode.com/problems/replace-the-substring-for-balanced-string/  
	https://leetcode.com/problems/maximum-length-of-repeated-subarray/  
	  
	  
	Lee's Sliding
	1. Number of Substrings Containing All Three Characters
	2. Count Number of Nice Subarrays
	3. Replace the Substring for Balanced String
	4. Max Consecutive Ones III
	5. Binary Subarrays With Sum
	6. Subarrays with K Different Integers
	7. Fruit Into Baskets
	8. Shortest Subarray with Sum at Least K
	9. Minimum Size Subarray Sum
	
	From <https://leetcode.com/problems/binary-subarrays-with-sum/discuss/186683/> 
	
	PREFIX SUM  + SLIDING WINDOW
	https://leetcode.com/problems/grumpy-bookstore-owner/ (Very good problem. Must revise)
	
#### PREFIX SUM problems  
https://leetcode.com/problems/subarray-sum-equals-k/ (Very important, revise the solution.) The technique (hashmap one) used here can also be used to solve below with slight modification
https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/


#### Stack
**Very Important** https://leetcode.com/problems/trapping-rain-water (**you always confuse this question with area in histogram. BOTH ARE DIFFERENT**) in this we want to find the left and right bound for each element.  
Maintain decreasing monotonic stack  
https://leetcode.com/problems/maximum-subarray-min-product/ (Very good problem involving NSL and NSR concept)=> you thought it was sliding window but remember to think broadly. You could solve it but took more than 30 mins  
https://leetcode.com/problems/online-stock-span/ (good problem making use of Nearest Greater to the Left (NGL) pattern. Revise as you missed one corner case of all increasing values  
https://leetcode.com/problems/next-greater-element-ii/ **REVISE**(very good problem of using circular array. Also modulo operation is expensive than plus/minus. Good example is this problem)  
https://leetcode.com/problems/sum-of-subarray-minimums/ **REVISE** => could not remember how I solved it  
	```
	
	find range where arr[i] is min element, let that range be  
	        [l, r] = [nsl[i] + 1, nsr[i] - 1]  
	then subarray 
	cunt = (i - l + 1) * (r - i + 1) [REMEMBER THIS, SEE PROOF BELOW]
	     = (i - (nsl[i] + 1) + 1) * (nsr[i] - 1 - i + 1)
	     = (i - nsl[i]) * (nsr[i] - i)
	0 1 2 3
	3 1 2 4
	(-1, 1) => (0 - -1) * (1 - 0) = 1 * 1
	3 -> [0, 0]
	(0 - 0 + 1) * (0 - 0 + 1) = 1 * 1 = 1
	2 -> [l, r] = [2, 3]
	(2 - 2 + 1) * (3 - 2 + 1) = 1 * 2 = 2 
	1 -> [l, r] = [0, 3]
	(1 - 0 + 1) * (3 - 1 + 1) = 2 * 3 = 6
	3 -> [4, 4]
	(4 - 4 + 1) 
	```
	
	/*
	proof: 
	let A[i] be part of subarray starting from A[x] and ending at A[y]
	then 0 <= x <= i and i <= y <= arr.length
	we have (i - 0 + 1) choices for left side of A[i]
	and (arr.length - i + 1 - 1 (to avoid double counting A[i])) choices for right side of A[i]
	so total possible subarrays that include A[i] are
	left * right
	= (i + 1) * (arr.length - i)*/

#### Two pointers / Sliding window
https://leetcode.com/problems/container-with-most-water/ (**Important: You need two pointers to solve it. DON'T confuse this with trapping rain water. Ye stack se nai hoga**)  
we use two pointers `left`, `right` with `left = 0` and `right = a.length - 1` (initially)  
now we calculate `area = min(a[left], a[right]) * (right - left)` and keep track of max area observed. In order to move further, we use below observation  
if `a[left] < a[right]` and we keep left pointer fixed then for given left we will never find better result by reducing right. why?  because width reduces as we move right towards left but height of current container
remains fixed as it is `min(a[left], a[right]) = a[left]` and since `a[left]` is minimum and we are fixing left we can't improve further by reducing width
thus we move right towards left in hope of better solution.

https://leetcode.com/problems/maximum-width-ramp/ **[Very good problem]**
can be solved using **suffixMax** array. **The solution is to find maximum element >= a[i] to the right.**  
The two pointer solution using left pointer on original array and right pointer on suffixMax array is very clever  
you basically keep left pointer fixed and move right pointer from 0 to end. And we increment left pointer only when `A[left] > suffixMax[right]`   
=> ham aisa isiliye karte hain kyunki suffixMax decreasing order me sorted hai, toh current left ke liye jis point par hame us se badha ya barabar right element milta rahega ham right ko expand kar skte hain, cz we are targeting for max width  
=> jab `A[left] > suffixMax[right]` true hoga, us point ke baad se har right value ke liye ye true hoga,  
 cz suffixMax decreasing order me sorted hai. That means uske baad hame aisa koi right nai milega (current left ke liye) jo A[left] <= A[right] ko true kar pae. So we increment left.
This question can also be solved using binary search. For each i, find the farthest element in suffixMax array such that A[i] <= suffixMax[j] holds. Since suffixMax is sorted in non-increasing order so we can use bin search. O(n) space, O(nlogn) time.

#### DP (Double Penetration)
https://leetcode.com/problems/longest-increasing-subsequence/ DP is `O(n^2)`
 maintain an array `dp[j] = length of longest increasing subsequence` **ending** at `j` then for each `i` we want to move `j` from `i` to `0` and see if we can append `a[i]` to any of the
 existing subsequences. We basically run below loop  
 ```
for(int i = 1; i < a.length; i++) {  
    dp[i] = 1; // base case when we can't append a[i] to any existing subsequence  
    for(int j = i; j >= 0; j--) {  
        if(a[j] < a[i]) {  
            dp[i] = max(dp[i], 1 + dp[j]);  
        }  
    }  
 }
```
There is also below `O(nlogn)` solution that uses **binary search**.  
we define `dp[i] = element at which subsequence of length i ends, if no such element exists then dp[i] = INF  `
```
public int lengthOfLIS(int[] nums) {
        if(1 == nums.length) {
            return 1;
        }
        int[] dp = new int[nums.length + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++) {
            int idxWhereNUMSICanFit = Arrays.binarySearch(dp, nums[i]);
            if(idxWhereNUMSICanFit < 0) {
                idxWhereNUMSICanFit = -1 - idxWhereNUMSICanFit; // because inbuilt function returns negative offset position
            }
            if(dp[idxWhereNUMSICanFit - 1] < nums[i] && nums[i] < dp[idxWhereNUMSICanFit]) {
                dp[idxWhereNUMSICanFit] = nums[i];
            }
        }
        int max = 0;
        for(int i = 1; i < dp.length; i++) {
            if(dp[i] < Integer.MAX_VALUE) {
                max = i;
            }
        }
        
        return max;
    }
```