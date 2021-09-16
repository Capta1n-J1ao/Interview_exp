package amazon;

/*
我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。

（这里，平面上两点之间的距离是欧几里德距离。）

你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。

 

示例 1：

输入：points = [[1,3],[-2,2]], K = 1
输出：[[-2,2]]
解释：
(1, 3) 和原点之间的距离为 sqrt(10)，
(-2, 2) 和原点之间的距离为 sqrt(8)，
由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
示例 2：

输入：points = [[3,3],[5,-1],[-2,4]], K = 2
输出：[[3,3],[-2,4]]
（答案 [[-2,4],[3,3]] 也会被接受。）

方法一： 使用PriorityQueue的方法
带priorityqueue的自定义排序器设置如下，查看lc215可以看到详细的设置方法：
https://leetcode-cn.com/problems/k-closest-points-to-origin/solution/priorityqueuezui-xiao-dui-by-volcanno/

时间复杂度：O(n) = N * log(N) 或者 N * log(k)。
其中 nn 是数组 \textit{points}points 的长度。由于优先队列维护的是前 KK 个距离最小的点，
因此弹出和插入操作的单次时间复杂度均为 O(\log K)O(logK)。
在最坏情况下，数组里 nn 个点都会插入，因此时间复杂度为 O(n\log K)O(nlogK)。

空间复杂度：O(n) = O(k)。

方法二： 快速排序
花了两天时间排查除了问题，并且写了两种版本的patition，且全部通过，非常不容易，并且总结心得如下：
1. lc215的视频方法没有问题：https://www.bilibili.com/video/BV15Z4y1p7KR?t=412
2. 对于patition来说用随机数的方法并不好debug，有了错误也不容易使用，
   所以从此以后不要使用随机数作为pivot，而是使用patition的start或者end作为pivot
3. 对于取start或者end作为pivot代码的细节是不一样的，建议使用test3进行debug就能看出问题，
   并且在注释里面我也会写清楚
4. 对于模板来说，优先选择使用end作为pivot的，因为start作为pivot的情况需要多一句
   if(start == end) return start;来处理corner case。代码会有详细注释
5. 时间复杂度 ≈ O(n)， 空间复杂度 ≈ O(1)
* */

import java.util.*;

public class lc973 {
    /*
    public int[][] kClosest(int[][] points, int K) {
//        通过看源文件可以知道，新建比较器的时候，那个K代表的是initialCapacity,但是我试了下，写一个大于1的都可以
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(K, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int dis1 = o1[0] * o1[0] + o1[1] * o1[1];
                int dis2 = o2[0] * o2[0] + o2[1] * o2[1];
//                大根堆
                return dis2 - dis1;
//                小根堆
//                return dis1 - dis2;
            }
        });

//        时间复杂度 O(n) = N * log(N) 的方法，要记得把上面的构造器改成return dis2 - dis1;
//        for(int[] point : points){
//            priorityQueue.add(point);
//        }

//        时间复杂度 O(n) = N * log(k) 的方法
        for(int[] point : points){
            if(priorityQueue.size() < K){
                priorityQueue.add(point);
            }else if(priorityQueue.comparator().compare(point, priorityQueue.peek()) > 0){
                priorityQueue.poll();
                priorityQueue.add(point);
            }

        }

        int[][] res = new int[K][2];
        for(int i = 0; i < K; i++){
            res[i] = priorityQueue.poll();
        }
        return res;
    }

     */


    private int[][] points;
    public int[][] kClosest(int[][] points, int K) {
        this.points = points;
        int pLen = points.length;
        int start = 0, end = pLen - 1, cur = -1;
        while (cur != K - 1){
            cur = patition(start, end);
            if(cur > K - 1){
                end = cur - 1;
            }else if(cur < K - 1){
                start = cur + 1;
            }else break;
        }
        return Arrays.copyOfRange(points, 0, K);
    }

//    三刷尝试写一版随机数的,这里面的逻辑极度容易错，特别是while里面的语句
    private int patition(int left, int right){
        int lo = left, hi = right - 1, pivot = left  + (int)(Math.random() * (right - left + 1));
        swap(pivot, right);
        while (lo < hi){
//            注意下面那个while是>而不是>=，还有就是由于pivot和right换了位置，所以始终都是hi,lo和right在比，别搞错！！
            while (lo < hi && distance(lo) <=  distance(right)) lo++;
            while (lo < hi && distance(hi) > distance(right)) hi--;
            swap(lo, hi);
        }
        if(distance(lo) > distance(right)){
            swap(lo, right);
            return lo;
        }
        return right;
    }


//    private int patition(int start, int end){
////        使用end作为pivot，那么要注意while循环里面的while要以如下顺序进行遍历，
////        否则就会出错，有点类似于二分法的mid选取，即左中位数和右中位数问题
//        int lo = start, hi = end - 1, pivot = distance(end);
//        while (lo < hi){
////            以下两句话的顺序需要严格遵守，否则就不对，用test3debug下就知道为什么了，
//// 类似于二分法取mid问题就是因为这个原因debug了一天多
//            while (lo < hi && distance(lo) <= pivot) lo++;
//            while (lo < hi && distance(hi) > pivot) hi--;
//            swap(lo, hi);
//        }
////        针对corner case，视频中也提到了,而且两个版本的判断条件也不同
//        if(pivot < distance(lo)){
//            swap(lo,end);
//            return lo;
//        }
//        return end;
//    }

//    private int patition(int start, int end){
////        这句话是针对start为pivot特有的，end为pivot则不会有这个问题，用test4可验证
////        *******所以默认模板希望用end为pivot的*********
//        if(start == end) return start;
////        使用start作为pivot，那么要注意while循环里面的while要以如下顺序进行遍历，
////        否则就会出错，有点类似于二分法的mid选取，即左中位数和右中位数问题
//        int lo = start + 1, hi = end, pivot = distance(start);
//        while (lo < hi){
////            以下两句话的顺序需要严格遵守，否则就不对
//            while (lo < hi && distance(hi) > pivot) hi--;
//            while (lo < hi && distance(lo) <= pivot) lo++;
//            swap(lo, hi);
//        }
//        if(pivot > distance(lo)){
//            swap(lo, start);
//            return lo;
//        }
//        return start;
//    }


    private void swap(int start, int end){
        int[] temp = points[start];
        points[start] = points[end];
        points[end] = temp;
    }

    private int distance(int index){
        return points[index][0] * points[index][0] + points[index][1] * points[index][1];
    }

    public static void main(String[] args) {
//        int[][] test = {{3,3},{5,-1},{-2,4}};
        int[][] test1 = {{2,2},{2,2},{2,2},{2,2},{2,2},{2,2},{1,1}};
//        int[][] test2 = {{-4,-7},{6,7},{-8,-5},{6,9},{-9,-4},{-9,-8},{-10,-6}};
//        int[][] test3 = {{89, 6}, {-39, -4}, {-13, 91}, {97, -61}, {1, 7}, {-66, 69}, {-51, 68}, {82, -6}, {-21, 44}, {-58, -83}, {-40, 73}, {-88, -24}};
//        int[][] test4 = {{0,1},{1,0}};
//        System.out.println(Arrays.toString(new lc973().kClosest(test, 2)));
        System.out.println(Arrays.toString(new lc973().kClosest(test1, 1)));
//        System.out.println(Arrays.toString(new lc973().kClosest(test2, 6)));
//        System.out.println(Arrays.toString(new lc973().kClosest(test3, 8)));
//        System.out.println(Arrays.toString(new lc973().kClosest(test4, 2)));
    }
}
