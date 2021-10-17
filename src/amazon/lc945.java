package amazon;

/*
Twitter OA,首刷，我一开始想到的就是最简单的那种办法，但是有个问题那就是太简单，并且时间复杂度较高
用并查集的话能够瞬间降低时间复杂度，所以这里尝试用并查集写。
原因很简单，并查集是一个O(N)时间复杂度，常规方法是一个O(NLogN)
然后在做这道题目的时候思考{3,2,1,7,3,7}这个case，怎么样在7进来的时候海能保证4,5,6能在7之后被填上
liweiwei 题解：
https://leetcode-cn.com/problems/minimum-increment-to-make-array-unique/solution/tan-xin-suan-fa-bing-cha-ji-java-by-liweiwei1419/
* */

import java.util.Arrays;

public class lc945 {
    private int[] nums;
    public int minIncrementForUnique(int[] nums) {
        this.nums = nums;
        int numLen = nums.length, res = 0;
        if(numLen < 2) return 0;
        UnionFind unionFind = new UnionFind();
        for(int num : nums){
            if(!unionFind.contains(num)){
                unionFind.init(num);
            }else {
                int dad = unionFind.find(num);
                int maxDad = dad + 1;
                res += (maxDad - num);
                unionFind.init(maxDad);
            }
            System.out.println(Arrays.toString(unionFind.union));
        }
        return res;
    }

    public class UnionFind{
        private int[] union;
        public UnionFind(){
//            这里是存实际的数,要注意如果用这个数组大小跑java visualizer的话会死机，记得改小
//            this.union = new int[7];
//            题目不准确，已经到100010了
            this.union = new int[110000];
            Arrays.fill(union, -1);
        }

        public void init(int a){
            union[a] = a;
            if(a > 0 && union[a - 1] != -1) union(a, a - 1);
            if(a < 109999 && union[a + 1] != -1) union(a, a + 1);
        }

        public void union(int a, int b){
            int aDad = find(a);
            int bDad = find(b);
            if(aDad < bDad) union[aDad] = bDad;
            else if(bDad < aDad) union[bDad] = aDad;
        }

        public int find(int a){
            while (a != union[a]){
//                下面这句是路径压缩，还挺关键的,能省不少时间
                union[a] = union[union[a]];
                a = union[a];
            }
            return a;
        }

        public boolean contains(int a){
            return union[a] != -1;
        }
    }

    public static void main(String[] args) {
        int[] test = {1,2,2};
        System.out.println(new lc945().minIncrementForUnique(test));
    }
}
