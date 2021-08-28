package 链表;

public class 判断一个链表是否有环 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
        public boolean hasCycle(ListNode head) {
            if(head==null||head.next==null) return false;
            ListNode slow=head;
            ListNode fast=head.next;
            while(slow!=fast)
            {
                //问题1：怎么表示快慢指针
                //slow=slow.next;
                //fast=fast.next.next;
                //问题2：怎么表示快指针在慢指针前面
                //回答，不需要判断，如果快指针先到null，则说明没有环
                //如果快慢指针相遇，则说明有环
                //问题3：为什么慢指针一步，快指针两步
                //因为如果快指针一步慢指针两步，每一次循环两者的距离减小1，一定会遇到，不错过，最安全
                if(fast==null||fast.next==null){return false;}

                slow=slow.next;
                fast=fast.next.next;

            }
            return true;
        }

}
