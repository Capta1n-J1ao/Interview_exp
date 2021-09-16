package amazon;

/*
一开始忘记这道题目怎么做了，所以必须要先复习lc210和lc207，再来做就会简单很多
然后可以看以下题解，虽然自己写的代码，但是题解里面还引入了出度的概念，感觉更加简洁，可以学习：
https://leetcode-cn.com/problems/find-the-town-judge/solution/tu-ru-du-chu-du-by-qing-mo-9/
* */

public class lc997 {
    public int findJudge(int N, int[][] trust) {
        if(N < 1) return -1;
        int[] inDegree = new int[N + 1];
        for(int[] t : trust){
            inDegree[t[1]]++;
        }
        int count = 0, tempRes = -1;
        for(int i = 1; i <= N; i++){
            if(inDegree[i] == (N - 1)){
                count++;
                if(count > 1) return -1;
                tempRes = i;
            }
        }
        for(int[] t : trust){
            if(t[0] == tempRes) return -1;
        }
        return tempRes;
    }
}
