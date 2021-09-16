package amazon;

public class lc70 {
    public int climbStairs(int n) {
        if(n < 3) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new lc70().climbStairs(3));
        System.out.println(new lc70().climbStairs(1));
    }
}
