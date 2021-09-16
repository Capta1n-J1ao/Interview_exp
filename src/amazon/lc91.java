package amazon;

/*先看着这个视屏，讲的很清楚：
https://www.bilibili.com/video/BV1f54y1U7cb?from=search&seid=13382386100533317648
然后可以结合liweiwei的讲解：
https://leetcode-cn.com/problems/decode-ways/solution/dong-tai-gui-hua-java-python-by-liweiwei1419/
* */

public class lc91 {
//    public int numDecodings(String s) {
//        char[] sChar = s.toCharArray();
//        int sLen = sChar.length;
//        int[] dp = new int[sLen];
//        if(sChar[0] == '0') return 0;
//        if(sLen == 1 && sChar[0] != '0') return 1;
//        dp[0] = 1;
////        判断s[0 ~ 1]组成的数字能有几种可能
//        int first2 = 10 * (sChar[0] - '0')  + (sChar[1] - '0');
//        if(first2 > 26 && sChar[1] == '0') return 0;
//        else if(first2 == 10 || first2 == 20 || first2 > 26) dp[1] = 1;
//        else dp[1] = 2;
//        for(int i = 2; i < sLen; i++){
//            int curNum = sChar[i] - '0';
//            if(curNum != 0) dp[i] = dp[i - 1];
//            int twoDigits = 10 * (sChar[i - 1] - '0')  + sChar[i] - '0';
////            用第二个test case好好体会为什么下面这个是不对的
////            if(1 <= twoDigits && twoDigits <= 26) dp[i] += dp[i - 2];
//            if(10 <= twoDigits && twoDigits <= 26) dp[i] += dp[i - 2];
//        }
//        return dp[sLen - 1];
//    }

//    上面这个写法是基于直观的理解，但是可以看到在判断前两位的时候有很多复杂并且容易错的case
//    建议看下之前的写法，可以避免
//    当然在liweiwei里面最后一种最好，连i == 1的特判都不要，但是对于dp[0] = 1的理解并不是很方便
//    个人感觉其实就是硬凑的，为了让整个代码更加简洁
    public int numDecodings(String s) {
        int sLen = s.length();
        int[] dp = new int[sLen];
        char[] sChar = s.toCharArray();
        //记住这个是char型，所以是'0'而不是0！！！，一开始写错了
        //if(strChar[0] == 0) return 0;
        if(sChar[0] == '0') return 0;
        //if(s == "0") return 0;
        dp[0] = 1;
        for(int i = 1; i < sLen; i++){
            int curNum = sChar[i] - '0';
            if(curNum != 0) dp[i] = dp[i - 1];
            int twoDigits = 10 * (sChar[i - 1] - '0')  + sChar[i] - '0';
//            用第二个test case好好体会为什么下面这个是不对的
//            注意这个twodigits本来就是两位数
//            if(1 <= twoDigits && twoDigits <= 26) dp[i] += dp[i - 2];
            if(10 <= twoDigits && twoDigits <= 26) {
                if(i == 1) dp[i]++;
                else dp[i] += dp[i - 2];
            }
        }
        return dp[sLen - 1];
    }

    public static void main(String[] args) {
//        System.out.println('Z' - 'A');
//        String test = "10";
//        System.out.println("Correct Answer is 1, and code answer is : "  + new lc91().numDecodings(test));

        System.out.println("Correct Answer is 1, and code answer is : "  + new lc91().numDecodings("2101"));
        System.out.println("Correct Answer is 0, and code answer is : "  + new lc91().numDecodings("301"));
        System.out.println("Correct Answer is 1, and code answer is : "  + new lc91().numDecodings("27"));
    }
}
