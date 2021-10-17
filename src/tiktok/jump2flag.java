package tiktok;

public class jump2flag {
    public static int jumps(int flagHeight, int bigJump) {
        // Write your code here
//        System.out.println(flagHeight);
//        System.out.println(bigJump);
        if(bigJump ==  flagHeight) return 1;
        if(bigJump >  flagHeight) return flagHeight;
        int[] dp = new int[flagHeight + 1];
        for(int i = 1; i <= flagHeight; i++){
            if(i - bigJump < 0){
                dp[i] = dp[i - 1] + 1;
            }else if(bigJump > (flagHeight - i + 1)){
                return dp[i - 1] + flagHeight - i + 1;
            }else{
                dp[i] = Math.min(dp[i - 1], dp[i - bigJump]) + 1;
            }
        }
        return dp[flagHeight];
    }

    public static void main(String[] args) {
//        System.out.println(new jump2flag().jumps(3, 2));
//        System.out.println(new jump2flag().jumps(458777924, 7237710));
//        10039966
        System.out.println(new jump2flag().jumps(458777924, 7237710));
//        130935451
        System.out.println(new jump2flag().jumps(823564441, 115438166));
//        116297115
        System.out.println(new jump2flag().jumps(784484493, 74243043));
//        27989103
        System.out.println(new jump2flag().jumps(441282328, 16531730));
    }
}
