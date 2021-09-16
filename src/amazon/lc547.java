package amazon;

/*
下面这个题解最符合我的思路，但是并查集还是用的自己的模板
https://leetcode-cn.com/problems/number-of-provinces/solution/biao-zhun-de-bing-cha-ji-ti-mu-by-fang-t-fmea/
* */

public class lc547 {
    private int[] union;
    public int findCircleNum(int[][] isConnected) {
        int cLen = isConnected.length;
        if(cLen == 1) return cLen;
        union = new int[cLen];
        for(int i = 0; i < cLen; i++) union[i] = i;
        for(int i = 0; i < cLen; i++){
            for(int k = 0; k < cLen; k++){
                if(i == k) continue;
                if(isConnected[i][k] == 1){
                    merge(i, k);
                }
//                System.out.println("i = " + i + " k = " + k);
            }
        }
        int res = 0;
        for(int i = 0; i < cLen; i++){
            if(union[i] == i) res++;
        }
        return res;
    }

    private void merge(int a, int b){
        int Dada = findDad(a);
        int Dadb = findDad(b);
//        注意下面这个写法，debug很久，别搞错了！
//        if(Dada != Dadb) union[a] = b;
        if(Dada != Dadb) union[Dadb] = Dada;
    }

    private int findDad(int son){
        while (union[son] != son){
            son = findDad(union[son]);
        }
        return union[son];
    }

    public static void main(String[] args) {
        int[][] test = {{1,0,0,1}, {0,1,1,1}, {1,1,1,0}, {0,0,0,1}};
        System.out.println(new lc547().findCircleNum(test));
    }
}
