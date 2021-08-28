
public class  ReverseList {

    public static void main(String[] args) {
       
    }
    public static ListNode ReverseListfunc(ListNode head) {
        if(head==null)
            return null;
        //head为当前节点，如果当前节点为空，那就什么都不做，直接返回空
        ListNode pre=null;
        ListNode next=null;
        while(head!=null){
            next=head.next;
            head.next=pre;
            pre=head;
            head=next;
        }
        return pre;

    }


}
