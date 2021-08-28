package 链表;


public class DeleteNode {
    /*
    请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。
    https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
     */
      public class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }


        public void deleteNode(ListNode node) {
            //之前的做法是：将node有个nodeList来记录，所以可以找到前继节点和后继节点，但是
            //现在没有size和nodeList,所以我找不到

                    node.val=node.next.val;
                    node.next=node.next.next;

        }

}
