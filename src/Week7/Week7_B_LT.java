package Week7;

import java.util.Arrays;
import java.util.Scanner;

public class Week7_B_LT {
    private static final int HASH = 141;

    private static class ArrayList {
        private int size;
        public int size() {
            return size;
        }

        private final int[] data;
        public ArrayList(int capacity) {
            data = new int[capacity];
        }

        public void add(int code) {
            data[size++] = code;
        }

        public int get(int index) {
            return data[index];
        }

        public int[] getData() {
            return data;
        }
    }

    private interface Transform {
        int toIndex(int code);
    }
    private static class OddTrans implements Transform {
        @Override
        public int toIndex(int code) {
            return code >= 0 ? 2 * code + 1 : -2;
        }
    }
    private static class EvenTrans implements Transform {
        @Override
        public int toIndex(int code) {
            return 2 * code;
        }
    }

    private static boolean searchFor(String s1, String s2, int length) {
        int power = 1;
        for (int i = 0; i < length - 1; i++) {
            power *= HASH;
        }

        ArrayList list1 = getPalHashCode(s1, length, power);
        ArrayList list2 = getPalHashCode(s2, length, power);

        Arrays.sort(list1.getData(), 0, list1.size());
        Arrays.sort(list2.getData(), 0, list2.size());
//        quickSort(list1.getData(), 0, list1.size() - 1);
//        quickSort(list2.getData(), 0, list2.size() - 1);

        int i = 0;
        int j = 0;
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) == list2.get(j)) {
                return true;
            } else if (list1.get(i) < list2.get(j)) {
                ++i;
            } else {
                ++j;
            }
        }
        return false;
    }

    public static void quickSort(int[] array, int l, int r) {
        if (l >= r) {
            return;
        }

        int pivot = array[l];
        int i = l;
        int j = r;
        while (i < j) {
            while (i < j && array[j] >= pivot) {
                --j;
            }
            if (i < j) {
                array[i++] = array[j];
            }
            while (i < j && array[i] < pivot) {
                ++i;
            }
            if (i < j) {
                array[j--] = array[i];
            }
        }
        array[i] = pivot;
        quickSort(array, l, i - 1);
        quickSort(array, i + 1, r);
    }

    private static ArrayList getPalHashCode(String str, int length, int power) {
        int[] hashCodes = new int[str.length() - length + 1];
        //get the first hashcode
        for (int i = 0; i < length; i++) {
            hashCodes[0] = hashCodes[0] * HASH + str.charAt(i) - 'a';
        }
        //get the rest hashcode
        for (int i = 0; i < str.length() - length; i++) {
            hashCodes[i + 1] = (hashCodes[i] - power * (str.charAt(i) - 'a')) * HASH
                    + str.charAt(i + length) - 'a';
        }

        ArrayList list = new ArrayList(str.length() - length + 1);

        //get the backward hashcode
        int backCode = 0;
        for (int i = str.length() - 1; i >= str.length() - length; i--) {
            backCode = backCode * HASH + str.charAt(i) - 'a';
        }
        if (backCode == hashCodes[str.length() - length]) {
            list.add(backCode);
        }

        //search for the rest hashcode
        for (int i = str.length() - length - 1; i >= 0; --i) {
            backCode = (backCode - power * (str.charAt(i + length) - 'a')) * HASH
                    + str.charAt(i) - 'a';
            if (backCode == hashCodes[i]) {
                list.add(backCode);
            }
        }
        return list;
    }

    private static int binarySearch(String s1, String s2, int size, Transform trans) {
        int l = 0;
        int r = size;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (searchFor(s1, s2, trans.toIndex(mid) + 1)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return trans.toIndex(r) + 1;
    }

    public static int execute(String s1, String s2) {
        int min = Math.min(s1.length(), s2.length());
        int odd = binarySearch(s1, s2, min / 2 - 1, new OddTrans());
        int even = binarySearch(s1, s2, (min - 1) / 2, new EvenTrans());
        return Math.max(odd, even);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.next();
        String s2 = in.next();
        System.out.println(execute(s1, s2));
    }
}
