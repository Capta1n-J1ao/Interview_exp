package amazon;

/*
并查集类似题目：684/778/785/924

最容易写的就是BFS + 二分法，liweiwei的题解说的很清楚了：
https://leetcode-cn.com/problems/swim-in-rising-water/solution/shui-wei-shang-sheng-de-yong-chi-zhong-y-862o/

然后因为之前做过并查集，所以还复习了一下l684，顺便写了一下并查集的解法，同样看liweiwei的视频
建议先做lc684再做lc778的并查集写法

还有就是对于并查集的时间复杂度是一个难点，需要特别注意：
时间复杂度：O(NlogN)，其中 N 是图中的节点个数。需要遍历图中的 N 条边，对于每条边，
需要对两个节点查找祖先，如果两个节点的祖先不同则需要进行合并，需要进行 2 次查找和最多 1 次合并。
一共需要进行 2N 次查找和最多 N 次合并，因此总时间复杂度是O(2NlogN)=O(NlogN)。
这里的并查集使用了路径压缩，但是没有使用按秩合并，最坏情况下的时间复杂度是 O(NlogN)，
平均情况下的时间复杂度依然是 O(Nα(N))，其中 α 为阿克曼函数的反函数，α(N) 可以认为是一个很小的常数。
* */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class lc778 {
//    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}, grid;
//    private int gLen;
//    boolean[][] visited;
//    public int swimInWater(int[][] grid) {
//        this.grid = grid;
//        gLen = grid.length;
//        visited = new boolean[gLen][gLen];
//        int left = 0, right = gLen * gLen - 1;
//        while (left < right){
//            int mid = left + (right - left) / 2;
//            if(BFS(mid)){
//                right = mid;
//            }else {
//                left = mid + 1;
//            }
//        }
//        return left;
//    }
//
//    private boolean BFS(int mid){
//        for(boolean[] v : visited) Arrays.fill(v, false);
//        Queue<int[]> queue = new LinkedList<>();
//        if(grid[0][0] > mid) return false;
//        queue.add(new int[]{0, 0});
//        visited[0][0] = true;
//        while (!queue.isEmpty()){
//            int qLen = queue.size();
//            for(int i = 0; i < qLen; i++){
//                int[] curRes = queue.poll();
//                if(curRes[0] == gLen - 1 && curRes[1] == gLen - 1 && grid[gLen - 1][gLen - 1] <= mid) return true;
//                for(int[] dir : dirs){
//                    int x = curRes[0] + dir[0];
//                    int y = curRes[1] + dir[1];
//                    if(isValid(x, y)) {
//                        if(grid[x][y] <= mid && !visited[x][y]){
//                            queue.add(new int[]{x, y});
//                            visited[x][y] = true;
//                        }
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//    private boolean isValid(int row1, int col1){
//        return 0 <= row1 && row1 < gLen && 0 <= col1 && col1 < gLen;
//    }


//    方法二：用并查集思路来做
//    这道题目的时间复杂度有点困难：是O(N2*logN)，其中比较难理解哪里来的LogN，
//    可以看最上面的解释，说得很清楚，出处为lc684
    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}, grid;
    private int gLen;
    private int[][] gPos;
    public int swimInWater(int[][] grid) {
        this.grid = grid;
        gLen = grid.length;
        int index = gLen * gLen;
        gPos = new int[index][2];
//        循环作用就是把grid里面的值和坐标一一对应，方便并查集操作
        for(int i = 0; i < gLen; i++){
            for(int k = 0; k < gLen; k++){
                gPos[grid[i][k]][0] = i;
                gPos[grid[i][k]][1] = k;
            }
        }
        unionFound unionFound = new unionFound(gLen);
//        循环的变量就是时间，从grid里面找符合<=时间的格子，
//        看能不能merge grid[0][0]和grid[gLen - 1][gLen - 1]
        for(int i = 0; i < index; i++){
            int row = gPos[i][0];
            int col = gPos[i][1];
            for(int[] dir : dirs){
                int x = row + dir[0];
                int y = col + dir[1];
                if(isValid(x, y)){
                    if(grid[x][y] <= i) {
                        unionFound.merge(grid[row][col], grid[x][y]);
                    }
                }
                int a = unionFound.find(grid[0][0]);
                int b = unionFound.find(grid[gLen - 1][gLen - 1]);
                if(a == b) return i;
            }
        }
        return -1;
    }

    private boolean isValid(int row1, int col1){
        return 0 <= row1 && row1 < gLen && 0 <= col1 && col1 < gLen;
    }

//    并查集class
    private class unionFound{
        int[] union;
        private unionFound(int uLen){
            int count = uLen * uLen;
            union = new int[count];
            for(int i = 0; i < count; i++) union[i] = i;
        }

        private int find(int a){
            while (union[a] != a) a = union[a];
            return a;
        }

        private void merge(int x, int y){
            int xDad = find(x);
            int yDad = find(y);
            union[xDad] = union[yDad];
        }
    }

    public static void main(String[] args) {
        int[][] test = {{0,2},{1,3}};
        System.out.println("正确答案为3， 代码答案为：" + new lc778().swimInWater(test));

//        int[][] test = {{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}};
//        System.out.println("正确答案为16， 代码答案为：" + new lc778().swimInWater(test));

//        int[][] test = {{3,2},{0,1}};
//        System.out.println("正确答案为3， 代码答案为：" + new lc778().swimInWater(test));
    }
}
