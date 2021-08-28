package 链表;

public class 移除链表元素 {
    /*
    https://leetcode-cn.com/problems/remove-linked-list-elements/

给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
输入：head = [1,2,6,3,4,5,6], val = 6
输出：[1,2,3,4,5]
     */

     public class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
     /*
     1.为什么不能返回head
     因为head是head,dummy是dummy,是两个不同的地址
     而dummy和temp就是指向了同一个地址的两个不同指针
     之所以要有temp是为了记录dummy.next的位置
     因为dummy的位置是不断移动的，因此节点的位置不断的再变化
     收获很大
      */

    public ListNode removeElements(ListNode head, int val) {
        if(head==null) {return null;}
        if(head.next==null&&head.val==val){return null;}
        ListNode dummyNode=new ListNode(0);
        dummyNode.next=head;
        ListNode temp=dummyNode;
        while(dummyNode.next!=null){
            if(dummyNode.next.val==val){
                dummyNode.next=dummyNode.next.next;
            }else{
                dummyNode=dummyNode.next;
            }

        }
        return temp.next;
    }
}
