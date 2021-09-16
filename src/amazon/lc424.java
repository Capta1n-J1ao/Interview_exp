package amazon;

/*
这道题目感觉是滑动窗口里面除了hard的题目里面最难得了，所以对于滑动窗口的做题顺序应该是：
3/209 -> 424 -> 76 -> 992
参考liweiwei的题解，视频说的很清楚：
https://leetcode-cn.com/problems/longest-repeating-character-replacement/solution/ti-huan-hou-de-zui-chang-zhong-fu-zi-fu-eaacp/
* */

public class lc424 {
    public int characterReplacement(String s, int k) {
        int sLen = s.length();
        if(sLen < 2) return sLen;
        int[] count = new int[128];
        char[] sChar = s.toCharArray();
        int left = 0, right = 0, maxCharCount = 0, res = 0;
        while (right < sLen){
            char rightChar = sChar[right];
            count[rightChar]++;
            maxCharCount = Math.max(maxCharCount, count[rightChar]);
            right++;
//            下面这里根据liweiwei的说法if和while都可以，但是为了模板一致性，还是使用while
            while (right - left > maxCharCount + k){
                char leftChar = sChar[left];
                count[leftChar]--;
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new lc424().characterReplacement("ABABBA", 1));
    }
}
