package amazon;

/*
这个方法就叫做埃氏筛，想不出来直接看题解即可
https://leetcode-cn.com/problems/count-primes/solution/kuai-lai-miao-dong-shai-zhi-shu-by-sweetiee/
* */

import java.util.Arrays;

public class lc204 {
    public int countPrimes(int n) {
        if(n < 3) return 0;
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for(int i = 2; i * i < n; i++){
//            下面这句话很重要，埃氏筛其实是用质数进行筛选，
//            虽然没有那个if判断也可以过，但是效率低很多
            if(isPrime[i]){
                for (int k = i * i; k < n; k += i) {
                    isPrime[k] = false;
                }
            }
        }
        int res = 0;
        for(boolean prime : isPrime){
            if(prime) res++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new lc204().countPrimes(10));
    }
}
