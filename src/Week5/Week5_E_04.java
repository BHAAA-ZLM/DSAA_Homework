package Week5;

public class Week5_E_04 {
    public static final QReader in = new QReader();
    public static final QWriter out = new QWriter();

    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        int sqrtN = (int) Math.floor(Math.sqrt(n));
        BlockList block = new BlockList(sqrtN);

        int[][] positions = new int[283][283];
        int[][] buckets = new int[283][80000];

        int index = 0;
        int count = 0;
        for(int z = 0; z < n; z++){
            int x = in.nextInt();
            Node newNode = new Node(x);
            BlockList.insert(block, newNode);

            //operate on the two arrays
            if(count < sqrtN){
                positions[index][x/283]++;
                buckets[index][x]++;
                count++;
            }else{
                index++;
                count = 0;
                for(int i = 0; i < 283; i++) {
                    positions[index][i] = positions[index - 1][i];
                }
                for(int i = 0; i < 80000; i++) {
                    buckets[index][i] = buckets[index - 1][i];
                }
                positions[index][x/283]++;
                buckets[index][x]++;
                count++;
            }
        }

        for(int z = 0; z < m; z++){
            String operation = in.next();
            if(operation.equals("I")){
                insert(block, in.nextInt(), in.nextInt(),positions,buckets);
            }else if(operation.equals("M")){
                exchange(block, in.nextInt(),in.nextInt(),positions,buckets);
            }else if(operation.equals("Q")){
                findKSmallest(block,in.nextInt(),in.nextInt(),in.nextInt(),positions,buckets);
            }
        }

        out.close();
    }

    public static BlockList insert(BlockList block, int a, int x, int[][] positions,int[][] buckets){
        Node newNode = new Node(x);
        Guide tmpGuide = block.headGuide.next;

        int index = 0;
        while(a > tmpGuide.count){
            a -= tmpGuide.count;
            tmpGuide = tmpGuide.next;
            index++;
        }

        Node tmpNode = tmpGuide.element;
        for(int i = 0; i < a - 1; i++){
            tmpNode = tmpNode.next;
        }

        newNode.next = tmpNode.next;
        tmpNode.next = newNode;

        tmpGuide.count++;

        positions[index][x/283]++;
        buckets[index][x]++;

        if(tmpGuide.count > 2 * block.guideSize){
            int guideCount = tmpGuide.count;
            Guide newGuide = new Guide();

            newGuide.next = tmpGuide.next;
            tmpGuide.next = newGuide;

            int breakNodeIndex = guideCount/2;
            Node breakNode = tmpGuide.element;
            for(int z = 0; z < breakNodeIndex; z++){
                breakNode = breakNode.next;
            }
            newGuide.element.next = breakNode.next;
            breakNode.next = null;

            tmpGuide.count = breakNodeIndex;
            newGuide.count = guideCount - breakNodeIndex;

            block.guideSize++;

            for(int z = 282; z > 0; z--){
                if(index != z - 1) {
                    for(int i = 0; i < 283; i++) {
                        positions[z][i] = positions[z - 1][i];
                    }
                    for(int i = 0; i < 80000; i++) {
                        buckets[z] = buckets[z - 1];
                    }
                }else{
                    break;
                }
            }
            Node tmpGuideNode = tmpGuide.element.next;
            while(tmpGuideNode != null){
                positions[index][tmpGuideNode.val/283]--;
                buckets[index][tmpGuideNode.val]--;
                tmpGuideNode = tmpGuideNode.next;
            }
        }
        return block;
    }

    public static BlockList exchange(BlockList block, int a, int x, int[][] positions, int[][] buckets){
        Node newNode = new Node(x);
        Guide tmpGuide = block.headGuide.next;
        int index = 0;

        while(a > tmpGuide.count){
            a -= tmpGuide.count;
            tmpGuide = tmpGuide.next;
            index++;
        }

        Node tmpNode = tmpGuide.element;
        for(int i = 0; i < a - 1; i++){
            tmpNode = tmpNode.next;
        }

        positions[index][tmpNode.val/283]--;
        positions[index][x/283]++;
        buckets[index][tmpNode.val]--;
        buckets[index][x]++;

        newNode.next = tmpNode.next.next;
        tmpNode.next = newNode;

        return block;
    }

    public static void findKSmallest(BlockList block, int l, int r, int k, int[][] positions, int[][] buckets){
        Guide lGuide = block.headGuide.next;
        int dif = r - l;
        int unchangedif = dif;
        int lIndex = 0;

        while(l > lGuide.count){
            l -= lGuide.count;
            lGuide = lGuide.next;
            lIndex++;
        }

        Node lNode = lGuide.element;
        for(int z = 0; z < l; z++){
            lNode = lNode.next;
        }

        int leftleft = lGuide.count - l;
        dif -= leftleft;

        int[] finalPositions = new int[283];
        int[] finalBuckets = new int[80000];
        if(dif < lGuide.next.count){
            for(int i = 0; i < unchangedif + 1; i++){
                finalPositions[lNode.val/283]++;
                finalBuckets[lNode.val]++;
                if(lNode.next != null){
                    lNode = lNode.next;
                }else{
                    lNode = lGuide.next.element.next;
                }
            }
            int interval = 0;
            while(k < finalPositions[interval]){
                k -= finalPositions[interval];
                interval++;
            }
            int kSmallest = interval * 283;
            while(k < finalBuckets[kSmallest]){
                k -= finalBuckets[kSmallest];
                kSmallest++;
            }
            out.println(kSmallest);
        }else{
            int rIndex = lIndex + 1;
            Guide rGuide = lGuide.next;
            while(dif > rGuide.count){
                dif -= rGuide.count;
                rGuide = rGuide.next;
                rIndex++;
            }

            while(lNode != null){
                finalPositions[lNode.val/283]++;
                finalBuckets[lNode.val]++;
                lNode = lNode.next;
            }

            Node rNode = rGuide.element;
            for(int i = 0; i < dif; i++){
                rNode = rNode.next;
                finalPositions[rNode.val/283]++;
                finalBuckets[rNode.val]++;
            }

            int interval = 0;
            while(interval < 283){
                finalPositions[interval] += positions[rIndex][interval] - positions[lIndex][interval];
                if(k > finalPositions[interval]){
                    k -= finalPositions[interval];
                    interval++;
                }else{
                    break;
                }
            }

            int kSmallest = interval * 283;
            while(kSmallest < (interval + 1) * 283){
                finalBuckets[kSmallest] += buckets[rIndex][kSmallest] - buckets[lIndex][kSmallest];
                if(k > finalBuckets[kSmallest]){
                    k -= finalBuckets[kSmallest];
                    kSmallest++;
                }else{
                    break;
                }
            }
            out.println(kSmallest);
        }
    }

    static class Node{
        Node next;
        int val;
        Node(int val){
            this.val = val;
        }
    }

    static class Guide{
        Guide next;
        int count = 0;
        Node element = new Node(-1);
        Node tmpElement;
    }

    static class BlockList{
        Guide headGuide = new Guide();
        Guide tmpGuide = headGuide;
        int guideSize;

        BlockList(int n){
            guideSize = n;
        }

        public static BlockList insert(BlockList block, Node node){
            if(block.headGuide.next == null){
                Guide newGuide = new Guide();
                block.headGuide.next = newGuide;
                block.tmpGuide = newGuide;
            }

            if(block.tmpGuide.count < block.guideSize){
                if(block.tmpGuide.element.next == null){
                    block.tmpGuide.element.next = node;
                    block.tmpGuide.tmpElement = node;
                    block.tmpGuide.count++;
                }else{
                    block.tmpGuide.tmpElement.next = node;
                    block.tmpGuide.tmpElement = node;
                    block.tmpGuide.count++;
                }
            }else{
                Guide newGuide = new Guide();
                block.tmpGuide.next = newGuide;
                block.tmpGuide = newGuide;
                block.tmpGuide.element.next = node;
                block.tmpGuide.tmpElement = node;
                block.tmpGuide.count++;
            }
            return block;
        }
    }
}
