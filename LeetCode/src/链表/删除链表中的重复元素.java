package 链表;

public class 删除链表中的重复元素 {
    /*
    https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
   存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
   返回同样按升序排列的结果链表。
   输入：head = [1,1,2]
   输出：[1,2]
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode dummyNode = new ListNode(0);
            dummyNode = head;
            ListNode temp = dummyNode;
            while (temp.next != null) {
                if (temp.val == temp.next.val) {
                    temp.next = temp.next.next;

                } else {
                    temp = temp.next;
                }
            }
            return dummyNode;
        }

    }
}
