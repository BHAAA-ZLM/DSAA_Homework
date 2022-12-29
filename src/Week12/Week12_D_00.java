package Week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Week12_D_00 {
    public static final boolean debug = false;
    public static int C;

    public static final QReader in = new QReader();
    public static void main(String[] args) {
//        int T = in.nextInt();
        for(int z = 0; z < 1; z++) {
            C = in.nextInt();
            int N = in.nextInt();
            AVLTree tree = new AVLTree();
            for (int i = 0; i < N; i++) {
                int x = in.nextInt();
                tree.node = tree.insertWithSize(tree.node, i, x);
            }

            if(debug) tree.inOrder(tree.node);

            int M = in.nextInt();
            for (int i = 0; i < M; i++) {
                command(tree);
            }
            if(debug) tree.inOrder(tree.node);
        }
    }

    public static void command(AVLTree tree){
        String cmd = in.next();
        if(cmd.equals("ask")){
            int n = in.nextInt();
            if(tree.node == null){
                System.out.println(n);
            }else {
                System.out.println(tree.node.ask(n));
            }
        }else if(cmd.equals("rem")){
            int x = in.nextInt();
            tree.node = tree.remove(tree.node,x);
            if(debug) tree.inOrder(tree.node);
        }else if(cmd.equals("ins")){
            int pos = in.nextInt() - 1;
            int key = in.nextInt();
            tree.node = tree.insertWithSize(tree.node,pos,key);
        }
    }

    static class Node{
        int key;
        int mergeKey, leftBound, rightBound;
        int orgLBound, orgRBound;
        int height;
        int size;
        Node left;
        Node right;
        Node(int key){
            this.key = key;
            this.height = 1;
            this.size = 1;
            this.leftBound = Math.max(0, -key);
            this.rightBound = Math.min(C, C - key);
//            if(leftBound > C){
//                this.orgLBound = 0;
//                this.orgRBound = 0;
//                this.mergeKey = 0;
//            }else if(rightBound < 0){
//                this.orgLBound = C;
//                this.orgRBound = C;
//                this.mergeKey = 0;
//            }else {
                this.orgLBound = this.leftBound;
                this.orgRBound = this.rightBound;
                this.mergeKey = key;
//            }
        }

        int ask(int n){
            if(n < leftBound){
                return leftBound + mergeKey;
            }else if(n > rightBound){
                return rightBound + mergeKey;
            }else{
                return n + mergeKey;
            }
        }
    }

    static class AVLTree{
        Node node;

        int getLeftBound(Node node){
            if(node == null) return 0;
            return node.leftBound;
        }

        int getRightBound(Node node){
            if(node == null) return C;
            return node.rightBound;
        }

        int getOrgLeftBound(Node node){
            if(node == null) return 0;
            return node.orgLBound;
        }

        int getOrgRightBound(Node node){
            if(node == null) return C;
            return node.orgRBound;
        }

        int getMergeKey(Node node){
            if(node == null) return 0;
            return node.mergeKey;
        }

        int getKey(Node node){
            if(node == null) return 0;
            return node.key;
        }

        void mergeKey(Node node){

            //first merge the left node and the middle node
            Node A = node.left;
            Node B = node;

            //the position after command A
            int tmpALeftBound = getLeftBound(A) + getMergeKey(A);
            int tmpARightBound = getRightBound(A) + getMergeKey(A);
            if(tmpARightBound < getOrgLeftBound(B)) {
                //if the interval of A is to the left of B's interval
                int newLeftBound = getOrgLeftBound(B);
                int newRightBound = getOrgLeftBound(B);
                B.leftBound = newLeftBound;
                B.rightBound = newRightBound;
                B.mergeKey = getKey(B);
            }else if(tmpALeftBound > getOrgRightBound(B)) {
                //if the interval of A is to the right of B's interval
                int newLeftBound = getOrgRightBound(B);
                int newRightBound = getOrgRightBound(B);
                B.leftBound = newLeftBound;
                B.rightBound = newRightBound;
                B.mergeKey = getKey(B);
            }else{
                //if the two intervals intersect
                int newLeftBound = Math.max(getOrgLeftBound(B), tmpALeftBound);
                int newRightBound = Math.min(getOrgRightBound(B), tmpARightBound);

                //the new offset for B
                B.mergeKey = getMergeKey(A) + getKey(B);

                //new start position for B
                B.leftBound = newLeftBound - getMergeKey(A);
                B.rightBound = newRightBound - getMergeKey(A);
            }

            Node C = node.right;
            int tmpBLeftBound = getLeftBound(B) + getMergeKey(B);
            int tmpBRightBound = getRightBound(B) + getMergeKey(B);
            if(tmpBRightBound < getLeftBound(C)) {
                //if the interval of B is to the left of C's interval
                int newLeftBound = getLeftBound(C);
                int newRightBound = getLeftBound(C);
                B.leftBound = newLeftBound;
                B.rightBound = newRightBound;
                B.mergeKey = getMergeKey(C);
            }else if(tmpBLeftBound > getRightBound(C)) {
                //if the interval of B is to the right of C's interval
                int newLeftBound = getRightBound(C);
                int newRightBound = getRightBound(C);
                B.leftBound = newLeftBound;
                B.rightBound = newRightBound;
                B.mergeKey = getMergeKey(C);
            }else {
                //if the two intervals intersect

                //get the intersection of the two interval
                int newLeftBound = Math.max(getLeftBound(C), tmpBLeftBound);
                int newRightBound = Math.min(getRightBound(C), tmpBRightBound);

                B.rightBound = newRightBound - getMergeKey(B);
                B.leftBound = newLeftBound - getMergeKey(B);
                B.mergeKey = getMergeKey(B) + getMergeKey(C);
            }
        }

        int height(Node node){
            if(node == null){
                return 0;
            }
            return node.height;
        }

        int size(Node node){
            if(node == null){
                return 0;
            }
            return node.size;
        }

        Node rightRotate(Node a){
            Node b = a.left;
            Node A = b.right;

            int aSize = size(a);
            int bSize = size(b);
            aSize = aSize - size(b.left) - 1;
            bSize = bSize + size(a.right) + 1;

            b.right = a;
            a.left = A;

            a.height = Math.max(height(a.left), height(a.right)) + 1;
            b.height = Math.max(height(b.left), height(b.right)) + 1;

            b.size = bSize;
            a.size = aSize;

            mergeKey(a);
            mergeKey(b);
            return b;
        }

        Node leftRotate(Node a){
            Node b = a.right;
            Node A = b.left;

            int aSize = size(a);
            int bSize = size(b);
            aSize = aSize - size(b.right) - 1;
            bSize = bSize + size(a.left) + 1;

            b.left = a;
            a.right = A;

            a.height = Math.max(height(a.left), height(a.right)) + 1;
            b.height = Math.max(height(b.left), height(b.right)) + 1;
            a.size = aSize;
            b.size = bSize;

            mergeKey(a);
            mergeKey(b);
            return b;
        }

        int getBalance(Node node){
            if(node == null){
                return 0;
            }
            return height(node.left) - height(node.right);
        }

        Node checkBalance(Node node){
            int balance = getBalance(node);
            //the left left case
            if(balance > 1 && getBalance(node.left) >= 0){
                return rightRotate(node);
            }
            //the left right case
            if(balance > 1 && getBalance(node.left) < 0){
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            //the right right case
            if(balance < -1 && getBalance(node.right) <= 0){
                return leftRotate(node);
            }
            //the right left case
            if(balance < -1 && getBalance(node.right) > 0){
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
            //if nothing is changed
            return node;
        }

        //insert the key into the wanted position
        Node insertWithSize(Node node, int size, int key){

            if(node == null){
                return (new Node(key));
            }
            if(size(node.left) >= size) {
                node.left = insertWithSize(node.left, size,key);
            } else {
                node.right = insertWithSize(node.right, size - size(node.left) - 1,key);
            }
            node.size = size(node.left) + size(node.right) + 1;
            node.height = Math.max(height(node.left), height(node.right)) + 1;

            Node balanced = checkBalance(node);
            mergeKey(node);
            return balanced;
        }

        Node searchMin(Node node){
            Node cur = node;
            while(cur.left != null){
                cur = cur.left;
            }
            return cur;
        }

        Node delete(Node node, int key){
            if(node == null){
                return null;
            }

            if(key < node.key){
                node.left = delete(node.left, key);
            }else if (key > node.key) {
                node.right = delete(node.right, key);
            }else{
                if(node.left == null || node.right == null){
                    if(node.left == null && node.right == null){
                        node = null;
                    }
                    else if(node.left == null){
                        node = node.right;
                    }
                    else{
                        node = node.left;
                    }
                }else{
                    Node temp = searchMin(node.right);
                    node.key = temp.key;
                    node.right = delete(node.right, temp.key);
                }
            }

            //if the root have height 0, return
            if(node == null){
                return null;
            }
            node.height = Math.max(height(node.left), height(node.right)) + 1;
            node.size = size(node.left) + size(node.right) + 1;
            Node balanced = checkBalance(node);
            mergeKey(node);
            return balanced;
        }

        //delete the key at the wanted position
        Node remove(Node node, int size){
            if(debug) System.out.println("checking " + getKey(node) +" " + getSize(node) + " with " + size);
            if(node == null) {
                if(debug) System.out.println("find smallest");
                return null;
            }

            if(size(node.left) >= size) {
                node.left = remove(node.left, size);
            } else if(size(node.left) < size - 1) {
                node.right = remove(node.right, size - size(node.left) - 1);
            } else {
                if(node.left == null || node.right == null){
                    if(node.left == null && node.right == null){
                        node = null;
                    }
                    else if(node.left == null){
                        node = node.right;
                    }
                    else{
                        node = node.left;
                    }
                }else{
                    Node tmp = searchMin(node.right);
                    node.key = tmp.key;
                    node.orgLBound = tmp.orgLBound;
                    node.orgRBound = tmp.orgRBound;
//                    node.right = delete(node.right, tmp.key);
                    node.right = remove(node.right, 1);
                }
            }

            if(node == null) {
                return null;
            }
            node.height = Math.max(height(node.left), height(node.right)) + 1;
            node.size = size(node.left) + size(node.right) + 1;
            Node balanced = checkBalance(node);
            mergeKey(node);
            return balanced;
        }

        //the inorder traversal of the tree
        void inOrder(Node node) {
            if (node != null)
            {
                inOrder(node.left);
                System.out.print(node.key + " ");
                inOrder(node.right);
            }
        }

        void preOrder(Node node) {
            if (node != null)
            {
                System.out.print(node.key + " ");
                preOrder(node.left);
                preOrder(node.right);
            }
        }

        int getSize(Node node){
            if(node == null){
                return 0;
            }
            return node.size;
        }
    }
}

class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}
