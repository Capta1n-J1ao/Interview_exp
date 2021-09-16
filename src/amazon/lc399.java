package amazon;

/*
这道题目其实一开始就能想到并查集，但是怎么应用并查集是难点，第一次遇到，预留两个小时
这道题目有一个很tricky的地方那就是题目里面会有ab cd这种情况，
本来的想法是用x-a的形式得到一个数字然后进行并查集的计算，
但是有了这个东西以后就不行了，需要在想一个办法，所以用编号的办法更加合适，
也就是题解中的index，给每一个不同的string加一个index
liweiwei题解最清晰：
https://leetcode-cn.com/problems/evaluate-division/solution/399-chu-fa-qiu-zhi-nan-du-zhong-deng-286-w45d/

这道题目tricky的地方太多了，一定要认真看题解，非常难！
1. 这道题目不同于其他的并查集题目，他其实是带有向图的，之前在写并查集的题目的时候其实没有过多关注方向以及路径压缩，
   但是这道题目因为引入了权重，所以必须仔细考虑方向，我为了方便理解，其实改了下代码，
   比如题解里面a/b = 2是b为a的dad，然后weight[a] = 2
   但是我为了方便我自己的理解，改成了a是b的dad，weight[b] = 2,具体可以看代码
2. merge里面那个语句巨难，如果真的要体会，跑一下testcase4,还有一个注意点就是merge其实并不进行路径压缩，
   而是在find进行的，原因的话写一遍代码就清楚了
3. 还是testcase4，有一个地方就是我在进行b/c的merge的时候，其实求的是a/d，这个需要指出，很tricky
   原因的话是因为虽然b/c进行了merge，但是本质是对他们的dad进行merge，因为这样才能够实现对于变量的归一化，
   方便后续的路径压缩，换句话说b/c的merge本质上是bDad/cDad的merge，因为只有dad两者之间关系清楚了
   后面的倍数才能够清楚。所以在整个过程中b/c=8一直没有出现在weight中，而是a/d=6出现在了weight中
4. 由于想要模板的一致性，所以对于并查集的find函数下了很大功夫，参考下面这个题解：
   https://leetcode-cn.com/problems/evaluate-division/solution/javabing-cha-ji-by-zhycome-y8cl/
* */

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class lc399 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int eLen = equations.size(), index = 0;
//        hashMap其实也可以放Unionfind里面，但是不放的话可以最大限度参照模板
        HashMap<String, Integer> hashMap = new HashMap<>();
//        注意这里使用2 * eLen是必须的，因为要考虑equations里面每个都不重复的的情况
        UnionFind unionFind = new UnionFind(2 * eLen);
        for(int i = 0; i < eLen; i++){
            String s1 = equations.get(i).get(0);
            String s2 = equations.get(i).get(1);
            if(!hashMap.containsKey(s1)) {
                hashMap.put(s1, index);
                index++;
            }
            if(!hashMap.containsKey(s2)) {
                hashMap.put(s2, index);
                index++;
            }
            unionFind.merge(hashMap.get(s1), hashMap.get(s2), values[i]);
        }
        int qLen = queries.size();
        double[] res = new double[qLen];
        for(int i = 0; i < qLen; i++){
            String s1 = queries.get(i).get(0);
            String s2 = queries.get(i).get(1);
            Integer index1 = hashMap.get(s1);
            Integer index2 = hashMap.get(s2);
            if(index1 == null || index2 == null) res[i] = -1.0;
            else res[i] = unionFind.calRes(index1, index2);
        }
        return res;
    }

//    这个unionfind的写法和题解略有区别，写了一种我觉得可以接受的思路，但是核心没变
    private class UnionFind{
        private int size;
        private double[] weight;
        private int[] union;
        public UnionFind(int size){
            this.size = size;
            weight = new double[size];
            union = new int[size];
            for(int i = 0; i < size; i++){
                union[i] = i;
                weight[i] = 1.0;
            }
        }

        public void merge(int a, int b, double mul){
            int aDad = find(a);
            int bDad = find(b);
            union[bDad] = aDad;
//            这句话非常难，看题解，可以结合testcase4理解下，然后结合题解再体会一下
            weight[bDad] = weight[a] * mul / weight[b];
        }

//        为了模板一致，这个find函数找了很多题解才找到一个写法，很tricky!
        private int find(int son){
            int curRes = son;
            while (union[son] != son) {
                son = union[son];
                weight[curRes] *= weight[son];
//                下面这句可以不要，很容易出错，跑一下testcase3就懂了
//                在循环外直接赋值最终的dad就行了
//                union[curRes] = union[son];
            }
            union[curRes] = son;
            return son;
        }

        private double calRes(int a, int b){
            int aDad = find(a);
            int bDad = find(b);
            if(aDad == bDad) return weight[b]/weight[a];
            else return -1.0;
        }
    }

    public static void main(String[] args) {
//        答案是6.0,0.5
//        List<String> l1 = new LinkedList<>();
//        l1.add("a");
//        l1.add("b");
//        List<String> l2 = new LinkedList<>();
//        l2.add("b");
//        l2.add("c");
//        List<List<String>> list = new LinkedList<>();
//        list.add(l1);
//        list.add(l2);
//        double[] val = {2.0, 3.0};
//        List<String> l3 = new LinkedList<>();
//        l3.add("a");
//        l3.add("c");
//        List<String> l4 = new LinkedList<>();
//        l4.add("b");
//        l4.add("a");
//        List<List<String>> list1 = new LinkedList<>();
//        list1.add(l3);
//        list1.add(l4);
//        System.out.println(Arrays.toString(new lc399().calcEquation(list, val, list1)));

//        答案是1.3333
//        List<String> l1 = new LinkedList<>();
//        l1.add("a");
//        l1.add("e");
//        List<String> l2 = new LinkedList<>();
//        l2.add("b");
//        l2.add("e");
//        List<List<String>> list = new LinkedList<>();
//        list.add(l1);
//        list.add(l2);
//        double[] val = {4.0, 3.0};
//        List<String> l3 = new LinkedList<>();
//        l3.add("a");
//        l3.add("b");
//        List<List<String>> list1 = new LinkedList<>();
//        list1.add(l3);
//        System.out.println(Arrays.toString(new lc399().calcEquation(list, val, list1)));

//        答案是360,0.00833， 20
//        List<String> l1 = new LinkedList<>();
//        l1.add("x1");
//        l1.add("x2");
//        List<String> l2 = new LinkedList<>();
//        l2.add("x2");
//        l2.add("x3");
//        List<String> l3 = new LinkedList<>();
//        l3.add("x3");
//        l3.add("x4");
//        List<String> l4 = new LinkedList<>();
//        l4.add("x4");
//        l4.add("x5");
//        List<List<String>> list = new LinkedList<>();
//        list.add(l1);
//        list.add(l2);
//        list.add(l3);
//        list.add(l4);
//        double[] val = {3.0, 4.0, 5.0, 6.0};
//        List<String> l5 = new LinkedList<>();
//        l5.add("x1");
//        l5.add("x5");
//        List<String> l6 = new LinkedList<>();
//        l6.add("x5");
//        l6.add("x2");
//        List<String> l7 = new LinkedList<>();
//        l7.add("x2");
//        l7.add("x4");
//        List<List<String>> list1 = new LinkedList<>();
//        list1.add(l5);
//        list1.add(l6);
//        list1.add(l7);
//        System.out.println(Arrays.toString(new lc399().calcEquation(list, val, list1)));

//        答案是6.0
        List<String> l1 = new LinkedList<>();
        l1.add("a");
        l1.add("b");
        List<String> l2 = new LinkedList<>();
        l2.add("d");
        l2.add("c");
        List<String> l3 = new LinkedList<>();
        l3.add("b");
        l3.add("c");
        List<List<String>> list = new LinkedList<>();
        list.add(l1);
        list.add(l2);
        list.add(l3);
        double[] val = {3.0, 4.0, 8.0};
        List<String> l4 = new LinkedList<>();
        l4.add("a");
        l4.add("d");
        List<List<String>> list1 = new LinkedList<>();
        list1.add(l4);
        System.out.println(Arrays.toString(new lc399().calcEquation(list, val, list1)));

    }
}
