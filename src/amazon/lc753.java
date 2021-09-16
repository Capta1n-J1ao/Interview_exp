package amazon;

/*
这道题目理解起来很费劲，建议看一下下面的文字题解，先理解一下题目

文字版的解释：
读不懂题的我来解释一下： 当n=1, k=2的时候，就是说密码是1位，可能是0，也可能是1，那么这个答案就应该包含0，
也包含1，顺序不要紧，只要按照这答案输入，遇到对的情况自然会打开。
继续看n=2, k=2，那么密码就可能是01、10、00、11，下面任意一个密码都应该包含这四种情况，
看一下答案，"00110" , "01100", "10011", "11001"，
是不是这四种答案每个里边都包含这个子串？
再看n=3, k=2，那么密码就应该包含000、001、010、011...还有几个我就不写了，
可以看一下答案，答案肯定是包含这几个串的：0011101000，然后大家再看题解理解一下。


不要用什么欧拉或者汉密尔顿路径来解，顶多用BackTracking或者DFS来解
而且BackTracking的思路更加自然，而DFS的思路有点巧妙，不容易想到
如果想省时间直接方法二，知道这一种方法也够了

方法二用的是BackTracking，思路参考huahua
代码完全是靠自己写的，但是会发现有一个问题那就是回溯的时候要从hashset里面删元素，这个效率很低
C++可以这么做，从之前归并题目的经验来看是因为C++的hashSet其实用到了TreeMap类似的，搜索和删除效率高
https://www.bilibili.com/video/BV1PW411y7Qz?from=search&seid=9020933221894382526

方法一的java版本，是在youtube上找到的，用的DFS，可以喝方法二比较一下思路
难点就是怎么把res给拼接起来，视频这个方法其实很巧妙，如果看不懂就用testcase跑下代码：
https://www.youtube.com/watch?v=6VSWohXUZMQ

文字版的解释：
读不懂题的我来解释一下： 当n=1, k=2的时候，就是说密码是1位，可能是0，也可能是1，那么这个答案就应该包含0，
也包含1，顺序不要紧，只要按照这答案输入，遇到对的情况自然会打开。
继续看n=2, k=2，那么密码就可能是01、10、00、11，下面任意一个密码都应该包含这四种情况，
看一下答案，"00110" , "01100", "10011", "11001"，
是不是这四种答案每个里边都包含这个子串？
再看n=3, k=2，那么密码就应该包含000、001、010、011...还有几个我就不写了，
可以看一下答案，答案肯定是包含这几个串的：0011101000，然后大家再看题解理解一下。
* */

import java.util.HashSet;

public class lc753 {
//    方法一：DFS，这个方法和方法二的思路是相似的，但是顺序是相反的，是反着探索答案
//    private HashSet<String> visited = new HashSet<>();
//    private  StringBuilder res = new StringBuilder();
//    private int k;
//    public String crackSafe(int n, int k) {
//        this.k = k;
//        if(n == 1 && k == 1) return "0";
//        StringBuilder sb = new StringBuilder();
//        for(int i = 0; i < n - 1; i++){
//            sb.append("0");
//        }
//        String start = sb.toString();
//        dfs(start);
//        res.append(start);
//        return res.toString();
//    }
//
//    private void dfs(String node){
//        for(int i = 0; i < k; i++){
//            String val = node + i;
//            if(!visited.contains(val)){
//                visited.add(val);
//                dfs(val.substring(1));
//                res.append(i);
//                System.out.println(res);
//            }
//        }
//    }

//  方法二：回溯，这个方法和上面那个方法的思路是相反的，这个方法是正着去探索
//    推荐用这个方法，好理解
    private HashSet<String> visited = new HashSet<>();
    private  StringBuilder res = new StringBuilder();
    private int k,n, maxCount;
    public String crackSafe(int n, int k) {
        this.k = k;
        this.n = n;
        maxCount = (int) Math.pow(k, n);
        if(n == 1 && k == 1) return "0";
        for(int i = 0; i < n; i++){
            res.append("0");
        }
        visited.add(res.toString());
        if(BackTracking(res)) return res.toString();
        return "";
    }

    private boolean BackTracking(StringBuilder curRes){
        if(visited.size() == maxCount) return true;
        int rLen = curRes.length();
        StringBuilder sb = new StringBuilder();
//        注意这里是一个细节，是怎么取substring得
//        其实思想就是从当前res中取最后n-1位，然后通过叠加不同的ch组成新的n位答案
        sb.append(curRes.substring(rLen - (n - 1), rLen));
        for(char ch = '0'; ch < '0' + k; ch++){
            sb.append(ch);
            String toFind = sb.toString();
            if(!visited.contains(toFind)){
                res.append(ch);
                visited.add(toFind);
                if(BackTracking(res)) {
//                    System.out.println(res);
                    return true;
                }
                visited.remove(toFind);
                res.deleteCharAt(res.length() - 1);
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(new lc753().crackSafe(1, 2));
        System.out.println(new lc753().crackSafe(2, 2));
    }
}
