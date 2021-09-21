package drw;

public class oa3 {
    public int solution(int[] A, int S) {
        // write your code in Java SE 8
        if(A == null) return 0;
        int aLen = A.length;
        int res = 0;
        int[] sum = new int[aLen + 1];
        int[] aDup = new int[aLen + 1];
        sum[0] = 0;
        for(int i = 1; i <= aLen; i++){
            sum[i] = A[i - 1] + sum[i - 1];
        }
        aDup[0] = 0;
        for(int i = 1; i <= aLen; i++){
            aDup[i] = A[i - 1];
        }
        for(int i = 0; i <= aLen; i++){
            for(int k = i; k <= aLen; k++){
                if(i == 0 && k == 0) continue;
                else if(i != k && (sum[k] - sum[i])== S * (k - i)){
                    res++;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
//        int[] test = {2,1,3};
//        System.out.println(new oa3().solution(test, 2));

        int[] test = {0,4,3,-1};
        System.out.println(new oa3().solution(test, 2));
    }
}
