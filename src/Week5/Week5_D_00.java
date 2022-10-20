package Week5;

public class Week5_D_00 {
    public static void main(String[] args) {

    }

    static class Node{
        int val;

        Node right;
        Node down;

        Node(int val){
            this.val = val;
        }
    }

    static class LinkedMatrix{
        Node head = new Node(-1);

        LinkedMatrix(int n, int m){
            Node downtmp = head;
            Node righttmp = head;
            for(int i = 0; i < n; i++){
                downtmp.down = new Node(-1);
                downtmp = downtmp.down;
            }
            for(int i = 0; i < m; i++){
                righttmp.right = new Node(-1);
                righttmp = righttmp.right;
            }
        }
    }
}
