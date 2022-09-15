package Week2;
import java.util.Scanner;

public class Week2_B_LT {

    private static int[] array;

    private static int searchSmaller(int num, int l, int r) {
        if (num <= array[l]) {
            return l - 1;
        }

        while (l <= r) {
            int mid = l + (r - l) / 2 + (r - l) % 2 ;
            //System.out.println(l + " " + r + " " + mid);
            if (array[mid] < num) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    private static int searchSmaller(int num) {
        return searchSmaller(num, 0, array.length - 1);
    }

    private static int searchLarger(int num, int l, int r) {
        if (num >= array[r]) {
            return r + 1;
        }

        while (l <= r) {
            int mid = l + (r - l) / 2;
            //System.out.println(l + " " + r + " " + mid);
            if (array[mid] > num) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private static int searchLarger(int num) {
        return searchLarger(num, 0, array.length - 1);
    }

    public static long search(int sum) {
        int smallestB = sum / 3 + sum % 3 == 0 ? 0 : 1;
        int largestB = (sum - array[0]) / 2;
        int bMin = Math.max(searchSmaller(smallestB) + 1, 1);
        int bMax = Math.min(searchLarger(largestB) - 1, array.length - 2);
        long count = 0;
        for (int b = bMin; b <= bMax; ++b) {
            for (int a = 0; a < b; ++a) {
                Debug.println(array[a] + " at a = " + a + ", " + array[b] + " at b = " + b);
                int requiredNum = sum - array[a] - array[b];
                int pairs = searchLarger(requiredNum, b + 1, array.length - 1)
                        - searchSmaller(requiredNum, b + 1, array.length - 1) - 1;
                count += pairs;
                if (pairs > 0)
                    Debug.println("find " + pairs + " pairs");
            }
        }
        return count;
    }

    public static void main(String[] args) {
        //Debug.isOn = true;
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int sum = input.nextInt();

        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = input.nextInt();
        }

        System.out.println(search(sum));
    }

    private static class Debug {
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
}