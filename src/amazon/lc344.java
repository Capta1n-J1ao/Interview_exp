package amazon;

/*
不要做了，没有意义
* */

public class lc344 {
    private char[] s;
    public void reverseString(char[] s) {
        this.s = s;
        if(s == null || s.length == 0) return;
        int sLen = s.length;
        int left = 0, right = sLen - 1;
        while (left < right){
            swap(left, right);
            left++;
            right--;
        }
        return;
    }

    private void swap(int l, int r){
        char temp = s[l];
        s[l] = s[r];
        s[r] = temp;
    }
}
