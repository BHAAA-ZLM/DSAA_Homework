package Week1;

import java.util.ArrayList;
import java.util.Scanner;


public class Week1_D_02 {
    public static final Scanner input = new Scanner(System.in);
    public static final boolean debug = false;

    static class Mahjong {
        public int num;
        public char suit;
        public boolean checked;

        public Mahjong(char num, char suit){
            this.num = Character.getNumericValue(num);
            this.suit = suit;
            this.checked = false;
        }

        public Mahjong(String str){
            this.num = Character.getNumericValue(str.charAt(0));
            this.suit = str.charAt(1);
            this.checked = false;
        }

        /**
         * to see if two mahjong are equal to each other
         * @param a
         * @return
         */
        public boolean equal(Mahjong a){
            return a.num == num && a.suit == suit;
        }

        /**
         * to see if two mahjong are in series of each other
         * @param a
         * @return
         */
        public boolean inSeries(Mahjong a){
            if(this.suit == 'z'){
                return false;
            }
            if(this.suit == a.suit){
                if(this.num == a.num + 1||this.num == a.num -1){
                    return true;
                }
            }
            return false;
        }

        public String print(){
            String a = String.valueOf(this.num) + Character.toString(this.suit);
            return a;
        }
    }

    public static void main(String[] args) {
        int times = input.nextInt();
        for(int i = 0; i < times; i++){
            matchCount = 0;
            String str = input.next();
            if(checkMahjong(str)){
                System.out.println("Blessing of Heaven");
            }else{
                System.out.println("Bad luck");
//                System.out.println(str);
            }
        }
    }

    private static boolean checkMahjong(String str){
        Mahjong[] mahjongArr = convertMahjongArray(str);
        ArrayList<int[]> pairIndex = findPairIndex(mahjongArr);
        if(pairIndex.size() != 0){
            for(int[] index : pairIndex){
                if(debug) {
                    System.out.println();
                    System.out.println("checking pair " + mahjongArr[index[0]].print() +
                            mahjongArr[index[1]].print());
                }
                if(checkPairSuit(index, mahjongArr)) {
                    mahjongArr[index[0]].checked = true;
                    mahjongArr[index[1]].checked = true;
                    if (checkArr(index, mahjongArr)) {
                        return true;
                    } else {
                        mahjongArr[index[0]].checked = false;
                        mahjongArr[index[1]].checked = false;
                    }
                }
            }
            return false;
        }else{
            return false;
        }
    }

    private static boolean checkPairSuit(int[] index, Mahjong[] mahjongArr){
        int bcount = 0;
        int scount = 0;
        int wcount = 0;
        int zcount = 0;
        for(int i = 0; i < 14; i++){
            if(i != index[0] && i != index[1]){
                if(mahjongArr[i].suit == 'b'){
                    bcount++;
                }
                if(mahjongArr[i].suit == 's'){
                    scount++;
                }
                if(mahjongArr[i].suit == 'w'){
                    wcount++;
                }
                if(mahjongArr[i].suit == 'z'){
                    zcount++;
                }
            }
        }
        if(bcount%3!=0||scount%3!=0||wcount%3!=0||zcount%3!=0){
            if(debug) System.out.println("unvalid pair "+ mahjongArr[index[0]].print());
            return false;
        }else{
            return true;
        }
    }

    private static int matchCount = 0;
    private static boolean checkArr(int[] index, Mahjong[] mahjongArr){
        if(debug) {
            for (Mahjong b : mahjongArr) {
                if (b.checked) {
                    System.out.println("check is true for " + b.print());
                }
            }
        }
//        for(int i : index){
//            if(debug) {
//                System.out.println("setting checked true for " + mahjongArr[i].print());
//            }
//            mahjongArr[i].checked = true;
//        }
        for(Mahjong a : mahjongArr){
            if(debug) System.out.println(a.print() + "'s checked is "+a.checked);
            if(!a.checked){
                if(debug){
                    System.out.println("checking possible index for "+a.print());
                }
                if(debug) {
                    System.out.println("setting checked true for " + a.print());
                }
                a.checked = true;
                ArrayList<int[]> possibleIndex = new ArrayList<>();
                possibleIndex.add(find3Pair(a, mahjongArr));
                if(a.suit != 'z') {
                    possibleIndex.addAll(find3Series(a, mahjongArr));
                }
                if(!checkAllNull(possibleIndex)) {
                    if(debug) System.out.println("checked not null index for "+ a.print());
                    for (int[] index3 : possibleIndex) {
                        if(index3 == null){
                            if(debug) System.out.println("continuing");
                            continue;
                        }
                        matchCount++;
                        if(debug) {
                            System.out.println("we have found " + matchCount + " matches");
                        }
                        if (matchCount == 4) {
                            return true;
                        }
                        for(int i = 0; i < 2; i++){
                            mahjongArr[index3[i]].checked = true;
                        }
                        if(checkArr(index3, mahjongArr)){
                            return true;
                        }
                        if(debug) {
                            System.out.println("failed, reducing matchCount");
                        }
                        for(int i = 0; i < 2; i++){
                            mahjongArr[index3[i]].checked = false;
                        }
                        matchCount--;
                    }
                }
                a.checked = false;
            }
        }
//        for(int i : index){
//            mahjongArr[i].checked = false;
//        }
        return false;
    }

    private static int[] find3Pair(Mahjong a, Mahjong[] mahjongArr){
        int[] pairIndex = new int[2];
        int count = 0;
        for(int i = 0; i < 14; i++){
            if(!mahjongArr[i].checked && mahjongArr[i].equal(a)){
                pairIndex[count] = i;
                count++;
            }
            if(count == 2){
                if(debug) {
                    System.out.println("found three pair for " + a.print());
                }
                return pairIndex;
            }
        }
        return null;
    }

    private static ArrayList<int[]> find3Series(Mahjong a, Mahjong[] mahjongArr){
        ArrayList<int[]> seriesIndex = new ArrayList<>();

        for(int i = 0; i < 14; i++){
            Mahjong b = mahjongArr[i];
            if(b.inSeries(a) && !b.checked){
                for(int j = i; j < 14; j++){
                    Mahjong c = mahjongArr[j];
                    if(c.inSeries(b) && !c.equal(a) && !c.checked){
                        if(debug) {
                            System.out.println("found three series for " + a.print() + b.print() + c.print());
                        }
                        seriesIndex.add(new int[]{i,j});
                    }
                    if(c.inSeries(a) && !c.equal(b) && !c.checked){
                        if(debug) {
                            System.out.println("found three series for " + a.print() + b.print() + c.print());
                        }
                        seriesIndex.add(new int[]{i,j});
                    }
                }
            }
        }
        if(debug && seriesIndex.size() == 0){
            System.out.println("can't find series for " + a.print());
        }
        return seriesIndex;
    }


    private static ArrayList<int[]> findPairIndex(Mahjong[] mahjongArr){
        ArrayList<int[]> pairIndex = new ArrayList<int[]>();
        for(int i = 0; i < 14; i++){
            for(int j = i + 1; j < 14; j++){
                if(mahjongArr[i].equal(mahjongArr[j])){
                    pairIndex.add(new int[]{i, j});
                }
            }
        }
        return pairIndex;
    }

    private static Mahjong[] convertMahjongArray(String str){
        Mahjong[] mahjongArr = new Mahjong[14];
        for(int i = 0; i < 14; i++){
            mahjongArr[i] = new Mahjong(str.charAt(2 * i),str.charAt(2 * i + 1));
        }
        return mahjongArr;
    }

    private static boolean checkAllNull(ArrayList<int[]> indexArr){
        for(int[] a : indexArr){
            if(a != null){
                return false;
            }
        }
        return true;
    }
}
