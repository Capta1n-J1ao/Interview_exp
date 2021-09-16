package amazon;

/*
题目不难，就是代码很复杂，
还有要注意的是
1. 思考如果是 1 4 2 8 6 5 7这种没有顺序的情况需要怎么计算，这样更加能够充分思考
2. 比如Corner Case的处理

可以参考题解：
https://leetcode-cn.com/problems/cut-off-trees-for-golf-event/solution/java-bfs-you-xian-dui-lie-zhi-xing-yong-shi-435-ms/

有如下注意点：
1. 对 List<List<Integer>> 取size是有非常方便的方法的
2. 对于priorityQueue的定义函数也是有讲究的，要自己写一遍
* */

import java.util.*;

public class lc675 {
    private int row;
    private int col;
    private int[][] dirs = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    private List<List<Integer>> forest;
    public int cutOffTree(List<List<Integer>> forest) {
        this.forest = forest;
        row = forest.size();
        col = forest.get(0).size();
        PriorityQueue<int[]> priorityQueue = new PriorityQueue(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return forest.get(o1[0]).get(o1[1]) - forest.get(o2[0]).get(o2[1]);
            }
        });

        for(int i = 0; i < row; i++){
            for(int k = 0; k < col; k++){
                if(forest.get(i).get(k) > 1) priorityQueue.add(new int[]{i, k});
            }
        }

        int res = 0;
        int[] begin = new int[]{0,0};
        while (!priorityQueue.isEmpty()){
            int[] temp = priorityQueue.poll();
            int curRes = BFS(begin, temp, new boolean[row][col]);
            if(curRes == -1) return -1;
            res += curRes;
            begin = temp;
        }
        return res;
    }

    private int BFS(int[] begin, int[] end, boolean[][] visited){
//        这句话是用来专门解决下面的Corner Case的
        if(begin[0] == end[0] && begin[1] == end[1]) return 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(begin);
        visited[begin[0]][begin[1]] = true;
        int depth = 0;
        while (!queue.isEmpty()){
            depth++;
            int qLen = queue.size();
            for(int i = 0; i < qLen; i++){
                int[] temp = queue.poll();
                for(int[] dir : dirs){
                    int x = temp[0] + dir[0];
                    int y = temp[1] + dir[1];
                    if(x < 0 || x >= row || y < 0 || y >= col || visited[x][y] || forest.get(x).get(y) == 0) continue;
                    if(x == end[0] && y == end[1]) return depth;
                    visited[x][y] = true;
                    queue.add(new int[]{x,y});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List<Integer> list1 = new LinkedList<>();
        list1.add(2);
        list1.add(3);
        list1.add(4);

        List<Integer> list2 = new LinkedList<>();
        list2.add(0);
        list2.add(0);
        list2.add(5);

        List<Integer> list3 = new LinkedList<>();
        list3.add(8);
        list3.add(7);
        list3.add(6);

        List<List<Integer>> list = new LinkedList<>();
        list.add(list1);
        list.add(list2);
        list.add(list3);

        System.out.println(new lc675().cutOffTree(list));
    }
}
