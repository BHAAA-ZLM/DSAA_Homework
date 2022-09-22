package Week3;

import java.util.Scanner;

public class Week4_D_00 {
    private static final Scanner input = new Scanner(System.in);

    private static void mergeSort(int[] a, int[] b, int left, int right){
        if(left < right){
            int mid = left + (right - left) / 2;
            mergeSort(a,b,left,mid);
            mergeSort(a,b,mid + 1,right);
            merge(a,b,left,mid+1,right);
        }
    }

    private static void merge(int[] a, int[] b, int left, int mid, int right){
        int leftEnd = mid - 1;
        int tmpPos = left;
        int numElemtns = right - left + 1;

        while(left <= leftEnd && mid <= right){
            if(a[left] - a[mid] >= 0)
                b[tmpPos++] = a[left++];
            else
                b[tmpPos++] = a[mid++];
        }

        while(left <= leftEnd){
            b[tmpPos++] = a[left++];
        }

        while(mid <= right){
            b[tmpPos++] = a[mid++];
        }

        for(int i = 0; i < numElemtns; i++, right--){
            a[right] = b[right];
        }
    }

    public static void main(String[] args) {
        int n = input.nextInt();
        int m = input.nextInt();
        int k = input.nextInt();
        Numbers[] numbers = new Numbers[n];
        for(int i = 0; i < n; i++){
            numbers[i] = new Numbers(input.nextInt());
        }
        System.out.println(check(numbers,m,k));
    }

    private static class Numbers {
        int[] digits;
        int positive;
        int value;
        int maxDigit;
        int maxDigitIndex = 0;
        int left = 0;

        Numbers(int a){
            //take down the original value of the number
            value = a;

            //determine the sign of the number for future swapping
            if(a > 0)
                positive = 1;
            else {
                a = -a;
                positive = -1;
            }

            //convert the string to an array and at the same time take down the largest number
            String str = Integer.toString(a);
            maxDigit = positive * (str.charAt(0) - '0');
            digits = new int[str.length()];
            for(int i = 0; i < str.length(); i++){
                digits[i] = str.charAt(i) - '0';
                if(positive * digits[i] > maxDigit){
                    maxDigit = positive * digits[i];
                    maxDigitIndex = i;
                }
            }
        }

        boolean exchange(){
            while(digits[left] == positive * maxDigit){
                left++;
                if(left == digits.length - 1){
                    return false;
                }
                maxDigit = positive * digits[left];
                for(int i = left; i < digits.length; i++){
                    if(positive * digits[i] > maxDigit){
                        maxDigit = positive * digits[i];
                        maxDigitIndex = i;
                    }
                }
            }

            int temp = digits[left];
            digits[left] = digits[maxDigitIndex];
            digits[maxDigitIndex] = temp;
            return true;
        }

        int toValue(){
            int numericalValue = 0;
            for(int i = 0; i < digits.length; i++){
                numericalValue += digits[i] * Math.pow(10,digits.length - i - 1);
            }
            return positive * numericalValue;
        }

    }

    private static long check(Numbers[] numbers,int m,int k){
        int scoreCount = 0;
        long Sum = 0;
        for(Numbers n : numbers){
            scoreCount += n.digits.length;
            Sum += n.value;
        }
        int[] score = new int[scoreCount];

        int i = 0;
        for(Numbers n : numbers){
            while(n.exchange()) {
                score[i++] = n.toValue() - n.value;
                n.value = n.toValue();
            }
        }

        int[] b = new int[score.length];
        mergeSort(score,b,0, score.length-1);

        int j = 0;
        while(j < m && score[j] > k){
            Sum += score[j];
            j++;
        }
        Sum -= (long)j * (long)k;
        return Sum;
    }
}
