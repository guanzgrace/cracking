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
}