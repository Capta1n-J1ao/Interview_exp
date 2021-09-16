package amazon;

/*
这道题目注意有一种情况就是points里面是会有重复点的，这个是很重要的。
然后这道题目用暴力法其实也可以做到很快，就是要用对方法
还有一个需要注意的就是计算斜率的精度问题：
double保留小数点后的位数有限，对于很大又很接近的数会出现误判，所以我们要人为提高double的精度，
将被除数10000000，相当于再提高了好多位的精度，这样对于int范围内的很大又很接近的数就不会出现相同的情况
题解：
https://leetcode-cn.com/problems/max-points-on-a-line/solution/mei-ju-jing-du-wen-ti-de-hua-jiang-bei-c-jaey/

后面又找到一个更加高效的方法，就是和lc652类似，用String作为hashmap里面的key的判断标准，
这样更简洁，代码就是参考以下题解：
https://leetcode-cn.com/problems/max-points-on-a-line/solution/yong-xie-lu-by-powcai/
* */

import java.util.HashMap;

public class lc149 {
    public int maxPoints(int[][] points) {
        int pLen = points.length, res = 0;
        if(pLen < 2) return pLen;
//        注意这里两个for循环的初始值为什么要这么取
        for(int i = 0; i < pLen - 1; i++){
//            注意这里一定要新建hashMap,否则就会重复，
//            所以不能放在一开始声明，而是每次都新声明一个
            HashMap<String, Integer> slopes = new HashMap<>();
            int[] p1 = points[i];
            int curRes = 0 , repeat = 0;
            for(int k = i + 1; k < pLen; k++){
                int[] p2 = points[k];
                int width = p2[0] - p1[0];
                int height = p2[1] - p1[1];
                if(width == 0 && height == 0){
                    repeat++;
                    continue;
                }
                int greatestCommonDivider = gcd(height, width);
                if(greatestCommonDivider != 0){
                    width /= greatestCommonDivider;
                    height /= greatestCommonDivider;
                }
                String str = String.valueOf(height) + '/' + String.valueOf(width);
                slopes.put(str, slopes.getOrDefault(str, 0) + 1);
                curRes = Math.max(curRes, slopes.get(str));
            }
            res = Math.max(res, curRes + repeat + 1);
        }
        return res;
    }

    private int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        int[][] test = {{1,1},{2,2},{3,3}};
        System.out.println(new lc149().maxPoints(test));
    }
}
