package Week7;

import java.io.*;
import java.util.StringTokenizer;

public class Week7_A_01 {
    public static final QReader in = new QReader();
    public static final QWriter out = new QWriter();

    public static void main(String[] args) {
        int T = in.nextInt();
        for(int t = 0; t < T; t++){
            String text = in.next();
            int n = in.nextInt();
            String[] cases = new String[n];
            for(int ni = 0; ni < n; ni++){
                cases[ni] = in.next();
            }
            out.print(colour(text,cases));
        }
        out.close();
    }

    public static String colour(String text, String[] cases){
        int left = 0, right = 0;
        StringBuilder sb = new StringBuilder();
        String tmp = "";
        int count = 0;

        while(right < text.length()){
            int tmpLeft = left;
            int tmpRight = right;
            while(tmpLeft <= right) {
                for (int caseIndex = 0; caseIndex < cases.length; caseIndex++) {
                    String str = cases[caseIndex];
                    if(tmpLeft + str.length() < tmpRight || tmpLeft + str.length() > text.length()) {
                        continue;
                    }
                    for (int i = 0; i < str.length(); i++) {
                        if (str.charAt(i) != text.charAt(tmpLeft + i)) {
                            break;
                        }
                        if (i == str.length() - 1) {
                            if(tmpLeft + str.length() > tmpRight) {
                                tmpRight = tmpLeft + str.length();
                            }
                            tmp = (caseIndex + 1) + " " + (tmpLeft + 1) + "\n";
                        }
                    }
                }
                tmpLeft++;
            }
            if(tmpRight == right){
                return "-1\n";
            }else {
                right = tmpRight;
                sb.append(tmp);
                count++;
            }
        }
        if(right > text.length()){
            return "-1\n";
        }else{
            return count + "\n" + sb;
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