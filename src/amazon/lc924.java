package amazon;

/*
并查集类似题目：684/778/785/924
并查集已经做过很多次了，每次其实都是变形，我的思路和这个题解最像：
https://leetcode-cn.com/problems/minimize-malware-spread/solution/bing-cha-ji-by-keytian-yt15/

这道题目排查了一个多小时，最后发现并查集有个地方写错了，所以如果出了问题，要看下并查集代码那里
**/

import java.util.Arrays;

public class lc924 {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int gLen = graph.length;
//        if(gLen == 1) return 0;
        unionFind uf = new unionFind(gLen);
        for(int i = 0; i < gLen; i++){
            for(int k = i + 1; k < gLen; k++){
                if(graph[i][k] == 1) uf.merge(i, k);
            }
        }
//        要注意initial不一定是按照顺序的
        Arrays.sort(initial);
        int iLen = initial.length, unionNum = -1, res = -1;
//        这里是判断每个爹出现的次数
        int[] dadUnion = new int[gLen];
        for(int i = 0; i < gLen; i++) dadUnion[uf.find(i)]++;
        /*
        这里是判断被污染的区域的爹，如果initial里面有多个结点共用一个爹
        那就说明减去其中任何一个，其实都只能减少一个感染，是等价的，于是就挑序号最小的就行

        如果每个initial里面的节点都是不同的爹，也就是说initialUnion里面最大值为1
        那就需要看他们那个感染的范围最大，这样能减少感染的范围就最小
        * */
        int[] initialUnion = new int[gLen];
        for(int i = 0; i < iLen; i++) initialUnion[uf.find(initial[i])]++;

        for(int i = 0; i < iLen; i++){
            int iDad = uf.find(initial[i]);
//            这个代表在initial里面的元素，他们对应的dad出现的次数
            int curRes = initialUnion[iDad];
//            这个代表initial里面的元素所连接的整块区域的大小，这块面积越大，
//            那么去掉initial这个节点后能够被洗白的区域也就越大
            int totalArea = dadUnion[iDad];
            if(curRes > 1) {
                if(unionNum < 0) {
                    res = initial[i];
                    unionNum = 0;
                }
            }else {
                if(totalArea > unionNum) {
                    res = initial[i];
                    unionNum = totalArea;
                }
            }
        }
        return res;
    }

    public class unionFind{
        private int[] union;
        public unionFind(int n){
            union = new int[n];
            for(int i = 0; i < n; i++){
                union[i] = i;
            }
        }

        public void merge(int a, int b){
            int aDad = find(a);
            int bDad = find(b);
//            其实这个if不要也可以
            if(aDad != bDad) union[aDad] = union[bDad];
        }

//        上面这个代码在leetcode里面效率很差，
//        但是用下面那个会好很多，下次再做的时候要知道
//        但是时间复杂度是一样的
        public int find(int a){
            while (union[a] != a){
                a = union[a];
            }
            return a;
        }
//        这个代码的效率快很多
//        public int find(int a){
//            if(union[a] != a){
//                union[a] = find(union[a]);
//            }
//            return union[a];
//        }
    }

    public static void main(String[] args) {
        int[][] test = {{1,0,0,0},{0,1,0,0},{0,0,1,1},{0,0,1,1}};
        int[] test1 = {3,1};
        System.out.println("正确答案为 3, 代码答案为 ： " +new lc924().minMalwareSpread(test, test1));
    }
}
