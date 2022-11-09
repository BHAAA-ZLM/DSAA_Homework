package Week5;

import java.io.*;
import java.util.StringTokenizer;

public class Week5_E_03 {
    public static final QReader in = new QReader();
    public static final QWriter out = new QWriter();

    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        BlockList block = new BlockList(n);

        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            Node newNode = new Node(x);
            BlockList.insert(block, newNode);
        }
        for (int i = 0; i < m; i++) {
            String operation = in.next();
            if (operation.equals("I")) {
                int a = in.nextInt();
                int x = in.nextInt();
                Node b = new Node(x);
                Guide tmpGuide = block.headGuide.next;

                while(a > tmpGuide.count){
                    a -= tmpGuide.count;
                    tmpGuide = tmpGuide.next;
                }

                Node tmpNode = tmpGuide.element;
                for(int z = 0; z < a; z++){
                    tmpNode = tmpNode.next;
                }

                b.next = tmpNode;
                b.last = tmpNode.last;
                tmpNode.last.next = b;
                tmpNode.last = b;

                tmpGuide.count++;
                Guide adjustArrayGuide = tmpGuide;
                while(adjustArrayGuide != null){
                    adjustArrayGuide.positions[x/283]++;
                    adjustArrayGuide.buckets[x]++;
                    adjustArrayGuide = adjustArrayGuide.next;
                }

                if(tmpGuide.count > 283 * 2){
                    int guideCount = tmpGuide.count;
                    Guide newGuide = new Guide(tmpGuide);
                    newGuide.next = tmpGuide.next;
                    if(tmpGuide.next != null) tmpGuide.next.last = newGuide;
                    tmpGuide.next = newGuide;
                    newGuide.last = tmpGuide;
                    Node breakNode = tmpGuide.element.next;
                    int halfGuide = guideCount/2;
                    for(int i2 = 0; i2 < halfGuide; i2++){
                        breakNode = breakNode.next;
                    }
                    breakNode.last.next = null;
                    breakNode.last = newGuide.element;
                    newGuide.element.next = breakNode;
                    tmpGuide.count = halfGuide;
                    newGuide.count = guideCount - halfGuide;

                    for(int i2 = 0; i2 < tmpGuide.positions.length; i2++){
                        newGuide.positions[i2] = tmpGuide.positions[i2];
                    }
                    for(int i3 = 0; i3 < tmpGuide.buckets.length; i3++){
                        newGuide.buckets[i3] = tmpGuide.buckets[i3];
                    }
                    Node newGuideTmpNode = newGuide.element.next;
                    while(newGuideTmpNode != null){
                        tmpGuide.positions[newGuideTmpNode.val/283]--;
                        tmpGuide.buckets[newGuideTmpNode.val]--;
                        newGuideTmpNode = newGuideTmpNode.next;
                    }
                }
            } else if (operation.equals("M")) {
                int a = in.nextInt();
                int b = in.nextInt();
                Node newNode = new Node(b);

                Guide tmpGuide = block.headGuide.next;
                while(a > tmpGuide.count){
                    a -= tmpGuide.count;
                    tmpGuide = tmpGuide.next;
                }
                Node changeNode = tmpGuide.element;
                for(int z = 0; z < a; z++){
                    changeNode = changeNode.next;
                }

                while(tmpGuide != null) {
                    tmpGuide.positions[changeNode.val / 283]--;
                    tmpGuide.positions[b / 283]++;
                    tmpGuide.buckets[changeNode.val]--;
                    tmpGuide.buckets[b]++;
                    tmpGuide = tmpGuide.next;
                }

                changeNode.last.next = newNode;
                if(changeNode.next != null) changeNode.next.last = newNode;
                newNode.last = changeNode.last;
                newNode.next = changeNode.next;
                changeNode.last = null;
                changeNode.next = null;
            } else if (operation.equals("Q")) {
                int l = in.nextInt();
                int r = in.nextInt();
                int k = in.nextInt();
                int kSmallest;
                int dif = r - l;
                int unchangedif = dif;
                Guide lGuide = block.headGuide.next;

                while(l > lGuide.count){
                    l -= lGuide.count;
                    lGuide = lGuide.next;
                }

                Node lNode = lGuide.element;
                for(int z = 0; z < l; z++){
                    lNode = lNode.next;
                }
                if(unchangedif == 0){
                    kSmallest = lNode.val;
                }

                int leftleft = lGuide.count - l;
                if(dif > leftleft){
                    dif -= leftleft;
                    if(dif <= lGuide.next.count){
//                int[] elements = new int[unchangedif + 1];
//                for(int i = 0; i < unchangedif + 1; i++){
//                    elements[i] = lNode.val;
//                    if(lNode.next != null){
//                        lNode = lNode.next;
//                    }else{
//                        lNode = lGuide.next.element.next;
//                    }
//                }
//                mergeSort(elements, 0, elements.length - 1);
//                return elements[k - 1];
                        int[] finalPositions = new int[283];
                        int[] finalBuckets = new int[80000];
                        for(int z = 0; z < unchangedif + 1; z++){
                            finalPositions[lNode.val/283]++;
                            finalBuckets[lNode.val]++;
                            if(lNode.next != null){
                                lNode = lNode.next;
                            }else{
                                lNode = lGuide.next.element.next;
                            }
                        }

                        int intervalIndex = 0;
                        while(k > finalPositions[intervalIndex]){
                            k -= finalPositions[intervalIndex];
                            intervalIndex++;
                        }
                        int bucketIndex = intervalIndex * 283;
                        while(k > finalBuckets[bucketIndex]){
                            k -= finalBuckets[bucketIndex];
                            bucketIndex++;
                        }
                        kSmallest = bucketIndex;
                    }else{
                        Guide rGuide = lGuide.next;
                        while(dif > rGuide.count){
                            dif -= rGuide.count;
                            rGuide = rGuide.next;
                        }
                        Node rNode = rGuide.element;
                        for(int z = 0; z < dif; z++){
                            rNode = rNode.next;
                        }

                        int[] finalPositions = new int[283];
                        Node tmpLNode = lNode;
                        Node tmpRNode = rGuide.element.next;
                        while(tmpLNode != null){
                            finalPositions[tmpLNode.val/283]++;
                            tmpLNode = tmpLNode.next;
                        }
                        while(tmpRNode != rNode.next){
                            finalPositions[tmpRNode.val/283]++;
                            tmpRNode = tmpRNode.next;
                        }

                        int interval = 0;
                        for(int z = 0; z < 283; z++){
                            finalPositions[z] += rGuide.last.positions[z] - lGuide.positions[z];
                            if(k > finalPositions[interval]){
                                k -= finalPositions[interval];
                                interval++;
                            }else{
                                break;
                            }
                        }

                        int[] finalBuckets = new int[283];
                        int firstNum = interval * 283;
                        int finalNumber = 0;
                        tmpLNode = lNode;
                        tmpRNode = rGuide.element.next;
                        while(tmpLNode != null){
                            if(tmpLNode.val >= firstNum && tmpLNode.val < firstNum + 283) {
                                finalBuckets[tmpLNode.val - firstNum]++;
                            }
                            tmpLNode = tmpLNode.next;
                        }
                        while(tmpRNode != rNode.next){
                            if(tmpRNode.val >= firstNum && tmpRNode.val < firstNum + 283) {
                                finalBuckets[tmpRNode.val - firstNum]++;
                            }
                            tmpRNode = tmpRNode.next;
                        }
                        for(int z = 0; z < 283; z++){
                            finalBuckets[z] += rGuide.last.buckets[firstNum + z] - lGuide.buckets[firstNum + z];
                            if(k > finalBuckets[finalNumber]){
                                k-=finalBuckets[finalNumber];
                                finalNumber++;
                            }else{
                                break;
                            }
                        }

                        kSmallest = firstNum + finalNumber;
                    }

                }else{
                    int[] finalPositions = new int[283];
                    int[] finalBuckets = new int[80000];
                    for(int z = 0; z < unchangedif; z++){
                        finalPositions[lNode.val/283]++;
                        finalBuckets[lNode.val]++;
                        if(lNode.next != null){
                            lNode = lNode.next;
                        }else{
                            lNode = lGuide.next.element.next;
                        }
                    }

                    int intervalIndex = 0;
                    while(k > finalPositions[intervalIndex]){
                        k -= finalPositions[intervalIndex];
                        intervalIndex++;
                    }
                    int bucketIndex = intervalIndex * 283;
                    while(k > finalBuckets[bucketIndex]){
                        k -= finalBuckets[bucketIndex];
                        bucketIndex++;
                    }
                    kSmallest = bucketIndex;
                }
                out.println(kSmallest);
            }
        }
        out.close();
    }

    static class Node{
        Node next;
        Node last;
        int val;
        Node(int val){
            this.val = val;
        }
    }

    static class Guide{
        Guide next;
        Guide last;
        int count = 0;
        Node element = new Node(-1);
        Node tmpElement;

        int[] positions = new int[283];
        int[] buckets = new int[80000];

        Guide(Guide last){
            this.last = last;
            if(last != null) {
                for (int i = 0; i < 283; i++) {
                    positions[i] = last.positions[i];
                }
                for (int i = 0; i < 80000; i++) {
                    buckets[i] = last.buckets[i];
                }
            }
        }
    }

    static class BlockList{
        Guide headGuide = new Guide(null);
        Guide tmpGuide = headGuide;
        int guideSize;
        BlockList(int n){
            guideSize = (int)Math.floor(Math.sqrt(n));
        }

        public static BlockList insert(BlockList block, Node node){
            //Insert guide when there are no guides
            if(block.headGuide.next == null){
                Guide newGuide = new Guide(block.headGuide);
                block.headGuide.next = newGuide;
                newGuide.last = block.headGuide;
                block.tmpGuide = newGuide;
            }

            //Insert elements into the tmpGuide
            if(block.tmpGuide.count < 283){
                int positionIndex = node.val/283;
                block.tmpGuide.positions[positionIndex]++;
                block.tmpGuide.buckets[node.val]++;
                if(block.tmpGuide.element.next == null){
                    block.tmpGuide.element.next = node;
                    node.last = block.tmpGuide.element;
                    block.tmpGuide.tmpElement = node;
                    block.tmpGuide.count++;
                }else{
                    block.tmpGuide.tmpElement.next = node;
                    node.last = block.tmpGuide.tmpElement;
                    block.tmpGuide.tmpElement = node;
                    block.tmpGuide.count++;
                }
            }

            //When tmpGuide is full, create new guide and set tmpGuide to the new Guide
            if(block.tmpGuide.count >= 283){
                Guide newGuide = new Guide(block.tmpGuide);
                block.tmpGuide.next = newGuide;
                newGuide.last = block.tmpGuide;
                block.tmpGuide = newGuide;
            }
            return block;
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

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}