package amazon;

/*
lc556和lc31极像，一起服用

int范围：
最小值：Integer.MIN_VALUE= -2147483648 （-2的31次方）
最大值：Integer.MAX_VALUE= 2147483647  （2的31次方-1）
* */

import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class lc556 {
    private char[] numChar;
    private int nLen;
    public int nextGreaterElement(int n) {
        String num = String.valueOf(n);
        numChar = num.toCharArray();
        nLen = numChar.length;
        for(int i = nLen - 1; i > 0; i--){
            if(numChar[i - 1] < numChar[i]){
                for(int k = nLen - 1; k >= i; k--){
                    if(numChar[k] > numChar[i - 1]){
                        swap(i - 1, k);
                        Arrays.sort(numChar, i, nLen);
                        String sNum = String.valueOf(numChar);
//                        System.out.println(isValid(sNum));
                        return isValid(sNum) ? Integer.valueOf(sNum) : -1;
                    }
                }
            }
        }
        return -1;
    }


    private void swap(int a, int b){
        char temp = numChar[a];
        numChar[a] = numChar[b];
        numChar[b] = temp;
    }

    private boolean isValid(String str){
        String temp = str.substring(0, nLen - 1);
        int temp1 = Integer.valueOf(temp), temp2 = Integer.valueOf(str.substring(nLen - 1));
        if(temp1 > 0) return (temp1 < Integer.MAX_VALUE / 10) || (temp1 == Integer.MAX_VALUE / 10 && temp2 <= 7);
        else return (temp1 > Integer.MIN_VALUE / 10) || (temp1 == Integer.MIN_VALUE / 10 && temp2 <= 8);
    }


    public static void main(String[] args) {
//        System.out.println(new lc556().nextGreaterElement(12));
        System.out.println(new lc556().nextGreaterElement(2147483486));
    }
}
