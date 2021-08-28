package 链表;

public class 链表的中间节点 {
    public class ListNode {
     int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * https://leetcode-cn.com/problems/middle-of-the-linked-list/
     * @param head
     * @return
     * 输入：[1,2,3,4,5]
     * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
     * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
     * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
     * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
     */
    //方法一：快慢指针法：快指针每次走两步，慢指针每次走一步，当快指针走到链表的末尾。慢指针肯定在中间
    //方法二：遍历整个链表，数一下有多少个节点，再遍历第二遍，取N/2
    public ListNode middleNode(ListNode head) {
         ListNode fast=null;
         ListNode slow=null;
         fast=slow=head;
         while(fast!=null){
             fast=fast.next.next;
             slow=slow.next;
         }
         return slow;

    }
}
