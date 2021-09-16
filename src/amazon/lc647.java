package amazon;

public class lc647 {
    public int countSubstrings(String s) {
        int sLen = s.length(), res = 0;
        if(s == null || sLen == 0) return 0;
        char[] sChar = s.toCharArray();
        boolean[][] dp = new boolean[sLen][sLen];
        for(int k = 0; k < sLen; k++){
            for(int i = 0; i <= k; i++){
                if(k - i < 2 && sChar[i] == sChar[k]) {
                    dp[i][k] = true;
                    res++;
                } else {
                    if(sChar[i] == sChar[k] && dp[i + 1][k - 1]){
                        dp[i][k] = true;
                        res++;
                    }else continue;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new lc647().countSubstrings("aaa"));
        System.out.println(new lc647().countSubstrings("abaa"));
    }
}
