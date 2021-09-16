package amazon;

public class lc509 {
    public int fib(int n) {
        int[] dp = new int[31];
        dp[0] = 0;
        dp[1] = 1;
        if(n < 2) return dp[n];
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new lc509().fib(2));
        System.out.println(new lc509().fib(3));
        System.out.println(new lc509().fib(4));
    }
}
