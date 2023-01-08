package Week1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Week1_D_01 {

    public static final Scanner input = new Scanner(System.in);

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
            if(checkMahjong(input.next())){
                System.out.println("Blessing of heaven");
            }else{
                System.out.println("Bad luck");
            }
        }
    }

    private static boolean checkMahjong(String str){
        Mahjong[] mahjongArr = convertMahjongArray(str);
        for(Mahjong a : mahjongArr){
            a.checked = true;
            if(findPair(a, mahjongArr) && checkArr(mahjongArr)){
                return true;
            }

            //make sure all the mahjong are checked in the new loop
            for(Mahjong b :mahjongArr){
                b.checked = false;
            }
        }
        return false;
    }

    private static boolean findPair(Mahjong a,Mahjong[] mahjongArr){
        for(Mahjong b : mahjongArr){
            if(!b.checked && b.equal(a)){
                b.checked = true;
                System.out.println("found pair:" + a.print()+ b.print());
                return true;
            }
        }
        return false;
    }

    private static boolean find3Pair(Mahjong a, Mahjong[] mahjongArr){
        ArrayList<Mahjong> checkedPair = new ArrayList<>();
        System.out.println("checking 3 pairs for " + a.print());
        for(Mahjong b : mahjongArr) {
            if (!b.checked && b.equal(a)) {
                checkedPair.add(b);
            }
        }
        if(checkedPair.size() >= 2){
            matchCount++;
            System.out.println("found 3 pair for: " + a.print());
            for(Mahjong b : checkedPair) {
                b.checked = true;
            }
            return true;
        }else{
            System.out.println("can't find 3 pair for " + a.print());
            return false;
        }
    }

    private static boolean findSeries(Mahjong a, Mahjong[] mahjongArr){
        if(a.suit == 'z'){
            return false;
        }
        for(Mahjong b: mahjongArr){
//            for(Mahjong c:mahjongArr){
//                if(a.inSeries(b) && a.inSeries(c) && !b.equal(c)){
//                    return true;
//                }
//                if(b.inSeries(a) && b.inSeries(c) && !a.equal(c)){
//                    return true;
//                }
//                if(c.inSeries(a) && c.inSeries(b) && !a.equal(b)){
//                    return true;
//                }
//            }
            if(b.inSeries(a) && !b.checked){
                for(Mahjong c : mahjongArr){
                    if(c.inSeries(b) && !c.equal(a) && !c.checked){
                        b.checked = true;
                        c.checked = true;
                        System.out.println("found series:" + a.print()+b.print()+c.print());
                        matchCount++;
                        return true;
                    }
                    if(c.inSeries(a) && !c.equal(b) && !c.checked){
                        b.checked = true;
                        c.checked = true;
                        System.out.println("found series:" + a.print()+b.print()+c.print());
                        matchCount++;
                        return true;
                    }
                }
            }
        }
        System.out.println("can't find any series");
        return false;
    }

    public static int matchCount = 0;
    private static boolean checkArr(Mahjong[] mahjongArr){
        if(matchCount == 4){
            return true;
        }
        for(Mahjong a : mahjongArr){
            if(!a.checked) {
                a.checked = true;
                if (find3Pair(a, mahjongArr) || findSeries(a, mahjongArr)) {
                    checkArr(mahjongArr);
                }else{
                    return false;
                }
            }
        }
        return false;
    }

    private static Mahjong[] convertMahjongArray(String str){
        Mahjong[] mahjongArr = new Mahjong[14];
        for(int i = 0; i < 14; i++){
            mahjongArr[i] = new Mahjong(str.charAt(2 * i),str.charAt(2 * i + 1));
        }
        return mahjongArr;
    }
}
