package amazon;

/*
并查集类似题目：684/778/785/924

下面这个把并查集的概念讲得很清楚了，但是没有java代码，所以主要看思路
一开始可能会看起来比较晕，但是多看几遍就懂了：
https://leetcode-cn.com/problems/redundant-connection/solution/tong-su-jiang-jie-bing-cha-ji-bang-zhu-xiao-bai-ku/

代码实现看这个，虽然没用path conpression，但是很好懂：
https://leetcode-cn.com/problems/redundant-connection/solution/xiao-bai-de-bing-cha-ji-si-lu-by-zackqf/

然后在做的时候要注意到并查集的时间复杂度是有点特殊的，需要特别注意：
时间复杂度：O(NlogN)，其中 N 是图中的节点个数。需要遍历图中的 N 条边，对于每条边，
需要对两个节点查找祖先，如果两个节点的祖先不同则需要进行合并，需要进行 2 次查找和最多 1 次合并。
一共需要进行 2N 次查找和最多 N 次合并，因此总时间复杂度是O(2NlogN)=O(NlogN)。
这里的并查集使用了路径压缩，但是没有使用按秩合并，最坏情况下的时间复杂度是 O(NlogN)，
平均情况下的时间复杂度依然是 O(Nα(N))，其中 α 为阿克曼函数的反函数，α(N) 可以认为是一个很小的常数。
* */

public class lc684 {
    private int[] union = new int[1001];
    public int[] findRedundantConnection(int[][] edges) {
        int[] res = new int[2];
        for(int i = 0; i < union.length; i++) union[i] = i;
        for(int[] edge : edges){
//            这样写是不对的，应该是用find，而不是用union
//            if(union[edge[0]] != union[edge[1]]){
            if(find(edge[0]) != find(edge[1])){
                merge(edge[0], edge[1]);
            }else {
                res[0] = edge[0];
                res[1] = edge[1];
            }
        }
        return res;
    }

    private int find(int a){
        while (union[a] != a){
            a = union[a];
        }
        return a;
    }

    private void merge(int x, int y){
        int xDad = find(x);
        int yDad = find(y);
//        下面两个是等价的，没区别
//        union[xDad] = union[yDad];
        union[xDad] = yDad;
    }
}
