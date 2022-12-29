package Week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
public class Week13_D_LT {
    public static boolean[] execute(Integer[] degrees) {
        boolean[] result = new boolean[3];
        Arrays.fill(result, true);
        Arrays.sort(degrees, Comparator.reverseOrder());
        long[] suffixSum = new long[degrees.length];
        suffixSum[degrees.length - 1] = degrees[degrees.length - 1];
        for (int i = degrees.length - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + degrees[i];
        }
        //not a graph
        if (suffixSum[0] % 2 != 0) {
            Arrays.fill(result, false);
            return result;
        }

        if (degrees.length == 1) {
            result[1] = result[2] = degrees[0] == 0;
            return result;
        }

        //check single graph
        long requirement = 0;
        int k = degrees.length - 1;
        for (int i = 0; i < degrees.length; i++) {
            requirement += degrees[i];
            while (k > i && degrees[k] < i + 1) {
                --k;
            }
            long provided = (long) i * (i + 1);
            if (Math.max(i, k) < degrees.length - 1) {
                provided += suffixSum[Math.max(i, k) + 1];
            }
            if (k > i) {
                provided += (long) (k - i) * (i + 1);
            }
            if (provided < requirement) {
                result[1] = false;
                result[2] = false;
                return result;
            }
        }

        //check tree
        if (suffixSum[0] != 2L * degrees.length - 2 || degrees[degrees.length - 1] == 0) {
            result[2] = false;
        }
        return result;
    }

    public static void main(String[] args) {
        QReader reader = new QReader(System.in);
        int n = reader.nextInt();
        Integer[] degrees = new Integer[n];
        for (int i = 0; i < n; i++) {
            degrees[i] = reader.nextInt();
        }
        boolean[] result = execute(degrees);
        for (int i = 0; i < 3; i++) {
            System.out.println(result[i] ? "YES" : "NO");
        }
    }


    private static class QReader {
        private final BufferedReader reader;
        private StringTokenizer tokenizer = new StringTokenizer("");
        QReader(InputStream inputStream) {
            reader = new BufferedReader(new InputStreamReader(inputStream));
        }

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
}
