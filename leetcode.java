class Solution {

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] toReturn = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                toReturn[0] = map.get(target - nums[i]);
                toReturn[1] = i;
            }    
            map.put(nums[i], i);
        }
        return toReturn;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode first = new ListNode(0);
        ListNode current = first;
        int firstVal = l1.val + l2.val;
        int carryOver = firstVal / 10;
        first.val = firstVal % 10;
        while (l1.next != null || l2.next != null || carryOver > 0) {
            ListNode next = new ListNode(0);
            current.next = next;
            current = next;
            int val = carryOver;
            if (l1.next != null) {
                l1 = l1.next;
                val += l1.val;
            } 
            if (l2.next != null) {
                l2 = l2.next;
                val += l2.val;
            } 
            current.val = val % 10;
            carryOver = val / 10;
        }
        return first;
    }

    // sliding window approach to strings
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalNum = nums1.length + nums2.length;
        int medianIndexEven = totalNum / 2;
        int medianIndex = totalNum / 2 + 1;
        int i = 0, j = 0;
        double toReturn = 0;
        while (i < nums1.length || j < nums2.length) {
            if ((i < nums1.length && j < nums2.length && nums1[i] <= nums2[j]) || (i < nums1.length && j >= nums2.length)) {
                if ((i + j + 1) == medianIndex && totalNum % 2 == 1) {
                    toReturn = nums1[i];
                    return toReturn;
                }
                if ((i + j + 1) == medianIndex && totalNum % 2 == 0) {
                    toReturn += nums1[i];
                    return toReturn / 2;
                }
                if ((i + j + 1 == medianIndexEven) && totalNum % 2 == 0) {
                    toReturn += nums1[i];
                }
                i++;
            } else if ((i < nums1.length && j < nums2.length && nums1[i] > nums2[j]) || (j < nums2.length && i >= nums1.length)) {
                if ((i + j + 1) == medianIndex && totalNum % 2 == 1) {
                    toReturn = nums2[j];
                    return toReturn;
                }
                if ((i + j + 1) == medianIndex && totalNum % 2 == 0) {
                    toReturn += nums2[j];
                    return toReturn / 2;
                }
                if ((i + j + 1 == medianIndexEven) && totalNum % 2 == 0) {
                    toReturn += nums2[j];
                }
                j++;
            }
        }
        return toReturn;
    }

    // remember to check for overflow
    // if x = a * b + c
    // if (a != 0 && (x - c) / a != b) break;
    public int reverse(int x) {
        boolean negative = false;
        if (x < 0) {
            negative = true;
             x = -1 * x;
        }
        int toReturn = 0;
        while (x > 0) {
            int oldToReturn = toReturn;
            int mod = x % 10;
            toReturn = 10 * oldToReturn + mod;
            if (oldToReturn != 0 && (toReturn - mod) / oldToReturn != 10) { return 0; }
            x = x / 10;
        }
        if (negative) { toReturn = -1 * toReturn; }
        return toReturn;
    }

    public boolean isPalindrome(int x) {
        if (x < 0) { return false; }
        int num = x;
        int rev = 0;
        while (x > 0) {
            rev = rev * 10 + x % 10;
            x = x / 10;
        }
        return (num == rev);
    }
}
