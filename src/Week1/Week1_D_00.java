package Week1;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Week1_D_00 {
    private static final Scanner input = new Scanner(System.in);

    private static class Mahjong {
        public int num;
        public char suit;
        public boolean checked = false;

        public Mahjong(char num, char suit){
            this.num = Character.getNumericValue(num);
            this.suit = suit;
        }

        public Mahjong(String str){
            this.num = Character.getNumericValue(str.charAt(0));
            this.suit = str.charAt(1);
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

        public boolean searchEqual(ArrayList<Mahjong> mahjongArray){
            this.checked = true;
            for(Mahjong a : mahjongArray){
                a.checked = true;
                if(this.equal(a)){
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        int times = input.nextInt();

        for(int i = 0; i < times; i++) {
            if(checkMahjong(input.nextLine())){
                System.out.println("Blessing of Heaven");
            }else{
                System.out.println("Bad luck");
            }
        }
//        Mahjong a = new Mahjong(input.next());
//        Mahjong b = new Mahjong(input.next());
//        System.out.println(a.inSeries(b));
    }

    public static boolean checkMahjong(String mahjongStr){
        ArrayList<Mahjong> mahjongArray = createMahjong(mahjongStr);
        for(Mahjong a : mahjongArray){
            if(a.searchEqual(mahjongArray)){

            }
        }
        return false;
    }

    /**
     * Create the Mahjong array that stores all your mahjong
     * @param MahjongStr
     * @return
     */
    public static ArrayList<Mahjong> createMahjong(String MahjongStr){
        ArrayList<Mahjong> MahjongArray = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            MahjongArray.add(new
                    Mahjong(MahjongStr.charAt(2 * i), MahjongStr.charAt(2 * i + 1))
            );
        }
        return MahjongArray;
    }
}

/*
* find all the pairs in the mahjong
*   finding all pairs:
*       for each mahjong, look for it's replicate
*           if found, mark both as found and start choosing the rest
*           if not found, end
*
* choose one pair of the mahjong, separate it from the rest 12 mahjong (by marking them as found)
* if (can find suitable AAA or BCD mahjongs in the rest 12 mahjong) print Hule
*
*   finding the suitable AAA or BCD:
*       if the # of each suit mod 3 = 0
*           choose one suit
*           if(number forms AAA or BCD)
*           choose another suit
*           if all suit satisfy
*               found
*           if all suit cannot satisfy
*               end
*       else end
*
* else choose another pair of mahjong
* if all the pairs cannot satisfy the requirement, print bad luck
* */