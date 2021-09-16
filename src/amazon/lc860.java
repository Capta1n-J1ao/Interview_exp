package amazon;

/*
这道题目其实唯一的算法就是贪心，其实我的写法也是属于贪心的一部分
就是用digit来进行选择，注意digit从1开始就行了，没必要从2开始，target最大值为15
所以20元永远不可能作为找零

相关题解可以参考，思路和我的完全一样，只是因为一开始就想用贪心的思路，代码比我的简洁：
https://leetcode-cn.com/problems/lemonade-change/solution/ning-meng-shui-zhao-ling-by-leetcode-sol-nvp7/
* */

public class lc860 {
    public boolean lemonadeChange(int[] bills) {
        if(bills == null || bills.length == 0) return true;
//        int res = 0;
        int bLen = bills.length;
//        change一共有三位，分别代表5/10/20元
        int[] change = new int[3];
        for(int bill : bills){
            if(bill == 5) change[0] ++;
            else{
                int digit = 1;
                int target = bill - 5;
                while (target > 0 && digit >= 0){
                    if(change[digit] > 0) {
                        int toCharge = 0;
                        if(digit == 0)toCharge = 5;
                        else if(digit == 1) toCharge = 10;
                        else toCharge = 20;
                        if(target >= toCharge) {
                            target -= toCharge;
                            change[digit]--;
                        }else digit--;
                    }
                    else digit--;
                }
                if(target > 0 || digit < 0) return false;
                if(bill == 10) change[1]++;
                if(bill == 20) change[2]++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] test = {5,5,10,10,20};
        System.out.println(new lc860().lemonadeChange(test));

//        int[] test1 = {5,5,5,10,20};
//        System.out.println(new lc860().lemonadeChange(test1));
    }
}
