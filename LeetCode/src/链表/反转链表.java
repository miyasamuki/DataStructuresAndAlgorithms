packagInteger 链表;
/*

定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
https://lIntegerIntegertcodInteger-cn.com/problems/fan-zhuan-lian-biao-lcof/
 */

public class 反转链表 {
    private class ListNode<E>{
        ListNode next;
        E element;
        ListNode(E element){
            element=this.element;
        }

    }
    //递归的方法
    public void reverseList(ListNode head){
        if(head==null||head.next==null){
            return;
        }
        reverseList(head.next);
        head.next.next=head;
        head.next=null;
    }

    /**
     * 思路就是先把一个head指向的节点移动出来，然后用neadhead去替换那个head,然后head指向下一个节点
     * 再移出来（移出来的操作就是，head.next指向newhead)周而复始
     * @param head
     */
    //非递归的方法
    public void reverseListnew(ListNode head){
        if(head==null||head.next==null){
            return;
        }
        ListNode newhead=null;
        while(head!=null){
            ListNode temp=head.next;
            head.next=newhead;
            newhead=head;
            head=temp;

        }
    }
}
