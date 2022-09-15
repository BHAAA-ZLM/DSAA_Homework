package Week1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Week1_D_LT {
    static class Mahjong {
        public final int value;
        public final char suit;

        Mahjong(String card) {
            this.value = Integer.parseInt(card.substring(0,1));
            this.suit = card.charAt(1);
        }

        public boolean isIdenticalWith(Mahjong other) {
            return suit == other.suit && value == other.value;
        }

        public boolean canStraightWith(Mahjong other) {
            return suit != 'z' && suit == other.suit;
        }

        @Override
        public String toString() {
            return "" + value + suit;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Mahjong mahjong = (Mahjong) o;

            if (value != mahjong.value) return false;
            return suit == mahjong.suit;
        }

        @Override
        public int hashCode() {
            int result = value;
            result = 31 * result + (int) suit;
            return result;
        }
    }

    static class Debug {
        public static boolean isOn;
        public static void println(String s) {
            if (isOn) {
                System.out.println(s);
            }
        }

        public static void print(String s) {
            if (isOn) {
                System.out.print(s);
            }
        }
    }

    private static final Mahjong[] tiles = new Mahjong[14];
    public static final boolean[] isUsed = new boolean[14];

    /**
     * @param index Suggest that index is "isUsed"
     */
    private static List<int[]> searchCombs(int index) {
        ArrayList<int[]> result = new ArrayList<>();
        Mahjong current = tiles[index];
        //search for identical
        int[] identicalComb = searchNext(
                mahjong -> mahjong.isIdenticalWith(current),
                mahjong -> mahjong.isIdenticalWith(current)
        );

        if (identicalComb != null) {
            Debug.println("Possible comb: " + current + tiles[identicalComb[0]] + tiles[identicalComb[1]]);
            result.add(identicalComb);
        }

        //search for straight
        int[] firstComb = searchNext(
                mahjong -> mahjong.canStraightWith(current) && mahjong.value == current.value + 1,
                mahjong -> mahjong.canStraightWith(current) && mahjong.value == current.value + 2
        );
        int[] secondComb = searchNext(
                mahjong -> mahjong.canStraightWith(current) && mahjong.value == current.value - 1,
                mahjong -> mahjong.canStraightWith(current) && mahjong.value == current.value + 1
        );
        int[] lastComb = searchNext(
                mahjong -> mahjong.canStraightWith(current) && mahjong.value == current.value - 1,
                mahjong -> mahjong.canStraightWith(current) && mahjong.value == current.value - 2
        );

        if (firstComb != null) {
            Debug.println("Possible comb: " + current + tiles[firstComb[0]] + tiles[firstComb[1]]);
            result.add(firstComb);
        }
        if (secondComb != null) {
            Debug.println("Possible comb: " + current + tiles[secondComb[0]] + tiles[secondComb[1]]);
            result.add(secondComb);
        }
        if (lastComb != null) {
            Debug.println("Possible comb: " + current + tiles[lastComb[0]] + tiles[lastComb[1]]);
            result.add(lastComb);
        }

        return result;
    }

    public static int[] searchNext(Function<Mahjong, Boolean> c1, Function<Mahjong, Boolean> c2) {
        int[] result = new int[2];
        boolean hasFound = false;
        int m1, m2;
        if ((m1 = searchNext(c1)) != -1) {
            isUsed[m1] = true;
            if ((m2 = searchNext(c2)) != -1) {
                result[0] = m1;
                result[1] = m2;
                hasFound = true;
            }
            isUsed[m1] = false;
        }

        return hasFound ? result : null;
    }

    public static int searchNext(Function<Mahjong, Boolean> condition) {
        for (int i = 0; i < 14; ++i) {
            if (!isUsed[i] && condition.apply(tiles[i])) {
                return i;
            }
        }
        return -1;
    }

    private static int nextUnused() {
        return searchNext(m -> true);
    }

    private static boolean search() {
        ArrayList<Mahjong> hasSearched = new ArrayList<>();
        for (int i = 0; i < 14; ++i) {
            Mahjong m1 = tiles[i];
            if (hasSearched.contains(m1))
                continue;

            isUsed[i] = true;
            int m2 = searchNext(mahjong -> mahjong.isIdenticalWith(m1));
            if (m2 != -1) {
                hasSearched.add(tiles[i]);
                isUsed[m2] = true;
                //has found a pair
                Debug.println("Found a pair: " + m1 + tiles[m2]);

                if (searchRecursion(0))
                    return true;
                isUsed[m2] = false;
            }
            isUsed[i] = false;
        }
        return false;
    }


    private static boolean searchRecursion(int combCount) {
        Debug.println("SearchRecursion: " + combCount);
        for (int i = 0; i < 14; ++i) {
            if (isUsed[i])
                Debug.print(tiles[i] + " ");
            else
                Debug.print("__ ");
        }
        Debug.println("");

        if (combCount >= 4) {
            return true;
        }

        int m1 = nextUnused();
        boolean isWin = false;
        isUsed[m1] = true;
        for (int[] comb : searchCombs(m1)) {
            isUsed[comb[0]] = true;
            isUsed[comb[1]] = true;
            isWin = searchRecursion(combCount + 1);
            isUsed[comb[0]] = false;
            isUsed[comb[1]] = false;

            if (isWin)
                break;
        }
        isUsed[m1] = false;
        return isWin;
    }

    public static void main(String[] args) {
        Debug.isOn = false;

        Scanner input = new Scanner(System.in);
        int times = input.nextInt();
        for (int t = 0; t < times; ++t) {
            String tilesStr = input.next();
            for (int i = 0; i < 14; ++i) {
                tiles[i] = new Mahjong(tilesStr.substring(i * 2, i * 2 + 2));
                isUsed[i] = false;
            }
            if (search())
                System.out.println("Blessing of Heaven");
            else
                System.out.println("Bad luck");
        }
    }
}
