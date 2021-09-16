package amazon;

/*
完全自己想到的办法，官解和我一样：
https://leetcode-cn.com/problems/gas-station/solution/jia-you-zhan-by-leetcode-solution/

还有一个巧办法的话是使用了题目的如下规则，感兴趣可以看下，但是个人感觉意义不是很大：
1. 必定有一个解
2. 由于是找起点问题，如果gas[i] - cost[i] < 0是一定不能当起点的，所以就排除
* */

public class lc134 {
//    public int canCompleteCircuit(int[] gas, int[] cost) {
//        if(gas == null || gas.length == 0) return -1;
//        int gLen = gas.length;
//        for(int i = 0; i < gLen; i++){
//            int res = 0, count = 1, curGas = gas[i];
//            while (count <= gLen){
//                curGas -= cost[(i + count - 1) % gLen];
////                System.out.println(" i = " + i + " count = " + count + " curGas = " + curGas);
//                if(curGas < 0) break;
//                curGas += gas[(i + count) % gLen];
//                count++;
//            }
//            if(curGas >= 0) return i;
//        }
//        return -1;
//    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas == null || gas.length == 0) return -1;
        int gLen = gas.length, res = 0, totalGas = 0, totalCost = 0;
        int from_i_Start_gasSum = 0;
        for(int i = 0; i < gLen; i++){
            totalGas += gas[i];
            totalCost += cost[i];
//            注意这里不能用isStart来判断，否则这样可能会跳过正确答案
//            看第四个test case
            int isStart = gas[i] - cost[i];
            from_i_Start_gasSum += isStart;
//            if(isStart < 0){
            if(from_i_Start_gasSum < 0){
                res = i + 1;
                from_i_Start_gasSum = 0;
                continue;
            }
        }
        if(totalGas < totalCost) return -1;
        return res;
    }

    public static void main(String[] args) {
//        int[] gas = {1,2,3,4,5};
//        int[] cost = {3,4,5,1,2};
//        System.out.println("正确答案为： 3 ， 代码答案为： " + new lc134().canCompleteCircuit(gas, cost));

//        int[] gas = {5,8,2,8};
//        int[] cost = {6,5,6,6};
//        System.out.println("正确答案为： 3 ， 代码答案为： " + new lc134().canCompleteCircuit(gas, cost));

//        int[] gas = {2,3,4};
//        int[] cost = {3,4,3};
//        System.out.println("正确答案为： -1 ， 代码答案为： " + new lc134().canCompleteCircuit(gas, cost));

        int[] gas = {7,1,0,11,4};
        int[] cost = {5,9,1,2,5};
        System.out.println("正确答案为： 3 ， 代码答案为： " + new lc134().canCompleteCircuit(gas, cost));

    }
}
