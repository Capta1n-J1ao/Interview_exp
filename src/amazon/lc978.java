package amazon;

/*
直接参考Liweiwei题解，建议两种解法都要会，如果使用dp的话同类问题有lc300/53/152/376，
其中978和376最像，都是用一个思路
滑动窗口的话难度会高一些，有时间再搞
https://leetcode-cn.com/problems/longest-turbulent-subarray/solution/zui-chang-tuan-liu-zi-shu-zu-by-leetcode-zqoq/
* */

public class lc978 {
//    public int maxTurbulenceSize(int[] arr) {
//        if(arr.length < 2) return arr.length;
//        int aLen = arr.length, res = 1;
//        int[] increase = new int[aLen];
//        int[] decrease = new int[aLen];
//        increase[0] = 1;
//        decrease[0] = 1;
//        for(int i = 1; i < aLen; i++){
//            if(arr[i - 1] > arr[i]){
//                decrease[i] = increase[i - 1] + 1;
//                increase[i] = 1;
//            }else if(arr[i - 1] < arr[i]){
//                increase[i] = decrease[i - 1] + 1;
//                decrease[i] = 1;
//            }else {
//                increase[i] = 1;
//                decrease[i] = 1;
//            }
//            res = Math.max(res, Math.max(increase[i], decrease[i]));
//        }
//        return res;
//    }

//    方法二，滑动窗口，这里尝试按照模板把right++放在上面，但是会有很多问题
//    特别是对于最后一个数的判断，所以还是下面这个解法最好
    public int maxTurbulenceSize(int[] arr) {
        if(arr.length < 2) return arr.length;
        int aLen = arr.length, left = 0, right = 1, res = 1;
//        这里写了就不用像liweiwei那样要特判right=1情况
        boolean pre = arr[right] > arr[left];
        while (right < aLen){
            boolean cur = arr[right] > arr[right - 1];
//            有以下两种情况需要收缩左边界：
//            1. 三个数字之间的大小关系是同号并且arr[right]与arr[right-1]不相等
//            2. arr[right]与arr[right-1]相等
//            下面这个排除情况一,这个写法更加清晰，体现思路
            if(cur == pre && arr[right] != arr[right - 1]){
                left = right - 1;
//                这一这里不能else if，只能if，因为这样才能排除情况2,
//                而且不能和上面合并，具体问题跑一个testcase就能知道了
//            }else if(arr[right] == arr[right - 1]) {
            }
            if(arr[right] == arr[right - 1]) {
                left = right;
            }
            right++;
            res = Math.max(res, right - left);
            pre = cur;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test = {9,4,2,10,7,8,8,1,9};
        System.out.println(new lc978().maxTurbulenceSize(test));
    }
}
