package amazon;

import java.util.Arrays;

public class lc322 {
    public int coinChange(int[] coins, int amount) {
        if(amount == 0) return 0;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        for(int coin : coins){
            if(coin <= amount) dp[coin] = 1;
        }
        for(int i = 1; i <= amount; i++){
            if(dp[i] == 1) continue;
            int minCount = Integer.MAX_VALUE;
            for(int coin : coins){
                if(i - coin < 0 || dp[i - coin] == -1) continue;
                dp[i] = Math.min(minCount, dp[i - coin] + 1);
//                一开始一直debug不出，主要就是少了这句话，就是说minCount也是要一直更新的
                minCount = dp[i];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
//        int[] test = {1,2,5};
//        System.out.println(new lc322().coinChange(test, 11));

//        int[] test = {2,5,10,1};
//        System.out.println(new lc322().coinChange(test, 27));

        int[] test = {186,419,83,408};
        System.out.println(new lc322().coinChange(test, 6249));
    }
}
