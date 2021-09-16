package amazon;

/*
这道题最好预留1 ~ 2小时思考，非常麻烦,而且一定要自己编出来，个人感觉是一个hard难度。
这个解答里面的debug语句可以协助我下面的说明：
https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/solution/zhong-yu-ming-bai-liao-er-fen-fa-de-shou-lian-guo-/

但是代码的话还是看官解：
https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/solution/you-xu-ju-zhen-zhong-di-kxiao-de-yuan-su-by-leetco/

这道题目在思考的时候有三个难点：
1. 既然是用二分法，但又不是个常规二分法，需要决定结构，这个结构需要直接套，自己想不出，
   就是先把matrix里面最大最小值作为二分的初值。自此之后二分得到的mid其实和matrix无关
   但是最后left = right得到的最终答案一定是matrix中的，原因见第二条

2. 当进行二分的时候虽然mid不和matrix一一对应，但是在二分的时候需要对mid在matrix里面的位置有一个判断，
   以题目的例子来说，如果mid算出来是14，但是matrix里面并没有14，那么经过helper以后可以得到false，
   此时right = 14，这时候关键来了，由于这个模板是取的左中位数，所以最后的情况一定是left = 13, right = 14
   然后经过最后一次二分循环得到left = right = 13，无论怎样都会得到matrix中的值的，
   而这也是这道题目的核心所在

3. 这道题目helper函数的编写完全参照lc240，在处理有规律的数组的时候，为什么要从左下角处理

时间复杂度个人认为是：二分法log(n)，然后helper里面在matrix寻找是n+n = 2n ~= n，所以应该是O(nlog(n))
* */

public class lc378 {
    private int[][] matrix;
    private int k, n;
    public int kthSmallest(int[][] matrix, int k) {
        this.matrix = matrix;
        this.k = k;
        this.n = matrix.length;
        int left = matrix[0][0], right = matrix[n - 1][n - 1];
        while (left < right){
            int mid = left + (right - left) / 2;
            if(helper(mid)) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    private boolean helper(int mid){
        int row = n - 1, col = 0, count = 0;
        while (0 <= row && row < n && 0 <= col && col < n){
//            每次col有改动的时候才更新count，
//            所以row如果更新的话先不改count，因为如果改了col会一起改的
//            而且这里的判断符号一定不能错，错了就不对了，和主函数里面的左中位数二分法一致
//            就以默认的test case为例，到了第二个二分法得到是12，这时候就能看出问题
//            核心就是要搞清这个helper函数的意义，他就是在找matrix里面有几个数<= mid
//            而用<=的核心其实就是因为mid是左中位数，要把mid包含进去，
//            这样才符合主函数里面的left = mid + 1，这样就把mid给排除了
//            再换一种说法就是，主函数里面if如果为真，后面的left = mid + 1，是会把mid给排除了
//            那么在if里面的这个判断一定要包含mid才行，否则就会漏掉
//            if(matrix[row][col] < mid){
            if(matrix[row][col] <= mid){
                count += (row + 1);
                col++;
            }else {
                row--;
            }
        }
        return count < k;
    }

    public static void main(String[] args) {
        int[][] test = {{1,5,9},{10,11,13},{12,13,15}};
        System.out.println(new lc378().kthSmallest(test, 8));
    }
}
